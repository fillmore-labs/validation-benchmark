package com.fillmore_labs.example.validation.immutables;

import static com.google.common.base.Preconditions.checkState;

import com.google.errorprone.annotations.Immutable;
import java.util.Optional;
import org.immutables.value.Value;

@Immutable
@Value.Style(
    optionalAcceptNullable = true,
    passAnnotations = {Immutable.class})
@Value.Immutable
public abstract class Person implements WithPerson {
  /* package */ Person() {}

  public static Builder builder() {
    return new Builder();
  }

  public abstract String givenName();

  public abstract Optional<String> surname();

  @Value.Check
  /* package */ final void check() {
    var givenNameIsEmpty = givenName().isEmpty();
    var surnameIsPresentAndEmpty = surname().map(String::isEmpty).orElse(false);
    checkState(!givenNameIsEmpty, "Given name must not be empty");
    checkState(!surnameIsPresentAndEmpty, "Surname must not be empty");
  }

  public static final class Builder extends ImmutablePerson.Builder {}
}
