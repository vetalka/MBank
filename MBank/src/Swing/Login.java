package Swing;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dbClasses.ConnectionFactory;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;

import mainClasses.Client;
import mainClasses.Managers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection con = null;
	Managers managers = Managers.getInstance();
	
	private JTextField textFieldName;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		try {
			con =  ConnectionFactory.getInstance().getConnection();
			JOptionPane.showMessageDialog(null, "Connection Successful!!!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please connect to your database!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 566, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(262, 94, 86, 20);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(191, 97, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(191, 133, 61, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(262, 130, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent ev) {
				
				try {
					
					String query = "SELECT * FROM Properties  where  prop_value = ? ";
					PreparedStatement pst = (PreparedStatement) con.prepareStatement(query);
					pst.setString(1, textFieldName.getText());
					pst.setString(1, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0 ;
					while(rs.next()){
						
						count = count + 1;
					}
				
					if (count == 1){
						
						JOptionPane.showMessageDialog(null, "Name and Password are corect ");
						frame.dispose();
						AdminSwing admin = new AdminSwing();
						admin.setVisible(true);
						
					}else if(count > 1){
						
						JOptionPane.showMessageDialog(null, "Duplicate Name and Passord");
						
					}else{
						
						JOptionPane.showMessageDialog(null, "Name and Password is not correct Try Again!");
					}
					
					rs.close();
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogin.setBounds(224, 195, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
}
