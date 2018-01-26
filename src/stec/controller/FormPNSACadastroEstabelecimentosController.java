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

public class FormPNSACadastroEstabelecimentosController implements Initializable, IFormulario {

    Resposta resposta = new Resposta();
    FormPNSACadastroEstabelecimentos formulario = new FormPNSACadastroEstabelecimentos();
    private boolean btConfirmarClicked = false;
    private Stage dialog;

    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

    ObservableList<String> observableOpcoes;

    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXComboBox<String> cbEstabelecimentos;
    @FXML
    private JFXComboBox<String> cbProcedimentos;
    @FXML
    private JFXTextField nAviculturaComeciaisCadastrados;
    @FXML
    private JFXTextField nAviculturaComerciaisRegistrados;
    @FXML
    private JFXTextField nAviculturaSubsistencia;
    @FXML
    private JFXTextField nAviculturaVendaVivas;
    @FXML
    private Label lblEstabelecimentos;
    @FXML
    private Label lblProcedimentos;
    @FXML
    private Label lblAviculturaComeciaisCadastrados;
    @FXML
    private Label lblAviculturaComerciaisRegistrados;
    @FXML
    private Label lblAviculturaSubsistencia;
    @FXML
    private Label lblAviculturaVendaVivas;
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

        cbEstabelecimentos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
                newValue) -> adicionarComentario(newValue, "Estabelecimentos de criação cadastrados"));
        cbProcedimentos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
                newValue) -> adicionarComentario(newValue, "Procedimentos relacionados ao cadastramento"));
    }

    public void adicionarComentario(String value, String label) {
        if (value.equals("NE") || value.equals("ED")) {
            txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
        }
    }

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setEstabelecimentos(cbEstabelecimentos.getSelectionModel().getSelectedItem());
            formulario.setProcedimentos(cbProcedimentos.getSelectionModel().getSelectedItem());
            formulario.setnAviculturaComeciaisCadastrados(nAviculturaComeciaisCadastrados.getText());
            formulario.setnAviculturaComerciaisRegistrados(nAviculturaComerciaisRegistrados.getText());
            formulario.setnAviculturaSubsistencia(nAviculturaSubsistencia.getText());
            formulario.setnAviculturaVendaVivas(nAviculturaVendaVivas.getText());

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

    public void carregarComboboxOpcoes() {
        observableOpcoes = FXCollections.observableList(supervisaoDAO.opcoesSupervisao);

        cbEstabelecimentos.setItems(observableOpcoes);
        cbProcedimentos.setItems(observableOpcoes);
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

            FormPNSACadastroEstabelecimentos formulario = gson.fromJson(resposta.getResposta(),
                    FormPNSACadastroEstabelecimentos.class);

            cbEstabelecimentos.getSelectionModel().select(formulario.getEstabelecimentos());
            cbProcedimentos.getSelectionModel().select(formulario.getProcedimentos());
            nAviculturaComeciaisCadastrados.setText(formulario.getnAviculturaComeciaisCadastrados());
            nAviculturaComerciaisRegistrados.setText(formulario.getnAviculturaComerciaisRegistrados());
            nAviculturaSubsistencia.setText(formulario.getnAviculturaSubsistencia());
            nAviculturaVendaVivas.setText(formulario.getnAviculturaVendaVivas());

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
        if (cbEstabelecimentos.getSelectionModel().isEmpty()){
            error = true;
            lblEstabelecimentos.setStyle("-fx-text-fill:red");
        }
        if (cbProcedimentos.getSelectionModel().isEmpty()){
            error = true;
            lblProcedimentos.setStyle("-fx-text-fill:red");
        }
        if (nAviculturaComeciaisCadastrados.getText() == null || nAviculturaComeciaisCadastrados.getText().length() == 0){
            error = true;
            lblAviculturaComeciaisCadastrados.setStyle("-fx-text-fill:red");
        }
        if (nAviculturaComerciaisRegistrados.getText() == null || nAviculturaComerciaisRegistrados.getText().length() == 0){
            error = true;
            lblAviculturaComerciaisRegistrados.setStyle("-fx-text-fill:red");
        }
        if (nAviculturaSubsistencia.getText() == null || nAviculturaSubsistencia.getText().length() == 0){
            error = true;
            lblAviculturaSubsistencia.setStyle("-fx-text-fill:red");
        }
        if (nAviculturaVendaVivas.getText() == null || nAviculturaVendaVivas.getText().length() == 0){
            error = true;
            lblAviculturaVendaVivas.setStyle("-fx-text-fill:red");
        }
        if (error == false){
            return true;
        }else{
            AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
            return false;
        }
    }

    public class FormPNSACadastroEstabelecimentos {

        private String estabelecimentos;
        private String procedimentos;
        private String nAviculturaComeciaisCadastrados;
        private String nAviculturaComerciaisRegistrados;
        private String nAviculturaSubsistencia;
        private String nAviculturaVendaVivas;

        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;

        public String getEstabelecimentos() {
            return estabelecimentos;
        }

        public void setEstabelecimentos(String estabelecimentos) {
            this.estabelecimentos = estabelecimentos;
        }

        public String getProcedimentos() {
            return procedimentos;
        }

        public void setProcedimentos(String procedimentos) {
            this.procedimentos = procedimentos;
        }

        public String getnAviculturaComeciaisCadastrados() {
            return nAviculturaComeciaisCadastrados;
        }

        public void setnAviculturaComeciaisCadastrados(String nAviculturaComeciaisCadastrados) {
            this.nAviculturaComeciaisCadastrados = nAviculturaComeciaisCadastrados;
        }

        public String getnAviculturaComerciaisRegistrados() {
            return nAviculturaComerciaisRegistrados;
        }

        public void setnAviculturaComerciaisRegistrados(String nAviculturaComerciaisRegistrados) {
            this.nAviculturaComerciaisRegistrados = nAviculturaComerciaisRegistrados;
        }

        public String getnAviculturaSubsistencia() {
            return nAviculturaSubsistencia;
        }

        public void setnAviculturaSubsistencia(String nAviculturaSubsistencia) {
            this.nAviculturaSubsistencia = nAviculturaSubsistencia;
        }

        public String getnAviculturaVendaVivas() {
            return nAviculturaVendaVivas;
        }

        public void setnAviculturaVendaVivas(String nAviculturaVendaVivas) {
            this.nAviculturaVendaVivas = nAviculturaVendaVivas;
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
