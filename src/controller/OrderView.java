package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class OrderView extends JFrame {
  /*
   * Students inquire about the orders and merchants inquire about the dishes they
   * sell
   */

  JPanel contain;
  JTextArea list;

  public OrderView(String id, int flag) {
    super("Order");
    setSize(600, 400);
    contain = new JPanel();
    setLocation(600, 400);
    list = new JTextArea();
    list.setRows(15); // Set the number of rows
    list.setColumns(45); // Set the number of columns
    list.setEditable(false);
    // Create a JScrollPane and put a JTextArea into it
    JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    contain.add(scrollPane); // Put the JScrollPane containing the scrollbar into JPanel
    list.append("ID\tAddress\tPhoneNum\t\tDishName\t\tPrice\n");

    String CustomerInfo[] = new String[3];
    String OrderInfo[] = new String[5];
    String windowID = null;

    if (flag == 1) { // Customers inquire about the orders

      String path = System.getProperty("user.dir") + "/data/orders.txt";
      String CusList = System.getProperty("user.dir") + "/data/student.txt";
      // Find the name by the student ID
      CustomerInfo = findOrderForId(CusList, id, flag);
      OrderInfo[0] = CustomerInfo[0];
      OrderInfo[1] = CustomerInfo[1];
      OrderInfo[2] = CustomerInfo[2];

      File file = new File(path);// Order

      try {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        while ((s = br.readLine()) != null) {// Read each line of orders
          String[] result = s.split(" ");
          if (result[0].equals(CustomerInfo[0]) && result[1].equals(CustomerInfo[1])) { // check name
            OrderInfo[3] = result[4];
            OrderInfo[4] = result[5];

            list.append(OrderInfo[0] + "\t");
            list.append(OrderInfo[1] + "\t");
            list.append(OrderInfo[2] + "\t");
            list.append(OrderInfo[3] + "\t\t");
             list.append(OrderInfo[4] + "\t");
            list.append("\n");
          }
        }
        br.close();
      }

      catch (Exception e) {
        e.printStackTrace();
      }
    } else if (flag == 2) {
      String path = System.getProperty("user.dir") + "/data/orders.txt";
      String CusList = System.getProperty("user.dir") + "/data/teacher.txt";
      // Find the name by the teacher's ID
      CustomerInfo = findOrderForId(CusList, id, flag);
      OrderInfo[0] = CustomerInfo[0];
      OrderInfo[1] = CustomerInfo[1];
      OrderInfo[2] = CustomerInfo[2];

      File file = new File(path);// Order

      try {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        while ((s = br.readLine()) != null) {// Read each line of orders
          String[] result = s.split(" ");
          if (result[0].equals(CustomerInfo[0]) && result[1].equals(CustomerInfo[1])) { // check name
            OrderInfo[3] = result[4];
            OrderInfo[4] = result[5];

            list.append(OrderInfo[0] + "\t");
            list.append(OrderInfo[1] + "\t");
            list.append(OrderInfo[2] + "\t");
            list.append(OrderInfo[3] + "\t\t");
            list.append(OrderInfo[4] + "\t");
            list.append("\n");
          }
        }
        br.close();
      }

      catch (Exception e) {
        e.printStackTrace();
      }
    } else if (flag == 3) { // merchants inquire about the orders
      String OrderList = System.getProperty("user.dir") + "/data/orders.txt";
      String adList = System.getProperty("user.dir") + "/data/seller.txt";
      // String path = "D://test//course.txt";
      String s = null;

      // 通过adminID找windowID
      try (BufferedReader br = new BufferedReader(new FileReader(adList))) {
        String line;
        while ((line = br.readLine()) != null) {
          String[] parts = line.split(" ");
          if (parts[0].equals(id)) {
            windowID = parts[3];
          }
        }
      } catch (IOException exception) {
        exception.printStackTrace();
      }

      

      try {
        BufferedReader br = new BufferedReader(new FileReader(OrderList));// Check the order
        while ((s = br.readLine()) != null) {
          String[] result = s.split(" ");
          System.out.println(result.length);
          if (result[3].equals(windowID)) {

            OrderInfo[0] = result[0];
            OrderInfo[1] = result[1];
            OrderInfo[2] = result[2];
            OrderInfo[3] = result[4];
            OrderInfo[4] = result[5];

            list.append(OrderInfo[0] + "\t");
            list.append(OrderInfo[1] + "\t");
            list.append(OrderInfo[2] + "\t");
            list.append(OrderInfo[3] + "\t\t");
            list.append(OrderInfo[4] + "\t");
            list.append("\n");
          }
        }
        br.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    add(contain);
    setVisible(true);
  }

  String[] findOrderForId(String file, String id, int flag) {
    String[] OrderInfo = new String[3];
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(" ");

        if (parts[0].equals(id)) {

          OrderInfo[0] = parts[1];
          OrderInfo[1] = parts[3];
          OrderInfo[2] = parts[4];

        }
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    return OrderInfo;
  }

  public static void main(String[] args) {
    new OrderView("A01", 3);
  }
}
