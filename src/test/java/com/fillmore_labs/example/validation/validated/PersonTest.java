package com.fillmore_labs.example.validation.validated;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.Assert.assertThrows;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public final class PersonTest {
  @SuppressWarnings(
      "nullness:initialization.static.field.uninitialized") // Initialized in beforeClass()
  private static ValidatorFactory validatorFactory;

  @SuppressWarnings(
      "nullness:initialization.static.field.uninitialized") // Initialized in beforeClass()
  private static PersonFactory factory;

  @EnsuresNonNull({"validatorFactory", "factory"})
  @BeforeClass
  public static void beforeClass() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    factory = new PersonFactory(validatorFactory.getValidator());
  }

  @AfterClass
  public static void afterClass() {
    validatorFactory.close();
  }

  @Test
  public void canBuild() {
    var person = factory.create("Kartini", null);

    assertThat(person.getGivenName()).isEqualTo("Kartini");
    assertThat(person.getSurname()).isEmpty();
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void givenNameRequired() {
    assertThrows(ConstraintViolationException.class, () -> factory.create(null, null));
  }

  @Test
  public void nonEmptyGivenName() {
    assertThrows(ConstraintViolationException.class, () -> factory.create("", null));
  }

  @Test
  public void nonEmptySurname() {
    assertThrows(ConstraintViolationException.class, () -> factory.create("Kartini", ""));
  }
}
