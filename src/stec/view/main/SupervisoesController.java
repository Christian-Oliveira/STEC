package stec.view.main;

import stec.view.login.LoginController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import stec.view.supervisao.SupervisaoController;
import stec.model.dao.RespostaDAO;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.RelatorioSupervisao;
import stec.model.domain.Supervisao;
import stec.model.domain.Ur;
import stec.resources.Classes.AlertMaker;

public class SupervisoesController implements Initializable {

    List<Supervisao> listSupervisoes;//Lista das supervisoes
    ObservableList<Supervisao> observableListSupervisoes;//Observable list das supervisoes
    private final SupervisaoDAO supervisaoDAO = new SupervisaoDAO();//objeto DAO responsavel pela interacao com o bd
    public Stage dialog;

    @FXML
    private JFXButton btnVisualizar;
    @FXML
    private JFXButton btnFinalizar;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private TableView<Supervisao> tViewSupervisoes;
    @FXML
    private TableColumn<Supervisao, Ur> tColumnUR;
    @FXML
    private TableColumn<Supervisao, String> tColumnMunicipio;
    @FXML
    private TableColumn<Supervisao, String> tColumnProgramas;
    @FXML
    private TableColumn<Supervisao, Boolean> tColumnStatus;
    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabelaSupervisoes();
    }

    /**
     * Carrega em uma nova janela os dados da supervisao selecionada
     */
    @FXML
    private void handleVisualizar(ActionEvent event) throws IOException {

        Supervisao supervisao = tViewSupervisoes.getSelectionModel().getSelectedItem();
        
        //Caso nao tenha selecionado uma supervisao
        if (supervisao == null) {
            AlertMaker.showErrorMessage("Aviso", "Por favor, selecione uma supervisão.");
        } else {
            //Verifica se a supervisao esta finalizada
            if (supervisao.isStatus()) {
                AlertMaker.showSimpleAlert("Aviso", "Será gerado um PDF da supervisão");
                
                RespostaDAO respostaDAO = new RespostaDAO();
                
                //passa para classe de relatorio o objeto da supervisao e suas respostas
                RelatorioSupervisao relatorio = new RelatorioSupervisao(supervisao, respostaDAO.listarRespostasDaSupervisao(supervisao));

                //Monta o nome do pdf da supervisao
                String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                        : supervisao.getUlsav().getNome());
                String data[] = supervisao.getCreated().split(" ");//pega apenas a data
                String file = "Supervisão " + municipio + " " + data[0];
                                
                relatorio.show(file);
            
            //passa o objeto para a janela que vai apresentar os dados
            } else {
                
                //Muda a tela passando dados
                URL url = getClass().getResource("/stec/view/supervisao/Supervisao.fxml");

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(url);
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                               
                showSupervisao(supervisao, loader.load(url.openStream()));
                
                ((SupervisaoController)loader.getController()).setSupervisao(supervisao);
            }

        }
    }
       
    /**
     * Carrega a tela de Supervisão e apresenta os dados
     */
    public void showSupervisao(Supervisao supervisao, Node node) throws IOException {
        
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
     * Função que finaliza uma supervisão
     */
    @FXML
    private void handleFinalizar(ActionEvent event) throws IOException {
        Supervisao supervisao = tViewSupervisoes.getSelectionModel().getSelectedItem();//Instancia um objeto com os dados da supervisao selecionada

        if (supervisao == null) {
            AlertMaker.showErrorMessage("Aviso", "Por favor, selecione uma supervisão.");
        } else {
            if (supervisao.isStatus()) {//se a supervisao tiver finalizada
                AlertMaker.showSimpleAlert("Aviso", "Supervisão já foi finalizada");
            } else {
                supervisaoDAO.finalizar(supervisao);
                carregarTabelaSupervisoes();
            }
        }
    }

    /**
     * Função que exclui uma supervisão
     */
    @FXML
    private void handleExcluir(ActionEvent event) {
        Supervisao supervisao = tViewSupervisoes.getSelectionModel().getSelectedItem();//Instancia um objeto com os dados da supervisao selecionada

        if (supervisao == null) {
            AlertMaker.showErrorMessage("Aviso", "Por favor, selecione uma supervis�o.");
        } else {
            if (supervisao.isStatus()) {//se a supervisao selecionada estiver finalizada
                AlertMaker.showErrorMessage("Aviso", "Não é possível excluir uma supervisão que foi finalizada.");
            } else {

                //Mensagem de confirmação para apagar uma informa��o
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deletar informação");
                alert.setHeaderText(null);
                alert.setContentText("Deseja apagar a supervisão?");

                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    supervisaoDAO.excluir(supervisao);
                }

                carregarTabelaSupervisoes();
            }
        }
    }
    
    /**
     * Carrega as supervisoes do usuario
     */
    public void carregarTabelaSupervisoes() {
        //deve ser exatamente o nome do atributo da classe
        tColumnUR.setCellValueFactory(new PropertyValueFactory<>("ur"));
        tColumnMunicipio.setCellValueFactory(new PropertyValueFactory<>("escritorio"));
        tColumnProgramas.setCellValueFactory(new PropertyValueFactory<>("programas"));
        tColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        //Lista as supervisoes do usuario
        listSupervisoes = supervisaoDAO.listar(LoginController.USUARIO);

        observableListSupervisoes = FXCollections.observableArrayList(listSupervisoes);
        tViewSupervisoes.setItems(observableListSupervisoes);
    }
}
