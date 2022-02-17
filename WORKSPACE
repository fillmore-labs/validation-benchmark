workspace(name = "com_fillmore_labs_validation_benchmark")

register_toolchains("//toolchain:toolchain_java11_definition")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# ---

http_archive(
    name = "rules_jvm_external",
    sha256 = "2cd77de091e5376afaf9cc391c15f093ebd0105192373b334f0a855d89092ad5",
    strip_prefix = "rules_jvm_external-4.2",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.2.tar.gz",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

# ---

load("//toolchain:defs.bzl", "testonly_artifacts")

maven_install(
    artifacts = [
        "com.google.errorprone:error_prone_annotations:2.11.0",
        "com.google.guava:guava:31.0.1-jre",
        "jakarta.validation:jakarta.validation-api:2.0.2",
        "org.checkerframework:checker-qual:3.21.2",
        "org.checkerframework:checker-util:3.21.2",
        "org.checkerframework:checker:3.21.2",
        "org.glassfish:jakarta.el:3.0.4",
        "org.hibernate.validator:hibernate-validator:6.2.2.Final",
        "org.immutables:value-annotations:2.9.0",
        "org.immutables:value-processor:2.9.0",
        "org.openjdk.jmh:jmh-core:1.34",
        "org.openjdk.jmh:jmh-generator-annprocess:1.34",
        "org.ow2.asm:asm:9.2",
        "org.projectlombok:lombok:1.18.22",
    ] + testonly_artifacts([
        "com.google.truth.extensions:truth-java8-extension:1.1.3",
        "com.google.truth:truth:1.1.3",
        "junit:junit:4.13.2",
        "nl.jqno.equalsverifier:equalsverifier:3.9",
    ]),
    fetch_sources = True,
    maven_install_json = "//:maven_install.json",
    repositories = [
        "https://maven-central-eu.storage-download.googleapis.com/maven2",
        "https://repo1.maven.org/maven2",
    ],
)

load("@maven//:defs.bzl", "pinned_maven_install")

pinned_maven_install()

# ---

maven_install(
    name = "jee9",
    artifacts = [
        "jakarta.validation:jakarta.validation-api:3.0.1",
        "org.glassfish:jakarta.el:4.0.2",
        "org.hibernate.validator:hibernate-validator:7.0.2.Final",
    ],
    fetch_sources = True,
    maven_install_json = "//:jee9_install.json",
    repositories = [
        "https://maven-central-eu.storage-download.googleapis.com/maven2",
        "https://repo1.maven.org/maven2",
    ],
)

load("@jee9//:defs.bzl", jee9_pinned_maven_install = "pinned_maven_install")

jee9_pinned_maven_install()

# ---
