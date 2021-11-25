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
        "@maven//:org_glassfish_jakarta_el",
        "@maven//:org_hibernate_validator_hibernate_validator",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/example/validation/immutables",
        "//src/main/java/com/fillmore_labs/example/validation/pojo",
        "//src/main/java/com/fillmore_labs/example/validation/self_validating",
        "//src/main/java/com/fillmore_labs/example/validation/validated",
        "//toolchain:checker",
        "//toolchain:jmh",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_validation_jakarta_validation_api",
    ],
)
