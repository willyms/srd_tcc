package br.edu.fatima.entities.utils;

public class SrdUtils {
	
	public static boolean isNullOrBlank(String param) {
		if(isNull(param)){
			return true;
		}
		if(isEmpty(param)){
			return true;
		}
        if (isNull(param) || param.trim().length() == 0) {
            return true;
        }
        return false;
    }
	
	public static boolean isEmpty(String s) {
        if ((s != null) && (s.trim().length() > 0))
            return false;
        else
            return true;
    }
	
	public static boolean isNull(String str) {
        return str == null ? true : false;
    }
	
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
