package kio.gui;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import kio.core.Analyze2;

@SuppressWarnings("serial")
public class Analyze2TableModel extends AbstractTableModel {
	private static final int idassigmentCOL = 0;
	private static final int idscheduleCOL = 1;
	private static final int dateCOL = 2;
	private static final int rozpCOL = 3;
	
	private String[] columnNames = { "idassigment", "idschedule", "date", "zapovn" };
	private List<Analyze2> anl2List;
	
	public Analyze2TableModel(List<Analyze2> thAnl2) {
		anl2List = thAnl2;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return anl2List.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Analyze2 tempAnalyze2 = anl2List.get(row);
		
		switch (col) {
		case idassigmentCOL:
			return tempAnalyze2.getidassigment();
		case idscheduleCOL:
			return tempAnalyze2.getidschedule();
		case dateCOL:
			return tempAnalyze2.getDate();
		case rozpCOL:
			return tempAnalyze2.getrozp();
		default:
			return tempAnalyze2.getidassigment();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
	}
}
