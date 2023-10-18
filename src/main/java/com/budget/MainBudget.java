package com.budget;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.budget.service.BudgetTrackerService;
import com.budget.service.impl.BudgetTrackerServiceImpl;
import com.budget.util.CustomFormatter;

public class MainBudget {

    private static final Logger LOGGER = Logger.getLogger(MainBudget.class.getName());

    static public void initializeLogger() {
        FileHandler handler;
        try {
            handler = new FileHandler("log.txt", true);
            CustomFormatter formatter = new CustomFormatter();
            LOGGER.addHandler(handler);
            handler.setFormatter(formatter);
            LOGGER.info("Starting application..");
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        ;
    }

    public static void main(String[] args) {
        initializeLogger();
        BudgetTrackerService budgetTrackerService = new BudgetTrackerServiceImpl(LOGGER);
        budgetTrackerService.startApplication();

    }

}