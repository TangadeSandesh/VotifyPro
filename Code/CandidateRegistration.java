package OOPScp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CandidateRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CandidateRegistration frame = new CandidateRegistration();
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
	public CandidateRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register for Candidature");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(60, 222, 375, 46);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 218, 1386, 2);
		contentPane.add(separator);
		
		
		JButton btnSeeElections = new JButton("Show Elections");
		btnSeeElections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");

					PreparedStatement pst =(PreparedStatement) con.prepareStatement("Select * from election");
					
					
					boolean hasResults = pst.execute();
					
					
					if (hasResults)
					{
						ResultSet r = pst.getResultSet();
					
						DefaultTableModel tmodel=new DefaultTableModel();
						tmodel.addColumn("Election-ID");
						tmodel.addColumn("Name");
						tmodel.addColumn("Election Date");
					
						table=new JTable(tmodel);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(710, 300, 530, 300);
				        getContentPane().add(scrollPane);
				        
				        while(r.next())
						{
							String ID=r.getString("e_id");
							String Name=r.getString("e_name");
							String date=r.getString("e_date");
						
						    Object[] row= {ID,Name,date};
							tmodel.addRow(row);	
						}
						
						JOptionPane.showMessageDialog(null, "These are Current Arranged Elections");
					
					}
					else 
		            {
		                System.out.println("No row selected");
		                JOptionPane.showMessageDialog(null, "No Elections arranged");
		            }
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
			}
		});
		
		btnSeeElections.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnSeeElections.setBounds(819, 245, 200, 46);
		contentPane.add(btnSeeElections);
		
		
		JLabel lblNewLabel_1 = new JLabel("Candidate Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(185, 303, 182, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Election-ID");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(185, 365, 182, 35);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Age");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_2.setBounds(185, 423, 182, 35);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Gender");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_3.setBounds(185, 479, 182, 35);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Party");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_4.setBounds(185, 539, 182, 35);
		contentPane.add(lblNewLabel_1_4);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField.setBounds(377, 304, 196, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(374, 366, 196, 35);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(374, 424, 196, 35);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(377, 540, 196, 35);
		contentPane.add(textField_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
		comboBox.setBounds(377, 480, 196, 35);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String name=textField.getText();
					String eid=textField_1.getText();
					String age=textField_2.getText();
					String gender=(String) comboBox.getSelectedItem();
					String party=textField_3.getText();
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
					PreparedStatement pst=(PreparedStatement) con.prepareStatement("insert into candidate(e_id,name,age,gender,party,status) values(?,?,?,?,?,\"pending\")");
					
//					pst.setInt(1,2);
					pst.setString(1,eid);
					pst.setString(2,name);
					pst.setString(3,age);
					pst.setString(4,gender);
					pst.setString(5,party);
					
					
		 			
		 			
		 			textField.setText("");
		 			textField_1.setText("");
		 			textField_2.setText("");
		 			textField_3.setText("");
		 			
		 			if(name.isEmpty() || eid.isEmpty()||gender.isEmpty() || age.isEmpty()||party.isEmpty())
		 			{
		 				
		 				JOptionPane.showMessageDialog(null,"Enter all fields");
		 			}
		 			else
		 			{
		 				pst.executeUpdate();
		 				JOptionPane.showMessageDialog(null,"Candidate Registered Successfully");
		 			}

				}
				catch(Exception e1)
				{
					e1.printStackTrace();				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnNewButton.setBounds(324, 601, 126, 46);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Voters V=new Voters();
				V.setVisible(true);
				dispose();
				
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnBack.setBounds(324, 657, 126, 46);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(CandidateRegistration.class.getResource("/CP_images/online-voting-flag (5).jpg")));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_2.setBounds(0, 0, 1400, 212);
		contentPane.add(lblNewLabel_2);
		
	}
		
	}
