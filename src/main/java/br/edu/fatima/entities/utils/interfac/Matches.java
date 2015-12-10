package br.edu.fatima.entities.utils.interfac;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;




@Target({TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = MatchesValidator.class)
@Documented
public @interface Matches {
	
	 String message() default "re-enter password invalidates";

	  Class<?>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};

	  String[] fields();

	  String[] verifyFields();
}
