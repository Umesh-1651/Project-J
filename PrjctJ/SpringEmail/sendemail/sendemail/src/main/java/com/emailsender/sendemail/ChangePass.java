package com.emailsender.sendemail;

import com.emailsender.sendemail.SendemailApplication;
import javax.swing.*;

import org.springframework.boot.SpringApplication;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ChangePass implements ActionListener {
    static int generatedOTP;
    private String enteredOTP;
    public static String emailadd;
    JFrame frame = new JFrame();
    JLabel backgroundLabel = new JLabel();
    ImageIcon icon = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\J_processed.png");
    JLabel usernameLabel = new JLabel();
    JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel();
    JLabel cpl = new JLabel();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField cpf= new JPasswordField();
    
    JLabel errorLabel = new JLabel();
    JButton submitButton = new JButton();
    JButton backButton = new JButton();
    ChangePass() {
        frame.setLayout(null);
        frame.setTitle("Project J | Change Password");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240));
        frame.setIconImage(icon.getImage());

        ImageIcon loginImage = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\binary.png");
        backgroundLabel.setBounds(0,0,500,500);
        backgroundLabel.setIcon(loginImage);

        usernameLabel.setBounds(100, 60, 350, 30);
        usernameLabel.setText("Enter Username/E-mail");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        usernameField.setBounds(100, 100, 250, 30);
        usernameField.setFont(new Font("Monospaced",Font.BOLD,20));

        passwordLabel.setBounds(150, 150, 350, 30);
        passwordLabel.setText("New Password");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        passwordField.setBounds(100, 190, 250, 30);
        passwordField.setFont(new Font("Monospaced",Font.BOLD,20));

        cpl.setBounds(130, 240, 350, 30);
        cpl.setText("Confirm Password");
        cpl.setForeground(Color.white);
        cpl.setFont(new Font("Monospaced",Font.BOLD,20));
        cpf.setBounds(100, 280, 240, 30);
        cpf.setFont(new Font("Monospaced",Font.BOLD,20));



        errorLabel.setBounds(110,310,500,30);
        errorLabel.setFont(new Font("Monospaced",Font.BOLD,15));
        errorLabel.setForeground(Color.white);
        errorLabel.setText("");


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

        submitButton.setText("Verify");
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
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(cpl);
        frame.add(cpf);
        frame.add(submitButton);
        frame.add(backButton);
        frame.setVisible(true);
        frame.add(backgroundLabel);
    }

    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitButton) {
                errorLabel.setText("");
                
                String usernameOrEmail = usernameField.getText().trim();
                String newPassword = new String(passwordField.getPassword());
                String confirmPassword = new String(cpf.getPassword());
        
                // Check for empty fields
                if (usernameOrEmail.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    errorLabel.setText("Please fill in all fields");
                    return;
                }
        
                // Check if passwords match
                if (!newPassword.equals(confirmPassword)) {
                    errorLabel.setText("Passwords do not match");
                    return;
                }
        
                try {
                    String url = "jdbc:mysql://localhost:3306/projectj";
                    String user = "root";
                    String pass = "Shiva@#888";
                    Connection cnt = DriverManager.getConnection(url, user, pass);
                    System.out.println("Connected to Database successfully");
        
                    // Query the database to check if the username or email exists
                    String query = "SELECT * FROM users WHERE username = ? OR email = ?";
                    PreparedStatement pstmt = cnt.prepareStatement(query);
                    pstmt.setString(1, usernameOrEmail);
                    pstmt.setString(2, usernameOrEmail);
                    ResultSet rs = pstmt.executeQuery();
        
                    if (rs.next()) {
                        // Update the user's password
                        String updateQuery = "UPDATE users SET password = ? WHERE username = ? OR email = ?";
                        PreparedStatement updateStmt = cnt.prepareStatement(updateQuery);
                        updateStmt.setString(1, newPassword);
                        updateStmt.setString(2, usernameOrEmail);
                        updateStmt.setString(3, usernameOrEmail);
                        int rowsAffected = updateStmt.executeUpdate();
        
                        if (rowsAffected > 0) {
                            errorLabel.setText("Password updated successfully");
                            Timer timer = new Timer(2000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    frame.dispose();
                                    Login l = new Login();
                                }
                            });
                            timer.setRepeats(false); // Execute only once
                            timer.start();
                        } else {
                            errorLabel.setText("Failed to update password");
                        }
                    } else {
                        errorLabel.setText("Username or email not found");
                    }
        
                    rs.close();
                    pstmt.close();
                    cnt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    errorLabel.setText("Error connecting to the database");
                }
            } else if (e.getSource() == backButton) {
                frame.dispose();
                LandPage nframe = new LandPage();
            }
        }

    // Method to generate a 6-digit OTP
    private String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        ChangePass cp = new ChangePass();
    }
}
