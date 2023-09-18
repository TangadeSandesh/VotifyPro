package OOPScp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Elections extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Elections frame = new Elections();
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
	public Elections() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Elections");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(129, 268, 272, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Election Name");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(87, 426, 181, 34);
		contentPane.add(lblNewLabel_1_1);
		
		
		JLabel lblNewLabel_1_2 = new JLabel("Election Date");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_2.setBounds(87, 490, 168, 48);
		contentPane.add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField.setColumns(10);
		textField.setBounds(307, 350, 181, 48);
		contentPane.add(textField);
		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Election ID");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_1_1.setBounds(87, 356, 181, 34);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(307, 420, 181, 48);
		contentPane.add(textField_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(307, 490, 181, 48);
		contentPane.add(dateChooser);
		
		
		String[] columns = {"E-id","E-Name", "E-Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new TitledBorder(null, "Elections", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setBounds(604, 347, 730, 268);
        contentPane.add(scrollPane);
        
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String eid=textField.getText();
					String ename=textField_1.getText();
					Date edate = new Date(dateChooser.getDate().getTime());
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
					PreparedStatement pst=c.prepareStatement("insert into election(e_id, e_name, e_date) values(?,?,?)");
					
					pst.setString(1, eid);
					pst.setString(2, ename); 
					pst.setDate(3, edate);
					
					pst.executeUpdate();
					
					textField.setText("");
					textField_1.setText("");
					dateChooser.setToolTipText("");
		 			
	                    // Add data to the table
	                    Object[] rowData = {eid, ename, edate};
	                    model.addRow(rowData);
	                
		 			JOptionPane.showMessageDialog(null,"Election is created");
				}
				catch(Exception e4)
				{
					e4.printStackTrace();
				}
			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnNewButton.setBounds(81, 567, 121, 48);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String eid=textField.getText();
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
					PreparedStatement pst=c.prepareStatement("delete from election where e_id=?");
					
					pst.setString(1,eid);
					
						if(eid.isEmpty())
						{
							JOptionPane.showMessageDialog(null,"Enter Election!!");
		                }
						else
						{						
							pst.executeUpdate();
							
							textField.setText("");
							textField_1.setText("");
							dateChooser.setToolTipText("");
				 			
							 int[] rows = table.getSelectedRows();
			                    for (int i = 0; i < rows.length; i++)
			                    {
			                        model.removeRow(rows[i] - i);
			                    }
				 			JOptionPane.showMessageDialog(null,"Election is deleted");
						}
					
				}
				catch(Exception e5)
				{
					e5.printStackTrace();				
				}
				
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnDelete.setBounds(244, 567, 121, 48);
		contentPane.add(btnDelete);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					String eid=textField.getText();
					String ename=textField_1.getText();
					String edate=dateChooser.getToolTipText();
					
					// Update data in the table
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1)
                    {
                        model.setValueAt(eid, selectedRow, 0);
                        model.setValueAt(ename, selectedRow, 1);
                        model.setValueAt(edate, selectedRow, 2);
					
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system","root","Mysandesh@123");
					PreparedStatement pst=c.prepareStatement("update election set e_name=?, e_date=? where e_id=?");
					

					pst.setString(3, eid);
					pst.setString(1, ename); 
					pst.setString(2, edate);
					
					
						if(eid.isEmpty()||ename.isEmpty()||edate.isEmpty())
						{
							JOptionPane.showMessageDialog(null,"Enter Election-ID!!");
		                }
						else
						{						
							pst.executeUpdate();
							
							textField.setText("");
							textField_1.setText("");
							dateChooser.setToolTipText("");
							   
		                    JOptionPane.showMessageDialog(null,"Election is Updated");
						}
						
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Please Select Single Election to update");
                    }
					
                    
				}
				catch(Exception e6)
				{
					e6.printStackTrace();
				}
			
				
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnEdit.setBounds(416, 566, 121, 48);
		contentPane.add(btnEdit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Manager m=new Manager();
				m.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnBack.setBounds(244, 640, 121, 48);
		contentPane.add(btnBack);
		btnEdit.setFont(new Font("Montserrat Medium", Font.BOLD, 20));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 240, 1400, 23);
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
 
				        while(r.next())
						{
							String ID=r.getString("e_id");
							String Name=r.getString("e_name");
							String date=r.getString("e_date");
						
						    Object[] row= {ID,Name,date};
							model.addRow(row);	
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
		btnSeeElections.setBounds(868, 289, 177, 48);
		contentPane.add(btnSeeElections);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Elections.class.getResource("/CP_images/online-voting-flag (5).jpg")));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1.setBounds(0, 0, 1400, 230);
		contentPane.add(lblNewLabel_1);
		
	
	
	}
}
