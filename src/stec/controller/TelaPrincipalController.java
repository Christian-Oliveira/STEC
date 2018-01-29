package stec.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class TelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane anchorPaneDashboard;
    @FXML
    private JFXButton btnSupervisoes;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void handleSupervisoes(ActionEvent event) throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/stec/view/TabelaSupervisoes.fxml"));
        anchorPaneDashboard.getChildren().setAll(a);
    }
    
    @FXML
    private void handleLogout(ActionEvent event) {
        
    }

    @FXML
    private void handleSair(ActionEvent event) {
        Platform.exit();
    }
}
