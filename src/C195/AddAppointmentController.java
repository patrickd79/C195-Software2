package C195;

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

public class AddAppointmentController {
    @FXML
    public TextField addAppointmentTitleField;
    @FXML
    public TextField addAppointmentDescField;
    @FXML
    public TextField addAppointmentLocationField;
    @FXML
    public TextField addAppointmentTypeField;
    @FXML
    public DatePicker addAppointmentStartDate;
    @FXML
    public DatePicker addAppointmentEndDate;
    @FXML
    public ComboBox<String> addAppointmentCustIDField;
    @FXML
    public ComboBox<String> addAppointmentUserIDField;
    @FXML
    public ComboBox<String> addAppointmentContactNameField;
    @FXML
    public Label addApptErrorField;
    @FXML
    public TextField addApptStartTimeField;
    @FXML
    public TextField addApptEndTimeField;
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    ObservableList<String> contactNames = FXCollections.observableArrayList();
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<String> customerNames = FXCollections.observableArrayList();
    ObservableList<User> users = FXCollections.observableArrayList();
    ObservableList<String> userNames = FXCollections.observableArrayList();

    public void addAppointment(ActionEvent event){
        User user = DBUser.getAUserByName(addAppointmentUserIDField.getValue());
        Contact contact = DBContacts.getAContactByName(addAppointmentContactNameField.getValue());
        Customer customer = DBCustomer.getACustomerByName(addAppointmentCustIDField.getValue());
        String startTime = addAppointmentStartDate.getValue().toString() + " " + addApptStartTimeField.getText();
        String endTime = addAppointmentEndDate.getValue().toString() + " " + addApptEndTimeField.getText();
        try{
            DBAppointment.addAppointment(addAppointmentTitleField.getText(), addAppointmentDescField.getText(), addAppointmentLocationField.getText(),
                    addAppointmentTypeField.getText(), startTime,  endTime,
                    user.getUserName(),
                    String.valueOf(customer.getCustomer_ID()), String.valueOf(user.getUserID()),
                     String.valueOf(contact.getContactID()));
            addApptErrorField.setTextFill(Color.BLACK);
            addApptErrorField.setText("Appointment Created");
            JDBC.closeConnection();
        }
        catch(Exception e){
            addApptErrorField.setTextFill(Color.RED);
            addApptErrorField.setText("Please complete all fields");
            e.printStackTrace();
        }
    }


    public void populateComboBoxContactName(){
        for(Contact c : contacts){
            contactNames.add(c.getContactName());
        }
        addAppointmentContactNameField.setItems(contactNames);
    }

    public void populateComboBoxCustomerNames(){
        for(Customer c : customers){
            customerNames.add(c.getCustomer_Name());
        }
        addAppointmentCustIDField.setItems(customerNames);
    }

    public void populateComboBoxUserNames(){
        for(User u : users){
            userNames.add(u.getUserName());
        }
        addAppointmentUserIDField.setItems(userNames);
    }

    public void goToMainMenuWindow(ActionEvent event) throws IOException {
        JDBC.closeConnection();
        Parent mainMenu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }

    public void initialize() {
        JDBC.openConnection();
        contacts = DBContacts.getAllContacts();
        customers = DBCustomer.getAllCustomers();
        users = DBUser.getAllUsers();
        populateComboBoxContactName();
        populateComboBoxCustomerNames();
        populateComboBoxUserNames();
    }
}
