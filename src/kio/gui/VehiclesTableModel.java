package kio.gui;

import kio.core.*;

import java.util.*;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class VehiclesTableModel extends AbstractTableModel {
	public static final int objVhcCOL = -1;
	private static final int idvehiclesCOL = 0;
	private static final int vehiclesmarkCOL = 1;
	private static final int vehiclesvrpCOL = 2;
	private static final int vehiclestsCOL = 3;
	
	private String[] columnNames = { "Код авто", "Марка авто", "Номерний знак", "К-ть місць" };
	private List<Vehicles> vhcList;
	
	public VehiclesTableModel(List<Vehicles> thVhc) {
		vhcList = thVhc;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return vhcList.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Vehicles tempVehicles = vhcList.get(row);
		
		switch (col) {
		case idvehiclesCOL:
			return tempVehicles.getIdvehicles();
		case vehiclesmarkCOL:
			return tempVehicles.getVehiclesmark();
		case vehiclesvrpCOL:
			return tempVehicles.getVehiclesvrp();
		case vehiclestsCOL:
			return tempVehicles.getVehiclests();
		case objVhcCOL:
			return tempVehicles;
		default:
			return tempVehicles.getIdvehicles();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
	}
}
