package first;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminLogin {
    private static final Scanner scanner = new Scanner(System.in);
    public static void adLogin(Connection connection) throws SQLException {


        System.out.println("Welcome back, login to your account."+ '\n');
        String sql = "select * from admin where adminName = ?";

        //?(passing parameter) its value will be set by calling the setter methods of prepared statement.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Enter username");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter your password");
        String password = scanner.nextLine();

        ResultSet result = preparedStatement.executeQuery();

        if (result.next()) {
            String correctPassword = result.getString("adminPass");
            if ((correctPassword).equals(password)) {
                System.out.println("##########      ADMIN PANEL     ###########\n");
                System.out.println("You are logged in");
                System.out.println("1: Update User");
                System.out.println("2: Delete User");
                System.out.println("3: Add Admin");
                System.out.println("4: Return to main menu");
                int choice = Integer.parseInt(scanner.nextLine());
                if(choice ==1){
                    Update.upd(connection);
                }
                else if(choice==2){
                    Delete.del(connection);
                } else if (choice == 3) {
                    AdminReg.adReg(connection);
                } else if(choice==4){
                    MyJDBC.menu();
                }
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
}
