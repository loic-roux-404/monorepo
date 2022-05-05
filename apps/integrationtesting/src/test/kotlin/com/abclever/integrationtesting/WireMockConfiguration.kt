package com.abclever.integrationtesting

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WireMockConfiguration {

  @Bean
  fun wireMock(): WireMockServer {

    val wmServer = WireMockServer(WireMockConfiguration().dynamicPort())
    wmServer.start()

    return wmServer
  }
}
