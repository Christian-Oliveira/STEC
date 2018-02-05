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

public class FormPNCEBTVigilianciaController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormPNCEBTVigilancia formulario = new FormPNCEBTVigilancia();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    ObservableList<String> observableOpcoes;
    
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXComboBox<String> cbDirecionamento;
    @FXML
    private JFXComboBox<String> cbFiscalizacaoPropriedades;
    @FXML
    private JFXComboBox<String> cbFiscalizacaoLaboratorio;
    @FXML
    private JFXComboBox<String> cbProcedimentoAdotado;
    @FXML
    private JFXTextField nPropriedades;
    @FXML
    private JFXTextField doencaCurso;
    @FXML
    private Label lblDirecionamento;
    @FXML
    private Label lblFiscalizacaoPropriedades;
    @FXML
    private Label lblFiscalizacaoLaboratorio;
    @FXML
    private Label lblPropriedades;
    @FXML
    private Label lblDoencaCurso;
    @FXML
    private Label lblProcedimentoAdotado;
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
        cbFiscalizacaoLaboratorio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Fiscalização em laboratório"));
        cbFiscalizacaoPropriedades.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Fiscalização em propriedades"));
        cbProcedimentoAdotado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Procedimentos adotados"));
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
            formulario.setFiscalizacaoPropriedade(cbFiscalizacaoPropriedades.getSelectionModel().getSelectedItem());
            formulario.setFiscalizacaoLaboratorio(cbFiscalizacaoLaboratorio.getSelectionModel().getSelectedItem());
            formulario.setnPropriedadesFoco(nPropriedades.getText());
            formulario.setDoencaCurso(doencaCurso.getText());
            formulario.setProcedimentoAdotadoFoco(cbProcedimentoAdotado.getSelectionModel().getSelectedItem());

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
            
            FormPNCEBTVigilancia formulario = gson.fromJson(resposta.getResposta(), FormPNCEBTVigilancia.class);
            
            cbDirecionamento.getSelectionModel().select(formulario.getDirecionamento());
            cbFiscalizacaoPropriedades.getSelectionModel().select(formulario.getFiscalizacaoPropriedade());
            cbFiscalizacaoLaboratorio.getSelectionModel().select(formulario.getFiscalizacaoLaboratorio());
            nPropriedades.setText(formulario.getnPropriedadesFoco());
            doencaCurso.setText(formulario.getDoencaCurso());
            cbProcedimentoAdotado.getSelectionModel().select(formulario.getProcedimentoAdotadoFoco());
            
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
        if (cbFiscalizacaoPropriedades.getSelectionModel().isEmpty()){
            error = true;
            lblFiscalizacaoPropriedades.setStyle("-fx-text-fill:red");
        }
        if (cbFiscalizacaoLaboratorio.getSelectionModel().isEmpty()){
            error = true;
            lblFiscalizacaoLaboratorio.setStyle("-fx-text-fill:red");
        }
        if (nPropriedades.getText() == null || nPropriedades.getText().length() == 0){
            error = true;
            lblPropriedades.setStyle("-fx-text-fill:red");
        }
        if (doencaCurso.getText() == null || doencaCurso.getText().length() == 0){
            error = true;
            lblDoencaCurso.setStyle("-fx-text-fill:red");
        }
        if (cbProcedimentoAdotado.getSelectionModel().isEmpty()){
            error = true;
            lblProcedimentoAdotado.setStyle("-fx-text-fill:red");
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
        
        cbDirecionamento.setItems(observableOpcoes);
        cbFiscalizacaoLaboratorio.setItems(observableOpcoes);
        cbFiscalizacaoPropriedades.setItems(observableOpcoes);
        cbProcedimentoAdotado.setItems(observableOpcoes);
    }
    
    public class FormPNCEBTVigilancia {
        
        private String direcionamento;
        private String fiscalizacaoPropriedade;
        private String fiscalizacaoLaboratorio;
        private String nPropriedadesFoco;
        private String doencaCurso;
        private String procedimentoAdotadoFoco;
        
        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;
        
        public FormPNCEBTVigilancia() {
        }

        public String getDirecionamento() {
            return direcionamento;
        }

        public void setDirecionamento(String direcionamento) {
            this.direcionamento = direcionamento;
        }

        public String getFiscalizacaoPropriedade() {
            return fiscalizacaoPropriedade;
        }

        public void setFiscalizacaoPropriedade(String fiscalizacaoPropriedade) {
            this.fiscalizacaoPropriedade = fiscalizacaoPropriedade;
        }

        public String getFiscalizacaoLaboratorio() {
            return fiscalizacaoLaboratorio;
        }

        public void setFiscalizacaoLaboratorio(String fiscalizacaoLaboratorio) {
            this.fiscalizacaoLaboratorio = fiscalizacaoLaboratorio;
        }

        public String getnPropriedadesFoco() {
            return nPropriedadesFoco;
        }

        public void setnPropriedadesFoco(String nPropriedadesFoco) {
            this.nPropriedadesFoco = nPropriedadesFoco;
        }

        public String getDoencaCurso() {
            return doencaCurso;
        }

        public void setDoencaCurso(String doencaCurso) {
            this.doencaCurso = doencaCurso;
        }

        public String getProcedimentoAdotadoFoco() {
            return procedimentoAdotadoFoco;
        }

        public void setProcedimentoAdotadoFoco(String procedimentoAdotadoFoco) {
            this.procedimentoAdotadoFoco = procedimentoAdotadoFoco;
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
