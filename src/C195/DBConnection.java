package C195;

import java.sql.Connection;

public class DBConnection {
    private static final String DBName = "client_schedule";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DBName;
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;
}
