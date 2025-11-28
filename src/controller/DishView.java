package controller;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DishView extends JFrame implements ActionListener {
    private JLabel inputLabel, attentionLabel;
    private JTextField searchField;
    private JButton searchButton, readSelectedButton;
    private JList<String> menuList;
    private DefaultListModel<String> listModel;
    private JTextArea resultArea;
    private JPanel panel;
    String id;
    int flag;

    public DishView(String id, int flag) {
        setTitle("Dish Search & Select");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.id = id;
        this.flag = flag;

        panel = new JPanel();
        panel.setLayout(null);

        inputLabel = new JLabel("Enter text to search:");
        attentionLabel = new JLabel("any information about the dish, such as price: 15");
        inputLabel.setBounds(20, 20, 150, 25);
        attentionLabel.setBounds(20, 50, 300, 25);
        panel.add(inputLabel);
        panel.add(attentionLabel);

        searchField = new JTextField();
        searchField.setBounds(170, 20, 150, 25);
        panel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(100, 77, 100, 25);
        searchButton.addActionListener(this);
        panel.add(searchButton);

        listModel = new DefaultListModel<>();
        menuList = new JList<>(listModel);
        menuList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(menuList);
        scrollPane.setBounds(20, 110, 345, 200);
        panel.add(scrollPane);

        readSelectedButton = new JButton("Read Selected");
        readSelectedButton.setBounds(100, 320, 150, 25);
        readSelectedButton.addActionListener(this);
        panel.add(readSelectedButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBounds(20, 350, 345, 100);
        resultArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                listModel.remove(index);
            }
        });
        panel.add(resultArea);

        loadMenuItems(id);
        add(panel);
        setVisible(true);
    }

    private void loadMenuItems(String id) {
        String documentPath = System.getProperty("user.dir") + "/data/dishes.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(documentPath))) {
            String line;
            if(flag == 1 || flag == 2){
                while ((line = br.readLine()) != null) {
                    listModel.addElement(line);
                }
            }else{
                String menu = findFileForAd(id);
                System.out.println(menu);
                while ((line = br.readLine()) != null) {
                    if(line.toLowerCase().contains(menu)){
                        listModel.addElement(line);
                    }
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            resultArea.setText("Error occurred while reading the document.");
        }
    }

    private String[] findFileForId(String id) {
        String file = "";
        if (flag == 1) {
            // file = "D://test//student.txt";
            file = System.getProperty("user.dir") + "/data/student.txt";
        } else if (flag == 2) {
            // file = "D://test//teacher.txt";
            file = System.getProperty("user.dir") + "/data/teacher.txt";
        } 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String[] values = new String[3];
                if (parts[0].equals(id)) {
                    values[0] = parts[1];
                    values[1] = parts[3];
                    values[2] = parts[4];
                    return values;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private String findFileForAd(String id) {
        String file = System.getProperty("user.dir") + "/data/seller.txt";;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String value;
                if (parts[0].equals(id)) {
                    value = parts[3];
                    return value;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchTerm = searchField.getText().toLowerCase();
            listModel.clear();
            if(flag == 1 || flag == 2){
                try (BufferedReader br = new BufferedReader(new FileReader("OrderingOnline/data/dishes.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.toLowerCase().contains(searchTerm)) {
                            listModel.addElement(line);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error occurred while reading the document.");
                }
            }
            else if(flag == 3){
                String menu = findFileForAd(id);
                try (BufferedReader br = new BufferedReader(new FileReader("OrderingOnline/data/dishes.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.toLowerCase().contains(searchTerm) && line.toLowerCase().contains(menu)) {
                            listModel.addElement(line);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error occurred while reading the document.");
                }
            }
        } else if (e.getSource() == readSelectedButton) {
            StringBuilder selectedItems = new StringBuilder();
            System.out.println(selectedItems.toString());
            if(flag == 1 || flag == 2){
                String[] newOrders = findFileForId(id);
                for (String selectedItem : menuList.getSelectedValuesList()) {
                    selectedItems.append(newOrders[0]).append(" ").append(newOrders[1]).append(" ").append(newOrders[2]).append(" ").append(selectedItem).append("\n");
                }
                resultArea.setText(selectedItems.toString());
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/data/orders.txt", true))) {
                    writer.write(selectedItems.toString());
                    // writer.newLine();
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error occurred while writing to the file.");
                }
            }else if(flag == 3){
                for (String selectedItem : menuList.getSelectedValuesList()) {
                    selectedItems.append(selectedItem).append("\n");
                }
                resultArea.setText(selectedItems.toString());
            }
            
        }
    }

    public static void main(String[] args) {
        new DishView("S02", 1);
    }
}