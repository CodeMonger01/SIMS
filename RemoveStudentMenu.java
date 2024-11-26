package SIMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RemoveStudentMenu extends JFrame {
    private JTextField studentIdField;

    public RemoveStudentMenu() {
        // Set up the frame
        setTitle("Remove Student");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Labels and fields for input
        add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        add(studentIdField);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        add(deleteButton);

        // Action Listener for Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
    }

    // Method to remove student from the database
    private void removeStudent() {
        String studentId = studentIdField.getText().trim();

        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.");
            return;
        }

        try (Connection connection = DatabaseConnection.connect()) {
            // Check if student exists
            String checkQuery = "SELECT * FROM Students WHERE student_id= ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, Integer.parseInt(studentId));
            ResultSet resultSet = checkStatement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Student ID not found.");
                return;
            }

            // Delete student
            String deleteQuery = "DELETE FROM Students WHERE student_id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, Integer.parseInt(studentId));

            int rowsDeleted = deleteStatement.executeUpdate();
            if (rowsDeleted > 0) 
            {
                JOptionPane.showMessageDialog(this, "Student removed successfully!");
            } 
            else
            {
                JOptionPane.showMessageDialog(this, "Failed to remove student.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error removing student: " + ex.getMessage());
        }
    }
}
