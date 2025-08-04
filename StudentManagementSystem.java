import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentManagementSystem {

    // Student class
    static class Student {
        String name;
        String roll;
        String branch;

        Student(String name, String roll, String branch) {
            this.name = name;
            this.roll = roll;
            this.branch = branch;
        }

        public String toString() {
            return "Name: " + name + ", Roll No: " + roll + ", Branch: " + branch;
        }
    }

    // ArrayList to store student records
    static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        // Frame
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Title
        JLabel title = new JLabel("Student Management System");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(120, 10, 300, 30);
        frame.add(title);

        // Labels & TextFields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 60, 200, 30);
        frame.add(nameField);

        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(50, 110, 100, 30);
        frame.add(rollLabel);

        JTextField rollField = new JTextField();
        rollField.setBounds(150, 110, 200, 30);
        frame.add(rollField);

        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setBounds(50, 160, 100, 30);
        frame.add(branchLabel);

        JTextField branchField = new JTextField();
        branchField.setBounds(150, 160, 200, 30);
        frame.add(branchField);

        // Output Area
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(50, 250, 380, 250);
        frame.add(scrollPane);

        // Buttons
        JButton addBtn = new JButton("Add Student");
        addBtn.setBounds(50, 210, 120, 30);
        frame.add(addBtn);

        JButton showBtn = new JButton("Show All");
        showBtn.setBounds(180, 210, 100, 30);
        frame.add(showBtn);

        JButton clearBtn = new JButton("Clear Fields");
        clearBtn.setBounds(290, 210, 120, 30);
        frame.add(clearBtn);

        JButton deleteBtn = new JButton("Delete by Roll No");
        deleteBtn.setBounds(150, 520, 180, 30);
        frame.add(deleteBtn);

        // Action Listeners

        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String branch = branchField.getText().trim();

            if (name.isEmpty() || roll.isEmpty() || branch.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            studentList.add(new Student(name, roll, branch));
            JOptionPane.showMessageDialog(frame, "Student added successfully!");
            nameField.setText(""); rollField.setText(""); branchField.setText("");
        });

        showBtn.addActionListener(e -> {
            outputArea.setText("");
            if (studentList.isEmpty()) {
                outputArea.setText("No student records found.");
            } else {
                for (Student s : studentList) {
                    outputArea.append(s + "\n");
                }
            }
        });

        clearBtn.addActionListener(e -> {
            nameField.setText(""); rollField.setText(""); branchField.setText("");
        });

        deleteBtn.addActionListener(e -> {
            String rollToDelete = JOptionPane.showInputDialog(frame, "Enter Roll No to delete:");
            if (rollToDelete != null) {
                boolean found = false;
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).roll.equals(rollToDelete.trim())) {
                        studentList.remove(i);
                        JOptionPane.showMessageDialog(frame, "Student deleted successfully.");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(frame, "No student found with that Roll No.");
                }
            }
        });

        // Show Frame
        frame.setVisible(true);
    }
}