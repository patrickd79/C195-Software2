package C195.Helper;

import C195.Entities.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DBCustomer {



    public static void updateCustomer(String id, String name, String address, String postalCode, String phone, String updatedBy, String divID){

        //this creates a local timestamp
        Date date = new Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
        String update_date = sqlDate.toString();
        System.out.println(update_date);

        String sqlStmt = "UPDATE Customers " +
                "SET" +
                " Customer_Name = '"+name+"'," +
                " Address = '"+address+"'," +
                " Postal_Code = '"+postalCode+"'," +
                " Phone = '"+phone+"'," +
                " Last_Update = '"+TimeZones.getUTCTime()+"'," +
                " Last_Updated_By = '"+updatedBy+"'," +
                " Division_ID = '"+divID+"'" +
                "WHERE Customer_ID = "+id+";";

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

    public static void deleteCustomer(String id){
        String sqlStmt = "DELETE FROM Customers WHERE Customer_ID = "+id+";";

        try {
            //prepare the sql stmt
            PreparedStatement customerPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            customerPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void addCustomer(String name, String address, String postalCode, String phone, String createdBy, String divID){

        //this creates a local timestamp
        Date date = new Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
        String create_date = sqlDate.toString();

        String sqlStmt = "Insert into Customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Division_ID)" +
                "Values('"+name+"', '"+address+"', '"+postalCode+"', '"+phone+"', '"+TimeZones.getUTCTime()+
                "', '"+createdBy+"', '"+TimeZones.getUTCTime()+"', '"+createdBy+"', '"+divID+"');";
        try {
            //prepare the sql stmt
            PreparedStatement customerPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            customerPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static Customer getACustomerByName(String name){
        ObservableList<Customer> customer = FXCollections.observableArrayList();
        Customer cust = null;

        try{
            String sqlStmt = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID " +
                    "FROM Customers WHERE Customer_Name = '"+name+"';";
            PreparedStatement customerPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = customerPS.executeQuery();
            while(results.next()){
                int customerID = results.getInt("Customer_ID");
                String Customer_Name = results.getString("Customer_Name");
                String address = results.getString("Address");
                String postalCode = results.getString("Postal_Code");
                String phone = results.getString("Phone");
                String createDate = results.getString("Create_Date");
                String createdBy = results.getString("Created_By");
                String lastUpdate = results.getString("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                int divID = results.getInt("Division_ID");
                String createDateLocal = TimeZones.convertToCurrentTimeZone(createDate);
                String updateDateLocal = TimeZones.convertToCurrentTimeZone(lastUpdate);

                cust = new Customer(customerID,Customer_Name,address,postalCode,phone,createDateLocal,createdBy,updateDateLocal,lastUpdatedBy,divID);
                customer.add(cust);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return cust;
    }

    public static Customer getACustomerByID(int id){
        ObservableList<Customer> customer = FXCollections.observableArrayList();
        Customer cust = null;
        try{
            String sqlStmt = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID " +
                    "FROM Customers WHERE Customer_ID = "+id+";";
            PreparedStatement customerPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = customerPS.executeQuery();
            while(results.next()){
                int customerID = results.getInt("Customer_ID");
                String Customer_Name = results.getString("Customer_Name");
                String address = results.getString("Address");
                String postalCode = results.getString("Postal_Code");
                String phone = results.getString("Phone");
                String createDate = results.getString("Create_Date");
                String createdBy = results.getString("Created_By");
                String lastUpdate = results.getString("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                int divID = results.getInt("Division_ID");
                String createDateLocal = TimeZones.convertToCurrentTimeZone(createDate);
                String updateDateLocal = TimeZones.convertToCurrentTimeZone(lastUpdate);

                cust = new Customer(customerID,Customer_Name,address,postalCode,phone,createDateLocal,createdBy,updateDateLocal,lastUpdatedBy,divID);
                customer.add(cust);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return cust;
    }

    public static ObservableList<Customer> getAllCustomers(){
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        Customer cust = null;
        try{
            String sqlStmt = "SELECT * FROM Customers;";
            PreparedStatement customerPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = customerPS.executeQuery();

            while(results.next()){
                int customerID = results.getInt("Customer_ID");
                String Customer_Name = results.getString("Customer_Name");
                String address = results.getString("Address");
                String postalCode = results.getString("Postal_Code");
                String phone = results.getString("Phone");
                String createDate = results.getString("Create_Date");
                String createdBy = results.getString("Created_By");
                String lastUpdate = results.getString("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                int divID = results.getInt("Division_ID");
                String createDateLocal = TimeZones.convertToCurrentTimeZone(createDate);
                String updateDateLocal = TimeZones.convertToCurrentTimeZone(lastUpdate);

                cust = new Customer(customerID,Customer_Name,address,postalCode,phone,createDateLocal,createdBy,updateDateLocal,lastUpdatedBy,divID);
                customerList.add(cust);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return customerList;
    }




}
