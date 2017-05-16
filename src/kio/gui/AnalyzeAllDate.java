package kio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import kio.core.*;

@SuppressWarnings({ "serial", "unused" })
public class AnalyzeAllDate extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	
	private PlannerDAO plnrDAO;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyzeAllDate frame = new AnalyzeAllDate();
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
	public AnalyzeAllDate() {
		setTitle("\u0410\u043D\u0430\u043B\u0456\u0437");
		
		try {
			plnrDAO = new PlannerDAO();
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
		}
		
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
		setBounds(100, 100, 549, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("\u041F\u0435\u0440\u0456\u043E\u0434 \u0437:");
		panel.add(lblNewLabel, "2, 4");
		
		textField = new JTextField();
		panel.add(textField, "2, 6, left, top");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u041F\u0435\u0440\u0456\u043E\u0434 \u0434\u043E:");
		panel.add(lblNewLabel_1, "2, 8");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "2, 10, fill, default");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u0410\u043D\u0430\u043B\u0456\u0437");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1 = textField.getText();
				String date2 = textField_1.getText();
				String fil1 = textField_2.getText();
				String fil2 = textField_3.getText();

						if (date1 != null && date2 != null && fil1 != null && fil2 != null) {
							try {
								String sql = "select a.idassigment, a.idschedule, a.date, a.tickets/v.vehiclests*100 as zapovn from assigment a, vehicles v where a.idvehicles = v.idvehicles and a.date <= ? and a.date >= ? order by a.idassigment";
								List<Analyze1> anl1List = null;
								List<Analyze2> anl2List = null;
								anl1List = plnrDAO.getAnalyze1(Date.valueOf(date2),Date.valueOf(date1), fil1, fil2);
								anl2List = plnrDAO.getAnalyze2(Date.valueOf(date2),Date.valueOf(date1), fil1, fil2);
								
								Analyze1TableModel model = new Analyze1TableModel(anl1List);
								table.setModel(model);
								Analyze2TableModel model1 = new Analyze2TableModel(anl2List);
								table_1.setModel(model1);
							} catch (Exception exc) {
								JOptionPane.showMessageDialog(AnalyzeAllDate.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							try {
								String sql = "select a.idassigment, a.idschedule, a.date, a.tickets/v.vehiclests*100 as zapovn from assigment a, vehicles v where a.idvehicles = v.idvehicles and a.date <= ? and a.date >= ? order by a.idassigment";
								List<Analyze1> anl1List = null;
								List<Analyze2> anl2List = null;
								anl1List = plnrDAO.getAnalyze1a(Date.valueOf(date2),Date.valueOf(date1));
								anl2List = plnrDAO.getAnalyze2a(Date.valueOf(date2),Date.valueOf(date1));
								
								Analyze1TableModel model = new Analyze1TableModel(anl1List);
								table.setModel(model);
								Analyze2TableModel model1 = new Analyze2TableModel(anl2List);
								table_1.setModel(model1);
							} catch (Exception exc) {
								JOptionPane.showMessageDialog(AnalyzeAllDate.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
		});
		
		JLabel lblNewLabel_2 = new JLabel("\u0412\u0456\u0434\u0431\u0443\u0442\u0442\u044F \u0437:");
		panel.add(lblNewLabel_2, "2, 14");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "2, 16, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u041F\u0440\u0438\u0431\u0443\u0442\u0442\u044F \u0432:");
		panel.add(lblNewLabel_3, "2, 18");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "2, 20, fill, default");
		textField_3.setColumns(10);
		panel.add(btnNewButton, "2, 24");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("\u0417\u0430\u043F\u043E\u0432\u043D\u044E\u0432\u0430\u043D\u0456\u0441\u0442\u044C", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("\u041D\u0435\u0440\u0456\u0432\u043D\u043E\u043C\u0456\u0440\u043D\u0456\u0441\u0442\u044C \u0440\u043E\u0437\u043F\u043E\u0434\u0456\u043B\u0443", null, scrollPane_1, null);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
	}

}
