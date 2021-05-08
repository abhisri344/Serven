package com.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.dbutils.CrudOperation;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;    
import java.awt.*;    
import java.awt.event.*; 
import javax.swing.event.*; 
import java.awt.Font;
import java.awt.Color;


public class Receptionist extends JFrame implements ActionListener, ListSelectionListener {

	private JPanel contentPane, panel;
	private JTextField textField;
	private JTextField txtmeterreadingadd;
	private JTextField txtrequestidadd;
	private Connection con=null;
	private String column[]= {"Vehicle Number"};
	private String tableData[][]=null;
	private PreparedStatement pscount,psdata;
	private ResultSet rscount, rsdata;
	private JTable table;

	private JTextField txtcustomeridadd;
	private JTextField txttypeidadd;
	private JTextField txtvehiclenumadd;
	private JTextField txttotalamountadd;
	private JTextField txtrequeststatusadd;
	private JTextField txtservicestatusadd;
	private JTextField txtdeliverystatusadd;
	private JDateChooser txtindateadd;
	private JDateChooser txtduedateadd;
	private JTextField txtreceptionistidadd;
    private JLabel txtvehiclenumdelete;
	private JLabel txtcustomeriddelete;
	private JButton btnDelete;
	private JTextField txtmeterreadingupd;
	private JTextField txtrequestidupd;
	private JTextField txtcustomeridupd;
	private JTextField txttypeidupd;
	private JTextField txtvehiclenumupd;
	private JTextField txtserviceidupd;
	private JTextField txttotalamountupd;
	private JDateChooser txtindateupd;
	private JDateChooser txtduedateupd;
	private JTextField txtreceptionistidupd;
	private JList list;
	private ButtonGroup btg, btg1;
	
	
	
	private JRadioButton notassign;
	private JRadioButton assign;
	private JRadioButton pick;
	private JRadioButton drop;
	private String requeststatus;
	private String deliverystatus;
	
	
	private JButton btnUpdate;
	private Connection conn;
	private PreparedStatement ps;
	private PreparedStatement psemployee, pslist;
    private ResultSet rs,rse, rslist;
    private JTextField txtallotid;
    private JTextField txtallotempid;
    private JTextField txtallotVehiclenum;
    private JDateChooser txtallotdate;
    private JTextField txtiddelete;
    private JTextField txtcustomeradddetails;
    private JTextField txtnamedetails;
    private JTextField txtemaildetails;
    private JTextField txtphonedetails;
    private JTextField txtservicestatusupd;
    private JTextField txtserviceidadd;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionist frame = new Receptionist();
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
	public Receptionist() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/com/images/MainIcon.png")));
		setTitle("Receptionist Control Page");
		conn=CrudOperation.createConnection();
		con=CrudOperation.createConnection();
		createGui();
		jListDemo();
	}
public void createGui()
{
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 918, 507);
	
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	
	JMenu mnView = new JMenu("View");
	menuBar.add(mnView);
	
	JMenuItem miViewService = new JMenuItem("View Service Price");
	mnView.add(miViewService);
	miViewService.addActionListener(this);
	
	JMenuItem miViewVehicleType = new JMenuItem("View Vehicle Types");
	mnView.add(miViewVehicleType);
	
	JMenuItem miReciept = new JMenuItem("Customer Reciept");
	mnView.add(miReciept);
	
	JMenuItem miViewServEng = new JMenuItem("View Service By Engineer");
	mnView.add(miViewServEng);
	
	JMenuItem micustomer = new JMenuItem("View Customer Details");
	mnView.add(micustomer);
	
	JMenuItem misereng = new JMenuItem("View Service Engineer");
	mnView.add(misereng);
	micustomer.addActionListener(this);
	misereng.addActionListener(this);
	
	miViewServEng.addActionListener(this);
	miReciept.addActionListener(this);
	
	
	
	miViewVehicleType.addActionListener(this);
	JMenu mnMore = new JMenu("More");
	menuBar.add(mnMore);
	
	JMenuItem miAbout = new JMenuItem("About System");
	miAbout.addActionListener(this);
	mnMore.add(miAbout);
	
	JMenuItem miContact = new JMenuItem("Contact Developing Team");
	miContact.addActionListener(this);
	mnMore.add(miContact);
	
	JMenuItem miCustomerFeedback = new JMenuItem("Customer Feedback");
	mnMore.add(miCustomerFeedback);
	miCustomerFeedback.addActionListener(this);
	
	JMenu mnAccount = new JMenu(" Account");
	menuBar.add(mnAccount);
	
	JMenuItem miChangePass = new JMenuItem("Change Password");
	mnAccount.add(miChangePass);
	miChangePass.addActionListener(this);
	
	
	JMenuItem miLogout = new JMenuItem("Logout");
	mnAccount.add(miLogout);
	miLogout.addActionListener(this);
	
	
	
	
	
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	setLocationRelativeTo(null);
	contentPane.setLayout(null);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setBounds(12, 12, 894, 434);
	contentPane.add(tabbedPane);
	
	panel = new JPanel();
	tabbedPane.addTab("Add Service Request", null, panel, null);
	panel.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Request ID");
	lblNewLabel.setBounds(24, 21, 127, 15);
	panel.add(lblNewLabel);
	
	JLabel lblCustomerId = new JLabel("Customer ID");
	lblCustomerId.setBounds(24, 48, 106, 15);
	panel.add(lblCustomerId);
	
	JLabel lblVehicleNumber = new JLabel("Vehicle Number");
	lblVehicleNumber.setBounds(24, 75, 141, 15);
	panel.add(lblVehicleNumber);
	
	JLabel lblServiceIds = new JLabel("Service IDs");
	lblServiceIds.setBounds(24, 129, 127, 15);
	panel.add(lblServiceIds);
	
	JLabel lblMeterReading = new JLabel("Meter Reading");
	lblMeterReading.setBounds(24, 156, 127, 15);
	panel.add(lblMeterReading);
	
	JLabel lblIndate = new JLabel("InDate");
	lblIndate.setBounds(24, 183, 66, 15);
	panel.add(lblIndate);
	
	JLabel lblDueDate = new JLabel("Due date");
	lblDueDate.setBounds(24, 210, 66, 15);
	panel.add(lblDueDate);
	
	JLabel lblTotalAmount = new JLabel("Total amount");
	lblTotalAmount.setBounds(24, 237, 106, 15);
	panel.add(lblTotalAmount);
	
	JLabel lblRequestStatus = new JLabel("Request Status");
	lblRequestStatus.setBounds(24, 264, 127, 15);
	panel.add(lblRequestStatus);
	
	JLabel lblServiceStatus = new JLabel("Service Status");
	lblServiceStatus.setBounds(24, 291, 127, 15);
	panel.add(lblServiceStatus);
	
	JLabel lblDeliveryStatus = new JLabel("Delivery Status");
	lblDeliveryStatus.setBounds(24, 318, 127, 15);
	panel.add(lblDeliveryStatus);
	
	JLabel lblReceptionistId = new JLabel("Receptionist ID");
	lblReceptionistId.setBounds(24, 345, 154, 15);
	panel.add(lblReceptionistId);
	
	JButton btnSubmitadd = new JButton("Submit");
	btnSubmitadd.setBounds(447, 359, 114, 25);
	panel.add(btnSubmitadd);
	btnSubmitadd.addActionListener(this);
	
	txtmeterreadingadd = new JTextField();
	txtmeterreadingadd.setBounds(309, 154, 124, 19);
	panel.add(txtmeterreadingadd);
	txtmeterreadingadd.setColumns(10);
	
	txtrequestidadd = new JTextField();
	txtrequestidadd.setBounds(309, 19, 124, 19);
	panel.add(txtrequestidadd);
	txtrequestidadd.setColumns(10);
	
	txtcustomeridadd = new JTextField();
	txtcustomeridadd.setBounds(309, 46, 124, 19);
	panel.add(txtcustomeridadd);
	txtcustomeridadd.setColumns(10);
	
	txttypeidadd = new JTextField();
	txttypeidadd.setBounds(309, 100, 124, 19);
	panel.add(txttypeidadd);
	txttypeidadd.setColumns(10);
	
	txtvehiclenumadd = new JTextField();
	txtvehiclenumadd.setBounds(309, 73, 124, 19);
	panel.add(txtvehiclenumadd);
	txtvehiclenumadd.setColumns(10);
	
	JLabel lblTypeId = new JLabel("Type ID");
	lblTypeId.setBounds(24, 102, 66, 15);
	panel.add(lblTypeId);
	
	txttotalamountadd = new JTextField();
	txttotalamountadd.setEditable(false);
	txttotalamountadd.setBounds(309, 235, 124, 19);
	panel.add(txttotalamountadd);
	txttotalamountadd.setColumns(10);
	
	txtrequeststatusadd = new JTextField();
	txtrequeststatusadd.setEditable(false);
	txtrequeststatusadd.setText("Not Assigned");
	txtrequeststatusadd.setBounds(309, 262, 124, 19);
	panel.add(txtrequeststatusadd);
	txtrequeststatusadd.setColumns(10);
	
	txtservicestatusadd = new JTextField();
	txtservicestatusadd.setEditable(false);
	txtservicestatusadd.setText("Not Done");
	txtservicestatusadd.setBounds(309, 289, 124, 19);
	panel.add(txtservicestatusadd);
	txtservicestatusadd.setColumns(10);
	
	txtdeliverystatusadd = new JTextField();
	txtdeliverystatusadd.setEditable(false);
	txtdeliverystatusadd.setText("Pick");
	txtdeliverystatusadd.setBounds(309, 316, 124, 19);
	panel.add(txtdeliverystatusadd);
	txtdeliverystatusadd.setColumns(10);
	
	txtreceptionistidadd = new JTextField();
	txtreceptionistidadd.setEditable(false);
	txtreceptionistidadd.setBounds(309, 343, 124, 19);
	panel.add(txtreceptionistidadd);
	txtreceptionistidadd.setColumns(10);
	
	txtindateadd = new JDateChooser();
	txtindateadd.setBounds(309, 179, 127, 19);
	panel.add(txtindateadd);
	txtindateadd.setMinSelectableDate(new Date());
	txtindateadd.setDateFormatString("yyyy-MM-dd");
	
	txtduedateadd = new JDateChooser();
	txtduedateadd.setBounds(309, 206, 127, 19);
	panel.add(txtduedateadd);
	txtduedateadd.setMinSelectableDate(new Date());
	txtduedateadd.setDateFormatString("yyyy-MM-dd");
	
	JLabel label_5 = new JLabel("A.S.S.U.R'E.");
	label_5.setForeground(Color.BLACK);
	label_5.setFont(label_5.getFont().deriveFont(label_5.getFont().getStyle() | Font.BOLD, label_5.getFont().getSize() + 2f));
	label_5.setBounds(757, 368, 83, 27);
	panel.add(label_5);
	
	
	
	JPanel panel_1 = new JPanel();
	tabbedPane.addTab("Allot Services", null, panel_1, null);
	panel_1.setLayout(null);
	
	JLabel lblAllotId = new JLabel("Allot ID");
	lblAllotId.setBounds(41, 31, 66, 15);
	panel_1.add(lblAllotId);
	
	JLabel lblEmployeeId = new JLabel("Employee ID");
	lblEmployeeId.setBounds(41, 88, 121, 15);
	panel_1.add(lblEmployeeId);
	
	JLabel lblVehicleNumber_1 = new JLabel("Vehicle Number");
	lblVehicleNumber_1.setBounds(41, 149, 121, 15);
	panel_1.add(lblVehicleNumber_1);
	
	JLabel lblDate = new JLabel("Date");
	lblDate.setBounds(41, 205, 66, 15);
	panel_1.add(lblDate);
	
	JButton btnAllot = new JButton("Allot");
	btnAllot.setBounds(274, 273, 114, 25);
	panel_1.add(btnAllot);
	btnAllot.addActionListener(this);
	
	txtallotid = new JTextField();
	txtallotid.setBounds(366, 29, 124, 19);
	panel_1.add(txtallotid);
	txtallotid.setColumns(10);
	
	txtallotempid = new JTextField();
	txtallotempid.setBounds(366, 86, 124, 19);
	panel_1.add(txtallotempid);
	txtallotempid.setColumns(10);
	
	txtallotVehiclenum = new JTextField();
	txtallotVehiclenum.setBounds(366, 147, 124, 19);
	panel_1.add(txtallotVehiclenum);
	txtallotVehiclenum.setColumns(10);
	
	txtallotdate = new JDateChooser();
	txtallotdate.setBounds(366, 201, 124, 19);
	txtallotdate.setMinSelectableDate(new Date());
	panel_1.add(txtallotdate);
	
	JLabel label_4 = new JLabel("A.S.S.U.R'E.");
	label_4.setForeground(Color.BLACK);
	label_4.setFont(label_4.getFont().deriveFont(label_4.getFont().getStyle() | Font.BOLD, label_4.getFont().getSize() + 2f));
	label_4.setBounds(757, 368, 83, 27);
	panel_1.add(label_4);
	
	
	
	





JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(628, 40, 162, 176);
	panel_1.add(scrollPane);
	
	table = new JTable();
	scrollPane.setViewportView(table);
	table.setFillsViewportHeight(true);
	table.setBackground(Color.WHITE);
	table.setForeground(Color.BLACK);
	fillTwoD();
	table.setModel(new DefaultTableModel(
			tableData,column
			
					));
	
	JPanel panel_3 = new JPanel();
	tabbedPane.addTab("Search / Update Service Request", null, panel_3, null);
	panel_3.setLayout(null);
	
	JLabel lbl1NewLabel = new JLabel("Request ID");
	lbl1NewLabel.setBounds(24, 21, 127, 15);
	panel_3.add(lbl1NewLabel);
	
	JLabel lbl1CustomerId = new JLabel("Customer ID");
	lbl1CustomerId.setBounds(24, 48, 106, 15);
	panel_3.add(lbl1CustomerId);
	
	JLabel lbl1VehicleNumber = new JLabel("Vehicle Number");
	lbl1VehicleNumber.setBounds(24, 75, 141, 15);
	panel_3.add(lbl1VehicleNumber);
	
	JLabel lbl1ServiceIds = new JLabel("Service IDs");
	lbl1ServiceIds.setBounds(24, 129, 127, 15);
	panel_3.add(lbl1ServiceIds);
	
	JLabel lbl1MeterReading = new JLabel("Meter Reading");
	lbl1MeterReading.setBounds(24, 156, 127, 15);
	panel_3.add(lbl1MeterReading);
	
	JLabel lbl1Indate = new JLabel("InDate");
	lbl1Indate.setBounds(24, 183, 66, 15);
	panel_3.add(lbl1Indate);
	
	JLabel lbl1DueDate = new JLabel("Due date");
	lbl1DueDate.setBounds(24, 210, 66, 15);
	panel_3.add(lbl1DueDate);
	
	JLabel lbl1TotalAmount = new JLabel("Total amount");
	lbl1TotalAmount.setBounds(24, 237, 106, 15);
	panel_3.add(lbl1TotalAmount);
	
	JLabel lbl1RequestStatus = new JLabel("Request Status *");
	lbl1RequestStatus.setBounds(24, 264, 127, 15);
	panel_3.add(lbl1RequestStatus);
	
	JLabel lbl1ServiceStatus = new JLabel("Service Status *");
	lbl1ServiceStatus.setBounds(24, 291, 127, 15);
	panel_3.add(lbl1ServiceStatus);
	
	JLabel lbl1DeliveryStatus = new JLabel("Delivery Status *");
	lbl1DeliveryStatus.setBounds(24, 318, 127, 15);
	panel_3.add(lbl1DeliveryStatus);
	
	JLabel lbl1ReceptionistId = new JLabel("Receptionist ID");
	lbl1ReceptionistId.setBounds(24, 345, 154, 15);
	panel_3.add(lbl1ReceptionistId);
	
    btnUpdate = new JButton("Update");
    btnUpdate.setEnabled(false);
	btnUpdate.setBounds(552, 173, 114, 25);
	panel_3.add(btnUpdate);
	btnUpdate.addActionListener(this);
	
	
	txtmeterreadingupd = new JTextField();
	txtmeterreadingupd.setEditable(false);
	txtmeterreadingupd.setBounds(309, 154, 124, 19);
	panel_3.add(txtmeterreadingupd);
	txtmeterreadingupd.setColumns(10);
	
	txtrequestidupd = new JTextField();
	txtrequestidupd.setBounds(309, 19, 124, 19);
	panel_3.add(txtrequestidupd);
	txtrequestidupd.setColumns(10);
	
	txtcustomeridupd = new JTextField();
	txtcustomeridupd.setEditable(false);
	txtcustomeridupd.setBounds(309, 46, 124, 19);
	panel_3.add(txtcustomeridupd);
	txtcustomeridupd.setColumns(10);
	
	txttypeidupd = new JTextField();
	txttypeidupd.setEditable(false);
	txttypeidupd.setBounds(309, 100, 124, 19);
	panel_3.add(txttypeidupd);
	txttypeidupd.setColumns(10);
	
	txtvehiclenumupd = new JTextField();
	txtvehiclenumupd.setEditable(false);
	txtvehiclenumupd.setBounds(309, 73, 124, 19);
	panel_3.add(txtvehiclenumupd);
	txtvehiclenumupd.setColumns(10);
	
	txtserviceidupd = new JTextField();
	txtserviceidupd.setEditable(false);
	txtserviceidupd.setBounds(309, 127, 568, 19);
	panel_3.add(txtserviceidupd);
	txtserviceidupd.setColumns(10);
	
	JLabel lbl1TypeId = new JLabel("Type ID");
	lbl1TypeId.setBounds(24, 102, 66, 15);
	panel_3.add(lbl1TypeId);
	
	txttotalamountupd = new JTextField();
	txttotalamountupd.setEditable(false);
	txttotalamountupd.setBounds(309, 235, 124, 19);
	panel_3.add(txttotalamountupd);
	txttotalamountupd.setColumns(10);
	
	txtreceptionistidupd = new JTextField();
	txtreceptionistidupd.setEditable(false);
	txtreceptionistidupd.setBounds(309, 343, 124, 19);
	panel_3.add(txtreceptionistidupd);
	txtreceptionistidupd.setColumns(10);
	
	txtindateupd = new JDateChooser();
	txtindateupd.setBounds(309, 179, 127, 19);
	panel_3.add(txtindateupd);
	txtindateupd.setMinSelectableDate(new Date());
	txtindateupd.setDateFormatString("yyyy-MM-dd");
	
	txtduedateupd = new JDateChooser();
	txtduedateupd.setBounds(309, 206, 127, 19);
	panel_3.add(txtduedateupd);
	txtduedateupd.setMinSelectableDate(new Date());
	txtduedateupd.setDateFormatString("yyyy-MM-dd");
	
	btg=new ButtonGroup();
	btg1=new ButtonGroup();
	
	
	notassign = new JRadioButton("Not Assigned");
	notassign.setBounds(309, 260, 144, 23);
	panel_3.add(notassign);
	btg.add(notassign);
	
	assign = new JRadioButton("Assigned");
	assign.setBounds(458, 260, 144, 23);
	panel_3.add(assign);
	btg.add(assign);
	
	pick = new JRadioButton("Pick");
	pick.setBounds(309, 314, 144, 23);
	panel_3.add(pick);
	btg1.add(pick);
	
	drop = new JRadioButton("Drop");
	drop.setBounds(458, 314, 144, 23);
	panel_3.add(drop);
	btg1.add(drop);
	
	
	JButton btnGo = new JButton("GO");
	btnGo.setBounds(458, 19, 83, 17);
	panel_3.add(btnGo);
	
	JLabel label_3 = new JLabel("A.S.S.U.R'E.");
	label_3.setForeground(Color.BLACK);
	label_3.setFont(label_3.getFont().deriveFont(label_3.getFont().getStyle() | Font.BOLD, label_3.getFont().getSize() + 2f));
	label_3.setBounds(757, 368, 83, 27);
	panel_3.add(label_3);
	
	JLabel lblNoteOnly = new JLabel("NOTE : Only Fields Marked With * can be Changed");
	lblNoteOnly.setBounds(49, 381, 317, 14);
	panel_3.add(lblNoteOnly);
	
	txtservicestatusupd = new JTextField();
	txtservicestatusupd.setEditable(false);
	txtservicestatusupd.setBounds(309, 289, 124, 19);
	panel_3.add(txtservicestatusupd);
	txtservicestatusupd.setColumns(10);
	btnGo.addActionListener(this);
	
	JPanel panel_2 = new JPanel();
	tabbedPane.addTab("Delete Service Request", null, panel_2, null);
	panel_2.setLayout(null);
	
	JLabel lblRequestId = new JLabel("Request ID");
	lblRequestId.setBounds(99, 50, 102, 30);
	panel_2.add(lblRequestId);
	
	txtiddelete = new JTextField();
	txtiddelete.setBounds(404, 56, 124, 19);
	panel_2.add(txtiddelete);
	txtiddelete.setColumns(10);
	
	btnDelete = new JButton("Delete");
	btnDelete.setBounds(429, 222, 114, 25);
	panel_2.add(btnDelete);
	btnDelete.addActionListener(this);
	btnDelete.setVisible(false);
	
	JLabel lblVehicleNumber_2 = new JLabel("Vehicle Number");
	lblVehicleNumber_2.setBounds(99, 106, 135, 15);
	panel_2.add(lblVehicleNumber_2);
	
	JLabel lblCustomerid = new JLabel("CustomerId");
	lblCustomerid.setBounds(99, 145, 135, 15);
	panel_2.add(lblCustomerid);
	
	txtvehiclenumdelete = new JLabel("");
	txtvehiclenumdelete.setBounds(404, 106, 124, 15);
	panel_2.add(txtvehiclenumdelete);
	
	txtcustomeriddelete = new JLabel("");
	txtcustomeriddelete.setBounds(404, 145, 124, 15);
	panel_2.add(txtcustomeriddelete);
	
	JButton btnFind = new JButton("FIND");
	btnFind.setBounds(138, 222, 114, 25);
	panel_2.add(btnFind);
	
	JLabel label = new JLabel("");
	label.setIcon(new ImageIcon(Receptionist.class.getResource("/com/images/trash.jpg")));
	label.setBounds(646, 50, 154, 135);
	panel_2.add(label);
	
	JLabel label_1 = new JLabel("A.S.S.U.R'E.");
	label_1.setForeground(Color.BLACK);
	label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD, label_1.getFont().getSize() + 2f));
	label_1.setBounds(757, 368, 83, 27);
	panel_2.add(label_1);
	
	JPanel panel_4 = new JPanel();
	tabbedPane.addTab("Add Customer Details", null, panel_4, null);
	panel_4.setLayout(null);
	
	JLabel lblCustomerId_1 = new JLabel("Customer Id");
	lblCustomerId_1.setBounds(68, 46, 82, 14);
	panel_4.add(lblCustomerId_1);
	
	JLabel lblEmployeeid = new JLabel("Name");
	lblEmployeeid.setBounds(68, 88, 94, 14);
	panel_4.add(lblEmployeeid);
	
	JLabel lblEmail = new JLabel("Email");
	lblEmail.setBounds(68, 130, 48, 14);
	panel_4.add(lblEmail);
	
	JLabel lblPhone = new JLabel("Phone");
	lblPhone.setBounds(68, 174, 48, 14);
	panel_4.add(lblPhone);
	
	txtcustomeradddetails = new JTextField();
	txtcustomeradddetails.setBounds(300, 43, 96, 20);
	panel_4.add(txtcustomeradddetails);
	txtcustomeradddetails.setColumns(10);
	
	txtnamedetails = new JTextField();
	txtnamedetails.setBounds(300, 85, 96, 20);
	panel_4.add(txtnamedetails);
	txtnamedetails.setColumns(10);
	
	txtemaildetails = new JTextField();
	txtemaildetails.setBounds(300, 127, 96, 20);
	panel_4.add(txtemaildetails);
	txtemaildetails.setColumns(10);
	
	txtphonedetails = new JTextField();
	txtphonedetails.setBounds(300, 171, 96, 20);
	panel_4.add(txtphonedetails);
	txtphonedetails.setColumns(10);
	
	JButton btnAddDetails = new JButton("Add Details");
	btnAddDetails.setBounds(227, 248, 139, 23);
	panel_4.add(btnAddDetails);
	
	JLabel label_2 = new JLabel("A.S.S.U.R'E.");
	label_2.setForeground(Color.BLACK);
	label_2.setFont(label_2.getFont().deriveFont(label_2.getFont().getStyle() | Font.BOLD, label_2.getFont().getSize() + 2f));
	label_2.setBounds(757, 368, 83, 27);
	panel_4.add(label_2);
	btnAddDetails.addActionListener(this);
	btnFind.addActionListener(this);
	
	
	txtreceptionistidadd.setText(OthersLogin.userId);
	
	}
    

public void Add()
{	
	
	
	String requestid=txtrequestidadd.getText();
	String customerid=txtcustomeridadd.getText();
	String vehiclenum=txtvehiclenumadd.getText();
	String typeid=txttypeidadd.getText();
	String serviceid=txtserviceidadd.getText();
	String meterreading=txtmeterreadingadd.getText();
	java.util.Date indate1 = txtindateadd.getDate();
	java.util.Date duedate1=txtduedateadd.getDate();
	String totalamount=txttotalamountadd.getText();
	String requeststatus=txtrequeststatusadd.getText();
	String servicestatus=txtservicestatusadd.getText();
	String deliverystatus=txtdeliverystatusadd.getText();
    String receptionistid=txtreceptionistidadd.getText();
	
	
	
	
	if(requestid.isEmpty()||customerid.isEmpty()||vehiclenum.isEmpty()||typeid.isEmpty()||meterreading.isEmpty()||serviceid.isEmpty()||indate1==null||
			duedate1==null||totalamount.isEmpty()||requeststatus.isEmpty()||servicestatus.isEmpty()||deliverystatus.isEmpty()||receptionistid.isEmpty())
	{
		
	JOptionPane.showMessageDialog(this,"Please provide all the informations","Data Required",JOptionPane.WARNING_MESSAGE);	
		
		}

	    		
 	else
 	{
		
		
 		try {


			long indate2=indate1.getTime();
	    	long duedate2=duedate1.getTime();
	    
 			
 			
 			
		String strsql="select * from ServiceRequest where RequestId=?";//JTABLE
	
			ps=conn.prepareStatement(strsql);
			ps.setString(1,requestid);
			rs=ps.executeQuery();
			
       String strcustomer="select * from CustomerDetails where CustomerId=?";//JTABLE
		 	
			psemployee=conn.prepareStatement(strcustomer);
			psemployee.setString(1,customerid);
			rse=psemployee.executeQuery();
			
			
			
			if(rs.next()==true)
			{
				JOptionPane.showMessageDialog(this,"Record already exist","Adding Record",JOptionPane.WARNING_MESSAGE);						
			}

			else if(rse.next()!=true)
			{
				JOptionPane.showMessageDialog(this,"Customer does not exist","Adding Record",JOptionPane.WARNING_MESSAGE);						
			}

			
			
			
			else {
				
				String strinsert="insert into ServiceRequest values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				psemployee=conn.prepareStatement(strinsert);
				psemployee.setString(1, requestid);
				psemployee.setString(2, customerid);
				psemployee.setString(3, vehiclenum);
				psemployee.setString(4, typeid);
				psemployee.setString(5, serviceid);
				psemployee.setString(6, meterreading);
				psemployee.setDate(7, new java.sql.Date(indate2));
				psemployee.setDate(8, new java.sql.Date(duedate2));
				psemployee.setFloat(9, Float.parseFloat(totalamount));
				psemployee.setString(10, requeststatus);
				psemployee.setString(11, servicestatus);
				psemployee.setString(12, deliverystatus);
				psemployee.setString(13, receptionistid);
				
				int row=psemployee.executeUpdate();  //insert/update/delete
				if (row>0)
				{
					JOptionPane.showMessageDialog(this, "data added");
				
					txtcustomeridadd.setText("");
					txtduedateadd.setDate(null);
					txtindateadd.setDate(null);
					txtreceptionistidadd.setText("");
					txtrequestidadd.setText("");
					txtserviceidadd.setText("");
					txttotalamountadd.setText("");
					txttypeidadd.setText("");
					txtvehiclenumadd.setText("");
					txtmeterreadingadd.setText("");
					
					fillTwoD();
					table.setModel(new DefaultTableModel(
							tableData,column
							
									));
					
					
					
					
				}
			}
			
			
			
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		
 	}	
	}
		
		

    public void Search()
    {
    	 btg.add(assign);
			btg.add(notassign);
			btg1.add(drop);
			btg1.add(pick);
		
    	
    	
    	
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
					Date indate=rs.getDate("InDate");
					Date duedate=rs.getDate("DueDate");
					Float totalamount=rs.getFloat("TotalAmount");
					String requeststatus=rs.getString("RequestStatus");
					String servicestatus=rs.getString("ServiceStatus");
					String deliverystatus=rs.getString("DeliveryStatus");
					String receptionistid=rs.getString("ReceptionistId");
		
						txtrequestidupd.setText(requestid);
						txtcustomeridupd.setText(customerid);
						txtvehiclenumupd.setText(vehiclenum);
						txttypeidupd.setText(typeid);
						txtserviceidupd.setText(serviceid);
						txtmeterreadingupd.setText(meterreading);
						txtindateupd.setDate(indate);
						txtduedateupd.setDate(duedate);
						txttotalamountupd.setText(String.valueOf(totalamount));
						txtservicestatusupd.setText(servicestatus);
						txtreceptionistidupd.setText(receptionistid);
						
						if(requeststatus.equals("Not Assigned"))
			    		{
			    			notassign.setSelected(true);
			    		}
						else
						{
						     assign.setSelected(true);
				    			
						}
			    		if(deliverystatus.equals("Pick"))
			    		{
			    			pick.setSelected(true);
			    		}
			    		else
			    		{
			    			drop.setSelected(true);
			    		}
			    		
			  					
						
						
						
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
    
    public void Update()
    {
    	 requeststatus = null;
    	
    	 deliverystatus = null;
    	 
    	 btg.add(assign);
			btg.add(notassign);
			btg1.add(drop);
			btg1.add(pick);
			
    	 
    	 String id1=txtrequestidupd.getText();
    	 
    	 if(drop.isSelected()==true)
    		{
    			deliverystatus=drop.getText();
    		}
    		if(pick.isSelected()==true)
    		{
    			deliverystatus=pick.getText();
    		}
     		
    		
    		if(notassign.isSelected()==true)
    		{
    			requeststatus=notassign.getText();
    		}
    		if(assign.isSelected()==true)
    		{
    			requeststatus=assign.getText();
    		}
     
			String strupdate="Update ServiceRequest set RequestStatus=?,DeliveryStatus=? where RequestId=?";
			
			
			try {
				ps=conn.prepareStatement(strupdate);
				ps.setString(1, requeststatus);
				ps.setString(2, deliverystatus);
				ps.setString(3, id1);
				int row;
				
				
				row = ps.executeUpdate();
			 	if(row>0)
			
			
			{
				
				JOptionPane.showMessageDialog(this,"Row Updated","Updating Data",JOptionPane.PLAIN_MESSAGE);
				
				txtcustomeridupd.setText("");
				txtduedateupd.setDate(null);
				txtindateupd.setDate(null);
				txtreceptionistidupd.setText("");
				txtrequestidupd.setText("");
				txtserviceidupd.setText("");
			//	txtservicestatusupd.setText("");
				txttotalamountupd.setText("");
				txttypeidupd.setText("");
				txtvehiclenumupd.setText("");
				txtmeterreadingupd.setText("");
				
				btg.remove(assign);
				btg.remove(notassign);
				btg1.remove(drop);
				btg1.remove(pick);
				
				if(drop.isSelected()==true)
	    		{
	    			drop.setSelected(false);
	    		}
	    		if(pick.isSelected()==true)
	    		{
	    			pick.setSelected(false);	    		
			    }
	    		
	    		if(notassign.isSelected()==true)
	    		{
	    			notassign.setSelected(false);
	    		}
	    		if(assign.isSelected()==true)
	    		{
	    			assign.setSelected(false);
	    	    }
	    	      
				
				btnUpdate.setEnabled(false);
				
				
				
				
					
			}
	
}
			
	catch (SQLException se) {
		
		System.out.println(se);
	}

}


   public void Allot()
   {
	   String allotid=txtallotid.getText();
	   String allotempid=txtallotempid.getText();
	   String allotvehiclenum=txtallotVehiclenum.getText();
	   java.util.Date allotdate=txtallotdate.getDate();
	   
	   if(allotid.isEmpty()||allotempid.isEmpty()||allotvehiclenum.isEmpty()||allotdate==null)
	   {
		   JOptionPane.showMessageDialog(this,"Please provide all the informations","Data Required",JOptionPane.WARNING_MESSAGE);
	   }
	   else
   	{  
		   
		   try { 
			   
			   
			   String strcond="select * from Employee where EmployeeId=?";
				
				
				ps=conn.prepareStatement(strcond);
				
				ps.setString(1,allotempid);
				rs=ps.executeQuery();
			
				 String strcond2="select * from ServiceRequest where VehicleNumber=?";
					
					psemployee=conn.prepareStatement(strcond2);
					
					psemployee.setString(1,allotvehiclenum);
					rse=psemployee.executeQuery();
				
		   
		   if(rs.next()==false)//||rse.next()==false)
	
		   {
			   JOptionPane.showMessageDialog(this, "Invalid Employee ID","Alloting Services",JOptionPane.WARNING_MESSAGE);
		   }
		   else if(rse.next()==false)			   
		   {
			   JOptionPane.showMessageDialog(this, "Invalid Vehicle Number","Alloting Services",JOptionPane.WARNING_MESSAGE);
		   }
		   else   
   	
		   {		String strsql="select * from AllotService where AllotId=?";//JTABLE
          
   		    int rowcount=-1;
   		
   		    ps=conn.prepareStatement(strsql);
   			ps.setInt(1,Integer.parseInt(allotid));
   			rs=ps.executeQuery();
   			
   		 long allotdate2=allotdate.getTime();
   			
   			String strlimit="select count(*) from AllotService where EmployeeId=? and Date=?";
   			psemployee=conn.prepareStatement(strlimit);
   			psemployee.setString(1, allotempid);
   			psemployee.setDate(2, new java.sql.Date(allotdate2));
   			rse=psemployee.executeQuery();
   			rse.next();
   			rowcount=rse.getInt(1);
   			//System.out.println(rowcount);
   			
   			
   			
   			if(rs.next()==true)
   			{
   				JOptionPane.showMessageDialog(this,"Allot Id already exist","Alloting Services",JOptionPane.WARNING_MESSAGE);						
   			}
   			else
   			{
   				if(rowcount<5)
   			{ 
              long allotdate1=allotdate.getTime();
	           
              String strallot="insert into AllotService values(?,?,?,?)";
	          
				ps=conn.prepareStatement(strallot);
			    ps.setInt(1, Integer.parseInt(allotid));
			    ps.setString(2, allotempid);
				ps.setString(3, allotvehiclenum);
				ps.setDate(4, new java.sql.Date(allotdate1) );
				
				int row = ps.executeUpdate();
				
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Service Alloted","Alloting Services",JOptionPane.PLAIN_MESSAGE);
				
				
			        txtallotdate.setDate(null);	
				    txtallotempid.setText("");
				    txtallotid.setText("");
	                txtallotVehiclenum.setText("");
				
				}
				
   			}
   			else
   			{
   				JOptionPane.showMessageDialog(this, "Sorry, No more services can be alloted to this employee","Alloting Services",JOptionPane.WARNING_MESSAGE);
   			}
   			}
   			
		   } }
		     catch (SQLException se) {
			
				System.out.println(se);
			}
   	}
	   
   		
	   
   }


          public void find()
          {
        	  String iddelete=txtiddelete.getText();
        	  if(iddelete.isEmpty())
        	  {
        		  JOptionPane.showMessageDialog(this,"Please provide ID","Data Required",JOptionPane.WARNING_MESSAGE);
        	  }
        	  else
        	  {
        		  try
        		  {
        			  String strdelete="select * from ServiceRequest where RequestId=?";
        			  ps=conn.prepareStatement(strdelete);
        			  ps.setString(1, iddelete);
        			  rs=ps.executeQuery();
        			  if(rs.next()==true)
        			  {
        				 String vehiclenumdelete1=rs.getString("VehicleNumber");
        				 String customername=rs.getString("CustomerId");
        				 
        				 txtcustomeriddelete.setText(customername);
        				 txtvehiclenumdelete.setText(vehiclenumdelete1);
        				 
        				 btnDelete.setVisible(true);
        				 
        			  }
        			  else
        			  {
        				  JOptionPane.showMessageDialog(this, "ID not Found","Deleting Service Request",JOptionPane.WARNING_MESSAGE);
                          txtiddelete.setText("");        			
        			  
        			  }
        			  
        			  
        			  
        		  }
        		  catch(SQLException se)
        		  {
        			  System.out.println(se);
        		  }
        		  
        	  }
        	  
        	  
          }
          public void delete()
          {
        	  String iddelete1=txtiddelete.getText();
        	  String strdelete="Delete from ServiceRequest where RequestId=?";
			  try {
				ps=conn.prepareStatement(strdelete);
			  ps.setString(1, iddelete1);
			  int row=ps.executeUpdate();
			  if(row>0)
			  {
				  JOptionPane.showMessageDialog(this,"Service Request has been deleted","Deleting Service Request",JOptionPane.PLAIN_MESSAGE);
				  
				  txtiddelete.setText("");
				  txtcustomeriddelete.setText("");
				  txtvehiclenumdelete.setText("");
				  btnDelete.setVisible(false);
				  
			  }
			  } catch (SQLException e) {
				
				  System.out.println(e);
				  
			  }
				
			  
          }

    
          public void adddetails()
          {	
          	String addcustomerid=txtcustomeradddetails.getText();
          	String name=txtnamedetails.getText();
          	String email=txtemaildetails.getText();
          	String phone=txtphonedetails.getText();
          	int flag=0;
          	
          	
          	
          	if(addcustomerid.isEmpty()||name.isEmpty()||email.isEmpty()||phone.isEmpty())
          	{
          		
          	JOptionPane.showMessageDialog(this,"Please provide all the informations","Data Required",JOptionPane.WARNING_MESSAGE);	
          		
          		}

          	else {
          		int s=phone.length();
                if(s==10)        
                {  for(int i=0;i<10;i++)
         	      {
         	    	  if(phone.charAt(i)=='1'||phone.charAt(i)=='2'||phone.charAt(i)=='3'||phone.charAt(i)=='4'||phone.charAt(i)=='5'
         	    			  ||phone.charAt(i)=='6'||phone.charAt(i)=='7'||phone.charAt(i)=='8'||phone.charAt(i)=='9'||phone.charAt(i)=='0')
         	    	  
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
         		JOptionPane.showMessageDialog(this, "Enter Valid Phone Number","Validating phone number",JOptionPane.ERROR_MESSAGE);
         	}
          	
          	else
          	{  
          		String strsql="select * from CustomerDetails where CustomerId=?";//JTABLE
          		
          		try {
          			
          			
          			ps=conn.prepareStatement(strsql);
          			ps.setString(1,addcustomerid);
          			rs=ps.executeQuery();
          			if(rs.next()==true)
          			{
          				JOptionPane.showMessageDialog(this,"Record already exist","Adding Record",JOptionPane.WARNING_MESSAGE);						
          			}
          			
          			else {
          				
          				String strinsert="insert into CustomerDetails values(?,?,?,?)";
          				psemployee=conn.prepareStatement(strinsert);
          				psemployee.setString(1, addcustomerid);
          				psemployee.setString(2, name);
          				psemployee.setString(3, email);
          				psemployee.setString(4, phone);
          				
          				int row=psemployee.executeUpdate();  //insert/update/delete
          				if (row>0)
          				{
          					JOptionPane.showMessageDialog(this, "data added");
          				
          					txtcustomeradddetails.setText("");
          					txtnamedetails.setText("");
          					txtemaildetails.setText("");
          					txtphonedetails.setText("");
          					
          					
          					
          					
          					
          				}
          			}
          			
          			
          			
          		}
          		catch(SQLException se)
          		{
          			System.out.println(se);
          		}
          		
          		
          	}
          		
          	}          		
          	}

          	
         



         
  

@Override
public void actionPerformed(ActionEvent e) {
	
	String Caption=e.getActionCommand();
	if(Caption.equalsIgnoreCase("Submit"))
	{
        Add();  
	
	}
	if (Caption.equalsIgnoreCase("GO"))
	{
		
		Search();
		
		
	}
		
	    	if(Caption.equalsIgnoreCase("Update"))
		{		
	              Update();
		}
	    	

           if(Caption.equalsIgnoreCase("Allot"))
           {
        	   Allot();
           }
           
           if(Caption.equalsIgnoreCase("FIND"))
           {
        	   find();
           }
           if(Caption.equalsIgnoreCase("Delete"))
           {
        	   delete();
           }
           
           if(Caption.equalsIgnoreCase("Add Details"))
           {
        	   adddetails();
           }
           if(Caption.equalsIgnoreCase("Logout"))
           {
        	   WelcomeScreen ob=new WelcomeScreen();
        	   ob.setVisible(true);
        	   this.dispose();
           }
           if(Caption.equalsIgnoreCase("Change Password"))
           {
        	   	ChangePassword ob=new ChangePassword();
        	   	ob.setVisible(true);
           }
           if(Caption.equalsIgnoreCase("Contact Developing Team"))
           {
        	   Contact ob=new Contact();
        	   ob.setVisible(true);
           }
           if(Caption.equalsIgnoreCase("About System"))
           {
        	   AboutSystem ob=new AboutSystem();
        	   ob.setVisible(true);
           }
           if(Caption.equals("View Service Price"))
           {
        	   ViewReceptionistService ob=new ViewReceptionistService();
        	   ob.setVisible(true);
           }
           if(Caption.equals("View Vehicle Types"))
           {
        	   ViewVehicle ob=new ViewVehicle();
        	   ob.setVisible(true);
           }
           if(Caption.equals("Customer Reciept"))
           {
        	   ViewReceipt ob=new ViewReceipt();
        	   ob.setVisible(true);
           }
           if(Caption.equals("View Service By Engineer"))
           {
        	   ViewAdminServiced ob=new ViewAdminServiced();
        	   ob.setVisible(true);
           }
           if(Caption.equals("View Customer Details"))
           {
        	   ViewCustomer ob= new ViewCustomer();
        	   ob.setVisible(true);
           }
           if(Caption.equals("View Service Engineer"))
           {
        	   ViewEmployee ob= new ViewEmployee();
        	   ob.setVisible(true);
           }
           if(Caption.equals("Customer Feedback"))
           {
        	   Feedback ob=new Feedback();
        	   ob.setVisible(true);
           }
}
	public void jListDemo()
	{
		
		String item="";
		String strsql= "Select ServiceName from Service_Task";
		try {
			pslist=conn.prepareStatement(strsql);
			rslist=pslist.executeQuery();
			while(rslist.next())
			{
				item+=":"+rslist.getString("ServiceName");
			}
			String arr[]=item.split(":");
			list=new JList(arr);
			panel.add(list);
			list.setBounds(549, 85, 154, 259);
			
			list.setVisibleRowCount(6);
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			JLabel lblSelectMultipleItem = new JLabel("* Select multiple items from Service Ids using ctrl+");
			lblSelectMultipleItem.setBounds(515, 21, 362, 15);
			panel.add(lblSelectMultipleItem);
			
			JLabel lblServiceIds_1 = new JLabel("Service Ids*");
			lblServiceIds_1.setBounds(578, 58, 114, 15);
			panel.add(lblServiceIds_1);
			
			txtserviceidadd = new JTextField();
			txtserviceidadd.setEnabled(false);
			txtserviceidadd.setEditable(false);
			txtserviceidadd.setBounds(309, 125, 124, 19);
			panel.add(txtserviceidadd);
			txtserviceidadd.setColumns(10);
			list.addListSelectionListener(this);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String des="";
		float cost=0.0f;
		Object a[]=list.getSelectedValues();
		  for(int i = 0; i < a.length; i++)
		    {
		      //des += (String) a[i];
			  String serType=(String)a[i];
			  txtserviceidadd.setText(txtserviceidadd.getText()+":"+serType);
			  String strsql="select * from Service_Task where ServiceName= ?";
			  try {
				  pslist=conn.prepareStatement(strsql);
				  pslist.setString(1, serType);
				  rslist=pslist.executeQuery();
				  if(rslist.next())
				  {
					  cost+=rslist.getFloat("ServiceCost");
				  }
				  txttotalamountadd.setText(String.valueOf(cost));
				  
			  }
			  
			  catch(SQLException se)
			  {
				  System.out.println(se);
			  }
			  
		    }
		 
		    
		  }
	
	
	
	public void fillTwoD()
	{
		try {
			String strcount="Select count(*) from servicerequest where RequestStatus= ?";
			pscount=con.prepareStatement(strcount);
			pscount.setString(1, "Not Assigned");
			rscount=pscount.executeQuery();
			rscount.next();
			int rowcnt=rscount.getInt(1);
			//System.out.println(rowcnt);
			if(rowcnt>0)
			{
				tableData=new String[rowcnt][1];
				String strsql="Select * from servicerequest where RequestStatus= ?";
				psdata=con.prepareStatement(strsql);
				psdata.setString(1, "Not Assigned");
				rsdata=psdata.executeQuery();
				int row=0;
				while(rsdata.next())
				{
					String vehicle=rsdata.getString("VehicleNumber");
					
					tableData[row][0]=vehicle;
					row++;		
				}
			}
		
		}
		catch(SQLException se) {
			System.out.println(se);
		}	
		
	 }

	
}

	
	
	
	
		

