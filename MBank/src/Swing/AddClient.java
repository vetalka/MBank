package Swing;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import Exepsions.CheckedExceptions;




import Exepsions.UncheckedRuntimeException;
import dbClasses.ConnectionPoll;
import dbClasses.PKGenerator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import mainClasses.ClientType;
import mainClasses.Managers;


public class AddClient extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldPassword;
	private JLabel lblType;
	private JLabel lblName;
	private JLabel lblAddClent;
	private JLabel lblAddress;
	private JTextField textFieldEmail;
	private JLabel lblEmail;
	private JTextField textFieldPhone;
	private JLabel lblPhone;
	private JTextField textFieldComment;
	private JLabel lblComment;
	private JButton btnOk;

	private JList typeList;
	
	Connection connection = null;
    java.sql.PreparedStatement ptmt = null;
    ResultSet resultSet = null;
	
	 
	 private Connection getConnection() throws SQLException {
         Connection conn;
         conn = ConnectionPoll.getInstance().getConnection();
         return conn;
 }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddClient frame = new AddClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private JLabel lblAccountBean;
	private JLabel lblClientBean;
	private JTextField textFieldBalance;
	private JTextField textFieldCommentA;
	private JLabel lblBalance;
	private JLabel lblComment_1;
	private JLabel lblCreditLimit;
	private JList typeList1;
	private JToolBar toolBar;
	private JButton btnCSP;
	
	
	/**
	 * Create the frame.
	 */
	public AddClient() throws CheckedExceptions, UncheckedRuntimeException{
		
		super("MBank");
		
		Managers managers = Managers.getInstance();
		Map<String , String > prop = new HashMap<>();
		prop = managers.getProperties();
	
		Double commission = Double.parseDouble(prop.get("commission_rate"));
		Calendar acitityDate = Calendar.getInstance();
	    Date c = new Date(acitityDate.getTimeInMillis());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
        typeList = new JList ();
        typeList.setBounds(87, 116, 112, 72);
        typeList1 = new JList();
        typeList1.setBounds(312, 116, 181, 90);
		
		DefaultListModel typeModel = new DefaultListModel();
		//typeModel.addElement( new Type(0, "RERULAR"));
		typeModel.addElement(ClientType.REGULAR);
		//typeModel.addElement(new Type(1,"GOLD"));
		typeModel.addElement(ClientType.GOLD);
		//typeModel.addElement(new Type(2, "PLATINUM"));
		typeModel.addElement(ClientType.PLATINUM);
		contentPane.setLayout(null);
		typeList.setModel(typeModel);
		
		typeList.setPreferredSize(new Dimension(110, 60));
		typeList.setBorder(BorderFactory.createEtchedBorder());
		//typeList.setSelectedIndex(1);
		contentPane.add(typeList);
		contentPane.add(typeList);

		DefaultListModel typeModel1 = new DefaultListModel();
		
		typeModel1.addElement( new Type(0, prop.get("regular_credit_limit")));
		typeModel1.addElement(new Type(1,prop.get("gold_credit_limit")));
		typeModel1.addElement(new Type(2,prop.get("platinum_credit_limit")));
		typeList1.setModel(typeModel1);
		
		typeList1.setPreferredSize(new Dimension(110, 60));
		typeList1.setBorder(BorderFactory.createEtchedBorder());
		typeList1.setSelectedIndex(1);
		contentPane.add(typeList1);
		contentPane.add(typeList1);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(87, 54, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(87, 85, 86, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(14, 88, 70, 14);
		contentPane.add(lblPassword);
		
		lblType = new JLabel("Type");
		lblType.setBounds(14, 117, 70, 14);
		contentPane.add(lblType);
		
		lblName = new JLabel("Name");
		lblName.setBounds(14, 57, 59, 14);
		contentPane.add(lblName);
		
		lblAddClent = new JLabel("Add Client");
		lblAddClent.setBounds(14, 0, 70, 14);
		contentPane.add(lblAddClent);
		
		JTextField textFieldAddress = new JTextField();
		textFieldAddress.setBounds(87, 202, 86, 20);
		contentPane.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(14, 205, 70, 14);
		contentPane.add(lblAddress);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(87, 233, 86, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(14, 236, 70, 14);
		contentPane.add(lblEmail);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(87, 264, 86, 20);
		contentPane.add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(14, 267, 70, 14);
		contentPane.add(lblPhone);
		
		textFieldComment = new JTextField();
		textFieldComment.setBounds(87, 295, 86, 20);
		textFieldComment.setText("");
		contentPane.add(textFieldComment);
		textFieldComment.setColumns(10);
		
		lblComment = new JLabel("Comment");
		lblComment.setBounds(14, 301, 70, 14);
		contentPane.add(lblComment);
		
		btnOk = new JButton("Add");
		btnOk.setBounds(292, 236, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
		        	   long client_id1 = PKGenerator.getNextPk("Clients", "client_id");
		        	   
		                   String queryString = "INSERT INTO Clients (client_id , client_name , password , "
		                   		+ "client_type , address , email , phone , comment ) "
		                   		+ "VALUES(?,?,?,?,?,?,?,?)";
		                   connection = getConnection();
		                   ptmt = connection.prepareStatement(queryString);		                   
		                   ptmt.setLong(1, client_id1);
		                   ptmt.setString(2, textFieldName.getText());
		                   ptmt.setString(3, textFieldPassword.getText());
		                   ptmt.setString(4, typeList.getSelectedValue().toString());
		                   ptmt.setString(5, textFieldAddress.getText());
		                   ptmt.setString(6, textFieldEmail.getText());
		                   ptmt.setString(7, textFieldPhone.getText());
		                   ptmt.setString(8, textFieldComment.getText());
		                   
		                   ptmt.executeUpdate();
		                   
		                   long account_id = PKGenerator.getNextPk("Accounts", "account_id");
			        	  // long client_id = ((PKGenerator.getNextPk("Clients", "client_id"))-1);
			        	   connection = getConnection();
	
			        	   Double balance =  Double.parseDouble(textFieldBalance.getText());
			        	   Double a = balance - commission;
			        	   
			                   String queryString1 = "INSERT INTO Accounts (account_id , client_id , balance , "
			                      		+ " credit_limit , comment ) "
			                       		+ "VALUES(?,?,?,?,?)";
			                   ptmt = connection.prepareStatement(queryString1);		                   
			                   ptmt.setLong(1, account_id);
			                   ptmt.setLong(2, client_id1);
			                   ptmt.setDouble(3,  a);
			                   ptmt.setString(4, typeList1.getSelectedValue().toString());
			                   ptmt.setString(5, textFieldCommentA.getText());
			                   
			                   ptmt.executeUpdate();
			                   
			                   long id = PKGenerator.getNextPk("Activity", "id");
					     	   String queryString11 = "INSERT INTO Activity (id , client_id , amount , "
					             		+ "activity_date , commission , description ) "
					            		+ "VALUES(?,?,?,?,?,?)";
					            connection = getConnection();
					            ptmt = connection.prepareStatement(queryString11);
					            ptmt.setLong(1, id);
					            ptmt.setLong(2, client_id1);
					            ptmt.setDouble(3,  Double.parseDouble(textFieldBalance.getText()));
					            ptmt.setDate(4, c);
					            ptmt.setDouble(5, commission);
					            ptmt.setString(6, "First Deposit");   
		                   
		                   if ( Double.parseDouble(textFieldBalance.getText()) <= 0 || textFieldBalance.getText().equals("")
		                		   || textFieldBalance.getText().equals(null)
		                		   || textFieldCommentA.getText().equals(null) || textFieldCommentA.getText().equals("")
		                		   || typeList1.toString().equals(null) || typeList1.toString().equals("")
		                		   || textFieldAddress.getText().equals(null) || textFieldAddress.getText().equals("") || textFieldName.getText().equals(null)
		                		   || textFieldName.getText().equals("") || textFieldPassword.getText().equals(null) || textFieldPassword.getText().equals("")
		                		   || textFieldEmail.getText().equals(null) || textFieldEmail.getText().equals("") || textFieldPhone.getText().equals(null)
		                		   || textFieldPhone.getText().equals("") || textFieldComment.getText().equals(null) || textFieldComment.getText().equals("")
		                		   || typeList.toString().equals(null) || typeList.toString().equals("")){
		                	   
		                	//throw new UncheckedRuntimeException("One of column is empty!");
		                	//JOptionPane.showMessageDialog(null, "One of column is empty!");
		                	
		                	String queryString111 = "DELETE FROM Clients "
	                          		+ "WHERE client_id =" +client_id1;
			                   connection = getConnection();
			                   ptmt = connection.prepareStatement(queryString111);		                
			                   ptmt.executeUpdate();
		                	
			                   String queryString1111 = "DELETE FROM Accounts "
			                 		  +"WHERE client_id =" +client_id1;
			                   connection = getConnection();
			                   ptmt = connection.prepareStatement(queryString1111);
			                   ptmt.executeUpdate();   
			                   
		                
		                   }else{
		                	   
		                   ptmt.executeUpdate();
		                 
		                   }
		           } catch (Exception e) {
		        	   e.printStackTrace();
		           } finally {
			              try {
		                      if (ptmt != null)
		                              ptmt.close();
		                      if (connection != null)
		                              connection.close();
		              } catch (SQLException e) {
		            	e.printStackTrace();
		              } 

		      }
		              
				

				
				dispose();
				AdminSwing admin = new AdminSwing();
				admin.setVisible(true);
		
				}
		});
		contentPane.add(btnOk);
		
		lblAccountBean = new JLabel("Account Bean");
		lblAccountBean.setBounds(292, 29, 86, 14);
		contentPane.add(lblAccountBean);
		
		lblClientBean = new JLabel("Client Bean");
		lblClientBean.setBounds(87, 29, 79, 14);
		contentPane.add(lblClientBean);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setBounds(312, 54, 86, 20);
		contentPane.add(textFieldBalance);
		textFieldBalance.setColumns(10);
		
		textFieldCommentA = new JTextField();
		textFieldCommentA.setBounds(312, 85, 86, 20);
		contentPane.add(textFieldCommentA);
		textFieldCommentA.setColumns(10);
		
		lblBalance = new JLabel("First Deposit");
		lblBalance.setBounds(221, 57, 81, 14);
		contentPane.add(lblBalance);
		
		lblComment_1 = new JLabel("Comment");
		lblComment_1.setBounds(221, 88, 70, 14);
		contentPane.add(lblComment_1);
		
		lblCreditLimit = new JLabel("Credit Limit");
		lblCreditLimit.setBounds(221, 119, 74, 14);
		contentPane.add(lblCreditLimit);
		
		toolBar = new JToolBar();
		toolBar.setBounds(14, 343, 605, 51);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(toolBar);
		
		btnCSP = new JButton("Back To Main Frame");
		btnCSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				AdminSwing admin = new AdminSwing();
				admin.setVisible(true);

			}
		});
		toolBar.add(btnCSP);

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
