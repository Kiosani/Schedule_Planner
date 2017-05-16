package kio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import kio.core.*;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager.*;
import javax.swing.JLabel;
import javax.swing.JSeparator;

@SuppressWarnings({ "serial", "unused" })
public class PlannerMain extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private PlannerDAO plnrDAO;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlannerMain frame = new PlannerMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlannerMain() {
		setTitle("\u041E\u0433\u043B\u044F\u0434");
		
		try {
			plnrDAO = new PlannerDAO();
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
		}
		
		/*try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}*/
		
		try {
			//UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
			//UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();

		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("\u0414\u0440\u0443\u043A");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//print
				try {
					boolean complete = table.print();
				}
				catch (PrinterException pe) {
					JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + pe, "Помилка", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menu.add(menuItem_2);
		
		JMenuItem menuItem_1 = new JMenuItem("\u0417\u0430\u043A\u0440\u0438\u0442\u0438");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u0410\u043D\u0430\u043B\u0456\u0437");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_4 = new JMenuItem("\u0410\u043D\u0430\u043B\u0456\u0437 \u043F\u0430\u0441\u0430\u0436\u0438\u0440\u043E\u043F\u043E\u0442\u043E\u043A\u0443");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//pasanaliz
				//closef();
				AnalyzeAllDate anla = new AnalyzeAllDate();
				anla.setVisible(true);
			}
		});
		menu_1.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("\u0412\u0438\u043A\u043E\u0440\u0438\u0441\u0442\u0430\u043D\u043D\u044F \u0442\u0440\u0443\u0434. \u0442\u0430 \u043C\u0430\u0442. \u0440\u0435\u0441.");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//resanaliz
				//closef();
				AnalyzeUsage anla1 = new AnalyzeUsage();
				anla1.setVisible(true);
			}
		});
		menu_1.add(menuItem_5);
		
		JMenu menu_2 = new JMenu("\u0414\u043E\u0432\u0456\u0434\u043A\u0430");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u041F\u0440\u043E \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u0443");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//help
			}
		});
		menu_2.add(menuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		String[] tableschoosetext = {"Автомобільний парк", "Водії", "Розподіл і звітність", "Розклад рейсів"};
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox comboBox = new JComboBox(tableschoosetext);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("\u041E\u043D\u043E\u0432\u0438\u0442\u0438");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (comboBox.getSelectedItem().equals("Водії")) {
					try {
						
						List<Drivers> drvList = null;
						
						drvList = plnrDAO.getAllDrivers();
						
						DriversTableModel model = new DriversTableModel(drvList);
						table.setModel(model);
					}
					catch (Exception exc) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if (comboBox.getSelectedItem().equals("Розклад рейсів")) {
					try {
						
						List<Schedule> schList = null;
						

							schList = plnrDAO.getAllSchedule();
						
						ScheduleTableModel model = new ScheduleTableModel(schList);
						table.setModel(model);
					}
					catch (Exception exc) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if (comboBox.getSelectedItem().equals("Автомобільний парк")) {
					try {
						
						List<Vehicles> vhcList = null;
						

						vhcList = plnrDAO.getAllVehicles();
						
						VehiclesTableModel model = new VehiclesTableModel(vhcList);
						table.setModel(model);
					}
					catch (Exception exc) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if (comboBox.getSelectedItem().equals("Розподіл і звітність")) {
					try {
						
						List<Assigment> asgList = null;
						

						asgList = plnrDAO.getAllAssigment();
						
						AssigmentTableModel model = new AssigmentTableModel(asgList);
						table.setModel(model);
					}
					catch (Exception exc) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		
		JLabel label = new JLabel("\u0424\u0456\u043B\u044C\u0442\u0440");
		panel.add(label);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("  \u0414\u043E\u0434\u0430\u0442\u0438  ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add
				if (comboBox.getSelectedItem().equals("Водії")) {
					DriversDialog dialog = new DriversDialog(PlannerMain.this, plnrDAO);
					dialog.setVisible(true);
				}
				
				if (comboBox.getSelectedItem().equals("Автомобільний парк")) {
					VehiclesDialog dialog = new VehiclesDialog(PlannerMain.this, plnrDAO);
					dialog.setVisible(true);
				}
				
				if (comboBox.getSelectedItem().equals("Розклад рейсів")) {
					ScheduleDialog dialog = new ScheduleDialog(PlannerMain.this, plnrDAO);
					dialog.setVisible(true);
				}
				
				if (comboBox.getSelectedItem().equals("Розподіл і звітність")) {
					AssigmentDialog dialog = new AssigmentDialog(PlannerMain.this, plnrDAO);
					dialog.setVisible(true);
				}
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//update
				if (comboBox.getSelectedItem().equals("Водії")) {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Виберіть водія!", "Помилка", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Drivers tempDrivers = (Drivers) table.getValueAt(row, DriversTableModel.objCOL);
					DriversDialog dialog = new DriversDialog(PlannerMain.this, plnrDAO, tempDrivers, true);
					dialog.setVisible(true);
				}
				
				if (comboBox.getSelectedItem().equals("Автомобільний парк")) {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Виберіть авто!", "Помилка", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Vehicles tempVehicles = (Vehicles) table.getValueAt(row, VehiclesTableModel.objVhcCOL);
					VehiclesDialog dialog = new VehiclesDialog(PlannerMain.this, plnrDAO, tempVehicles, true);
					dialog.setVisible(true);
				}
				
				if (comboBox.getSelectedItem().equals("Розклад рейсів")) {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Виберіть рейс!", "Помилка", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Schedule tempSchedule = (Schedule) table.getValueAt(row, ScheduleTableModel.objRtsCOL);
					ScheduleDialog dialog = new ScheduleDialog(PlannerMain.this, plnrDAO, tempSchedule, true);
					dialog.setVisible(true);
				}
				
				if (comboBox.getSelectedItem().equals("Розподіл і звітність")) {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Виберіть запис!", "Помилка", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Assigment tempAssigment = (Assigment) table.getValueAt(row, AssigmentTableModel.schobjCOL);
					AssigmentDialog dialog = new AssigmentDialog(PlannerMain.this, plnrDAO, tempAssigment, true);
					dialog.setVisible(true);
				}
			}
		});
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton(" \u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438 ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//delete
				if (comboBox.getSelectedItem().equals("Водії")) {
					try {
						int row = table.getSelectedRow();
						if (row < 0) {
							JOptionPane.showMessageDialog(PlannerMain.this, "Виберіть водія!", "Помилка", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						int rsp = JOptionPane.showConfirmDialog(PlannerMain.this, "Видали водія?", "Видалення", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (rsp != JOptionPane.YES_OPTION) {
							return;
						}
						
						Drivers tempDrivers = (Drivers) table.getValueAt(row, DriversTableModel.objCOL);
						plnrDAO.deleteDrivers(tempDrivers.getIddrivers());
						refreshDriversView();
					}
					catch (Exception exc) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if (comboBox.getSelectedItem().equals("Автомобільний парк")) {
					try {
						int row = table.getSelectedRow();
						if (row < 0) {
							JOptionPane.showMessageDialog(PlannerMain.this, "Виберіть авто!", "Помилка", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						int rsp = JOptionPane.showConfirmDialog(PlannerMain.this, "Видали авто?", "Видалення", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (rsp != JOptionPane.YES_OPTION) {
							return;
						}
						
						Vehicles tempVehicles = (Vehicles) table.getValueAt(row, VehiclesTableModel.objVhcCOL);
						plnrDAO.deleteVehicles(tempVehicles.getIdvehicles());
						refreshVehiclesView();
					}
					catch (Exception exc) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if (comboBox.getSelectedItem().equals("Розклад рейсів")) {
					try {
						int row = table.getSelectedRow();
						if (row < 0) {
							JOptionPane.showMessageDialog(PlannerMain.this, "Виберіть рейс!", "Помилка", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						int rsp = JOptionPane.showConfirmDialog(PlannerMain.this, "Видали рейс?", "Видалення", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (rsp != JOptionPane.YES_OPTION) {
							return;
						}
						
						Schedule tempSchedule = (Schedule) table.getValueAt(row, ScheduleTableModel.objRtsCOL);
						plnrDAO.deleteSchedule(tempSchedule.getIdschedule());
						refreshScheduleView();
					}
					catch (Exception exc) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if (comboBox.getSelectedItem().equals("Розподіл і звітність")) {
					try {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(PlannerMain.this, "Виберіть запис!", "Помилка", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int rsp = JOptionPane.showConfirmDialog(PlannerMain.this, "Видали запис?", "Видалення", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (rsp != JOptionPane.YES_OPTION) {
						return;
					}
					
					Assigment tempAssigment = (Assigment) table.getValueAt(row, AssigmentTableModel.schobjCOL);
					plnrDAO.deleteAssigment(tempAssigment.getIdschedule());
					refreshAssigmentView();
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
				}
				}
			}
		});
		panel_1.add(btnNewButton_3);
	}
	
	public void refreshDriversView() {
		try {
			List<Drivers> drvList = plnrDAO.getAllDrivers();
			DriversTableModel model = new DriversTableModel(drvList);
			table.setModel(model);
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void refreshScheduleView() {
		try {
			List<Schedule> schList = plnrDAO.getAllSchedule();
			ScheduleTableModel model = new ScheduleTableModel(schList);
			table.setModel(model);
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void refreshVehiclesView() {
		try {
			List<Vehicles> vhcList = plnrDAO.getAllVehicles();
			VehiclesTableModel model = new VehiclesTableModel(vhcList);
			table.setModel(model);
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void refreshAssigmentView() {
		try {
			List<Assigment> asgList = plnrDAO.getAllAssigment();
			AssigmentTableModel model = new AssigmentTableModel(asgList);
			table.setModel(model);
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(PlannerMain.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void closef(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
