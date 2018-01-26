package stec.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.Supervisao;
import stec.resources.Classes.AlertMaker;

public class MainController implements Initializable {

    List<Supervisao> listSupervisoes;//Lista das supervisoes
    ObservableList<Supervisao> observableListSupervisoes;//Observable list das supervisoes
    private final SupervisaoDAO supervisaoDAO = new SupervisaoDAO();//objeto DAO responsavel pela interacao com o bd

    @FXML
    private AnchorPane MainPane;
    @FXML
    private AnchorPane anchorPaneSupervisoes;
    @FXML
    private MenuItem miNovaSupervisao;
    @FXML
    private MenuItem miNovaSupervisaoPFFA;
    @FXML
    private MenuItem miRelatorios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //carregarTabelaSupervisoes();
    }
    
    @FXML
    private void handleMINovaSupervisao(ActionEvent event) throws IOException {
        //cria um objeto limpo
        Supervisao supervisao = new Supervisao();

        //passa o objeto para o formulario
        boolean btConfirmaClicked = showFormNovaSupervisao(supervisao);

        if (btConfirmaClicked) {
            supervisaoDAO.inserir(supervisao);
            //carregarTabelaSupervisoes();
        }
    }

    @FXML
    private void handleMIRelatorios() throws IOException {
        //Fecha a tela principal
        ((Stage) MainPane.getScene().getWindow()).close();

        //Carrega a tela principal
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/stec/view/Relatorios.fxml"));

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
    }

    @FXML
    private void handleMINovaSupervisaoPFFA() throws IOException {
        //cria um objeto limpo
        Supervisao supervisao = new Supervisao();

        //passa o objeto para o formulario
        boolean btConfirmaClicked = showFormNovaSupervisaoPFFA(supervisao);

        if (btConfirmaClicked) {
            supervisaoDAO.inserir(supervisao);
            //carregarTabelaSupervisoes();
        }
    }

    public boolean showFormNovaSupervisao(Supervisao supervisao) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(FormSupervisaoController.class.getResource("/stec/view/formularios/FormSupervisao.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        stage.setTitle("Nova Supervisão");
        Scene scene = new Scene(pane);
        stage.setResizable(false);
        stage.setScene(scene);

        FormSupervisaoController controller = loader.getController();
        controller.setDialog(stage);
        controller.setSupervisao(supervisao);

        stage.showAndWait();

        return controller.isBtConfirmarClicked();
    }
    //falta
    public boolean showFormNovaSupervisaoPFFA(Supervisao supervisao) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(FormSupervisaoController.class.getResource("/stec/view/formularios/FormSupervisao.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        stage.setTitle("Nova Supervisão");
        Scene scene = new Scene(pane);
        stage.setResizable(false);
        stage.setScene(scene);

        FormSupervisaoController controller = loader.getController();
        controller.setDialog(stage);
        controller.setSupervisao(supervisao);

        stage.showAndWait();

        return controller.isBtConfirmarClicked();
    }
    /**
    public void carregarTabelaSupervisoes() {
        colunaUr.setCellValueFactory(new PropertyValueFactory<>("ur"));
        colunaEscritorioMunicipio.setCellValueFactory(new PropertyValueFactory<>("escritorio"));//deve ser exatamente o nome do atributo da classe
        colunaProgramas.setCellValueFactory(new PropertyValueFactory<>("programas"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        listSupervisoes = supervisaoDAO.listar(LoginController.USUARIO);//lista as supervisoes do usuario

        observableListSupervisoes = FXCollections.observableArrayList(listSupervisoes);
        tabelaSupervisoes.setItems(observableListSupervisoes);
    }

    @FXML
    private void handleVisualizar(ActionEvent event) throws IOException {

        Supervisao supervisao = tabelaSupervisoes.getSelectionModel().getSelectedItem();//Instancia um objeto com os dados da supervisao selecionada

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

    public void showSupervisao(Supervisao supervisao) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(TelaController.class.getResource("/stec/view/Supervisao.fxml"));
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

    @FXML
    private void handleFinalizar(ActionEvent event) throws IOException {
        Supervisao supervisao = tabelaSupervisoes.getSelectionModel().getSelectedItem();//Instancia um objeto com os dados da supervisao selecionada

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

    @FXML
    private void handleExcluir(ActionEvent event) {
        Supervisao supervisao = tabelaSupervisoes.getSelectionModel().getSelectedItem();//Instancia um objeto com os dados da supervisao selecionada

        if (supervisao == null) {
            AlertMaker.showErrorMessage("Aviso", "Por favor, selecione uma supervis�o.");
        } else {
            if (supervisao.isStatus()) {//se a supervisao selecionada estiver finalizada
                AlertMaker.showErrorMessage("Aviso", "Não é possível excluir uma supervisão que foi finalizada.");
            } else {

                //Mensagem de confirmação para apagar uma informação
                Alert alert = new Alert(AlertType.CONFIRMATION);
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
    }**/
}
