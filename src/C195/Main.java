

package C195;

import C195.Helper.JDBC;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {


    public static String userLanguage = Locale.getDefault().getLanguage();
    public static String userCountry = Locale.getDefault().getCountry();
    //public static String userLanguage = "fr";
    //public static String userCountry = "CA";
    public static String zone = ZoneId.systemDefault().getDisplayName(TextStyle.FULL,
            Locale.getDefault());
    //public static Locale locale = new Locale(userLanguage, userCountry);
    public static ResourceBundle resourceBundle;
    public static ResourceBundle french = ResourceBundle.getBundle("C195/Bundle", new Locale("fr", "CA") );
    public static ResourceBundle english = ResourceBundle.getBundle("C195/Bundle", new Locale("en", "US") );

    public ResourceBundle setResourceBundle(String userLanguage){
        if(userLanguage.equals("fr")){
            resourceBundle = french;
        }else{
            resourceBundle = english;
        }
        return resourceBundle;
    }

    @Override
    public void start(Stage stage) throws Exception{
        setResourceBundle(userLanguage);
        // Load the FXML file
        Parent parent = FXMLLoader.load(
                getClass().getResource("login.fxml"));
        // Build the scene graph
        Scene scene = new Scene(parent);
        // Display window using the scene graph
        stage.setTitle(resourceBundle.getString("SchedulingApplication"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeScene(String s, ActionEvent e) throws IOException {
        Parent newWindow = FXMLLoader.load(getClass().getResource(s));
        Scene newScene = new Scene(newWindow);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
}
