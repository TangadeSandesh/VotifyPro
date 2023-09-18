package OOPScp;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.ImageIcon;

public class Voters extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Voters frame = new Voters();
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
	public Voters() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Voters-Dashboard");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(10, 240, 287, 36);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 223, 1396, 18);
		contentPane.add(separator);
		
		

		JLabel lblNewLabel1 = new JLabel("Enter Voter-ID");
		lblNewLabel1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel1.setBounds(70, 338, 173, 35);
		contentPane.add(lblNewLabel1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_1.setBounds(70, 383, 173, 37);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		Object[] rowdata=new Object[5];
		
		JButton btnNewButton = new JButton("Vote");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String voter_ID=textField_1.getText();
				String candi_ID=(String) rowdata[0];
				
				//if(Integer.parseInt(voter_ID)>-1)
				//{
				try
				{
					if(voter_ID.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Please Enter Voter-ID");
					
					}
					else
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");

						PreparedStatement pst =(PreparedStatement) con.prepareStatement("UPDATE candidate SET vote = COALESCE(vote, 0) + 1 WHERE candidate_id = ?");
						
						
						pst.setString(1, candi_ID);
						
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Voted successfully!");
						
					}
						
					}
				
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(639, 590, 173, 63);
		contentPane.add(btnNewButton);
		btnNewButton.setVisible(false);
		
		JButton btnBookOrders = new JButton("Select Candidate");
		btnBookOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");

					PreparedStatement pst =(PreparedStatement) con.prepareStatement("select * from candidate");
					
					boolean hasResults = pst.execute();
					
					if (hasResults)
					{
						ResultSet r = pst.getResultSet();
					
					DefaultTableModel tmodel=new DefaultTableModel();
					tmodel.addColumn("candi-ID");
					tmodel.addColumn("Name");
					tmodel.addColumn("Age");
					tmodel.addColumn("Gender");
					tmodel.addColumn("Party");
					
					Object[] rowdata=new Object[6];
					
					table=new JTable(tmodel);
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setBounds(380, 320, 630, 250);
			        getContentPane().add(scrollPane);
			        
			        
			        
			        while(r.next())
					{
						String ID=r.getString("candidate_id");
						String Name=r.getString("name");
						String Age=r.getString("age");
						String Gender=r.getString("gender");
						String Party=r.getString("party");
						
				 		
					    Object[] row= {ID,Name,Age,Gender,Party,};
						tmodel.addRow(row);	
					}
					
					JOptionPane.showMessageDialog(null, "Select Candidate to vote");
					
					
				}
				
					table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener()
					{
						public void valueChanged(ListSelectionEvent event) 
					    {
							if (!event.getValueIsAdjusting())
					        {
								int selectedRowIndex = table.getSelectedRow();
					            
					            if (selectedRowIndex != -1)
					            {
					            		TableModel model = table.getModel();
						           
						                for (int i = 0; i < rowdata.length; i++)
						                {
						                    rowdata[i] = model.getValueAt(selectedRowIndex, i);
						                }
						                
						                String selectedData = Arrays.toString(rowdata);
						                
						                //tested Successfully
						                System.out.println("Selected data: " + selectedData);
						                btnNewButton.setVisible(true);
						                
						                
 
						               
					            }
					            else 
					            {
					                System.out.println("No row selected");
					                JOptionPane.showMessageDialog(null, "No Candidate selected");
					            }
					            
					        }
						
					}
					
					
				}
					
					
			);
				
					
				
			}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnBookOrders.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBookOrders.setBounds(70, 446, 192, 49);
		contentPane.add(btnBookOrders);
		
		JButton btnBeCandidate = new JButton("Be Candidate");
		btnBeCandidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CandidateRegistration C=new CandidateRegistration();
				C.setVisible(true);
				dispose();
			}
		}); 
		btnBeCandidate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBeCandidate.setBounds(70, 518, 192, 49);
		contentPane.add(btnBeCandidate);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage1 L=new LoginPage1();
				L.setVisible(true);
				dispose();
			}
		});
		btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnLogout.setBounds(1248, 240, 108, 49);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Voters.class.getResource("/CP_images/online-voting-flag (5).jpg")));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1.setBounds(0, 0, 1396, 213);
		contentPane.add(lblNewLabel_1);

		
		
	}
}
