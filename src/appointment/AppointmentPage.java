package appointment;

import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AppointmentPage extends JFrame implements ActionListener {

    JLabel titleLabel, patientIdLabel,
            doctorIdLabel, dateLabel,
            timeLabel, statusLabel;

    JTextField patientIdField,
            doctorIdField,
            dateField,
            timeField,
            statusField;

    JButton bookButton;

    public AppointmentPage() {

        setTitle("Book Appointment");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titleLabel = new JLabel("Appointment Booking");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(120, 30, 300, 30);
        add(titleLabel);

        patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setBounds(50, 100, 120, 30);
        add(patientIdLabel);

        patientIdField = new JTextField();
        patientIdField.setBounds(180, 100, 200, 30);
        add(patientIdField);

        doctorIdLabel = new JLabel("Doctor ID:");
        doctorIdLabel.setBounds(50, 150, 120, 30);
        add(doctorIdLabel);

        doctorIdField = new JTextField();
        doctorIdField.setBounds(180, 150, 200, 30);
        add(doctorIdField);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 200, 120, 30);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(180, 200, 200, 30);
        add(dateField);

        timeLabel = new JLabel("Time:");
        timeLabel.setBounds(50, 250, 120, 30);
        add(timeLabel);

        timeField = new JTextField();
        timeField.setBounds(180, 250, 200, 30);
        add(timeField);

        statusLabel = new JLabel("Status:");
        statusLabel.setBounds(50, 300, 120, 30);
        add(statusLabel);

        statusField = new JTextField();
        statusField.setBounds(180, 300, 200, 30);
        add(statusField);

        bookButton = new JButton("Book Appointment");
        bookButton.setBounds(140, 380, 180, 40);

        bookButton.addActionListener(this);

        add(bookButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int patientId = Integer.parseInt(patientIdField.getText());

        int doctorId = Integer.parseInt(doctorIdField.getText());

        String date = dateField.getText();

        String time = timeField.getText();

        String status = statusField.getText();

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time, status) VALUES(?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, patientId);
            pst.setInt(2, doctorId);
            pst.setString(3, date);
            pst.setString(4, time);
            pst.setString(5, status);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Appointment Booked Successfully");

            patientIdField.setText("");
            doctorIdField.setText("");
            dateField.setText("");
            timeField.setText("");
            statusField.setText("");

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new AppointmentPage();
    }
}