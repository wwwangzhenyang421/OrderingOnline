package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DishesEditInfo extends JFrame implements ActionListener {
    private String WindowID, flag;
    private JPanel contain;
    private JButton submit;
    private JLabel dishName,dishPrice,material,flavor,isSoldOut,weight;
    private JTextField dishNamet,dishPricet,materialt,flavort,isSoldOutt,weightt;

	// Modify the info of an existed dish
    public DishesEditInfo(String WindowID) {
        super("Modify dishes info");
        setSize(400, 520);
        setLocation(600, 400);
        this.WindowID = WindowID;

        contain = new JPanel();
        contain.setLayout(null);

        
        submit = new JButton("submit");
        dishNamet = new JTextField();
        dishPricet = new JTextField();
        materialt = new JTextField();
        flavort = new JTextField();
        isSoldOutt = new JTextField();
        weightt = new JTextField();

        dishName = new JLabel("dishName");
        dishPrice = new JLabel("dishPrice");
        material = new JLabel("material");
        flavor = new JLabel("flavor");
        isSoldOut = new JLabel("isSoldOut");
        weight = new JLabel("weight");

        dishName.setBounds(40, 35, 75, 35);
        dishNamet.setBounds(150, 35, 150, 35);
        dishPrice.setBounds(40, 90, 75, 35);
        dishPricet.setBounds(150, 90, 150, 35);
        material.setBounds(40, 145, 75, 35);
        materialt.setBounds(150, 145, 150, 35);
        flavor.setBounds(40, 200, 75, 35);
        flavort.setBounds(150, 200, 150, 35);
        isSoldOut.setBounds(40, 255, 150, 35);
        isSoldOutt.setBounds(150, 255, 150, 35);
        weight.setBounds(40, 310, 150, 35);
        weightt.setBounds(150, 310, 150, 35);

        submit.setBounds(100, 420, 100, 30);
        contain.add(dishName);
        contain.add(dishNamet);
        contain.add(dishPrice);
        contain.add(dishPricet);
        contain.add(material);
        contain.add(materialt);
        contain.add(flavor);
        contain.add(flavort);
        contain.add(isSoldOut);
        contain.add(isSoldOutt);
        contain.add(weight);
        contain.add(weightt);
        contain.add(submit);

        submit.addActionListener(this);
        add(contain);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (dishNamet.getText().isEmpty() || dishPricet.getText().isEmpty() || materialt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The necessary information cannot be empty!", "prompt", JOptionPane.INFORMATION_MESSAGE);
            } 
            else {
                if(findAndUpdateFileForId(WindowID).equals("true")){
                    JOptionPane.showMessageDialog(null, "File updated successfully", "prompt", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Dish not found in the certain Window", "prompt", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    

    private String findAndUpdateFileForId(String WindowID) {
        String filePath = System.getProperty("user.dir") + "/data/dishes.txt";
            try (BufferedReader file = new BufferedReader(new FileReader(filePath))) {
                String line;
                flag = "false";
                StringBuilder inputBuffer = new StringBuilder();
                while ((line = file.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(WindowID)) {
                         if (parts.length > 1 && parts[1].equals(dishNamet.getText())) {
                            // If part[1] is also equal, handle it accordingly
                            inputBuffer.append(WindowID).append(" ").append(dishNamet.getText()).append(" ").append(dishPricet.getText()).append(" ").append(materialt.getText())
                            .append(" ").append(flavort.getText()).append(" ").append(isSoldOutt.getText()).append(" ").append(weightt.getText()).append("\n");
                            flag = "true";
                        } else {
                            inputBuffer.append(line).append("\n");
                        }
                    }
                    else{
                        inputBuffer.append(line).append("\n");
                    }
                }
                file.close();
                FileWriter fileOut = new FileWriter(filePath);
                fileOut.write(inputBuffer.toString());
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return flag;
        }

    public static void main(String[] args) {
        // Create a DishesEditInfo object and display the GUI interface
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DishesEditInfo("谷物渔粉");
            }
        });
    }
}