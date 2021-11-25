package com.fillmore_labs.example.validation.jee9.self_validating;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public abstract class SelfValidating<T extends SelfValidating<T>> {
  @SuppressWarnings({"FieldCanBeFinal", "Immutable"})
  private Validator validator;

  protected SelfValidating() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  protected void validateSelf() {
    @SuppressWarnings("unchecked")
    Set<ConstraintViolation<T>> violations = validator.validate((T) this);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
