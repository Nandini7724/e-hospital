package patient;

import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddPatient extends JFrame implements ActionListener {

    JLabel titleLabel, patientIdLabel,
            nameLabel, ageLabel, genderLabel,
            phoneLabel, addressLabel, diseaseLabel,
            doctorLabel, dateLabel;

    JTextField patientIdField,
            nameField,
            ageField,
            phoneField,
            addressField,
            diseaseField,
            dateField;

    JComboBox<String> doctorBox;

    JComboBox<String> genderBox;

    JButton saveButton, searchButton, updateButton;

    public AddPatient() {

        setTitle("Add Patient");
        setSize(500, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titleLabel = new JLabel("Patient Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(120, 30, 300, 30);
        add(titleLabel);
        patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setBounds(50, 80, 100, 30);
        add(patientIdLabel);

        patientIdField = new JTextField();
        patientIdField.setBounds(180, 80, 200, 30);
        add(patientIdField);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 130, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 130, 200, 30);
        add(nameField);

        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 180, 100, 30);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(180, 180, 200, 30);
        add(ageField);

        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 230, 100, 30);
        add(genderLabel);

        genderBox = new JComboBox<>();
        genderBox.addItem("Male");
        genderBox.addItem("Female");
        genderBox.addItem("Other");

        genderBox.setBounds(180, 230, 200, 30);
        add(genderBox);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 280, 100, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(180, 280, 200, 30);
        add(phoneField);
        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 330, 100, 30);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(180, 330, 200, 30);
        add(addressField);


        diseaseLabel = new JLabel("Disease:");
        diseaseLabel.setBounds(50, 380, 100, 30);
        add(diseaseLabel);

        diseaseField = new JTextField();
        diseaseField.setBounds(180, 380, 200, 30);
        add(diseaseField);
        doctorLabel = new JLabel("Doctor:");
        doctorLabel.setBounds(50, 430, 100, 30);
        add(doctorLabel);

        doctorBox = new JComboBox<>();

        loadDoctors();

        doctorBox.setBounds(180, 430, 200, 30);

        add(doctorBox);

        dateLabel = new JLabel("Admission Date:");
        dateLabel.setBounds(50, 480, 120, 30);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(180, 480, 200, 30);
        add(dateField);

        saveButton = new JButton("Save Patient");
        saveButton.setBounds(80, 570, 150, 40);

        saveButton.addActionListener(this);

        add(saveButton);

        searchButton = new JButton("Search");

        searchButton.setBounds(250, 570, 120, 40);

        searchButton.addActionListener(this);

        add(searchButton);

        updateButton = new JButton("Update");

        updateButton.setBounds(160, 620, 120, 40);

        updateButton.addActionListener(this);

        add(updateButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == searchButton) {

            try {

                int patientId =
                        Integer.parseInt(patientIdField.getText());

                Connection con = DBConnection.getConnection();

                String query =
                        "SELECT * FROM patients WHERE patient_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setInt(1, patientId);

                ResultSet rs = pst.executeQuery();

                if(rs.next()) {

                    nameField.setText(rs.getString("name"));

                    ageField.setText(
                            String.valueOf(rs.getInt("age")));

                    genderBox.setSelectedItem(
                            rs.getString("gender"));

                    phoneField.setText(
                            rs.getString("phone"));

                    addressField.setText(
                            rs.getString("address"));

                    diseaseField.setText(
                            rs.getString("disease"));

                    doctorBox.setSelectedItem(
                            rs.getString("doctor_assigned"));

                    dateField.setText(
                            rs.getString("admission_date"));

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Patient Not Found");
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            return;
        }
        if(e.getSource() == updateButton) {

            try {

                int patientId =
                        Integer.parseInt(patientIdField.getText());

                String name = nameField.getText();

                int age =
                        Integer.parseInt(ageField.getText());

                String gender =
                        genderBox.getSelectedItem().toString();

                String phone = phoneField.getText();

                String address = addressField.getText();

                String disease = diseaseField.getText();

                String doctor =
                        doctorBox.getSelectedItem().toString();

                String admissionDate = dateField.getText();

                Connection con = DBConnection.getConnection();

                String query =
                        "UPDATE patients SET name=?, age=?, gender=?, phone=?, address=?, disease=?, doctor_assigned=?, admission_date=? WHERE patient_id=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setString(1, name);
                pst.setInt(2, age);
                pst.setString(3, gender);
                pst.setString(4, phone);
                pst.setString(5, address);
                pst.setString(6, disease);
                pst.setString(7, doctor);
                pst.setString(8, admissionDate);

                pst.setInt(9, patientId);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Patient Updated Successfully");

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            return;
        }

        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());

        String gender = genderBox.getSelectedItem().toString();

        String phone = phoneField.getText();

        String address = addressField.getText();

        String disease = diseaseField.getText();

        String doctor =
                doctorBox.getSelectedItem().toString();

        String admissionDate = dateField.getText();

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO patients(name, age, gender, phone, address, disease, doctor_assigned, admission_date) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, gender);
            pst.setString(4, phone);
            pst.setString(5, address);
            pst.setString(6, disease);
            pst.setString(7, doctor);
            pst.setString(8, admissionDate);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Patient Added Successfully");

            nameField.setText("");
            ageField.setText("");
            phoneField.setText("");
            diseaseField.setText("");
            addressField.setText("");
            dateField.setText("");

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
    public void loadDoctors() {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT doctor_name FROM doctors";

            PreparedStatement pst =
                    con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                doctorBox.addItem(
                        rs.getString("doctor_name"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new AddPatient();
    }
}