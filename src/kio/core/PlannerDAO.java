package kio.core;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;
import java.sql.Date;

public class PlannerDAO {
	
	private Connection myConn;
	
	public PlannerDAO() throws Exception {
		
		Properties props = new Properties();
		props.load(new FileInputStream("lib/kio.properties"));
		
		String dburl = props.getProperty("dburl");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		
		/*String dburl = "jdbc:mysql://localhost:3307/planner";
		String user = "root";
		String password = "jekobo 11";*/
		
		myConn = DriverManager.getConnection(dburl, user, password);
		
	}
	
	public List<Drivers> getAllDrivers() throws Exception {
		List<Drivers> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from drivers");
			
			while (myRs.next()) {
				Drivers tempDrivers = convertRowToDrivers(myRs);
				list.add(tempDrivers);
			}
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Vehicles> getAllVehicles() throws Exception {
		List<Vehicles> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from vehicles");
			
			while (myRs.next()) {
				Vehicles tempVehicles = convertRowToVehicles(myRs);
				list.add(tempVehicles);
			}
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Assigment> getAllAssigment() throws Exception {
		List<Assigment> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from assigment");
			
			while (myRs.next()) {
				Assigment tempAssigment = convertRowToAssigment(myRs);
				list.add(tempAssigment);
			}
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Schedule> getAllSchedule() throws Exception {
		List<Schedule> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from schedule");
			
			while (myRs.next()) {
				Schedule tempSchedule = convertRowToSchedule(myRs);
				list.add(tempSchedule);
			}
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public void addVehicles (Vehicles theVehicles) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into vehicles (vehiclesmark, vehiclesvrp, vehiclests) values (?, ?, ?)");
			
			myStmt.setString(1, theVehicles.getVehiclesmark());
			myStmt.setString(2, theVehicles.getVehiclesvrp());
			myStmt.setInt(3, theVehicles.getVehiclests());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void addDrivers (Drivers theDrivers) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into drivers (driversname, driversregistration, driverspasid) values (?, ?, ?)");
			
			myStmt.setString(1, theDrivers.getDriversname());
			myStmt.setString(2, theDrivers.getDriversregistration());
			myStmt.setString(3, theDrivers.getDriverspasid());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void addAssigment (Assigment theAssigment) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into assigment (idschedule, date, iddrivers, idvehicles, tickets) values (?, ?, ?, ?, ?)");
			
			myStmt.setInt(1, theAssigment.getIdschedule());
			myStmt.setDate(2, theAssigment.getDate());
			myStmt.setInt(3, theAssigment.getIddrivers());
			myStmt.setInt(4, theAssigment.getIdvehicles());
			myStmt.setInt(5, theAssigment.getTickets());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void addSchedule (Schedule theSchedule) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into schedule (routestart, routeend, day, timestart, timeend, prize, comment) values (?, ?, ?, ?, ?, ?, ?)");
			
			myStmt.setString(1, theSchedule.getRoutestart());
			myStmt.setString(2, theSchedule.getRouteend());
			myStmt.setString(3, theSchedule.getDay());
			myStmt.setString(4, theSchedule.getTimestart().toString());
			myStmt.setString(5, theSchedule.getTimeend());
			myStmt.setInt(6, theSchedule.getPrize());
			myStmt.setString(7, theSchedule.getComment());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void deleteDrivers(int drvID) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("delete from drivers where iddrivers = ?");
			myStmt.setInt(1, drvID);
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void deleteAssigment(int asgID) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("delete from assigment where idassigment = ?");
			myStmt.setInt(1, asgID);
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void updateDrivers(Drivers theDrivers) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("update drivers set driversname = ?, driversregistration = ?, driverspasid = ? where iddrivers = ?");
			
			myStmt.setString(1, theDrivers.getDriversname());
			myStmt.setString(2, theDrivers.getDriversregistration());
			myStmt.setString(3, theDrivers.getDriverspasid());
			myStmt.setInt(4, theDrivers.getIddrivers());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void updateAssigment(Assigment theAssigment) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("update assigment set idschedule = ?, date = ?, iddrivers = ?, idvehicles = ?, tickets = ? where idassigment = ?");
			
			myStmt.setInt(1, theAssigment.getIdschedule());
			myStmt.setDate(2, theAssigment.getDate());
			myStmt.setInt(3, theAssigment.getIddrivers());
			myStmt.setInt(4, theAssigment.getIdvehicles());
			myStmt.setInt(5, theAssigment.getTickets());
			myStmt.setInt(6, theAssigment.getIdAssigment());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	private Vehicles convertRowToVehicles(ResultSet myRs) throws Exception {
		int idvehicles = myRs.getInt("idvehicles");
		String vehiclesmark = myRs.getString("vehiclesmark");
		String vehiclesvrp = myRs.getString("vehiclesvrp");
		int vehiclests = myRs.getInt("vehiclests");
		
		Vehicles tempVehicles = new Vehicles(idvehicles, vehiclesmark, vehiclesvrp, vehiclests);
		
		return tempVehicles;
	}
	
	private Assigment convertRowToAssigment(ResultSet myRs) throws Exception {
		int idassigment = myRs.getInt("idassigment");
		int idschedule = myRs.getInt("idschedule");
		Date date = myRs.getDate("date");
		int iddrivers = myRs.getInt("iddrivers");
		int idvehicles = myRs.getInt("idvehicles");
		int tickets = myRs.getInt("tickets");
		
		Assigment tempAssigment = new Assigment(idassigment, idschedule, date, iddrivers, idvehicles, tickets);
		
		return tempAssigment;
	}
	
	public void deleteVehicles(int vhcID) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("delete from vehicles where idvehicles = ?");
			myStmt.setInt(1, vhcID);
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void updateVehicles(Vehicles theVehicles) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("update vehicles set vehiclesmark = ?, vehiclesvrp = ?, vehiclests = ? where idvehicles = ?");
			
			myStmt.setString(1, theVehicles.getVehiclesmark());
			myStmt.setString(2, theVehicles.getVehiclesvrp());
			myStmt.setInt(3, theVehicles.getVehiclests());
			myStmt.setInt(4, theVehicles.getIdvehicles());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void deleteSchedule(int schID) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("delete from schedule where idschedule = ?");
			myStmt.setInt(1, schID);
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void updateSchedule(Schedule tempSchedule) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("update schedule set routestart = ?, routeend = ?, day = ?, timestart = ?, timeend = ?, prize = ?, comment = ? where idschedule = ?");
			
			myStmt.setString(1, tempSchedule.getRoutestart());
			myStmt.setString(2, tempSchedule.getRouteend());
			myStmt.setString(3, tempSchedule.getDay());
			myStmt.setString(4, tempSchedule.getTimestart().toString());
			myStmt.setString(5, tempSchedule.getTimeend());
			myStmt.setInt(6, tempSchedule.getPrize());
			myStmt.setString(7, tempSchedule.getComment());
			myStmt.setInt(8, tempSchedule.getIdschedule());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	private Drivers convertRowToDrivers(ResultSet myRs) throws Exception {
		int iddrivers = myRs.getInt("iddrivers");
		String driversname = myRs.getString("driversname");
		String driversregistration = myRs.getString("driversregistration");
		String driverspasid = myRs.getString("driverspasid");
		
		Drivers tempDrivers = new Drivers(iddrivers, driversname, driversregistration, driverspasid);
		
		return tempDrivers;
	}
	
	private Schedule convertRowToSchedule(ResultSet myRs) throws Exception {
		int idschedule = myRs.getInt("idschedule");
		String routestart = myRs.getString("routestart");
		String routeend = myRs.getString("routeend");
		String day = myRs.getString("day");
		String timestart = myRs.getString("timestart");
		String timeend = myRs.getString("timeend");
		int prize = myRs.getInt("prize");
		String comment = myRs.getString("comment");
		Schedule tempSchedule = new Schedule(idschedule, routestart, routeend, day, timestart, timeend, prize, comment);
		
		return tempSchedule;
	}
	
	public static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}
		
		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}
	
	private void close (Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}
	
	private void close (Statement myStmt) throws SQLException {
		close(null, myStmt, null);
	}
	
	//Analyze
	
	public List<Analyze1> getAnalyze1(Date d1, Date d2, String s1, String s2) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		List<Analyze1> list = new ArrayList<>();
		
		try {
			myStmt = myConn.prepareStatement("select a.idassigment, a.idschedule, a.date, a.tickets/v.vehiclests*100 as zapovn from assigment a, vehicles v where a.idvehicles = v.idvehicles and a.date <= ? and a.date >= ? order by a.idassigment");
			
				myStmt.setDate(1, d1);
				myStmt.setDate(2, d2);
				myStmt.setString(3, s1);
				myStmt.setString(4, s2);
				
				myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Analyze1 tempAnalyze1 = convertRowToAnalyze1(myRs);
				list.add(tempAnalyze1);
			}
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Analyze1> getAnalyze1a(Date d1, Date d2) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		List<Analyze1> list = new ArrayList<>();
		
		try {
			myStmt = myConn.prepareStatement("select a.idassigment, a.idschedule, a.date, a.tickets/v.vehiclests*100 as zapovn from assigment a, vehicles v where a.idvehicles = v.idvehicles and a.date <= ? and a.date >= ? order by a.idassigment");
			
				myStmt.setDate(1, d1);
				myStmt.setDate(2, d2);
				
				myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Analyze1 tempAnalyze1 = convertRowToAnalyze1(myRs);
				list.add(tempAnalyze1);
			}
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Analyze1 convertRowToAnalyze1(ResultSet myRs) throws Exception {
		int idassigment = myRs.getInt("idassigment");
		int idschedule = myRs.getInt("idschedule");
		Date date = myRs.getDate("date");
		int zapovn = myRs.getInt("zapovn");
		
		Analyze1 tempAnalyze1 = new Analyze1(idassigment, idschedule, date, zapovn);
		
		return tempAnalyze1;
	}
	
	private Analyze2 convertRowToAnalyze2(ResultSet myRs) throws Exception {
		int idassigment = myRs.getInt("idassigment");
		int idschedule = myRs.getInt("idschedule");
		Date date = myRs.getDate("date");
		float rozp = myRs.getFloat("rozp");
		
		Analyze2 tempAnalyze2 = new Analyze2(idassigment, idschedule, date, rozp);
		
		return tempAnalyze2;
	}
	
	public List<Analyze2> getAnalyze2a(Date d1, Date d2) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		PreparedStatement myStmt1 = null;
		List<Analyze2> list = new ArrayList<>();
		
		try {
			myStmt = myConn.prepareStatement("select avg(a.tickets) from assigment a where a.date >= ? and a.date <= ?");
			myStmt.setDate(1, d2);
			myStmt.setDate(2, d1);			
			myRs = myStmt.executeQuery();
			Float foo = null;
			while (myRs.next()) {
				foo = myRs.getFloat("avg(a.tickets)");
				}
			myStmt1 = myConn.prepareStatement("select a.idassigment, a.idschedule, a.date, a.tickets/? as rozp from assigment a, vehicles v where a.idvehicles = v.idvehicles and a.date <= ? and a.date >= ? order by a.idassigment");
			myStmt1.setFloat(1, foo);
			myStmt1.setDate(2, d1);
			myStmt1.setDate(3, d2);
			myRs1 = myStmt1.executeQuery();
			while (myRs1.next()) {
				Analyze2 tempAnalyze2 = convertRowToAnalyze2(myRs1);
				list.add(tempAnalyze2);
			}
			return list;
			}
		finally {
			close(myStmt, myRs);
			close(myStmt1, myRs1);
		}
	}
	
	public List<Analyze2> getAnalyze2(Date d1, Date d2, String s1, String s2) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		PreparedStatement myStmt1 = null;
		List<Analyze2> list = new ArrayList<>();
		
		try {
			myStmt = myConn.prepareStatement("select avg(a.tickets) from assigment a where a.date >= ? and a.date <= ?");
			myStmt.setDate(1, d2);
			myStmt.setDate(2, d1);			
			myRs = myStmt.executeQuery();
			Float foo = null;
			while (myRs.next()) {
				foo = myRs.getFloat("avg(a.tickets)");
				}
			myStmt1 = myConn.prepareStatement("select a.idassigment, a.idschedule, a.date, a.tickets/? as rozp from assigment a, vehicles v where a.idvehicles = v.idvehicles and a.date <= ? and a.date >= ? order by a.idassigment");
			myStmt1.setFloat(1, foo);
			myStmt1.setDate(2, d1);
			myStmt1.setDate(3, d2);
			myRs1 = myStmt1.executeQuery();
			while (myRs1.next()) {
				Analyze2 tempAnalyze2 = convertRowToAnalyze2(myRs1);
				list.add(tempAnalyze2);
			}
			return list;
			}
		finally {
			close(myStmt, myRs);
			close(myStmt1, myRs1);
		}
	}
	
	public List<AnlDrvClass> getAnlDrvClass(Date d1, Date d2) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		List<AnlDrvClass> list = new ArrayList<>();
		
		try {
			myStmt = myConn.prepareStatement("select d.iddrivers, d.driversname, count(a.iddrivers) as number from drivers d, assigment a where d.iddrivers = a.iddrivers and a.date <= ? and a.date >= ? order by d.iddrivers");
			
				myStmt.setDate(1, d1);
				myStmt.setDate(2, d2);
				
				myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				AnlDrvClass tempAnlDrvClass = convertRowToAnlDrvClass(myRs);
				list.add(tempAnlDrvClass);
			}
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private AnlDrvClass convertRowToAnlDrvClass(ResultSet myRs) throws Exception {
		int iddrivers = myRs.getInt("iddrivers");
		String driversname = myRs.getString("driversname");
		int driverscount = myRs.getInt("driverscount");
		int driversperc = myRs.getInt("driversperc");
		
		AnlDrvClass tempAnlDrvClass = new AnlDrvClass(iddrivers, driversname, driverscount, driversperc);
		
		return tempAnlDrvClass;
	}
	
	public List<AnlVhcClass> getAnlVhcClass(Date d1, Date d2) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		List<AnlVhcClass> list = new ArrayList<>();
		
		try {
			myStmt = myConn.prepareStatement("select d.iddrivers, d.driversname, count(a.iddrivers) as number from drivers d, assigment a where d.iddrivers = a.iddrivers and a.date <= ? and a.date >= ? order by d.iddrivers");
			
				myStmt.setDate(1, d1);
				myStmt.setDate(2, d2);
				
				myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				AnlVhcClass tempAnlVhcClass = convertRowToAnlVhcClass(myRs);
				list.add(tempAnlVhcClass);
			}
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private AnlVhcClass convertRowToAnlVhcClass(ResultSet myRs) throws Exception {
		int idvehicles = myRs.getInt("idvehicles");
		String vehiclesmark = myRs.getString("vehiclesmark");
		int vehiclescount = myRs.getInt("vehiclescount");
		int vehiclesperc = myRs.getInt("vehiclesperc");
		
		AnlVhcClass tempAnlVhcClass = new AnlVhcClass(idvehicles, vehiclesmark, vehiclescount, vehiclesperc);
		
		return tempAnlVhcClass;
	}
	
}
