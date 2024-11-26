package SIMS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudentMenu extends JFrame {
    public AddStudentMenu() {
        // Set up the frame
        setTitle("Add Student");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closes only this window
        setLayout(new GridLayout(5, 2, 10, 10));

        // Add input fields
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();

        JLabel departmentIdLabel = new JLabel("Department ID:");
        JTextField departmentIdField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton submitButton = new JButton("Add Student");

        // Add components to the frame
        add(firstNameLabel);
        add(firstNameField);

        add(lastNameLabel);
        add(lastNameField);

        add(departmentIdLabel);
        add(departmentIdField);

        add(emailLabel);
        add(emailField);

        add(new JLabel()); // Empty space
        add(submitButton);

        // Add ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                int departmentId = Integer.parseInt(departmentIdField.getText());
                String email = emailField.getText();

                try (Connection connection = DatabaseConnection.connect()) {
                    String query = "INSERT INTO Students(first_name, last_name, department_id, email) VALUES (?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setInt(3, departmentId);
                    statement.setString(4, email);
                    statement.executeUpdate();

                    JOptionPane.showMessageDialog(AddStudentMenu.this, "Student added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddStudentMenu.this, "Error: " + ex.getMessage());
                }
            }
        });
    }
}



