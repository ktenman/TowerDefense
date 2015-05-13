package ee.tlu.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Map {
	
	private String name, content;
	private int id;
	private Date date;

	public Map(int id, String name, String content, String date) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.date = parseDate(date);
	}
	
	private Date parseDate(String date) {
		try {
			//return new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(date);
			//Wed May 13 00:00:42 EEST 2015
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(date);
			//2015-05-12 23:25:44.0
		} catch (ParseException e) {
			return null;
		}
	}
	
	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String
				.format("Map [name=%s, content=%s, id=%s, date=%s, getName()=%s, getContent()=%s, getId()=%s, getDate()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
						name, content, id, date, getName(), getContent(),
						getId(), getDate(), getClass(), hashCode(),
						super.toString());
	}

}
