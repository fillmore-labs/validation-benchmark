import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
  application
  id("com.github.ben-manes.versions")
  id("se.patrikerdes.use-latest-versions")
}

buildDir = file("gradle-build")

sourceSets {
  map {
    it.java {
      setSrcDirs(rootProject.sourceSets.getByName(it.name).java.srcDirs)
      exclude(
        "com/fillmore_labs/example/validation/benchmark/**",
        "com/fillmore_labs/example/validation/self_validating/**",
        "com/fillmore_labs/example/validation/validated/**",
      )
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
  google()
  mavenCentral()
}

dependencies {
  compileOnly("org.projectlombok:lombok:1.18.22")
  annotationProcessor("org.projectlombok:lombok:1.18.22")

  compileOnly("org.immutables:value-annotations:2.9.0-rc1")
  annotationProcessor("org.immutables:value-processor:2.9.0-rc1")

  implementation("org.openjdk.jmh:jmh-core:1.33")
  annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.33")

  compileOnly("org.checkerframework:checker-qual:3.19.0")
  testCompileOnly("org.checkerframework:checker-qual:3.19.0")

  implementation("com.google.guava:guava:31.0.1-jre")
  implementation("jakarta.validation:jakarta.validation-api:3.0.1")
  runtimeOnly("org.glassfish:jakarta.el:4.0.2")
  runtimeOnly("org.hibernate.validator:hibernate-validator:7.0.1.Final")
  runtimeOnly("org.ow2.asm:asm:9.2")

  testImplementation("junit:junit:4.13.2")
  testImplementation("com.google.truth:truth:1.1.3")
  testImplementation("com.google.truth.extensions:truth-java8-extension:1.1.3")
  testImplementation("nl.jqno.equalsverifier:equalsverifier:3.7.2")
}