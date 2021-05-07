package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class ServiceEngineer extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceEngineer frame = new ServiceEngineer();
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
	public ServiceEngineer() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		createGui();
	}

	void createGui()
	{
		setTitle("Service Engineer Work Home Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem miAllotVehicle = new JMenuItem("View Alloted Vehicles");
		mnView.add(miAllotVehicle);
		miAllotVehicle.addActionListener(this);
		
		JMenu mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		
		JMenuItem miChangePass = new JMenuItem("Change Password");
		mnAccount.add(miChangePass);
		miChangePass.addActionListener(this);
		
		JMenuItem miLogout = new JMenuItem("Logout");
		mnAccount.add(miLogout);
		miLogout.addActionListener(this);
		
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
		
		JButton btnViewAllotedService = new JButton("View Alloted Vehicles");
		btnViewAllotedService.setBounds(427, 294, 163, 39);
		btnViewAllotedService.addActionListener(this);
		contentPane.add(btnViewAllotedService);
		
		JLabel lblHello = new JLabel("Service Engineer  :  "+OthersLogin.userName);
		lblHello.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHello.setForeground(Color.WHITE);
		lblHello.setBounds(717, 11, 257, 27);
		contentPane.add(lblHello);
		
		JLabel label = new JLabel("A.S.S.U.R'E.");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(898, 614, 76, 14);
		contentPane.add(label);
		
		JButton btnManageService = new JButton("Manage Service");
		btnManageService.setBounds(643, 294, 163, 42);
		contentPane.add(btnManageService);
		btnManageService.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cap=e.getActionCommand();
		if(cap.equals("Change Password"))
		{
			ChangePassword ob=new ChangePassword();
			ob.setVisible(true);
		}
		else if(cap.equals("Logout"))
		{
			WelcomeScreen ob=new WelcomeScreen();
			ob.setVisible(true);
			this.dispose();
		}
		else if(cap.equals("View Alloted Vehicles"))
		{
			ViewEngineerAllot ob=new ViewEngineerAllot();
			ob.setVisible(true);
		}
		else if(cap.equals("About System"))
		{
			AboutSystem ad=new AboutSystem();
			ad.setVisible(true);
		}
		else if(cap.equals("Contact Developing Team"))
		{
			Contact ad=new Contact();
			ad.setVisible(true);
		}
		else if(cap.equals("Manage Service"))
		{
		EnggManageService ad=new EnggManageService();
		ad.setVisible(true);
		}
	}
}
