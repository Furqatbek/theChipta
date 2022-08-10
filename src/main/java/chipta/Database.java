package chipta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/BotUsers";
        String username = "postgres";
        String password = "admin";
        try {
            Connection connection = (Connection) DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to database");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }

    }
}
