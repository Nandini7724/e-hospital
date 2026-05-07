package medicine;

import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MedicinePage extends JFrame implements ActionListener {

    JLabel titleLabel, nameLabel,
            companyLabel, quantityLabel,
            priceLabel, expiryLabel;

    JTextField nameField,
            companyField,
            quantityField,
            priceField,
            expiryField;

    JButton saveButton;

    public MedicinePage() {

        setTitle("Medicine Inventory");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titleLabel = new JLabel("Medicine Inventory");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(130, 30, 300, 30);
        add(titleLabel);

        nameLabel = new JLabel("Medicine Name:");
        nameLabel.setBounds(50, 100, 140, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 100, 200, 30);
        add(nameField);

        companyLabel = new JLabel("Company:");
        companyLabel.setBounds(50, 150, 140, 30);
        add(companyLabel);

        companyField = new JTextField();
        companyField.setBounds(200, 150, 200, 30);
        add(companyField);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(50, 200, 140, 30);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(200, 200, 200, 30);
        add(quantityField);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 250, 140, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(200, 250, 200, 30);
        add(priceField);

        expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setBounds(50, 300, 140, 30);
        add(expiryLabel);

        expiryField = new JTextField();
        expiryField.setBounds(200, 300, 200, 30);
        add(expiryField);

        saveButton = new JButton("Save Medicine");
        saveButton.setBounds(150, 380, 180, 40);

        saveButton.addActionListener(this);

        add(saveButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String medicineName = nameField.getText();

        String company = companyField.getText();

        int quantity =
                Integer.parseInt(quantityField.getText());

        double price =
                Double.parseDouble(priceField.getText());

        String expiryDate = expiryField.getText();

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO medicines(medicine_name, company, quantity, price, expiry_date) VALUES(?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, medicineName);
            pst.setString(2, company);
            pst.setInt(3, quantity);
            pst.setDouble(4, price);
            pst.setString(5, expiryDate);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Medicine Added Successfully");

            nameField.setText("");
            companyField.setText("");
            quantityField.setText("");
            priceField.setText("");
            expiryField.setText("");

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new MedicinePage();
    }
}