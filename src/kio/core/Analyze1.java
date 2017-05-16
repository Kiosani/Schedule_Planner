package kio.core;

import java.sql.Date;

public class Analyze1 {
	private int idassigment;
	private int idschedule;
	private Date date;
	private int zapovn;
	
	public Analyze1 (int idassigment, int idschedule, Date date, int zapovn) {
		super();
		this.idassigment = idassigment;
		this.idschedule = idschedule;
		this.date = date;
		this.zapovn = zapovn;
	}
	
	public Analyze1 () {
		
	}
	
	public int getidassigment() {
		return idassigment;
	}
	
	public void setidassigment(int idassigment) {
		this.idassigment = idassigment;
	}
	
	public int getidschedule() {
		return idschedule;
	}
	
	public void setidschedule(int idschedule) {
		this.idschedule = idschedule;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getzapovn() {
		return zapovn;
	}
	
	public void setzapovn(int zapovn) {
		this.zapovn = zapovn;
	}
	
	@Override
	public String toString() {
		return String.format("Analyze1 [idassigment=%s, idschedule=%s, date=%s, zapovn=%s]", idassigment, idschedule, date, zapovn);
	}
	
}
