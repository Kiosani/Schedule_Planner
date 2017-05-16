package kio.core;

public class Vehicles {
	private int idvehicles;
	private String vehiclesmark;
	private String vehiclesvrp;
	private int vehiclests;
	
	public Vehicles (int idvehicles, String vehiclesmark, String vehiclesvrp, int vehiclests) {
		super();
		this.idvehicles = idvehicles;
		this.vehiclesmark = vehiclesmark;
		this.vehiclesvrp = vehiclesvrp;
		this.vehiclests = vehiclests;
	}
	
	public Vehicles() {
		
	}
	
	public Vehicles(String vehiclesmark, String vehiclesvrp, int vehiclests) {
		this.vehiclesmark = vehiclesmark;
		this.vehiclesvrp = vehiclesvrp;
		this.vehiclests = vehiclests;
	}
	
	public Vehicles (int idvehicles) {
		this.idvehicles = idvehicles;
	}
	
	public int getIdvehicles() {
		return idvehicles;
	}
	
	public void setIdvehicles(int idvehicles) {
		this.idvehicles = idvehicles;
	}
	
	public String getVehiclesmark() {
		return vehiclesmark;
	}
	
	public void setVehiclesmark(String vehiclesmark) {
		this.vehiclesmark = vehiclesmark;
	}
	
	public String getVehiclesvrp() {
		return vehiclesvrp;
	}
	
	public void setVehiclesvrp(String vehiclesvrp) {
		this.vehiclesvrp = vehiclesvrp;
	}
	
	public int getVehiclests() {
		return vehiclests;
	}
	
	public void setVehiclests(int vehiclests) {
		this.vehiclests = vehiclests;
	}
	
	@Override
	public String toString() {
		return String.format("vehicles [idvehicles=%s, vehiclesmark=%s, vehiclesvrp=%s, vehiclesvrp=%s]", idvehicles, vehiclesmark, vehiclesvrp, vehiclests);
	}
	
}
