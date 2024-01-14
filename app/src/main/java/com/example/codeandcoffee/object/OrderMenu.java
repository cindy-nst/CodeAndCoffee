package com.example.codeandcoffee.object;

public class OrderMenu extends CoffeeMenuItem{
    private int quantity;

    public OrderMenu(String name, double price, int image, String description, int quantity) {
        super(name, price, image, description);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
