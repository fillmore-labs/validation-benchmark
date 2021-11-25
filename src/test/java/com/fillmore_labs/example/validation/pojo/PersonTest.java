package com.fillmore_labs.example.validation.pojo;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.Assert.assertThrows;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public final class PersonTest {
  @Test
  public void canBuild() {
    var person = new Person("Kartini", null);

    assertThat(person.givenName()).isEqualTo("Kartini");
    assertThat(person.surname()).isEmpty();
  }

  @Test
  @SuppressWarnings("nullness:argument")
  public void givenNameRequired() {
    assertThrows(NullPointerException.class, () -> new Person(null, null));
  }

  @Test
  public void nonEmptyGivenName() {
    assertThrows(IllegalStateException.class, () -> new Person("", null));
  }

  @Test
  public void nonEmptySurname() {
    assertThrows(IllegalStateException.class, () -> new Person("Kartini", ""));
  }

  @Test
  public void testEquals() {
    EqualsVerifier.forClass(Person.class).withNonnullFields("givenName").verify();
  }

  @Test
  public void testToString() {
    var person = new Person("Kartini", null);

    assertThat(person.toString()).contains("Kartini");
  }
}
