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
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import kio.core.*;

@SuppressWarnings("serial")
public class VehiclesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	
	private PlannerDAO plnrDAO;
	private PlannerMain plnrMain;
	private Vehicles prevVhc = null;
	private boolean updateMode = false;
	private JTextField textField_2;
	
	public VehiclesDialog(PlannerMain theplnrMain, PlannerDAO theplnrDAO, Vehicles theprevVhc, boolean theupdateMode) {
		this();
		plnrDAO = theplnrDAO;
		plnrMain = theplnrMain;
		
		prevVhc = theprevVhc;
		updateMode = theupdateMode;
		
		if (updateMode) {
			setTitle("Редагувати авто");
			pGUI(prevVhc);
		}
	}
	
	private void pGUI (Vehicles theVehicles) {
		textField.setText(theVehicles.getVehiclesmark());
		textField_1.setText(theVehicles.getVehiclesvrp());
		textField_2.setText(String.valueOf(theVehicles.getVehiclests()));
	}
	
	public VehiclesDialog (PlannerMain theplnrMain, PlannerDAO theplnrDAO) {
		this(theplnrMain, theplnrDAO, null, false);
	}

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			VehiclesDialog dialog = new VehiclesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public VehiclesDialog() {
		setTitle("\u0414\u043E\u0434\u0430\u0442\u0438 \u0430\u0432\u0442\u043E\u043C\u043E\u0431\u0456\u043B\u044C");
		setBounds(100, 100, 450, 160);
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
			JLabel lblNewLabel = new JLabel("\u041C\u0430\u0440\u043A\u0430 \u0430\u0432\u0442\u043E\u043C\u043E\u0431\u0456\u043B\u044F");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u041D\u043E\u043C\u0435\u0440\u043D\u0438\u0439 \u0437\u043D\u0430\u043A");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u041A-\u0442\u044C \u043C\u0456\u0441\u0446\u044C");
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
						//add
						saveVehicles();
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

	protected void saveVehicles() {
		String addVhcMark = textField.getText();
		String addVhcVrp = textField_1.getText();
		int addVhcSts = Integer.parseInt(textField_2.getText());
		
		Vehicles tempVehicles = null;
		
		if (updateMode) {
			tempVehicles = prevVhc;
			
			tempVehicles.setVehiclesmark(addVhcMark);
			tempVehicles.setVehiclesvrp(addVhcVrp);
			tempVehicles.setVehiclests(addVhcSts);
			
		}
		else {
			tempVehicles = new Vehicles(addVhcMark, addVhcVrp, addVhcSts);
		}
		
		try {
			
			if (updateMode) {
				plnrDAO.updateVehicles(tempVehicles);
				
				setVisible(false);
				dispose();
				
				plnrMain.refreshVehiclesView();
				
				JOptionPane.showMessageDialog(plnrMain, "Авто успішно редаговано.", "Авто редаговано", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				plnrDAO.addVehicles(tempVehicles);
				
				setVisible(false);
				dispose();
				
				plnrMain.refreshVehiclesView();
				
				JOptionPane.showMessageDialog(plnrMain, "Авто успішно додано.", "Авто додано", JOptionPane.INFORMATION_MESSAGE);
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
