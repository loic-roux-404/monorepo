package com.abclever.integrationtesting

import com.abclever.auth.model.user.User
import com.github.tomakehurst.wiremock.*
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
import com.abclever.integrationtesting.appLauncher.*
import com.abclever.integrationtesting.http.post
import io.cucumber.java8.Scenario

class AuthStepDefinitions(
  @Autowired private var wireMockServer: WireMockServer,
  @Autowired private var properties: AppsConfigurationProperties
) : En {

  init {

    var processes: Map<String, Process> = mapOf()

    Given("start auth test env") {
      // Initial add of clients
      processes = launch(listOf("auth-server"))

      post(properties.fullRoute("auth-server", "/client"), mapOf(
        "client_id" to "toto",
        "client_secret" to "test"
      ))

      // restart to refresh clients
      processes = restart(processes)
    }

    var users: List<User>? = null

    DataTableType { entry: Map<String, String> ->
      return@DataTableType User(
        entry["id"]?.toLong()!!,
        entry["firstName"],
        entry["lastName"],
        entry["password"],
        entry["picture"],
        entry["email"],
        entry["emailVerified"],
        Date.valueOf(entry["updatedAt"]),
        entry["gender"]?.toInt()!!,
        Date.valueOf(entry["createdAt"]),
        Date.valueOf(entry["birthdate"]),
      )
    }

    Given("non registered users") { userTable: DataTable ->
      users = userTable.asList(User::class.java)
    }

    AfterStep { _: Scenario ->
      if (processes.isNotEmpty()) kill(processes)
    }

  }
}
