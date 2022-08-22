package first;

import java.sql.*;
import java.util.Scanner;

public class MyJDBC {
    private static Connection connection = null;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "password");
            System.out.println("Connected");
//            Registration.signUp(connection);
            menu();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        Login reg = new Login();
//        chkPass();
    }
    public static void menu() throws SQLException {

        System.out.println("1: Sign up");
        System.out.println("2: Login");
        int choice = Integer.parseInt(scanner.nextLine());
        if(choice ==1){
            Registration.signUp(connection);
        }else if(choice==2){
            Login.login(connection);
        }
    }
    static void print() {

//        try {
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "password");
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("select * from users");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("password"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    static void chkUsername(){
//        try {
//
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "password");
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("select * from users where username '" + reg.Uname + "'");
//
//            while (resultSet.next()) {
//                String chkUsername = resultSet.getString("username");
//                System.out.println(chkUsername);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    static void chkPass(){
//        try {
//
//            Registration reg2 = new Registration();
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "password");
//
//            Statement statement = connection.createStatement();
//
//            String query = "select * from users where username ='" + reg2.Pass+"'";
//
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                String chkPass = resultSet.getString("password");
//                System.out.println(chkPass);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
