package kio.core;

public class Week {
	private String day;
	public Week (String day) {
		super();
		this.day = day;
	}
	
	public Week () {
		
	}
	
	public String getDay() {
		return day;
	}
	
	public void setDay (String day) {
		this.day = day;
	}
	
	@Override
	public String toString() {
		return String.format("week [day=%s]", day);
	}
	
}
