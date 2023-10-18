package com.budget.service;

import java.sql.SQLException;

public interface AuthenticationService {
    boolean logIn(String username, String password) throws SQLException, ClassNotFoundException;

}
