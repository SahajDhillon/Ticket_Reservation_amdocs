package first;

import java.sql.*;

import java.util.Scanner;

public class Registration {
    private static final Scanner scanner = new Scanner(System.in);

    public static void signUp(Connection connection) throws SQLException {
        System.out.println("Welcome, make an account for free!!");

        String sql = "insert into users(username, password) values (?,?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Enter username");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter password");
        preparedStatement.setString(2, scanner.nextLine());

        int rows = preparedStatement.executeUpdate();

        if (rows > 0 ) {
            System.out.println("Account made successfully. Please login to start booking.");
            System.out.println('\n');
            MyJDBC.menu();

        }else {
            System.out.println("Something went wrong. Try again later.");
            MyJDBC.menu();
        }

    }
}
