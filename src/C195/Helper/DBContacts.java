package C195.Helper;

import C195.Entities.Contact;
import C195.Entities.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DBContacts {

    public static Contact getAContactByName(String name){
        Contact contact = null;
        try{
            String sqlStmt = "SELECT Contact_ID, Contact_Name, Email FROM CONTACTS WHERE Contact_Name = '"+name+"';";
            PreparedStatement contactPS = JDBC.getConnection().prepareStatement(sqlStmt);
            ResultSet results = contactPS.executeQuery();
            while(results.next()){
                int contact_id = results.getInt("Contact_ID");
                String contact_name = results.getString("Contact_Name");
                String email = results.getString("Email");
                contact = new Contact(contact_id,contact_name,email);
            }
        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return contact;
    }


    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        Contact contact = null;
            try{
                String sqlStmt = "SELECT * FROM CONTACTS;";
                PreparedStatement contactPS = JDBC.getConnection().prepareStatement(sqlStmt);
                ResultSet results = contactPS.executeQuery();
                while(results.next()){
                    int contact_id = results.getInt("Contact_ID");
                    String contact_name = results.getString("Contact_Name");
                    String email = results.getString("Email");
                    contact = new Contact(contact_id,contact_name,email);
                    contactList.add(contact);
                }
            }
            catch(SQLException throwable){
                throwable.printStackTrace();
            }
            return contactList;
    }
}
