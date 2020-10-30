package com.school.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame {
	

//#5DADE2 blue   93,173,226
//
//#37353D black  55,53,61
	
	
	
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(93,173,226));
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(null);
		panel_1.setBounds(235, 0, 621, 451);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAdmission = new JButton("Admission");
		btnAdmission.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAdmission);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAdmission);
			}
		});
		btnAdmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard(); 
				AdmissionFrame admissionFrame = new AdmissionFrame();
				admissionFrame.setVisible(true);
				dashboard.setVisible(false);
				admissionFrame.setTitle("Admission");
				dispose();
			}
		});
		btnAdmission.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdmission.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdmission.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_student_male_48px_2.png")));
		
		btnAdmission.setForeground(new Color(255, 255, 255));
		// For Transpareting the Button
		btnAdmission.setOpaque(false);
		btnAdmission.setContentAreaFilled(false);
		btnAdmission.setBorder(new LineBorder(Color.WHITE, 2, true));
		
		btnAdmission.setBounds(28, 70, 133, 97);
		panel_1.add(btnAdmission);
		
		JButton btnStaff = new JButton("Staff");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard dashboard = new Dashboard();  
				StaffFrame staffFrame = new StaffFrame();
				staffFrame.setVisible(true);
				dashboard.setVisible(false);
				staffFrame.setTitle("Staff");
				dispose();
			}
		});
		btnStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnStaff);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnStaff);
			}
		});
		btnStaff.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_conference_foreground_selected_48px_1.png")));
		btnStaff.setOpaque(false);
		btnStaff.setHorizontalAlignment(SwingConstants.LEFT);
		btnStaff.setForeground(Color.WHITE);
		btnStaff.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnStaff.setContentAreaFilled(false);
		btnStaff.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnStaff.setBounds(171, 70, 133, 97);
		panel_1.add(btnStaff);
		
		JButton btnClass = new JButton("Class");
		btnClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard();  
				ClassFrame classFrame = new ClassFrame();
				classFrame.setVisible(true);
				dashboard.setVisible(false);
				classFrame.setTitle("Class");
				dispose();
			}
		});
		btnClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnClass);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnClass);
			}
		});
		btnClass.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_book_48px.png")));
		btnClass.setOpaque(false);
		btnClass.setHorizontalAlignment(SwingConstants.LEFT);
		btnClass.setForeground(Color.WHITE);
		btnClass.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClass.setContentAreaFilled(false);
		btnClass.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnClass.setBounds(314, 70, 133, 97);
		panel_1.add(btnClass);
		
		JButton btnAttendance = new JButton("Attendance");
		btnAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard dashboard = new Dashboard();  
				AttendanceSubjectFrame attendanceFrame = new AttendanceSubjectFrame();
				attendanceFrame.setVisible(true);
				dashboard.setVisible(false);
				attendanceFrame.setTitle("Attendance");
				dispose();
			}
		});
		btnAttendance.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAttendance);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAttendance);
			}
		});
		btnAttendance.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_checkmark_48px.png")));
		btnAttendance.setOpaque(false);
		btnAttendance.setHorizontalAlignment(SwingConstants.LEFT);
		btnAttendance.setForeground(Color.WHITE);
		btnAttendance.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAttendance.setContentAreaFilled(false);
		btnAttendance.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAttendance.setBounds(457, 70, 144, 97);
		panel_1.add(btnAttendance);
		
		JButton btnGroups = new JButton("Groups");
		btnGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard();  
				GroupFrame groupFrame = new GroupFrame();
				groupFrame.setTitle("Groups");
				dashboard.setVisible(false);
				groupFrame.setVisible(true);
				dispose();
			}
		});
		btnGroups.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnGroups);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnGroups);
			}
		});
		btnGroups.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_user_groups_48px.png")));
		btnGroups.setOpaque(false);
		btnGroups.setHorizontalAlignment(SwingConstants.LEFT);
		btnGroups.setForeground(Color.WHITE);
		btnGroups.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGroups.setContentAreaFilled(false);
		btnGroups.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnGroups.setBounds(28, 178, 133, 97);
		panel_1.add(btnGroups);
		
		JButton btnTimeTable = new JButton("Time Table");
		btnTimeTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard();  
				TimeTableFrame timeTableFrame = new TimeTableFrame(); 
				timeTableFrame.setTitle("Time Table Frame"); 
				dashboard.setVisible(false);
				timeTableFrame.setVisible(true);
				dispose();
			}
		});
		btnTimeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnTimeTable);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnTimeTable);
			}
		});
		btnTimeTable.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_calendar_3_48px.png")));
		btnTimeTable.setOpaque(false);
		btnTimeTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnTimeTable.setForeground(Color.WHITE);
		btnTimeTable.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTimeTable.setContentAreaFilled(false);
		btnTimeTable.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnTimeTable.setBounds(171, 178, 133, 97);
		panel_1.add(btnTimeTable);
		
		JButton btnSubjects = new JButton("Subjects");
		btnSubjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Dashboard dashboard = new Dashboard();  
				SubjectFrame subjectFrame = new SubjectFrame(); 
				subjectFrame.setVisible(true);
				dashboard.setVisible(false);
				subjectFrame.setTitle("Subjects");
				dispose();
			}
		});
		btnSubjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnSubjects);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnSubjects);
			}
		});
		btnSubjects.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_book_shelf_48px.png")));
		btnSubjects.setOpaque(false);
		btnSubjects.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubjects.setForeground(Color.WHITE);
		btnSubjects.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubjects.setContentAreaFilled(false);
		btnSubjects.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnSubjects.setBounds(314, 178, 133, 97);
		panel_1.add(btnSubjects);
		
		JButton btnResultss = new JButton("Results");
		btnResultss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard();  
				ResultFrame resultFrame = new ResultFrame();  
				resultFrame.setVisible(true);
				dashboard.setVisible(false);
				resultFrame.setTitle("Results");
				dispose();
			}
		});
		btnResultss.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnResultss);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnResultss);
			}
		});
		btnResultss.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_total_sales_48px.png")));
		btnResultss.setOpaque(false);
		btnResultss.setHorizontalAlignment(SwingConstants.LEFT);
		btnResultss.setForeground(Color.WHITE);
		btnResultss.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnResultss.setContentAreaFilled(false);
		btnResultss.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnResultss.setBounds(457, 178, 144, 97);
		panel_1.add(btnResultss);
		
		JButton btnAssignments = new JButton("Assignment");
		btnAssignments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard dashboard = new Dashboard();
				AssignmentFrame assignmentFrame = new AssignmentFrame();
				dashboard.setVisible(false);
				assignmentFrame.setVisible(true);
				assignmentFrame.setTitle("Assignment");
				dispose();
			}
		});
		btnAssignments.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAssignments);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAssignments);
			}
		});
		btnAssignments.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_group_of_projects_48px.png")));
		btnAssignments.setOpaque(false);
		btnAssignments.setHorizontalAlignment(SwingConstants.LEFT);
		btnAssignments.setForeground(Color.WHITE);
		btnAssignments.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAssignments.setContentAreaFilled(false);
		btnAssignments.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAssignments.setBounds(28, 291, 133, 97);
		panel_1.add(btnAssignments);
		
		JButton btnFees = new JButton("Fees");
		btnFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard();  
				FeesFrame feesFrame = new FeesFrame();  
				feesFrame.setVisible(true);
				dashboard.setVisible(false);
				feesFrame.setTitle("Fees");
				dispose();
			}
		});
		btnFees.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnFees);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnFees);
			}
		});
		btnFees.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_transaction_48px.png")));
		btnFees.setOpaque(false);
		btnFees.setHorizontalAlignment(SwingConstants.LEFT);
		btnFees.setForeground(Color.WHITE);
		btnFees.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFees.setContentAreaFilled(false);
		btnFees.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnFees.setBounds(171, 291, 133, 97);
		panel_1.add(btnFees);
		
		JButton btnShifts = new JButton("Projects");
		btnShifts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard();
				ProjectFrame projectFrame = new ProjectFrame(); 
				dashboard.setVisible(false);
				projectFrame.setVisible(true);
				projectFrame.setTitle("Project");
				dispose();
			}
		});
		btnShifts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnShifts);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnShifts);
			}
		});
		btnShifts.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_prototype_48px.png")));
		btnShifts.setOpaque(false);
		btnShifts.setHorizontalAlignment(SwingConstants.LEFT);
		btnShifts.setForeground(Color.WHITE);
		btnShifts.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShifts.setContentAreaFilled(false);
		btnShifts.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnShifts.setBounds(314, 291, 133, 97);
		panel_1.add(btnShifts);
		
		JButton btnPeriods = new JButton("Periods");
		btnPeriods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dashboard = new Dashboard();
				PeriodFrame periodFrame = new PeriodFrame();
				dashboard.setVisible(false);
				periodFrame.setVisible(true);
				periodFrame.setTitle("Periods");
				dispose();
			}
		});
		btnPeriods.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnPeriods);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnPeriods);
			}
		});
		btnPeriods.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_time_48px.png")));
		btnPeriods.setOpaque(false);
		btnPeriods.setHorizontalAlignment(SwingConstants.LEFT);
		btnPeriods.setForeground(Color.WHITE);
		btnPeriods.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPeriods.setContentAreaFilled(false);
		btnPeriods.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnPeriods.setBounds(457, 291, 144, 97);
		panel_1.add(btnPeriods);
		
		JButton button = new JButton("Sign out");
		button.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_sign_out_26px_1.png")));
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button.setForeground(Color.WHITE);
				button.setBorder(new LineBorder(Color.WHITE, 1 , true));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button.setForeground(Color.BLACK);
				button.setBorder(new LineBorder(Color.BLACK, 1 , true));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard dashboard = new Dashboard();
				LoginFrame loginframe = new LoginFrame();
				loginframe.setVisible(true);
				dashboard.setVisible(false);
				loginframe.setTitle("Login");
				dispose();
			}
		});
		button.setOpaque(false);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Segoe Script", Font.BOLD, 16));
		button.setContentAreaFilled(false);
		button.setBorder(new LineBorder(Color.BLACK, 1, true));
		button.setBounds(487, 409, 114, 31);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(55,53,61));
		panel_2.setBounds(-11, 0, 660, 43);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(22, 11, 56, 19);
		panel_2.add(lblAdmin);
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBorder(null);
		panel.setBackground(new Color(55,53,61));
		panel.setBounds(0, 0, 239, 451);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDashboard.setForeground(new Color(255, 255, 255));
		lblDashboard.setBounds(10, 11, 98, 19);
		panel.add(lblDashboard);
		
		JLabel lblSchoolManagement = new JLabel("School");
		
		lblSchoolManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchoolManagement.setFont(new Font("Segoe Script", Font.BOLD, 20));
		
		lblSchoolManagement.setForeground(Color.WHITE);
		lblSchoolManagement.setBounds(10, 110, 229, 33);
		panel.add(lblSchoolManagement);
		
		JLabel lblManagement = new JLabel("Management");
		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagement.setForeground(Color.WHITE);
		lblManagement.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblManagement.setBounds(10, 154, 229, 33);
		panel.add(lblManagement);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setForeground(Color.WHITE);
		lblSystem.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblSystem.setBounds(10, 194, 229, 33);
		panel.add(lblSystem);
		
		
		
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
