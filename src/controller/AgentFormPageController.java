package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import util.Controller.AgentController;
import view.tm.AgentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class AgentFormPageController {
    public AnchorPane root;
    public TextField textId;
    public TextField textName;
    public Button btnAdd;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnClear;
    public ComboBox cmbGender;
    public TableView<AgentTM> tblAgent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colAge;
    public TableColumn colGender;
    public TextField txtAge;
    public TextField txtContact;

    private AgentController controller=new AgentController();

    public void initialize(){
        cmbGender.getItems().addAll(
                "Male", "Female"
        );
        initTable();

    }

    public void initTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("Aid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        try {
            setAgentToTable(controller.getAllAgent());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setAgentToTable(ArrayList<Agent> allBloods) {
        ObservableList<AgentTM> obList = FXCollections.observableArrayList();
        allBloods.forEach(e->{
            obList.add(
                    new AgentTM(e.getAid(),e.getName(),e.getAge(),e.getContact(),e.getGender()));
        });
        tblAgent.setItems(obList);

    }

    public void Clear_On_Action(ActionEvent actionEvent) {
        textId.clear();
        txtAge.clear();
        textName.clear();
        txtContact.clear();
        cmbGender.getSelectionModel().clearSelection();
    }

    public void clear(){
        textId.clear();
        txtAge.clear();
        textName.clear();
        txtContact.clear();
        cmbGender.getSelectionModel().clearSelection();
    }

    public void Update_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        Agent a1= new Agent(
                textId.getText(),textName.getText(),Integer.parseInt(txtAge.getText()),txtContact.getText(),String.valueOf(cmbGender.getValue())
        );

        if (controller.updateAgent(a1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();

            setAgentToTable(controller.getAllAgent());
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
            if (controller.deleteAgent(textId.getText())) ;

            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();

            clear();
            setAgentToTable(controller.getAllAgent());
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
           clear();
        }
    }

    public void Add_On_Action(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Agent b1 = new Agent(
                textId.getText(),textName.getText(),Integer.parseInt(txtAge.getText()),txtContact.getText(),String.valueOf(cmbGender.getValue())
        );

        if(controller.saveAgent(b1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

            setAgentToTable(controller.getAllAgent());
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

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }

    public void txtSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        String agentId = textId.getText();

        Agent a1= new AgentController().getAgent(agentId);
        if (a1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(a1);
        }
    }

    private void setData(Agent a) {
        textId.setText(a.getAid());
        textName.setText(a.getName());
        txtAge.setText(String.valueOf(a.getAge()));
        txtContact.setText(a.getContact());
        cmbGender.setValue(a.getGender());
    }
}
