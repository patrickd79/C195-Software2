package C195;

import C195.Helper.DBCustomer;
import C195.Helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class AddCustomerController {
    @FXML
    public ComboBox addCustomerComboDivId;
    @FXML
    public ComboBox addCustomerComboCountry;
    @FXML
    public TextField custNameField;
    @FXML
    public TextField custAddressField;
    @FXML
    public TextField custPostalCodeField;
    @FXML
    public TextField custPhoneField;
    @FXML
    public TextField customerCreatedByField;
    @FXML
    public Button addCustomerBtn;
    @FXML
    public Label addCustErrorField;

    public void addCustomer(ActionEvent event){
        try {
            DBCustomer.addCustomer(custNameField.getText().toString(), custAddressField.getText().toString(),
                    custPostalCodeField.getText().toString(), custPhoneField.getText().toString(),
                    customerCreatedByField.getText().toString(), addCustomerComboDivId.getValue().toString());
            addCustErrorField.setTextFill(Color.BLACK);
            addCustErrorField.setText("Customer Record Created");

            JDBC.closeConnection();
        }catch(Exception exception){
            addCustErrorField.setTextFill(Color.RED);
            addCustErrorField.setText("Please complete all fields");
        }
    }

    //CHANGE TO DYNAMIC DATA RETRIEVE WHEN POSSIBLE!!!
    public void populateComboBoxCountry(){
        //change to dynamic data retrieve when possible!!!
        addCustomerComboCountry.getItems().addAll("USA","China");
    }

    //CHANGE TO DYNAMIC DATA RETRIEVE WHEN POSSIBLE!!!
    public void populateComboBoxDivId(){
        addCustomerComboDivId.getItems().addAll(1,2,3,4);
    }

    public void initialize() {
        populateComboBoxCountry();
        populateComboBoxDivId();
    }



}
