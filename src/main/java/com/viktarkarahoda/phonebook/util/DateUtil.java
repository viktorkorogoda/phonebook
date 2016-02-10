package com.viktarkarahoda.phonebook.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	static {
		DATE_FORMAT.setLenient(true);
	}

	//convert a date from format "yyyy-mm-dd" to format "dd.mm.yyyy"
	public static String dateConvert(String dateToConvert) {
		String convertedDate = "";
		SimpleDateFormat fromDb = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd.mm.yyyy");
		try {
			convertedDate = myFormat.format(fromDb.parse(dateToConvert));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}
	
	//check the date according to format "dd.mm.yyyy"
	public static boolean dateCheck(String startDate, String endDate) {
		try {
			return DATE_FORMAT.format(DATE_FORMAT.parse(startDate)).equals(startDate) && DATE_FORMAT.format(DATE_FORMAT.parse(endDate)).equals(endDate);

		} catch (ParseException ex) {
			return false;
		}
	}
	
	//check the date according to format "dd.mm.yyyy"
	public static boolean dateCheck(String date) {
		try {
			return DATE_FORMAT.format(DATE_FORMAT.parse(date)).equals(date);
		} catch (ParseException ex) {
			return false;
		}

	}
}
