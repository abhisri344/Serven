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
import javax.swing.JLabel;
public class ViewReceptionistService extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection con=null;
	private JTable table;
	private String column[]= {"Service ID","Service Type","Service Cost"};
	private String tableData[][]=null;
	private PreparedStatement pscount,psdata;
	private ResultSet rscount, rsdata;
	private JLabel label;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewReceptionistService frame = new ViewReceptionistService();
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
	public ViewReceptionistService() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		con=CrudOperation.createConnection();
		createGui();
	}
	public void fillTwoD()
	{
		try {
			String strcount="Select count(*) from service_task";
			pscount=con.prepareStatement(strcount);
			
			rscount=pscount.executeQuery();
			rscount.next();
			int rowcnt=rscount.getInt(1);
			//System.out.println(rowcnt);
			if(rowcnt>0)
			{
				tableData=new String[rowcnt][3];
				String strsql="Select * from service_task";
				psdata=con.prepareStatement(strsql);
				rsdata=psdata.executeQuery();
				int row=0;
				while(rsdata.next())
				{
					String id=rsdata.getString(1);
					String name=rsdata.getString(2);
					String cost=rsdata.getString(3);
					tableData[row][0]=id;
					tableData[row][1]=name;
					tableData[row][2]=cost;
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
		 setTitle("View Service Type and Cost");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 644, 440);
			contentPane = new JPanel();
			contentPane.setBackground(Color.DARK_GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			setLocationRelativeTo(null);
			contentPane.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setForeground(Color.WHITE);
			scrollPane.setBackground(Color.RED);
			scrollPane.setBounds(10, 11, 608, 353);
			contentPane.add(scrollPane);
			table = new JTable();
			JTableHeader header=table.getTableHeader();
			header.setBackground(Color.BLACK);
			header.setForeground(Color.WHITE);
			header.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,20));
			
			table.setFillsViewportHeight(true);
			table.setBackground(Color.WHITE);
			table.setForeground(Color.BLACK);
			fillTwoD();
			table.setModel(new DefaultTableModel(
					tableData,column
					
							));

			
			
			scrollPane.setViewportView(table);
			
			label = new JLabel("A.S.S.U.R'E.");
			label.setForeground(Color.WHITE);
			label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD, label.getFont().getSize() + 2f));
			label.setBounds(535, 363, 83, 27);
			contentPane.add(label);
			//fillTwoD();
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
		
	}
}
