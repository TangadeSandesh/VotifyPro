package OOPScp;

import java.awt.EventQueue;

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
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


public class Results extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public Results() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Election Results");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(0, 258, 262, 45);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 246, 1410, 13);
		contentPane.add(separator);
		
		JLabel Winner = new JLabel("");
		Winner.setForeground(new Color(0, 255, 0));
		Winner.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Winner.setBounds(291, 462, 180, 31);
		contentPane.add(Winner);
		
		JLabel VotesObtained = new JLabel("");
		VotesObtained.setForeground(new Color(0, 255, 0));
		VotesObtained.setFont(new Font("Times New Roman", Font.BOLD, 20));
		VotesObtained.setBounds(291, 515, 180, 31);
		contentPane.add(VotesObtained);
		
		JLabel PartyName = new JLabel("");
		PartyName.setForeground(new Color(0, 255, 0));
		PartyName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		PartyName.setBounds(291, 570, 180, 31);
		contentPane.add(PartyName);
		
		
		
		JLabel EleName = new JLabel("");
		EleName.setBackground(new Color(255, 0, 0));
		EleName.setForeground(new Color(255, 100, 0));
		EleName.setFont(new Font("Times New Roman", Font.BOLD, 26));
		EleName.setBounds(214, 401, 257, 31);
		contentPane.add(EleName);
		
		
		DefaultTableModel tablemodel=new DefaultTableModel();
		tablemodel.addColumn("Candidate-ID");
		tablemodel.addColumn("Election-ID");
		tablemodel.addColumn("Candidate Name");
		
		
		Object[] rowdata=new Object[3];
		
		table=new JTable(tablemodel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(561, 357, 753, 244);
        getContentPane().add(scrollPane);
		
        JButton btnNewButton_1 = new JButton("Show Result");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int selectedRow = table.getSelectedRow();
				
				if (selectedRow != -1) 
				{
				    // get the candidate ID from the selected row
				    String e_Id = (String) table.getValueAt(selectedRow, 0);
				    String e_name = (String) table.getValueAt(selectedRow, 1);
				    
				    EleName.setText(e_name);

				    
				    try {
				    	
				    	Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
						
				        PreparedStatement pst = con.prepareStatement("SELECT candidate_id, name, party, vote FROM candidate WHERE e_id = ? ORDER BY vote DESC LIMIT 1;");
				        
				        pst.setString(1, e_Id);
				       
				
				        ResultSet r= pst.executeQuery();
				        if(r.next())
				        {
				        	 String name = r.getString("name");
				        	 String maxvotes = r.getString("vote");
				        	 String party = r.getString("party");
				        	 
				        	 Winner.setText(name);
				        	 VotesObtained.setText(maxvotes);
				        	 PartyName.setText(party);
				        	 
				        }
				        else
				        {
				        	JOptionPane.showMessageDialog(null, "No data is Available???");
				        }
				    } 
				    catch (Exception e1) 
				    {
				        e1.printStackTrace();	
				    }

				   //tablemodel.removeRow(selectedRow);
				    
				    JOptionPane.showMessageDialog(null, "Result is Shown in Label");
					
				    
				}    
				else
				{
					JOptionPane.showMessageDialog(null, "Please select a Application to Accept.");
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnNewButton_1.setBounds(847, 612, 159, 45);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
        
		
		JButton btnNewButton = new JButton("Select Election");
		btnNewButton.addActionListener(new ActionListener() {
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
 
				        while(r.next())
						{
							String ID=r.getString("e_id");
							String Name=r.getString("e_name");
							String date=r.getString("e_date");
						
						    Object[] row= {ID,Name,date};
							tablemodel.addRow(row);	
						}
						
						JOptionPane.showMessageDialog(null, "These are Current Arranged Elections");
						
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
							                
							                btnNewButton_1.setVisible(true);
								            
							               
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
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnNewButton.setBounds(847, 305, 180, 45);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Winner Candidate");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(95, 462, 180, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Votes Obtained");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(95, 515, 149, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Political Party");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_1_1.setBounds(95, 570, 149, 31);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblResultOf = new JLabel("Result of");
		lblResultOf.setForeground(new Color(255, 100, 0));
		lblResultOf.setBackground(new Color(255, 0, 0));
		lblResultOf.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblResultOf.setBounds(95, 401, 109, 31);
		contentPane.add(lblResultOf);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(95, 442, 376, 10);
		contentPane.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(95, 393, 376, 10);
		contentPane.add(separator_1_1);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Manager M=new Manager();
				M.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnNewButton_2.setBounds(857, 667, 149, 36);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Results.class.getResource("/CP_images/online-voting-flag (5).jpg")));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_2.setBounds(0, 0, 1400, 236);
		contentPane.add(lblNewLabel_2);
		
		
		
	
	}


public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Results frame = new Results();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}
