package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteOrder extends JFrame {

    private JPanel contain;
    private JLabel orderName, orderNum;
    private JTextField orderNamet, orderNumt;
    private JButton submit;
    String path;

    public DeleteOrder() {
        super("Delete Order");
        setSize(330, 360);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        orderName = new JLabel("Need to delete ID:");
        orderNum = new JLabel("Need to delete Num:");
        submit = new JButton("submit");
        orderNamet = new JTextField();
        orderNumt = new JTextField();

        orderName.setBounds(15, 40, 125, 35);
        orderNamet.setBounds(145, 40, 160, 35);
        orderNum.setBounds(15, 80, 125, 35);
        orderNumt.setBounds(145, 80, 160, 35);
        submit.setBounds(100, 140, 75, 30);

        contain.add(orderName);
        contain.add(orderNamet);
        contain.add(orderNum);
        contain.add(orderNumt);
        contain.add(submit);

        submit.addActionListener(e -> {
            String path = System.getProperty("user.dir") + "/data/orders.txt";
            try {
                int orderNumber = Integer.parseInt(orderNumt.getText());
                deleteNthOccurrence(path, orderNamet.getText(), orderNumber);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input for order number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            contain.revalidate(); // Relayout
            contain.repaint(); // Refresh the interface
        });

        add(contain);
        setVisible(true);
    }

    public void deleteNthOccurrence(String filePath, String target, int orderNumber) {
        List<String> updatedLines = new ArrayList<>();
        int occurrence = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] result = line.split(" ");
                if (result[0].equals(target)) {
                    occurrence++;
                    if (occurrence != orderNumber) {
                        updatedLines.add(line);
                    }
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : updatedLines) {
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DeleteOrder();
    }
}