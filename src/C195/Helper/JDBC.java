package C195.Helper;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class JDBC {
    private static final String DBName = "client_schedule";
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DBName + "?connectionTimeZone = SERVER";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DBName + "?connectionTimeZone = UTC";

    //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    public static Connection connection;

    public static void openConnection(){
        try{
            Class.forName(driver);//locate driver
            connection = DriverManager.getConnection(DB_URL, username, password);//reference to connection object
            System.out.println("Connection successful");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static Connection getConnection(){
        return connection;
    }

    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Connection closed");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}

