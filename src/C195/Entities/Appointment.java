package C195.Entities;

import C195.Helper.DBContacts;
import C195.Helper.DBCustomer;
import C195.Helper.DBUser;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String createdDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int customerID;//fk
    private String customerName;
    private String customerIDAndName = "ID:"+customerID+ ", Name:"+customerName+"";
    private int userID;//fk
    private String userName;
    private String userIDAndName = "ID:"+userID+ ", Name:"+userName+"";
    private int contactID;//fk
    private String contactName;
    private String contactIDAndName = "ID:"+contactID+ ", Name:"+contactName+"";

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIDAndName() {
        return customerIDAndName;
    }

    public void setCustomerIDAndName(String customerIDAndName) {
        this.customerIDAndName = customerIDAndName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIDAndName() {
        return userIDAndName;
    }

    public void setUserIDAndName(String userIDAndName) {
        this.userIDAndName = userIDAndName;
    }

    public String getContactIDAndName() {
        return contactIDAndName;
    }

    public void setContactIDAndName(String contactIDAndName) {
        this.contactIDAndName = contactIDAndName;
    }



    public Appointment(){

    }


    public Appointment(int appointmentID, String title, String description, String location, String type, String start, String end, String createdDate, String createdBy, String lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        Customer customer = DBCustomer.getACustomerByID(customerID);
        this.customerName = customer.getCustomer_Name();
        User user = DBUser.getAUserByID(userID);
        this.userName = user.getUserName();
        Contact contact = DBContacts.getAContactByID(String.valueOf(contactID));
        this.contactName = contact.getContactName();

    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }


    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setCustomerName(int customerID){
        Customer customer = DBCustomer.getACustomerByID(customerID);
        this.customerName = customer.getCustomer_Name();
    }
    public String getCustomerName(){
        return customerName;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserName(int userID){
        User user = DBUser.getAUserByID(userID);
        this.userName = user.getUserName();
    }
    public String getUserName(){
       return userName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    public void setContactName(String contactID){
        Contact contact = DBContacts.getAContactByID(contactID);
        this.contactName = contact.getContactName();
    }
    public String getContactName(){
        return contactName;
    }
}
