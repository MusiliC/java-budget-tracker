package com.budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemInterfaceImp implements ItemInterface {

    Scanner scanner = new Scanner(System.in);
    private Connection connection = DatabaseConnection.getConnection();
    List<Item> itemsArray = new ArrayList<>();

    @Override
    public void addItem() {

        String addItemQuery = "INSERT INTO item(itemName,itemQuantity, itemPrice, userId)VALUES(?,?,?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addItemQuery);
            Item newItem = getItem(scanner);

            preparedStatement.setString(1, newItem.getName());
            preparedStatement.setInt(2, newItem.getItemQuantity());
            preparedStatement.setDouble(3, newItem.getPrice());
            preparedStatement.setInt(4, newItem.getUserId());

            int noOfRowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected in user table i.e inserted " + noOfRowsAffected +
                    "\n");
            System.out.println();
            System.out.println("Item added successful\n");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error when adding item " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        System.out.println("Working");
    }

    @Override
    public List<Item> getItems() {
        try {

            ResultSet items = checkItemsInDB();
            // System.out.println("Item Name\t" + "Quantity\t" + "Unit Price\t" + "Total
            // Value ");
            System.out.println();

            // result set returns retrieved items from db
            while (items.next()) {
                int itemId = items.getInt("itemId");
                String itemName = items.getString("itemName");
                int itemQuantity = items.getInt("itemQuantity");
                double itemPrice = items.getDouble("itemPrice");
                int userId = items.getInt("userId");

                Item item = new Item(itemId, itemName, itemQuantity, itemPrice, userId);

                itemsArray.add(item);

            }
            return itemsArray;

        } catch (SQLException e) {
            System.out.println("Error when fetching items " + e.getMessage());
            e.printStackTrace();
        }
        return itemsArray;

    }

    private static Item getItem(Scanner scanner) {
        System.out.println();
        // reading user input
        System.out.print("Enter item name: ");
        String item_name = scanner.nextLine().trim();

        System.out.print("Enter item quantity: ");
        int item_quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter item price: ");
        double item_price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println();

        Item new_item = new Item(item_name, item_quantity, item_price, 1);

        return new_item;
    }

    private ResultSet checkItemsInDB() {
        String selectUserItems = "SELECT i.itemId, i.itemName,i.itemQuantity, i.itemPrice,  u.userId FROM item i JOIN user u ON i.userId = u.userId;";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(selectUserItems);
            ResultSet items = preparedStatement.executeQuery();
            return items;
        } catch (SQLException e) {
            System.out.println("Error When fetching items from database");
            e.printStackTrace();
        }
        return null;
    }

}
