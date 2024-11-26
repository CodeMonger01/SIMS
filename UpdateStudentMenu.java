package SIMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateStudentMenu extends JFrame {
    private JTextField studentIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField departmentIdField;
    private JTextField emailField;

    public UpdateStudentMenu() {
        //Set up the frame
        setTitle(("Update Student Details"));
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        //Labels and fields for input
        add(new JLabel("Enter Student ID and SEARCH: "));
        studentIdField = new JTextField();
        add(studentIdField);

        add(new JLabel("First Name: "));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Department ID:"));
        departmentIdField = new JTextField();
        add(departmentIdField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        //Search button
        JButton searchButton = new JButton("Search");
        add(searchButton);

        //Update button
        JButton updateButton = new JButton("Update");
        add(updateButton);

        //Action Listener for Search
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        //Action Listener for Update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
    }

    //Method to fetch student details from the database
    private void searchStudent()
    {
        String studentId = studentIdField.getText().trim();

        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.");
        }

        try (Connection connection = DatabaseConnection.connect()) {
            String query = "SELECT * FROM Students WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(studentId));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                firstNameField.setText(resultSet.getString("first_name"));
                lastNameField.setText(resultSet.getString("last_name"));
                departmentIdField.setText(String.valueOf(resultSet.getInt("department_id")));
                emailField.setText(resultSet.getString("email"));
            }

            else {
                JOptionPane.showMessageDialog(this, "Student not found");
            }
        }

        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching student details: "+ ex.getMessage());
        }
    }

    //Method to update student details in the database
    private void updateStudent() {
        String studentId = studentIdField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String departmentId = departmentIdField.getText().trim();
        String email = emailField.getText().trim();

        if ( studentId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || departmentId.isEmpty() || email.isEmpty() )
        {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try (Connection connection = DatabaseConnection.connect())
        {
            String query = "UPDATE Students SET first_name = ?, last_name = ?, department_id = ?, email = ? WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, Integer.parseInt(departmentId));
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, Integer.parseInt(studentId));

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0)
            {
                JOptionPane.showMessageDialog(this, "Student details updated successfully!");
            }

            else
            {
                JOptionPane.showMessageDialog(this, "Student ID not found.");
            }
        }

        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error updating student details: "+ ex.getMessage());
        }
    }
}
