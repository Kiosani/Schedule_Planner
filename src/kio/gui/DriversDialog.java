package kio.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import kio.core.*;

@SuppressWarnings("serial")
public class DriversDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	private PlannerDAO plnrDAO;
	private PlannerMain plnrMain;
	
	private Drivers prevDrv = null;
	private boolean updateMode = false;
	
	public DriversDialog(PlannerMain theplnrMain, PlannerDAO theplnrDAO, Drivers theprevDrv, boolean theupdateMode) {
		this();
		plnrDAO = theplnrDAO;
		plnrMain = theplnrMain;
		
		prevDrv = theprevDrv;
		updateMode = theupdateMode;
		
		if (updateMode) {
			setTitle("Редагувати водія");
			pGUI(prevDrv);
		}
	}
	
	private void pGUI (Drivers theDrivers) {
		textField.setText(theDrivers.getDriversname());
		textField_1.setText(theDrivers.getDriversregistration());
		textField_2.setText(theDrivers.getDriverspasid());
	}
	
	public DriversDialog (PlannerMain theplnrMain, PlannerDAO theplnrDAO) {
		this(theplnrMain, theplnrDAO, null, false);
	}
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			DriversDialog dialog = new DriversDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public DriversDialog() {
		setTitle("\u0414\u043E\u0434\u0430\u0442\u0438 \u0432\u043E\u0434\u0456\u044F");
		setBounds(100, 100, 450, 180);
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
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("\u041F\u0406\u0411 \u0432\u043E\u0434\u0456\u044F");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u041C\u0456\u0441\u0446\u0435 \u043F\u0440\u043E\u0436\u0438\u0432\u0430\u043D\u043D\u044F");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u041F\u0430\u0441\u043F\u043E\u0440\u0442");
			contentPanel.add(lblNewLabel_2, "2, 6, right, default");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "4, 6, fill, default");
			textField_2.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0417\u0431\u0435\u0440\u0435\u0433\u0442\u0438");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveDrivers();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u0412\u0456\u0434\u043C\u0456\u043D\u0438\u0442\u0438");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveDrivers() {
		String addDrvName = textField.getText();
		String addDrvReg = textField_1.getText();
		String addDrvPas = textField_2.getText();
		
		Drivers tempDrivers = null;
		
		if (updateMode) {
			tempDrivers = prevDrv;
			
			tempDrivers.setDriversname(addDrvName);
			tempDrivers.setDriversregistration(addDrvReg);
			tempDrivers.setDriverspasid(addDrvPas);
			
		}
		else {
			tempDrivers = new Drivers(addDrvName, addDrvReg, addDrvPas);
		}
		
		try {
			
			if (updateMode) {
				plnrDAO.updateDrivers(tempDrivers);
				
				setVisible(false);
				dispose();
				
				plnrMain.refreshDriversView();
				
				JOptionPane.showMessageDialog(plnrMain, "Водій успішно редагований.", "Водій редагований", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				plnrDAO.addDrivers(tempDrivers);
				
				setVisible(false);
				dispose();
				
				plnrMain.refreshDriversView();
				
				JOptionPane.showMessageDialog(plnrMain, "Водій успішно додан.", "Водій додан", JOptionPane.INFORMATION_MESSAGE);
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
