package com.emailsender.sendemail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Login implements ActionListener {
    JFrame frame = new JFrame();
    JLabel backgroundLabel = new JLabel();
    ImageIcon icon = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\J_processed.png");
    JLabel usernameLabel = new JLabel();
    JLabel passwordLabel = new JLabel();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel errorLabel = new JLabel();

    JButton submitButton = new JButton();
    JButton backButton = new JButton();
    JButton Fpass = new JButton();

    Login() {
        frame.setLayout(null);
        frame.setTitle("Project J | Login");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240));
        frame.setIconImage(icon.getImage());

        ImageIcon loginImage = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\binary.png");
        backgroundLabel.setBounds(0,0,500,500);
        backgroundLabel.setIcon(loginImage);

        usernameLabel.setBounds(80, 60, 350, 30);
        usernameLabel.setText("Enter Username or E-mail");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        usernameField.setBounds(100, 100, 250, 30);
        usernameField.setFont(new Font("Monospaced",Font.BOLD,20));

        passwordLabel.setBounds(140, 150, 200, 30);
        passwordLabel.setText("Enter Password");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        passwordField.setBounds(100, 190, 250, 30);
        passwordField.setFont(new Font("Monospaced",Font.BOLD,20));


        errorLabel.setBounds(110,240,500,30);
        errorLabel.setFont(new Font("Monospaced",Font.BOLD,15));
        errorLabel.setForeground(Color.white);
        errorLabel.setText("");


        Fpass.setText("Forget Password!");
        Fpass.setBounds(120, 300, 200, 30);
        Fpass.setBackground(Color.black);
        Fpass.setForeground(Color.white);
        Fpass.addActionListener(this);
        Fpass.setFocusPainted(false);
        Fpass.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt){
                Fpass.setBackground(Color.white);
                Fpass.setForeground(Color.black);
            }
            public void mouseExited(MouseEvent evt){
                Fpass.setBackground(Color.black);
                Fpass.setForeground(Color.white);
            }
        });

        backButton.setText("Back");
        backButton.setBounds(100, 350, 100, 30);
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt){
                backButton.setBackground(Color.white);
                backButton.setForeground(Color.black);
            }
            public void mouseExited(MouseEvent evt){
                backButton.setBackground(Color.black);
                backButton.setForeground(Color.white);
            }
        });

        submitButton.setText("Submit");
        submitButton.setBounds(240, 350, 100, 30);
        submitButton.setBackground(Color.black);
        submitButton.setForeground(Color.white);
        submitButton.addActionListener(this);
        submitButton.setFocusPainted(false);
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt){
                submitButton.setBackground(Color.white);
                submitButton.setForeground(Color.black);
            }
            public void mouseExited(MouseEvent evt){
                submitButton.setBackground(Color.black);
                submitButton.setForeground(Color.white);
            }
        });
        
        frame.setResizable(false);
        frame.add(errorLabel);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(submitButton);
        frame.add(backButton);
        frame.add(Fpass);
        frame.setVisible(true);
        frame.add(backgroundLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
            String usernameOrEmail = usernameField.getText();
            String password = new String(passwordField.getPassword());
    
            if (usernameOrEmail.isEmpty() || password.isEmpty()) {
                errorLabel.setText("All fields are required");
                return;
            } // If all validations pass, clear the error label
            else {
                errorLabel.setText("");
                try {
                    String url = "jdbc:mysql://localhost:3306/projectj";
                    String user = "root";
                    String pass = "Shiva@#888";
                    Connection cnt = DriverManager.getConnection(url, user, pass);
                    System.out.println("Connected to Database successfully");
                
                    // Check if username and password match
                    String loginQuery = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
                    PreparedStatement loginStatement = cnt.prepareStatement(loginQuery);
                    loginStatement.setString(1, usernameOrEmail);
                    loginStatement.setString(2, usernameOrEmail);
                    loginStatement.setString(3, password);
                    ResultSet loginResultSet = loginStatement.executeQuery();
                
                    if (loginResultSet.next()) {
                        // Username or email and password match
                        errorLabel.setText("Login successful!");
                    
                        // Retrieve the username from the ResultSet
                        String loggedInUsername = loginResultSet.getString("username");
                    
                        // Wait for 5 seconds before closing the frame and going to the landing page
                        Timer timer = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Dashboard db = new Dashboard(loggedInUsername);
                                frame.dispose();
                            }
                        });
                        timer.setRepeats(false); // Execute only once
                        timer.start();
                    }else {
                        // Username or email and password do not match
                        errorLabel.setText("Invalid username/email or password");
                    }
                } catch (SQLException e1) {
                    // Handle the SQLException
                    System.out.println("Error: " + e1.getMessage());
                    // Optionally, display the error message in your UI
                    errorLabel.setText("Error: " + e1.getMessage());
                }
            }
        } else if(e.getSource() == backButton) {
            frame.dispose();
            LandPage lp = new LandPage();
        }
        else if(e.getSource() == Fpass) {
            VerifyUser vf1 = new VerifyUser();
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        Login frame2 = new Login();
    }
}
