package kio.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

//import javafx.scene.control.ComboBox;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Properties;

import kio.core.*;

import javax.swing.JComboBox;

@SuppressWarnings({ "serial", "unchecked", "unused" })
public class ScheduleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private PlannerDAO plnrDAO;
	private PlannerMain plnrMain;
	private Schedule prevSch = null;
	private boolean updateMode = false;
	private JTextField textField_4;
	private JTextField textField_5;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox = new JComboBox();
	
	public ScheduleDialog(PlannerMain theplnrMain, PlannerDAO theplnrDAO, Schedule theprevSch, boolean theupdateMode) {
		this();
		plnrDAO = theplnrDAO;
		plnrMain = theplnrMain;
		
		prevSch = theprevSch;
		updateMode = theupdateMode;
		
		if (updateMode) {
			setTitle("Редагувати рейс");
			pGUI(prevSch);
		}
	}
	
	{
		try {
	        
			Properties props = new Properties();
			props.load(new FileInputStream("lib/kio.properties"));
			
			String dburl = props.getProperty("dburl");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			
			Connection conday = DriverManager.getConnection(dburl,user,password);
	        Statement stday = conday.createStatement();
	        String sday = "select day from week";
	        ResultSet rsday = stday.executeQuery(sday);
	          while(rsday.next())
	          {
	        	  String day = rsday.getString("day");
	        	  comboBox.addItem(day);
	        	  
	          }
	      }
	    catch(Exception e){
	          JOptionPane.showMessageDialog(null, "ERROR");
	          }
	}
	
	private void pGUI (Schedule theSchedule) {
		textField.setText(theSchedule.getRoutestart());
		textField_1.setText(theSchedule.getRouteend());
		comboBox.setSelectedItem(theSchedule.getDay());
		textField_4.setText(String.valueOf(theSchedule.getTimestart()));
		textField_5.setText(String.valueOf(theSchedule.getTimeend()));
		textField_2.setText(String.valueOf(theSchedule.getPrize()));
		textField_3.setText(theSchedule.getComment());
	}
	
	public ScheduleDialog (PlannerMain theplnrMain, PlannerDAO theplnrDAO) {
		this(theplnrMain, theplnrDAO, null, false);
	}
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			RoutesDialog dialog = new RoutesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ScheduleDialog() {
		setTitle("\u0414\u043E\u0434\u0430\u0442\u0438 \u0440\u0435\u0439\u0441");
		setBounds(100, 100, 450, 290);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
		{
			JLabel lblNewLabel = new JLabel("\u041C\u0456\u0441\u0446\u0435 \u0432\u0456\u0434\u0431\u0443\u0442\u0442\u044F");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u041C\u0456\u0441\u0446\u0435 \u043F\u0440\u0438\u0431\u0443\u0442\u0442\u044F");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("\u0414\u0435\u043D\u044C");
			contentPanel.add(lblNewLabel_4, "2, 6, right, default");
		}
		{
			//JComboBox comboBox = new JComboBox();
			contentPanel.add(comboBox, "4, 6, fill, default");
		}
		{
			JLabel lblNewLabel_5 = new JLabel("\u0427\u0430\u0441 \u0432\u0456\u0434\u0431\u0443\u0442\u0442\u044F");
			contentPanel.add(lblNewLabel_5, "2, 8, right, default");
		}
		{
			textField_4 = new JTextField();
			contentPanel.add(textField_4, "4, 8, fill, default");
			textField_4.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("\u0427\u0430\u0441 \u043F\u0440\u0438\u0431\u0443\u0442\u0442\u044F");
			contentPanel.add(lblNewLabel_6, "2, 10, right, default");
		}
		{
			textField_5 = new JTextField();
			contentPanel.add(textField_5, "4, 10, fill, default");
			textField_5.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u0412\u0430\u0440\u0442\u0456\u0441\u0442\u044C");
			contentPanel.add(lblNewLabel_2, "2, 12, right, default");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "4, 12, fill, default");
			textField_2.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("\u041C\u0430\u0440\u0448\u0440\u0443\u0442");
			contentPanel.add(lblNewLabel_3, "2, 14, right, default");
		}
		{
			textField_3 = new JTextField();
			contentPanel.add(textField_3, "4, 14, fill, default");
			textField_3.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0417\u0431\u0435\u0440\u0435\u0433\u0442\u0438");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//add
						saveSchedule();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u0412\u0456\u0434\u043C\u0456\u043D\u0438\u0442\u0438");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						close();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveSchedule() {
		String addSchStr = textField.getText();
		String addSchEdn = textField_1.getText();
		String addDay = (String) comboBox.getSelectedItem();
		String addTimestart = String.valueOf(textField_4.getText());
		String addTimeend = String.valueOf(textField_5.getText());
		int addSchPrz = Integer.valueOf(textField_2.getText());
		String addSchCm = textField_3.getText();
		
		Schedule tempSchedule = null;
		
		if (updateMode) {
			tempSchedule = prevSch;
			
			tempSchedule.setRoutestart(addSchStr);
			tempSchedule.setRouteend(addSchEdn);
			tempSchedule.setDay(addDay);
			tempSchedule.setTimestart(addTimestart);
			tempSchedule.setTimeend(addTimeend);
			tempSchedule.setPrize(addSchPrz);
			tempSchedule.setComment(addSchCm);
			
		}
		else {
			tempSchedule = new Schedule(addSchStr, addSchEdn, addDay, addTimestart, addTimeend, addSchPrz, addSchCm);
		}
		
		try {
			
			if (updateMode) {
				plnrDAO.updateSchedule(tempSchedule);
				
				setVisible(false);
				dispose();
				
				plnrMain.refreshScheduleView();
				
				JOptionPane.showMessageDialog(plnrMain, "Рейс успішно редаговано.", "Рейс редаговано", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				plnrDAO.addSchedule(tempSchedule);
				
				setVisible(false);
				dispose();
				
				plnrMain.refreshScheduleView();
				
				JOptionPane.showMessageDialog(plnrMain, "Рейс успішно додано.", "Рейс додано", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(plnrMain, "Помилка: " + exc.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
