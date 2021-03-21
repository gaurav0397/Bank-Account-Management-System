package BManagementSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transactions extends JFrame implements ActionListener{
   

    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    Transactions(String pin){
        this.pin = pin;
        setLayout(null);
        
        l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("System", Font.BOLD, 32));
        l1.setBounds(130,150,700,35);
        add(l1);
        
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/deposit.png"));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/withdrawal.jpg"));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/fastcash.png"));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/statement.png"));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/pin.png"));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/balance.png"));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/exit.png"));
        
       
        l2 = new JLabel();
        l2.setIcon(icon1);
        l2.setBounds(127,240,150,150);
        add(l2);

        l3 = new JLabel();
        l3.setIcon(icon2);
        l3.setBounds(127,440,150,150);
        add(l3);

        l4 = new JLabel();
        l4.setIcon(icon3);
        l4.setBounds(404,240,150,150);
        add(l4);

        l5 = new JLabel();
        l5.setIcon(icon4);
        l5.setBounds(404,440,150,150);
        add(l5);
        
        l6 = new JLabel();
        l6.setIcon(icon5);
        l6.setBounds(681,240,150,150);
        add(l6);

        l7 = new JLabel();
        l7.setIcon(icon6);
        l7.setBounds(681,440,150,150);
        add(l7);
        
        l8 = new JLabel("Welcome To Indian Bank" ,SwingConstants.CENTER);
        l8.setFont(new Font("Osward", Font.BOLD,40));
        l8.setBounds(127,50,706,45);
        l8.setOpaque(true);
        l8.setBackground(Color.BLUE);
        l8.setForeground(Color.WHITE);
        add(l8);

        b1 = new JButton("Deposit Funds");
        b1.setFont(new Font("Osward", Font.BOLD,20));
        b1.setBounds(107,400,190,30);
        b1.setOpaque(true);
        b1.setBackground(Color.BLUE);
        b1.setForeground(Color.WHITE);
        add(b1);
        
        b2 = new JButton("Cash Withdrawl");
        b2.setFont(new Font("Osward", Font.BOLD,20));
        b2.setBounds(107,600,190,30);
        b2.setOpaque(true);
        b2.setBackground(Color.BLUE);
        b2.setForeground(Color.WHITE);
        add(b2);
        
        b3 = new JButton("Transfer Funds");
        b3.setFont(new Font("Osward", Font.BOLD,20));
        b3.setBounds(384,400,190,30);
        b3.setOpaque(true);
        b3.setBackground(Color.BLUE);
        b3.setForeground(Color.WHITE);
        add(b3);
        
        b4 = new JButton("Mini Statement");
        b4.setFont(new Font("Osward", Font.BOLD,20));
        b4.setBounds(384,600,190,30);
        b4.setOpaque(true);
        b4.setBackground(Color.BLUE);
        b4.setForeground(Color.WHITE);
        add(b4);
        
        b5 = new JButton("Change Pin");
        b5.setFont(new Font("Osward", Font.BOLD,20));
        b5.setBounds(661,400,190,30);
        b5.setOpaque(true);
        b5.setBackground(Color.BLUE);
        b5.setForeground(Color.WHITE);
        add(b5);
        
        b6 = new JButton("Balance Enquiry");
        b6.setFont(new Font("Osward", Font.BOLD,20));
        b6.setBounds(661,600,190,30);
        b6.setOpaque(true);
        b6.setBackground(Color.BLUE);
        b6.setForeground(Color.WHITE);
        add(b6);
        
        b7 = new JButton();
        b7.setIcon(icon7);
        b7.setBounds(875,10,50,50);
        b7.setBackground(Color.WHITE);
        b7.setBorderPainted(false);
        add(b7);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        setSize(960,680);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.BLACK));
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){ 
            setVisible(false);
            new Deposit(pin).setVisible(true);
        }else if(ae.getSource()==b2){ 
            setVisible(false);
            new Withdrawl(pin).setVisible(true);
        }else if(ae.getSource()==b3){ 
            setVisible(false);
            new Transfer(pin).setVisible(true);
        }else if(ae.getSource()==b4){ 
            new MiniStatement(pin).setVisible(true);
        }else if(ae.getSource()==b5){ 
            setVisible(false);
            new Pin(pin).setVisible(true);
        }else if(ae.getSource()==b6){ 
            this.setVisible(false);
            new BalanceEnquiry(pin).setVisible(true);
        }else if(ae.getSource()==b7){ 
            System.exit(0);
        }
    }
    
    public static void main(String[] args){
        new Transactions("").setVisible(true);
    }
}