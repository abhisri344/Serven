package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbutils.CrudOperation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Feedback extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtCustomer;
	private JTextArea  txtfeed;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feedback frame = new Feedback();
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
	public Feedback() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=CrudOperation.createConnection();
		setTitle("FEEDBACK");
		createGui();
		
	}
public void createGui()
{
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 675, 455);
	contentPane = new JPanel();
	contentPane.setBackground(Color.DARK_GRAY);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	setLocationRelativeTo(null);
	contentPane.setLayout(null);
	
	JLabel lblCustomerId = new JLabel("Customer ID");
	lblCustomerId.setForeground(Color.WHITE);
	lblCustomerId.setBounds(185, 32, 95, 22);
	contentPane.add(lblCustomerId);
	
	txtCustomer = new JTextField();
	txtCustomer.setBounds(290, 33, 167, 20);
	contentPane.add(txtCustomer);
	txtCustomer.setColumns(10);
	
	JLabel lblFeedback = new JLabel("Feedback");
	lblFeedback.setForeground(Color.WHITE);
	lblFeedback.setBounds(309, 89, 89, 22);
	contentPane.add(lblFeedback);
	
	txtfeed = new JTextArea();
	txtfeed.setBounds(135, 122, 399, 206);
	contentPane.add(txtfeed);
	
	JButton btnSave = new JButton("Save");
	btnSave.setBounds(290, 358, 89, 23);
	contentPane.add(btnSave);
	btnSave.addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String id=txtCustomer.getText();
	String feed=txtfeed.getText();
	if(id.isEmpty()||feed.isEmpty())
	{
		JOptionPane.showMessageDialog(this, "Please Fill All Details","MISSING DETAILS",JOptionPane.INFORMATION_MESSAGE);
	}
	else
	{
	
	try {
		
	String strup="select * from  feedback where CustomerID='"+id+"' ";
	ps=con.prepareStatement(strup);
	rs=ps.executeQuery();
	if(rs.next()==true)
	{
		JOptionPane.showMessageDialog(this,"This ID is already assigned");	
			
	}
	else {
	
	
	String str="Insert into FEEDBACK values (?,?)";
			ps=con.prepareStatement(str);
	
	ps.setString(1, id);
	ps.setString(2, feed);
	int row=ps.executeUpdate();
	if(row>0)
	{
		JOptionPane.showMessageDialog(this, "Feedback Added");
		txtCustomer.setText("");
		txtfeed.setText("");
		
	}
	}
	}
	
	catch(SQLException se)
	{
		System.out.println(se);
	}
}}
}
