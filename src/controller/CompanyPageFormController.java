package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CompanyPageFormController {
    public AnchorPane root;
    public Button btnAdd;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnClear;
    public TableView tblCompany;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TextField textId;
    public TextField txtAddress;
    public TextField textName;
    public TextField txtContact;

    public void Clear_On_Action(ActionEvent actionEvent) {
    }

    public void Update_On_Action(ActionEvent actionEvent) {
    }

    public void Delete_On_Action(ActionEvent actionEvent) {
    }

    public void Add_On_Action(ActionEvent actionEvent) {
    }

    public void Medicine_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MedicinePaheForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void Agents_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AgentFormPage.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void Setting_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SettingPageForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }
}
