package first;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private static Scanner scanner = new Scanner(System.in);
    public static void login(Connection connection) throws SQLException {


        System.out.println("Welcome back, login to your account."+ '\n');
        String sql = "select * from users where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("Enter username");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter your password");
        String password = scanner.nextLine();

//		String sql = "select password from user where email = "+'\u0022' + email + '\u0022';

        //String sql = "insert into users(username, password) values (?,?) ";

        ResultSet result = preparedStatement.executeQuery();

        if (result.next()) {
            String correctPassword = result.getString("password");
            String username = result.getString("username");
            if ((correctPassword).equals(password)) {
                System.out.println("_____________\n");
                System.out.println("You are logged in");
                profile(connection);
            } else {
                System.out.println("Invalid password");
                System.out.println("Try again");
                MyJDBC.menu();

            }

        } else {
            System.out.println("Sorry, email not found");
            System.out.println("Try Again");
            MyJDBC.menu();
        }
    }
    public static void profile(Connection connection) throws SQLException {
        System.out.println("Welcome, to your account select booking");

        String sql = "insert into tickets(source, destination, ticketNum) values (?,?,?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Enter source");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter destination");
        preparedStatement.setString(2, scanner.nextLine());

        String ticketNum ="1234";
        preparedStatement.setString(2, ticketNum);

        int rows = preparedStatement.executeUpdate();

        if (rows > 0 ) {
            System.out.println("Your Ticket is booked successfully , Your ticket number is " + ticketNum);
            System.out.println('\n');


        }else {
            System.out.println("Something went wrong. Try again later.");
        }
    }
}