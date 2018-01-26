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

public class FormPNEFAVigilanciaController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormPNEFAVigilancia formulario = new FormPNEFAVigilancia();
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
	private JFXComboBox<String> cbPropriedadesRisco;
	@FXML
	private JFXComboBox<String> cbRegistroVigilancia;
	@FXML
	private JFXComboBox<String> cbIndicadores;
	@FXML
	private JFXTextField nPropriedadesFoco;
	@FXML
	private JFXTextField doencaCurso;
	@FXML
	private JFXComboBox<String> cbProcedimentosAdotadosFoco;
	@FXML
	private JFXComboBox<String> cbProcedimentosAdotadosVinculo;
	@FXML
	private JFXTextField nPropriedadeVinculo;
        @FXML
        private Label lblDirecionamento;
        @FXML
        private Label lblPropriedadesRisco;
        @FXML
        private Label lblRegistroVigilancia;
        @FXML
        private Label lblIndicadores;
        @FXML
        private Label lblPropriedadesFoco;
        @FXML
        private Label lblDoencaCurso;
        @FXML
        private Label lblProcedimentosAdotadosFoco;
        @FXML
        private Label lblPropriedadeVinculo;
        @FXML
        private Label lblProcedimentosAdotadosVinculo;
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
				newValue) -> adicionarComentario(newValue, "Direcionamento das ações de vigil�ncia"));
		cbIndicadores.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Indicadores da caracterização epidemiológica"));
		cbProcedimentosAdotadosFoco.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Procedimentos adotados"));
		cbProcedimentosAdotadosVinculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Procedimentos adotados"));
		cbPropriedadesRisco.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Propriedades e pontos de risco"));
		cbRegistroVigilancia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Realização e registro da vigilância em propriedades e pontos de risco"));
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
		formulario.setPropriedadesRisco(cbPropriedadesRisco.getSelectionModel().getSelectedItem());
		formulario.setRegistroVigilancia(cbRegistroVigilancia.getSelectionModel().getSelectedItem());
		formulario.setIndicadores(cbIndicadores.getSelectionModel().getSelectedItem());
		formulario.setnPropriedadeFoco(nPropriedadesFoco.getText());
		formulario.setDoencaCurso(doencaCurso.getText());
		formulario.setProcedimentosAdotadosFoco(cbProcedimentosAdotadosFoco.getSelectionModel().getSelectedItem());
		formulario.setnPropriedadeVinculo(nPropriedadeVinculo.getText());
		formulario
				.setProcedimentosAdotadosVinculo(cbProcedimentosAdotadosVinculo.getSelectionModel().getSelectedItem());

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
		cbIndicadores.setItems(observableOpcoes);
		cbProcedimentosAdotadosFoco.setItems(observableOpcoes);
		cbProcedimentosAdotadosVinculo.setItems(observableOpcoes);
		cbPropriedadesRisco.setItems(observableOpcoes);
		cbRegistroVigilancia.setItems(observableOpcoes);
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

			FormPNEFAVigilancia formulario = gson.fromJson(resposta.getResposta(), FormPNEFAVigilancia.class);

			cbDirecionamento.getSelectionModel().select(formulario.getDirecionamento());
			cbPropriedadesRisco.getSelectionModel().select(formulario.getPropriedadesRisco());
			cbRegistroVigilancia.getSelectionModel().select(formulario.getRegistroVigilancia());
			cbIndicadores.getSelectionModel().select(formulario.getIndicadores());
			nPropriedadesFoco.setText(formulario.getnPropriedadeFoco());
			doencaCurso.setText(formulario.getDoencaCurso());
			cbProcedimentosAdotadosFoco.getSelectionModel().select(formulario.getProcedimentosAdotadosFoco());
			nPropriedadeVinculo.setText(formulario.getnPropriedadeVinculo());
			cbProcedimentosAdotadosVinculo.getSelectionModel().select(formulario.getProcedimentosAdotadosVinculo());

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
            if (cbDirecionamento.getSelectionModel().isEmpty()){
                error = true;
                lblDirecionamento.setStyle("-fx-text-fill:red");
            }
            if (cbPropriedadesRisco.getSelectionModel().isEmpty()){
                error = true;
                lblPropriedadesRisco.setStyle("-fx-text-fill:red");
            }
            if (cbRegistroVigilancia.getSelectionModel().isEmpty()){
                error = true;
                lblRegistroVigilancia.setStyle("-fx-text-fill:red");
            }
            if (cbIndicadores.getSelectionModel().isEmpty()){
                error = true;
                lblIndicadores.setStyle("-fx-text-fill:red");
            }
            if (nPropriedadesFoco.getText() == null || nPropriedadesFoco.getText().length() == 0){
                error = true;
                lblPropriedadesFoco.setStyle("-fx-text-fill:red");
            }
            if (doencaCurso.getText() == null || doencaCurso.getText().length() == 0){
                error = true;
                lblDoencaCurso.setStyle("-fx-text-fill:red");
            }
            if (cbProcedimentosAdotadosFoco.getSelectionModel().isEmpty()){
                error = true;
                lblProcedimentosAdotadosFoco.setStyle("-fx-text-fill:red");
            }
            if (nPropriedadeVinculo.getText() == null || nPropriedadeVinculo.getText().length() == 0){
                error = true;
                lblPropriedadeVinculo.setStyle("-fx-text-fill:red");
            }
            if (cbProcedimentosAdotadosVinculo.getSelectionModel().isEmpty()){
                error = true;
                lblProcedimentosAdotadosVinculo.setStyle("-fx-text-fill:red");
            }
            if (error == false){
                return true;
            }else{
                AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
                return false;
            }
        }

	public class FormPNEFAVigilancia {

		private String direcionamento;
		private String propriedadesRisco;
		private String registroVigilancia;
		private String indicadores;
		private String nPropriedadeFoco;
		private String doencaCurso;
		private String procedimentosAdotadosFoco;
		private String nPropriedadeVinculo;
		private String procedimentosAdotadosVinculo;

		private String comentario;
		private String recomendacaoUlsavEac;
		private String prazo;
		private String recomendacaoUr;
		private String recomendacaoUC;

		public FormPNEFAVigilancia() {
		}

		public String getDirecionamento() {
			return direcionamento;
		}

		public void setDirecionamento(String direcionamento) {
			this.direcionamento = direcionamento;
		}

		public String getPropriedadesRisco() {
			return propriedadesRisco;
		}

		public void setPropriedadesRisco(String propriedadesRisco) {
			this.propriedadesRisco = propriedadesRisco;
		}

		public String getRegistroVigilancia() {
			return registroVigilancia;
		}

		public void setRegistroVigilancia(String registroVigilancia) {
			this.registroVigilancia = registroVigilancia;
		}

		public String getIndicadores() {
			return indicadores;
		}

		public void setIndicadores(String indicadores) {
			this.indicadores = indicadores;
		}

		public String getnPropriedadeFoco() {
			return nPropriedadeFoco;
		}

		public void setnPropriedadeFoco(String nPropriedadeFoco) {
			this.nPropriedadeFoco = nPropriedadeFoco;
		}

		public String getDoencaCurso() {
			return doencaCurso;
		}

		public void setDoencaCurso(String doencaCurso) {
			this.doencaCurso = doencaCurso;
		}

		public String getProcedimentosAdotadosFoco() {
			return procedimentosAdotadosFoco;
		}

		public void setProcedimentosAdotadosFoco(String procedimentosAdotadosFoco) {
			this.procedimentosAdotadosFoco = procedimentosAdotadosFoco;
		}

		public String getnPropriedadeVinculo() {
			return nPropriedadeVinculo;
		}

		public void setnPropriedadeVinculo(String nPropriedadeVinculo) {
			this.nPropriedadeVinculo = nPropriedadeVinculo;
		}

		public String getProcedimentosAdotadosVinculo() {
			return procedimentosAdotadosVinculo;
		}

		public void setProcedimentosAdotadosVinculo(String procedimentosAdotadosVinculo) {
			this.procedimentosAdotadosVinculo = procedimentosAdotadosVinculo;
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
