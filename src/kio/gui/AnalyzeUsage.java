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
public class AnalyzeUsage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	
	private PlannerDAO plnrDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyzeUsage frame = new AnalyzeUsage();
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
	public AnalyzeUsage() {
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
		panel.add(lblNewLabel, "2, 8");
		
		textField = new JTextField();
		panel.add(textField, "2, 10, left, top");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u041F\u0435\u0440\u0456\u043E\u0434 \u0434\u043E:");
		panel.add(lblNewLabel_1, "2, 12");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "2, 14, fill, default");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u0410\u043D\u0430\u043B\u0456\u0437");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1 = textField.getText();
				String date2 = textField_1.getText();

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
							JOptionPane.showMessageDialog(AnalyzeUsage.this, "Помилка: " + exc, "Помилка", JOptionPane.ERROR_MESSAGE);
						}
					}
		});
		panel.add(btnNewButton, "2, 20");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("\u0412\u0438\u043A\u043E\u0440\u0438\u0441\u0442\u0430\u043D\u043D\u044F \u0442\u0440\u0443\u0434.\u0440\u0435\u0441.", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("\u0412\u0438\u043A\u043E\u0440\u0438\u0441\u0442\u0430\u043D\u043D\u044F \u043C\u0430\u0442.\u0440\u0435\u0441.", null, scrollPane_1, null);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
	}

}
