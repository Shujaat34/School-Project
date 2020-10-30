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

import com.school.bean.ProjectBean;
import com.school.dao.AdmissionDAO;
import com.school.dao.PeriodDAO;
import com.school.dao.ProjectDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.PeriodDAOImpl;
import com.school.daoimpl.ProjectDAOImpl;
import com.school.util.MyTableModel;

public class PeriodFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnAddPeriod;
	private JButton btnUpdatePeriod;
	private JButton btnDeletePeriod;
	private JTextField txtPeriod;
	private JComboBox cmboPeriodDuration;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeriodFrame frame = new PeriodFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PeriodFrame() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 460);
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
		panel.setBounds(0, 0, 934, 421);
		panel.setBackground(new Color(93, 173, 226));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Period");
		lblName.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblName.setBounds(467, 159, 64, 29);
		panel.add(lblName);

		txtPeriod = new JTextField();
		txtPeriod.setText("Name");
		txtPeriod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPeriod.setBounds(559, 162, 171, 26);
		panel.add(txtPeriod);

		JLabel lblNewLabel = new JLabel("Periods");
		lblNewLabel.setBounds(384, 11, 162, 61);
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_time_48px.png")));
	lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 23));
		panel.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 451, 251);
		panel.add(scrollPane);

		btnAddPeriod = new JButton("Add Period");
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
				
				try {
					if (txtPeriod.getText().toString().isEmpty()) {
						JOptionPane.showMessageDialog(PeriodFrame.this, "The Field cannot be Empty", "Period View",
								JOptionPane.DEFAULT_OPTION);
					} else {
						
						String period = txtPeriod.getText().toString();
						String duration = cmboPeriodDuration.getSelectedItem().toString();
						boolean check =  periodExist(period);
						if(check == false) {
							PeriodDAO dao = new PeriodDAOImpl();
							int row = dao.addPeriod(period, duration);
							if (row == 1) {
								JOptionPane.showMessageDialog(PeriodFrame.this, "Period Added Successfully",
										"Period View", JOptionPane.DEFAULT_OPTION);
							} else {
								JOptionPane.showMessageDialog(PeriodFrame.this, "Period Not Added", "Period View",
										JOptionPane.ERROR_MESSAGE);
							}
							txtPeriod.setText("");
						}else {	
							JOptionPane.showMessageDialog(PeriodFrame.this, "Period Already Exist",
									"Period View", JOptionPane.DEFAULT_OPTION);
						}

					}

				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(PeriodFrame.this, "Fill the Fields", "Project View",
							JOptionPane.DEFAULT_OPTION);
				}
				populateTable();

			}
		});

		btnAddPeriod.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_plus_26px.png")));
		btnAddPeriod.setOpaque(false);
		btnAddPeriod.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddPeriod.setForeground(Color.WHITE);
		btnAddPeriod.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnAddPeriod.setContentAreaFilled(false);
		btnAddPeriod.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAddPeriod.setBounds(752, 159, 165, 45);
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
				

				String project = (String) table.getValueAt(table.getSelectedRow(), 1);
				Integer duration = (int) table.getValueAt(table.getSelectedRow(), 2);

				txtPeriod.setText(project);
				cmboPeriodDuration.setSelectedItem(duration.toString());

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
				PeriodFrame periodFrame = new PeriodFrame(); 
				periodFrame.setVisible(false);
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
		btnSignOut.setBounds(799, 377, 118, 33);
		panel.add(btnSignOut);

		btnDeletePeriod = new JButton("Delete Period");
		btnDeletePeriod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					PeriodDAO dao = new PeriodDAOImpl();

					int row = dao.deletePeriod(id);
					if (row == 1) {
						JOptionPane.showMessageDialog(PeriodFrame.this, "Period Deleted Successful", "Period View",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(PeriodFrame.this, "Period Not Deleted", "Period View",
								JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(PeriodFrame.this, "Select a Period", "Period View",
							JOptionPane.DEFAULT_OPTION);
				}
				txtPeriod.setText("");
				populateTable();

			}
		});
		btnDeletePeriod.setOpaque(false);
		btnDeletePeriod.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeletePeriod.setForeground(Color.WHITE);
		btnDeletePeriod.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnDeletePeriod.setContentAreaFilled(false);
		btnDeletePeriod.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDeletePeriod.setBounds(752, 312, 165, 45);
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
				PeriodFrame periodFrame = new PeriodFrame();  
				periodFrame.setVisible(false);
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

		btnUpdatePeriod = new JButton("Update Period");
		btnUpdatePeriod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

				String period = txtPeriod.getText().toString(); 
				String duration = cmboPeriodDuration.getSelectedItem().toString();
				PeriodDAO dao = new PeriodDAOImpl();
				
				int row = dao.updatePeriod(period, duration, id.toString());

				if (row == 1) {
					JOptionPane.showMessageDialog(PeriodFrame.this, "Period Updated Successfully", "Period View",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(PeriodFrame.this, "Period Not Updated", "Period View",
							JOptionPane.ERROR_MESSAGE);
				}
				txtPeriod.setText("");
				populateTable();
			}
		});
		btnUpdatePeriod.setOpaque(false);
		btnUpdatePeriod.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdatePeriod.setForeground(Color.WHITE);
		btnUpdatePeriod.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnUpdatePeriod.setContentAreaFilled(false);
		btnUpdatePeriod.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnUpdatePeriod.setBounds(752, 238, 165, 45);
		btnUpdatePeriod
				.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_restart_26px.png")));
		panel.add(btnUpdatePeriod);

		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblDuration.setBounds(467, 228, 110, 29);
		panel.add(lblDuration);

		cmboPeriodDuration = new JComboBox();
		cmboPeriodDuration.setModel(
				new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		cmboPeriodDuration.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboPeriodDuration.setBounds(559, 232, 171, 20);
		panel.add(cmboPeriodDuration);

		populateTable();
		disableButton();

	}
	public void disableButton() {
		btnAddPeriod.setEnabled(true);
		btnDeletePeriod.setEnabled(false);
		btnUpdatePeriod.setEnabled(false);  
	}
	private void populateTable() {
		PeriodDAO pdao = new PeriodDAOImpl(); 
		ResultSet rs = pdao.getDataResultSet();

		table.setModel(MyTableModel.buildTableModel(rs));
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Period");
		table.getColumnModel().getColumn(2).setHeaderValue("Duration(Hours)");
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
