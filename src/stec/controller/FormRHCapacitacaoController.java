package stec.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.Resposta;
import stec.resources.Classes.AlertMaker;
import stec.resources.Classes.IFormulario;

public class FormRHCapacitacaoController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormRHCapacitacao formulario = new FormRHCapacitacao();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	List<Capacitacao> listCapacitacoes = new ArrayList<>();

	ObservableList<String> observableOpcoes;
	ObservableList<Capacitacao> observableCapacitacoes;
	ObservableList<String> observableAvaliacao;

	@FXML
	private TableView<Capacitacao> tabelaCapacitacoes;
	@FXML
	private TableColumn<Capacitacao, String> cNome;
	@FXML
	private TableColumn<Capacitacao, String> cCargo;
	@FXML
	private TableColumn<Capacitacao, String> cTreinamento;
	@FXML
	private TableColumn<Capacitacao, String> cAno;
	@FXML
	private JFXTextField nome;
	@FXML
	private JFXTextField cargo;
	@FXML
	private JFXTextField treinamento;
	@FXML
	private JFXTextField ano;
        @FXML
        private Label lblAplicacaoPratica;
        @FXML
        private Label lblAvaliacao;
	@FXML
	private JFXButton btAdicionarCapacitacao;
	@FXML
	private JFXComboBox<String> cbAplicacaoPratica;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
	@FXML
	private JFXButton btExcluirCapacitacao;
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
		cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));// deve ser exatamente o nome do atributo da classe
		cTreinamento.setCellValueFactory(new PropertyValueFactory<>("treinamento"));
		cAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

		carregarComboboxOpcoes();

		cbAplicacaoPratica.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Aplicação prática das capacitações na rotina de trabalho"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML // funcionando
	private void handleAdicionarCapacitacao(ActionEvent event) {
            if (validarDadosCapacitacao()){
		Capacitacao capacitacao = new Capacitacao();

		capacitacao.setNome(nome.getText());
		capacitacao.setCargo(cargo.getText());
		capacitacao.setTreinamento(treinamento.getText());
		capacitacao.setAno(ano.getText());

		listCapacitacoes.add(capacitacao);

		observableCapacitacoes = FXCollections.observableArrayList(listCapacitacoes);
		tabelaCapacitacoes.setItems(observableCapacitacoes);
            }
	}

	@FXML // funcionando
	private void handleExcluirCapacitacao(ActionEvent event) {
		// Instancia um objeto com os dados da supervisao selecionada
		Capacitacao capacitacao = tabelaCapacitacoes.getSelectionModel().getSelectedItem();

		if (capacitacao == null) {
			AlertMaker.showErrorMessage("Aviso", "Por favor, selecione um registro da tabela.");
		} else {
			// deleta o objeto selecionado
			listCapacitacoes.remove(capacitacao);
			observableCapacitacoes = FXCollections.observableArrayList(listCapacitacoes);
			tabelaCapacitacoes.setItems(observableCapacitacoes);
		}
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setCapacitacoes(listCapacitacoes);
		formulario.setAplicacaoPratica(cbAplicacaoPratica.getSelectionModel().getSelectedItem());

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
		cbAplicacaoPratica.setItems(observableOpcoes);
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

			FormRHCapacitacao formulario = gson.fromJson(resposta.getResposta(), FormRHCapacitacao.class);

			listCapacitacoes = formulario.getCapacitacoes();// passa as capacitacoes pro list
			observableCapacitacoes = FXCollections.observableArrayList(formulario.getCapacitacoes());
			tabelaCapacitacoes.setItems(observableCapacitacoes);

			cbAplicacaoPratica.getSelectionModel().select(formulario.getAplicacaoPratica());

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
        
        //Função para validar dados de capacitação
        public boolean validarDadosCapacitacao(){
            boolean error = false;
            if (nome.getText() == null || nome.getText().length() == 0){
                error = true;
                nome.setStyle("-jfx-unfocus-color:red");
            }
            if (treinamento.getText() == null || treinamento.getText().length() == 0){
                error = true;
                treinamento.setStyle("-jfx-unfocus-color:red");
            }
            if (cargo.getText() == null || cargo.getText().length() == 0){
                error = true;
                cargo.setStyle("-jfx-unfocus-color:red");
            }
            if (ano.getText() == null || ano.getText().length() == 0){
                error = true;
                ano.setStyle("-jfx-unfocus-color:red");
            }
            if (error == false){
                return true;
            }else{
                //Mostrando a menssagem de erro
                AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
                return false;
            }
        }
        
        //Função de validação de entrada de dados
        @Override
        public boolean validarDados(){
            boolean error = false;
            /**if (tabelaCapacitacoes.getItems().isEmpty()){
                error = true;
                tabelaCapacitacoes.setStyle("-fx-border-color:red");
            }**/
            if (cbAplicacaoPratica.getSelectionModel().isEmpty()){
                error = true;
                lblAplicacaoPratica.setStyle("-fx-text-fill:red");
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

	public class FormRHCapacitacao {

		private List<Capacitacao> capacitacoes;
		private String aplicacaoPratica;

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

		public List<Capacitacao> getCapacitacoes() {
			return capacitacoes;
		}

		public void setCapacitacoes(List<Capacitacao> capacitacoes) {
			this.capacitacoes = capacitacoes;
		}

		public String getAplicacaoPratica() {
			return aplicacaoPratica;
		}

		public void setAplicacaoPratica(String aplicacaoPratica) {
			this.aplicacaoPratica = aplicacaoPratica;
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

	public class Capacitacao {

		private String nome;
		private String cargo;
		private String treinamento;
		private String ano;

		public Capacitacao() {
		}

		@Override
		public String toString() {
			return "Capacitacao[ " + "Nome: " + nome + " Cargo: " + cargo + " Treinamento: " + treinamento + " Ano: "
					+ ano + " ]";
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCargo() {
			return cargo;
		}

		public void setCargo(String cargo) {
			this.cargo = cargo;
		}

		public String getTreinamento() {
			return treinamento;
		}

		public void setTreinamento(String treinamento) {
			this.treinamento = treinamento;
		}

		public String getAno() {
			return ano;
		}

		public void setAno(String ano) {
			this.ano = ano;
		}

	}
}
