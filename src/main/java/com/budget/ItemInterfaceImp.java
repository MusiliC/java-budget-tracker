package com.budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemInterfaceImp implements ItemInterface {

    Scanner scanner = new Scanner(System.in);
    private Connection connection = DatabaseConnection.getConnection();

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItems'");
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

        Item new_item = new Item(item_name,  item_quantity,item_price, 1);

        return new_item;
    }

}
