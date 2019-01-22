package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class newThreatmentController implements Initializable {

    @FXML
    TextField newThreatementThreatmentID, newThretmentPatientName, newTreatementPatientFac, newThretmentPatientHall,
            newThretmentPatientAge;

    @FXML
    TextArea newThretmentReason, newThretmentAboutThreatment;

    @FXML
    Button medicineRecieptButton;
    @FXML
    AnchorPane newPatientPanel;

    String index;
    ResultSet results = null;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void searchThretment(ActionEvent event){

        String currentThreatmentId = newThreatementThreatmentID.getText();
        System.out.println(currentThreatmentId);

        dataModel datamodel = new dataModel();
        datamodel.connectDataBase();
        results = datamodel.searchData("currentthreatments","threatmentId",currentThreatmentId);

        try {

            if(!results.next()){
                System.out.println("Invalid Threatment ID");
            }
            else{
                index = results.getString("indexNo");
                newThretmentPatientName.setText(results.getString("patientName"));
                newTreatementPatientFac.setText(results.getString("faculty"));
                newThretmentPatientHall.setText(results.getString("residenceHall"));
                newThretmentPatientAge.setText(results.getString("age"));
                newThretmentReason.setText(results.getString("reason"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void medicineReciptAction(ActionEvent event){

        medicneRecieptController mrc = new medicneRecieptController();
        mrc.setIndex(index);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/newMedicineRecipt.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            navPane.getChildren().clear();
//            navPane.getChildren().add(newThretmentPanel);
//            double widthNavPane = navPane.getWidth();
//            double widthSignUpPanel = newThretmentPanel.getPrefWidth();
//            double posX =(widthNavPane-widthSignUpPanel)/2;
//            newThretmentPanel.setLayoutX(posX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void issueMedicalAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/newMedical.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            navPane.getChildren().clear();
//            navPane.getChildren().add(newThretmentPanel);
//            double widthNavPane = navPane.getWidth();
//            double widthSignUpPanel = newThretmentPanel.getPrefWidth();
//            double posX =(widthNavPane-widthSignUpPanel)/2;
//            newThretmentPanel.setLayoutX(posX);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void newThretmentSubmit(){}

    public void toUpperCase(){}
}
