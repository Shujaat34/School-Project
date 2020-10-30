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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

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

import com.school.bean.AdmissionBean;
import com.school.bean.ProjectBean;
import com.school.dao.AdmissionDAO;
import com.school.dao.ProjectDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.ProjectDAOImpl;
import com.school.util.MyTableModel;

public class ProjectFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnAddProject;
	private JButton btnUpdateProject;
	private JButton btnDeleteProject;
	private JTextField txtProjectName;
	private JComboBox cmboProjectDuration;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectFrame frame = new ProjectFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProjectFrame() {
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
				btnAddProject.setEnabled(true);
				btnDeleteProject.setEnabled(false);
				btnUpdateProject.setEnabled(false);
			}
		});
		panel.setBounds(0, 0, 934, 421);
		panel.setBackground(new Color(93, 173, 226));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Project");
		lblName.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblName.setBounds(467, 159, 64, 29);
		panel.add(lblName);

		txtProjectName = new JTextField();
		txtProjectName.setText("Name");
		txtProjectName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProjectName.setBounds(559, 162, 171, 26);
		panel.add(txtProjectName);

		JLabel lblNewLabel = new JLabel("Projects");
		lblNewLabel.setBounds(384, 11, 162, 61);
		lblNewLabel
				.setIcon(new ImageIcon(GroupFrame.class.getResource("/com/school/images/icons8_prototype_48px.png")));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 23));
		panel.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 451, 251);
		panel.add(scrollPane);

		btnAddProject = new JButton("Add Project");
		btnAddProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAddProject);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAddProject);
			}
		});
		btnAddProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (txtProjectName.getText().toString().isEmpty()) {
						JOptionPane.showMessageDialog(ProjectFrame.this, "The Field cannot be Empty", "Project View",
								JOptionPane.DEFAULT_OPTION);
					} else {
						String project = txtProjectName.getText().toString();
						String duration = cmboProjectDuration.getSelectedItem().toString();

						ProjectBean pb = new ProjectBean(project, duration);
						ProjectDAO dao = new ProjectDAOImpl();

						int row = dao.addProject(pb.getProject(), pb.getDuration());

						if (row == 1) {
							JOptionPane.showMessageDialog(ProjectFrame.this, "Project Added Successfully",
									"Project View", JOptionPane.DEFAULT_OPTION);
						} else {
							JOptionPane.showMessageDialog(ProjectFrame.this, "Project Not Added", "Project View",
									JOptionPane.ERROR_MESSAGE);
						}
						txtProjectName.setText("");

					}

				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(ProjectFrame.this, "Fill the Fields", "Project View",
							JOptionPane.DEFAULT_OPTION);
				}
				populateTable();

			}
		});

		btnAddProject.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_plus_26px.png")));
		btnAddProject.setOpaque(false);
		btnAddProject.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddProject.setForeground(Color.WHITE);
		btnAddProject.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnAddProject.setContentAreaFilled(false);
		btnAddProject.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAddProject.setBounds(752, 159, 165, 45);
		panel.add(btnAddProject);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setSelectionForeground(Color.WHITE);

				btnAddProject.setEnabled(false);
				btnDeleteProject.setEnabled(true);
				btnUpdateProject.setEnabled(true);

				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				AdmissionDAO dao = new AdmissionDAOImpl();

				String project = (String) table.getValueAt(table.getSelectedRow(), 1);
				Integer duration = (int) table.getValueAt(table.getSelectedRow(), 2);

				txtProjectName.setText(project);
				cmboProjectDuration.setSelectedItem(duration.toString());

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
				ProjectFrame projectFrame = new ProjectFrame();
				projectFrame.setVisible(false);
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

		btnDeleteProject = new JButton("Delete Project");
		btnDeleteProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					ProjectDAO dao = new ProjectDAOImpl();
				
					boolean check = dao.checkIdExistInChild(id.toString());
					
					if(check) {
						JOptionPane.showMessageDialog(ProjectFrame.this, "Project Already Exist in another Table\nIt can not be deleted", "Project View",
								JOptionPane.ERROR_MESSAGE);
					}else {
						
						int row = dao.deleteProject(id);
						if (row == 1) {
							JOptionPane.showMessageDialog(ProjectFrame.this, "Project Deleted Successful", "Project View",
									JOptionPane.DEFAULT_OPTION);
						} else {
							JOptionPane.showMessageDialog(ProjectFrame.this, "Project Not Deleted", "Project View",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(ProjectFrame.this, "Project Select a record", "Project View",
							JOptionPane.DEFAULT_OPTION);
				}
				txtProjectName.setText("");
				populateTable();

			}
		});
		btnDeleteProject.setOpaque(false);
		btnDeleteProject.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteProject.setForeground(Color.WHITE);
		btnDeleteProject.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnDeleteProject.setContentAreaFilled(false);
		btnDeleteProject.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDeleteProject.setBounds(752, 312, 165, 45);
		btnDeleteProject
				.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_delete_26px.png")));
		panel.add(btnDeleteProject);

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
				ProjectFrame projectFrame = new ProjectFrame(); 
				projectFrame.setVisible(false);
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

		btnUpdateProject = new JButton("Update Project");
		btnUpdateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

				String project = txtProjectName.getText().toString();
				String duration = cmboProjectDuration.getSelectedItem().toString();

				ProjectBean pb = new ProjectBean(project, duration);
				ProjectDAO dao = new ProjectDAOImpl();
				
			
				int row = dao.updateProject(pb.getProject(), pb.getDuration(), id.toString());

				if (row == 1) {
					JOptionPane.showMessageDialog(ProjectFrame.this, "Project Updated Successfully", "Project View",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(ProjectFrame.this, "Project Not Updated", "Project View",
							JOptionPane.ERROR_MESSAGE);
				}
				txtProjectName.setText("");
				populateTable();
			}
		});
		btnUpdateProject.setOpaque(false);
		btnUpdateProject.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdateProject.setForeground(Color.WHITE);
		btnUpdateProject.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnUpdateProject.setContentAreaFilled(false);
		btnUpdateProject.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnUpdateProject.setBounds(752, 238, 165, 45);
		btnUpdateProject
				.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_restart_26px.png")));
		panel.add(btnUpdateProject);

		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblDuration.setBounds(467, 228, 110, 29);
		panel.add(lblDuration);

		cmboProjectDuration = new JComboBox();
		cmboProjectDuration.setModel(
				new DefaultComboBoxModel(new String[] { "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cmboProjectDuration.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboProjectDuration.setBounds(559, 232, 171, 20);
		panel.add(cmboProjectDuration);

		populateTable();
		disableButton();

	}
	public void disableButton() {
		btnAddProject.setEnabled(true);
		btnDeleteProject.setEnabled(false);
		btnUpdateProject.setEnabled(false);
	}
	private void populateTable() {
		ProjectDAO sdao = new ProjectDAOImpl();
		ResultSet rs = sdao.getDataResultSet();

		table.setModel(MyTableModel.buildTableModel(rs));
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Projects");
		table.getColumnModel().getColumn(2).setHeaderValue("Duration(Months)");
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
