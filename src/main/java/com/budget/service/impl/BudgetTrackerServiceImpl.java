package com.budget.service.impl;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.budget.service.AuthenticationService;
import com.budget.service.BudgetTrackerService;
import com.budget.util.Config;

public class BudgetTrackerServiceImpl implements BudgetTrackerService {

    private AuthenticationService authenticationService;
    private Logger logger;
    Scanner scanner;

    public BudgetTrackerServiceImpl(Logger logger) {
        scanner = new Scanner(System.in);
        this.logger = logger;
        authenticationService = new AuthenticationServiceImpl();
    }

    @Override
    public void startApplication() {
        logger.info("In the budget tracker implementation");
        boolean isLoggedIn;
        try {
            isLoggedIn = authenticateUser();
            logger.info("User authenticated: " + isLoggedIn);

        } catch (SQLException | ClassNotFoundException e) {
            logger.severe(e.getMessage());
        }

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
