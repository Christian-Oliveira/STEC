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

public class FormAGBaseLegalController implements Initializable, IFormulario {

    Resposta resposta = new Resposta();
    FormAGBaseLegal formulario = new FormAGBaseLegal();
    private boolean btConfirmarClicked = false;
    private Stage dialog;

    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

    ObservableList<String> observableOpcoes;
    ObservableList<String> observableAvaliacao;

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
    @FXML
    private JFXComboBox<String> cbLegislacao;
    @FXML
    private JFXComboBox<String> cbManuais;
    @FXML
    private JFXComboBox<String> cbPOP;
    @FXML
    private JFXComboBox<String> cbAplicacao;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblLegislacao;
    @FXML
    private Label lblManuais;
    @FXML
    private Label lblPOP;
    @FXML
    private Label lblAplicacao;
    @FXML
    private Label lblAvaliacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboboxOpcoes();

        cbAplicacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Aplicação de procedimentos"));
        cbLegislacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Legislação"));
        cbManuais.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Manuais"));
        cbPOP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "POP"));
    }

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setAplicacao(cbAplicacao.getSelectionModel().getSelectedItem());
            formulario.setLegislacao(cbLegislacao.getSelectionModel().getSelectedItem());
            formulario.setManuais(cbManuais.getSelectionModel().getSelectedItem());
            formulario.setPOP(cbPOP.getSelectionModel().getSelectedItem());

            formulario.setAvaliacao(cbAvaliacao.getSelectionModel().getSelectedItem());
            formulario.setComentario(txtComentarios.getText());
            formulario.setRecomendacaoUlsavEac(txtRULSAVEAC.getText());
            formulario.setPrazo(txtPrazo.getText());
            formulario.setRecomendacaoUr(txtRUR.getText());
            formulario.setRecomendacaoUC(txtRUC.getText());

            // passa a resposta do fromulario como json
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

        // Recupera as informacoes do formulario caso exista a resposta
        if (resposta.getResposta() != null) {

            Gson gson = new Gson();

            FormAGBaseLegal formulario = gson.fromJson(resposta.getResposta(), FormAGBaseLegal.class);

            cbAplicacao.getSelectionModel().select(formulario.getAplicacao());
            cbLegislacao.getSelectionModel().select(formulario.getLegislacao());
            cbManuais.getSelectionModel().select(formulario.getManuais());
            cbPOP.getSelectionModel().select(formulario.getPOP());

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

    public void carregarComboboxOpcoes() {
        observableOpcoes = FXCollections.observableList(supervisaoDAO.opcoesSupervisao);
        observableAvaliacao = FXCollections.observableList(supervisaoDAO.opcoesAvaliacao);

        cbAplicacao.setItems(observableOpcoes);
        cbLegislacao.setItems(observableOpcoes);
        cbManuais.setItems(observableOpcoes);
        cbPOP.setItems(observableOpcoes);
        cbAvaliacao.setItems(observableAvaliacao);
    }

    public void adicionarComentario(String value, String label) {
        if (value.equals("NE") || value.equals("ED")) {
            txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
        }
    }
    
    //Função para validar dados
    @Override
    public boolean validarDados(){
        boolean error = false;
        if (cbLegislacao.getSelectionModel().isEmpty()){
            error = true;
            lblLegislacao.setStyle("-fx-text-fill:red");
        }
        if (cbManuais.getSelectionModel().isEmpty()){
            error = true;
            lblManuais.setStyle("-fx-text-fill:red");
        }
        if (cbPOP.getSelectionModel().isEmpty()){
            error = true;
            lblPOP.setStyle("-fx-text-fill:red");
        }
        if (cbAplicacao.getSelectionModel().isEmpty()){
            error = true;
            lblAplicacao.setStyle("-fx-text-fill:red");
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

    public class FormAGBaseLegal {

        private String legislacao;
        private String manuais;
        private String POP;
        private String aplicacao;

        private String avaliacao;
        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;

        public String getLegislacao() {
            return legislacao;
        }

        public void setLegislacao(String legislacao) {
            this.legislacao = legislacao;
        }

        public String getManuais() {
            return manuais;
        }

        public void setManuais(String manuais) {
            this.manuais = manuais;
        }

        public String getPOP() {
            return POP;
        }

        public void setPOP(String POP) {
            this.POP = POP;
        }

        public String getAplicacao() {
            return aplicacao;
        }

        public void setAplicacao(String aplicacao) {
            this.aplicacao = aplicacao;
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

        public String getAvaliacao() {
            return avaliacao;
        }

        public void setAvaliacao(String avaliacao) {
            this.avaliacao = avaliacao;
        }
    }
}
