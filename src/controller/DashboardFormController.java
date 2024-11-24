package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {
    public BorderPane dashboardContext;

    public void attendOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AttendanceForm");
    }

    public void memberOnAction(ActionEvent actionEvent) throws IOException {
        setUi("MemberForm");
    }

    public void equipmentOnAction(ActionEvent actionEvent) throws IOException {
        setUi("EquipmentForm");
    }
    private void setUi(String location) throws IOException {
        URL resource = getClass().getResource("/view/" + location + ".fxml");
        Stage stage = (Stage) dashboardContext.getScene().getWindow();
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }
}
