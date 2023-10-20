package com.budget.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.budget.model.Item;
import com.budget.model.ItemCart;
import com.budget.service.AuthenticationService;
import com.budget.service.BudgetTrackerService;
import com.budget.util.Config;
import com.budget.util.InputSanitizer;

public class BudgetTrackerServiceImpl implements BudgetTrackerService {

    private AuthenticationService authenticationService;
    private Logger logger;
    Scanner scanner;
    private ItemCart itemCart;

    public BudgetTrackerServiceImpl(Logger logger) {
        scanner = new Scanner(System.in);
        this.logger = logger;
        authenticationService = new AuthenticationServiceImpl();
        itemCart = new ItemCart();
    }

    @Override
    public void startApplication() {
        logger.info("In the budget tracker implementation");
        boolean isLoggedIn;
        try {
            isLoggedIn = authenticateUser();
            logger.info("User authenticated: " + isLoggedIn);
            // ?show menu
            boolean keepShowingMenu = true;
            while (keepShowingMenu) {
                showMenu();
                System.out.print("Choose an option: ");
                String input = scanner.nextLine();
                int option = InputSanitizer.sanitizeStringToInt(input, logger);
                // ?handle the option

                switch (option) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        totalCalculation();
                        break;
                    case 3:
                        if (itemCart.getItems().isEmpty()) {
                            System.out.println("No items to display");
                        }
                        displayBudgetList();
                        break;
                    case 4:
                        // Exit the program
                        System.out.println("Exiting the program.");
                        keepShowingMenu = false;
                        break;

                    default:
                        System.out.println("Invalid Option, Choose between 1-4");
                        System.out.println();
                        showMenu();
                        break;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            logger.severe(e.getMessage());
        }

    }

    private void displayBudgetList() {
        System.out.println();
        System.out.println("Item Name\t" + "Quantity\t" + "Unit Price\t" + "Total Value ");
        System.out.println();
        itemCart.getItems()
                .forEach(item -> {
                    double subTotal = item.getItemQuantity() * item.getPrice();
                    System.out.println(item.getName() + " \t\t" + item.getItemQuantity() + " \t\t" + item.getPrice() +
                            " \t\t"
                            + subTotal);
                });
    }

    private void totalCalculation() {
        // calculate items cart total
        List<Item> items = itemCart.getItems();
        double totalCost = 0.0;
        for (Item item : items) {
            totalCost = totalCost + item.getItemQuantity() * item.getPrice();
        }
      
        displayBudgetList();
        System.out.println();
        System.out.println("****************************************************");
        System.out.println();
        System.out.println("Total items price: " + "\t" + " - " + "\t" + "sh: " +
                totalCost + "\n");
    }

    private void addItem() {
        boolean keepAddingItems = true;

        while (keepAddingItems) {
            // prompt items details
            System.out.println();
            // reading user input
            System.out.print("Enter item name: ");
            String item_name = scanner.nextLine().trim();

            System.out.print("Enter item quantity: ");
            int item_quantity = InputSanitizer.sanitizeStringToInt(scanner.nextLine(), logger);

            System.out.print("Enter item price: ");

            double item_price = InputSanitizer.sanitizeStringToDouble(scanner.nextLine(), logger);

            Item new_item = new Item(item_name, item_quantity, item_price, 1);

            itemCart.addItem(new_item);

            System.out.println();
            // prompt do you want to add another items
            System.out.println();
            System.out.print("Do you want to enter another item? (Y/N): ");
            String addOthers = scanner.nextLine();
            if (!addOthers.equalsIgnoreCase("Y")) {
                keepAddingItems = false;
            }
        }

    }

    private void showMenu() {
        System.out.println();
        System.out.println("*****************************************");
        System.out.println();
        System.out.println("BUDGET TRACKING SYSTEM");
        System.out.println();
        System.out.println("*****************************************");
        System.out.println();
        System.out.println("1. ADD ITEM");
        System.out.println("2. DO TOTAL CALCULATIONS");
        System.out.println("3. DISPLAY BUDGET LIST");
        System.out.println("4: EXIT");
        System.out.println();
        System.out.println("*********************");
        System.out.println();
    }

    private boolean authenticateUser() throws SQLException, ClassNotFoundException {
        logger.info("Authenticate the user");
        int loginEntries = 1;

        System.out.println();
        System.out.println("Welcome to Budget Tracker System");
        System.out.println();

        while (loginEntries <= Config.LOGIN_LIMIT) {

            if (userLogIn())
                return true;
            loginEntries++;
            System.out.println("Incorrect credentials");
            System.out.println();
        }

        return false;
    }

    private boolean userLogIn() throws SQLException, ClassNotFoundException {
        System.out.println();
        System.out.println("Log in to your account");
        System.out.println("***************************");
        System.out.println();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        if (authenticationService.logIn(username, password)) {
            return true;
        }
        return false;
    }

}
