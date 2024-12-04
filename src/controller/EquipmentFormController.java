package controller;

import db.DatabaseAccessCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Equipment;
import view.tm.EquipmentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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

    public void initialize() throws SQLException, ClassNotFoundException {
        setID();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTools.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadEquipmentTable();
    }

    ObservableList <EquipmentTm> observableList = FXCollections.observableArrayList();
    private void loadEquipmentTable() throws SQLException, ClassNotFoundException {
        observableList.clear();
        ResultSet set = DatabaseAccessCode.findAllEquipment();
        while (set.next()){
            ButtonBar buttonBar = new ButtonBar();
            Button btn1 = new Button("Delete");
            Button btn2 = new Button("Update");
            buttonBar.getButtons().addAll(btn1,btn2);
            EquipmentTm equipmentTm = new EquipmentTm(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    buttonBar
            );

            btn1.setOnAction(e->{
                try {
                    if (DatabaseAccessCode.deleteEquipmentById(equipmentTm.getId())){
                        loadEquipmentTable();
                        new Alert(Alert.AlertType.INFORMATION,"Delete Successfully!").show();

                    }else {
                        new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            btn2.setOnAction(e->{
                        try {
                            ResultSet set1 = DatabaseAccessCode.findAllEquipment();
                            while (set1.next()){
                                txtId.setText(set1.getString(1));
                                txtTitle.setText(set1.getString(2));
                                txtDescription.setText(set1.getString(3));

                                saveOrUpdateMember.setText("Update Product");
                                return;
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
            });

            observableList.add(equipmentTm);
        }
        tblEquipment.setItems(observableList);

    }

    public void resetOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Equipment equipment = new Equipment();
        equipment.seteId(txtId.getText());
        equipment.setTitle(txtTitle.getText());
        equipment.setDescription(txtDescription.getText());

        if (DatabaseAccessCode.saveEquipment(equipment)){
            new Alert(Alert.AlertType.INFORMATION,"Saved Equipment!",ButtonType.OK).show();
            loadEquipmentTable();
            clear();
            setID();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong..",ButtonType.OK).show();
        }
    }

    private void setID(){
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); // Remove non-numeric characters
        String fourDigits = uuid.substring(0, 4);
        txtId.setText(fourDigits);
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
        txtDescription.clear();
        txtTitle.clear();
        txtSearchHere.clear();
    }
}
