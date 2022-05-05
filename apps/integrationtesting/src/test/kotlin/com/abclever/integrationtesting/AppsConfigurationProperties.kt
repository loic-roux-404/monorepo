package com.abclever.integrationtesting

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("apps")
data class AppsConfigurationProperties(
  val map: Map<String, String>
) {

  fun fullRoute(app: String, route: String): String {
    val url: String = map[app]!!

    return "http://${url}${route}"
  }
}
