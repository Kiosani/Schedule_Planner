package kio.gui;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import kio.core.*;

@SuppressWarnings("serial")
public class AssigmentTableModel extends AbstractTableModel {
	public static final int schobjCOL = -1;
	private static final int idassigment = 0;
	private static final int idschedule = 1;
	private static final int iddate = 2;
	private static final int iddrivers = 3;
	private static final int idvehicles = 4;
	private static final int idtickets = 5;
	
	private String[] columnNames = { "Звіт", "Рейс", "Дата", "Водій", "Авто", "К-ть пас." };
	private List<Assigment> asgList;
	
	public AssigmentTableModel(List<Assigment> thAsg) {
		asgList = thAsg;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return asgList.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Assigment tempSchedule = asgList.get(row);
		
		switch (col) {
		case idassigment:
			return tempSchedule.getIdAssigment();
		case idschedule:
			return tempSchedule.getIdschedule();
		case iddate:
			return tempSchedule.getDate();
		case iddrivers:
			return tempSchedule.getIddrivers();
		case idvehicles:
			return tempSchedule.getIdvehicles();
		case idtickets:
			return tempSchedule.getTickets();
		case schobjCOL:
			return tempSchedule;
		default:
			return tempSchedule.getIdschedule();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
	}
	
}
