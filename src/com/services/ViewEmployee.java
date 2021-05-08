package com.services;
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

import javax.swing.JButton;
import javax.swing.JLabel;
public class ViewEmployee extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection con=null;
	private JTable table;
	private String column[]= {"Employee ID","Employee Name","Email","Phone Number","Gender"};
	private String tableData[][]=null;
	private PreparedStatement pscount,psdata;
	private ResultSet rscount, rsdata;
	private JLabel lblAssure;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEmployee frame = new ViewEmployee();
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
	public ViewEmployee() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=CrudOperation.createConnection();
		createGui();
	}
	public void fillTwoD()
	{
		try {
			String strcount="Select count(*) from employee";
			pscount=con.prepareStatement(strcount);
			
			rscount=pscount.executeQuery();
			rscount.next();
			int rowcnt=rscount.getInt(1);
			//System.out.println(rowcnt);
			if(rowcnt>0)
			{
				tableData=new String[rowcnt][5];
				String strsql="Select * from employee";
				psdata=con.prepareStatement(strsql);
				rsdata=psdata.executeQuery();
				int row=0;
				while(rsdata.next())
				{
					String id=rsdata.getString(1);
					String name=rsdata.getString(2);
					String email=rsdata.getString(3);
					String phone=rsdata.getString(4);
					String gen=rsdata.getString(5);

					tableData[row][0]=id;
					tableData[row][1]=name;
					tableData[row][2]=email;
					tableData[row][3]=phone;
					tableData[row][4]=gen;
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
		 setTitle("View Employee\r\ns");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 739, 477);
			contentPane = new JPanel();
			contentPane.setBackground(Color.DARK_GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			setLocationRelativeTo(null);
			contentPane.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setForeground(Color.BLACK);
			scrollPane.setBackground(Color.RED);
			scrollPane.setBounds(10, 11, 703, 384);
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
			table.setModel(new DefaultTableModel(
					tableData,column
					
							));

			
			
			scrollPane.setViewportView(table);
			
			lblAssure = new JLabel("A.S.S.U.R'E.");
			lblAssure.setForeground(Color.WHITE);
			lblAssure.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblAssure.setBounds(637, 408, 76, 14);
			contentPane.add(lblAssure);
			//fillTwoD();
	 }
	
		

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
