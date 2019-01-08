package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Pane navPane;
    @FXML
    Button newPatientButton;
    @FXML
    Button registerStudentButton;
    @FXML
    TextField userNameLogin;
    @FXML
    PasswordField passwordLogin;
    @FXML
    VBox sideButtons;
    @FXML
    AnchorPane loginPanel;
    @FXML
    AnchorPane registerPatientPane;
    @FXML
    AnchorPane signUpPanel;
    @FXML
    AnchorPane newPatientPanel;

    Connection connection = null;
    PreparedStatement prepStatment = null;
    ResultSet results = null;

    public Controller(){
       // System.out.println("Here");
    }

    public void initialize(URL location, ResourceBundle resources) {

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/peraems","root","94omalka");
            connection= conn;
            System.out.println(connection);
        }
        catch(Exception e)
        {
            connection= null;
        }
    }

    @FXML
    public void loadSignUp(ActionEvent event) throws IOException {

        String userName = userNameLogin.getText();
        String password = passwordLogin.getText();

        System.out.println(userName);
        System.out.println(password);

        String sql = "SELECT * FROM users WHERE userId = ?";

        try {
            prepStatment = connection.prepareStatement(sql);
            prepStatment.setString(1,userName);
            //prepStatment.setString(2,password);
            results = prepStatment.executeQuery();

            if(!results.next()){
                System.out.println("Invalid user");
            }
            else if(results.getString("password").equals(password)){
                System.out.println("Login Succesfull");
                loginPanel.setVisible(false);
                sideButtons.setVisible(true);
            }
            else{
                System.out.println("Login Unsuccesfull");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



//        signUpPanel = FXMLLoader.load(getClass().getResource("/newUser.fxml"));
//        navPane.getChildren().clear();
//        navPane.getChildren().add(signUpPanel);
//        double x = navPane.getLayoutX();
//        double widthNavPane = navPane.getWidth();
//        double widthSignUpPanel = signUpPanel.getPrefWidth();
//        double posX =(widthNavPane-widthSignUpPanel)/2;
//        signUpPanel.setLayoutX(posX);
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
}
