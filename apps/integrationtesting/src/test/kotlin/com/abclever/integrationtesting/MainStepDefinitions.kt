package com.abclever.integrationtesting

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import org.junit.jupiter.api.Assertions.*
import org.opentest4j.TestAbortedException
import org.springframework.beans.factory.annotation.Autowired
import com.abclever.integrationtesting.http.*

var lastInstance: MainStepDefinitions? = null

class MainStepDefinitions(
  @Autowired private var properties: AppsConfigurationProperties
) : En {

  init {

    Before { _: Scenario ->
      assertNotSame(this, lastInstance)
      lastInstance = this
    }

    BeforeStep { _: Scenario ->
      assertSame(this, lastInstance)
      lastInstance = this
    }

    AfterStep { _: Scenario ->
      assertSame(this, lastInstance)
      lastInstance = this
    }

    After { _: Scenario ->
      assertSame(this, lastInstance)
      lastInstance = this
    }

    Then("app {word} on route {word} status is {int}") { app: String, route: String, code: Int ->
      assertEquals(get(properties.fullRoute(app, route)), code)
    }

    DataTableType { entry: Map<String, String> ->
      return@DataTableType Person(
        entry["first"],
        entry["last"]
      )
    }

    Given("this data table:") { peopleTable: DataTable ->
      val people: List<Person> = peopleTable.asList(Person::class.java)
      assertEquals("Aslak", people[0].first)
      assertEquals("Hellesoy", people[0].last)
    }

    val alreadyHadThisManyCukes = 1
    Given("I have {double} cukes in my belly") { n: Double ->
      assertEquals(1, alreadyHadThisManyCukes)
      assertEquals(42.00, n)
    }

    val localState = "hello"
    Then("I really have {int} cukes in my belly") { i: Int ->
      assertEquals(42, i)
      assertEquals("hello", localState)
    }

    Given("A statement with a body expression") { assertTrue(true) }

    Given("A statement with a simple match") { assertTrue(true) }

    Given("something that is skipped") { throw TestAbortedException("skip this!") }

    val localInt = 1
    Given("A statement with a scoped argument") { assertEquals(2, localInt + 1) }

    Given(
      "I will give you {int} and {float} and {word} and {int}")
    { a: Int, b: Float, c: String, d: Int ->
      assertEquals(1, a)
      assertEquals(2.2f, b)
      assertEquals("three", c)
      assertEquals(4, d)
    }
  }

}

data class Person(val first: String?, val last: String?)
