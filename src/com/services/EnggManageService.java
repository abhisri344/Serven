package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dbutils.CrudOperation;
import com.toedter.calendar.JDateChooser;

import java.awt.Color;

public class EnggManageService extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtmeterreadingupd;
	private JTextField txtrequestidupd;
	private JTextField txtcustomeridupd;
	private JTextField txttypeidupd;
	private JTextField txtvehiclenumupd;
	private JTextField txtserviceidupd;
	private JTextField txtservicestatusupd;
	private JButton btnUpdate;
	private PreparedStatement ps;
	private ResultSet rs;
	private Connection conn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnggManageService frame = new EnggManageService();
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
	public EnggManageService() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		conn=CrudOperation.createConnection();
	createGui();
	}
	void createGui()
	{
		setTitle("Manage Allotted Services");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 455);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl1NewLabel = new JLabel("Request ID");
		lbl1NewLabel.setForeground(Color.WHITE);
		lbl1NewLabel.setBounds(24, 21, 127, 15);
		contentPane.add(lbl1NewLabel);
		
		JLabel lbl1CustomerId = new JLabel("Customer ID");
		lbl1CustomerId.setForeground(Color.WHITE);
		lbl1CustomerId.setBounds(24, 48, 106, 15);
		contentPane.add(lbl1CustomerId);
		
		JLabel lbl1VehicleNumber = new JLabel("Vehicle Number");
		lbl1VehicleNumber.setForeground(Color.WHITE);
		lbl1VehicleNumber.setBounds(24, 75, 141, 15);
		contentPane.add(lbl1VehicleNumber);
		
		JLabel lbl1ServiceIds = new JLabel("Service IDs");
		lbl1ServiceIds.setForeground(Color.WHITE);
		lbl1ServiceIds.setBounds(24, 129, 127, 15);
		contentPane.add(lbl1ServiceIds);
		
		JLabel lbl1MeterReading = new JLabel("Meter Reading");
		lbl1MeterReading.setForeground(Color.WHITE);
		lbl1MeterReading.setBounds(24, 156, 127, 15);
		contentPane.add(lbl1MeterReading);
		
		JLabel lbl1ServiceStatus = new JLabel("Service Status *");
		lbl1ServiceStatus.setForeground(Color.WHITE);
		lbl1ServiceStatus.setBounds(24, 291, 127, 15);
		contentPane.add(lbl1ServiceStatus);
		
	    btnUpdate = new JButton("Update");
	    btnUpdate.setEnabled(false);
		btnUpdate.setBounds(552, 173, 114, 25);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		
		txtmeterreadingupd = new JTextField();
		txtmeterreadingupd.setDisabledTextColor(Color.BLACK);
		txtmeterreadingupd.setForeground(Color.BLACK);
		txtmeterreadingupd.setEnabled(false);
		txtmeterreadingupd.setEditable(false);
		txtmeterreadingupd.setBounds(309, 154, 124, 19);
		contentPane.add(txtmeterreadingupd);
		txtmeterreadingupd.setColumns(10);
		
		txtrequestidupd = new JTextField();
		txtrequestidupd.setBounds(309, 19, 124, 19);
		contentPane.add(txtrequestidupd);
		txtrequestidupd.setColumns(10);
		
		txtcustomeridupd = new JTextField();
		txtcustomeridupd.setDisabledTextColor(Color.BLACK);
		txtcustomeridupd.setForeground(Color.BLACK);
		txtcustomeridupd.setEnabled(false);
		txtcustomeridupd.setEditable(false);
		txtcustomeridupd.setBounds(309, 46, 124, 19);
		contentPane.add(txtcustomeridupd);
		txtcustomeridupd.setColumns(10);
		
		txttypeidupd = new JTextField();
		txttypeidupd.setDisabledTextColor(Color.BLACK);
		txttypeidupd.setForeground(Color.BLACK);
		txttypeidupd.setEnabled(false);
		txttypeidupd.setEditable(false);
		txttypeidupd.setBounds(309, 100, 124, 19);
		contentPane.add(txttypeidupd);
		txttypeidupd.setColumns(10);
		
		txtvehiclenumupd = new JTextField();
		txtvehiclenumupd.setDisabledTextColor(Color.BLACK);
		txtvehiclenumupd.setForeground(Color.BLACK);
		txtvehiclenumupd.setEnabled(false);
		txtvehiclenumupd.setEditable(false);
		txtvehiclenumupd.setBounds(309, 73, 124, 19);
		contentPane.add(txtvehiclenumupd);
		txtvehiclenumupd.setColumns(10);
		
		txtserviceidupd = new JTextField();
		txtserviceidupd.setDisabledTextColor(Color.BLACK);
		txtserviceidupd.setForeground(Color.BLACK);
		txtserviceidupd.setEnabled(false);
		txtserviceidupd.setEditable(false);
		txtserviceidupd.setBounds(309, 127, 124, 19);
		contentPane.add(txtserviceidupd);
		txtserviceidupd.setColumns(10);
		
		JLabel lbl1TypeId = new JLabel("Type ID");
		lbl1TypeId.setForeground(Color.WHITE);
		lbl1TypeId.setBounds(24, 102, 66, 15);
		contentPane.add(lbl1TypeId);
		
		txtservicestatusupd = new JTextField();
		txtservicestatusupd.setBounds(309, 289, 124, 19);
		contentPane.add(txtservicestatusupd);
		txtservicestatusupd.setColumns(10);
		
		JButton btnGo = new JButton("GO");
		btnGo.setBounds(458, 19, 83, 17);
		contentPane.add(btnGo);
		
		JLabel label_3 = new JLabel("A.S.S.U.R'E.");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(label_3.getFont().deriveFont(label_3.getFont().getStyle() | Font.BOLD, label_3.getFont().getSize() + 2f));
		label_3.setBounds(757, 368, 83, 27);
		contentPane.add(label_3);
		
		JLabel lblNoteOnly = new JLabel("NOTE : Only Fields Marked With * can be Changed");
		lblNoteOnly.setForeground(Color.WHITE);
		lblNoteOnly.setBounds(49, 381, 317, 14);
		contentPane.add(lblNoteOnly);
		btnGo.addActionListener(this);
		
		
		
	}
	
	public void Update()
    {
    	 //String requeststatus = txtrequeststatusupd.getText();
    	 
    //	 String deliverystatus = txtdeliverystatusupd.getText();
   				    
    	 String id1=txtrequestidupd.getText();
    	 String servicestatus= txtservicestatusupd.getText();
     
			String strupdate="Update ServiceRequest set ServiceStatus=? where RequestId=?";
			
			
			try {
				ps=conn.prepareStatement(strupdate);
				ps.setString(1, servicestatus);
				ps.setString(2, id1);
				int row;
				
				
				row = ps.executeUpdate();
			 	if(row>0)
			
			
			{
				
				JOptionPane.showMessageDialog(this,"Row Updated","Updating Data",JOptionPane.PLAIN_MESSAGE);
				
				txtcustomeridupd.setText("");
				txtrequestidupd.setText("");
				txtserviceidupd.setText("");
				txtservicestatusupd.setText("");
				
				txttypeidupd.setText("");
				txtvehiclenumupd.setText("");
				txtmeterreadingupd.setText("");
			
				btnUpdate.setEnabled(false);
				
				
				
				
			}				

	
}
			
	catch (SQLException se) {
		
		System.out.println(se);
	}

}
	
	
	
	 public void Search()
	    {
	    	String id=txtrequestidupd.getText();
	    	if(id.isEmpty())
			{
				
				JOptionPane.showMessageDialog(this, "ID Needed","Data Required",JOptionPane.WARNING_MESSAGE );
			}
			else {
				String strsearch="select * from  ServiceRequest where RequestId=?";//JTABLE
				try {
					
					
					ps=conn.prepareStatement(strsearch);
					
					ps.setString(1,id);
					rs=ps.executeQuery();
					if(rs.next()==true)
					{
						btnUpdate.setEnabled(true);
							
						String requestid=rs.getString("RequestId");
						String customerid=rs.getString("CustomerId");
						String vehiclenum=rs.getString("VehicleNumber");
						String typeid=rs.getString("TypeId");
						String serviceid=rs.getString("ServiceIds");
						String meterreading=rs.getString("MeterReading");
						
						
						
						String servicestatus=rs.getString("ServiceStatus");
			
							txtrequestidupd.setText(requestid);
							txtcustomeridupd.setText(customerid);
							txtvehiclenumupd.setText(vehiclenum);
							txttypeidupd.setText(typeid);
							txtserviceidupd.setText(serviceid);
							txtmeterreadingupd.setText(meterreading);
							
							txtservicestatusupd.setText(servicestatus);
							
					}	
					else {
						
			JOptionPane.showMessageDialog(this, "NO SUCH ID","Searching",JOptionPane.QUESTION_MESSAGE);
						
					}
					
					
					
				}
				catch(SQLException se)
				{
					System.out.println(se);
				}
		
	    	
			}
	    	
	    	
	    }
	    
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cap=e.getActionCommand();
		if(cap.equals("GO"))
		{
			Search();
		}
		else if(cap.equals("Update"))
		{
			Update();
		}
	}

}
