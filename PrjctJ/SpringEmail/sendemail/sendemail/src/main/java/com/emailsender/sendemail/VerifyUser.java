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

public class VerifyUser implements ActionListener {
    static int generatedOTP;
    private String enteredOTP;
    public static String emailadd;
    JFrame frame = new JFrame();
    JLabel backgroundLabel = new JLabel();
    ImageIcon icon = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\J_processed.png");
    JLabel usernameLabel = new JLabel();
    JLabel otpLabel = new JLabel();
    JTextField usernameField = new JTextField();
    JTextField otpField = new JTextField();
    JLabel errorLabel = new JLabel();

    JButton submitButton = new JButton();
    JButton backButton = new JButton();
    JButton otp = new JButton();

    VerifyUser() {
        frame.setLayout(null);
        frame.setTitle("Project J | Verify");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240));
        frame.setIconImage(icon.getImage());

        ImageIcon loginImage = new ImageIcon("C:\\Users\\wwwve\\Documents\\CodeFiles\\PrjctJ\\ImageResources\\binary.png");
        backgroundLabel.setBounds(0,0,500,500);
        backgroundLabel.setIcon(loginImage);

        usernameLabel.setBounds(100, 60, 350, 30);
        usernameLabel.setText("Enter E-mail");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        usernameField.setBounds(100, 100, 250, 30);
        usernameField.setFont(new Font("Monospaced",Font.BOLD,20));

        otp.setText("Generate OTP");
        otp.setBounds(100, 150, 250, 30);
        otp.setBackground(Color.black);
        otp.setForeground(Color.white);
        otp.addActionListener(this);
        otp.setFocusPainted(false);
        otp.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt){
                otp.setBackground(Color.white);
                otp.setForeground(Color.black);
            }
            public void mouseExited(MouseEvent evt){
                otp.setBackground(Color.black);
                otp.setForeground(Color.white);
            }
        });

        otpLabel.setBounds(140, 200, 200, 30);
        otpLabel.setText("Enter OTP");
        otpLabel.setForeground(Color.white);
        otpLabel.setFont(new Font("Monospaced",Font.BOLD,20));
        otpField.setBounds(100, 250, 250, 30);
        otpField.setFont(new Font("Monospaced",Font.BOLD,20));


        errorLabel.setBounds(110,300,500,30);
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
        frame.add(otpLabel);
        frame.add(usernameField);
        frame.add(otpField);
        frame.add(submitButton);
        frame.add(backButton);
        frame.add(otp);
        frame.setVisible(true);
        frame.add(backgroundLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == otp) {
            // Generate and display a 6-digit OTP

            try {
                String url = "jdbc:mysql://localhost:3306/projectj";
                String user = "root";
                String pass = "Shiva@#888";
                String email = usernameField.getText();
                Connection cnt = DriverManager.getConnection(url, user, pass);
                System.out.println("Connected to Database successfully");
            
                // Check if username and password match
                String loginQuery = "SELECT * FROM users WHERE  email = ?";
                PreparedStatement loginStatement = cnt.prepareStatement(loginQuery);
                loginStatement.setString(1, email);
                ResultSet loginResultSet = loginStatement.executeQuery();
            
                if (loginResultSet.next()) {
                    Random random = new Random();
                    int min = 100000;
                    int max = 999999;
                    generatedOTP = random.nextInt(max-min)+min;
                    emailadd = usernameField.getText();
                    SpringApplication.run(SendemailApplication.class,new String [0]);
                    JOptionPane.showMessageDialog(null,"OTP Sent!, Please Check your email.");
                    otp.setEnabled(false);
                    usernameField.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Email Not Signedup yet!");
                    usernameField.setText("");
                }

            } catch (SQLException e1) {
                // Handle the SQLException
                System.out.println("Error: " + e1.getMessage());
                // Optionally, display the error message in your UI
                errorLabel.setText("Error: " + e1.getMessage());
            }

        } else if (e.getSource() == submitButton) {
            int cotp = Integer.parseInt(otpField.getText());
            if(generatedOTP == cotp){
                errorLabel.setText("Email verification Succesfull!");
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        ChangePass cp = new ChangePass();
                    }
                });
                timer.setRepeats(false); // Execute only once
                timer.start();
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid OTP!");
            }
        }
        else if(e.getSource() == backButton){
            frame.dispose();
            Login frame2 = new Login();
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
        VerifyUser frame2 = new VerifyUser();
    }
}
