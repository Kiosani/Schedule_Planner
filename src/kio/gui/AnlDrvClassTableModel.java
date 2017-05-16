package kio.gui;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import kio.core.AnlDrvClass;

@SuppressWarnings("serial")
public class AnlDrvClassTableModel extends AbstractTableModel {
	private static final int iddriversCOL = 0;
	private static final int driversnameCOL = 1;
	private static final int driverscountCOL = 2;
	private static final int driverspercCOL = 3;
	
	private String[] columnNames = { "iddrivers", "driversname", "driverscount", "driversperc" };
	private List<AnlDrvClass> anlDrvList;
	
	public AnlDrvClassTableModel(List<AnlDrvClass> thAnlstd) {
		anlDrvList = thAnlstd;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return anlDrvList.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		AnlDrvClass tempAnalyzeDrv = anlDrvList.get(row);
		
		switch (col) {
		case iddriversCOL:
			return tempAnalyzeDrv.getIddrivers();
		case driversnameCOL:
			return tempAnalyzeDrv.getDriversname();
		case driverscountCOL:
			return tempAnalyzeDrv.getDriverscount();
		case driverspercCOL:
			return tempAnalyzeDrv.getDriversperc();
		default:
			return tempAnalyzeDrv.getIddrivers();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
	}
}
