package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MedicinePaheFormController {
    public AnchorPane root;
    public DatePicker txtMadeDate;
    public DatePicker txtxExpDate;
    public ComboBox cmbCompany;
    public Button btnAdd;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnClear;
    public TableView tblMedicine;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPack;
    public TableColumn colQty;
    public TableColumn colMadeDate;
    public TableColumn colExpDate;
    public TableColumn colCompany;
    public TextField textId;
    public TextField txtQty;
    public TextField txtPack;
    public TextField textName;


    public void Update_On_Action(ActionEvent actionEvent) {
    }

    public void Delete_On_Action(ActionEvent actionEvent) {
    }

    public void Add_On_Action(ActionEvent actionEvent) {
    }

    public void Setting_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SettingPageForm.fxml");
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

    public void Company_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CompanyPageForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }

    public void txtSearch(ActionEvent actionEvent) {

    }
}
