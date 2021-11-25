load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "benchmark",
    srcs = glob(["*.java"]),
    javacopts = [
        "-Xlint:-cast",
        "-AskipDefs=\\.jmh_generated\\.",
    ],
    visibility = ["//visibility:public"],
    runtime_deps = [
        "@jee9//:org_glassfish_jakarta_el",
        "@jee9//:org_hibernate_validator_hibernate_validator",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/example/validation/immutables",
        "//src/main/java/com/fillmore_labs/example/validation/jee9/self_validating",
        "//src/main/java/com/fillmore_labs/example/validation/jee9/validated",
        "//src/main/java/com/fillmore_labs/example/validation/pojo",
        "//toolchain:checker",
        "//toolchain:jmh",
        "@jee9//:jakarta_validation_jakarta_validation_api",
        "@maven//:com_google_errorprone_error_prone_annotations",
    ],
)
