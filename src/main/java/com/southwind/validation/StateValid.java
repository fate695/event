package com.southwind.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StateValidator.class)
public @interface StateValid {
    String message() default "文章状态只能是[已发布]或[草稿]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
