package controller;

import java.awt.AWTEvent;
import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddUser extends JFrame implements ItemListener {

    private JPanel contain;
    private JLabel id, name, PW, dynamicLabel1, dynamicLabel2;
    private JTextField idt, namet, PWt, inputField1, inputField2;
    private JButton submit;
    private Choice choice;

    // Add a new student, teacher or seller
    public AddUser() {
        super("Add User");
        setSize(300, 360);
        setLocation(600, 400);

        contain = new JPanel();
        contain.setLayout(null);

        id = new JLabel("id");
        name = new JLabel("name");
        PW = new JLabel("PW");
        dynamicLabel1 = new JLabel("address"); // By default, address labels are displayed
        dynamicLabel2 = new JLabel("phone");

        submit = new JButton("submit");
        choice = new Choice();
        choice.addItem("Student");
        choice.addItem("Teacher");
        choice.addItem("Seller");

        idt = new JTextField();
        namet = new JTextField();
        PWt = new JTextField();
        inputField1 = new JTextField();
        inputField2 = new JTextField();


        id.setBounds(25, 60, 75, 35);
        idt.setBounds(80, 60, 180, 35);
        name.setBounds(25, 20, 75, 35);
        namet.setBounds(80, 20, 180, 35);
        PW.setBounds(25,100, 75, 35);
        PWt.setBounds(80, 100, 180, 35);
        dynamicLabel1.setBounds(25, 140, 75, 35); // Set the location and size of the address label
        inputField1.setBounds(80, 140, 180, 35); // Set the position and size of the input box
        dynamicLabel2.setBounds(25, 180, 75, 35);
        inputField2.setBounds(80, 180, 180, 35); // Set the position and size of the input 
        choice.setBounds(80, 220, 180, 35);
        submit.setBounds(100, 280, 75, 30);

        contain.add(id);
        contain.add(idt);
        contain.add(name);
        contain.add(namet);
        contain.add(PW);
        contain.add(PWt);
        contain.add(submit);
        contain.add(choice);
        contain.add(inputField1);
        contain.add(dynamicLabel1); // Add an address label to the panel
        contain.add(inputField2);
        contain.add(dynamicLabel2); // Add an phone label to the panel

        choice.addItemListener(this); // Register a choice listener

        submit.addActionListener((e) -> {
          if (idt.getText().isEmpty() || namet.getText().isEmpty() || inputField1.getText().isEmpty() || PWt.getText().isEmpty()) {
              JOptionPane.showMessageDialog(null, "The information cannot be empty!", "prompt", JOptionPane.INFORMATION_MESSAGE);
          } else {
              String selectedOption = choice.getSelectedItem();
              if ("Student".equals(selectedOption)) {
                  handleStudentInfo();
              } else if ("Teacher".equals(selectedOption)) {
                  handleTeacherInfo();
              } else if ("Seller".equals(selectedOption)) {
                  handleSellerInfo();
              }
          }
      });

        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == choice) {
            String selectedOption = choice.getSelectedItem();
            if ("Student".equals(selectedOption)) {
                dynamicLabel1.setBounds(25, 140, 75, 35);
                dynamicLabel1.setText("address");
                contain.add(dynamicLabel2);
                contain.add(inputField2);
            }
            else if ("Teacher".equals(selectedOption)) {
                dynamicLabel1.setBounds(25, 140, 75, 35);
                dynamicLabel1.setText("address");
                contain.add(dynamicLabel2);
                contain.add(inputField2);

            }
            else if ("Seller".equals(selectedOption)) {
                dynamicLabel1.setBounds(15, 140, 75, 35);
                dynamicLabel1.setText("WindowID");
                contain.remove(dynamicLabel2);
                contain.remove(inputField2);
            }
            contain.revalidate(); // Relayout
            contain.repaint(); // Refresh the interface
        }
    }

    private void handleStudentInfo() {
        String file = System.getProperty("user.dir") + "/data/student.txt";
        String user = idt.getText() + " " + namet.getText() + " " + PWt.getText() + " " + inputField1.getText() + " " + inputField2.getText();
        writeToFile(file, user);
        JOptionPane.showMessageDialog(null, "A student has been successfully added", "prompt", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Reopen the page and log in again!",
								"Mention", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void handleTeacherInfo() {
        String file = System.getProperty("user.dir") + "/data/teacher.txt";
        String user = idt.getText() + " " + namet.getText() + " " + PWt.getText() + " " + inputField1.getText() + " " + inputField2.getText();
        writeToFile(file, user);
        JOptionPane.showMessageDialog(null, "A teacher has been successfully added", "prompt", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Reopen the page and log in again!",
								"Mention", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleSellerInfo() {
        String file = System.getProperty("user.dir") + "/data/seller.txt";
        String user = idt.getText() + " " + namet.getText() + " " + PWt.getText() + " " + inputField1.getText();
        writeToFile(file, user);
        JOptionPane.showMessageDialog(null, "An seller has been successfully added", "prompt", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Reopen the page and log in again!",
								"Mention", JOptionPane.INFORMATION_MESSAGE);
    }

    private void writeToFile(String file, String content) {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddUser();
    }
}