package br.edu.fatima.entities.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class CriptografiaUtil {
		private final static Logger logger = Logger.getLogger(CriptografiaUtil.class);
		
	public static String criptografarString(String source){
		if(source == null || source.isEmpty())
				return null;		
		String md5 = null;
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5");
			mdEnc.update(source.getBytes(), 0, source.length());
			md5 = new BigInteger(1, mdEnc.digest()).toString(16);
			
		} catch (Exception e) {
			logger.error("Problema ao criptografar "+ source +". Error : "+ e.getMessage());
			return null;
		}
		return md5;
	}
}
