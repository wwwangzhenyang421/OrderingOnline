package model;

public class Order extends Dish {
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerPhone;

    public Order(String dishName, String dishPrice, String AdministratorWindowName, String material,
                 String customerName, String customerAddress, String CustomerPhone) {
        super(dishName, dishPrice, AdministratorWindowName, material);
        this.CustomerName = customerName;
        this.CustomerAddress = customerAddress;
        this.CustomerPhone = CustomerPhone;
    
    }

    public Order(String dishName, String dishPrice, String AdministratorWindowName, String material, String flavor, String weight,
                 String customerName, String customerAddress, String CustomerPhone) {
        super(dishName, dishPrice, AdministratorWindowName, material, flavor, weight);
        this.CustomerName = customerName;
        this.CustomerAddress = customerAddress;
        this.CustomerPhone = CustomerPhone;
      }    

    public Order(String dishName, String dishPrice, String AdministratorWindowName, String material, String flavor, String weight, String isSoldOut,
                 String customerName, String customerAddress, String CustomerPhone) {
        super(dishName, dishPrice, AdministratorWindowName, material, flavor, weight, isSoldOut);
        this.CustomerName = customerName;
        this.CustomerAddress = customerAddress;
        this.CustomerPhone = CustomerPhone;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        this.CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.CustomerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.CustomerPhone = customerPhone;
    }

}