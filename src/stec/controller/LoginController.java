package stec.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import stec.model.dao.UsuarioDAO;
import stec.model.domain.Usuario;
import stec.resources.Classes.AlertMaker;

public class LoginController implements Initializable {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    //Constante que armazena o usuario logado e suas informacoes
    public static final Usuario USUARIO = new Usuario();
    
    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField senha;
    @FXML
    private JFXButton btLogin;
    @FXML
    private AnchorPane LoginPane;
    @FXML
    private ImageView imgProgress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgProgress.setVisible(false);
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
        imgProgress.setVisible(true);
        Usuario usuario = new Usuario();//instanciando novo usuario
        
        usuario.setLogin(login.getText());
        usuario.setSenha(senha.getText());
        
        if (usuarioDAO.login(usuario)) {
            //Passa o id e nome do usuario para a constante
            USUARIO.setId(usuario.getId());
            USUARIO.setNome(usuario.getNome());
            
            //Fecha a tela de login
            ((Stage) LoginPane.getScene().getWindow()).close();
            
            //Carrega a tela principal
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/stec/view/Main.fxml"));
                
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("STEC - Sistema de Supervisão Técnica AGED");
                stage.setScene(new Scene(parent));
                stage.setMaximized(true);
                stage.show();         
                
                //caso a janela principal seja fechada encerra a aplicacao
                stage.setOnCloseRequest(e -> Platform.exit());
            } catch (Exception e) {
                AlertMaker.showErrorMessage("Error", e.getMessage());
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            AlertMaker.showSimpleAlert("Falha", "Login ou senha incorretos");
        }
    }
}