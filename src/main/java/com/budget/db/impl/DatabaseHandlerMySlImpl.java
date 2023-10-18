package com.budget.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.budget.db.DatabaseHandler;

public class DatabaseHandlerMySlImpl implements DatabaseHandler {

    @Override
    public Connection connect(String connectionUrl, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionUrl, username, password);
    }

}
