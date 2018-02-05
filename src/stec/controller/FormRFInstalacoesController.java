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

public class FormRFInstalacoesController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormRFInstalacoes formulario = new FormRFInstalacoes();
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
	private JFXComboBox<String> cbNaturezaPredio;
	@FXML
	private JFXComboBox<String> cbLimpeza;
	@FXML
	private JFXComboBox<String> cbConservacao;
	@FXML
	private JFXComboBox<String> cbBanheiro;
	@FXML
	private JFXComboBox<String> cbSalaAtendimento;
	@FXML
	private JFXComboBox<String> cbSalaVeterinario;
	@FXML
	private JFXComboBox<String> cbCondicoesAtendimento;
	@FXML
	private JFXComboBox<String> cbApresentacaoExterna;
	@FXML
	private JFXComboBox<String> cbOrganizacaoInterna;
	@FXML
	private JFXComboBox<String> cbAtendeDemanda;
	@FXML
	private JFXComboBox<String> cbRelacaoMobiliario;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
        @FXML
        private Label lblNaturezaPredio;
        @FXML
        private Label lblLimpeza;
         @FXML
        private Label lblConservacao;
        @FXML
        private Label lblBanheiro;
        @FXML
        private Label lblSalaAtendimento;
        @FXML
        private Label lblSalaVeterinario;
         @FXML
        private Label lblCondicoesAtendimento;
        @FXML
        private Label lblApresentacaoExterna;
        @FXML
        private Label lblOrganizacaoInterna;
        @FXML
        private Label lblAtendeDemanda;
        @FXML
        private Label lblRelacaoMobiliario;
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

		cbApresentacaoExterna.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Apresentação externa"));
		cbAtendeDemanda.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "DE MODO GERAL O MOBILIÁRIO ATENDE À DEMANDA?"));
		cbBanheiro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "banheiro"));
		cbCondicoesAtendimento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Condições de atendimento ao público"));
		cbConservacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "estrutura (conservação)"));
		cbLimpeza.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "limpeza"));
		cbNaturezaPredio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Natureza do prédio"));
		cbOrganizacaoInterna.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Organização interna (impressão visual)"));
		cbRelacaoMobiliario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Há uma relação do mobiliário existente por EAC?"));
		cbSalaAtendimento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "sala de atendimento"));
		cbSalaVeterinario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "sala p/ veterinário"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setApresentacaoExterna(cbApresentacaoExterna.getSelectionModel().getSelectedItem());
		formulario.setAtendeDemanda(cbAtendeDemanda.getSelectionModel().getSelectedItem());
		formulario.setBanheiro(cbBanheiro.getSelectionModel().getSelectedItem());
		formulario.setCondicoesAtendimento(cbCondicoesAtendimento.getSelectionModel().getSelectedItem());
		formulario.setConservacao(cbConservacao.getSelectionModel().getSelectedItem());
		formulario.setLimpeza(cbLimpeza.getSelectionModel().getSelectedItem());
		formulario.setNaturezaPredio(cbNaturezaPredio.getSelectionModel().getSelectedItem());
		formulario.setOrganizacaoInterna(cbOrganizacaoInterna.getSelectionModel().getSelectedItem());
		formulario.setRelacaoMobiliario(cbRelacaoMobiliario.getSelectionModel().getSelectedItem());
		formulario.setSalaAtendimento(cbSalaAtendimento.getSelectionModel().getSelectedItem());
		formulario.setSalaVeterinario(cbSalaVeterinario.getSelectionModel().getSelectedItem());

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

		cbApresentacaoExterna.setItems(observableOpcoes);
		cbAtendeDemanda.setItems(observableOpcoes);
		cbBanheiro.setItems(observableOpcoes);
		cbCondicoesAtendimento.setItems(observableOpcoes);
		cbConservacao.setItems(observableOpcoes);
		cbLimpeza.setItems(observableOpcoes);
		cbNaturezaPredio.setItems(observableOpcoes);
		cbOrganizacaoInterna.setItems(observableOpcoes);
		cbRelacaoMobiliario.setItems(observableOpcoes);
		cbSalaAtendimento.setItems(observableOpcoes);
		cbSalaVeterinario.setItems(observableOpcoes);
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

			FormRFInstalacoes formulario = gson.fromJson(resposta.getResposta(), FormRFInstalacoes.class);

			cbApresentacaoExterna.getSelectionModel().select(formulario.getApresentacaoExterna());
			cbAtendeDemanda.getSelectionModel().select(formulario.getAtendeDemanda());
			cbBanheiro.getSelectionModel().select(formulario.getBanheiro());
			cbCondicoesAtendimento.getSelectionModel().select(formulario.getCondicoesAtendimento());
			cbConservacao.getSelectionModel().select(formulario.getConservacao());
			cbLimpeza.getSelectionModel().select(formulario.getLimpeza());
			cbNaturezaPredio.getSelectionModel().select(formulario.getNaturezaPredio());
			cbOrganizacaoInterna.getSelectionModel().select(formulario.getOrganizacaoInterna());
			cbRelacaoMobiliario.getSelectionModel().select(formulario.getRelacaoMobiliario());
			cbSalaAtendimento.getSelectionModel().select(formulario.getSalaAtendimento());
			cbSalaVeterinario.getSelectionModel().select(formulario.getSalaVeterinario());

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
            if (cbNaturezaPredio.getSelectionModel().isEmpty()){
                error = true;
                lblNaturezaPredio.setStyle("-fx-text-fill:red");
            }
            if (cbLimpeza.getSelectionModel().isEmpty()){
                error = true;
                lblLimpeza.setStyle("-fx-text-fill:red");
            }
            if (cbConservacao.getSelectionModel().isEmpty()){
                error = true;
                lblConservacao.setStyle("-fx-text-fill:red");
            }
            if(cbBanheiro.getSelectionModel().isEmpty()){
                error = true;
                lblBanheiro.setStyle("-fx-text-fill:red");
            }
            if (cbSalaAtendimento.getSelectionModel().isEmpty()){
                error = true;
                lblSalaAtendimento.setStyle("-fx-text-fill:red");
            }
            if (cbSalaVeterinario.getSelectionModel().isEmpty()){
                error = true;
                lblSalaVeterinario.setStyle("-fx-text-fill:red");
            }
            if(cbCondicoesAtendimento.getSelectionModel().isEmpty()){
                error = true;
                lblCondicoesAtendimento.setStyle("-fx-text-fill:red");
            }
            if(cbApresentacaoExterna.getSelectionModel().isEmpty()){
                error = true;
                lblApresentacaoExterna.setStyle("-fx-text-fill:red");
            }
            if(cbOrganizacaoInterna.getSelectionModel().isEmpty()){
                error = true;
                lblOrganizacaoInterna.setStyle("-fx-text-fill:red");
            }
            if(cbAtendeDemanda.getSelectionModel().isEmpty()){
                error = true;
                lblAtendeDemanda.setStyle("-fx-text-fill:red");
            }
            if(cbRelacaoMobiliario.getSelectionModel().isEmpty()){
                error = true;
                lblRelacaoMobiliario.setStyle("-fx-text-fill:red");
            }
            if(cbAvaliacao.getSelectionModel().isEmpty()){
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

	public class FormRFInstalacoes {

		private String apresentacaoExterna;
		private String atendeDemanda;
		private String banheiro;
		private String condicoesAtendimento;
		private String conservacao;
		private String limpeza;
		private String naturezaPredio;
		private String organizacaoInterna;
		private String relacaoMobiliario;
		private String salaAtendimento;
		private String salaVeterinario;

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

		public String getApresentacaoExterna() {
			return apresentacaoExterna;
		}

		public void setApresentacaoExterna(String apresentacaoExterna) {
			this.apresentacaoExterna = apresentacaoExterna;
		}

		public String getAtendeDemanda() {
			return atendeDemanda;
		}

		public void setAtendeDemanda(String atendeDemanda) {
			this.atendeDemanda = atendeDemanda;
		}

		public String getBanheiro() {
			return banheiro;
		}

		public void setBanheiro(String banheiro) {
			this.banheiro = banheiro;
		}

		public String getCondicoesAtendimento() {
			return condicoesAtendimento;
		}

		public void setCondicoesAtendimento(String condicoesAtendimento) {
			this.condicoesAtendimento = condicoesAtendimento;
		}

		public String getConservacao() {
			return conservacao;
		}

		public void setConservacao(String conservacao) {
			this.conservacao = conservacao;
		}

		public String getLimpeza() {
			return limpeza;
		}

		public void setLimpeza(String limpeza) {
			this.limpeza = limpeza;
		}

		public String getNaturezaPredio() {
			return naturezaPredio;
		}

		public void setNaturezaPredio(String naturezaPredio) {
			this.naturezaPredio = naturezaPredio;
		}

		public String getOrganizacaoInterna() {
			return organizacaoInterna;
		}

		public void setOrganizacaoInterna(String organizacaoInterna) {
			this.organizacaoInterna = organizacaoInterna;
		}

		public String getRelacaoMobiliario() {
			return relacaoMobiliario;
		}

		public void setRelacaoMobiliario(String relacaoMobiliario) {
			this.relacaoMobiliario = relacaoMobiliario;
		}

		public String getSalaAtendimento() {
			return salaAtendimento;
		}

		public void setSalaAtendimento(String salaAtendimento) {
			this.salaAtendimento = salaAtendimento;
		}

		public String getSalaVeterinario() {
			return salaVeterinario;
		}

		public void setSalaVeterinario(String salaVeterinario) {
			this.salaVeterinario = salaVeterinario;
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
