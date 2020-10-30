package com.school.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.school.dao.AdmissionDAO;
import com.school.dao.ClassDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.ClassDAOImpl;
import com.school.util.MyTableModel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class ClassFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassFrame frame = new ClassFrame();
					frame.setLocationRelativeTo(null);
					frame.setTitle("Class");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClassFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 529, 399);
		panel.setBackground(new Color(93,173,226));
		contentPane.add(panel);
		
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
		btnSignOut.setBounds(376, 355, 123, 33);
		panel.add(btnSignOut);
		
		JLabel lblName = new JLabel("Class Teacher");
		lblName.setFont(new Font("Segoe Script", Font.BOLD, 19));
		lblName.setBounds(36, 98, 152, 17);
		panel.add(lblName);
		
		comboBox = new JComboBox();
		comboBox.setForeground(Color.BLUE);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().toString().equals("All")) {
					populateTable();
					return;
				}
				String teacher = comboBox.getSelectedItem().toString();
				populateTableByTeacher(teacher);
			}
		});
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nersary", "KG", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"}));
		comboBox.setFont(new Font("Segoe Script", Font.BOLD, 19));
		comboBox.setBounds(198, 96, 138, 20);
		panel.add(comboBox);
		
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
				ClassFrame classFrame = new ClassFrame(); 
				classFrame.setVisible(false);
				dashboard.setVisible(true);
				classFrame.setTitle("Dashboard");
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
		
		JLabel lblDashboard = new JLabel("Class");
		lblDashboard.setIcon(new ImageIcon(ClassFrame.class.getResource("/com/school/images/icons8_book_48px.png")));
		lblDashboard.setFont(new Font("Segoe Script", Font.BOLD, 23));
		lblDashboard.setBounds(194, 11, 123, 48);
		panel.add(lblDashboard);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 497, 191);
		panel.add(scrollPane);
		
		table=new JTable();
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
		populateTable();
		populateComboBox();
		
	}
	
	public void populateComboBox() {
		ClassDAO dao = new ClassDAOImpl();
		List <String> names = dao.getAllTeachers();
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
	private void populateTable() {
		ClassDAO cdao = new	ClassDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(MyTableModel.buildTableModel(rs));
		
		table.getColumnModel().getColumn(3).setHeaderValue("Teacher");
	}
	private void populateTableByTeacher(String teacher) {
		ClassDAO cdao = new	ClassDAOImpl();
		ResultSet rs = cdao.getSpecificResultSet(teacher);
		table.setModel(MyTableModel.buildTableModel(rs));
		table.getColumnModel().getColumn(3).setHeaderValue("Teacher");
	}
	
}
