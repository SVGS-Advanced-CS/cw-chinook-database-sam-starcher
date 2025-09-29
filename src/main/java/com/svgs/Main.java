package com.svgs;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            //conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void loginUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("Username?");
        String username = input.nextLine();
        System.out.println("Password?");
        String password = input.nextLine();
        
        String query = "SELECT role FROM users WHERE username='" + username + "' AND password='" + password + "'";
        try{
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery(query);
            if(!result.next()){
                System.out.println("incorrect username or password.");
                mainMenu();
                return;
            }
            System.out.println("you have logged in. your role is " + result.getString("role"));
            if(result.getString("role").equals("admin")){
                adminMenu();
            }else{
                userMenu();
            }

        }catch(SQLException e){

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

        String query = "INSERT into users(username,password,role) VALUES('" + username + "','" + password + "','" + role + "')";
        try{
            Statement state = conn.createStatement();
            state.executeUpdate(query);
        }catch(SQLException e){
            if(e.getErrorCode()==19){
                System.out.println("That user already exists.");
                createUser();
            }
        }
    }

    public static void mainMenu() {
        System.out.println("1. Login");
        System.out.println("2. Create User");
        System.out.println("3. Exit");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if(choice.equals("1")){
            loginUser();
        }else if(choice.equals("2")){
            createUser();
        }else if(choice.equals("3")){
            try{
                conn.close();
            }catch(Exception e){
                System.out.println(e);
            }
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