package com.gsantander.tesla.annotations;

import com.gsantander.tesla.validators.DateValidator;
import com.gsantander.tesla.validators.TaxIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateValidator.class)
public @interface DateValidation {

    public String message() default "{date.invalid}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}