

package C195;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        // Load the FXML file
        Parent parent = FXMLLoader.load(
                getClass().getResource("login.fxml"));

        // Build the scene graph
        Scene scene = new Scene(parent);
        // Display window using the scene graph
        stage.setTitle("Scheduling Application");
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
