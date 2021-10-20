package C195.Helper;

import C195.Entities.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
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

    public static ObservableList<Appointment> getAppointmentsForASingleCustomerByID(String id){
        ObservableList<Appointment> appts = FXCollections.observableArrayList();
        String sqlStmt = " SELECT * FROM APPOINTMENTS WHERE Customer_ID= '"+id+"';";
        try {
            //prepare the sql stmt
            PreparedStatement customerAppointPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            customerAppointPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return appts;
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
                TimeZones.convertToUTCTimeZone(start)+"', '"+TimeZones.convertToUTCTimeZone(end)+"', '"+TimeZones.getUTCTime()+"', '"+createdBy+"', '"+TimeZones.getUTCTime()+"', '"+createdBy+"', '"+customerId+
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


        String start = startDate+" "+startTime;
        String end = endDate+" "+endTime;

        String sqlStmt = "UPDATE APPOINTMENTS " +
                "SET" +
                " Title = '"+title+"'," +
                " Description = '"+description+"'," +
                " Location = '"+location+"'," +
                " Type = '"+type+"'," +
                " Start = '"+TimeZones.convertToUTCTimeZone(start)+"',"+
                " End = '"+TimeZones.convertToUTCTimeZone(end)+"',"+
                " Last_Update = '"+TimeZones.getUTCTime()+"'," +
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
                String localStart = TimeZones.convertToCurrentTimeZone(start);
                String localEnd = TimeZones.convertToCurrentTimeZone(end);
                String createDateLocal = TimeZones.convertToCurrentTimeZone(createDate);
                String updateDateLocal = TimeZones.convertToCurrentTimeZone(lastUpdate);
                appt = new Appointment(apptID,title,description,location,type,localStart,localEnd,createDateLocal,createdBy,updateDateLocal,lastUpdatedBy,custID,userID,contactID);
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
                String localStart = TimeZones.convertToCurrentTimeZone(start);
                String localEnd = TimeZones.convertToCurrentTimeZone(end);
                String createDateLocal = TimeZones.convertToCurrentTimeZone(createDate);
                String updateDateLocal = TimeZones.convertToCurrentTimeZone(lastUpdate);
                appt = new Appointment(apptID,title,description,location,type,localStart,localEnd,createDateLocal,createdBy,updateDateLocal,lastUpdatedBy,custID,userID,contactID);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return appt;
    }

    public static String extractMonth(String date){
        char[] ca = date.toCharArray();
        StringBuilder sb = new StringBuilder();
        String month;
        for(int i = 5; i < 7; i++){
            sb.append(ca[i]);
        }
        month = sb.toString();
        //System.out.println("Month ="+month+" : date = "+date);
        return month;
    }

    public static String extractYear(String date){
        char[] ca = date.toCharArray();
        StringBuilder sb = new StringBuilder();
        String year;
        for(int i = 0; i < 4; i++){
            sb.append(ca[i]);
        }
        year = sb.toString();
        //System.out.println("Year ="+year+" : date = "+date);
        return year;
    }

    public static String extractDay(String date){
        char[] ca = date.toCharArray();
        StringBuilder sb = new StringBuilder();
        String day;
        for(int i = 8; i < 10; i++){
            sb.append(ca[i]);
        }
        day = sb.toString();
        //System.out.println("Day ="+day+" : date = "+date);
        return day;
    }

    public static String extractWeek(String Date)  {
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(extractYear(Date));
        //have to subtract 1 from the month to account for the fact that calendar months are zero indexed
        int month = Integer.parseInt(extractMonth(Date))-1;
        int day = Integer.parseInt(extractDay(Date));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        //System.out.println("Date calendar set to:  "+calendar.getTime());
        return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
    }

    public static ObservableList<Appointment> getAppointmentsByMonth() {
        ObservableList<Appointment> appts = getAllAppointments();
        ObservableList<Appointment> apptsThatMatch = FXCollections.observableArrayList();
        LocalDate date = LocalDate.now();
        int monthNumber = date.getMonthValue();
        int yearNumber = date.getYear();
        String currentMonth = String.valueOf(monthNumber);
        String currentYear = String.valueOf(yearNumber);


        for(Appointment a: appts){
            String apptMonth = extractMonth(a.getStart());
            String apptYear = extractYear(a.getStart());
            if(apptMonth.equals(currentMonth) && apptYear.equals(currentYear)){
                apptsThatMatch.add(a);
            }
            //System.out.println(currentMonth +": "+ extractMonth(a.getStart()));
        }
        return apptsThatMatch;
    }

    public static ObservableList<Appointment> getAppointmentsByWeek() {
        ObservableList<Appointment> appts = getAllAppointments();
        ObservableList<Appointment> apptsThatMatch = FXCollections.observableArrayList();
        //get current week number
        Calendar now = Calendar.getInstance();
        String currentWeek = String.valueOf(now.get(Calendar.WEEK_OF_YEAR));
        LocalDate date = LocalDate.now();
        int yearNumber = date.getYear();
        String currentYear = String.valueOf(yearNumber);

        for(Appointment a: appts){
            String apptWeek = extractWeek(a.getStart());
            String apptYear = extractYear(a.getStart());

            if(apptWeek.equals(currentWeek) && apptYear.equals(currentYear)){
                apptsThatMatch.add(a);
            }
            //System.out.println(currentWeek +": "+ extractWeek(a.getStart()));

        }

        return apptsThatMatch;
    }

}
