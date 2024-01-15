package com.example.codeandcoffee.object;
import com.example.codeandcoffee.R;
public class CoffeeMenuItem {
    private String name;
    private double price;
    private int image; // Resource ID for the local drawable image
    private String description;

    public CoffeeMenuItem(String name, double price, int image, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public CoffeeMenuItem() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}