package kio.core;

import java.sql.Date;

public class Assigment {
	private int idassigment;
	private int idschedule;
	private Date date;
	private int iddrivers;
	private int idvehicles;
	private int tickets;
	
	public Assigment (int idassigment, int idschedule, Date date, int iddrivers, int idvehicles, int tickets) {
		super();
		this.idassigment = idassigment;
		this.idschedule = idschedule;
		this.date = date;
		this.iddrivers = iddrivers;
		this.idvehicles = idvehicles;
		this.tickets = tickets;
	}
	
	public Assigment () {
		
	}
	
	public Assigment (int idschedule, Date date, int iddrivers, int idvehicles, int tickets) {
		this.idschedule = idschedule;
		this.date = date;
		this.iddrivers = iddrivers;
		this.idvehicles = idvehicles;
		this.tickets = tickets;
	}
	
	public Assigment (int idassigment) {
		this.idassigment = idassigment;
	}
	
	public int getIdAssigment() {
		return idassigment;
	}
	
	public void setIdAssigment(int idassigment) {
		this.idassigment = idassigment;
	}
	
	public int getIdschedule() {
		return idschedule;
	}
	
	public void setIdschedule(int idschedule) {
		this.idschedule = idschedule;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getIddrivers() {
		return iddrivers;
	}
	
	public void setIddrivers(int iddrivers) {
		this.iddrivers = iddrivers;
	}
	
	public int getIdvehicles() {
		return idvehicles;
	}
	
	public void setIdvehicles(int idvehicles) {
		this.idvehicles = idvehicles;
	}
	
	public int getTickets() {
		return tickets;
	}
	
	public void setTickets(int tickets) {
		this.tickets = tickets;
	}
	
	@Override
	public String toString() {
		return String.format("assigment [idassigment=%s, idschedule=%s, date=%s, iddrivers=%s, idvehicles=%s, tickets=%s]", idassigment, idschedule, date, iddrivers, idvehicles, tickets);
	}
}
