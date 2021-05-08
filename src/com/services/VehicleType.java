package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbutils.CrudOperation;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VehicleType extends JFrame implements ActionListener
{
	private Connection con;
	private PreparedStatement psAdd,psSe,psDel,psUp;
	private ResultSet rsAdd,rsSe,rsDel,rsUp;
	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField txtTypeID;
	private JTextField txtTypeName;
	private JButton btnAdd,btnUpdate,btnDelete;
	private JTextField txtID;
	private JLabel lblName;
	private JTextField txtIdup;
	private JTextField txtNameup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleType frame = new VehicleType();
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
	public VehicleType() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		setResizable(false);
		con=CrudOperation.createConnection();
		
		createGui();
	}
public void createGui()
{
	setTitle("Manage Vehicle Type");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 607, 420);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	setLocationRelativeTo(null);
	contentPane.setLayout(null);
	tabbedPane.setBounds(0, 0, 601, 392);
	contentPane.add(tabbedPane);
	
	JPanel AddVehicleType = new JPanel();
	tabbedPane.addTab("Add Vehicle Type", null, AddVehicleType, null);
	AddVehicleType.setLayout(null);
	
	JLabel lblTypeName = new JLabel("Type Name");
	lblTypeName.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblTypeName.setBounds(148, 150, 93, 35);
	AddVehicleType.add(lblTypeName);
	
	JLabel lblTypeId = new JLabel("Type Id");
	lblTypeId.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblTypeId.setBounds(148, 82, 93, 35);
	AddVehicleType.add(lblTypeId);
	
	txtTypeID = new JTextField();
	txtTypeID.setBounds(285, 91, 112, 20);
	AddVehicleType.add(txtTypeID);
	txtTypeID.setColumns(10);
	
	txtTypeName = new JTextField();
	txtTypeName.setColumns(10);
	txtTypeName.setBounds(285, 159, 112, 20);
	AddVehicleType.add(txtTypeName);
	
	btnAdd = new JButton("ADD");
	btnAdd.addActionListener(this);
	btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
	btnAdd.setBounds(233, 219, 89, 23);
	AddVehicleType.add(btnAdd);
	
	JLabel label = new JLabel("A.S.S.U.R'E.");
	label.setForeground(Color.BLACK);
	label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD, label.getFont().getSize() + 2f));
	label.setBounds(503, 326, 83, 27);
	AddVehicleType.add(label);
	
	JLabel label_3 = new JLabel("");
	label_3.setIcon(new ImageIcon(VehicleType.class.getResource("/com/images/vehicleType.png")));
	label_3.setBounds(10, 240, 204, 113);
	AddVehicleType.add(label_3);
	
	JPanel panel_1 = new JPanel();
	tabbedPane.addTab("Delete Vehice Type", null, panel_1, null);
	panel_1.setLayout(null);
	
	JLabel lblTypyeId = new JLabel("Type ID");
	lblTypyeId.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblTypyeId.setBounds(117, 69, 95, 29);
	panel_1.add(lblTypyeId);
	
	txtID = new JTextField();
	txtID.setBounds(260, 75, 86, 20);
	panel_1.add(txtID);
	txtID.setColumns(10);
	
	JLabel lblTypeName_1 = new JLabel("Type Name");
	lblTypeName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblTypeName_1.setBounds(117, 137, 95, 29);
	panel_1.add(lblTypeName_1);
	
	lblName = new JLabel("");
	lblName.setBounds(260, 146, 86, 20);
	panel_1.add(lblName);
	
	JButton btnGo = new JButton("GO");
	btnGo.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ae) {
		String Id=txtID.getText();
		if(Id.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please Enter a Type ID");
		}
		else
		{
		try {
			String strsql="select * from  vehicletype where TypeID='"+Id+"' ";
			psSe=con.prepareStatement(strsql);
			rsSe=psSe.executeQuery();
			if(rsSe.next()==true)
			{
				String Tname=rsSe.getString("TypeName");
				lblName.setText(Tname);
				btnDelete.setEnabled(true);	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This Id does not exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
		  
		try
		{
			if(rsSe!=null)
		    rsSe.close();
			if(psSe!=null)
		    psSe.close();
		 }
		catch(SQLException se)
		{
		System.out.println(se);
		}
}
	}}
	});
	btnGo.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnGo.setBounds(389, 69, 69, 29);
	panel_1.add(btnGo);
	
	btnDelete = new JButton("DELETE");
	btnDelete.setEnabled(false);
	btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent aea) {
			String Id=txtID.getText();
			if(JOptionPane.showConfirmDialog(null,"Are You sure you want to Delete?")==0)
			{
			try {
				String strsql="Delete from  vehicletype where TypeID='"+Id+"' ";
				psDel=con.prepareStatement(strsql);
				int row=psDel.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(null, "Data deleted successfully");
					txtID.setText("");
					lblName.setText("");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try
			{
				if(rsDel!=null)
			    rsDel.close();
				if(psDel!=null)
			    psDel.close();
			 }
			catch(SQLException se)
			{
			System.out.println(se);
			}
	}}
		}
		);
	btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
	btnDelete.setBounds(203, 210, 95, 29);
	panel_1.add(btnDelete);
	
	JLabel label_1 = new JLabel("A.S.S.U.R'E.");
	label_1.setForeground(Color.BLACK);
	label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD, label_1.getFont().getSize() + 2f));
	label_1.setBounds(503, 326, 83, 27);
	panel_1.add(label_1);
	
	JPanel panel_2 = new JPanel();
	tabbedPane.addTab("Update Vehice Type", null, panel_2, null);
	panel_2.setLayout(null);
	
	JLabel lblTypeId_1 = new JLabel("Type ID");
	lblTypeId_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblTypeId_1.setBounds(169, 45, 88, 35);
	panel_2.add(lblTypeId_1);
	
	JLabel lblTypeName_2 = new JLabel("Type Name");
	lblTypeName_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblTypeName_2.setBounds(169, 183, 102, 24);
	panel_2.add(lblTypeName_2);
	
	txtIdup = new JTextField();
	txtIdup.setBounds(309, 54, 86, 20);
	panel_2.add(txtIdup);
	txtIdup.setColumns(10);
	

	
	JButton btnSearch = new JButton("SEARCH");
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent aea) {
			String IdUp=txtIdup.getText();
			if(IdUp.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Please Enter a Type ID");
			}
			else
			{
			try {
				String strup="select * from  vehicletype where TypeID='"+IdUp+"' ";
				psUp=con.prepareStatement(strup);
				rsUp=psUp.executeQuery();
				if(rsUp.next()==true)
				{
					String Tname=rsUp.getString("TypeName");
					txtNameup.setText(Tname);
					btnUpdate.setEnabled(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "This Id does not exist");
					txtIdup.setText("");
				}
			} catch (SQLException ae) {
				
				ae.printStackTrace();
			}
			finally
			{
			  
			try
			{
				if(rsUp!=null)
			    rsUp.close();
				if(psUp!=null)
			    psUp.close();
			 }
			catch(SQLException se)
			{
			System.out.println(se);
			}
	}
		}}
	});
	btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
	btnSearch.setBounds(224, 115, 102, 24);
	panel_2.add(btnSearch);
	
	txtNameup = new JTextField();
	txtNameup.setBounds(309, 183, 86, 20);
	panel_2.add(txtNameup);
	txtNameup.setColumns(10);

	btnUpdate = new JButton("UPDATE");
	btnUpdate.setEnabled(false);
	btnUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String IdUp=txtIdup.getText();

			String typname1=txtNameup.getText();
			
			if(typname1.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter 'Type Name'","Data Required",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			if(JOptionPane.showConfirmDialog(null,"Are You sure you want to Update?")==0)
			{
		try {
			String strup="select * from  vehicletype where TypeID='"+IdUp+"' ";
			psUp=con.prepareStatement(strup);
			rsUp=psUp.executeQuery();
			if(rsUp.next()==true)
			{
		
				
					
					String strupdate="update vehicletype set TypeName=? where TypeID='"+IdUp+"' ";
					String typname=rsUp.getString("TypeName");
			psUp=con.prepareStatement(strupdate);
			psUp.setString(1, typname1);
			int row=psUp.executeUpdate();
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Data Updated succesfully");
				txtIdup.setText("");
				txtNameup.setText("");
				
			}
			
			}
		}catch (Exception e2) {
			// TODO: handle exception
		}
			
		finally
		{
		  
		try
		{
			if(rsUp!=null)
		    rsUp.close();
			if(psUp!=null)
		    psUp.close();
		 }
		catch(SQLException se)
		{
		System.out.println(se);
		}
}}}
		}
	});
	btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnUpdate.setBounds(214, 236, 123, 35);
	panel_2.add(btnUpdate);
	
	JLabel label_2 = new JLabel("A.S.S.U.R'E.");
	label_2.setForeground(Color.BLACK);
	label_2.setFont(label_2.getFont().deriveFont(label_2.getFont().getStyle() | Font.BOLD, label_2.getFont().getSize() + 2f));
	label_2.setBounds(503, 326, 83, 27);
	panel_2.add(label_2);
}

@Override
public void actionPerformed(ActionEvent e) {
	String ID=txtTypeID.getText();
	String Name=txtTypeName.getText();
	if(ID.isEmpty()||Name.isEmpty())
		JOptionPane.showMessageDialog(this,"Please fill all the fields","Fill Details", JOptionPane.INFORMATION_MESSAGE);
	else
	{
		try
		{
			String strup="select * from  vehicletype where TypeID='"+ID+"' ";
			psSe=con.prepareStatement(strup);
			rsSe=psSe.executeQuery();
			if(rsSe.next()==true)
			{
				JOptionPane.showMessageDialog(this,"This ID is already assigned");	
				txtTypeID.setText("");
				txtTypeName.setText("");
			}
			
			
			
			else {
			String strinsert="insert into vehicletype values(?,?)";
			psAdd=con.prepareStatement(strinsert);
			psAdd.setString(1, ID);
			psAdd.setString(2, Name);
			int row=psAdd.executeUpdate();
			if(row>0)
			{
				JOptionPane.showMessageDialog(this,"Data Added Successfully","Congrats",JOptionPane.INFORMATION_MESSAGE);
				txtTypeID.setText("");
				txtTypeName.setText("");
			}	
			
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
}
