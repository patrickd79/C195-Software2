package C195.Helper;

import C195.Entities.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DBCustomer {

    public static void deleteCustomer(int id){
        //write a deleteAppointment method in DBAppointment

    }

    public static void addCustomer(String name, String address, String postalCode, String phone, String createdBy, String divID){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String create_date = sqlDate.toString();
        System.out.println(create_date);
        System.out.println("name:"+name+" address:"+address+" postal:"+postalCode+" "+"phone:"+phone+" divID:"+divID+"");
        String sqlStmt = "Insert into Customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Division_ID)" +
                "Values('"+name+"', '"+address+"', '"+postalCode+"', '"+phone+"', '"+create_date+
                "', '"+createdBy+"', '"+create_date+"', '"+createdBy+"', '"+divID+"');";
        try {
            //prepare the sql stmt
            PreparedStatement customerPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            customerPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static Customer getACustomer(int ID){
        ObservableList<Customer> customer = FXCollections.observableArrayList();
        Customer cust = null;
        try{
            String sqlStmt = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID " +
                    "FROM Customers WHERE Customer_ID = "+ID+";";
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
                cust = new Customer(customerID,Customer_Name,address,postalCode,phone,createDate,createdBy,lastUpdate,lastUpdatedBy,divID);
                customer.add(cust);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return cust;
    }


}
