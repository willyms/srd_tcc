package br.edu.fatima.entities.utils;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimePersistenceConverter  implements AttributeConverter<LocalTime, Time> {

	@Override
    public Time convertToDatabaseColumn(LocalTime entityValue) {
        return (entityValue == null) ? null : Time.valueOf(entityValue);
    }


    @Override
    public LocalTime convertToEntityAttribute(Time databaseValue) {
    	if(databaseValue != null)
    		return databaseValue.toLocalTime();
    return null;	
    }

	
}
