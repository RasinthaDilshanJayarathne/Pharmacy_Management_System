package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginPageFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField textPassword;
    public AnchorPane root;
    public Label lblError;

    public void Login_On_Action(ActionEvent actionEvent) throws IOException {

        String userName=txtUserName.getText();
        String password=textPassword.getText();

        if (userName.equals("Nimal") && password.equals("1234")){
            URL resource = getClass().getResource("../view/MedicinePaheForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) root.getScene().getWindow();
            window.setScene(new Scene(load));
        }else
            lblError.setText("Enter correct username or password");


    }

    public void moveToPassword(ActionEvent actionEvent) {
        textPassword.requestFocus();
    }

    public void Clear_On_Action(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }

}
