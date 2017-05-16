package kio.core;

public class Drivers {
	private int iddrivers;
	private String driversname;
	private String driversregistration;
	private String driverspasid;
	
	public Drivers (int iddrivers, String driversname, String driversregistration, String driverspasid) {
		super();
		this.iddrivers = iddrivers;
		this.driversname = driversname;
		this.driversregistration = driversregistration;
		this.driverspasid = driverspasid;
	}
	
	public Drivers() {
		
	}
	
	public Drivers (String driversname, String driversregistration, String driverspasid) {
		this.driversname = driversname;
		this.driversregistration = driversregistration;
		this.driverspasid = driverspasid;
	}
	
	public Drivers(int iddrivers) {
		this.iddrivers = iddrivers;
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
	
	public String getDriversregistration() {
		return driversregistration;
	}
	
	public void setDriversregistration(String driversregistration) {
		this.driversregistration = driversregistration;
	}
	
	public String getDriverspasid() {
		return driverspasid;
	}
	
	public void setDriverspasid(String driverspasid) {
		this.driverspasid = driverspasid;
	}
	
	@Override
	public String toString() {
		return String.format("drivers [iddrivers=%s, driversname=%s, driversregistration=%s, driverspasid=%s]", iddrivers, driversname, driversregistration, driverspasid);
	}
	
}