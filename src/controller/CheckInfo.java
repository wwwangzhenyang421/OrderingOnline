package controller;

import java.io.BufferedReader;
import java.io.FileReader;

public class CheckInfo {
  /*
   * Check user information when logging in
   */

  public int isMember(String table, String id, String passwd) {

    // String file = "D://test//".concat(table.concat(".txt"));
    String file = System.getProperty("user.dir") + "/data/".concat(table).concat(".txt");
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));// Construct a BufferedReader class to read the file
      String s = null;
      while ((s = br.readLine()) != null) {// Use the readLine method to read one line at a time
        String[] result = s.split(" ");
        if (result[0].equals(id) && result[2].equals(passwd)) {
          br.close();
          return 1;// Check whether the login information is correct
        }
        if (result[0].equals(id)) {
          br.close();
          return 2;// Determine whether the user exists
        }

      }
      br.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return 0;
  }
  public static void main(String[] args) {
    new CheckInfo();
}
}
