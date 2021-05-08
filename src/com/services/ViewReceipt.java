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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.dbutils.CrudOperation;
//import com.mysql.fabric.xmlrpc.base.Value;
import com.toedter.calendar.JDateChooser;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.ImageIcon;

public class ViewReceipt extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtvehiclenum;
	private JTextField txtpaidamount;
	
	private Connection conn;
	private PreparedStatement ps;
	private PreparedStatement psemployee;
    private ResultSet rs,rse;
    
    
    private JLabel lblEmployeeId;
    private JLabel lblRs;
    private JLabel lblChange;
    private JLabel txtcustomerid;
    private JLabel txtrequestid;
    private JLabel txttypeid;
    private JLabel txtserviceid;
    private JLabel txtmeterreading;
    private JLabel txtindate;
    private JLabel txtduedate;
    private JLabel txttotalamount;
    private JLabel txtcustomername;
    private JLabel txtemail;
    private JLabel txtreceptionistid;
    private JLabel txtempid;
    private JLabel txtdueamount;
    private JLabel txtphone;
    private JButton btncalc;
    private JButton btnRefresh;
    private JLabel lblDate;
    private JLabel txttodaydate;
    private JLabel lblAssure;
    private JLabel lblNewLabel;
    private JTextField textField;
    
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewReceipt frame = new ViewReceipt();
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
	public ViewReceipt() 
	{

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		setTitle("View Receipt");
	    creategui();	
	    conn=CrudOperation.createConnection();
	
	}
	
	   public void creategui()
	   {
		   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 915, 566);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			setLocationRelativeTo(null);
			contentPane.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBounds(12, 79, 887, 451);
			contentPane.add(panel);
			
			JLabel label = new JLabel("Request ID");
			label.setBounds(24, 75, 127, 15);
			panel.add(label);
			
			JLabel label_1 = new JLabel("Customer ID");
			label_1.setBounds(24, 48, 106, 15);
			panel.add(label_1);
			
			JLabel label_2 = new JLabel("Vehicle Number");
			label_2.setBounds(24, 21, 141, 15);
			panel.add(label_2);
			
			JLabel label_3 = new JLabel("Service IDs");
			label_3.setBounds(24, 129, 127, 15);
			panel.add(label_3);
			
			JLabel label_4 = new JLabel("Meter Reading");
			label_4.setBounds(24, 156, 127, 15);
			panel.add(label_4);
			
			JLabel label_5 = new JLabel("InDate");
			label_5.setBounds(24, 183, 66, 15);
			panel.add(label_5);
			
			JLabel label_6 = new JLabel("Due date");
			label_6.setBounds(24, 210, 66, 15);
			panel.add(label_6);
			
			JLabel label_7 = new JLabel("Total amount");
			label_7.setBounds(24, 237, 106, 15);
			panel.add(label_7);
			
			JLabel lblCustomerName = new JLabel("Customer Name");
			lblCustomerName.setBounds(24, 264, 127, 15);
			panel.add(lblCustomerName);
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setBounds(24, 291, 127, 15);
			panel.add(lblEmail);
			
			JLabel lblPhoneNumber = new JLabel("Phone Number");
			lblPhoneNumber.setBounds(24, 318, 127, 15);
			panel.add(lblPhoneNumber);
			
			JLabel label_11 = new JLabel("Receptionist ID");
			label_11.setBounds(24, 345, 154, 15);
			panel.add(label_11);
			
			JLabel label_12 = new JLabel("Type ID");
			label_12.setBounds(24, 102, 66, 15);
			panel.add(label_12);
			
			txtvehiclenum = new JTextField();
			txtvehiclenum.setColumns(10);
			txtvehiclenum.setBounds(309, 19, 210, 19);
			panel.add(txtvehiclenum);
			
			JButton btngo = new JButton("GO");
			btngo.setBounds(631, 20, 83, 17);
			panel.add(btngo);
			btngo.addActionListener(this);
			
			lblEmployeeId = new JLabel("Employee Id");
			lblEmployeeId.setBounds(24, 372, 134, 15);
			panel.add(lblEmployeeId);
			
			JTextPane txtpnThankYouValuable = new JTextPane();
			txtpnThankYouValuable.setText("\r\nThank you valuable customer !  For giving\r\n\r\nus a chance to serve you and we are looking\r\n\r\n forward serving you again soon.please \r\n\r\ngive us your valuable feedback.. \r\n\r\n      Team     A.S.S.U.R'E");
			txtpnThankYouValuable.setBounds(572, 75, 290, 177);
			panel.add(txtpnThankYouValuable);
			
			lblRs = new JLabel("Rs/-");
			lblRs.setBounds(185, 237, 66, 15);
			panel.add(lblRs);
			
			JLabel lblPaid = new JLabel("Paid :");
			lblPaid.setBounds(618, 264, 66, 15);
			panel.add(lblPaid);
			
			txtpaidamount = new JTextField();
			txtpaidamount.setBounds(746, 262, 124, 19);
			panel.add(txtpaidamount);
			txtpaidamount.setColumns(10);
			
		    lblChange = new JLabel("Due :");
			lblChange.setBounds(618, 293, 66, 15);
			panel.add(lblChange);
			
			btncalc = new JButton("Calculate");
			btncalc.setBounds(668, 318, 114, 25);
			panel.add(btncalc);
			btncalc.addActionListener(this);
			btncalc.setVisible(false);
			
			JLabel lblRs_1 = new JLabel("Rs/-");
			lblRs_1.setBounds(668, 264, 66, 15);
			panel.add(lblRs_1);
			
			JLabel lblRs_2 = new JLabel("Rs/-");
			lblRs_2.setBounds(668, 291, 66, 15);
			panel.add(lblRs_2);
			
			txtcustomerid = new JLabel("");
			txtcustomerid.setBounds(309, 48, 210, 15);
			panel.add(txtcustomerid);
			
			txtrequestid = new JLabel("");
			txtrequestid.setBounds(309, 75, 210, 15);
			panel.add(txtrequestid);
			
			txttypeid = new JLabel("");
			txttypeid.setBounds(309, 102, 210, 15);
			panel.add(txttypeid);
			
			txtserviceid = new JLabel("");
			txtserviceid.setBounds(309, 129, 210, 15);
			panel.add(txtserviceid);
			
			txtmeterreading = new JLabel("");
			txtmeterreading.setBounds(309, 156, 210, 15);
			panel.add(txtmeterreading);
			
			txtindate = new JLabel("");
			txtindate.setBounds(309, 183, 210, 15);
			panel.add(txtindate);
			
			txtduedate = new JLabel("");
			txtduedate.setBounds(309, 210, 210, 15);
			panel.add(txtduedate);
			
			txttotalamount = new JLabel("");
			txttotalamount.setBounds(309, 237, 210, 15);
			panel.add(txttotalamount);
			
			txtcustomername = new JLabel("");
			txtcustomername.setBounds(309, 264, 210, 15);
			panel.add(txtcustomername);
			
			txtemail = new JLabel("");
			txtemail.setBounds(309, 291, 210, 15);
			panel.add(txtemail);
			
			txtreceptionistid = new JLabel("");
			txtreceptionistid.setBounds(309, 345, 210, 15);
			panel.add(txtreceptionistid);
			
			txtempid = new JLabel("");
			txtempid.setBounds(309, 372, 210, 15);
			panel.add(txtempid);
			
			txtdueamount = new JLabel("");
			txtdueamount.setBounds(746, 291, 66, 15);
			panel.add(txtdueamount);
			
			txtphone = new JLabel("");
			txtphone.setBounds(309, 318, 210, 15);
			panel.add(txtphone);
			
			btnRefresh = new JButton("Refresh");
			btnRefresh.setBounds(756, 18, 106, 20);
			panel.add(btnRefresh);
			
			lblDate = new JLabel("Date");
			lblDate.setBounds(572, 372, 66, 15);
			panel.add(lblDate);
			
			txttodaydate = new JLabel("");
			txttodaydate.setBounds(680, 372, 182, 15);
			panel.add(txttodaydate);
			
			textField = new JTextField();
			textField.setBounds(708, 398, 154, 42);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblReceptionistSignature = new JLabel(" Receptionist Signature");
			lblReceptionistSignature.setBounds(557, 412, 141, 14);
			panel.add(lblReceptionistSignature);
			
			lblAssure = new JLabel("A.S.S.U.R'E.");
			lblAssure.setFont(lblAssure.getFont().deriveFont(lblAssure.getFont().getStyle() | Font.BOLD, lblAssure.getFont().getSize() + 7f));
			lblAssure.setBounds(420, 51, 125, 22);
			contentPane.add(lblAssure);
			
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(ViewReceipt.class.getResource("/com/images/MainIcon.png")));
			lblNewLabel.setBounds(444, 11, 66, 41);
			contentPane.add(lblNewLabel);
		    btnRefresh.addActionListener(this);
		   
	   }
	   
	   
	   
	   public void Search()
	    {    String customerid = null;
	    	String vehiclenum=txtvehiclenum.getText();
	    	if(vehiclenum.isEmpty())
			{
				
				JOptionPane.showMessageDialog(this, "Vehicle Number needed","Data Required",JOptionPane.WARNING_MESSAGE );
			}
			else {
				String strsearch="select * from  ServiceRequest where VehicleNumber=?";//JTABLE
				try {
					
					
					ps=conn.prepareStatement(strsearch);
					
					ps.setString(1,vehiclenum);
					rs=ps.executeQuery();
					if(rs.next()==true)
					{
							
					    btncalc.setVisible(true);
						
						String requestid=rs.getString("RequestId");
						 customerid=rs.getString("CustomerId");
						String typeid=rs.getString("TypeId");
						String serviceid=rs.getString("ServiceIds");
						String meterreading=rs.getString("MeterReading");
						Date indate=rs.getDate("InDate");
						Date duedate=rs.getDate("DueDate");
						Float totalamount=rs.getFloat("TotalAmount");
						String receptionistid=rs.getString("ReceptionistId");
			
							txtrequestid.setText(requestid);
							txtcustomerid.setText(customerid);
							txtvehiclenum.setText(vehiclenum);
							txttypeid.setText(typeid);
							txtserviceid.setText(serviceid);
							txtmeterreading.setText(meterreading);
							txtindate.setText(String.valueOf(indate));
							txtduedate.setText(String.valueOf(duedate));
							txttotalamount.setText(String.valueOf(totalamount));
							txtreceptionistid.setText(receptionistid);
							
					}	
					else {
						
			JOptionPane.showMessageDialog(this, "NO SUCH VEHICLE","Searching",JOptionPane.QUESTION_MESSAGE);
					 
			
					}
					

					
					
					
					strsearch="select * from  AllotService where VehicleNumber=?";//JTABLE
						
						ps=conn.prepareStatement(strsearch);
						
						ps.setString(1,vehiclenum);
						rs=ps.executeQuery();
						if(rs.next()==true)
						{
								
							String empid=rs.getString("EmployeeId");
		       				txtempid.setText(empid);
								
						}	
						

					
						
						
						
						
                        strsearch="select * from  CustomerDetails where CustomerId=?";//JTABLE
						
						ps=conn.prepareStatement(strsearch);
						
						ps.setString(1,customerid);
						rs=ps.executeQuery();
						if(rs.next()==true)
						{
								
							String name=rs.getString("Name");
		       				txtcustomername.setText(name);
		       				String email=rs.getString("Email");
		       				txtemail.setText(email);
		       				String phone=rs.getString("Phone");
		       				txtphone.setText(phone);
							
		       				
		       				
		       				
		       				
						}	
						
					
					
					
					
					
					
					
				}
				catch(SQLException se)
				{
					System.out.println(se);
				}
		      
				finally
				{
				   try
				{
				   if(rs!=null)
				    rs.close();
				   if(rse!=null)
				    rse.close();
				   if(ps!=null)
				    ps.close();
				   if(psemployee!=null)
				    psemployee.close();






				}
				catch(SQLException se)
				{

				System.out.println(se);

				}




				}
	    	
			}
	    	
	    	
	    }

	   public void calculate()
	   {   
		   String totalamount=txtpaidamount.getText();
		   if(totalamount.isEmpty())
		   {
			JOptionPane.showMessageDialog(this, "No payment has been made yet","Receipt",JOptionPane.WARNING_MESSAGE);   
		   }
		   else
	    	{  
	    		int flag=0,flag1=0;
	    		
	    		
	    		int s=totalamount.length();
	                   
	              for(int i=0;i<s;i++)
	     	      {
	     	    	  if(totalamount.charAt(i)=='1'||totalamount.charAt(i)=='2'||totalamount.charAt(i)=='3'||totalamount.charAt(i)=='4'||totalamount.charAt(i)=='5'||
	     	    			 totalamount.charAt(i)=='6'||totalamount.charAt(i)=='7'||totalamount.charAt(i)=='8'||totalamount.charAt(i)=='9'||totalamount.charAt(i)=='0')
	     	    	  
	     	    	  {

	     	    		  flag=0;
	     	  	  
	     	    	  } 
	     	    	  else if (totalamount.charAt(i)=='.') 
	     	    	  {
	     	    		  flag1++;
				   	}
	     	    	  else
	     	    	  {
	     	    		  flag=2;
	     	    		  break;
	     	    	  }
	     	    	  
	     	      }
	            
	     	
	     	if(flag==2||flag1>1)
	     	{	
	     		JOptionPane.showMessageDialog(this, "Enter Valid Amount","Validating Amount",JOptionPane.ERROR_MESSAGE);
	     	}
	    	
		   else
		   {	   
		   Float paid=Float.parseFloat(totalamount);
	   
	       String amount=txttotalamount.getText();
		   Float amnt=Float.parseFloat(amount);
	   
	       float due=amnt-paid;
	       txtdueamount.setText(String.valueOf(due));
	   
	   
	   
		   }
	    	}
	   
	   }
	   
	   public void refresh()
	   {
		   
		   txtrequestid.setText("");
			txtcustomerid.setText("");
			txtvehiclenum.setText("");
			txttypeid.setText("");
			txtserviceid.setText("");
			txtmeterreading.setText("");
			txtindate.setText("");
			txtduedate.setText("");
			txttotalamount.setText("");
			txtreceptionistid.setText("");
			txtempid.setText("");
		    txtcustomername.setText("");
			txtemail.setText("");
			txtphone.setText("");
			txttotalamount.setText("");
		    txtdueamount.setText("");
		    txtpaidamount.setText("");
		    txttodaydate.setText("");
		    btncalc.setVisible(false);
		   
	   }
	   
	   
	   
	   
	   

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String Caption =e.getActionCommand();
		
	 if(Caption.equalsIgnoreCase("GO"))
	 {
		 Search();
	 }
		
	 if(Caption.equalsIgnoreCase("Calculate"))
	 {
		 calculate();
		DateTimeFormatter dtf =DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now=LocalDateTime.now();
		 txttodaydate.setText(dtf.format(now));
		 
	 }
		
	 if(Caption.equalsIgnoreCase("Refresh"))
	 {
		 refresh();
	 }
	 
	}
}
