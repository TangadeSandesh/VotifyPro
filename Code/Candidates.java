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

public class Candidates extends JFrame {

	private JPanel contentPane;
 	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Candidates frame = new Candidates();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	);
}

	/**
	 * Create the frame.
	 */
	public Candidates()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Candidates");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(133, 248, 179, 48);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 223, 1401, 15);
		contentPane.add(separator);
		

		DefaultTableModel tablemodel=new DefaultTableModel();
		tablemodel.addColumn("Candidate-ID");
		tablemodel.addColumn("Election-ID");
		tablemodel.addColumn("Candidate Name");
		tablemodel.addColumn("Age");
		tablemodel.addColumn("Gender");
		tablemodel.addColumn("Party");
		tablemodel.addColumn("Status");
		
		Object[] rowdata=new Object[6];
		
		table=new JTable(tablemodel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(467, 317, 872, 253);
        getContentPane().add(scrollPane);
		
	
		JButton btnApprove = new JButton("Accept");
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int selectedRow = table.getSelectedRow();
				
				if (selectedRow != -1) 
				{
				    // get the candidate ID from the selected row
				    String cand_Id = (String) table.getValueAt(selectedRow, 0);

				 // update the database with the approval status
				    try {
				    	
				    	Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
						
				        PreparedStatement pst = con.prepareStatement("update candidate set status= ? WHERE candidate_id = ?");
				        pst.setString(1, "Accepted");
				        pst.setString(2, cand_Id);
				        
				        pst.executeUpdate();
				        
				    } 
				    catch (Exception e1) 
				    {
				        e1.printStackTrace();
				    }

				    tablemodel.removeRow(selectedRow);
				    
				    JOptionPane.showMessageDialog(null, "Application request accepted.");
					
				    
				}    
				else
				{
					JOptionPane.showMessageDialog(null, "Please select a Application to Accept.");
				}
				
				
			}
		});
		btnApprove.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnApprove.setBounds(830, 580, 179, 53);
		contentPane.add(btnApprove);
		btnApprove.setVisible(false);
		
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int selectedRow = table.getSelectedRow();
				
				System.out.print(selectedRow);
				
				if (selectedRow != -1) 
				{
				    // get the candidate ID from the selected row
				    String cand_Id = (String) table.getValueAt(selectedRow, 0);

				 // update the database with the approval status
				    try {
				    	
				    	Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
						
				        PreparedStatement pst = con.prepareStatement("delete from candidate where candidate_id = ?");
				        
				        pst.setString(1, cand_Id);
				        
				        pst.executeUpdate();
				        
				    } 
				    catch (Exception e1) 
				    {
				        e1.printStackTrace();
				    }

				    tablemodel.removeRow(selectedRow);
				    
				    JOptionPane.showMessageDialog(null, "Candidate Deleted Successfully.");
					
				    
				}    
				else
				{
					JOptionPane.showMessageDialog(null, "Please select Candidate to delete.");
				}
				
				
			}
		});
		btnNewButton_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1_2.setBounds(1116, 582, 132, 48);
		contentPane.add(btnNewButton_1_2);
		btnNewButton_1_2.setVisible(false);
		
		
		JButton btnNewButton_1_1 = new JButton("Update");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int selectedRow = table.getSelectedRow();
				
				System.out.print(selectedRow);
				
				if (selectedRow != -1) 
				{
				    // get the candidate ID from the selected row
				    String cand_Id = (String) table.getValueAt(selectedRow, 0);
				    String name= (String) table.getValueAt(selectedRow, 2);
				    String age = (String) table.getValueAt(selectedRow, 3);
				    String gender= (String) table.getValueAt(selectedRow, 4);
				    String party = (String) table.getValueAt(selectedRow, 5);

				    // update the database with the approval status
				    try {
				    	
				    	Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
						
				        PreparedStatement pst = con.prepareStatement("update candidate set name= ?, age= ?, gender=?, party= ? where candidate_id= ?");
				        
				        
				        pst.setString(1, name);
				        pst.setString(2, age);
				        pst.setString(3, gender);
				        pst.setString(4, party);
				        pst.setString(5, cand_Id);
				        
				        pst.executeUpdate();
				        
				    } 
				    catch (Exception e1) 
				    {
				        e1.printStackTrace();
				    }

				    
				    
				    JOptionPane.showMessageDialog(null, "Candidate updated Successfully.");
					
				    
				}    
				else
				{
					JOptionPane.showMessageDialog(null, "Please select Candidate to delete.");
				}
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(576, 582, 132, 48);
		contentPane.add(btnNewButton_1_1);
		btnNewButton_1_1.setVisible(false);
		
		
		JButton btnCheckOrders = new JButton("Check Applications");
		btnCheckOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
				
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");

					PreparedStatement pst =(PreparedStatement) con.prepareStatement("Select * from candidate where status=\"pending\"");
					
			        ResultSet r = pst.executeQuery();
			       
					

			        	while(r.next())
			        	{
			        		String ID=r.getString("candidate_id");
			        		String Eid=r.getString("e_id");
							String Name=r.getString("Name");
							String Age=r.getString("age");
							String Gender=r.getString("gender");
							String Party=r.getString("party");
							String Status=r.getString("status");
							
						    Object[] row= {ID,Eid,Name,Age,Gender,Party,Status};
							tablemodel.addRow(row);
							
						}
			        	
			        	if(table.getRowCount() == 0)
						{
							System.out.println("No Applications Available");
							JOptionPane.showMessageDialog(null,"No Applications Available");
							return;
						}
			        	
			        	JOptionPane.showMessageDialog(null,"Click on Row to Accept Application");
			        
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
							                
								            btnApprove.setVisible(true);
								            
							               
					            }
					            else 
					            {
					                System.out.println("No row selected");
					            }
						
						     }
						    }
						}
					 );
					}
					catch(Exception e5)
					{
						e5.printStackTrace();
						
					}
			}
		});
		btnCheckOrders.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCheckOrders.setBounds(110, 354, 214, 42);
		contentPane.add(btnCheckOrders);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Manager M=new Manager();
				M.setVisible(true); 
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBounds(150, 582, 132, 48);
		contentPane.add(btnNewButton_1);
		
		JButton btnAllCandidates = new JButton("All Candidates");
		btnAllCandidates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
	
					PreparedStatement pst =(PreparedStatement) con.prepareStatement("Select * from candidate");
					
			        ResultSet r = pst.executeQuery();
			        
			        	while(r.next())
			        	{
			        		String ID=r.getString("candidate_id");
			        		String Eid=r.getString("e_id");
							String Name=r.getString("Name");
							String Age=r.getString("age");
							String Gender=r.getString("gender");
							String Party=r.getString("party");
							String Status=r.getString("status");
							
						    Object[] row= {ID,Eid,Name,Age,Gender,Party,Status};
							tablemodel.addRow(row);
						}
			        
			      
			        	if(table.getRowCount()==0)
						{
							System.out.println("No Applications Available");
							JOptionPane.showMessageDialog(null,"No Applications Available");
							return;
						}
			        	 
			        	 JOptionPane.showMessageDialog(null,"Click Row to \nUpdate or Delete");
							
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
								          
								                btnNewButton_1_2.setVisible(true);
								                btnNewButton_1_1.setVisible(true);
								               
						            }
						            else 
						            {
						                System.out.println("No row selected");
						            }
							
							     }
							    }
							}
						 );
						}
				        
			        	
					
					catch(Exception e6)
					{
						e6.printStackTrace();
					}
						
			}
		});
		btnAllCandidates.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAllCandidates.setBounds(110, 438, 214, 42);
		contentPane.add(btnAllCandidates);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Candidates.class.getResource("/CP_images/online-voting-flag (5).jpg")));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1.setBounds(0, 0, 1401, 213);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
