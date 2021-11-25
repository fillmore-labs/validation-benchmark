load("@rules_java//java:defs.bzl", "java_binary")

exports_files(["lombok.config"])

JVM_FLAGS = [
    "--add-opens",
    "java.base/java.io=ALL-UNNAMED",
    "-Djava.util.logging.config.file=$(rootpath :conf/logging.properties)",
    "-XX:+CrashOnOutOfMemoryError",
]

java_binary(
    name = "benchmark",
    data = [
        ":conf/logging.properties",
    ],
    jvm_flags = JVM_FLAGS,
    main_class = "org.openjdk.jmh.Main",
    runtime_deps = [
        "//src/main/java/com/fillmore_labs/example/validation/benchmark",
    ],
)

java_binary(
    name = "jee9-benchmark",
    data = [
        ":conf/logging.properties",
    ],
    jvm_flags = JVM_FLAGS,
    main_class = "org.openjdk.jmh.Main",
    runtime_deps = [
        "//src/main/java/com/fillmore_labs/example/validation/jee9/benchmark",
    ],
)
