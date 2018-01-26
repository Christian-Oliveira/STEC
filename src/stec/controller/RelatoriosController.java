package stec.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import stec.model.dao.RespostaDAO;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.RelatorioAvaliacaoSupervisao;
import stec.model.domain.Supervisao;
import stec.model.domain.Ur;
import stec.resources.Classes.AlertMaker;

public class RelatoriosController implements Initializable {
    
    List<Supervisao> listSupervisoes;//Lista das supervisoes
    ObservableList<Supervisao> observableListSupervisoes;//Observable list das supervisoes
    private final SupervisaoDAO supervisaoDAO = new SupervisaoDAO();//objeto DAO responsavel pela interacao com o bd
    
    @FXML
    private AnchorPane RelatoriosPane;
    @FXML
    private TableView<Supervisao> tabelaSupervisoes;
    @FXML
    private TableColumn<Supervisao, Ur> colunaUr;
    @FXML
    private TableColumn<Supervisao, String> colunaEscritorioMunicipio;
    @FXML
    private TableColumn<Supervisao, String> colunaProgramas;
    @FXML
    private JFXButton btAuditoriaCompilada;
    @FXML
    private JFXButton btAvaliacaoQualidade;
    @FXML
    private JFXButton btVoltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabelaSupervisoesImportadas();
    }

    @FXML
    private void handleAuditoriaCompilada(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(FormSupervisaoController.class.getResource("/stec/view/relatorios/FormAuditoriaCompilada.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        stage.setTitle("Relatórios Compilados");
        Scene scene = new Scene(pane);
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void handleAvaliacaoQualidade(ActionEvent event) {
        //Instancia um objeto com os dados da supervisao selecionada
        Supervisao supervisao = tabelaSupervisoes.getSelectionModel().getSelectedItem();

        if (supervisao == null) {
            AlertMaker.showErrorMessage("Aviso", "Por favor, selecione uma supervisão.");
        } else {
            AlertMaker.showSimpleAlert("Aviso", "Será gerado um PDF das Avaliações da supervisão");
            RelatorioAvaliacaoSupervisao relatorio = new RelatorioAvaliacaoSupervisao();
            relatorio.setSupervisao(supervisao);

            RespostaDAO respostaDAO = new RespostaDAO();
            relatorio.setHashRespostas(respostaDAO.listarRespostasDaSupervisaoImportada(supervisao));

            //Nome do pdf da supervisao
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            String data[] = supervisao.getCreated().split(" ");//pega apenas a data

            String file = "Avaliação da supervisão " + municipio + " " + data[0];

            relatorio.show(file);

        }
    }
    
    @FXML
    void handleVoltar(ActionEvent event) {
        //Fecha a tela de relatorios
        ((Stage) RelatoriosPane.getScene().getWindow()).close();
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
    }
        
    public void carregarTabelaSupervisoesImportadas() {
        colunaUr.setCellValueFactory(new PropertyValueFactory<>("ur"));
        colunaEscritorioMunicipio.setCellValueFactory(new PropertyValueFactory<>("escritorio"));//deve ser exatamente o nome do atributo da classe
        colunaProgramas.setCellValueFactory(new PropertyValueFactory<>("programas"));

        listSupervisoes = supervisaoDAO.listarImportadas();//lista as supervisoes do usuario

        observableListSupervisoes = FXCollections.observableArrayList(listSupervisoes);
        tabelaSupervisoes.setItems(observableListSupervisoes);
    }
}
