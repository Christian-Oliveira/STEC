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

public class FormCTOCapacidadeDeteccaoPrecoceController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormCTOCapacidadeDeteccaoPrecoce formulario = new FormCTOCapacidadeDeteccaoPrecoce();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	ObservableList<String> observableOpcoes;
	ObservableList<String> observableAvaliacao;

	@FXML
	private JFXTextField vigilancia;
	@FXML
	private JFXTextField proprietario;
	@FXML
	private JFXTextField terceiros;
	@FXML
	private JFXTextField enfermidade;
	@FXML
	private JFXComboBox<String> cbFluxo;
	@FXML
	private JFXComboBox<String> cbParticipacao;
	@FXML
	private JFXComboBox<String> cbFonte;
	@FXML
	private JFXComboBox<String> cbRegistro;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
        @FXML
        private Label lblEnfermidade;
        @FXML
        private Label lblFluxo;
        @FXML
        private Label lblParticipacao;
        @FXML
        private Label lblFonte;
        @FXML
        private Label lblVigilancia;
        @FXML
        private Label lblProprietario;
        @FXML
        private Label lblTerceiros;
        @FXML
        private Label lblRegistro;
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
		
		cbFluxo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Fluxo de informações"));
		cbFonte.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Fontes de informação"));
		cbParticipacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Participação da comunidade"));
		cbRegistro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Registro das comunicações e atendimentos de ocorrência de enfermidades"));
	}
	
	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setTerceiros(terceiros.getText());
		formulario.setVigilancia(vigilancia.getText());
		formulario.setProprietario(proprietario.getText());
		formulario.setEnfermidade(enfermidade.getText());
		formulario.setFluxo(cbFluxo.getSelectionModel().getSelectedItem());
		formulario.setFonte(cbFonte.getSelectionModel().getSelectedItem());
		formulario.setParticipacao(cbParticipacao.getSelectionModel().getSelectedItem());
		formulario.setRegistro(cbRegistro.getSelectionModel().getSelectedItem());

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

	public void carregarComboboxOpcoes() {
		observableOpcoes = FXCollections.observableList(supervisaoDAO.opcoesSupervisao);
		observableAvaliacao = FXCollections.observableList(supervisaoDAO.opcoesAvaliacao);

		cbFluxo.setItems(observableOpcoes);
		cbFonte.setItems(observableOpcoes);
		cbParticipacao.setItems(observableOpcoes);
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

		// Recupera as informacoes do formulario caso exista a resposta
		if (resposta.getResposta() != null) {

			Gson gson = new Gson();

			FormCTOCapacidadeDeteccaoPrecoce formulario = gson.fromJson(resposta.getResposta(),
					FormCTOCapacidadeDeteccaoPrecoce.class);

			terceiros.setText(formulario.getTerceiros());
			vigilancia.setText(formulario.getVigilancia());
			proprietario.setText(formulario.getProprietario());
			enfermidade.setText(formulario.getEnfermidade());
			cbFluxo.getSelectionModel().select(formulario.getFluxo());
			cbFonte.getSelectionModel().select(formulario.getFonte());
			cbParticipacao.getSelectionModel().select(formulario.getParticipacao());
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
            if (vigilancia.getText() == null || vigilancia.getText().length() == 0){
                error = true;
                lblVigilancia.setStyle("-fx-text-fill:red");
            }
            if (proprietario.getText() == null || proprietario.getText().length() == 0){
                error = true;
                lblProprietario.setStyle("-fx-text-fill:red");
            }
            if (terceiros.getText() == null || terceiros.getText().length() == 0){
                error = true;
                lblTerceiros.setStyle("-fx-text-fill:red");
            }
            if (enfermidade.getText() == null || enfermidade.getText().length() == 0){
                error = true;
                lblEnfermidade.setStyle("-fx-text-fill:red");
            }
            if (cbFluxo.getSelectionModel().isEmpty()){
                error = true;
                lblFluxo.setStyle("-fx-text-fill:red");
            }
            if (cbParticipacao.getSelectionModel().isEmpty()){
                error = true;
                lblParticipacao.setStyle("-fx-text-fill:red");
            }
            if (cbFonte.getSelectionModel().isEmpty()){
                error = true;
                lblFonte.setStyle("-fx-text-fill:red");
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

	public class FormCTOCapacidadeDeteccaoPrecoce {

		private String vigilancia;
		private String proprietario;
		private String terceiros;
		private String enfermidade;
		private String fluxo;
		private String participacao;
		private String fonte;
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

		public String getVigilancia() {
			return vigilancia;
		}

		public void setVigilancia(String vigilancia) {
			this.vigilancia = vigilancia;
		}

		public String getProprietario() {
			return proprietario;
		}

		public void setProprietario(String proprietario) {
			this.proprietario = proprietario;
		}

		public String getTerceiros() {
			return terceiros;
		}

		public void setTerceiros(String terceiros) {
			this.terceiros = terceiros;
		}

		public String getEnfermidade() {
			return enfermidade;
		}

		public void setEnfermidade(String enfermidade) {
			this.enfermidade = enfermidade;
		}

		public String getFluxo() {
			return fluxo;
		}

		public void setFluxo(String fluxo) {
			this.fluxo = fluxo;
		}

		public String getParticipacao() {
			return participacao;
		}

		public void setParticipacao(String participacao) {
			this.participacao = participacao;
		}

		public String getFonte() {
			return fonte;
		}

		public void setFonte(String fonte) {
			this.fonte = fonte;
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
