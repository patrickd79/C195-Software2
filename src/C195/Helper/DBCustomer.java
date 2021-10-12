package C195.Helper;

import java.sql.PreparedStatement;
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


}
