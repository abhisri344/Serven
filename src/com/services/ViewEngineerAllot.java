package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.dbutils.CrudOperation;
import javax.swing.JLabel;

public class ViewEngineerAllot extends JFrame implements ActionListener {
	public void run() {
	}

	private JPanel contentPane;
	private Connection con;
	private JTable table;
	private String column[]= {"Allot Id","Employee Id","Vehicle Number","Date"};
	private String tableData[][]=null;
	private PreparedStatement pscount,psdata;
	private ResultSet rscount, rsdata;
	private String id=OthersLogin.userId;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEngineerAllot frame = new ViewEngineerAllot();
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
	public ViewEngineerAllot() 
	{

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=CrudOperation.createConnection();
		
		createGui();	
	}

	public void fillTwoD()
	{
		try {
			String strcount="Select count(*) from AllotService";
			pscount=con.prepareStatement(strcount);
			
			rscount=pscount.executeQuery();
			rscount.next();
			int rowcnt=rscount.getInt(1);
			//System.out.println(rowcnt);
			if(rowcnt>0)
			{
				tableData=new String[rowcnt][4];
				String strsql="Select * from AllotService where EmployeeId=? order by Date desc ";
				psdata=con.prepareStatement(strsql);
				psdata.setString(1, id);
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
				JOptionPane.showMessageDialog(this,"error");
			}
		}
		catch(SQLException se) {
			System.out.println(se);
		}	
	 }
	
	 public void createGui()
	 {
		 setTitle("View Engineer Allotted Vehicles");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 840, 440);
			contentPane = new JPanel();
			contentPane.setBackground(Color.DARK_GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setForeground(Color.BLACK);
			scrollPane.setBackground(Color.RED);
			scrollPane.setBounds(10, 11, 804, 353);
			contentPane.add(scrollPane);
			table = new JTable();
			JTableHeader header=table.getTableHeader();
			header.setBackground(Color.WHITE);
			header.setForeground(Color.BLACK);
			header.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,20));
			
			table.setFillsViewportHeight(true);
			table.setBackground(Color.WHITE);
			table.setForeground(Color.BLACK);
			fillTwoD();
			table.setModel(new DefaultTableModel(tableData,column));
			scrollPane.setViewportView(table);
			
			label = new JLabel("A.S.S.U.R'E.");
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Tahoma", Font.BOLD, 13));
			label.setBounds(738, 375, 76, 14);
			contentPane.add(label);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
