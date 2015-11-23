package br.edu.fatima.entities.utils;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureValidator.class)
@Documented
public @interface SRDFuture {
	 	
		String message() default "Data devera esta no futuro.";
		
	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};

}
