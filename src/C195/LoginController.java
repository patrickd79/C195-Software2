package C195;

import C195.Helper.TimeZones;
import C195.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    public Label scheduleLogInLabel;
    @FXML
    public Label languageDisplayLabel;
    @FXML
    public TextField userNameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Label errorMessageLabel;
    @FXML
    public Button logInBtn;
    @FXML
    public Label userNameLabel;
    @FXML
    public Label passwordLabel;

    public LoginController(){
    }

    public void setLanguageDisplayLabel(){
        languageDisplayLabel.setText(Main.zoneID + "   " +Main.zone + " : " + Main.userLanguage + " : " + Main.userCountry);
    }

    public void setLabels(){
        scheduleLogInLabel.setText(Main.resourceBundle.getString("SchedulingApplication"));
        logInBtn.setText(Main.resourceBundle.getString("login"));
        userNameLabel.setText(Main.resourceBundle.getString("username"));
        passwordLabel.setText(Main.resourceBundle.getString("password"));
    }

    public void logInAttempt(ActionEvent event) throws IOException {
        if(verifyLogIn(event)){
            changeScene("mainMenu.fxml", event);
        }else {
            errorMessageLabel.setText(Main.resourceBundle.getString("IncorrectUsernameOrPassword"));
                logInBtn.setText(Main.resourceBundle.getString("Retry"));
            }
    }

    public boolean verifyLogIn(ActionEvent e) throws IOException {
        return userNameField.getText().toString().equals("test") &&
                passwordField.getText().toString().equals("test");
    }

    public void changeScene(String s, ActionEvent e) throws IOException {
        Parent newWindow = FXMLLoader.load(getClass().getResource(s));
        Scene newScene = new Scene(newWindow);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguageDisplayLabel();
        setLabels();
        System.out.println(TimeZones.getDayOfWeekEST("2021-10-19 08:00:00"));
    }
}
