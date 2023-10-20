package com.budget.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemCart {
    private String itemCartId;

    List<Item> items;

    private LocalDate itemCartTime;

    public String getItemCartId() {
        return itemCartId;
    }

    public void setItemCartId(String itemCartId) {
        this.itemCartId = itemCartId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LocalDate getItemCartTime() {
        return itemCartTime;
    }

    public void setItemCartTime(LocalDate itemCartTime) {
        this.itemCartTime = itemCartTime;
    }

    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();

        }
        items.add(item);
    }

   

    @Override
    public String toString() {
        return "ItemCart itemCartId -> " + itemCartId + ", items -> " + items + ", itemCartTime -> " + itemCartTime;
    }

}
