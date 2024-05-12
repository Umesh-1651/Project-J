package com.emailsender.sendemail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Signup implements ActionListener {
    JFrame frame = new JFrame();
    JLabel backgroundLabel = new JLabel();
    ImageIcon icon = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\J_processed.png");
    JLabel usernameLabel = new JLabel();
    JLabel passwordLabel = new JLabel();
    JLabel confirmPasswordLabel = new JLabel();
    JLabel emailLabel = new JLabel();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confirmPasswordField = new JPasswordField();
    JTextField emailField = new JTextField();
    JLabel errorLabel = new JLabel();

    JButton submitButton = new JButton();
    JButton backButton = new JButton();

    Signup() {
        frame.setLayout(null);
        frame.setTitle("Project J | Signup");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240));
        frame.setIconImage(icon.getImage());

        ImageIcon signupImage = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\binary.png");
        backgroundLabel.setBounds(0,0,500,500);
        backgroundLabel.setIcon(signupImage);

        usernameLabel.setBounds(140, 20, 200, 30);
        usernameLabel.setText("Create Username");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        usernameField.setBounds(100, 60, 250, 30);
        usernameField.setFont(new Font("Monospaced",Font.BOLD,20));

        passwordLabel.setBounds(140, 110, 200, 30);
        passwordLabel.setText("Enter Password");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        passwordField.setBounds(100, 150, 250, 30);
        passwordField.setFont(new Font("Monospaced",Font.BOLD,20));

        confirmPasswordLabel.setBounds(130, 200, 200, 30);
        confirmPasswordLabel.setText("Confirm Password");
        confirmPasswordLabel.setForeground(Color.white);
        confirmPasswordLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        confirmPasswordField.setBounds(100, 240, 250, 30);
        confirmPasswordField.setFont(new Font("Monospaced",Font.BOLD,20));

        emailLabel.setBounds(140, 290, 200, 30);
        emailLabel.setText("Enter E-mail");
        emailLabel.setForeground(Color.white);
        emailLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        emailField.setBounds(100, 330, 250, 30);
        emailField.setFont(new Font("Monospaced",Font.BOLD,20));

        errorLabel.setBounds(80,380,500,30);
        errorLabel.setFont(new Font("Monospaced",Font.BOLD,15));
        errorLabel.setForeground(Color.white);
        errorLabel.setText("(Username must be unique and lowercase)");

        backButton.setText("Back");
        backButton.setBounds(100, 430, 100, 30);
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
        submitButton.setBounds(240, 430, 100, 30);
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
        frame.add(confirmPasswordLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(submitButton);
        frame.add(backButton);
        frame.add(emailLabel);
        frame.add(emailField);
    
        frame.setVisible(true);
        frame.add(backgroundLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String email = emailField.getText();
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                errorLabel.setText("All fields are required");
                return;
            }
    
            // Validate username
            else if (!username.matches("[a-z]+")) {
                errorLabel.setText("Username must contain only lowercase letters");
                return;
            }
    
            // Validate password match
            else if (!password.equals(confirmPassword)) {
                errorLabel.setText("Passwords do not match");
                return;
            }
    
            // Validate email
            else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                errorLabel.setText("Invalid email address");
                return;
            }
    
            // If all validations pass, clear the error label
            else{
                errorLabel.setText("");
                try{
                    String url = "jdbc:mysql://localhost:3306/projectj";
                    String user = "root";
                    String pass = "Shiva@#888";
                        Connection cnt = DriverManager.getConnection(url, user, pass);
                        System.out.println("Connected to Database successfully");
                        
                        // Check if username already exists
                        String usernameQuery = "SELECT * FROM users WHERE username = ?";
                        PreparedStatement usernameStatement = cnt.prepareStatement(usernameQuery);
                        usernameStatement.setString(1, username);
                        ResultSet usernameResultSet = usernameStatement.executeQuery();

                        if (usernameResultSet.next()) {
                            // Username already exists
                            errorLabel.setText("Username already exists, Please pick another.");
                        } else {
                            // Check if email already exists
                            String emailQuery = "SELECT * FROM users WHERE email = ?";
                            PreparedStatement emailStatement = cnt.prepareStatement(emailQuery);
                            emailStatement.setString(1, email);
                            ResultSet emailResultSet = emailStatement.executeQuery();

                            if (emailResultSet.next()) {
                                // Email already exists
                                errorLabel.setText("Email already exists, Please try login.");
                            } else {
                                // Username and email are unique, proceed with insertion
                                String insertQuery = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                                PreparedStatement insertStatement = cnt.prepareStatement(insertQuery);
                                insertStatement.setString(1, username);
                                insertStatement.setString(2, password);
                                insertStatement.setString(3, email);
                                int rowsAffected = insertStatement.executeUpdate();

                                if (rowsAffected > 0) {
                                    System.out.println("Record inserted successfully");
                                    errorLabel.setText("Account Created Successfully, Please Login");

                                    // Wait for 5 seconds before closing the frame and going to the landing page
                                    Timer timer = new Timer(2000, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            frame.dispose();
                                            LandPage lp = new LandPage();
                                        }
                                    });
                                    timer.setRepeats(false); // Execute only once
                                    timer.start();
                                } else {
                                    System.out.println("Failed to insert record");
                                }
                            }
                        }
                    } catch (SQLException e1) {
                        System.out.println("Error: " + e1.getMessage());
                    }

            // Leave space for database connection here
            // Call storeCredentials(username, password, email);
        }
    }
            // Validation and database connection logic here
        else if(e.getSource() == backButton) {
            frame.dispose();
            LandPage lp = new LandPage();
        }
    }

    private void storeCredentials(String username, String password, String confirmPassword) {
        // Database connection and insertion logic here
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        Signup frame2 = new Signup();
    }
}
