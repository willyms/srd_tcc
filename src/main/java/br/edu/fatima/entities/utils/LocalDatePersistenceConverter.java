package br.edu.fatima.entities.utils;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {
     
	Logger logger = LoggerFactory.getLogger(LocalDatePersistenceConverter.class);
	
	@Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        return (entityValue == null) ? null : Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
    	logger.debug("ConvertTo data para o banco de dados ...");
    	if(databaseValue != null)
    		return (databaseValue == null) ? null : databaseValue.toLocalDate();
    	return null;
    }

}