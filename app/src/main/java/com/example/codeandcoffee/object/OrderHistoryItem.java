package com.example.codeandcoffee.object;

public class OrderHistoryItem {
    String id;
    String gmail;
    private String itemName;
    private int quantity;
    private String price;
    private String orderDate;
    private int imageCoffee;
    private String orderDetail;

    public OrderHistoryItem() {
    }

    public OrderHistoryItem(String id, String gmail, String itemName, int quantity, String price, String orderDate, int imageCoffee, String orderDetail) {
        this.id = id;
        this.gmail = gmail;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
        this.imageCoffee = imageCoffee;
        this.orderDetail = orderDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getImageCoffee() {
        return imageCoffee;
    }

    public void setImageCoffee(int imageCoffee) {
        this.imageCoffee = imageCoffee;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }
}