package OOPScp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.xml.sax.Parser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class RegistrationPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationPage frame = new RegistrationPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrationPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration Form");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(553, 240, 279, 36);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 218, 1400, 12);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Email-ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(518, 359, 132, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("User Name");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(518, 305, 132, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_2.setBounds(518, 410, 132, 28);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Gender");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_3.setBounds(518, 471, 132, 28);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Age");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_4.setBounds(518, 529, 132, 28);
		contentPane.add(lblNewLabel_1_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addItem("Male");
		comboBox_1.addItem("Female");
		comboBox_1.addItem("Other");
		
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox_1.setBounds(708, 468, 188, 36);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_1_5 = new JLabel("Address");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_5.setBounds(518, 578, 132, 28);
		contentPane.add(lblNewLabel_1_5);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setBounds(708, 302, 188, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(708, 526, 188, 36);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(708, 575, 188, 36);
		contentPane.add(textField_2);
		
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(708, 356, 188, 36);
		contentPane.add(textField_3);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordField.setBounds(708, 408, 188, 35);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String email=textField_3.getText();
					String user=textField.getText();
					String pass=passwordField.getText();
					String gender=(String) comboBox_1.getSelectedItem();
					String patta=textField_2.getText();
					int age = Integer.parseInt(textField_1.getText());
					System.out.print(age);
					if(age<18)
					{
						JOptionPane.showMessageDialog(null,"Your are NOT eligible to become Voter");
					}
					else
					{
						
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
						PreparedStatement pst=(PreparedStatement) con.prepareStatement("insert into voter(voter_name,password,gender,email,phone,address) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
						
						
						pst.setString(1,user);
						pst.setString(2,pass);
						pst.setString(3,gender);
						pst.setString(4,email);
						pst.setInt(5,age);
						pst.setString(6, patta);
				
			 			textField.setText("");
			 			passwordField.setText("");
			 			textField_1.setText("");
			 			textField_2.setText("");
				 			
			 			if(user.isEmpty() || pass.isEmpty()||gender.isEmpty() || email.isEmpty() || patta.isEmpty())
			 			{
			 				JOptionPane.showMessageDialog(null,"Enter all fields");
			 			}
			 			else 
			 			{
			 				pst.executeUpdate();
				 			ResultSet generatedKeys = pst.getGeneratedKeys();
				 			if (generatedKeys.next())
				 			{
				 			    int voterId = generatedKeys.getInt(1);
				 			    JOptionPane.showMessageDialog(null,"Voter is added \nYour Voter-ID =" + voterId);
				 			}
			 			}
			 		
					}
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace(); 
				}
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnNewButton.setBounds(605, 621, 176, 36);
		contentPane.add(btnNewButton);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage1 L=new LoginPage1();
				L.setVisible(true);
				dispose();
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnLogin.setBounds(605, 667, 176, 36);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RegistrationPage.class.getResource("/CP_images/Adoobe_vote_For_India (1).jpg")));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_2.setBounds(0, 0, 1400, 210);
		contentPane.add(lblNewLabel_2);
	
	}
}
