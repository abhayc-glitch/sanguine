import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "com.server"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.1.2"
val junitJupiterVersion = "5.7.0"

val mainVerticleName = "com.server.backend.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-service-proxy")
  implementation("io.vertx:vertx-auth-htdigest")
  implementation("io.vertx:vertx-service-discovery")
  implementation("io.vertx:vertx-hazelcast")
  implementation("io.vertx:vertx-auth-oauth2")
  implementation("io.vertx:vertx-reactive-streams")
  implementation("io.vertx:vertx-jdbc-client")
  implementation("io.vertx:vertx-web-templ-handlebars")
  implementation("io.vertx:vertx-web-templ-pebble")
  implementation("io.vertx:vertx-web-sstore-cookie")
  implementation("io.vertx:vertx-lang-kotlin-coroutines")
  implementation("io.vertx:vertx-auth-sql-client")
  implementation("io.vertx:vertx-infinispan")
  implementation("io.vertx:vertx-web-sstore-redis")
  implementation("io.vertx:vertx-web-validation")
  implementation("io.vertx:vertx-auth-ldap")
  implementation("io.vertx:vertx-auth-jwt")
  implementation("io.vertx:vertx-auth-mongo")
  implementation("io.vertx:vertx-http-service-factory")
  implementation("io.vertx:vertx-rx-java3")
  implementation("io.vertx:vertx-web-templ-rocker")
  implementation("io.vertx:vertx-mqtt")
  implementation("io.vertx:vertx-auth-htpasswd")
  implementation("io.vertx:vertx-zipkin")
  implementation("io.vertx:vertx-rx-java2")
  implementation("io.vertx:vertx-cassandra-client")
  implementation("io.vertx:vertx-auth-properties")
  implementation("io.vertx:vertx-opentelemetry")
  implementation("io.vertx:vertx-circuit-breaker")
  implementation("io.vertx:vertx-web-sstore-infinispan")
  implementation("io.vertx:vertx-mail-client")
  implementation("io.vertx:vertx-consul-client")
  implementation("io.vertx:vertx-kafka-client")
  implementation("io.vertx:vertx-web-client")
  implementation("io.vertx:vertx-amqp-client")
  implementation("io.vertx:vertx-health-check")
  implementation("io.vertx:vertx-web-templ-jade")
  implementation("io.vertx:vertx-web-openapi")
  implementation("io.vertx:vertx-camel-bridge")
  implementation("io.vertx:vertx-stomp")
  implementation("io.vertx:vertx-tcp-eventbus-bridge")
  implementation("io.vertx:vertx-opentracing")
  implementation("io.vertx:vertx-dropwizard-metrics")
  implementation("io.vertx:vertx-web-templ-httl")
  implementation("io.vertx:vertx-auth-shiro")
  implementation("io.vertx:vertx-ignite")
  implementation("io.vertx:vertx-pg-client")
  implementation("io.vertx:vertx-auth-webauthn")
  implementation("io.vertx:vertx-rx-java")
  implementation("io.vertx:vertx-mongo-client")
  implementation("io.vertx:vertx-web-templ-freemarker")
  implementation("io.vertx:vertx-web-templ-thymeleaf")
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-zookeeper")
  implementation("io.vertx:vertx-mysql-client")
  implementation("io.vertx:vertx-micrometer-metrics")
  implementation("io.vertx:vertx-json-schema")
  implementation("io.vertx:vertx-shell")
  implementation("io.vertx:vertx-web-api-contract")
  implementation("io.vertx:vertx-web-templ-mvel")
  implementation("io.vertx:vertx-redis-client")
  implementation("io.vertx:vertx-config")
  implementation("io.vertx:vertx-web-graphql")
  implementation("io.vertx:vertx-maven-service-factory")
  implementation("io.vertx:vertx-auth-jdbc")
  implementation("io.vertx:vertx-rabbitmq-client")
  implementation("junit:junit:4.13.1")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  isZip64 = true
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}


tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<Test> {
  useJUnitPlatform()
}
