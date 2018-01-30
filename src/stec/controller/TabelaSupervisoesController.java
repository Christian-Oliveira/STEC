package stec.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stec.model.dao.RespostaDAO;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.RelatorioSupervisao;
import stec.model.domain.Supervisao;
import stec.model.domain.Ur;
import stec.resources.Classes.AlertMaker;

public class TabelaSupervisoesController implements Initializable {
    List<Supervisao> listSupervisoes;//Lista das supervisoes
    ObservableList<Supervisao> observableListSupervisoes;//Observable list das supervisoes
    private final SupervisaoDAO supervisaoDAO = new SupervisaoDAO();//objeto DAO responsavel pela interacao com o bd
    
    @FXML
    private AnchorPane anchorPaneTabela;
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
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabelaSupervisoes();

    }
    
    //Função do botão visualizar para preencher uma supervisão
    @FXML
    private void handleVisualizar(ActionEvent event) throws IOException {

        Supervisao supervisao = tViewSupervisoes.getSelectionModel().getSelectedItem();//Instancia um objeto com os dados da supervisao selecionada

        if (supervisao == null) {
            AlertMaker.showErrorMessage("Aviso", "Por favor, selecione uma supervisão.");
        } else {
            if (supervisao.isStatus()) {//se a supervisao tiver finalizada
                AlertMaker.showSimpleAlert("Aviso", "Será gerado um PDF da supervisão");
                RelatorioSupervisao relatorio = new RelatorioSupervisao();
                relatorio.setSupervisao(supervisao);

                RespostaDAO respostaDAO = new RespostaDAO();

                relatorio.setHashRespostas(respostaDAO.listarRespostasDaSupervisao(supervisao));

                //Nome do pdf da supervisao
                String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                        : supervisao.getUlsav().getNome());
                String data[] = supervisao.getCreated().split(" ");//pega apenas a data

                String file = "Supervisão " + municipio + " " + data[0];

                relatorio.show(file);
            } else {
                showSupervisao(supervisao);//passa o objeto para a janela que vai apresentar os dados
            }

        }
    }
    
    //Função que carrega a tela de Supervisão e apresenta os dados
    public void showSupervisao(Supervisao supervisao) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(FormSupervisaoController.class.getResource("/stec/view/Supervisao.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        
        Stage stage = new Stage();
        stage.setTitle("Supervisão");
        Scene scene = new Scene(pane);
        stage.setMaximized(true);
        stage.setScene(scene);
        
        SupervisaoController controller = loader.getController();
        controller.setDialog(stage);
        controller.setSupervisao(supervisao);

        stage.show();
    }
    
    //Função que finaliza uma supervisão
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
    
    //Função que exclui uma supervisão
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
    
    public void carregarTabelaSupervisoes() {
        tColumnUR.setCellValueFactory(new PropertyValueFactory<>("ur"));
        tColumnMunicipio.setCellValueFactory(new PropertyValueFactory<>("escritorio"));//deve ser exatamente o nome do atributo da classe
        tColumnProgramas.setCellValueFactory(new PropertyValueFactory<>("programas"));
        tColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        listSupervisoes = supervisaoDAO.listar(LoginController.USUARIO);//lista as supervisoes do usuario

        observableListSupervisoes = FXCollections.observableArrayList(listSupervisoes);
        tViewSupervisoes.setItems(observableListSupervisoes);
    }
}
