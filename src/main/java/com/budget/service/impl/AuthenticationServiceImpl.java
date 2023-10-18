package com.budget.service.impl;

import com.budget.db.DatabaseHandler;
import com.budget.db.impl.DatabaseHandlerMySlImpl;
import com.budget.service.AuthenticationService;
import com.budget.util.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationServiceImpl implements AuthenticationService {
    DatabaseHandler databaseHandler;

    public AuthenticationServiceImpl() {
        databaseHandler = new DatabaseHandlerMySlImpl();
    }

    @Override
    public boolean logIn(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD);
        String selectUserQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectUserQuery);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return true;
        }
        return false;
    }

}
