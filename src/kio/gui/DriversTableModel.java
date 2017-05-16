package kio.gui;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import kio.core.Drivers;

@SuppressWarnings("serial")
public class DriversTableModel extends AbstractTableModel {
	public static final int objCOL = -1;
	private static final int iddriversCOL = 0;
	private static final int driversnameCOL = 1;
	private static final int driversregistrationCOL = 2;
	private static final int driverspasidCOL = 3;
	
	private String[] columnNames = { "Код водія", "ПІБ", "Регістрація", "Номер паспорта" };
	private List<Drivers> drvList;
	
	public DriversTableModel(List<Drivers> thDrv) {
		drvList = thDrv;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return drvList.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Drivers tempDrivers = drvList.get(row);
		
		switch (col) {
		case iddriversCOL:
			return tempDrivers.getIddrivers();
		case driversnameCOL:
			return tempDrivers.getDriversname();
		case driversregistrationCOL:
			return tempDrivers.getDriversregistration();
		case driverspasidCOL:
			return tempDrivers.getDriverspasid();
		case objCOL:
			return tempDrivers;
		default:
			return tempDrivers.getIddrivers();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
	}
}
