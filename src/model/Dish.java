package model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Dish {

  private String dishName;
  private String dishPrice;
  private String SellerWindowName;
  private String material;
  private String flavor;
  private String isSoldOut;
  private String weight;

  public Dish(String dishName, String dishPrice, String SellerWindowName, String material) {

    this.dishName = dishName;
    this.dishPrice =dishPrice;
    this.SellerWindowName = SellerWindowName;
    this.material = material;

  }

  public Dish(String dishName, String dishPrice, String SellerWindowName, String material, String flavor, String weight) {

    this.dishName = dishName;
    this.dishPrice =dishPrice;
    this.SellerWindowName = SellerWindowName;
    this.material = material;
    this.flavor = flavor;
    this.weight = weight;

  }

  public Dish(String dishName, String dishPrice, String SellerWindowName, String material, String flavor, String weight, String isSoldOut) {

    this.dishName = dishName;
    this.dishPrice =dishPrice;
    this.SellerWindowName = SellerWindowName;
    this.material = material;
    this.flavor = flavor;
    this.weight = weight;
    this.isSoldOut = isSoldOut;

  }


  public String getDishName() {
    return dishName;
  }

  public void setDishName(String dishName) {
    this.dishName = dishName;
  }

  public String getDishPrice() {
    return dishPrice;
  }

  public void setDishPrice(String dishPrice) {
    this.dishPrice = dishPrice;
  }

  public String getSellerWindowName() {
    return SellerWindowName;
  }

  public void setSellerWindowName(String SellerWindowName) {
    this.SellerWindowName = SellerWindowName;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getFlavor() {
    return flavor;
  }

  public void setFlavor(String flavor) {

    this.flavor = flavor;
  }

  public String getIsSoldOut() {
    return isSoldOut;
  }

  public void setIsSoldOut(String isSoldOut) {
    this.isSoldOut = isSoldOut;
  }

  public int hasDish() {
    String file = System.getProperty("user.dir") + "/data/dishes.txt";
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String s = null;
      while ((s = br.readLine()) != null) {
        String[] result = s.split(" ");
        if (result[0].equals(this.dishName)) {
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

  public int isValidate() {
    if (Integer.valueOf(this.weight) < 0 || Integer.valueOf(this.weight) > 1200) {
      return 1;
    }
    return 0;
  }

}
