import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	war
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	id("com.diffplug.spotless") version "6.2.2"
}

group = "com.abclever"
version = System.getenv("APP_VERSION") ?: "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
  val cucumberVersion = "7.2.3"
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  testImplementation("org.junit.platform:junit-platform-suite-api:1.8.2")
  testImplementation("org.junit.platform:junit-platform-console:1.8.2")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")
  testImplementation("io.cucumber:cucumber-java8:$cucumberVersion")
  testImplementation("io.cucumber:cucumber-junit:$cucumberVersion")
  testImplementation("org.assertj:assertj-guava:3.4.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
  systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

// Hack to use junit platform with cucumber
tasks {

  val consoleLauncherTest by registering(JavaExec::class) {
    dependsOn(testClasses)
    val reportsDir = file("$buildDir/test-results")
    outputs.dir(reportsDir)
    classpath = sourceSets["test"].runtimeClasspath
    main = "org.junit.platform.console.ConsoleLauncher"
    args("--scan-classpath")
    args("--include-engine", "cucumber")
    args("--reports-dir", reportsDir)
  }

  test {
    dependsOn(consoleLauncherTest)
    exclude("**/*")
  }
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {

    format("misc") {
        // define the files to apply 'misc' to
        target("*.md", ".gitignore")

        // define the steps to apply to those files
        trimTrailingWhitespace()
        indentWithTabs() // or spaces. Takes an integer argument if you don't like 4
        endWithNewline()
    }

    kotlin { // to customize, go to https://github.com/diffplug/spotless/tree/main/plugin-gradle#kotlin

        // Apply ktfmt formatter(similar to google-java-format, but for Kotlin)
        ktfmt()
    }
    kotlinGradle {
        target("*.gradle.kts") // default target for kotlinGradle
        ktfmt()
    }
}
