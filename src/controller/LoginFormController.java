package controller;

import db.CrudUtil;
import db.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import util.PasswordEncoder;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public BorderPane loginContext;
    public PasswordField txtPassword;
    public TextField txtEmail;

    public void LoginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (txtEmail.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please enter your email", ButtonType.OK).show();
            return;
        } else if (txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Please enter your password", ButtonType.OK).show();
            return;
        }
        String email = txtEmail.getText();
        ResultSet set = CrudUtil.execute("SELECT * FROM user WHERE email=?", email);


        if (set.next()){
           if ( PasswordEncoder.check(txtPassword.getText(),set.getString("password"))){
               Utilities.user= new User(
                       set.getString("userId"),
                       set.getString("name"),
                       set.getInt("contact"),
                       set.getString("email"),
                       set.getString("password"),
                       set.getString("role"));
               URL resource = getClass().getResource("/view/" + "DashboardForm" + ".fxml");
               Stage stage = (Stage) loginContext.getScene().getWindow();
               Parent parent = FXMLLoader.load(resource);
               Scene scene = new Scene(parent);
               stage.setScene(scene);
               stage.setMaximized(true);

           }else {
               new Alert(Alert.AlertType.WARNING,"Wrong Password!!", ButtonType.OK).show();
               return;
           }
        }else {
            new Alert(Alert.AlertType.WARNING,"User email not found!", ButtonType.OK).show();
        }

    }

    public void dontHaveAccOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RegisterForm");
    }

    private void setUi(String location) throws IOException {
        URL resource = getClass().getResource("/view/" + location + ".fxml");
        Stage stage = (Stage) loginContext.getScene().getWindow();
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
