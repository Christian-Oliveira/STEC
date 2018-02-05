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

public class FormPNSEVigilanciaController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormPNSEVigilancia formulario = new FormPNSEVigilancia();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	ObservableList<String> observableOpcoes;

	@FXML
	private JFXButton btAdicionar;
	@FXML
	private JFXButton btCancelar;
	@FXML
	private JFXComboBox<String> cbDirecionamento;
	@FXML
	private JFXComboBox<String> cbVigilancia;
	@FXML
	private JFXTextField nPropriedade;
	@FXML
	private JFXTextField doencasCurso;
	@FXML
	private JFXComboBox<String> cbProcedimentosAdotados;
	@FXML
	private JFXComboBox<String> cbDoencasNotificacao;
        @FXML
        private Label lblDirecionamento;
        @FXML
        private Label lblVigilancia;
        @FXML
        private Label lblDoencasNotificacao;
        @FXML
        private Label lblPropriedade;
        @FXML
        private Label lblDoencasCurso;
        @FXML
        private Label lblProcedimentosAdotados;
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

		cbDirecionamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Direcionamento das ações de vigiláncia"));
		cbDoencasNotificacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Doençaas de notificação obrigatória"));
		cbProcedimentosAdotados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Procedimentos adotados"));
		cbVigilancia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Vigilância em propriedades"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setDirecionamento(cbDirecionamento.getSelectionModel().getSelectedItem());
		formulario.setDoencaNotificacao(cbDoencasNotificacao.getSelectionModel().getSelectedItem());
		formulario.setProcedimentoAdotado(cbProcedimentosAdotados.getSelectionModel().getSelectedItem());
		formulario.setVigilancia(cbVigilancia.getSelectionModel().getSelectedItem());
		formulario.setnPropriedade(nPropriedade.getText());
		formulario.setDoencaCurso(doencasCurso.getText());

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

		cbDirecionamento.setItems(observableOpcoes);
		cbDoencasNotificacao.setItems(observableOpcoes);
		cbProcedimentosAdotados.setItems(observableOpcoes);
		cbVigilancia.setItems(observableOpcoes);
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

			FormPNSEVigilancia formulario = gson.fromJson(resposta.getResposta(), FormPNSEVigilancia.class);

			cbDirecionamento.getSelectionModel().select(formulario.getDirecionamento());
			cbDoencasNotificacao.getSelectionModel().select(formulario.getDoencaNotificacao());
			cbProcedimentosAdotados.getSelectionModel().select(formulario.getProcedimentoAdotado());
			cbVigilancia.getSelectionModel().select(formulario.getVigilancia());
			nPropriedade.setText(formulario.getnPropriedade());
			doencasCurso.setText(formulario.getDoencaCurso());

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
            if (cbVigilancia.getSelectionModel().isEmpty()){
                error = true;
                lblVigilancia.setStyle("-fx-text-fill:red");
            }
            if (cbDoencasNotificacao.getSelectionModel().isEmpty()){
                error = true;
                lblDoencasNotificacao.setStyle("-fx-text-fill:red");
            }
            if (nPropriedade.getText() == null || nPropriedade.getText().length() == 0){
                error = true;
                lblPropriedade.setStyle("-fx-text-fill:red");
            }
            if (doencasCurso.getText() == null || doencasCurso.getText().length() == 0){
                error = true;
                lblDoencasCurso.setStyle("-fx-text-fill:red");
            }
            if (cbProcedimentosAdotados.getSelectionModel().isEmpty()){
                error = true;
                lblProcedimentosAdotados.setStyle("-fx-text-fill:red");
            }
            if (error == false){
                return true;
            }else{
                AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
                return false;
            }
        }

	public class FormPNSEVigilancia {

		private String direcionamento;
		private String vigilancia;
		private String nPropriedade;
		private String doencaCurso;
		private String procedimentoAdotado;
		private String doencaNotificacao;

		private String comentario;
		private String recomendacaoUlsavEac;
		private String prazo;
		private String recomendacaoUr;
		private String recomendacaoUC;

		public FormPNSEVigilancia() {
		}

		public String getDirecionamento() {
			return direcionamento;
		}

		public void setDirecionamento(String direcionamento) {
			this.direcionamento = direcionamento;
		}

		public String getVigilancia() {
			return vigilancia;
		}

		public void setVigilancia(String vigilancia) {
			this.vigilancia = vigilancia;
		}

		public String getnPropriedade() {
			return nPropriedade;
		}

		public void setnPropriedade(String nPropriedade) {
			this.nPropriedade = nPropriedade;
		}

		public String getDoencaCurso() {
			return doencaCurso;
		}

		public void setDoencaCurso(String doencaCurso) {
			this.doencaCurso = doencaCurso;
		}

		public String getProcedimentoAdotado() {
			return procedimentoAdotado;
		}

		public void setProcedimentoAdotado(String procedimentoAdotado) {
			this.procedimentoAdotado = procedimentoAdotado;
		}

		public String getDoencaNotificacao() {
			return doencaNotificacao;
		}

		public void setDoencaNotificacao(String doencaNotificacao) {
			this.doencaNotificacao = doencaNotificacao;
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
