package com.fillmore_labs.example.validation.self_validating;

import com.google.errorprone.annotations.Immutable;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings({
  "MissingBraces",
  "MultiVariableDeclaration",
  "SameNameButDifferent",
  "UnnecessarilyFullyQualified",
  "Var",
  "allcheckers:type.anno.before.modifier",
})
@Immutable
@Value
@EqualsAndHashCode(callSuper = false)
public class Person extends SelfValidating<Person> {
  @NotEmpty String givenName;

  @Size(min = 1)
  @Nullable String surname;

  public Person(String givenName, @Nullable String surname) {
    this.givenName = givenName;
    this.surname = surname;

    this.validateSelf();
  }

  public Optional<String> surname() {
    return Optional.ofNullable(surname);
  }
}
