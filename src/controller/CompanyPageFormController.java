package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Agent;
import model.Company;
import util.Controller.AgentController;
import util.Controller.CompanyController;
import util.Validation;
import view.tm.AgentTM;
import view.tm.CompanyTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

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
        storeValidations();
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

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern companyIDPattern = Pattern.compile("^(A00)[-]?[0-9]{4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern phoneNoPattern = Pattern.compile("^[0-9]{3}[-]?[0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");

    private void storeValidations() {
        map.put(textId, companyIDPattern);
        map.put(textName, namePattern);
        map.put(txtContact,phoneNoPattern);
        map.put(txtAddress,addressPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = Validation.validate(map,btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
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
