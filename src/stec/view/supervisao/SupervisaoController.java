package stec.view.supervisao;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stec.controller.FormAGBaseLegalController;
import stec.controller.FormAGOrganizacaoController;
import stec.controller.FormAGSupervisaoController;
import stec.controller.FormAMAcessoController;
import stec.controller.FormCTOAtendimentoSuspeitaController;
import stec.controller.FormCTOCapacidadeDeteccaoPrecoceController;
import stec.controller.FormCTOControleCadastroController;
import stec.controller.FormCTOControleController;
import stec.controller.FormCTOControleEventosAglomeracaoController;
import stec.controller.FormCTOControleTransitoAnimaisController;
import stec.controller.FormCTOFiscalizacaoController;
import stec.controller.FormCTOPlanejamentoController;
import stec.controller.FormEOEstruturaController;
import stec.controller.FormICEducacaoSanitariaController;
import stec.controller.FormICParticipacaoComunidadeController;
import stec.controller.FormICParticipacaoInstituicoesController;
import stec.controller.FormIISUSController;
import stec.controller.FormIISistemaInspecaoController;
import stec.controller.FormIMVHabilitacaoController;
import stec.controller.FormIdentificacaoEscritorioController;
import stec.controller.FormPNCEBTControlesController;
import stec.controller.FormPNCEBTFiscalizacoesController;
import stec.controller.FormPNCEBTVigilianciaController;
import stec.controller.FormPNCRHFiscalizacoesController;
import stec.controller.FormPNCRHVigilanciaController;
import stec.controller.FormPNEEBVigilanciaController;
import stec.controller.FormPNEFAFiscalizacoesController;
import stec.controller.FormPNEFAVigilanciaController;
import stec.controller.FormPNSACadastroEstabelecimentosController;
import stec.controller.FormPNSAVigilanciaController;
import stec.controller.FormPNSCOCadastroController;
import stec.controller.FormPNSCOVigilanciaController;
import stec.controller.FormPNSECadastroEstabelecimentosController;
import stec.controller.FormPNSEControlesController;
import stec.controller.FormPNSEVigilanciaController;
import stec.controller.FormPNSSCadastroEstabelecimentosController;
import stec.controller.FormPNSSVigilanciaController;
import stec.controller.FormRFEquipamentosController;
import stec.controller.FormRFIArrecadacaoController;
import stec.controller.FormRFICusteioController;
import stec.controller.FormRFInstalacoesController;
import stec.controller.FormRFSistemasInformacaoController;
import stec.controller.FormRFTransportesController;
import stec.controller.FormRHCapacitacaoController;
import stec.controller.FormRHCompetenciasController;
import stec.controller.FormRHEstabilidadeController;
import stec.controller.FormRHQuantitativoController;
import stec.controller.FormVulnerabilidadesPotencialidadesController;
import stec.model.dao.RespostaDAO;
import stec.model.domain.Formulario;
import stec.model.domain.Resposta;
import stec.model.domain.Supervisao;
import stec.resources.Classes.IFormulario;

public class SupervisaoController implements Initializable {

    private Supervisao supervisao = new Supervisao();
    private final RespostaDAO respostaDAO = new RespostaDAO();

    @FXML
    private Label lbUr;
    @FXML
    private Label lbEscritorio;
    @FXML
    private Label lbData;
    @FXML
    private Label lbProgramas;
    @FXML
    private Label lbAuditor;
    @FXML
    private AnchorPane anchorPaneSupervisoes;
    @FXML
    private AnchorPane formulariosPane;
    @FXML
    private JFXButton btnRHFF;
    @FXML
    private JFXButton btnCTO;
    @FXML
    private JFXButton btnProgramas;
    @FXML
    private JFXButton btnIPI;
    @FXML
    private JFXButton btnAcesso;
//parei aqui

    private JFXButton btnEOEstrutura = new JFXButton("Estrutura Organizacional");
    private JFXButton btnAGBaseLegal = new JFXButton("Autoridade e Gestão da Qualidade - Base Legal e aplicação da legislação, manuais e POPs");
    private JFXButton btnAGOrganizacao = new JFXButton("Autoridade e Gestão da Qualidade - Organização dos processos e unidades");
    private JFXButton btnAGSupervisao = new JFXButton("Autoridade e Gestão da Qualidade - Supervisão e controle interno");

    private JFXButton btnCTOControleCadastro = new JFXButton("Capacidade Técnica e Operacional - Controle de cadastro de produtores, propriedades e animais");
    private JFXButton btnCTOPlanejamento = new JFXButton("Capacidade Técnica e Operacional - Planejamento, execução de atividades e registro");
    private JFXButton btnCTOControle = new JFXButton("Capacidade Técnica e Operacional - Controle de divisas e trânsito interno");
    private JFXButton btnCTOControleTransitoAnimais = new JFXButton("Capacidade Técnica e Operacional - Controle de trânsito de animais e produtos de origem animal");
    private JFXButton btnCTOControleEventosAglomeracao = new JFXButton("Capacidade Técnica e Operacional - Controle de eventos de aglomeração de animais");
    private JFXButton btnCTOFiscalizacao = new JFXButton("Capacidade Técnica e Operacional - Fiscalização em revendas veterinárias");
    private JFXButton btnCTODeteccaoPrecoce = new JFXButton("Capacidade Técnica e Operacional - Detecção precoce e notificação imediata de doenças");
    private JFXButton btnCTOAtendimentoSuspeita = new JFXButton("Capacidade Técnica e Operacional - Atendimento a suspeitas e atuação em emergências");
    
    private JFXButton btnICEducacaoSanitaria = new JFXButton("Interação com a Comunidade - Educação sanitária e comunicação social (divulgação e publicidade)");
    private JFXButton btnICParticipacaoComunidade = new JFXButton("Interação com a Comunidade - Participação com a comunidade");
    private JFXButton btnICParticipacaoInstituicoes = new JFXButton("Interação com a Comunidade - Participação com instituições e representações");
    private JFXButton btnIMVHabilitacao = new JFXButton("Interação com Médico Veterinário - Habilitação e cadastramento dos médicos veterinários");
    private JFXButton btnIISistemaInspecao = new JFXButton("Interação com Instituições - Sistema de inspeção");
    private JFXButton btnIISUS = new JFXButton("Interação com Instituições - Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)");
    

    private JFXButton btnAMAcesso = new JFXButton("Acesso aos mercados");
    private JFXButton btnVulnerabilidadesPotencialidades = new JFXButton("Vulnerabilidades e Potencialidades");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void handleRFF(ActionEvent event) {
        VBox vbox = new VBox();
        vbox.setSpacing(5);

        //-----------------Identificacao de Escritorio------------------------------
        JFXButton btnIdentificacaoEscritorio = new JFXButton("Identificação de Escritório");
        btnIdentificacaoEscritorio.setOnAction((e) -> {
            try {
                show(
                        new Formulario("identificacao_escritorio", "1.1 Identificação do Escritório"),
                        "/stec/view/formularios/FormIdentificacaoEscritorio.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //------------------------Recursos Humanos----------------------------------
        JFXButton btnRHQuantitativo = new JFXButton("Recursos Humanos - Quantitativo, jornada e distribuição");
        btnRHQuantitativo.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rh_quantitativo", "Recursos Humanos - Quantitativo, jornada e distribuição"),
                        "/stec/view/formularios/FormRHQuantitativo.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFXButton btnRHEstabilidade = new JFXButton("Recursos Humanos - Estabilidade das estruturas e sustentabilidade das políticas sanitárias");
        btnRHEstabilidade.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rh_estabilidade", "Recursos Humanos - Estabilidade das estruturas e sustentabilidade das políticas sanitárias"),
                        "/stec/view/formularios/FormRHEstabilidade.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFXButton btnRHCapacitacao = new JFXButton("Recursos Humanos - Capacitação técnica e formação continuada");
        btnRHCapacitacao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rh_capacitacao", "Recursos Humanos - Capacitação técnica e formação continuada"),
                        "/stec/view/formularios/FormRHCapacitacao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFXButton btnRHCompetencias = new JFXButton("Recursos Humanos - Competências e independência técnica");
        btnRHCompetencias.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rh_competencias", "Recursos Humanos - Competências e independência técnica"),
                        "/stec/view/formularios/FormRHCompetencias.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //------------------------Recursos Físicos------------------------------
        JFXButton btnRFInstalacoes = new JFXButton("Recursos Físicos - Instalações");
        btnRFInstalacoes.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rf_instalacoes", "Recursos Físicos - INSTALAÇÕES"),
                        "/stec/view/formularios/FormRFInstalacoes.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFXButton btnRFTransportes = new JFXButton("Recursos Físicos - Transportes");
        btnRFTransportes.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rf_transportes", "Recursos Físicos - TRANSPORTES"),
                        "/stec/view/formularios/FormRFTransportes.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFXButton btnRFEquipamentos = new JFXButton("Recursos Físicos - Equipamentos e acesso a comunicação");
        btnRFEquipamentos.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rf_equipamentos", "Recursos Físicos - Equipamentos e acesso à comunicação"),
                        "/stec/view/formularios/FormRFEquipamentos.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFXButton btnRFSistemasInformacao = new JFXButton("Recursos Físicos - Sistemas de informação");
        btnRFSistemasInformacao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rf_sistemas", "Recursos Físicos - Sistemas de informação"),
                        "/stec/view/formularios/FormRFSistemasInformacao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //------------------------Recursos Financeiros------------------------------
        JFXButton btnRFICusteio = new JFXButton("Recursos Financeiros - Custeio");
        btnRFICusteio.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rfi_custeio", "Recursos Financeiros - RECURSOS PARA CUSTEIO"),
                        "/stec/view/formularios/FormRFICusteio.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFXButton btnRFIArrecadacao = new JFXButton("Recursos Financeiros - Arrecadação");
        btnRFIArrecadacao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("rfi_arrecadacao", "Recursos Financeiros - ARRECADAÇÃO"),
                        "/stec/view/formularios/FormRFIArrecadacao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        vbox.getChildren().addAll(
                btnIdentificacaoEscritorio,
                btnRHQuantitativo,
                btnRHEstabilidade,
                btnRHCapacitacao,
                btnRHCompetencias,
                btnRFInstalacoes,
                btnRFTransportes,
                btnRFEquipamentos,
                btnRFSistemasInformacao,
                btnRFICusteio,
                btnRFIArrecadacao);

        formulariosPane.getChildren().clear();
        formulariosPane.getChildren().add(vbox);
    }

    @FXML
    private void handleCTO(ActionEvent event) {
        VBox vbox = new VBox();
        vbox.setSpacing(5);

        //------------------------Estrutura organizacional--------------------------
        btnEOEstrutura.setOnAction((e) -> {
            try {
                show(
                        new Formulario("eo_estrutura", "Estrutura Organizacional - Estrutura organizacional e capacidade de coordenação interna"),
                        "/stec/view/formularios/FormEOEstrutura.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //------------------------Autoridade e Gestão da Qualidade-----------------
        btnAGBaseLegal.setOnAction((e) -> {
            try {
                show(
                        new Formulario("ag_base_legal", "Autoridade e Gestão da Qualidade - Base legal e aplicação da legislação, manuais e POPs"),
                        "/stec/view/formularios/FormAGBaseLegal.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnAGOrganizacao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("ag_organizacao", "Autoridade e Gestão de Qualidade - Organização dos processos e unidades"),
                        "/stec/view/formularios/FormAGOrganizacao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnAGSupervisao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("ag_supervisao", "Autoridade e Gestão de Qualidade - Supervisão e controle interno"),
                        "/stec/view/formularios/FormAGSupervisao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //------------------------Capacidade técnica operacional--------------------
        btnCTOControleCadastro.setOnAction((e) -> {
            try {
                show(
                        new Formulario("cto_controle_cadastro", "Capacidade Técnica e Operacional - Controle de cadastro de produtores, propriedades e animais"),
                        "/stec/view/formularios/FormCTOControleCadastro.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCTOPlanejamento.setOnAction((e) -> {
            try {
                show(
                        new Formulario("cto_planejamento", "Capacidade Técnica e Operacional - Planejamento, execução de atividades e registro"),
                        "/stec/view/formularios/FormCTOPlanejamento.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCTOControle.setOnAction((e) -> {
            try {
                show(
                        new Formulario("cto_controle", "Capacidade Técnica e Operacional - Controle de divisas e trânsito interno"),
                        "/stec/view/formularios/FormCTOControle.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCTOControleTransitoAnimais.setOnAction((e) -> {
            try {
                show(
                        new Formulario("cto_transito_animais", "Capacidade Técnica e Operacional - Controle de trânsito de animais e produtos de origem animal"),
                        "/stec/view/formularios/FormCTOControleTransitoAnimais.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCTOControleEventosAglomeracao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("cto_controle_eventos", "Capacidade Técnica e Operacional - Controle de eventos de aglomeração de animais"),
                        "/stec/view/formularios/FormCTOControleEventosAglomeracao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCTOFiscalizacao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("cto_fiscalizacao", "Capacidade Técnica e Operacional - Fiscalização em revendas veterinárias"),
                        "/stec/view/formularios/FormCTOFiscalizacao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCTODeteccaoPrecoce.setOnAction((e) -> {
            try {
                show(
                        new Formulario("cto_deteccao_precoce", "Capacidade Técnica e Operacional - Capacidade para detecção precoce e notificação imediata de doenças"),
                        "/stec/view/formularios/FormCTOCapacidadeDeteccaoPrecoce.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCTOAtendimentoSuspeita.setOnAction((e) -> {
            try {
                show(
                        new Formulario("cto_atendimento_suspeita", "Capacidade Técnica e Operacional - Capacidade para atendimento a suspeitas e atuação em emergências"),
                        "/stec/view/formularios/FormCTOAtendimentoSuspeita.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        vbox.getChildren().addAll(
                btnEOEstrutura,
                btnAGBaseLegal,
                btnAGOrganizacao,
                btnAGSupervisao,
                btnCTOControleCadastro,
                btnCTOPlanejamento,
                btnCTOControle,
                btnCTOControleTransitoAnimais,
                btnCTOControleEventosAglomeracao,
                btnCTOFiscalizacao,
                btnCTODeteccaoPrecoce,
                btnCTOAtendimentoSuspeita);

        formulariosPane.getChildren().clear();
        formulariosPane.getChildren().add(vbox);
    }

    @FXML
    private void handleProgramas(ActionEvent event) {

    }

    @FXML
    private void handleIPI(ActionEvent event) {
        VBox vbox = new VBox();
        vbox.setSpacing(5);

        //------------------------Interacao com a comunidade------------------------
        btnICEducacaoSanitaria.setOnAction((e) -> {
            try {
                show(
                        new Formulario("interacao_comunidade_educacao", "INTERAÇÃO COM A COMUNIDADE: Educação sanitária e comunicação social (divulgação e publicidade)"),
                        "/stec/view/formularios/FormICEducacaoSanitaria.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnICParticipacaoComunidade.setOnAction((e) -> {
            try {
                show(
                        new Formulario("interacao_comunidade_comunidade", "INTERAÇÃO COM A COMUNIDADE: Participação com a comunidade"),
                        "/stec/view/formularios/FormICParticipacaoComunidade.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnICParticipacaoInstituicoes.setOnAction((e) -> {
            try {
                show(
                        new Formulario("interacao_comunidade_instituicoes", "INTERAÇÃO COM A COMUNIDADE: Participação com instituições e representações"),
                        "/stec/view/formularios/FormICParticipacaoInstituicoes.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //------------------------Interacao com medicos veterinarios----------------
        btnIMVHabilitacao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("interacao_veterinario_habilitacao", "INTERAÇÃO COM OS MÉDICOS VETERINÁRIOS: Habilitação e cadastramento dos médicos veterinários"),
                        "/stec/view/formularios/FormIMVHabilitacao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //------------------------Interacao com as instituicoes---------------------
        btnIISistemaInspecao.setOnAction((e) -> {
            try {
                show(
                        new Formulario("interacao_instituicao_inspecao", "INTERAÇÃO COM INSTITUIÇÕES: Sistema de inspeção (seguridade alimentar)"),
                        "/stec/view/formularios/FormIISistemaInspecao.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnIISUS.setOnAction((e) -> {
            try {
                show(
                        new Formulario("interacao_instituicao_sus", "INTERAÇÃO COM INSTITUIÇÕES: Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)"),
                        "/stec/view/formularios/FormIISUS.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        vbox.getChildren().addAll(
                btnICEducacaoSanitaria,
                btnICParticipacaoComunidade,
                btnICParticipacaoInstituicoes,
                btnIMVHabilitacao,
                btnIISistemaInspecao,
                btnIISUS);

        formulariosPane.getChildren().clear();
        formulariosPane.getChildren().add(vbox);
    }

    @FXML
    private void handleAcesso(ActionEvent event) {
        VBox vbox = new VBox();
        vbox.setSpacing(5);

        //------------------------Acesso aos mercados-------------------------------
        btnAMAcesso.setOnAction((e) -> {
            try {
                show(
                        new Formulario("acesso_mercado", "ACESSO AOS MERCADOS"),
                        "/stec/view/formularios/FormAMAcesso.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //------------------------Vulnerabilidade e potencialidades-----------------
        btnVulnerabilidadesPotencialidades.setOnAction((e) -> {
            try {
                show(
                        new Formulario("vulnerabilidade_potencialidade", "Vulnerabilidades e Potencialidades"),
                        "/stec/view/formularios/FormVulnerabilidadesPotencialidades.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SupervisaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        vbox.getChildren().addAll(btnAMAcesso, btnVulnerabilidadesPotencialidades);

        formulariosPane.getChildren().clear();
        formulariosPane.getChildren().add(vbox);
    }

    public Supervisao getSupervisao() {
        return supervisao;
    }

    public void setSupervisao(Supervisao supervisao) {
        this.supervisao = supervisao;

        lbUr.setText(supervisao.getUr().getNome());

        String escritorio = this.supervisao.getTipoEscritorio() + " / ";

        if (this.supervisao.getTipoEscritorio().equals("EAC")) {
            escritorio += this.supervisao.getEac().getNome();
        } else {
            escritorio += this.supervisao.getUlsav().getNome();
        }

        lbEscritorio.setText(escritorio);
        lbData.setText(supervisao.getCreated());
        lbProgramas.setText(supervisao.getProgramas().toString());
        lbAuditor.setText(supervisao.getUsuario().getNome());

        //verifica se tem o programa, caso tenha habilita para preenchimento
//        if (supervisao.getProgramas().toString().contains("PNEFA"))
//            tabPNEFA.setDisable(false);
//
//        if (supervisao.getProgramas().toString().contains("PNCEBT"))
//            tabPNCEBT.setDisable(false);
//
//        if (supervisao.getProgramas().toString().contains("PNCRH"))
//            tabPNCRH.setDisable(false);
//
//        if (supervisao.getProgramas().toString().contains("PNEEB"))
//            tabPNEEB.setDisable(false);
//
//        if (supervisao.getProgramas().toString().contains("PNSA"))
//            tabPNSA.setDisable(false);
//
//        if (supervisao.getProgramas().toString().contains("PNSCO"))
//            tabPNSCO.setDisable(false);
//
//        if (supervisao.getProgramas().toString().contains("PNSE"))
//            tabPNSE.setDisable(false);
//
//        if (supervisao.getProgramas().toString().contains("PNSS"))
//            tabPNSS.setDisable(false);
    }

    public void show(Formulario form, String url) throws IOException {
        Boolean update = false;

        Resposta resposta = new Resposta();

        //passo o slug e titulo do formulario trabalhado
        resposta.setFormulario(form);
        //passo a supervisao
        resposta.setSupervisao(supervisao);

        //Verificar se ja existe uma resposta nesta supervisao pra esse formulario
        if (respostaDAO.temResposta(resposta)) {
            //traz as informacoes para preencher no formulario
            resposta = respostaDAO.buscar(resposta);
            update = true;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(url));

        AnchorPane pane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        stage.setTitle(resposta.getFormulario().getNome());
        //stage.setMaximized(true);
        //stage.setResizable(false);

        Scene scene = new Scene(pane);

        stage.setScene(scene);

        IFormulario controller = loader.getController();

        controller.setDialog(stage);
        controller.setResposta(resposta);

        stage.showAndWait();

        if (controller.isBtConfirmarClicked()) {
            if (update) {
                respostaDAO.alterar(resposta);
            } else {
                respostaDAO.inserir(resposta);
            }
        }

    }

    //------------------------PNEFA---------------------------------------------
//    private void handlePNEFAFiscalizacoes(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnefa_fiscalizacoes", "PNEFA - FISCALIZAÇÕES DE VACINAÇÕES");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNEFAFiscalizacoesController.class, "/stec/view/formularios/FormPNEFAFiscalizacoes.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNEFAFiscalizacoesController.class, "/stec/view/formularios/FormPNEFAFiscalizacoes.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNEFAVigilancia(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnefa_vigilancia", "PNEFA - VIGILÂNCIA EPIDEMIOLÓGICA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNEFAVigilanciaController.class, "/stec/view/formularios/FormPNEFAVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNEFAVigilanciaController.class, "/stec/view/formularios/FormPNEFAVigilancia.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    //------------------------PNCEBT--------------------------------------------
//    private void handlePNCEBTFiscalizacoes(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pncebt_fiscalizacoes", "PNCEBT - FISCALIZAÇÕES DE VACINAÇÕES");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNCEBTFiscalizacoesController.class, "/stec/view/formularios/FormPNCEBTFiscalizacoes.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNCEBTFiscalizacoesController.class, "/stec/view/formularios/FormPNCEBTFiscalizacoes.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNCEBTVigilancia(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pncebt_vigilancia", "PNCEBT - VIGILÂNCIA EPIDEMIOLÓGICA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNCEBTVigilianciaController.class, "/stec/view/formularios/FormPNCEBTVigiliancia.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNCEBTVigilianciaController.class, "/stec/view/formularios/FormPNCEBTVigiliancia.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNCEBTControles(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pncebt_controles", "PNCEBT - CONTROLES DO PROGRAMA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNCEBTControlesController.class, "/stec/view/formularios/FormPNCEBTControles.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNCEBTControlesController.class, "/stec/view/formularios/FormPNCEBTControles.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    //------------------------PNCRH---------------------------------------------
//    private void handlePNCRHFiscalizacoes(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pncrh_fiscalizacoes", "PNCRH - FISCALIZAÇÕES DE VACINAÇÕES");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNCRHFiscalizacoesController.class, "/stec/view/formularios/FormPNCRHFiscalizacoes.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNCRHFiscalizacoesController.class, "/stec/view/formularios/FormPNCRHFiscalizacoes.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNCRHVigilancia(ActionEvent event) throws IOException {
//
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pncrh_vigilancia", "PNCRH - VIGILÂNCIA EPIDEMIOLÓGICA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNCRHVigilanciaController.class, "/stec/view/formularios/FormPNCRHVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNCRHVigilanciaController.class, "/stec/view/formularios/FormPNCRHVigilancia.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    //------------------------PNEEB---------------------------------------------
//    private void handlePNEEBVigilancia(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pneeb_vigilancia", "PNEEB - VIGILÂNCIA EPIDEMIOLÓGICA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNEEBVigilanciaController.class, "/stec/view/formularios/FormPNEEBVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNEEBVigilanciaController.class, "/stec/view/formularios/FormPNEEBVigilancia.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    //------------------------PNSE----------------------------------------------
//    private void handlePNSECadastro(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnse_cadastro_estabelecimentos", "PNSE - CADASTRO DE ESTABELECIMENTOS");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSECadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSECadastroEstabelecimentos.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSECadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSECadastroEstabelecimentos.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNSEVigilancia(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnse_vigilancia", "PNSE - VIGILÂNCIA EPIDEMIOLÓGICA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSEVigilanciaController.class, "/stec/view/formularios/FormPNSEVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSEVigilanciaController.class, "/stec/view/formularios/FormPNSEVigilancia.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNSEControles(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnse_controles", "PNSE - CONTROLES DO PROGRAMA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSEControlesController.class, "/stec/view/formularios/FormPNSEControles.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSEControlesController.class, "/stec/view/formularios/FormPNSEControles.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    //------------------------PNSCO---------------------------------------------
//    private void handlePNSCOCadastro(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnsco_cadastro_estabelecimentos", "PNSCO - CADASTRO DE ESTABELECIMENTOS");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSCOCadastroController.class, "/stec/view/formularios/FormPNSCOCadastro.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSCOCadastroController.class, "/stec/view/formularios/FormPNSCOCadastro.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNSCOVigilancia(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnsco_vigilancia", "PNSCO - VIGILÂNCIA EPIDEMIOLÓGICA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSCOVigilanciaController.class, "/stec/view/formularios/FormPNSCOVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSCOVigilanciaController.class, "/stec/view/formularios/FormPNSCOVigilancia.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    //------------------------PNSS----------------------------------------------
//    private void handlePNSSCadastro(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnss_cadastro_estabelecimentos", "PNSS - CADASTRO DE ESTABELECIMENTOS");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSSCadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSSCadastroEstabelecimentos.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSSCadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSSCadastroEstabelecimentos.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNSSVigilancia(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnss_vigilancia", "PNSS - VIGILÂNCIA EPIDEMIOLÓGICA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSSVigilanciaController.class, "/stec/view/formularios/FormPNSSVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSSVigilanciaController.class, "/stec/view/formularios/FormPNSSVigilancia.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    //------------------------PNSA----------------------------------------------
//    private void handlePNSACadastro(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnsa_cadastro_estabelecimentos", "PNSA - CADASTRO DE ESTABELECIMENTOS");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSACadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSACadastroEstabelecimentos.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSACadastroEstabelecimentosController.class, "/stec/view/formularios/FormPNSACadastroEstabelecimentos.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }
//
//    private void handlePNSAVigilancia(ActionEvent event) throws IOException {
//        //instanciar o objeto com os dados do formulario
//        Formulario formulario = new Formulario("pnsa_vigilancia", "PNSA - VIGILÂNCIA EPIDEMIOLÓGICA");
//
//        Resposta resposta = new Resposta();
//        resposta.setFormulario(formulario);//passo o formulario trabalhado
//        resposta.setSupervisao(supervisao);//passo a supervisao aberta
//
//        if (respostaDAO.temResposta(resposta)) {//Verificar se ja existe uma resposta nesta supervisao pra esse formulario
//            resposta = respostaDAO.buscar(resposta);//traz as informacoes para preencher no formulario
//
//            if (showForm(resposta, FormPNSAVigilanciaController.class, "/stec/view/formularios/FormPNSAVigilancia.fxml"))//se clicou no botao adicionar, inseri a respostas
//            {
//                respostaDAO.alterar(resposta);
//            }
//        } else {
//
//            if (showForm(resposta, FormPNSAVigilanciaController.class, "/stec/view/formularios/FormPNSAVigilancia.fxml")) {
//                respostaDAO.inserir(resposta);
//            }
//        }
//    }

}