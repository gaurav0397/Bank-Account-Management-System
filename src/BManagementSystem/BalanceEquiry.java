package BManagementSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

class BalanceEnquiry extends JFrame implements ActionListener {

    JButton b1;
    JLabel l1;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;
        
        setLayout(null);
        
        l1 = new JLabel();
        l1.setForeground(Color.BLUE);
        l1.setFont(new Font("System", Font.BOLD, 20));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setVerticalAlignment(SwingConstants.CENTER);
        l1.setBounds(15, 100, 680, 60);
        add(l1);

        b1 = new JButton("BACK");
        b1.setFont(new Font("System", Font.BOLD, 28));
        b1.setBackground(new Color(46,196,182));
        b1.setForeground(Color.WHITE);
        b1.setBounds(260, 220, 200, 50);
        add(b1);
        b1.addActionListener(this);

        int balance = 0;
        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
            while (rs.next()) {
                if (rs.getString("mode").equals("Credited")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){}
        
        l1.setText("Your Current Account Balance is Rs "+balance);

        setSize(720, 480);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setVisible(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.BLACK));
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}
