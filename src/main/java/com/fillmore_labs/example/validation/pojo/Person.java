package com.fillmore_labs.example.validation.pojo;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.base.MoreObjects;
import com.google.errorprone.annotations.Immutable;
import java.util.Objects;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable
public final class Person {
  private final String givenName;

  private final @Nullable String surname;

  public Person(String givenName, @Nullable String surname) {
    this.givenName = Objects.requireNonNull(givenName);
    this.surname = surname;

    checkState(!givenName.isEmpty(), "Given name must not be empty");
    checkState(surname == null || !surname.isEmpty(), "Surname must not be empty");
  }

  public String givenName() {
    return givenName;
  }

  public Optional<String> surname() {
    return Optional.ofNullable(surname);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Person)) {
      return false;
    }

    var person = (Person) o;
    return givenName.equals(person.givenName) && Objects.equals(surname, person.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(givenName, surname);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .omitNullValues()
        .add("givenName", givenName)
        .add("surname", surname)
        .toString();
  }
}
