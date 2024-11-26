package SIMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeMenu {
    public static void main(String[] args)
    {
        //Create the main frame
        JFrame frame = new JFrame ("Home Menu");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        //Add the label
        JLabel titleLabel = new JLabel("Student Identity Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        frame.add(titleLabel, BorderLayout.NORTH);

        //Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));

        //Create the buttons
        JButton addStudentButton = new JButton("Add Student");
        JButton viewStudentButton = new JButton("View Student Details");
        JButton updateStudentButton = new JButton("Update Student Details");
        JButton removeStudentButton = new JButton("Remove Student");

        //Add buttons to the panel
        buttonPanel.add(addStudentButton);
        buttonPanel.add(viewStudentButton);
        buttonPanel.add(updateStudentButton);
        buttonPanel.add(removeStudentButton);

        //Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        //Add action listener for the Add Student button
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentMenu addStudentMenu = new AddStudentMenu(); // Create an instance
                addStudentMenu.setVisible(true); // Call setVisible on the instance
            }
        });
        //Set the frame to be visible


        //Add action listener for the View Students button
        viewStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ViewStudentMenu viewStudentMenu = new ViewStudentMenu(); //Create an instance
                viewStudentMenu.setVisible(true); //Opens the View Student Details window
            }
        });

        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateStudentMenu updateStudentMenu = new UpdateStudentMenu(); // Create an instance
                updateStudentMenu.setVisible(true); // Opens the Update Student Details window
            }
        });

        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveStudentMenu removeStudentMenu = new RemoveStudentMenu(); // Create an instance
                removeStudentMenu.setVisible(true); // Opens the Remove Student window
            }
        });
        

        frame.setVisible(true);
    }
}
