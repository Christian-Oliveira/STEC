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

public class FormPNEEBVigilanciaController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormPNEEBVigilancia formulario = new FormPNEEBVigilancia();
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
    private JFXComboBox<String> cbDirecionamento;
    @FXML
    private JFXComboBox<String> cbIdentificacao;
    @FXML
    private JFXComboBox<String> cbInspecoes;
    @FXML
    private JFXComboBox<String> cbFiscalizacaoMatadouro;
    @FXML
    private JFXComboBox<String> cbFiscalizacaoGraxarias;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private JFXTextField nPropriedades;
    @FXML
    private JFXTextField doencasCurso;
    @FXML
    private JFXComboBox<String> cbProcedimentosAdotados;
    @FXML
    private Label lblDirecionamento;
    @FXML
    private Label lblIdentificacao;
    @FXML
    private Label lblInspecoes;
    @FXML
    private Label lblFiscalizacaoMatadouro;
    @FXML
    private Label lblFiscalizacaoGraxarias;
    @FXML
    private Label lblPropriedades;
    @FXML
    private Label lblDoencasCurso;
    @FXML
    private Label lblProcedimentosAdotados;
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
        
        cbDirecionamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Direcionamento das ações de vigilância"));
        cbFiscalizacaoGraxarias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Fiscalização em graxarias"));
        cbFiscalizacaoMatadouro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Fiscalização em matadouro"));
        cbIdentificacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Identificação de áreas segundo os fatores de risco"));
        cbInspecoes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Inspeções em propriedades"));
        cbProcedimentosAdotados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Procedimentos adotados"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setDirecionamento(cbDirecionamento.getSelectionModel().getSelectedItem());
            formulario.setFiscalizacaograxaria(cbFiscalizacaoGraxarias.getSelectionModel().getSelectedItem());
            formulario.setFiscalizacaoMatadouro(cbFiscalizacaoMatadouro.getSelectionModel().getSelectedItem());
            formulario.setIdentificacao(cbIdentificacao.getSelectionModel().getSelectedItem());
            formulario.setInspecoes(cbInspecoes.getSelectionModel().getSelectedItem());
            formulario.setProcedimentosAdotados(cbProcedimentosAdotados.getSelectionModel().getSelectedItem());
            formulario.setnPropriedades(nPropriedades.getText());
            formulario.setDoencaCurso(doencasCurso.getText());

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
        
        cbDirecionamento.setItems(observableOpcoes);
        cbFiscalizacaoGraxarias.setItems(observableOpcoes);
        cbFiscalizacaoMatadouro.setItems(observableOpcoes);
        cbIdentificacao.setItems(observableOpcoes);
        cbInspecoes.setItems(observableOpcoes);
        cbProcedimentosAdotados.setItems(observableOpcoes);
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
            
            FormPNEEBVigilancia formulario = gson.fromJson(resposta.getResposta(), FormPNEEBVigilancia.class);
            
            cbDirecionamento.getSelectionModel().select(formulario.getDirecionamento());
            cbFiscalizacaoGraxarias.getSelectionModel().select(formulario.getFiscalizacaograxaria());
            cbFiscalizacaoMatadouro.getSelectionModel().select(formulario.getFiscalizacaoMatadouro());
            cbIdentificacao.getSelectionModel().select(formulario.getIdentificacao());
            cbInspecoes.getSelectionModel().select(formulario.getInspecoes());
            cbProcedimentosAdotados.getSelectionModel().select(formulario.getProcedimentosAdotados());
            nPropriedades.setText(formulario.getnPropriedades());
            doencasCurso.setText(formulario.getDoencaCurso());
            
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
    
    //Funcão para validar dados
    @Override
    public boolean validarDados(){
        boolean error = false;
        if (cbDirecionamento.getSelectionModel().isEmpty()){
            error = true;
            lblDirecionamento.setStyle("-fx-text-fill:red");
        }
        if (cbIdentificacao.getSelectionModel().isEmpty()){
            error = true;
            lblIdentificacao.setStyle("-fx-text-fill:red");
        }
        if (cbInspecoes.getSelectionModel().isEmpty()){
            error = true;
            lblInspecoes.setStyle("-fx-text-fill:red");
        }
        if (cbFiscalizacaoMatadouro.getSelectionModel().isEmpty()){
            error = true;
            lblFiscalizacaoMatadouro.setStyle("-fx-text-fill:red");
        }
        if (cbFiscalizacaoGraxarias.getSelectionModel().isEmpty()){
            error = true;
            lblFiscalizacaoGraxarias.setStyle("-fx-text-fill:red");
        }
        if (nPropriedades.getText() == null || nPropriedades.getText().length() == 0){
            error = true;
            lblPropriedades.setStyle("-fx-text-fill:red");
        }
        if (doencasCurso.getText() == null || doencasCurso.getText().length() == 0){
            error = true;
            lblDoencasCurso.setStyle("-fx-text-fill:red");
        }
        if (cbProcedimentosAdotados.getSelectionModel().isEmpty()){
            error = true;
            lblProcedimentosAdotados.setStyle("-fx-text-fill:red");
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
    
    public class FormPNEEBVigilancia {
        
        private String direcionamento;
        private String fiscalizacaograxaria;
        private String fiscalizacaoMatadouro;
        private String identificacao;
        private String inspecoes;
        private String procedimentosAdotados;
        private String nPropriedades;
        private String doencaCurso;
        
        private String avaliacao;
        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;

        public FormPNEEBVigilancia() {
        }
        
        public String getAvaliacao() {
        	return avaliacao;
        }
        
        public void setAvaliacao(String avaliacao) {
        	this.avaliacao = avaliacao;
        }
        
        public String getDirecionamento() {
            return direcionamento;
        }

        public void setDirecionamento(String direcionamento) {
            this.direcionamento = direcionamento;
        }

        public String getFiscalizacaograxaria() {
            return fiscalizacaograxaria;
        }

        public void setFiscalizacaograxaria(String fiscalizacaograxaria) {
            this.fiscalizacaograxaria = fiscalizacaograxaria;
        }

        public String getFiscalizacaoMatadouro() {
            return fiscalizacaoMatadouro;
        }

        public void setFiscalizacaoMatadouro(String fiscalizacaoMatadouro) {
            this.fiscalizacaoMatadouro = fiscalizacaoMatadouro;
        }

        public String getIdentificacao() {
            return identificacao;
        }

        public void setIdentificacao(String identificacao) {
            this.identificacao = identificacao;
        }

        public String getInspecoes() {
            return inspecoes;
        }

        public void setInspecoes(String inspecoes) {
            this.inspecoes = inspecoes;
        }

        public String getProcedimentosAdotados() {
            return procedimentosAdotados;
        }

        public void setProcedimentosAdotados(String procedimentosAdotados) {
            this.procedimentosAdotados = procedimentosAdotados;
        }

        public String getnPropriedades() {
            return nPropriedades;
        }

        public void setnPropriedades(String nPropriedades) {
            this.nPropriedades = nPropriedades;
        }

        public String getDoencaCurso() {
            return doencaCurso;
        }

        public void setDoencaCurso(String doencaCurso) {
            this.doencaCurso = doencaCurso;
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
