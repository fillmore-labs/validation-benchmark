load("@rules_java//java:defs.bzl", "java_library")
load("//toolchain:java_library_without_header_compilation.bzl", "java_library_without_header_compilation")

java_library_without_header_compilation(
    name = "validated",
    dep = ":validated_impl",
    visibility = ["//visibility:public"],
)

java_library(
    name = "validated_impl",
    srcs = glob(["*.java"]),
    deps = [
        "//toolchain:checker",
        "//toolchain:immutables",
        "//toolchain:lombok",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_validation_jakarta_validation_api",
    ],
)
