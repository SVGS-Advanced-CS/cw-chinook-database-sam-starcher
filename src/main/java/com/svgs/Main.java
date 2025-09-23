package com.svgs;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;



public class Main {
    private static Connection conn;
    public static void main(String[] args) {
        createDatabase();
        mainMenu();

    }

    public static void createDatabase(){
        String url = "jdbc:sqlite:./src/main/resources/users.db";
        try{
            conn = DriverManager.getConnection(url);
            Statement state = conn.createStatement();

            state.executeUpdate("CREATE TABLE IF NOT EXISTS users(username TEXT PRIMARY KEY,password TEXT NOT NULL,role TEXT) ");

            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void createUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("username: ");
        String username = input.nextLine();
        System.out.println("password: ");
        String password = input.nextLine();
        System.out.println("what is your role? ");
        String role = input.nextLine();

        String query = "INSERT into users(username,password,role)";
    }

    public static void mainMenu() {
        System.out.println("1. Login");
        System.out.println("2. Create User");
        System.out.println("3. Exit");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if(choice.equals("1")){

        }else if(choice.equals("2")){
            createUser();
        }else if(choice.equals("3")){
            System.exit(0);
        }else{
            System.out.println("Please enter a valid option");
        }
        mainMenu();
    }
}

/*state.executeUpdate("INSERT INTO employees(FirstName,LastName,employeeId) VALUES('Anthony','Tyler',9)");
            state.executeUpdate("DELETE FROM employees WHERE EmployeeId=11"); 
            String query = "SELECT * FROM employees WHERE EmployeeId == 4 ORDER BY LastName ASC";
            ResultSet results = state.executeQuery(query);
            while(results.next()){
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                System.out.println(firstName + " " + lastName);
                
            }*/