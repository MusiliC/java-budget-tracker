package com.budget.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseHandler {
    Connection connect(String connectionUrl, String username, String password) throws SQLException, ClassNotFoundException;
}
