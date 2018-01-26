package stec.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import stec.model.dao.EacDAO;
import stec.model.dao.SupervisaoDAO;
import stec.model.dao.UlsavDAO;
import stec.model.dao.UrDAO;
import stec.model.domain.Eac;
import stec.model.domain.Supervisao;
import stec.model.domain.Ulsav;
import stec.model.domain.Ur;
import stec.resources.Classes.AlertMaker;

public class FormSupervisaoController implements Initializable {
    
    private Supervisao supervisao = new Supervisao();
    private final SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    private Stage dialog;
    private boolean btConfirmarClicked = false;
    
    private final UrDAO urDAO = new UrDAO();
    private List<Ur> listUrs = new ArrayList<>();   
    ObservableList<Ur> observableListUrs;
    
    private final EacDAO eacDAO = new EacDAO();
    private final UlsavDAO ulsavDAO = new UlsavDAO();
    
    ObservableList<String> observableListEscritorios;
    ObservableList<Object> observableListMunicipios;
    
    @FXML
    private JFXComboBox<Ur> cbUR;
    @FXML
    private JFXComboBox<String> cbEscritorio;
    @FXML
    private JFXComboBox<Object> cbMunicipio;
    @FXML
    private JFXCheckBox chPNEFA;
    @FXML
    private JFXCheckBox chPNCEBT;
    @FXML
    private JFXCheckBox chPNCRH;
    @FXML
    private JFXCheckBox chPNEEB;
    @FXML
    private JFXCheckBox chPNSE;
    @FXML
    private JFXCheckBox chPNSS;
    @FXML
    private JFXCheckBox chPNSA;
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXCheckBox chTODOS;
    @FXML
    private JFXCheckBox chPNSCO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        carregarComboboxUr();
        carregarComboboxEscritorioAuditado();
        
        cbEscritorio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> carregarComboBoxMunicipios(newValue));
        chTODOS.selectedProperty().addListener((observable, oldValue, newValue) -> checkAll(newValue));
    }    

    @FXML
    private void handleAdicionar(ActionEvent event) {
        List<String> programas = new ArrayList<>();
        
        if (validarInput()) {
            supervisao.setUsuario(LoginController.USUARIO);
            
            supervisao.setUr(cbUR.getSelectionModel().getSelectedItem());
            supervisao.setTipoEscritorio(cbEscritorio.getSelectionModel().getSelectedItem());
            
            if (cbEscritorio.getSelectionModel().getSelectedItem().equals("EAC"))
                supervisao.setEac((Eac) cbMunicipio.getSelectionModel().getSelectedItem());
            else 
                supervisao.setUlsav((Ulsav) cbMunicipio.getSelectionModel().getSelectedItem());
            
            if (chPNCEBT.isSelected())
                programas.add("PNCEBT");
            
            if (chPNSS.isSelected())
                programas.add("PNSS");
            
            if (chPNCRH.isSelected())
                programas.add("PNCRH");
            
            if (chPNEEB.isSelected())
                programas.add("PNEEB");
            
            if (chPNEFA.isSelected())
                programas.add("PNEFA");
            
            if (chPNSA.isSelected())
                programas.add("PNSA");
            
            if (chPNSE.isSelected())
                programas.add("PNSE");
            
            if (chPNSCO.isSelected())
                programas.add("PNSCO");
            
            supervisao.setProgramas(programas);
            
            btConfirmarClicked = true;
            dialog.close();
        }
    }

    @FXML
    private void handleCancelar(ActionEvent event) {
        dialog.close();
    }   
    
    private boolean validarInput() {
        String message = "";
        
        if (cbUR.getSelectionModel().getSelectedItem() == null)
            message += "Regional inválida\n";
        
        if (cbEscritorio.getSelectionModel().getSelectedItem() == null)
            message += "Escritório inválido\n";
        
        if (cbMunicipio.getSelectionModel().getSelectedItem() == null)
            message += "Município inválido\n";
        
        if (chPNCEBT.isSelected() == false && chPNCRH.isSelected() == false &&
                chPNEEB.isSelected() == false && chPNEFA.isSelected() == false &&
                chPNSA.isSelected() == false && chPNSE.isSelected() == false &&
                chPNSCO.isSelected() == false && chPNSS.isSelected() == false) {
            message += "Selecione pelo menos um programa\n";
        }
                
        if (message.length() == 0) {
            return true;
        } else {
            AlertMaker.showErrorMessage("Campos inválidos, por favor, corrija...", message);
            return false;
        }
    }
    
    public void checkAll(Boolean status) {
        if (status) {
            chPNEFA.setSelected(true);
            chPNCEBT.setSelected(true);
            chPNCRH.setSelected(true);
            chPNEEB.setSelected(true);
            chPNSA.setSelected(true);
            chPNSE.setSelected(true);
            chPNSCO.setSelected(true);
            chPNSS.setSelected(true);
        } else {
            chPNEFA.setSelected(false);
            chPNCEBT.setSelected(false);
            chPNCRH.setSelected(false);
            chPNEEB.setSelected(false);
            chPNSA.setSelected(false);
            chPNSE.setSelected(false);
            chPNSCO.setSelected(false);
            chPNSS.setSelected(false);
        }
    }
    
    public void carregarComboboxUr() {
        listUrs = urDAO.listar();
        observableListUrs = FXCollections.observableArrayList(listUrs);
        cbUR.setItems(observableListUrs);
    }
    
    public void carregarComboboxEscritorioAuditado() {
        observableListEscritorios = FXCollections.observableArrayList(supervisaoDAO.listEscritorios);
        cbEscritorio.setItems(observableListEscritorios);
    }
    
    public boolean carregarComboBoxMunicipios(String escritorio) {
        String message = "";
        
        if (cbUR.getSelectionModel().getSelectedItem() == null)
            message += "Regional inválida\n";
        
        if (cbEscritorio.getSelectionModel().getSelectedItem() == null)
            message += "Escritório inválido\n";
        
        if (message.length() > 0) {
            AlertMaker.showErrorMessage("Campos inválidos, por favor, corrija...", message);
            return false;            
        }
        
        //Se a opcao escolhida do escritorio for EAC
        if (escritorio.equals("EAC")) {

            List<Eac> listEac = new ArrayList<>();//Cria um list para receber as EACS
            
            listEac = eacDAO.listarPor("UR", cbUR.getSelectionModel().getSelectedItem().getId());//Busca todas as EAC que pertencem aquela UR
            
            observableListMunicipios = FXCollections.observableArrayList(listEac);//Transforma a list em uma observable list
            
            cbMunicipio.setItems(observableListMunicipios);//Preenche o combobox com a observable list criada
        } else if (escritorio.equals("ULSAV")) {
            List<Ulsav> listUlsav = new ArrayList<>();
            listUlsav = ulsavDAO.listarPorUr(cbUR.getSelectionModel().getSelectedItem().getId());
            observableListMunicipios = FXCollections.observableArrayList(listUlsav);
            cbMunicipio.setItems(observableListMunicipios);
        } else {
            return false;
        }
        
        return true;
    }

    public Supervisao getSupervisao() {
        return supervisao;
    }

    public void setSupervisao(Supervisao supervisao) {
        this.supervisao = supervisao;
    }

    public Stage getDialog() {
        return dialog;
    }

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    public void setBtConfirmarClicked(boolean btConfirmarClicked) {
        this.btConfirmarClicked = btConfirmarClicked;
    }
    
}
