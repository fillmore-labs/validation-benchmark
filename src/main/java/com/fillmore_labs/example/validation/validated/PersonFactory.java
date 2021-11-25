package com.fillmore_labs.example.validation.validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class PersonFactory {
  private final Validator validator;

  public PersonFactory(Validator validator) {
    this.validator = validator;
  }

  public Person create(String givenName, @Nullable String surname) {
    var person = new Person(givenName, surname);
    var violations = validator.validate(person);
    if (violations.isEmpty()) {
      return person;
    } else {
      throw new ConstraintViolationException(violations);
    }
  }
}
