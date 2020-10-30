package com.school.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.school.bean.AdmissionBean;
import com.school.bean.PostBean;
import com.school.bean.StaffBean;
import com.school.dao.AdmissionDAO;
import com.school.dao.StaffDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.StaffDAOImpl;
import com.school.util.MyTableModel;
import com.school.util.Validation;

public class StaffFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtName;
	private JTextField txtRollNum;
	private JComboBox cmboPost;
	private JLabel lblEmpCount;
	private JButton btnAddStaff;
	private JRadioButton rdbMale;
	private JRadioButton rdbFemale;
	private JLabel lblSalary;
	private JButton btnDeleteStaff;
	private Integer employees = 0;
	private JButton btnUpdateStaff;
	
	StaffBean sb = new StaffBean();
	PostBean pb = new PostBean();
	StaffDAO sdao = new StaffDAOImpl();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffFrame frame = new StaffFrame();
					frame.setLocationRelativeTo(null);
					frame.setTitle("Staff");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAddStaff.setEnabled(true);
				btnDeleteStaff.setEnabled(false);
				btnUpdateStaff.setEnabled(false);
			}
		});
		panel.setBounds(0, 0, 856, 406);
		panel.setBackground(new Color(93,173,226));
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAddStaff);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAddStaff);
			}
		});
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				String name = txtName.getText().toString();
				String rollnum = txtRollNum.getText().toString();
				String gender = "";
				
				if(rdbMale.isSelected()) {
					gender = rdbMale.getText().toString();
				}else {
					gender =rdbFemale.getText().toString();
				}
				
				sb.setName(name);
				sb.setRollnum(rollnum);
				sb.setGender(gender);
				sb.setPost(pb);
				
				Validation validate = new Validation();
			
				
				boolean isNameString = validate.nameValidate(sb.getName());
				boolean  unique = validate.isStringUnique(sb.getRollnum(), "Staff", "rollnum");
				
				if(isNameString == false) {
					JOptionPane.showMessageDialog(StaffFrame.this, "Invalid Name", "Staff View", JOptionPane.DEFAULT_OPTION);
				}else if(rollnum.isEmpty() || unique==false) {
					JOptionPane.showMessageDialog(StaffFrame.this, "Invalid Roll Number", "Staff View", JOptionPane.DEFAULT_OPTION);
				}
				else {
	
					
				int row = sdao.addNewStaff(sb.getName(), sb.getRollnum(), sb.getGender(), sb.getPost().getId());
				if(row ==1) {
					JOptionPane.showMessageDialog(StaffFrame.this, "Staff Added Successfully", "Admission View", JOptionPane.DEFAULT_OPTION);
				}
				else {
					JOptionPane.showMessageDialog(StaffFrame.this, "Staff Not Added", "Admission View", JOptionPane.ERROR_MESSAGE);
				}
				clear();
			}
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(StaffFrame.this, "Fill the Fields", "Admission View", JOptionPane.DEFAULT_OPTION);
				}
				populateTable();
			}
		});
		
		btnAddStaff.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_plus_26px.png")));
		btnAddStaff.setOpaque(false);
		btnAddStaff.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddStaff.setForeground(Color.WHITE);
		btnAddStaff.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnAddStaff.setContentAreaFilled(false);
		btnAddStaff.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAddStaff.setBounds(688, 89, 148, 45);
		panel.add(btnAddStaff);
		
		JLabel lblDashboard = new JLabel("Staff");
		lblDashboard.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_conference_foreground_selected_48px_1.png")));
		lblDashboard.setFont(new Font("Segoe Script", Font.BOLD, 23));
		lblDashboard.setForeground(new Color(0, 0, 0));
		lblDashboard.setBounds(364, 9, 148, 33);
		panel.add(lblDashboard);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 122, 446, 214);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setSelectionForeground(Color.WHITE);
				btnAddStaff.setEnabled(false);
				btnDeleteStaff.setEnabled(true);
				btnUpdateStaff.setEnabled(true);
				
				//Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				String name = (String) table.getValueAt(table.getSelectedRow(), 1);
				String rollnum = (String)table.getValueAt(table.getSelectedRow(), 2);
				String gender = (String) table.getValueAt(table.getSelectedRow(), 3);
				String post = (String) table.getValueAt(table.getSelectedRow(), 4);
				Integer salary = (Integer) table.getValueAt(table.getSelectedRow(), 5);
				
				txtName.setText(name);
				txtRollNum.setText(rollnum);
		
				if(gender.equalsIgnoreCase("Male")) {
					rdbMale.setSelected(true);
				}else {
					rdbFemale.setSelected(true);
				}
				cmboPost.setSelectedItem(post);
				lblSalary.setText(salary.toString());
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.BLUE);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(220,20,60));
		scrollPane.setViewportView(table);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setBounds(536, 139, 112, 20);
		panel.add(txtName);
		
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
				StaffFrame staffFrame = new StaffFrame();  
				staffFrame.setVisible(false);
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
		
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblName.setBounds(466, 142, 60, 14);
		panel.add(lblName);
		
		JLabel lblRollNum = new JLabel("Roll Num");
		lblRollNum.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblRollNum.setBounds(466, 183, 73, 14);
		panel.add(lblRollNum);
		
		txtRollNum = new JTextField();
		txtRollNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRollNum.setBounds(536, 180, 112, 20);
		panel.add(txtRollNum);
		
		rdbMale = new JRadioButton("Male");
		rdbMale.setFont(new Font("Segoe Script", Font.BOLD, 16));
		rdbMale.setBounds(466, 219, 73, 23);
		rdbMale.setSelected(true);
		rdbMale.setBackground(new Color(93,173,226));
		//rdbtnMale.setSelected(true);
		panel.add(rdbMale);
		
		rdbFemale = new JRadioButton("Female");
		rdbFemale.setFont(new Font("Segoe Script", Font.BOLD, 16));
		rdbFemale.setBackground(new Color(93, 173, 226));
		rdbFemale.setBounds(536, 219, 86, 23);
		panel.add(rdbFemale);
		
		ButtonGroup btngroup = new ButtonGroup();
		btngroup.add(rdbMale);
		btngroup.add(rdbFemale);
		
		JLabel lblPost = new JLabel("Post");
		lblPost.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblPost.setBounds(466, 275, 46, 14);
		panel.add(lblPost);
		
		cmboPost = new JComboBox();
		cmboPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(cmboPost.getSelectedItem().toString().equals("Teacher")) {
					pb.setId("2");
					lblSalary.setText("24000");
				}else {
					pb.setId("3");
					lblSalary.setText("12000");
				}
			}
		});
		cmboPost.setModel(new DefaultComboBoxModel(new String[] {"Teacher", "Peon"}));
		cmboPost.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboPost.setBounds(542, 272, 106, 20);
		panel.add(cmboPost);
		
		JLabel LabelSalary = new JLabel("Salary");
		LabelSalary.setFont(new Font("Segoe Script", Font.BOLD, 16));
		LabelSalary.setBounds(466, 339, 66, 20);
		panel.add(LabelSalary);
		
		JLabel lblTotalEmployees = new JLabel("Total Employees");
		lblTotalEmployees.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblTotalEmployees.setBounds(10, 363, 156, 19);
		panel.add(lblTotalEmployees);
		
		lblEmpCount = new JLabel("23");
		lblEmpCount.setForeground(Color.RED);
		lblEmpCount.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblEmpCount.setBounds(176, 363, 39, 19);
		panel.add(lblEmpCount);
		
		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_businessman_26px.png")));
		lblEmployee.setFont(new Font("Segoe Script", Font.BOLD, 17));
		lblEmployee.setBounds(31, 69, 135, 39);
		panel.add(lblEmployee);
		
		JComboBox cmboEmployee = new JComboBox();
		cmboEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffDAO dao = new StaffDAOImpl();
				String post = cmboEmployee.getSelectedItem().toString(); 
				if(post.equals("All")) {
					employees = dao.getAllEmployees();
					lblEmpCount.setText(employees.toString()); 
					populateTable();
				}else if(post.equalsIgnoreCase("Principal")) {
					employees = dao.getEmployees(post);
					lblEmpCount.setText(employees.toString());
					populateTableByPost(post);
				}else if(post.equalsIgnoreCase("Teacher")) {
					employees = dao.getEmployees(post);
					lblEmpCount.setText(employees.toString());
					populateTableByPost(post);
				}else {
					employees = dao.getEmployees(post);
					lblEmpCount.setText(employees.toString());
					populateTableByPost(post);
				}
			}
		});
		cmboEmployee.setForeground(Color.BLUE);
		cmboEmployee.setFont(new Font("Segoe Script", Font.BOLD, 19));
		cmboEmployee.setModel(new DefaultComboBoxModel(new String[] {"All","Principal", "Teacher", "Peon"}));
		cmboEmployee.setBounds(201, 80, 153, 23);
		panel.add(cmboEmployee);
		
		btnDeleteStaff = new JButton("Delete Staff");
		btnDeleteStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				
				int row = sdao.deleteStaff(id);
				if(row ==1) {
					JOptionPane.showMessageDialog(StaffFrame.this, "Staff Deleted Successful", "Staff View", JOptionPane.DEFAULT_OPTION);
				}
				else {
					JOptionPane.showMessageDialog(StaffFrame.this, "Staff Not Deleted", "Staff View", JOptionPane.ERROR_MESSAGE);
				}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(StaffFrame.this, "Please Select a record", "Staff View", JOptionPane.DEFAULT_OPTION);
				}
				clear();
				populateTable();

			}
		});
		btnDeleteStaff.setOpaque(false);
		btnDeleteStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnDeleteStaff);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnDeleteStaff);
			}
		});
		btnDeleteStaff.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteStaff.setForeground(Color.WHITE);
		btnDeleteStaff.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnDeleteStaff.setContentAreaFilled(false);
		btnDeleteStaff.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDeleteStaff.setBounds(688, 185, 148, 39);
		btnDeleteStaff.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_delete_26px.png")));
		panel.add(btnDeleteStaff);
		
		btnUpdateStaff = new JButton("Update Staff");
		btnUpdateStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				
				String name = txtName.getText().toString();
				String rollnum = txtRollNum.getText().toString();
				String gender = "";
				if(rdbMale.isSelected()) {
					gender= "Male";
				}else {
					gender = "Female";
				}
				
				int row = sdao.updateStudent(name, rollnum, gender, sb.getPost().getId(), id.toString());
				if(row==1) {
					JOptionPane.showMessageDialog(StaffFrame.this, "Staff Updated Successfully", "Staff View", JOptionPane.DEFAULT_OPTION);
				}else {
					JOptionPane.showMessageDialog(StaffFrame.this, "Staff Not Updated", "Staff View", JOptionPane.ERROR_MESSAGE);
				}
				clear();
				populateTable();
			}
		});
		btnUpdateStaff.setOpaque(false);
		btnUpdateStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnUpdateStaff);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnUpdateStaff);
			}
		});
		btnUpdateStaff.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdateStaff.setForeground(Color.WHITE);
		btnUpdateStaff.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnUpdateStaff.setContentAreaFilled(false);
		btnUpdateStaff.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnUpdateStaff.setBounds(688, 277, 148, 33);
		btnUpdateStaff.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_restart_26px.png")));
		panel.add(btnUpdateStaff);
		
		lblSalary = new JLabel("");
		lblSalary.setForeground(Color.RED);
		lblSalary.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblSalary.setBounds(543, 339, 105, 20);
		panel.add(lblSalary);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame loginframe = new LoginFrame();  
				StaffFrame staffFrame = new StaffFrame();
				staffFrame.setVisible(false);
				loginframe.setVisible(true);
				loginframe.setTitle("Login");  
				dispose();
			}
		});
		btnSignOut.setOpaque(false);
		btnSignOut.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_sign_out_26px_1.png")));
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
		btnSignOut.setBounds(715, 362, 118, 33);
		panel.add(btnSignOut);
		
		if(cmboPost.getSelectedItem().toString().equals("Teacher")) {
			lblSalary.setText("24000");
			pb.setId("2");
			sb.setPost(pb);
		}else {
			lblSalary.setText("12000");
			pb.setId("3");
			sb.setPost(pb);
		}
		
		populateTable();
		disableButton();

	}
	
	public void mouseClickedEffect(JButton button) {
		button.setForeground(Color.BLACK);
		button.setBorder(new LineBorder(Color.BLACK, 2 , true));
	}
	public void mouseReleasedEffect(JButton button) {
		button.setForeground(Color.WHITE);
		button.setBorder(new LineBorder(Color.WHITE,2,true));
	}
	
	public void disableButton() {
		btnAddStaff.setEnabled(true);
		btnDeleteStaff.setEnabled(false);
		btnUpdateStaff.setEnabled(false);
	}
	private void populateTable() {
		StaffDAO sdao = new	StaffDAOImpl();
		ResultSet rs = sdao.getDataResultSet();
		table.setModel(MyTableModel.buildTableModel(rs));
		table.getColumnModel().getColumn(1).setHeaderValue("Teacher");
		table.getColumnModel().getColumn(2).setHeaderValue("Roll Number");
	}
	private void populateTableByPost(String post) {
		StaffDAO sdao = new	StaffDAOImpl(); 
		ResultSet rs = sdao.getSpecificResultSet(post);
		table.setModel(MyTableModel.buildTableModel(rs));
	}
	public void clear() {
		txtName.setText("");
		txtRollNum.setText("");
	}
}
