package com.school.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.school.bean.AdmissionBean;
import com.school.bean.ClassBean;
import com.school.dao.AdmissionDAO;
import com.school.dao.ClassDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.ClassDAOImpl;
import com.school.util.Validation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AdmissionFrame extends JFrame {
	
	//#5DADE2 blue   93,173,226
	//
	//#37353D black  55,53,61

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSname;
	private JTextField txtFname;
	private JTextField txtPcontact;
	private JDateChooser dateChooser;
	private JComboBox comboBox;
	private JLabel lblTotalStudents;
	private JComboBox cmboClass;
	
	private JButton btnDeleteStudent;
	private JButton btnUpdateStudent;
	private JButton btnaddStudent;
	
	
	ClassDAO classDao = new ClassDAOImpl(); 
	
	private Integer students=0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmissionFrame frame = new AdmissionFrame();
					frame.setLocationRelativeTo(null);
					frame.setTitle("Admission");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	AdmissionDAO adao = new AdmissionDAOImpl();

	public AdmissionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1140, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 618, 259);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				table.setSelectionForeground(Color.WHITE);
				
				btnaddStudent.setEnabled(false);
				btnDeleteStudent.setEnabled(true);
				btnUpdateStudent.setEnabled(true);
				
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				AdmissionDAO dao = new AdmissionDAOImpl();
				
				AdmissionBean ab = dao.getDataById(id);
				txtSname.setText(ab.getName());
				txtFname.setText(ab.getFname());
				txtPcontact.setText(ab.getParentContact());
				dateChooser.setDate(ab.getDob());
				String CLASS = (String)table.getValueAt(table.getSelectedRow(), 5);
				comboBox.setSelectedItem(CLASS);
			
				
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.BLUE);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(220,20,60));
		scrollPane.setViewportView(table);		
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnaddStudent.setEnabled(true);
				btnDeleteStudent.setEnabled(false);
				btnUpdateStudent.setEnabled(false);
			}
		});
		panel.setBounds(0, 0, 1124, 461);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(93,173,226));
		
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
		btnSignOut.setBounds(997, 415, 117, 33);
		panel.add(btnSignOut);
		
		
		btnaddStudent = new JButton("New Student");
		btnaddStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnaddStudent);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnaddStudent);
			}
		});
		btnaddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdmissionBean ab = new AdmissionBean();
				AdmissionDAO adao = new AdmissionDAOImpl();
			
				ClassDAO cdao = new ClassDAOImpl();
				ClassBean cb = new ClassBean();
				try {
				String Sname = txtSname.getText().toString();
				String Fname = txtFname.getText().toString();
				String Pcontact = txtPcontact.getText().toString();
				
				Date date = dateChooser.getDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date); 
				Timestamp dob = new Timestamp(cal.getTimeInMillis());
				
				ab.setName(Sname);
				ab.setFname(Fname);
				ab.setParentContact(Pcontact);
				ab.setDob(dob);
				
				String standard = comboBox.getSelectedItem().toString();
				
				System.out.println(ab.getName()+" "+ab.getFname());
				Integer classID = cdao.getClassIdByName(standard);
				Integer studentsInClass = cdao.getStudents(standard);
				// because we add one more student in that class
				studentsInClass++;
				
				
				cb.setId(Integer.toString(classID));
				cb.setStudents(Integer.toString(studentsInClass));
				
				Validation validate = new Validation();
			
				
				
				boolean isSnameString = validate.nameValidate(ab.getName());
				boolean isFnameString = validate.nameValidate(ab.getFname());	
				boolean iscontactNumber = validate.isNumber(ab.getParentContact());
				boolean iscontactUnique = validate.isNumberUnique(ab.getParentContact(), "admission", "Parent_Contact");
				boolean contactValidate = validate.contactNumberValidation(ab.getParentContact());
					
				if(isSnameString == false) {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Invalid Student Name", "Admission View", JOptionPane.DEFAULT_OPTION);
				}else if(isFnameString == false) {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Invalid Father's Name", "Admission View", JOptionPane.DEFAULT_OPTION);
				}else if(iscontactNumber == false) {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Contact is not a Number", "Admission View", JOptionPane.DEFAULT_OPTION);
				}else if(iscontactUnique == false) {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Contact can't be Duplicate", "Admission View", JOptionPane.DEFAULT_OPTION);
				}else if(contactValidate==false) {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Contact should have 11 Digits", "Admission View", JOptionPane.DEFAULT_OPTION);
				}
				else {
				int row= adao.addNewStudent(ab.getName(), ab.getFname(), ab.getDob(), ab.getParentContact(), cb.getId());
				
				if(row ==1) {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Student Admitted Successfully", "Admission View", JOptionPane.DEFAULT_OPTION);
					Integer status = cdao.updateStudentsAfterAddnew(cb.getStudents(), standard);
					
				}
				else {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Student Not Admitted", "Admission View", JOptionPane.ERROR_MESSAGE);
				}
				clear();
			}
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Fill the Fields", "Admission View", JOptionPane.DEFAULT_OPTION);
				}
				populateTable();
			}
		});
		
		btnaddStudent.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_plus_26px.png")));
		btnaddStudent.setOpaque(false);
		btnaddStudent.setHorizontalAlignment(SwingConstants.LEFT);
		btnaddStudent.setForeground(Color.WHITE);
		btnaddStudent.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnaddStudent.setContentAreaFilled(false);
		btnaddStudent.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnaddStudent.setBounds(939, 158, 175, 46);
		panel.add(btnaddStudent);
		
		
		JLabel lblDashboard = new JLabel("Admission");
		lblDashboard.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_student_male_48px_2.png")));
		
		lblDashboard.setFont(new Font("Segoe Script", Font.BOLD, 23));
		lblDashboard.setForeground(new Color(0, 0, 0));
		lblDashboard.setBounds(419, 23, 212, 46);
		panel.add(lblDashboard);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setForeground(Color.BLACK);
		lblClass.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblClass.setBounds(22, 103, 67, 22);
		panel.add(lblClass);
		
		txtSname = new JTextField();
		txtSname.setText("Name");
		txtSname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSname.setBounds(780, 149, 129, 20);
		panel.add(txtSname);

		
		txtFname = new JTextField();
		txtFname.setText("Father's Name");
		txtFname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFname.setColumns(10);
		txtFname.setBounds(780, 194, 129, 20);
		panel.add(txtFname);
		
		txtPcontact = new JTextField();
		txtPcontact.setText("Parent's Contact");
		txtPcontact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPcontact.setColumns(10);
		txtPcontact.setBounds(780, 241, 129, 20);
		panel.add(txtPcontact);
		
		dateChooser = new JDateChooser();
		dateChooser.setToolTipText("DOB");
		dateChooser.setBounds(780, 290, 129, 19);
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(dateChooser);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblName.setBounds(636, 153, 119, 14);
		panel.add(lblName);
		
		JLabel lblFathersName = new JLabel("Father's Name");
		lblFathersName.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblFathersName.setBounds(636, 198, 134, 16);
		panel.add(lblFathersName);
		
		JLabel lblParentContact = new JLabel("Contact");
		lblParentContact.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblParentContact.setBounds(635, 245, 120, 14);
		panel.add(lblParentContact);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblDateOfBirth.setBounds(636, 295, 129, 14);
		panel.add(lblDateOfBirth);
		
		JLabel lblClass_1 = new JLabel("Class");
		lblClass_1.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblClass_1.setBounds(636, 339, 94, 14);
		panel.add(lblClass_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nersary", "KG", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setBounds(780, 335, 129, 20);
		panel.add(comboBox);
		
		btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnDeleteStudent);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnDeleteStudent);
			}
		});
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					String standard = (String)table.getValueAt(table.getSelectedRow(), 5);
					AdmissionDAO dao = new AdmissionDAOImpl();
					int row = dao.deleteStudent(id);
					if(row ==1) {
						ClassDAO cdao = new ClassDAOImpl();
						Integer studentsInClass = cdao.getStudents(standard);
						// because we add one more student in that class
						studentsInClass--;
						Integer status = cdao.updateStudentsAfterAddnew(studentsInClass.toString(), standard);
						
						JOptionPane.showMessageDialog(AdmissionFrame.this, "Student Deleted Successful", "Admission View", JOptionPane.DEFAULT_OPTION);
					}
					else {
						JOptionPane.showMessageDialog(AdmissionFrame.this, "Student Not Deleted", "Admission View", JOptionPane.ERROR_MESSAGE);
					}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(AdmissionFrame.this, "Please Select a record", "Admission View", JOptionPane.DEFAULT_OPTION);
						
					}
				
					clear();
				
					populateTable();
			}
		});
		btnDeleteStudent.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_delete_26px.png")));
		btnDeleteStudent.setOpaque(false);
		btnDeleteStudent.setForeground(Color.WHITE);
		btnDeleteStudent.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnDeleteStudent.setContentAreaFilled(false);
		btnDeleteStudent.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDeleteStudent.setBounds(939, 228, 175, 46);
		panel.add(btnDeleteStudent);
		  
		
		btnUpdateStudent = new JButton("Update Student");
		btnUpdateStudent.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdateStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnUpdateStudent);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnUpdateStudent);
			}
		});
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				String Sname = txtSname.getText().toString();
				String fname = txtFname.getText().toString();
				String contact = txtPcontact.getText().toString();
				
				Date date = dateChooser.getDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date); 
				Timestamp dob = new Timestamp(cal.getTimeInMillis());
				
				String standard = comboBox.getSelectedItem().toString();
				Integer class_id = classDao.getIdByName(standard);
				
				AdmissionDAO adao = new AdmissionDAOImpl();
				
				int row = adao.updateStudent(Sname, fname, dob, contact, class_id.toString(), id.toString());
				if(row==1) {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Student Updated Successfully", "Admission View", JOptionPane.DEFAULT_OPTION);
				}else {
					JOptionPane.showMessageDialog(AdmissionFrame.this, "Student Not Updated", "Admission View", JOptionPane.ERROR_MESSAGE);
				}
				clear();
				populateTable();
			}
		});
		
		btnUpdateStudent.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_restart_26px.png")));
		btnUpdateStudent.setOpaque(false);
		btnUpdateStudent.setForeground(Color.WHITE);
		btnUpdateStudent.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnUpdateStudent.setContentAreaFilled(false);
		btnUpdateStudent.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnUpdateStudent.setBounds(939, 290, 175, 46);
		panel.add(btnUpdateStudent);
		
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
				AdmissionFrame admissionFrame = new AdmissionFrame();
				admissionFrame.setVisible(false);
				dashboard.setVisible(true);
				dashboard.setTitle("Dashboard");
				dispose();
			}
		});
		btnBack.setOpaque(false);
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnBack.setBounds(10, 11, 95, 40);
		panel.add(btnBack);
		
		JLabel LabelTotalStudents = new JLabel("Total Students");
		LabelTotalStudents.setForeground(Color.BLACK);
		LabelTotalStudents.setFont(new Font("Segoe Script", Font.BOLD, 18));
		LabelTotalStudents.setBounds(10, 424, 153, 14);
		panel.add(LabelTotalStudents);
		
		lblTotalStudents = new JLabel();
		lblTotalStudents.setForeground(Color.RED);
		lblTotalStudents.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblTotalStudents.setBounds(165, 423, 153, 14);
		panel.add(lblTotalStudents);
	
		
		cmboClass = new JComboBox();
		cmboClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String standard = cmboClass.getSelectedItem().toString();
				if(standard.equals("All")) {
					students = adao.getAllStudentsCount();
					lblTotalStudents.setText(students.toString());
					populateTable();
				}else if(standard.equalsIgnoreCase("Nersary")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("KG")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("One")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("two")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("Three")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("four")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("five")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("Six")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("Seven")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("Eight")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else if(standard.equalsIgnoreCase("Nine")) {
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}else{
					students = classDao.getStudents(standard);
					lblTotalStudents.setText(students.toString());
					popultaTableByClass(standard);
				}
			}
		});
		cmboClass.setForeground(Color.BLUE);
		cmboClass.setModel(new DefaultComboBoxModel(new String[] {"All", "Nersary", "KG", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"}));
		cmboClass.setFont(new Font("Segoe Script", Font.BOLD, 20));
		cmboClass.setBounds(94, 99, 165, 30);
		panel.add(cmboClass);
		
		populateTable();
		
		
		if(cmboClass.getSelectedItem().toString().equals("All")) {
			Integer students = adao.getAllStudentsCount();
			lblTotalStudents.setText(students.toString());
		}
		
		disableButton();
		populateComboBox();
	}
	public void populateComboBox() {
		ClassDAO dao = new ClassDAOImpl();
		List <String> names = dao.getAllClasses();
		comboBox.addItem("All");
		for(String n: names) {
			comboBox.addItem(n);
		}
	}
	public void mouseClickedEffect(JButton button) {
		button.setForeground(Color.BLACK);
		button.setBorder(new LineBorder(Color.BLACK, 2 , true));
	}
	public void mouseReleasedEffect(JButton button) {
		button.setForeground(Color.WHITE);
		button.setBorder(new LineBorder(Color.WHITE,2,true));
	}
	
	private void clear() {
		txtSname.setText("");
		txtFname.setText("");
		txtPcontact.setText("");
	}
	private void populateTable() {
		AdmissionDAO cdao = new	AdmissionDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(buildTableModel(rs));
	}
	private void popultaTableByClass(String CLASS) {
		AdmissionDAO cdao = new	AdmissionDAOImpl();
		ResultSet rs = cdao.getSpecificResultSet(CLASS);
		table.setModel(buildTableModel(rs));
	}
	// For Data Population
	public static DefaultTableModel buildTableModel(ResultSet rs) {
		Vector <String> colNames=null;
		Vector <Vector<Object>> data = null;
		try {
			ResultSetMetaData metadata = rs.getMetaData();
			colNames = new Vector<String>();
			int colcount = metadata.getColumnCount();
			
			for(int col=1;col<=colcount;col++) {
				colNames.add(metadata.getColumnName(col));
			}
			data = new Vector <Vector<Object>>();
			
			while(rs.next()) {
				Vector<Object>vector = new Vector();
				for(int colindex = 1; colindex<=colcount;colindex++) {
					vector.add( rs.getObject(colindex));
				}
				data.add(vector);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new DefaultTableModel(data,colNames);
	}
	public void disableButton() {
		btnaddStudent.setEnabled(true);
		btnDeleteStudent.setEnabled(false);
		btnUpdateStudent.setEnabled(false);
	}
}

		//Alternate Color For Table
//		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//	        @Override
//	        public Component getTableCellRendererComponent(JTable table, 
//	                Object value, boolean isSelected, boolean hasFocus,
//	                int row, int column) {
//	            Component c = super.getTableCellRendererComponent(table, 
//	                value, isSelected, hasFocus, row, column);
//	            c.setBackground(row%2==0 ? Color.white : Color.black); 
//	            
//	            if(c.getBackground().equals(Color.black)) {
//	            	table.setForeground(Color.white);
//	            }else {
//	            	table.setForeground(Color.black);
//	            }
//	            return c;
//	        };
//	    });


