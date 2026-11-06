package com.southwind.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class StateValidator implements ConstraintValidator<StateValid, String> {

    private static final Set<String> ALLOWED = Set.of("已发布", "草稿");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // null/空串交给 @NotBlank 处理
        }
        return ALLOWED.contains(value);
    }
}