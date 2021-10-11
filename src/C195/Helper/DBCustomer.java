package C195.Helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DBCustomer {

    public static void addCustomer(String name, String address, String postalCode, String phone, String createdBy, String divID){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        //fix this to produce utc time
        String create_date = sqlDate.toString();
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
