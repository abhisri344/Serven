package com.services;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dbutils.CrudOperation;

import java.awt.Color;
import java.awt.Font;

public class OthersLogin extends JFrame implements ActionListener, WindowListener{

	private JPanel contentPane;
	private JTextField txtID;
	private JPasswordField txtPass;
	private Connection con;
	private PreparedStatement ps, psuser;
	private ResultSet rs, rsuser;
	public static String userId, userName=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OthersLogin frame = new OthersLogin();
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
	public OthersLogin() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=CrudOperation.createConnection();
		createGui();
	}
	void createGui()
	{
		setTitle("Employee Login Page\r\n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 468);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnLogin = new JMenu("Login");
		menuBar.add(mnLogin);
		
		JMenuItem miAdmin = new JMenuItem("Admin");
		miAdmin.addActionListener(this);
		mnLogin.add(miAdmin);
		
		JMenuItem miReceptionist = new JMenuItem("Receptionist");
		miReceptionist.addActionListener(this);
		mnLogin.add(miReceptionist);
		
		JMenuItem miServEng = new JMenuItem("Service Engineer");
		miServEng.addActionListener(this);
		mnLogin.add(miServEng);
		
		JMenu mnMore = new JMenu("More");
		menuBar.add(mnMore);
		
		JMenuItem miAbout = new JMenuItem("About System");
		miAbout.addActionListener(this);
		mnMore.add(miAbout);
		
		JMenuItem miContact = new JMenuItem("Contact Developing Team");
		miContact.addActionListener(this);
		mnMore.add(miContact);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JTextArea txtrPleaseProvideLogin = new JTextArea();
		txtrPleaseProvideLogin.setEditable(false);
		txtrPleaseProvideLogin.setForeground(Color.WHITE);
		txtrPleaseProvideLogin.setCaretColor(Color.WHITE);
		txtrPleaseProvideLogin.setOpaque(false);
		txtrPleaseProvideLogin.setText("Please Provide Login ID and Password and Click on Login");
		txtrPleaseProvideLogin.setBounds(107, 64, 464, 22);
		contentPane.add(txtrPleaseProvideLogin);
		
		JLabel lblLoginId = new JLabel("Login ID *          :");
		lblLoginId.setForeground(Color.WHITE);
		lblLoginId.setBounds(112, 147, 99, 14);
		contentPane.add(lblLoginId);
		
		JLabel lblPassword = new JLabel("Password *        :");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(112, 198, 99, 14);
		contentPane.add(lblPassword);
		
		txtID = new JTextField();
		txtID.setBounds(253, 144, 160, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(253, 194, 160, 22);
		contentPane.add(txtPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(230, 255, 89, 23);
		btnLogin.addActionListener(this);
		contentPane.add(btnLogin);
		
		JTextArea txtrNoteFields = new JTextArea();
		txtrNoteFields.setEditable(false);
		txtrNoteFields.setForeground(Color.WHITE);
		txtrNoteFields.setOpaque(false);
		txtrNoteFields.setText("Note : Fields marked with * are Compulsory ");
		txtrNoteFields.setBounds(97, 319, 349, 22);
		contentPane.add(txtrNoteFields);
		
		JLabel label = new JLabel("A.S.S.U.R'E.");
		label.setForeground(Color.WHITE);
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD, label.getFont().getSize() + 2f));
		label.setBounds(488, 369, 83, 27);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String caption=ae.getActionCommand();
		if(caption.equals("Login"))
		{
			String id=txtID.getText();
			char pass[]=txtPass.getPassword();
			if(id.isEmpty()||pass.length==0)
			{
				JOptionPane.showMessageDialog(this,"Please Provide All Details","Details Required",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				String p=String.copyValueOf(pass);
				String strsql="Select * from login where id=?";
				String user="Select * from employee where employeeId=?";
				try {
					psuser=con.prepareStatement(user);
					psuser.setString(1, id);
					rsuser=psuser.executeQuery();
					ps=con.prepareStatement(strsql);
					ps.setString(1, id);
					rs=ps.executeQuery();
					if(rs.next()&&rsuser.next())
					{
						userId=id;
						String username=rsuser.getString("name");
						userName=username;
						String tPass=rs.getString("password");
						String tUser=rs.getString("UserType");
						if(tPass.equals(p))
						{
							if(tUser.equalsIgnoreCase("Receptionist"))
							{
								JOptionPane.showMessageDialog(this,"Welcome "+username,"Welcome",JOptionPane.PLAIN_MESSAGE);
								Receptionist ad=new Receptionist();
								ad.setVisible(true);
								this.dispose();
							}
							else if(tUser.equalsIgnoreCase("Service Engineer"))
							{
								JOptionPane.showMessageDialog(this,"Welcome "+username,"Welcome",JOptionPane.PLAIN_MESSAGE);
								ServiceEngineer ad=new ServiceEngineer();
								ad.setVisible(true);
								this.dispose();
							}
							
						}
						else {
							JOptionPane.showMessageDialog(this,"Wrong Password","Invalid Credentials",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Wrong User ID","Invalid Credentials",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(SQLException se)
				{
					System.out.println(se);
				}
			}
			
		}
		else if(caption.equals("Admin"))
		{
			AdminLogin ad=new AdminLogin();
					ad.setVisible(true);
					this.dispose();
		}
		else if(caption.equals("About System"))
		{
			AboutSystem ad=new AboutSystem();
			ad.setVisible(true);
		}
		else if(caption.equals("Contact Developing Team"))
		{
			Contact ad=new Contact();
			ad.setVisible(true);
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		/*
		 * if((JOptionPane.showConfirmDialog(
		 * this,"Are You Sure You Wish To Close The Window?","Confirm Exit",JOptionPane.
		 * CANCEL_OPTION))==0) { this.dispose();}
		 */
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
