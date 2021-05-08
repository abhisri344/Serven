package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class AdminFrame extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		createGui();
	}
	void createGui()
	{
		setTitle("Admin Control Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEmployee = new JMenu("Manage");
		menuBar.add(mnEmployee);
		
		JMenuItem miManageEmp = new JMenuItem("Manage Employees");
		miManageEmp.addActionListener(this);
		mnEmployee.add(miManageEmp);
		
		JMenuItem miManageServ = new JMenuItem("Manage Services");
		miManageServ.addActionListener(this);
		mnEmployee.add(miManageServ);
		
		JMenuItem miManageVehicle = new JMenuItem("Manage Vehicles");
		miManageVehicle.addActionListener(this);
		mnEmployee.add(miManageVehicle);
		
		JMenu mnMore = new JMenu("More");
		menuBar.add(mnMore);
		
		JMenuItem miAbout = new JMenuItem("About System");
		miAbout.addActionListener(this);
		mnMore.add(miAbout);
		
		JMenuItem miContact = new JMenuItem("Contact Developing Team");
		miContact.addActionListener(this);
		mnMore.add(miContact);
		
		JMenu mnViewSerEng = new JMenu("View");
		menuBar.add(mnViewSerEng);
		
		JMenuItem miViewEmp = new JMenuItem("View Employees");
		mnViewSerEng.add(miViewEmp);
		miViewEmp.addActionListener(this);
		
		JMenuItem miViewServ = new JMenuItem("View Services");
		mnViewSerEng.add(miViewServ);
		miViewServ.addActionListener(this);
		
		JMenuItem miViewVehicle = new JMenuItem("View Vehicle Type");
		miViewVehicle.addActionListener(this);
		mnViewSerEng.add(miViewVehicle);
		
		JMenuItem miViewServEng = new JMenuItem("View Service By Engineer");
		miViewServEng.addActionListener(this);
		mnViewSerEng.add(miViewServEng);
		
		JMenuItem miTotalServices = new JMenuItem("View Total Services");
		miTotalServices.addActionListener(this);
		mnViewSerEng.add(miTotalServices);
		
		JMenu mnLogout = new JMenu("Account");
		menuBar.add(mnLogout);
		
		JMenuItem miLogout = new JMenuItem("Logout");
		miLogout.addActionListener(this);
		
		JMenuItem miChangePass = new JMenuItem("Change Password");
		mnLogout.add(miChangePass);
		miChangePass.addActionListener(this);
		
		
		mnLogout.add(miLogout);
		contentPane = new JPanel();
		miViewVehicle.addActionListener(this);
		
		
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton btnEmployee = new JButton("Manage Employees");
		btnEmployee.setBounds(204, 239, 155, 23);
		btnEmployee.addActionListener(this);
		contentPane.add(btnEmployee);
		
		JButton btnService = new JButton("Manage Services");
		btnService.setBounds(432, 239, 155, 23);
		btnService.addActionListener(this);
		contentPane.add(btnService);
		
		JButton btnVehicle = new JButton("Manage Vehicles");
		btnVehicle.setBounds(645, 239, 155, 23);
		btnVehicle.addActionListener(this);
		contentPane.add(btnVehicle);
		
		JTextArea txtrClickOnA = new JTextArea();
		txtrClickOnA.setFont(new Font("Monospaced", Font.PLAIN, 17));
		txtrClickOnA.setEditable(false);
		txtrClickOnA.setForeground(Color.WHITE);
		txtrClickOnA.setOpaque(false);
		txtrClickOnA.setText("Click On a Button to move to the Respective Page");
		txtrClickOnA.setBounds(252, 132, 508, 23);
		contentPane.add(txtrClickOnA);
		
		JTextPane txtpnWelcomeAdmin = new JTextPane();
		txtpnWelcomeAdmin.setFont(txtpnWelcomeAdmin.getFont().deriveFont(txtpnWelcomeAdmin.getFont().getStyle() | Font.BOLD, txtpnWelcomeAdmin.getFont().getSize() + 12f));
		txtpnWelcomeAdmin.setOpaque(false);
		txtpnWelcomeAdmin.setForeground(Color.WHITE);
		txtpnWelcomeAdmin.setEditable(false);
		txtpnWelcomeAdmin.setText("Welcome Admin");
		txtpnWelcomeAdmin.setBounds(400, 11, 248, 35);
		contentPane.add(txtpnWelcomeAdmin);
		
		JLabel label = new JLabel("A.S.S.U.R'E.");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(898, 614, 76, 14);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String caption=ae.getActionCommand();
		
		 if(caption.equals("Manage Employees")) 
		  { 
			  Employee emp=new Employee();
			  emp.setVisible(true); 
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
		 
		  else if(caption.equals("View Employees")) 
		  { 
			  ViewEmployee emp=new ViewEmployee(); 
			  emp.setVisible(true); 
		  } 
		 
		  else if(caption.equals("Logout"))
		  {
			WelcomeScreen emp=new WelcomeScreen();
			emp.setVisible(true);
			this.dispose();
		  }
		  else if(caption.equals("Add Employee"))
			{
				Employee emp=new Employee();
				emp.setVisible(true);
			}
		  else if(caption.equals("Manage Vehicles"))
		  {
			  VehicleType emp=new VehicleType();
			  emp.setVisible(true);
		  }
		  else if(caption.equals("Manage Services"))
		  {
			  Service emp=new Service();
			  emp.setVisible(true);
		  }
		
		 else if(caption.equals("View Vehicle Type"))
		 {
			  ViewVehicle emp=new ViewVehicle();
			  emp.setVisible(true); 
		  } 
		 else if(caption.equals("Change Password"))
		 {
			 ChangePasswordAdmin emp=new ChangePasswordAdmin();
			 emp.setVisible(true);
		 }
		 else if(caption.equals("View Service By Engineer"))
		 {
			 ViewAdminServiced emp=new ViewAdminServiced();
			 emp.setVisible(true);
		 }		 
		 else if(caption.equals("View Services"))
		 {
			 ViewReceptionistService emp=new ViewReceptionistService();
			 emp.setVisible(true);
		 }
		 else if(caption.equals("View Total Services"))
		 {
			ViewAllotServices emp=new ViewAllotServices();
			 emp.setVisible(true);
		 }
			
			
	}
}
