
package BManagementSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    String pin;
    Withdrawl(String pin){
        this.pin = pin;
        setLayout(null);

        l1 = new JLabel("MAXIMUM WITHDRAWAL LIMIT IS RS.10,000",SwingConstants.CENTER);
        l1.setOpaque(true);
        l1.setBackground(Color.RED);
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 32));
        l1.setBounds(15,70,680,40);
        add(l1);
        
        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT",SwingConstants.CENTER);
        l2.setForeground(Color.WHITE);
        l2.setOpaque(true);
        l2.setBackground(new Color(58,134,255));
        l2.setFont(new Font("System", Font.BOLD, 28));
        l2.setBounds(140,150,440,30);
        add(l2);
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        t1.setBounds(210,220,300,30);
        add(t1);
        
        b1 = new JButton("WITHDRAW");
        b1.setBounds(106,295,200,45);
        b1.setBackground(new Color(46,196,182));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("System", Font.BOLD, 28));
        add(b1);
        b1.addActionListener(this);
        
        b2 = new JButton("BACK");
        b2.setBounds(412,295,200,45);
        b2.setBackground(new Color(46,196,182));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("System", Font.BOLD, 28));
        add(b2);
        b2.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(720,480);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.BLACK));
    }
    
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String amount = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                }else{
                    Conn c1 = new Conn();
                    
                    ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
                    int balance = 0;
                    while(rs.next()){
                       if(rs.getString("mode").equals("Credited")){
                           balance += Integer.parseInt(rs.getString("amount"));
                       }else{
                           balance -= Integer.parseInt(rs.getString("amount"));
                       }
                    }
                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }
                    
                    c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Debited', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
            
    }

    
    
    public static void main(String[] args){
        new Withdrawl("").setVisible(true);
    }
}
