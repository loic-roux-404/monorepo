package com.abclever.integrationtesting

import org.springframework.boot.test.context.SpringBootTest
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(
  classes = [IntegrationtestingApplication::class],
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
@ContextConfiguration(classes = [WireMockConfiguration::class])
@CucumberContextConfiguration
@EnableConfigurationProperties(AppsConfigurationProperties::class)
class IntegrationtestingApplicationTests {

  @Autowired
  private lateinit var properties: AppsConfigurationProperties

  init {

  }
}
