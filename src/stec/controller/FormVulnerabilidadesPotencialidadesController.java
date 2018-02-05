package stec.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.Resposta;
import stec.resources.Classes.AlertMaker;
import stec.resources.Classes.IFormulario;

public class FormVulnerabilidadesPotencialidadesController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormVulnerabilidadesPotencialidades formulario = new FormVulnerabilidadesPotencialidades();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    
    //ObservableList<String> observableOpcoes;
    
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXTextArea txtVulnerabilidades;
    @FXML
    private JFXTextArea txtPotencialidades;
    @FXML
    private Label lblVulnerabilidades;
    @FXML
    private Label lblPotencialidades;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if (validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setVulnerabilidades(txtVulnerabilidades.getText());
            formulario.setPotencialidades(txtPotencialidades.getText());

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
            
            FormVulnerabilidadesPotencialidades formulario = gson.fromJson(resposta.getResposta(), FormVulnerabilidadesPotencialidades.class);
            
            txtPotencialidades.setText(formulario.getPotencialidades());
            txtVulnerabilidades.setText(formulario.getVulnerabilidades());
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
        if (txtVulnerabilidades.getText() == null || txtVulnerabilidades.getText().length() == 0){
            error = true;
            lblVulnerabilidades.setStyle("-fx-text-fill:red");
        }
        if (txtPotencialidades.getText() == null || txtPotencialidades.getText().length() == 0){
            error = true;
            lblPotencialidades.setStyle("-fx-text-fill:red");
        }
        if (error == false){
            return true;
        }else{
            AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
            return false;
        }
    }
        
    public class FormVulnerabilidadesPotencialidades {
        
        private String vulnerabilidades;
        private String potencialidades;

        public String getVulnerabilidades() {
            return vulnerabilidades;
        }

        public void setVulnerabilidades(String vulnerabilidades) {
            this.vulnerabilidades = vulnerabilidades;
        }

        public String getPotencialidades() {
            return potencialidades;
        }

        public void setPotencialidades(String potencialidades) {
            this.potencialidades = potencialidades;
        }
        
        
    }
}
