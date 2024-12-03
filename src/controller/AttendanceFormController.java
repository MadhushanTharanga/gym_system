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
import model.Attendance;
import model.User;
import view.tm.AttendanceTm;
import view.tm.MemberTm;
import view.tm.UserTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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
    public ComboBox <String> cmbMember;
    public DatePicker datePicker;

    private String memberId;

    public void initialize() throws SQLException, ClassNotFoundException {
        setID();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("user_userId"));
        colTools.setCellValueFactory(new PropertyValueFactory<>("btn"));

        cmbMember.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            memberId=newValue;
            try {
                loadMember();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        loadMember();
        loadAttendanceTable();
    }


    private void loadMember() throws SQLException, ClassNotFoundException {
        ObservableList <String> list = FXCollections.observableArrayList();

        ResultSet set = DatabaseAccessCode.findAllMember();
        while (set.next()){
            list.add(set.getString("userId"));

            ButtonBar buttonBar = new ButtonBar();
            Button btn1 = new Button("Delete");
            Button btn2 = new Button("Update");
            buttonBar.getButtons().addAll(btn1,btn2);

            MemberTm memberTm = new MemberTm();
            memberTm.setId(set.getString(1));
            memberTm.setName(set.getString(2));

            btn1.setOnAction(e->{
                try {
                    if (DatabaseAccessCode.deleteAttendanceById(memberTm.getId())){
                        new Alert(Alert.AlertType.INFORMATION,"Delete Successfully!").show();
                        loadAttendanceTable();
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
                    ResultSet set1 = DatabaseAccessCode.findAttendanceById(memberTm.getId());
                    while (set1.next()){
                        txtAttendId.setText(set1.getString(1));
                        txtDate.setText(set1.getString(2));
                        txtUserId.setText(set1.getString(3));

                        saveOrUpdateAttendance.setText("Update Product");
                        return;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        tblContext.setItems(observableList);
        cmbMember.setItems(list);
    }

    private void setID(){
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); // Remove non-numeric characters
        String fourDigits = uuid.substring(0, 4);
        txtAttendId.setText(fourDigits);
    }

    ObservableList <AttendanceTm> observableList= FXCollections.observableArrayList();
    private void loadAttendanceTable() throws SQLException, ClassNotFoundException {
        observableList.clear();
        ResultSet set = DatabaseAccessCode.findAllAttendance();
        while (set.next()){
            ButtonBar buttonBar = new ButtonBar();
            Button btn1 = new Button("Delete");
            buttonBar.getButtons().addAll(btn1);

            AttendanceTm attendanceTm = new AttendanceTm(set.getString(1),set.getDate(2), set.getString(3),buttonBar);


            btn1.setOnAction(e->{
                try {
                    if (DatabaseAccessCode.deleteAttendanceById(attendanceTm.getId())){
                        new Alert(Alert.AlertType.INFORMATION,"Delete Successfully!").show();
                        loadAttendanceTable();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            observableList.add(attendanceTm);
        }
        tblContext.setItems(observableList);
    }

    public void resetOnAction(ActionEvent actionEvent) {

    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Attendance attendance = new Attendance();
        attendance.setaId(txtAttendId.getText());
        attendance.setDate(Date.valueOf(datePicker.getValue()));
        attendance.setUser_userId(memberId);

        if (DatabaseAccessCode.saveAttendance(attendance)){
            new Alert(Alert.AlertType.INFORMATION,"Save Attendance!",ButtonType.OK).show();
            loadAttendanceTable();
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
