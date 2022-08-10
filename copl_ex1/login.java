package copl_ex1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;


public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public login() {
		setBackground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 12));
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 295);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(245, 245, 245));
		txtUsername.setToolTipText("");
		txtUsername.setBounds(249, 87, 233, 27);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBackground(new Color(245, 245, 245));
		txtPassword.setBounds(249, 125, 233, 27);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/copl_db","root","");
					Statement stmt =(Statement) con.createStatement();
					
					String sql = "Select * from users_tbl where username='"+ txtUsername.getText().toString()+"' and password='"+ txtPassword.getText()+"'";
					
					ResultSet rs = ((java.sql.Statement)stmt).executeQuery(sql);
					
					if(rs.next()) {
						String userName = txtUsername.getText();
						Dashboard frmtwo = new Dashboard();
						frmtwo.lbluserT.setText("User: " + userName);
						frmtwo.setVisible(true);
						dispose();
						
					JOptionPane.showMessageDialog(null, "Login successful...","Login Alert",2);
				}else if (txtUsername.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Username is required...","Login Warning",2);
				}else if (txtPassword.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Password is required...","Login Warning",2);
				}else {
					JOptionPane.showMessageDialog(null, "Username or password incorrect...","Login Warning",2);
				}
			}catch(Exception ex) {
				System.out.print(ex);
			}
				
			}
		});
		btnLogin.setBounds(249, 163, 80, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("EXIT");
		btnCancel.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(411, 163, 71, 23);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\ICONS COPL\\DASHBOARDPROFILE.png"));
		lblNewLabel.setBounds(10, 35, 144, 191);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_1.setBounds(164, 93, 91, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(164, 131, 80, 21);
		contentPane.add(lblNewLabel_1_1);
	}
}
