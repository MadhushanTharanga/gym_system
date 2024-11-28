package controller;

import db.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {
    public BorderPane dashboardContext;
    public Label lblDashboardName;

    public void initialize(){
        lblDashboardName.setText(Utilities.user.getName());
    }

    public void attendOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AttendanceForm",true);
    }

    public void memberOnAction(ActionEvent actionEvent) throws IOException {
        setUi("MemberForm",true);
    }

    public void equipmentOnAction(ActionEvent actionEvent) throws IOException {
        setUi("EquipmentForm",true);
    }
    private void setUi(String location,boolean isActive) throws IOException {
        URL resource = getClass().getResource("/view/" + location + ".fxml");
        Stage stage = (Stage) dashboardContext.getScene().getWindow();
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(isActive);
    }

    

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm",false);
    }
}
