package Swing;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;




import com.mysql.jdbc.PreparedStatement;

import dbClasses.ConnectionFactory;
import dbClasses.PKGenerator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.SQLException;




public class ChangeSystemProperties extends JFrame {

	private JPanel contentPane;
	private JList typeList;
	private JTextField textField;
	Connection con = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeSystemProperties frame = new ChangeSystemProperties();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ChangeSystemProperties() {
		
		super("MBank");
		
		try {
			con =  ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		typeList = new JList ();
        typeList.setBounds(47, 41, 259, 324);
		
		DefaultListModel typeModel = new DefaultListModel();
		typeModel.addElement( new Type(0, "regular_deposit_rate"));
		typeModel.addElement(new Type(1,"gold_deposit_rate"));
		typeModel.addElement(new Type(2, "platinum_deposit_rate"));
		typeModel.addElement( new Type(3, "regular_deposit_commission"));
		typeModel.addElement(new Type(4,"gold_deposit_commission"));
		typeModel.addElement(new Type(5, "platinum_deposit_commission"));
		typeModel.addElement( new Type(6, "regular_credit_limit"));
		typeModel.addElement(new Type(7,"gold_credit_limit"));
		typeModel.addElement(new Type(8, "platinum_credit_limit"));
		typeModel.addElement( new Type(9, "commission_rate"));
		typeModel.addElement(new Type(10,"regular_daily_interest"));
		typeModel.addElement(new Type(11, "gold_daily_interest"));
		typeModel.addElement( new Type(12, "platinum_daily_interest"));
		typeModel.addElement(new Type(13,"pre_open_fee"));
		typeModel.addElement(new Type(14, "admin_username"));
		typeModel.addElement(new Type(15, "admin_password"));
		
		contentPane.setLayout(null);
		typeList.setModel(typeModel);
		
		typeList.setPreferredSize(new Dimension(110, 60));
		typeList.setBorder(BorderFactory.createEtchedBorder());
		typeList.setSelectedIndex(1);
		contentPane.add(typeList);
		
		JLabel lblPropertyName = new JLabel("Property Name");
		lblPropertyName.setBounds(47, 16, 97, 14);
		contentPane.add(lblPropertyName);
		
		JLabel lblPropertyValue = new JLabel("New Property Value");
		lblPropertyValue.setBounds(344, 44, 117, 14);
		contentPane.add(lblPropertyValue);
		
		textField = new JTextField();
		textField.setBounds(459, 41, 117, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                Type typekey = (Type)typeList.getSelectedValue();

				try {
		                   String queryString = "UPDATE Properties"
		                   		  +" SET prop_value = ? "
		                  		  +" WHERE prop_key = ?" ;
		                   PreparedStatement ptmt = (PreparedStatement) con.prepareStatement(queryString);
		                   ptmt.setString(1, textField.getText());
		                   ptmt.setString(2, typekey.toString());
		                   
		                   if (textField.getText().equals(null) || textField.getText().equals("")
		                		   || typekey.toString().equals(null) || typekey.toString().equals("")){
		                	   
		                	   throw new UncheckedIOException("One of column is empty!", null);
		                	   
		                   }else{
		                	   
		                   
		                   ptmt.executeUpdate();
		                 
		                   }
		           } catch (Exception e1) {
		        	   e1.printStackTrace();
		           }
				
				dispose();
				AdminSwing admin = new AdminSwing();
				admin.setVisible(true);
			}
		});
		btnUpdate.setBounds(425, 130, 89, 23);
		contentPane.add(btnUpdate);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBounds(10, 367, 663, 44);
		contentPane.add(toolBar);
		
		JButton btnBackToMain = new JButton("Back To Main Frame");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminSwing admin = new AdminSwing();
				admin.setVisible(true);
			}
		});
		toolBar.add(btnBackToMain);
		
	}
	 class Type{
			
			private int id ;
			private String text;
			
			public Type (int id , String text){
				
				this.id = id;
				this.text = text;
			}
			
			public String toString (){
				
				return text;
			}
			
			public int getId(){
				return id;
			}
		}
}
