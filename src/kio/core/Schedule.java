package kio.core;

//import java.sql.Time;

public class Schedule {
	private int idschedule;
	private String routestart;
	private String routeend;
	private String day;
	private String timestart;
	private String timeend;
	private int prize;
	private String comment;
	
	public Schedule (int idschedule, String routestart, String routeend, String day, String timestart, String timeend, int prize, String comment) {
		super();
		this.idschedule = idschedule;
		this.routestart = routestart;
		this.routeend = routeend;
		this.day = day;
		this.timestart = timestart;
		this.timeend = timeend;
		this.prize = prize;
		this.comment = comment;
	}
	
	public Schedule () {
		
	}
	
	public Schedule (String routestart, String routeend, String day, String timestart, String timeend, int prize, String comment) {
		this.routestart = routestart;
		this.routeend = routeend;
		this.day = day;
		this.timestart = timestart;
		this.timeend = timeend;
		this.prize = prize;
		this.comment = comment;
	}
	
	public Schedule (String routestart, String routeend, String day, String timestart, String timeend, int prize) {
		this.routestart = routestart;
		this.routeend = routeend;
		this.day = day;
		this.timestart = timestart;
		this.timeend = timeend;
		this.prize = prize;
	}
	
	public Schedule (int idschedule) {
		this.idschedule = idschedule;
	}
	
	public int getIdschedule() {
		return idschedule;
	}
	
	public void setIdschedule(int idschedule) {
		this.idschedule = idschedule;
	}
	
	public String getRoutestart() {
		return routestart;
	}
	
	public void setRoutestart(String routestart) {
		this.routestart = routestart;
	}
	
	public String getRouteend() {
		return routeend;
	}
	
	public void setRouteend(String routeend) {
		this.routeend = routeend;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getTimestart() {
		return timestart;
	}
	
	public void setTimestart(String timestart) {
		this.timestart = timestart;
	}
	
	public String getTimeend() {
		return timeend;
	}
	
	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}
	
	public int getPrize() {
		return prize;
	}
	
	public void setPrize(int prize) {
		this.prize = prize;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return String.format("routes [idschedule=%s, routestart=%s, routeend=%s, day=%s, timestart=%s, timeend=%s, prize=%s, comment=%s]", idschedule, routestart, routeend, day, timestart, timeend, prize, comment);
	}
}
