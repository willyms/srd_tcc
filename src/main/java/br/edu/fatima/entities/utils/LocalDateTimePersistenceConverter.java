package br.edu.fatima.entities.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, Date> {

	Logger logger = LoggerFactory.getLogger(LocalDateTimePersistenceConverter.class);

	@Override
	public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
		logger.debug("Convert To Data base Column ...");
		if (localDateTime != null) {
			Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
			return Date.from(instant);
		}
		return null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Date dbData) {
		logger.debug("ConvertTo Entity Attribute ...");
		if (dbData != null) {
			Instant instant = Instant.ofEpochMilli(dbData.getTime());
			return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		} else {
			return null;
		}

	}
}
