package com.example.codeandcoffee;

public class OrderHistoryItem {
    private String itemName;
    private String orderDate;
    private float rating;

    public OrderHistoryItem(String itemName, String orderDate, float rating) {
        this.itemName = itemName;
        this.orderDate = orderDate;
        this.rating = rating;
    }

    public String getItemName() {
        return itemName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public float getRating() {
        return rating;
    }
}

