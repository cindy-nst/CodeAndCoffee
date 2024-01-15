package com.example.codeandcoffee.object;

import android.os.Parcel;
import android.os.Parcelable;

public class CoffeeMenuItem implements Parcelable {
    private String name;
    private double price;
    private int image;
    private String description;
    private String category;

    public CoffeeMenuItem(String name, double price, int image, String description, String category) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    // Add default constructor
    public CoffeeMenuItem() {
        // Default constructor
    }

    protected CoffeeMenuItem(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        image = in.readInt();
        description = in.readString();
        category = in.readString();
    }

    public static final Creator<CoffeeMenuItem> CREATOR = new Creator<CoffeeMenuItem>() {
        @Override
        public CoffeeMenuItem createFromParcel(Parcel in) {
            return new CoffeeMenuItem(in);
        }

        @Override
        public CoffeeMenuItem[] newArray(int size) {
            return new CoffeeMenuItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    // Other methods...

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(image);
        dest.writeString(description);
        dest.writeString(category);
    }
}
