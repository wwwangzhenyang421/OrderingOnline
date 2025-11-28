package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteDishes extends JFrame implements ActionListener {

    // Delete an existed dish
    
    private JPanel contain;
    private JLabel dishName;
    private JTextField dishNamet;
    private JButton submit;

    public DeleteDishes() {
        super("Delete Dish");
        setSize(300, 360);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        dishName = new JLabel("dishName");
        submit = new JButton("submit");
        dishNamet = new JTextField();
        dishName.setBounds(25, 60, 75, 35);
        dishNamet.setBounds(100, 60, 160, 35);
        submit.setBounds(100, 120, 75, 30);

        contain.add(dishName);
        contain.add(dishNamet);
        contain.add(submit);

        submit.addActionListener(this);
        add(contain);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String ch = dishNamet.getText();
            if (!ch.isEmpty()) {
                if (isMemberAndDelete(ch)) {
                    JOptionPane.showMessageDialog(this, "The " + ch + " was deleted successfully!", "prompt", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "This " + ch + " doesn't exist!", "prompt", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private boolean isMemberAndDelete(String id) {
        String filePath = System.getProperty("user.dir") + "/data/dishes.txt";
        ArrayList<String> fileContent = readFileContent(filePath);
        boolean found = false;

        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            String[] result = line.split(" ");
            if (result[1].equals(id)) {
                found = true;
                fileContent.remove(i);
                writeFileContent(filePath, fileContent); // Write the deleted content back to the file
                break; // Stop the loop when the matching ID is found
            }
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
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Error writing file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }

    // public static void main(String[] args) {
    //     new DeleteDishes();
    // }
}