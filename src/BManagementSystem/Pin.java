
package BManagementSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener{
    
    JPasswordField t1,t2;
    JButton b1,b2;                               
    JLabel l1,l2,l3;
    String pin;
    Pin(String pin){
        this.pin = pin;
        setLayout(null);
        
        l1 = new JLabel("CHANGE YOUR PIN",SwingConstants.CENTER);
        l1.setFont(new Font("System", Font.BOLD, 28));
        l1.setForeground(Color.WHITE);
        l1.setOpaque(true);
        l1.setBackground(new Color(58,134,255));
        l1.setBounds(75,100,570,50);
        add(l1);
        
        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setForeground(Color.BLACK);
        l2.setBounds(170,215,150,35);
        add(l2);
        
        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setForeground(Color.BLACK);
        l3.setBounds(170,280,200,35);
        add(l3);
        
        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        t1.setBounds(390,215,180,30);
        add(t1);
        
        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));
        t2.setBounds(390,280,180,30);
        add(t2);
        
        b1 = new JButton("CHANGE");
        b1.setBackground(new Color(46,196,182));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("System", Font.BOLD, 24));
        b1.setBounds(140,380,150,45);
        add(b1);
        b1.addActionListener(this);
        
        b2 = new JButton("BACK");
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("System", Font.BOLD, 24));
        b2.setBackground(new Color(46,196,182));
         b2.setBounds(430,380,150,45);
        add(b2);
        b2.addActionListener(this);

        setSize(720,480);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
         getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.BLACK));
          getContentPane().setBackground(Color.WHITE);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String npin = t1.getText();
            String rpin = t2.getText();
            
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            
            if(ae.getSource()==b1){
                if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                }
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                }
                
                Conn c1 = new Conn();
                String q1 = "update bank set pin = '"+rpin+"' where pin = '"+pin+"' ";
                String q2 = "update login set pin = '"+rpin+"' where pin = '"+pin+"' ";
                String q3 = "update signup3 set pin = '"+rpin+"' where pin = '"+pin+"' ";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);
            
            }else if(ae.getSource()==b2){
                new Transactions(pin).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Pin("").setVisible(true);
    }
}
