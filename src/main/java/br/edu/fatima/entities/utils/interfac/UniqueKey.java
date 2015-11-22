package br.edu.fatima.entities.utils.interfac;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {UniqueKeyValidator.class })
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueKey {

   String property();

   String message() default "{validator.geral.unico}";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};

   @Target({ ElementType.TYPE })
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @interface List {
       UniqueKey[] value();
   }

}