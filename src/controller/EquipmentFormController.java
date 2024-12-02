package controller;

import db.DatabaseAccessCode;
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
import model.Equipment;

import java.io.IOException;
import java.net.URL;

public class EquipmentFormController {
    public TextField txtTitle;
    public TextField txtDescription;
    public Button saveOrUpdateMember;
    public TextField txtSearchHere;
    public TextField txtId;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colDescription;
    public TableColumn colTools;
    public BorderPane equipmentContext;
    public TableView tblEquipment;

    public void resetOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void saveOnAction(ActionEvent actionEvent) {
//        Equipment equipment = new Equipment();
//        equipment.seteId(txtId.ge);
//        if (DatabaseAccessCode.saveEquipment())
    }
    private void setUi(String location) throws IOException {
        URL resource = getClass().getResource("/view/" + location + ".fxml");
        Stage stage = (Stage) equipmentContext.getScene().getWindow();
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }
    public void clear(){
        txtId.clear();
        txtDescription.clear();
        txtTitle.clear();
        txtSearchHere.clear();
    }
}
