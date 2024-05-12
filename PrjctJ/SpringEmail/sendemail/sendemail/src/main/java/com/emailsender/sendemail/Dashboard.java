package com.emailsender.sendemail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Dashboard implements ActionListener {
    private String loggedInUsername;
    JFrame frame = new JFrame();
    JLabel backgroundLabel = new JLabel();
    ImageIcon icon = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\J_processed.png");
    JLabel usernameLabel = new JLabel();
    JLabel lb1 = new JLabel();
    JLabel lb2 = new JLabel();
    JPanel pn1 = new JPanel();
    JButton mb = new JButton();
    JButton lout = new JButton();
    JButton cp = new JButton();

    Dashboard(String username) {
        frame.setLayout(null);
        frame.setTitle("Project J | Dashboard");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);
        frame.setIconImage(icon.getImage());

        lb1.setText("Dashboard");
        lb1.setBounds(300,30,300,30);
        lb1.setForeground(Color.black);
        lb1.setFont(new Font("Monospaced",Font.BOLD,30));

        mb.setText(username);
        mb.setBounds(600,40,150,30);
        mb.setForeground(Color.white);
        mb.setBackground(Color.black);
        mb.setFont(new Font("Monospaced",Font.BOLD,15));
        mb.addActionListener(this);
        mb.setFocusPainted(false);

        pn1.setBounds(520,100,260,400);
        pn1.setBackground(Color.black);
        pn1.setVisible(false);

        lb2.setText("Menu");
        lb2.setForeground(Color.white);
        lb2.setBounds(610,110,100,50);
        lb2.setFont(new Font("Monospaced",Font.BOLD,30));
        lb2.setVisible(false);

        cp.setText("Change Password");
        cp.setForeground(Color.black);
        cp.setBackground(Color.white);
        cp.setFont(new Font("Monospaced",Font.PLAIN,15));
        cp.setBounds(550,200,200,40);
        cp.setFocusPainted(false);
        cp.addActionListener(this);
        cp.setVisible(false);

        lout.setText("Logout");
        lout.setForeground(Color.black);
        lout.setBackground(Color.white);
        lout.setFont(new Font("Monospaced",Font.PLAIN,15));
        lout.setBounds(550,250,200,40);
        lout.setFocusPainted(false);
        lout.addActionListener(this);
        lout.setVisible(false);

        ImageIcon signupImage = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\binary.png");
        backgroundLabel.setBounds(0,0,500,500);
        backgroundLabel.setIcon(signupImage);
        
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(lb1);
        frame.add(mb);
        frame.add(lb2);
        frame.add(cp);
        frame.add(lout);
        frame.add(pn1);

        //Database Connection
        try {
            String url = "jdbc:mysql://localhost:3306/projectj";
            String user = "root";
            String pass = "Shiva@#888";
            Connection cnt = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to Database successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mb) {
            pn1.setVisible(!pn1.isVisible());
            lb2.setVisible(!lb2.isVisible());
            cp.setVisible(!cp.isVisible());
            lout.setVisible(!lout.isVisible());
        }
        else if(e.getSource() == cp){
            ChangePass cp = new ChangePass();
            frame.dispose();
        }
        else if(e.getSource() == lout){
            Login l = new Login();
            frame.dispose();
        }
    }


    public static void main(String[] args) {
        System.out.println("Hello");
        Dashboard frame2 = new Dashboard("Hi");
    }
}
