import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
public class PRoject2 {
	private JFrame frame;
	
	private JTextField pname;
	private JTextField vaccines;
	private JTextField age;
	private JTextField txtpid;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PRoject2 window = new PRoject2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	  public void table_load()
	     {
		  
	      try 
	      {
	     pst = con.prepareStatement("select * from patient_table");
	     rs = pst.executeQuery();
	 } 
	      catch (SQLException e) 
	      {
	      e.printStackTrace();
	   } 
	     }
	public PRoject2() {
		initialize();
		Connect();
		table_load();
	}
	
Connection con;
 PreparedStatement pst;
	
 ResultSet rs;
	
 private JTextField txtWelcomeToOur;
 private JTextField textpname;
 private JTextField costtake;
 private JTextField Costset;
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/patient", "root","");
        }
        catch (ClassNotFoundException ex) 
        {
		
          ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
        	   ex.printStackTrace();
        }

    }
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 450, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 58, 209, 135);
		frame.getContentPane().add(panel);
		panel.setLayout(null);		
		JLabel lblNewLabel_1 = new JLabel("Name/id");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setBounds(10, 27, 46, 14);
		panel.add(lblNewLabel_1);		
		JLabel lblNewLabel_1_1 = new JLabel("Vaccines");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setBounds(10, 52, 70, 14);
		panel.add(lblNewLabel_1_1);		
		JLabel lblNewLabel_1_2 = new JLabel("Age");
		
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setBounds(10, 72, 46, 14);
		panel.add(lblNewLabel_1_2);		
		pname = new JTextField();
		pname.setBounds(100, 24, 86, 20);
		panel.add(pname);
		pname.setColumns(10);		
		vaccines = new JTextField();
		vaccines.setBounds(100, 49, 86, 20);
		panel.add(vaccines);
		vaccines.setColumns(10);		
		age = new JTextField();
		age.setForeground(Color.RED);
		age.setBounds(100, 69, 86, 20);
		panel.add(age);
		age.setColumns(10);	
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,vac,agee;
				bname=pname.getText();
				vac=vaccines.getText();
				agee=age.getText();
				 try {
				 pst = con.prepareStatement("insert into patient_table(name,vaccines,age)values(?,?,?)");
				 pst.setString(1, bname);
				 pst.setString(2, vac);
				 pst.setString(3, agee);
				 pst.executeUpdate();
				 JOptionPane.showMessageDialog(null, "Record is inserted.");
				 table_load();
				            
				 pname.setText("");
				 vaccines.setText("");
				 age.setText("");
				 pname.requestFocus();
				    }
				 
				 catch (SQLException e1) 
				        {
				 
				 e1.printStackTrace();
				 }
				
			}
		});
		btnNewButton.setBounds(10, 204, 73, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.BLUE);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(93, 204, 57, 23);
		frame.getContentPane().add(btnExit);
		
		JPanel panel_1 = new JPanel();

		panel_1.setBounds(32, 252, 190, 57);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1.setForeground(Color.RED);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtpid = new JTextField();
		txtpid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

try {
           
             String id = txtpid.getText();
 
                 pst = con.prepareStatement("select name,vaccines,age from patient_table where id = ?");
                 pst.setString(1, id);
                 ResultSet rs = pst.executeQuery();
 
             if(rs.next()==true)
             {
               
                 String name = rs.getString(1);
                 String vac = rs.getString(2);
                 String agee = rs.getString(3);
                 
                 pname.setText(name);
                 vaccines.setText(vac);
                 age.setText(agee);
                 
                 
             }   
             else
             {
              pname.setText("");
              vaccines.setText("");
                 age.setText("");
                  
             }
             
 
 
         } 
 
 catch (SQLException ex) {
            
         }
			}
		});
		txtpid.setColumns(10);
		panel_1.add(txtpid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.MAGENTA);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bname,vac,agee,pid;
				bname=pname.getText();
				vac=vaccines.getText();
				agee=age.getText();
				pid=txtpid.getText();
				 try {
				 pst = con.prepareStatement("update patient_table set name= ?,vaccines=?,age=? where id =?");
				 pst.setString(1, bname);
				 pst.setString(2, vac);
				 pst.setString(3, agee);
				 pst.setString(4, pid);
				 pst.executeUpdate();
				 JOptionPane.showMessageDialog(null, "Record is updated.");
				 table_load();
				            
				 pname.setText("");
				 vaccines.setText("");
				 age.setText("");
				 pname.requestFocus();
				    }
				 
				 catch (SQLException e1) 
				        {
				 
				 e1.printStackTrace();
				 }
			}
		});
		btnUpdate.setBounds(240, 204, 97, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String pid;
				
				pid=txtpid.getText();
				 try {
				 pst = con.prepareStatement("delete from patient_table where id =?");
				
				 pst.setString(1, pid);
				 pst.executeUpdate();
				 JOptionPane.showMessageDialog(null, "Record is deleted.");
				 table_load();
				            
				 pname.setText("");
				 vaccines.setText("");
				 age.setText("");
				 pname.requestFocus();
				    }
				 
				 catch (SQLException e1) 
				        {
				 
				 e1.printStackTrace();
				 }				
			}
		});
		btnDelete.setBounds(347, 204, 77, 23);
		frame.getContentPane().add(btnDelete);
		
		txtWelcomeToOur = new JTextField();
		txtWelcomeToOur.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtWelcomeToOur.setForeground(Color.RED);
		txtWelcomeToOur.setText("Welcome to our system.");
		txtWelcomeToOur.setBounds(42, 11, 326, 36);
		frame.getContentPane().add(txtWelcomeToOur);
		txtWelcomeToOur.setColumns(10);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(234, 252, 190, 57);
		frame.getContentPane().add(panel_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("name");
		lblNewLabel_1_1_1_1.setForeground(Color.RED);
		panel_1_1.add(lblNewLabel_1_1_1_1);
		
		textpname = new JTextField();
		textpname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

try {
           
             String name = textpname.getText();
 
                 pst = con.prepareStatement("select id,vaccines,age from patient_table where name = ?");
                 pst.setString(1, name);
                 ResultSet rs = pst.executeQuery();
 
             if(rs.next()==true)
             {
               
                 String id = rs.getString(1);
                 String vac = rs.getString(2);
                 String agee = rs.getString(3);
                 
                 pname.setText(id);
                 vaccines.setText(vac);
                 age.setText(agee);
                 
                 
             }   
             else
             {
              pname.setText("");
              vaccines.setText("");
                 age.setText("");
                  
             }
             
 
 
         } 
 
 catch (SQLException ex) {
            
         }
			}
		});
		textpname.setColumns(10);
		panel_1_1.add(textpname);
		
		JLabel lblNewLabel = new JLabel("Total vaccines");
		lblNewLabel.setBounds(240, 68, 73, 14);
		frame.getContentPane().add(lblNewLabel);
		
		costtake = new JTextField();
		costtake.setBounds(323, 65, 86, 20);
		frame.getContentPane().add(costtake);
		costtake.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cost");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cost =Double.parseDouble(costtake.getText())*1.5;
				String itax=String.format("%.2f",cost);
				Costset.setText(itax);
				
			}
		});
		btnNewButton_1.setBounds(229, 101, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		Costset = new JTextField();
		Costset.setBounds(323, 102, 86, 20);
		frame.getContentPane().add(Costset);
		Costset.setColumns(10);
		
	}
}
