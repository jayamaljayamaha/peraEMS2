package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Pane navPane;

    @FXML
    AnchorPane signUpPanel;


    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loadSignUp(ActionEvent event) throws IOException {

        signUpPanel = FXMLLoader.load(getClass().getResource("/newUser.fxml"));
        navPane.getChildren().clear();
        navPane.getChildren().add(signUpPanel);
        double x = navPane.getLayoutX();
        double widthNavPane = navPane.getWidth();
        double widthSignUpPanel = signUpPanel.getPrefWidth();
        double posX =(widthNavPane-widthSignUpPanel)/2;
        signUpPanel.setLayoutX(posX);
    }

}
