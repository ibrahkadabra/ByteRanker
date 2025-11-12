plugins {
  id("org.springframework.boot") version "3.3.2"
  id("io.spring.dependency-management") version "1.1.5"
  jacoco
  java
}

java {
  toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-amqp")
  implementation("org.springframework.boot:spring-boot-starter-logging")
  implementation("software.amazon.awssdk:s3:2.25.60")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
  useJUnitPlatform()
  finalizedBy(tasks.jacocoTestReport, tasks.jacocoTestCoverageVerification)
}

jacoco { toolVersion = "0.8.10" }

tasks.jacocoTestReport {
  reports { xml.required.set(true); html.required.set(true) }
}

tasks.jacocoTestCoverageVerification {
  violationRules {
    rule { limit { counter = "LINE"; value = "COVEREDRATIO"; minimum = "0.80".toBigDecimal() } }
  }
}
