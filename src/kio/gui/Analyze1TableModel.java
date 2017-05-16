package kio.gui;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import kio.core.Analyze1;

@SuppressWarnings("serial")
public class Analyze1TableModel extends AbstractTableModel {
	private static final int idassigmentCOL = 0;
	private static final int idscheduleCOL = 1;
	private static final int dateCOL = 2;
	private static final int zapovnCOL = 3;
	
	private String[] columnNames = { "Звіт", "Рейс", "Дата", "Заповненість" };
	private List<Analyze1> anl1List;
	
	public Analyze1TableModel(List<Analyze1> thAnl1) {
		anl1List = thAnl1;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return anl1List.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Analyze1 tempAnalyze1 = anl1List.get(row);
		
		switch (col) {
		case idassigmentCOL:
			return tempAnalyze1.getidassigment();
		case idscheduleCOL:
			return tempAnalyze1.getidschedule();
		case dateCOL:
			return tempAnalyze1.getDate();
		case zapovnCOL:
			return tempAnalyze1.getzapovn();
		default:
			return tempAnalyze1.getidassigment();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
	}
}
