package stec.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.Resposta;
import stec.resources.Classes.AlertMaker;
import stec.resources.Classes.IFormulario;

public class FormPNCEBTControlesController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormPNCEBTControles formulario = new FormPNCEBTControles();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    ObservableList<String> observableOpcoes;
    
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXComboBox<String> cbPortaria;
    @FXML
    private JFXComboBox<String> cbPontualidade;
    @FXML
    private JFXComboBox<String> cbRelatoriosPNCEBT;
    @FXML
    private JFXComboBox<String> cbComercializacao;
    @FXML
    private Label lblPortaria;
    @FXML
    private Label lblPontualidade;
    @FXML
    private Label lblRelatoriosPNCEBT;
    @FXML
    private Label lblComercializacao;
    @FXML
    private Tab comentarios;
    @FXML
    private JFXTextArea txtComentarios;
    @FXML
    private Tab recomendacoesULSAVEAC;
    @FXML
    private JFXTextArea txtRULSAVEAC;
    @FXML
    private Tab prazo;
    @FXML
    private JFXTextArea txtPrazo;
    @FXML
    private Tab recomendacoesUR;
    @FXML
    private JFXTextArea txtRUR;
    @FXML
    private Tab recomendacoesUC;
    @FXML
    private JFXTextArea txtRUC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboboxOpcoes();
        
        cbComercializacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Comercialização de blocos de Receituário e atestados de vacinação contra brucelose (UR)"));
        cbPontualidade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Pontualidade Med Vet cadastrados e/ou habilitados"));
        cbPortaria.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Portarias Med Vet cadastrados e/ou habilitados"));
        cbRelatoriosPNCEBT.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Relatários do PNCEBT/MA"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setComercializacao(cbComercializacao.getSelectionModel().getSelectedItem());
            formulario.setPontualidade(cbPontualidade.getSelectionModel().getSelectedItem());
            formulario.setPortaria(cbPortaria.getSelectionModel().getSelectedItem());
            formulario.setRelatorios(cbRelatoriosPNCEBT.getSelectionModel().getSelectedItem());

            formulario.setComentario(txtComentarios.getText());
            formulario.setRecomendacaoUlsavEac(txtRULSAVEAC.getText());
            formulario.setPrazo(txtPrazo.getText());
            formulario.setRecomendacaoUr(txtRUR.getText());
            formulario.setRecomendacaoUC(txtRUC.getText());

            //passa a resposta do fromulario como json
            resposta.setResposta(gson.toJson(formulario));

            btConfirmarClicked = true;
            dialog.close();
        }
    }

    @FXML
    private void handleCancelar(ActionEvent event) {
        dialog.close();
    }

    @Override
    public Resposta getResposta() {
        return resposta;
    }

    @Override
    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
        
        //Recupera as informacoes do formulario caso exista a resposta
        if (resposta.getResposta() != null) {
            
            Gson gson = new Gson();
            
            FormPNCEBTControles formulario = gson.fromJson(resposta.getResposta(), FormPNCEBTControles.class);
            
            cbComercializacao.getSelectionModel().select(formulario.getComercializacao());
            cbPontualidade.getSelectionModel().select(formulario.getPontualidade());
            cbPortaria.getSelectionModel().select(formulario.getPortaria());
            cbRelatoriosPNCEBT.getSelectionModel().select(formulario.getRelatorios());
            
            txtComentarios.setText(formulario.getComentario());
            txtPrazo.setText(formulario.getPrazo());
            txtRUC.setText(formulario.getRecomendacaoUC());
            txtRULSAVEAC.setText(formulario.getRecomendacaoUlsavEac());
            txtRUR.setText(formulario.getRecomendacaoUr());
        }
    }

    @Override
    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    @Override
    public void setBtConfirmarClicked(boolean btConfirmarClicked) {
        this.btConfirmarClicked = btConfirmarClicked;
    }

    @Override
    public Stage getDialog() {
        return dialog;
    }

    @Override
    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }
    
    //Funcão para validar dados
    @Override
    public boolean validarDados(){
        boolean error = false;
        if (cbPortaria.getSelectionModel().isEmpty()){
            error = true;
            lblPortaria.setStyle("-fx-text-fill:red");
        }
        if (cbPontualidade.getSelectionModel().isEmpty()){
            error = true;
            lblPontualidade.setStyle("-fx-text-fill:red");
        }
        if (cbRelatoriosPNCEBT.getSelectionModel().isEmpty()){
            error = true;
            lblRelatoriosPNCEBT.setStyle("-fx-text-fill:red");
        }
        if (cbComercializacao.getSelectionModel().isEmpty()){
            error = true;
            lblComercializacao.setStyle("-fx-text-fill:red");
        }
        if (error == false){
            return true;
        }else{
            AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
            return false;
        }
    }
    
    public void carregarComboboxOpcoes(){
        observableOpcoes = FXCollections.observableList(supervisaoDAO.opcoesSupervisao);
        
        cbComercializacao.setItems(observableOpcoes);
        cbPontualidade.setItems(observableOpcoes);
        cbPortaria.setItems(observableOpcoes);
        cbRelatoriosPNCEBT.setItems(observableOpcoes);
    }
    
    public class FormPNCEBTControles {
        
        private String portaria;
        private String pontualidade;
        private String relatorios;
        private String comercializacao;
        
        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;

        public FormPNCEBTControles() {
        }

        public String getPortaria() {
            return portaria;
        }

        public void setPortaria(String portaria) {
            this.portaria = portaria;
        }

        public String getPontualidade() {
            return pontualidade;
        }

        public void setPontualidade(String pontualidade) {
            this.pontualidade = pontualidade;
        }

        public String getRelatorios() {
            return relatorios;
        }

        public void setRelatorios(String relatorios) {
            this.relatorios = relatorios;
        }

        public String getComercializacao() {
            return comercializacao;
        }

        public void setComercializacao(String comercializacao) {
            this.comercializacao = comercializacao;
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }

        public String getRecomendacaoUlsavEac() {
            return recomendacaoUlsavEac;
        }

        public void setRecomendacaoUlsavEac(String recomendacaoUlsavEac) {
            this.recomendacaoUlsavEac = recomendacaoUlsavEac;
        }

        public String getPrazo() {
            return prazo;
        }

        public void setPrazo(String prazo) {
            this.prazo = prazo;
        }

        public String getRecomendacaoUr() {
            return recomendacaoUr;
        }

        public void setRecomendacaoUr(String recomendacaoUr) {
            this.recomendacaoUr = recomendacaoUr;
        }

        public String getRecomendacaoUC() {
            return recomendacaoUC;
        }

        public void setRecomendacaoUC(String recomendacaoUC) {
            this.recomendacaoUC = recomendacaoUC;
        }
        
        
    }
}
