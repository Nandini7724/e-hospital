package patient;

import Database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewPatients extends JFrame {

    JTable table;

    DefaultTableModel model;

    JScrollPane scrollPane;

    public ViewPatients() {

        setTitle("View Patients");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Phone");
        model.addColumn("Address");
        model.addColumn("Disease");
        model.addColumn("Doctor");
        model.addColumn("Admission Date");

        fetchPatients();

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
    }

    public void fetchPatients() {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM patients";

            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                model.addRow(new Object[] {

                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("disease"),
                        rs.getString("doctor_assigned"),
                        rs.getDate("admission_date")
                });
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new ViewPatients();
    }
}