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

public class FormCTOControleTransitoAnimaisController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormCTOControleTransitoAnimais formulario = new FormCTOControleTransitoAnimais();
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
    private JFXTextField nGTAEmitente;
    @FXML
    private JFXComboBox<String> cbCredenciamento;
    @FXML
    private JFXComboBox<String> cbManuais;
    @FXML
    private JFXComboBox<String> cbEmissaoGTA;
    @FXML
    private JFXComboBox<String> cbExigencias;
    @FXML
    private JFXComboBox<String> cbRelatorios;
    @FXML
    private JFXComboBox<String> cbGraficos;
    @FXML
    private JFXComboBox<String> cbOrganizacao;
    @FXML
    private JFXComboBox<String> cbGuias;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblGTAEmitente;
    @FXML
    private Label lblCredenciamento;
    @FXML
    private Label lblManuais;
    @FXML
    private Label lblEmissaoGTA;
    @FXML
    private Label lblExigencias;
    @FXML
    private Label lblRelatorios;
    @FXML
    private Label lblGraficos;
    @FXML
    private Label lblOrganizacao;
    @FXML
    private Label lblGuias;
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
        
        cbCredenciamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Credenciamento e descredenciamento dos emitentes"));
        cbEmissaoGTA.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Emissão de GTA"));
        cbExigencias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Exigências zoossanitárias"));
        cbGraficos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Gr�ficos da movimenta��o"));
        cbGuias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Guias de trânsito para subprodutos de origem animal"));
        cbManuais.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Manuais para preenchimento"));
        cbOrganizacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Organização das GTAs"));
        cbRelatorios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Relat�rios de emissão de GTA"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setnGTAEmitente(nGTAEmitente.getText());
            formulario.setCredenciamento(cbCredenciamento.getSelectionModel().getSelectedItem());
            formulario.setManuais(cbManuais.getSelectionModel().getSelectedItem());
            formulario.setEmissaoGTA(cbEmissaoGTA.getSelectionModel().getSelectedItem());
            formulario.setExigencias(cbExigencias.getSelectionModel().getSelectedItem());
            formulario.setRelatorios(cbRelatorios.getSelectionModel().getSelectedItem());
            formulario.setGraficos(cbGraficos.getSelectionModel().getSelectedItem());
            formulario.setOrganizacao(cbOrganizacao.getSelectionModel().getSelectedItem());
            formulario.setGuias(cbGuias.getSelectionModel().getSelectedItem());

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
        
        cbCredenciamento.setItems(observableOpcoes);
        cbEmissaoGTA.setItems(observableOpcoes);
        cbExigencias.setItems(observableOpcoes);
        cbGraficos.setItems(observableOpcoes);
        cbGuias.setItems(observableOpcoes);
        cbManuais.setItems(observableOpcoes);
        cbOrganizacao.setItems(observableOpcoes);
        cbRelatorios.setItems(observableOpcoes);
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
            
            FormCTOControleTransitoAnimais formulario = gson.fromJson(resposta.getResposta(), FormCTOControleTransitoAnimais.class);
            
            nGTAEmitente.setText(formulario.getnGTAEmitente());
            cbCredenciamento.getSelectionModel().select(formulario.getCredenciamento());
            cbEmissaoGTA.getSelectionModel().select(formulario.getEmissaoGTA());
            cbExigencias.getSelectionModel().select(formulario.getExigencias());
            cbGraficos.getSelectionModel().select(formulario.getGraficos());
            cbGuias.getSelectionModel().select(formulario.getGuias());
            cbManuais.getSelectionModel().select(formulario.getManuais());
            cbOrganizacao.getSelectionModel().select(formulario.getOrganizacao());
            cbRelatorios.getSelectionModel().select(formulario.getRelatorios());
            
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
        if (nGTAEmitente.getText() == null || nGTAEmitente.getText().length() == 0){
            error = true;
            lblGTAEmitente.setStyle("-fx-text-fill:red");
        }
        if (cbCredenciamento.getSelectionModel().isEmpty()){
            error = true;
            lblCredenciamento.setStyle("-fx-text-fill:red");
        }
        if (cbManuais.getSelectionModel().isEmpty()){
            error = true;
            lblManuais.setStyle("-fx-text-fill:red");
        }
        if (cbEmissaoGTA.getSelectionModel().isEmpty()){
            error = true;
            lblEmissaoGTA.setStyle("-fx-text-fill:red");
        }
        if (cbExigencias.getSelectionModel().isEmpty()){
            error = true;
            lblExigencias.setStyle("-fx-text-fill:red");
        }
        if (cbRelatorios.getSelectionModel().isEmpty()){
            error = true;
            lblRelatorios.setStyle("-fx-text-fill:red");
        }
        if (cbGraficos.getSelectionModel().isEmpty()){
            error = true;
            lblGraficos.setStyle("-fx-text-fill:red");
        }
        if (cbOrganizacao.getSelectionModel().isEmpty()){
            error = true;
            lblOrganizacao.setStyle("-fx-text-fill:red");
        }
        if (cbGuias.getSelectionModel().isEmpty()){
            error = true;
            lblGuias.setStyle("-fx-text-fill:red");
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
        
    public class FormCTOControleTransitoAnimais {
        
        private String nGTAEmitente;
        private String credenciamento;
        private String manuais;
        private String emissaoGTA;
        private String exigencias;
        private String relatorios;
        private String graficos;
        private String organizacao;
        private String guias;
        
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
        
        public String getnGTAEmitente() {
            return nGTAEmitente;
        }

        public void setnGTAEmitente(String nGTAEmitente) {
            this.nGTAEmitente = nGTAEmitente;
        }

        public String getCredenciamento() {
            return credenciamento;
        }

        public void setCredenciamento(String credenciamento) {
            this.credenciamento = credenciamento;
        }

        public String getManuais() {
            return manuais;
        }

        public void setManuais(String manuais) {
            this.manuais = manuais;
        }

        public String getEmissaoGTA() {
            return emissaoGTA;
        }

        public void setEmissaoGTA(String emissaoGTA) {
            this.emissaoGTA = emissaoGTA;
        }

        public String getExigencias() {
            return exigencias;
        }

        public void setExigencias(String exigencias) {
            this.exigencias = exigencias;
        }

        public String getRelatorios() {
            return relatorios;
        }

        public void setRelatorios(String relatorios) {
            this.relatorios = relatorios;
        }

        public String getGraficos() {
            return graficos;
        }

        public void setGraficos(String graficos) {
            this.graficos = graficos;
        }

        public String getOrganizacao() {
            return organizacao;
        }

        public void setOrganizacao(String organizacao) {
            this.organizacao = organizacao;
        }

        public String getGuias() {
            return guias;
        }

        public void setGuias(String guias) {
            this.guias = guias;
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
