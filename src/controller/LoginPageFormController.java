package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;

public class LoginPageFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField textPassword;

    public void moveToPassword(ActionEvent actionEvent) {
        textPassword.requestFocus();
    }

    public void Clear_On_Action(ActionEvent actionEvent) {
    }

    public void Login_On_Action(ActionEvent actionEvent) {
    }
}
