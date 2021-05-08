package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class SplashScreen extends JFrame {

	private JPanel contentPane;
	private JLabel lblGuiDemos ;
	private JLabel lblVehicleManagementSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
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
	public SplashScreen() {
		setTitle("A.S.S.U.R'E.");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		createGui();
		loadFrame();
		
	}
	
	
	
	
	public void loadFrame()
	{
		new Thread(
				new Runnable()
				{
					public void run()
					{
						try {
							int x=0; int y=0;
							
							while(true)
							{
							lblVehicleManagementSystem.setBounds(163, y++, 350, 28);
							Thread.sleep(15);
							if(y==60)
								break;
							}
							while(true)
							{
								lblGuiDemos.setBounds(x++, 20, 267, 210);
								Thread.sleep(12);
								if(x==200)
									break;
							}
							 Thread.sleep(2000);
							 
							WelcomeScreen ws =new WelcomeScreen();
							SplashScreen.this.dispose();
							ws.setVisible(true);
							
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					
					
				}
				
				
				
				
				).start();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 649, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		
		
		lblGuiDemos = new JLabel("A.S.S.U.R'E.");
		lblGuiDemos.setForeground(Color.WHITE);
		lblGuiDemos.setFont(lblGuiDemos.getFont().deriveFont(lblGuiDemos.getFont().getStyle() | Font.BOLD, lblGuiDemos.getFont().getSize() + 30f));
		//lblGuiDemos.setBounds(206, 11, 267, 210);
		contentPane.add(lblGuiDemos);
		
		lblVehicleManagementSystem = new JLabel("Vehicle Service Management System");
		lblVehicleManagementSystem.setFont(lblVehicleManagementSystem.getFont().deriveFont(lblVehicleManagementSystem.getFont().getSize() + 6f));
		lblVehicleManagementSystem.setForeground(Color.WHITE);
		//lblVehicleManagementSystem.setBounds(206, 55, 255, 28);
		contentPane.add(lblVehicleManagementSystem);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(SplashScreen.class.getResource("/com/images/IconImage.png")));
		lblImage.setBounds(10, 11, 613, 427);
		contentPane.add(lblImage);
	}
}
