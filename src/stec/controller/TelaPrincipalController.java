package stec.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.Supervisao;

public class TelaPrincipalController implements Initializable {
    
    private final TabelaSupervisoesController tabela = new TabelaSupervisoesController(); //instancia a classe da tabela de supervisoes
    private final SupervisaoDAO supervisaoDAO = new SupervisaoDAO();//objeto DAO responsavel pela interacao com o bd

    @FXML
    private AnchorPane anchorPaneDashboard;
    @FXML
    private JFXButton btnNovaSupervisao;
    @FXML
    private JFXButton btnSupervisoes;
    @FXML
    private JFXButton btnRelatorios;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnSair;
    
    private AnchorPane listSupervisoes, supervisao, relatorios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createPages();
    }
    
    //envia o conteudo do botão selecionado
    private void setNode(Node node) {
        anchorPaneDashboard.getChildren().clear();
        anchorPaneDashboard.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    //carrega todos os arquivos fxml
    private void createPages() {
        try {
            listSupervisoes = FXMLLoader.load(getClass().getResource("/stec/view/TabelaSupervisoes.fxml"));
            supervisao = FXMLLoader.load(getClass().getResource("/stec/view/Supervisao.fxml"));
            relatorios = FXMLLoader.load(getClass().getResource("/stec/view/Relatorios.fxml"));

            //set up default node on page load
            setNode(listSupervisoes);
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Função que instância uma nova supervisão
    @FXML
    private void handleNovaSupervisao(ActionEvent event) throws IOException {
        //cria um objeto limpo
        Supervisao novaSupervisao = new Supervisao();

        //passa o objeto para o formulario
        boolean btConfirmaClicked = showFormNovaSupervisao(novaSupervisao);

        if (btConfirmaClicked) {
            supervisaoDAO.inserir(novaSupervisao);
            tabela.carregarTabelaSupervisoes();
        }
    }
    //Função que mostra a tela para criar uma nova supervisão
    public boolean showFormNovaSupervisao(Supervisao novaSupervisao) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(FormSupervisaoController.class.getResource("/stec/view/formularios/FormSupervisao.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Nova Supervisão");
        stage.setResizable(false);
        Scene scene = new Scene(pane);
        stage.setScene(scene);

        FormSupervisaoController controller = loader.getController();
        controller.setDialog(stage);
        controller.setSupervisao(novaSupervisao);

        stage.showAndWait();

        return controller.isBtConfirmarClicked();
    }
    
    //Função que retona a tabela com as supervisões
    @FXML
    private void handleSupervisoes(ActionEvent event) throws IOException{
        setNode(listSupervisoes);
    }
    
    @FXML
    private void handleRelatorios(ActionEvent event){
        setNode(relatorios);
    }
    
    @FXML
    private void handleLogout(ActionEvent event) {
        
    }

    @FXML
    private void handleSair(ActionEvent event) {
        Platform.exit();
    }
}
