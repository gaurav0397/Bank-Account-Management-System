
package BManagementSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener{
    
    JTextField t1;
    JButton b1,b2;
    JLabel l1;
    String pin;
    Deposit(String pin){
        this.pin = pin;
        
        setLayout(null);
        
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT",SwingConstants.CENTER);
        l1.setForeground(Color.WHITE);
        l1.setOpaque(true);
        l1.setBackground(new Color(58,134,255));
        l1.setFont(new Font("System", Font.BOLD, 28));
        l1.setBounds(75,100,570,50);
        add(l1);
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        t1.setBounds(220,180,300,30);
        add(t1);
        
        b1 = new JButton("DEPOSIT");
        b1.setBackground(new Color(46,196,182));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("System", Font.BOLD, 24));
        b1.setBounds(140,275,150,45);
        add(b1);
        b1.addActionListener(this);
        
        b2 = new JButton("BACK");
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("System", Font.BOLD, 24));
        b2.setBackground(new Color(46,196,182));
        b2.setBounds(430,275,150,45);
        add(b2);       
        b2.addActionListener(this);

        setSize(720,480);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setVisible(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.BLACK));
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String amount = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                }else{
                    Conn c1 = new Conn();
                    c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Credited', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Deposited Successfully");
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
    public static void main(String[] args){
        new Deposit("").setVisible(true);
    }
}