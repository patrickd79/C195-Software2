package C195;

import C195.Entities.Appointment;
import C195.Entities.Customer;
import C195.Helper.DBAppointment;
import C195.Helper.DBCustomer;
import C195.Helper.JDBC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseAppointmentToUpdateController {
    @FXML
    public TableView<Appointment> tableView;
    @FXML
    public TableColumn<Appointment, Integer> IDCol;
    @FXML
    public TableColumn<Appointment, String> titleCol;
    @FXML
    public TableColumn<Appointment, String> descriptionCol;
    @FXML
    public TableColumn<Appointment, String> locationCol;
    @FXML
    public TableColumn<Appointment, String> typeCol;
    @FXML
    public TableColumn<Appointment, String> startCol;
    @FXML
    public TableColumn<Appointment, String> endCol;
    @FXML
    public TableColumn<Appointment, String> createDateCol;
    @FXML
    public TableColumn<Appointment, String> createdByCol;
    @FXML
    public TableColumn<Appointment, String> lastUpdateDateCol;
    @FXML
    public TableColumn<Appointment, String> lastUpdatedByCol;
    @FXML
    public TableColumn<Appointment, String> customerIDCol;
    @FXML
    public TableColumn<Appointment, String> userIDCol;
    @FXML
    public TableColumn<Appointment, String> contactIDCol;
    @FXML
    public Label deleteAppointmentMessage;
    public static String apptID;
    public static String title;

    public void initialize() {
        JDBC.openConnection();
        setTableView();

    }

    public void setTableView(){
        tableView.setItems(DBAppointment.getAllAppointments());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateDateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void goToUpdateAppointmentWindow(ActionEvent event) throws IOException {
        apptToUpdate();
        Parent updateAppointmentWindow = FXMLLoader.load(getClass().getResource("updateAppointment.fxml"));
        Scene updateAppointmentScene = new Scene(updateAppointmentWindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(updateAppointmentScene);
        window.show();
    }

    public void apptToUpdate(){
            ObservableList<Appointment> selectedAppt;
            selectedAppt = tableView.getSelectionModel().getSelectedItems();
            for(Appointment appt: selectedAppt){
                apptID = String.valueOf(appt.getAppointmentID());
                title = appt.getTitle();
            }
        }

    public void goToMainMenuWindow(ActionEvent event) {
    }

    public void deleteAppointment(ActionEvent event) {
    }
}
