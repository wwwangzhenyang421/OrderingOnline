package controller;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Dish;

@SuppressWarnings("serial")
public class AddDishes extends JFrame implements ActionListener {

  JPanel contain;
  JButton submit;
  JLabel dishName,dishPrice,material,flavor,isSoldOut,weight;
  JTextField dishNamet,dishPricet,materialt,flavort,isSoldOutt,weightt;
  String WindowID;

  public AddDishes(String WindowID) {
    super("Add Dishes");
    this.WindowID = WindowID;
    setSize(400, 520);
    setLocation(600, 400);
    contain = new JPanel();
    contain.setLayout(null);
    dishName = new JLabel("Name");
    dishPrice = new JLabel("Price");
    material = new JLabel("Material");
    flavor = new JLabel("Flavor");
    isSoldOut = new JLabel("IsSoldOut");
    weight = new JLabel("Weight");


    submit = new JButton("Submit");

    dishNamet= new JTextField();
    dishPricet= new JTextField();
    materialt= new JTextField();
    flavort= new JTextField();
    isSoldOutt= new JTextField();
    weightt= new JTextField();
    

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

    submit.setBounds(100, 365, 100, 30);
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
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
  }

  public int hasdishes(String id) { 
    String file = System.getProperty("user.dir") + "/data/dishes.txt";
    // System.out.println(file);
    try {
      BufferedReader br = new BufferedReader(new FileReader(file)); 
      String s = null;
      while ((s = br.readLine()) != null) {
        String[] result = s.split(" ");
        if (result[0].equals(id)) {
          br.close();
          return 1;

        }
      }
      br.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return 0;
  }

  @SuppressWarnings("unused")
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submit) {
      if (dishNamet.getText().equals("") || dishPricet.getText().equals("") || materialt.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Information cannot be empty.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
      }
      else {
        if (hasdishes(dishNamet.getText()) == 1) {
          JOptionPane.showMessageDialog(null, "This dish already exists.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          String file = System.getProperty("user.dir") + "/data/dishes.txt";

          ArrayList<String> modifiedContent = new ArrayList<>();
          // StringBuilder result = new StringBuilder();
          try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) { 
              String[] result = s.split(" ");

              String s1 = "";
              for (int i = 0; i < result.length - 1; i++) {
                s1 = s1 + result[i];
                s1 = s1 + " ";
              }
              s1 = s1 + result[result.length - 1];
              // System.out.println(s1);
              modifiedContent.add(s1);
            }
            br.close();

          }
          catch (Exception e1) {
            e1.printStackTrace();
          }

          Dish dish = new Dish(dishNamet.getText(), dishPricet.getText(), WindowID, materialt.getText(),
              flavort.getText(),weightt.getText(), isSoldOutt.getText());

          modifiedContent.add(dish.getSellerWindowName() + " " + dish.getDishName() + " " + dish.getDishPrice() + " " 
              + dish.getMaterial() + " " + dish.getFlavor()+ " " + dish.getIsSoldOut() + " " + dish.getWeight() );

          try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String element : modifiedContent) {
              bw.write(element);
              bw.newLine();
            }

            bw.close();
            fw.close();
          }
          catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }

          JOptionPane.showMessageDialog(null, "Success!", "Prompt", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    }
  }

  @Override
  public void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      this.dispose();
      setVisible(false);
    }
  }

  // public static void main(String[] args) {
  //   new AddDishes();
  // }
}
