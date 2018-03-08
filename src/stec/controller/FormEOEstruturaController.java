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

public class FormEOEstruturaController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormEOEstrutura formulario = new FormEOEstrutura();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    ObservableList<String> observableOpcoes;
    ObservableList<String> observableAvaliacao;
    
    @FXML
    private JFXTextField areaULSAV;
    @FXML
    private JFXTextField areaMunicipio;
    @FXML
    private JFXTextField nMunicipioJurisdicao;
    @FXML
    private JFXTextField nEAC;
    @FXML
    private JFXTextField nMunicipiosAtendidos;
    @FXML
    private JFXTextField nPostosFixos;
    @FXML
    private JFXTextField ulsav;
    @FXML
    private JFXTextField eac1;
    @FXML
    private JFXTextField eac2;
    @FXML
    private JFXTextField eac3;
    @FXML
    private JFXTextField eac4;
    @FXML
    private JFXTextField eac5;
    @FXML
    private JFXTextField mAtendido1;
    @FXML
    private JFXTextField mAtendido2;
    @FXML
    private JFXTextField mAtendido3;
    @FXML
    private JFXTextField mAtendido4;
    @FXML
    private JFXTextField mAtendido5;
    @FXML
    private JFXComboBox<String> cbAtendeNecessidade;
    @FXML
    private JFXComboBox<String> cbEstruturaOrganizacional;
    @FXML
    private JFXComboBox<String> cbCoordInterna;
    @FXML
    private JFXComboBox<String> cbAvaliacao;
    @FXML
    private Label lblAreaMunicipio;
    @FXML
    private Label lblAreaULSAV;
    @FXML
    private Label lblMunicipiosAtendidos;
    @FXML
    private Label lblEAC;
    @FXML
    private Label lblMunicipioJurisdicao;
    @FXML
    private Label lblPostosFixos;
    @FXML
    private Label lblCoordInterna;
    @FXML
    private Label lblEstruturaOrganizacional;
    @FXML
    private Label lblAtendeNecessidade;
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
        
        cbAtendeNecessidade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "De modo geral a capilaridade atende às necessidades do serviço?"));
        cbCoordInterna.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Coordenação interna"));
        cbEstruturaOrganizacional.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> adicionarComentario(newValue, "Estrutura organizacional"));
    }    

    public void adicionarComentario(String value, String label) {
		if (value.equals("NE") || value.equals("ED"))
			txtComentarios.setText(txtComentarios.getText() + "\n" + label + " - ");
	}
    
    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setAreaMunicipio(areaMunicipio.getText());
            formulario.setAreaULSAV(areaULSAV.getText());
            formulario.setnMunicipioJurisdicao(nMunicipioJurisdicao.getText());
            formulario.setnEAC(nEAC.getText());
            formulario.setnMunicipiosAtendidos(nMunicipiosAtendidos.getText());
            formulario.setnPostosFixos(nPostosFixos.getText());
            formulario.setUlsav(ulsav.getText());
            formulario.setEac1(eac1.getText());
            formulario.setEac2(eac2.getText());
            formulario.setEac3(eac3.getText());
            formulario.setEac4(eac4.getText());
            formulario.setEac5(eac5.getText());
            formulario.setmAtendido1(mAtendido1.getText());
            formulario.setmAtendido2(mAtendido2.getText());
            formulario.setmAtendido3(mAtendido3.getText());
            formulario.setmAtendido4(mAtendido4.getText());
            formulario.setmAtendido5(mAtendido5.getText());
            formulario.setAtendeNecessidade(cbAtendeNecessidade.getSelectionModel().getSelectedItem());
            formulario.setCoordInterna(cbCoordInterna.getSelectionModel().getSelectedItem());
            formulario.setEstruturaOrganizacional(cbEstruturaOrganizacional.getSelectionModel().getSelectedItem());

            formulario.setAvaliacao(cbAvaliacao.getSelectionModel().getSelectedItem());
            formulario.setComentario(txtComentarios.getText());
            formulario.setRecomendacaoUlsavEac(txtRULSAVEAC.getText());
            formulario.setPrazo(txtPrazo.getText());
            formulario.setRecomendacaoUr(txtRUR.getText());
            formulario.setRecomendacaoUC(txtRUC.getText());

            //passa a resposta do fromulario como json
            resposta.setResposta(gson.toJson(formulario));

            btConfirmarClicked = true;
            dialog.close();
        }
    }

    @FXML
    private void handleCancelar(ActionEvent event) {
        dialog.close();
    }
    
    public void carregarComboboxOpcoes(){
        observableOpcoes = FXCollections.observableList(supervisaoDAO.opcoesSupervisao);
        observableAvaliacao = FXCollections.observableList(supervisaoDAO.opcoesAvaliacao);
        
        cbAtendeNecessidade.setItems(observableOpcoes);
        cbCoordInterna.setItems(observableOpcoes);
        cbEstruturaOrganizacional.setItems(observableOpcoes);
        cbAvaliacao.setItems(observableAvaliacao);
    }

    @Override
    public Resposta getResposta() {
        return resposta;
    }

    @Override
    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
        
        //Recupera as informacoes do formulario caso exista a resposta
        if (resposta.getResposta() != null) {
            
            Gson gson = new Gson();
            
            FormEOEstrutura formulario = gson.fromJson(resposta.getResposta(), FormEOEstrutura.class);
            
            areaMunicipio.setText(formulario.getAreaMunicipio());
            areaULSAV.setText(formulario.getAreaULSAV());
            nMunicipioJurisdicao.setText(formulario.getnMunicipioJurisdicao());
            nEAC.setText(formulario.getnEAC());
            nMunicipiosAtendidos.setText(formulario.getnMunicipiosAtendidos());
            nPostosFixos.setText(formulario.getnPostosFixos());
            ulsav.setText(formulario.getUlsav());
            eac1.setText(formulario.getEac1());
            eac2.setText(formulario.getEac2());
            eac3.setText(formulario.getEac3());
            eac4.setText(formulario.getEac4());
            eac5.setText(formulario.getEac5());
            mAtendido1.setText(formulario.getmAtendido1());
            mAtendido2.setText(formulario.getmAtendido2());
            mAtendido3.setText(formulario.getmAtendido3());
            mAtendido4.setText(formulario.getmAtendido4());
            mAtendido5.setText(formulario.getmAtendido5());
            cbAtendeNecessidade.getSelectionModel().select(formulario.getAtendeNecessidade());
            cbEstruturaOrganizacional.getSelectionModel().select(formulario.getEstruturaOrganizacional());
            cbCoordInterna.getSelectionModel().select(formulario.getCoordInterna());
            
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
            if (areaMunicipio.getText() == null || areaMunicipio.getText().length() == 0){
                error = true;
                lblAreaMunicipio.setStyle("-fx-text-fill:red");
            }
            if (areaULSAV.getText() == null || areaULSAV.getText().length() == 0){
                error = true;
                lblAreaULSAV.setStyle("-fx-text-fill:red");
            }
            if (nMunicipiosAtendidos.getText() == null || nMunicipiosAtendidos.getText().length() == 0){
                error = true;
                lblMunicipiosAtendidos.setStyle("-fx-text-fill:red");
            }
            if (nEAC.getText() == null || nEAC.getText().length() == 0){
                error = true;
                lblEAC.setStyle("-fx-text-fill:red");
            }
            if (nMunicipioJurisdicao.getText() == null || nMunicipioJurisdicao.getText().length() == 0){
                error = true;
                lblMunicipioJurisdicao.setStyle("-fx-text-fill:red");
            }
            if (nPostosFixos.getText() == null || nPostosFixos.getText().length() == 0){
                error = true;
                lblPostosFixos.setStyle("-fx-text-fill:red");
            }
            if (cbAtendeNecessidade.getSelectionModel().isEmpty()){
                error = true;
                lblAtendeNecessidade.setStyle("-fx-text-fill:red");
            }
            if (cbEstruturaOrganizacional.getSelectionModel().isEmpty()){
                error = true;
                lblEstruturaOrganizacional.setStyle("-fx-text-fill:red");
            }
            if (cbCoordInterna.getSelectionModel().isEmpty()){
                error = true;
                lblCoordInterna.setStyle("-fx-text-fill:red");
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
        
    public class FormEOEstrutura {
        
        private String areaULSAV;
        private String areaMunicipio;
        private String nMunicipioJurisdicao;
        private String nEAC;
        private String nMunicipiosAtendidos;
        private String nPostosFixos;
        private String ulsav;
        private String eac1;
        private String eac2;
        private String eac3;
        private String eac4;
        private String eac5;
        private String mAtendido1;
        private String mAtendido2;
        private String mAtendido3;
        private String mAtendido4;
        private String mAtendido5;
        private String atendeNecessidade;
        private String estruturaOrganizacional;
        private String coordInterna;
        
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
        
        public String getAreaULSAV() {
            return areaULSAV;
        }

        public void setAreaULSAV(String areaULSAV) {
            this.areaULSAV = areaULSAV;
        }

        public String getAreaMunicipio() {
            return areaMunicipio;
        }

        public void setAreaMunicipio(String areaMunicipio) {
            this.areaMunicipio = areaMunicipio;
        }

        public String getnMunicipioJurisdicao() {
            return nMunicipioJurisdicao;
        }

        public void setnMunicipioJurisdicao(String nMunicipioJurisdicao) {
            this.nMunicipioJurisdicao = nMunicipioJurisdicao;
        }

        public String getnEAC() {
            return nEAC;
        }

        public void setnEAC(String nEAC) {
            this.nEAC = nEAC;
        }

        public String getnMunicipiosAtendidos() {
            return nMunicipiosAtendidos;
        }

        public void setnMunicipiosAtendidos(String nMunicipiosAtendidos) {
            this.nMunicipiosAtendidos = nMunicipiosAtendidos;
        }

        public String getnPostosFixos() {
            return nPostosFixos;
        }

        public void setnPostosFixos(String nPostosFixos) {
            this.nPostosFixos = nPostosFixos;
        }

        public String getUlsav() {
            return ulsav;
        }

        public void setUlsav(String ulsav) {
            this.ulsav = ulsav;
        }

        public String getEac1() {
            return eac1;
        }

        public void setEac1(String eac1) {
            this.eac1 = eac1;
        }

        public String getEac2() {
            return eac2;
        }

        public void setEac2(String eac2) {
            this.eac2 = eac2;
        }

        public String getEac3() {
            return eac3;
        }

        public void setEac3(String eac3) {
            this.eac3 = eac3;
        }

        public String getEac4() {
            return eac4;
        }

        public void setEac4(String eac4) {
            this.eac4 = eac4;
        }

        public String getEac5() {
            return eac5;
        }

        public void setEac5(String eac5) {
            this.eac5 = eac5;
        }

        public String getmAtendido1() {
            return mAtendido1;
        }

        public void setmAtendido1(String mAtendido1) {
            this.mAtendido1 = mAtendido1;
        }

        public String getmAtendido2() {
            return mAtendido2;
        }

        public void setmAtendido2(String mAtendido2) {
            this.mAtendido2 = mAtendido2;
        }

        public String getmAtendido3() {
            return mAtendido3;
        }

        public void setmAtendido3(String mAtendido3) {
            this.mAtendido3 = mAtendido3;
        }

        public String getmAtendido4() {
            return mAtendido4;
        }

        public void setmAtendido4(String mAtendido4) {
            this.mAtendido4 = mAtendido4;
        }

        public String getmAtendido5() {
            return mAtendido5;
        }

        public void setmAtendido5(String mAtendido5) {
            this.mAtendido5 = mAtendido5;
        }

        public String getAtendeNecessidade() {
            return atendeNecessidade;
        }

        public void setAtendeNecessidade(String atendeNecessidade) {
            this.atendeNecessidade = atendeNecessidade;
        }

        public String getEstruturaOrganizacional() {
            return estruturaOrganizacional;
        }

        public void setEstruturaOrganizacional(String estruturaOrganizacional) {
            this.estruturaOrganizacional = estruturaOrganizacional;
        }

        public String getCoordInterna() {
            return coordInterna;
        }

        public void setCoordInterna(String coordInterna) {
            this.coordInterna = coordInterna;
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
