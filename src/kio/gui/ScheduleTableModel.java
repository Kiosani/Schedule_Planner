package kio.gui;

import kio.core.*;

import javax.swing.table.AbstractTableModel;

import java.util.*;

@SuppressWarnings("serial")
public class ScheduleTableModel extends AbstractTableModel {
	public static final int objRtsCOL = -1;
	private static final int idscheduleCOL = 0;
	private static final int routestartCOL = 1;
	private static final int routeendCOL = 2;
	private static final int dayCOL = 3;
	private static final int timestartCOL = 4;
	private static final int timeendCOL = 5;
	private static final int prizeCOL = 6;
	private static final int commentCOL = 7;
	
	private String[] columnNames = { "Код рейсу", "Місце відбуття", "Місце прибуття", "День", "Час відбуття", "Час прибуття", "Ціна", "Проміжні пункти" };
	private List<Schedule> schList;
	
	public ScheduleTableModel(List<Schedule> thSch) {
		schList = thSch;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		return schList.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Schedule tempSchedule = schList.get(row);
		
		switch (col) {
		case idscheduleCOL:
			return tempSchedule.getIdschedule();
		case routestartCOL:
			return tempSchedule.getRoutestart();
		case routeendCOL:
			return tempSchedule.getRouteend();
		case dayCOL:
			return tempSchedule.getDay();
		case timestartCOL:
			return tempSchedule.getTimestart();
		case timeendCOL:
			return tempSchedule.getTimeend();
		case prizeCOL:
			return tempSchedule.getPrize();
		case commentCOL:
			return tempSchedule.getComment();
		case objRtsCOL:
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
