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
import java.sql.SQLException;
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

import com.school.dao.AdmissionDAO;
import com.school.dao.ClassDAO;
import com.school.dao.FeesDAO;
import com.school.dao.ProjectDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.ClassDAOImpl;
import com.school.daoimpl.FeesDAOImpl;
import com.school.daoimpl.ProjectDAOImpl;
import com.school.util.MyTableModel;

public class FeesFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSearch;
	private JButton btnDeleteFees;
	private JButton btnAddFees;
	private JLabel lblStudent;
	private JLabel lblMonth;
	private JComboBox cmboMonth;
	private JComboBox cmboStudent;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeesFrame frame = new FeesFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FeesFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAddFees.setEnabled(true);
				btnDeleteFees.setEnabled(false);
			}
		});
		panel.setBounds(0, 0, 915, 416);
		panel.setBackground(new Color(93, 173, 226));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFees = new JLabel("Fees");
		lblFees.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_transaction_48px.png")));
		lblFees.setFont(new Font("Segoe Script", Font.BOLD, 23));
		lblFees.setBounds(380, 11, 111, 51);
		panel.add(lblFees);
		
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
				FeesFrame feesFrame = new FeesFrame(); 
				feesFrame.setVisible(false);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 169, 459, 236);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setSelectionForeground(Color.WHITE);
				btnAddFees.setEnabled(false);
				btnDeleteFees.setEnabled(true);
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.BLUE);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(220, 20, 60));
		scrollPane.setViewportView(table);
		
		txtSearch = new JTextField();
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
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearch.setBounds(141, 108, 161, 25);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblSearch.setBounds(26, 110, 104, 25);
		panel.add(lblSearch);
		
		btnAddFees = new JButton("Add Fees"); 
		btnAddFees.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAddFees);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAddFees);
			}
		});
		btnAddFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {
	
					String studentName = cmboStudent.getSelectedItem().toString();
					String month = cmboMonth.getSelectedItem().toString();
					
					AdmissionDAO adao =new AdmissionDAOImpl();
					Integer admission_id = adao.getStudentIdbyName(studentName);
					
					FeesDAO fdao = new FeesDAOImpl();
					String amount = "1000";
					String status = "Paid";
					
					int row = fdao.addFees(amount, status, month, admission_id.toString());
					if(row==1) {
						JOptionPane.showMessageDialog(FeesFrame.this, "Fees Added", "Fees View",
								JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(FeesFrame.this, "Fees Not Added", "Fees View",
								JOptionPane.ERROR_MESSAGE);
					}
				
					
				
				populateTable();
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(FeesFrame.this, "No Such Student Exist", "Fees View",
						JOptionPane.ERROR_MESSAGE);
			}
			
			}
		});
 
		btnAddFees.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_plus_26px.png")));
		btnAddFees.setOpaque(false);
		btnAddFees.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddFees.setForeground(Color.WHITE);
		btnAddFees.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnAddFees.setContentAreaFilled(false);
		btnAddFees.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAddFees.setBounds(741, 169, 164, 45);
		panel.add(btnAddFees);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame loginframe = new LoginFrame();  
				FeesFrame feesFrame = new FeesFrame(); 
				feesFrame.setVisible(false);
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
		btnSignOut.setBounds(782, 372, 123, 33);
		panel.add(btnSignOut);
		
		btnDeleteFees = new JButton("Delete Fees");
		btnDeleteFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				FeesDAO dao  = new FeesDAOImpl();
				
				int row = dao.deleteFees(id);
				if (row == 1) {
					JOptionPane.showMessageDialog(FeesFrame.this, "Fees Deleted Successful", "Fees View",
							JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(FeesFrame.this, "Fees Not Deleted", "Fees View",
							JOptionPane.ERROR_MESSAGE);
				}
			
				populateTable();

			}
		});
		btnDeleteFees.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_delete_26px.png")));
		
		btnDeleteFees.setOpaque(false);
		btnDeleteFees.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteFees.setForeground(Color.WHITE);
		btnDeleteFees.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnDeleteFees.setContentAreaFilled(false);
		btnDeleteFees.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDeleteFees.setBounds(741, 268, 164, 45);
		panel.add(btnDeleteFees);
		
		lblStudent = new JLabel("Student");
		lblStudent.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudent.setBounds(479, 174, 65, 14);
		panel.add(lblStudent);
		
		lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMonth.setBounds(479, 222, 65, 14);
		panel.add(lblMonth);
		
		cmboMonth = new JComboBox();
		cmboMonth.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboMonth.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		cmboMonth.setBounds(570, 221, 141, 20);
		panel.add(cmboMonth);

		btnDeleteFees.setEnabled(false);
		
		cmboStudent = new JComboBox();
		cmboStudent.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboStudent.setBounds(570, 171, 141, 20);
		panel.add(cmboStudent);
		
		
		populateComboBox();
		populateTable();
	}

	public void populateComboBox() {
		AdmissionDAO dao = new AdmissionDAOImpl();
		List <String> names = dao.getAllStudents();
		for(String n: names) {
			cmboStudent.addItem(n);
		}
	}
	
	private void populateTable() {
		FeesDAO fdao = new FeesDAOImpl(); 
		ResultSet rs = fdao.getDataResultSet();
		table.setModel(MyTableModel.buildTableModel(rs));

		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Student");
		table.getColumnModel().getColumn(2).setHeaderValue("Amount");
		table.getColumnModel().getColumn(3).setHeaderValue("Stutus");
		table.getColumnModel().getColumn(4).setHeaderValue("Month");
		
		
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
