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

public class FormCTOControleCadastroController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormCTOControleCadastro formulario = new FormCTOControleCadastro();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    ObservableList<String> observableOpcoes;
    ObservableList<String> observableAvaliacao;
    
    @FXML
    private JFXTextField nPropriedades;
    @FXML
    private JFXTextField nProdutores;
    @FXML
    private JFXTextField bovina;
    @FXML
    private JFXTextField bubalina;
    @FXML
    private JFXTextField suidea;
    @FXML
    private JFXTextField ovina;
    @FXML
    private JFXTextField caprina;
    @FXML
    private JFXTextField equina;
    @FXML
    private JFXTextField muar;
    @FXML
    private JFXTextField asinina;
    @FXML
    private JFXTextField avicola;
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXComboBox<String> cbInfoProdutoresPropriedades;
    @FXML
    private JFXComboBox<String> cbDocumentacao;
    @FXML
    private JFXComboBox<String> cbProcedimentos;
    @FXML
    private JFXComboBox<String> cbRegistro;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
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
    private Label lblPropriedades;
    @FXML
    private Label lblProdutores;
    @FXML
    private Label lblInfoProdutoresPropriedades;
    @FXML
    private Label lblDocumentacao;
    @FXML
    private Label lblProcedimentos;
    @FXML
    private Label lblRegistro;
    @FXML
    private Label lblAvaliacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboboxOpcoes();
        
        cbDocumentacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Documentação referente ao cadastramento de propriedade, produtores rurais e explorações pecuárias"));
        cbInfoProdutoresPropriedades.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Informações de produtores, propriedades e efetivos de todos os municípios da jurisdição"));
        cbProcedimentos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Procedimentos para abertura e encerramento de cadastros"));
        cbRegistro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Registro de entrada, sa�da e saldo nas fichas de movimentação"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setnProdutores(nProdutores.getText());
            formulario.setnPropriedades(nPropriedades.getText());
            formulario.setDocumentacao(cbDocumentacao.getSelectionModel().getSelectedItem());
            formulario.setInfoProdutoresPropriedades(cbInfoProdutoresPropriedades.getSelectionModel().getSelectedItem());
            formulario.setProcedimentos(cbProcedimentos.getSelectionModel().getSelectedItem());
            formulario.setRegistro(cbRegistro.getSelectionModel().getSelectedItem());
            formulario.setBovina(bovina.getText());
            formulario.setBubalina(bubalina.getText());
            formulario.setSuidea(suidea.getText());
            formulario.setOvina(ovina.getText());
            formulario.setCaprina(caprina.getText());
            formulario.setEquina(equina.getText());
            formulario.setMuar(muar.getText());
            formulario.setAsinina(asinina.getText());
            formulario.setAvicola(avicola.getText());        

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
        
        cbDocumentacao.setItems(observableOpcoes);
        cbInfoProdutoresPropriedades.setItems(observableOpcoes);
        cbProcedimentos.setItems(observableOpcoes);
        cbRegistro.setItems(observableOpcoes);
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
            
            FormCTOControleCadastro formulario = gson.fromJson(resposta.getResposta(), FormCTOControleCadastro.class);
            
            nProdutores.setText(formulario.getnProdutores());
            nPropriedades.setText(formulario.getnPropriedades());
            cbDocumentacao.getSelectionModel().select(formulario.getDocumentacao());
            cbInfoProdutoresPropriedades.getSelectionModel().select(formulario.getInfoProdutoresPropriedades());
            cbProcedimentos.getSelectionModel().select(formulario.getProcedimentos());
            cbRegistro.getSelectionModel().select(formulario.getRegistro());
            bovina.setText(formulario.getBovina());
            bubalina.setText(formulario.getBubalina());
            suidea.setText(formulario.getSuidea());
            ovina.setText(formulario.getOvina());
            caprina.setText(formulario.getCaprina());
            equina.setText(formulario.getEquina());
            muar.setText(formulario.getMuar());
            asinina.setText(formulario.getAsinina());
            avicola.setText(formulario.getAvicola());
            
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
        if (nPropriedades.getText() == null || nPropriedades.getText().length() == 0){
            error = true;
            lblPropriedades.setStyle("-fx-text-fill:red");
        }
        if (nProdutores.getText() == null || nProdutores.getText().length() == 0){
            error = true;
            lblProdutores.setStyle("-fx-text-fill:red");
        }
        if (cbInfoProdutoresPropriedades.getSelectionModel().isEmpty()){
            error = true;
            lblInfoProdutoresPropriedades.setStyle("-fx-text-fill:red");
        }
        if (cbDocumentacao.getSelectionModel().isEmpty()){
            error = true;
            lblDocumentacao.setStyle("-fx-text-fill:red");
        }
        if (cbProcedimentos.getSelectionModel().isEmpty()){
            error = true;
            lblProcedimentos.setStyle("-fx-text-fill:red");
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
            
    public class FormCTOControleCadastro {
        
        private String nPropriedades;
        private String nProdutores;
        private String bovina;
        private String bubalina;
        private String suidea;
        private String ovina;
        private String caprina;
        private String equina;
        private String muar;
        private String asinina;
        private String avicola;
        private String infoProdutoresPropriedades;
        private String documentacao;
        private String procedimentos;
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
        
        public String getnPropriedades() {
            return nPropriedades;
        }

        public void setnPropriedades(String nPropriedades) {
            this.nPropriedades = nPropriedades;
        }

        public String getnProdutores() {
            return nProdutores;
        }

        public void setnProdutores(String nProdutores) {
            this.nProdutores = nProdutores;
        }

        public String getBovina() {
            return bovina;
        }

        public void setBovina(String bovina) {
            this.bovina = bovina;
        }

        public String getBubalina() {
            return bubalina;
        }

        public void setBubalina(String bubalina) {
            this.bubalina = bubalina;
        }

        public String getSuidea() {
            return suidea;
        }

        public void setSuidea(String suidea) {
            this.suidea = suidea;
        }

        public String getOvina() {
            return ovina;
        }

        public void setOvina(String ovina) {
            this.ovina = ovina;
        }

        public String getCaprina() {
            return caprina;
        }

        public void setCaprina(String caprina) {
            this.caprina = caprina;
        }

        public String getEquina() {
            return equina;
        }

        public void setEquina(String equina) {
            this.equina = equina;
        }

        public String getMuar() {
            return muar;
        }

        public void setMuar(String muar) {
            this.muar = muar;
        }

        public String getAsinina() {
            return asinina;
        }

        public void setAsinina(String asinina) {
            this.asinina = asinina;
        }

        public String getAvicola() {
            return avicola;
        }

        public void setAvicola(String avicola) {
            this.avicola = avicola;
        }

        public String getInfoProdutoresPropriedades() {
            return infoProdutoresPropriedades;
        }

        public void setInfoProdutoresPropriedades(String infoProdutoresPropriedades) {
            this.infoProdutoresPropriedades = infoProdutoresPropriedades;
        }

        public String getDocumentacao() {
            return documentacao;
        }

        public void setDocumentacao(String documentacao) {
            this.documentacao = documentacao;
        }

        public String getProcedimentos() {
            return procedimentos;
        }

        public void setProcedimentos(String procedimentos) {
            this.procedimentos = procedimentos;
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
