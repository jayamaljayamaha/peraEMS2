package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Pane navPane;
    @FXML
    Button newPatientButton, registerStudentButton, newPatientSearchButton, newThreatmentButton;
    @FXML
    TextField userNameLogin, newPatientPatientID,newPatientPatientName,newPatientPatientFac,newPatientPatientHall,
            newPatientPatientAge;
    @FXML
    TextArea newPatientPatientReason;
    @FXML
    PasswordField passwordLogin;
    @FXML
    VBox sideButtons;
    @FXML
    AnchorPane loginPanel, homePage, registerPatientPane, signUpPanel, newPatientPanel, newThretmentPanel, newMedicalPanel;

    Connection connection = null;
    PreparedStatement prepStatment = null;
    ResultSet results = null;

    public Controller(){

    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loadSignUp(ActionEvent event) throws IOException {

        String userName = userNameLogin.getText();
        String password = passwordLogin.getText();

        System.out.println(userName);
        System.out.println(password);

        dataModel model = new dataModel();
        model.connectDataBase();
        results = model.searchData("users","userId",userName);


        try {

            if(!results.next()){
                System.out.println("Invalid user");
            }
            else if(results.getString("password").equals(password)){
                System.out.println("Login Succesfull");
                loginPanel.setVisible(false);
                homePage = FXMLLoader.load(getClass().getResource("/homePage.fxml"));
                navPane.getChildren().clear();
                navPane.getChildren().add(homePage);
                double widthNavPane = navPane.getWidth();
                double widthSignUpPanel = homePage.getPrefWidth();
                double posX =(widthNavPane-widthSignUpPanel)/2;
                homePage.setLayoutX(posX);
                sideButtons.setVisible(true);
            }
            else{
                System.out.println("Login Unsuccesfull");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.closeConnection();
    }

    @FXML
    public void openNewPatientPanel(ActionEvent event) throws IOException {

        newPatientPanel = FXMLLoader.load(getClass().getResource("/newPatnt.fxml"));
        navPane.getChildren().clear();
        navPane.getChildren().add(newPatientPanel);
        double widthNavPane = navPane.getWidth();
        double widthSignUpPanel = newPatientPanel.getPrefWidth();
        double posX =(widthNavPane-widthSignUpPanel)/2;
        newPatientPanel.setLayoutX(posX);

    }

    @FXML
    public void openRegStudentPanel(ActionEvent event){

        try {
            registerPatientPane = FXMLLoader.load(getClass().getResource("/registerStudent.fxml"));
            navPane.getChildren().clear();
            navPane.getChildren().add(registerPatientPane);
            double widthNavPane = navPane.getWidth();
            double widthSignUpPanel = registerPatientPane.getPrefWidth();
            double posX =(widthNavPane-widthSignUpPanel)/2;
            registerPatientPane.setLayoutX(posX);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void searchDetails(ActionEvent event){



    }

    @FXML
    public void searchPatient(ActionEvent event){
        System.out.println(navPane);
        String patientID = newPatientPatientID.getText();

        dataModel model = new dataModel();
        model.connectDataBase();
        results = model.searchData("students","regNum",patientID);

        try {

            if(!results.next()){
                System.out.println("Student doesn't exist");
            }
            else{
                newPatientPatientName.setText(results.getString("studentName"));
                newPatientPatientFac.setText(results.getString("faculty"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void toUpperCase(KeyEvent evt){

        newPatientPatientID.setText(newPatientPatientID.getText().toUpperCase());
        newPatientPatientID.positionCaret(newPatientPatientID.getText().length());
    }

    @FXML
    public void createNewThreatment(ActionEvent event){

        String indexNo = newPatientPatientID.getText();
        String name = newPatientPatientName.getText();
        String fac = newPatientPatientFac.getText();
        String hall = newPatientPatientHall.getText();
        String age = newPatientPatientAge.getText();
        String reason = newPatientPatientReason.getText();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        String threatmentID = indexNo+cal.get(Calendar.DAY_OF_MONTH)+cal.get(Calendar.HOUR)+cal.get(Calendar.MINUTE);
        System.out.println(threatmentID);

        String[] keys ={"threatmentId", "indexNo","patientName","faculty","residenceHall","age","reason"};
        String[] values = {threatmentID,indexNo,name,fac,hall,age,reason};
        //Object[] values2={threatmentID,indexNo,name,fac,hall,Integer.parseInt(age),reason};


        System.out.println(navPane);
       // newPatientPanel.setVisible(false);
        //showHomePage();

        dataModel model = new dataModel();
        model.connectDataBase();
        model.insertData("currentthreatments",keys,values);

        model.closeConnection();
        TextField textArea = new TextField(threatmentID);
        textArea.setEditable(false);
        textArea.setMinWidth(300);
        //textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Threatment ID");
        alert.setHeaderText("Threatment ID is ");
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();





    }

    public void newThreatment(ActionEvent event){


        try {
            newThretmentPanel = FXMLLoader.load(getClass().getResource("/newThreatment.fxml"));
            navPane.getChildren().clear();
            navPane.getChildren().add(newThretmentPanel);
            double widthNavPane = navPane.getWidth();
            double widthSignUpPanel = newThretmentPanel.getPrefWidth();
            double posX =(widthNavPane-widthSignUpPanel)/2;
            newThretmentPanel.setLayoutX(posX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHomePage(){

        try {
            homePage = FXMLLoader.load(getClass().getResource("/homePage.fxml"));
            navPane.getChildren().clear();
            navPane.getChildren().add(homePage);
            double widthNavPane = navPane.getWidth();
            double widthSignUpPanel = homePage.getPrefWidth();
            double posX =(widthNavPane-widthSignUpPanel)/2;
            homePage.setLayoutX(posX);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void issueMedicalAction(){
        try {
            newMedicalPanel = FXMLLoader.load(getClass().getResource("/newMedical.fxml"));
            navPane.getChildren().clear();
            navPane.getChildren().add(newMedicalPanel);
            double widthNavPane = navPane.getWidth();
            double widthSignUpPanel = newMedicalPanel.getPrefWidth();
            double posX =(widthNavPane-widthSignUpPanel)/2;
            newMedicalPanel.setLayoutX(posX);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
