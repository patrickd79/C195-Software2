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
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import C195.Helper.*;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

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
    @FXML
    public RadioButton startTimeAMToggle;
    @FXML
    public ToggleGroup startTime;
    @FXML
    public RadioButton startTimePMToggle;
    @FXML
    public RadioButton endTimeAMToggle;
    @FXML
    public ToggleGroup endTime;
    @FXML
    public RadioButton endTimePMToggle;
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    ObservableList<String> contactNames = FXCollections.observableArrayList();
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<String> customerNames = FXCollections.observableArrayList();
    ObservableList<User> users = FXCollections.observableArrayList();
    ObservableList<String> userNames = FXCollections.observableArrayList();

    public void addAppointment(ActionEvent event){

        try {
                User user = DBUser.getAUserByName(addAppointmentUserIDField.getValue());
                Contact contact = DBContacts.getAContactByName(addAppointmentContactNameField.getValue());
                Customer customer = DBCustomer.getACustomerByName(addAppointmentCustIDField.getValue());
                String start = addApptStartTimeField.getText();
                String end = addApptEndTimeField.getText();
                String adjustedStartTime;
                String adjustedEndTime;

                //adjust time to a 24 hour clock for the start time
                if(startTimePMToggle.isSelected() && getHour(start) < 12){
                    adjustedStartTime = (getHour(start) + 12) +":"+ getMinutes(start);
                }else{
                    adjustedStartTime = start;
                }
                //adjust time to a 24 hour clock for the end time
                if(endTimePMToggle.isSelected() && getHour(end) < 12){
                    adjustedEndTime = (getHour(end) + 12) +":"+ getMinutes(end);
                }else{
                    adjustedEndTime = end;
                }
                System.out.println("Start: "+adjustedStartTime+" End: "+adjustedEndTime);
                String startTime = addAppointmentStartDate.getValue().toString() + " "+ adjustedStartTime;
                String endTime = addAppointmentStartDate.getValue().toString() + " " +adjustedEndTime;
                if(isDuringOfficeHours(startTime, endTime) &&
                        !customerHasOverlappingAppointments(String.valueOf(customer.getCustomer_ID()),startTime, endTime)){
                    DBAppointment.addAppointment(addAppointmentTitleField.getText(), addAppointmentDescField.getText(), addAppointmentLocationField.getText(),
                            addAppointmentTypeField.getText(), startTime, endTime,
                            user.getUserName(),
                            String.valueOf(customer.getCustomer_ID()), String.valueOf(user.getUserID()),
                            String.valueOf(contact.getContactID()));
                    addApptErrorField.setTextFill(Color.BLACK);
                    addApptErrorField.setText("Appointment Created");

                }else if(!isDuringOfficeHours(startTime, endTime)){
                    addApptErrorField.setTextFill(Color.RED);
                    addApptErrorField.setText("Please make sure that appointment time is between 0800 EST and 2200 EST.");
                }else if(customerHasOverlappingAppointments(String.valueOf(customer.getCustomer_ID()),startTime, endTime)){
                    addApptErrorField.setTextFill(Color.RED);
                    addApptErrorField.setText("Please change the date or time of this appointment. This appointment overlaps another one of the customer's appointments.");
                }


        } catch (Exception e) {
                addApptErrorField.setTextFill(Color.RED);
                addApptErrorField.setText("Please complete all fields");
                e.printStackTrace();
            }
    }

    public static boolean isDuringOfficeHours(String start, String end){
        System.out.println("Start: "+start+"   End: "+end);
        //convert the passed date and times to EST
        String startEST = TimeZones.convertToESTTimeZone(start);
        String endEST = TimeZones.convertToESTTimeZone(end);
        //String startDayOfWeek
        //System.out.println("Start EST Converted: "+startEST+"    End EST Converted: "+endEST);
        // extract the time portion of the string from the dates
        String startTimeEST = UpdateAppointmentController.getTime(startEST);
        String endTimeEST = UpdateAppointmentController.getTime(endEST);
        //remove the : from the time and convert to an int
        int startTimeInt = removeColonFromTime(startTimeEST);
        int endTimeInt = removeColonFromTime(endTimeEST);
        //System.out.println("Start Int: "+startTimeInt+ "    End Time Int:"+endTimeInt);

        return startTimeInt > 759 && startTimeInt < endTimeInt && endTimeInt < 2201;
    }

    public static boolean customerHasOverlappingAppointments(String customerID, String startDate, String endDate){
        ObservableList<Appointment> appts = DBAppointment.getAppointmentsForASingleCustomerByID(customerID);
        ObservableList<Date> dates = FXCollections.observableArrayList();
        Date start = TimeZones.convertStringToDate(startDate);
        Date end = TimeZones.convertStringToDate(endDate);
            for(Appointment a : appts){
                if(start.before(TimeZones.convertStringToDate(a.getEnd())) ||
                        end.after(TimeZones.convertStringToDate(a.getStart()))){
                    return true;
                }
            }
            return false;
    }

    public String adjustStartTimeTo24H(String time){
        String adjustedTime;
        //adjust time to a 24 hour clock for the start time
        if(startTimePMToggle.isSelected() && getHour(time) < 12){
            return adjustedTime = (getHour(time) + 12) +":"+ getMinutes(time);
        }else{
            return adjustedTime = time;
        }
    }

    public String adjustEndTimeTo24H(String time){
        String adjustedTime;
        //adjust time to a 24 hour clock for the start time
        if(endTimePMToggle.isSelected() && getHour(time) < 12){
            return adjustedTime = (getHour(time) + 12) +":"+ getMinutes(time);
        }else{
            return adjustedTime = time;
        }
    }
    public static int removeColonFromTime(String time){
        char[] ca = time.toCharArray();
        StringBuilder sb = new StringBuilder();
        String number;
        for(int i = 0; i < 2; i++){
            sb.append(ca[i]);
        }
        for(int i = 3; i < 5; i++){
            sb.append(ca[i]);
        }
        number = sb.toString();
        //returns the time with the ':' removed
        return Integer.parseInt(number);
    }

    public static String getMinutes(String time){
        String[] strings = time.split(":",2);
         return strings[1];
    }

    public static int getHour(String time){
        String[] strings = time.split(":",2);
        int result = Integer.parseInt(strings[0]);
        //System.out.println(result);
        return result;
    }

    public boolean togglesEmpty(){
        boolean empty = false;
        if(startTime.getToggles().isEmpty()){
            startTimeAMToggle.setTextFill(Color.RED);
            startTimePMToggle.setTextFill(Color.RED);
            addApptErrorField.setTextFill(Color.RED);
            addApptErrorField.setText("Please choose /'AM/' or /'PM/'");
            empty = true;
        }else if(endTime.getToggles().isEmpty()){
            endTimeAMToggle.setTextFill(Color.RED);
            endTimePMToggle.setTextFill(Color.RED);
            addApptErrorField.setTextFill(Color.RED);
            addApptErrorField.setText("Please choose /'AM/' or /'PM/'");
            empty = true;
        }
        return empty;
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
