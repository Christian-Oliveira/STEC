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

public class FormRHQuantitativoController implements Initializable, IFormulario {

	Resposta resposta = new Resposta();
	FormRHQuantitativo formulario = new FormRHQuantitativo();
	private boolean btConfirmarClicked = false;
	private Stage dialog;

	SupervisaoDAO supervisaoDAO = new SupervisaoDAO();

	ObservableList<String> observableOpcoes;
	ObservableList<String> observableAvaliacao;

	@FXML
	private JFXTextField vetQEAFA;
	@FXML
	private JFXTextField vetQEANS;
	@FXML
	private JFXTextField vetQEEMARPH;
	@FXML
	private JFXTextField vetQESAGRIMA;
	@FXML
	private JFXTextField vetQESaude;
	@FXML
	private JFXTextField vetQEPrefeitura;
	@FXML
	private JFXTextField vetQEINAGRO;
	@FXML
	private JFXTextField vetCTAFA;
	@FXML
	private JFXTextField vetCTANS;
	@FXML
	private JFXTextField vetCTEMARPH;
	@FXML
	private JFXTextField vetCTSAGRIMA;
	@FXML
	private JFXTextField vetCTSaude;
	@FXML
	private JFXTextField vetCTPrefeitura;
	@FXML
	private JFXTextField vetCTINAGRO;
	@FXML
	private JFXTextField vetCPAFA;
	@FXML
	private JFXTextField vetCPANS;
	@FXML
	private JFXTextField vetCPEMARPH;
	@FXML
	private JFXTextField vetCPSAGRIMA;
	@FXML
	private JFXTextField vetCPSaude;
	@FXML
	private JFXTextField vetCPPrefeitura;
	@FXML
	private JFXTextField vetCPINAGRO;
	@FXML
	private JFXTextField agroQEAFA;
	@FXML
	private JFXTextField agroQEANS;
	@FXML
	private JFXTextField agroQEEMARPH;
	@FXML
	private JFXTextField agroQESAGRIMA;
	@FXML
	private JFXTextField agroQESaude;
	@FXML
	private JFXTextField agroQEPrefeitura;
	@FXML
	private JFXTextField agroQEINAGRO;
	@FXML
	private JFXTextField agroCTAFA;
	@FXML
	private JFXTextField agroCTANS;
	@FXML
	private JFXTextField agroCTEMARPH;
	@FXML
	private JFXTextField agroCTSAGRIMA;
	@FXML
	private JFXTextField agroCTSaude;
	@FXML
	private JFXTextField agroCTPrefeitura;
	@FXML
	private JFXTextField agroCTINAGRO;
	@FXML
	private JFXTextField agroCPAFA;
	@FXML
	private JFXTextField agroCPANS;
	@FXML
	private JFXTextField agroCPEMARPH;
	@FXML
	private JFXTextField agroCPSAGRIMA;
	@FXML
	private JFXTextField agroCPSaude;
	@FXML
	private JFXTextField agroCPPrefeitura;
	@FXML
	private JFXTextField agroCPINAGRO;
	@FXML
	private JFXTextField florQEAFA;
	@FXML
	private JFXTextField florQEANS;
	@FXML
	private JFXTextField florQEEMARPH;
	@FXML
	private JFXTextField florQESAGRIMA;
	@FXML
	private JFXTextField florQESaude;
	@FXML
	private JFXTextField florQEPrefeitura;
	@FXML
	private JFXTextField florQEINAGRO;
	@FXML
	private JFXTextField florCTAFA;
	@FXML
	private JFXTextField florCTANS;
	@FXML
	private JFXTextField florCTEMARPH;
	@FXML
	private JFXTextField florCTSAGRIMA;
	@FXML
	private JFXTextField florCTSaude;
	@FXML
	private JFXTextField florCTPrefeitura;
	@FXML
	private JFXTextField florCTINAGRO;
	@FXML
	private JFXTextField florCPAFA;
	@FXML
	private JFXTextField florCPANS;
	@FXML
	private JFXTextField florCPEMARPH;
	@FXML
	private JFXTextField florCPSAGRIMA;
	@FXML
	private JFXTextField florCPSaude;
	@FXML
	private JFXTextField florCPPrefeitura;
	@FXML
	private JFXTextField florCPINAGRO;
	@FXML
	private JFXTextField zooQEAFA;
	@FXML
	private JFXTextField zooQEANS;
	@FXML
	private JFXTextField zooQEEMARPH;
	@FXML
	private JFXTextField zooQESAGRIMA;
	@FXML
	private JFXTextField zooQESaude;
	@FXML
	private JFXTextField zooQEPrefeitura;
	@FXML
	private JFXTextField zooQEINAGRO;
	@FXML
	private JFXTextField zooCTANS;
	@FXML
	private JFXTextField zooCTEMARPH;
	@FXML
	private JFXTextField zooCTSAGRIMA;
	@FXML
	private JFXTextField zooCTSaude;
	@FXML
	private JFXTextField zooCTPrefeitura;
	@FXML
	private JFXTextField zooCTINAGRO;
	@FXML
	private JFXTextField zooCPAFA;
	@FXML
	private JFXTextField zooCPANS;
	@FXML
	private JFXTextField zooCTAFA;
	@FXML
	private JFXTextField zooCPEMARPH;
	@FXML
	private JFXTextField zooCPSAGRIMA;
	@FXML
	private JFXTextField zooCPSaude;
	@FXML
	private JFXTextField zooCPPrefeitura;
	@FXML
	private JFXTextField zooCPINAGRO;
	@FXML
	private JFXTextField tecAgroQEAFA;
	@FXML
	private JFXTextField tecAgroQEANS;
	@FXML
	private JFXTextField tecAgroQEEMARPH;
	@FXML
	private JFXTextField tecAgroQESAGRIMA;
	@FXML
	private JFXTextField tecAgroQESEDUC;
	@FXML
	private JFXTextField tecAgroQESaude;
	@FXML
	private JFXTextField tecAgroQEPrefeitura;
	@FXML
	private JFXTextField tecAgroQEINAGRO;
	@FXML
	private JFXTextField tecAgroCTAFA;
	@FXML
	private JFXTextField tecAgroCTANS;
	@FXML
	private JFXTextField tecAgroCTEMARPH;
	@FXML
	private JFXTextField tecAgroCTSAGRIMA;
	@FXML
	private JFXTextField tecAgroCTSEDUC;
	@FXML
	private JFXTextField tecAgroCTSaude;
	@FXML
	private JFXTextField tecAgroCTPrefeitura;
	@FXML
	private JFXTextField tecAgroCTINAGRO;
	@FXML
	private JFXTextField auxAdmQEAFA;
	@FXML
	private JFXTextField auxAdmQEANS;
	@FXML
	private JFXTextField auxAdmQEADO;
	@FXML
	private JFXTextField auxAdmQEEMARPH;
	@FXML
	private JFXTextField auxAdmQESAGRIMA;
	@FXML
	private JFXTextField auxAdmQESEDUC;
	@FXML
	private JFXTextField auxAdmQESaude;
	@FXML
	private JFXTextField auxAdmQEPrefeitura;
	@FXML
	private JFXTextField auxAdmQEINAGRO;
	@FXML
	private JFXTextField auxAdmCTAFA;
	@FXML
	private JFXTextField auxAdmCTANS;
	@FXML
	private JFXTextField auxAdmCTADO;
	@FXML
	private JFXTextField auxAdmCTEMARPH;
	@FXML
	private JFXTextField auxAdmCTSAGRIMA;
	@FXML
	private JFXTextField auxAdmCTSEDUC;
	@FXML
	private JFXTextField auxAdmCTSaude;
	@FXML
	private JFXTextField auxAdmCTPrefeitura;
	@FXML
	private JFXTextField auxAdmCTINAGRO;
	@FXML
	private JFXComboBox<String> cbAtendeDemanda;
	@FXML
	private JFXTextField funcionamentoManha;
	@FXML
	private JFXTextField funcionamentoTarde;
        @FXML
        private Label lblHorarioFuncionamento;
        @FXML
        private Label lblAtendeDemanda;
        @FXML
        private Label lblAvaliacao;
	@FXML
	private JFXButton btAdicionar;
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
	private JFXButton btCancelar;
	@FXML
	private JFXComboBox<String> cbAvaliacao;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		carregarComboboxOpcoes();

		cbAtendeDemanda.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> adicionarComentario(newValue, "Considerando a quantidade: atende à demanda?"));
	}

	public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}

	@FXML
	private void handleAdicionar(ActionEvent event) {
            if (validarDados()){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		formulario.setVetQEAFA(vetQEAFA.getText());
		formulario.setVetQEANS(vetQEANS.getText());
		formulario.setVetQEEMARPH(vetQEEMARPH.getText());
		formulario.setVetQESAGRIMA(vetQESAGRIMA.getText());
		formulario.setVetQESaude(vetQESaude.getText());
		formulario.setVetQEPrefeitura(vetQEPrefeitura.getText());
		formulario.setVetQEINAGRO(vetQEINAGRO.getText());
		formulario.setVetCTAFA(vetCTAFA.getText());
		formulario.setVetCTANS(vetCTANS.getText());
		formulario.setVetCTEMARPH(vetCTEMARPH.getText());
		formulario.setVetCTSAGRIMA(vetCTSAGRIMA.getText());
		formulario.setVetCTSaude(vetCTSaude.getText());
		formulario.setVetCTPrefeitura(vetCTPrefeitura.getText());
		formulario.setVetCTINAGRO(vetCTINAGRO.getText());
		formulario.setVetCPAFA(vetCPAFA.getText());
		formulario.setVetCPANS(vetCPANS.getText());
		formulario.setVetCPEMARPH(vetCPEMARPH.getText());
		formulario.setVetCPSAGRIMA(vetCPSAGRIMA.getText());
		formulario.setVetCPSaude(vetCPSaude.getText());
		formulario.setVetCPPrefeitura(vetCPPrefeitura.getText());
		formulario.setVetCPINAGRO(vetCPINAGRO.getText());

		formulario.setAgroQEAFA(agroQEAFA.getText());
		formulario.setAgroQEANS(agroQEANS.getText());
		formulario.setAgroQEEMARPH(agroQEEMARPH.getText());
		formulario.setAgroQESAGRIMA(agroQESAGRIMA.getText());
		formulario.setAgroQESaude(agroQESaude.getText());
		formulario.setAgroQEPrefeitura(agroQEPrefeitura.getText());
		formulario.setAgroQEINAGRO(agroQEINAGRO.getText());
		formulario.setAgroCTAFA(agroCTAFA.getText());
		formulario.setAgroCTANS(agroCTANS.getText());
		formulario.setAgroCTEMARPH(agroCTEMARPH.getText());
		formulario.setAgroCTSAGRIMA(agroCTSAGRIMA.getText());
		formulario.setAgroCTSaude(agroCTSaude.getText());
		formulario.setAgroCTPrefeitura(agroCTPrefeitura.getText());
		formulario.setAgroCTINAGRO(agroCTINAGRO.getText());
		formulario.setAgroCPAFA(agroCPAFA.getText());
		formulario.setAgroCPANS(agroCPANS.getText());
		formulario.setAgroCPEMARPH(agroCPEMARPH.getText());
		formulario.setAgroCPSAGRIMA(agroCPSAGRIMA.getText());
		formulario.setAgroCPSaude(agroCPSaude.getText());
		formulario.setAgroCPPrefeitura(agroCPPrefeitura.getText());
		formulario.setAgroCPINAGRO(agroCPINAGRO.getText());

		formulario.setFlorQEAFA(florQEAFA.getText());
		formulario.setFlorQEANS(florQEANS.getText());
		formulario.setFlorQEEMARPH(florQEEMARPH.getText());
		formulario.setFlorQESAGRIMA(florQESAGRIMA.getText());
		formulario.setFlorQESaude(florQESaude.getText());
		formulario.setFlorQEPrefeitura(florQEPrefeitura.getText());
		formulario.setFlorQEINAGRO(florQEINAGRO.getText());
		formulario.setFlorCTAFA(florCTAFA.getText());
		formulario.setFlorCTANS(florCTANS.getText());
		formulario.setFlorCTEMARPH(florCTEMARPH.getText());
		formulario.setFlorCTSAGRIMA(florCTSAGRIMA.getText());
		formulario.setFlorCTSaude(florCTSaude.getText());
		formulario.setFlorCTPrefeitura(florCTPrefeitura.getText());
		formulario.setFlorCTINAGRO(florCTINAGRO.getText());
		formulario.setFlorCPAFA(florCPAFA.getText());
		formulario.setFlorCPANS(florCPANS.getText());
		formulario.setFlorCPEMARPH(florCPEMARPH.getText());
		formulario.setFlorCPSAGRIMA(florCPSAGRIMA.getText());
		formulario.setFlorCPSaude(florCPSaude.getText());
		formulario.setFlorCPPrefeitura(florCPPrefeitura.getText());
		formulario.setFlorCPINAGRO(florCPINAGRO.getText());

		formulario.setZooQEAFA(zooQEAFA.getText());
		formulario.setZooQEANS(zooQEANS.getText());
		formulario.setZooQEEMARPH(zooQEEMARPH.getText());
		formulario.setZooQESAGRIMA(zooQESAGRIMA.getText());
		formulario.setZooQESaude(zooQESaude.getText());
		formulario.setZooQEPrefeitura(zooQEPrefeitura.getText());
		formulario.setZooQEINAGRO(zooQEINAGRO.getText());
		formulario.setZooCTAFA(zooCTAFA.getText());
		formulario.setZooCTANS(zooCTANS.getText());
		formulario.setZooCTEMARPH(zooCTEMARPH.getText());
		formulario.setZooCTSAGRIMA(zooCTSAGRIMA.getText());
		formulario.setZooCTSaude(zooCTSaude.getText());
		formulario.setZooCTPrefeitura(zooCTPrefeitura.getText());
		formulario.setZooCTINAGRO(zooCTINAGRO.getText());
		formulario.setZooCPAFA(zooCPAFA.getText());
		formulario.setZooCPANS(zooCPANS.getText());
		formulario.setZooCPEMARPH(zooCPEMARPH.getText());
		formulario.setZooCPSAGRIMA(zooCPSAGRIMA.getText());
		formulario.setZooCPSaude(zooCPSaude.getText());
		formulario.setZooCPPrefeitura(zooCPPrefeitura.getText());
		formulario.setZooCPINAGRO(zooCPINAGRO.getText());

		formulario.setTecAgroQEAFA(tecAgroQEAFA.getText());
		formulario.setTecAgroQEANS(tecAgroQEANS.getText());
		formulario.setTecAgroQEEMARPH(tecAgroQEEMARPH.getText());
		formulario.setTecAgroQESAGRIMA(tecAgroQESAGRIMA.getText());
		formulario.setTecAgroQESEDUC(tecAgroQESEDUC.getText());
		formulario.setTecAgroQESaude(tecAgroQESaude.getText());
		formulario.setTecAgroQEPrefeitura(tecAgroQEPrefeitura.getText());
		formulario.setTecAgroQEINAGRO(tecAgroQEINAGRO.getText());
		formulario.setTecAgroCTAFA(tecAgroCTAFA.getText());
		formulario.setTecAgroCTANS(tecAgroCTANS.getText());
		formulario.setTecAgroCTEMARPH(tecAgroCTEMARPH.getText());
		formulario.setTecAgroCTSAGRIMA(tecAgroCTSAGRIMA.getText());
		formulario.setTecAgroCTSEDUC(tecAgroCTSEDUC.getText());
		formulario.setTecAgroCTSaude(tecAgroCTSaude.getText());
		formulario.setTecAgroCTPrefeitura(tecAgroCTPrefeitura.getText());
		formulario.setTecAgroCTINAGRO(tecAgroCTINAGRO.getText());

		formulario.setAuxAdmQEAFA(auxAdmQEAFA.getText());
		formulario.setAuxAdmQEANS(auxAdmQEANS.getText());
		formulario.setAuxAdmQEADO(auxAdmQEADO.getText());
		formulario.setAuxAdmQEEMARPH(auxAdmQEEMARPH.getText());
		formulario.setAuxAdmQESAGRIMA(auxAdmQESAGRIMA.getText());
		formulario.setAuxAdmQESEDUC(auxAdmQESEDUC.getText());
		formulario.setAuxAdmQESaude(auxAdmQESaude.getText());
		formulario.setAuxAdmQEPrefeitura(auxAdmQEPrefeitura.getText());
		formulario.setAuxAdmQEINAGRO(auxAdmQEINAGRO.getText());
		formulario.setAuxAdmCTAFA(auxAdmCTAFA.getText());
		formulario.setAuxAdmCTANS(auxAdmCTANS.getText());
		formulario.setAuxAdmCTADO(auxAdmCTADO.getText());
		formulario.setAuxAdmCTEMARPH(auxAdmCTEMARPH.getText());
		formulario.setAuxAdmCTSAGRIMA(auxAdmCTSAGRIMA.getText());
		formulario.setAuxAdmCTSEDUC(auxAdmCTSEDUC.getText());
		formulario.setAuxAdmCTSaude(auxAdmCTSaude.getText());
		formulario.setAuxAdmCTPrefeitura(auxAdmCTPrefeitura.getText());
		formulario.setAuxAdmCTINAGRO(auxAdmCTINAGRO.getText());

		formulario.setFuncionamentoManha(funcionamentoManha.getText());
		formulario.setFuncionamentoTarde(funcionamentoTarde.getText());

		formulario.setAtendeDemanda(cbAtendeDemanda.getSelectionModel().getSelectedItem());

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
			// continuar daqui
			Gson gson = new Gson();

			FormRHQuantitativo formulario = gson.fromJson(resposta.getResposta(), FormRHQuantitativo.class);

			vetQEAFA.setText(formulario.getVetQEAFA());
			vetQEANS.setText(formulario.getVetQEANS());
			vetQEEMARPH.setText(formulario.getVetQEEMARPH());
			vetQESAGRIMA.setText(formulario.getVetQESAGRIMA());
			vetQESaude.setText(formulario.getVetQESaude());
			vetQEPrefeitura.setText(formulario.getVetQEPrefeitura());
			vetQEINAGRO.setText(formulario.getVetQEINAGRO());
			vetCTAFA.setText(formulario.getVetCTAFA());
			vetCTANS.setText(formulario.getVetCTANS());
			vetCTEMARPH.setText(formulario.getVetCTEMARPH());
			vetCTSAGRIMA.setText(formulario.getVetCTSAGRIMA());
			vetCTSaude.setText(formulario.getVetCTSaude());
			vetCTPrefeitura.setText(formulario.getVetCTPrefeitura());
			vetCTINAGRO.setText(formulario.getVetCTINAGRO());
			vetCPAFA.setText(formulario.getVetCPAFA());
			vetCPANS.setText(formulario.getVetCPANS());
			vetCPEMARPH.setText(formulario.getVetCPEMARPH());
			vetCPSAGRIMA.setText(formulario.getVetCPSAGRIMA());
			vetCPSaude.setText(formulario.getVetCPSaude());
			vetCPPrefeitura.setText(formulario.getVetCPPrefeitura());
			vetCPINAGRO.setText(formulario.getVetCPINAGRO());

			agroQEAFA.setText(formulario.getAgroQEAFA());
			agroQEANS.setText(formulario.getAgroQEANS());
			agroQEEMARPH.setText(formulario.getAgroQEEMARPH());
			agroQESAGRIMA.setText(formulario.getAgroQESAGRIMA());
			agroQESaude.setText(formulario.getAgroQESaude());
			agroQEPrefeitura.setText(formulario.getAgroQEPrefeitura());
			agroQEINAGRO.setText(formulario.getAgroQEINAGRO());
			agroCTAFA.setText(formulario.getAgroCTAFA());
			agroCTANS.setText(formulario.getAgroCTANS());
			agroCTEMARPH.setText(formulario.getAgroCTEMARPH());
			agroCTSAGRIMA.setText(formulario.getAgroCTSAGRIMA());
			agroCTSaude.setText(formulario.getAgroCTSaude());
			agroCTPrefeitura.setText(formulario.getAgroCTPrefeitura());
			agroCTINAGRO.setText(formulario.getAgroCTINAGRO());
			agroCPAFA.setText(formulario.getAgroCPAFA());
			agroCPANS.setText(formulario.getAgroCPANS());
			agroCPEMARPH.setText(formulario.getAgroCPEMARPH());
			agroCPSAGRIMA.setText(formulario.getAgroCPSAGRIMA());
			agroCPSaude.setText(formulario.getAgroCPSaude());
			agroCPPrefeitura.setText(formulario.getAgroCPPrefeitura());
			agroCPINAGRO.setText(formulario.getAgroCPINAGRO());

			florQEAFA.setText(formulario.getFlorQEAFA());
			florQEANS.setText(formulario.getFlorQEANS());
			florQEEMARPH.setText(formulario.getFlorQEEMARPH());
			florQESAGRIMA.setText(formulario.getFlorQESAGRIMA());
			florQESaude.setText(formulario.getFlorQESaude());
			florQEPrefeitura.setText(formulario.getFlorQEPrefeitura());
			florQEINAGRO.setText(formulario.getFlorQEINAGRO());
			florCTAFA.setText(formulario.getFlorCTAFA());
			florCTANS.setText(formulario.getFlorCTANS());
			florCTEMARPH.setText(formulario.getFlorCTEMARPH());
			florCTSAGRIMA.setText(formulario.getFlorCTSAGRIMA());
			florCTSaude.setText(formulario.getFlorCTSaude());
			florCTPrefeitura.setText(formulario.getFlorCTPrefeitura());
			florCTINAGRO.setText(formulario.getFlorCTINAGRO());
			florCPAFA.setText(formulario.getFlorCPAFA());
			florCPANS.setText(formulario.getFlorCPANS());
			florCPEMARPH.setText(formulario.getFlorCPEMARPH());
			florCPSAGRIMA.setText(formulario.getFlorCPSAGRIMA());
			florCPSaude.setText(formulario.getFlorCPSaude());
			florCPPrefeitura.setText(formulario.getFlorCPPrefeitura());
			florCPINAGRO.setText(formulario.getFlorCPINAGRO());

			zooQEAFA.setText(formulario.getZooQEAFA());
			zooQEANS.setText(formulario.getZooQEANS());
			zooQEEMARPH.setText(formulario.getZooQEEMARPH());
			zooQESAGRIMA.setText(formulario.getZooQESAGRIMA());
			zooQESaude.setText(formulario.getZooQESaude());
			zooQEPrefeitura.setText(formulario.getZooQEPrefeitura());
			zooQEINAGRO.setText(formulario.getZooQEINAGRO());
			zooCTAFA.setText(formulario.getZooCTAFA());
			zooCTANS.setText(formulario.getZooCTANS());
			zooCTEMARPH.setText(formulario.getZooCTEMARPH());
			zooCTSAGRIMA.setText(formulario.getZooCTSAGRIMA());
			zooCTSaude.setText(formulario.getZooCTSaude());
			zooCTPrefeitura.setText(formulario.getZooCTPrefeitura());
			zooCTINAGRO.setText(formulario.getZooCTINAGRO());
			zooCPAFA.setText(formulario.getZooCPAFA());
			zooCPANS.setText(formulario.getZooCPANS());
			zooCPEMARPH.setText(formulario.getZooCPEMARPH());
			zooCPSAGRIMA.setText(formulario.getZooCPSAGRIMA());
			zooCPSaude.setText(formulario.getZooCPSaude());
			zooCPPrefeitura.setText(formulario.getZooCPPrefeitura());
			zooCPINAGRO.setText(formulario.getZooCPINAGRO());

			tecAgroQEAFA.setText(formulario.getTecAgroQEAFA());
			tecAgroQEANS.setText(formulario.getTecAgroQEANS());
			tecAgroQEEMARPH.setText(formulario.getTecAgroQEEMARPH());
			tecAgroQESAGRIMA.setText(formulario.getTecAgroQESAGRIMA());
			tecAgroQESEDUC.setText(formulario.getTecAgroQESEDUC());
			tecAgroQESaude.setText(formulario.getTecAgroQESaude());
			tecAgroQEPrefeitura.setText(formulario.getTecAgroQEPrefeitura());
			tecAgroQEINAGRO.setText(formulario.getTecAgroQEINAGRO());
			tecAgroCTAFA.setText(formulario.getTecAgroCTAFA());
			tecAgroCTANS.setText(formulario.getTecAgroCTANS());
			tecAgroCTEMARPH.setText(formulario.getTecAgroCTEMARPH());
			tecAgroCTSAGRIMA.setText(formulario.getTecAgroCTSAGRIMA());
			tecAgroCTSEDUC.setText(formulario.getTecAgroCTSEDUC());
			tecAgroCTSaude.setText(formulario.getTecAgroCTSaude());
			tecAgroCTPrefeitura.setText(formulario.getTecAgroCTPrefeitura());
			tecAgroCTINAGRO.setText(formulario.getTecAgroCTINAGRO());

			auxAdmQEAFA.setText(formulario.getAuxAdmQEAFA());
			auxAdmQEANS.setText(formulario.getAuxAdmQEANS());
			auxAdmQEADO.setText(formulario.getAuxAdmQEADO());
			auxAdmQEEMARPH.setText(formulario.getAuxAdmQEEMARPH());
			auxAdmQESAGRIMA.setText(formulario.getAuxAdmQESAGRIMA());
			auxAdmQESEDUC.setText(formulario.getAuxAdmQESEDUC());
			auxAdmQESaude.setText(formulario.getAuxAdmQESaude());
			auxAdmQEPrefeitura.setText(formulario.getAuxAdmQEPrefeitura());
			auxAdmQEINAGRO.setText(formulario.getAuxAdmQEINAGRO());
			auxAdmCTAFA.setText(formulario.getAuxAdmCTAFA());
			auxAdmCTANS.setText(formulario.getAuxAdmCTANS());
			auxAdmCTADO.setText(formulario.getAuxAdmCTADO());
			auxAdmCTEMARPH.setText(formulario.getAuxAdmCTEMARPH());
			auxAdmCTSAGRIMA.setText(formulario.getAuxAdmCTSAGRIMA());
			auxAdmCTSEDUC.setText(formulario.getAuxAdmCTSEDUC());
			auxAdmCTSaude.setText(formulario.getAuxAdmCTSaude());
			auxAdmCTPrefeitura.setText(formulario.getAuxAdmCTPrefeitura());
			auxAdmCTINAGRO.setText(formulario.getAuxAdmCTINAGRO());

			funcionamentoManha.setText(formulario.getFuncionamentoManha());
			funcionamentoTarde.setText(formulario.getFuncionamentoTarde());

			cbAtendeDemanda.getSelectionModel().select(formulario.getAtendeDemanda());

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
            if (funcionamentoManha.getText() == null || funcionamentoManha.getText().length() == 0){
                error = true;
                lblHorarioFuncionamento.setStyle("-fx-text-fill:red");
                funcionamentoManha.setStyle("-jfx-unfocus-color:red");
            }
            if (funcionamentoTarde.getText() == null || funcionamentoTarde.getText().length() == 0){
                error = true;
                lblHorarioFuncionamento.setStyle("-fx-text-fill:red");
                funcionamentoTarde.setStyle("-jfx-unfocus-color:red");
            }
            if (cbAtendeDemanda.getSelectionModel().isEmpty()){
                error = true;
                lblAtendeDemanda.setStyle("-fx-text-fill:red");
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

	public class FormRHQuantitativo {

		private String vetQEAFA;
		private String vetQEANS;
		private String vetQEEMARPH;
		private String vetQESAGRIMA;
		private String vetQESaude;
		private String vetQEPrefeitura;
		private String vetQEINAGRO;
		private String vetCTAFA;
		private String vetCTANS;
		private String vetCTEMARPH;
		private String vetCTSAGRIMA;
		private String vetCTSaude;
		private String vetCTPrefeitura;
		private String vetCTINAGRO;
		private String vetCPAFA;
		private String vetCPANS;
		private String vetCPEMARPH;
		private String vetCPSAGRIMA;
		private String vetCPSaude;
		private String vetCPPrefeitura;
		private String vetCPINAGRO;

		private String agroQEAFA;
		private String agroQEANS;
		private String agroQEEMARPH;
		private String agroQESAGRIMA;
		private String agroQESaude;
		private String agroQEPrefeitura;
		private String agroQEINAGRO;
		private String agroCTAFA;
		private String agroCTANS;
		private String agroCTEMARPH;
		private String agroCTSAGRIMA;
		private String agroCTSaude;
		private String agroCTPrefeitura;
		private String agroCTINAGRO;
		private String agroCPAFA;
		private String agroCPANS;
		private String agroCPEMARPH;
		private String agroCPSAGRIMA;
		private String agroCPSaude;
		private String agroCPPrefeitura;
		private String agroCPINAGRO;

		private String florQEAFA;
		private String florQEANS;
		private String florQEEMARPH;
		private String florQESAGRIMA;
		private String florQESaude;
		private String florQEPrefeitura;
		private String florQEINAGRO;
		private String florCTAFA;
		private String florCTANS;
		private String florCTEMARPH;
		private String florCTSAGRIMA;
		private String florCTSaude;
		private String florCTPrefeitura;
		private String florCTINAGRO;
		private String florCPAFA;
		private String florCPANS;
		private String florCPEMARPH;
		private String florCPSAGRIMA;
		private String florCPSaude;
		private String florCPPrefeitura;
		private String florCPINAGRO;

		private String zooQEAFA;
		private String zooQEANS;
		private String zooQEEMARPH;
		private String zooQESAGRIMA;
		private String zooQESaude;
		private String zooQEPrefeitura;
		private String zooQEINAGRO;
		private String zooCTANS;
		private String zooCTEMARPH;
		private String zooCTSAGRIMA;
		private String zooCTSaude;
		private String zooCTPrefeitura;
		private String zooCTINAGRO;
		private String zooCPAFA;
		private String zooCPANS;
		private String zooCTAFA;
		private String zooCPEMARPH;
		private String zooCPSAGRIMA;
		private String zooCPSaude;
		private String zooCPPrefeitura;
		private String zooCPINAGRO;

		private String tecAgroQEAFA;
		private String tecAgroQEANS;
		private String tecAgroQEEMARPH;
		private String tecAgroQESAGRIMA;
		private String tecAgroQESEDUC;
		private String tecAgroQESaude;
		private String tecAgroQEPrefeitura;
		private String tecAgroQEINAGRO;
		private String tecAgroCTAFA;
		private String tecAgroCTANS;
		private String tecAgroCTEMARPH;
		private String tecAgroCTSAGRIMA;
		private String tecAgroCTSEDUC;
		private String tecAgroCTSaude;
		private String tecAgroCTPrefeitura;
		private String tecAgroCTINAGRO;

		private String auxAdmQEAFA;
		private String auxAdmQEANS;
		private String auxAdmQEADO;
		private String auxAdmQEEMARPH;
		private String auxAdmQESAGRIMA;
		private String auxAdmQESEDUC;
		private String auxAdmQESaude;
		private String auxAdmQEPrefeitura;
		private String auxAdmQEINAGRO;
		private String auxAdmCTAFA;
		private String auxAdmCTANS;
		private String auxAdmCTADO;
		private String auxAdmCTEMARPH;
		private String auxAdmCTSAGRIMA;
		private String auxAdmCTSEDUC;
		private String auxAdmCTSaude;
		private String auxAdmCTPrefeitura;
		private String auxAdmCTINAGRO;

		private String atendeDemanda;
		private String funcionamentoManha;
		private String funcionamentoTarde;

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

		public String getVetQEAFA() {
			return vetQEAFA;
		}

		public void setVetQEAFA(String vetQEAFA) {
			this.vetQEAFA = vetQEAFA;
		}

		public String getVetQEANS() {
			return vetQEANS;
		}

		public void setVetQEANS(String vetQEANS) {
			this.vetQEANS = vetQEANS;
		}

		public String getVetQEEMARPH() {
			return vetQEEMARPH;
		}

		public void setVetQEEMARPH(String vetQEEMARPH) {
			this.vetQEEMARPH = vetQEEMARPH;
		}

		public String getVetQESAGRIMA() {
			return vetQESAGRIMA;
		}

		public void setVetQESAGRIMA(String vetQESAGRIMA) {
			this.vetQESAGRIMA = vetQESAGRIMA;
		}

		public String getVetQESaude() {
			return vetQESaude;
		}

		public void setVetQESaude(String vetQESaude) {
			this.vetQESaude = vetQESaude;
		}

		public String getVetQEPrefeitura() {
			return vetQEPrefeitura;
		}

		public void setVetQEPrefeitura(String vetQEPrefeitura) {
			this.vetQEPrefeitura = vetQEPrefeitura;
		}

		public String getVetQEINAGRO() {
			return vetQEINAGRO;
		}

		public void setVetQEINAGRO(String vetQEINAGRO) {
			this.vetQEINAGRO = vetQEINAGRO;
		}

		public String getVetCTAFA() {
			return vetCTAFA;
		}

		public void setVetCTAFA(String vetCTAFA) {
			this.vetCTAFA = vetCTAFA;
		}

		public String getVetCTANS() {
			return vetCTANS;
		}

		public void setVetCTANS(String vetCTANS) {
			this.vetCTANS = vetCTANS;
		}

		public String getVetCTEMARPH() {
			return vetCTEMARPH;
		}

		public void setVetCTEMARPH(String vetCTEMARPH) {
			this.vetCTEMARPH = vetCTEMARPH;
		}

		public String getVetCTSAGRIMA() {
			return vetCTSAGRIMA;
		}

		public void setVetCTSAGRIMA(String vetCTSAGRIMA) {
			this.vetCTSAGRIMA = vetCTSAGRIMA;
		}

		public String getVetCTSaude() {
			return vetCTSaude;
		}

		public void setVetCTSaude(String vetCTSaude) {
			this.vetCTSaude = vetCTSaude;
		}

		public String getVetCTPrefeitura() {
			return vetCTPrefeitura;
		}

		public void setVetCTPrefeitura(String vetCTPrefeitura) {
			this.vetCTPrefeitura = vetCTPrefeitura;
		}

		public String getVetCTINAGRO() {
			return vetCTINAGRO;
		}

		public void setVetCTINAGRO(String vetCTINAGRO) {
			this.vetCTINAGRO = vetCTINAGRO;
		}

		public String getVetCPAFA() {
			return vetCPAFA;
		}

		public void setVetCPAFA(String vetCPAFA) {
			this.vetCPAFA = vetCPAFA;
		}

		public String getVetCPANS() {
			return vetCPANS;
		}

		public void setVetCPANS(String vetCPANS) {
			this.vetCPANS = vetCPANS;
		}

		public String getVetCPEMARPH() {
			return vetCPEMARPH;
		}

		public void setVetCPEMARPH(String vetCPEMARPH) {
			this.vetCPEMARPH = vetCPEMARPH;
		}

		public String getVetCPSAGRIMA() {
			return vetCPSAGRIMA;
		}

		public void setVetCPSAGRIMA(String vetCPSAGRIMA) {
			this.vetCPSAGRIMA = vetCPSAGRIMA;
		}

		public String getVetCPSaude() {
			return vetCPSaude;
		}

		public void setVetCPSaude(String vetCPSaude) {
			this.vetCPSaude = vetCPSaude;
		}

		public String getVetCPPrefeitura() {
			return vetCPPrefeitura;
		}

		public void setVetCPPrefeitura(String vetCPPrefeitura) {
			this.vetCPPrefeitura = vetCPPrefeitura;
		}

		public String getVetCPINAGRO() {
			return vetCPINAGRO;
		}

		public void setVetCPINAGRO(String vetCPINAGRO) {
			this.vetCPINAGRO = vetCPINAGRO;
		}

		public String getAgroQEAFA() {
			return agroQEAFA;
		}

		public void setAgroQEAFA(String agroQEAFA) {
			this.agroQEAFA = agroQEAFA;
		}

		public String getAgroQEANS() {
			return agroQEANS;
		}

		public void setAgroQEANS(String agroQEANS) {
			this.agroQEANS = agroQEANS;
		}

		public String getAgroQEEMARPH() {
			return agroQEEMARPH;
		}

		public void setAgroQEEMARPH(String agroQEEMARPH) {
			this.agroQEEMARPH = agroQEEMARPH;
		}

		public String getAgroQESAGRIMA() {
			return agroQESAGRIMA;
		}

		public void setAgroQESAGRIMA(String agroQESAGRIMA) {
			this.agroQESAGRIMA = agroQESAGRIMA;
		}

		public String getAgroQESaude() {
			return agroQESaude;
		}

		public void setAgroQESaude(String agroQESaude) {
			this.agroQESaude = agroQESaude;
		}

		public String getAgroQEPrefeitura() {
			return agroQEPrefeitura;
		}

		public void setAgroQEPrefeitura(String agroQEPrefeitura) {
			this.agroQEPrefeitura = agroQEPrefeitura;
		}

		public String getAgroQEINAGRO() {
			return agroQEINAGRO;
		}

		public void setAgroQEINAGRO(String agroQEINAGRO) {
			this.agroQEINAGRO = agroQEINAGRO;
		}

		public String getAgroCTAFA() {
			return agroCTAFA;
		}

		public void setAgroCTAFA(String agroCTAFA) {
			this.agroCTAFA = agroCTAFA;
		}

		public String getAgroCTANS() {
			return agroCTANS;
		}

		public void setAgroCTANS(String agroCTANS) {
			this.agroCTANS = agroCTANS;
		}

		public String getAgroCTEMARPH() {
			return agroCTEMARPH;
		}

		public void setAgroCTEMARPH(String agroCTEMARPH) {
			this.agroCTEMARPH = agroCTEMARPH;
		}

		public String getAgroCTSAGRIMA() {
			return agroCTSAGRIMA;
		}

		public void setAgroCTSAGRIMA(String agroCTSAGRIMA) {
			this.agroCTSAGRIMA = agroCTSAGRIMA;
		}

		public String getAgroCTSaude() {
			return agroCTSaude;
		}

		public void setAgroCTSaude(String agroCTSaude) {
			this.agroCTSaude = agroCTSaude;
		}

		public String getAgroCTPrefeitura() {
			return agroCTPrefeitura;
		}

		public void setAgroCTPrefeitura(String agroCTPrefeitura) {
			this.agroCTPrefeitura = agroCTPrefeitura;
		}

		public String getAgroCTINAGRO() {
			return agroCTINAGRO;
		}

		public void setAgroCTINAGRO(String agroCTINAGRO) {
			this.agroCTINAGRO = agroCTINAGRO;
		}

		public String getAgroCPAFA() {
			return agroCPAFA;
		}

		public void setAgroCPAFA(String agroCPAFA) {
			this.agroCPAFA = agroCPAFA;
		}

		public String getAgroCPANS() {
			return agroCPANS;
		}

		public void setAgroCPANS(String agroCPANS) {
			this.agroCPANS = agroCPANS;
		}

		public String getAgroCPEMARPH() {
			return agroCPEMARPH;
		}

		public void setAgroCPEMARPH(String agroCPEMARPH) {
			this.agroCPEMARPH = agroCPEMARPH;
		}

		public String getAgroCPSAGRIMA() {
			return agroCPSAGRIMA;
		}

		public void setAgroCPSAGRIMA(String agroCPSAGRIMA) {
			this.agroCPSAGRIMA = agroCPSAGRIMA;
		}

		public String getAgroCPSaude() {
			return agroCPSaude;
		}

		public void setAgroCPSaude(String agroCPSaude) {
			this.agroCPSaude = agroCPSaude;
		}

		public String getAgroCPPrefeitura() {
			return agroCPPrefeitura;
		}

		public void setAgroCPPrefeitura(String agroCPPrefeitura) {
			this.agroCPPrefeitura = agroCPPrefeitura;
		}

		public String getAgroCPINAGRO() {
			return agroCPINAGRO;
		}

		public void setAgroCPINAGRO(String agroCPINAGRO) {
			this.agroCPINAGRO = agroCPINAGRO;
		}

		public String getFlorQEAFA() {
			return florQEAFA;
		}

		public void setFlorQEAFA(String florQEAFA) {
			this.florQEAFA = florQEAFA;
		}

		public String getFlorQEANS() {
			return florQEANS;
		}

		public void setFlorQEANS(String florQEANS) {
			this.florQEANS = florQEANS;
		}

		public String getFlorQEEMARPH() {
			return florQEEMARPH;
		}

		public void setFlorQEEMARPH(String florQEEMARPH) {
			this.florQEEMARPH = florQEEMARPH;
		}

		public String getFlorQESAGRIMA() {
			return florQESAGRIMA;
		}

		public void setFlorQESAGRIMA(String florQESAGRIMA) {
			this.florQESAGRIMA = florQESAGRIMA;
		}

		public String getFlorQESaude() {
			return florQESaude;
		}

		public void setFlorQESaude(String florQESaude) {
			this.florQESaude = florQESaude;
		}

		public String getFlorQEPrefeitura() {
			return florQEPrefeitura;
		}

		public void setFlorQEPrefeitura(String florQEPrefeitura) {
			this.florQEPrefeitura = florQEPrefeitura;
		}

		public String getFlorQEINAGRO() {
			return florQEINAGRO;
		}

		public void setFlorQEINAGRO(String florQEINAGRO) {
			this.florQEINAGRO = florQEINAGRO;
		}

		public String getFlorCTAFA() {
			return florCTAFA;
		}

		public void setFlorCTAFA(String florCTAFA) {
			this.florCTAFA = florCTAFA;
		}

		public String getFlorCTANS() {
			return florCTANS;
		}

		public void setFlorCTANS(String florCTANS) {
			this.florCTANS = florCTANS;
		}

		public String getFlorCTEMARPH() {
			return florCTEMARPH;
		}

		public void setFlorCTEMARPH(String florCTEMARPH) {
			this.florCTEMARPH = florCTEMARPH;
		}

		public String getFlorCTSAGRIMA() {
			return florCTSAGRIMA;
		}

		public void setFlorCTSAGRIMA(String florCTSAGRIMA) {
			this.florCTSAGRIMA = florCTSAGRIMA;
		}

		public String getFlorCTSaude() {
			return florCTSaude;
		}

		public void setFlorCTSaude(String florCTSaude) {
			this.florCTSaude = florCTSaude;
		}

		public String getFlorCTPrefeitura() {
			return florCTPrefeitura;
		}

		public void setFlorCTPrefeitura(String florCTPrefeitura) {
			this.florCTPrefeitura = florCTPrefeitura;
		}

		public String getFlorCTINAGRO() {
			return florCTINAGRO;
		}

		public void setFlorCTINAGRO(String florCTINAGRO) {
			this.florCTINAGRO = florCTINAGRO;
		}

		public String getFlorCPAFA() {
			return florCPAFA;
		}

		public void setFlorCPAFA(String florCPAFA) {
			this.florCPAFA = florCPAFA;
		}

		public String getFlorCPANS() {
			return florCPANS;
		}

		public void setFlorCPANS(String florCPANS) {
			this.florCPANS = florCPANS;
		}

		public String getFlorCPEMARPH() {
			return florCPEMARPH;
		}

		public void setFlorCPEMARPH(String florCPEMARPH) {
			this.florCPEMARPH = florCPEMARPH;
		}

		public String getFlorCPSAGRIMA() {
			return florCPSAGRIMA;
		}

		public void setFlorCPSAGRIMA(String florCPSAGRIMA) {
			this.florCPSAGRIMA = florCPSAGRIMA;
		}

		public String getFlorCPSaude() {
			return florCPSaude;
		}

		public void setFlorCPSaude(String florCPSaude) {
			this.florCPSaude = florCPSaude;
		}

		public String getFlorCPPrefeitura() {
			return florCPPrefeitura;
		}

		public void setFlorCPPrefeitura(String florCPPrefeitura) {
			this.florCPPrefeitura = florCPPrefeitura;
		}

		public String getFlorCPINAGRO() {
			return florCPINAGRO;
		}

		public void setFlorCPINAGRO(String florCPINAGRO) {
			this.florCPINAGRO = florCPINAGRO;
		}

		public String getZooQEAFA() {
			return zooQEAFA;
		}

		public void setZooQEAFA(String zooQEAFA) {
			this.zooQEAFA = zooQEAFA;
		}

		public String getZooQEANS() {
			return zooQEANS;
		}

		public void setZooQEANS(String zooQEANS) {
			this.zooQEANS = zooQEANS;
		}

		public String getZooQEEMARPH() {
			return zooQEEMARPH;
		}

		public void setZooQEEMARPH(String zooQEEMARPH) {
			this.zooQEEMARPH = zooQEEMARPH;
		}

		public String getZooQESAGRIMA() {
			return zooQESAGRIMA;
		}

		public void setZooQESAGRIMA(String zooQESAGRIMA) {
			this.zooQESAGRIMA = zooQESAGRIMA;
		}

		public String getZooQESaude() {
			return zooQESaude;
		}

		public void setZooQESaude(String zooQESaude) {
			this.zooQESaude = zooQESaude;
		}

		public String getZooQEPrefeitura() {
			return zooQEPrefeitura;
		}

		public void setZooQEPrefeitura(String zooQEPrefeitura) {
			this.zooQEPrefeitura = zooQEPrefeitura;
		}

		public String getZooQEINAGRO() {
			return zooQEINAGRO;
		}

		public void setZooQEINAGRO(String zooQEINAGRO) {
			this.zooQEINAGRO = zooQEINAGRO;
		}

		public String getZooCTANS() {
			return zooCTANS;
		}

		public void setZooCTANS(String zooCTANS) {
			this.zooCTANS = zooCTANS;
		}

		public String getZooCTEMARPH() {
			return zooCTEMARPH;
		}

		public void setZooCTEMARPH(String zooCTEMARPH) {
			this.zooCTEMARPH = zooCTEMARPH;
		}

		public String getZooCTSAGRIMA() {
			return zooCTSAGRIMA;
		}

		public void setZooCTSAGRIMA(String zooCTSAGRIMA) {
			this.zooCTSAGRIMA = zooCTSAGRIMA;
		}

		public String getZooCTSaude() {
			return zooCTSaude;
		}

		public void setZooCTSaude(String zooCTSaude) {
			this.zooCTSaude = zooCTSaude;
		}

		public String getZooCTPrefeitura() {
			return zooCTPrefeitura;
		}

		public void setZooCTPrefeitura(String zooCTPrefeitura) {
			this.zooCTPrefeitura = zooCTPrefeitura;
		}

		public String getZooCTINAGRO() {
			return zooCTINAGRO;
		}

		public void setZooCTINAGRO(String zooCTINAGRO) {
			this.zooCTINAGRO = zooCTINAGRO;
		}

		public String getZooCPAFA() {
			return zooCPAFA;
		}

		public void setZooCPAFA(String zooCPAFA) {
			this.zooCPAFA = zooCPAFA;
		}

		public String getZooCPANS() {
			return zooCPANS;
		}

		public void setZooCPANS(String zooCPANS) {
			this.zooCPANS = zooCPANS;
		}

		public String getZooCTAFA() {
			return zooCTAFA;
		}

		public void setZooCTAFA(String zooCTAFA) {
			this.zooCTAFA = zooCTAFA;
		}

		public String getZooCPEMARPH() {
			return zooCPEMARPH;
		}

		public void setZooCPEMARPH(String zooCPEMARPH) {
			this.zooCPEMARPH = zooCPEMARPH;
		}

		public String getZooCPSAGRIMA() {
			return zooCPSAGRIMA;
		}

		public void setZooCPSAGRIMA(String zooCPSAGRIMA) {
			this.zooCPSAGRIMA = zooCPSAGRIMA;
		}

		public String getZooCPSaude() {
			return zooCPSaude;
		}

		public void setZooCPSaude(String zooCPSaude) {
			this.zooCPSaude = zooCPSaude;
		}

		public String getZooCPPrefeitura() {
			return zooCPPrefeitura;
		}

		public void setZooCPPrefeitura(String zooCPPrefeitura) {
			this.zooCPPrefeitura = zooCPPrefeitura;
		}

		public String getZooCPINAGRO() {
			return zooCPINAGRO;
		}

		public void setZooCPINAGRO(String zooCPINAGRO) {
			this.zooCPINAGRO = zooCPINAGRO;
		}

		public String getTecAgroQEAFA() {
			return tecAgroQEAFA;
		}

		public void setTecAgroQEAFA(String tecAgroQEAFA) {
			this.tecAgroQEAFA = tecAgroQEAFA;
		}

		public String getTecAgroQEANS() {
			return tecAgroQEANS;
		}

		public void setTecAgroQEANS(String tecAgroQEANS) {
			this.tecAgroQEANS = tecAgroQEANS;
		}

		public String getTecAgroQEEMARPH() {
			return tecAgroQEEMARPH;
		}

		public void setTecAgroQEEMARPH(String tecAgroQEEMARPH) {
			this.tecAgroQEEMARPH = tecAgroQEEMARPH;
		}

		public String getTecAgroQESAGRIMA() {
			return tecAgroQESAGRIMA;
		}

		public void setTecAgroQESAGRIMA(String tecAgroQESAGRIMA) {
			this.tecAgroQESAGRIMA = tecAgroQESAGRIMA;
		}

		public String getTecAgroQESEDUC() {
			return tecAgroQESEDUC;
		}

		public void setTecAgroQESEDUC(String tecAgroQESEDUC) {
			this.tecAgroQESEDUC = tecAgroQESEDUC;
		}

		public String getTecAgroQESaude() {
			return tecAgroQESaude;
		}

		public void setTecAgroQESaude(String tecAgroQESaude) {
			this.tecAgroQESaude = tecAgroQESaude;
		}

		public String getTecAgroQEPrefeitura() {
			return tecAgroQEPrefeitura;
		}

		public void setTecAgroQEPrefeitura(String tecAgroQEPrefeitura) {
			this.tecAgroQEPrefeitura = tecAgroQEPrefeitura;
		}

		public String getTecAgroQEINAGRO() {
			return tecAgroQEINAGRO;
		}

		public void setTecAgroQEINAGRO(String tecAgroQEINAGRO) {
			this.tecAgroQEINAGRO = tecAgroQEINAGRO;
		}

		public String getTecAgroCTAFA() {
			return tecAgroCTAFA;
		}

		public void setTecAgroCTAFA(String tecAgroCTAFA) {
			this.tecAgroCTAFA = tecAgroCTAFA;
		}

		public String getTecAgroCTANS() {
			return tecAgroCTANS;
		}

		public void setTecAgroCTANS(String tecAgroCTANS) {
			this.tecAgroCTANS = tecAgroCTANS;
		}

		public String getTecAgroCTEMARPH() {
			return tecAgroCTEMARPH;
		}

		public void setTecAgroCTEMARPH(String tecAgroCTEMARPH) {
			this.tecAgroCTEMARPH = tecAgroCTEMARPH;
		}

		public String getTecAgroCTSAGRIMA() {
			return tecAgroCTSAGRIMA;
		}

		public void setTecAgroCTSAGRIMA(String tecAgroCTSAGRIMA) {
			this.tecAgroCTSAGRIMA = tecAgroCTSAGRIMA;
		}

		public String getTecAgroCTSEDUC() {
			return tecAgroCTSEDUC;
		}

		public void setTecAgroCTSEDUC(String tecAgroCTSEDUC) {
			this.tecAgroCTSEDUC = tecAgroCTSEDUC;
		}

		public String getTecAgroCTSaude() {
			return tecAgroCTSaude;
		}

		public void setTecAgroCTSaude(String tecAgroCTSaude) {
			this.tecAgroCTSaude = tecAgroCTSaude;
		}

		public String getTecAgroCTPrefeitura() {
			return tecAgroCTPrefeitura;
		}

		public void setTecAgroCTPrefeitura(String tecAgroCTPrefeitura) {
			this.tecAgroCTPrefeitura = tecAgroCTPrefeitura;
		}

		public String getTecAgroCTINAGRO() {
			return tecAgroCTINAGRO;
		}

		public void setTecAgroCTINAGRO(String tecAgroCTINAGRO) {
			this.tecAgroCTINAGRO = tecAgroCTINAGRO;
		}

		public String getAuxAdmQEAFA() {
			return auxAdmQEAFA;
		}

		public void setAuxAdmQEAFA(String auxAdmQEAFA) {
			this.auxAdmQEAFA = auxAdmQEAFA;
		}

		public String getAuxAdmQEANS() {
			return auxAdmQEANS;
		}

		public void setAuxAdmQEANS(String auxAdmQEANS) {
			this.auxAdmQEANS = auxAdmQEANS;
		}

		public String getAuxAdmQEADO() {
			return auxAdmQEADO;
		}

		public void setAuxAdmQEADO(String auxAdmQEADO) {
			this.auxAdmQEADO = auxAdmQEADO;
		}

		public String getAuxAdmQEEMARPH() {
			return auxAdmQEEMARPH;
		}

		public void setAuxAdmQEEMARPH(String auxAdmQEEMARPH) {
			this.auxAdmQEEMARPH = auxAdmQEEMARPH;
		}

		public String getAuxAdmQESAGRIMA() {
			return auxAdmQESAGRIMA;
		}

		public void setAuxAdmQESAGRIMA(String auxAdmQESAGRIMA) {
			this.auxAdmQESAGRIMA = auxAdmQESAGRIMA;
		}

		public String getAuxAdmQESEDUC() {
			return auxAdmQESEDUC;
		}

		public void setAuxAdmQESEDUC(String auxAdmQESEDUC) {
			this.auxAdmQESEDUC = auxAdmQESEDUC;
		}

		public String getAuxAdmQESaude() {
			return auxAdmQESaude;
		}

		public void setAuxAdmQESaude(String auxAdmQESaude) {
			this.auxAdmQESaude = auxAdmQESaude;
		}

		public String getAuxAdmQEPrefeitura() {
			return auxAdmQEPrefeitura;
		}

		public void setAuxAdmQEPrefeitura(String auxAdmQEPrefeitura) {
			this.auxAdmQEPrefeitura = auxAdmQEPrefeitura;
		}

		public String getAuxAdmQEINAGRO() {
			return auxAdmQEINAGRO;
		}

		public void setAuxAdmQEINAGRO(String auxAdmQEINAGRO) {
			this.auxAdmQEINAGRO = auxAdmQEINAGRO;
		}

		public String getAuxAdmCTAFA() {
			return auxAdmCTAFA;
		}

		public void setAuxAdmCTAFA(String auxAdmCTAFA) {
			this.auxAdmCTAFA = auxAdmCTAFA;
		}

		public String getAuxAdmCTANS() {
			return auxAdmCTANS;
		}

		public void setAuxAdmCTANS(String auxAdmCTANS) {
			this.auxAdmCTANS = auxAdmCTANS;
		}

		public String getAuxAdmCTADO() {
			return auxAdmCTADO;
		}

		public void setAuxAdmCTADO(String auxAdmCTADO) {
			this.auxAdmCTADO = auxAdmCTADO;
		}

		public String getAuxAdmCTEMARPH() {
			return auxAdmCTEMARPH;
		}

		public void setAuxAdmCTEMARPH(String auxAdmCTEMARPH) {
			this.auxAdmCTEMARPH = auxAdmCTEMARPH;
		}

		public String getAuxAdmCTSAGRIMA() {
			return auxAdmCTSAGRIMA;
		}

		public void setAuxAdmCTSAGRIMA(String auxAdmCTSAGRIMA) {
			this.auxAdmCTSAGRIMA = auxAdmCTSAGRIMA;
		}

		public String getAuxAdmCTSEDUC() {
			return auxAdmCTSEDUC;
		}

		public void setAuxAdmCTSEDUC(String auxAdmCTSEDUC) {
			this.auxAdmCTSEDUC = auxAdmCTSEDUC;
		}

		public String getAuxAdmCTSaude() {
			return auxAdmCTSaude;
		}

		public void setAuxAdmCTSaude(String auxAdmCTSaude) {
			this.auxAdmCTSaude = auxAdmCTSaude;
		}

		public String getAuxAdmCTPrefeitura() {
			return auxAdmCTPrefeitura;
		}

		public void setAuxAdmCTPrefeitura(String auxAdmCTPrefeitura) {
			this.auxAdmCTPrefeitura = auxAdmCTPrefeitura;
		}

		public String getAuxAdmCTINAGRO() {
			return auxAdmCTINAGRO;
		}

		public void setAuxAdmCTINAGRO(String auxAdmCTINAGRO) {
			this.auxAdmCTINAGRO = auxAdmCTINAGRO;
		}

		public String getAtendeDemanda() {
			return atendeDemanda;
		}

		public void setAtendeDemanda(String atendeDemanda) {
			this.atendeDemanda = atendeDemanda;
		}

		public String getFuncionamentoManha() {
			return funcionamentoManha;
		}

		public void setFuncionamentoManha(String funcionamentoManha) {
			this.funcionamentoManha = funcionamentoManha;
		}

		public String getFuncionamentoTarde() {
			return funcionamentoTarde;
		}

		public void setFuncionamentoTarde(String funcionamentoTarde) {
			this.funcionamentoTarde = funcionamentoTarde;
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
