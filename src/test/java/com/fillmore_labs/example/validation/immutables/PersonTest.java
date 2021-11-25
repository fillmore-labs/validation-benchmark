package com.fillmore_labs.example.validation.immutables;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public final class PersonTest {
  @Test
  public void canBuild() {
    var person = Person.builder().givenName("Kartini").build();

    assertThat(person.givenName()).isEqualTo("Kartini");
    assertThat(person.surname()).isEmpty();
  }

  @Test
  public void required() {
    assertThrows(IllegalStateException.class, () -> Person.builder().build());
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void notNull() {
    assertThrows(NullPointerException.class, () -> Person.builder().givenName(null).build());
  }

  @Test
  public void nonEmptyGivenName() {
    assertThrows(IllegalStateException.class, () -> Person.builder().givenName("").build());
  }

  @Test
  public void nonEmptySurname() {
    assertThrows(
        IllegalStateException.class,
        () -> Person.builder().givenName("Kartini").surname("").build());
  }
}
