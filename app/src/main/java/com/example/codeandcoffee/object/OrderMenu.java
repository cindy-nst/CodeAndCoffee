package com.example.codeandcoffee.object;

public class OrderMenu extends CoffeeMenuItem{
    private int quantity;

    public OrderMenu(String name, double price, int image, int quantity) {
        super(name, price, image);
        this.quantity = quantity;
    }

    public OrderMenu(String name, double price, int image) {
        super(name, price, image);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
