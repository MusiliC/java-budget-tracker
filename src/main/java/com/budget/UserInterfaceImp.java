package com.budget;

import java.sql.Connection;

public class UserInterfaceImp implements UserInterface{

    private Connection connection = DatabaseConnection.getConnection();

    @Override
    public void addUser(User user) {
        System.out.println("Implementing soon");
    }

    @Override
    public User getUser() {        
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }
    
}
