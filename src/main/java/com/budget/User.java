package com.budget;

public class User {
    
    private String userId;
    private String username;
    private String password;

    

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }    
  
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

      @Override
    public String toString() {
        return "username - " + username + " , " + " id - " + userId ;
    }

    
    
    
}
