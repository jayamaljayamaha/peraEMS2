package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class medicneRecieptController implements Initializable {

    ResultSet results = null;

    @FXML
    TextField medicineName, medicineDosage, numOfDays;
    @FXML
    TableView<medicineReceiptItem> medicineListTable;
    @FXML
    TableColumn<medicineReceiptItem, String> nameColumn, dosageColumn, daysColumn;
    @FXML
    Button medicineReciptOkButton;

    private String index;

    public void setIndex(String index){
        this.index=index;
    }


    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("here");
        dataModel model = new dataModel();
        model.connectDataBase();
        results = model.getAllData("medicines");
        String[] arr = {"paracitamol","aspirin", "antibiotics"};
        ArrayList<String> medicineNames = new ArrayList<String>();
        try {
            while(results.next()){
                medicineNames.add(results.getString("Name"));
            }

            TextFields.bindAutoCompletion(medicineName,medicineNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMedicineButton(ActionEvent event){

        String name = medicineName.getText();
        String dosage = medicineDosage.getText();
        String days = numOfDays.getText();

        nameColumn.setCellValueFactory(new PropertyValueFactory<medicineReceiptItem, String>("name"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<medicineReceiptItem, String>("dosage"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<medicineReceiptItem, String>("days"));

        medicineReceiptItem item = new medicineReceiptItem(name,dosage,days);
        medicineListTable.getItems().add(item);

        medicineName.setText("");
        medicineDosage.setText("");
        numOfDays.setText("");

    }

    public void deleteMedicine(ActionEvent event){

        medicineReceiptItem selectedItem = medicineListTable.getSelectionModel().getSelectedItem();
        medicineListTable.getItems().remove(selectedItem);

    }

    public void medicineReciptOK(ActionEvent event){

        medicineReceiptItem items = new medicineReceiptItem();
        dataModel datamodel = new dataModel();
        datamodel.connectDataBase();
       // String medicineRecieptId =

        for(int i=0; i<medicineListTable.getItems().size(); i++){
            items = medicineListTable.getItems().get(i);

        }

        Stage stage = (Stage) medicineReciptOkButton.getScene().getWindow();
        stage.close();

    }
}
