package SIMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudentMenu extends JFrame {
    public ViewStudentMenu() {
        //Set up the frame
        setTitle("View Student Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        //Table to display student data
        JTable studentTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        studentTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        //Add table headers
        tableModel.addColumn("Student ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Department ID");
        tableModel.addColumn("Email");

        //Fetch student data from the database
        try (Connection connection = DatabaseConnection.connect())
        {
            String query = "SELECT * FROM Students";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            //Add data to the table
            while (resultSet.next())
            {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int departmentId = resultSet.getInt("department_id");
                String email = resultSet.getString("email");

                tableModel.addRow(new Object[]{studentId, firstName, lastName, departmentId, email});
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error fetching student data: " + e.getMessage());
        }
    }
}
