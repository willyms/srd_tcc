package br.edu.fatima.entities.utils;

import java.time.LocalDate;
import java.time.temporal.Temporal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FutureValidator implements ConstraintValidator<SRDFuture, Temporal> {

	@Override
	public void initialize(SRDFuture constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(Temporal value, ConstraintValidatorContext context) {
		if(value == null){
			return true;
		}
		
		LocalDate ld = LocalDate.from(value);
		if(ld.isAfter(LocalDate.now())){
			return true;
		}
		return false;
	}
	
}
