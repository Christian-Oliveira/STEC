package stec.view.main;

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
import stec.view.formularios.FormSupervisao.FormSupervisaoController;

public class MainController implements Initializable {

    //instancia a classe da tabela de supervisoes
    private final SupervisoesController tabela = new SupervisoesController();

    //objeto DAO responsavel pela interacao com o bd
    private final SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

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
    @FXML
    public AnchorPane content;
    @FXML
    public AnchorPane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {             
        try {
            loadContent(FXMLLoader.load(getClass().getResource("/stec/view/main/Supervisoes.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpa a o AnchorPane e carrega o conteudo do botao selecionado.
     * Aplica uma transicao entre um conteudo e outro
     */
    public void loadContent(Node node) {
        content.getChildren().clear();
        content.getChildren().add((Node) node);
        
        //Faz com que a janela filha preencha completamente as dimensoes do pai
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    /**
     * Função que abre o formulario de nova supervisao e
     * ao confirmar insere no BD
     */
    @FXML
    private void handleNovaSupervisao(ActionEvent event) throws IOException {
        //Instancia um novo objeto 
        Supervisao supervisao = new Supervisao();

        //Abre o formulario de nova supervisao
        boolean btConfirmaClicked = showFormNovaSupervisao(supervisao);
        
        //Ao confirmar inseri no BD o objeto com as informacoes preenchidas
        if (btConfirmaClicked) {
            supervisaoDAO.inserir(supervisao);
            
            //Recarrega a tabela de supervisoes
            loadContent(FXMLLoader.load(getClass().getResource("/stec/view/main/Supervisoes.fxml")));
        }
    }
    
    /**
     * Cria a janela de nova Supervisao
     */
    public boolean showFormNovaSupervisao(Supervisao novaSupervisao) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(FormSupervisaoController.class.getResource("/stec/view/formularios/FormSupervisao/FormSupervisao.fxml"));
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

    /**
     * Carrega a view da tabela com as supervisões
     */
    @FXML
    private void handleSupervisoes(ActionEvent event) throws IOException {
        loadContent(FXMLLoader.load(getClass().getResource("/stec/view/main/Supervisoes.fxml")));
    }
    
    /**
     * Carrega a view da tabela com as supervisoes importadas
     */
    @FXML
    private void handleRelatorios(ActionEvent event) throws IOException {
        loadContent(FXMLLoader.load(getClass().getResource("/stec/view/Relatorios.fxml")));
    }
    
    /**
     * Realiza o logout do sistema
     */
    @FXML
    private void handleLogout(ActionEvent event) {

    }
    
    /**
     * Fecha a aplicacao
     */
    @FXML
    private void handleSair(ActionEvent event) {
        Platform.exit();
    }
}
