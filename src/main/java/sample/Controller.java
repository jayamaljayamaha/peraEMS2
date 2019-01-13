package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    Button newPatientButton, registerStudentButton, newPatientSearchButton;
    @FXML
    TextField userNameLogin, newPatientPatientID,newPatientPatientName,newPatientPatientFac;
    @FXML
    PasswordField passwordLogin;
    @FXML
    VBox sideButtons;
    @FXML
    AnchorPane loginPanel, homePage, registerPatientPane, signUpPanel, newPatientPanel;

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

                //Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                System.out.println(cal.get(Calendar.DAY_OF_MONTH));
                System.out.println(cal.get(Calendar.MINUTE));
                System.out.println(cal.get(Calendar.SECOND));

                //String date = localDate.getDayOfMonth();;

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
}
