package br.edu.fatima.entities.utils;

import java.time.LocalTime;
import java.util.Locale;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;


@Alternative
@Priority(javax.interceptor.Interceptor.Priority.APPLICATION)
@Convert(LocalTime.class)
public class LocalTimeConverter  implements Converter<LocalTime>{
	
	@Inject
    private Locale locale;
    Logger logger = LoggerFactory.getLogger(LocalTimeConverter.class);
            
	@Override
	public LocalTime convert(String value, Class<? extends LocalTime> type) {
		logger.debug("Local Time Converter ...");
        if (value == null || value.isEmpty()) {
            return null;
        }        
        try {        	
        	LocalTime time = LocalTime.parse(value);        	
        	return time;        	
		} catch (UnsupportedOperationException | IllegalArgumentException e) {        	
            throw new ConversionException(new ConversionMessage("valor invalidor" +value, value));
		}		
	}
}
