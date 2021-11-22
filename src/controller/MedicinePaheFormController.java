package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Company;
import model.Medicine;
import util.Controller.CompanyController;
import util.Controller.MedicineController;
import view.tm.CompanyTM;
import view.tm.MedicineTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class MedicinePaheFormController {
    public AnchorPane root;
    public DatePicker txtMadeDate;
    public DatePicker txtxExpDate;
    /*public ComboBox cmbCompany;*/
    public Button btnAdd;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnClear;
    public TableView<MedicineTM> tblMedicine;
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
    public TextField txtCompany;

    private MedicineController controller=new MedicineController();

    public void initTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPack.setCellValueFactory(new PropertyValueFactory<>("pack"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colMadeDate.setCellValueFactory(new PropertyValueFactory<>("madeDate"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

        try {
            setMedicineToTable(controller.getAllMedicine());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void clear(){
        textId.clear();
        textName.clear();
        txtPack.clear();
        txtQty.clear();
        txtMadeDate.getEditor().clear();
        txtxExpDate.getEditor().clear();
       /* cmbCompany.getSelectionModel().clearSelection();*/
        txtCompany.clear();
    }

    private void setMedicineToTable(ArrayList<Medicine> allMedicine) {
        ObservableList<MedicineTM> obList = FXCollections.observableArrayList();
        allMedicine.forEach(e->{
            obList.add(
                    new MedicineTM(e.getId(),e.getName(),e.getPack(),e.getQty(),e.getMadeDate(),e.getExpDate(),e.getCompany()));
        });
        tblMedicine.setItems(obList);

    }

    public void Update_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        Medicine m1= new Medicine(
                textId.getText(),textName.getText(),txtPack.getText(),Integer.parseInt(txtQty.getText()),txtMadeDate.getValue().toString(),txtxExpDate.getValue().toString(),txtCompany.getText()
        );

        if (controller.updateMedicine(m1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();

            setMedicineToTable(controller.getAllMedicine());
            clear();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void Delete_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you suer you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            if (controller.deleteMedicine(textId.getText())) ;

            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();

            clear();
            setMedicineToTable(controller.getAllMedicine());
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            clear();
        }
    }

    public void Add_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Medicine m1= new Medicine(
                textId.getText(),textName.getText(),txtPack.getText(),Integer.parseInt(txtQty.getText()),txtMadeDate.getValue().toString(),txtxExpDate.getValue().toString(),txtCompany.getText()
        );

        if(controller.saveMedicine(m1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

            setMedicineToTable(controller.getAllMedicine());
            clear();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
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

    public void txtSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        String medicineId = textId.getText();

        Medicine m1= new MedicineController().getMedicine(medicineId);
        if (m1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(m1);
        }
    }

    private void setData(Medicine m1) {
        textId.setText(m1.getId());
        textName.setText(m1.getName());
        txtPack.setText(m1.getPack());
        txtQty.setText(String.valueOf(m1.getQty()));
        txtMadeDate.setValue(LocalDate.parse(m1.getMadeDate()));
        txtxExpDate.setValue(LocalDate.parse(m1.getExpDate()));
       /* cmbCompany.setValue(m1.getCompany());*/
        txtCompany.setText(m1.getCompany());
    }
}
