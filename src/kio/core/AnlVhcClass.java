package kio.core;

public class AnlVhcClass {
	private int idvehicles;
	private String vehiclesmark;
	private int vehiclescount;
	private int vehiclesperc;
	
	public AnlVhcClass (int idvehicles, String vehiclesmark, int vehiclescount, int vehiclesperc) {
		super();
		this.idvehicles = idvehicles;
		this.vehiclesmark = vehiclesmark;
		this.vehiclescount = vehiclescount;
		this.vehiclesperc = vehiclesperc;
	}
	
	public AnlVhcClass () {
		
	}
	
	public int getidvehicles() {
		return idvehicles;
	}
	
	public void setidvehicles(int idvehicles) {
		this.idvehicles = idvehicles;
	}
	
	public String getvehiclesmark() {
		return vehiclesmark;
	}
	
	public void setvehiclesmark(String vehiclesmark) {
		this.vehiclesmark = vehiclesmark;
	}
	
	public int getvehiclescount() {
		return vehiclescount;
	}
	
	public void setvehiclescount(int vehiclescount) {
		this.vehiclescount = vehiclescount;
	}
	
	public int getvehiclesperc() {
		return vehiclesperc;
	}
	
	public void setvehiclesperc(int vehiclesperc) {
		this.vehiclesperc = vehiclesperc;
	}
	
	@Override
	public String toString() {
		return String.format("AnlDrvClass [idvehicles=%s, vehiclesmark=%s, vehiclescount=%s, vehiclesperc=%s]", idvehicles, vehiclesmark, vehiclescount, vehiclesperc);
	}
}
