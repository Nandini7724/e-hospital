package billing;

import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BillingPage extends JFrame implements ActionListener {

    JLabel titleLabel, patientIdLabel,
            consultationLabel, medicineLabel,
            totalLabel, paymentStatusLabel;

    JTextField patientIdField,
            consultationField,
            medicineField,
            totalField,
            paymentField;

    JButton generateButton;

    public BillingPage() {

        setTitle("Billing System");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titleLabel = new JLabel("Patient Billing");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(150, 30, 250, 30);
        add(titleLabel);

        patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setBounds(50, 100, 120, 30);
        add(patientIdLabel);

        patientIdField = new JTextField();
        patientIdField.setBounds(200, 100, 200, 30);
        add(patientIdField);

        consultationLabel = new JLabel("Consultation Fee:");
        consultationLabel.setBounds(50, 150, 140, 30);
        add(consultationLabel);

        consultationField = new JTextField();
        consultationField.setBounds(200, 150, 200, 30);
        add(consultationField);

        medicineLabel = new JLabel("Medicine Charge:");
        medicineLabel.setBounds(50, 200, 140, 30);
        add(medicineLabel);

        medicineField = new JTextField();
        medicineField.setBounds(200, 200, 200, 30);
        add(medicineField);

        totalLabel = new JLabel("Total Amount:");
        totalLabel.setBounds(50, 250, 140, 30);
        add(totalLabel);

        totalField = new JTextField();
        totalField.setBounds(200, 250, 200, 30);
        totalField.setEditable(false);
        add(totalField);

        paymentStatusLabel = new JLabel("Payment Status:");
        paymentStatusLabel.setBounds(50, 300, 140, 30);
        add(paymentStatusLabel);

        paymentField = new JTextField();
        paymentField.setBounds(200, 300, 200, 30);
        add(paymentField);

        generateButton = new JButton("Generate Bill");
        generateButton.setBounds(150, 380, 180, 40);

        generateButton.addActionListener(this);

        add(generateButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int patientId = Integer.parseInt(patientIdField.getText());

        double consultationFee =
                Double.parseDouble(consultationField.getText());

        double medicineCharge =
                Double.parseDouble(medicineField.getText());

        double total =
                consultationFee + medicineCharge;

        totalField.setText(String.valueOf(total));

        String paymentStatus = paymentField.getText();

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO billing(patient_id, consultation_fee, medicine_charge, total_amount, payment_status) VALUES(?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, patientId);
            pst.setDouble(2, consultationFee);
            pst.setDouble(3, medicineCharge);
            pst.setDouble(4, total);
            pst.setString(5, paymentStatus);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Bill Generated Successfully");

            patientIdField.setText("");
            consultationField.setText("");
            medicineField.setText("");
            totalField.setText("");
            paymentField.setText("");

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new BillingPage();
    }
}