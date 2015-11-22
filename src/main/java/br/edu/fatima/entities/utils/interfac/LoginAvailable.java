package br.edu.fatima.entities.utils.interfac;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.edu.fatima.entities.repositories.usuario.ReposUsuario;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ReposUsuario.class })
@Documented
public @interface LoginAvailable {
	String message() default "{usuario.e.senha.invalida}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
