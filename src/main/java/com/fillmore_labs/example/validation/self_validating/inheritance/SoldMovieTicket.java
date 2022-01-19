package com.fillmore_labs.example.validation.self_validating.inheritance;

import com.fillmore_labs.example.validation.self_validating.Person;
import com.google.errorprone.annotations.Immutable;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@SuppressWarnings({
  "MissingBraces",
  "MissingOverride",
  "MultiVariableDeclaration",
  "SameNameButDifferent",
  "UnnecessarilyFullyQualified",
  "Var",
  "allcheckers:type.anno.before.modifier",
})
@Immutable
@Value
@EqualsAndHashCode(callSuper = true)
public class SoldMovieTicket extends MovieTicket {
  @NotNull Person owner;

  public SoldMovieTicket(String movieName, long serial, Person owner) {
    super(movieName, serial);
    this.owner = owner;

    this.validateSelf();
  }
}
