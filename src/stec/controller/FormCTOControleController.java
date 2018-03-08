package stec.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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

public class FormCTOControleController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormCTOControle formulario = new FormCTOControle();
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
    private JFXTextField nBlitzAno;
    @FXML
    private JFXTextField qtdBlitzMes;
    @FXML
    private JFXTextField qtdBlitzMesInspecionados;
    @FXML
    private JFXTextField qtdBlitzMesAnimais;
    @FXML
    private JFXTextField qtdBlitzMesProdutos;
    @FXML
    private JFXTextField qtdBlitzMesVazio;
    @FXML
    private JFXComboBox<String> cbPontosEstrategicos;
    @FXML
    private JFXComboBox<String> cbRealizacaoMensal;
    @FXML
    private JFXComboBox<String> cbRegistroBlitz;
    @FXML
    private JFXComboBox<String> cbApreensoes;
    @FXML
    private JFXComboBox<String> cbMaterialBlitz;
    @FXML
    private JFXComboBox<String> cbRegistro;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblBlitzAno;
    @FXML
    private Label lblQtdBlitzMes;
    @FXML
    private Label lblQtdBlitzMesAnimais;
    @FXML
    private Label lblQtdBlitzMesProdutos;
    @FXML
    private Label lblQtdBlitzMesVazio;
    @FXML
    private Label lblQtdBlitzMesInspecionados;
    @FXML
    private Label lblPontosEstrategicos;
    @FXML
    private Label lblRealizacaoMensal;
    @FXML
    private Label lblRegistroBlitz;
    @FXML
    private Label lblApreensoes;
    @FXML
    private Label lblMaterialBlitz;
    @FXML
    private Label lblRegistro;
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
        
        cbApreensoes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Apreensões"));
        cbMaterialBlitz.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Material para realizaão de blitz"));
        cbPontosEstrategicos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Pontos Estratégicos para realização de blitz"));
        cbRealizacaoMensal.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Realização mensal de blitz"));
        cbRegistro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Registro sobre operações volantes"));
        cbRegistroBlitz.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Registro da blitz"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setnBlitzAno(nBlitzAno.getText());
            formulario.setQtdBlitzMes(qtdBlitzMes.getText());
            formulario.setQtdBlitzMesInspecionados(qtdBlitzMesInspecionados.getText());
            formulario.setQtdBlitzMesAnimais(qtdBlitzMesAnimais.getText());
            formulario.setQtdBlitzMesProdutos(qtdBlitzMesProdutos.getText());
            formulario.setQtdBlitzMesVazio(qtdBlitzMesVazio.getText());
            formulario.setApreensoes(cbApreensoes.getSelectionModel().getSelectedItem());
            formulario.setMaterialBlitz(cbMaterialBlitz.getSelectionModel().getSelectedItem());
            formulario.setPontosEstrategicos(cbPontosEstrategicos.getSelectionModel().getSelectedItem());
            formulario.setRealizacaoMensal(cbRealizacaoMensal.getSelectionModel().getSelectedItem());
            formulario.setRegistro(cbRegistro.getSelectionModel().getSelectedItem());
            formulario.setRegistroBlitz(cbRegistroBlitz.getSelectionModel().getSelectedItem());

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
    
    public void carregarComboboxOpcoes(){
        observableOpcoes = FXCollections.observableList(supervisaoDAO.opcoesSupervisao);
        observableAvaliacao = FXCollections.observableList(supervisaoDAO.opcoesAvaliacao);
        
        cbApreensoes.setItems(observableOpcoes);
        cbMaterialBlitz.setItems(observableOpcoes);
        cbPontosEstrategicos.setItems(observableOpcoes);
        cbRealizacaoMensal.setItems(observableOpcoes);
        cbRegistro.setItems(observableOpcoes);
        cbRegistroBlitz.setItems(observableOpcoes);
        cbAvaliacao.setItems(observableAvaliacao);
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
            
            FormCTOControle formulario = gson.fromJson(resposta.getResposta(), FormCTOControle.class);
            
            nBlitzAno.setText(formulario.getnBlitzAno());
            qtdBlitzMes.setText(formulario.getQtdBlitzMes());
            qtdBlitzMesInspecionados.setText(formulario.getQtdBlitzMesInspecionados());
            qtdBlitzMesAnimais.setText(formulario.getQtdBlitzMesAnimais());
            qtdBlitzMesProdutos.setText(formulario.getQtdBlitzMesProdutos());
            qtdBlitzMesVazio.setText(formulario.getQtdBlitzMesVazio());
            cbApreensoes.getSelectionModel().select(formulario.getApreensoes());
            cbMaterialBlitz.getSelectionModel().select(formulario.getMaterialBlitz());
            cbPontosEstrategicos.getSelectionModel().select(formulario.getPontosEstrategicos());
            cbRealizacaoMensal.getSelectionModel().select(formulario.getRealizacaoMensal());
            cbRegistro.getSelectionModel().select(formulario.getRegistro());
            cbRegistroBlitz.getSelectionModel().select(formulario.getRegistroBlitz());
            
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
    
    //Função para validar dados
    @Override
    public boolean validarDados(){
        boolean error = false;
        if (nBlitzAno.getText() == null || nBlitzAno.getText().length() == 0){
            error = true;
            lblBlitzAno.setStyle("-fx-text-fill:red");
        }
        if (qtdBlitzMes.getText() == null || qtdBlitzMes.getText().length() == 0){
            error = true;
            lblQtdBlitzMes.setStyle("-fx-text-fill:red");
        }
        if (qtdBlitzMesAnimais.getText() == null || qtdBlitzMesAnimais.getText().length() == 0){
            error = true;
            lblQtdBlitzMesAnimais.setStyle("-fx-text-fill:red");
        }
        if (qtdBlitzMesProdutos.getText() == null || qtdBlitzMesProdutos.getText().length() == 0){
            error = true;
            lblQtdBlitzMesProdutos.setStyle("-fx-text-fill:red");
        }
        if (qtdBlitzMesVazio.getText() == null || qtdBlitzMesVazio.getText().length() == 0){
            error = true;
            lblQtdBlitzMesVazio.setStyle("-fx-text-fill:red");
        }
        if (qtdBlitzMesInspecionados.getText() == null || qtdBlitzMesInspecionados.getText().length() == 0){
            error = true;
            lblQtdBlitzMesInspecionados.setStyle("-fx-text-fill:red");
        }
        if (cbPontosEstrategicos.getSelectionModel().isEmpty()){
            error = true;
            lblPontosEstrategicos.setStyle("-fx-text-fill:red");
        }
        if (cbRealizacaoMensal.getSelectionModel().isEmpty()){
            error = true;
            lblRealizacaoMensal.setStyle("-fx-text-fill:red");
        }
        if (cbRegistroBlitz.getSelectionModel().isEmpty()){
            error = true;
            lblRegistroBlitz.setStyle("-fx-text-fill:red");
        }
        if (cbApreensoes.getSelectionModel().isEmpty()){
            error = true;
            lblApreensoes.setStyle("-fx-text-fill:red");
        }
        if (cbMaterialBlitz.getSelectionModel().isEmpty()){
            error = true;
            lblMaterialBlitz.setStyle("-fx-text-fill:red");
        }
        if (cbRegistro.getSelectionModel().isEmpty()){
            error = true;
            lblRegistro.setStyle("-fx-text-fill:red");
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
        
    public class FormCTOControle {
        
        private String nBlitzAno;
        private String qtdBlitzMes;
        private String qtdBlitzMesInspecionados;
        private String qtdBlitzMesAnimais;
        private String qtdBlitzMesProdutos;
        private String qtdBlitzMesVazio;
        private String pontosEstrategicos;
        private String realizacaoMensal;
        private String registroBlitz;
        private String apreensoes;
        private String materialBlitz;
        private String registro;
        
        private String avaliacao;
        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;
        
        public String getAvaliacao() {
        	return avaliacao;
        }
        
        public void setAvaliacao(String avaliacao) {
        	this.avaliacao = avaliacao;
        }
        
        public String getnBlitzAno() {
            return nBlitzAno;
        }

        public void setnBlitzAno(String nBlitzAno) {
            this.nBlitzAno = nBlitzAno;
        }

        public String getQtdBlitzMes() {
            return qtdBlitzMes;
        }

        public void setQtdBlitzMes(String qtdBlitzMes) {
            this.qtdBlitzMes = qtdBlitzMes;
        }

        public String getQtdBlitzMesInspecionados() {
            return qtdBlitzMesInspecionados;
        }

        public void setQtdBlitzMesInspecionados(String qtdBlitzMesInspecionados) {
            this.qtdBlitzMesInspecionados = qtdBlitzMesInspecionados;
        }

        public String getQtdBlitzMesAnimais() {
            return qtdBlitzMesAnimais;
        }

        public void setQtdBlitzMesAnimais(String qtdBlitzMesAnimais) {
            this.qtdBlitzMesAnimais = qtdBlitzMesAnimais;
        }

        public String getQtdBlitzMesProdutos() {
            return qtdBlitzMesProdutos;
        }

        public void setQtdBlitzMesProdutos(String qtdBlitzMesProdutos) {
            this.qtdBlitzMesProdutos = qtdBlitzMesProdutos;
        }

        public String getQtdBlitzMesVazio() {
            return qtdBlitzMesVazio;
        }

        public void setQtdBlitzMesVazio(String qtdBlitzMesVazio) {
            this.qtdBlitzMesVazio = qtdBlitzMesVazio;
        }

        public String getPontosEstrategicos() {
            return pontosEstrategicos;
        }

        public void setPontosEstrategicos(String pontosEstrategicos) {
            this.pontosEstrategicos = pontosEstrategicos;
        }

        public String getRealizacaoMensal() {
            return realizacaoMensal;
        }

        public void setRealizacaoMensal(String realizacaoMensal) {
            this.realizacaoMensal = realizacaoMensal;
        }

        public String getRegistroBlitz() {
            return registroBlitz;
        }

        public void setRegistroBlitz(String registroBlitz) {
            this.registroBlitz = registroBlitz;
        }

        public String getApreensoes() {
            return apreensoes;
        }

        public void setApreensoes(String apreensoes) {
            this.apreensoes = apreensoes;
        }

        public String getMaterialBlitz() {
            return materialBlitz;
        }

        public void setMaterialBlitz(String materialBlitz) {
            this.materialBlitz = materialBlitz;
        }

        public String getRegistro() {
            return registro;
        }

        public void setRegistro(String registro) {
            this.registro = registro;
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
