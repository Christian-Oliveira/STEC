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

public class FormCTOControleEventosAglomeracaoController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormCTOControleEventosAglomeracao formulario = new FormCTOControleEventosAglomeracao();
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
    private JFXTextField nRecintos;
    @FXML
    private JFXTextField nEventosRealizados;
    @FXML
    private JFXTextField nEventosFiscalizados;
    @FXML
    private JFXTextField nAnimaisParticipantes;
    @FXML
    private JFXTextField nAnimaisInspecionados;
    @FXML
    private JFXComboBox<String> cbDocCadastramento;
    @FXML
    private JFXComboBox<String> cbDocRealizacao;
    @FXML
    private JFXComboBox<String> cbRegistro;
    @FXML
    private JFXComboBox<String> cbInspecao;
    @FXML
    private JFXComboBox<String> cbGTASaida;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblRecintos;
    @FXML
    private Label lblEventosRealizados;
    @FXML
    private Label lblEventosFiscalizados;
    @FXML
    private Label lblAnimaisParticipantes;
    @FXML
    private Label lblAnimaisInspecionados;
    @FXML
    private Label lblDocCadastramento;
    @FXML
    private Label lblDocRealizacao;
    @FXML
    private Label lblRegistro;
    @FXML
    private Label lblInspecao;
    @FXML
    private Label lblGTASaida;
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
        
        cbDocCadastramento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Documentação de cadastramento dos recintos"));
        cbDocRealizacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Documentação para realização do evento"));
        cbGTASaida.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Emissão de GTAs de sa�da"));
        cbInspecao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Inspeção clínica de animais"));
        cbRegistro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Registro de eventos"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setnRecintos(nRecintos.getText());
            formulario.setnEventosRealizados(nEventosRealizados.getText());
            formulario.setnEventosFiscalizados(nEventosRealizados.getText());
            formulario.setnAnimaisInspecionados(nAnimaisInspecionados.getText());
            formulario.setnAnimaisParticipantes(nAnimaisParticipantes.getText());
            formulario.setDocCadastramento(cbDocCadastramento.getSelectionModel().getSelectedItem());
            formulario.setDocRealizacao(cbDocRealizacao.getSelectionModel().getSelectedItem());
            formulario.setRegistro(cbRegistro.getSelectionModel().getSelectedItem());
            formulario.setInspecao(cbInspecao.getSelectionModel().getSelectedItem());
            formulario.setGTASaida(cbGTASaida.getSelectionModel().getSelectedItem());

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
        
        cbDocCadastramento.setItems(observableOpcoes);
        cbDocRealizacao.setItems(observableOpcoes);
        cbGTASaida.setItems(observableOpcoes);
        cbInspecao.setItems(observableOpcoes);
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
            
            FormCTOControleEventosAglomeracao formulario = gson.fromJson(resposta.getResposta(), FormCTOControleEventosAglomeracao.class);
            
            nRecintos.setText(formulario.getnRecintos());
            nEventosRealizados.setText(formulario.getnEventosRealizados());
            nEventosFiscalizados.setText(formulario.getnEventosFiscalizados());
            nAnimaisParticipantes.setText(formulario.getnAnimaisParticipantes());
            nAnimaisInspecionados.setText(formulario.getnAnimaisInspecionados());
            cbDocCadastramento.getSelectionModel().select(formulario.getDocCadastramento());
            cbDocRealizacao.getSelectionModel().select(formulario.getDocRealizacao());
            cbRegistro.getSelectionModel().select(formulario.getRegistro());
            cbInspecao.getSelectionModel().select(formulario.getInspecao());
            cbGTASaida.getSelectionModel().select(formulario.getGTASaida());
            
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
        if (nRecintos.getText() == null || nRecintos.getText().length() == 0){
            error = true;
            lblRecintos.setStyle("-fx-text-fill:red");
        }
        if (nEventosRealizados.getText() == null || nEventosRealizados.getText().length() == 0){
            error = true;
            lblEventosRealizados.setStyle("-fx-text-fill:red");
        }
        if (nEventosFiscalizados.getText() == null || nEventosFiscalizados.getText().length() == 0){
            error = true;
            lblEventosFiscalizados.setStyle("-fx-text-fill:red");
        }
        if (nAnimaisParticipantes.getText() == null || nAnimaisParticipantes.getText().length() == 0){
            error = true;
            lblAnimaisParticipantes.setStyle("-fx-text-fill:red");
        }
        if (nAnimaisInspecionados.getText() == null || nAnimaisInspecionados.getText().length() == 0){
            error = true;
            lblAnimaisInspecionados.setStyle("-fx-text-fill:red");
        }
        if (cbDocCadastramento.getSelectionModel().isEmpty()){
            error = true;
            lblDocCadastramento.setStyle("-fx-text-fill:red");
        }
        if (cbDocRealizacao.getSelectionModel().isEmpty()){
            error = true;
            lblDocRealizacao.setStyle("-fx-text-fill:red");
        }
        if (cbRegistro.getSelectionModel().isEmpty()){
            error = true;
            lblRegistro.setStyle("-fx-text-fill:red");
        }
        if (cbInspecao.getSelectionModel().isEmpty()){
            error = true;
            lblInspecao.setStyle("-fx-text-fill:red");
        }
        if (cbGTASaida.getSelectionModel().isEmpty()){
            error = true;
            lblGTASaida.setStyle("-fx-text-fill:red");
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
        
    public class FormCTOControleEventosAglomeracao {
        
        private String nRecintos;
        private String nEventosRealizados;
        private String nEventosFiscalizados;
        private String nAnimaisParticipantes;
        private String nAnimaisInspecionados;
        private String docCadastramento;
        private String docRealizacao;
        private String registro;
        private String inspecao;
        private String GTASaida;
        
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
        
        public String getnRecintos() {
            return nRecintos;
        }

        public void setnRecintos(String nRecintos) {
            this.nRecintos = nRecintos;
        }

        public String getnEventosRealizados() {
            return nEventosRealizados;
        }

        public void setnEventosRealizados(String nEventosRealizados) {
            this.nEventosRealizados = nEventosRealizados;
        }

        public String getnEventosFiscalizados() {
            return nEventosFiscalizados;
        }

        public void setnEventosFiscalizados(String nEventosFiscalizados) {
            this.nEventosFiscalizados = nEventosFiscalizados;
        }

        public String getnAnimaisParticipantes() {
            return nAnimaisParticipantes;
        }

        public void setnAnimaisParticipantes(String nAnimaisParticipantes) {
            this.nAnimaisParticipantes = nAnimaisParticipantes;
        }

        public String getnAnimaisInspecionados() {
            return nAnimaisInspecionados;
        }

        public void setnAnimaisInspecionados(String nAnimaisInspecionados) {
            this.nAnimaisInspecionados = nAnimaisInspecionados;
        }

        public String getDocCadastramento() {
            return docCadastramento;
        }

        public void setDocCadastramento(String docCadastramento) {
            this.docCadastramento = docCadastramento;
        }

        public String getDocRealizacao() {
            return docRealizacao;
        }

        public void setDocRealizacao(String docRealizacao) {
            this.docRealizacao = docRealizacao;
        }

        public String getRegistro() {
            return registro;
        }

        public void setRegistro(String registro) {
            this.registro = registro;
        }

        public String getInspecao() {
            return inspecao;
        }

        public void setInspecao(String inspecao) {
            this.inspecao = inspecao;
        }

        public String getGTASaida() {
            return GTASaida;
        }

        public void setGTASaida(String GTASaida) {
            this.GTASaida = GTASaida;
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
