package doctor;

import Database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewDoctors extends JFrame {

    JTable table;

    DefaultTableModel model;

    JScrollPane scrollPane;

    public ViewDoctors() {

        setTitle("View Doctors");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        table = new JTable(model);

        model.addColumn("Doctor ID");
        model.addColumn("Doctor Name");
        model.addColumn("Specialization");
        model.addColumn("Experience");
        model.addColumn("Phone");
        model.addColumn("Availability");

        fetchDoctors();

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
    }

    public void fetchDoctors() {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM doctors";

            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                model.addRow(new Object[] {

                        rs.getInt("doctor_id"),
                        rs.getString("doctor_name"),
                        rs.getString("specialization"),
                        rs.getInt("experience"),
                        rs.getString("phone"),
                        rs.getString("availability")
                });
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new ViewDoctors();
    }
}