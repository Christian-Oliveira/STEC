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

public class FormPNCRHVigilanciaController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormPNCRHVigilancia formulario = new FormPNCRHVigilancia();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    ObservableList<String> observableOpcoes;
    
    @FXML
    private JFXComboBox<String> cbDirecionamento;
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXComboBox<String> cbCadastroMorcegos;
    @FXML
    private JFXComboBox<String> cbCapturaMorcegos;
    @FXML
    private JFXTextField nPropriedade;
    @FXML
    private JFXTextField doencaCurso;
    @FXML
    private Label lblDirecionamento;
    @FXML
    private Label lblCadastroMorcegos;
    @FXML
    private Label lblCapturaMorcegos;
    @FXML
    private Label lblPropriedade;
    @FXML
    private Label lblDoencaCurso;
    @FXML
    private Label lblProcedimentoAdotados;
    @FXML
    private JFXComboBox<String> cbProcedimentoAdotados;
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
        
        cbCadastroMorcegos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Cadastro e monitoramento de abrigos de morcegos hematófagos"));
        cbCapturaMorcegos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Captura de morcegos"));
        cbDirecionamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Direcionamento das ações de vigilância"));
        cbProcedimentoAdotados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Procedimentos adotados"));
    }   
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setCadastroMorcegos(cbCadastroMorcegos.getSelectionModel().getSelectedItem());
            formulario.setCapturaMorcegos(cbCapturaMorcegos.getSelectionModel().getSelectedItem());
            formulario.setDirecionamento(cbDirecionamento.getSelectionModel().getSelectedItem());
            formulario.setProcedimentosAdotados(cbProcedimentoAdotados.getSelectionModel().getSelectedItem());
            formulario.setnPropriedades(nPropriedade.getText());
            formulario.setDoencaCurso(doencaCurso.getText());

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
        
        cbCadastroMorcegos.setItems(observableOpcoes);
        cbCapturaMorcegos.setItems(observableOpcoes);
        cbDirecionamento.setItems(observableOpcoes);
        cbProcedimentoAdotados.setItems(observableOpcoes);
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
            
            FormPNCRHVigilancia formulario = gson.fromJson(resposta.getResposta(), FormPNCRHVigilancia.class);
            
            cbCadastroMorcegos.getSelectionModel().select(formulario.getCadastroMorcegos());
            cbCapturaMorcegos.getSelectionModel().select(formulario.getCapturaMorcegos());
            cbDirecionamento.getSelectionModel().select(formulario.getDirecionamento());
            cbProcedimentoAdotados.getSelectionModel().select(formulario.getProcedimentosAdotados());
            nPropriedade.setText(formulario.getnPropriedades());
            doencaCurso.setText(formulario.getDoencaCurso());
            
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
        if (cbCadastroMorcegos.getSelectionModel().isEmpty()){
            error = true;
            lblCadastroMorcegos.setStyle("-fx-text-fill:red");
        }
        if (cbCapturaMorcegos.getSelectionModel().isEmpty()){
            error = true;
            lblCapturaMorcegos.setStyle("-fx-text-fill:red");
        }
        if (nPropriedade.getText() == null || nPropriedade.getText().length() == 0){
            error = true;
            lblPropriedade.setStyle("-fx-text-fill:red");
        }
        if (doencaCurso.getText() == null || doencaCurso.getText().length() == 0){
            error = true;
            lblDoencaCurso.setStyle("-fx-text-fill:red");
        }
        if (cbProcedimentoAdotados.getSelectionModel().isEmpty()){
            error = true;
            lblProcedimentoAdotados.setStyle("-fx-text-fill:red");
        }
        if (error == false){
            return true;
        }else{
            AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
            return false;
        }
    }
        
    public class FormPNCRHVigilancia {
        
        private String direcionamento;
        private String cadastroMorcegos;
        private String capturaMorcegos;
        private String nPropriedades;
        private String doencaCurso;
        private String procedimentosAdotados;
        
        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;

        public FormPNCRHVigilancia() {
        }

        public String getDirecionamento() {
            return direcionamento;
        }

        public void setDirecionamento(String direcionamento) {
            this.direcionamento = direcionamento;
        }

        public String getCadastroMorcegos() {
            return cadastroMorcegos;
        }

        public void setCadastroMorcegos(String cadastroMorcegos) {
            this.cadastroMorcegos = cadastroMorcegos;
        }

        public String getCapturaMorcegos() {
            return capturaMorcegos;
        }

        public void setCapturaMorcegos(String capturaMorcegos) {
            this.capturaMorcegos = capturaMorcegos;
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

        public String getProcedimentosAdotados() {
            return procedimentosAdotados;
        }

        public void setProcedimentosAdotados(String procedimentosAdotados) {
            this.procedimentosAdotados = procedimentosAdotados;
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
