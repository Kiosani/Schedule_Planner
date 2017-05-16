package kio.core;

public class AnlDrvClass {
	private int iddrivers;
	private String driversname;
	private int driverscount;
	private int driversperc;
	
	public AnlDrvClass (int iddrivers, String driversname, int driverscount, int driversperc) {
		super();
		this.iddrivers = iddrivers;
		this.driversname = driversname;
		this.driverscount = driverscount;
		this.driversperc = driversperc;
	}
	
	public AnlDrvClass () {
		
	}
	
	public int getIddrivers() {
		return iddrivers;
	}
	
	public void setIddrivers(int iddrivers) {
		this.iddrivers = iddrivers;
	}
	
	public String getDriversname() {
		return driversname;
	}
	
	public void setDriversname(String driversname) {
		this.driversname = driversname;
	}
	
	public int getDriverscount() {
		return driverscount;
	}
	
	public void setDriverscount(int driverscount) {
		this.driverscount = driverscount;
	}
	
	public int getDriversperc() {
		return driversperc;
	}
	
	public void setDriversperc(int driversperc) {
		this.driversperc = driversperc;
	}
	
	@Override
	public String toString() {
		return String.format("AnlDrvClass [iddrivers=%s, driversname=%s, driverscount=%s, driversperc=%s]", iddrivers, driversname, driverscount, driversperc);
	}
}
