package C195;

import C195.Entities.Country;
import C195.Entities.Customer;
import C195.Helper.DBCountries;
import C195.Helper.DBCustomer;
import C195.Helper.DBFirstLevDiv;
import C195.Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class CustomerUpdateController {
    @FXML
    public Label updateCustIDLabel;
    @FXML
    public ComboBox<String> updateCustomerComboDivId;
    @FXML
    public ComboBox<String> updateCustomerComboCountry;
    @FXML
    public TextField updateCustNameField;
    @FXML
    public TextField updateCustAddressField;
    @FXML
    public TextField updateCustPostalCodeField;
    @FXML
    public TextField updateCustPhoneField;
    @FXML
    public TextField currentPersonCustomerUpdatedByField;
    @FXML
    public Button updateCustomerBtn;
    @FXML
    public Label updateCustErrorField;
    @FXML
    public Label updateCustCreatedBy;
    @FXML
    public Label updateCustCreatedOn;
    @FXML
    public Label updateCustLastUpdatedBy;
    @FXML
    public Label updateCustLastUpdateDate;
    private ObservableList<Country> countries = FXCollections.observableArrayList();
    private ObservableList<String> countryNames = FXCollections.observableArrayList();
    private HashMap<String, Integer> countryIdAndName = new HashMap<>();
    private String customerID;

    public void updateCustomer(ActionEvent event) throws SQLException {
        String name = updateCustNameField.getText();
        String address = updateCustAddressField.getText();
        String postalCode = updateCustPostalCodeField.getText();
        String phone = updateCustPhoneField.getText();
        String updatedBy = currentPersonCustomerUpdatedByField.getText();
        String divID = DBFirstLevDiv.getDivID(updateCustomerComboDivId.getValue());
        //call DBCustomer update method
        DBCustomer.updateCustomer(customerID,name,address,postalCode,phone,updatedBy,divID);
    }

    public void populateCustomerData(String customerID){
        int cid = Integer.parseInt(customerID);
        Customer customer = DBCustomer.getACustomer(cid);
        updateCustIDLabel.setText(String.valueOf(customer.getCustomer_ID()));
        updateCustNameField.setText(customer.getCustomer_Name());
        updateCustAddressField.setText(customer.getAddress());
        updateCustPostalCodeField.setText(customer.getPostalCode());
        updateCustPhoneField.setText(customer.getPhone());
        updateCustCreatedBy.setText(customer.getCreatedBy());
        updateCustCreatedOn.setText(customer.getCreatedDate());
        updateCustLastUpdatedBy.setText(customer.getLastUpdatedBy());
        updateCustLastUpdateDate.setText(customer.getLastUpdate());

    }

    public void populateComboBoxCountry(){
        countryNames = FXCollections.observableArrayList();
        for(Country c : countries){
            countryNames.add(c.getCountryName());
            countryIdAndName.put(c.getCountryName(), c.getCountryID());
        }
        updateCustomerComboCountry.setItems(countryNames);
    }

    public void populateComboBoxDivId(ActionEvent event){
        if(countryNames != null){
            String country = updateCustomerComboCountry.getValue().toString();
            Integer countryId = countryIdAndName.get(country);
            ObservableList<String> divNames = DBFirstLevDiv.getDivNames(countryId);
            updateCustomerComboDivId.setItems(divNames);
        }else{
            updateCustErrorField.setTextFill(Color.RED);
            updateCustErrorField.setText("Please choose a Country First");
        }
    }

    public void goToMainMenuWindow(ActionEvent event) throws IOException {
        JDBC.closeConnection();
        Parent mainMenu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }

    public void initialize() {
        //JDBC.openConnection();
        countries = DBCountries.getAllCountries();
        populateComboBoxCountry();
        customerID = ChooseCustomerToUpdateController.customerID;
        populateCustomerData(customerID);
        System.out.println(customerID);
    }


}
