package BManagementSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
  
    Login(){
        
        this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
        
        setTitle("BANK ACCOUNT MANAGEMENT SYSTEM");
        
         
        try {
            Connection c =DriverManager.getConnection("jdbc:mysql:///bms","root","");    
            Statement s =c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM login");
            while (rs.next()) {
            String accno = rs.getObject(2).toString();
            String pin = rs.getObject(3).toString();
            System.out.println(accno);
            System.out.println(pin);


        }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setLayout(null);
        
        l1 = new JLabel("Welcome to Indian Bank",SwingConstants.CENTER);
        l1.setFont(new Font("Osward", Font.BOLD,46));
        l1.setBounds(50,20,700,120);
        l1.setOpaque(true);
        l1.setBackground(Color.CYAN);
        l1.setForeground(Color.BLUE);
        add(l1);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.png"));
        l1.setIcon(img);
        
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/admin.png"));
        Image i2 = img1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 170, 150, 150);
        add(l11);
              
        l2 = new JLabel("Account No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(275,175,375,30);
        add(l2);
        
        tf1 = new JTextField(15);
        tf1.setBounds(450,175,230,30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);
        
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(275,245,375,30);
        add(l3);
        
        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(450,245,230,30);
        add(pf2);
                
        b1 = new JButton("SIGN IN");
        b1.setBackground(new Color(46,196,182));
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(450,300,100,30);
        add(b1);
        b1.addActionListener(this);
        
        b2 = new JButton("CLEAR");
        b2.setBackground(new Color(46,196,182));
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(580,300,100,30);
        add(b2);
        b2.addActionListener(this);
        
        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.GREEN);
        b3.setForeground(Color.BLACK);
        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(450,350,230,30);
        add(b3);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);  
        setSize(800,480);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        try{        
            if(ae.getSource()==b1){
                Conn c1 = new Conn();
                String accno  = tf1.getText();
                String pin  = pf2.getText();
                String q  = "select * from login where cardno = '"+accno+"' and pin = '"+pin+"'";

                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Account Number or PIN");
                }
            }else if(ae.getSource()==b2){
                tf1.setText("");
                pf2.setText("");
            }else if(ae.getSource()==b3){
                setVisible(false);
                new Signup().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Login().setVisible(true);
    }

    
}



