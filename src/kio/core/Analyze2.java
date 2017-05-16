package kio.core;

import java.sql.Date;

public class Analyze2 {
	private int idassigment;
	private int idschedule;
	private Date date;
	private Float rozp;
	
	public Analyze2 (int idassigment, int idschedule, Date date, float rozp) {
		super();
		this.idassigment = idassigment;
		this.idschedule = idschedule;
		this.date = date;
		this.rozp = rozp;
	}
	
	public Analyze2 () {
		
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
	
	public float getrozp() {
		return rozp;
	}
	
	public void setrozp(float rozp) {
		this.rozp = rozp;
	}
	
	@Override
	public String toString() {
		return String.format("Analyze2 [idassigment=%s, idschedule=%s, date=%s, rozp=%s]", idassigment, idschedule, date, rozp);
	}
	
}
