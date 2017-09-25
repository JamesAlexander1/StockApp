package chart;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * dailyData takes in a date string and double price. The date string is then converted to a calendar, so the jsp can access
 * the month, day of month and year fields of the date.
 * @author Johnson Xu
 *
 */
public class dailyData {
	
	private Date date;
	private Double value;
	private Calendar newdate;
	public dailyData(String d, Double p) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = (Date) sdf.parse(d);
			newdate = Calendar.getInstance();
			newdate.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		value = p;
		System.out.println(newdate.get(Calendar.MONTH));
	}
	
	public Date getDate() {
		return date;
	}
	
	public Double getValue() {
		return value;
	}
	public int getYear() {
		return newdate.get(Calendar.YEAR);
	}
	public int getMonth() {
		return newdate.get(Calendar.MONTH);
	}
	public int getDay() {
		return newdate.get(Calendar.DAY_OF_MONTH);
	}
	
}
