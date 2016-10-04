package com.flp.ems.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	public static boolean validateKinId(){
		return true;
	}
	
	
	public static boolean validateName(String input){
		if(input==null)
			return false;
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("([a-zA-Z]+)");
	    matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	public static boolean validateDate(String input){
		if(input==null)
			return false;
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d)");
	    matcher = pattern.matcher(input);
		return matcher.matches();
	}
	public static boolean validateEmail(String input){
		if(input==null)
			return false;
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("([A-Z0-9._]+)@([A-Z0-9.]+).([A-Z]+)",Pattern.CASE_INSENSITIVE);
	    matcher = pattern.matcher(input);
		return matcher.matches();
	}


	public static boolean validatePhoneNo(String input) {
		if(input==null)
			return false;
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("([0-9]{10})",Pattern.CASE_INSENSITIVE);
	    matcher = pattern.matcher(input);
		return matcher.matches();
	}


	public static boolean validateEmployeeId(String input) {
		if(input==null)
			return false;
		return true;
		/*Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("([0-9]{10})",Pattern.CASE_INSENSITIVE);
	    matcher = pattern.matcher(input);
		return matcher.matches();*/
	}
}
