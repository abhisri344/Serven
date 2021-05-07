package com.services;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbutils.CrudOperation;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class Service extends JFrame  implements ActionListener, WindowListener{

	private JTextField txtidAdd;
	private JTextField txtnameAdd;
	private JTextField txtcostAdd;
    private Connection con=null;
    private  JTextField txtidupdate,txtnameupdate,txtcostupdate,txtremarkupdate;
	private JPanel contentPane;
	private JTextField txtremark;
	private JLabel txtnamedel, txtcostdel;
	private PreparedStatement psDelete, psAdd;
	private ResultSet rsDel;
	private JTextField txtidDel;
	JButton btnDelete;
	private JTextField txtidsearch;
	private PreparedStatement pssearch;
	private ResultSet rssearch;
	private JLabel lblnamesearch,lblcostsearch,lblremarksearch;
	JButton btnsearch;
	private PreparedStatement psUpdate;
	private ResultSet rsUpdate;
	JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Service frame = new Service();
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
	public Service() {
		setTitle("Manage Services");

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=CrudOperation.createConnection();
		createGui();
	}
public void createGui()
{
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 724, 489);
	contentPane = new JPanel();
	contentPane.setBackground(Color.DARK_GRAY);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	setLocationRelativeTo(null);
	contentPane.setLayout(null);
	
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setBounds(0, 0, 700, 458);
	contentPane.add(tabbedPane);
	
	JPanel addservice = new JPanel();
	tabbedPane.addTab("Add Service", null, addservice, null);
	addservice.setLayout(null);
	
	JLabel lblServicecost = new JLabel("ServiceCost");
	lblServicecost.setBounds(68, 115, 80, 14);
	addservice.add(lblServicecost);
	
	JLabel lblRemarks = new JLabel("Remarks");
	lblRemarks.setBounds(68, 162, 57, 14);
	addservice.add(lblRemarks);
	
	txtidAdd = new JTextField();
	txtidAdd.setBounds(191, 39, 141, 20);
	addservice.add(txtidAdd);
	txtidAdd.setColumns(10);
	
	txtnameAdd = new JTextField();
	txtnameAdd.setBounds(191, 81, 141, 20);
	addservice.add(txtnameAdd);
	txtnameAdd.setColumns(10);
	
	txtcostAdd = new JTextField();
	txtcostAdd.setBounds(219, 112, 96, 20);
	addservice.add(txtcostAdd);
	txtcostAdd.setColumns(10);
	
	JLabel label = new JLabel("/-");
	label.setBounds(325, 115, 29, 14);
	addservice.add(label);
	
	JButton btnSubmit = new JButton("Submit");
	btnSubmit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			
			String caption =e.getActionCommand();
			if(caption.equals("Submit"))
			{
				
				String id=txtidAdd.getText();
				String name=txtnameAdd.getText();
				String totalamount=txtcostAdd.getText();
				String remarks=txtremark.getText();
						if(id.isEmpty()||name.isEmpty()||totalamount.isEmpty()||remarks.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please fill up all details");
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
				     		JOptionPane.showMessageDialog(null, "Enter Valid Amount","Validating Amount",JOptionPane.ERROR_MESSAGE);
				     	}
				    		
				     	
				else
				{
					float cost=Float.parseFloat(totalamount);
					try {
						String strinsert="insert into service_task values (?,?,?,?)";
						psAdd=con.prepareStatement(strinsert);
						psAdd.setString(1, id);
						psAdd.setString(2, name);
						psAdd.setFloat(3, cost);
						psAdd.setString(4, remarks);
						
						int row=psAdd.executeUpdate();
						if(row>0)
							{JOptionPane.showMessageDialog(null,"data added");
						txtidDel.setText("");
						txtnameAdd.setText("");
						txtcostAdd.setText("");
						txtremark.setText("");
						txtidAdd.setText("");
							}
					}
					catch(SQLException se){
						System.out.println(se);
						
					}}}}}}
		);
	btnSubmit.setBounds(440, 333, 102, 23);
	 
    
	JLabel lblServiceid = new JLabel("ServiceId");
	lblServiceid.setBounds(68, 42, 80, 14);
	addservice.add(lblServiceid);
	 
	 JLabel lblServicename = new JLabel("ServiceName");
	 lblServicename.setBounds(68, 82, 80, 14);
	 addservice.add(lblServicename);
	 addservice.add(btnSubmit);
	 
	 txtremark = new JTextField();
	 txtremark.setBounds(191, 162, 340, 95);
	 addservice.add(txtremark);
	 txtremark.setColumns(10);
	 
	 JLabel lblRs = new JLabel(" Rs.");
	 lblRs.setBounds(170, 115, 39, 14);
	 addservice.add(lblRs);
	 
	 JLabel label_5 = new JLabel("A.S.S.U.R'E.");
	 label_5.setForeground(Color.BLACK);
	 label_5.setFont(label_5.getFont().deriveFont(label_5.getFont().getStyle() | Font.BOLD, label_5.getFont().getSize() + 2f));
	 label_5.setBounds(602, 392, 83, 27);
	 addservice.add(label_5);
	 
	 JPanel deleteservice = new JPanel();
	 tabbedPane.addTab("Delete Service", null, deleteservice, null);
	 deleteservice.setLayout(null);

	 JLabel lblServiceid1 = new JLabel("ServiceId");
		lblServiceid1.setBounds(85, 88, 61, 14);
		deleteservice.add(lblServiceid1);
		
		txtidDel = new JTextField();
		txtidDel.setBounds(228, 85, 136, 20);
		deleteservice.add(txtidDel);
		txtidDel.setColumns(10);
		
		JLabel lblServicename2 = new JLabel("ServiceName");
		lblServicename2.setBounds(85, 143, 78, 14);
		deleteservice.add(lblServicename2);
		
		txtnamedel = new JLabel("");
		txtnamedel.setBounds(228, 143, 171, 14);
		deleteservice.add(txtnamedel);
		
		JLabel lblServicecost1 = new JLabel("ServiceCost");
		lblServicecost1.setBounds(85, 185, 72, 14);
		deleteservice.add(lblServicecost1);
		
		txtcostdel = new JLabel("");
		txtcostdel.setBounds(253, 185, 146, 14);
		deleteservice.add(txtcostdel);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String Id=txtidDel.getText();
					if(Id.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter ID","Enter Details",JOptionPane.WARNING_MESSAGE);
					}
					else {
						if((JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Delete this Entry","Confirmation",JOptionPane.WARNING_MESSAGE))==0)
						{
						String strsql="Delete from  Service_Task where ServiceID=?";
					try {
						psDelete=con.prepareStatement(strsql);
						psDelete.setString(1, Id);
						int row=psDelete.executeUpdate();
						if(row>0)
						{
							JOptionPane.showMessageDialog(null, "Data Deleted Successfully");
							txtidDel.setText("");
							txtnamedel.setText("");
							txtcostdel.setText("");
						}
						else {
							JOptionPane.showMessageDialog(null,"Cannot Delete");
						}
					} catch (SQLException se) {
						System.out.println(se);
					}
						}
					}
				
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(177, 254, 113, 23);
		deleteservice.add(btnDelete);
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{	
					String id=txtidDel.getText();
					if(id.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter ID","Enter Details",JOptionPane.WARNING_MESSAGE);
						
					}
					
					else {
						
				String strsql="Select * from service_task where ServiceId = ?";
				psDelete=con.prepareStatement(strsql);
				psDelete.setString(1, id);
				rsDel=psDelete.executeQuery();
				if(rsDel.next())
				{		
					
					
						btnDelete.setEnabled(true);
					
					String name=rsDel.getString("ServiceName");
					float cost=rsDel.getFloat("ServiceCost");
					txtnamedel.setText(name);
					txtcostdel.setText(String.valueOf(cost));
					
					
					//JOptionPane.showMessageDialog(this, "done");
							/*z*/
					
					
					
				}
				else {
					JOptionPane.showMessageDialog(null,"No Such Data Found");
					txtidDel.setText("");
				}
					}
				}
				catch(SQLException se){
					System.out.println(se);
				}
				try {
					if(rsDel!=null)
						rsDel.close();
					if(psDelete!=null)
						psDelete.close();
				}
				catch(SQLException se)
				{
					System.out.println(se);
				}
			}

			
				
			});
		
		btnGo.setBounds(374, 84, 89, 23);
		deleteservice.add(btnGo);
		
		JLabel lblRs_2 = new JLabel("Rs.");
		lblRs_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRs_2.setBounds(228, 185, 24, 14);
		deleteservice.add(lblRs_2);
		
		JLabel label_6 = new JLabel("A.S.S.U.R'E.");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(label_6.getFont().deriveFont(label_6.getFont().getStyle() | Font.BOLD, label_6.getFont().getSize() + 2f));
		label_6.setBounds(602, 392, 83, 27);
		deleteservice.add(label_6);
		
		JPanel searchservice = new JPanel();
		tabbedPane.addTab("Search Service", null, searchservice, null);
		searchservice.setLayout(null);
		
		JLabel lblServicename3 = new JLabel("ServiceName");
		lblServicename3.setBounds(94, 176, 83, 14);
		searchservice.add(lblServicename3);
		
		JLabel lblServiceid2 = new JLabel("ServiceId");
		lblServiceid2.setBounds(92, 114, 85, 14);
		searchservice.add(lblServiceid2);
		
		txtidsearch = new JTextField();
		txtidsearch.setBounds(236, 111, 96, 20);
		searchservice.add(txtidsearch);
		txtidsearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				
				String id=txtidsearch.getText();
				if(id.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please Enter ID","Enter Details",JOptionPane.WARNING_MESSAGE);
				}
				else {

					String str="Select * from service_task where ServiceId = ?";
					try {
						pssearch=con.prepareStatement(str);
						pssearch.setString(1, id);
						rssearch=pssearch.executeQuery();
						if(rssearch.next())
						{
							String Name=rssearch.getString("ServiceName");
							String cost=rssearch.getString("ServiceCost");
							String remark=rssearch.getString("Remarks");
							lblnamesearch.setText(Name);
							lblcostsearch.setText(cost);
							lblremarksearch.setText(remark);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "No Record Found");
							txtnameupdate.setText("");
							txtcostupdate.setText("");
							txtremarkupdate.setText("");
							
						}
						
					}
					catch(SQLException se){System.out.println(se);
						
					}
					finally {
						try {if(rssearch!=null)
							rssearch.close();
						if(pssearch!=null)
							pssearch.close();
						}
							
						
						catch(SQLException se) 
						{System.out.println(se);}
					}
			}
			




				
				
				
			}
		});
		
		btnSearch.setBounds(456, 110, 83, 23);
		searchservice.add(btnSearch);
		
		 lblnamesearch = new JLabel("");
		lblnamesearch.setBounds(236, 170, 179, 20);
		searchservice.add(lblnamesearch);
		
		JLabel lblServicecost2 = new JLabel("ServiceCost");
		lblServicecost2.setBounds(94, 228, 83, 14);
		searchservice.add(lblServicecost2);
		
		 lblcostsearch = new JLabel("");
		lblcostsearch.setBounds(257, 226, 146, 16);
		searchservice.add(lblcostsearch);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setBounds(94, 298, 83, 14);
		searchservice.add(lblRemark);
		
		lblremarksearch = new JLabel("");
		lblremarksearch.setBounds(236, 263, 334, 87);
		searchservice.add(lblremarksearch);
		
		JLabel label_2 = new JLabel("Rs.");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(236, 226, 24, 16);
		searchservice.add(label_2);
		
		JLabel label_7 = new JLabel("A.S.S.U.R'E.");
		label_7.setForeground(Color.BLACK);
		label_7.setFont(label_7.getFont().deriveFont(label_7.getFont().getStyle() | Font.BOLD, label_7.getFont().getSize() + 2f));
		label_7.setBounds(602, 392, 83, 27);
		searchservice.add(label_7);
		
		JPanel updateservice = new JPanel();
		tabbedPane.addTab("Update Service\r\n", null, updateservice, null);
		updateservice.setLayout(null);
		
		
		
		
		
		
		
		
		JLabel lblServiceid3 = new JLabel("ServiceId");
		lblServiceid3.setBounds(115, 55, 59, 14);
		updateservice.add(lblServiceid3);
		
		txtidupdate = new JTextField();
		txtidupdate.setBounds(205, 51, 179, 20);
		updateservice.add(txtidupdate);
		txtidupdate.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String id=txtidupdate.getText();
				if(id.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please Enter ID","Enter Details",JOptionPane.WARNING_MESSAGE);
				}
				
				else
				{
					btnUpdate.setEnabled(true);
					String str="Select * from Service_Task where ServiceId = ?";
					try {
						psUpdate=con.prepareStatement(str);
						psUpdate.setString(1, id);
						rsUpdate=psUpdate.executeQuery();
						if(rsUpdate.next())
						{
							String name=rsUpdate.getString("ServiceName");
							String cost=rsUpdate.getString("ServiceCost");
							String remark=rsUpdate.getString("Remarks");
							txtnameupdate.setText(name);
							txtcostupdate.setText(String.valueOf(cost));
							txtremarkupdate.setText(remark);
							
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "No Record found");
						}
						
						
					}
					catch(SQLException se) {
						System.out.println(se);
					}
					finally {
						try {
							if(rsUpdate!=null)
								rsUpdate.close();
							if(psUpdate!=null)
								psUpdate.close();
						}
						catch(SQLException we) {
							System.out.println(we);
						}
					}
				
			}
			}});
		btnFind.setBounds(409, 51, 89, 23);
		updateservice.add(btnFind);
		
		JLabel label1 = new JLabel("ServiceName");
		label1.setBounds(111, 110, 76, 14);
		updateservice.add(label1);
		
		JLabel label_1 = new JLabel("ServiceCost");
		label_1.setBounds(113, 163, 74, 14);
		updateservice.add(label_1);
		
		JLabel lblRs_1 = new JLabel("       Rs.  ");
		lblRs_1.setBounds(184, 164, 48, 14);
		updateservice.add(lblRs_1);
		
		JLabel label_3 = new JLabel("/-");
		label_3.setBounds(350, 163, 106, 14);
		updateservice.add(label_3);
		
		JLabel label_4 = new JLabel("Remarks");
		label_4.setBounds(115, 212, 63, 14);
		updateservice.add(label_4);
		
		 btnUpdate = new JButton("Update");
		 btnUpdate.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
		 		
		 		
		 		
		 		
		 		String id=txtidupdate.getText();
				String name=txtnameupdate.getText();
				String totalamount=txtcostupdate.getText();
				String remark=txtremarkupdate.getText();
				if(id.isEmpty()||name.isEmpty()||totalamount.isEmpty()||remark.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter the details","Details Required",JOptionPane.INFORMATION_MESSAGE);
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
		     		JOptionPane.showMessageDialog(null, "Enter Valid Amount","Validating Amount",JOptionPane.ERROR_MESSAGE);
		     	}
		    		
		     	else
		     	{
				
					if((JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Update this Entry","Confirmation",JOptionPane.WARNING_MESSAGE))==0)
					{
						
						float cost =Float.parseFloat(totalamount);
					
					try {
						String strsql="Update service_task set ServiceName = ?,ServiceCost = ?,Remarks = ? where ServiceId = ?";
						psUpdate=con.prepareStatement(strsql);
						psUpdate.setString(1, name);
						psUpdate.setFloat(2, cost);
						psUpdate.setString(3, remark);
						psUpdate.setString(4, id);
						int row=psUpdate.executeUpdate();
						if(row>0)
						{
							JOptionPane.showMessageDialog(null, "Data Updated Succesfully");
							txtnameupdate.setText("");
							txtidupdate.setText("");
							txtcostupdate.setText("");
							txtremarkupdate.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Can't Update Data");
						}
						
						
					}
					
					catch(SQLException qe)
					{
						System.out.println(qe);
					}
					finally
					{
						try {
							if(rsUpdate!=null)
								rsUpdate.close();
							if(psUpdate!=null)
								psUpdate.close();
							
						}
					
						catch(SQLException de)
						{
							System.out.println(de);
						}
					}
					}
				}
		 		
		 		
		 		
		 		
		 	}
		 	} });
		 btnUpdate.setBounds(242, 374, 89, 23);
		 updateservice.add(btnUpdate);
		 btnUpdate.setEnabled(false);
		 
		 txtnameupdate = new JTextField();
		 txtnameupdate.setBounds(206, 107, 178, 20);
		 updateservice.add(txtnameupdate);
		 txtnameupdate.setColumns(10);
		 
		 txtcostupdate = new JTextField();
		 txtcostupdate.setBounds(242, 160, 96, 20);
		 updateservice.add(txtcostupdate);
		 txtcostupdate.setColumns(10);
		 
		 txtremarkupdate = new JTextField();
		 txtremarkupdate.setBounds(205, 212, 293, 108);
		 updateservice.add(txtremarkupdate);
		 txtremarkupdate.setColumns(10);
		 
		 JLabel label_8 = new JLabel("A.S.S.U.R'E.");
		 label_8.setForeground(Color.BLACK);
		 label_8.setFont(label_8.getFont().deriveFont(label_8.getFont().getStyle() | Font.BOLD, label_8.getFont().getSize() + 2f));
		 label_8.setBounds(602, 392, 83, 27);
		 updateservice.add(label_8);
	
		

	 

}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
	//CrudOperation.closeConnection();
}

@Override
public void windowClosed(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
	}












