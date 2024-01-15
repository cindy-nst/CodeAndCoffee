package com.example.codeandcoffee.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.service.controls.actions.BooleanAction;

import androidx.annotation.NonNull;

public class OrderMenu extends CoffeeMenuItem{
    private int quantity;
    private String orderDetail;
    private Boolean whippedCreame;
    private Boolean CaramelDrizzled;
    private Boolean ChocolateDrizzled;

    public OrderMenu(String name, double price, int image, String description, int quantity, String orderDetail, Boolean whippedCreame, Boolean caramelDrizzled, Boolean chocolateDrizzled) {
        super(name, price, image, description);
        this.quantity = quantity;
        this.orderDetail = orderDetail;
        this.whippedCreame = whippedCreame;
        CaramelDrizzled = caramelDrizzled;
        ChocolateDrizzled = chocolateDrizzled;
    }

    public OrderMenu(int quantity, String orderDetail, Boolean whippedCreame, Boolean caramelDrizzled, Boolean chocolateDrizzled) {
        this.quantity = quantity;
        this.orderDetail = orderDetail;
        this.whippedCreame = whippedCreame;
        CaramelDrizzled = caramelDrizzled;
        ChocolateDrizzled = chocolateDrizzled;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Boolean getWhippedCreame() {
        return whippedCreame;
    }

    public void setWhippedCreame(Boolean whippedCreame) {
        this.whippedCreame = whippedCreame;
    }

    public Boolean getCaramelDrizzled() {
        return CaramelDrizzled;
    }

    public void setCaramelDrizzled(Boolean caramelDrizzled) {
        CaramelDrizzled = caramelDrizzled;
    }

    public Boolean getChocolateDrizzled() {
        return ChocolateDrizzled;
    }

    public void setChocolateDrizzled(Boolean chocolateDrizzled) {
        ChocolateDrizzled = chocolateDrizzled;
    }
}