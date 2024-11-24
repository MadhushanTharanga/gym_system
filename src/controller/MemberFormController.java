package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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

    public void addNewMemberOnAction(ActionEvent actionEvent) {
    }

    public void resetOnAction(ActionEvent actionEvent) {
    }

    public void saveMemberOnAction(ActionEvent actionEvent) {
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
}
