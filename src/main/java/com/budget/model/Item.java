package com.budget.model;

public class Item {
    private int itemId;
    private String name;
    private int itemQuantity;
    private double price;
    private int userId;

    public Item(int itemId, String name, int itemQuantity, double price, int userId) {
        this.itemId = itemId;
        this.name = name;
        this.itemQuantity = itemQuantity;
        this.price = price;
        this.userId = userId;
    }

    public Item() {
    }

    public Item(String name,int itemQuantity,  double price, int userId) {
        this.name = name;
        this.itemQuantity = itemQuantity;
        this.price = price;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "Item itemId -> " + itemId + ", name -> " + name + ",  price -> " + price + ", userId -> " + userId;
    }

}
