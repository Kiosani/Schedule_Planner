package kio.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

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
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import kio.core.*;

@SuppressWarnings({ "serial", "unchecked" })
public class AssigmentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	@SuppressWarnings("rawtypes")
	//private JComboBox comboBox1;
	JComboBox comboBox1 = new JComboBox();
	@SuppressWarnings("rawtypes")
	//private JComboBox comboBox2;
	JComboBox comboBox2 = new JComboBox();
	@SuppressWarnings("rawtypes")
	//private JComboBox comboBox3;
	JComboBox comboBox3 = new JComboBox();
	
	private PlannerDAO plnrDAO;
	private PlannerMain plnrMain;
	
	private Assigment prevAsg = null;
	private boolean updateMode = false;
	private JTextField textField_1;
	
	public AssigmentDialog(PlannerMain theplnrMain, PlannerDAO theplnrDAO, Assigment theprevAsg, boolean theupdateMode) {
		this();
		plnrDAO = theplnrDAO;
		plnrMain = theplnrMain;
		
		prevAsg = theprevAsg;
		updateMode = theupdateMode;
		
		if (updateMode) {
			setTitle("Редагувати запис");
			pGUI(prevAsg);
		}
	}
	
	{
		try {
	        
			Properties props = new Properties();
			props.load(new FileInputStream("lib/kio.properties"));
			
			String dburl = props.getProperty("dburl");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			
			Connection con3 = DriverManager.getConnection(dburl,user,password);
	        Statement st3 = con3.createStatement();
	        String s3 = "select idschedule from schedule";
	        ResultSet rs3 = st3.executeQuery(s3);
	          while(rs3.next())
	          {
	        	  Integer idschedule = rs3.getInt("idschedule");
	        	  comboBox1.addItem(idschedule);
	        	  
	          }
	      }
	    catch(Exception e){
	          JOptionPane.showMessageDialog(null, "ERROR");
	          }
	      }

	{
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("lib/kio.properties"));
			
			String dburl = props.getProperty("dburl");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			
			Connection con1 = DriverManager.getConnection(dburl,user,password);
	        Statement st1 = con1.createStatement();
	        String s1 = "select iddrivers from drivers";
	        ResultSet rs1 = st1.executeQuery(s1);
	          while(rs1.next())
	          {
	        	  Integer iddrivers = rs1.getInt("iddrivers");
	        	  comboBox2.addItem(iddrivers);
	        	  
	          }
	      }
	    catch(Exception e){
	          JOptionPane.showMessageDialog(null, "ERROR");
	          }
	      }
	
	{
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("lib/kio.properties"));
			
			String dburl = props.getProperty("dburl");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			
			Connection con2 = DriverManager.getConnection(dburl,user,password);
	        Statement st2 = con2.createStatement();
	        String s2 = "select idvehicles from vehicles";
	        ResultSet rs2 = st2.executeQuery(s2);
	          while(rs2.next())
	          {
	        	  Integer idvehicles = rs2.getInt("idvehicles");
	        	  comboBox3.addItem(idvehicles);
	        	  
	          }
	      }
	    catch(Exception e){
	          JOptionPane.showMessageDialog(null, "ERROR");
	          }
	      }
	
	private void pGUI (Assigment theAssigment) {
		comboBox1.setSelectedItem(theAssigment.getIdschedule());
		textField.setText(String.valueOf(theAssigment.getDate()));
		textField_1.setText(String.valueOf(theAssigment.getTickets()));
		comboBox2.setSelectedItem(theAssigment.getIddrivers());
		comboBox3.setSelectedItem(theAssigment.getIdvehicles());
	}
	
	public AssigmentDialog (PlannerMain theplnrMain, PlannerDAO theplnrDAO) {
		this(theplnrMain, theplnrDAO, null, false);
	}
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ScheduleDialog dialog = new ScheduleDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public AssigmentDialog() {
		setTitle("\u0414\u043E\u0434\u0430\u0442\u0438 \u0437\u0430\u043F\u0438\u0441");
		setBounds(100, 100, 450, 244);
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
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("\u0420\u0435\u0439\u0441");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			//comboBox1 = new JComboBox();
			contentPanel.add(comboBox1, "4, 2, fill, default");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u0414\u0430\u0442\u0430");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 4, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("\u0412\u043E\u0434\u0456\u0439");
			contentPanel.add(lblNewLabel_3, "2, 6, right, default");
		}
		{
			//comboBox2 = new JComboBox();
			contentPanel.add(comboBox2, "4, 6, fill, default");
		}
		{
			JLabel lblNewLabel_4 = new JLabel("\u0410\u0432\u0442\u043E");
			contentPanel.add(lblNewLabel_4, "2, 8, right, default");
		}
		{
			//JComboBox comboBox3 = new JComboBox();
			//comboBox3 = new JComboBox();
			contentPanel.add(comboBox3, "4, 8, fill, default");
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u0411\u0456\u043B\u0435\u0442\u0438");
			contentPanel.add(lblNewLabel_2, "2, 10, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 10, fill, default");
			textField_1.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0417\u0431\u0435\u0440\u0435\u0433\u0442\u0438");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//add
						saveAssigment();
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
	
	protected void saveAssigment() {
		int addSchId = (int) comboBox1.getSelectedItem();
		Date addDate = Date.valueOf(textField.getText());
		int addDrvId = (int) comboBox2.getSelectedItem();
		int addVhcId = (int) comboBox3.getSelectedItem();
		int addTick = Integer.valueOf(textField_1.getText());
		
		Assigment tempAssigment = null;
		
		if (updateMode) {
			tempAssigment = prevAsg;
			
			tempAssigment.setIdschedule(addSchId);
			tempAssigment.setDate(addDate);
			tempAssigment.setIddrivers(addDrvId);
			tempAssigment.setIdvehicles(addVhcId);
			tempAssigment.setTickets(addTick);
			
		}
		else {
			tempAssigment = new Assigment(addSchId, addDate, addDrvId, addVhcId, addTick);
		}
		
		try {
			
			if (updateMode) {
				plnrDAO.updateAssigment(tempAssigment);
				
				setVisible(false);
				dispose();
				
				plnrMain.refreshAssigmentView();
				
				JOptionPane.showMessageDialog(plnrMain, "Запис успішно редагований.", "Запис редаговано", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				plnrDAO.addAssigment(tempAssigment);
				
				setVisible(false);
				dispose();
				
				plnrMain.refreshAssigmentView();
				
				JOptionPane.showMessageDialog(plnrMain, "Запис успішно доданий.", "Запис доданий", JOptionPane.INFORMATION_MESSAGE);
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
