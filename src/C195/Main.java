

package C195;

import C195.Helper.JDBC;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    //sets the user language and country settings based on user's local settings
    public static String userLanguage = Locale.getDefault().getLanguage();
    public static String userCountry = Locale.getDefault().getCountry();
    //public static String userLanguage = "fr";
    //public static String userCountry = "CA";

    //sets the zone string based on user's local system settings
    public static String zone = ZoneId.systemDefault().getDisplayName(TextStyle.FULL,
            Locale.getDefault());
    public static String zoneID = ZoneId.systemDefault().getId();
    //public static Locale locale = new Locale(userLanguage, userCountry);

    public static ResourceBundle resourceBundle;
    //creates an instance of Resource bundle depending on results of setResourceBundle method
    public static ResourceBundle french = ResourceBundle.getBundle("C195/Bundle", new Locale("fr", "CA") );
    public static ResourceBundle english = ResourceBundle.getBundle("C195/Bundle", new Locale("en", "US") );

    //selects the resource bundle to be used based on the user's language settings
    public void setResourceBundle(String userLanguage){
        if(userLanguage.equals("fr")){
            resourceBundle = french;
        }else{
            resourceBundle = english;
        }
    }

    @Override
    public void start(Stage stage) throws Exception{
        //set the resource bundle to use for the current user's experience
        setResourceBundle(userLanguage);
        Date date = new Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
        System.out.println(sqlDate.toString());
        // Load the FXML file
        Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
        // Build the scene graph
        Scene scene = new Scene(parent);
        // Display window using the scene graph
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        stage.setTitle(resourceBundle.getString("SchedulingApplication"));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> exitApplication());
    }

    public static void main(String[] args) {
        launch(args);
    }

    //changes from one scene to the next
    public void changeScene(String s, ActionEvent e) throws IOException {
        Parent newWindow = FXMLLoader.load(getClass().getResource(s));
        Scene newScene = new Scene(newWindow);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    /**
     * closes the application on the click of the exit button
     */
    public void exitApplication(){
        JDBC.closeConnection();
        System.out.println("Closing");
        System.exit(0);
    }
}
