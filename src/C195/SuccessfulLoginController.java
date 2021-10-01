package C195;

import C195.Entities.Country;
import C195.Helper.DBCountries;
import C195.Helper.JDBC;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class SuccessfulLoginController {
    @FXML
    public TextField successfulLoginTextArea;
    public Connection connection;

    //get all countries and print out
    public void printCountries() {
        ObservableList<Country> countries = DBCountries.getAllCountries();
        for (Country country : countries) {
            System.out.println(country.getCountryID() +" "+ country.getCountryName());
        }
    }



    public void initialize() {
    //System.out.println(Locale.getDefault().toString());
    //successfulLoginTextArea.setText(Locale.getDefault().toString());
        //open a connection to the DB
        JDBC.openConnection();
        //get the connection object to use in queries
        //connection = JDBC.getConnection();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        //DBCountries.addCountry("Britain", sqlDate);
        //printCountries();
        JDBC.closeConnection();

    //don't forget to close the connection!

    }





    /*public static void printTime(ActionEvent e) throws IOException {
        Locale locale = Locale.getDefault();
        successfulLoginTextArea.setText(locale.toString());
    }*/


}
