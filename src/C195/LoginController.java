package C195;

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
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
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
    //public String userLanguage = Locale.getDefault().getDisplayLanguage();
    public String userLanguage = "French";
    public boolean french = false;
    public String zone = ZoneId.systemDefault().getDisplayName(TextStyle.FULL,
            Locale.getDefault());



    public LoginController(){
    }

    public void setLanguageDisplayLabel(){
        languageDisplayLabel.setText(zone + " : " + userLanguage);
    }

    public void setAppLanguage( String userLanguage){
        //need to write logic to translate to users language
        if(userLanguage.equals("French")) {
            french = true;
        }
    }
    public void setScheduleLogInLabel(){
        if(french){
            scheduleLogInLabel.setText("Se Connecter");
        }
    }

    public void logInAttempt(ActionEvent event) throws IOException {
        if(verifyLogIn(event)){
            changeScene("successfulLogin.fxml", event);
        }else {
            if(!french){
                errorMessageLabel.setText("Incorrect username or password");
                logInBtn.setText("Retry?");
            }else{
                errorMessageLabel.setText("Identifiant ou mot de passe incorrect");
                logInBtn.setText("Réessayez?");
            }

        }
    }

    public boolean verifyLogIn(ActionEvent e) throws IOException {
        if(userNameField.getText().toString().equals("test") &&
                passwordField.getText().toString().equals("test")) return true;
        return false;
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
        setAppLanguage(userLanguage);
        setScheduleLogInLabel();
    }
}
