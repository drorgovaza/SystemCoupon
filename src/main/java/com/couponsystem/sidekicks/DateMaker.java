package com.couponsystem.sidekicks;

import java.util.Calendar;
import java.util.Date;

public class DateMaker {

	/**
	 * Return Date (util) with the Calendar
	 * @param year
	 * @param month
	 * @param day
	 * @return Date
	 */
	public static Date fixDate(int year , int month , int day)
	{
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(year, month , day);
		date = cal.getTime();
		
		return date;
	}
}
