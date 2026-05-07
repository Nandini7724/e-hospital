package login;

import Database.DBConnection;
import dashboard.Dashboard;

import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends JFrame implements ActionListener {

    JLabel titleLabel, usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginPage() {

        setTitle("e-Hospital Login");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("e-Hospital Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(80, 40, 350, 30);
        add(titleLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(80, 120, 100, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 120, 180, 30);
        add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(80, 180, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 180, 180, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(180, 250, 100, 35);
        loginButton.addActionListener(this);
        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(this,
                        "Login Successful");

                new Dashboard();

                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new LoginPage();
    }
}