package stec.controller;

import com.jfoenix.controls.JFXButton;
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
import stec.model.dao.EacDAO;
import stec.model.dao.RespostaDAO;
import stec.model.dao.SupervisaoDAO;
import stec.model.dao.UlsavDAO;
import stec.model.dao.UrDAO;
import stec.model.domain.Eac;
import stec.model.domain.RelatorioCompiladoSupervisao;
import stec.model.domain.Supervisao;
import stec.model.domain.Ulsav;
import stec.model.domain.Ur;
import stec.resources.Classes.AlertMaker;

public class FormAuditoriaCompiladaController implements Initializable {
    
    private final SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    private final RespostaDAO respostaDAO = new RespostaDAO();
    
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
    private JFXButton btGerarRelatorio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboboxUr();
        carregarComboboxEscritorioAuditado();

        cbEscritorio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> carregarComboBoxMunicipios(newValue));
    }    
    
    @FXML
    private void handleGerarRelatorio(ActionEvent event) {
        
        RelatorioCompiladoSupervisao relatorio = new RelatorioCompiladoSupervisao();
        
        //Estado
        if (cbUR.getSelectionModel().getSelectedItem() == null 
                && cbEscritorio.getSelectionModel().getSelectedItem() == null
                && cbMunicipio.getSelectionModel().getSelectedItem() == null) {
            //AlertMaker.showSimpleAlert("Relatório compilado", "Será gerado o relatório compilado do ESTADO, conténdo todas as Superviões");
            
            //para cada supervisao que foi importada
            for (Supervisao supervisao : supervisaoDAO.listarImportadas()) {

                //armazena as respostas da supervisao
                supervisao.setHashRespostas(respostaDAO.listarRespostasDaSupervisaoImportada(supervisao));
                
                //adiciona a supervisao e suas respostas no list de supervisoes
                relatorio.getListSupervisao().add(supervisao);
            }
            
            //funcao para criar o relatorio compilado. passando o nome do relatorio
            relatorio.showRelatorioEstado("Relatório compilado das supervisões do ESTADO");       
        } else if (cbUR.getSelectionModel().getSelectedItem() != null //Unidade Regional
                && cbEscritorio.getSelectionModel().getSelectedItem() == null
                && cbMunicipio.getSelectionModel().getSelectedItem() == null) {
            //AlertMaker.showSimpleAlert("Relatório compilado", "Será gerado o relatório compilado da UR, conténdo todas as ULSAVs e EACs da mesma");
            
            relatorio.showRelatorioUr("Relatório compilado das supervisões da UR " + cbUR.getSelectionModel().getSelectedItem());
        } else if (cbUR.getSelectionModel().getSelectedItem() != null //Município
                && cbEscritorio.getSelectionModel().getSelectedItem() != null
                && cbMunicipio.getSelectionModel().getSelectedItem() != null) {
            //AlertMaker.showSimpleAlert("Relatório compilado", "Será gerado o relatório compilado da ULSAV / EAC selecionada");
            
            Supervisao supervisao = new Supervisao();
            
            //seta o tipo do escritorio para a pesquisa do municipio
            supervisao.setTipoEscritorio(cbEscritorio.getSelectionModel().getSelectedItem());
            
            //faz a busca do municipio, armazenando no respectivo atributo
            if (cbEscritorio.getSelectionModel().getSelectedItem().equals("EAC"))
                supervisao.setEac((Eac) cbMunicipio.getSelectionModel().getSelectedItem());
            else
                supervisao.setUlsav((Ulsav) cbMunicipio.getSelectionModel().getSelectedItem());
                        
            //faz a busca das outras informacoes da supervisao do municipio
            supervisao = supervisaoDAO.buscarPorMunicipio(supervisao);
            
            //armazena as respostas da supervisao do municipio escolhido
            supervisao.setHashRespostas(respostaDAO.listarRespostasDaSupervisaoImportada(supervisao));
            
            //adiciona a supervisao e suas respostas no list de supervisoes
            relatorio.getListSupervisao().add(supervisao);
                        
            //funcao para criar o relatorio compilado. passando o nome do relatorio
            relatorio.showRelatorioMunicipio("Relatório compilado das supervisões da " + cbEscritorio.getSelectionModel().getSelectedItem() + " " + cbMunicipio.getSelectionModel().getSelectedItem());
        } else {
            AlertMaker.showErrorMessage("Campos inválidos, por favor, corrija...", "Município inválido");
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
        } else if (escritorio.equals("ULSAV")) {
            List<Ulsav> listUlsav = new ArrayList<>();
            listUlsav = ulsavDAO.listarPorUr(cbUR.getSelectionModel().getSelectedItem().getId());
            observableListMunicipios = FXCollections.observableArrayList(listUlsav);
        }
        
        cbMunicipio.setItems(observableListMunicipios);//Preenche o combobox com a observable list criada
        
        return true;
    }

}
