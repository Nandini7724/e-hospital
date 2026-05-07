package dashboard;

import patient.AddPatient;
import patient.ViewPatients;
import doctor.AddDoctor;
import doctor.ViewDoctors;
import appointment.AppointmentPage;
import billing.BillingPage;
import medicine.MedicinePage;
import login.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Dashboard extends JFrame implements ActionListener {

    JLabel titleLabel;

    JButton patientButton;
    JButton viewPatientButton;
    JButton doctorButton;
    JButton viewDoctorButton;
    JButton appointmentButton;
    JButton billingButton;
    JButton medicineButton;
    JButton logoutButton;

    public Dashboard() {

        setTitle("e-Hospital Dashboard");
        setSize(700, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("e-Hospital Management Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(140, 40, 450, 40);
        add(titleLabel);

        patientButton = new JButton("Patient Management");
        patientButton.setBounds(80, 140, 220, 50);
        patientButton.addActionListener(this);
        add(patientButton);

        viewPatientButton = new JButton("View Patients");
        viewPatientButton.setBounds(80, 200, 220, 50);

        viewPatientButton.addActionListener(this);

        add(viewPatientButton);

        doctorButton = new JButton("Doctor Management");
        doctorButton.setBounds(360, 140, 220, 50);

        doctorButton.addActionListener(this);

        add(doctorButton);

        viewDoctorButton = new JButton("View Doctors");
        viewDoctorButton.setBounds(360, 200, 220, 50);

        viewDoctorButton.addActionListener(this);

        add(viewDoctorButton);

        appointmentButton = new JButton("Appointments");
        appointmentButton.setBounds(80, 240, 220, 50);

        appointmentButton.addActionListener(this);

        add(appointmentButton);

        billingButton = new JButton("Billing");
        billingButton.setBounds(360, 240, 220, 50);

        billingButton.addActionListener(this);

        add(billingButton);

        medicineButton = new JButton("Medicine Inventory");
        medicineButton.setBounds(80, 340, 220, 50);

        medicineButton.addActionListener(this);

        add(medicineButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(360, 340, 220, 50);

        logoutButton.addActionListener(this);

        add(logoutButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == patientButton) {

            new AddPatient();
        }
        if(e.getSource() == viewPatientButton) {

            new ViewPatients();
        }
        if(e.getSource() == doctorButton) {

            new AddDoctor();
        }
        if(e.getSource() == viewDoctorButton) {

            new ViewDoctors();
        }
        if(e.getSource() == appointmentButton) {

            new AppointmentPage();
        }
        if(e.getSource() == billingButton) {

            new BillingPage();
        }
        if(e.getSource() == medicineButton) {

            new MedicinePage();
        }
        if(e.getSource() == logoutButton) {

            new LoginPage();

            dispose();
        }
    }

    public static void main(String[] args) {

        new Dashboard();
    }
}