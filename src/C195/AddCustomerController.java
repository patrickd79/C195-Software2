package C195;

import C195.Entities.Country;
import C195.Helper.DBCountries;
import C195.Helper.DBCustomer;
import C195.Helper.DBFirstLevDiv;
import C195.Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class AddCustomerController {
    @FXML
    public ComboBox<String> addCustomerComboDivId;
    @FXML
    public ComboBox<String> addCustomerComboCountry;
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
    private ObservableList<Country> countries = FXCollections.observableArrayList();
    private ObservableList<String> countryNames = FXCollections.observableArrayList();
    private ObservableList<String> divNames = FXCollections.observableArrayList();
    private HashMap<String, Integer> countryIdAndName = new HashMap<>();


    public void addCustomer(ActionEvent event){
        try {
            DBCustomer.addCustomer(custNameField.getText().toString(), custAddressField.getText().toString(),
                    custPostalCodeField.getText().toString(), custPhoneField.getText().toString(),
                    customerCreatedByField.getText().toString(), DBFirstLevDiv.getDivID(addCustomerComboDivId.getValue()));
            addCustErrorField.setTextFill(Color.BLACK);
            addCustErrorField.setText("Customer Record Created");

            JDBC.closeConnection();
        }catch(Exception exception){
            addCustErrorField.setTextFill(Color.RED);
            addCustErrorField.setText("Please complete all fields");
            exception.printStackTrace();
        }
    }

    //CHANGE TO DYNAMIC DATA RETRIEVE WHEN POSSIBLE!!!
    public void populateComboBoxCountry(){
        countryNames = FXCollections.observableArrayList();
        for(Country c : countries){
            countryNames.add(c.getCountryName());
            countryIdAndName.put(c.getCountryName(), c.getCountryID());
        }
        addCustomerComboCountry.setItems(countryNames);
    }

    //CHANGE TO DYNAMIC DATA RETRIEVE WHEN POSSIBLE!!!
    public void populateComboBoxDivId(ActionEvent event){
        if(countryNames != null){
            String country = addCustomerComboCountry.getValue().toString();
            Integer countryId = countryIdAndName.get(country);
            divNames = DBFirstLevDiv.getDivNames(countryId);
            addCustomerComboDivId.setItems(divNames);
        }else{
            addCustErrorField.setTextFill(Color.RED);
            addCustErrorField.setText("Please choose a Country First");
        }
    }

    public void initialize() {
        JDBC.openConnection();
        countries = DBCountries.getAllCountries();
        populateComboBoxCountry();
    }



}
