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

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.school.dao.ResultDAO;
import com.school.daoimpl.ResultDAOImpl;
import com.school.util.MyTableModel;

public class ResultFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSearch;
	private JLabel lblPercentage;
	private JTextField txtStudent;
	private JLabel lblGrade;
	private JLabel lblStatus ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultFrame frame = new ResultFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ResultFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 688, 623);
		contentPane.add(panel);
		panel.setBackground(new Color(93, 173, 226));
		panel.setLayout(null);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame loginframe = new LoginFrame();  
				ResultFrame resultFrame = new ResultFrame();  
				resultFrame.setVisible(false);
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
		btnSignOut.setBounds(555, 576, 123, 33);
		panel.add(btnSignOut);
		
		JLabel lblNewLabel = new JLabel("Results");
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 23));
		lblNewLabel.setBounds(264, 11, 184, 67);
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_total_sales_48px.png")));
		panel.add(lblNewLabel);
		
		
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
				ResultFrame resultFrame = new ResultFrame(); 
				resultFrame.setVisible(false);
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
		scrollPane.setBounds(10, 147, 668, 263);
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
		table.setSelectionBackground(new Color(220, 20, 60));
		scrollPane.setViewportView(table);

		
		JLabel lblStudent = new JLabel("Search");
		lblStudent.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblStudent.setBounds(23, 101, 108, 30);
		panel.add(lblStudent);
		
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
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearch.setBounds(138, 101, 167, 31);
		panel.add(txtSearch);
		
		JLabel LABELPercentage = new JLabel("Percentage");
		LABELPercentage.setFont(new Font("Segoe Script", Font.BOLD, 18));
		LABELPercentage.setBounds(10, 514, 138, 30);
		panel.add(LABELPercentage);
		
		lblPercentage = new JLabel("");
		lblPercentage.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblPercentage.setBounds(158, 514, 118, 30);
		panel.add(lblPercentage);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtStudent.getText().toString().isEmpty()) {
					JOptionPane.showMessageDialog(ResultFrame.this, "Fill the Field","Alert",JOptionPane.INFORMATION_MESSAGE);
				}else {
					ResultDAO rdao = new ResultDAOImpl();
					String studentName = txtStudent.getText().toString();
					Double avg = rdao.getTotalAvgPercentage(studentName);
					
					if(avg == -1) {
						JOptionPane.showMessageDialog(ResultFrame.this, "No Student With such Name "+studentName,"Alert",JOptionPane.INFORMATION_MESSAGE);
					}else {
						if(avg>60) {
							lblPercentage.setForeground(Color.BLUE);
							lblGrade.setForeground(Color.BLUE);
							lblStatus.setForeground(Color.BLUE);
						}else {
							lblPercentage.setForeground(Color.RED);
							lblGrade.setForeground(Color.RED);
							lblStatus.setForeground(Color.RED);
						}
						lblPercentage.setText(avg.toString());
						
						if(avg>60 && avg <=70) {
							lblGrade.setText("C");
							lblStatus.setText("Pass");
						}else if(avg>70 && avg <85) {
							lblGrade.setText("B");
							lblStatus.setText("Pass");
						}else if(avg>=85 && avg <=100) {
							lblGrade.setText("A");
							lblStatus.setText("Pass");
						}else {
							lblGrade.setText("Null");
							lblStatus.setText("Fail");
						}
						
						
					}
				}
			}
		});
		btnCalculate.setFont(new Font("Segoe Script", Font.BOLD, 15));
		btnCalculate.setBounds(11, 429, 137, 23);
		panel.add(btnCalculate);
		
		txtStudent = new JTextField();
		txtStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStudent.setBounds(109, 471, 167, 23);
		panel.add(txtStudent);
		txtStudent.setColumns(10);
		
		JLabel lblStudent_1 = new JLabel("Student");
		lblStudent_1.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblStudent_1.setBounds(10, 472, 108, 23);
		panel.add(lblStudent_1);
		
		lblGrade = new JLabel("");
		lblGrade.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblGrade.setBounds(374, 514, 78, 30);
		panel.add(lblGrade);
		
		JLabel LABELGrad = new JLabel("Grade");
		LABELGrad.setFont(new Font("Segoe Script", Font.BOLD, 18));
		LABELGrad.setBounds(286, 514, 78, 30);
		panel.add(LABELGrad);

		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblStatus.setBounds(550, 514, 90, 30);
		panel.add(lblStatus);
		
		JLabel LabelStatus = new JLabel("Status");
		LabelStatus.setFont(new Font("Segoe Script", Font.BOLD, 18));
		LabelStatus.setBounds(462, 514, 78, 30);
		panel.add(LabelStatus);

		
		populateTable();
	}
	
	private void populateTable() {
		ResultDAO rdao = new ResultDAOImpl(); 
		ResultSet rs = rdao.getDataResultSet();
		table.setModel(MyTableModel.buildTableModel(rs));

		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Student");
		table.getColumnModel().getColumn(2).setHeaderValue("Subject");
		table.getColumnModel().getColumn(3).setHeaderValue("Percentage");
		
		
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
