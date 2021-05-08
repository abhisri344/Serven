package com.services;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.dbutils.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ViewAdminServiced extends JFrame implements ActionListener {
	

	private JPanel contentPane;
	private Connection con;
	private JTable table;
	private String column[]= {"Allot Id","Employee Id","Vehicle Number","Date"};
	private String tableData[][]=null;
	private PreparedStatement pscount,psdata;
	private ResultSet rscount, rsdata;
	private JTextField txtempid;
	private String empid;
	private JScrollPane scrollPane;
	private JLabel label;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAdminServiced frame = new ViewAdminServiced();
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
	public ViewAdminServiced() 
	{
		createGui();
		con=CrudOperation.createConnection();
	}
	
	public void fillTwoD()
	{
		try {
			String strcount="Select count(*) from AllotService where EmployeeId=?";
			pscount=con.prepareStatement(strcount);
			pscount.setString(1, empid);
			
			rscount=pscount.executeQuery();
			rscount.next();
			int rowcnt=rscount.getInt(1);
			//System.out.println(rowcnt);
			if(rowcnt>0)
			{
				tableData=new String[rowcnt][4];
				String strsql="Select * from AllotService where EmployeeId=? order by Date desc ";
				psdata=con.prepareStatement(strsql);
				psdata.setString(1, empid);
				rsdata=psdata.executeQuery();
				int row=0;
				while(rsdata.next())
				{
					String allotid=rsdata.getString(1);
					String employeeid=rsdata.getString(2);
					String vehiclenum=rsdata.getString(3);
					Date date=rsdata.getDate(4);
					tableData[row][0]=allotid;
					tableData[row][1]=employeeid;
					tableData[row][2]=vehiclenum;
					tableData[row][3]=String.valueOf(date);
					row++;		
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No Services For this Employee","Wrong Input",JOptionPane.ERROR_MESSAGE);
				
			}
		}
		catch(SQLException se) {
			System.out.println(se);
		}	
	 }
	
	 public void createGui()
	 {
		 setTitle("Service Done By Engineer");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 840, 470);
			contentPane = new JPanel();
			contentPane.setBackground(Color.DARK_GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			setLocationRelativeTo(null);
			contentPane.setLayout(null);
			
			scrollPane = new JScrollPane();
			scrollPane.setForeground(Color.BLACK);
			scrollPane.setBackground(Color.RED);
			scrollPane.setBounds(12, 53, 802, 339);
			contentPane.add(scrollPane);
			table = new JTable();
			JTableHeader header=table.getTableHeader();
			header.setBackground(Color.WHITE);
			header.setForeground(Color.BLACK);
			header.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,20));
			
			table.setFillsViewportHeight(true);
			table.setBackground(Color.WHITE);
			table.setForeground(Color.BLACK);
			table.setModel(new DefaultTableModel(tableData,column));
			scrollPane.setViewportView(table);
			
			JLabel lblEmployeeId = new JLabel("Employee Id");
			lblEmployeeId.setForeground(Color.WHITE);
			lblEmployeeId.setBounds(89, 12, 171, 15);
			contentPane.add(lblEmployeeId);
			
			txtempid = new JTextField();
			txtempid.setBounds(358, 10, 124, 19);
			contentPane.add(txtempid);
			txtempid.setColumns(10);
			
			JButton btnView = new JButton("View");
			btnView.setBounds(649, 7, 114, 25);
			contentPane.add(btnView);
			
			label = new JLabel("A.S.S.U.R'E.");
			label.setForeground(Color.WHITE);
			label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD, label.getFont().getSize() + 2f));
			label.setBounds(731, 393, 83, 27);
			contentPane.add(label);
			btnView.addActionListener(this);

			
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		empid=txtempid.getText();
		if(empid.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please provide the details ","Data Required",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			
			fillTwoD();
			table.setModel(new DefaultTableModel(tableData,column));
			scrollPane.setViewportView(table);
		
			
			
			
		}
	}
}
