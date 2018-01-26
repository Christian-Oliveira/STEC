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

public class FormRFTransportesController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormRFTransportes formulario = new FormRFTransportes();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	List<Transporte> listTransportes = new ArrayList<>();
	List<String> listTipoTransporte = new ArrayList<>();

	ObservableList<String> observableTipoTransportes;
	ObservableList<String> observableOpcoes;
	ObservableList<Transporte> observableTransportes;
	ObservableList<String> observableAvaliacao;

	@FXML
	private TableView<Transporte> tabelaTransportes;
	@FXML
	private TableColumn<Transporte, String> cTipo;
	@FXML
	private TableColumn<Transporte, String> cQuantidade;
	@FXML
	private TableColumn<Transporte, String> cConservacao;
	@FXML
	private TableColumn<Transporte, String> cAno;
	@FXML
	private TableColumn<Transporte, String> cValor;
	@FXML
	private TableColumn<Transporte, String> cAtende;
	@FXML
	private TableColumn<Transporte, String> cPreventiva;
	@FXML
	private TableColumn<Transporte, String> cEmergencial;
	@FXML
	private TableColumn<Transporte, String> cMapaKM;
	@FXML
	private TableColumn<Transporte, String> cCartao;
	@FXML
	private JFXTextField quantidade;
	@FXML
	private JFXButton btAdicionarTransporte;
	@FXML
	private JFXButton btExcluirTransporte;
	@FXML
	private JFXComboBox<String> cbConservacao;
	@FXML
	private JFXComboBox<String> cbMapaKM;
	@FXML
	private JFXComboBox<String> cbCartaoAbastecimento;
	@FXML
	private JFXComboBox<String> cbTipo;
	@FXML
	private JFXComboBox<String> cbCondutores;
	@FXML
	private JFXComboBox<String> cbRelacaoVeiculosLotacao;
	@FXML
	private JFXComboBox<String> cbFrotaAdequada;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
	@FXML
	private JFXButton btAdicionar;
	@FXML
	private JFXButton btCancelar;
	@FXML
	private JFXTextField valor;
	@FXML
	private JFXComboBox<String> cbAtendeDemanda;
	@FXML
	private JFXComboBox<String> cbPreventiva;
	@FXML
	private JFXComboBox<String> cbEmergencial;
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
	@FXML
	private JFXTextField ano;
        @FXML
        private Label lblTipo;
        @FXML
        private Label lblQuantidade;
        @FXML
        private Label lblConservacao;
        @FXML
        private Label lblAno;
        @FXML
        private Label lblValor;
        @FXML
        private Label lblAtendeDemanda;
        @FXML
        private Label lblPreventiva;
         @FXML
        private Label lblEmergencial;
        @FXML
        private Label lblMapaKM;
        @FXML
        private Label lblCartaoAbastecimento;
        @FXML
        private Label lblCondutores;
        @FXML
        private Label lblRelacaoVeiculosLotacao;
        @FXML
        private Label lblFrotaAdequada;
        @FXML
        private Label lblAvaliacao;


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		cAtende.setCellValueFactory(new PropertyValueFactory<>("atendeDemanda"));// deve ser exatamente o nome do atributo da classe
		cCartao.setCellValueFactory(new PropertyValueFactory<>("cartaoAbastecimento"));
		cConservacao.setCellValueFactory(new PropertyValueFactory<>("conservacao"));
		cEmergencial.setCellValueFactory(new PropertyValueFactory<>("manutencaoEmergencial"));
		cMapaKM.setCellValueFactory(new PropertyValueFactory<>("registroKM"));
		cPreventiva.setCellValueFactory(new PropertyValueFactory<>("manutencaoPreventiva"));
		cQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		cTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		cValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

		listTipoTransporte.add("Carro");
		listTipoTransporte.add("Caminhonete");
		listTipoTransporte.add("Moto");
		listTipoTransporte.add("Embarcacao");

		carregarComboboxOpcoes();
		carregarComboboxTipoTransporte();

		cbCondutores.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Condutores para veículos"));
		cbRelacaoVeiculosLotacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Possui a relação dos veículos e a lotação (ULSAV e EAC)?"));
		cbFrotaAdequada.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "A frota é adequada à região?"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionarTransporte(ActionEvent event) {
            if (validarDadosTransporte()){
		Transporte transporte = new Transporte();

		transporte.setAno(ano.getText());
		transporte.setAtendeDemanda(cbAtendeDemanda.getSelectionModel().getSelectedItem());
		transporte.setCartaoAbastecimento(cbCartaoAbastecimento.getSelectionModel().getSelectedItem());
		transporte.setConservacao(cbConservacao.getSelectionModel().getSelectedItem());
		transporte.setManutencaoEmergencial(cbEmergencial.getSelectionModel().getSelectedItem());
		transporte.setManutencaoPreventiva(cbPreventiva.getSelectionModel().getSelectedItem());
		transporte.setQuantidade(quantidade.getText());
		transporte.setRegistroKM(cbMapaKM.getSelectionModel().getSelectedItem());
		transporte.setTipo(cbTipo.getSelectionModel().getSelectedItem());
		transporte.setValor(valor.getText());

		listTransportes.add(transporte);

		observableTransportes = FXCollections.observableArrayList(listTransportes);
		tabelaTransportes.setItems(observableTransportes);
            }
	}

	@FXML
	private void handleExcluirTransporte(ActionEvent event) {
		// Instancia um objeto com os dados da supervisao selecionada
		Transporte transporte = tabelaTransportes.getSelectionModel().getSelectedItem();

		if (transporte == null) {
			AlertMaker.showErrorMessage("Aviso", "Por favor, selecione um registro da tabela.");
		} else {
			// deleta o objeto selecionado
			listTransportes.remove(transporte);
			observableTransportes = FXCollections.observableArrayList(listTransportes);
			tabelaTransportes.setItems(observableTransportes);
		}
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setTransportes(listTransportes);
		formulario.setCondutoresVeiculo(cbCondutores.getSelectionModel().getSelectedItem());
		formulario.setRelacaoVeiculos(cbRelacaoVeiculosLotacao.getSelectionModel().getSelectedItem());
		formulario.setFrotaAdequada(cbFrotaAdequada.getSelectionModel().getSelectedItem());

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
		cbAtendeDemanda.setItems(observableOpcoes);
		cbCartaoAbastecimento.setItems(observableOpcoes);
		cbCondutores.setItems(observableOpcoes);
		cbConservacao.setItems(observableOpcoes);
		cbEmergencial.setItems(observableOpcoes);
		cbFrotaAdequada.setItems(observableOpcoes);
		cbMapaKM.setItems(observableOpcoes);
		cbPreventiva.setItems(observableOpcoes);
		cbRelacaoVeiculosLotacao.setItems(observableOpcoes);
	}

	public void carregarComboboxTipoTransporte() {
		observableTipoTransportes = FXCollections.observableList(listTipoTransporte);

		cbTipo.setItems(observableTipoTransportes);
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

			FormRFTransportes formulario = gson.fromJson(resposta.getResposta(), FormRFTransportes.class);

			listTransportes = formulario.getTransportes();// passa as capacitacoes pro list
			observableTransportes = FXCollections.observableArrayList(formulario.getTransportes());
			tabelaTransportes.setItems(observableTransportes);

			cbFrotaAdequada.getSelectionModel().select(formulario.getFrotaAdequada());
			cbRelacaoVeiculosLotacao.getSelectionModel().select(formulario.getRelacaoVeiculos());
			cbCondutores.getSelectionModel().select(formulario.getCondutoresVeiculo());

			cbAvaliacao.getSelectionModel().select(formulario.getAvaliacao());
			txtComentarios.setText(formulario.getComentario());
			txtPrazo.setText(formulario.getPrazo());
			txtRUC.setText(formulario.getRecomendacaoUC());
			txtRULSAVEAC.setText(formulario.getRecomendacaoUlsavEac());
			txtRUR.setText(formulario.getRecomendacaoUr());
		}
	}

        @Override
	public Stage getDialog() {
		return dialog;
	}

        @Override
	public void setDialog(Stage dialog) {
		this.dialog = dialog;
	}

        @Override
	public boolean isBtConfirmarClicked() {
		return btConfirmarClicked;
	}

        @Override
	public void setBtConfirmarClicked(boolean btConfirmarClicked) {
		this.btConfirmarClicked = btConfirmarClicked;
	}
        
        //Função para validar dados ao adicionar transportes
        public boolean validarDadosTransporte(){
            boolean error = false;
            if (cbTipo.getSelectionModel().isEmpty()){
                error = true;
                lblTipo.setStyle("-fx-text-fill:red");
            }
            if (quantidade.getText() == null || quantidade.getText().length() == 0){
                error = true;
                lblQuantidade.setStyle("-fx-text-fill:red");
            }
            if (cbConservacao.getSelectionModel().isEmpty()){
                error = true;
                lblConservacao.setStyle("-fx-text-fill:red");
            }
            if (ano.getText() == null || ano.getText().length() == 0){
                error = true;
                lblAno.setStyle("-fx-text-fill:red");
            }
            if (valor.getText() == null || valor.getText().length() == 0){
                error = true;
                lblValor.setStyle("-fx-text-fill:red");
            }
            if (cbAtendeDemanda.getSelectionModel().isEmpty()){
                error = true;
                lblAtendeDemanda.setStyle("-fx-text-fill:red");
            }
            if (cbPreventiva.getSelectionModel().isEmpty()){
                error = true;
                lblPreventiva.setStyle("-fx-text-fill:red");
            }
            if (cbEmergencial.getSelectionModel().isEmpty()){
                error = true;
                lblEmergencial.setStyle("-fx-text-fill:red");
            }
            if (cbMapaKM.getSelectionModel().isEmpty()){
                error = true;
                lblMapaKM.setStyle("-fx-text-fill:red");
            }
            if (cbCartaoAbastecimento.getSelectionModel().isEmpty()){
                error = true;
                lblCartaoAbastecimento.setStyle("-fx-text-fill:red");
            }
            if (error == false){
                return true;
            }else{
                //Mostrando a menssagem de erro
                AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
                return false;
            }
        }

        //Função para validar dados
        @Override
        public boolean validarDados(){
            boolean error = false;
            /**if (tabelaTransportes.getItems().isEmpty()){
                error = true;
                tabelaTransportes.setStyle("-fx-border-color:red");
            }**/
            if (cbCondutores.getSelectionModel().isEmpty()){
                error = true;
                lblCondutores.setStyle("-fx-text-fill:red");
            }
            if (cbRelacaoVeiculosLotacao.getSelectionModel().isEmpty()){
                error = true;
                lblRelacaoVeiculosLotacao.setStyle("-fx-text-fill:red");
            }
            if (cbFrotaAdequada.getSelectionModel().isEmpty()){
                error = true;
                lblFrotaAdequada.setStyle("-fx-text-fill:red");
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
	public class FormRFTransportes {
		private List<Transporte> transportes;
		private String condutoresVeiculo;
		private String relacaoVeiculos;
		private String frotaAdequada;

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

		public List<Transporte> getTransportes() {
			return transportes;
		}

		public void setTransportes(List<Transporte> transportes) {
			this.transportes = transportes;
		}

		public String getCondutoresVeiculo() {
			return condutoresVeiculo;
		}

		public void setCondutoresVeiculo(String condutoresVeiculo) {
			this.condutoresVeiculo = condutoresVeiculo;
		}

		public String getRelacaoVeiculos() {
			return relacaoVeiculos;
		}

		public void setRelacaoVeiculos(String relacaoVeiculos) {
			this.relacaoVeiculos = relacaoVeiculos;
		}

		public String getFrotaAdequada() {
			return frotaAdequada;
		}

		public void setFrotaAdequada(String frotaAdequada) {
			this.frotaAdequada = frotaAdequada;
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

	public class Transporte {

		private String tipo;
		private String quantidade;
		private String conservacao;
		private String ano;
		private String valor;
		private String atendeDemanda;
		private String manutencaoPreventiva;
		private String manutencaoEmergencial;
		private String registroKM;
		private String cartaoAbastecimento;

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(String quantidade) {
			this.quantidade = quantidade;
		}

		public String getConservacao() {
			return conservacao;
		}

		public void setConservacao(String conservacao) {
			this.conservacao = conservacao;
		}

		public String getAno() {
			return ano;
		}

		public void setAno(String ano) {
			this.ano = ano;
		}

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}

		public String getAtendeDemanda() {
			return atendeDemanda;
		}

		public void setAtendeDemanda(String atendeDemanda) {
			this.atendeDemanda = atendeDemanda;
		}

		public String getManutencaoPreventiva() {
			return manutencaoPreventiva;
		}

		public void setManutencaoPreventiva(String manutencaoPreventiva) {
			this.manutencaoPreventiva = manutencaoPreventiva;
		}

		public String getManutencaoEmergencial() {
			return manutencaoEmergencial;
		}

		public void setManutencaoEmergencial(String manutencaoEmergencial) {
			this.manutencaoEmergencial = manutencaoEmergencial;
		}

		public String getRegistroKM() {
			return registroKM;
		}

		public void setRegistroKM(String registroKM) {
			this.registroKM = registroKM;
		}

		public String getCartaoAbastecimento() {
			return cartaoAbastecimento;
		}

		public void setCartaoAbastecimento(String cartaoAbastecimento) {
			this.cartaoAbastecimento = cartaoAbastecimento;
		}

	}
}
