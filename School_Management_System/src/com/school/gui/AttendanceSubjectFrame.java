package com.school.gui;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.school.connection.DBConnection;
import com.school.dao.AttendanceSubjectDAO;

import com.school.daoimpl.AttendanceSubjectDAOImpl;
import com.school.daoimpl.ClassDAOImpl;
import com.school.daoimpl.StaffDAOImpl;
import com.school.util.MyTableModel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class AttendanceSubjectFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cmboStudent; 
	private int percentage=0;
	private JLabel lblPercentage;
	private JLabel lblEligibility;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceSubjectFrame frame = new AttendanceSubjectFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AttendanceSubjectFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(93,173,226));
		panel.setBounds(0, 0, 667, 470);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 131, 434, 247);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setSelectionForeground(Color.WHITE);
			}
		});
		
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.BLUE);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(220,20,60));
		scrollPane.setViewportView(table);
		
		JButton btnReport = new JButton("Generate Report"); 
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Connection con = DBConnection.getConnection();
			        String reportPath = "C:\\\\Users\\\\shuja\\\\eclipse-workspace\\\\School_Management_System\\\\src\\\\com\\\\school\\\\gui\\\\Attendance.jrxml";		  
			        HashMap parameters = new HashMap<>();
			        parameters.put("StudentName", cmboStudent.getSelectedItem().toString());
			        parameters.put("Percentage",lblPercentage.getText().toString());
			        parameters.put("Eligibility", lblEligibility.getText().toString());
			       
			        JasperReport jr = JasperCompileManager.compileReport(reportPath);
			        JasperPrint jp = JasperFillManager.fillReport(jr,   parameters, con);
			        JasperViewer.viewReport(jp);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnReport);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnReport);
			}
		});
		
		
		btnReport.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_report_file_32px_3.png")));
		btnReport.setOpaque(false);
		btnReport.setHorizontalAlignment(SwingConstants.LEFT);
		btnReport.setForeground(Color.WHITE);
		btnReport.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnReport.setContentAreaFilled(false);
		btnReport.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnReport.setBounds(470, 131, 187, 38);
		panel.add(btnReport);
		
		JButton btnBack = new JButton("Back");
		btnBack.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_back_arrow_26px.png")));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnBack);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnBack);
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard(); 
				TimeTableFrame timetable = new TimeTableFrame();   
				timetable.setVisible(false);
				dashboard.setVisible(true);
				dashboard.setTitle("Dashboard");
				dispose();
			}
		});
		panel.setLayout(null);
		btnBack.setOpaque(false);
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnBack.setBounds(10, 11, 93, 33);
		panel.add(btnBack);
		
		JLabel lblStudent = new JLabel("Student"); 
		lblStudent.setForeground(Color.BLACK);
		lblStudent.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblStudent.setBounds(22, 103, 100, 22);
		panel.add(lblStudent);
		
		JLabel lblNewLabel = new JLabel("Attendnce");
		lblNewLabel.setIcon(new ImageIcon(AttendanceSubjectFrame.class.getResource("/com/school/images/icons8_checkmark_48px.png")));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 23));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(219, 22, 225, 59);
		panel.add(lblNewLabel);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame loginframe = new LoginFrame();   
				AttendanceSubjectFrame attendanceFrame = new AttendanceSubjectFrame(); 
				attendanceFrame.setVisible(false);
				loginframe.setVisible(true);
				loginframe.setTitle("Login");  
				dispose();
			}
		});
		btnSignOut.setOpaque(false);
		btnSignOut.setIcon(new ImageIcon(TimeTableFrame.class.getResource("/com/school/images/icons8_sign_out_26px_1.png")));
		btnSignOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnSignOut.setForeground(Color.WHITE);
				btnSignOut.setBorder(new LineBorder(Color.WHITE,2,true));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnSignOut.setForeground(Color.BLACK);
				btnSignOut.setBorder(new LineBorder(Color.BLACK,2,true));
			}
		});
		btnSignOut.setHorizontalAlignment(SwingConstants.LEFT);
		btnSignOut.setForeground(Color.BLACK);
		btnSignOut.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnSignOut.setContentAreaFilled(false);
		btnSignOut.setBorder(new LineBorder(Color.BLACK, 2, true));
		btnSignOut.setBounds(540, 426, 117, 33);
		panel.add(btnSignOut);
		
		cmboStudent = new JComboBox();
		cmboStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//populateTable(cmboStudent.getSelectedItem().toString());
				populateTable(cmboStudent.getSelectedItem().toString());
				defineEligiblity();
				
				
			}
		});
		cmboStudent.setBounds(132, 100, 151, 25);
		cmboStudent.setForeground(Color.BLUE);
		cmboStudent.setFont(new Font("Segoe Script", Font.BOLD, 19));
		panel.add(cmboStudent);
		
		JLabel lblTotalAttendance = new JLabel("Total Attendance");
		lblTotalAttendance.setForeground(Color.BLACK);
		lblTotalAttendance.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblTotalAttendance.setBounds(10, 418, 197, 22);
		panel.add(lblTotalAttendance);
		
		lblEligibility = new JLabel("");
		lblEligibility.setForeground(Color.BLUE);
		lblEligibility.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblEligibility.setBounds(315, 418, 181, 22);
		panel.add(lblEligibility);
		
		lblPercentage = new JLabel("");
		lblPercentage.setForeground(Color.RED);
		lblPercentage.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblPercentage.setBounds(224, 418, 74, 22);
		panel.add(lblPercentage);
		
		populateComboBox();
		populateTable(cmboStudent.getSelectedItem().toString());
		
		
		defineEligiblity();
	}
	
	
	private void defineEligiblity() {
		percentage = getPercentage(cmboStudent.getSelectedItem().toString());
		lblPercentage.setText(percentage+"% ");
		if(percentage >=75) {
			lblEligibility.setText("Eligible");
		}else {
			lblEligibility.setText("Not Eligible");
		}
	}
	
	private Integer getPercentage(String studetnName) {
		AttendanceSubjectDAO dao = new AttendanceSubjectDAOImpl();
		List<Integer> attendance = dao.getAllAttendance(studetnName);
		int sum = 0;
		int count = 0;
		int totalPercentage = 0;
		for(Integer values: attendance) {
			count++;
			sum += values;
		}
		if(count == 0) {
			JOptionPane.showMessageDialog(AttendanceSubjectFrame.this, "Attendance Not Defined","Attendance View",JOptionPane.INFORMATION_MESSAGE);
			return 0;
		}
		return totalPercentage =  sum / count;
		
	}
	
	private void populateComboBox() {
		AttendanceSubjectDAO dao = new AttendanceSubjectDAOImpl();
		List <String> names = dao.getAllStudents();
		for(String n: names) {
			cmboStudent.addItem(n);
		}
	}
	private void populateTable(String name) {
		AttendanceSubjectDAO dao = new AttendanceSubjectDAOImpl();
		ResultSet rs = dao.getDataResultSet(name);
		table.setModel(MyTableModel.buildTableModel(rs));
		
		table.getColumnModel().getColumn(0).setHeaderValue("Student");
		table.getColumnModel().getColumn(1).setHeaderValue("Subject");
	}
	public void mouseClickedEffect(JButton button) {
		button.setForeground(Color.BLACK);
		button.setBorder(new LineBorder(Color.BLACK, 2 , true));
	}
	public void mouseReleasedEffect(JButton button) {
		button.setForeground(Color.WHITE);
		button.setBorder(new LineBorder(Color.WHITE,2,true));
	}
}
