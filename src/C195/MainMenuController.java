package C195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

public class MainMenuController {

    @FXML
    public Button addAppointmentBtn;
    public Connection connection;

    public void initialize() {
    //System.out.println(Locale.getDefault().toString());
    //successfulLoginTextArea.setText(Locale.getDefault().toString());
        //open a connection to the DB
        //JDBC.openConnection();
        //get the connection object to use in queries
        //connection = JDBC.getConnection();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        //DBCountries.addCountry("Britain", sqlDate);
        //printCountries();


    //don't forget to close the connection!



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

    public void goToUpdateAppointmentWindow(ActionEvent event) throws IOException {
        Parent updateAppointmentWindow = FXMLLoader.load(getClass().getResource("chooseAppointmentToUpdate.fxml"));
        Scene updateAppointmentScene = new Scene(updateAppointmentWindow);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(updateAppointmentScene);
        window.show();
    }

    //get all countries and print out
    /*public void printCountries() {
        ObservableList<Country> countries = DBCountries.getAllCountries();
        for (Country country : countries) {
            System.out.println(country.getCountryID() +" "+ country.getCountryName());
        }
    }*/





    /*public static void printTime(ActionEvent e) throws IOException {
        Locale locale = Locale.getDefault();
        successfulLoginTextArea.setText(locale.toString());
    }*/


}
