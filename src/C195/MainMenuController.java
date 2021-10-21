package C195;

import C195.Entities.Appointment;
import C195.Entities.User;
import C195.Helper.DBAppointment;
import C195.Helper.DBUser;
import C195.Helper.JDBC;
import C195.Helper.TimeZones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainMenuController {

    @FXML
    public Button addAppointmentBtn;
    public Connection connection;
    public static User user;
    @FXML
    public Label mainMenuMessages;
    public String userID;
    public static Paint color;


    public void initialize() {
        JDBC.openConnection();
        //System.out.println("UserName:  "+ LoginController.userNameField.getText().trim());
        user = DBUser.getAUserByName(LoginController.thisUser);
        //user = DBUser.getAUserByName("User One");
        userID = String.valueOf(user.getUserID());
        populateMainMenuLabel();
    }

    public void populateMainMenuLabel(){
        mainMenuMessages.setText(checkUserAppts(user));
        mainMenuMessages.setTextFill(color);
    }

    public static String checkUserAppts(User user) {
        String msg = null;
        Appointment nextAppt;
        //System.out.println("User Name: "+user.getUserName());
        //System.out.println("User ID: "+user.getUserID());
        ObservableList<Appointment> appts = FXCollections.observableArrayList();
        appts = DBAppointment.getAppointmentsForASingleUserByID(String.valueOf(user.getUserID()));
        //System.out.println("Appts size:"+appts.size());
        for (Appointment a : appts) {
            System.out.println(a.getUserID());
        }
        String currentDate = getCurrentTime();
        //System.out.println("Current Date: "+currentDate);
        String ESTCurrentDate = TimeZones.convertToESTTimeZone(currentDate);
        //System.out.println("EST Current Date: "+ESTCurrentDate);
        String currentYear = DBAppointment.extractYear(ESTCurrentDate);
        //System.out.println("cur year:"+currentYear);
        String currentMonth = DBAppointment.extractMonth(ESTCurrentDate);
        //System.out.println("current mnth:"+currentMonth);
        String currentDay = DBAppointment.extractDay(ESTCurrentDate);
        //System.out.println("current day:"+currentDay);
        String time = UpdateAppointmentController.getTime(ESTCurrentDate);
        String currentHour = String.valueOf(AddAppointmentController.getHour(time));
        String currentMinutes = String.valueOf(AddAppointmentController.getMinutes(time));
        int currentTimeInt = Integer.parseInt(currentHour + currentMinutes);
        //System.out.println("Current Time int:"+currentTimeInt);
        String ESTApptDate;
        String apptYear;
        String apptMonth;
        String apptDay;
        String apptHour;
        String apptMinutes;
        int apptTimeInt = 0;

        for (Appointment a : appts) {
            System.out.println("appointment");
            //get the date of the appt in EST
            ESTApptDate = TimeZones.convertToESTTimeZone(a.getStart());
            System.out.println("EST Appt Date: " + ESTApptDate + "   Appt ID: " + a.getAppointmentID());
            apptYear = DBAppointment.extractYear(ESTApptDate);
            System.out.println("apt year:" + apptYear);
            apptMonth = DBAppointment.extractMonth(ESTApptDate);
            System.out.println("apt mnth:" + apptMonth);
            apptDay = DBAppointment.extractDay(ESTApptDate);
            System.out.println("apt day:" + apptDay);
            String apptTime = UpdateAppointmentController.getTime(ESTApptDate);
            apptHour = String.valueOf(AddAppointmentController.getHour(apptTime));
            apptMinutes = String.valueOf(AddAppointmentController.getMinutes(apptTime));
            apptTimeInt = Integer.parseInt(apptHour + apptMinutes);

            //check if the date matches current date
            if (apptYear.equals(currentYear) && apptMonth.equals(currentMonth) && apptDay.equals(currentDay)) {
                System.out.println("Date Matches");
                if (apptTimeInt - currentTimeInt < 15) {
                    msg = "You have an upcoming appointment, Title: " + a.getTitle() + " at " + a.getStart();
                     color = Color.RED;
                    break;
                } else {
                    msg = "You have no upcoming appointments within the next 15 minutes.";
                    color = Color.BLACK;
                }
            } else {
                msg = "You have no upcoming appointments within the next 15 minutes.";
                color = Color.BLACK;
            }

        }
        return msg;
    }


    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    public void goToAddCustomerWindow(ActionEvent event) throws IOException {
        Parent addCustomerWindow = FXMLLoader.load(getClass().getResource("addCustomer.fxml"));
        Scene addCustomerScene = new Scene(addCustomerWindow);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addCustomerScene);
        window.show();
    }

    public void goToUpdateCustomerWindow(ActionEvent event) throws IOException {
        Parent updateCustomerWindow = FXMLLoader.load(getClass().getResource("chooseCustomerToUpdate.fxml"));
        Scene updateCustomerScene = new Scene(updateCustomerWindow);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(updateCustomerScene);
        window.show();
    }

    public void goToAddAppointmentWindow(ActionEvent event) throws IOException {
        Parent addAppointmentWindow = FXMLLoader.load(getClass().getResource("addAppointment.fxml"));
        Scene addAppointmentScene = new Scene(addAppointmentWindow);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addAppointmentScene);
        window.show();
    }

    public void goToViewAppointmentWindow(ActionEvent event) throws IOException {
        Parent viewAppointmentWindow = FXMLLoader.load(getClass().getResource("viewAppointments.fxml"));
        Scene viewAppointmentScene = new Scene(viewAppointmentWindow);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewAppointmentScene);
        window.show();
    }

    public void goToUpdateAppointmentWindow(ActionEvent event) throws IOException {
        Parent updateAppointmentWindow = FXMLLoader.load(getClass().getResource("chooseAppointmentToUpdate.fxml"));
        Scene updateAppointmentScene = new Scene(updateAppointmentWindow);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(updateAppointmentScene);
        window.show();
    }
}
