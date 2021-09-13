package C195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {



    public LoginController(){

    }

    @FXML
    public TextField userNameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Label errorMessageLabel;
    @FXML
    public Button logInBtn;

    public void logInAttempt(ActionEvent event) throws IOException {
        verifyLogIn(event);
        //errorMessageLabel.setText("clicked");



    }

    public void verifyLogIn(ActionEvent e) throws IOException {
        Main main = new Main();
        if(userNameField.getText().toString().equals("test") &&
                passwordField.getText().toString().equals("test")){
            main.changeScene("successfulLogin.fxml", e);
        }else{
            errorMessageLabel.setText("Incorrect username or password");
            logInBtn.setText("Retry?");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
