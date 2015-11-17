package br.edu.fatima.entities.utils;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;

@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@Convert(LocalDate.class)
public class LocalDateConverter implements Converter<LocalDate> {

    @Inject
    private Locale locale;
    Logger logger = LoggerFactory.getLogger(LocalDateConverter.class);

    @Override
    public LocalDate convert(String value, Class<? extends LocalDate> type) {
    	logger.debug("Local Date Converter ...");
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {        	
        	DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale);
            return   LocalDate.parse(value, dateFormatter);
        } catch (UnsupportedOperationException | IllegalArgumentException e) {        	
            throw new ConversionException(new ConversionMessage("valor invalidor" +value, value));
        }
    }


}
