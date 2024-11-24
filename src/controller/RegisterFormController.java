package controller;

import db.CrudUtil;
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

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RegisterFormController {
    public BorderPane regContext;
    public TextField txtName;
    public PasswordField txtPassword;
    public TextField txtEmail;
    public TextField txtContact;

    public void registerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        if (txtName.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Please Enter Your Name", ButtonType.OK).show();
            return;
        } else if (txtEmail.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Please Enter Your Email", ButtonType.OK).show();
            return;
        }  else if (txtContact.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Please Enter Your Contact", ButtonType.OK).show();
            return;
        } else if (txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Please Enter Password", ButtonType.OK).show();
            return;
        }

        ResultSet set = CrudUtil.execute("SELECT * FROM user WHERE email=? OR contact=?" , txtEmail.getText(),Integer.parseInt(txtContact.getText())) ;
        if (set.next()){
            new Alert(Alert.AlertType.WARNING,"User email or contact number already in use.",ButtonType.OK).show();
            return;
        }
        User user = new User(setID(),txtName.getText(),Integer.parseInt(txtContact.getText()),txtEmail.getText(),PasswordEncoder.encode(txtPassword.getText()),"TRAINER");
        CrudUtil.execute("INSERT INTO user (`userId`,`name`,`contact`,`email`,`password`,`role`)  values(?,?,?,?,?,?)",user.getUserId(),user.getName(),user.getContact(),user.getEmail(),user.getPassword(),user.getRole());
        setUi("LoginForm");
    }

    private String setID(){
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); // Remove non-numeric characters
        return uuid.substring(0, 4);

    }
    public void haveAccOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    private void setUi(String location) throws IOException {
        URL resource = getClass().getResource("/view/" + location + ".fxml");
        Stage stage = (Stage) regContext.getScene().getWindow();
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }
}
