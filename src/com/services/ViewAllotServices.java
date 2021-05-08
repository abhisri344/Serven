package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
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
public class ViewAllotServices extends JFrame implements ActionListener {
	

	private JPanel contentPane;
	private Connection con;
	private JTable table;
	private String column[]= {"Allot Id","Employee Id","Vehicle Number","Date","Service Status"};
	private String tableData[][]=null;
	private PreparedStatement pscount,psdata, pse;
	private ResultSet rscount, rsdata, rse;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllotServices frame = new ViewAllotServices();
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
	public ViewAllotServices() {
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
				tableData=new String[rowcnt][5];
				
				
				String strsql1="select AllotService.AllotId, AllotService.Date, AllotService.EmployeeId ,AllotService.VehicleNumber,"
						+ " ServiceRequest.ServiceStatus from AllotService join ServiceRequest"
						+ " where AllotService.VehicleNumber=ServiceRequest.VehicleNumber order by AllotService.Date desc";
				pse=con.prepareStatement(strsql1);
				rse=pse.executeQuery();
	
				
				
				
				int row=0;
				while(rse.next())
				{
					String allotid=rse.getString("AllotService.AllotId");
					String employeeid=rse.getString("AllotService.EmployeeId");
					String vehiclenum=rse.getString("AllotService.VehicleNumber");
					Date date=rse.getDate("AllotService.Date");
                    String serv=rse.getString("ServiceRequest.ServiceStatus");				
					
					tableData[row][0]=allotid;
					tableData[row][1]=employeeid;
					tableData[row][2]=vehiclenum;
					tableData[row][3]=String.valueOf(date);
					tableData[row][4]=serv;
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
		 setTitle("View Employee");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 840, 440);
			contentPane = new JPanel();
			contentPane.setBackground(Color.DARK_GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			setLocationRelativeTo(null);
			contentPane.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setForeground(Color.BLACK);
			scrollPane.setBackground(Color.RED);
			scrollPane.setBounds(10, 11, 818, 379);
			contentPane.add(scrollPane);
			table = new JTable();
			JTableHeader header=table.getTableHeader();
			header.setBackground(Color.BLACK);
			header.setForeground(Color.WHITE);
			header.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,20));
			
			table.setFillsViewportHeight(true);
			table.setBackground(Color.BLACK);
			table.setForeground(Color.BLUE);
			fillTwoD();
			table.setModel(new DefaultTableModel(tableData,column));
			scrollPane.setViewportView(table);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
		
	}
}
