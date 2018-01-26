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

public class FormPNSSVigilanciaController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormPNSSVigilancia formulario = new FormPNSSVigilancia();
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
	private JFXComboBox<String> cbPropriedades;
	@FXML
	private JFXComboBox<String> cbMortandade;
	@FXML
	private JFXComboBox<String> cbRegistroVigilancia;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
	@FXML
	private JFXTextField nGranjasVigilanciaExistentes;
	@FXML
	private JFXTextField nGranjasVigilanciaVisitadas;
	@FXML
	private JFXTextField nCriatoriosVigilanciaExistentes;
	@FXML
	private JFXTextField nCriatoriosVigilanciaVisitadas;
        @FXML
        private Label lblDirecionamento;
        @FXML
        private Label lblPropriedades;
        @FXML
        private Label lblRegistroVigilancia;
        @FXML
        private Label lblMortandade;
        @FXML
        private Label lblGranjasVigilanciaExistentes;
        @FXML
        private Label lblGranjasVigilanciaVisitadas;
        @FXML
        private Label lblCriatoriosVigilanciaExistentes;
        @FXML
        private Label lblCriatoriosVigilanciaVisitadas;
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

		cbDirecionamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Direcionamento das ações de vigilância"));
		cbMortandade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Mortandade"));
		cbPropriedades.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Propriedades de risco"));
		cbRegistroVigilancia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Registro da vigilância em propriedades e pontos de risco"));
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
		formulario.setMortandade(cbMortandade.getSelectionModel().getSelectedItem());
		formulario.setPropriedades(cbPropriedades.getSelectionModel().getSelectedItem());
		formulario.setRegistroVigilancia(cbRegistroVigilancia.getSelectionModel().getSelectedItem());
		formulario.setnCriatoriosExistentes(nCriatoriosVigilanciaExistentes.getText());
		formulario.setnCriatoriosExistentes(nCriatoriosVigilanciaExistentes.getText());
		formulario.setnCriatoriosVisitadas(nCriatoriosVigilanciaVisitadas.getText());
		formulario.setnGranjaExistente(nGranjasVigilanciaExistentes.getText());
		formulario.setnGranjaVisitadas(nGranjasVigilanciaVisitadas.getText());

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

		cbDirecionamento.setItems(observableOpcoes);
		cbMortandade.setItems(observableOpcoes);
		cbPropriedades.setItems(observableOpcoes);
		cbRegistroVigilancia.setItems(observableOpcoes);
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

			FormPNSSVigilancia formulario = gson.fromJson(resposta.getResposta(), FormPNSSVigilancia.class);

			cbDirecionamento.getSelectionModel().select(formulario.getDirecionamento());
			cbMortandade.getSelectionModel().select(formulario.getMortandade());
			cbPropriedades.getSelectionModel().select(formulario.getPropriedades());
			cbRegistroVigilancia.getSelectionModel().select(formulario.getRegistroVigilancia());
			nCriatoriosVigilanciaExistentes.setText(formulario.getnCriatoriosExistentes());
			nCriatoriosVigilanciaVisitadas.setText(formulario.getnCriatoriosVisitadas());
			nGranjasVigilanciaExistentes.setText(formulario.getnGranjaExistente());
			nGranjasVigilanciaVisitadas.setText(formulario.getnGranjaVisitadas());

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
            if (cbPropriedades.getSelectionModel().isEmpty()){
                error = true;
                lblPropriedades.setStyle("-fx-text-fill:red");
            }
            if (cbMortandade.getSelectionModel().isEmpty()){
                error = true;
                lblMortandade.setStyle("-fx-text-fill:red");
            }
            if (cbRegistroVigilancia.getSelectionModel().isEmpty()){
                error = true;
                lblRegistroVigilancia.setStyle("-fx-text-fill:red");
            }
            if (nGranjasVigilanciaExistentes.getText() == null || nGranjasVigilanciaExistentes.getText().length() == 0){
                error = true;
                lblGranjasVigilanciaExistentes.setStyle("-fx-text-fill:red");
            }
            if (nGranjasVigilanciaVisitadas.getText() == null || nGranjasVigilanciaVisitadas.getText().length() == 0){
                error = true;
                lblGranjasVigilanciaVisitadas.setStyle("-fx-text-fill:red");
            }
            if (nCriatoriosVigilanciaExistentes.getText() == null || nCriatoriosVigilanciaExistentes.getText().length() == 0){
                error = true;
                lblCriatoriosVigilanciaExistentes.setStyle("-fx-text-fill:red");
            }
            if (nCriatoriosVigilanciaVisitadas.getText() == null || nCriatoriosVigilanciaVisitadas.getText().length() == 0){
                error = true;
                lblCriatoriosVigilanciaVisitadas.setStyle("-fx-text-fill:red");
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

	public class FormPNSSVigilancia {
		private String direcionamento;
		private String propriedades;
		private String mortandade;
		private String registroVigilancia;
		private String nGranjaExistente;
		private String nGranjaVisitadas;
		private String nCriatoriosExistentes;
		private String nCriatoriosVisitadas;

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

		public String getPropriedades() {
			return propriedades;
		}

		public void setPropriedades(String propriedades) {
			this.propriedades = propriedades;
		}

		public String getMortandade() {
			return mortandade;
		}

		public void setMortandade(String mortandade) {
			this.mortandade = mortandade;
		}

		public String getRegistroVigilancia() {
			return registroVigilancia;
		}

		public void setRegistroVigilancia(String registroVigilancia) {
			this.registroVigilancia = registroVigilancia;
		}

		public String getnGranjaExistente() {
			return nGranjaExistente;
		}

		public void setnGranjaExistente(String nGranjaExistente) {
			this.nGranjaExistente = nGranjaExistente;
		}

		public String getnGranjaVisitadas() {
			return nGranjaVisitadas;
		}

		public void setnGranjaVisitadas(String nGranjaVisitadas) {
			this.nGranjaVisitadas = nGranjaVisitadas;
		}

		public String getnCriatoriosExistentes() {
			return nCriatoriosExistentes;
		}

		public void setnCriatoriosExistentes(String nCriatoriosExistentes) {
			this.nCriatoriosExistentes = nCriatoriosExistentes;
		}

		public String getnCriatoriosVisitadas() {
			return nCriatoriosVisitadas;
		}

		public void setnCriatoriosVisitadas(String nCriatoriosVisitadas) {
			this.nCriatoriosVisitadas = nCriatoriosVisitadas;
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
