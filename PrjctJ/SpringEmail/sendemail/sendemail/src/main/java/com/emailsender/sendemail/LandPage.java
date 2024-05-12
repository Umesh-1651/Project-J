package com.emailsender.sendemail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LandPage implements ActionListener{
    JFrame window = new JFrame();
    JPanel panel = new JPanel();
    JButton login = new JButton("Login");
    JButton signup = new JButton("Signup");
    JLabel welcome = new JLabel();
    LandPage() {
        window.setTitle("Project J");
        window.setSize(1000, 530);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        ImageIcon icon = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\J_processed.png");
        window.setIconImage(icon.getImage());
        // Create two separate panels for left and right sides
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JLabel leftP  = new JLabel();
        JLabel rpH  = new JLabel();
        JLabel rpT  = new JLabel();
        ImageIcon LPimage = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\image2.png");


        // Set sizes and positions for left and right panels
        leftPanel.setBounds(0, 0, 500, 500);
        rightPanel.setBounds(500, 0, 500, 500);
        
        // Set background colors for left and right panels
        Color rpcolor = Color.decode("#f3f3f3");
        rightPanel.setBackground(rpcolor);

        // Add buttons to the right panel
        login.setBounds(50, 250, 110, 50);
        signup.setBounds(220, 250, 110, 50);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.setFont(new Font("Monospaced", Font.BOLD, 20));
        login.setFont(new Font("Monospaced", Font.BOLD, 20));
        login.setFocusPainted(false); 
        signup.setFocusPainted(false); 
        rpH.setText("Hey There! Welcome To");
        rpH.setFont(new Font("Courier New", Font.BOLD, 24));
        rpH.setBounds(40,90, 350, 50);
        rpH.setForeground(Color.black);

        rpT.setText("PROJECT J");
        rpT.setFont(new Font("Monospaced", Font.BOLD, 34));
        rpT.setBounds(90,130,300, 50);
        rpT.setForeground(Color.black);
        rightPanel.setLayout(null);
        rightPanel.add(login);
        rightPanel.add(rpH);
        rightPanel.add(rpT);
        rightPanel.add(signup);

        //Adding text label to left panel
        leftP.setBounds(0,0,500,500);
        leftP.setIcon(LPimage);
        leftPanel.setLayout(null);
        leftPanel.add(leftP);

        // Add panels to the window
        window.add(leftPanel);
        window.add(rightPanel);
        // Add action listeners to buttons
        login.addActionListener(this);
        signup.addActionListener(this);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            login.setBackground(Color.white);
            login.setForeground(Color.black);
            window.dispose();
            // Assuming Signin and Signup classes exist and have appropriate functionality
            Login frame1 = new Login();
        }
        else if (e.getSource() == signup) {
            window.dispose();
            // Assuming Signin and Signup classes exist and have appropriate functionality
            Signup frame2 = new Signup();
        }
    }
}