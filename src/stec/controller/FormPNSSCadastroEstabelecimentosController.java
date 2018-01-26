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

public class FormPNSSCadastroEstabelecimentosController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormPNSSCadastroEstabelecimento formulario = new FormPNSSCadastroEstabelecimento();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	ObservableList<String> observableOpcoes;

	@FXML
	private JFXButton btAdicionar;
	@FXML
	private JFXButton btCancelar;
	@FXML
	private JFXComboBox<String> cbEstabelecimentosCadastrados;
	@FXML
	private JFXComboBox<String> cbAtualizacaoCadastral;
	@FXML
	private JFXComboBox<String> cbSuideos;
	@FXML
	private JFXTextField nGranjas;
	@FXML
	private JFXTextField nCriatorios;
        @FXML
        private Label lblEstabelecimentosCadastrados;
        @FXML
        private Label lblGranjas;
        @FXML
        private Label lblCriatorios;
        @FXML
        private Label lblAtualizacaoCadastral;
        @FXML
        private Label lblSuideos;
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

		cbAtualizacaoCadastral.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Atualização cadastral"));
		cbEstabelecimentosCadastrados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Estabelecimentos de criação cadastrados"));
		cbSuideos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Suídeos asselvajados"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setAtualizacaoCadastral(cbAtualizacaoCadastral.getSelectionModel().getSelectedItem());
		formulario.setEstabelecimentoCadastrados(cbEstabelecimentosCadastrados.getSelectionModel().getSelectedItem());
		formulario.setSuideos(cbSuideos.getSelectionModel().getSelectedItem());
		formulario.setnCriatorios(nCriatorios.getText());
		formulario.setnGranjas(nGranjas.getText());

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

		cbAtualizacaoCadastral.setItems(observableOpcoes);
		cbEstabelecimentosCadastrados.setItems(observableOpcoes);
		cbSuideos.setItems(observableOpcoes);
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

			FormPNSSCadastroEstabelecimento formulario = gson.fromJson(resposta.getResposta(),
					FormPNSSCadastroEstabelecimento.class);

			cbAtualizacaoCadastral.getSelectionModel().select(formulario.getAtualizacaoCadastral());
			cbEstabelecimentosCadastrados.getSelectionModel().select(formulario.getEstabelecimentoCadastrados());
			cbSuideos.getSelectionModel().select(formulario.getSuideos());
			nCriatorios.setText(formulario.getnCriatorios());
			nGranjas.setText(formulario.getnGranjas());

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
            if (cbEstabelecimentosCadastrados.getSelectionModel().isEmpty()){
                error = true;
                lblEstabelecimentosCadastrados.setStyle("-fx-text-fill:red");
            }
            if (nGranjas.getText() == null || nGranjas.getText().length() == 0){
                error = true;
                lblGranjas.setStyle("-fx-text-fill:red");
            }
            if (nCriatorios.getText() == null || nCriatorios.getText().length() == 0){
                error = true;
                lblCriatorios.setStyle("-fx-text-fill:red");
            }
            if (cbAtualizacaoCadastral.getSelectionModel().isEmpty()){
                error = true;
                lblAtualizacaoCadastral.setStyle("-fx-text-fill:red");
            }
            if (cbSuideos.getSelectionModel().isEmpty()){
                error = true;
                lblSuideos.setStyle("-fx-text-fill:red");
            }
            if (error == false){
                return true;
            }else{
                AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
                return false;
            }
        }

	public class FormPNSSCadastroEstabelecimento {
		private String estabelecimentoCadastrados;
		private String atualizacaoCadastral;
		private String suideos;
		private String nGranjas;
		private String nCriatorios;

		private String comentario;
		private String recomendacaoUlsavEac;
		private String prazo;
		private String recomendacaoUr;
		private String recomendacaoUC;

		public String getEstabelecimentoCadastrados() {
			return estabelecimentoCadastrados;
		}

		public void setEstabelecimentoCadastrados(String estabelecimentoCadastrados) {
			this.estabelecimentoCadastrados = estabelecimentoCadastrados;
		}

		public String getAtualizacaoCadastral() {
			return atualizacaoCadastral;
		}

		public void setAtualizacaoCadastral(String atualizacaoCadastral) {
			this.atualizacaoCadastral = atualizacaoCadastral;
		}

		public String getSuideos() {
			return suideos;
		}

		public void setSuideos(String suideos) {
			this.suideos = suideos;
		}

		public String getnGranjas() {
			return nGranjas;
		}

		public void setnGranjas(String nGranjas) {
			this.nGranjas = nGranjas;
		}

		public String getnCriatorios() {
			return nCriatorios;
		}

		public void setnCriatorios(String nCriatorios) {
			this.nCriatorios = nCriatorios;
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
