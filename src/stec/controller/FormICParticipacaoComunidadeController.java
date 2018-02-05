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

public class FormICParticipacaoComunidadeController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormICParticipacaoComunidade formulario = new FormICParticipacaoComunidade();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    ObservableList<String> observableOpcoes;
    ObservableList<String> observableAvaliacao;
    
    @FXML
    private JFXComboBox<String> cbInteracaoComunidade;
    @FXML
    private JFXComboBox<String> cbComusa;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblInteracaoComunidade;
    @FXML
    private Label lblComusa;
    @FXML
    private Label lblAvaliacao;
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
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
        
        cbComusa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "COMUSA"));
        cbInteracaoComunidade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Interação com a comunidade"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setComusa(cbComusa.getSelectionModel().getSelectedItem());
            formulario.setInteracaoComunidade(cbInteracaoComunidade.getSelectionModel().getSelectedItem());

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
        
        cbComusa.setItems(observableOpcoes);
        cbInteracaoComunidade.setItems(observableOpcoes);
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
            
            FormICParticipacaoComunidade formulario = gson.fromJson(resposta.getResposta(), FormICParticipacaoComunidade.class);
            
            cbComusa.getSelectionModel().select(formulario.getComusa());
            cbInteracaoComunidade.getSelectionModel().select(formulario.getInteracaoComunidade());
            
            txtComentarios.setText(formulario.getComentario());
            txtPrazo.setText(formulario.getPrazo());
            txtRUC.setText(formulario.getRecomendacaoUC());
            txtRULSAVEAC.setText(formulario.getRecomendacaoUlsavEac());
            txtRUR.setText(formulario.getRecomendacaoUr());
            cbAvaliacao.getSelectionModel().select(formulario.getAvaliacao());
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
        if (cbInteracaoComunidade.getSelectionModel().isEmpty()){
            error = true;
            lblInteracaoComunidade.setStyle("-fx-text-fill:red");
        }
        if (cbComusa.getSelectionModel().isEmpty()){
            error = true;
            lblComusa.setStyle("-fx-text-fill:red");
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
        
    public class FormICParticipacaoComunidade {
        
        private String interacaoComunidade;
        private String comusa;
        
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

        public String getInteracaoComunidade() {
            return interacaoComunidade;
        }

        public void setInteracaoComunidade(String interacaoComunidade) {
            this.interacaoComunidade = interacaoComunidade;
        }

        public String getComusa() {
            return comusa;
        }

        public void setComusa(String comusa) {
            this.comusa = comusa;
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
