package stec.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stec.model.dao.RespostaDAO;
import stec.model.domain.Formulario;
import stec.model.domain.Resposta;
import stec.model.domain.Supervisao;
import stec.resources.Classes.IFormulario;

public class SupervisaoController implements Initializable {

    private Supervisao supervisao = new Supervisao();
    private Stage dialog;
    private final RespostaDAO respostaDAO = new RespostaDAO();

    @FXML
    private AnchorPane anchorPaneSpv;
    @FXML
    private Label lbUr;
    @FXML
    private Label lbEscritorio;
    @FXML
    private Label lbMunicipio;
    @FXML
    private Label lbData;
    @FXML
    private Label lbProgramas;
    @FXML
    private Label lbAuditor;
    @FXML
    private JFXButton btFormIdentificacaoEscritorio;
    @FXML
    private Tab tabPNEFA;
    @FXML
    private Tab tabPNCEBT;
    @FXML
    private Tab tabPNCRH;
    @FXML
    private Tab tabPNEEB;
    @FXML
    private Tab tabPNSE;
    @FXML
    private Tab tabPNSCO;
    @FXML
    private Tab tabPNSS;
    @FXML
    private Tab tabPNSA;
    @FXML
    private JFXButton btFormPNEFAFiscalizacoes;
    @FXML
    private JFXButton btFormPNEFAVigilancia;
    @FXML
    private JFXButton btFormPNCEBTFiscalizacoes;
    @FXML
    private JFXButton btFormPNCEBTVigilancia;
    @FXML
    private JFXButton btFormPNCEBTControles;
    @FXML
    private JFXButton btFormPNCRHFiscalizacoes;
    @FXML
    private JFXButton btFormPNCRHVigilancia;
    @FXML
    private JFXButton btFormPNEEBVigilancia;
    @FXML
    private JFXButton btFormPNSECadastro;
    @FXML
    private JFXButton btFormPNSEVigilancia;
    @FXML
    private JFXButton btFormPNSEControles;
    @FXML
    private JFXButton btFormPNSCOCadastro;
    @FXML
    private JFXButton btFormPNSCOVigilancia;
    @FXML
    private JFXButton btFormPNSSCadastro;
    @FXML
    private JFXButton btFormPNSACadastro;
    @FXML
    private JFXButton btFormPNSAVigilancia;
    @FXML
    private JFXButton btFormPNSSVigilancia;
    @FXML
    private JFXButton btRHQuantitativo;
    @FXML
    private JFXButton btRHEstabilidade;
    @FXML
    private JFXButton btRHCapacitacao;
    @FXML
    private JFXButton btRHCompetencias;
    @FXML
    private JFXButton btRFInstalacoes;
    @FXML
    private JFXButton btRFTransportes;
    @FXML
    private JFXButton btRFEquipamentos;
    @FXML
    private JFXButton btRFSistemasInformacao;
    @FXML
    private JFXButton btRFICusteio;
    @FXML
    private JFXButton btRFIArrecadacao;
    @FXML
    private JFXButton btEOEstrutura;
    @FXML
    private JFXButton btAGBaseLegal;
    @FXML
    private JFXButton btAGOrganizacao;
    @FXML
    private JFXButton btAGSupervisao;
    @FXML
    private JFXButton btCTOControleCadastro;
    @FXML
    private JFXButton btCTOPlanejamento;
    @FXML
    private JFXButton btCTOControle;
    @FXML
    private JFXButton btCTOControleTransitoAnimais;
    @FXML
    private JFXButton btCTOControleEventosAglomeracao;
    @FXML
    private JFXButton btCTOFiscalizacao;
    @FXML
    private JFXButton btICEducacaoSanitaria;
    @FXML
    private JFXButton btICParticipacaoComunidade;
    @FXML
    private JFXButton btICParticipacaoInstituicoes;
    @FXML
    private JFXButton btIMVHabilitacao;
    @FXML
    private JFXButton btIISistemaInspecao;
    @FXML
    private JFXButton btIISUS;
    @FXML
    private JFXButton btAMAcesso;
    @FXML
    private JFXButton btVulnerabilidadesPotencialidades;
    @FXML
    private JFXButton btCTODeteccaoPrecoce;
    @FXML
    private JFXButton btCTOAtendimentoSuspeita;
    @FXML
    private JFXButton btnVoltar;
    @FXML
    private JFXButton btnSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Supervisao getSupervisao() {
        return supervisao;
    }

    public void setSupervisao(Supervisao supervisao) {
        this.supervisao = supervisao;

        lbUr.setText(supervisao.getUr().getNome());
        lbEscritorio.setText(supervisao.getTipoEscritorio());
        if (supervisao.getTipoEscritorio().equals("EAC")) {
            lbMunicipio.setText(supervisao.getEac().getNome());
        } else {
            lbMunicipio.setText(supervisao.getUlsav().getNome());
        }
        lbData.setText(supervisao.getCreated());
        lbProgramas.setText(supervisao.getProgramas().toString());
        lbAuditor.setText(supervisao.getUsuario().getNome());

        //verifica se tem o programa, caso tenha habilita para preenchimento
        if (supervisao.getProgramas().toString().contains("PNEFA"))
            tabPNEFA.setDisable(false);

        if (supervisao.getProgramas().toString().contains("PNCEBT")) {
            tabPNCEBT.setDisable(false);
        }

        if (supervisao.getProgramas().toString().contains("PNCRH"))
            tabPNCRH.setDisable(false);

        if (supervisao.getProgramas().toString().contains("PNEEB"))
            tabPNEEB.setDisable(false);

        if (supervisao.getProgramas().toString().contains("PNSA"))
            tabPNSA.setDisable(false);

        if (supervisao.getProgramas().toString().contains("PNSCO"))
            tabPNSCO.setDisable(false);

        if (supervisao.getProgramas().toString().contains("PNSE"))
            tabPNSE.setDisable(false);

        if (supervisao.getProgramas().toString().contains("PNSS"))
            tabPNSS.setDisable(false);
    }

    public Stage getDialog() {
        return dialog;
    }

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    public boolean showForm(Resposta resposta, Class formulario, String url) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(formulario.getResource(url));
        AnchorPane pane = (AnchorPane) loader.load();
        Stage stage = new Stage();
        stage.setTitle(resposta.getFormulario().getNome());
        Scene scene = new Scene(pane);
        stage.setMaximized(true);
        stage.setScene(scene);
        
        IFormulario controller = loader.getController();
        controller.setDialog(stage);
        controller.setResposta(resposta);

        stage.showAndWait();
        
        return controller.isBtConfirmarClicked();
        
    }

    //-----------------Identificacao de Escritorio------------------------------
    @FXML
    private void handleIdentificacaoEscritorio(ActionEvent event) throws IOException {

        Formulario formulario = new Formulario("identificacao_escritorio", "Identificação do Escritório");//instanciar o objeto com os dados do formulario

        Resposta resposta = new Resposta();//instancia o objeto resposta
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta
        
        //Verificar se ja existe uma resposta nesta supervisao pra esse formulario
        if (respostaDAO.temResposta(resposta)) {
            //traz as informacoes para preencher no formulario
            resposta = respostaDAO.buscar(resposta);
            
            //se clicou no botao adicionar, inseri a respostas
            if (showForm(resposta, FormIdentificacaoEscritorioController.class, "/stec/view/formularios/FormIdentificacaoEscritorio.fxml"))
                respostaDAO.alterar(resposta);
        } else {

            if (showForm(resposta, FormIdentificacaoEscritorioController.class, "/stec/view/formularios/FormIdentificacaoEscritorio.fxml"))
                respostaDAO.inserir(resposta);
        }
    }
   
    //------------------------Recursos Humanos----------------------------------
    @FXML
    private void handleRHQuantitativo(ActionEvent event) throws IOException {

        Formulario formulario = new Formulario("rh_quantitativo", "Recursos Humanos - Quantitativo, jornada e distribuição");//instanciar o objeto com os dados do formulario

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRHQuantitativoController.class, "/stec/view/formularios/FormRHQuantitativo.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormIdentificacaoEscritorioController.class, "/stec/view/formularios/FormRHQuantitativo.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleRHEstabilidade(ActionEvent event) throws IOException {

        Formulario formulario = new Formulario("rh_estabilidade", "Recursos Humanos - Estabilidade das estruturas e sustentabilidade das políticas sanitárias");//instanciar o objeto com os dados do formulario

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRHEstabilidadeController.class, "/stec/view/formularios/FormRHEstabilidade.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRHEstabilidadeController.class, "/stec/view/formularios/FormRHEstabilidade.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleRHCapacitacao(ActionEvent event) throws IOException {

        Formulario formulario = new Formulario("rh_capacitacao", "Recursos Humanos - Capacitação técnica e formação continuada");//instanciar o objeto com os dados do formulario

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRHCapacitacaoController.class, "/stec/view/formularios/FormRHCapacitacao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRHCapacitacaoController.class, "/stec/view/formularios/FormRHCapacitacao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleRHCompetencias(ActionEvent event) throws IOException {

        Formulario formulario = new Formulario("rh_competencias", "Recursos Humanos - Competências e independência técnica");//instanciar o objeto com os dados do formulario

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRHCompetenciasController.class, "/stec/view/formularios/FormRHCompetencias.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRHCompetenciasController.class, "/stec/view/formularios/FormRHCompetencias.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Recursos Físicos----------------------------------
    @FXML
    private void handleRFInstalacoes(ActionEvent event) throws IOException {

        Formulario formulario = new Formulario("rf_instalacoes", "Recursos Físicos - INSTALAÇÕES");//instanciar o objeto com os dados do formulario

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRFInstalacoesController.class, "/stec/view/formularios/FormRFInstalacoes.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRFInstalacoesController.class, "/stec/view/formularios/FormRFInstalacoes.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleRFTransportes(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("rf_transportes", "Recursos Físicos - TRANSPORTES");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRFTransportesController.class, "/stec/view/formularios/FormRFTransportes.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRFTransportesController.class, "/stec/view/formularios/FormRFTransportes.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleRFEquipamentos(ActionEvent event) throws IOException {

        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("rf_equipamentos", "Recursos Físicos - Equipamentos e acesso à comunicação");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRFEquipamentosController.class, "/stec/view/formularios/FormRFEquipamentos.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRFEquipamentosController.class, "/stec/view/formularios/FormRFEquipamentos.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleRFSistemasInformacao(ActionEvent event) throws IOException {

        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("rf_sistemas", "Recursos Físicos - Sistemas de informação");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRFSistemasInformacaoController.class, "/stec/view/formularios/FormRFSistemasInformacao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRFSistemasInformacaoController.class, "/stec/view/formularios/FormRFSistemasInformacao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Recursos Financeiros------------------------------
    @FXML
    private void handleRFICusteio(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("rfi_custeio", "Recursos Financeiros - RECURSOS PARA CUSTEIO");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRFICusteioController.class, "/stec/view/formularios/FormRFICusteio.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRFICusteioController.class, "/stec/view/formularios/FormRFICusteio.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleRFIArrecadacao(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("rfi_arrecadacao", "Recursos Financeiros - ARRECADAÇÃO");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormRFIArrecadacaoController.class, "/stec/view/formularios/FormRFIArrecadacao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormRFIArrecadacaoController.class, "/stec/view/formularios/FormRFIArrecadacao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Estrutura organizacional--------------------------
    @FXML
    private void handleEOEstrutura(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("eo_estrutura", "Estrutura Organizacional - Estrutura organizacional e capacidade de coordenação interna");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormEOEstruturaController.class, "/stec/view/formularios/FormEOEstrutura.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormEOEstruturaController.class, "/stec/view/formularios/FormEOEstrutura.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Autoridade e Gestão da Qualidade-----------------
    @FXML
    private void handleAGBaseLegal(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("ag_base_legal", "Autoridade e Gestão da Qualidade - Base legal e aplicação da legislação, manuais e POPs");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormAGBaseLegalController.class, "/stec/view/formularios/FormAGBaseLegal.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormAGBaseLegalController.class, "/stec/view/formularios/FormAGBaseLegal.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleAGOrganizacao(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("ag_organizacao", "Autoridade e Gestão de Qualidade - Organização dos processos e unidades");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormAGOrganizacaoController.class, "/stec/view/formularios/FormAGOrganizacao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormAGOrganizacaoController.class, "/stec/view/formularios/FormAGOrganizacao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleAGSupervisao(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("ag_supervisao", "Autoridade e Gestão de Qualidade - Supervisão e controle interno");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormAGSupervisaoController.class, "/stec/view/formularios/FormAGSupervisao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormAGSupervisaoController.class, "/stec/view/formularios/FormAGSupervisao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Capacidade técnica operacional--------------------
    @FXML
    private void handleCTOControleCadastro(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("cto_controle_cadastro", "Capacidade Técnica e Operacional - Controle de cadastro de produtores, propriedades e animais");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormCTOControleCadastroController.class, "/stec/view/formularios/FormCTOControleCadastro.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormCTOControleCadastroController.class, "/stec/view/formularios/FormCTOControleCadastro.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleCTOPlanejamento(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("cto_planejamento", "Capacidade Técnica e Operacional - Planejamento, execução de atividades e registro");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormCTOPlanejamentoController.class, "/stec/view/formularios/FormCTOPlanejamento.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormCTOPlanejamentoController.class, "/stec/view/formularios/FormCTOPlanejamento.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleCTOControle(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("cto_controle", "Capacidade Técnica e Operacional - Controle de divisas e trânsito interno");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormCTOControleController.class, "/stec/view/formularios/FormCTOControle.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormCTOControleController.class, "/stec/view/formularios/FormCTOControle.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleCTOControleTransitoAnimais(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("cto_transito_animais", "Capacidade Técnica e Operacional - Controle de trânsito de animais e produtos de origem animal");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormCTOControleTransitoAnimaisController.class, "/stec/view/formularios/FormCTOControleTransitoAnimais.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormCTOControleTransitoAnimaisController.class, "/stec/view/formularios/FormCTOControleTransitoAnimais.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleCTOControleEventosAglomeracao(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("cto_controle_eventos", "Capacidade Técnica e Operacional - Controle de eventos de aglomeração de animais");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormCTOControleEventosAglomeracaoController.class, "/stec/view/formularios/FormCTOControleEventosAglomeracao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormCTOControleEventosAglomeracaoController.class, "/stec/view/formularios/FormCTOControleEventosAglomeracao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleCTOFiscalizacao(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("cto_fiscalizacao", "Capacidade Técnica e Operacional - Fiscalização em revendas veterinárias");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormCTOFiscalizacaoController.class, "/stec/view/formularios/FormCTOFiscalizacao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormCTOFiscalizacaoController.class, "/stec/view/formularios/FormCTOFiscalizacao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleCTODeteccaoPrecoce(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("cto_deteccao_precoce", "Capacidade Técnica e Operacional - Capacidade para detecção precoce e notificação imediata de doenças");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormCTOCapacidadeDeteccaoPrecoceController.class, "/stec/view/formularios/FormCTOCapacidadeDeteccaoPrecoce.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormCTOCapacidadeDeteccaoPrecoceController.class, "/stec/view/formularios/FormCTOCapacidadeDeteccaoPrecoce.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleCTOAtendimentoSuspeita(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("cto_atendimento_suspeita", "Capacidade Técnica e Operacional - Capacidade para atendimento a suspeitas e atuação em emergências");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormCTOAtendimentoSuspeitaController.class, "/stec/view/formularios/FormCTOAtendimentoSuspeita.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormCTOAtendimentoSuspeitaController.class, "/stec/view/formularios/FormCTOAtendimentoSuspeita.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------PNEFA---------------------------------------------
    @FXML
    private void handlePNEFAFiscalizacoes(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnefa_fiscalizacoes", "PNEFA - FISCALIZAÇÕES DE VACINAÇÕES");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNEFAFiscalizacoesController.class, "/stec/view/formularios/FormPNEFAFiscalizacoes.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNEFAFiscalizacoesController.class, "/stec/view/formularios/FormPNEFAFiscalizacoes.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNEFAVigilancia(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnefa_vigilancia", "PNEFA - VIGILÂNCIA EPIDEMIOLÓGICA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNEFAVigilanciaController.class, "/stec/view/formularios/FormPNEFAVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNEFAVigilanciaController.class, "/stec/view/formularios/FormPNEFAVigilancia.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------PNCEBT--------------------------------------------
    @FXML
    private void handlePNCEBTFiscalizacoes(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pncebt_fiscalizacoes", "PNCEBT - FISCALIZAÇÕES DE VACINAÇÕES");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNCEBTFiscalizacoesController.class, "/stec/view/formularios/FormPNCEBTFiscalizacoes.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNCEBTFiscalizacoesController.class, "/stec/view/formularios/FormPNCEBTFiscalizacoes.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNCEBTVigilancia(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pncebt_vigilancia", "PNCEBT - VIGILÂNCIA EPIDEMIOLÓGICA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNCEBTVigilianciaController.class, "/stec/view/formularios/FormPNCEBTVigiliancia.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNCEBTVigilianciaController.class, "/stec/view/formularios/FormPNCEBTVigiliancia.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNCEBTControles(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pncebt_controles", "PNCEBT - CONTROLES DO PROGRAMA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNCEBTControlesController.class, "/stec/view/formularios/FormPNCEBTControles.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNCEBTControlesController.class, "/stec/view/formularios/FormPNCEBTControles.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------PNCRH---------------------------------------------
    @FXML
    private void handlePNCRHFiscalizacoes(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pncrh_fiscalizacoes", "PNCRH - FISCALIZAÇÕES DE VACINAÇÕES");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNCRHFiscalizacoesController.class, "/stec/view/formularios/FormPNCRHFiscalizacoes.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNCRHFiscalizacoesController.class, "/stec/view/formularios/FormPNCRHFiscalizacoes.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNCRHVigilancia(ActionEvent event) throws IOException {

        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pncrh_vigilancia", "PNCRH - VIGILÂNCIA EPIDEMIOLÓGICA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNCRHVigilanciaController.class, "/stec/view/formularios/FormPNCRHVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNCRHVigilanciaController.class, "/stec/view/formularios/FormPNCRHVigilancia.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------PNEEB---------------------------------------------
    @FXML
    private void handlePNEEBVigilancia(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pneeb_vigilancia", "PNEEB - VIGILÂNCIA EPIDEMIOLÓGICA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNEEBVigilanciaController.class, "/stec/view/formularios/FormPNEEBVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNEEBVigilanciaController.class, "/stec/view/formularios/FormPNEEBVigilancia.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------PNSE----------------------------------------------
    @FXML
    private void handlePNSECadastro(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnse_cadastro_estabelecimentos", "PNSE - CADASTRO DE ESTABELECIMENTOS");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSECadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSECadastroEstabelecimentos.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSECadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSECadastroEstabelecimentos.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNSEVigilancia(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnse_vigilancia", "PNSE - VIGILÂNCIA EPIDEMIOLÓGICA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSEVigilanciaController.class, "/stec/view/formularios/FormPNSEVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSEVigilanciaController.class, "/stec/view/formularios/FormPNSEVigilancia.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNSEControles(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnse_controles", "PNSE - CONTROLES DO PROGRAMA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSEControlesController.class, "/stec/view/formularios/FormPNSEControles.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSEControlesController.class, "/stec/view/formularios/FormPNSEControles.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------PNSCO---------------------------------------------
    @FXML
    private void handlePNSCOCadastro(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnsco_cadastro_estabelecimentos", "PNSCO - CADASTRO DE ESTABELECIMENTOS");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSCOCadastroController.class, "/stec/view/formularios/FormPNSCOCadastro.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSCOCadastroController.class, "/stec/view/formularios/FormPNSCOCadastro.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNSCOVigilancia(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnsco_vigilancia", "PNSCO - VIGILÂNCIA EPIDEMIOLÓGICA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSCOVigilanciaController.class, "/stec/view/formularios/FormPNSCOVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSCOVigilanciaController.class, "/stec/view/formularios/FormPNSCOVigilancia.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------PNSS----------------------------------------------
    @FXML
    private void handlePNSSCadastro(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnss_cadastro_estabelecimentos", "PNSS - CADASTRO DE ESTABELECIMENTOS");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSSCadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSSCadastroEstabelecimentos.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSSCadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSSCadastroEstabelecimentos.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNSSVigilancia(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnss_vigilancia", "PNSS - VIGILÂNCIA EPIDEMIOLÓGICA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSSVigilanciaController.class, "/stec/view/formularios/FormPNSSVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSSVigilanciaController.class, "/stec/view/formularios/FormPNSSVigilancia.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------PNSA----------------------------------------------
    @FXML
    private void handlePNSACadastro(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnsa_cadastro_estabelecimentos", "PNSA - CADASTRO DE ESTABELECIMENTOS");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSACadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSACadastroEstabelecimentos.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSACadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSACadastroEstabelecimentos.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handlePNSAVigilancia(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("pnsa_vigilancia", "PNSA - VIGILÂNCIA EPIDEMIOLÓGICA");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormPNSAVigilanciaController.class, "/stec/view/formularios/FormPNSAVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormPNSAVigilanciaController.class, "/stec/view/formularios/FormPNSAVigilancia.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Interacao com a comunidade------------------------
    @FXML
    private void handleICEducacaoSanitaria(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("interacao_comunidade_educacao", "INTERAÇÃO COM A COMUNIDADE: Educação sanitária e comunicação social (divulgação e publicidade)");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormICEducacaoSanitariaController.class, "/stec/view/formularios/FormICEducacaoSanitaria.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormICEducacaoSanitariaController.class, "/stec/view/formularios/FormICEducacaoSanitaria.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleICParticipacaoComunidade(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("interacao_comunidade_comunidade", "INTERAÇÃO COM A COMUNIDADE: Participação com a comunidade");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormICParticipacaoComunidadeController.class, "/stec/view/formularios/FormICParticipacaoComunidade.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormICParticipacaoComunidadeController.class, "/stec/view/formularios/FormICParticipacaoComunidade.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleICParticipacaoInstituicoes(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("interacao_comunidade_instituicoes", "INTERAÇÃO COM A COMUNIDADE: Participação com instituições e representações");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormICParticipacaoInstituicoesController.class, "/stec/view/formularios/FormICParticipacaoInstituicoes.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormICParticipacaoInstituicoesController.class, "/stec/view/formularios/FormICParticipacaoInstituicoes.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Interacao com medicos veterinarios----------------
    @FXML
    private void handleIMVHabilitacao(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("interacao_veterinario_habilitacao", "INTERAÇÃO COM OS MÉDICOS VETERINÁRIOS: Habilitação e cadastramento dos médicos veterinários");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormIMVHabilitacaoController.class, "/stec/view/formularios/FormIMVHabilitacao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormIMVHabilitacaoController.class, "/stec/view/formularios/FormIMVHabilitacao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Interacao com as instituicoes---------------------
    @FXML
    private void handleIISistemaInspecao(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("interacao_instituicao_inspecao", "INTERAÇÃO COM INSTITUIÇÕES: Sistema de inspeção (seguridade alimentar)");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormIISistemaInspecaoController.class, "/stec/view/formularios/FormIISistemaInspecao.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormIISistemaInspecaoController.class, "/stec/view/formularios/FormIISistemaInspecao.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    @FXML
    private void handleIISUS(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("interacao_instituicao_sus", "INTERAÇÃO COM INSTITUIÇÕES: Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormIISUSController.class, "/stec/view/formularios/FormIISUS.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormIISUSController.class, "/stec/view/formularios/FormIISUS.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Acesso aos mercados-------------------------------
    @FXML
    private void handleAMAcesso(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("acesso_mercado", "ACESSO AOS MERCADOS");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormAMAcessoController.class, "/stec/view/formularios/FormAMAcesso.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormAMAcessoController.class, "/stec/view/formularios/FormAMAcesso.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }

    //------------------------Vulnerabilidade e potencialidades-----------------
    @FXML
    private void handleVulnerabilidadesPotencialidades(ActionEvent event) throws IOException {
        //instanciar o objeto com os dados do formulario
        Formulario formulario = new Formulario("vulnerabilidade_potencialidade", "Vulnerabilidades e Potencialidades");

        Resposta resposta = new Resposta();
        resposta.setFormulario(formulario);//passo o formulario trabalhado
        resposta.setSupervisao(supervisao);//passo a supervisao aberta

        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario

            if (showForm(resposta, FormVulnerabilidadesPotencialidadesController.class, "/stec/view/formularios/FormVulnerabilidadesPotencialidades.fxml"))//se clicou no botao adicionar, inseri a respostas
            {
                respostaDAO.alterar(resposta);
            }
        } else {

            if (showForm(resposta, FormVulnerabilidadesPotencialidadesController.class, "/stec/view/formularios/FormVulnerabilidadesPotencialidades.fxml")) {
                respostaDAO.inserir(resposta);
            }
        }
    }
    
    //Função do botão voltar
    @FXML
    public void handleVoltar(ActionEvent event){
        dialog.close();
    }
    
    //Função do botão sair
    public void handleSair(ActionEvent event){
        Platform.exit();
    }
}
