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

public class FormCTOAtendimentoSuspeitaController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormCTOAtendimentoSuspeita formulario = new FormCTOAtendimentoSuspeita();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    ObservableList<String> observableOpcoes;
    ObservableList<String> observableAvaliacao;
    
    @FXML
    private JFXComboBox<String> cbEPI;
    @FXML
    private JFXComboBox<String> cbKitVesiculares;
    @FXML
    private JFXComboBox<String> cbKitMaterialBiologico;
    @FXML
    private JFXComboBox<String> cbAcondicionamento;
    @FXML
    private JFXComboBox<String> cbProcedimentos;
    @FXML
    private JFXComboBox<String> cbAgroprodutivo;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblEPI;
    @FXML
    private Label lblKitVesiculares;
    @FXML
    private Label lblKitMaterialBiologico;
    @FXML
    private Label lblAcondicionamento;
    @FXML
    private Label lblProcedimentos;
    @FXML
    private Label lblAgroprodutivo;
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
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboboxOpcoes();
        
        cbAcondicionamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Acondicionamento e remessa de material para a Unidade Central "));
        cbAgroprodutivo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Cadastro Agroprodutivo"));
        cbEPI.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Equipamento de Proteção Individual (EPI)"));
        cbKitMaterialBiologico.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Kit de atendimento para colheita de material biológico (doenças nervosas)"));
        cbKitVesiculares.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Kit de atendimento � enfermidades vesiculares"));
        cbProcedimentos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Procedimentos no atendimento às notificações"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setEPI(cbEPI.getSelectionModel().getSelectedItem());
            formulario.setKitVesiculares(cbKitVesiculares.getSelectionModel().getSelectedItem());
            formulario.setKitMaterialBiologico(cbKitMaterialBiologico.getSelectionModel().getSelectedItem());
            formulario.setAcondicionamento(cbAcondicionamento.getSelectionModel().getSelectedItem());
            formulario.setProcedimentos(cbProcedimentos.getSelectionModel().getSelectedItem());
            formulario.setAgroprodutivo(cbAgroprodutivo.getSelectionModel().getSelectedItem());

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
        
        cbAcondicionamento.setItems(observableOpcoes);
        cbAgroprodutivo.setItems(observableOpcoes);
        cbEPI.setItems(observableOpcoes);
        cbKitMaterialBiologico.setItems(observableOpcoes);
        cbKitVesiculares.setItems(observableOpcoes);
        cbProcedimentos.setItems(observableOpcoes);
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
            
            FormCTOAtendimentoSuspeita formulario = gson.fromJson(resposta.getResposta(), FormCTOAtendimentoSuspeita.class);
            
            cbEPI.getSelectionModel().select(formulario.getEPI());
            cbKitVesiculares.getSelectionModel().select(formulario.getKitVesiculares());
            cbKitMaterialBiologico.getSelectionModel().select(formulario.getKitMaterialBiologico());
            cbAcondicionamento.getSelectionModel().select(formulario.getAcondicionamento());
            cbProcedimentos.getSelectionModel().select(formulario.getProcedimentos());
            cbAgroprodutivo.getSelectionModel().select(formulario.getAgroprodutivo());
            
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
        if (cbEPI.getSelectionModel().isEmpty()){
            error = true;
            lblEPI.setStyle("-fx-text-fill:red");
        }
        if (cbKitVesiculares.getSelectionModel().isEmpty()){
            error = true;
            lblKitVesiculares.setStyle("-fx-text-fill:red");
        }
        if (cbKitMaterialBiologico.getSelectionModel().isEmpty()){
            error = true;
            lblKitMaterialBiologico.setStyle("-fx-text-fill:red");
        }
        if (cbAcondicionamento.getSelectionModel().isEmpty()){
            error = true;
            lblAcondicionamento.setStyle("-fx-text-fill:red");
        }
        if (cbProcedimentos.getSelectionModel().isEmpty()){
            error = true;
            lblProcedimentos.setStyle("-fx-text-fill:red");
        }
        if (cbAgroprodutivo.getSelectionModel().isEmpty()){
            error = true;
            lblAgroprodutivo.setStyle("-fx-text-fill:red");
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
        
    public class FormCTOAtendimentoSuspeita {
        
        private String EPI;
        private String kitVesiculares;
        private String kitMaterialBiologico;
        private String acondicionamento;
        private String procedimentos;
        private String agroprodutivo;
        
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
        
        public String getEPI() {
            return EPI;
        }

        public void setEPI(String EPI) {
            this.EPI = EPI;
        }

        public String getKitVesiculares() {
            return kitVesiculares;
        }

        public void setKitVesiculares(String kitVesiculares) {
            this.kitVesiculares = kitVesiculares;
        }

        public String getKitMaterialBiologico() {
            return kitMaterialBiologico;
        }

        public void setKitMaterialBiologico(String kitMaterialBiologico) {
            this.kitMaterialBiologico = kitMaterialBiologico;
        }

        public String getAcondicionamento() {
            return acondicionamento;
        }

        public void setAcondicionamento(String acondicionamento) {
            this.acondicionamento = acondicionamento;
        }

        public String getProcedimentos() {
            return procedimentos;
        }

        public void setProcedimentos(String procedimentos) {
            this.procedimentos = procedimentos;
        }

        public String getAgroprodutivo() {
            return agroprodutivo;
        }

        public void setAgroprodutivo(String agroprodutivo) {
            this.agroprodutivo = agroprodutivo;
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
