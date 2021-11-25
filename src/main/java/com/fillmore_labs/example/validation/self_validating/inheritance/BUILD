load("@rules_java//java:defs.bzl", "java_library")
load("//toolchain:java_library_without_header_compilation.bzl", "java_library_without_header_compilation")

java_library_without_header_compilation(
    name = "inheritance",
    dep = ":inheritance_impl",
    visibility = ["//visibility:public"],
)

java_library(
    name = "inheritance_impl",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/example/validation/self_validating",
        "//toolchain:checker",
        "//toolchain:lombok",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_validation_jakarta_validation_api",
    ],
)
