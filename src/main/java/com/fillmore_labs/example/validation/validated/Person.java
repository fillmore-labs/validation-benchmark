package com.fillmore_labs.example.validation.validated;

import static lombok.AccessLevel.PACKAGE;

import com.google.errorprone.annotations.Immutable;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings({
  "MissingBraces",
  "MultiVariableDeclaration",
  "SameNameButDifferent",
  "UnnecessarilyFullyQualified",
  "Var",
  "allcheckers:type.anno.before"
})
@Immutable
@Value
@AllArgsConstructor(access = PACKAGE)
@Accessors(fluent = false)
public class Person {
  @Getter(onMethod_ = @NotEmpty)
  String givenName;

  @Nullable String surname;

  public Optional<@Size(min = 1) String> getSurname() {
    return Optional.ofNullable(surname);
  }
}
