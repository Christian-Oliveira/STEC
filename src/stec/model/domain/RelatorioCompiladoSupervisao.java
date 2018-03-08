package stec.model.domain;

import com.google.gson.Gson;

import static j2html.TagCreator.*; // Use static star import
import j2html.tags.Tag;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import stec.controller.FormCTOPlanejamentoController.FormCTOPlanejamento;
import stec.controller.FormEOEstruturaController.FormEOEstrutura;
import stec.controller.FormICEducacaoSanitariaController.FormICEducacaoSanitaria;
import stec.controller.FormICParticipacaoComunidadeController.FormICParticipacaoComunidade;
import stec.controller.FormICParticipacaoInstituicoesController.FormICParticipacaoInstituicoes;
import stec.controller.FormIISUSController.FormIISUS;
import stec.controller.FormIISistemaInspecaoController.FormIISistemaInspecao;
import stec.controller.FormIMVHabilitacaoController.FormIMVHabilitacao;
import stec.controller.FormPNCEBTControlesController.FormPNCEBTControles;
import stec.controller.FormPNCEBTFiscalizacoesController.FormPNCEBTFiscalizacoes;
import stec.controller.FormPNCEBTVigilianciaController.FormPNCEBTVigilancia;
import stec.controller.FormPNCRHFiscalizacoesController.FormPNCRHFiscalizacoes;
import stec.controller.FormPNCRHVigilanciaController.FormPNCRHVigilancia;
import stec.controller.FormPNEEBVigilanciaController.FormPNEEBVigilancia;
import stec.controller.FormPNEFAFiscalizacoesController.FormPNEFAFiscalizacoes;
import stec.controller.FormPNEFAVigilanciaController.FormPNEFAVigilancia;
import stec.controller.FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos;
import stec.controller.FormPNSAVigilanciaController.FormPNSAVigilancia;
import stec.controller.FormPNSCOCadastroController.FormPNSCOCadastros;
import stec.controller.FormPNSCOVigilanciaController.FormPNSCOVigilancia;
import stec.controller.FormPNSECadastroEstabelecimentosController.FormPNSECadastroEstabelecimento;
import stec.controller.FormPNSEControlesController.FormPNSEControles;
import stec.controller.FormPNSEVigilanciaController.FormPNSEVigilancia;
import stec.controller.FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades;
import stec.controller.FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento;
import stec.controller.FormPNSSVigilanciaController.FormPNSSVigilancia;
import stec.controller.FormRFEquipamentosController.FormRFEquipamentos;
import stec.controller.FormRFEquipamentosController.Municipio;
import stec.controller.FormRFIArrecadacaoController.FormRFIArrecadacao;
import stec.controller.FormRFICusteioController.FormRFICusteio;
import stec.controller.FormRFInstalacoesController.FormRFInstalacoes;
import stec.controller.FormRFSistemasInformacaoController.FormRFSistemasInformacao;
import stec.controller.FormRFTransportesController.FormRFTransportes;
import stec.controller.FormRFTransportesController.Transporte;
import stec.controller.FormRHCapacitacaoController.Capacitacao;
import stec.controller.FormRHCapacitacaoController.FormRHCapacitacao;
import stec.controller.FormRHCompetenciasController.FormRHCompetencias;
import stec.controller.FormRHEstabilidadeController.FormRHEstabilidade;
import stec.controller.FormRHQuantitativoController.FormRHQuantitativo;
import stec.controller.FormCTOControleController.FormCTOControle;
import stec.controller.FormCTOControleEventosAglomeracaoController.FormCTOControleEventosAglomeracao;
import stec.controller.FormCTOControleTransitoAnimaisController.FormCTOControleTransitoAnimais;
import stec.controller.FormCTOFiscalizacaoController.FormCTOFiscalizacao;
import stec.controller.FormAGBaseLegalController.FormAGBaseLegal;
import stec.controller.FormAGOrganizacaoController.FormAGOrganizacao;
import stec.controller.FormAGSupervisaoController.FormAGSupervisao;
import stec.controller.FormAMAcessoController.FormAMAcesso;
import stec.controller.FormCTOAtendimentoSuspeitaController.FormCTOAtendimentoSuspeita;
import stec.controller.FormCTOCapacidadeDeteccaoPrecoceController.FormCTOCapacidadeDeteccaoPrecoce;
import stec.controller.FormCTOControleCadastroController.FormCTOControleCadastro;

public class RelatorioCompiladoSupervisao {

    Gson gson = new Gson();

    public List<Supervisao> listSupervisao = new ArrayList<>();

    public HashMap<String, Integer> hashPadrao = new HashMap<String, Integer>() {
        {
            put("ED", 0);
            put("EA", 0);
            put("NE", 0);
            put("NA", 0);
        }
    };

    public HashMap<String, Integer> hashAvaliacaoPadrao = new HashMap<String, Integer>() {
        {
            put("1", 0);
            put("2", 0);
            put("3", 0);
            put("4", 0);
            put("5", 0);
            put("NA", 0);
        }
    };

    public List<Supervisao> getListSupervisao() {
        return listSupervisao;
    }

    public void setListSupervisao(List<Supervisao> listSupervisao) {
        this.listSupervisao = listSupervisao;
    }
   
    public void showRelatorioUr(String file) {

    }

    public void showRelatorioMunicipio(String file) {

    }

    public void ava(String i, HashMap<String, Integer> h) {

        //Recebe os valores atuais do hash
        int qtdUm = h.get("1");
        int qtdDois = h.get("2");
        int qtdTres = h.get("3");
        int qtdQuatro = h.get("4");
        int qtdCinco = h.get("5");
        int qtdNAA = h.get("NA");

        switch (i) {
            case "1":
                qtdUm += 1;
                break;
            case "2":
                qtdDois += 1;
                break;
            case "3":
                qtdTres += 1;
                break;
            case "4":
                qtdQuatro += 1;
                break;
            case "5":
                qtdCinco += 1;
                break;
            case "NA":
                qtdNAA += 1;
                break;
            default:
                break;
        }

        h.put("1", qtdUm);
        h.put("2", qtdDois);
        h.put("3", qtdTres);
        h.put("4", qtdQuatro);
        h.put("5", qtdCinco);
        h.put("NA", qtdNAA);
    }

    public void item(String i, HashMap<String, Integer> h) {

        //Recebe os valores atuais do hash
        int qtdEA = h.get("EA");
        int qtdED = h.get("ED");
        int qtdNE = h.get("NE");
        int qtdNA = h.get("NA");

        switch (i) {
            case "EA":
                qtdEA += 1;
                break;
            case "ED":
                qtdED += 1;
                break;
            case "NE":
                qtdNE += 1;
                break;
            case "NA":
                qtdNA += 1;
                break;
            default:
                break;
        }

        h.put("EA", qtdEA);
        h.put("ED", qtdED);
        h.put("NE", qtdNE);
        h.put("NA", qtdNA);
    }

    public void showRelatorioEstado(String file) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String report = "";

            report = render(file, main(
                    h3("1. RECURSOS HUMANOS, FÍSICOS E FINANCEIROS")
                    ,div(attrs(".row")).with(RecursosHumanos(listSupervisao))
                    ,div(attrs(".row")).with(RecursosFisicos(listSupervisao))
                    ,div(attrs(".row")).with(RecursosFinanceiros(listSupervisao))
                    ,h3("2. AUTORIDADE, CAPACIDADE TÉCNICA E OPERACIONAL")
                    ,div(attrs(".row")).with(EstruturaOrganizacional(listSupervisao))
                    ,div(attrs(".row")).with(AutoridadeGestao(listSupervisao))
                    ,div(attrs(".row")).with(CapacidadeTecnicaOperacional(listSupervisao))
                    ,h4(attrs(".titulo-form"), "2.4 PREVENÇÃO, CONTROLE E ERRADICAÇÃO DE DOENÇAS")
                    ,div(attrs(".row")).with(PNEFA(listSupervisao))
                    ,div(attrs(".row")).with(PNCEBT(listSupervisao))
                    ,div(attrs(".row")).with(PNCRH(listSupervisao))
                    ,div(attrs(".row")).with(PNEEB(listSupervisao))
                    ,div(attrs(".row")).with(PNSE(listSupervisao))
                    ,div(attrs(".row")).with(PNSCO(listSupervisao))
                    ,div(attrs(".row")).with(PNSS(listSupervisao))
                    ,div(attrs(".row")).with(PNSA(listSupervisao))
                    ,h3("3. INTERAÇÃO COM AS PARTES INTERESSADAS")
                    ,div(attrs(".row")).with(InteracaoComunidade(listSupervisao))
                    ,div(attrs(".row")).with(InteracaoVeterinario(listSupervisao))
                    ,div(attrs(".row")).with(InteracaoInstituicoes(listSupervisao))
                    ,h3("4 ACESSO AOS MERCADOS")
                    ,div(attrs(".row")).with(acessoMercado(listSupervisao))
            ));

            fw = new FileWriter(file + ".html");
            bw = new BufferedWriter(fw);
            bw.write(report);

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static String render(String pageTitle, Tag mainTag) {
        return document(
                html(
                        head(
                                title(pageTitle),
                                link().withRel("stylesheet").withHref("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"),
                                script().withSrc("https://www.gstatic.com/charts/loader.js"),
                                style(".chart-container {\n"
                                        + "                position: relative;\n"
                                        + "                margin: auto;\n"
                                        + "                height: 80vh;\n"
                                        + "                width: 40vw;\n"
                                        + "            }\n"
                                        + "                 .titulo-form {\n"
                                        + "			clear: both;\n"
                                        + "                   }"
                                        + "            @media print {\n"
                                        + "                .page-break {page-break-after: always;}\n"
                                        + "\n"
                                        + "                .chart-container {\n"
                                        + "                    position: relative;\n"
                                        + "                    margin: auto;\n"
                                        + "                    height: 70vh;\n"
                                        + "                    width: 30vw;\n"
                                        + "                }\n"
                                        + "            }")
                        ),
                        body(attrs(".container"),
                                header(),
                                mainTag,
                                footer()
                        ),
                        script().withSrc("graficos.js")
                )
        );
    }

    public Tag tabelaAvaliacao(String nomeTabela, String idColuna, HashMap<String, Integer> hash) {
        return div(attrs(".col-md-4"),
                table(attrs(".table .table-condensed .table-striped"),
                        caption(nomeTabela),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), "1"),
                                        td(attrs(".text-center"), "2"),
                                        td(attrs(".text-center"), "3"),
                                        td(attrs(".text-center"), "4"),
                                        td(attrs(".text-center"), "5"),
                                        td(attrs(".text-center"), "NA")
                                ),
                                tr(
                                        td(attrs(".text-center"), hash.get("1").toString()).withId(idColuna + "Av1"),
                                        td(attrs(".text-center"), hash.get("2").toString()).withId(idColuna + "Av2"),
                                        td(attrs(".text-center"), hash.get("3").toString()).withId(idColuna + "Av3"),
                                        td(attrs(".text-center"), hash.get("4").toString()).withId(idColuna + "Av4"),
                                        td(attrs(".text-center"), hash.get("5").toString()).withId(idColuna + "Av5"),
                                        td(attrs(".text-center"), hash.get("NA").toString()).withId(idColuna + "AvNA")
                                )
                        )
                )
        );
    }

    public Tag tabelaItem(String nomeTabela, String idColuna, HashMap<String, Integer> hash) {
        return table(attrs(".table .table-condensed .table-striped"),
                caption(nomeTabela),
                tbody(
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), "NA")
                        ),
                        tr(
                                td(attrs(".text-center"), hash.get("EA").toString()).withId(idColuna + "EA"),
                                td(attrs(".text-center"), hash.get("ED").toString()).withId(idColuna + "ED"),
                                td(attrs(".text-center"), hash.get("NE").toString()).withId(idColuna + "NE"),
                                td(attrs(".text-center"), hash.get("NA").toString()).withId(idColuna + "NA")
                        )
                )
        );
    }
    
    /*
    * Gera as tabelas do item recursos humanos
    */
    public Tag RecursosHumanos(List<Supervisao> list) {

        //1.1.1 Quantitativo, jornada e distribuição
        int qtdAGED = 0;
        int qtdINAGRO = 0;
        int qtdOUTROS = 0;
        int qtdTOTAL = 0;
        int qtdTREINAMENTO = 0;
        int qtdPOS = 0;

        // -----------------Médico veterinário----------------------- 
        HashMap<String, Integer> veterinario = new HashMap<>();

        for (Supervisao supervisao : list) {
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);

            qtdAGED += Integer.parseInt(formulario.getVetQEANS()) + Integer.parseInt(formulario.getVetQEAFA());
            qtdINAGRO += Integer.parseInt(formulario.getVetQEINAGRO());
            qtdOUTROS += Integer.parseInt(formulario.getVetQEEMARPH()) + Integer.parseInt(formulario.getVetQEPrefeitura())
                    + Integer.parseInt(formulario.getVetQESAGRIMA()) + Integer.parseInt(formulario.getVetQESaude());
            qtdTREINAMENTO += Integer.parseInt(formulario.getVetCTAFA()) + Integer.parseInt(formulario.getVetCTANS())
                    + Integer.parseInt(formulario.getVetCTEMARPH()) + Integer.parseInt(formulario.getVetCTINAGRO())
                    + Integer.parseInt(formulario.getVetCTPrefeitura()) + Integer.parseInt(formulario.getVetCTSAGRIMA())
                    + Integer.parseInt(formulario.getVetCTSaude());
            qtdPOS += Integer.parseInt(formulario.getVetCPAFA()) + Integer.parseInt(formulario.getVetCPANS())
                    + Integer.parseInt(formulario.getVetCPEMARPH()) + Integer.parseInt(formulario.getVetCPINAGRO())
                    + Integer.parseInt(formulario.getVetCPPrefeitura()) + Integer.parseInt(formulario.getVetCPSAGRIMA())
                    + Integer.parseInt(formulario.getVetCPSaude());

            veterinario.put("existenteAGED", qtdAGED);
            veterinario.put("existenteINAGRO", qtdINAGRO);
            veterinario.put("existenteOUTROS", qtdOUTROS);
            veterinario.put("treinamento", qtdTREINAMENTO);
            veterinario.put("posgraduacao", qtdPOS);
        }

        //o total deve ser apos o loop das supervisoes para não trazer o resultado errado
        qtdTOTAL = qtdAGED + qtdINAGRO + qtdOUTROS;
        veterinario.put("existenteTOTAL", qtdTOTAL);

        qtdAGED = 0;
        qtdINAGRO = 0;
        qtdOUTROS = 0;
        qtdTOTAL = 0;
        qtdTREINAMENTO = 0;
        qtdPOS = 0;

        // -----------------Agronomo-----------------------
        HashMap<String, Integer> agronomo = new HashMap<>();

        for (Supervisao supervisao : list) {
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);

            qtdAGED += Integer.parseInt(formulario.getAgroQEANS()) + Integer.parseInt(formulario.getAgroQEAFA());
            qtdINAGRO += Integer.parseInt(formulario.getAgroQEINAGRO());
            qtdOUTROS += Integer.parseInt(formulario.getAgroQEEMARPH()) + Integer.parseInt(formulario.getAgroQEPrefeitura())
                    + Integer.parseInt(formulario.getAgroQESAGRIMA()) + Integer.parseInt(formulario.getAgroQESaude());
            qtdTREINAMENTO += Integer.parseInt(formulario.getAgroCTAFA()) + Integer.parseInt(formulario.getAgroCTANS())
                    + Integer.parseInt(formulario.getAgroCTEMARPH()) + Integer.parseInt(formulario.getAgroCTINAGRO())
                    + Integer.parseInt(formulario.getAgroCTPrefeitura()) + Integer.parseInt(formulario.getAgroCTSAGRIMA())
                    + Integer.parseInt(formulario.getAgroCTSaude());
            qtdPOS += Integer.parseInt(formulario.getAgroCPAFA()) + Integer.parseInt(formulario.getAgroCPANS())
                    + Integer.parseInt(formulario.getAgroCPEMARPH()) + Integer.parseInt(formulario.getAgroCPINAGRO())
                    + Integer.parseInt(formulario.getAgroCPPrefeitura()) + Integer.parseInt(formulario.getAgroCPSAGRIMA())
                    + Integer.parseInt(formulario.getAgroCPSaude());

            agronomo.put("existenteAGED", qtdAGED);
            agronomo.put("existenteINAGRO", qtdINAGRO);
            agronomo.put("existenteOUTROS", qtdOUTROS);
            agronomo.put("treinamento", qtdTREINAMENTO);
            agronomo.put("posgraduacao", qtdPOS);
        }

        qtdTOTAL += qtdAGED + qtdINAGRO + qtdOUTROS;
        agronomo.put("existenteTOTAL", qtdTOTAL);

        qtdAGED = 0;
        qtdINAGRO = 0;
        qtdOUTROS = 0;
        qtdTOTAL = 0;
        qtdTREINAMENTO = 0;
        qtdPOS = 0;

        // -----------------Florestal-----------------------
        HashMap<String, Integer> florestal = new HashMap<>();

        for (Supervisao supervisao : list) {
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);

            qtdAGED += Integer.parseInt(formulario.getFlorQEANS()) + Integer.parseInt(formulario.getFlorQEAFA());
            qtdINAGRO += Integer.parseInt(formulario.getFlorQEINAGRO());
            qtdOUTROS += Integer.parseInt(formulario.getFlorQEEMARPH()) + Integer.parseInt(formulario.getFlorQEPrefeitura())
                    + Integer.parseInt(formulario.getFlorQESAGRIMA()) + Integer.parseInt(formulario.getFlorQESaude());
            qtdTREINAMENTO += Integer.parseInt(formulario.getFlorCTAFA()) + Integer.parseInt(formulario.getFlorCTANS())
                    + Integer.parseInt(formulario.getFlorCTEMARPH()) + Integer.parseInt(formulario.getFlorCTINAGRO())
                    + Integer.parseInt(formulario.getFlorCTPrefeitura()) + Integer.parseInt(formulario.getFlorCTSAGRIMA())
                    + Integer.parseInt(formulario.getFlorCTSaude());
            qtdPOS += Integer.parseInt(formulario.getFlorCPAFA()) + Integer.parseInt(formulario.getFlorCPANS())
                    + Integer.parseInt(formulario.getFlorCPEMARPH()) + Integer.parseInt(formulario.getFlorCPINAGRO())
                    + Integer.parseInt(formulario.getFlorCPPrefeitura()) + Integer.parseInt(formulario.getFlorCPSAGRIMA())
                    + Integer.parseInt(formulario.getFlorCPSaude());

            florestal.put("existenteAGED", qtdAGED);
            florestal.put("existenteINAGRO", qtdINAGRO);
            florestal.put("existenteOUTROS", qtdOUTROS);
            florestal.put("treinamento", qtdTREINAMENTO);
            florestal.put("posgraduacao", qtdPOS);
        }

        qtdTOTAL += qtdAGED + qtdINAGRO + qtdOUTROS;
        florestal.put("existenteTOTAL", qtdTOTAL);

        qtdAGED = 0;
        qtdINAGRO = 0;
        qtdOUTROS = 0;
        qtdTOTAL = 0;
        qtdTREINAMENTO = 0;
        qtdPOS = 0;

        // -----------------Zootecnista-----------------------
        HashMap<String, Integer> zootecnista = new HashMap<>();

        for (Supervisao supervisao : list) {
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);

            qtdAGED += Integer.parseInt(formulario.getZooQEANS()) + Integer.parseInt(formulario.getZooQEAFA());
            qtdINAGRO += Integer.parseInt(formulario.getZooQEINAGRO());
            qtdOUTROS += Integer.parseInt(formulario.getZooQEEMARPH()) + Integer.parseInt(formulario.getZooQEPrefeitura())
                    + Integer.parseInt(formulario.getZooQESAGRIMA()) + Integer.parseInt(formulario.getZooQESaude());
            qtdTREINAMENTO += Integer.parseInt(formulario.getZooCTAFA()) + Integer.parseInt(formulario.getZooCTANS())
                    + Integer.parseInt(formulario.getZooCTEMARPH()) + Integer.parseInt(formulario.getZooCTINAGRO())
                    + Integer.parseInt(formulario.getZooCTPrefeitura()) + Integer.parseInt(formulario.getZooCTSAGRIMA())
                    + Integer.parseInt(formulario.getZooCTSaude());
            qtdPOS += Integer.parseInt(formulario.getZooCPAFA()) + Integer.parseInt(formulario.getZooCPANS())
                    + Integer.parseInt(formulario.getZooCPEMARPH()) + Integer.parseInt(formulario.getZooCPINAGRO())
                    + Integer.parseInt(formulario.getZooCPPrefeitura()) + Integer.parseInt(formulario.getZooCPSAGRIMA())
                    + Integer.parseInt(formulario.getZooCPSaude());

            zootecnista.put("existenteAGED", qtdAGED);
            zootecnista.put("existenteINAGRO", qtdINAGRO);
            zootecnista.put("existenteOUTROS", qtdOUTROS);
            zootecnista.put("treinamento", qtdTREINAMENTO);
            zootecnista.put("posgraduacao", qtdPOS);
        }

        qtdTOTAL += qtdAGED + qtdINAGRO + qtdOUTROS;
        zootecnista.put("existenteTOTAL", qtdTOTAL);

        qtdAGED = 0;
        qtdINAGRO = 0;
        qtdOUTROS = 0;
        qtdTOTAL = 0;
        qtdTREINAMENTO = 0;
        qtdPOS = 0;

        // -----------------Tec Agropecuaria-----------------------
        HashMap<String, Integer> tecagropecuaria = new HashMap<>();

        for (Supervisao supervisao : list) {
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);

            qtdAGED += Integer.parseInt(formulario.getTecAgroQEANS()) + Integer.parseInt(formulario.getTecAgroQEAFA());
            qtdINAGRO += Integer.parseInt(formulario.getTecAgroQEINAGRO());
            qtdOUTROS += Integer.parseInt(formulario.getTecAgroQEEMARPH()) + Integer.parseInt(formulario.getTecAgroQEPrefeitura())
                    + Integer.parseInt(formulario.getTecAgroQESAGRIMA()) + Integer.parseInt(formulario.getTecAgroQESaude())
                    + Integer.parseInt(formulario.getTecAgroQESEDUC());
            qtdTREINAMENTO += Integer.parseInt(formulario.getTecAgroCTAFA()) + Integer.parseInt(formulario.getTecAgroCTANS())
                    + Integer.parseInt(formulario.getTecAgroCTEMARPH()) + Integer.parseInt(formulario.getTecAgroCTINAGRO())
                    + Integer.parseInt(formulario.getTecAgroCTPrefeitura()) + Integer.parseInt(formulario.getTecAgroCTSAGRIMA())
                    + Integer.parseInt(formulario.getTecAgroCTSaude())
                    + Integer.parseInt(formulario.getTecAgroCTSEDUC());

            tecagropecuaria.put("existenteAGED", qtdAGED);
            tecagropecuaria.put("existenteINAGRO", qtdINAGRO);
            tecagropecuaria.put("existenteOUTROS", qtdOUTROS);
            tecagropecuaria.put("treinamento", qtdTREINAMENTO);
        }

        qtdTOTAL += qtdAGED + qtdINAGRO + qtdOUTROS;
        tecagropecuaria.put("existenteTOTAL", qtdTOTAL);

        qtdAGED = 0;
        qtdINAGRO = 0;
        qtdOUTROS = 0;
        qtdTOTAL = 0;
        qtdTREINAMENTO = 0;
        qtdPOS = 0;

        // -----------------Tec Administrativo-----------------------
        HashMap<String, Integer> tecadministrativo = new HashMap<>();

        for (Supervisao supervisao : list) {
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);

            qtdAGED += Integer.parseInt(formulario.getAuxAdmQEANS()) + Integer.parseInt(formulario.getAuxAdmQEAFA())
                    + Integer.parseInt(formulario.getAuxAdmQEADO());
            qtdINAGRO += Integer.parseInt(formulario.getAuxAdmQEINAGRO());
            qtdOUTROS += Integer.parseInt(formulario.getAuxAdmQEEMARPH()) + Integer.parseInt(formulario.getAuxAdmQEPrefeitura())
                    + Integer.parseInt(formulario.getAuxAdmQESAGRIMA()) + Integer.parseInt(formulario.getAuxAdmQESaude())
                    + Integer.parseInt(formulario.getAuxAdmQESEDUC());

            qtdTREINAMENTO += Integer.parseInt(formulario.getAuxAdmCTAFA()) + Integer.parseInt(formulario.getAuxAdmCTANS())
                    + Integer.parseInt(formulario.getAuxAdmCTEMARPH()) + Integer.parseInt(formulario.getAuxAdmCTINAGRO())
                    + Integer.parseInt(formulario.getAuxAdmCTPrefeitura()) + Integer.parseInt(formulario.getAuxAdmCTSAGRIMA())
                    + Integer.parseInt(formulario.getAuxAdmCTSaude()) + Integer.parseInt(formulario.getAuxAdmCTSEDUC());

            tecadministrativo.put("existenteAGED", qtdAGED);
            tecadministrativo.put("existenteINAGRO", qtdINAGRO);
            tecadministrativo.put("existenteOUTROS", qtdOUTROS);
            tecadministrativo.put("treinamento", qtdTREINAMENTO);
        }

        qtdTOTAL += qtdAGED + qtdINAGRO + qtdOUTROS;
        tecadministrativo.put("existenteTOTAL", qtdTOTAL);

        //-----------------
        HashMap<String, Integer> avaliacaoQuantitativo = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> atendeDemanda = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoQuantitativo);

            //------------------Itens-------
            item(formulario.getAtendeDemanda(), atendeDemanda);
        }

        //1.1.2 Estabilidade das estruturas e sustentabilidade das políticas sanitárias
        HashMap<String, Integer> avaliacaoEstabilidade = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> estabilidade = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRHEstabilidade formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_estabilidade").getResposta(),
                    FormRHEstabilidade.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoEstabilidade);

            //------------------Itens-------
            item(formulario.getEvidenciaEstabilidade(), estabilidade);
        }

        //1.1.3 Capacitação técnica e formação continuada
        HashMap<String, Integer> avaliacaoCapacitacao = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> capacitacoes = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRHCapacitacao formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_capacitacao").getResposta(),
                    FormRHCapacitacao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoCapacitacao);

            //------------------Itens-------
            item(formulario.getAplicacaoPratica(), capacitacoes);
        }

        //1.1.4 Competências e independência técnica
        HashMap<String, Integer> avaliacaoCompetencias = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> competencias = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRHCompetencias formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_competencias").getResposta(),
                    FormRHCompetencias.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoCompetencias);

            //------------------Itens-------
            item(formulario.getCompetencias(), competencias);
        }

        return div(
                h4(attrs(".titulo-form"), "1.1 RECURSOS HUMANOS"),
                h4(attrs(".titulo-form"), "1.1.1 Quantitativo, jornada e distribuição"),
                tabelaAvaliacao("Avaliação", "rh_av_quantitativo", avaliacaoQuantitativo),
                div(attrs(".col-md-6")).withId("grafico_rh_av_quantitativo"),
                //----------------------
                table(attrs(".table .table-condensed .table-striped"),
                        caption(join("Distribuição dos ", b("Médicos Veterinários"), " quanto à quantidade, tipo de vínculo e capacitação técnica")),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "AGED"),
                                        td(attrs(".text-center"), "INAGRO"),
                                        td(attrs(".text-center"), "OUTROS"),
                                        td(attrs(".text-center"), "TOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "Quantidade"),
                                        td(attrs(".text-center"), veterinario.get("existenteAGED").toString()).withId("vetQEAGED"),
                                        td(attrs(".text-center"), veterinario.get("existenteINAGRO").toString()).withId("vetQEINAGRO"),
                                        td(attrs(".text-center"), veterinario.get("existenteOUTROS").toString()).withId("vetQEOUTROS"),
                                        td(attrs(".text-center"), veterinario.get("existenteTOTAL").toString()).withId("vetQETOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "TREINAMENTO"),
                                        td(attrs(".text-center"), veterinario.get("treinamento").toString()).attr("colspan", 4).withId("vetCT")
                                ),
                                tr(
                                        td(attrs(".text-center"), "COM PÓS"),
                                        td(attrs(".text-center"), veterinario.get("posgraduacao").toString()).attr("colspan", 4).withId("vetCP")
                                )
                        )),
                div(attrs(".col-md-6")).withId("grafico_vet_vinculo"),
                div(attrs(".col-md-6")).withId("grafico_vet_capacitacao"),
                //----------------------
                table(attrs(".table .table-condensed .table-striped"),
                        caption(join("Distribuição dos ", b("Engenheiro Agronomo"), " quanto à quantidade, tipo de vínculo e capacitação técnica")),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "AGED"),
                                        td(attrs(".text-center"), "INAGRO"),
                                        td(attrs(".text-center"), "OUTROS"),
                                        td(attrs(".text-center"), "TOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "Quantidade"),
                                        td(attrs(".text-center"), agronomo.get("existenteAGED").toString()).withId("agroQEAGED"),
                                        td(attrs(".text-center"), agronomo.get("existenteINAGRO").toString()).withId("agroQEINAGRO"),
                                        td(attrs(".text-center"), agronomo.get("existenteOUTROS").toString()).withId("agroQEOUTROS"),
                                        td(attrs(".text-center"), agronomo.get("existenteTOTAL").toString()).withId("agroQETOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "TREINAMENTO"),
                                        td(attrs(".text-center"), agronomo.get("treinamento").toString()).attr("colspan", 4).withId("agroCT")
                                ),
                                tr(
                                        td(attrs(".text-center"), "COM PÓS"),
                                        td(attrs(".text-center"), agronomo.get("posgraduacao").toString()).attr("colspan", 4).withId("agroCP")
                                )
                        )),
                div(attrs(".col-md-6")).withId("grafico_agro_vinculo"),
                div(attrs(".col-md-6")).withId("grafico_agro_capacitacao"),
                //----------------------
                table(attrs(".table .table-condensed .table-striped"),
                        caption(join("Distribuição dos ", b("Engenheiro Florestal"), " quanto à quantidade, tipo de vínculo e capacitação técnica")),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "AGED"),
                                        td(attrs(".text-center"), "INAGRO"),
                                        td(attrs(".text-center"), "OUTROS"),
                                        td(attrs(".text-center"), "TOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "Quantidade"),
                                        td(attrs(".text-center"), florestal.get("existenteAGED").toString()).withId("florQEAGED"),
                                        td(attrs(".text-center"), florestal.get("existenteINAGRO").toString()).withId("florQEINAGRO"),
                                        td(attrs(".text-center"), florestal.get("existenteOUTROS").toString()).withId("florQEOUTROS"),
                                        td(attrs(".text-center"), florestal.get("existenteTOTAL").toString()).withId("florQETOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "TREINAMENTO"),
                                        td(attrs(".text-center"), florestal.get("treinamento").toString()).attr("colspan", 4).withId("florCT")
                                ),
                                tr(
                                        td(attrs(".text-center"), "COM PÓS"),
                                        td(attrs(".text-center"), florestal.get("posgraduacao").toString()).attr("colspan", 4).withId("florCP")
                                )
                        )),
                div(attrs(".col-md-6")).withId("grafico_flor_vinculo"),
                div(attrs(".col-md-6")).withId("grafico_flor_capacitacao"),
                //----------------------
                table(attrs(".table .table-condensed .table-striped"),
                        caption(join("Distribuição dos ", b("Zootecnista"), " quanto à quantidade, tipo de vínculo e capacitação técnica")),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "AGED"),
                                        td(attrs(".text-center"), "INAGRO"),
                                        td(attrs(".text-center"), "OUTROS"),
                                        td(attrs(".text-center"), "TOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "Quantidade"),
                                        td(attrs(".text-center"), zootecnista.get("existenteAGED").toString()).withId("zooQEAGED"),
                                        td(attrs(".text-center"), zootecnista.get("existenteINAGRO").toString()).withId("zooQEINAGRO"),
                                        td(attrs(".text-center"), zootecnista.get("existenteOUTROS").toString()).withId("zooQEOUTROS"),
                                        td(attrs(".text-center"), zootecnista.get("existenteTOTAL").toString()).withId("zooQETOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "TREINAMENTO"),
                                        td(attrs(".text-center"), zootecnista.get("treinamento").toString()).attr("colspan", 4).withId("zooCT")
                                ),
                                tr(
                                        td(attrs(".text-center"), "COM PÓS"),
                                        td(attrs(".text-center"), zootecnista.get("posgraduacao").toString()).attr("colspan", 4).withId("zooCP")
                                )
                        )),
                div(attrs(".col-md-6")).withId("grafico_zoo_vinculo"),
                div(attrs(".col-md-6")).withId("grafico_zoo_capacitacao"),
                //----------------------
                table(attrs(".table .table-condensed .table-striped"),
                        caption(join("Distribuição dos ", b("Técnico em Agropecuária"), " quanto à quantidade, tipo de vínculo e capacitação técnica")),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "AGED"),
                                        td(attrs(".text-center"), "INAGRO"),
                                        td(attrs(".text-center"), "OUTROS"),
                                        td(attrs(".text-center"), "TOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "Quantidade"),
                                        td(attrs(".text-center"), tecagropecuaria.get("existenteAGED").toString()).withId("tecAgroQEAGED"),
                                        td(attrs(".text-center"), tecagropecuaria.get("existenteINAGRO").toString()).withId("tecAgroQEINAGRO"),
                                        td(attrs(".text-center"), tecagropecuaria.get("existenteOUTROS").toString()).withId("tecAgroQEOUTROS"),
                                        td(attrs(".text-center"), tecagropecuaria.get("existenteTOTAL").toString()).withId("tecAgroQETOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "TREINAMENTO"),
                                        td(attrs(".text-center"), tecagropecuaria.get("treinamento").toString()).attr("colspan", 4).withId("tecAgroCT")
                                )
                        )),
                div(attrs(".col-md-6")).withId("grafico_tecAgro_vinculo"),
                div(attrs(".col-md-6")).withId("grafico_tecAgro_capacitacao"),
                //----------------------
                table(attrs(".table .table-condensed .table-striped"),
                        caption(join("Distribuição dos ", b("Técnico Administrativo"), " quanto à quantidade, tipo de vínculo e capacitação técnica")),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "AGED"),
                                        td(attrs(".text-center"), "INAGRO"),
                                        td(attrs(".text-center"), "OUTROS"),
                                        td(attrs(".text-center"), "TOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "Quantidade"),
                                        td(attrs(".text-center"), tecadministrativo.get("existenteAGED").toString()).withId("tecAdmQEAGED"),
                                        td(attrs(".text-center"), tecadministrativo.get("existenteINAGRO").toString()).withId("tecAdmQEINAGRO"),
                                        td(attrs(".text-center"), tecadministrativo.get("existenteOUTROS").toString()).withId("tecAdmQEOUTROS"),
                                        td(attrs(".text-center"), tecadministrativo.get("existenteTOTAL").toString()).withId("tecAdmQETOTAL")
                                ),
                                tr(
                                        td(attrs(".text-center"), "TREINAMENTO"),
                                        td(attrs(".text-center"), tecadministrativo.get("treinamento").toString()).attr("colspan", 4).withId("tecAdmCT")
                                )
                        )),
                div(attrs(".col-md-6")).withId("grafico_tecAdm_vinculo"),
                div(attrs(".col-md-6")).withId("grafico_tecAdm_capacitacao"),

                div(attrs(".col-md-6"), tabelaItem("Quantidade atende a demanda?", "rh_atende_demanda", atendeDemanda)),
                div(attrs(".col-md-6")).withId("grafico_rh_atende_demanda"),
                
                //----------------------
                h4(attrs(".titulo-form"), "1.1.2 Estabilidade das estruturas e sustentabilidade das políticas sanitárias"),
                tabelaAvaliacao("Avaliação", "rh_av_estabilidade", avaliacaoEstabilidade),
                div(attrs(".col-md-6")).withId("grafico_rh_av_estabilidade"),
                
                div(attrs(".col-md-6"), tabelaItem("Existe evidências de estabilidade do quadro funcional?", "rh_estabilidade", estabilidade)),
                div(attrs(".col-md-6")).withId("grafico_rh_estabilidade"),
                //----------------------
                h4(attrs(".titulo-form"), "1.1.3 Capacitação técnica e formação continuada"),
                tabelaAvaliacao("Avaliação", "rh_av_capacitacao", avaliacaoCapacitacao),
                div(attrs(".col-md-6")).withId("grafico_rh_av_capacitacao"),
                
                div(attrs(".col-md-6"), tabelaItem("Aplicação prática das capacitações na rotina de trabalho", "rh_capacitacao", capacitacoes)),
                div(attrs(".col-md-6")).withId("grafico_rh_capacitacao"),
                //----------------------
                h4(attrs(".titulo-form"), "1.1.4 Competências e independência técnica"),
                tabelaAvaliacao("Avaliação", "rh_av_competencias", avaliacaoCompetencias),
                div(attrs(".col-md-6")).withId("grafico_rh_av_competencias"),
                
                div(attrs(".col-md-6"), tabelaItem("Competências e independência técnica", "rh_competencias", competencias))
                ,div(attrs(".col-md-6")).withId("grafico_rh_competencias")
        );

    }

    public Tag RecursosFisicos(List<Supervisao> list) {

        //1.2.1 – INSTALAÇÕES
        HashMap<String, Integer> avaliacaoInstalacoes = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> instalacoesNatureza = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesLimpeza = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesConservacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesBanheiro = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesAtendimento = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesVeterinario = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesPublico = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesExterna = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesInterna = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesDemanda = new HashMap<>(hashPadrao);
        HashMap<String, Integer> instalacoesMobiliario = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRFInstalacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_instalacoes").getResposta(), FormRFInstalacoes.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoInstalacoes);

            //------------------Itens-------
            item(formulario.getNaturezaPredio(), instalacoesNatureza);
            item(formulario.getLimpeza(), instalacoesLimpeza);
            item(formulario.getConservacao(), instalacoesConservacao);
            item(formulario.getBanheiro(), instalacoesBanheiro);
            item(formulario.getSalaAtendimento(), instalacoesAtendimento);
            item(formulario.getSalaVeterinario(), instalacoesVeterinario);
            item(formulario.getCondicoesAtendimento(), instalacoesPublico);
            item(formulario.getApresentacaoExterna(), instalacoesExterna);
            item(formulario.getOrganizacaoInterna(), instalacoesInterna);
            item(formulario.getAtendeDemanda(), instalacoesDemanda);
            item(formulario.getRelacaoMobiliario(), instalacoesMobiliario);
        }

        //1.2.2 – TRANSPORTES
        HashMap<String, Integer> avaliacaoTransportes = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> transporteConservacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> transporteDemanda = new HashMap<>(hashPadrao);
        HashMap<String, Integer> transportePreventiva = new HashMap<>(hashPadrao);
        HashMap<String, Integer> transporteEmergencial = new HashMap<>(hashPadrao);
        HashMap<String, Integer> transporteMapaKM = new HashMap<>(hashPadrao);
        HashMap<String, Integer> transporteCartao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> transporteCondutores = new HashMap<>(hashPadrao);
        HashMap<String, Integer> transporteRelacaoVeiculos = new HashMap<>(hashPadrao);
        HashMap<String, Integer> transporteFrotaAdequada = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRFTransportes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_transportes").getResposta(),
                    FormRFTransportes.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoTransportes);

            //------------------Itens-------
            //para cada transporte da supervisao Conservacao
            for (Transporte transporte : formulario.getTransportes()) {
                item(transporte.getConservacao(), transporteConservacao);
                item(transporte.getAtendeDemanda(), transporteDemanda);
                item(transporte.getManutencaoPreventiva(), transportePreventiva);
                item(transporte.getManutencaoEmergencial(), transporteEmergencial);
                item(transporte.getRegistroKM(), transporteMapaKM);
                item(transporte.getCartaoAbastecimento(), transporteCartao);
            }

            item(formulario.getCondutoresVeiculo(), transporteCondutores);
            item(formulario.getRelacaoVeiculos(), transporteRelacaoVeiculos);
            item(formulario.getFrotaAdequada(), transporteFrotaAdequada);
        }

        //---------1.2.3 –  Equipamentos e acesso à comunicação-----
        HashMap<String, Integer> avaliacaoEquipamentos = new HashMap<>(hashAvaliacaoPadrao);

        HashMap<String, Integer> equipamentoSuficiente = new HashMap<>(hashPadrao);
        HashMap<String, Integer> equipamentoCondicoesUso = new HashMap<>(hashPadrao);
        HashMap<String, Integer> equipamentoAcessoInternet = new HashMap<>(hashPadrao);
        HashMap<String, Integer> equipamentoTelefone = new HashMap<>(hashPadrao);
        HashMap<String, Integer> equipamentoDemais = new HashMap<>(hashPadrao);
        HashMap<String, Integer> equipamentoFluxoInformacao = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRFEquipamentos formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_equipamentos").getResposta(),
                    FormRFEquipamentos.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoEquipamentos);

            //------------------Itens-------
            //para cada transporte da supervisao Conservacao
            for (Municipio municipio : formulario.getMunicipios()) {
                item(municipio.getSuficiente(), equipamentoSuficiente);
                item(municipio.getCondicoes(), equipamentoCondicoesUso);
                item(municipio.getInternet(), equipamentoAcessoInternet);
                item(municipio.getTelefone(), equipamentoTelefone);
                item(municipio.getDemaisEquipamentos(), equipamentoDemais);
            }

            item(formulario.getFluxo(), equipamentoFluxoInformacao);

        }

        //----------------1.2.4 – Sistemas de informação
        HashMap<String, Integer> avaliacaoSistemas = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> equipamentoSistema = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRFSistemasInformacao formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_sistemas").getResposta(),
                    FormRFSistemasInformacao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoSistemas);

            //------------------Itens-------
            item(formulario.getSistemas(), equipamentoSistema);

        }

        return div(
                h4(attrs(".titulo-form"), "1.2 RECURSOS FÍSICOS"),
                h4(attrs(".titulo-form"), "1.2.1 Instalações"),
                tabelaAvaliacao("Avaliação", "rf_av_instalacoes", avaliacaoInstalacoes),
                div(attrs(".col-md-6")).withId("grafico_rf_av_instalacoes"),
                table(attrs(".table .table-condensed"),
                        caption("Instalações"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " ").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Natureza do prédio").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Manutenção").attr("colspan", 2),
                                        td(attrs(".text-center"), "Instalações").attr("colspan", 3),
                                        td(attrs(".text-center"), "Condições de atendimento ao público").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Apresentação externa").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Organização interna (impressão visual)").attr("rowspan", 2)
                                ),
                                tr(
                                        td(attrs(".text-center"), "Limpeza "),
                                        td(attrs(".text-center"), "Estrutura"),
                                        td(attrs(".text-center"), "Banheiro"),
                                        td(attrs(".text-center"), "Sala de Atendimento"),
                                        td(attrs(".text-center"), "Sala p/ veterinpário")
                                ),
                                tr(
                                        td(attrs(".text-center"), "EA"),
                                        td(attrs(".text-center"), instalacoesNatureza.get("EA").toString()).withId("naturezaEA"),
                                        td(attrs(".text-center"), instalacoesLimpeza.get("EA").toString()).withId("limpezaEA"),
                                        td(attrs(".text-center"), instalacoesConservacao.get("EA").toString()).withId("conservacaoEA"),
                                        td(attrs(".text-center"), instalacoesBanheiro.get("EA").toString()).withId("banheiroEA"),
                                        td(attrs(".text-center"), instalacoesAtendimento.get("EA").toString()).withId("atendimentoEA"),
                                        td(attrs(".text-center"), instalacoesVeterinario.get("EA").toString()).withId("veterinarioEA"),
                                        td(attrs(".text-center"), instalacoesPublico.get("EA").toString()).withId("publicoEA"),
                                        td(attrs(".text-center"), instalacoesExterna.get("EA").toString()).withId("externoEA"),
                                        td(attrs(".text-center"), instalacoesInterna.get("EA").toString()).withId("internoEA")
                                ),
                                tr(
                                        td(attrs(".text-center"), "ED"),
                                        td(attrs(".text-center"), instalacoesNatureza.get("ED").toString()).withId("naturezaED"),
                                        td(attrs(".text-center"), instalacoesLimpeza.get("ED").toString()).withId("limpezaED"),
                                        td(attrs(".text-center"), instalacoesConservacao.get("ED").toString()).withId("conservacaoED"),
                                        td(attrs(".text-center"), instalacoesBanheiro.get("ED").toString()).withId("banheiroED"),
                                        td(attrs(".text-center"), instalacoesAtendimento.get("ED").toString()).withId("atendimentoED"),
                                        td(attrs(".text-center"), instalacoesVeterinario.get("ED").toString()).withId("veterinarioED"),
                                        td(attrs(".text-center"), instalacoesPublico.get("ED").toString()).withId("publicoED"),
                                        td(attrs(".text-center"), instalacoesExterna.get("ED").toString()).withId("externoED"),
                                        td(attrs(".text-center"), instalacoesInterna.get("ED").toString()).withId("internoED")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NE"),
                                        td(attrs(".text-center"), instalacoesNatureza.get("NE").toString()).withId("naturezaNE"),
                                        td(attrs(".text-center"), instalacoesLimpeza.get("NE").toString()).withId("limpezaNE"),
                                        td(attrs(".text-center"), instalacoesConservacao.get("NE").toString()).withId("conservacaoNE"),
                                        td(attrs(".text-center"), instalacoesBanheiro.get("NE").toString()).withId("banheiroNE"),
                                        td(attrs(".text-center"), instalacoesAtendimento.get("NE").toString()).withId("atendimentoNE"),
                                        td(attrs(".text-center"), instalacoesVeterinario.get("NE").toString()).withId("veterinarioNE"),
                                        td(attrs(".text-center"), instalacoesPublico.get("NE").toString()).withId("publicoNE"),
                                        td(attrs(".text-center"), instalacoesExterna.get("NE").toString()).withId("externoNE"),
                                        td(attrs(".text-center"), instalacoesInterna.get("NE").toString()).withId("internoNE")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NA"),
                                        td(attrs(".text-center"), instalacoesNatureza.get("NA").toString()).withId("naturezaNA"),
                                        td(attrs(".text-center"), instalacoesLimpeza.get("NA").toString()).withId("limpezaNA"),
                                        td(attrs(".text-center"), instalacoesConservacao.get("NA").toString()).withId("conservacaoNA"),
                                        td(attrs(".text-center"), instalacoesBanheiro.get("NA").toString()).withId("banheiroNA"),
                                        td(attrs(".text-center"), instalacoesAtendimento.get("NA").toString()).withId("atendimentoNA"),
                                        td(attrs(".text-center"), instalacoesVeterinario.get("NA").toString()).withId("veterinarioNA"),
                                        td(attrs(".text-center"), instalacoesPublico.get("NA").toString()).withId("publicoNA"),
                                        td(attrs(".text-center"), instalacoesExterna.get("NA").toString()).withId("externoNA"),
                                        td(attrs(".text-center"), instalacoesInterna.get("NA").toString()).withId("internoNA")
                                )
                        )),
                div(attrs(".col-md-12")).withId("grafico_rf_instalacoes"),
                
                div(attrs(".col-md-6"), tabelaItem("De modo geral o mobiliário atende a demanda?", "rf_demanda", instalacoesDemanda)),
                div(attrs(".col-md-6")).withId("grafico_rf_demanda"),
                
                div(attrs(".col-md-6"), tabelaItem("Há uma relação do mobiliário existente por EAC", "rf_mobiliario_relacao", instalacoesMobiliario)),
                div(attrs(".col-md-6")).withId("grafico_rf_mobiliario_relacao"),
                
                h4(attrs(".titulo-form"), "1.2.2 Transportes"),
                tabelaAvaliacao("Avaliação", "rf_av_transportes", avaliacaoTransportes),
                div(attrs(".col-md-6")).withId("grafico_rf_av_transportes"),
                table(attrs(".table .table-condensed"),
                        caption("Transportes"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "Estado de conservação"),
                                        td(attrs(".text-center"), "Atende a demanda?"),
                                        td(attrs(".text-center"), "Manutenção preventiva"),
                                        td(attrs(".text-center"), "Manutenção emergencial"),
                                        td(attrs(".text-center"), "Mapa KM"),
                                        td(attrs(".text-center"), "Cartão de abastecimento"),
                                        td(attrs(".text-center"), "Condutores para veículos"),
                                        td(attrs(".text-center"), "Relação dos veículos e a lotação?"),
                                        td(attrs(".text-center"), "Frota adequada?")
                                ),
                                tr(
                                        td(attrs(".text-center"), "EA"),
                                        td(attrs(".text-center"), transporteConservacao.get("EA").toString()).withId("trasnporteConservacaoEA"),
                                        td(attrs(".text-center"), transporteDemanda.get("EA").toString()).withId("demandaEA"),
                                        td(attrs(".text-center"), transportePreventiva.get("EA").toString()).withId("preventivaEA"),
                                        td(attrs(".text-center"), transporteEmergencial.get("EA").toString()).withId("emergencialEA"),
                                        td(attrs(".text-center"), transporteMapaKM.get("EA").toString()).withId("mapaKmEA"),
                                        td(attrs(".text-center"), transporteCartao.get("EA").toString()).withId("cartaoEA"),
                                        td(attrs(".text-center"), transporteCondutores.get("EA").toString()).withId("condutoresEA"),
                                        td(attrs(".text-center"), transporteRelacaoVeiculos.get("EA").toString()).withId("relacaoVeiculosEA"),
                                        td(attrs(".text-center"), transporteFrotaAdequada.get("EA").toString()).withId("frotaAdequadaEA")
                                ),
                                tr(
                                        td(attrs(".text-center"), "ED"),
                                        td(attrs(".text-center"), transporteConservacao.get("ED").toString()).withId("trasnporteConservacaoED"),
                                        td(attrs(".text-center"), transporteDemanda.get("ED").toString()).withId("demandaED"),
                                        td(attrs(".text-center"), transportePreventiva.get("ED").toString()).withId("preventivaED"),
                                        td(attrs(".text-center"), transporteEmergencial.get("ED").toString()).withId("emergencialED"),
                                        td(attrs(".text-center"), transporteMapaKM.get("ED").toString()).withId("mapaKmED"),
                                        td(attrs(".text-center"), transporteCartao.get("ED").toString()).withId("cartaoED"),
                                        td(attrs(".text-center"), transporteCondutores.get("ED").toString()).withId("condutoresED"),
                                        td(attrs(".text-center"), transporteRelacaoVeiculos.get("ED").toString()).withId("relacaoVeiculosED"),
                                        td(attrs(".text-center"), transporteFrotaAdequada.get("ED").toString()).withId("frotaAdequadaED")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NE"),
                                        td(attrs(".text-center"), transporteConservacao.get("NE").toString()).withId("trasnporteConservacaoNE"),
                                        td(attrs(".text-center"), transporteDemanda.get("NE").toString()).withId("demandaNE"),
                                        td(attrs(".text-center"), transportePreventiva.get("NE").toString()).withId("preventivaNE"),
                                        td(attrs(".text-center"), transporteEmergencial.get("NE").toString()).withId("emergencialNE"),
                                        td(attrs(".text-center"), transporteMapaKM.get("NE").toString()).withId("mapaKmNE"),
                                        td(attrs(".text-center"), transporteCartao.get("NE").toString()).withId("cartaoNE"),
                                        td(attrs(".text-center"), transporteCondutores.get("NE").toString()).withId("condutoresNE"),
                                        td(attrs(".text-center"), transporteRelacaoVeiculos.get("NE").toString()).withId("relacaoVeiculosNE"),
                                        td(attrs(".text-center"), transporteFrotaAdequada.get("NE").toString()).withId("frotaAdequadaNE")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NA"),
                                        td(attrs(".text-center"), transporteConservacao.get("NA").toString()).withId("trasnporteConservacaoNA"),
                                        td(attrs(".text-center"), transporteDemanda.get("NA").toString()).withId("demandaNA"),
                                        td(attrs(".text-center"), transportePreventiva.get("NA").toString()).withId("preventivaNA"),
                                        td(attrs(".text-center"), transporteEmergencial.get("NA").toString()).withId("emergencialNA"),
                                        td(attrs(".text-center"), transporteMapaKM.get("NA").toString()).withId("mapaKmNA"),
                                        td(attrs(".text-center"), transporteCartao.get("NA").toString()).withId("cartaoNA"),
                                        td(attrs(".text-center"), transporteCondutores.get("NA").toString()).withId("condutoresNA"),
                                        td(attrs(".text-center"), transporteRelacaoVeiculos.get("NA").toString()).withId("relacaoVeiculosNA"),
                                        td(attrs(".text-center"), transporteFrotaAdequada.get("NA").toString()).withId("frotaAdequadaNA")
                                )
                        )),
                div(attrs(".col-md-6")).withId("grafico_rf_transportes"),
                
                h4(attrs(".titulo-form"), "1.2.3 –  Equipamentos e acesso à comunicação"),
                tabelaAvaliacao("Avaliação", "rf_av_equipamentos", avaliacaoEquipamentos),
                div(attrs(".col-md-6")).withId("grafico_rf_av_equipamentos"),
                table(attrs(".table .table-condensed"),
                        caption("Equipamentos e acesso à comunicação"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " ").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Computadores").attr("colspan", 2),
                                        td(attrs(".text-center"), "Acesso a internet").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Linha Telefônica").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Demais equipamentos").attr("rowspan", 2)
                                ),
                                tr(
                                        td(attrs(".text-center"), "Suficiente?"),
                                        td(attrs(".text-center"), "Condições de uso")
                                ),
                                tr(
                                        td(attrs(".text-center"), "EA"),
                                        td(attrs(".text-center"), equipamentoSuficiente.get("EA").toString()).withId("suficienteEA"),
                                        td(attrs(".text-center"), equipamentoCondicoesUso.get("EA").toString()).withId("condicoesEquipamentoEA"),
                                        td(attrs(".text-center"), equipamentoAcessoInternet.get("EA").toString()).withId("acessoInternetEA"),
                                        td(attrs(".text-center"), equipamentoTelefone.get("EA").toString()).withId("telefoneEA"),
                                        td(attrs(".text-center"), equipamentoDemais.get("EA").toString()).withId("demaisEquipamentosEA")
                                ),
                                tr(
                                        td(attrs(".text-center"), "ED"),
                                        td(attrs(".text-center"), equipamentoSuficiente.get("ED").toString()).withId("suficienteED"),
                                        td(attrs(".text-center"), equipamentoCondicoesUso.get("ED").toString()).withId("condicoesEquipamentoED"),
                                        td(attrs(".text-center"), equipamentoAcessoInternet.get("ED").toString()).withId("acessoInternetED"),
                                        td(attrs(".text-center"), equipamentoTelefone.get("ED").toString()).withId("telefoneED"),
                                        td(attrs(".text-center"), equipamentoDemais.get("ED").toString()).withId("demaisEquipamentosED")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NE"),
                                        td(attrs(".text-center"), equipamentoSuficiente.get("NE").toString()).withId("suficienteNE"),
                                        td(attrs(".text-center"), equipamentoCondicoesUso.get("NE").toString()).withId("condicoesEquipamentoNE"),
                                        td(attrs(".text-center"), equipamentoAcessoInternet.get("NE").toString()).withId("acessoInternetNE"),
                                        td(attrs(".text-center"), equipamentoTelefone.get("NE").toString()).withId("telefoneNE"),
                                        td(attrs(".text-center"), equipamentoDemais.get("NE").toString()).withId("demaisEquipamentosNE")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NA"),
                                        td(attrs(".text-center"), equipamentoSuficiente.get("NA").toString()).withId("suficienteNA"),
                                        td(attrs(".text-center"), equipamentoCondicoesUso.get("NA").toString()).withId("condicoesEquipamentoNA"),
                                        td(attrs(".text-center"), equipamentoAcessoInternet.get("NA").toString()).withId("acessoInternetNA"),
                                        td(attrs(".text-center"), equipamentoTelefone.get("NA").toString()).withId("telefoneNA"),
                                        td(attrs(".text-center"), equipamentoDemais.get("NA").toString()).withId("demaisEquipamentosNA")
                                )
                        )),
                div(attrs(".col-md-6")).withId("grafico_rf_equipamentos"),
                
                h4(attrs(".titulo-form"), "1.2.4 – Sistemas de informação"),
                tabelaAvaliacao("Avaliação", "rf_av_sistemas", avaliacaoSistemas),
                div(attrs(".col-md-6")).withId("grafico_rf_av_sistemas"),
                
                div(attrs(".col-md-6"), tabelaItem("Sistemas de informação", "rf_sistemas", equipamentoSistema))
                ,div(attrs(".col-md-6")).withId("grafico_rf_sistemas")
        );

    }

    public Tag RecursosFinanceiros(List<Supervisao> list) {

        //1.3.1 – RECURSOS PARA CUSTEIO                
        HashMap<String, Integer> avaliacaoCusteio = new HashMap<>(hashAvaliacaoPadrao);

        HashMap<String, Integer> financeiroDiarias = new HashMap<>(hashPadrao);
        HashMap<String, Integer> financeiroFAI = new HashMap<>(hashPadrao);
        HashMap<String, Integer> financeiroRelatorioKm = new HashMap<>(hashPadrao);
        HashMap<String, Integer> financeiroViagem = new HashMap<>(hashPadrao);
        HashMap<String, Integer> financeiroRecursos = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRFICusteio formulario = gson.fromJson(supervisao.getHashRespostas().get("rfi_custeio").getResposta(), FormRFICusteio.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoCusteio);

            //------------------Itens-------
            item(formulario.getSolicitacaoDiaria(), financeiroDiarias);
            item(formulario.getFAI(), financeiroFAI);
            item(formulario.getRelatorioKM(), financeiroRelatorioKm);
            item(formulario.getRelatorioViagem(), financeiroViagem);
            item(formulario.getDisponibilidadeFinanceiro(), financeiroRecursos);
        }

        //1.3.2 – ARRECADAÇÃO             
        HashMap<String, Integer> avaliacaoArrecadacao = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> controleMensal = new HashMap<>(hashPadrao);
        HashMap<String, Integer> autosInfracao = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormRFIArrecadacao formulario = gson.fromJson(supervisao.getHashRespostas().get("rfi_arrecadacao").getResposta(), FormRFIArrecadacao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoArrecadacao);

            //------------------Itens-------
            item(formulario.getControleMensal(), controleMensal);
            item(formulario.getAutosInfracao(), autosInfracao);
        }

        return div(
                h4(attrs(".titulo-form"), "1.3 RECURSOS FINANCEIROS"),
                h4(attrs(".titulo-form"), "1.3.1 Recursos para custeio"),
                tabelaAvaliacao("Avaliação", "rfi_av_custeio", avaliacaoCusteio),
                div(attrs(".col-md-6")).withId("grafico_rfi_av_custeio"),
                
                table(attrs(".table .table-condensed"),
                        caption("Recursos para custeio"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " ").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Solicitação de diárias").attr("rowspan", 2),
                                        td(attrs(".text-center"), "Planejamento x Comprovação da ação").attr("colspan", 3),
                                        td(attrs(".text-center"), "Disponibilidade de recurso financeiro").attr("rowspan", 2)
                                ),
                                tr(
                                        td(attrs(".text-center"), "FAI"),
                                        td(attrs(".text-center"), "Relatório de KM"),
                                        td(attrs(".text-center"), "Relatório de viagem")
                                ),
                                tr(
                                        td(attrs(".text-center"), "EA"),
                                        td(attrs(".text-center"), financeiroDiarias.get("EA").toString()).withId("diariasEA"),
                                        td(attrs(".text-center"), financeiroFAI.get("EA").toString()).withId("faiEA"),
                                        td(attrs(".text-center"), financeiroRelatorioKm.get("EA").toString()).withId("relatorioKmEA"),
                                        td(attrs(".text-center"), financeiroViagem.get("EA").toString()).withId("relatorioViagemEA"),
                                        td(attrs(".text-center"), financeiroRecursos.get("EA").toString()).withId("recursosEA")
                                ),
                                tr(
                                        td(attrs(".text-center"), "ED"),
                                        td(attrs(".text-center"), financeiroDiarias.get("ED").toString()).withId("diariasED"),
                                        td(attrs(".text-center"), financeiroFAI.get("ED").toString()).withId("faiED"),
                                        td(attrs(".text-center"), financeiroRelatorioKm.get("ED").toString()).withId("relatorioKmED"),
                                        td(attrs(".text-center"), financeiroViagem.get("ED").toString()).withId("relatorioViagemED"),
                                        td(attrs(".text-center"), financeiroRecursos.get("ED").toString()).withId("recursosED")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NE"),
                                        td(attrs(".text-center"), financeiroDiarias.get("NE").toString()).withId("diariasNE"),
                                        td(attrs(".text-center"), financeiroFAI.get("NE").toString()).withId("faiNE"),
                                        td(attrs(".text-center"), financeiroRelatorioKm.get("NE").toString()).withId("relatorioKmNE"),
                                        td(attrs(".text-center"), financeiroViagem.get("NE").toString()).withId("relatorioViagemNE"),
                                        td(attrs(".text-center"), financeiroRecursos.get("NE").toString()).withId("recursosNE")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NA"),
                                        td(attrs(".text-center"), financeiroDiarias.get("NA").toString()).withId("diariasNA"),
                                        td(attrs(".text-center"), financeiroFAI.get("NA").toString()).withId("faiNA"),
                                        td(attrs(".text-center"), financeiroRelatorioKm.get("NA").toString()).withId("relatorioKmNA"),
                                        td(attrs(".text-center"), financeiroViagem.get("NA").toString()).withId("relatorioViagemNA"),
                                        td(attrs(".text-center"), financeiroRecursos.get("NA").toString()).withId("recursosNA")
                                )
                        )),
                div(attrs(".col-md-12")).withId("grafico_rfi_custeio"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Arrecadação"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "Controle mensal de arrecadação"),
                                        td(attrs(".text-center"), "Autos de infração")
                                ),
                                tr(
                                        td(attrs(".text-center"), "EA"),
                                        td(attrs(".text-center"), controleMensal.get("EA").toString()).withId("arrecadacaoEA"),
                                        td(attrs(".text-center"), autosInfracao.get("EA").toString()).withId("autosInfracaoEA")
                                ),
                                tr(
                                        td(attrs(".text-center"), "ED"),
                                        td(attrs(".text-center"), controleMensal.get("ED").toString()).withId("arrecadacaoED"),
                                        td(attrs(".text-center"), autosInfracao.get("ED").toString()).withId("autosInfracaoED")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NE"),
                                        td(attrs(".text-center"), controleMensal.get("NE").toString()).withId("arrecadacaoNE"),
                                        td(attrs(".text-center"), autosInfracao.get("NE").toString()).withId("autosInfracaoNE")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NA"),
                                        td(attrs(".text-center"), controleMensal.get("NA").toString()).withId("arrecadacaoNA"),
                                        td(attrs(".text-center"), autosInfracao.get("NA").toString()).withId("autosInfracaoNA")
                                )
                        )))
        ,div(attrs(".col-md-6")).withId("grafico_rfi_arrecadacao")
        );

    }

    public Tag EstruturaOrganizacional(List<Supervisao> list) {

        //2.1.1 Estrutura organizacional e capacidade de coordenação interna                
        HashMap<String, Integer> avaliacaoEstruturaOrganizacional = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> capilaridadeNecessaria = new HashMap<>(hashPadrao);
        HashMap<String, Integer> estruturaOrganizacional = new HashMap<>(hashPadrao);
        HashMap<String, Integer> coordenacaoInterna = new HashMap<>(hashPadrao);
        HashMap<String, Integer> capilaridade = new HashMap<>();

        int qtdMunicipios = 0;
        int qtdEACs = 0;
        int qtdAtendicos = 0;
        int qtdPostos = 0;

        for (Supervisao supervisao : list) {
            FormEOEstrutura formulario = gson.fromJson(supervisao.getHashRespostas().get("eo_estrutura").getResposta(), FormEOEstrutura.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoEstruturaOrganizacional);

            //------------------Itens-------
            qtdMunicipios += Integer.parseInt(formulario.getnMunicipioJurisdicao());
            qtdEACs += Integer.parseInt(formulario.getnEAC());
            qtdAtendicos += Integer.parseInt(formulario.getnMunicipiosAtendidos());
            qtdPostos += Integer.parseInt(formulario.getnPostosFixos());

            capilaridade.put("municipiosJurisdicao", qtdMunicipios);
            capilaridade.put("nEacs", qtdEACs);
            capilaridade.put("municipiosAtendidos", qtdAtendicos);
            capilaridade.put("postosFiscalizacao", qtdPostos);

            item(formulario.getAtendeNecessidade(), capilaridadeNecessaria);
            item(formulario.getEstruturaOrganizacional(), estruturaOrganizacional);
            item(formulario.getCoordInterna(), coordenacaoInterna);
        }

        return div(
                h4(attrs(".titulo-form"), "2.1 ESTRUTURA ORGANIZACIONAL"),
                h4(attrs(".titulo-form"), "2.1.1 Estrutura organizacional e capacidade de coordenação interna"),
                tabelaAvaliacao("Avaliação", "eo_av_estrutura", avaliacaoEstruturaOrganizacional),
                div(attrs(".col-md-6")).withId("grafico_eo_av_estrutura"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Estrutura organizacional"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), "Municípios da jurisdição"),
                                        td(attrs(".text-center"), "N° de EACs"),
                                        td(attrs(".text-center"), "Municípios Atendidos"),
                                        td(attrs(".text-center"), "Postos de Fiscalização")
                                ),
                                tr(
                                        td(attrs(".text-center"), capilaridade.get("municipiosJurisdicao").toString()).withId("nMunicipioJurisdicao"),
                                        td(attrs(".text-center"), capilaridade.get("nEacs").toString()).withId("nEACs"),
                                        td(attrs(".text-center"), capilaridade.get("municipiosAtendidos").toString()).withId("nMunicipioAtendido"),
                                        td(attrs(".text-center"), capilaridade.get("postosFiscalizacao").toString()).withId("nPostosFiscalizacao")
                                )
                        )))
        ,div(attrs(".col-md-6")).withId("grafico_eo_estrutura")
        );

    }

    public Tag AutoridadeGestao(List<Supervisao> list) {

        //2.2.1 Base legal e aplicação da legislação, manuais e POPs        
        HashMap<String, Integer> avaliacaoBaseLegal = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> legislacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> manuais = new HashMap<>(hashPadrao);
        HashMap<String, Integer> pop = new HashMap<>(hashPadrao);
        HashMap<String, Integer> aplicacaoProcedimentos = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormAGBaseLegal formulario = gson.fromJson(supervisao.getHashRespostas().get("ag_base_legal").getResposta(), FormAGBaseLegal.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoBaseLegal);

            //------------------Itens-------
            item(formulario.getLegislacao(), legislacao);
            item(formulario.getManuais(), manuais);
            item(formulario.getPOP(), pop);
            item(formulario.getAplicacao(), aplicacaoProcedimentos);
        }

        //2.2.2 Organização dos processos e unidades
        HashMap<String, Integer> avaliacaoOrganizacaoProcessos = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> arquivo = new HashMap<>(hashPadrao);
        HashMap<String, Integer> mural = new HashMap<>(hashPadrao);
        HashMap<String, Integer> fluxoInformacoes = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormAGOrganizacao formulario = gson.fromJson(supervisao.getHashRespostas().get("ag_organizacao").getResposta(), FormAGOrganizacao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoOrganizacaoProcessos);

            //------------------Itens-------
            item(formulario.getArquivo(), arquivo);
            item(formulario.getMural(), mural);
            item(formulario.getFluxo(), fluxoInformacoes);
        }

        //2.2.3 Supervisão e controle interno
        HashMap<String, Integer> avaliacaoSupervisaoControle = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> visitaPFFA = new HashMap<>(hashPadrao);
        HashMap<String, Integer> visitaEAC = new HashMap<>(hashPadrao);
        HashMap<String, Integer> supervisoesRecebidas = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormAGSupervisao formulario = gson.fromJson(supervisao.getHashRespostas().get("ag_supervisao").getResposta(), FormAGSupervisao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoSupervisaoControle);

            //------------------Itens-------
            item(formulario.getPFFA(), visitaPFFA);
            item(formulario.getEAC(), visitaEAC);
            item(formulario.getSupervisao(), supervisoesRecebidas);
        }

        return div(
                h4(attrs(".titulo-form"), "2.2 AUTORIDADE E GESTÃO DA QUALIDADE"),
                h4(attrs(".titulo-form"), "2.2.1 Base legal e aplicação da legislação, manuais e POPs"),
                tabelaAvaliacao("Avaliação", "ag_av_base_legal", avaliacaoBaseLegal),
                div(attrs(".col-md-6")).withId("grafico_ag_av_base_legal"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Base legal e aplicação da legislação, manuais e POPs"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "Legislação"),
                                        td(attrs(".text-center"), "Manuais"),
                                        td(attrs(".text-center"), "POPs"),
                                        td(attrs(".text-center"), "Aplicação de procedimentos")
                                ),
                                tr(
                                        td(attrs(".text-center"), "EA"),
                                        td(attrs(".text-center"), legislacao.get("EA").toString()).withId("legislacaoEA"),
                                        td(attrs(".text-center"), manuais.get("EA").toString()).withId("manualEA"),
                                        td(attrs(".text-center"), pop.get("EA").toString()).withId("popEA"),
                                        td(attrs(".text-center"), aplicacaoProcedimentos.get("EA").toString()).withId("procedimentosEA")
                                ),
                                tr(
                                        td(attrs(".text-center"), "ED"),
                                        td(attrs(".text-center"), legislacao.get("ED").toString()).withId("legislacaoED"),
                                        td(attrs(".text-center"), manuais.get("ED").toString()).withId("manualED"),
                                        td(attrs(".text-center"), pop.get("ED").toString()).withId("popED"),
                                        td(attrs(".text-center"), aplicacaoProcedimentos.get("ED").toString()).withId("procedimentosED")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NE"),
                                        td(attrs(".text-center"), legislacao.get("NE").toString()).withId("legislacaoNE"),
                                        td(attrs(".text-center"), manuais.get("NE").toString()).withId("manualNE"),
                                        td(attrs(".text-center"), pop.get("NE").toString()).withId("popNE"),
                                        td(attrs(".text-center"), aplicacaoProcedimentos.get("NE").toString()).withId("procedimentosNE")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NA"),
                                        td(attrs(".text-center"), legislacao.get("NA").toString()).withId("legislacaoNA"),
                                        td(attrs(".text-center"), manuais.get("NA").toString()).withId("manualNA"),
                                        td(attrs(".text-center"), pop.get("NA").toString()).withId("popNA"),
                                        td(attrs(".text-center"), aplicacaoProcedimentos.get("NA").toString()).withId("procedimentosNA")
                                )
                        ))),
                div(attrs(".col-md-6")).withId("grafico_ag_base_legal"),
                
                h4(attrs(".titulo-form"), "2.2.2 Organização dos processos e unidades"),
                tabelaAvaliacao("Avaliação", "ag_av_organizacao", avaliacaoOrganizacaoProcessos),
                div(attrs(".col-md-6")).withId("grafico_ag_av_organizacao"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Organização dos processos e unidades"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "Arquivo"),
                                        td(attrs(".text-center"), "Mural técnico"),
                                        td(attrs(".text-center"), "Fluxo de informações")
                                ),
                                tr(
                                        td(attrs(".text-center"), "EA"),
                                        td(attrs(".text-center"), arquivo.get("EA").toString()).withId("arquivoEA"),
                                        td(attrs(".text-center"), mural.get("EA").toString()).withId("muralEA"),
                                        td(attrs(".text-center"), fluxoInformacoes.get("EA").toString()).withId("fluxoInformacaoEA")
                                ),
                                tr(
                                        td(attrs(".text-center"), "ED"),
                                        td(attrs(".text-center"), arquivo.get("ED").toString()).withId("arquivoED"),
                                        td(attrs(".text-center"), mural.get("ED").toString()).withId("muralED"),
                                        td(attrs(".text-center"), fluxoInformacoes.get("ED").toString()).withId("fluxoInformacaoED")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NE"),
                                        td(attrs(".text-center"), arquivo.get("NE").toString()).withId("arquivoNE"),
                                        td(attrs(".text-center"), mural.get("NE").toString()).withId("muralNE"),
                                        td(attrs(".text-center"), fluxoInformacoes.get("NE").toString()).withId("fluxoInformacaoNE")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NA"),
                                        td(attrs(".text-center"), arquivo.get("NA").toString()).withId("arquivoNA"),
                                        td(attrs(".text-center"), mural.get("NA").toString()).withId("muralNA"),
                                        td(attrs(".text-center"), fluxoInformacoes.get("NA").toString()).withId("fluxoInformacaoNA")
                                )
                        ))),
                div(attrs(".col-md-6")).withId("grafico_ag_organizacao"),
                
                h4(attrs(".titulo-form"), "2.2.3 Supervisão e controle interno"),
                tabelaAvaliacao("Avaliação", "ag_av_supervisao", avaliacaoSupervisaoControle),
                div(attrs(".col-md-6")).withId("grafico_ag_av_supervisao"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Supervisão e controle interno"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), " "),
                                        td(attrs(".text-center"), "Visita técnica PFFA"),
                                        td(attrs(".text-center"), "Visita técnica EAC"),
                                        td(attrs(".text-center"), "Supervisoes Recebidas")
                                ),
                                tr(
                                        td(attrs(".text-center"), "EA"),
                                        td(attrs(".text-center"), visitaPFFA.get("EA").toString()).withId("pffaEA"),
                                        td(attrs(".text-center"), visitaEAC.get("EA").toString()).withId("eacEA"),
                                        td(attrs(".text-center"), supervisoesRecebidas.get("EA").toString()).withId("supervisaoEA")
                                ),
                                tr(
                                        td(attrs(".text-center"), "ED"),
                                        td(attrs(".text-center"), visitaPFFA.get("ED").toString()).withId("pffaED"),
                                        td(attrs(".text-center"), visitaEAC.get("ED").toString()).withId("eacED"),
                                        td(attrs(".text-center"), supervisoesRecebidas.get("ED").toString()).withId("supervisaoED")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NE"),
                                        td(attrs(".text-center"), visitaPFFA.get("NE").toString()).withId("pffaNE"),
                                        td(attrs(".text-center"), visitaEAC.get("NE").toString()).withId("eacNE"),
                                        td(attrs(".text-center"), supervisoesRecebidas.get("NE").toString()).withId("supervisaoNE")
                                ),
                                tr(
                                        td(attrs(".text-center"), "NA"),
                                        td(attrs(".text-center"), visitaPFFA.get("NA").toString()).withId("pffaNA"),
                                        td(attrs(".text-center"), visitaEAC.get("NA").toString()).withId("eacNA"),
                                        td(attrs(".text-center"), supervisoesRecebidas.get("NA").toString()).withId("supervisaoNA")
                                )
                        )))
        ,div(attrs(".col-md-6")).withId("grafico_ag_supervisao")
        );

    }

    public Tag CapacidadeTecnicaOperacional(List<Supervisao> list) {

        //2.3.1 Controle de cadastro de produtores, propriedades e animais            
        HashMap<String, Integer> avaliacaoControleCadastro = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> informacaoProdutores = new HashMap<>(hashPadrao);
        HashMap<String, Integer> documentacaoCadastramento = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentoAbertura = new HashMap<>(hashPadrao);
        HashMap<String, Integer> registroEntrada = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormCTOControleCadastro formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_controle_cadastro").getResposta(), FormCTOControleCadastro.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoControleCadastro);

            //------------------Itens-------
            item(formulario.getInfoProdutoresPropriedades(), informacaoProdutores);
            item(formulario.getDocumentacao(), documentacaoCadastramento);
            item(formulario.getProcedimentos(), procedimentoAbertura);
            item(formulario.getRegistro(), registroEntrada);
        }

        //2.3.2 Planejamento, execução de atividades e registro         
        HashMap<String, Integer> avaliacaoPlanejamentoExecucao = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> planejamentoMensal = new HashMap<>(hashPadrao);
        HashMap<String, Integer> registroAcoes = new HashMap<>(hashPadrao);
        HashMap<String, Integer> cumprimentoMeta = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormCTOPlanejamento formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_planejamento").getResposta(), FormCTOPlanejamento.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoPlanejamentoExecucao);

            //------------------Itens-------
            item(formulario.getPlanejamento(), planejamentoMensal);
            item(formulario.getRegistro(), registroAcoes);
            item(formulario.getCumprimento(), cumprimentoMeta);
        }

        //2.3.3 Controle de divisas e trânsito interno        
        HashMap<String, Integer> avaliacaoControle = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> pontosEstrategicos = new HashMap<>(hashPadrao);
        HashMap<String, Integer> realizacaoMensal = new HashMap<>(hashPadrao);
        HashMap<String, Integer> registroBlitz = new HashMap<>(hashPadrao);
        HashMap<String, Integer> apreensoes = new HashMap<>(hashPadrao);
        HashMap<String, Integer> materialBlitz = new HashMap<>(hashPadrao);
        HashMap<String, Integer> registroOperacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> controle = new HashMap<>();

        int nBlitz = 0;
        int qtdBlitzMes = 0;
        int nVeiculoAnimais = 0;
        int nVeiculoProduto = 0;
        int nVeiculoVazio = 0;
        int nAnimaisInspecionados = 0;

        for (Supervisao supervisao : list) {
            FormCTOControle formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_controle").getResposta(), FormCTOControle.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoControle);

            //------------------Itens-------
            nBlitz += Integer.parseInt(formulario.getnBlitzAno());
            qtdBlitzMes += Integer.parseInt(formulario.getQtdBlitzMes());
            nVeiculoAnimais += Integer.parseInt(formulario.getQtdBlitzMesAnimais());
            nVeiculoProduto += Integer.parseInt(formulario.getQtdBlitzMesProdutos());
            nVeiculoVazio += Integer.parseInt(formulario.getQtdBlitzMesVazio());
            nAnimaisInspecionados += Integer.parseInt(formulario.getQtdBlitzMesInspecionados());

            controle.put("nBlitz", nBlitz);
            controle.put("qtdBlitzMes", qtdBlitzMes);
            controle.put("nVeiculoAnimais", nVeiculoAnimais);
            controle.put("nVeiculoProduto", nVeiculoProduto);
            controle.put("nVeiculoVazio", nVeiculoVazio);
            controle.put("nAnimaisInspecionados", nAnimaisInspecionados);

            item(formulario.getPontosEstrategicos(), pontosEstrategicos);
            item(formulario.getRealizacaoMensal(), realizacaoMensal);
            item(formulario.getRegistroBlitz(), registroBlitz);
            item(formulario.getApreensoes(), apreensoes);
            item(formulario.getMaterialBlitz(), materialBlitz);
            item(formulario.getRegistro(), registroOperacao);
        }

        //2.3.4 Controle de trânsito de animais e produtos de origem animal
        HashMap<String, Integer> avaliacaoControleTransito = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> credenciamentoEmitente = new HashMap<>(hashPadrao);
        HashMap<String, Integer> manualPreenchimento = new HashMap<>(hashPadrao);
        HashMap<String, Integer> emissaoGTA = new HashMap<>(hashPadrao);
        HashMap<String, Integer> exigenciaZoosanitaria = new HashMap<>(hashPadrao);
        HashMap<String, Integer> relatorioEmissao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> graficoMovimentacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> organizacaoGTA = new HashMap<>(hashPadrao);
        HashMap<String, Integer> guiaTransito = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormCTOControleTransitoAnimais formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_transito_animais").getResposta(),
                    FormCTOControleTransitoAnimais.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoControleTransito);

            //------------------Itens-------
            item(formulario.getCredenciamento(), credenciamentoEmitente);
            item(formulario.getManuais(), manualPreenchimento);
            item(formulario.getEmissaoGTA(), emissaoGTA);
            item(formulario.getExigencias(), exigenciaZoosanitaria);
            item(formulario.getRelatorios(), relatorioEmissao);
            item(formulario.getGraficos(), graficoMovimentacao);
            item(formulario.getOrganizacao(), organizacaoGTA);
            item(formulario.getGuias(), guiaTransito);

        }

        //2.3.5 Controle de eventos de aglomeração de animais
        HashMap<String, Integer> avaliacaoControleEventos = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> documentacaoCadastramentoRecinto = new HashMap<>(hashPadrao);
        HashMap<String, Integer> documentacaoEvento = new HashMap<>(hashPadrao);
        HashMap<String, Integer> registroEventos = new HashMap<>(hashPadrao);
        HashMap<String, Integer> inspecaoClinica = new HashMap<>(hashPadrao);
        HashMap<String, Integer> emissaoGTASaida = new HashMap<>(hashPadrao);
        HashMap<String, Integer> controleEventos = new HashMap<>();

        int nRe = 0;
        int nEr = 0;
        int nEf = 0;
        int nAP = 0;
        int nCai = 0;

        for (Supervisao supervisao : list) {
            FormCTOControleEventosAglomeracao formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_controle_eventos").getResposta(), FormCTOControleEventosAglomeracao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoControleEventos);

            //------------------Itens-------
            nRe += Integer.parseInt(formulario.getnRecintos());
            nEr += Integer.parseInt(formulario.getnEventosRealizados());
            nEf += Integer.parseInt(formulario.getnEventosFiscalizados());
            nAP += Integer.parseInt(formulario.getnAnimaisParticipantes());
            nCai += Integer.parseInt(formulario.getnAnimaisInspecionados());

            controleEventos.put("nRecintoAglomeracao", nRe);
            controleEventos.put("nEventosRealizados", nEr);
            controleEventos.put("nEventosFiscalizados", nEf);
            controleEventos.put("nAnimaisParticipantes", nAP);
            controleEventos.put("nControleAnimaisInspecionados", nCai);

            item(formulario.getDocCadastramento(), documentacaoCadastramentoRecinto);
            item(formulario.getDocRealizacao(), documentacaoEvento);
            item(formulario.getRegistro(), registroEventos);
            item(formulario.getInspecao(), inspecaoClinica);
            item(formulario.getGTASaida(), emissaoGTASaida);
        }

        //2.3.6 Fiscalização em revendas veterinárias
        HashMap<String, Integer> avaliacaoFiscalizacaoRevenda = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> cadastramentoRevendas = new HashMap<>(hashPadrao);
        HashMap<String, Integer> frequenciaFiscalizacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> fiscalizacaoDiaria = new HashMap<>(hashPadrao);
        HashMap<String, Integer> fiscalizacaoSemanal = new HashMap<>(hashPadrao);
        HashMap<String, Integer> supervisaoRevenda = new HashMap<>(hashPadrao);
        HashMap<String, Integer> apreensaoVacina = new HashMap<>(hashPadrao);
        HashMap<String, Integer> revendas = new HashMap<>();

        int nTotal = 0;
        int nComercializam = 0;
        int nFiscalizadas = 0;

        for (Supervisao supervisao : list) {
            FormCTOFiscalizacao formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_fiscalizacao").getResposta(), FormCTOFiscalizacao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoFiscalizacaoRevenda);

            //------------------Itens-------
            nTotal += Integer.parseInt(formulario.getnRevendasTotal());
            nComercializam += Integer.parseInt(formulario.getnRevendasVacinas());
            nFiscalizadas += Integer.parseInt(formulario.getnRevendasFiscalizadas());

            revendas.put("nTotal", nTotal);
            revendas.put("nComercializam", nComercializam);
            revendas.put("nFiscalizadas", nFiscalizadas);

            item(formulario.getCadastramento(), cadastramentoRevendas);
            item(formulario.getFrequenciaFiscalizacao(), frequenciaFiscalizacao);
            item(formulario.getDiarias(), fiscalizacaoDiaria);
            item(formulario.getSemanais(), fiscalizacaoSemanal);
            item(formulario.getSupervisaoRevenda(), supervisaoRevenda);
            item(formulario.getApreensaoVacinas(), apreensaoVacina);
        }

        //2.3.7 Capacidade para detecção precoce e notificação imediata de doenças
        HashMap<String, Integer> avaliacaoCapacidadeDeteccao = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> fluxoInformacoes = new HashMap<>(hashPadrao);
        HashMap<String, Integer> participacaoComunidade = new HashMap<>(hashPadrao);
        HashMap<String, Integer> fontesInformacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> registroComunicacoes = new HashMap<>(hashPadrao);
        HashMap<String, Integer> capacidadeDeteccao = new HashMap<>();

        int nVigilancia = 0;
        int nProprietario = 0;
        int nTerceitos = 0;

        for (Supervisao supervisao : list) {
            FormCTOCapacidadeDeteccaoPrecoce formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_deteccao_precoce").getResposta(), FormCTOCapacidadeDeteccaoPrecoce.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoCapacidadeDeteccao);

            //------------------Itens-------
            nVigilancia += Integer.parseInt(formulario.getVigilancia());
            nProprietario += Integer.parseInt(formulario.getProprietario());
            nTerceitos += Integer.parseInt(formulario.getTerceiros());

            capacidadeDeteccao.put("nVigilancia", nVigilancia);
            capacidadeDeteccao.put("nProprietario", nProprietario);
            capacidadeDeteccao.put("nTerceitos", nTerceitos);

            item(formulario.getFluxo(), fluxoInformacoes);
            item(formulario.getParticipacao(), participacaoComunidade);
            item(formulario.getFonte(), fontesInformacao);
            item(formulario.getRegistro(), registroComunicacoes);
        }

        //2.3.8 Capacidade para atendimento a suspeitas e atuação em emergências
        HashMap<String, Integer> avaliacaoCapacidadeAtendimento = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> materialEPI = new HashMap<>(hashPadrao);
        HashMap<String, Integer> materialKitAtendimento = new HashMap<>(hashPadrao);
        HashMap<String, Integer> materialKitBiologico = new HashMap<>(hashPadrao);
        HashMap<String, Integer> acondicionamentoMaterial = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentoNotificacoes = new HashMap<>(hashPadrao);
        HashMap<String, Integer> cadastroAgroprodutivo = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormCTOAtendimentoSuspeita formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_atendimento_suspeita").getResposta(), FormCTOAtendimentoSuspeita.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoCapacidadeAtendimento);

            //------------------Itens-------
            item(formulario.getEPI(), materialEPI);
            item(formulario.getKitVesiculares(), materialKitAtendimento);
            item(formulario.getKitMaterialBiologico(), materialKitBiologico);
            item(formulario.getAcondicionamento(), acondicionamentoMaterial);
            item(formulario.getProcedimentos(), procedimentoNotificacoes);
            item(formulario.getAgroprodutivo(), cadastroAgroprodutivo);
        }

        return div(
                h4(attrs(".titulo-form"), "2.3 CAPACIDADE TÉCNICA E OPERACIONAL"),
                h4(attrs(".titulo-form"), "2.3.1 Controle de cadastro de produtores, propriedades e animais"),
                tabelaAvaliacao("Avaliação", "cto_av_controle_cadastro", avaliacaoControleCadastro),
                div(attrs(".col-md-6")).withId("grafico_cto_av_controle_cadastro"),
                table(attrs(".table .table-condensed"),
                        caption("Controle de cadastro de produtores, propriedades e animais"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Informações de produtores, propriedades e efetivos de todos os municípios da jurisdição"),
                                td(attrs(".text-center"), "Documentação referente ao cadastramento de propriedade, produtores rurais e explorações pecuárias"),
                                td(attrs(".text-center"), "Procedimentos para abertura e encerramento de cadastros"),
                                td(attrs(".text-center"), "Registro de entrada, saída e saldo nas fichas de movimentação")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), informacaoProdutores.get("EA").toString()).withId("infoProdutoresEA"),
                                td(attrs(".text-center"), documentacaoCadastramento.get("EA").toString()).withId("docCadastramentoEA"),
                                td(attrs(".text-center"), procedimentoAbertura.get("EA").toString()).withId("proAberturaEA"),
                                td(attrs(".text-center"), registroEntrada.get("EA").toString()).withId("regEntradaEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), informacaoProdutores.get("ED").toString()).withId("infoProdutoresED"),
                                td(attrs(".text-center"), documentacaoCadastramento.get("ED").toString()).withId("docCadastramentoED"),
                                td(attrs(".text-center"), procedimentoAbertura.get("ED").toString()).withId("proAberturaED"),
                                td(attrs(".text-center"), registroEntrada.get("ED").toString()).withId("regEntradaED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), informacaoProdutores.get("NE").toString()).withId("infoProdutoresNE"),
                                td(attrs(".text-center"), documentacaoCadastramento.get("NE").toString()).withId("docCadastramentoNE"),
                                td(attrs(".text-center"), procedimentoAbertura.get("NE").toString()).withId("proAberturaNE"),
                                td(attrs(".text-center"), registroEntrada.get("NE").toString()).withId("regEntradaNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), informacaoProdutores.get("NA").toString()).withId("infoProdutoresNA"),
                                td(attrs(".text-center"), documentacaoCadastramento.get("NA").toString()).withId("docCadastramentoNA"),
                                td(attrs(".text-center"), procedimentoAbertura.get("NA").toString()).withId("proAberturaNA"),
                                td(attrs(".text-center"), registroEntrada.get("NA").toString()).withId("regEntradaNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_cto_controle_cadastro"),
                
                //-
                h4(attrs(".titulo-form"), "2.3.2 Planejamento, execução de atividades e registro"),
                tabelaAvaliacao("Avaliação", "cto_av_planejamento", avaliacaoPlanejamentoExecucao),
                div(attrs(".col-md-6")).withId("grafico_cto_av_planejamento"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Planejamento, execução de atividades e registro"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Planejamento Técnico Mensal"),
                                td(attrs(".text-center"), "Registro das ações planejadas"),
                                td(attrs(".text-center"), "Cumprimento das metas propostas pelos programas sanitários")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), planejamentoMensal.get("EA").toString()).withId("planMensalEA"),
                                td(attrs(".text-center"), registroAcoes.get("EA").toString()).withId("regAcoesEA"),
                                td(attrs(".text-center"), cumprimentoMeta.get("EA").toString()).withId("cumMetaEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), planejamentoMensal.get("ED").toString()).withId("planMensalED"),
                                td(attrs(".text-center"), registroAcoes.get("ED").toString()).withId("regAcoesED"),
                                td(attrs(".text-center"), cumprimentoMeta.get("ED").toString()).withId("cumMetaED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), planejamentoMensal.get("NE").toString()).withId("planMensalNE"),
                                td(attrs(".text-center"), registroAcoes.get("NE").toString()).withId("regAcoesNE"),
                                td(attrs(".text-center"), cumprimentoMeta.get("NE").toString()).withId("cumMetaNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), planejamentoMensal.get("NA").toString()).withId("planMensalNA"),
                                td(attrs(".text-center"), registroAcoes.get("NA").toString()).withId("regAcoesNA"),
                                td(attrs(".text-center"), cumprimentoMeta.get("NA").toString()).withId("cumMetaNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_cto_planejamento"),
                //-
                h4(attrs(".titulo-form"), "2.3.3 Controle de divisas e trânsito interno"),
                tabelaAvaliacao("Avaliação", "cto_av_controle_divisas", avaliacaoControle),
                div(attrs(".col-md-6")).withId("grafico_cto_av_controle_divisas"),
                table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), "Nº de blitz realizadas durante o ano").attr("rowspan", 3),
                                td(attrs(".text-center"), "Blitz realizadas durante o mês").attr("colspan", 5)
                        ),
                        tr(
                                td(attrs(".text-center"), "Quantidade").attr("rowspan", 2),
                                td(attrs(".text-center"), "Nº de veículos inspecionados").attr("colspan", 3),
                                td(attrs(".text-center"), "Nº de animais inspecionados").attr("rowspan", 2)
                        ),
                        tr(
                                td(attrs(".text-center"), "Com animais"),
                                td(attrs(".text-center"), "Com produtos e subprodutos"),
                                td(attrs(".text-center"), "Vazios ou com carga que não seja de interesse da Defesa Animal")
                        ),
                        tr(
                                td(attrs(".text-center"), controle.get("nBlitz").toString()).withId("nBlitz"),
                                td(attrs(".text-center"), controle.get("qtdBlitzMes").toString()).withId("qtdBlitzMes"),
                                td(attrs(".text-center"), controle.get("nVeiculoAnimais").toString()).withId("nVeiculoAnimais"),
                                td(attrs(".text-center"), controle.get("nVeiculoProduto").toString()).withId("nVeiculoProduto"),
                                td(attrs(".text-center"), controle.get("nVeiculoVazio").toString()).withId("nVeiculoVazio"),
                                td(attrs(".text-center"), controle.get("nAnimaisInspecionados").toString()).withId("nAnimaisInspecionados")
                        )
                ),
                table(attrs(".table .table-condensed"),
                        caption("Controle de divisas e trânsito interno"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Pontos Estratégicos para realização de blitz"),
                                td(attrs(".text-center"), "Realização mensal de blitz"),
                                td(attrs(".text-center"), "Registro da blitz"),
                                td(attrs(".text-center"), "Apreensões"),
                                td(attrs(".text-center"), "Material para realização de blitz"),
                                td(attrs(".text-center"), "Registro sobre operações volantes")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), pontosEstrategicos.get("EA").toString()).withId("pontoEstrategicoEA"),
                                td(attrs(".text-center"), realizacaoMensal.get("EA").toString()).withId("relMensalEA"),
                                td(attrs(".text-center"), registroBlitz.get("EA").toString()).withId("regBlitzEA"),
                                td(attrs(".text-center"), apreensoes.get("EA").toString()).withId("apreensoesEA"),
                                td(attrs(".text-center"), materialBlitz.get("EA").toString()).withId("matBlitzEA"),
                                td(attrs(".text-center"), registroOperacao.get("EA").toString()).withId("regOperacoesEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), pontosEstrategicos.get("ED").toString()).withId("pontoEstrategicoED"),
                                td(attrs(".text-center"), realizacaoMensal.get("ED").toString()).withId("relMensalED"),
                                td(attrs(".text-center"), registroBlitz.get("ED").toString()).withId("regBlitzED"),
                                td(attrs(".text-center"), apreensoes.get("ED").toString()).withId("apreensoesED"),
                                td(attrs(".text-center"), materialBlitz.get("ED").toString()).withId("matBlitzED"),
                                td(attrs(".text-center"), registroOperacao.get("ED").toString()).withId("regOperacoesED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), pontosEstrategicos.get("NE").toString()).withId("pontoEstrategicoNE"),
                                td(attrs(".text-center"), realizacaoMensal.get("NE").toString()).withId("relMensalNE"),
                                td(attrs(".text-center"), registroBlitz.get("NE").toString()).withId("regBlitzNE"),
                                td(attrs(".text-center"), apreensoes.get("NE").toString()).withId("apreensoesNE"),
                                td(attrs(".text-center"), materialBlitz.get("NE").toString()).withId("matBlitzNE"),
                                td(attrs(".text-center"), registroOperacao.get("NE").toString()).withId("regOperacoesNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), pontosEstrategicos.get("NA").toString()).withId("pontoEstrategicoNA"),
                                td(attrs(".text-center"), realizacaoMensal.get("NA").toString()).withId("relMensalNA"),
                                td(attrs(".text-center"), registroBlitz.get("NA").toString()).withId("regBlitzNA"),
                                td(attrs(".text-center"), apreensoes.get("NA").toString()).withId("apreensoesNA"),
                                td(attrs(".text-center"), materialBlitz.get("NA").toString()).withId("matBlitzNA"),
                                td(attrs(".text-center"), registroOperacao.get("NA").toString()).withId("regOperacoesNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_cto_controle_divisas"),
                //-
                h4(attrs(".titulo-form"), "2.3.4 Controle de trânsito de animais e produtos de origem animal"),
                tabelaAvaliacao("Avaliação", "cto_av_controle_transito", avaliacaoControleTransito),
                div(attrs(".col-md-6")).withId("grafico_cto_av_controle_transito"),
                table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Credenciamento e descredenciamento dos emitentes"),
                                td(attrs(".text-center"), "Manuais para preenchimento"),
                                td(attrs(".text-center"), "Emissão de GTA"),
                                td(attrs(".text-center"), "Exigências zoossanitárias"),
                                td(attrs(".text-center"), "Relatórios de emissão de GTA"),
                                td(attrs(".text-center"), "Gráficos da movimentação"),
                                td(attrs(".text-center"), "Organização das GTAs"),
                                td(attrs(".text-center"), "Guias de trânsito para subprodutos de origem animal")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), credenciamentoEmitente.get("EA").toString()).withId("credenciamentoEA"),
                                td(attrs(".text-center"), manualPreenchimento.get("EA").toString()).withId("manualPreenchimentoEA"),
                                td(attrs(".text-center"), emissaoGTA.get("EA").toString()).withId("emissaoGTAEA"),
                                td(attrs(".text-center"), exigenciaZoosanitaria.get("EA").toString()).withId("exiZoosanitariaEA"),
                                td(attrs(".text-center"), relatorioEmissao.get("EA").toString()).withId("relEmissaoEA"),
                                td(attrs(".text-center"), graficoMovimentacao.get("EA").toString()).withId("grafMovimentacaoEA"),
                                td(attrs(".text-center"), organizacaoGTA.get("EA").toString()).withId("organizacaoGTAEA"),
                                td(attrs(".text-center"), guiaTransito.get("EA").toString()).withId("guiaTransitoEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), credenciamentoEmitente.get("ED").toString()).withId("credenciamentoED"),
                                td(attrs(".text-center"), manualPreenchimento.get("ED").toString()).withId("manualPreenchimentoED"),
                                td(attrs(".text-center"), emissaoGTA.get("ED").toString()).withId("emissaoGTAED"),
                                td(attrs(".text-center"), exigenciaZoosanitaria.get("ED").toString()).withId("exiZoosanitariaED"),
                                td(attrs(".text-center"), relatorioEmissao.get("ED").toString()).withId("relEmissaoED"),
                                td(attrs(".text-center"), graficoMovimentacao.get("ED").toString()).withId("grafMovimentacaoED"),
                                td(attrs(".text-center"), organizacaoGTA.get("ED").toString()).withId("organizacaoGTAED"),
                                td(attrs(".text-center"), guiaTransito.get("ED").toString()).withId("guiaTransitoED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), credenciamentoEmitente.get("NE").toString()).withId("credenciamentoNE"),
                                td(attrs(".text-center"), manualPreenchimento.get("NE").toString()).withId("manualPreenchimentoNE"),
                                td(attrs(".text-center"), emissaoGTA.get("NE").toString()).withId("emissaoGTANE"),
                                td(attrs(".text-center"), exigenciaZoosanitaria.get("NE").toString()).withId("exiZoosanitariaNE"),
                                td(attrs(".text-center"), relatorioEmissao.get("NE").toString()).withId("relEmissaoNE"),
                                td(attrs(".text-center"), graficoMovimentacao.get("NE").toString()).withId("grafMovimentacaoNE"),
                                td(attrs(".text-center"), organizacaoGTA.get("NE").toString()).withId("organizacaoGTANE"),
                                td(attrs(".text-center"), guiaTransito.get("NE").toString()).withId("guiaTransitoNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), credenciamentoEmitente.get("NA").toString()).withId("credenciamentoNA"),
                                td(attrs(".text-center"), manualPreenchimento.get("NA").toString()).withId("manualPreenchimentoNA"),
                                td(attrs(".text-center"), emissaoGTA.get("NA").toString()).withId("emissaoGTANA"),
                                td(attrs(".text-center"), exigenciaZoosanitaria.get("NA").toString()).withId("exiZoosanitariaNA"),
                                td(attrs(".text-center"), relatorioEmissao.get("NA").toString()).withId("relEmissaoNA"),
                                td(attrs(".text-center"), graficoMovimentacao.get("NA").toString()).withId("grafMovimentacaoNA"),
                                td(attrs(".text-center"), organizacaoGTA.get("NA").toString()).withId("organizacaoGTANA"),
                                td(attrs(".text-center"), guiaTransito.get("NA").toString()).withId("guiaTransitoNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_cto_controle_transito"),
                //-
                h4(attrs(".titulo-form"), "2.3.5 Controle de eventos de aglomeração de animais"),
                tabelaAvaliacao("Avaliação", "cto_av_controle_eventos", avaliacaoControleEventos),
                div(attrs(".col-md-6")).withId("grafico_cto_av_controle_eventos"),
                table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), "Nº de recintos de aglomeração cadastrados"),
                                td(attrs(".text-center"), "Nº de eventos realizados*"),
                                td(attrs(".text-center"), "Nº de eventos fiscalizados*"),
                                td(attrs(".text-center"), "Nº de animais participantes"),
                                td(attrs(".text-center"), "Nº de animais inspecionados")
                        ),
                        tr(
                                td(attrs(".text-center"), controleEventos.get("nRecintoAglomeracao").toString()).withId("nRecintoAglomeracao"),
                                td(attrs(".text-center"), controleEventos.get("nEventosRealizados").toString()).withId("nEventosRealizados"),
                                td(attrs(".text-center"), controleEventos.get("nEventosFiscalizados").toString()).withId("nEventosFiscalizados"),
                                td(attrs(".text-center"), controleEventos.get("nAnimaisParticipantes").toString()).withId("nAnimaisParticipantes"),
                                td(attrs(".text-center"), controleEventos.get("nControleAnimaisInspecionados").toString()).withId("nControleAnimaisInspecionados")
                        )
                ),
                table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Documentação de cadastramento dos recintos"),
                                td(attrs(".text-center"), "Documentação para realização do evento"),
                                td(attrs(".text-center"), "Registro de eventos"),
                                td(attrs(".text-center"), "Inspeção clínica de animais"),
                                td(attrs(".text-center"), "Emissão de GTAs de saída")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), documentacaoCadastramentoRecinto.get("EA").toString()).withId("cadastramentoRecintoEA"),
                                td(attrs(".text-center"), documentacaoEvento.get("EA").toString()).withId("docEventoEA"),
                                td(attrs(".text-center"), registroEventos.get("EA").toString()).withId("regEventosEA"),
                                td(attrs(".text-center"), inspecaoClinica.get("EA").toString()).withId("inspClinicaEA"),
                                td(attrs(".text-center"), emissaoGTASaida.get("EA").toString()).withId("emissaoGTASaidaEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), documentacaoCadastramentoRecinto.get("ED").toString()).withId("cadastramentoRecintoED"),
                                td(attrs(".text-center"), documentacaoEvento.get("ED").toString()).withId("docEventoED"),
                                td(attrs(".text-center"), registroEventos.get("ED").toString()).withId("regEventosED"),
                                td(attrs(".text-center"), inspecaoClinica.get("ED").toString()).withId("inspClinicaED"),
                                td(attrs(".text-center"), emissaoGTASaida.get("ED").toString()).withId("emissaoGTASaidaED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), documentacaoCadastramentoRecinto.get("NE").toString()).withId("cadastramentoRecintoNE"),
                                td(attrs(".text-center"), documentacaoEvento.get("NE").toString()).withId("docEventoNE"),
                                td(attrs(".text-center"), registroEventos.get("NE").toString()).withId("regEventosNE"),
                                td(attrs(".text-center"), inspecaoClinica.get("NE").toString()).withId("inspClinicaNE"),
                                td(attrs(".text-center"), emissaoGTASaida.get("NE").toString()).withId("emissaoGTASaidaNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), documentacaoCadastramentoRecinto.get("NA").toString()).withId("cadastramentoRecintoNA"),
                                td(attrs(".text-center"), documentacaoEvento.get("NA").toString()).withId("docEventoNA"),
                                td(attrs(".text-center"), registroEventos.get("NA").toString()).withId("regEventosNA"),
                                td(attrs(".text-center"), inspecaoClinica.get("NA").toString()).withId("inspClinicaNA"),
                                td(attrs(".text-center"), emissaoGTASaida.get("NA").toString()).withId("emissaoGTASaidaNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_cto_controle_eventos"),
                //-
                h4(attrs(".titulo-form"), "2.3.6 Fiscalização em revendas veterinárias"),
                tabelaAvaliacao("Avaliação", "cto_av_fiscalizacao_revendas", avaliacaoFiscalizacaoRevenda),
                div(attrs(".col-md-6")).withId("grafico_cto_av_fiscalizacao_revendas"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), "Nº de Revendas Cadastradas").attr("colspan", 2),
                                td(attrs(".text-center"), "Nº de revendas fiscalizadas durante o mês").attr("rowspan", 2)
                        ),
                        tr(
                                td(attrs(".text-center"), "TOTAL"),
                                td(attrs(".text-center"), "Que comercializam vacinas")
                        ),
                        tr(
                                td(attrs(".text-center"), revendas.get("nTotal").toString()).withId("nTotal"),
                                td(attrs(".text-center"), revendas.get("nComercializam").toString()).withId("nComercializam"),
                                td(attrs(".text-center"), revendas.get("nFiscalizadas").toString()).withId("nFiscalizadas")
                        )
                )),
                table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), " ").attr("rowspan", 2),
                                td(attrs(".text-center"), "Cadastramento de Revendas Veterinárias").attr("rowspan", 2),
                                td(attrs(".text-center"), "Frequência de fiscalizações em revendas").attr("rowspan", 2),
                                td(attrs(".text-center"), "Procedimentos de Fiscalização").attr("colspan", 2),
                                td(attrs(".text-center"), "Supervisão na revenda veterinária").attr("rowspan", 2),
                                td(attrs(".text-center"), "Apreensão de vacinas").attr("rowspan", 2)
                        ),
                        tr(
                                td(attrs(".text-center"), "Diárias"),
                                td(attrs(".text-center"), "Semanais")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), cadastramentoRevendas.get("EA").toString()).withId("cadastramentoRevendasEA"),
                                td(attrs(".text-center"), frequenciaFiscalizacao.get("EA").toString()).withId("freqFiscalizacaoEA"),
                                td(attrs(".text-center"), fiscalizacaoDiaria.get("EA").toString()).withId("fiscDiariaEA"),
                                td(attrs(".text-center"), fiscalizacaoSemanal.get("EA").toString()).withId("fiscSemanalEA"),
                                td(attrs(".text-center"), supervisaoRevenda.get("EA").toString()).withId("supRevendaEA"),
                                td(attrs(".text-center"), apreensaoVacina.get("EA").toString()).withId("apreensaoVacinaEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), cadastramentoRevendas.get("ED").toString()).withId("cadastramentoRevendasED"),
                                td(attrs(".text-center"), frequenciaFiscalizacao.get("ED").toString()).withId("freqFiscalizacaoED"),
                                td(attrs(".text-center"), fiscalizacaoDiaria.get("ED").toString()).withId("fiscDiariaED"),
                                td(attrs(".text-center"), fiscalizacaoSemanal.get("ED").toString()).withId("fiscSemanalED"),
                                td(attrs(".text-center"), supervisaoRevenda.get("ED").toString()).withId("supRevendaED"),
                                td(attrs(".text-center"), apreensaoVacina.get("ED").toString()).withId("apreensaoVacinaED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), cadastramentoRevendas.get("NE").toString()).withId("cadastramentoRevendasNE"),
                                td(attrs(".text-center"), frequenciaFiscalizacao.get("NE").toString()).withId("freqFiscalizacaoNE"),
                                td(attrs(".text-center"), fiscalizacaoDiaria.get("NE").toString()).withId("fiscDiariaNE"),
                                td(attrs(".text-center"), fiscalizacaoSemanal.get("NE").toString()).withId("fiscSemanalNE"),
                                td(attrs(".text-center"), supervisaoRevenda.get("NE").toString()).withId("supRevendaNE"),
                                td(attrs(".text-center"), apreensaoVacina.get("NE").toString()).withId("apreensaoVacinaNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), cadastramentoRevendas.get("NA").toString()).withId("cadastramentoRevendasNA"),
                                td(attrs(".text-center"), frequenciaFiscalizacao.get("NA").toString()).withId("freqFiscalizacaoNA"),
                                td(attrs(".text-center"), fiscalizacaoDiaria.get("NA").toString()).withId("fiscDiariaNA"),
                                td(attrs(".text-center"), fiscalizacaoSemanal.get("NA").toString()).withId("fiscSemanalNA"),
                                td(attrs(".text-center"), supervisaoRevenda.get("NA").toString()).withId("supRevendaNA"),
                                td(attrs(".text-center"), apreensaoVacina.get("NA").toString()).withId("apreensaoVacinaNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_cto_fiscalizacao_revendas"),
                //-
                h4(attrs(".titulo-form"), "2.3.7 Capacidade para detecção precoce e notificação imediata de doenças"),
                tabelaAvaliacao("Avaliação", "cto_av_deteccao_precoce", avaliacaoCapacidadeDeteccao),
                div(attrs(".col-md-6")).withId("grafico_cto_av_deteccao_precoce"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), "Nº de notificações de enfermidades recebidas / Tipo de notificação").attr("colspan", 3)
                        ),
                        tr(
                                td(attrs(".text-center"), "Vigilância (Vg)"),
                                td(attrs(".text-center"), "Proprietário (Pp)"),
                                td(attrs(".text-center"), "Terceiros (Te)")
                        ),
                        tr(
                                td(attrs(".text-center"), capacidadeDeteccao.get("nVigilancia").toString()).withId("nVigilancia"),
                                td(attrs(".text-center"), capacidadeDeteccao.get("nProprietario").toString()).withId("nProprietario"),
                                td(attrs(".text-center"), capacidadeDeteccao.get("nTerceitos").toString()).withId("nTerceitos")
                        )
                )),
                table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Fluxo de informações"),
                                td(attrs(".text-center"), "Participação da comunidade"),
                                td(attrs(".text-center"), "Fontes de informação"),
                                td(attrs(".text-center"), "Registro das comunicações e atendimentos de ocorrência de enfermidades ")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), fluxoInformacoes.get("EA").toString()).withId("fluxoInformacoesEA"),
                                td(attrs(".text-center"), participacaoComunidade.get("EA").toString()).withId("participacaoComunidadeEA"),
                                td(attrs(".text-center"), fontesInformacao.get("EA").toString()).withId("fonteInformacaoEA"),
                                td(attrs(".text-center"), registroComunicacoes.get("EA").toString()).withId("registroComunicacoesEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), fluxoInformacoes.get("ED").toString()).withId("fluxoInformacoesED"),
                                td(attrs(".text-center"), participacaoComunidade.get("ED").toString()).withId("participacaoComunidadeED"),
                                td(attrs(".text-center"), fontesInformacao.get("ED").toString()).withId("fonteInformacaoED"),
                                td(attrs(".text-center"), registroComunicacoes.get("ED").toString()).withId("registroComunicacoesED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), fluxoInformacoes.get("NE").toString()).withId("fluxoInformacoesNE"),
                                td(attrs(".text-center"), participacaoComunidade.get("NE").toString()).withId("participacaoComunidadeNE"),
                                td(attrs(".text-center"), fontesInformacao.get("NE").toString()).withId("fonteInformacaoNE"),
                                td(attrs(".text-center"), registroComunicacoes.get("NE").toString()).withId("registroComunicacoesNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), fluxoInformacoes.get("NA").toString()).withId("fluxoInformacoesNA"),
                                td(attrs(".text-center"), participacaoComunidade.get("NA").toString()).withId("participacaoComunidadeNA"),
                                td(attrs(".text-center"), fontesInformacao.get("NA").toString()).withId("fonteInformacaoNA"),
                                td(attrs(".text-center"), registroComunicacoes.get("NA").toString()).withId("registroComunicacoesNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_cto_deteccao_precoce"),
                //-
                h4(attrs(".titulo-form"), "2.3.8 Capacidade para atendimento a suspeitas e atuação em emergências"),
                tabelaAvaliacao("Avaliação", "cto_av_capacidade_atendimento", avaliacaoCapacidadeAtendimento),
                div(attrs(".col-md-6")).withId("grafico_cto_av_capacidade_atendimento"),
                table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), " ").attr("rowspan", 2),
                                td(attrs(".text-center"), "Material para atendimento").attr("colspan", 3),
                                td(attrs(".text-center"), "Acondicionamento e remessa de material para a Unidade Central").attr("rowspan", 2),
                                td(attrs(".text-center"), "Procedimentos no atendimento às notificações").attr("rowspan", 2),
                                td(attrs(".text-center"), "Cadastro Agroprodutivo").attr("rowspan", 2)
                        ),
                        tr(
                                td(attrs(".text-center"), "EPI"),
                                td(attrs(".text-center"), "Enfermidades Vesiculares"),
                                td(attrs(".text-center"), "Colheita de material biológico")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), materialEPI.get("EA").toString()).withId("materialEPIEA"),
                                td(attrs(".text-center"), materialKitAtendimento.get("EA").toString()).withId("materialKitAtendimentoEA"),
                                td(attrs(".text-center"), materialKitBiologico.get("EA").toString()).withId("materialKitBiologicoEA"),
                                td(attrs(".text-center"), acondicionamentoMaterial.get("EA").toString()).withId("acondicionamentoEA"),
                                td(attrs(".text-center"), procedimentoNotificacoes.get("EA").toString()).withId("procedimentoNotificacoesEA"),
                                td(attrs(".text-center"), cadastroAgroprodutivo.get("EA").toString()).withId("cadastroAgroprodutivoEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), materialEPI.get("ED").toString()).withId("materialEPIED"),
                                td(attrs(".text-center"), materialKitAtendimento.get("ED").toString()).withId("materialKitAtendimentoED"),
                                td(attrs(".text-center"), materialKitBiologico.get("ED").toString()).withId("materialKitBiologicoED"),
                                td(attrs(".text-center"), acondicionamentoMaterial.get("ED").toString()).withId("acondicionamentoED"),
                                td(attrs(".text-center"), procedimentoNotificacoes.get("ED").toString()).withId("procedimentoNotificacoesED"),
                                td(attrs(".text-center"), cadastroAgroprodutivo.get("ED").toString()).withId("cadastroAgroprodutivoED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), materialEPI.get("NE").toString()).withId("materialEPINE"),
                                td(attrs(".text-center"), materialKitAtendimento.get("NE").toString()).withId("materialKitAtendimentoNE"),
                                td(attrs(".text-center"), materialKitBiologico.get("NE").toString()).withId("materialKitBiologicoNE"),
                                td(attrs(".text-center"), acondicionamentoMaterial.get("NE").toString()).withId("acondicionamentoNE"),
                                td(attrs(".text-center"), procedimentoNotificacoes.get("NE").toString()).withId("procedimentoNotificacoesNE"),
                                td(attrs(".text-center"), cadastroAgroprodutivo.get("NE").toString()).withId("cadastroAgroprodutivoNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), materialEPI.get("NA").toString()).withId("materialEPINA"),
                                td(attrs(".text-center"), materialKitAtendimento.get("NA").toString()).withId("materialKitAtendimentoNA"),
                                td(attrs(".text-center"), materialKitBiologico.get("NA").toString()).withId("materialKitBiologicoNA"),
                                td(attrs(".text-center"), acondicionamentoMaterial.get("NA").toString()).withId("acondicionamentoNA"),
                                td(attrs(".text-center"), procedimentoNotificacoes.get("NA").toString()).withId("procedimentoNotificacoesNA"),
                                td(attrs(".text-center"), cadastroAgroprodutivo.get("NA").toString()).withId("cadastroAgroprodutivoNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_cto_capacidade_atendimento")
        );

    }

    public Tag PNEFA(List<Supervisao> list) {

        //2.4.1 Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)
        HashMap<String, Integer> avaliacaoPNEFA = new HashMap<>(hashAvaliacaoPadrao);

        //A - FISCALIZAÇÕES DE VACINAÇÕES
        HashMap<String, Integer> procedimentosVacinacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentosComprovacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> relatorioCampanha = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentoPosEtapa = new HashMap<>(hashPadrao);

        //B - VIGILÂNCIA EPIDEMIOLÓGICA
        HashMap<String, Integer> direcionamentoAcoesVigilancia = new HashMap<>(hashPadrao);
        HashMap<String, Integer> propriedadePontosRisco = new HashMap<>(hashPadrao);
        HashMap<String, Integer> realizacaoRegistroVigilancia = new HashMap<>(hashPadrao);
        HashMap<String, Integer> indicadoresCaracterizacaoEpidemiologica = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentosAdotadosFoco = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentosAdotadosVinculo = new HashMap<>(hashPadrao);
        HashMap<String, Integer> nPropriedadeMonitoramento = new HashMap<>();

        int nFoco = 0;
        int nVinculo = 0;

        for (Supervisao supervisao : list) {

            if (supervisao.getHashRespostas().containsKey("pnefa_fiscalizacoes")) {
                FormPNEFAFiscalizacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("pnefa_fiscalizacoes").getResposta(), FormPNEFAFiscalizacoes.class);

                //------------------Avaliação-------
                ava(formulario.getAvaliacao(), avaliacaoPNEFA);

                //------------------Itens-------
                item(formulario.getVacinacao(), procedimentosVacinacao);
                item(formulario.getComprovacao(), procedimentosComprovacao);
                item(formulario.getRelatorios(), relatorioCampanha);
                item(formulario.getPosetapa(), procedimentoPosEtapa);
            }

            if (supervisao.getHashRespostas().containsKey("pnefa_vigilancia")) {
                FormPNEFAVigilancia formulario2 = gson.fromJson(supervisao.getHashRespostas().get("pnefa_vigilancia").getResposta(), FormPNEFAVigilancia.class);

                nFoco += Integer.parseInt(formulario2.getnPropriedadeFoco());
                nVinculo += Integer.parseInt(formulario2.getnPropriedadeVinculo());

                nPropriedadeMonitoramento.put("nPropriedadeFoco", nFoco);
                nPropriedadeMonitoramento.put("nPropriedadeVinculo", nVinculo);

                item(formulario2.getDirecionamento(), direcionamentoAcoesVigilancia);
                item(formulario2.getPropriedadesRisco(), propriedadePontosRisco);
                item(formulario2.getRegistroVigilancia(), realizacaoRegistroVigilancia);
                item(formulario2.getIndicadores(), indicadoresCaracterizacaoEpidemiologica);
                item(formulario2.getProcedimentosAdotadosFoco(), procedimentosAdotadosFoco);
                item(formulario2.getProcedimentosAdotadosVinculo(), procedimentosAdotadosVinculo);
            }
        }

        return div(
                h4(attrs(".titulo-form"), "2.4.1 Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)"),
                tabelaAvaliacao("Avaliação", "pnefa_av", avaliacaoPNEFA),
                div(attrs(".col-md-6")).withId("grafico_pnefa_av"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Fiscalização de vacinações"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Procedimentos relacionados à vacinação "),
                                td(attrs(".text-center"), "Procedimentos de comprovação de vacinação"),
                                td(attrs(".text-center"), "Relatórios de campanha"),
                                td(attrs(".text-center"), "Procedimentos pós-etapa")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), procedimentosVacinacao.get("EA").toString()).withId("procedimentoVacinacaoEA"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("EA").toString()).withId("procedimentoComprovacaoEA"),
                                td(attrs(".text-center"), relatorioCampanha.get("EA").toString()).withId("relatorioCampanhaEA"),
                                td(attrs(".text-center"), procedimentoPosEtapa.get("EA").toString()).withId("procedimentoPosEtapaEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), procedimentosVacinacao.get("ED").toString()).withId("procedimentoVacinacaoED"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("ED").toString()).withId("procedimentoComprovacaoED"),
                                td(attrs(".text-center"), relatorioCampanha.get("ED").toString()).withId("relatorioCampanhaED"),
                                td(attrs(".text-center"), procedimentoPosEtapa.get("ED").toString()).withId("procedimentoPosEtapaED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), procedimentosVacinacao.get("NE").toString()).withId("procedimentoVacinacaoNE"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("NE").toString()).withId("procedimentoComprovacaoNE"),
                                td(attrs(".text-center"), relatorioCampanha.get("NE").toString()).withId("relatorioCampanhaNE"),
                                td(attrs(".text-center"), procedimentoPosEtapa.get("NE").toString()).withId("procedimentoPosEtapaNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), procedimentosVacinacao.get("NA").toString()).withId("procedimentoVacinacaoNA"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("NA").toString()).withId("procedimentoComprovacaoNA"),
                                td(attrs(".text-center"), relatorioCampanha.get("NA").toString()).withId("relatorioCampanhaNA"),
                                td(attrs(".text-center"), procedimentoPosEtapa.get("NA").toString()).withId("procedimentoPosEtapaNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pnefa_fiscalizacoes"),
                
                table(attrs(".table .table-condensed"),
                        caption("Vigilância Epidemiológica"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Direcionamento das ações de vigilância"),
                                td(attrs(".text-center"), "Propriedades e pontos de risco"),
                                td(attrs(".text-center"), "Realização e registro da vigilância em propriedades e pontos de risco"),
                                td(attrs(".text-center"), "Indicadores da caracterização epidemiológica"),
                                td(attrs(".text-center"), "Procedimentos adotados Foco"),
                                td(attrs(".text-center"), "Procedimentos adotados Vínculo")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("EA").toString()).withId("direcionamentoAcoesEA"),
                                td(attrs(".text-center"), propriedadePontosRisco.get("EA").toString()).withId("propriedadePontosRiscoEA"),
                                td(attrs(".text-center"), realizacaoRegistroVigilancia.get("EA").toString()).withId("realizacaoRegistroVigilanciaEA"),
                                td(attrs(".text-center"), indicadoresCaracterizacaoEpidemiologica.get("EA").toString()).withId("indicadoresCaracterizacaoEpidemiologicaEA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("EA").toString()).withId("procedimentosAdotadosFocoEA"),
                                td(attrs(".text-center"), procedimentosAdotadosVinculo.get("EA").toString()).withId("procedimentosAdotadosVinculoEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("ED").toString()).withId("direcionamentoAcoesED"),
                                td(attrs(".text-center"), propriedadePontosRisco.get("ED").toString()).withId("propriedadePontosRiscoED"),
                                td(attrs(".text-center"), realizacaoRegistroVigilancia.get("ED").toString()).withId("realizacaoRegistroVigilanciaED"),
                                td(attrs(".text-center"), indicadoresCaracterizacaoEpidemiologica.get("ED").toString()).withId("indicadoresCaracterizacaoEpidemiologicaED"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("ED").toString()).withId("procedimentosAdotadosFocoED"),
                                td(attrs(".text-center"), procedimentosAdotadosVinculo.get("ED").toString()).withId("procedimentosAdotadosVinculoED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("NE").toString()).withId("direcionamentoAcoesNE"),
                                td(attrs(".text-center"), propriedadePontosRisco.get("NE").toString()).withId("propriedadePontosRiscoNE"),
                                td(attrs(".text-center"), realizacaoRegistroVigilancia.get("NE").toString()).withId("realizacaoRegistroVigilanciaNE"),
                                td(attrs(".text-center"), indicadoresCaracterizacaoEpidemiologica.get("NE").toString()).withId("indicadoresCaracterizacaoEpidemiologicaNE"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NE").toString()).withId("procedimentosAdotadosFocoNE"),
                                td(attrs(".text-center"), procedimentosAdotadosVinculo.get("NE").toString()).withId("procedimentosAdotadosVinculoNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("NA").toString()).withId("direcionamentoAcoesNA"),
                                td(attrs(".text-center"), propriedadePontosRisco.get("NA").toString()).withId("propriedadePontosRiscoNA"),
                                td(attrs(".text-center"), realizacaoRegistroVigilancia.get("NA").toString()).withId("realizacaoRegistroVigilanciaNA"),
                                td(attrs(".text-center"), indicadoresCaracterizacaoEpidemiologica.get("NA").toString()).withId("indicadoresCaracterizacaoEpidemiologicaNA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NA").toString()).withId("procedimentosAdotadosFocoNA"),
                                td(attrs(".text-center"), procedimentosAdotadosVinculo.get("NA").toString()).withId("procedimentosAdotadosVinculoNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_pnefa_vigilancia"),
                table(attrs(".table .table-condensed"),
                        caption("Propriedades sob monitoramento para saneamento de foco"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), "Propriedade Foco"),
                                        td(attrs(".text-center"), "Proriedade Vínculo")
                                ),
                                tr(
                                        td(attrs(".text-center"), nPropriedadeMonitoramento.get("nPropriedadeFoco").toString()).withId("nPropriedadeFocoPNEFA"),
                                        td(attrs(".text-center"), nPropriedadeMonitoramento.get("nPropriedadeVinculo").toString()).withId("nPropriedadeVinculoPNEFA")
                                )
                        ))
        );

    }

    public Tag PNCEBT(List<Supervisao> list) {

        //2.4.2 Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)
        HashMap<String, Integer> avaliacaoPNCEBT = new HashMap<>(hashAvaliacaoPadrao);

        //A - FISCALIZAÇÕES DE VACINAÇÕES
        HashMap<String, Integer> procedimentosComprovacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> relatorioCobertura = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentoInadimplente = new HashMap<>(hashPadrao);

        //B - VIGILÂNCIA EPIDEMIOLÓGICA
        HashMap<String, Integer> direcionamentoAcoesVigilancia = new HashMap<>(hashPadrao);
        HashMap<String, Integer> fiscalizacaoPropriedade = new HashMap<>(hashPadrao);
        HashMap<String, Integer> fiscalizacaoLaboratorio = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentosAdotadosFoco = new HashMap<>(hashPadrao);
        HashMap<String, Integer> nPropriedadeMonitoramento = new HashMap<>();

        int nFoco = 0;

        //C - CONTROLES DO PROGRAMA
        HashMap<String, Integer> portariaVetCadastrados = new HashMap<>();
        portariaVetCadastrados.putAll(hashPadrao);

        HashMap<String, Integer> pontualidadeVetCadastrado = new HashMap<>();
        pontualidadeVetCadastrado.putAll(hashPadrao);

        HashMap<String, Integer> relatorioPNCEBT = new HashMap<>();
        relatorioPNCEBT.putAll(hashPadrao);

        HashMap<String, Integer> comercializacaoReceituario = new HashMap<>();
        comercializacaoReceituario.putAll(hashPadrao);

        for (Supervisao supervisao : list) {

            if (supervisao.getHashRespostas().containsKey("pncebt_fiscalizacoes")) {
                FormPNCEBTFiscalizacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("pncebt_fiscalizacoes").getResposta(), FormPNCEBTFiscalizacoes.class);

                //------------------Avaliação-------
                ava(formulario.getAvaliacao(), avaliacaoPNCEBT);

                //------------------Itens-------
                item(formulario.getProcedimentosComprovacao(), procedimentosComprovacao);
                item(formulario.getRelatorios(), relatorioCobertura);
                item(formulario.getProcedimentosInadimplentes(), procedimentoInadimplente);
            }

            if (supervisao.getHashRespostas().containsKey("pncebt_vigilancia")) {
                FormPNCEBTVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pncebt_vigilancia").getResposta(), FormPNCEBTVigilancia.class);

                nFoco += Integer.parseInt(formulario.getnPropriedadesFoco());

                nPropriedadeMonitoramento.put("nPropriedadeFoco", nFoco);

                item(formulario.getDirecionamento(), direcionamentoAcoesVigilancia);
                item(formulario.getFiscalizacaoPropriedade(), fiscalizacaoPropriedade);
                item(formulario.getFiscalizacaoLaboratorio(), fiscalizacaoLaboratorio);
                item(formulario.getProcedimentoAdotadoFoco(), procedimentosAdotadosFoco);
            }

            if (supervisao.getHashRespostas().containsKey("pncebt_controles")) {
                FormPNCEBTControles formulario = gson.fromJson(supervisao.getHashRespostas().get("pncebt_controles").getResposta(), FormPNCEBTControles.class);

                //------------------Itens-------
                item(formulario.getPortaria(), portariaVetCadastrados);
                item(formulario.getPontualidade(), pontualidadeVetCadastrado);
                item(formulario.getRelatorios(), relatorioPNCEBT);
                item(formulario.getComercializacao(), comercializacaoReceituario);
            }
        }

        return div(
                h4(attrs(".titulo-form"), "2.4.2 Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)"),
                tabelaAvaliacao("Avaliação", "pncebt_av", avaliacaoPNCEBT),
                div(attrs(".col-md-6")).withId("grafico_pncebt_av"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Fiscalização de vacinações"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Procedimentos de comprovação de vacinação"),
                                td(attrs(".text-center"), "Relatórios de cobertura vacinal"),
                                td(attrs(".text-center"), "Procedimentos com inadimplentes")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("EA").toString()).withId("procedimentoComprovacaoPNCEBTEA"),
                                td(attrs(".text-center"), relatorioCobertura.get("EA").toString()).withId("relatorioCoberturaEA"),
                                td(attrs(".text-center"), procedimentoInadimplente.get("EA").toString()).withId("procedimentoInadimplenteEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("ED").toString()).withId("procedimentoComprovacaoPNCEBTED"),
                                td(attrs(".text-center"), relatorioCobertura.get("ED").toString()).withId("relatorioCoberturaED"),
                                td(attrs(".text-center"), procedimentoInadimplente.get("ED").toString()).withId("procedimentoInadimplenteED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("NE").toString()).withId("procedimentoComprovacaoPNCEBTNE"),
                                td(attrs(".text-center"), relatorioCobertura.get("NE").toString()).withId("relatorioCoberturaNE"),
                                td(attrs(".text-center"), procedimentoInadimplente.get("NE").toString()).withId("procedimentoInadimplenteNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("NA").toString()).withId("procedimentoComprovacaoPNCEBTNA"),
                                td(attrs(".text-center"), relatorioCobertura.get("NA").toString()).withId("relatorioCoberturaNA"),
                                td(attrs(".text-center"), procedimentoInadimplente.get("NA").toString()).withId("procedimentoInadimplenteNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pncebt_fiscalizacao"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Vigilância Epidemiológica"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Direcionamento das ações de vigilância"),
                                td(attrs(".text-center"), "Fiscalização em propriedades"),
                                td(attrs(".text-center"), "Fiscalização em laboratório"),
                                td(attrs(".text-center"), "Procedimentos adotados Foco")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("EA").toString()).withId("direcionamentoAcoesEA"),
                                td(attrs(".text-center"), fiscalizacaoPropriedade.get("EA").toString()).withId("propriedadePontosRiscoEA"),
                                td(attrs(".text-center"), fiscalizacaoLaboratorio.get("EA").toString()).withId("realizacaoRegistroVigilanciaEA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("EA").toString()).withId("procedimentosAdotadosFocoEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("ED").toString()).withId("direcionamentoAcoesED"),
                                td(attrs(".text-center"), fiscalizacaoPropriedade.get("ED").toString()).withId("propriedadePontosRiscoED"),
                                td(attrs(".text-center"), fiscalizacaoLaboratorio.get("ED").toString()).withId("realizacaoRegistroVigilanciaED"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("ED").toString()).withId("procedimentosAdotadosFocoED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("NE").toString()).withId("direcionamentoAcoesNE"),
                                td(attrs(".text-center"), fiscalizacaoPropriedade.get("NE").toString()).withId("propriedadePontosRiscoNE"),
                                td(attrs(".text-center"), fiscalizacaoLaboratorio.get("NE").toString()).withId("realizacaoRegistroVigilanciaNE"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NE").toString()).withId("procedimentosAdotadosFocoNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("NA").toString()).withId("direcionamentoAcoesNA"),
                                td(attrs(".text-center"), fiscalizacaoPropriedade.get("NA").toString()).withId("propriedadePontosRiscoNA"),
                                td(attrs(".text-center"), fiscalizacaoLaboratorio.get("NA").toString()).withId("realizacaoRegistroVigilanciaNA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NA").toString()).withId("procedimentosAdotadosFocoNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pncebt_vigilancia"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Propriedades sob monitoramento para saneamento de foco"),
                        tbody(
                                tr(td(attrs(".text-center"), "Propriedade Foco")),
                                tr(td(attrs(".text-center"), nPropriedadeMonitoramento.get("nPropriedadeFoco").toString()).withId("nPropriedadeFocoPNCEBT"))
                        ))),
                
                table(attrs(".table .table-condensed"),
                        caption("Controles do Programa"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Portarias Med Vet cadastrados e/ou habilitados"),
                                td(attrs(".text-center"), "Pontualidade Med Vet cadastrados e/ou habilitados"),
                                td(attrs(".text-center"), "Relatórios do PNCEBT/MA"),
                                td(attrs(".text-center"), "Comercialização de blocos de Receituário e atestados de vacinação contra brucelose (UR)")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), portariaVetCadastrados.get("EA").toString()).withId("portariaVetCadastradoEA"),
                                td(attrs(".text-center"), pontualidadeVetCadastrado.get("EA").toString()).withId("pontualidadeVetCadastradoEA"),
                                td(attrs(".text-center"), relatorioPNCEBT.get("EA").toString()).withId("relatorioPNCEBTEA"),
                                td(attrs(".text-center"), comercializacaoReceituario.get("EA").toString()).withId("comercializacaoReceituarioEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), portariaVetCadastrados.get("ED").toString()).withId("portariaVetCadastradoED"),
                                td(attrs(".text-center"), pontualidadeVetCadastrado.get("ED").toString()).withId("pontualidadeVetCadastradoED"),
                                td(attrs(".text-center"), relatorioPNCEBT.get("ED").toString()).withId("relatorioPNCEBTED"),
                                td(attrs(".text-center"), comercializacaoReceituario.get("ED").toString()).withId("comercializacaoReceituarioED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), portariaVetCadastrados.get("NE").toString()).withId("portariaVetCadastradoNE"),
                                td(attrs(".text-center"), pontualidadeVetCadastrado.get("NE").toString()).withId("pontualidadeVetCadastradoNE"),
                                td(attrs(".text-center"), relatorioPNCEBT.get("NE").toString()).withId("relatorioPNCEBTNE"),
                                td(attrs(".text-center"), comercializacaoReceituario.get("NE").toString()).withId("comercializacaoReceituarioNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), portariaVetCadastrados.get("NA").toString()).withId("portariaVetCadastradoNA"),
                                td(attrs(".text-center"), pontualidadeVetCadastrado.get("NA").toString()).withId("pontualidadeVetCadastradoNA"),
                                td(attrs(".text-center"), relatorioPNCEBT.get("NA").toString()).withId("relatorioPNCEBTNA"),
                                td(attrs(".text-center"), comercializacaoReceituario.get("NA").toString()).withId("comercializacaoReceituarioNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_pncebt_controle")
        );

    }

    public Tag PNCRH(List<Supervisao> list) {

        //2.4.3 Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)
        HashMap<String, Integer> avaliacaoPNCRH = new HashMap<>(hashAvaliacaoPadrao);

        //A - FISCALIZAÇÕES DE VACINAÇÕES
        HashMap<String, Integer> procedimentosComprovacao = new HashMap<>(hashPadrao);
        HashMap<String, Integer> relatorioCobertura = new HashMap<>(hashPadrao);

        //B - VIGILÂNCIA EPIDEMIOLÓGICA
        HashMap<String, Integer> direcionamentoVigilancia = new HashMap<>(hashPadrao);
        HashMap<String, Integer> cadastroMonitoramento = new HashMap<>(hashPadrao);
        HashMap<String, Integer> capturaMorcegos = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentosAdotadosFoco = new HashMap<>(hashPadrao);
        HashMap<String, Integer> nPropriedades = new HashMap<>();

        int nFoco = 0;

        for (Supervisao supervisao : list) {

            if (supervisao.getHashRespostas().containsKey("pncrh_fiscalizacoes")) {
                FormPNCRHFiscalizacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("pncrh_fiscalizacoes").getResposta(), FormPNCRHFiscalizacoes.class);

                //------------------Avaliação-------
                ava(formulario.getAvaliacao(), avaliacaoPNCRH);

                //------------------Itens-------
                item(formulario.getProcedimentosComprovacao(), procedimentosComprovacao);
                item(formulario.getRelatoriosCobertura(), relatorioCobertura);
            }

            if (supervisao.getHashRespostas().containsKey("pncrh_vigilancia")) {
                FormPNCRHVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pncrh_vigilancia").getResposta(), FormPNCRHVigilancia.class);

                nFoco += Integer.parseInt(formulario.getnPropriedades());

                nPropriedades.put("foco", nFoco);

                item(formulario.getDirecionamento(), direcionamentoVigilancia);
                item(formulario.getCadastroMorcegos(), cadastroMonitoramento);
                item(formulario.getCapturaMorcegos(), capturaMorcegos);
                item(formulario.getProcedimentosAdotados(), procedimentosAdotadosFoco);
            }
        }

        return div(
                h4(attrs(".titulo-form"), "2.4.3 Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)"),
                tabelaAvaliacao("Avaliação", "pncrh_av", avaliacaoPNCRH),
                div(attrs(".col-md-6")).withId("grafico_pncrh_av"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Fiscalização de vacinações"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Procedimentos de comprovação de vacinação"),
                                td(attrs(".text-center"), "Relatórios de cobertura vacinal")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("EA").toString()).withId("procedimentoComprovacaoPNCRHEA"),
                                td(attrs(".text-center"), relatorioCobertura.get("EA").toString()).withId("relatorioCoberturaPNCRHEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("ED").toString()).withId("procedimentoComprovacaoPNCRHED"),
                                td(attrs(".text-center"), relatorioCobertura.get("ED").toString()).withId("relatorioCoberturaPNCRHED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("NE").toString()).withId("procedimentoComprovacaoPNCRHNE"),
                                td(attrs(".text-center"), relatorioCobertura.get("NE").toString()).withId("relatorioCoberturaPNCRHNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), procedimentosComprovacao.get("NA").toString()).withId("procedimentoComprovacaoPNCRHNA"),
                                td(attrs(".text-center"), relatorioCobertura.get("NA").toString()).withId("relatorioCoberturaPNCRHNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pncrh_fiscalizacao"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Vigilância Epidemiológica"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Direcionamento das ações de vigilância"),
                                td(attrs(".text-center"), "Cadastro e monitoramento de abrigos de morcegos hematófagos"),
                                td(attrs(".text-center"), "Captura de morcegos"),
                                td(attrs(".text-center"), "Procedimentos adotados Foco")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("EA").toString()).withId("direcionamentoVigilanciaPNCRHEA"),
                                td(attrs(".text-center"), cadastroMonitoramento.get("EA").toString()).withId("cadastroMonitoramentoPNCRHEA"),
                                td(attrs(".text-center"), capturaMorcegos.get("EA").toString()).withId("capturaMorcegosPNCRHEA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("EA").toString()).withId("procedimentosAdotadosFocoPNCRHEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("ED").toString()).withId("direcionamentoVigilanciaPNCRHED"),
                                td(attrs(".text-center"), cadastroMonitoramento.get("ED").toString()).withId("cadastroMonitoramentoPNCRHED"),
                                td(attrs(".text-center"), capturaMorcegos.get("ED").toString()).withId("capturaMorcegosPNCRHED"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("ED").toString()).withId("procedimentosAdotadosFocoPNCRHED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("NE").toString()).withId("direcionamentoVigilanciaPNCRHNE"),
                                td(attrs(".text-center"), cadastroMonitoramento.get("NE").toString()).withId("cadastroMonitoramentoPNCRHNE"),
                                td(attrs(".text-center"), capturaMorcegos.get("NE").toString()).withId("capturaMorcegosPNCRHNE"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NE").toString()).withId("procedimentosAdotadosFocoPNCRHNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("NA").toString()).withId("direcionamentoVigilanciaPNCRHNA"),
                                td(attrs(".text-center"), cadastroMonitoramento.get("NA").toString()).withId("cadastroMonitoramentoPNCRHNA"),
                                td(attrs(".text-center"), capturaMorcegos.get("NA").toString()).withId("capturaMorcegosPNCRHNA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NA").toString()).withId("procedimentosAdotadosFocoPNCRHNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pncrh_vigilancia"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Propriedades sob monitoramento para saneamento de foco"),
                        tbody(
                                tr(td(attrs(".text-center"), "Propriedade Foco")),
                                tr(td(attrs(".text-center"), nPropriedades.get("foco").toString()).withId("procedimentosAdotadosFocoPNCRH"))
                        )))
        );
    }

    public Tag PNEEB(List<Supervisao> list) {

        //2.4.4 Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)
        HashMap<String, Integer> avaliacaoPNEEB = new HashMap<>(hashAvaliacaoPadrao);

        //B - VIGILÂNCIA EPIDEMIOLÓGICA
        HashMap<String, Integer> direcionamentoVigilancia = new HashMap<>(hashPadrao);
        HashMap<String, Integer> identificacaoAreas = new HashMap<>(hashPadrao);
        HashMap<String, Integer> inspecoesPropriedade = new HashMap<>(hashPadrao);
        HashMap<String, Integer> fiscalizacaoMatadouro = new HashMap<>(hashPadrao);
        HashMap<String, Integer> fiscalizacaoGraxaria = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentosAdotadosFoco = new HashMap<>(hashPadrao);
        HashMap<String, Integer> nPropriedades = new HashMap<>();

        int nFoco = 0;

        for (Supervisao supervisao : list) {

            if (supervisao.getHashRespostas().containsKey("pneeb_vigilancia")) {
                FormPNEEBVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pneeb_vigilancia").getResposta(), FormPNEEBVigilancia.class);

                //------------------Avaliação-------
                ava(formulario.getAvaliacao(), avaliacaoPNEEB);

                //------------------Itens-------
                nFoco += Integer.parseInt(formulario.getnPropriedades());

                nPropriedades.put("foco", nFoco);

                item(formulario.getDirecionamento(), direcionamentoVigilancia);
                item(formulario.getIdentificacao(), identificacaoAreas);
                item(formulario.getInspecoes(), inspecoesPropriedade);
                item(formulario.getFiscalizacaoMatadouro(), fiscalizacaoMatadouro);
                item(formulario.getFiscalizacaograxaria(), fiscalizacaoGraxaria);
                item(formulario.getProcedimentosAdotados(), procedimentosAdotadosFoco);
            }
        }

        return div(
                h4(attrs(".titulo-form"), "2.4.4 Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)"),
                tabelaAvaliacao("Avaliação", "pneeb_av", avaliacaoPNEEB),
                div(attrs(".col-md-6")).withId("grafico_pneeb_av"),
                table(attrs(".table .table-condensed"),
                        caption("Vigilância Epidemiológica"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Direcionamento das ações de vigilância"),
                                td(attrs(".text-center"), "Identificação de áreas segundo os fatores de risco"),
                                td(attrs(".text-center"), "Inspeções em propriedades"),
                                td(attrs(".text-center"), "Fiscalização em matadouro"),
                                td(attrs(".text-center"), "Fiscalização em graxaria"),
                                td(attrs(".text-center"), "Procedimentos adotados Foco")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("EA").toString()).withId("direcionamentoVigilanciaPNEEBEA"),
                                td(attrs(".text-center"), identificacaoAreas.get("EA").toString()).withId("identificacaoAreasPNEEBEA"),
                                td(attrs(".text-center"), inspecoesPropriedade.get("EA").toString()).withId("inspecoesPropriedadePNEEBEA"),
                                td(attrs(".text-center"), fiscalizacaoMatadouro.get("EA").toString()).withId("fiscalizacaoMatadouroPNEEBEA"),
                                td(attrs(".text-center"), fiscalizacaoGraxaria.get("EA").toString()).withId("fiscalizacaoGraxariaPNEEBEA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("EA").toString()).withId("procedimentosAdotadosFocoPNEEBEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("ED").toString()).withId("direcionamentoVigilanciaPNEEBED"),
                                td(attrs(".text-center"), identificacaoAreas.get("ED").toString()).withId("identificacaoAreasPNEEBED"),
                                td(attrs(".text-center"), inspecoesPropriedade.get("ED").toString()).withId("inspecoesPropriedadePNEEBED"),
                                td(attrs(".text-center"), fiscalizacaoMatadouro.get("ED").toString()).withId("fiscalizacaoMatadouroPNEEBED"),
                                td(attrs(".text-center"), fiscalizacaoGraxaria.get("ED").toString()).withId("fiscalizacaoGraxariaPNEEBED"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("ED").toString()).withId("procedimentosAdotadosFocoPNEEBED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("NE").toString()).withId("direcionamentoVigilanciaPNEEBNE"),
                                td(attrs(".text-center"), identificacaoAreas.get("NE").toString()).withId("identificacaoAreasPNEEBNE"),
                                td(attrs(".text-center"), inspecoesPropriedade.get("NE").toString()).withId("inspecoesPropriedadePNEEBNE"),
                                td(attrs(".text-center"), fiscalizacaoMatadouro.get("NE").toString()).withId("fiscalizacaoMatadouroPNEEBNE"),
                                td(attrs(".text-center"), fiscalizacaoGraxaria.get("NE").toString()).withId("fiscalizacaoGraxariaPNEEBNE"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NE").toString()).withId("procedimentosAdotadosFocoPNEEBNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("NA").toString()).withId("direcionamentoVigilanciaPNEEBNA"),
                                td(attrs(".text-center"), identificacaoAreas.get("NA").toString()).withId("identificacaoAreasPNEEBNA"),
                                td(attrs(".text-center"), inspecoesPropriedade.get("NA").toString()).withId("inspecoesPropriedadePNEEBNA"),
                                td(attrs(".text-center"), fiscalizacaoMatadouro.get("NA").toString()).withId("fiscalizacaoMatadouroPNEEBNA"),
                                td(attrs(".text-center"), fiscalizacaoGraxaria.get("NA").toString()).withId("fiscalizacaoGraxariaPNEEBNA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NA").toString()).withId("procedimentosAdotadosFocoPNEEBNA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_pneeb_vigilancia"),
                table(attrs(".table .table-condensed"),
                        caption("Propriedades sob monitoramento para saneamento de foco"),
                        tbody(
                                tr(td(attrs(".text-center"), "Propriedade Foco")),
                                tr(td(attrs(".text-center"), nPropriedades.get("foco").toString()))
                        ))
        );
    }

    public Tag PNSE(List<Supervisao> list) {

        //2.4.5 Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)
        HashMap<String, Integer> avaliacaoPNSE = new HashMap<>(hashAvaliacaoPadrao);

        //A - CADASTRO DE ESTABELECIMENTOS
        HashMap<String, Integer> estabelecimentoCadastrado = new HashMap<>(hashPadrao);
        HashMap<String, Integer> atualizacaoCadastral = new HashMap<>(hashPadrao);

        //B - VIGILÂNCIA EPIDEMIOLÓGICA
        HashMap<String, Integer> direcionamentoVigilancia = new HashMap<>(hashPadrao);
        HashMap<String, Integer> vigilanciaPropriedades = new HashMap<>(hashPadrao);
        HashMap<String, Integer> doencaNotificacaoObrigatoria = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentosAdotadosFoco = new HashMap<>(hashPadrao);
        HashMap<String, Integer> nPropriedades = new HashMap<>();

        //C - CONTROLES DO PROGRAMA
        HashMap<String, Integer> relacaoVetCadastrados = new HashMap<>(hashPadrao);
        HashMap<String, Integer> relatorioVetCadastrados = new HashMap<>(hashPadrao);
        HashMap<String, Integer> nProcessos = new HashMap<>();

        int nFoco = 0;
        int nProcessoAntigo = 0;
        int nProcessoNovo = 0;

        for (Supervisao supervisao : list) {

            if (supervisao.getHashRespostas().containsKey("pnse_cadastro_estabelecimentos")) {
                FormPNSECadastroEstabelecimento formulario = gson.fromJson(supervisao.getHashRespostas().get("pnse_cadastro_estabelecimentos").getResposta(), FormPNSECadastroEstabelecimento.class);

                //------------------Avaliação-------
                ava(formulario.getAvaliacao(), avaliacaoPNSE);

                //------------------Itens-------
                item(formulario.getEstabelecimentoCadastrado(), estabelecimentoCadastrado);
                item(formulario.getAtualizacaoCadastral(), atualizacaoCadastral);
            }

            if (supervisao.getHashRespostas().containsKey("pnse_vigilancia")) {
                FormPNSEVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnse_vigilancia").getResposta(), FormPNSEVigilancia.class);

                //------------------Itens-------
                nFoco += Integer.parseInt(formulario.getnPropriedade());

                nPropriedades.put("foco", nFoco);

                item(formulario.getDirecionamento(), direcionamentoVigilancia);
                item(formulario.getVigilancia(), vigilanciaPropriedades);
                item(formulario.getDoencaNotificacao(), doencaNotificacaoObrigatoria);
                item(formulario.getProcedimentoAdotado(), procedimentosAdotadosFoco);
            }

            if (supervisao.getHashRespostas().containsKey("pnse_controles")) {
                FormPNSEControles formulario = gson.fromJson(supervisao.getHashRespostas().get("pnse_controles").getResposta(), FormPNSEControles.class);

                //------------------Itens-------
                nProcessoAntigo += Integer.parseInt(formulario.getQuantidadeAntigos());
                nProcessoNovo += Integer.parseInt(formulario.getQuantidadeRecentes());

                nProcessos.put("antigo", nProcessoAntigo);
                nProcessos.put("novo", nProcessoNovo);

                item(formulario.getRelacaoMedicoCadastrados(), relacaoVetCadastrados);
                item(formulario.getRelatorioColheita(), relatorioVetCadastrados);
            }
        }

        return div(
                h4(attrs(".titulo-form"), "2.4.5 Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)"),
                tabelaAvaliacao("Avaliação", "pnse_av", avaliacaoPNSE),
                div(attrs(".col-md-6")).withId("grafico_pnse_av"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Cadastro de Estabelecimentos"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Estabelecimentos de criação cadastrados"),
                                td(attrs(".text-center"), "Atualização cadastral")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("EA").toString()).withId("estabelecimentoCadastradoPNSEEA"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("EA").toString()).withId("atualizacaoCadastralPNSEEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("ED").toString()).withId("estabelecimentoCadastradoPNSEED"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("ED").toString()).withId("atualizacaoCadastralPNSEED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NE").toString()).withId("estabelecimentoCadastradoPNSENE"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("NE").toString()).withId("atualizacaoCadastralPNSENE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NA").toString()).withId("estabelecimentoCadastradoPNSENA"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("NA").toString()).withId("atualizacaoCadastralPNSENA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pnse_cadastro"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Vigilância Epidemiológica"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Direcionamento das ações de vigilância"),
                                td(attrs(".text-center"), "Vigilância em propriedades"),
                                td(attrs(".text-center"), "Doenças de notificação obrigatória"),
                                td(attrs(".text-center"), "Procedimentos adotados Foco")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("EA").toString()).withId("direcionamentoVigilanciaPNSEEA"),
                                td(attrs(".text-center"), vigilanciaPropriedades.get("EA").toString()).withId("vigilanciaPropriedadesPNSEEA"),
                                td(attrs(".text-center"), doencaNotificacaoObrigatoria.get("EA").toString()).withId("notificacaoObrigatoriaPNSEEA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("EA").toString()).withId("procedimentosAdotadosFocoPNSEEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("ED").toString()).withId("direcionamentoVigilanciaPNSEED"),
                                td(attrs(".text-center"), vigilanciaPropriedades.get("ED").toString()).withId("vigilanciaPropriedadesPNSEED"),
                                td(attrs(".text-center"), doencaNotificacaoObrigatoria.get("ED").toString()).withId("notificacaoObrigatoriaPNSEED"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("ED").toString()).withId("procedimentosAdotadosFocoPNSEED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("NE").toString()).withId("direcionamentoVigilanciaPNSENE"),
                                td(attrs(".text-center"), vigilanciaPropriedades.get("NE").toString()).withId("vigilanciaPropriedadesPNSENE"),
                                td(attrs(".text-center"), doencaNotificacaoObrigatoria.get("NE").toString()).withId("notificacaoObrigatoriaPNSENE"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NE").toString()).withId("procedimentosAdotadosFocoPNSENE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), direcionamentoVigilancia.get("NA").toString()).withId("direcionamentoVigilanciaPNSENA"),
                                td(attrs(".text-center"), vigilanciaPropriedades.get("NA").toString()).withId("vigilanciaPropriedadesPNSENA"),
                                td(attrs(".text-center"), doencaNotificacaoObrigatoria.get("NA").toString()).withId("notificacaoObrigatoriaPNSENA"),
                                td(attrs(".text-center"), procedimentosAdotadosFoco.get("NA").toString()).withId("procedimentosAdotadosFocoPNSENA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pnse_vigilancia"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Propriedades sob monitoramento para saneamento de foco"),
                        tbody(
                                tr(td(attrs(".text-center"), "Propriedade Foco")),
                                tr(td(attrs(".text-center"), nPropriedades.get("foco").toString()))
                        ))),
                table(attrs(".table .table-condensed"),
                        caption("Controles do programa"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Relação dos Med Vet cadastrados"),
                                td(attrs(".text-center"), "Relatórios de colheita Med Vet cadastrados")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), relacaoVetCadastrados.get("EA").toString()).withId("relacaoVetCadastradosPNSEEA"),
                                td(attrs(".text-center"), relatorioVetCadastrados.get("EA").toString()).withId("relatorioVetCadastradoPNSEEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), relacaoVetCadastrados.get("ED").toString()).withId("relacaoVetCadastradosPNSEED"),
                                td(attrs(".text-center"), relatorioVetCadastrados.get("ED").toString()).withId("relatorioVetCadastradoPNSEED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), relacaoVetCadastrados.get("NE").toString()).withId("relacaoVetCadastradosPNSENE"),
                                td(attrs(".text-center"), relatorioVetCadastrados.get("NE").toString()).withId("relatorioVetCadastradoPNSENE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), relacaoVetCadastrados.get("NA").toString()).withId("relacaoVetCadastradosPNSENA"),
                                td(attrs(".text-center"), relatorioVetCadastrados.get("NA").toString()).withId("relatorioVetCadastradoPNSENA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_pnse_controles"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Quantidade de processos  no escritório"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), "Antigos"),
                                        td(attrs(".text-center"), "Recentes")
                                ),
                                tr(
                                        td(attrs(".text-center"), nProcessos.get("antigo").toString()),
                                        td(attrs(".text-center"), nProcessos.get("novo").toString())
                                )
                        )))
        );
    }

    public Tag PNSCO(List<Supervisao> list) {

        //2.4.6  Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)
        HashMap<String, Integer> avaliacaoPNSCO = new HashMap<>(hashAvaliacaoPadrao);

        //A - CADASTRO DE ESTABELECIMENTOS
        HashMap<String, Integer> estabelecimentoCadastrado = new HashMap<>(hashPadrao);
        HashMap<String, Integer> atualizacaoCadastral = new HashMap<>(hashPadrao);

        //B - VIGILÂNCIA EPIDEMIOLÓGICA
        HashMap<String, Integer> doencaNotificacaoObrigatoria = new HashMap<>(hashPadrao);
        HashMap<String, Integer> acoesVigilancia = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {

            if (supervisao.getHashRespostas().containsKey("pnsco_cadastro_estabelecimentos")) {
                FormPNSCOCadastros formulario = gson.fromJson(supervisao.getHashRespostas().get("pnsco_cadastro_estabelecimentos").getResposta(), FormPNSCOCadastros.class);

                //------------------Avaliação-------
                ava(formulario.getAvaliacao(), avaliacaoPNSCO);

                //------------------Itens-------
                item(formulario.getEstabelecimentoCadastrado(), estabelecimentoCadastrado);
                item(formulario.getAtualizacaoCadastral(), atualizacaoCadastral);
            }

            if (supervisao.getHashRespostas().containsKey("pnsco_vigilancia")) {
                FormPNSCOVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnsco_vigilancia").getResposta(), FormPNSCOVigilancia.class);

                //------------------Itens-------
                item(formulario.getDoencaNotificacao(), doencaNotificacaoObrigatoria);
                item(formulario.getAcoesVigilancia(), acoesVigilancia);
            }
        }

        return div(
                h4(attrs(".titulo-form"), "2.4.6  Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)"),
                tabelaAvaliacao("Avaliação", "pnsco_av", avaliacaoPNSCO),
                div(attrs(".col-md-6")).withId("grafico_pnsco_av"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Cadastro de Estabelecimentos"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Estabelecimentos de criação cadastrados"),
                                td(attrs(".text-center"), "Atualização cadastral")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("EA").toString()).withId("estabelecimentoCadastradoPNSCOEA"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("EA").toString()).withId("atualizacaoCadastralPNSCOEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("ED").toString()).withId("estabelecimentoCadastradoPNSCOED"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("ED").toString()).withId("atualizacaoCadastralPNSCOED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NE").toString()).withId("estabelecimentoCadastradoPNSCONE"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("NE").toString()).withId("atualizacaoCadastralPNSCONE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NA").toString()).withId("estabelecimentoCadastradoPNSCONA"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("NA").toString()).withId("atualizacaoCadastralPNSCONA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pnsco_cadastro"),
                table(attrs(".table .table-condensed"),
                        caption("Vigilância Epidemiológica"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Doenças de notificação obrigatória"),
                                td(attrs(".text-center"), "Ações de vigilância")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), doencaNotificacaoObrigatoria.get("EA").toString()).withId("doencaNotificacaoObrigatoriaPNSCOEA"),
                                td(attrs(".text-center"), acoesVigilancia.get("EA").toString()).withId("acoesVigilanciaPNSCOEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), doencaNotificacaoObrigatoria.get("ED").toString()).withId("doencaNotificacaoObrigatoriaPNSCOED"),
                                td(attrs(".text-center"), acoesVigilancia.get("ED").toString()).withId("acoesVigilanciaPNSCOED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), doencaNotificacaoObrigatoria.get("NE").toString()).withId("doencaNotificacaoObrigatoriaPNSCONE"),
                                td(attrs(".text-center"), acoesVigilancia.get("NE").toString()).withId("acoesVigilanciaPNSCONE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), doencaNotificacaoObrigatoria.get("NA").toString()).withId("doencaNotificacaoObrigatoriaPNSCONA"),
                                td(attrs(".text-center"), acoesVigilancia.get("NA").toString()).withId("acoesVigilanciaPNSCONA")
                        )
                ),
                div(attrs(".col-md-6")).withId("grafico_pnsco_vigilancia")
        );
    }

    public Tag PNSS(List<Supervisao> list) {

        //2.4.7 Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)
        HashMap<String, Integer> avaliacaoPNSS = new HashMap<>(hashAvaliacaoPadrao);

        //A - CADASTRO DE ESTABELECIMENTOS
        HashMap<String, Integer> estabelecimentoCadastrado = new HashMap<>(hashPadrao);
        HashMap<String, Integer> atualizacaoCadastral = new HashMap<>(hashPadrao);
        HashMap<String, Integer> suideosAlvejados = new HashMap<>(hashPadrao);
        HashMap<String, Integer> nEstabelecimentos = new HashMap<>();

        //B - VIGILÂNCIA EPIDEMIOLÓGICA
        HashMap<String, Integer> direcionamentoAcoesVigilancia = new HashMap<>(hashPadrao);
        HashMap<String, Integer> propriedadesRisco = new HashMap<>(hashPadrao);
        HashMap<String, Integer> registroVigilancia = new HashMap<>(hashPadrao);
        HashMap<String, Integer> mortandade = new HashMap<>(hashPadrao);
        HashMap<String, Integer> nEstabelecimentosVigilancia = new HashMap<>();

        int nGranja = 0;
        int nSubsistencia = 0;

        int nGranjaExistente = 0;
        int nGranjaVisitada = 0;
        int nSubsistenciaExistente = 0;
        int nSubsistenciaVisitada = 0;

        for (Supervisao supervisao : list) {

            if (supervisao.getHashRespostas().containsKey("pnss_cadastro_estabelecimentos")) {
                FormPNSSCadastroEstabelecimento formulario = gson.fromJson(supervisao.getHashRespostas().get("pnss_cadastro_estabelecimentos").getResposta(), FormPNSSCadastroEstabelecimento.class);

                //------------------Itens-------
                nGranja += Integer.parseInt(formulario.getnGranjas());
                nSubsistencia += Integer.parseInt(formulario.getnCriatorios());

                nEstabelecimentos.put("granja", nGranja);
                nEstabelecimentos.put("subsistencia", nSubsistencia);

                item(formulario.getEstabelecimentoCadastrados(), estabelecimentoCadastrado);
                item(formulario.getAtualizacaoCadastral(), atualizacaoCadastral);
                item(formulario.getSuideos(), suideosAlvejados);
            }

            if (supervisao.getHashRespostas().containsKey("pnss_vigilancia")) {
                FormPNSSVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnss_vigilancia").getResposta(), FormPNSSVigilancia.class);

                //------------------Avaliação-------
                ava(formulario.getAvaliacao(), avaliacaoPNSS);

                //------------------Itens-------
                nGranjaExistente += Integer.parseInt(formulario.getnGranjaExistente());
                nGranjaVisitada += Integer.parseInt(formulario.getnGranjaVisitadas());
                nSubsistenciaExistente += Integer.parseInt(formulario.getnCriatoriosExistentes());
                nSubsistenciaVisitada += Integer.parseInt(formulario.getnCriatoriosVisitadas());

                nEstabelecimentosVigilancia.put("granjaExistente", nGranjaExistente);
                nEstabelecimentosVigilancia.put("granjaExistente", nGranjaVisitada);
                nEstabelecimentosVigilancia.put("subsistenciaExistente", nSubsistenciaExistente);
                nEstabelecimentosVigilancia.put("subsistenciaVisitada", nSubsistenciaVisitada);

                item(formulario.getDirecionamento(), direcionamentoAcoesVigilancia);
                item(formulario.getPropriedades(), propriedadesRisco);
                item(formulario.getRegistroVigilancia(), registroVigilancia);
                item(formulario.getMortandade(), mortandade);
            }
        }

        return div(
                h4(attrs(".titulo-form"), "2.4.7 Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)"),
                tabelaAvaliacao("Avaliação", "pnss_av", avaliacaoPNSS),
                div(attrs(".col-md-6")).withId("grafico_pnss_av"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Cadastro de Estabelecimentos"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Estabelecimentos de criação cadastrados"),
                                td(attrs(".text-center"), "Suídeos asselvajados"),
                                td(attrs(".text-center"), "Atualização cadastral")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("EA").toString()).withId("estabelecimentoCadastradoPNSSEA"),
                                td(attrs(".text-center"), suideosAlvejados.get("EA").toString()).withId("suideosAlvejadosPNSSEA"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("EA").toString()).withId("atualizacaoCadastralPNSSEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("ED").toString()).withId("estabelecimentoCadastradoPNSSED"),
                                td(attrs(".text-center"), suideosAlvejados.get("ED").toString()).withId("suideosAlvejadosPNSSED"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("ED").toString()).withId("atualizacaoCadastralPNSSED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NE").toString()).withId("estabelecimentoCadastradoPNSSNE"),
                                td(attrs(".text-center"), suideosAlvejados.get("NE").toString()).withId("suideosAlvejadosPNSSNE"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("NE").toString()).withId("atualizacaoCadastralPNSSNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NA").toString()).withId("estabelecimentoCadastradoPNSSNA"),
                                td(attrs(".text-center"), suideosAlvejados.get("NA").toString()).withId("suideosAlvejadosPNSSNA"),
                                td(attrs(".text-center"), atualizacaoCadastral.get("NA").toString()).withId("atualizacaoCadastralPNSSNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pnss_cadastro"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), "Nº de Granjas"),
                                        td(attrs(".text-center"), "Nº de Criatórios (subsistência)")
                                ),
                                tr(
                                        td(attrs(".text-center"), nEstabelecimentos.get("granja").toString()),
                                        td(attrs(".text-center"), nEstabelecimentos.get("subsistencia").toString())
                                )
                        ))),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Vigilância Epidemiológica"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Direcionamento das ações de vigilância"),
                                td(attrs(".text-center"), "Propriedades de risco"),
                                td(attrs(".text-center"), "Registro da vigilância em propriedades e pontos de risco"),
                                td(attrs(".text-center"), "Mortandade")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("EA").toString()).withId("direcionamentoAcoesVigilanciaPNSSEA"),
                                td(attrs(".text-center"), propriedadesRisco.get("EA").toString()).withId("propriedadeRiscoPNSSEA"),
                                td(attrs(".text-center"), registroVigilancia.get("EA").toString()).withId("registroVigilanciaPNSSEA"),
                                td(attrs(".text-center"), mortandade.get("EA").toString()).withId("mortandadePNSSEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("ED").toString()).withId("direcionamentoAcoesVigilanciaPNSSED"),
                                td(attrs(".text-center"), propriedadesRisco.get("ED").toString()).withId("propriedadeRiscoPNSSED"),
                                td(attrs(".text-center"), registroVigilancia.get("ED").toString()).withId("registroVigilanciaPNSSED"),
                                td(attrs(".text-center"), mortandade.get("ED").toString()).withId("mortandadePNSSED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("NE").toString()).withId("direcionamentoAcoesVigilanciaPNSSNE"),
                                td(attrs(".text-center"), propriedadesRisco.get("NE").toString()).withId("propriedadeRiscoPNSSNE"),
                                td(attrs(".text-center"), registroVigilancia.get("NE").toString()).withId("registroVigilanciaPNSSNE"),
                                td(attrs(".text-center"), mortandade.get("NE").toString()).withId("mortandadePNSSNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), direcionamentoAcoesVigilancia.get("NA").toString()).withId("direcionamentoAcoesVigilanciaPNSSNA"),
                                td(attrs(".text-center"), propriedadesRisco.get("NA").toString()).withId("propriedadeRiscoPNSSNA"),
                                td(attrs(".text-center"), registroVigilancia.get("NA").toString()).withId("registroVigilanciaPNSSNA"),
                                td(attrs(".text-center"), mortandade.get("NA").toString()).withId("mortandadePNSSNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pnss_vigilancia"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), "Nº de Granjas").attr("colspan", 2),
                                        td(attrs(".text-center"), "Nº de Criatórios (subsistência)").attr("colspan", 2)
                                ),
                                tr(
                                        td(attrs(".text-center"), "Existentes"),
                                        td(attrs(".text-center"), "Visitadas"),
                                        td(attrs(".text-center"), "Existentes"),
                                        td(attrs(".text-center"), "Visitadas")
                                ),
                                tr(
                                        td(attrs(".text-center"), nEstabelecimentosVigilancia.get("granjaExistente").toString()),
                                        td(attrs(".text-center"), nEstabelecimentosVigilancia.get("granjaExistente").toString()),
                                        td(attrs(".text-center"), nEstabelecimentosVigilancia.get("subsistenciaExistente").toString()),
                                        td(attrs(".text-center"), nEstabelecimentosVigilancia.get("subsistenciaVisitada").toString())
                                )
                        )))
        );
    }

    public Tag PNSA(List<Supervisao> list) {

        //2.4.8 Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)
        HashMap<String, Integer> avaliacaoPNSA = new HashMap<>(hashAvaliacaoPadrao);

        //A - CADASTRO DE ESTABELECIMENTOS
        HashMap<String, Integer> estabelecimentoCadastrado = new HashMap<>(hashPadrao);
        HashMap<String, Integer> procedimentosRelacionados = new HashMap<>(hashPadrao);

        HashMap<String, Integer> numerosEstabelecimentos = new HashMap<>();

        //B - VIGILÂNCIA EPIDEMIOLÓGICA
        HashMap<String, Integer> direcionamentoAcoes = new HashMap<>(hashPadrao);
        HashMap<String, Integer> pontosRisco = new HashMap<>(hashPadrao);
        HashMap<String, Integer> sitiosMigratorios = new HashMap<>(hashPadrao);
        HashMap<String, Integer> estbelecimentoComercialCadastrado = new HashMap<>(hashPadrao);
        HashMap<String, Integer> estabelecimentoComercialRegistrado = new HashMap<>(hashPadrao);
        HashMap<String, Integer> estabelecimentoVendaAves = new HashMap<>(hashPadrao);
        HashMap<String, Integer> mortandade = new HashMap<>(hashPadrao);
        HashMap<String, Integer> doencaNotificacao = new HashMap<>(hashPadrao);

        int nComerciaisCadastrados = 0;
        int nComerciaisRegistrados = 0;
        int nSubsistencia = 0;
        int nEstabelecimentoVenda = 0;

        for (Supervisao supervisao : list) {

            if (supervisao.getHashRespostas().containsKey("pnsa_cadastro_estabelecimentos")) {
                FormPNSACadastroEstabelecimentos formulario = gson.fromJson(supervisao.getHashRespostas().get("pnsa_cadastro_estabelecimentos").getResposta(), FormPNSACadastroEstabelecimentos.class);

                //------------------Itens-------
                item(formulario.getEstabelecimentos(), estabelecimentoCadastrado);
                item(formulario.getProcedimentos(), procedimentosRelacionados);

                nComerciaisCadastrados += Integer.parseInt(formulario.getnAviculturaComeciaisCadastrados());
                nComerciaisRegistrados += Integer.parseInt(formulario.getnAviculturaComerciaisRegistrados());
                nSubsistencia += Integer.parseInt(formulario.getnAviculturaSubsistencia());
                nEstabelecimentoVenda += Integer.parseInt(formulario.getnAviculturaVendaVivas());

                numerosEstabelecimentos.put("nComerciaisCadastrados", nComerciaisCadastrados);
                numerosEstabelecimentos.put("nComerciaisRegistrados", nComerciaisRegistrados);
                numerosEstabelecimentos.put("nSubsistencia", nSubsistencia);
                numerosEstabelecimentos.put("nEstabelecimentoVenda", nEstabelecimentoVenda);

            }

            if (supervisao.getHashRespostas().containsKey("pnsa_vigilancia")) {
                FormPNSAVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnsa_vigilancia").getResposta(), FormPNSAVigilancia.class);

                //------------------Avaliação-------
                ava(formulario.getAvaliacao(), avaliacaoPNSA);

                //------------------Itens-------
                item(formulario.getDirecionamento(), direcionamentoAcoes);
                item(formulario.getPontoRisco(), pontosRisco);
                item(formulario.getSitioMigratorio(), sitiosMigratorios);
                item(formulario.getCadastrado(), estbelecimentoComercialCadastrado);
                item(formulario.getRegistrado(), estabelecimentoComercialRegistrado);
                item(formulario.getVendasAvesVivas(), estabelecimentoVendaAves);
                item(formulario.getMortandade(), mortandade);
                item(formulario.getDoencaNotificacao(), doencaNotificacao);
            }
        }

        return div(
                h4(attrs(".titulo-form"), "2.4.8 Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)"),
                tabelaAvaliacao("Avaliação", "pnsa_av", avaliacaoPNSA),
                div(attrs(".col-md-6")).withId("grafico_pnsa_av"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        caption("Cadastro de Estabelecimentos"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Estabelecimentos de criação cadastrados"),
                                td(attrs(".text-center"), "Procedimentos relacionados ao cadastramento")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("EA").toString()).withId("estabelecimentoCadastradoPNSAEA"),
                                td(attrs(".text-center"), procedimentosRelacionados.get("EA").toString()).withId("procedimentosRelacionadosPNSAEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("ED").toString()).withId("estabelecimentoCadastradoPNSAED"),
                                td(attrs(".text-center"), procedimentosRelacionados.get("ED").toString()).withId("procedimentosRelacionadosPNSAED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NE").toString()).withId("estabelecimentoCadastradoPNSANE"),
                                td(attrs(".text-center"), procedimentosRelacionados.get("NE").toString()).withId("procedimentosRelacionadosPNSANE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NA").toString()).withId("estabelecimentoCadastradoPNSANA"),
                                td(attrs(".text-center"), procedimentosRelacionados.get("NA").toString()).withId("procedimentosRelacionadosPNSANA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_pnsa_cadastro"),
                table(attrs(".table .table-condensed"),
                        tbody(
                                tr(
                                        td(attrs(".text-center"), "N° de estabelecimentos de avicultura existentes").attr("colspan", 4)
                                ),
                                tr(
                                        td(attrs(".text-center"), "comerciais somente cadastrados (granjas corte/postura)"),
                                        td(attrs(".text-center"), "comerciais registrados (granjas corte/postura)"),
                                        td(attrs(".text-center"), "Subsistência"),
                                        td(attrs(".text-center"), "Estabelecimentos de venda de aves vivas (entreposto, distribuidores, casas agropecuárias, etc)")
                                ),
                                tr(
                                        td(attrs(".text-center"), numerosEstabelecimentos.get("nComerciaisCadastrados").toString()),
                                        td(attrs(".text-center"), numerosEstabelecimentos.get("nComerciaisRegistrados").toString()),
                                        td(attrs(".text-center"), numerosEstabelecimentos.get("nSubsistencia").toString()),
                                        td(attrs(".text-center"), numerosEstabelecimentos.get("nEstabelecimentoVenda").toString())
                                )
                        )),
                table(attrs(".table .table-condensed"),
                        caption("Vigilância Epidemiológica"),
                        tr(
                                td(attrs(".text-center"), " ").attr("rowspan", 2),
                                td(attrs(".text-center"), "Direcionamento das ações de vigilância").attr("rowspan", 2),
                                td(attrs(".text-center"), "Pontos de risco").attr("rowspan", 2),
                                td(attrs(".text-center"), "Sítios migratórios").attr("rowspan", 2),
                                td(attrs(".text-center"), "Estabelecimentos de avicultura comercial").attr("colspan", 2),
                                td(attrs(".text-center"), "Estabelecimentos de venda de aves vivas (entreposto, distribuidores, casas agropecuárias, etc)").attr("rowspan", 2),
                                td(attrs(".text-center"), "Mortandade").attr("rowspan", 2),
                                td(attrs(".text-center"), "Doenças de notificação obrigatória").attr("rowspan", 2)
                        ),
                        tr(
                                td(attrs(".text-center"), "Somente Cadastrados"),
                                td(attrs(".text-center"), "Registrados")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), direcionamentoAcoes.get("EA").toString()).withId("direcionamentoAcoesPNSAEA"),
                                td(attrs(".text-center"), pontosRisco.get("EA").toString()).withId("pontoRiscoPNSAEA"),
                                td(attrs(".text-center"), sitiosMigratorios.get("EA").toString()).withId("sitiosMigratoriosPNSAEA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("EA").toString()).withId("estabelecimentoCadastradoPNSAEA"),
                                td(attrs(".text-center"), estabelecimentoComercialRegistrado.get("EA").toString()).withId("estabelecimentoComercialRegistradoPNSAEA"),
                                td(attrs(".text-center"), estabelecimentoVendaAves.get("EA").toString()).withId("estabelecimentoVendaAvesPNSAEA"),
                                td(attrs(".text-center"), mortandade.get("EA").toString()).withId("mortandadePNSAEA"),
                                td(attrs(".text-center"), doencaNotificacao.get("EA").toString()).withId("doencaNotificacaoPNSAEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), direcionamentoAcoes.get("ED").toString()).withId("direcionamentoAcoesPNSAED"),
                                td(attrs(".text-center"), pontosRisco.get("ED").toString()).withId("pontoRiscoPNSAED"),
                                td(attrs(".text-center"), sitiosMigratorios.get("ED").toString()).withId("sitiosMigratoriosPNSAED"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("ED").toString()).withId("estabelecimentoCadastradoPNSAED"),
                                td(attrs(".text-center"), estabelecimentoComercialRegistrado.get("ED").toString()).withId("estabelecimentoComercialRegistradoPNSAED"),
                                td(attrs(".text-center"), estabelecimentoVendaAves.get("ED").toString()).withId("estabelecimentoVendaAvesPNSAED"),
                                td(attrs(".text-center"), mortandade.get("ED").toString()).withId("mortandadePNSAED"),
                                td(attrs(".text-center"), doencaNotificacao.get("ED").toString()).withId("doencaNotificacaoPNSAED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), direcionamentoAcoes.get("NE").toString()).withId("direcionamentoAcoesPNSANE"),
                                td(attrs(".text-center"), pontosRisco.get("NE").toString()).withId("pontoRiscoPNSANE"),
                                td(attrs(".text-center"), sitiosMigratorios.get("NE").toString()).withId("sitiosMigratoriosPNSANE"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NE").toString()).withId("estabelecimentoCadastradoPNSANE"),
                                td(attrs(".text-center"), estabelecimentoComercialRegistrado.get("NE").toString()).withId("estabelecimentoComercialRegistradoPNSANE"),
                                td(attrs(".text-center"), estabelecimentoVendaAves.get("NE").toString()).withId("estabelecimentoVendaAvesPNSANE"),
                                td(attrs(".text-center"), mortandade.get("NE").toString()).withId("mortandadePNSANE"),
                                td(attrs(".text-center"), doencaNotificacao.get("NE").toString()).withId("doencaNotificacaoPNSANE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), direcionamentoAcoes.get("NA").toString()).withId("direcionamentoAcoesPNSANA"),
                                td(attrs(".text-center"), pontosRisco.get("NA").toString()).withId("pontoRiscoPNSANA"),
                                td(attrs(".text-center"), sitiosMigratorios.get("NA").toString()).withId("sitiosMigratoriosPNSANA"),
                                td(attrs(".text-center"), estabelecimentoCadastrado.get("NA").toString()).withId("estabelecimentoCadastradoPNSANA"),
                                td(attrs(".text-center"), estabelecimentoComercialRegistrado.get("NA").toString()).withId("estabelecimentoComercialRegistradoPNSANA"),
                                td(attrs(".text-center"), estabelecimentoVendaAves.get("NA").toString()).withId("estabelecimentoVendaAvesPNSANA"),
                                td(attrs(".text-center"), mortandade.get("NA").toString()).withId("mortandadePNSANA"),
                                td(attrs(".text-center"), doencaNotificacao.get("NA").toString()).withId("doencaNotificacaoPNSANA")
                        )
                )
                ,div(attrs(".col-md-6")).withId("grafico_pnsa_vigilancia")
        );
    }

    public Tag InteracaoComunidade(List<Supervisao> list) {

        //3.1.1 Educação sanitária e comunicação social (divulgação e publicidade)
        HashMap<String, Integer> avaliacaoInteracaoEducacaoSanitaria = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> materialEducacaoSanitaria = new HashMap<>(hashPadrao);
        HashMap<String, Integer> acoesEducacaoSanitaria = new HashMap<>(hashPadrao);
        HashMap<String, Integer> diagnosticoEducativo = new HashMap<>(hashPadrao);
        HashMap<String, Integer> comunicacaoSocial = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormICEducacaoSanitaria formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_comunidade_educacao").getResposta(), FormICEducacaoSanitaria.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoInteracaoEducacaoSanitaria);

            //------------------Itens-------
            item(formulario.getMaterial(), materialEducacaoSanitaria);
            item(formulario.getAcaoEducacao(), acoesEducacaoSanitaria);
            item(formulario.getDiagnostico(), diagnosticoEducativo);
            item(formulario.getComunicacao(), comunicacaoSocial);
        }

        //3.1.2 Participação com a comunidade 
        HashMap<String, Integer> avaliacaoInteracaoComunidade = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> interacaoComunidade = new HashMap<>(hashPadrao);
        HashMap<String, Integer> comusa = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormICParticipacaoComunidade formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoInteracaoComunidade);

            //------------------Itens-------
            item(formulario.getInteracaoComunidade(), interacaoComunidade);
            item(formulario.getComusa(), comusa);
        }

        //3.1.3 Participação com instituições e representações
        HashMap<String, Integer> avaliacaoInteracaoInstituicoes = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> interacaoInstituicoes = new HashMap<>(hashPadrao);
        HashMap<String, Integer> parcerias = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormICParticipacaoInstituicoes formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoInteracaoInstituicoes);

            //------------------Itens-------
            item(formulario.getInteracaoInstituicao(), interacaoInstituicoes);
            item(formulario.getParcerias(), parcerias);
        }

        return div(
                h4(attrs(".titulo-form"), "3.1 INTERAÇÃO COM A COMUNIDADE"),
                h4(attrs(".titulo-form"), "3.1.1 Educação sanitária e comunicação social (divulgação e publicidade)"),
                tabelaAvaliacao("Avaliação", "ic_av_educacao", avaliacaoInteracaoEducacaoSanitaria),
                div(attrs(".col-md-6")).withId("grafico_ic_av_educacao"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Material de Educação Sanitária e Comunicação Social"),
                                td(attrs(".text-center"), "Ações de Educação Sanitária"),
                                td(attrs(".text-center"), "Diagnóstico Educativo"),
                                td(attrs(".text-center"), "Comunicação Social")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), materialEducacaoSanitaria.get("EA").toString()).withId("materialEducacaoSanitariaEA"),
                                td(attrs(".text-center"), acoesEducacaoSanitaria.get("EA").toString()).withId("acoesEducacaoSanitariaEA"),
                                td(attrs(".text-center"), diagnosticoEducativo.get("EA").toString()).withId("diagnosticoEducativoEA"),
                                td(attrs(".text-center"), comunicacaoSocial.get("EA").toString()).withId("comunicacaoSocialEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), materialEducacaoSanitaria.get("ED").toString()).withId("materialEducacaoSanitariaED"),
                                td(attrs(".text-center"), acoesEducacaoSanitaria.get("ED").toString()).withId("acoesEducacaoSanitariaED"),
                                td(attrs(".text-center"), diagnosticoEducativo.get("ED").toString()).withId("diagnosticoEducativoED"),
                                td(attrs(".text-center"), comunicacaoSocial.get("ED").toString()).withId("comunicacaoSocialED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), materialEducacaoSanitaria.get("NE").toString()).withId("materialEducacaoSanitariaNE"),
                                td(attrs(".text-center"), acoesEducacaoSanitaria.get("NE").toString()).withId("acoesEducacaoSanitariaNE"),
                                td(attrs(".text-center"), diagnosticoEducativo.get("NE").toString()).withId("diagnosticoEducativoNE"),
                                td(attrs(".text-center"), comunicacaoSocial.get("NE").toString()).withId("comunicacaoSocialNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), materialEducacaoSanitaria.get("NA").toString()).withId("materialEducacaoSanitariaNA"),
                                td(attrs(".text-center"), acoesEducacaoSanitaria.get("NA").toString()).withId("acoesEducacaoSanitariaNA"),
                                td(attrs(".text-center"), diagnosticoEducativo.get("NA").toString()).withId("diagnosticoEducativoNA"),
                                td(attrs(".text-center"), comunicacaoSocial.get("NA").toString()).withId("comunicacaoSocialNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_ic_educacao"),
                h4(attrs(".titulo-form"), "3.1.2 Participação com a comunidade"),
                tabelaAvaliacao("Avaliação", "ic_av_comunidade", avaliacaoInteracaoComunidade),
                div(attrs(".col-md-6")).withId("grafico_ic_av_comunidade"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Interação com a comunidade"),
                                td(attrs(".text-center"), "COMUSA")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), interacaoComunidade.get("EA").toString()).withId("interacaoComunidadeEA"),
                                td(attrs(".text-center"), comusa.get("EA").toString()).withId("comusaEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), interacaoComunidade.get("ED").toString()).withId("interacaoComunidadeED"),
                                td(attrs(".text-center"), comusa.get("ED").toString()).withId("comusaED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), interacaoComunidade.get("NE").toString()).withId("interacaoComunidadeNE"),
                                td(attrs(".text-center"), comusa.get("NE").toString()).withId("comusaNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), interacaoComunidade.get("NA").toString()).withId("interacaoComunidadeNA"),
                                td(attrs(".text-center"), comusa.get("NA").toString()).withId("comusaNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_ic_comunidade"),
                h4(attrs(".titulo-form"), "3.1.3 Participação com instituições e representações"),
                tabelaAvaliacao("Avaliação", "ic_av_instituicoes", avaliacaoInteracaoInstituicoes),
                div(attrs(".col-md-6")).withId("grafico_ic_av_instituicoes"),
                div(attrs(".col-md-6"), table(attrs(".table .table-condensed"),
                        tr(
                                td(attrs(".text-center"), " "),
                                td(attrs(".text-center"), "Interação com instituições e representações"),
                                td(attrs(".text-center"), "Parcerias")
                        ),
                        tr(
                                td(attrs(".text-center"), "EA"),
                                td(attrs(".text-center"), interacaoInstituicoes.get("EA").toString()).withId("interacaoInstituicoesEA"),
                                td(attrs(".text-center"), parcerias.get("EA").toString()).withId("parceriasEA")
                        ),
                        tr(
                                td(attrs(".text-center"), "ED"),
                                td(attrs(".text-center"), interacaoInstituicoes.get("ED").toString()).withId("interacaoInstituicoesED"),
                                td(attrs(".text-center"), parcerias.get("ED").toString()).withId("parceriasED")
                        ),
                        tr(
                                td(attrs(".text-center"), "NE"),
                                td(attrs(".text-center"), interacaoInstituicoes.get("NE").toString()).withId("interacaoInstituicoesNE"),
                                td(attrs(".text-center"), parcerias.get("NE").toString()).withId("parceriasNE")
                        ),
                        tr(
                                td(attrs(".text-center"), "NA"),
                                td(attrs(".text-center"), interacaoInstituicoes.get("NA").toString()).withId("interacaoInstituicoesNA"),
                                td(attrs(".text-center"), parcerias.get("NA").toString()).withId("parceriasNA")
                        )
                )),
                div(attrs(".col-md-6")).withId("grafico_ic_instituicoes")
        );
    }

    public Tag InteracaoVeterinario(List<Supervisao> list) {

        //3.2.1 Habilitação e cadastramento dos médicos veterinários
        HashMap<String, Integer> avaliacaoInteracaoVeterinario = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> interacaoVeterinario = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormIMVHabilitacao formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_veterinario_habilitacao").getResposta(), FormIMVHabilitacao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoInteracaoVeterinario);

            //------------------Itens-------
            item(formulario.getInteracaoMV(), interacaoVeterinario);
        }

        return div(
                h4(attrs(".titulo-form"), "3.2 INTERAÇÃO COM OS MÉDICOS VETERINÁRIOS"),
                
                h4(attrs(".titulo-form"), "3.2.1 Habilitação e cadastramento dos médicos veterinários"),
                tabelaAvaliacao("Avaliação", "imv_av_veterinario", avaliacaoInteracaoVeterinario),
                div(attrs(".col-md-6")).withId("grafico_imv_av_veterinario"),
                div(attrs(".col-md-6"), tabelaItem("Interação com MVs cadastrados/habilitados", "imv_veterinario", interacaoVeterinario))
                ,div(attrs(".col-md-6")).withId("grafico_imv_veterinario")
        );
    }

    public Tag InteracaoInstituicoes(List<Supervisao> list) {

        //3.3.1 Sistema de inspeção (seguridade alimentar) 
        HashMap<String, Integer> avaliacaoInteracaoInstituicoes = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> interacaoSistemaInspecao = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            //parei aqui
            FormIISistemaInspecao formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_inspecao").getResposta(), FormIISistemaInspecao.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoInteracaoInstituicoes);

            //------------------Itens-------
            item(formulario.getInteracaoInspecao(), interacaoSistemaInspecao);
        }

        //3.3.2 Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)
        HashMap<String, Integer> avaliacaoInteracaoSUS = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> interacaoSUS = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            //parei aqui
            FormIISUS formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_sus").getResposta(), FormIISUS.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoInteracaoSUS);

            //------------------Itens-------
            item(formulario.getInteracaoSUS(), interacaoSUS);
        }

        return div(
                h4(attrs(".titulo-form"), "3.3 INTERAÇÃO COM INSTITUIÇÕES"),
                
                h4(attrs(".titulo-form"), "3.3.1 Sistema de inspeção (seguridade alimentar)"),
                tabelaAvaliacao("Avaliação", "ii_av_inspecao", avaliacaoInteracaoInstituicoes),
                div(attrs(".col-md-6")).withId("grafico_ii_av_inspecao"),
                div(attrs(".col-md-6"), tabelaItem("Interação com sistema de inspeção", "ii_inspecao", interacaoSistemaInspecao))
                ,div(attrs(".col-md-6")).withId("grafico_ii_inspecao"),
                
                h4(attrs(".titulo-form"), "3.3.2 Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)"),
                tabelaAvaliacao("Avaliação", "ii_av_sus", avaliacaoInteracaoSUS),
                div(attrs(".col-md-6")).withId("grafico_ii_av_sus"),
                div(attrs(".col-md-6"), tabelaItem("Acesso aos mercados", "ii_sus", interacaoSUS))
                ,div(attrs(".col-md-6")).withId("grafico_ii_sus")
        );
    }

    public Tag acessoMercado(List<Supervisao> list) {

        //3.2.1 Habilitação e cadastramento dos médicos veterinários
        HashMap<String, Integer> avaliacaoAcessoMercado = new HashMap<>(hashAvaliacaoPadrao);
        HashMap<String, Integer> acessoMercado = new HashMap<>(hashPadrao);

        for (Supervisao supervisao : list) {
            FormAMAcesso formulario = gson.fromJson(supervisao.getHashRespostas().get("acesso_mercado").getResposta(), FormAMAcesso.class);

            //------------------Avaliação-------
            ava(formulario.getAvaliacao(), avaliacaoAcessoMercado);

            //------------------Itens-------
            item(formulario.getAcesso(), acessoMercado);
        }

        return div(
                tabelaAvaliacao("Avaliação", "am_av_acesso", avaliacaoAcessoMercado),
                div(attrs(".col-md-6")).withId("grafico_am_av_acesso"),
                div(attrs(".col-md-6"), tabelaItem("Acesso aos mercados", "am_acesso", acessoMercado))
                ,div(attrs(".col-md-6")).withId("grafico_am_acesso")
        );
    }
}
