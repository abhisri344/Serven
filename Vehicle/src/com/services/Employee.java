package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dbutils.CrudOperation;
import com.mysql.jdbc.Connection;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Employee extends JFrame {

	private JPanel contentPane;
	private Connection con;
	private PreparedStatement psSeAdd,psAdd,psSeDel,psDel,psSeUp,psUp,psSea,psSear, ps;
	private ResultSet rsSeAdd,rsAdd,rsSeDel,rsDel,rsSeUp,rsUp,rsSea,rsSear, rs;
	private JComboBox cmbRole;
	private JTextField txtID,txtIDDel,txtIdSea,txtIdUp,txtNameUp,txtPhoneUp,txtEmailUp;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JButton btnAddEmployee;
	private JLabel lblPassword,lblNameDel,lblEmailDel,lblNameSea,lblEmailSea,lblPhoneSea,lblUserSea,lblGenSea;
	private JPasswordField txtPass;
	private ButtonGroup Gender;
	private JRadioButton rdmale,rdfemale;
	private JPanel deleteEmployee;
	private JButton btnDelete;
	private JTextField txtPassUp;
	private JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
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
	public Employee() {
		setTitle("Manage Employee");

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=(Connection) CrudOperation.createConnection();
		createGui();
	}
public void createGui()
{
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 684, 534);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	setLocationRelativeTo(null);
	contentPane.setLayout(null);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setBounds(10, 0, 667, 495);
	contentPane.add(tabbedPane);
	
	JPanel addEmployee = new JPanel();
	tabbedPane.addTab("Add Employee", null, addEmployee, null);
	addEmployee.setLayout(null);

	
	JLabel lblName = new JLabel("Name");
	lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblName.setBounds(108, 35, 49, 28);
	addEmployee.add(lblName);
	
	JLabel lblEmail = new JLabel("Email");
	lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblEmail.setBounds(108, 82, 49, 31);
	addEmployee.add(lblEmail);
	
	JLabel lblPhone = new JLabel("Phone");
	lblPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblPhone.setBounds(108, 129, 61, 28);
	addEmployee.add(lblPhone);
	
	JLabel lblGender = new JLabel("Gender");
	lblGender.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblGender.setBounds(108, 178, 61, 28);
	addEmployee.add(lblGender);
	
	btnAddEmployee = new JButton("ADD EMPLOYEE");
	btnAddEmployee.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String ID=txtID.getText();
			String Name=txtName.getText();
			String Email=txtEmail.getText();
			String Phone=txtPhone.getText();
			String Gender=null;
			
			
			if(rdmale.isSelected()==true)
				Gender=rdmale.getText();
			
			if(rdfemale.isSelected()==true)
				Gender=rdfemale.getText();
			String Password=txtPass.getText();
			String Role=(String)cmbRole.getSelectedItem();
			
			
		if(ID.isEmpty()||Name.isEmpty()||Email.isEmpty()||Phone.isEmpty()||Gender.isEmpty()||Password.isEmpty()||Role.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please Fill All Details","MISSING DETAILS",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		else {
			
			int flag=0;
      		int s=Phone.length();
            if(s==10)        
            {  for(int i=0;i<10;i++)
     	      {
     	    	  if(Phone.charAt(i)=='1'||Phone.charAt(i)=='2'||Phone.charAt(i)=='3'||Phone.charAt(i)=='4'||Phone.charAt(i)=='5'
     	    			  ||Phone.charAt(i)=='6'||Phone.charAt(i)=='7'||Phone.charAt(i)=='8'||Phone.charAt(i)=='9'||Phone.charAt(i)=='0')
     	    	  
     	    	  {

     	    		  flag=0;
     	  	  
     	    	  }   	  
     	    	  else
     	    	  {
     	    		  flag=2;
     	    		  break;
     	    	  }
     	    	  
     	      }
            }
     	
     	if(s!=10||flag==2)
     	{	
     		JOptionPane.showMessageDialog(null, "Enter Valid Phone Number","Validating phone number",JOptionPane.ERROR_MESSAGE);
     	}
      	
		
		
		
		
		
		else
		{
			try
			{

				String strup="select * from  employee where EmployeeID='"+ID+"' ";
				psSeAdd=con.prepareStatement(strup);
				rsSeAdd=psSeAdd.executeQuery();
				if(rsSeAdd.next()==true)
				{
					JOptionPane.showMessageDialog(null,"This ID is already assigned");	
						
				}
				else
				{
				String strsql="insert into employee values (?,?,?,?,?)";
				
		psAdd=con.prepareStatement(strsql);
		psAdd.setString(1, ID);
		psAdd.setString(2, Name);
		psAdd.setString(3, Email);
		psAdd.setString(4, Phone);
		psAdd.setString(5, Gender);
		int row=psAdd.executeUpdate();
				if (row>0)
				{
					//JOptionPane.showMessageDialog(this, "DATA ADDED SUCCESSFULLY","Congrats",JOptionPane.INFORMATION_MESSAGE);
					
					
					
				}
				strsql ="insert into login values (?,?,?)";
				psAdd=con.prepareStatement(strsql);
				psAdd.setString(1, ID);
				psAdd.setString(2, Password);
				psAdd.setString(3,Role);
				row=psAdd.executeUpdate();
				if (row>0)
				{
					JOptionPane.showMessageDialog(null, "DATA ADDED SUCCESSFULLY","Congrats",JOptionPane.INFORMATION_MESSAGE);
					txtID.setText("");
				    fillCombo();
					txtPass.setText("");
					txtEmail.setText("");
					txtName.setText("");
					txtPhone.setText("");
					if(rdmale.isSelected())
						rdmale.setSelected(false);
					if(rdfemale.isSelected())
						rdfemale.setSelected(false);
					
					
				}
			}}
		
		catch(SQLException se)
			{
			System.out.println(se);
			}
			finally
			{
			  
			try
			{
				
				if(psAdd!=null)
			    psAdd.close();
				
			 }
			catch(SQLException se)
			{
			System.out.println(se);
			}
			}
		}
		}
	}});
	btnAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 13));
	btnAddEmployee.setBounds(160, 399, 164, 28);
	addEmployee.add(btnAddEmployee);
	
	txtID = new JTextField();
	txtID.setBounds(248, 234, 164, 20);
	addEmployee.add(txtID);
	txtID.setColumns(10);
	
	txtEmail = new JTextField();
	txtEmail.setBounds(248, 88, 164, 20);
	addEmployee.add(txtEmail);
	txtEmail.setColumns(10);
	
	txtPhone = new JTextField();
	txtPhone.setBounds(248, 134, 164, 20);
	addEmployee.add(txtPhone);
	txtPhone.setColumns(10);
	
	cmbRole	 = new JComboBox();
	cmbRole.setBounds(248, 336, 107, 20);
	cmbRole.setModel(new DefaultComboBoxModel(new String[] {"Select Role", "Receptionist", "Service Engineer"}));
	addEmployee.add(cmbRole);
	
	lblPassword = new JLabel("Password");
	lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblPassword.setBounds(108, 287, 96, 23);
	addEmployee.add(lblPassword);
	
	txtPass = new JPasswordField();
	txtPass.setBounds(249, 284, 163, 20);
	addEmployee.add(txtPass);
	
	
	Gender=new ButtonGroup();
	rdmale = new JRadioButton("Male");
	rdmale.setBounds(248, 182, 76, 23);
	Gender.add(rdmale);
	addEmployee.add(rdmale);
	
	

	rdfemale = new JRadioButton("Female");
	rdfemale.setBounds(326, 182, 96, 23);
	Gender.add(rdfemale);
	addEmployee.add(rdfemale);
	
	txtName = new JTextField();
	txtName.setBounds(248, 40, 164, 20);
	addEmployee.add(txtName);
	txtName.setColumns(10);
	
	JLabel lblEmployeeId = new JLabel("Employee Id");
	lblEmployeeId.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblEmployeeId.setBounds(108, 229, 96, 28);
	addEmployee.add(lblEmployeeId);
	
	JLabel lblRole = new JLabel("Role");
	lblRole.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblRole.setBounds(108, 331, 61, 28);
	addEmployee.add(lblRole);
	
	JLabel label = new JLabel("A.S.S.U.R'E.");
	label.setForeground(Color.BLACK);
	label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD, label.getFont().getSize() + 2f));
	label.setBounds(569, 429, 83, 27);
	addEmployee.add(label);
	
	deleteEmployee = new JPanel();
	tabbedPane.addTab("Delete Employee", null, deleteEmployee, null);
	deleteEmployee.setLayout(null);
	
	
	JLabel lblEmployeeId_1 = new JLabel("Employee ID");
	lblEmployeeId_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblEmployeeId_1.setBounds(42, 41, 105, 27);
	deleteEmployee.add(lblEmployeeId_1);
	
	txtIDDel = new JTextField();
	txtIDDel.setBounds(181, 46, 129, 27);
	deleteEmployee.add(txtIDDel);
	txtIDDel.setColumns(10);
	
	JButton btnNewButton = new JButton("GO");
	btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
			String Id=txtIDDel.getText();
			
			if(Id.isEmpty())
				JOptionPane.showMessageDialog(null, "Please Fill Employee ID","ID Required",JOptionPane.WARNING_MESSAGE);
				else
			{
			try {
				String strsql="select * from  employee where EmployeeID='"+Id+"' ";
				psSeDel=con.prepareStatement(strsql);
				rsSeDel=psSeDel.executeQuery();
				if(rsSeDel.next()==true)
				{
					
					String Ename=rsSeDel.getString("Name");
					lblNameDel.setText(Ename);
					String Eemail=rsSeDel.getString("Email");
					lblEmailDel.setText(Eemail);
					btnDelete.setEnabled(true);
				}
				

				else
				{
					JOptionPane.showMessageDialog(null, "This Id does not exist");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally
			{
			  
			try
			{
				
				if(psSeDel!=null)
			    psSeDel.close();
				if(rsSeDel!=null)
			    rsSeDel.close();
			 }
			catch(SQLException se)
			{
			System.out.println(se);
			}
			}
		}}
	});
	btnNewButton.setBounds(342, 46, 65, 27);
	
	deleteEmployee.add(btnNewButton);
	
	JLabel lblName_1 = new JLabel("Name");
	lblName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblName_1.setBounds(42, 103, 65, 27);
	deleteEmployee.add(lblName_1);
	
	lblNameDel = new JLabel("");
	lblNameDel.setBounds(181, 106, 226, 24);
	deleteEmployee.add(lblNameDel);
	
	JLabel lblEmail_1 = new JLabel("Email");
	lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblEmail_1.setBounds(42, 168, 65, 24);
	deleteEmployee.add(lblEmail_1);
	
	lblEmailDel = new JLabel("");
	lblEmailDel.setBounds(181, 168, 226, 27);
	deleteEmployee.add(lblEmailDel);
	
	btnDelete = new JButton("Delete");
	btnDelete.setEnabled(false);
	btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String Id=txtIDDel.getText();
			try {
				if((JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Delete this Employee Data","Confirmation",JOptionPane.WARNING_MESSAGE))==0)
			    {   
					String Servsts = "done";
					
					String strservice="select * from ServiceRequest join AllotService"
							+ " where ServiceRequest.VehicleNumber=AllotService.VehicleNumber && AllotService.EmployeeId=?";
					ps=con.prepareStatement(strservice);
					ps.setString(1, Id);
					rs=ps.executeQuery();
					if(rs.next()==true)
					{
						Servsts=rs.getString("ServiceRequest.ServiceStatus");
					}
					//System.out.println(Servsts);	
					if(Servsts.equalsIgnoreCase("Not Done"))
					{
						JOptionPane.showMessageDialog(null, "This employee has pending work to do hence can not be removed");
					}
					else
					{	
					String strsql="Delete from  employee where EmployeeID='"+Id+"' ";
				
				psDel=con.prepareStatement(strsql);
				int row=psDel.executeUpdate();
				if(row>0)
				{	
					
					txtIDDel.setText("");
					lblNameDel.setText("");
					lblEmailDel.setText("");
				}
				strsql="Delete from  login where ID='"+Id+"' ";
				psDel=con.prepareStatement(strsql);
				int row1=psDel.executeUpdate();
				if(row1>0)
				{	
					JOptionPane.showMessageDialog(null, "Data deleted successfully");
					txtIDDel.setText("");
					lblNameDel.setText("");
					lblEmailDel.setText("");
				
				}
				}}}
			 catch (SQLException e2) {
				
				e2.printStackTrace();
			}
			finally
			{
			  
			try
			{
				
				if(psDel!=null)
			    psDel.close();
				
			 }
			catch(SQLException se)
			{
			System.out.println(se);
			}
			}	
		}
	});
	btnDelete.setBounds(181, 254, 89, 23);
	deleteEmployee.add(btnDelete);
	
	JLabel label_1 = new JLabel("A.S.S.U.R'E.");
	label_1.setForeground(Color.BLACK);
	label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD, label_1.getFont().getSize() + 2f));
	label_1.setBounds(569, 429, 83, 27);
	deleteEmployee.add(label_1);
	
	JLabel label_4 = new JLabel("");
	label_4.setIcon(new ImageIcon(Employee.class.getResource("/com/images/trash.jpg")));
	label_4.setBounds(480, 57, 154, 135);
	deleteEmployee.add(label_4);

	JPanel searchEmployee = new JPanel();
	tabbedPane.addTab("Search Employee", null, searchEmployee, null);
	searchEmployee.setLayout(null);
	
	JLabel lblEmployeeId_2 = new JLabel("Employee ID");
	lblEmployeeId_2.setFont(new Font("Tahoma", Font.BOLD, 23));
	lblEmployeeId_2.setBounds(46, 12, 176, 45);
	searchEmployee.add(lblEmployeeId_2);
	
	JLabel lblName_2 = new JLabel("Name");
	lblName_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblName_2.setBounds(46, 132, 49, 20);
	searchEmployee.add(lblName_2);
	
	JLabel lblEmail_2 = new JLabel("Email");
	lblEmail_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblEmail_2.setBounds(378, 132, 49, 20);
	searchEmployee.add(lblEmail_2);
	
	JLabel lblPhone_1 = new JLabel("Phone");
	lblPhone_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblPhone_1.setBounds(378, 222, 49, 20);
	searchEmployee.add(lblPhone_1);
	
	JLabel lblGender_1 = new JLabel("Gender");
	lblGender_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblGender_1.setBounds(378, 313, 59, 20);
	searchEmployee.add(lblGender_1);
	
	JButton btnSearch = new JButton("Search");
	btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String Id=txtIdSea.getText();
			if(Id.isEmpty()==true)
			{
				JOptionPane.showMessageDialog(null, "Please enter Employee Id");
			}
			else
			{
			try {
				String strsql="select * from  employee where EmployeeID='"+Id+"' ";
				psSea=con.prepareStatement(strsql);
				rsSea=psSea.executeQuery();
				if(rsSea.next()==true)
				{
					
					String Ename=rsSea.getString("Name");
					lblNameSea.setText(Ename);
					String Eemail=rsSea.getString("Email");
					lblEmailSea.setText(Eemail);
					String phone=rsSea.getString("Phone");
					lblPhoneSea.setText(phone);
					String gender=rsSea.getString("Gender");
					lblGenSea.setText(gender);
				}
				 strsql="select * from  login where ID='"+Id+"' ";
				psSear=con.prepareStatement(strsql);
				rsSear=psSear.executeQuery();
				if(rsSear.next()==true)
				{
					
					String User=rsSear.getString("UserType");
					lblUserSea.setText(User);
					
				}
				

				else
				{
					JOptionPane.showMessageDialog(null, "This Id does not exist");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally
			{
			  
			try
			{
				
				if(psSear!=null)
			    psSear.close();
				if(rsSear!=null)
			    rsSear.close();
			 }
			catch(SQLException se)
			{
			System.out.println(se);
			}
			}
		}
		}});
	btnSearch.setBounds(185, 68, 109, 34);
	searchEmployee.add(btnSearch);
	
	JLabel lblUserType = new JLabel("User Type");
	lblUserType.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblUserType.setBounds(46, 222, 78, 20);
	searchEmployee.add(lblUserType);
	
	txtIdSea = new JTextField();
	txtIdSea.setColumns(10);
	txtIdSea.setBounds(265, 22, 166, 26);
	searchEmployee.add(txtIdSea);
	lblNameSea = new JLabel("");
	lblNameSea.setBounds(154, 132, 177, 20);
	searchEmployee.add(lblNameSea);
	
	lblUserSea = new JLabel("");
	lblUserSea.setBounds(154, 222, 166, 20);
	searchEmployee.add(lblUserSea);
	
	 lblEmailSea = new JLabel("");
	lblEmailSea.setBounds(473, 132, 166, 20);
	searchEmployee.add(lblEmailSea);
	
	 lblPhoneSea = new JLabel("");
	lblPhoneSea.setBounds(473, 222, 166, 20);
	searchEmployee.add(lblPhoneSea);
	
	lblGenSea = new JLabel("");
	lblGenSea.setBounds(473, 313, 166, 20);
	searchEmployee.add(lblGenSea);
	
	JLabel label_2 = new JLabel("A.S.S.U.R'E.");
	label_2.setForeground(Color.BLACK);
	label_2.setFont(label_2.getFont().deriveFont(label_2.getFont().getStyle() | Font.BOLD, label_2.getFont().getSize() + 2f));
	label_2.setBounds(569, 429, 83, 27);
	searchEmployee.add(label_2);
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon(Employee.class.getResource("/com/images/search.png")));
	lblNewLabel.setBounds(504, 12, 100, 102);
	searchEmployee.add(lblNewLabel);
	
	JPanel updateEmployee = new JPanel();
	tabbedPane.addTab("Update Employee", null, updateEmployee, null);
	updateEmployee.setLayout(null);
	
	JLabel label_8 = new JLabel("Employee ID");
	label_8.setFont(new Font("Tahoma", Font.BOLD, 26));
	label_8.setBounds(57, 31, 176, 45);
	updateEmployee.add(label_8);
	
	txtIdUp = new JTextField();
	txtIdUp.setColumns(10);
	txtIdUp.setBounds(292, 42, 155, 35);
	updateEmployee.add(txtIdUp);
	
	JLabel label_9 = new JLabel("Name");
	label_9.setFont(new Font("Tahoma", Font.BOLD, 14));
	label_9.setBounds(57, 162, 77, 28);
	updateEmployee.add(label_9);
	
	JLabel lblPhoneNo = new JLabel("Phone No.");
	lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblPhoneNo.setBounds(57, 224, 77, 28);
	updateEmployee.add(lblPhoneNo);
	
	JLabel lblEmail_3 = new JLabel("Email");
	lblEmail_3.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblEmail_3.setBounds(57, 287, 77, 28);
	updateEmployee.add(lblEmail_3);
	
	txtNameUp = new JTextField();
	txtNameUp.setBounds(237, 168, 210, 20);
	updateEmployee.add(txtNameUp);
	txtNameUp.setColumns(10);
	
	txtPhoneUp = new JTextField();
	txtPhoneUp.setColumns(10);
	txtPhoneUp.setBounds(237, 230, 210, 20);
	updateEmployee.add(txtPhoneUp);
	
	txtEmailUp = new JTextField();
	txtEmailUp.setColumns(10);
	txtEmailUp.setBounds(237, 293, 210, 20);
	updateEmployee.add(txtEmailUp);
	
	JButton btnGo = new JButton("GO");
	btnGo.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			String Id=txtIdUp.getText();
			btnUpdate.setEnabled(true);
			if(Id.isEmpty())
				JOptionPane.showMessageDialog(null, "Please Fill Employee ID");
				else
			{
			try {
				String strsql="select * from  employee where EmployeeID='"+Id+"' ";
				psSeUp=con.prepareStatement(strsql);
				rsSeUp=psSeUp.executeQuery();
				if(rsSeUp.next()==true)
				{
					
					String Ename=rsSeUp.getString("Name");
					txtNameUp.setText(Ename);
					String Eemail=rsSeUp.getString("Email");
					txtEmailUp.setText(Eemail);
					String phone=rsSeUp.getString("Phone");
					txtPhoneUp.setText(phone);
					
				
				
				String str="select * from  login where ID='"+Id+"' ";
				psSeUp=con.prepareStatement(str);
				rsSeUp=psSeUp.executeQuery();
				if(rsSeUp.next()==true)
				{
								/*
								 * String pass=rsSeUp.getString("Password"); txtPassUp.setText(pass);
								 */
					
					btnDelete.setEnabled(true);
				}
				}

				else
				{
					JOptionPane.showMessageDialog(null, "This Id does not exist");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally
			{
			  
			try
			{
				
				if(psSeUp!=null)
			    psSeUp.close();
				if(rsSeUp!=null)
			    rsSeUp.close();
			 }
			catch(SQLException se)
			{
			System.out.println(se);
			}
			}
		}}
	});
	
	btnGo.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnGo.setBounds(212, 101, 64, 35);
	updateEmployee.add(btnGo);
	
	 btnUpdate = new JButton("Update");
	btnUpdate.setEnabled(false);
	btnUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String IdUp=txtIdUp.getText();
			String name=txtNameUp.getText();
			String email=txtEmailUp.getText();
			String phone=txtPhoneUp.getText();
			//String pass=txtPassUp.getText();
			
			try {
				
				if((JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Update this Employee Data","Confirmation",JOptionPane.WARNING_MESSAGE))==0)
				{
					String strupdate="update employee set Name=? ,Phone=?, Email=? where employeeID=?";
				
							
				psUp=con.prepareStatement(strupdate);
				psUp.setString(1, name);
				psUp.setString(3, email);
				psUp.setString(2, phone);
				psUp.setString(4, IdUp);
				int row=psUp.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(null, "Data Updated succesfully");
					txtIdUp.setText("");
					txtNameUp.setText("");
					txtPhoneUp.setText("");
					txtEmailUp.setText("");
				}
				
				//String strupdate1="update login set Password=? where ID= '"+IdUp+"' ";
				
				//psUp=con.prepareStatement(strupdate1);
				//psUp.setString(1, pass);
				
				//int row1=psUp.executeUpdate();
				//if(row1>0)
				
				}
				}
			catch (SQLException e2) {
				System.out.println(e2);
			}
			finally
			{
			  
			try
			{
				
				if(psUp!=null)
			    psUp.close();
			 }
			catch(SQLException se)
			{
			System.out.println(se);
			}
			}
		}
	});
	btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
	btnUpdate.setBounds(199, 407, 110, 23);
	updateEmployee.add(btnUpdate);
	
	JLabel label_3 = new JLabel("A.S.S.U.R'E.");
	label_3.setForeground(Color.BLACK);
	label_3.setFont(label_3.getFont().deriveFont(label_3.getFont().getStyle() | Font.BOLD, label_3.getFont().getSize() + 2f));
	label_3.setBounds(569, 429, 83, 27);
	updateEmployee.add(label_3);
	

	
	
}
void fillCombo()
{
	cmbRole.setModel(new DefaultComboBoxModel(new String[] {"Select Role", "Receptionist", "Service Engineer"}));
}
}
