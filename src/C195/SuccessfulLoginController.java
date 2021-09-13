package C195;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SuccessfulLoginController {
    @FXML
    public TextField successfulLoginTextArea;


    public void initialize() {
    System.out.println(Locale.getDefault().toString());
    successfulLoginTextArea.setText(Locale.getDefault().toString());

    }





    /*public static void printTime(ActionEvent e) throws IOException {
        Locale locale = Locale.getDefault();
        successfulLoginTextArea.setText(locale.toString());
    }*/


}
