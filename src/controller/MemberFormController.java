package controller;

import db.CrudUtil;
import db.DatabaseAccessCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import view.tm.UserTm;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MemberFormController {
    public Label lblCopyRight;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtEmail;
    public TextField txtPassword;
    public Button saveOrUpdateMember;
    public TextField txtSearchHere;
    public TextField txtMemberId;
    public TableView tblContext;
    public TableColumn colMemberId;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colPassword;
    public TableColumn colTools;
    public BorderPane memberContext;

    public void initialize(){
        setID();
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTools.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadMemberTable();


    }
    ObservableList <UserTm> observableList = FXCollections.observableArrayList();
    private void loadMemberTable() {
        observableList.clear();
        try {
            ResultSet set = DatabaseAccessCode.getAllMember();
            while (set.next()){

                ButtonBar buttonBar = new ButtonBar();
                Button btn1 = new Button("Delete");
                Button btn2 = new Button("Update");
                buttonBar.getButtons().addAll(btn1,btn2);

                UserTm userTm = new UserTm();
                userTm.setUserId(set.getString(1));
                userTm.setName(set.getString(2));
                userTm.setContact(set.getInt(3));
                userTm.setEmail(set.getString(4));
                userTm.setBtn(buttonBar);

                btn1.setOnAction(e->{
                    try {
                        if (DatabaseAccessCode.deleteMemberById(userTm.getUserId())){
                            new Alert(Alert.AlertType.INFORMATION,"Delete Successfully!").show();
                            loadMemberTable();
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
                        ResultSet set1 = DatabaseAccessCode.findMemberById(userTm.getUserId());
                        while (set1.next()){
                            txtMemberId.setText(set1.getString(1));
                            txtName.setText(set1.getString(2));
                            txtEmail.setText(set1.getString(4));
                            txtContact.setText(set1.getString(3));

                            saveOrUpdateMember.setText("Update Member");
                            return;
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                observableList.add(userTm);
            }
            tblContext.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void saveMemberOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setUserId(txtMemberId.getText());
        user.setName(txtName.getText());
        user.setContact(Integer.parseInt(txtContact.getText()));
        user.setEmail(txtEmail.getText());
        user.setPassword("");
        user.setRole("MEMBER");

        if (saveOrUpdateMember.getText().equalsIgnoreCase("Save Member")){

            if (DatabaseAccessCode.saveMember(user)){
                loadMemberTable();
                new Alert(Alert.AlertType.INFORMATION,"Saved User!",ButtonType.OK).show();

            }else {
                new Alert(Alert.AlertType.ERROR,"Something went Wrong!",ButtonType.OK).show();
            }
        }else {
            if (DatabaseAccessCode.updateMemberById(user)){
                new Alert(Alert.AlertType.INFORMATION,"Updated!!",ButtonType.OK).show();
                loadMemberTable();
                saveOrUpdateMember.setText("Save Member");
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong",ButtonType.OK).show();
            }
        }
    }
    private void setID(){
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); // Remove non-numeric characters
        String fourDigits = uuid.substring(0, 4);
        txtMemberId.setText(fourDigits);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }
    private void setUi(String location) throws IOException {
        URL resource = getClass().getResource("/view/" + location + ".fxml");
        Stage stage = (Stage) memberContext.getScene().getWindow();
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }
    public void clear(){
        txtMemberId.clear();
        txtName.clear();
        txtEmail.clear();
        txtContact.clear();
    }
}
