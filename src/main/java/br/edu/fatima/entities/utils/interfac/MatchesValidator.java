package br.edu.fatima.entities.utils.interfac;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class MatchesValidator implements ConstraintValidator<Matches, Object> {

	private String[] fields;
	private String[] verfyFields;
		
	@Override
	public void initialize(Matches constraintAnnotation) {
		fields = constraintAnnotation.fields();
		verfyFields = constraintAnnotation.verifyFields();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean matches = true;

        for (int i=0; i<fields.length; i++) {
            Object fieldObj, verifyFieldObj;
            try {
                fieldObj = BeanUtils.getProperty(value, fields[i]);
                verifyFieldObj = BeanUtils.getProperty(value, verfyFields[i]);
            } catch (Exception e) {
                //ignore
                continue;
            }
            boolean neitherSet = (fieldObj == null) && (verifyFieldObj == null);
            if (neitherSet) {
                continue;
            }

            boolean tempMatches = (fieldObj != null) && fieldObj.equals(verifyFieldObj);

            if (!tempMatches) {
                addConstraintViolation(context,"re-enter password invalidates", verfyFields[i]);
            }

            matches = matches?tempMatches:matches;
        }
        return matches;
	}

	@SuppressWarnings("deprecation")
	private void addConstraintViolation(ConstraintValidatorContext context,
			String message, String field) {
		
		context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addNode(field).addConstraintViolation();
		
	}

}
