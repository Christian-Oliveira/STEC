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

public class FormPNEFAFiscalizacoesController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormPNEFAFiscalizacoes formulario = new FormPNEFAFiscalizacoes();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    ObservableList<String> observableOpcoes;
    ObservableList<String> observableAvaliacao;
    
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXComboBox<String> cbVacinacao;
    @FXML
    private JFXComboBox<String> cbComprovacao;
    @FXML
    private JFXComboBox<String> cbRelatorios;
    @FXML
    private JFXComboBox<String> cbPosEtapa;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblVacinacao;
    @FXML
    private Label lblComprovacao;
    @FXML
    private Label lblRelatorios;
    @FXML
    private Label lblPosEtapa;
    @FXML
    private Label lblAvaliacao;
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
        
        cbComprovacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Procedimentos de comprovação de vacinação"));
        cbPosEtapa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Procedimentos pós-etapa"));
        cbRelatorios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Relatórios de campanha"));
        cbVacinacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Procedimentos relacionados à vacinação"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setVacinacao(cbVacinacao.getSelectionModel().getSelectedItem());
            formulario.setComprovacao(cbComprovacao.getSelectionModel().getSelectedItem());
            formulario.setRelatorios(cbRelatorios.getSelectionModel().getSelectedItem());
            formulario.setPosetapa(cbPosEtapa.getSelectionModel().getSelectedItem());

            formulario.setAvaliacao(cbAvaliacao.getSelectionModel().getSelectedItem());
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
            
            FormPNEFAFiscalizacoes formulario = gson.fromJson(resposta.getResposta(), FormPNEFAFiscalizacoes.class);
            
            cbVacinacao.getSelectionModel().select(formulario.getVacinacao());
            cbComprovacao.getSelectionModel().select(formulario.getComprovacao());
            cbRelatorios.getSelectionModel().select(formulario.getRelatorios());
            cbPosEtapa.getSelectionModel().select(formulario.getPosetapa());
            
            cbAvaliacao.getSelectionModel().select(formulario.getAvaliacao());
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
    
    public void carregarComboboxOpcoes(){
        observableOpcoes = FXCollections.observableList(supervisaoDAO.opcoesSupervisao);
        observableAvaliacao = FXCollections.observableList(supervisaoDAO.opcoesAvaliacao);
        
        cbComprovacao.setItems(observableOpcoes);
        cbPosEtapa.setItems(observableOpcoes);
        cbRelatorios.setItems(observableOpcoes);
        cbVacinacao.setItems(observableOpcoes);
        cbAvaliacao.setItems(observableAvaliacao);
    }
    
    //Função para validar dados
    @Override
    public boolean validarDados(){
        boolean error = false;
        if (cbVacinacao.getSelectionModel().isEmpty()){
            error = true;
            lblVacinacao.setStyle("-fx-text-fill:red");
        }
        if (cbPosEtapa.getSelectionModel().isEmpty()){
            error = true;
            lblPosEtapa.setStyle("-fx-text-fill:red");
        }
        if (cbRelatorios.getSelectionModel().isEmpty()){
            error = true;
            lblRelatorios.setStyle("-fx-text-fill:red");
        }
        if (cbComprovacao.getSelectionModel().isEmpty()){
            error = true;
            lblComprovacao.setStyle("-fx-text-fill:red");
        }
        if (cbAvaliacao.getSelectionModel().isEmpty()){
            error = true;
            lblAvaliacao.setStyle("-fx-text-fill:red");
        }
        if (error == false){
            return true;
        }else{
            AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
            return false;
        }
    }
    
    //Classe do formulario
    public class FormPNEFAFiscalizacoes {
        
        private String vacinacao;
        private String comprovacao;
        private String relatorios;
        private String posetapa;
        
        private String avaliacao;
        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;
        
        public FormPNEFAFiscalizacoes() {
        }
        
        public String getAvaliacao() {
        	return avaliacao;
        }
        
        public void setAvaliacao(String avaliacao) {
        	this.avaliacao = avaliacao;
        }

        public String getVacinacao() {
            return vacinacao;
        }

        public void setVacinacao(String vacinacao) {
            this.vacinacao = vacinacao;
        }

        public String getComprovacao() {
            return comprovacao;
        }

        public void setComprovacao(String comprovacao) {
            this.comprovacao = comprovacao;
        }

        public String getRelatorios() {
            return relatorios;
        }

        public void setRelatorios(String relatorios) {
            this.relatorios = relatorios;
        }

        public String getPosetapa() {
            return posetapa;
        }

        public void setPosetapa(String posetapa) {
            this.posetapa = posetapa;
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
