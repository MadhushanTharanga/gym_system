package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AttendanceFormController {
    public TableView tblContext;
    public BorderPane attendContext;
    public TableColumn colId;
    public TableColumn colDate;
    public TableColumn colUserId;
    public TableColumn colTools;
    public TextField txtAttendId;
    public TextField txtSearchHere;
    public Button saveOrUpdateAttendance;
    public TextField txtUserId;
    public TextField txtDate;



    public void addNewEquipmentOnAction(MouseEvent mouseEvent) {
    }

    public void resetOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) {
    }
    private void setUi(String location) throws IOException {
        URL resource = getClass().getResource("/view/" + location + ".fxml");
        Stage stage = (Stage) attendContext.getScene().getWindow();
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }
}
