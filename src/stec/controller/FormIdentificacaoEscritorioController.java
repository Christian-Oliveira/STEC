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
import stec.model.domain.Resposta;
import stec.resources.Classes.AlertMaker;
import stec.resources.Classes.IFormulario;

public class FormIdentificacaoEscritorioController implements Initializable, IFormulario {
    
    Resposta resposta = new Resposta();
    FormIdentificacaoEscritorio formulario = new FormIdentificacaoEscritorio();
    private boolean btConfirmarClicked = false;
    private Stage dialog;
    
    @FXML
    private JFXComboBox<String> cbUR;
    @FXML
    private JFXTextField nomeChefe;
    @FXML
    private JFXTextField emailChefe;
    @FXML
    private JFXComboBox<String> cbEscritorio;
    @FXML
    private JFXTextField endereco;
    @FXML
    private JFXTextField telefone;
    @FXML
    private JFXTextField veterinario;
    @FXML
    private JFXTextField veterinarioEmail;
    @FXML
    private Label lblChefeUr;
    @FXML
    private Label lblEmailChefe;
    @FXML
    private Label lblEndereco;
    @FXML
    private Label lblTelefone;
    @FXML
    private Label lblNomeVeterinario;
    @FXML
    private Label lblEmailVeterinario;
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
        
    }    

    @FXML
    private void handleAdicionar(ActionEvent event) {
        if(validarDados()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            formulario.setUrId(resposta.getSupervisao().getUr().getId());
            formulario.setChefeUr(nomeChefe.getText());
            formulario.setEmailChefe(emailChefe.getText());

            if (resposta.getSupervisao().getTipoEscritorio().equals("EAC"))
                formulario.setEscritorioId(resposta.getSupervisao().getEac().getId());
            else
                formulario.setEscritorioId(resposta.getSupervisao().getUlsav().getId());

            formulario.setTelefone(telefone.getText());
            formulario.setEndereco(endereco.getText());
            formulario.setVeterinario(veterinario.getText());
            formulario.setEmailVeterinario(veterinarioEmail.getText());

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

    @Override
    public Stage getDialog() {
        return dialog;
    }

    @Override
    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    @Override
    public Resposta getResposta() {
        return resposta;
    }

    @Override
    public void setResposta(Resposta resposta) {
        this.resposta = resposta;       
        
        //Apenas para visualizacao do usuario
        ObservableList<String> ur = FXCollections.observableArrayList();
        ur.add(resposta.getSupervisao().getUr().getNome());
        cbUR.setItems(ur);
        cbUR.getSelectionModel().selectFirst();
                 
        ObservableList<String> escritorio = FXCollections.observableArrayList();
        
        if (resposta.getSupervisao().getTipoEscritorio().equals("EAC"))
            escritorio.add(resposta.getSupervisao().getEac().getNome());
        else
            escritorio.add(resposta.getSupervisao().getUlsav().getNome());
                
        cbEscritorio.setItems(escritorio);
        cbEscritorio.getSelectionModel().selectFirst();
        
        //Recupera as informacoes do formulario caso exista a resposta
        if (resposta.getResposta() != null) {
            
            Gson gson = new Gson();
            
            FormIdentificacaoEscritorio formulario = gson.fromJson(resposta.getResposta(), FormIdentificacaoEscritorio.class);
                        
            nomeChefe.setText(formulario.getChefeUr());
            emailChefe.setText(formulario.getEmailChefe());
            endereco.setText(formulario.getEndereco());
            telefone.setText(formulario.getTelefone());
            veterinario.setText(formulario.getVeterinario());
            veterinarioEmail.setText(formulario.getEmailVeterinario());
            
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
    
    //Função de validação de entrada de dados
    @Override
    public boolean validarDados() {
        boolean error = false;
        if (nomeChefe.getText() == null || nomeChefe.getText().length() == 0){
            error = true;
            lblChefeUr.setStyle("-fx-text-fill:red");
        }
        if (emailChefe.getText() == null || emailChefe.getText().length() == 0){
            error = true;
            lblEmailChefe.setStyle("-fx-text-fill:red");
        }
        if (endereco.getText() == null || endereco.getText().length() == 0){
            error = true;
            lblEndereco.setStyle("-fx-text-fill:red");
        }
        if (telefone.getText() == null || telefone.getText().length() == 0){
            error = true;
            lblTelefone.setStyle("-fx-text-fill:red");
        }
        if (veterinario.getText() == null || veterinario.getText().length() == 0){
            error = true;
            lblNomeVeterinario.setStyle("-fx-text-fill:red");
        }
        if (veterinarioEmail.getText() == null || veterinarioEmail.getText().length() == 0){
            error = true;
            lblEmailVeterinario.setStyle("-fx-text-fill:red");
        }
        if (error == false){
            return true;
        }else{
            //Mostrando a menssagem de erro
            AlertMaker.showSimpleAlert("Aviso", "Para adicionar é necessário preencher todos os campos.");
            return false;
        }
    }
    
    //Classe do formulario
    public class FormIdentificacaoEscritorio {
        
        private int urId;
        private String chefeUr;
        private String emailChefe;
        private int escritorioId;
        private String endereco;
        private String telefone;
        private String veterinario;
        private String emailVeterinario;
        
        private String comentario;
        private String recomendacaoUlsavEac;
        private String prazo;
        private String recomendacaoUr;
        private String recomendacaoUC;

        public FormIdentificacaoEscritorio() {
        }

        public void setUrId(int urId) {
            this.urId = urId;
        }

        public String getChefeUr() {
            return chefeUr;
        }

        public void setChefeUr(String chefeUr) {
            this.chefeUr = chefeUr;
        }

        public String getEmailChefe() {
            return emailChefe;
        }

        public void setEmailChefe(String emailChefe) {
            this.emailChefe = emailChefe;
        }

        public int getEscritorioId() {
            return escritorioId;
        }

        public void setEscritorioId(int escritorioId) {
            this.escritorioId = escritorioId;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getVeterinario() {
            return veterinario;
        }

        public void setVeterinario(String veterinario) {
            this.veterinario = veterinario;
        }

        public String getEmailVeterinario() {
            return emailVeterinario;
        }

        public void setEmailVeterinario(String emailVeterinario) {
            this.emailVeterinario = emailVeterinario;
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
        
        @Override
        public String toString()
        {
           return "Identificação de Escritório \n" + 
                  "[UR = " + urId + ",\n" +
                  "Chefe UR = " + chefeUr + ",\n" +
                  "Email Chefe UR = " + emailChefe + ",\n" +
                  "Escritório = " + escritorioId + ",\n" +
                  "Endereço = " + endereco + ",\n" +
                  "Telefone = " + telefone + ",\n" +
                  "Veterinario = " + veterinario + ",\n" +
                  "Email Veterinario = " + emailVeterinario + ",\n" +
                   "Comentarios = " + comentario + ",\n" +
                   "Ulsav Eac = " + recomendacaoUlsavEac + ",\n" +
                   "Prazo = " + prazo + ",\n" +
                   "Ur = " + recomendacaoUr + ",\n" +
                   "Uc = " + recomendacaoUC + "]";
        }
    }
    
}
