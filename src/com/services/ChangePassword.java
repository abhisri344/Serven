package com.services;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbutils.CrudOperation;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;

public class ChangePassword extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField txtOldPass;
	private JPasswordField txtNewPass;
	private JPasswordField txtNewPass2;
	private Connection con;
	private PreparedStatement ps, psUpdate;
	private ResultSet rs;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=CrudOperation.createConnection();
		createGui();
	}
	void createGui()
	{
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 534, 376);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblEnterOldPassword = new JLabel("Enter Old Password             :");
		lblEnterOldPassword.setForeground(Color.WHITE);
		lblEnterOldPassword.setBounds(92, 78, 157, 14);
		contentPane.add(lblEnterOldPassword);
		
		JLabel lblEnterNewPassword = new JLabel("Enter New Password           :");
		lblEnterNewPassword.setForeground(Color.WHITE);
		lblEnterNewPassword.setBounds(92, 136, 157, 14);
		contentPane.add(lblEnterNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password      :");
		lblConfirmNewPassword.setForeground(Color.WHITE);
		lblConfirmNewPassword.setBounds(92, 192, 157, 14);
		contentPane.add(lblConfirmNewPassword);
		
		JButton btnUpdatePassword = new JButton("Update Password");
		btnUpdatePassword.setBounds(173, 256, 148, 23);
		btnUpdatePassword.addActionListener(this);
		contentPane.add(btnUpdatePassword);
		
		txtOldPass = new JPasswordField();
		txtOldPass.setBounds(259, 75, 117, 20);
		contentPane.add(txtOldPass);
		
		txtNewPass = new JPasswordField();
		txtNewPass.setBounds(259, 133, 117, 20);
		contentPane.add(txtNewPass);
		
		txtNewPass2 = new JPasswordField();
		txtNewPass2.setBounds(259, 189, 117, 20);
		contentPane.add(txtNewPass2);
		
		label = new JLabel("A.S.S.U.R'E.");
		label.setForeground(Color.WHITE);
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD, label.getFont().getSize() + 2f));
		label.setBounds(425, 299, 83, 27);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int option=JOptionPane.showConfirmDialog(this,"Are You Sure You want to Update Password");
		if(option==0)
		{
		String oldPass=String.valueOf(txtOldPass.getPassword());
		String newPass=String.valueOf(txtNewPass.getPassword());
		String newPass2=String.valueOf(txtNewPass2.getPassword());
		if(oldPass.isEmpty()||newPass.isEmpty()||newPass2.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Fill All the Details","Data Required", JOptionPane.WARNING_MESSAGE);
		}
		else if(!newPass.equals(newPass2))
		{
			JOptionPane.showMessageDialog(this,"New Passwords Do Not Match", " Invalid Credentials",JOptionPane.ERROR_MESSAGE);
			txtOldPass.setText("");
			txtNewPass.setText("");
			txtNewPass2.setText("");
		}
		else
		{
			String id=OthersLogin.userId;
			String strsql="select * from login where id = ?";
			try {
				ps=con.prepareStatement(strsql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				
				if(rs.next())
				{
					String tOldPass=rs.getString("password");
					if(oldPass.equals(tOldPass))
					{
						String strUpdate="Update login set password =? where id=?";
						psUpdate=con.prepareStatement(strUpdate);
						psUpdate.setString(1,newPass);
						psUpdate.setString(2,id);
						int r=psUpdate.executeUpdate();
						if(r>0)
						{
							JOptionPane.showMessageDialog(this,"Password Updated Successfully");
							txtOldPass.setText("");
							txtNewPass.setText("");
							txtNewPass2.setText("");
						}
						
						
					}
					else {
						JOptionPane.showMessageDialog(this, "Old Password Incorrect","Invalic Credentials",JOptionPane.ERROR_MESSAGE);
						txtOldPass.setText("");
						txtNewPass.setText("");
						txtNewPass2.setText("");
					}
					
				}
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
		}
		
		
	}
}
