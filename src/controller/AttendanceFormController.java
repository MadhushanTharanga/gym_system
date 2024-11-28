package controller;

import db.DatabaseAccessCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Attendance;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;

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

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Attendance attendance = new Attendance();
        attendance.setaId(txtUserId.getText());
        attendance.setDate(Date.valueOf(txtDate.getText()));
        attendance.setUser_userId(txtUserId.getText());

        if (DatabaseAccessCode.saveAttendance(attendance)){
            new Alert(Alert.AlertType.INFORMATION,"Save Attendance!",ButtonType.OK).show();
        }
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
