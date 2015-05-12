package ee.tlu.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Gui {

	
	
	public static Date parseDate(String date) {
		try {
			//return new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(date);
			//Wed May 13 00:00:42 EEST 2015
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(date);
			//2015-05-12 23:25:44.0
		} catch (ParseException e) {
			return null;
		}
	}
	public static void main(String[] args) {
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String kuupaev = dateFormat.format(date);
		System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
		*/
		//so that I can simply write

		Date myDate = parseDate("2015-05-12 23:25:44.0");
		
		System.out.println(myDate);

	}

}
