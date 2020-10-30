package com.school.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.school.bean.LoginBean;
import com.school.connection.DBConnection;
import com.school.dao.LoginDAO;
import com.school.daoimpl.LoginDAOImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {
	

//#5DADE2 blue   93,173,226
//
//#37353D black  55,53,61

	Connection con = DBConnection.getConnection();
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblCredentials;
	private JLabel lblStatus;
	private JLabel lblShowpass;
	private int count=0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Login");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(93,173,226));
		panel.setForeground(Color.WHITE);
		panel.setBorder(null);
		panel.setBounds(0, 0, 349, 424);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("School Management\r\n");
		lblNewLabel.setForeground(new Color(55,53,61));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 24));
		lblNewLabel.setBounds(43, 50, 268, 40);
		panel.add(lblNewLabel);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setForeground(new Color(55,53,61));
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Segoe Script", Font.BOLD, 24));
		lblSystem.setBounds(43, 90, 268, 40);
		panel.add(lblSystem);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/school/images/icons8_student_male_48px_2.png")));
		lblNewLabel_1.setBounds(75, 193, 54, 56);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/school/images/icons8_graduate_48px.png")));
		label.setBounds(139, 193, 54, 56);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/school/images/icons8_to_do_48px.png")));
		label_1.setBounds(203, 193, 54, 56);
		panel.add(label_1);
		
		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Segoe UI Emoji", Font.BOLD | Font.ITALIC, 13));
		lblStatus.setBounds(10, 381, 159, 20);
		panel.add(lblStatus);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(55,53,61));
		panel_1.setBounds(347, 0, 377, 424);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		lblShowpass = new JLabel("");
		lblShowpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(count%2==0) {
				     txtPassword.setEchoChar((char)0); //password = JPasswordField
				}else {
					txtPassword.setEchoChar('*');
				}
				count++;
			}
		});
		lblShowpass.setBounds(334, 249, 21, 20);
		panel_1.add(lblShowpass);
		lblShowpass.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/school/images/icons8_eye_16px.png")));
		
		JLabel lblLogin = new JLabel("Sign in");
		lblLogin.setFont(new Font("Segoe Print", Font.PLAIN, 21));
		lblLogin.setForeground(new Color(93,173,226));
		lblLogin.setBounds(35, 34, 76, 28);
		panel_1.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(93,173,226));
		lblUsername.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblUsername.setBounds(35, 88, 106, 20);
		panel_1.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
				
			}
		});
		txtUsername.setForeground(Color.white);
		txtUsername.setOpaque(false);
		txtUsername.setBorder(null);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setBounds(35, 139, 320, 20);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 166, 320, 2);
		panel_1.add(separator);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(93, 173, 226));
		lblPassword.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblPassword.setBounds(35, 209, 106, 20);
		panel_1.add(lblPassword);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 280, 320, 2);
		panel_1.add(separator_1);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtPassword.setForeground(Color.white);
		txtPassword.setOpaque(false);
		txtPassword.setBorder(null);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(35, 249, 320, 20);
		panel_1.add(txtPassword);
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account");
		lblDontHaveAn.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDontHaveAn.setForeground(new Color(255, 255, 255));
		lblDontHaveAn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		lblDontHaveAn.setBounds(229, 345, 126, 20);
		panel_1.add(lblDontHaveAn);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				mouseClickedEffect(btnLogin);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnLogin);
			}
		});
		btnLogin.setForeground(new Color(255, 255, 255));
		// For Transpareting the Button
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorder(new LineBorder(Color.WHITE));

		btnLogin.setBounds(35, 320, 106, 28);
		panel_1.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setOpaque(false);
		btnRegister.setForeground(new Color(255, 153, 255));
		btnRegister.setContentAreaFilled(false);
		btnRegister.setBounds(239, 376, 116, 20);
		panel_1.add(btnRegister);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(239, 365, 116, 2);
		panel_1.add(separator_2);
		
		lblCredentials = new JLabel("");
		lblCredentials.setForeground(Color.RED);
		lblCredentials.setFont(new Font("Segoe UI Emoji", Font.BOLD | Font.ITALIC, 13));
		lblCredentials.setBounds(35, 379, 159, 20);
		panel_1.add(lblCredentials);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		if(con != null) {
			lblStatus.setText("Connected");
		}else {
			lblStatus.setText("Not Connected");
		}
		
		//setUndecorated(true);
	}
	
	public void mouseClickedEffect(JButton button) {
		button.setForeground(new Color(93,173,226));
		button.setBorder(new LineBorder(new Color(93,173,226)));
	}
	public void mouseReleasedEffect(JButton button) {
		button.setForeground(new Color(255, 255, 255));
		button.setBorder(new LineBorder(Color.WHITE));
	}
	
	public void login() {
		LoginBean loginbean = new LoginBean();
		if(!(txtUsername.getText().toString().isEmpty() || 
				txtPassword.getText().toString().isEmpty())){
			loginbean.setUsername(txtUsername.getText().toString().trim());
			loginbean.setPassword(txtPassword.getText().toString().trim());
			
			LoginDAO dao = new LoginDAOImpl();
			boolean check =  dao.letLoginin(loginbean);
			
			if(check == true) {
				JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful","Login Screen",
						JOptionPane.INFORMATION_MESSAGE);
				Dashboard dashboard = new Dashboard();
				LoginFrame loginframe = new LoginFrame();
				loginframe.setVisible(false);
				dashboard.setVisible(true);
				dashboard.setTitle("Doashboard");
				dispose();
				
			}else {
				lblCredentials.setText("Invalid Credentials");
			}
			
		}else {
			lblCredentials.setText("Please Fill the Fields");
		}
	}
	
}
