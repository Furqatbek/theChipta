package chipta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public  void connectDb() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/BotUsers";
        String username = "postgres";
        String password = "admin";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "";
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }

    }
}
