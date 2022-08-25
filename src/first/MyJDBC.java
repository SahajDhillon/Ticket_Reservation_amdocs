package first;

import java.sql.*;
import java.util.Scanner;

public class MyJDBC {
    private static Connection connection = null;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "password");
            System.out.println("Connected");
            menu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void menu() throws SQLException {

        System.out.println("###########      RAILWAY TICKET MAIN MENU      ##############");
        System.out.println("1: Sign up");
        System.out.println("2: Login");
        System.out.println("3: Admin Panel");
        System.out.println("4: Exit");
        int choice = Integer.parseInt(scanner.nextLine());
        if(choice ==1){
            Registration.signUp(connection);
        }
        else if(choice==2){
            Login.login(connection);
        }
        else if (choice == 3) {
            AdminLogin.adLogin(connection);
        }
        else if (choice == 5) {
            System.exit(0);
        }
    }
}
