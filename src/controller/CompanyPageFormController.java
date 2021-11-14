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
import model.Agent;
import model.Company;
import util.Controller.AgentController;
import util.Controller.CompanyController;
import view.tm.AgentTM;
import view.tm.CompanyTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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

    private CompanyController controller=new CompanyController();

    public void initialize(){
        initTable();
    }

    public void initTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("Cid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        try {
            setCompanyToTable(controller.getAllCompany());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void Clear_On_Action(ActionEvent actionEvent) {
        textId.clear();
        textName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void clear(){
        textId.clear();
        textName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    private void setCompanyToTable(ArrayList<Company> allCompanies) {
        ObservableList<CompanyTM> obList = FXCollections.observableArrayList();
        allCompanies.forEach(e->{
            obList.add(
                    new CompanyTM(e.getCid(),e.getName(),e.getAddress(),e.getContact()));
        });
        tblCompany.setItems(obList);

    }

    public void Update_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        Company a1= new Company(
                textId.getText(),textName.getText(),txtAddress.getText(),txtContact.getText()
        );

        if (controller.updateCompany(a1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();

            setCompanyToTable(controller.getAllCompany());
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
            if (controller.deleteCompany(textId.getText())) ;

            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();

            clear();
            setCompanyToTable(controller.getAllCompany());
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            clear();
        }
    }

    public void Add_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Company c1 = new Company(
                textId.getText(),textName.getText(),txtAddress.getText(),txtContact.getText()
        );

        if(controller.saveCompany(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

            setCompanyToTable(controller.getAllCompany());
            clear();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
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

    public void txtSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        String companyId = textId.getText();

        Company c1= new CompanyController().getCompany(companyId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    private void setData(Company c1) {
        textId.setText(c1.getCid());
        textName.setText(c1.getName());
        txtContact.setText(c1.getContact());
        txtAddress.setText(c1.getAddress());
    }
}
