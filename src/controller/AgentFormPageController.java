package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AgentFormPageController {
    public AnchorPane root;
    public TextField textId;
    public TextField txtQty;
    public TextField textName;
    public TextField txtPack;
    public Button btnAdd;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnClear;
    public ComboBox cmbGender;
    public TableView tblAgent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colAge;
    public TableColumn colGender;

    public void Clear_On_Action(ActionEvent actionEvent) {
    }

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

    public void Medicine_On_Action(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MedicinePaheForm.fxml");
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
}
