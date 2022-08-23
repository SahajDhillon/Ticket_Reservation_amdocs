package first;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private static final Scanner scanner = new Scanner(System.in);
    public static void login(Connection connection) throws SQLException {


        System.out.println("Welcome back, login to your account."+ '\n');
        String sql = "select * from users where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("Enter username");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter your password");
        String password = scanner.nextLine();

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
            System.out.println("Sorry, username not found");
            System.out.println("Try Again");
            MyJDBC.menu();
        }
    }

    //Generate random String..
    static String getAlphaNumericString()
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    public static void profile(Connection connection) throws SQLException {
        System.out.println("Welcome, to your account select booking");

        String sql = "insert into tickets(source, destination, ticketNum) values (?,?,?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Enter source");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter destination");
        preparedStatement.setString(2, scanner.nextLine());
        //Random rand = new Random();

        String ticketNum =getAlphaNumericString();
        preparedStatement.setString(3, ticketNum);

        int rows = preparedStatement.executeUpdate();

        if (rows > 0 ) {
            System.out.println("Your Ticket is booked successfully , Your ticket is " + ticketNum);
            System.out.println('\n');


        }else {
            System.out.println("Something went wrong. Try again later.");
        }
    }
}