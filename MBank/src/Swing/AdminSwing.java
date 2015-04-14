package Swing;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;
import mainClasses.ClientAction;
import mainClasses.Managers;
import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;

import com.mysql.jdbc.PreparedStatement;

import dbClasses.ConnectionFactory;
import dbClasses.PKGenerator;


public class AdminSwing extends JFrame {

	private JPanel contentPane;
	private JButton btnCSP;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSwing frame = new AdminSwing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con = null;
	
	/**
	 * Create the frame.
	 */
	public AdminSwing() {
		
		super("MBank");
		
		try {
			con =  ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBounds(10, 11, 195, 417);
		contentPane.add(toolBar);
		
		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			dispose();
			AddClient client = null;
			try {
				client = new AddClient();
			} catch (CheckedExceptions | UncheckedRuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			client.setVisible(true);
				
			}
		});
		toolBar.add(btnAddClient);
		
		btnCSP = new JButton("Change System Propeties");
		btnCSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChangeSystemProperties csp = new ChangeSystemProperties();
				csp.setVisible(true);
			}
		});
		toolBar.add(btnCSP);
		
		JButton btnViewClient = new JButton("View Client Details");
		btnViewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query = "SELECT * FROM Clients ";
					PreparedStatement pst = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		toolBar.add(btnViewClient);
		
		JButton btnVSP = new JButton("View System Properties");
		toolBar.add(btnVSP);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(219, 11, 463, 417);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		btnVSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                try {
					
					String query = "SELECT * FROM Properties ";
					PreparedStatement pst = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
