import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
  application
  id("com.github.ben-manes.versions") version "0.42.0"
  id("se.patrikerdes.use-latest-versions") version "0.2.18"
}

buildDir = file("gradle-build")

sourceSets {
  map {
    it.java {
      exclude("com/fillmore_labs/example/validation/jee9/**")
    }
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

application {
  mainClass.set("org.openjdk.jmh.Main")
  applicationDefaultJvmArgs = listOf(
    "-Djava.util.logging.config.file=conf/logging.properties",
    "-XX:+CrashOnOutOfMemoryError",
  )
}

tasks {
  test {
    testLogging {
      events(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
    }
  }
}

repositories {
  mavenCentral()
  google()
}

dependencies {
  compileOnly("org.projectlombok:lombok:1.18.22")
  annotationProcessor("org.projectlombok:lombok:1.18.22")

  compileOnly("org.immutables:value-annotations:2.9.0")
  annotationProcessor("org.immutables:value-processor:2.9.0")

  implementation("org.openjdk.jmh:jmh-core:1.35")
  annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.35")

  compileOnly("org.checkerframework:checker-qual:3.21.4")
  testCompileOnly("org.checkerframework:checker-qual:3.21.4")

  implementation("com.google.guava:guava:31.1-jre")
  implementation("jakarta.validation:jakarta.validation-api:2.0.2")
  runtimeOnly("org.glassfish:jakarta.el:3.0.4")
  runtimeOnly("org.hibernate.validator:hibernate-validator:6.2.3.Final")
  runtimeOnly("org.ow2.asm:asm:9.3")

  testImplementation("junit:junit:4.13.2")
  testImplementation("com.google.truth:truth:1.1.3")
  testImplementation("com.google.truth.extensions:truth-java8-extension:1.1.3")
  testImplementation("nl.jqno.equalsverifier:equalsverifier:3.10")
}

tasks.withType<DependencyUpdatesTask> {
  rejectVersionIf {
    candidate.group == "jakarta.validation"
      && candidate.module == "jakarta.validation-api"
      && !candidate.version.startsWith("2.")

      || candidate.group == "org.glassfish"
      && candidate.module == "jakarta.el"
      && !candidate.version.startsWith("3.")

      || candidate.group == "org.hibernate.validator"
      && candidate.module == "hibernate-validator"
      && !candidate.version.startsWith("6.")
  }
}
