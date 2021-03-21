package BManagementSystem;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MiniStatement extends JFrame implements ActionListener{
 
    JButton b1, b2;
    JLabel l1,l2,l3;
    JTable t1;
    String y[][] = new String[20][5];
    int i=0, j=0;
    String x[] = {"Date","Mode","Amount"};
    
    MiniStatement(String pin){    
        super("Mini Statement");
        setLayout(null);
        
        JLabel l1 = new JLabel("INDIAN BANK",SwingConstants.CENTER);
        l1.setFont(new Font("Osward", Font.BOLD,28));
        l1.setBounds(40, 20, 300, 40);
        add(l1);
        
        JLabel l2 = new JLabel();
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        l2.setVerticalAlignment(SwingConstants.CENTER);
        l2.setBounds(0, 130, 400, 25);
        l2.setFont(new Font("System", Font.BOLD, 18));
        add(l2);
            
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pin+"'");
            while(rs.next()){
                l2.setText("Account Number:    " + rs.getString("cardno").substring(0, 4) + "XXXXXXXX" + rs.getString("cardno").substring(12));
            }
        }catch(Exception e){}
        	 
        try{
            int balance = 0;
            Conn c1  = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '"+pin+"'");
            while(rs.next()){
                 y[i][j++]=rs.getString("date");
                y[i][j++]=rs.getString("mode");
                y[i][j++]=rs.getString("amount");
                i++;
                j=0;
                if(rs.getString("mode").equals("Credited")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else{
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            } t1 = new JTable(y,x);
            
            JLabel l3 = new JLabel();
            l3.setText("Your total Balance is Rs "+balance);
            l3.setHorizontalAlignment(SwingConstants.CENTER);
            l3.setVerticalAlignment(SwingConstants.CENTER);  
            l3.setBounds(50, 400, 300, 25);
            l3.setFont(new Font("System", Font.BOLD, 18));
            add(l3);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        b1 = new JButton("Exit");
        add(b1);
        b1.addActionListener(this);
        b1.setBounds(66,500,100,45);
        b1.setBackground(new Color(46,196,182));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("System", Font.BOLD, 24));
        
        b2 = new JButton("Print");
        add(b2);
        b2.addActionListener(this);
        b2.setBounds(232,500,100,45);
        b2.setBackground(new Color(46,196,182));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("System", Font.BOLD, 24));
        
        
         JScrollPane sp = new JScrollPane(t1);
         sp.setBounds(50, 180, 300, 200);
        add(sp);
        
         
        getContentPane().setBackground(Color.WHITE);
        setSize(400,600);
        setLocationRelativeTo(null);
       
    }
    public void actionPerformed(ActionEvent ae){
         if(ae.getSource()==b1){
        this.setVisible(false);
         }
         else if(ae.getSource()==b2){
             try{
                 t1.print();
             } catch (PrinterException ex) {
                 Logger.getLogger(MiniStatement.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
    
    public static void main(String[] args){
        new MiniStatement("").setVisible(true);
    }
    
}
