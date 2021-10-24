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
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;

public class ReportsController {
    @FXML
    public ComboBox<String> monthsCustomerHasApptsCombo;
    @FXML
    public TextField totalApptsforCustomerMonthField;
    @FXML
    public ComboBox<String> typesCombo;
    @FXML
    public TextField customerToQueryField;
    @FXML
    public TextField totalApptByType;
    @FXML
    public Button userApptReportBtn;
    @FXML
    public Button customerMonthReportBtn;
    @FXML
    public Button customerTypeReportBtn;
    @FXML
    public Label reportErrorMsgField;
    @FXML
    public Button getCustomerBtn;
    @FXML
    public ComboBox<String> customersCombo;
    @FXML
    public ComboBox<String> userCombo;
    @FXML
    public TextField userTotalApptField;
    public String customerID;
    public String userID;
    User user;
    String userApptCountResult;
    Customer customer;

    public static ObservableList<Appointment> appts = FXCollections.observableArrayList();
    ObservableList<String> apptMonths = FXCollections.observableArrayList();
    public static ObservableList<String> apptTypes = FXCollections.observableArrayList();
    public static ObservableList<Customer> customers = FXCollections.observableArrayList();
    public static ObservableList<String> customerIDs = FXCollections.observableArrayList();
    public static ObservableList<User> users = FXCollections.observableArrayList();
    public static ObservableList<String> userIDs = FXCollections.observableArrayList();

    public void initialize() {
        JDBC.openConnection();
        customers = DBCustomer.getAllCustomers();
        users = DBUser.getAllUsers();
        populateCustomerCombo();
        populateUserCombo();
    }

    public void goToMainMenuWindow(ActionEvent event) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }

    public void getUserApptCount(ActionEvent event){
        userID = userCombo.getValue();
        userApptCountResult = String.valueOf(DBAppointment.getApptCountForAUser(userID));
        userTotalApptField.setText(userApptCountResult);

    }

    public void getCustomerAppts(ActionEvent event) {
        try {
            customerID = customersCombo.getValue();
            customer = DBCustomer.getACustomerByID(Integer.parseInt(customerID));
            String name = customer.getCustomer_Name();
            appts = DBAppointment.getAppointmentsForASingleCustomerByID(customerID);
            //System.out.println("Customer Name : "+name);
            populateComboBoxCustomerApptMonths();
            populateComboBoxCustomerApptTypes();
            //System.out.println("Methods ran");
            reportErrorMsgField.setTextFill(Color.BLACK);
            reportErrorMsgField.setText("Retrieving appointments for Customer ID: " + customerID + ", Name: " + name);
        }
        catch (Exception ex){
            ex.printStackTrace();
            reportErrorMsgField.setTextFill(Color.RED);
            reportErrorMsgField.setText("Please choose a customer to query.");
        }
    }

    public void getCustomerReportsByMonth(ActionEvent event){
        try {
            int count = 0;
            for (Appointment a : appts) {

                String month = DBAppointment.extractMonth(a.getStart());
                String requestedMonth = monthsCustomerHasApptsCombo.getValue();
                if(month.equals(requestedMonth)){
                    count++;
                }
            }
            totalApptsforCustomerMonthField.setText(String.valueOf(count));
        }
        catch (Exception ex){
            ex.printStackTrace();
            reportErrorMsgField.setText("Please choose a month.");
        }
    }

    public void getCustomerReportsByType(ActionEvent event){
        try {
            int count = 0;
            for (Appointment a : appts) {
                String type = a.getType();
                String requestedType = typesCombo.getValue();
                if(type.equals(requestedType)){
                    count++;
                }
            }
            totalApptByType.setText(String.valueOf(count));
        }
        catch (Exception ex){
            ex.printStackTrace();
            reportErrorMsgField.setText("Please choose a type.");
        }
    }

    public void populateCustomerCombo(){
        String id;
        for(Customer c : customers){
            id = String.valueOf(c.getCustomer_ID());
            customerIDs.add(id);
        }
        customersCombo.setItems(customerIDs);
    }

    public void populateUserCombo(){
        String id;
        for(User u : users){
            id = String.valueOf(u.getUserID());
            userIDs.add(id);
        }
        userCombo.setItems(userIDs);
    }

    public void populateComboBoxCustomerApptMonths(){
        System.out.println("Method Start");
        String month;
        HashSet<String> months = new HashSet<>();
        System.out.println("Start for each");
        System.out.println("APPTS SIZE: "+ appts.size());
        for(Appointment a : appts){
            //System.out.println("Appointment ID: "+a.getAppointmentID());
            month = DBAppointment.extractMonth(a.getStart());
            if(!months.contains(month)){
                months.add(month);
                //System.out.println("MONTH: "+month);
            }
        }
        apptMonths.addAll(months);
        /*for(String am : apptMonths){
            System.out.println("APPT MNTH:"+ am );
        }*/
        monthsCustomerHasApptsCombo.setItems(apptMonths);
        System.out.println("MONTH SET ITEMS WORKS");
    }

    public void populateComboBoxCustomerApptTypes(){
        String type;
        HashSet<String> types = new HashSet<>();
        for(Appointment a : appts){
            type = a.getType();
            if(!types.contains(type)){
                types.add(type);
            }
        }
        apptTypes.addAll(types);
        typesCombo.setItems(apptTypes);
    }



}
