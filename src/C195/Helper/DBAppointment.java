package C195.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DBAppointment {
    public Connection connection;


    public static void deleteAppointments(int id){
        String sqlStmt = " DELETE FROM APPOINTMENTS WHERE Customer_ID="+id+";";
        try {
            //prepare the sql stmt
            PreparedStatement deleteAppointPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            deleteAppointPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void addAppointment(String title,String description,String location,String type,
                                      String start, String end, String createdBy, String customerId,
                                      String userId, String contactId){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String create_date = sqlDate.toString();
        String sqlStmt = "INSERT into APPOINTMENTS(Title, Description, Location, Type, Start, "+
                "End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, "+
                "User_ID, Contact_ID)Values('"+title+"', '"+description+"', '"+location+"', '"+type+"', '"+
                start+"', "+end+"', "+create_date+"', '"+createdBy+"', '"+create_date+"', '"+createdBy+"', '"+customerId+
                "'"+userId+"', "+contactId+"');";
        try {
            //prepare the sql stmt
            PreparedStatement appointPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            appointPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }







    /*public static void addCountry(String name, Date createdDate){
        String sql = "INSERT into Countries(Country, Create_Date) Values('"+name+"', '"+createdDate+"');";
        try {
            //prepare the sql stmt
            PreparedStatement countryPS = JDBC.getConnection().prepareStatement(sql);
            //execute the sql command
             countryPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }*/
}
