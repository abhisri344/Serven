package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AboutSystem extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutSystem frame = new AboutSystem();
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
	public AboutSystem() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		createGui();
	}
	void createGui()
	{
		setTitle("About System");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 477);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AboutSystem.class.getResource("/com/images/MainIcon.png")));
		label.setBounds(308, 11, 66, 41);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("A.S.S.U.R'E.");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD, label_1.getFont().getSize() + 7f));
		label_1.setBounds(284, 51, 125, 22);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("A.S.S.U.R'E.");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(label_2.getFont().deriveFont(label_2.getFont().getStyle() | Font.BOLD, label_2.getFont().getSize() + 2f));
		label_2.setBounds(528, 400, 83, 27);
		contentPane.add(label_2);
		
		JTextArea txtrAssureIsA = new JTextArea();
		txtrAssureIsA.setForeground(Color.WHITE);
		txtrAssureIsA.setOpaque(false);
		txtrAssureIsA.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtrAssureIsA.setRows(2);
		txtrAssureIsA.setText(" \tA.S.S.U.R'E. Software is a Vehicle Service Management System \r\nefficiently designed to Manage various Service Operations  and to Track the \r\nCompany's Employees, Vehicles Serviced, Customer Vehicles brought in etc.\r\n\r\nThe Name of the Company Software A.S.S.U.R'E stands for :\r\n\t\t\t\tA : Abhishek\r\n\t\t\t\tS : Sudhanshu\r\n\t\t\t\tS : Sanchit\r\n\t\t\t\tU : Utkarsh\r\n\t\t\t\tR : Rishabh's\r\n\t\t\t\tE : Establishment");
		txtrAssureIsA.setBounds(43, 127, 542, 231);
		contentPane.add(txtrAssureIsA);
		
		JLabel lblProjectCompletionDate = new JLabel("Project Completion Date : 27/07/2019");
		lblProjectCompletionDate.setForeground(Color.WHITE);
		lblProjectCompletionDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProjectCompletionDate.setBounds(43, 369, 274, 30);
		contentPane.add(lblProjectCompletionDate);
	}
}
