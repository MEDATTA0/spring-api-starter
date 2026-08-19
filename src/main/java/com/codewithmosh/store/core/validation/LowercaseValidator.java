package com.codewithmosh.store.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * LowercaseValidator
 */
public class LowercaseValidator
    implements ConstraintValidator<Lowercase, String>
{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.equals(value.toLowerCase());
    }
}
