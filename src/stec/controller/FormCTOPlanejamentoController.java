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

public class FormCTOPlanejamentoController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormCTOPlanejamento formulario = new FormCTOPlanejamento();
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
    private JFXComboBox<String> cbPlanejamento;
    @FXML
    private JFXComboBox<String> cbRegistro;
    @FXML
    private JFXComboBox<String> cbCumprimento;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblPlanejamento;
    @FXML
    private Label lblRegistro;
    @FXML
    private Label lblCumprimento;
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
        
        cbCumprimento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Cumprimento das metas propostas pelos programas sanitários"));
        cbPlanejamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Planejamento Técnico Mensal"));
        cbRegistro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Registro das ações planejadas"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setCumprimento(cbCumprimento.getSelectionModel().getSelectedItem());
            formulario.setPlanejamento(cbPlanejamento.getSelectionModel().getSelectedItem());
            formulario.setRegistro(cbRegistro.getSelectionModel().getSelectedItem());

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
        
        cbCumprimento.setItems(observableOpcoes);
        cbPlanejamento.setItems(observableOpcoes);
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
            
            FormCTOPlanejamento formulario = gson.fromJson(resposta.getResposta(), FormCTOPlanejamento.class);
            
            cbCumprimento.getSelectionModel().select(formulario.getCumprimento());
            cbPlanejamento.getSelectionModel().select(formulario.getPlanejamento());
            cbRegistro.getSelectionModel().select(formulario.getRegistro());
            
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
        if (cbPlanejamento.getSelectionModel().isEmpty()){
            error = true;
            lblPlanejamento.setStyle("-fx-text-fill:red");
        }
        if (cbRegistro.getSelectionModel().isEmpty()){
            error = true;
            lblRegistro.setStyle("-fx-text-fill:red");
        }
        if (cbCumprimento.getSelectionModel().isEmpty()){
            error = true;
            lblCumprimento.setStyle("-fx-text-fill:red");
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
        
    public class FormCTOPlanejamento {
        
        private String planejamento;
        private String registro;
        private String cumprimento;
        
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
        
        public String getPlanejamento() {
            return planejamento;
        }

        public void setPlanejamento(String planejamento) {
            this.planejamento = planejamento;
        }

        public String getRegistro() {
            return registro;
        }

        public void setRegistro(String registro) {
            this.registro = registro;
        }

        public String getCumprimento() {
            return cumprimento;
        }

        public void setCumprimento(String cumprimento) {
            this.cumprimento = cumprimento;
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
