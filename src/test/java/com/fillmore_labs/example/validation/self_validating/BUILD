load("@rules_java//java:defs.bzl", "java_library", "java_test")

java_library(
    name = "tests",
    testonly = True,
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/example/validation/self_validating",
        "//toolchain:checker",
        "//toolchain:truth",
        "@maven//:jakarta_validation_jakarta_validation_api",
    ],
)

java_test(
    name = "PersonTest",
    size = "small",
    runtime_deps = [
        ":tests",
        "@maven//:org_glassfish_jakarta_el",
        "@maven//:org_hibernate_validator_hibernate_validator",
    ],
)
