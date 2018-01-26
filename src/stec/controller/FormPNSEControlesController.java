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

public class FormPNSEControlesController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormPNSEControles formulario = new FormPNSEControles();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	ObservableList<String> observableOpcoes;
	ObservableList<String> observableAvaliacao;

	@FXML
	private JFXTextField qtdAntigos;
	@FXML
	private JFXTextField qtdRecentes;
	@FXML
	private JFXComboBox<String> cbRelacaoMVCadastrados;
	@FXML
	private JFXComboBox<String> cbRelatorioColheita;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
        @FXML
        private Label lblQtdAntigos;
        @FXML
        private Label lblQtdRecentes;
        @FXML
        private Label lblRelacaoMVCadastrados;
        @FXML
        private Label lblRelatorioColheita;
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

		cbRelacaoMVCadastrados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Relação dos Med Vet cadastrados"));
		cbRelatorioColheita.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Relatórios de colheita Med Vet cadastrados"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setQuantidadeAntigos(qtdAntigos.getText());
		formulario.setQuantidadeRecentes(qtdRecentes.getText());
		formulario.setRelacaoMedicoCadastrados(cbRelacaoMVCadastrados.getSelectionModel().getSelectedItem());
		formulario.setRelatorioColheita(cbRelatorioColheita.getSelectionModel().getSelectedItem());

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

		cbRelacaoMVCadastrados.setItems(observableOpcoes);
		cbRelatorioColheita.setItems(observableOpcoes);
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

			FormPNSEControles formulario = gson.fromJson(resposta.getResposta(), FormPNSEControles.class);

			qtdAntigos.setText(formulario.getQuantidadeAntigos());
			qtdRecentes.setText(formulario.getQuantidadeRecentes());
			cbRelacaoMVCadastrados.getSelectionModel().select(formulario.getRelacaoMedicoCadastrados());
			cbRelatorioColheita.getSelectionModel().select(formulario.getRelatorioColheita());

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
            if (qtdAntigos.getText() == null || qtdAntigos.getText().length() == 0){
                error = true;
                lblQtdAntigos.setStyle("-fx-text-fill:red");
            }
            if (qtdRecentes.getText() == null || qtdRecentes.getText().length() == 0){
                error = true;
                lblQtdRecentes.setStyle("-fx-text-fill:red");
            }
            if (cbRelacaoMVCadastrados.getSelectionModel().isEmpty()){
                error = true;
                lblRelacaoMVCadastrados.setStyle("-fx-text-fill:red");
            }
            if (cbRelatorioColheita.getSelectionModel().isEmpty()){
                error = true;
                lblRelatorioColheita.setStyle("-fx-text-fill:red");
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

	public class FormPNSEControles {

		private String relacaoMedicoCadastrados;
		private String relatorioColheita;
		private String quantidadeAntigos;
		private String quantidadeRecentes;

		private String avaliacao;
		private String comentario;
		private String recomendacaoUlsavEac;
		private String prazo;
		private String recomendacaoUr;
		private String recomendacaoUC;

		public FormPNSEControles() {
		}

		public String getAvaliacao() {
			return avaliacao;
		}

		public void setAvaliacao(String avaliacao) {
			this.avaliacao = avaliacao;
		}

		public String getRelacaoMedicoCadastrados() {
			return relacaoMedicoCadastrados;
		}

		public void setRelacaoMedicoCadastrados(String relacaoMedicoCadastrados) {
			this.relacaoMedicoCadastrados = relacaoMedicoCadastrados;
		}

		public String getRelatorioColheita() {
			return relatorioColheita;
		}

		public void setRelatorioColheita(String relatorioColheita) {
			this.relatorioColheita = relatorioColheita;
		}

		public String getQuantidadeAntigos() {
			return quantidadeAntigos;
		}

		public void setQuantidadeAntigos(String quantidadeAntigos) {
			this.quantidadeAntigos = quantidadeAntigos;
		}

		public String getQuantidadeRecentes() {
			return quantidadeRecentes;
		}

		public void setQuantidadeRecentes(String quantidadeRecentes) {
			this.quantidadeRecentes = quantidadeRecentes;
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
