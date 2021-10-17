package C195.Helper;

import C195.Entities.Appointment;
import C195.Entities.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DBAppointment {
    public Connection connection;

    public static void deleteAppointment(String id){
        String sqlStmt = " DELETE FROM APPOINTMENTS WHERE Appointment_ID= '"+id+"';";
        try {
            //prepare the sql stmt
            PreparedStatement deleteAppointPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            deleteAppointPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public static void deleteAppointmentsForASingleCustomer(String id){
        String sqlStmt = " DELETE FROM APPOINTMENTS WHERE Customer_ID= '"+id+"';";
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
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
        String create_date = sqlDate.toString();
        String sqlStmt = "INSERT into APPOINTMENTS(Title, Description, Location, Type, Start, "+
                "End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, "+
                "User_ID, Contact_ID)Values('"+title+"', '"+description+"', '"+location+"', '"+type+"', '"+
                start+"', '"+end+"', '"+create_date+"', '"+createdBy+"', '"+create_date+"', '"+createdBy+"', '"+customerId+
                "', '"+userId+"', '"+contactId+"');";
        try {
            //prepare the sql stmt
            PreparedStatement appointPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            appointPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void updateAppointment(String id, String title, String description, String location,
                                         String type, String startDate,String startTime, String endDate,String endTime,String updatedBy, String customerID,
                                         String userID, String contactID){
        Date date = new Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
        String update_date = sqlDate.toString();
        System.out.println(update_date);
        String sqlStmt = "UPDATE APPOINTMENTS " +
                "SET" +
                " Title = '"+title+"'," +
                " Description = '"+description+"'," +
                " Location = '"+location+"'," +
                " Type = '"+type+"'," +
                " Start = '"+startDate+" "+startTime+"',"+
                " End = '"+endDate+" "+endTime+"',"+
                " Last_Update = '"+update_date+"'," +
                " Last_Updated_By = '"+updatedBy+"'," +
                " Customer_ID = "+customerID+"," +
                " User_ID = "+userID+"," +
                " Contact_ID = "+contactID+"" +
                " WHERE Appointment_ID = "+id+";";

        System.out.println(sqlStmt);
        try {
            //prepare the sql stmt
            PreparedStatement customerPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            customerPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        Appointment appt = null;
        try{
            String sqlStmt = "SELECT * FROM APPOINTMENTS;";
            PreparedStatement apptPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = apptPS.executeQuery();

            while(results.next()){
                int apptID = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String type = results.getString("Type");
                String start = results.getString("Start");
                String end = results.getString("End");
                String createDate = results.getString("Create_Date");
                String createdBy = results.getString("Created_By");
                String lastUpdate = results.getString("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                int custID = results.getInt("Customer_ID");
                int userID = results.getInt("User_ID");
                int contactID = results.getInt("Contact_ID");
                appt = new Appointment(apptID,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdatedBy,custID,userID,contactID);
                apptList.add(appt);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }

        return apptList;
    }

    public static Appointment getAppointmentByID(String id) {
        Appointment appt = null;
        try{
            String sqlStmt = "SELECT " +
                    "Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID" +
                    " FROM APPOINTMENTS" +
                    " WHERE Appointment_ID = "+id+";";
            PreparedStatement apptPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = apptPS.executeQuery();

            while(results.next()){
                int apptID = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String type = results.getString("Type");
                String start = results.getString("Start");
                String end = results.getString("End");
                String createDate = results.getString("Create_Date");
                String createdBy = results.getString("Created_By");
                String lastUpdate = results.getString("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                int custID = results.getInt("Customer_ID");
                int userID = results.getInt("User_ID");
                int contactID = results.getInt("Contact_ID");
                appt = new Appointment(apptID,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdatedBy,custID,userID,contactID);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }

        return appt;
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
