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

public class FormRFEquipamentosController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormRFEquipamentos formulario = new FormRFEquipamentos();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	List<Municipio> listMunicipios = new ArrayList<>();

	ObservableList<String> observableOpcoes;
	ObservableList<Municipio> observableMunicipio;
	ObservableList<String> observableAvaliacao;

	@FXML
	private TableView<Municipio> tabelaMunicipios;
	@FXML
	private TableColumn<Municipio, String> cMunicipio;
	@FXML
	private TableColumn<Municipio, String> cQuantidade;
	@FXML
	private TableColumn<Municipio, String> cSuficiente;
	@FXML
	private TableColumn<Municipio, String> cCondicoes;
	@FXML
	private TableColumn<Municipio, String> cInternet;
	@FXML
	private TableColumn<Municipio, String> cLinhaTelefonica;
	@FXML
	private TableColumn<Municipio, String> cDemaisEquipamentos;
	@FXML
	private TableColumn<Municipio, String> cComputadoresInternet;
	@FXML
	private JFXTextField municipioEquipamento;
	@FXML
	private JFXComboBox<String> cbInternet;
	@FXML
	private JFXComboBox<String> cbTelefone;
	@FXML
	private JFXComboBox<String> cbDemaisEquipamentos;
	@FXML
	private JFXComboBox<String> cbFluxo;
	@FXML
	private JFXComboBox<String> cbAvaliacao;
	@FXML
	private JFXButton btAdicionarMunicipio;
	@FXML
	private JFXButton btExcluirMunicipio;
	@FXML
	private JFXButton btAdicionar;
	@FXML
	private JFXButton btCancelar;
	@FXML
	private JFXTextField pcQuantidade;
	@FXML
	private JFXComboBox<String> cbSuficiente;
	@FXML
	private JFXComboBox<String> cbCondicoes;
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
	private JFXTextField nComputadoresInternet;
        @FXML
        private Label lblMunicipioEquipamento;
        @FXML
        private Label lblQuantidade;
        @FXML
        private Label lblSuficiente;
        @FXML
        private Label lblCondicoes;
        @FXML
        private Label lblInternet;
        @FXML
        private Label lblTelefone;
        @FXML
        private Label lblDemaisEquipamentos;
        @FXML
        private Label lblComputadoresInternet;
        @FXML
        private Label lblFluxo;
        @FXML
        private Label lblAvaliacao;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cMunicipio.setCellValueFactory(new PropertyValueFactory<>("municipio"));
		cQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadePC"));
		cSuficiente.setCellValueFactory(new PropertyValueFactory<>("suficiente"));
		cCondicoes.setCellValueFactory(new PropertyValueFactory<>("condicoes"));
		cInternet.setCellValueFactory(new PropertyValueFactory<>("internet"));
		cLinhaTelefonica.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		cDemaisEquipamentos.setCellValueFactory(new PropertyValueFactory<>("demaisEquipamentos"));
		cComputadoresInternet.setCellValueFactory(new PropertyValueFactory<>("nComputadores"));

		carregarComboboxOpcoes();

		cbFluxo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Fluxo de informações entre UC-UR-Ulsav-EAC e vice versa"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionarMunicipio(ActionEvent event) {
            if (validarDadosMunicipio()){
		Municipio municipio = new Municipio();

		municipio.setCondicoes(cbCondicoes.getSelectionModel().getSelectedItem());
		municipio.setDemaisEquipamentos(cbDemaisEquipamentos.getSelectionModel().getSelectedItem());
		municipio.setInternet(cbInternet.getSelectionModel().getSelectedItem());
		municipio.setMunicipio(municipioEquipamento.getText());
		municipio.setQuantidadePC(pcQuantidade.getText());
		municipio.setSuficiente(cbSuficiente.getSelectionModel().getSelectedItem());
		municipio.setTelefone(cbTelefone.getSelectionModel().getSelectedItem());
		municipio.setnComputadores(nComputadoresInternet.getText());
		
		listMunicipios.add(municipio);

		observableMunicipio = FXCollections.observableArrayList(listMunicipios);
		tabelaMunicipios.setItems(observableMunicipio);
            }
	}

	@FXML
	private void handleExcluirMunicipio(ActionEvent event) {
		// Instancia um objeto com os dados da supervisao selecionada
		Municipio municipio = tabelaMunicipios.getSelectionModel().getSelectedItem();

		if (municipio == null) {
			AlertMaker.showErrorMessage("Aviso", "Por favor, selecione um registro da tabela.");
		} else {
			// deleta o objeto selecionado
			listMunicipios.remove(municipio);
			observableMunicipio = FXCollections.observableArrayList(listMunicipios);
			tabelaMunicipios.setItems(observableMunicipio);
		}
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setMunicipios(listMunicipios);
		formulario.setFluxo(cbFluxo.getSelectionModel().getSelectedItem());

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

		cbCondicoes.setItems(observableOpcoes);
		cbDemaisEquipamentos.setItems(observableOpcoes);
		cbInternet.setItems(observableOpcoes);
		cbSuficiente.setItems(observableOpcoes);
		cbTelefone.setItems(observableOpcoes);
		cbFluxo.setItems(observableOpcoes);
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

			FormRFEquipamentos formulario = gson.fromJson(resposta.getResposta(), FormRFEquipamentos.class);

			listMunicipios = formulario.getMunicipios();
			
			observableMunicipio = FXCollections.observableArrayList(formulario.getMunicipios());
			tabelaMunicipios.setItems(observableMunicipio);

			cbFluxo.getSelectionModel().select(formulario.getFluxo());

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
        
        //Função para validar dados ao adicionar municipio
        public boolean validarDadosMunicipio(){
            boolean error = false;
            if (municipioEquipamento.getText() == null || municipioEquipamento.getText().length() == 0){
                error = true;
                lblMunicipioEquipamento.setStyle("-fx-text-fill:red");
            }
            if (pcQuantidade.getText() == null || pcQuantidade.getText().length() == 0){
                error = true;
                lblQuantidade.setStyle("-fx-text-fill:red");
            }
            if (cbSuficiente.getSelectionModel().isEmpty()){
                error = true;
                lblSuficiente.setStyle("-fx-text-fill:red");
            }
            if (cbCondicoes.getSelectionModel().isEmpty()){
                error = true;
                lblCondicoes.setStyle("-fx-text-fill:red");
            }
            if (cbInternet.getSelectionModel().isEmpty()){
                error = true;
                lblInternet.setStyle("-fx-text-fill:red");
            }
            if (cbTelefone.getSelectionModel().isEmpty()){
                error = true;
                lblTelefone.setStyle("-fx-text-fill:red");
            }
            if (cbDemaisEquipamentos.getSelectionModel().isEmpty()){
                error = true;
                lblDemaisEquipamentos.setStyle("-fx-text-fill:red");
            }
            if (nComputadoresInternet.getText() == null || nComputadoresInternet.getText().length() == 0){
                error = true;
                lblComputadoresInternet.setStyle("-fx-text-fill:red");
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
            /**if (tabelaMunicipios.getItems().isEmpty()){
                error = true;
                tabelaMunicipios.setStyle("-fx-border-color:red");
            }**/
            if (cbFluxo.getSelectionModel().isEmpty()){
                error = true;
                lblFluxo.setStyle("-fx-text-fill:red");
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

	public class FormRFEquipamentos {

		private List<Municipio> municipios;
		
		private String fluxo;

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

		public List<Municipio> getMunicipios() {
			return municipios;
		}

		public void setMunicipios(List<Municipio> municipios) {
			this.municipios = municipios;
		}

		public String getFluxo() {
			return fluxo;
		}

		public void setFluxo(String fluxo) {
			this.fluxo = fluxo;
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

	public class Municipio {

		private String municipio;
		private String internet;
		private String telefone;
		private String demaisEquipamentos;
		private String quantidadePC;
		private String suficiente;
		private String condicoes;
		private String nComputadores;

		public String getMunicipio() {
			return municipio;
		}

		public void setMunicipio(String municipio) {
			this.municipio = municipio;
		}
		
		public String getnComputadores() {
			return nComputadores;
		}

		public void setnComputadores(String nComputadores) {
			this.nComputadores = nComputadores;
		}

		public String getInternet() {
			return internet;
		}

		public void setInternet(String internet) {
			this.internet = internet;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public String getDemaisEquipamentos() {
			return demaisEquipamentos;
		}

		public void setDemaisEquipamentos(String demaisEquipamentos) {
			this.demaisEquipamentos = demaisEquipamentos;
		}

		public String getQuantidadePC() {
			return quantidadePC;
		}

		public void setQuantidadePC(String quantidadePC) {
			this.quantidadePC = quantidadePC;
		}

		public String getSuficiente() {
			return suficiente;
		}

		public void setSuficiente(String suficiente) {
			this.suficiente = suficiente;
		}

		public String getCondicoes() {
			return condicoes;
		}

		public void setCondicoes(String condicoes) {
			this.condicoes = condicoes;
		}
	}
}
