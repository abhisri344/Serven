package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.ImageIcon;

public class Contact extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact frame = new Contact();
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
	public Contact() {
		setExtendedState(Frame.NORMAL);
		setResizable(false);
		createGui();
	}
	void createGui()
	{
		setTitle("Contact Developing Team");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 870, 663);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAbhishekSrivastava = new JLabel("ABHISHEK SRIVASTAVA");
		lblAbhishekSrivastava.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAbhishekSrivastava.setForeground(Color.WHITE);
		lblAbhishekSrivastava.setBounds(26, 56, 179, 25);
		contentPane.add(lblAbhishekSrivastava);
		
		JLabel lblRishabhGautam = new JLabel("RISHABH GAUTAM");
		lblRishabhGautam.setForeground(Color.WHITE);
		lblRishabhGautam.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRishabhGautam.setBounds(26, 164, 142, 25);
		contentPane.add(lblRishabhGautam);
		
		JLabel lblSanchitShahi = new JLabel("SANCHIT SHAHI");
		lblSanchitShahi.setForeground(Color.WHITE);
		lblSanchitShahi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSanchitShahi.setBounds(26, 274, 129, 25);
		contentPane.add(lblSanchitShahi);
		
		JLabel lblSudhanshuSingh = new JLabel("SUDHANSHU SINGH");
		lblSudhanshuSingh.setForeground(Color.WHITE);
		lblSudhanshuSingh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSudhanshuSingh.setBounds(26, 394, 179, 25);
		contentPane.add(lblSudhanshuSingh);
		
		JLabel lblUtkarshSingh = new JLabel("UTKARSH SINGH");
		lblUtkarshSingh.setForeground(Color.WHITE);
		lblUtkarshSingh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUtkarshSingh.setBounds(26, 509, 129, 25);
		contentPane.add(lblUtkarshSingh);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Contact.class.getResource("/com/images/Abhishek.jpg")));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(244, 11, 105, 105);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("9415213131");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(392, 56, 98, 25);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("8052391161");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(392, 164, 98, 25);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("7007012482");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(392, 274, 98, 25);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("6306440238");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(392, 394, 98, 25);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("8009184841");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(392, 509, 98, 25);
		contentPane.add(label_4);
		
		JLabel lblAbhisrigmailcom = new JLabel("abhisri344@gmail.com");
		lblAbhisrigmailcom.setForeground(Color.WHITE);
		lblAbhisrigmailcom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAbhisrigmailcom.setBounds(566, 56, 166, 25);
		contentPane.add(lblAbhisrigmailcom);
		
		JLabel lblRishurocksgmailcom = new JLabel("rishurocks555@gmail.com");
		lblRishurocksgmailcom.setForeground(Color.WHITE);
		lblRishurocksgmailcom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRishurocksgmailcom.setBounds(566, 164, 191, 25);
		contentPane.add(lblRishurocksgmailcom);
		
		JLabel lblSanchitshahigmailcom = new JLabel("sanchitshahi11091999@gmail.com");
		lblSanchitshahigmailcom.setForeground(Color.WHITE);
		lblSanchitshahigmailcom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSanchitshahigmailcom.setBounds(566, 274, 248, 25);
		contentPane.add(lblSanchitshahigmailcom);
		
		JLabel lblMesudhanshugmailcom = new JLabel("mesudhanshu257@gmail.com");
		lblMesudhanshugmailcom.setForeground(Color.WHITE);
		lblMesudhanshugmailcom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMesudhanshugmailcom.setBounds(566, 394, 208, 25);
		contentPane.add(lblMesudhanshugmailcom);
		
		JLabel lblUtkarshgreatgmailcom = new JLabel("utkarsh23great@gmail.com");
		lblUtkarshgreatgmailcom.setForeground(Color.WHITE);
		lblUtkarshgreatgmailcom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUtkarshgreatgmailcom.setBounds(566, 509, 200, 25);
		contentPane.add(lblUtkarshgreatgmailcom);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(Contact.class.getResource("/com/images/Rishabh.jpg")));
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(244, 127, 105, 105);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Contact.class.getResource("/com/images/Sanchit.jpg")));
		label_6.setForeground(Color.WHITE);
		label_6.setBounds(244, 241, 105, 105);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(Contact.class.getResource("/com/images/Sudhanshu.jpg")));
		label_7.setForeground(Color.WHITE);
		label_7.setBounds(244, 357, 105, 105);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(Contact.class.getResource("/com/images/Utkarsh.jpg")));
		label_8.setForeground(Color.WHITE);
		label_8.setBounds(244, 473, 105, 105);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("A.S.S.U.R'E.");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_9.setBounds(769, 599, 76, 14);
		contentPane.add(label_9);
		setLocationRelativeTo(null);
	}
}
