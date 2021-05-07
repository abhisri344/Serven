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
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class AdminLogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtID;
	private JPasswordField txtPass;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	static String adminId="Admin_001";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=CrudOperation.createConnection();
		createGui();
	}
	void createGui()
	{
		setTitle("Login Admin\r\n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 549, 423);
		
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
		txtrPleaseProvideLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrPleaseProvideLogin.setEditable(false);
		txtrPleaseProvideLogin.setForeground(Color.WHITE);
		txtrPleaseProvideLogin.setCaretColor(Color.WHITE);
		txtrPleaseProvideLogin.setOpaque(false);
		txtrPleaseProvideLogin.setText("Please Provide Login ID and Password and Click on Login");
		txtrPleaseProvideLogin.setBounds(87, 64, 340, 22);
		contentPane.add(txtrPleaseProvideLogin);
		
		JLabel lblLoginId = new JLabel("Login ID *          :");
		lblLoginId.setForeground(Color.WHITE);
		lblLoginId.setBounds(92, 147, 99, 14);
		contentPane.add(lblLoginId);
		
		JLabel lblPassword = new JLabel("Password *        :");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(92, 198, 99, 14);
		contentPane.add(lblPassword);
		
		txtID = new JTextField();
		txtID.setBounds(201, 144, 160, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(201, 193, 160, 22);
		contentPane.add(txtPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(201, 255, 89, 23);
		btnLogin.addActionListener(this);
		contentPane.add(btnLogin);
		
		JTextArea txtrNoteFields = new JTextArea();
		txtrNoteFields.setEditable(false);
		txtrNoteFields.setForeground(Color.WHITE);
		txtrNoteFields.setOpaque(false);
		txtrNoteFields.setText("Note : Fields marked with * are Compulsory ");
		txtrNoteFields.setBounds(113, 289, 349, 22);
		contentPane.add(txtrNoteFields);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AdminLogin.class.getResource("/com/images/admin.jpg")));
		label.setBounds(371, 147, 146, 92);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("A.S.S.U.R'E.");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD, label_1.getFont().getSize() + 2f));
		label_1.setBounds(440, 324, 83, 27);
		contentPane.add(label_1);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
	
		String caption=ae.getActionCommand();
		
		if(caption.equals("Others")||caption.equals("Receptionist")||caption.equals("Service Engineer"))
		{
			OthersLogin ad=new OthersLogin();
			ad.setVisible(true);
			this.dispose();
		}
		else if(caption.equals("Login"))
		{
			String id=txtID.getText();
			char pass[]=txtPass.getPassword();
			if(id.isEmpty()||pass.length==0)
			JOptionPane.showMessageDialog(this,"Please Provide All Details");
			
			else {
				String strsql="select * from login where id =?";
				try {
				ps=con.prepareStatement(strsql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next())
				{
				String tPass=rs.getString(2);
				String p=String.valueOf(pass);
				if(id.equals("Admin_001")&&p.equals(tPass))
				{
					JOptionPane.showMessageDialog(this,"Hello "+id,"Welcome",JOptionPane.PLAIN_MESSAGE);
					AdminFrame ad=new AdminFrame();
					ad.setVisible(true);
					this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Wrong User ID or Password","Invalid Credentials",JOptionPane.ERROR_MESSAGE);
				}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No Such Id Present");
				}
				}
				
				catch(SQLException se)
				{
					System.out.println(se);
				}
			}
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
}
