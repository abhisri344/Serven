package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.ImageIcon;

public class WelcomeScreen extends JFrame implements ActionListener{

	private JPanel contentPane;
	JTextArea txtrWelcomeToThe;
	JLabel label_1,label_2,label_3,label_4,label_5,label_6, lable_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen frame = new WelcomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	public void loadFrame()
	{
		new Thread(
				new Runnable()
				{
					public void run()
					{
						try {
							int x=200;
							int a1=10,a2=112,a3=214,a4=316,a5=418,a6=520,a7=-48;
							while(true)
							{
							txtrWelcomeToThe.setBounds(x--, 100, 474, 27);
							label_1.setBounds(a1++, 11, 102, 66);
							label_2.setBounds(a2++, 11, 102, 66);
							label_3.setBounds(a3++, 11, 102, 66);
							label_4.setBounds(a4++, 11, 102, 66);
							label_5.setBounds(a5++, 11, 102, 66);
							label_6.setBounds(a6++, 11, 102, 66);
							lable_7.setBounds(a7++, 34, 64, 14);
							
							
							Thread.sleep(25);
							if(a1==573)
								a1=-102;
							if(a2==573)
								a2=-102;
							if(a3==573)
								a3=-102;
							if(a4==573)
								a4=-102;
							if(a5==573)
								a5=-102;
							if(a6==573)
								a6=-102;
							if(a7==573)
								a7=-102;
							if(x==-350)
								x=573;
							}
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
				}).start();
	}
	
	
	
	
	
	

	/**
	 * Create the frame.
	 */
	public WelcomeScreen() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		setExtendedState(Frame.NORMAL);
		createGui();
		loadFrame();
		}
	void createGui()
	{

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		setTitle("Central Vehicle Service Management System");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
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
		contentPane.setName("");
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.WHITE, 5, true));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		 txtrWelcomeToThe = new JTextArea();
		txtrWelcomeToThe.setEditable(false);
		txtrWelcomeToThe.setForeground(Color.WHITE);
		txtrWelcomeToThe.setOpaque(false);
		txtrWelcomeToThe.setText("Welcome to the Central Vehicle Service Management System");
		//txtrWelcomeToThe.setBounds(52, 95, 474, 27);
		contentPane.add(txtrWelcomeToThe);
		
		JTextArea txtrPleaseLoginTo = new JTextArea();
		txtrPleaseLoginTo.setEditable(false);
		txtrPleaseLoginTo.setForeground(Color.WHITE);
		txtrPleaseLoginTo.setOpaque(false);
		txtrPleaseLoginTo.setText("Please Login to Continue");
		txtrPleaseLoginTo.setBounds(225, 152, 211, 27);
		contentPane.add(txtrPleaseLoginTo);
		
		JLabel lblLoginAs = new JLabel("Login As");
		lblLoginAs.setForeground(Color.WHITE);
		lblLoginAs.setBounds(95, 223, 64, 21);
		contentPane.add(lblLoginAs);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(186, 222, 89, 23);
		btnAdmin.addActionListener(this);
		contentPane.add(btnAdmin);
		
		JButton btnOthers = new JButton("Others");
		btnOthers.setBounds(325, 222, 89, 23);
		btnOthers.addActionListener(this);
		contentPane.add(btnOthers);
		
		JLabel lblAssure = new JLabel("A.S.S.U.R'E.");
		lblAssure.setFont(lblAssure.getFont().deriveFont(lblAssure.getFont().getStyle() | Font.BOLD, lblAssure.getFont().getSize() + 2f));
		lblAssure.setForeground(Color.WHITE);
		lblAssure.setBounds(501, 411, 83, 27);
		contentPane.add(lblAssure);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/com/images/Welcome1.jpg")));
		label.setBounds(186, 256, 228, 146);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/com/images/Wel1.jpg")));
		label_1.setBounds(10, 11, 102, 66);
		contentPane.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/com/images/Wel2.jpg")));
		label_2.setBounds(112, 11, 102, 66);
		contentPane.add(label_2);
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/com/images/Wel3.jpg")));
		label_3.setBounds(214, 11, 102, 66);
		contentPane.add(label_3);
		
		label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/com/images/Wel4.jpg")));
		label_4.setBounds(316, 11, 102, 66);
		contentPane.add(label_4);
		
		label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/com/images/Wel5.jpg")));
		label_5.setBounds(418, 11, 102, 66);
		contentPane.add(label_5);
		
		label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/com/images/Wel6.jpg")));
		label_6.setBounds(520, 11, 102, 66);
		contentPane.add(label_6);
		
		lable_7 = new JLabel("A.S.S.U.R'E.");
		lable_7.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lable_7.setForeground(Color.WHITE);
		lable_7.setBounds(634, 34, 64, 14);
		contentPane.add(lable_7);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String caption=ae.getActionCommand();
		if(caption.equals("Admin"))
		{
			AdminLogin ad=new AdminLogin();
					ad.setVisible(true);
					this.dispose();
		}
		else if(caption.equals("Others")||caption.equals("Receptionist")||caption.equals("Service Engineer"))
		{
			OthersLogin ad=new OthersLogin();
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
}
