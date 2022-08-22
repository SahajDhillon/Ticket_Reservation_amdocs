package first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {
    public static void main(String[] args) {

        chkPass();
    }
    static void print() {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "password");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static  void chkUsername(){
        try {
            Registration reg = new Registration();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "password");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users where username '" + reg.Uname + "'");

            while (resultSet.next()) {
                String chkUsername = resultSet.getString("username");
                System.out.println(chkUsername);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void chkPass(){
        try {

            Registration reg2 = new Registration();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_reservation", "root", "password");

            Statement statement = connection.createStatement();

            String query = "select * from users where username ='" + reg2.Pass+"'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String chkPass = resultSet.getString("password");
                System.out.println(chkPass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
