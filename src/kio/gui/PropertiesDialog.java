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
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("serial")
public class PropertiesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTextField textField;
	private static JTextField textField_1;
	private static JPasswordField passwordField;
	private static OpenFrame opfrm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PropertiesDialog dialog = new PropertiesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("lib/kio.properties"));
			
			String dburl = props.getProperty("dburl");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			
			textField.setText(dburl);
			textField_1.setText(user);
			passwordField.setText(password);
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(opfrm, "Помилка: " + e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
		}
}

	/**
	 * Create the dialog.
	 */
	public PropertiesDialog() {
		setTitle("\u041D\u0430\u043B\u0430\u0448\u0442\u0443\u0432\u0430\u043D\u043D\u044F");
		setBounds(100, 100, 450, 187);
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
			JLabel lblNewLabel = new JLabel("URL");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u041A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
			contentPanel.add(lblNewLabel_2, "2, 6, right, default");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "4, 6, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent arg0) {
						//save
						/*Properties prop = new Properties();
						OutputStream output = null;*/
						
						try {
							FileInputStream in = new FileInputStream("lib/kio.properties");
							Properties props = new Properties();
							props.load(in);
							in.close();

							FileOutputStream out = new FileOutputStream("lib/kio.properties");
							props.setProperty("dburl", textField.getText());
							props.setProperty("user", textField_1.getText());
							props.setProperty("password", passwordField.getText());
							props.store(out, null);
							out.close();
						}
						catch (IOException  io) {
							JOptionPane.showMessageDialog(opfrm, "Помилка: " + io.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
						}
						finally {
							close();
							OpenFrame dty1 = new OpenFrame();
							dty1.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						close();
						OpenFrame dty = new OpenFrame();
						dty.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
