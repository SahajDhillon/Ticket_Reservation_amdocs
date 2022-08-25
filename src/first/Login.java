package first;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Login {
    private static final Scanner scanner = new Scanner(System.in);
    public static void login(Connection connection) throws SQLException {


        System.out.println("Welcome back, login to your account."+ '\n');
        String sql = "select * from users where username = ?";

        //?(passing parameter) its value will be set by calling the setter methods of prepared statement.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Enter username");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter your password");
        String password = scanner.nextLine();

        ResultSet result = preparedStatement.executeQuery();

        if (result.next()) {
            String correctPassword = result.getString("password");
            if ((correctPassword).equals(password)) {

                //After logging in..

                System.out.println("_____________\n");
                System.out.println("You are logged in");
                System.out.println("1. Ticket Reservation");
                System.out.println("2. View Ticket");
                System.out.println("3. Back to main menu");
                Scanner obj = new Scanner(System.in);
                int j = obj.nextInt();
                if(j==1){
                    profile(connection);
                }
                else if (j==2) {
                    System.out.println("Ticket:\n\n");
                    showTicket(connection);
                }else if (j==3) {
                    MyJDBC.menu();
                }
                else{
                    System.out.println("invalid input returning to main menu..");
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

    // Ticket Booking System..
    public static void profile(Connection connection) throws SQLException {
        System.out.println("##########   TICKET BOOKER   ##########");

        String sql = "insert into tickets(source, destination, passenger1, passenger2, passenger3, ticketNum) values (?,?,?,?,?,?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Enter source");
        preparedStatement.setString(1, scanner.nextLine());

        System.out.println("Enter destination");
        preparedStatement.setString(2, scanner.nextLine());

        System.out.println("Enter passenger name:");
        preparedStatement.setString(3, scanner.nextLine());


        System.out.println("do you want to add another passenger y/n ?");
        Scanner myObj = new Scanner(System.in);
        String ans = myObj.nextLine();
        if(Objects.equals(ans, "y")){
            System.out.println("Enter passenger name:");
            preparedStatement.setString(4, scanner.nextLine());
        }else{
            preparedStatement.setNull(4, Types.NULL);
        }

        System.out.println("do you want to add another passenger y/n ?");
        Scanner myObj2 = new Scanner(System.in);
        String ans2 = myObj2.nextLine();
        if(Objects.equals(ans2, "y")){
            System.out.println("Enter passenger name:");
            preparedStatement.setString(5, scanner.nextLine());
        }else{
            preparedStatement.setNull(5, Types.NULL);
        }


        String ticketNum =getAlphaNumericString();
        preparedStatement.setString(6, ticketNum);

        int rows = preparedStatement.executeUpdate();

        if (rows > 0 ) {
            System.out.println("Your Ticket is booked successfully , Your ticket is " + ticketNum);
            System.out.println('\n');


        }else {
            System.out.println("Something went wrong. Try again later.");
        }
    }

    // Ticking Viewing System
    public static void showTicket(Connection connection) throws SQLException{
        System.out.println("Welcome back, login to your account."+ '\n');
        String sql = "select ticketNum from tickets where passenger1 = ?";

        //?(passing parameter) its value will be set by calling the setter methods of prepared statement.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Enter passenger");
        preparedStatement.setString(1, scanner.nextLine());

        ResultSet result = preparedStatement.executeQuery();

        if (result.next()) {
            String ticket = result.getString("ticketNum");

                System.out.println("_____________\n");
                System.out.println("Your PNR number is:"+ticket);
        } else {
            System.out.println("Sorry, username not found");
            System.out.println("Try Again");
            MyJDBC.menu();
        }
    }
}