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
import javafx.stage.Stage;

import java.io.IOException;

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
    private String apptID;
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    ObservableList<String> contactNames = FXCollections.observableArrayList();
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<String> customerNames = FXCollections.observableArrayList();
    ObservableList<User> users = FXCollections.observableArrayList();
    ObservableList<String> userNames = FXCollections.observableArrayList();


    public void goToMainMenuWindow(ActionEvent event) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }

    public void populateAppointmentData(String id){

        Appointment appt = DBAppointment.getAppointmentByID(id);
        apptIDLabel.setText(String.valueOf(appt.getAppointmentID()));
        titleField.setText(customer.getCustomer_Name());
        descriptionField.setText(customer.getAddress());
        locationField.setText(customer.getPostalCode());
        typeField.setText(customer.getPhone());
        updateAppointmentStartDate.setValue();
        updateAppointmentEndDate.setValue();
        startTimeField.setText();
        endTimeField.setText();
        createDateField.setText(customer.getCreatedBy());
        createdByField.setText(customer.getCreatedDate());
        lastUpdateDateField.setText(customer.getLastUpdatedBy());
        lastUpdatedByField.setText(customer.getLastUpdate());
        populateComboBoxContactName();
        populateComboBoxCustomerNames();
        populateComboBoxUserNames();
        contactCombo.setValue(DBContacts.getAContactByID(String.valueOf(ChooseAppointmentToUpdateController.thisAppt.getAppointmentID())));

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
        populateAppointmentData(apptID);

    }
}
