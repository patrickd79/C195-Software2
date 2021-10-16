package C195;

import C195.Entities.Appointment;
import C195.Entities.Contact;
import C195.Entities.Customer;
import C195.Entities.User;
import C195.Helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UpdateAppointmentController {
    @FXML
    public Label apptIDLabel;
    @FXML
    public DatePicker updateAppointmentStartDate;
    @FXML
    public DatePicker updateAppointmentEndDate;
    @FXML
    public ComboBox<String> customerCombo;
    @FXML
    public ComboBox<String> userCombo;
    @FXML
    public TextField startTimeField;
    @FXML
    public TextField endTimeField;
    @FXML
    public ComboBox<String> contactCombo;
    @FXML
    public TextField titleField;
    @FXML
    public TextField descriptionField;
    @FXML
    public TextField locationField;
    @FXML
    public TextField typeField;
    @FXML
    public TextField createDateField;
    @FXML
    public TextField createdByField;
    @FXML
    public TextField lastUpdateDateField;
    @FXML
    public TextField lastUpdatedByField;
    @FXML
    public Label updateApptErrorField;
    private static String apptID;
    @FXML
    public TextField updatingNowField;
    Appointment appt;
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    ObservableList<String> contactNames = FXCollections.observableArrayList();
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<String> customerNames = FXCollections.observableArrayList();
    ObservableList<User> users = FXCollections.observableArrayList();
    ObservableList<String> userNames = FXCollections.observableArrayList();

    public void updateAppointment(ActionEvent event) throws IOException {
        String id = apptID;
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();
        String startDate = String.valueOf(updateAppointmentStartDate.getValue());
        String startTime = startTimeField.getText();
        String endDate = String.valueOf(updateAppointmentEndDate.getValue());
        String endTime = endTimeField.getText();
        String updatedBy = updatingNowField.getText();
        String customerName = customerCombo.getValue();
        Customer customer = DBCustomer.getACustomerByName(customerName);
        String customerID = String.valueOf(customer.getCustomer_ID());
        String userName = userCombo.getValue();
        User user = DBUser.getAUserByName(userName);
        String userID = String.valueOf(user.getUserID());
        String contactName = contactCombo.getValue();
        Contact contact = DBContacts.getAContactByName(contactName);
        String contactID = String.valueOf(contact.getContactID());
                //START HERE NEED TO FIX COMBO BOXES AND THE IDS.!!!!!!!!!!!!!!!!!!!!!!!!!!!
        try {
            //call DBCustomer update method
            DBAppointment.updateAppointment(id, title, description, location, type, startDate,
                    startTime,endDate, endTime,updatedBy,customerID,userID,contactID);
            updateApptErrorField.setTextFill(Color.BLACK);
            updateApptErrorField.setText("Appointment Record Updated");
        } catch (Exception exception) {
            updateApptErrorField.setTextFill(Color.RED);
            updateApptErrorField.setText("Please complete all fields");
            exception.printStackTrace();
        }
    }



    public void goToMainMenuWindow(ActionEvent event) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }
    public static LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDate.parse(dateString, formatter);
    }

    public static String getDateAndTimeNoSeconds(String dateString){
        String date;
        StringBuilder sb = new StringBuilder();
        char[] ca = dateString.toCharArray();
        if(dateString.length() >= 11) {
            for (int i = 0; i < 16; i++) {
                sb.append(ca[i]);
            }
            date = sb.toString();
        }else{
            for (int i = 0; i < 10; i++) {
                sb.append(ca[i]);
            }
            date = sb.toString();
        }
        return date;
    }

    public static String getDate(String dateString){
        char[] ca = dateString.toCharArray();
        StringBuilder sb = new StringBuilder();
        String date;
        for(int i = 0; i < 10; i++){
            sb.append(ca[i]);
        }
        date = sb.toString();
        return date;
    }
    public static String getTime(String dateString){
        char[] ca = dateString.toCharArray();
        StringBuilder sb = new StringBuilder();
        String time;
        for(int i = 11; i < 16; i++){
            sb.append(ca[i]);
        }
        time = sb.toString();
        return time;
    }



    public void populateAppointmentData(){

        String contactID = String.valueOf(appt.getContactID());
        Contact contact = DBContacts.getAContactByID(contactID);
        int customerID = appt.getCustomerID();
        Customer customer = DBCustomer.getACustomerByID(customerID);
        int userID = appt.getUserID();
        User user = DBUser.getAUserByID(userID);
        apptIDLabel.setText(String.valueOf(appt.getAppointmentID()));
        titleField.setText(appt.getTitle());
        descriptionField.setText(appt.getDescription());
        locationField.setText(appt.getLocation());
        typeField.setText(appt.getType());
        updateAppointmentStartDate.setValue(LOCAL_DATE(appt.getStart()));
        updateAppointmentEndDate.setValue(LOCAL_DATE(appt.getEnd()));
        startTimeField.setText(getTime(appt.getStart()));
        endTimeField.setText(getTime(appt.getEnd()));
        createDateField.setText(getDateAndTimeNoSeconds(appt.getCreatedDate()));
        createdByField.setText(appt.getCreatedBy());
        lastUpdateDateField.setText(getDateAndTimeNoSeconds(appt.getLastUpdate()));
        lastUpdatedByField.setText(appt.getLastUpdatedBy());
        populateComboBoxContactName();
        populateComboBoxCustomerNames();
        populateComboBoxUserNames();
        contactCombo.setValue(contact.getContactName());
        customerCombo.setValue(customer.getCustomer_Name());
        userCombo.setValue(user.getUserName());

    }

    public void populateComboBoxContactName(){
        for(Contact c : contacts){
            contactNames.add(c.getContactName());
        }
        contactCombo.setItems(contactNames);
    }

    public void populateComboBoxCustomerNames(){
        for(Customer c : customers){
            customerNames.add(c.getCustomer_Name());
        }
        customerCombo.setItems(customerNames);
    }

    public void populateComboBoxUserNames(){
        for(User u : users){
            userNames.add(u.getUserName());
        }
        userCombo.setItems(userNames);
    }

    public void initialize() {
        JDBC.openConnection();
        apptID = ChooseAppointmentToUpdateController.apptID;
        appt = DBAppointment.getAppointmentByID(apptID);
        contacts = DBContacts.getAllContacts();
        customers = DBCustomer.getAllCustomers();
        users = DBUser.getAllUsers();
        populateAppointmentData();

    }
}
