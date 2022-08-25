package first;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminReg {
    private static final Scanner scanner = new Scanner(System.in);

    public static void adReg(Connection connection) throws SQLException {
        System.out.println("Welcome, make an account for admin!!");

        String sql = "insert into admin(adminName, adminPass) values (?,?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Enter username");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter password");
        preparedStatement.setString(2, scanner.nextLine());

        int rows = preparedStatement.executeUpdate();

        if (rows > 0 ) {
            System.out.println("Admin Account made successfully. ");
            System.out.println('\n');
            MyJDBC.menu();

        }else {
            System.out.println("Something went wrong. Try again later.");
            MyJDBC.menu();
        }

    }
}
