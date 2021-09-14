package C195;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.util.Locale;

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
