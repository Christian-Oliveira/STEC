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

public class FormPNSAVigilanciaController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormPNSAVigilancia formulario = new FormPNSAVigilancia();
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
	private JFXComboBox<String> cbDirecionamento;
	@FXML
	private JFXComboBox<String> cbPontoRisco;
	@FXML
	private JFXComboBox<String> cbSitioMigratorio;
	@FXML
	private JFXComboBox<String> cbVendaAvesVivas;
	@FXML
	private JFXComboBox<String> cbMortandade;
	@FXML
	private JFXComboBox<String> cbDoencaNotificacao;
	@FXML
	private JFXComboBox<String> cbCadastrado;
	@FXML
	private JFXComboBox<String> cbRegistrado;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
        @FXML
        private Label lblDirecionamento;
        @FXML
        private Label lblPontoRisco;
        @FXML
        private Label lblSitioMigratorio;
        @FXML
        private Label lblVendaAvesVivas;
        @FXML
        private Label lblMortandade;
        @FXML
        private Label lblDoencaNotificacao;
        @FXML
        private Label lblCadastrado;
        @FXML
        private Label lblRegistrado;
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

		cbCadastrado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Somente Cadastrados"));
		cbDirecionamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Direcionamento das ações de vigilância"));
		cbDoencaNotificacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Doen�as de notificação obrigatória"));
		cbMortandade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Mortandade"));
		cbPontoRisco.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Pontos de risco"));
		cbRegistrado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Registrados"));
		cbSitioMigratorio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Sítios migratórios"));
		cbVendaAvesVivas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Estabelecimentos de venda de aves vivas (entreposto, distribuidores, casas agropecuárias, etc)"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setCadastrado(cbCadastrado.getSelectionModel().getSelectedItem());
		formulario.setDirecionamento(cbDirecionamento.getSelectionModel().getSelectedItem());
		formulario.setDoencaNotificacao(cbDoencaNotificacao.getSelectionModel().getSelectedItem());
		formulario.setMortandade(cbMortandade.getSelectionModel().getSelectedItem());
		formulario.setPontoRisco(cbPontoRisco.getSelectionModel().getSelectedItem());
		formulario.setRegistrado(cbRegistrado.getSelectionModel().getSelectedItem());
		formulario.setSitioMigratorio(cbSitioMigratorio.getSelectionModel().getSelectedItem());
		formulario.setVendasAvesVivas(cbVendaAvesVivas.getSelectionModel().getSelectedItem());

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

		cbCadastrado.setItems(observableOpcoes);
		cbDirecionamento.setItems(observableOpcoes);
		cbDoencaNotificacao.setItems(observableOpcoes);
		cbMortandade.setItems(observableOpcoes);
		cbPontoRisco.setItems(observableOpcoes);
		cbRegistrado.setItems(observableOpcoes);
		cbSitioMigratorio.setItems(observableOpcoes);
		cbVendaAvesVivas.setItems(observableOpcoes);
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

			FormPNSAVigilancia formulario = gson.fromJson(resposta.getResposta(), FormPNSAVigilancia.class);

			cbCadastrado.getSelectionModel().select(formulario.getCadastrado());
			cbDirecionamento.getSelectionModel().select(formulario.getDirecionamento());
			cbDoencaNotificacao.getSelectionModel().select(formulario.getDoencaNotificacao());
			cbMortandade.getSelectionModel().select(formulario.getMortandade());
			cbPontoRisco.getSelectionModel().select(formulario.getPontoRisco());
			cbRegistrado.getSelectionModel().select(formulario.getRegistrado());
			cbSitioMigratorio.getSelectionModel().select(formulario.getSitioMigratorio());
			cbVendaAvesVivas.getSelectionModel().select(formulario.getVendasAvesVivas());

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
        
        //Funcão para validar dados
        @Override
        public boolean validarDados(){
            boolean error = false;
            if (cbDirecionamento.getSelectionModel().isEmpty()){
                error = true;
                lblDirecionamento.setStyle("-fx-text-fill:red");
            }
            if (cbPontoRisco.getSelectionModel().isEmpty()){
                error = true;
                lblPontoRisco.setStyle("-fx-text-fill:red");
            }
            if (cbSitioMigratorio.getSelectionModel().isEmpty()){
                error = true;
                lblSitioMigratorio.setStyle("-fx-text-fill:red");
            }
            if (cbVendaAvesVivas.getSelectionModel().isEmpty()){
                error = true;
                lblVendaAvesVivas.setStyle("-fx-text-fill:red");
            }
            if (cbMortandade.getSelectionModel().isEmpty()){
                error = true;
                lblMortandade.setStyle("-fx-text-fill:red");
            }
            if (cbDoencaNotificacao.getSelectionModel().isEmpty()){
                error = true;
                lblDoencaNotificacao.setStyle("-fx-text-fill:red");
            }
            if (cbCadastrado.getSelectionModel().isEmpty()){
                error = true;
                lblCadastrado.setStyle("-fx-text-fill:red");
            }
            if (cbRegistrado.getSelectionModel().isEmpty()){
                error = true;
                lblRegistrado.setStyle("-fx-text-fill:red");
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

	public class FormPNSAVigilancia {

		private String direcionamento;
		private String pontoRisco;
		private String sitioMigratorio;
		private String vendasAvesVivas;
		private String mortandade;
		private String doencaNotificacao;
		private String cadastrado;
		private String registrado;

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

		public String getDirecionamento() {
			return direcionamento;
		}

		public void setDirecionamento(String direcionamento) {
			this.direcionamento = direcionamento;
		}

		public String getPontoRisco() {
			return pontoRisco;
		}

		public void setPontoRisco(String pontoRisco) {
			this.pontoRisco = pontoRisco;
		}

		public String getSitioMigratorio() {
			return sitioMigratorio;
		}

		public void setSitioMigratorio(String sitioMigratorio) {
			this.sitioMigratorio = sitioMigratorio;
		}

		public String getVendasAvesVivas() {
			return vendasAvesVivas;
		}

		public void setVendasAvesVivas(String vendasAvesVivas) {
			this.vendasAvesVivas = vendasAvesVivas;
		}

		public String getMortandade() {
			return mortandade;
		}

		public void setMortandade(String mortandade) {
			this.mortandade = mortandade;
		}

		public String getDoencaNotificacao() {
			return doencaNotificacao;
		}

		public void setDoencaNotificacao(String doencaNotificacao) {
			this.doencaNotificacao = doencaNotificacao;
		}

		public String getCadastrado() {
			return cadastrado;
		}

		public void setCadastrado(String cadastrado) {
			this.cadastrado = cadastrado;
		}

		public String getRegistrado() {
			return registrado;
		}

		public void setRegistrado(String registrado) {
			this.registrado = registrado;
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
