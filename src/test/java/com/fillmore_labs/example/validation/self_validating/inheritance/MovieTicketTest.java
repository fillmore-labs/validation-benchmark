package com.fillmore_labs.example.validation.self_validating.inheritance;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.fillmore_labs.example.validation.self_validating.Person;
import javax.validation.ConstraintViolationException;
import org.junit.Test;

public class MovieTicketTest {
  @Test
  public void canInstantiate() {
    var ticket = new MovieTicket("A Movie", 1L);
    assertThat(ticket.serial()).isEqualTo(1L);
  }

  @Test
  public void cantInstantiate() {
    var person = new Person("Some", "Guy");
    assertThrows(
        ConstraintViolationException.class, () -> new SoldMovieTicket("A Movie", 1L, person));
  }
}
