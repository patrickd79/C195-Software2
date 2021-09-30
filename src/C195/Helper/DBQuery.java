package C195.Helper;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
        //create a statement reference
        private static Statement statement;

        public static void setStatement(Connection connection) throws SQLException {
            statement = connection.createStatement();
        }

        public static Statement getStatement(){
            return statement;
        }
}
