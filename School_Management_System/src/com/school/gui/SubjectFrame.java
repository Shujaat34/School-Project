package com.school.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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

import com.school.bean.StaffBean;
import com.school.bean.SubjectBean;
import com.school.dao.ClassDAO;
import com.school.dao.StaffDAO;
import com.school.dao.SubjectDAO;
import com.school.daoimpl.ClassDAOImpl;
import com.school.daoimpl.StaffDAOImpl;
import com.school.daoimpl.SubjectDAOImpl;
import com.school.util.MyTableModel;
import com.school.util.Validation;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SubjectFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSubject;
	private JTable table;
	private JButton btnDeleteSubject;
	private JButton btnAddSubject;
	private JButton btnUpdateSubject;
	private JComboBox cmboChapters;
	private JComboBox cmboTeachers;
	private JTextField txtSearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubjectFrame frame = new SubjectFrame();
					frame.setLocationRelativeTo(null);
					frame.setTitle("Subjects");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SubjectFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAddSubject.setEnabled(true);
				btnDeleteSubject.setEnabled(false);
				btnUpdateSubject.setEnabled(false);
			}
		});
		panel.setBounds(0, 0, 861, 401);
		panel.setBackground(new Color(93,173,226));
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtSubject = new JTextField(); 
		txtSubject.setText("Name");
		txtSubject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSubject.setBounds(545, 139, 118, 20);
		panel.add(txtSubject);
		
		
		JLabel lblName = new JLabel("Subject");
		lblName.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblName.setBounds(443, 142, 92, 14);
		panel.add(lblName);
		
		JLabel lblDashboard = new JLabel("Subjects");
		lblDashboard.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_book_shelf_48px.png")));
		lblDashboard.setFont(new Font("Segoe Script", Font.BOLD, 23));
		lblDashboard.setForeground(new Color(0, 0, 0));
		lblDashboard.setBounds(302, 11, 150, 48);
		panel.add(lblDashboard);
		
		 
		btnAddSubject = new JButton("Add Subject");
		btnAddSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAddSubject);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAddSubject);
			}
		});
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubjectDAO subDAO = new SubjectDAOImpl();

				SubjectBean sb = new SubjectBean();
				StaffBean stb = new StaffBean();
				try {
				String name = txtSubject.getText().toString();
				String chapters = cmboChapters.getSelectedItem().toString();
				String teacherName = cmboTeachers.getSelectedItem().toString();
				
				StaffDAO sdao = new StaffDAOImpl();
				Integer staff_id = sdao.getTeacherIdByName(teacherName);
				
				
				Validation validate = new Validation();
				boolean checkName = validate.nameValidate(name);
				boolean unique = validate.isStringUnique(name, "ssubject", "name");

				sb.setSubjectName(name);
				sb.setChapters(chapters);
				stb.setId(staff_id.toString());
				sb.setSb(stb);
				
				if(checkName == false || unique==false) {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Invalid Name or Already Exist", "Subject View", JOptionPane.DEFAULT_OPTION);
				}
				else {
	
					
				int row = subDAO.addNewSubject(sb.getSubjectName(), sb.getChapters(), sb.getSb().getId());
			
				if(row ==1) {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Subject Added Successfully", "Subject View", JOptionPane.DEFAULT_OPTION);
				}
				else {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Subject Not Added", "Subject View", JOptionPane.ERROR_MESSAGE);
				}
				clear();
			}
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Fill the Fields", "Subject View", JOptionPane.DEFAULT_OPTION);
				}
				populateTable();
			}
		});
		
		btnAddSubject.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_plus_26px.png")));
		btnAddSubject.setOpaque(false);
		btnAddSubject.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddSubject.setForeground(Color.WHITE);
		btnAddSubject.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnAddSubject.setContentAreaFilled(false);
		btnAddSubject.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAddSubject.setBounds(683, 131, 168, 38);
		panel.add(btnAddSubject);
		
		 
		btnUpdateSubject = new JButton("Update Subject");
		btnUpdateSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubjectDAO subdao = new SubjectDAOImpl();
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				
				String name = txtSubject.getText().toString();
				String chapters = cmboChapters.getSelectedItem().toString();
				String teacherName = cmboTeachers.getSelectedItem().toString();
				
				StaffDAO sdao = new StaffDAOImpl();
				Integer staff_id = sdao.getTeacherIdByName(teacherName);
				
				int row = subdao.updateSubject(name, chapters, staff_id.toString(), id.toString());
				if(row==1) {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Subject Updated Successfully", "Subject View", JOptionPane.DEFAULT_OPTION);
				}else {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Subject Not Updated", "Subject View", JOptionPane.ERROR_MESSAGE);
				}
				clear();
				populateTable();
			}
		});
		btnUpdateSubject.setOpaque(false);
		btnUpdateSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnUpdateSubject);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnUpdateSubject);
			}
		});
		btnUpdateSubject.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdateSubject.setForeground(Color.WHITE);
		btnUpdateSubject.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnUpdateSubject.setContentAreaFilled(false);
		btnUpdateSubject.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnUpdateSubject.setBounds(683, 264, 168, 38);
		btnUpdateSubject.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_restart_26px.png")));
		panel.add(btnUpdateSubject);
		
		 
		btnDeleteSubject = new JButton("Delete Subject");
		btnDeleteSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				SubjectDAO subdao = new SubjectDAOImpl();
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				
				int row = subdao.deleteStaff(id);
			
				if(row ==1) {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Subject Deleted Successful", "Subject View", JOptionPane.DEFAULT_OPTION);
				}
				else {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Subject Not Deleted", "Subject View", JOptionPane.ERROR_MESSAGE);
				}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(SubjectFrame.this, "Subject Select a record", "Subject View", JOptionPane.DEFAULT_OPTION);
				}
				clear();
				populateTable();

			}
		});
		btnDeleteSubject.setOpaque(false);
		btnDeleteSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnDeleteSubject);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnDeleteSubject);
			}
		});
		btnDeleteSubject.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteSubject.setForeground(Color.WHITE);
		btnDeleteSubject.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnDeleteSubject.setContentAreaFilled(false);
		btnDeleteSubject.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDeleteSubject.setBounds(683, 201, 168, 38);
		btnDeleteSubject.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_delete_26px.png")));
		panel.add(btnDeleteSubject);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 423, 260);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setSelectionForeground(Color.WHITE);
			
				btnAddSubject.setEnabled(false);
				btnDeleteSubject.setEnabled(true);
				btnUpdateSubject.setEnabled(true);
				
				String name = (String) table.getValueAt(table.getSelectedRow(), 1);
				Integer chapters = (Integer)table.getValueAt(table.getSelectedRow(), 2);
				String teacher = (String) table.getValueAt(table.getSelectedRow(), 3);
				
				txtSubject.setText(name);
				cmboChapters.setSelectedItem(chapters.toString());
				cmboTeachers.setSelectedItem(teacher);
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.BLUE);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(220,20,60));
		scrollPane.setViewportView(table);
		
		cmboChapters = new JComboBox();
		cmboChapters.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmboChapters.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8", "9", "10"}));
		cmboChapters.setBounds(545, 189, 118, 20);
		panel.add(cmboChapters);
		
		JLabel lblChapters = new JLabel("Chapters");
		lblChapters.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblChapters.setBounds(443, 191, 92, 17);
		panel.add(lblChapters);
		
		JLabel lblTeachers = new JLabel("Teachers");
		lblTeachers.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblTeachers.setBounds(443, 252, 92, 14);
		panel.add(lblTeachers);
		
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
				SubjectFrame subjectframe = new SubjectFrame();  
				subjectframe.setVisible(false);
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
		btnSignOut.setBounds(733, 357, 118, 33);
		panel.add(btnSignOut);
		
		
		cmboTeachers = new JComboBox();
		cmboTeachers.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmboTeachers.setBounds(545, 249, 118, 20);
		panel.add(cmboTeachers);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				DefaultTableModel myTable = (DefaultTableModel) table.getModel();
				String search = txtSearch.getText().toString();
				TableRowSorter <DefaultTableModel> tr = new TableRowSorter<>(myTable);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		txtSearch.setText("");
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearch.setBounds(102, 91, 118, 20);
		panel.add(txtSearch);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblSearch.setBounds(20, 94, 72, 17);
		panel.add(lblSearch);
		
		populateComboBox();
		populateTable();
		disableButton();
	}
	private void clear() {
		txtSubject.setText("");
	}
	public void disableButton() {
		btnAddSubject.setEnabled(true);
		btnDeleteSubject.setEnabled(false);
		btnUpdateSubject.setEnabled(false);
	}
	
	public void populateComboBox() {
		ClassDAO dao = new ClassDAOImpl();
		List <String> names = dao.getAllTeachers();
		for(String n: names) {
			cmboTeachers.addItem(n);
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
	private void populateTable() {
		SubjectDAO cdao = new	SubjectDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(MyTableModel.buildTableModel(rs));
		
		table.getColumnModel().getColumn(1).setHeaderValue("Subject");
		table.getColumnModel().getColumn(2).setHeaderValue("Chapters");
		table.getColumnModel().getColumn(3).setHeaderValue("Teacher");
	}
}
