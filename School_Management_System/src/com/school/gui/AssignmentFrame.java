package com.school.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.school.dao.AdmissionDAO;
import com.school.dao.AssignmentDAO;
import com.school.dao.ClassDAO;
import com.school.dao.PeriodDAO;
import com.school.dao.StaffDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.AssignmentDAOImpl;
import com.school.daoimpl.ClassDAOImpl;
import com.school.daoimpl.PeriodDAOImpl;
import com.school.daoimpl.StaffDAOImpl;
import com.school.util.MyTableModel;
import com.toedter.calendar.JDateChooser;

public class AssignmentFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnAddPeriod;
	private JButton btnUpdatePeriod;
	private JButton btnDeletePeriod;
	private JTextField txtAssignment;
	private JComboBox cmboStudent;
	private JLabel lblStartDate;
	private JLabel lblDueDate;
	private JComboBox cmboTeacher;
	private JDateChooser dateChooserStartDate;
	private JDateChooser dateChooserDueDate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {  
				try {
					AssignmentFrame frame = new AssignmentFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AssignmentFrame() {  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAddPeriod.setEnabled(true);
				btnDeletePeriod.setEnabled(false);
				btnUpdatePeriod.setEnabled(false);
			}
		});
		panel.setBounds(0, 0, 1183, 511);
		panel.setBackground(new Color(93, 173, 226));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Assignment");
		lblName.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblName.setBounds(648, 159, 129, 29);
		panel.add(lblName);

		txtAssignment = new JTextField();
		txtAssignment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAssignment.setBounds(769, 159, 171, 26);
		panel.add(txtAssignment);

		JLabel lblNewLabel = new JLabel("Assignment");
		lblNewLabel.setBounds(557, 11, 216, 61);
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_group_of_projects_48px.png")));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 23));
		panel.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 628, 341);
		panel.add(scrollPane);

		btnAddPeriod = new JButton("Add Assignment");
		btnAddPeriod.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAddPeriod);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAddPeriod);
			}
		});
		btnAddPeriod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					if (txtAssignment.getText().toString().isEmpty() || dateChooserStartDate.getDate().toString().isEmpty() || 
							dateChooserDueDate.getDate().toString().isEmpty()) {
						JOptionPane.showMessageDialog(AssignmentFrame.this, "The Field cannot be Empty", "Period View",
								JOptionPane.DEFAULT_OPTION);
					} else {
						
						AdmissionDAO adao = new AdmissionDAOImpl();
						StaffDAO sdao = new StaffDAOImpl();
						
						String asssignment = txtAssignment.getText().toString(); 
						String student = cmboStudent.getSelectedItem().toString();
						

						Date startDate = dateChooserStartDate.getDate();
						Date dueDate = dateChooserDueDate.getDate();
						String teacher = cmboTeacher.getSelectedItem().toString();
						
						Integer student_id = adao.getStudentIdbyName(student);
						Integer teacher_id = sdao.getTeacherIdByName(teacher);
						
						
						 if(startDate.compareTo(dueDate) > 0) {
							JOptionPane.showMessageDialog(AssignmentFrame.this, "Start Date is Prior to Due Date",
										"Assignment View", JOptionPane.DEFAULT_OPTION);
						 }else {
							 
							AssignmentDAO assigndao = new AssignmentDAOImpl(); 
			
							SimpleDateFormat dcn = new SimpleDateFormat("dd-MM-yyyy");
						    String startdate = dcn.format(startDate);
						    String duedate = dcn.format(dueDate);
						    
							
							int row  = assigndao.addAssignment(asssignment, student_id.toString(), startdate, duedate, teacher_id.toString());
							if (row == 1) {
								JOptionPane.showMessageDialog(AssignmentFrame.this, "Assignment Added Successfully",
										"Assignment View", JOptionPane.DEFAULT_OPTION);
							}
							else {
								JOptionPane.showMessageDialog(AssignmentFrame.this, "Assignment not Added",
										"Assignment View", JOptionPane.ERROR_MESSAGE);
							}
							txtAssignment.setText("");
							dateChooserStartDate.setDate(null);
							dateChooserDueDate.setDate(null);
						 }
						
						 populateTable();
						}

					}
		});

		btnAddPeriod.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_plus_26px.png")));
		btnAddPeriod.setOpaque(false);
		btnAddPeriod.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddPeriod.setForeground(Color.WHITE);
		btnAddPeriod.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnAddPeriod.setContentAreaFilled(false);
		btnAddPeriod.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAddPeriod.setBounds(976, 159, 197, 45);
		panel.add(btnAddPeriod);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setSelectionForeground(Color.WHITE);

				btnAddPeriod.setEnabled(false);
				btnDeletePeriod.setEnabled(true);
				btnUpdatePeriod.setEnabled(true);

				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

				String assignment = (String) table.getValueAt(table.getSelectedRow(), 1);
				String student = (String) table.getValueAt(table.getSelectedRow(), 2);
				String startdate = (String) table.getValueAt(table.getSelectedRow(), 3);
				String duedate = (String) table.getValueAt(table.getSelectedRow(), 4);
				String teacher = (String) table.getValueAt(table.getSelectedRow(), 5);

				txtAssignment.setText(assignment);
				cmboStudent.setSelectedItem(student);
				
		
			    Date date1;
			    Date date2;
				try {
					date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startdate);
					date2 = new SimpleDateFormat("dd-MM-yyyy").parse(duedate);
					dateChooserStartDate.setDate(date1);
					dateChooserDueDate.setDate(date2);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.BLUE);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(220, 20, 60));
		scrollPane.setViewportView(table);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame loginframe = new LoginFrame(); 
				AssignmentFrame assignmentFrame = new AssignmentFrame(); 
				assignmentFrame.setVisible(false);
				loginframe.setVisible(true);
				loginframe.setTitle("Login");
				dispose();
			}
		});
		btnSignOut.setOpaque(false);
		btnSignOut.setIcon(
				new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_sign_out_26px_1.png")));
		btnSignOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnSignOut.setForeground(Color.WHITE);
				btnSignOut.setBorder(new LineBorder(Color.WHITE, 2, true));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnSignOut.setForeground(Color.BLACK);
				btnSignOut.setBorder(new LineBorder(Color.BLACK, 2, true));
			}
		});
		btnSignOut.setHorizontalAlignment(SwingConstants.LEFT);
		btnSignOut.setForeground(Color.BLACK);
		btnSignOut.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnSignOut.setContentAreaFilled(false);
		btnSignOut.setBorder(new LineBorder(Color.BLACK, 2, true));
		btnSignOut.setBounds(1055, 467, 118, 33);
		panel.add(btnSignOut);

		btnDeletePeriod = new JButton("Delete Assignment");
		btnDeletePeriod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					AssignmentDAO dao = new AssignmentDAOImpl();

					int row = dao.deleteAssignment(id);
					if (row == 1) {
						JOptionPane.showMessageDialog(AssignmentFrame.this, "Assignment Deleted Successfully", "Assignment View",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(AssignmentFrame.this, "Assignment Not Deleted", "Assignment View",
								JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(AssignmentFrame.this, "Select an Assignment", "Assignment View",
							JOptionPane.DEFAULT_OPTION);
				}
				txtAssignment.setText("");
				populateTable();

			}
		});
		btnDeletePeriod.setOpaque(false);
		btnDeletePeriod.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeletePeriod.setForeground(Color.WHITE);
		btnDeletePeriod.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnDeletePeriod.setContentAreaFilled(false);
		btnDeletePeriod.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDeletePeriod.setBounds(976, 312, 197, 45);
		btnDeletePeriod
				.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_delete_26px.png")));
		panel.add(btnDeletePeriod);

		JTextField txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				DefaultTableModel myTable = (DefaultTableModel) table.getModel();
				String search = txtSearch.getText().toString();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(myTable);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearch.setBounds(111, 113, 164, 29);
		panel.add(txtSearch);
		
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
				AssignmentFrame assignFrame = new AssignmentFrame();  
				assignFrame.setVisible(false); 
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
		btnBack.setBounds(10, 11, 90, 33);
		panel.add(btnBack);
		
		
		JLabel lblEmployee = new JLabel("Search");
		lblEmployee.setFont(new Font("Segoe Script", Font.BOLD, 17));
		lblEmployee.setBounds(31, 109, 110, 39);
		panel.add(lblEmployee);

		btnUpdatePeriod = new JButton("Update Assignment");
		btnUpdatePeriod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

				String assignment = txtAssignment.getText().toString(); 
				String student = cmboStudent.getSelectedItem().toString();
				String teacher = cmboTeacher.getSelectedItem().toString();
				
				AssignmentDAO dao = new AssignmentDAOImpl();
				
				Date startDate = dateChooserStartDate.getDate();
				Date dueDate = dateChooserDueDate.getDate();
				SimpleDateFormat dcn = new SimpleDateFormat("dd-MM-yyyy");
				
				
				AdmissionDAO adao = new AdmissionDAOImpl();
				StaffDAO sdao = new StaffDAOImpl();
				Integer student_id = adao.getStudentIdbyName(student);
				Integer teacher_id = sdao.getTeacherIdByName(teacher);
				
			    String startdate = dcn.format(startDate);
			    String duedate = dcn.format(dueDate);
	 
				int row = dao.updateAssignment(assignment, student_id.toString(), startdate, duedate, teacher_id.toString(), id);

				if (row == 1) {
					JOptionPane.showMessageDialog(AssignmentFrame.this, "Assignment Updated Successfully", "Assignment View",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(AssignmentFrame.this, "Assignment Not Updated", "Assignment View",
							JOptionPane.ERROR_MESSAGE);
				}
				txtAssignment.setText("");
				dateChooserStartDate.setDate(null);
				dateChooserDueDate.setDate(null);
				populateTable();
			}
		});
		btnUpdatePeriod.setOpaque(false);
		btnUpdatePeriod.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdatePeriod.setForeground(Color.WHITE);
		btnUpdatePeriod.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnUpdatePeriod.setContentAreaFilled(false);
		btnUpdatePeriod.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnUpdatePeriod.setBounds(976, 238, 197, 45);
		btnUpdatePeriod
				.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_restart_26px.png")));
		panel.add(btnUpdatePeriod);

		JLabel lblDuration = new JLabel("Student");
		lblDuration.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblDuration.setBounds(648, 228, 110, 29);
		panel.add(lblDuration);

		cmboStudent = new JComboBox();
		cmboStudent.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboStudent.setBounds(769, 230, 171, 20);
		panel.add(cmboStudent);
		
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblStartDate.setBounds(648, 291, 110, 29);
		panel.add(lblStartDate);
		
		lblDueDate = new JLabel("Due Date");
		lblDueDate.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblDueDate.setBounds(648, 357, 110, 29);
		panel.add(lblDueDate);
		
		dateChooserStartDate = new JDateChooser();
		dateChooserStartDate.setBounds(768, 290, 172, 20);
		panel.add(dateChooserStartDate);
		
		dateChooserDueDate = new JDateChooser();
		dateChooserDueDate.setBounds(769, 356, 172, 20);
		panel.add(dateChooserDueDate);
		
		cmboTeacher = new JComboBox();
		cmboTeacher.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboTeacher.setBounds(769, 418, 171, 20);
		panel.add(cmboTeacher);
		
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblTeacher.setBounds(648, 416, 110, 29);
		panel.add(lblTeacher);

		populateTable();
		disableButton();
		populateTeacherCombobox();
		populateStudentCombobox();

	}
	public void disableButton() {
		btnAddPeriod.setEnabled(true);
		btnDeletePeriod.setEnabled(false);
		btnUpdatePeriod.setEnabled(false);  
	}
	private void populateTable() {
		AssignmentDAO adao = new AssignmentDAOImpl();  
		ResultSet rs = adao.getDataResultSet();

		table.setModel(MyTableModel.buildTableModel(rs));
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Assignment");
		table.getColumnModel().getColumn(2).setHeaderValue("Student");
		table.getColumnModel().getColumn(3).setHeaderValue("Start Date");
		table.getColumnModel().getColumn(4).setHeaderValue("Due Date");
		table.getColumnModel().getColumn(5).setHeaderValue("Teacher");
	}
	
	
	public void populateTeacherCombobox() {
		ClassDAO dao = new ClassDAOImpl();
		List <String> names = dao.getAllTeachers();
		for(String n: names) {
			cmboTeacher.addItem(n);
		}
	}
	public void populateStudentCombobox() {
		AdmissionDAO dao = new AdmissionDAOImpl();
		List <String> names = dao.getAllStudents();
		for(String n: names) {
			cmboStudent.addItem(n);
		}
	}
	
	public boolean periodExist(String period) {  
		for (int i = 1; i < table.getRowCount(); i++) {
			if(period.equalsIgnoreCase(table.getValueAt(i, 1).toString())){
				return true;
			}
		}
		return false;
	}

	public void mouseClickedEffect(JButton button) {
		button.setForeground(Color.BLACK);
		button.setBorder(new LineBorder(Color.BLACK, 2, true));
	}

	public void mouseReleasedEffect(JButton button) {
		button.setForeground(Color.WHITE);
		button.setBorder(new LineBorder(Color.WHITE, 2, true));
	}
}
