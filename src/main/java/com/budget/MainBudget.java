package com.budget;


public class MainBudget {

 
    public static void main(String[] args) {
        
        UserInterfaceImp app = new UserInterfaceImp();
        ItemInterfaceImp itemApp = new ItemInterfaceImp();
        System.out.println(itemApp.getItems());
    }


}