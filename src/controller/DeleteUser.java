package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteUser extends JFrame implements ActionListener {

    private JPanel contain;
    private JLabel id;
    private JTextField idt;
    private Choice choice;
    private JButton submit;

	// Delete an existed student, teacher or seller
    public DeleteUser() {
        super("Delete User");
        setSize(300, 360);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        choice = new Choice();
        choice.addItem("Student");
        choice.addItem("Teacher");
        choice.addItem("Seller");
        id = new JLabel("id");
        submit = new JButton("submit");
        idt = new JTextField();
        id.setBounds(25, 60, 75, 35);
        idt.setBounds(80, 60, 180, 35);
        choice.setBounds(80, 100, 180, 35);
        submit.setBounds(100, 160, 75, 30);

        contain.add(id);
        contain.add(idt);
        contain.add(choice);
        contain.add(submit);

        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(WindowEvent.WINDOW_CLOSING);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String ch = choice.getSelectedItem();
            String fileType = "";
            if ("Student".equals(ch)) {
                fileType = "student";
            } else if ("Teacher".equals(ch)) {
                fileType = "teacher";
            } else if ("Seller".equals(ch)) {
                fileType = "seller";
            }

            if (!fileType.isEmpty()) {
                if (isMemberAndDelete(fileType, idt.getText())) {
                    JOptionPane.showMessageDialog(this, "The " + fileType + " was deleted successfully!", "prompt", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "This " + fileType + " doesn't exist!", "prompt", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private boolean isMemberAndDelete(String fileType, String id) {
        String filePath = System.getProperty("user.dir") + "/data/" + fileType + ".txt";
        ArrayList<String> fileContent = readFileContent(filePath);
        boolean found = false;

        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            String[] result = line.split(" ");
            if (result[0].equals(id)) {
                found = true;
                fileContent.remove(i);
                break; // Stop the loop when the matching ID is found
            }
        }

        if (found) {
            writeFileContent(filePath, fileContent);
        }

        return found;
    }

    private ArrayList<String> readFileContent(String filePath) {
        ArrayList<String> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private void writeFileContent(String filePath, ArrayList<String> content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : content) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new DeleteUser();
    }
}