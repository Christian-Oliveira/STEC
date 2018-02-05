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

public class FormRFICusteioController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormRFICusteio formulario = new FormRFICusteio();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	ObservableList<String> observableOpcoes;
	ObservableList<String> observableAvaliacao;

	@FXML
	private JFXComboBox<String> cbSolicitacaoDiaria;
	@FXML
	private JFXComboBox<String> cbDisponibilidadeFinanceiro;
	@FXML
	private JFXButton btAdicionar;
	@FXML
	private JFXButton btCancelar;
	@FXML
	private JFXComboBox<String> cbFAI;
	@FXML
	private JFXComboBox<String> cbRelatorioKM;
	@FXML
	private JFXComboBox<String> cbRelatorioViagem;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
        @FXML
        private Label lblSolicitacaoDiaria;
        @FXML
        private Label lblFAI;
        @FXML
        private Label lblRelatorioKM;
        @FXML
        private Label lblRelatorioViagem;
        @FXML
        private Label lblDisponibilidadeFinanceiro;
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

		cbDisponibilidadeFinanceiro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Disponibilidade de recurso financeiro"));
		cbFAI.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "FAI"));
		cbRelatorioKM.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Relatório de quilometragem"));
		cbRelatorioViagem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Relatório de viagem"));
		cbSolicitacaoDiaria.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Procedimentos para solicitação de diárias"));
        }

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}
        
	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setDisponibilidadeFinanceiro(cbDisponibilidadeFinanceiro.getSelectionModel().getSelectedItem());
		formulario.setFAI(cbFAI.getSelectionModel().getSelectedItem());
		formulario.setRelatorioKM(cbRelatorioKM.getSelectionModel().getSelectedItem());
		formulario.setRelatorioViagem(cbRelatorioViagem.getSelectionModel().getSelectedItem());
		formulario.setSolicitacaoDiaria(cbSolicitacaoDiaria.getSelectionModel().getSelectedItem());

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

		cbDisponibilidadeFinanceiro.setItems(observableOpcoes);
		cbFAI.setItems(observableOpcoes);
		cbRelatorioKM.setItems(observableOpcoes);
		cbRelatorioViagem.setItems(observableOpcoes);
		cbSolicitacaoDiaria.setItems(observableOpcoes);
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

			FormRFICusteio formulario = gson.fromJson(resposta.getResposta(), FormRFICusteio.class);

			cbDisponibilidadeFinanceiro.getSelectionModel().select(formulario.getDisponibilidadeFinanceiro());
			cbFAI.getSelectionModel().select(formulario.getFAI());
			cbRelatorioKM.getSelectionModel().select(formulario.getRelatorioKM());
			cbRelatorioViagem.getSelectionModel().select(formulario.getRelatorioViagem());
			cbSolicitacaoDiaria.getSelectionModel().select(formulario.getSolicitacaoDiaria());

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
            if (cbSolicitacaoDiaria.getSelectionModel().isEmpty()){
                error = true;
                lblSolicitacaoDiaria.setStyle("-fx-text-fill:red");
            }
            if (cbFAI.getSelectionModel().isEmpty()){
                error = true;
                lblFAI.setStyle("-fx-text-fill:red");
            }
            if (cbRelatorioKM.getSelectionModel().isEmpty()){
                error = true;
                lblRelatorioKM.setStyle("-fx-text-fill:red");
            }
            if (cbRelatorioViagem.getSelectionModel().isEmpty()){
                error = true;
                lblRelatorioViagem.setStyle("-fx-text-fill:red");
            }
            if (cbDisponibilidadeFinanceiro.getSelectionModel().isEmpty()){
                error = true;
                lblDisponibilidadeFinanceiro.setStyle("-fx-text-fill:red");
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

	public class FormRFICusteio {

		private String solicitacaoDiaria;
		private String disponibilidadeFinanceiro;
		private String FAI;
		private String relatorioKM;
		private String relatorioViagem;

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

		public String getSolicitacaoDiaria() {
			return solicitacaoDiaria;
		}

		public void setSolicitacaoDiaria(String solicitacaoDiaria) {
			this.solicitacaoDiaria = solicitacaoDiaria;
		}

		public String getDisponibilidadeFinanceiro() {
			return disponibilidadeFinanceiro;
		}

		public void setDisponibilidadeFinanceiro(String disponibilidadeFinanceiro) {
			this.disponibilidadeFinanceiro = disponibilidadeFinanceiro;
		}

		public String getFAI() {
			return FAI;
		}

		public void setFAI(String FAI) {
			this.FAI = FAI;
		}

		public String getRelatorioKM() {
			return relatorioKM;
		}

		public void setRelatorioKM(String relatorioKM) {
			this.relatorioKM = relatorioKM;
		}

		public String getRelatorioViagem() {
			return relatorioViagem;
		}

		public void setRelatorioViagem(String relatorioViagem) {
			this.relatorioViagem = relatorioViagem;
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
