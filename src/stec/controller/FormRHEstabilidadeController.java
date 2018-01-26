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

public class FormRHEstabilidadeController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormRHEstabilidade formulario = new FormRHEstabilidade();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	ObservableList<String> observableOpcoes;
	ObservableList<String> observableAvaliacao;

	@FXML
	private JFXTextField tempoServicoDefesaSanitaria;
	@FXML
	private JFXTextField tempoServicoEscritorio;
	@FXML
	private JFXComboBox<String> cbEvidenciasEstabilidade;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
        @FXML
        private Label lblTempoDefesaSanitaria;
        @FXML
        private Label lblTempoServicoEscritorio;
        @FXML
        private Label lblEvidenciasEstabilidade;
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

		cbEvidenciasEstabilidade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Existe evidências de estabilidade do quadro funcional?"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setEvidenciaEstabilidade(cbEvidenciasEstabilidade.getSelectionModel().getSelectedItem());
		formulario.setTempoDefesaSanitaria(tempoServicoDefesaSanitaria.getText());
		formulario.setTempoEscritorio(tempoServicoEscritorio.getText());

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

		cbAvaliacao.setItems(observableAvaliacao);
		cbEvidenciasEstabilidade.setItems(observableOpcoes);
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

			FormRHEstabilidade formulario = gson.fromJson(resposta.getResposta(), FormRHEstabilidade.class);

			cbEvidenciasEstabilidade.getSelectionModel().select(formulario.getEvidenciaEstabilidade());
			tempoServicoDefesaSanitaria.setText(formulario.getTempoDefesaSanitaria());
			tempoServicoEscritorio.setText(formulario.getTempoEscritorio());

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
        
        //Função de validação de entrada de dados
        @Override
        public boolean validarDados(){
            boolean error = false;
            if (tempoServicoDefesaSanitaria.getText() == null || tempoServicoDefesaSanitaria.getText().length() == 0){
                error = true;
                lblTempoDefesaSanitaria.setStyle("-fx-text-fill:red");
            }
            if (tempoServicoEscritorio.getText() == null || tempoServicoEscritorio.getText().length() == 0){
                error = true;
                lblTempoServicoEscritorio.setStyle("-fx-text-fill:red");
            }
            if (cbEvidenciasEstabilidade.getSelectionModel().isEmpty()){
                error = true;
                lblEvidenciasEstabilidade.setStyle("-fx-text-fill:red");
            }
            if (cbAvaliacao.getSelectionModel().isEmpty()){
                error = true;
                lblAvaliacao.setStyle("-fx-text-fill:red");
            }
            if (error == false){
                return true;
            }else{
                //Mostrando a menssagem de erro
                AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
                return false;
            }
        }

	public class FormRHEstabilidade {

		private String tempoDefesaSanitaria;
		private String tempoEscritorio;
		private String evidenciaEstabilidade;

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

		public String getTempoDefesaSanitaria() {
			return tempoDefesaSanitaria;
		}

		public void setTempoDefesaSanitaria(String tempoDefesaSanitaria) {
			this.tempoDefesaSanitaria = tempoDefesaSanitaria;
		}

		public String getTempoEscritorio() {
			return tempoEscritorio;
		}

		public void setTempoEscritorio(String tempoEscritorio) {
			this.tempoEscritorio = tempoEscritorio;
		}

		public String getEvidenciaEstabilidade() {
			return evidenciaEstabilidade;
		}

		public void setEvidenciaEstabilidade(String evidenciaEstabilidade) {
			this.evidenciaEstabilidade = evidenciaEstabilidade;
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
