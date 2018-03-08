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

public class FormCTOFiscalizacaoController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormCTOFiscalizacao formulario = new FormCTOFiscalizacao();
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
    private JFXTextField nRevendasTotal;
    @FXML
    private JFXTextField nRevendasVacinas;    
    @FXML
    private JFXTextField nRevendasFiscalizadas;
    @FXML
    private JFXComboBox<String> cbCadastramento;
    @FXML
    private JFXComboBox<String> cbFrequenciaFiscalizacao;
    @FXML
    private JFXComboBox<String> cbDiarias;
    @FXML
    private JFXComboBox<String> cbSemanais;
    @FXML
    private JFXComboBox<String> cbSupervisaoRevenda;
    @FXML
    private JFXComboBox<String> cbApreensaoVacinas;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblRevendasTotal;
    @FXML
    private Label lblRevendasVacinas;
    @FXML
    private Label lblCadastramento;
    @FXML
    private Label lblRevendasFiscalizadas;
    @FXML
    private Label lblFrequenciaFiscalizacao;
    @FXML
    private Label lblDiarias;
    @FXML
    private Label lblSemanais;
    @FXML
    private Label lblSupervisaoRevenda;
    @FXML
    private Label lblApreensaoVacinas;
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
        
        cbCadastramento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Cadastramento de Revendas Veterinárias"));
        cbFrequenciaFiscalizacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Frequência de fiscalizações em revendas"));
        cbDiarias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Diárias"));
        cbSemanais.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Semanais"));
        cbSupervisaoRevenda.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Supervisão na revenda veterinária"));
        cbApreensaoVacinas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Apreensão de vacinas"));
    }    
    
    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setnRevendasTotal(nRevendasTotal.getText());
            formulario.setnRevendasVacinas(nRevendasVacinas.getText());
            formulario.setnRevendasFiscalizadas(nRevendasFiscalizadas.getText());
            formulario.setCadastramento(cbCadastramento.getSelectionModel().getSelectedItem());
            formulario.setFrequenciaFiscalizacao(cbFrequenciaFiscalizacao.getSelectionModel().getSelectedItem());
            formulario.setDiarias(cbDiarias.getSelectionModel().getSelectedItem());
            formulario.setSemanais(cbSemanais.getSelectionModel().getSelectedItem());
            formulario.setSupervisaoRevenda(cbSupervisaoRevenda.getSelectionModel().getSelectedItem());
            formulario.setApreensaoVacinas(cbApreensaoVacinas.getSelectionModel().getSelectedItem());

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
        
        cbCadastramento.setItems(observableOpcoes);
        cbFrequenciaFiscalizacao.setItems(observableOpcoes);
        cbDiarias.setItems(observableOpcoes);
        cbSemanais.setItems(observableOpcoes);
        cbSupervisaoRevenda.setItems(observableOpcoes);
        cbApreensaoVacinas.setItems(observableOpcoes);
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
            
            FormCTOFiscalizacao formulario = gson.fromJson(resposta.getResposta(), FormCTOFiscalizacao.class);
            
            nRevendasTotal.setText(formulario.getnRevendasTotal());
            nRevendasVacinas.setText(formulario.getnRevendasVacinas());
            nRevendasFiscalizadas.setText(formulario.getnRevendasFiscalizadas());
            cbCadastramento.getSelectionModel().select(formulario.getCadastramento());
            cbFrequenciaFiscalizacao.getSelectionModel().select(formulario.getFrequenciaFiscalizacao());
            cbDiarias.getSelectionModel().select(formulario.getDiarias());
            cbSemanais.getSelectionModel().select(formulario.getSemanais());
            cbSupervisaoRevenda.getSelectionModel().select(formulario.getSupervisaoRevenda());
            cbApreensaoVacinas.getSelectionModel().select(formulario.getApreensaoVacinas());
            
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
        if (nRevendasTotal.getText() == null || nRevendasTotal.getText().length() == 0){
            error = true;
            lblRevendasTotal.setStyle("-fx-text-fill:red");
        }
        if (nRevendasVacinas.getText() == null || nRevendasVacinas.getText().length() == 0){
            error = true;
            lblRevendasVacinas.setStyle("-fx-text-fill:red");
        }
        if (cbCadastramento.getSelectionModel().isEmpty()){
            error = true;
            lblCadastramento.setStyle("-fx-text-fill:red");
        }
        if (nRevendasFiscalizadas.getText() == null || nRevendasFiscalizadas.getText().length() == 0){
            error = true;
            lblRevendasFiscalizadas.setStyle("-fx-text-fill:red");
        }
        if (cbFrequenciaFiscalizacao.getSelectionModel().isEmpty()){
            error = true;
            lblFrequenciaFiscalizacao.setStyle("-fx-text-fill:red");
        }
        if (cbDiarias.getSelectionModel().isEmpty()){
            error = true;
            lblDiarias.setStyle("-fx-text-fill:red");
        }
        if (cbSemanais.getSelectionModel().isEmpty()){
            error = true;
            lblSemanais.setStyle("-fx-text-fill:red");
        }
        if (cbSupervisaoRevenda.getSelectionModel().isEmpty()){
            error = true;
            lblSupervisaoRevenda.setStyle("-fx-text-fill:red");
        }
        if (cbApreensaoVacinas.getSelectionModel().isEmpty()){
            error = true;
            lblApreensaoVacinas.setStyle("-fx-text-fill:red");
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
        
    public class FormCTOFiscalizacao {
        
        private String nRevendasTotal;
        private String nRevendasVacinas;
        private String nRevendasFiscalizadas;
        private String cadastramento;
        private String frequenciaFiscalizacao;
        private String diarias;
        private String semanais;
        private String supervisaoRevenda;
        private String apreensaoVacinas;
        
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
        
        public String getnRevendasTotal() {
            return nRevendasTotal;
        }

        public void setnRevendasTotal(String nRevendasTotal) {
            this.nRevendasTotal = nRevendasTotal;
        }

        public String getnRevendasVacinas() {
            return nRevendasVacinas;
        }

        public void setnRevendasVacinas(String nRevendasVacinas) {
            this.nRevendasVacinas = nRevendasVacinas;
        }

        public String getnRevendasFiscalizadas() {
            return nRevendasFiscalizadas;
        }

        public void setnRevendasFiscalizadas(String nRevendasFiscalizadas) {
            this.nRevendasFiscalizadas = nRevendasFiscalizadas;
        }

        public String getCadastramento() {
            return cadastramento;
        }

        public void setCadastramento(String cadastramento) {
            this.cadastramento = cadastramento;
        }

        public String getFrequenciaFiscalizacao() {
            return frequenciaFiscalizacao;
        }

        public void setFrequenciaFiscalizacao(String frequenciaFiscalizacao) {
            this.frequenciaFiscalizacao = frequenciaFiscalizacao;
        }

        public String getDiarias() {
            return diarias;
        }

        public void setDiarias(String diarias) {
            this.diarias = diarias;
        }

        public String getSemanais() {
            return semanais;
        }

        public void setSemanais(String semanais) {
            this.semanais = semanais;
        }

        public String getSupervisaoRevenda() {
            return supervisaoRevenda;
        }

        public void setSupervisaoRevenda(String supervisaoRevenda) {
            this.supervisaoRevenda = supervisaoRevenda;
        }

        public String getApreensaoVacinas() {
            return apreensaoVacinas;
        }

        public void setApreensaoVacinas(String apreensaoVacinas) {
            this.apreensaoVacinas = apreensaoVacinas;
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
