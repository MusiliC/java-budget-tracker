package com.budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterfaceImp implements UserInterface {

    static Scanner scanner = new Scanner(System.in);
    private Connection connection = DatabaseConnection.getConnection();
    private boolean isUserSignedIn;

    @Override
    public void createUser() {
        String insertUserQuery = "INSERT INTO user(username, password)VALUES(?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery);
            User newUser = getUserCredentials(scanner);

            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getPassword());

            int noOfRowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected in user table i.e inserted " + noOfRowsAffected +
                    "\n");
            System.out.println();
            System.out.println("User created successfully, proceed to login\n");
            System.out.println();
            logInUser();

        } catch (SQLException e) {
            System.out.println("Error when creating user " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    @Override
    public boolean logInUser() {
        System.out.println();
        System.out.println("Log in to your account");
        System.out.println("***************************");
        System.out.println();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        String selectUserQuery = "SELECT * FROM user WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectUserQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("User " + username + " logged in successfully");
                isUserSignedIn = true;
                return isUserSignedIn;
            } else {
                System.out.println("Incorrect credentials");
                isUserSignedIn = false;
                return isUserSignedIn;
            }
        } catch (SQLException e) {
            System.out.println("Error when logging user " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        return isUserSignedIn;

    }

    private static User getUserCredentials(Scanner scanner) {
        System.out.println();
        System.out.println("Create New Account");
        System.out.println("***************************");
        System.out.println();
        // reading user input
        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        User user = new User(username, password);

        return user;
    }

    public void userHasAccount(){
        System.out.println();
        System.out.println("Welcome to Budget Tracker System");
        System.out.println();

        System.out.print("Hello, do you have an account? (Y/N): ");
        char userHasAccount = scanner.nextLine().toUpperCase().trim().charAt(0);
        System.out.println();

        UserInterfaceImp app = new UserInterfaceImp();

          if (userHasAccount == 'Y') {
        
           app.logInUser();

        } else if (userHasAccount == 'N') {           
           app.createUser();

        } else {
            System.out.println("Invalid input, Choose either Y or N");
            System.out.println();
            userHasAccount();
        }

    }
}
