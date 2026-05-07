package doctor;

import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddDoctor extends JFrame implements ActionListener {

    JLabel titleLabel, nameLabel, specializationLabel,
            experienceLabel, phoneLabel, availabilityLabel;

    JTextField nameField,
            experienceField,
            phoneField;

    JComboBox<String> specializationBox,
            availabilityBox;

    JButton saveButton;

    public AddDoctor() {

        setTitle("Add Doctor");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titleLabel = new JLabel("Doctor Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(120, 30, 300, 30);
        add(titleLabel);

        nameLabel = new JLabel("Doctor Name:");
        nameLabel.setBounds(50, 100, 120, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 100, 200, 30);
        add(nameField);

        specializationLabel = new JLabel("Specialization:");
        specializationLabel.setBounds(50, 150, 120, 30);
        add(specializationLabel);

        specializationBox = new JComboBox<>();

        specializationBox.addItem("Cardiologist");
        specializationBox.addItem("Neurologist");
        specializationBox.addItem("Orthopedic");
        specializationBox.addItem("Dentist");
        specializationBox.addItem("Pediatrician");
        specializationBox.addItem("General Physician");

        specializationBox.setBounds(200, 150, 200, 30);

        add(specializationBox);

        experienceLabel = new JLabel("Experience:");
        experienceLabel.setBounds(50, 200, 120, 30);
        add(experienceLabel);

        experienceField = new JTextField();
        experienceField.setBounds(200, 200, 200, 30);
        add(experienceField);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 250, 120, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(200, 250, 200, 30);
        add(phoneField);

        availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setBounds(50, 300, 120, 30);
        add(availabilityLabel);

        availabilityBox = new JComboBox<>();

        availabilityBox.addItem("Morning");
        availabilityBox.addItem("Afternoon");
        availabilityBox.addItem("Evening");
        availabilityBox.addItem("Night");

        availabilityBox.setBounds(200, 300, 200, 30);

        add(availabilityBox);

        saveButton = new JButton("Save Doctor");
        saveButton.setBounds(160, 380, 150, 40);

        saveButton.addActionListener(this);

        add(saveButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();

        String specialization =
                specializationBox.getSelectedItem().toString();

        int experience = Integer.parseInt(experienceField.getText());

        String phone = phoneField.getText();

        String availability =
                availabilityBox.getSelectedItem().toString();

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO doctors(doctor_name, specialization, experience, phone, availability) VALUES(?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, specialization);
            pst.setInt(3, experience);
            pst.setString(4, phone);
            pst.setString(5, availability);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Doctor Added Successfully");

            nameField.setText("");
            experienceField.setText("");
            phoneField.setText("");


        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new AddDoctor();
    }
}