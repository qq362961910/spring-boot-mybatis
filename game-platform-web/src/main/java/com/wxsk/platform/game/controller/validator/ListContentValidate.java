package com.wxsk.platform.game.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListContentValidator.class)
public @interface ListContentValidate {

    String message() default "data not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
