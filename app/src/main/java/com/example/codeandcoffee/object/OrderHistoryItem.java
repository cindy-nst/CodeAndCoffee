package com.example.codeandcoffee.object;

public class OrderHistoryItem {
    String id;
    private String itemName;
    private String quantity;
    private String price;
    private String orderDate;
    private float rating;
    private int imageCoffee;

    public OrderHistoryItem(String id, String itemName, String quantity, String price, String orderDate, float rating, int imageCoffee) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
        this.rating = rating;
        this.imageCoffee = imageCoffee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getImageCoffee() {
        return imageCoffee;
    }

    public void setImageCoffee(int imageCoffee) {
        this.imageCoffee = imageCoffee;
    }
}

