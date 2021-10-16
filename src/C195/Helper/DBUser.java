package C195.Helper;

import C195.Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    public static User getAUserByName(String name){
        User user = null;
        try{
            String sqlStmt = "SELECT User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By " +
                    "FROM USERS WHERE User_Name = '"+name+"';";
            PreparedStatement userPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = userPS.executeQuery();
            while(results.next()){
                int userID = results.getInt("User_ID");
                String userName = results.getString("User_Name");
                String password = results.getString("Password");
                String createDate = results.getString("Create_Date");
                String createBy = results.getString("Created_By");
                String lastUpdate = results.getString("Last_Update");
                String lastUpdateBy = results.getString("Last_Updated_By");
                user = new User(userID,userName,password,createDate,createBy,lastUpdate,lastUpdateBy);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return user;
    }

    public static User getAUserByID(int id){
        User user = null;
        try{
            String sqlStmt = "SELECT User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By " +
                    "FROM USERS WHERE User_ID = "+id+";";
            PreparedStatement userPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = userPS.executeQuery();
            while(results.next()){
                int userID = results.getInt("User_ID");
                String userName = results.getString("User_Name");
                String password = results.getString("Password");
                String createDate = results.getString("Create_Date");
                String createBy = results.getString("Created_By");
                String lastUpdate = results.getString("Last_Update");
                String lastUpdateBy = results.getString("Last_Updated_By");
                user = new User(userID,userName,password,createDate,createBy,lastUpdate,lastUpdateBy);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return user;
    }

    public static ObservableList<User> getAllUsers(){
        ObservableList<User> userList = FXCollections.observableArrayList();
        User user = null;
        try{
            String sqlStmt = "SELECT * FROM USERS;";
            PreparedStatement userPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = userPS.executeQuery();
            while(results.next()){
                int userID = results.getInt("User_ID");
                String userName = results.getString("User_Name");
                String password = results.getString("Password");
                String createDate = results.getString("Create_Date");
                String createBy = results.getString("Created_By");
                String lastUpdate = results.getString("Last_Update");
                String lastUpdateBy = results.getString("Last_Updated_By");
                user = new User(userID,userName,password,createDate,createBy,lastUpdate,lastUpdateBy);
                userList.add(user);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return userList;
    }
}
