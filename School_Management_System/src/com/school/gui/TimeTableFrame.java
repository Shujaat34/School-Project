package com.school.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.school.connection.DBConnection;
import com.school.dao.AdmissionDAO;
import com.school.dao.ClassDAO;
import com.school.dao.SubjectDAO;
import com.school.dao.TimeTableDAO;
import com.school.daoimpl.AdmissionDAOImpl;
import com.school.daoimpl.ClassDAOImpl;
import com.school.daoimpl.SubjectDAOImpl;
import com.school.daoimpl.TimeTableDAOImpl;
import com.school.util.MyTableModel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JScrollPane;

public class TimeTableFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cmboClass;
	private JComboBox cmboDay;
	private JComboBox cmboSubject;
	private JButton btnUpdate;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeTableFrame frame = new TimeTableFrame();
					frame.setLocationRelativeTo(null);
					frame.setTitle("Time Table");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TimeTableFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnUpdate.setEnabled(false);
			}
		});
		panel.setBackground(new Color(93,173,226));
		panel.setBounds(0, 0, 953, 424);
		contentPane.add(panel);
		
		JButton btnReport = new JButton("Generate TimeTable"); 
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = DBConnection.getConnection();
			        String reportPath = "C:\\\\Users\\\\shuja\\\\eclipse-workspace\\\\School_Management_System\\\\src\\\\com\\\\school\\\\gui\\\\TimeTable.jrxml";		  
			        HashMap parameters = new HashMap<>();
			        if(cmboClass.getSelectedItem().toString().equals("KG")) {
			        	parameters.put("Class","Nersary");
					
			        }else {
			        	parameters.put("Class", cmboClass.getSelectedItem().toString());
			        }
			        parameters.put("Day", cmboDay.getSelectedIndex()+1);
					JasperReport jr = JasperCompileManager.compileReport(reportPath);
			        JasperPrint jp = JasperFillManager.fillReport(jr,   parameters, con);
			        JasperViewer.viewReport(jp);
				

//					String path = "C:\\Users\\shuja\\eclipse-workspace\\School_Management_System\\src\\com\\school\\gui\\TimeTable.jrxml";
//					
//					Connection con = DBConnection.getConnection();
//					InputStream in = new FileInputStream(new File(path));
//					JasperDesign jd = JRXmlLoader.load(new FileInputStream(new File(path)) );
//					String sql = "Select * from timetable";
//					JRDesignQuery newQuery = new JRDesignQuery();
//					newQuery.setText(sql);
//					jd.setQuery(newQuery);
//					JasperReport jr = JasperCompileManager.compileReport(jd);
//					HashMap para = new HashMap();
//					JasperPrint j = JasperFillManager.fillReport(jr, para,con);
//					JasperViewer.viewReport(j,false);
//					OutputStream os = new FileOutputStream(new File("D:\\Birt Reports files Geeks\\"));
//					JasperExportManager.exportReportToPdfStream(j,os);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseClickedEffect(btnReport);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnReport);
			}
		});
		
		JLabel lblDay = new JLabel("Day"); 
		lblDay.setFont(new Font("Segoe Script", Font.BOLD, 19));
		lblDay.setBounds(290, 85, 65, 33);
		panel.add(lblDay);
		
		btnReport.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_report_file_32px_3.png")));
		btnReport.setOpaque(false);
		btnReport.setHorizontalAlignment(SwingConstants.LEFT);
		btnReport.setForeground(Color.WHITE);
		btnReport.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnReport.setContentAreaFilled(false);
		btnReport.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnReport.setBounds(722, 123, 221, 38);
		panel.add(btnReport);
		
		 
		cmboClass = new JComboBox();
		cmboClass.setForeground(Color.BLUE);
		cmboClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String CLASS = cmboClass.getSelectedItem().toString();
				
				if (CLASS.equals("KG")) {
					populateTable("Nersary", cmboDay.getSelectedIndex()+1); 
					return ;
				}
				populateTable(CLASS , cmboDay.getSelectedIndex()+1); 
			}
		});

		cmboClass.setFont(new Font("Segoe Script", Font.BOLD, 19));
		cmboClass.setBounds(94, 85, 155, 33);
		panel.add(cmboClass);
		
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
				TimeTableFrame timetable = new TimeTableFrame();   
				timetable.setVisible(false);
				dashboard.setVisible(true);
				timetable.setTitle("Dashboard");
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 532, 284);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setSelectionForeground(Color.WHITE);
				btnUpdate.setEnabled(true);
				String subject = (String) table.getValueAt(table.getSelectedRow(), 1);
				
				cmboSubject.setSelectedItem(subject);
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.BLUE);
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(220,20,60));
		scrollPane.setViewportView(table);
		
		cmboDay = new JComboBox(); 
		cmboDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cmboClass.getSelectedItem().toString().equals("KG")) {
					populateTable("Nersary", cmboDay.getSelectedIndex()+1); 
					return ;
				}
				populateTable(cmboClass.getSelectedItem().toString() , cmboDay.getSelectedIndex()+1); 
			}
		});
		cmboDay.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wesnesday", "Thursday", "Friday"}));
		cmboDay.setForeground(Color.BLUE);
		cmboDay.setFont(new Font("Segoe Script", Font.BOLD, 19));
		cmboDay.setBounds(365, 85, 171, 33);
		panel.add(cmboDay);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Segoe Script", Font.BOLD, 19));
		lblClass.setBounds(10, 85, 74, 31);
		panel.add(lblClass);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				TimeTableDAO tado = new TimeTableDAOImpl();
				SubjectDAO dao = new SubjectDAOImpl();
				int subjectId = dao.getSubjectIdByName(cmboSubject.getSelectedItem().toString());
			
				
				int row = tado.updateSubjectId(subjectId, id);
				if(row==1) {
					JOptionPane.showMessageDialog(TimeTableFrame.this, "TimeTable Updated Successfully", "TimeTable View", JOptionPane.DEFAULT_OPTION);
				}else {
					JOptionPane.showMessageDialog(TimeTableFrame.this, "TimeTable Not Updated", "TimeTable View", JOptionPane.ERROR_MESSAGE);
				}
				
				populateTable(cmboClass.getSelectedItem().toString() , cmboDay.getSelectedIndex()+1); 
			}
		});
		btnUpdate.setOpaque(false);
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setIcon(new ImageIcon(AdmissionFrame.class.getResource("/com/school/images/icons8_restart_26px.png")));
		btnUpdate.setFont(new Font("Segoe Script", Font.BOLD, 16));
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnUpdate.setBounds(722, 207, 221, 38);
		panel.add(btnUpdate);
		
		cmboSubject = new JComboBox();
		cmboSubject.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmboSubject.setBounds(552, 217, 145, 20);
		panel.add(cmboSubject);
		
		
		JLabel lblNewLabel = new JLabel("Time Table");
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/com/school/images/icons8_calendar_3_48px.png")));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 23));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(342, 11, 232, 41);
		panel.add(lblNewLabel);
		
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame loginframe = new LoginFrame();  
				TimeTableFrame timetableframe = new TimeTableFrame(); 
				timetableframe.setVisible(false);
				loginframe.setVisible(true);
				loginframe.setTitle("Login");  
				dispose();
			}
		});
		btnSignOut.setOpaque(false);
		btnSignOut.setIcon(new ImageIcon(TimeTableFrame.class.getResource("/com/school/images/icons8_sign_out_26px_1.png")));
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
		btnSignOut.setBounds(826, 380, 117, 33);
		panel.add(btnSignOut);
		
		populateTable("Nersary",1);
		populateComboBox();
		populateComboBoxSubjects();
		btnUpdate.setEnabled(false);

		
		
	}
	
	public void populateComboBox() {
		ClassDAO dao = new ClassDAOImpl();
		List <String> names = dao.getAllClasses();
		for(String n: names) {
			cmboClass.addItem(n);
		}
	}
	
	public void populateComboBoxSubjects() {
		SubjectDAO dao = new SubjectDAOImpl();
		List <String> names = dao.getAllSubjects();
		for(String n: names) {
			cmboSubject.addItem(n);
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
	
	private void populateTable(String CLASS , Integer Day) {
		TimeTableDAO tdao = new	TimeTableDAOImpl();
		ResultSet rs = tdao.getDataResultSet(CLASS,Day);
		table.setModel(MyTableModel.buildTableModel(rs));
		
		table.getColumnModel().getColumn(1).setHeaderValue("Subject");
		table.getColumnModel().getColumn(5).setHeaderValue("Period");
	}
}



/********
 *  ---------------For Printing Reports Code----------------
 *  Connection con = DBConnection.getConnection();
	String reportPath = "C:\\\\Users\\\\shuja\\\\eclipse-workspace\\\\School_Management_System\\\\src\\\\com\\\\school\\\\gui\\\\TimeTable.jrxml";			        
	JasperReport jr = JasperCompileManager.compileReport(reportPath);
	JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
	JasperViewer.viewReport(jp);
 * 
 * 
 ***/
