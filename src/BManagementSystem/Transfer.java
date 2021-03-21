
package BManagementSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Transfer extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2;
    JLabel l1,l2,l3;
    String pin,cardno,pin2;
    Transfer(String pin){
        this.pin = pin;
        
         this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
        
         setLayout(null);
         
        l1 = new JLabel("PLEASE ENTER ACCOUNT NUMBER AND AMOUNT",SwingConstants.CENTER);
        l1.setForeground(Color.WHITE);
        l1.setOpaque(true);
        l1.setBackground(new Color(58,134,255));
        l1.setFont(new Font("System", Font.BOLD, 26));
        l1.setBounds(15,70,680,36);
        add(l1);
        
        l2 = new JLabel("ACCOUNT NUMBER",SwingConstants.CENTER);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("System", Font.BOLD, 18));
        l2.setBounds(100,150,210,30);
        add(l2);
        
        l3 = new JLabel("AMOUNT",SwingConstants.CENTER);
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("System", Font.BOLD, 18));
        l3.setBounds(100,230,210,30);
        add(l3);
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 20));
        t1.setBounds(360,150,250,25);
        add(t1);
        
        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 20));
        t2.setBounds(360,230,250,25);
        add(t2);
        
        b1 = new JButton("TRANSFER");
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
            String amount = t2.getText();
            String accno = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t2.getText().equals("") && t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Account Number and the Amount you want to Transfer");
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
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Transfered Successfully");
                    
                    ResultSet rst = c1.s.executeQuery("select * from login where cardno = '"+accno+"'");
                     if(rst.next()){
                         pin2  = rst.getString("pin");
                         c1.s.executeUpdate("insert into bank values('"+pin2+"', '"+date+"', 'Credited', '"+t2.getText()+"')");
                     }
                    
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
        new Transfer("").setVisible(true);
    }
}
