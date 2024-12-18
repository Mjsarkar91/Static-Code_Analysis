import java.sql.*;
import java.util.Scanner;

public class VulnerableLogin {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/ecommerce";
        String dbUser = "root";
        String dbPassword = "yourpassword";

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            System.out.println("Connected to the database.");

            // Prompt the user for credentials
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Vulnerable query
            String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

            System.out.println("Executing SQL query: " + sql);

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    System.out.println("Login successful! Welcome, " + resultSet.getString("username"));
                } else {
                    System.out.println("Invalid username or password.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
