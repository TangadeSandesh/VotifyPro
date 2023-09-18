package OOPScp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;

public class Manager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
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
	public Manager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Voting");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(588, 262, 233, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Manage Elections");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Elections E=new Elections();
				E.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnNewButton.setBounds(204, 507, 233, 56);
		contentPane.add(btnNewButton);
		
		JButton btnAlloteCandidate = new JButton("Manage Candidates");
		btnAlloteCandidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Candidates C=new Candidates();
				C.setVisible(true);
				dispose();
			}
		});
		btnAlloteCandidate.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnAlloteCandidate.setBounds(588, 511, 233, 49);
		contentPane.add(btnAlloteCandidate);
		
		JButton btnResults = new JButton("Show Results");
		btnResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Results R=new Results();
				R.setVisible(true);
				dispose();
			}
		});
		btnResults.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnResults.setBounds(961, 511, 214, 49);
		contentPane.add(btnResults);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				LoginPage1 L=new LoginPage1();
				L.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnBack.setBounds(628, 608, 155, 49);
		contentPane.add(btnBack);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 242, 1400, 12);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Manager.class.getResource("/CP_images/vote_VoterVoted (1).jpg")));
		lblNewLabel_1.setBounds(204, 367, 233, 130);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(Manager.class.getResource("/CP_images/candidate (1).jpg")));
		lblNewLabel_1_1.setBounds(588, 367, 233, 130);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setIcon(new ImageIcon(Manager.class.getResource("/CP_images/Screenshot (59) (1).jpg")));
		lblNewLabel_1_2.setBounds(956, 367, 233, 130);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("");
		lblNewLabel_1_3.setIcon(new ImageIcon(Manager.class.getResource("/CP_images/online-voting-flag (5).jpg")));
		lblNewLabel_1_3.setBounds(0, 0, 1400, 230);
		contentPane.add(lblNewLabel_1_3);
	}
}
