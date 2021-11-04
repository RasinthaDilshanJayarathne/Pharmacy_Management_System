package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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

    public void Clear_On_Action(ActionEvent actionEvent) {
    }

    public void Update_On_Action(ActionEvent actionEvent) {
    }

    public void Delete_On_Action(ActionEvent actionEvent) {
    }

    public void Add_On_Action(ActionEvent actionEvent) {
    }
}
