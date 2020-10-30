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

import com.school.bean.GroupBean;
import com.school.bean.GroupMemberBean;
import com.school.bean.ProjectBean;
import com.school.dao.AdmissionDAO;
import com.school.dao.GroupDAO;
import com.school.dao.ProjectDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.GroupDAOImpl;
import com.school.daoimpl.ProjectDAOImpl;
import com.school.util.MyTableModel;

public class GroupFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtSearch;
	private JComboBox cmboGroupLeader;
	private JComboBox cmboFirstMember;
	private JComboBox cmboSecondMember;
	private JComboBox cmboThirdMember;
	private JComboBox cmboProject;
	private JButton btnAddGroup;
	private JButton btnDeleteGroup;
	// private ComboBoxModel model;
	private Object element[];
	private int count = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupFrame frame = new GroupFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GroupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAddGroup.setEnabled(true);
				btnDeleteGroup.setEnabled(false);
				
			}
		});
		panel.setBounds(0, 0, 950, 439);
		panel.setBackground(new Color(93, 173, 226));
		contentPane.add(panel);
		panel.setLayout(null);

		btnAddGroup = new JButton("Add Group");
		btnAddGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnAddGroup);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnAddGroup);
			}
		});
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String groupLeader = cmboGroupLeader.getSelectedItem().toString();
					String firstMember = cmboFirstMember.getSelectedItem().toString();
					String secondMember = cmboSecondMember.getSelectedItem().toString();
					String thirdMember = cmboThirdMember.getSelectedItem().toString();
					String project = cmboProject.getSelectedItem().toString();
					
					GroupDAO gdao = new GroupDAOImpl();
					ProjectDAO pdao = new ProjectDAOImpl();

					// GroupBean gbean = new GroupBean();
					// ProjectBean pbean = new ProjectBean();

					if ((groupLeader.equals(firstMember) || groupLeader.equals(secondMember)
							|| groupLeader.equals(thirdMember) || firstMember.equals(secondMember)
							|| firstMember.equals(thirdMember) || secondMember.equals(thirdMember))) {
						JOptionPane.showMessageDialog(GroupFrame.this,
								"One Member can only be in one Group\nDifferent Students should be selected in a group",
								"GroupFrame View", JOptionPane.DEFAULT_OPTION);
						
					}else if(goupLeaderMatch(groupLeader) || goupMemberMatch(firstMember) ||
							goupMemberMatch(secondMember) || goupMemberMatch(thirdMember)) {
						
						JOptionPane.showMessageDialog(GroupFrame.this,
								"Any Member is Part of the Existing Groups",
								"GroupFrame View", JOptionPane.DEFAULT_OPTION);
					}else {
						String groupleaderId = String.valueOf(gdao.getGroupLeaderIdByName(groupLeader));
						String project_id = String.valueOf(pdao.getProjectIdByName(project));
						
						int row = gdao.addGroupLeader(groupleaderId, project_id);
						Integer gleaderid = gdao.getOriginalGroupLeaderId(groupleaderId);
						
						String firstmemberId = String.valueOf(gdao.getGroupMemberIdByName(firstMember));
						String secondmemberId = String.valueOf(gdao.getGroupMemberIdByName(secondMember));
						String thirdmemberId = String.valueOf(gdao.getGroupMemberIdByName(thirdMember));
						
						
						int row1 = gdao.addGroupFirstMember(firstMember, firstmemberId, gleaderid.toString());
						int row2 = gdao.addGroupSecondMember(secondMember, secondmemberId, gleaderid.toString());
						int row3 = gdao.addGroupthirdMember(thirdMember, thirdmemberId, gleaderid.toString());

						if(row1 == 1 && row2 == 1 && row3 == 1) {
							JOptionPane.showMessageDialog(GroupFrame.this, "New Group Added", "Group View",
									JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(GroupFrame.this, "Group Not Added", "Group View",
									JOptionPane.ERROR_MESSAGE);
						}
						
						
						
//						boolean leaderCheck = gdao.checkGroupLeaderIdExist(groupleaderId);
//						boolean memberCheck = gdao.checkGroupMemberIdExist(firstMember);
//						memberCheck = gdao.checkGroupMemberIdExist(secondmemberId);
//						memberCheck = gdao.checkGroupMemberIdExist(thirdmemberId);
//						
//						if (leaderCheck == true || memberCheck == true) {
//							JOptionPane.showMessageDialog(GroupFrame.this, "Group Already Exist or the Selected Member is in another Group", "Group View",
//									JOptionPane.DEFAULT_OPTION);
//						} else {
//							int row1 = gdao.addGroupFirstMember(groupleaderId, firstmemberId, project_id);
//							int row2 = gdao.addGroupSecondMember(groupleaderId, secondmemberId, project_id);
//							int row3 = gdao.addGroupthirdMember(groupleaderId, thirdmemberId, project_id);
//
//							if (row1 == 1 && row2 == 1 && row3 == 1) {
//								JOptionPane.showMessageDialog(GroupFrame.this, "Group Added Successfully", "Group View",
//										JOptionPane.DEFAULT_OPTION);
//
//								// cmboGroupLeader.removeAllItems();
//								// cmboFirstMember.removeAllItems();
//								// cmboSecondMember.removeAllItems();
//								// cmboThirdMember.removeAllItems();
//								//
//								// cmboGroupLeader.removeItem(cmboGroupLeader.getSelectedItem());
//								// cmboFirstMember.removeItem(cmboFirstMember.getSelectedItem());
//								// cmboSecondMember.removeItem(cmboSecondMember.getSelectedItem());
//								// cmboThirdMember.removeItem(cmboThirdMember.getSelectedItem());
//
//								// populateCmboBox(cmboGroupLeader);
//								// populateCmboBox(cmboFirstMember);
//								// populateCmboBox(cmboSecondMember);
//								// populateCmboBox(cmboThirdMember);
//
//							} 
						}
					}
				catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(GroupFrame.this, "Fill the Fields", "Group View",
							JOptionPane.DEFAULT_OPTION);
				}
				populateTable();
			}
		});

		btnAddGroup.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_plus_26px.png")));
		btnAddGroup.setOpaque(false);
		btnAddGroup.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddGroup.setForeground(Color.WHITE);
		btnAddGroup.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnAddGroup.setContentAreaFilled(false);
		btnAddGroup.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnAddGroup.setBounds(776, 161, 164, 45);
		panel.add(btnAddGroup);

		btnDeleteGroup = new JButton("Delete Group");
		btnDeleteGroup.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnDeleteGroup);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnDeleteGroup);
			}
		});
		btnDeleteGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					GroupDAO dao = new GroupDAOImpl();
					
					Integer groupLeader_id= (int) table.getValueAt(table.getSelectedRow(), 0);
					
					Integer gLeaderId = dao.getOriginalGroupLeaderId(groupLeader_id.toString());
					
					int row1 = dao.deleteGroup(gLeaderId);
					int row2 = dao.deleteGroupLeader(gLeaderId);
					
					System.out.println(row1+"   "+row2);
					
					if (row1 == 3 && row2 == 1) {
						JOptionPane.showMessageDialog(GroupFrame.this, "Group Deleted Successfully", "Group View",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(GroupFrame.this, "Group Not Deleted", "Group View",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(GroupFrame.this, "Please Select a record", "Group View",
							JOptionPane.DEFAULT_OPTION);
					ex.printStackTrace();
				}
				populateTable();
			}
		});
		btnDeleteGroup
				.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_delete_26px.png")));
		btnDeleteGroup.setOpaque(false);
		btnDeleteGroup.setForeground(Color.WHITE);
		btnDeleteGroup.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnDeleteGroup.setContentAreaFilled(false);
		btnDeleteGroup.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnDeleteGroup.setBounds(776, 227, 164, 46);
		panel.add(btnDeleteGroup);

		JLabel lblName = new JLabel("Search");
		lblName.setFont(new Font("Segoe Script", Font.BOLD, 19));
		lblName.setBounds(36, 126, 93, 17);
		panel.add(lblName);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 452, 271);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setSelectionForeground(Color.WHITE);
				btnAddGroup.setEnabled(false);
				btnDeleteGroup.setEnabled(true);
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.BLUE);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(220, 20, 60));
		scrollPane.setViewportView(table);
		
		////////////////////////
		
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
		btnSignOut.setBounds(822, 395, 118, 33);
		panel.add(btnSignOut);
	
		
		
		////////////////////

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
				GroupFrame groupFrame = new GroupFrame();
				groupFrame.setVisible(false);
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
		txtSearch.setBounds(139, 117, 164, 29);
		panel.add(txtSearch);
		txtSearch.setColumns(10);

		JLabel lblNewLabel = new JLabel("Groups");
		lblNewLabel.setBounds(404, 11, 162, 61);
		lblNewLabel
				.setIcon(new ImageIcon(GroupFrame.class.getResource("/com/school/images/icons8_user_groups_48px.png")));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 23));
		panel.add(lblNewLabel);

		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(GroupFrame.this,
						"According to the School Policy.\nIn One Group There can be maximum\n4 Group Members along with Group Leader ",
						"Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnInstructions.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnInstructions);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnInstructions);
			}
		});

		btnInstructions.setBounds(753, 11, 187, 33);
		btnInstructions.setOpaque(false);
		btnInstructions.setHorizontalAlignment(SwingConstants.LEFT);
		btnInstructions.setForeground(Color.WHITE);
		btnInstructions.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnInstructions.setContentAreaFilled(false);
		btnInstructions.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnInstructions.setIcon(new ImageIcon(GroupFrame.class.getResource("/com/school/images/icons8_note_32px.png")));

		//
		panel.add(btnInstructions);

		JLabel lblGroupLeader = new JLabel("Group Leader");
		lblGroupLeader.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblGroupLeader.setBounds(472, 162, 125, 17);
		panel.add(lblGroupLeader);

		JLabel lblFirstMember = new JLabel("First Member");
		lblFirstMember.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblFirstMember.setBounds(472, 214, 125, 17);
		panel.add(lblFirstMember);

		JLabel lblSecondMember = new JLabel("Second Member");
		lblSecondMember.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblSecondMember.setBounds(472, 258, 140, 17);
		panel.add(lblSecondMember);

		JLabel lblThirdMembers = new JLabel("Third Members");
		lblThirdMembers.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblThirdMembers.setBounds(472, 304, 125, 17);
		panel.add(lblThirdMembers);

		JLabel lblProject = new JLabel("Project");
		lblProject.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblProject.setBounds(472, 356, 65, 17);
		panel.add(lblProject);

		cmboGroupLeader = new JComboBox();
		cmboGroupLeader.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmboGroupLeader.setBounds(607, 161, 125, 20);
		panel.add(cmboGroupLeader);

		cmboFirstMember = new JComboBox();
		cmboFirstMember.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmboFirstMember.setBounds(607, 213, 125, 20);
		panel.add(cmboFirstMember);

		cmboSecondMember = new JComboBox();
		cmboSecondMember.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmboSecondMember.setBounds(607, 257, 125, 20);
		panel.add(cmboSecondMember);

		cmboThirdMember = new JComboBox();
		cmboThirdMember.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmboThirdMember.setBounds(607, 303, 125, 20);
		panel.add(cmboThirdMember);

		cmboProject = new JComboBox();
		cmboProject.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmboProject.setBounds(607, 355, 125, 20);
		panel.add(cmboProject);

		populateTable();

		// Populating All Combos
		populateCmboBox(cmboGroupLeader);
		populateCmboBox(cmboFirstMember);
		populateCmboBox(cmboSecondMember);
		populateCmboBox(cmboThirdMember);

		populateCmboBoxProjects(cmboProject);
		disableButton();

	}
	
	public void disableButton() {
		btnAddGroup.setEnabled(true);
		btnDeleteGroup.setEnabled(false);
	}

	private void populateCmboBox(JComboBox combobox) {
		GroupDAO dao = new GroupDAOImpl();
		List<String> names = dao.getAllGroupMembers();
		for (String n : names) {
			combobox.addItem(n);
		}
	}

	private void populateCmboBoxProjects(JComboBox combobox) {
		GroupDAO dao = new GroupDAOImpl();
		List<String> names = dao.getAllProjects();
		for (String n : names) {
			combobox.addItem(n);
		}
	}

	private void populateTable() {
		GroupDAO cdao = new GroupDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(MyTableModel.buildTableModel(rs));

		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Group Leader");
		table.getColumnModel().getColumn(2).setHeaderValue("Group Member");
		table.getColumnModel().getColumn(3).setHeaderValue("Project");
		
		
	}
	// Third Column GroupMember Names
	public boolean goupMemberMatch(String groupLeader) {
		for (int i = 1; i < table.getRowCount(); i++) {
			if(groupLeader.equals(table.getValueAt(i, 2).toString())){
				return true;
			}
		}
		return false;
	}
	// Second Column GroupLeader Names
	public boolean goupLeaderMatch(String groupLeader) {
		for (int i = 1; i < table.getRowCount(); i++) {
			if(groupLeader.equals(table.getValueAt(i, 1).toString())){
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
