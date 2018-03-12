package stec.model.domain;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
//import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import stec.controller.FormAGBaseLegalController.FormAGBaseLegal;
import stec.controller.FormAGOrganizacaoController.FormAGOrganizacao;
import stec.controller.FormAMAcessoController.FormAMAcesso;
import stec.controller.FormCTOAtendimentoSuspeitaController.FormCTOAtendimentoSuspeita;
import stec.controller.FormCTOCapacidadeDeteccaoPrecoceController.FormCTOCapacidadeDeteccaoPrecoce;
import stec.controller.FormCTOControleCadastroController.FormCTOControleCadastro;

import stec.controller.FormCTOPlanejamentoController.FormCTOPlanejamento;
import stec.controller.FormEOEstruturaController.FormEOEstrutura;
import stec.controller.FormICEducacaoSanitariaController.FormICEducacaoSanitaria;
import stec.controller.FormICParticipacaoComunidadeController.FormICParticipacaoComunidade;
import stec.controller.FormICParticipacaoInstituicoesController.FormICParticipacaoInstituicoes;
import stec.controller.FormIISUSController.FormIISUS;
import stec.controller.FormIISistemaInspecaoController.FormIISistemaInspecao;
import stec.controller.FormIMVHabilitacaoController.FormIMVHabilitacao;
import stec.controller.FormPNCEBTFiscalizacoesController.FormPNCEBTFiscalizacoes;
import stec.controller.FormPNCRHFiscalizacoesController.FormPNCRHFiscalizacoes;
import stec.controller.FormPNEEBVigilanciaController.FormPNEEBVigilancia;
import stec.controller.FormPNEFAFiscalizacoesController.FormPNEFAFiscalizacoes;
import stec.controller.FormPNSAVigilanciaController.FormPNSAVigilancia;
import stec.controller.FormPNSCOCadastroController.FormPNSCOCadastros;
import stec.controller.FormPNSECadastroEstabelecimentosController.FormPNSECadastroEstabelecimento;
import stec.controller.FormPNSSVigilanciaController.FormPNSSVigilancia;
import stec.controller.FormRFEquipamentosController.FormRFEquipamentos;
import stec.controller.FormRFIArrecadacaoController.FormRFIArrecadacao;
import stec.controller.FormRFICusteioController.FormRFICusteio;
import stec.controller.FormRFInstalacoesController.FormRFInstalacoes;
import stec.controller.FormRFSistemasInformacaoController.FormRFSistemasInformacao;
import stec.controller.FormRFTransportesController.FormRFTransportes;
import stec.controller.FormRHCapacitacaoController.FormRHCapacitacao;
import stec.controller.FormRHCompetenciasController.FormRHCompetencias;
import stec.controller.FormRHEstabilidadeController.FormRHEstabilidade;
import stec.controller.FormRHQuantitativoController.FormRHQuantitativo;
import stec.controller.FormCTOControleController.FormCTOControle;
import stec.controller.FormCTOControleEventosAglomeracaoController.FormCTOControleEventosAglomeracao;
import stec.controller.FormCTOControleTransitoAnimaisController.FormCTOControleTransitoAnimais;
import stec.controller.FormCTOFiscalizacaoController.FormCTOFiscalizacao;
import stec.controller.FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio;
import stec.controller.FormPNCEBTControlesController;
import stec.controller.FormPNCEBTControlesController.FormPNCEBTControles;
import stec.controller.FormPNCEBTVigilianciaController;
import stec.controller.FormPNCEBTVigilianciaController.FormPNCEBTVigilancia;
import stec.controller.FormPNCRHVigilanciaController;
import stec.controller.FormPNCRHVigilanciaController.FormPNCRHVigilancia;
import stec.controller.FormPNEFAVigilanciaController;
import stec.controller.FormPNEFAVigilanciaController.FormPNEFAVigilancia;
import stec.controller.FormPNSACadastroEstabelecimentosController;
import stec.controller.FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos;
import stec.controller.FormPNSCOVigilanciaController;
import stec.controller.FormPNSCOVigilanciaController.FormPNSCOVigilancia;
import stec.controller.FormPNSEControlesController;
import stec.controller.FormPNSEControlesController.FormPNSEControles;
import stec.controller.FormPNSEVigilanciaController;
import stec.controller.FormPNSEVigilanciaController.FormPNSEVigilancia;
import stec.controller.FormPNSSCadastroEstabelecimentosController;
import stec.controller.FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento;
import stec.controller.FormVulnerabilidadesPotencialidadesController;
import stec.model.dao.SupervisaoDAO;

public class RelatorioAuditoriaCompilada {

    private static Font f = new Font(FontFamily.HELVETICA, 10, Font.NORMAL);// para texto normal
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);// titulo do documento
    private static Font chaFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);// capitulos do documento
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);// negrito texto normal
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);// sub capitulos capitulo
    
    private SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    private HashMap<String, Resposta> hashRespostas = new HashMap<>();
    private List<Supervisao> listSupervisao = new ArrayList<>();
    
    public void setHashRespostas(HashMap<String, Resposta> respostas) {
        this.hashRespostas = respostas;
    }

    public HashMap<String, Resposta> getHashRespostas() {
        return this.hashRespostas;
    }
    public List<Supervisao> getListSupervisao() {
        return listSupervisao;
    }
    public void setListSupervisao(List<Supervisao> listSupervisao) {
        this.listSupervisao = listSupervisao;
    }

    public void show(String file) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Relatórios PDF/" + file + ".pdf"));
            document.open();

            this.Capa(document);
            this.IdentificacaoEscritorio(document, listSupervisao, hashRespostas);
            this.RecursosHumanos(document, listSupervisao, hashRespostas);
            this.RecursosFisicos(document, listSupervisao, hashRespostas);
            this.RecursosFinanceiros(document, listSupervisao, hashRespostas);
            this.EstruturaOrganizacional(document, listSupervisao, hashRespostas);
            this.AutoridadeGestao(document, listSupervisao, hashRespostas);
            this.CapacidadeTecnicaOperacional(document, listSupervisao, hashRespostas);
            
            for (Supervisao supervisao : listSupervisao){
                if (supervisao.getProgramas().toString().contains("PNEFA"))
                    this.PNEFA(document, listSupervisao, hashRespostas);
            }
            
            for (Supervisao supervisao : listSupervisao){
                if (supervisao.getProgramas().toString().contains("PNCEBT"))
                    this.PNCEBT(document, listSupervisao, hashRespostas);
            }
            
            for (Supervisao supervisao : listSupervisao){
                if (supervisao.getProgramas().toString().contains("PNCRH"))
                    this.PNCRH(document, listSupervisao, hashRespostas);
            }

            for (Supervisao supervisao : listSupervisao){
                if (supervisao.getProgramas().toString().contains("PNEEB"))
                    this.PNEEB(document, listSupervisao, hashRespostas);
            }

            for (Supervisao supervisao : listSupervisao){
                if (supervisao.getProgramas().toString().contains("PNSE"))
                    this.PNSE(document, listSupervisao, hashRespostas);
            }

            for (Supervisao supervisao : listSupervisao){
                if (supervisao.getProgramas().toString().contains("PNSCO"))
                    this.PNSCO(document, listSupervisao, hashRespostas);
            }

            for (Supervisao supervisao : listSupervisao){
                if (supervisao.getProgramas().toString().contains("PNSS"))
                    this.PNSS(document, listSupervisao, hashRespostas);
            }

            for (Supervisao supervisao : listSupervisao){
                if (supervisao.getProgramas().toString().contains("PNSA"))
                    this.PNSA(document, listSupervisao, hashRespostas);
            }
            /*
            this.InteracaoPartes(document, listSupervisao, hashRespostas);
            this.AcessoMercados(document, listSupervisao, hashRespostas);
            this.VulnerabilidadesPotencialidades(document, listSupervisao, hashRespostas);
            */
            document.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /*
    * Adiciona a capa com o titulo ao documento OK
     */
    //função que gera todo o PDF
    public void gerarPDF(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        
    }
    
    public void Capa(Document document) throws DocumentException {
        Paragraph capa = new Paragraph();

        Paragraph cabecalho = new Paragraph(
                "Estado do Maranhão" + "\nSecretaria de Estado de Agricultura, Pecuária e Pesca"
                + "\nAgência Estadual de Defesa Agropecuária do Maranhão"
                + "\nDiretoria de Defesa e Inspeção Sanitária Animal" + "\nCoordenadoria de Defesa Animal"
                + "\nSupervisão em Defesa Agropecuária");
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        capa.add(cabecalho);

        addEmptyLine(capa, 15);

        Paragraph titulo = new Paragraph("Relatório dos Comentarios, Recomendações ULSAV / EAC\n"
                + "Prazo para Ajuste e Recomendações UR e UC", catFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        capa.add(titulo);

        addEmptyLine(capa, 15);

        // Pega a data Atual e transforma em String
        DateFormat df = new SimpleDateFormat("MM/yyyy");
        Paragraph mesAno = new Paragraph(df.format(new Date()));
        mesAno.setAlignment(Element.ALIGN_CENTER);
        capa.add(mesAno);

        document.add(capa);// adiciona o conteudo da capa ao documento

        // Start a new page
        document.newPage();
    }
//Identificação Escritorio-----------------------------------------------------------------------------------------
    public void IdentificacaoEscritorio(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        //colocar titulo principal
        Paragraph identificacaoEscritorio = new Paragraph();
        Anchor titulo = new Anchor("Formulário - Identificação de Escritório", chaFont);
        titulo.setName("Identificação de Escritório");
        identificacaoEscritorio.add(titulo);
        addEmptyLine(identificacaoEscritorio, 1);
        document.add(identificacaoEscritorio);
        Gson gson = new Gson();
                
        //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
        String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
        Paragraph resp = new Paragraph();
        Paragraph comentariosRecomendacoes = null;
        //Laço de repetição para passar para o proximo sutitulo
        for (String tipo : tipoComentario){
            //Colocar o subtitulo
            Paragraph comentarios = new Paragraph(tipo, subFont);
            document.add(comentarios);
            comentarios.clear();
            for (Supervisao supervisao : list){
                FormIdentificacaoEscritorio formulario = gson.fromJson(supervisao.getHashRespostas().get("identificacao_escritorio").getResposta(),
                    FormIdentificacaoEscritorio.class);
                //Adiciona o nome do municipio auditado
                String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                        : supervisao.getUlsav().getNome());
                Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                resp.add(muniAuditado);
                //adiciona a resposta dos comentarios do municipio auditado
                if (tipo == "Comentarios"){
                    comentariosRecomendacoes = new Paragraph(
                    "\nComentários:\n" + formulario.getComentario()
                    + "\n______________________________________________________________________________________________\n", f);
                }else if(tipo == "Recomendações ULSAV / EAC"){
                    comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                    + "\n______________________________________________________________________________________________\n", f);
                }else if(tipo == "Prazo para Ajuste"){
                    comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste:\n" + formulario.getPrazo()
                    + "\n______________________________________________________________________________________________\n", f);
                }else if(tipo == "Recomendações UR"){
                    comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                    + "\n______________________________________________________________________________________________\n", f);
                }else if(tipo == "Recomendações UC"){
                    comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                    + "\n______________________________________________________________________________________________\n", f);
                }
                comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                resp.add(comentariosRecomendacoes);
                document.add(resp);
                resp.clear();
            }
            document.newPage();
        }
    }
//Fim
    public void RecursosHumanos(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Humanos", chaFont);
        anchor.setName("Recursos Humanos");

        // onde e descrito qual o numero do capitulo
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<4; i++){
            if(i==0)
                comentarios.add("RH Quantitativo\n");
            if(i==1)
                comentarios.add("RH Estabilidade\n");
            if(i==2)
                comentarios.add("RH Capacitação\n");
            if(i==3)
                comentarios.add("RH Competências\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //RH Quantitativo------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                            FormRHQuantitativo.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //RH Estabilidade-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormRHEstabilidade formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_estabilidade").getResposta(),
                            FormRHEstabilidade.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //RH Capacitação-----------------------------------------------------------------------------------------------
                    }else if(i==2){
                        FormRHCapacitacao formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_capacitacao").getResposta(),
                            FormRHCapacitacao.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //RH Competências-----------------------------------------------------------------------------------------------
                    }else if(i==3){
                        FormRHCompetencias formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_competencias").getResposta(),
                            FormRHCompetencias.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//fim
//Fecursos Fisicos-------------------------------------------------------------------------------------------
    public void RecursosFisicos(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Físicos", chaFont);
        anchor.setName("Recursos Físicos");

        Chapter catPart = new Chapter(new Paragraph(anchor), 2);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<4; i++){
            if(i==0)
                comentarios.add("RF Instalações\n");
            if(i==1)
                comentarios.add("RF Trnasportes\n");
            if(i==2)
                comentarios.add("RF Equipamentos\n");
            if(i==3)
                comentarios.add("RF Sistemas de Informação\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //RF Instalações------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormRFInstalacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_instalacoes").getResposta(),
                            FormRFInstalacoes.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //RF Transportes-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormRFTransportes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_transportes").getResposta(),
                            FormRFTransportes.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //RF Equipamentos-----------------------------------------------------------------------------------------------
                    }else if(i==2){
                        FormRFEquipamentos formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_equipamentos").getResposta(),
                            FormRFEquipamentos.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //RF SI--------------------------------------------------------------------------------------------------------
                    }else if(i==3){
                        FormRFSistemasInformacao formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_sistemas").getResposta(),
                            FormRFSistemasInformacao.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
    public void RecursosFinanceiros(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Financeiros", chaFont);
        anchor.setName("Recursos Financeiros");

        Chapter catPart = new Chapter(new Paragraph(anchor), 3);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<2; i++){
            if(i==0)
                comentarios.add("RFI Custeio\n");
            if(i==1)
                comentarios.add("RFI Arrecadação\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //RFI Custeio------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormRFICusteio formulario = gson.fromJson(supervisao.getHashRespostas().get("rfi_custeio").getResposta(),
                            FormRFICusteio.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //RFI Arrecadação-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormRFIArrecadacao formulario = gson.fromJson(supervisao.getHashRespostas().get("rfi_arrecadacao").getResposta(),
                            FormRFIArrecadacao.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
//EO Estrutura--------------------------------------------------------------------------------------------
    public void EstruturaOrganizacional(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Estrutura Organizacional", chaFont);
        anchor.setName("Estrutura Organizacional");

        Chapter catPart = new Chapter(new Paragraph(anchor), 4);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("Estrutura organizacional e capacidade de coordenação interna\n", subFont);
        //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
        String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
        Paragraph resp = new Paragraph();
        Paragraph comentariosRecomendacoes = null;
        for (String tipo : tipoComentario) {
            //Colocar o subtitulo
            comentarios.add(tipo);
            document.add(comentarios);
            comentarios.clear();
            for (Supervisao supervisao : list) {
                //Adiciona o nome do municipio auditado
                String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                        : supervisao.getUlsav().getNome());
                Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                resp.add(muniAuditado);
                FormEOEstrutura formulario = gson.fromJson(supervisao.getHashRespostas().get("eo_estrutura").getResposta(),
                        FormEOEstrutura.class);
                //adiciona a resposta dos comentarios do municipio auditado
                if (tipo == "Comentarios") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                } else if (tipo == "Recomendações ULSAV / EAC") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                } else if (tipo == "Prazo para Ajuste") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                } else if (tipo == "Recomendações UR") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                } else if (tipo == "Recomendações UC") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                }
                comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                resp.add(comentariosRecomendacoes);
                document.add(resp);
                resp.clear();
            }
        }
        document.newPage();
    }
//Fim
    public void AutoridadeGestao(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Autoridade e Gestão de Qualidade", chaFont);
        anchor.setName("Autoridade e Gestão de Qualidade");

        Chapter catPart = new Chapter(new Paragraph(anchor), 5);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<2; i++){
            if(i==0)
                comentarios.add("AG Base Legal\n");
            if(i==1)
                comentarios.add("AG Organização\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //AG Base Legal------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormAGBaseLegal formulario = gson.fromJson(supervisao.getHashRespostas().get("ag_base_legal").getResposta(),
                            FormAGBaseLegal.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //AG Organização-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormAGOrganizacao formulario = gson.fromJson(supervisao.getHashRespostas().get("ag_organizacao").getResposta(),
                            FormAGOrganizacao.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
    public void CapacidadeTecnicaOperacional(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Capacidade Técnica e Operacional", chaFont);
        anchor.setName("Capacidade Técnica e Operacional");

        Chapter catPart = new Chapter(new Paragraph(anchor), 6);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<8; i++){
            if(i==0)
                comentarios.add("CTO Controle de Cadastro\n");
            if(i==1)
                comentarios.add("CTO Planejamento\n");
            if(i==2)
                comentarios.add("CTO Controle de Divisas e Trânsito Interno\n");
            if(i==3)
                comentarios.add("CTO Controle de Trânsito de Animais e Produtos de Origem Animal\n");
            if(i==4)
                comentarios.add("CTO Controle de eventos de aglomeração de animais\n");
            if(i==5)
                comentarios.add("CTO Fiscalização em revendas veterinárias\n");
            if(i==6)
                comentarios.add("CTO Capacidade para detecção precoce e notificação imediata de doenças\n");
            if(i==7)
                comentarios.add("CTO Capacidade para atendimento a suspeitas e atuação em emergências\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //CTO Controle Cadastro------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormCTOControleCadastro formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_controle_cadastro").getResposta(),
                            FormCTOControleCadastro.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //CTO Planejamento-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormCTOPlanejamento formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_planejamento").getResposta(),
                            FormCTOPlanejamento.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //CTO Controle-----------------------------------------------------------------------------------------------
                    }else if(i==2){
                        FormCTOControle formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_controle").getResposta(),
                            FormCTOControle.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //CTO Transito de Animais--------------------------------------------------------------------------------------------------------
                    }else if(i==3){
                        FormCTOControleTransitoAnimais formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_transito_animais").getResposta(),
                            FormCTOControleTransitoAnimais.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //CTO Controle Eventos Aglomeracao--------------------------------------------------------------------------------------------
                    }else if(i==4){
                        FormCTOControleEventosAglomeracao formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_controle_eventos").getResposta(),
                            FormCTOControleEventosAglomeracao.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //CTO Fiscalização------------------------------------------------------------------------------------------------
                    }else if(i==5){
                        FormCTOFiscalizacao formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_fiscalizacao").getResposta(),
                            FormCTOFiscalizacao.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //CTO Detecção Precoce--------------------------------------------------------------------------------------------
                    }else if(i==6){
                        FormCTOCapacidadeDeteccaoPrecoce formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_deteccao_precoce").getResposta(),
                            FormCTOCapacidadeDeteccaoPrecoce.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //CTO Atendimento Suspeita--------------------------------------------------------------------------------------
                    }else if(i==7){
                        FormCTOAtendimentoSuspeita formulario = gson.fromJson(supervisao.getHashRespostas().get("cto_atendimento_suspeita").getResposta(),
                            FormCTOAtendimentoSuspeita.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
    public void PNEFA(Document document, List<Supervisao> list,HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 7);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<2; i++){
            if(i==0)
                comentarios.add("PNEFA Fiscalizações\n");
            if(i==1)
                comentarios.add("PNEFA Vigilância\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //PNEFA Fiscalizações------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormPNEFAFiscalizacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("pnefa_fiscalizacoes").getResposta(),
                            FormPNEFAFiscalizacoes.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNEFA Vigilância-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormPNEFAVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnefa_vigilancia").getResposta(),
                            FormPNEFAVigilancia.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
//PNCEBT------------------------------------------------------------------------------------------------------
    public void PNCEBT(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 8);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<3; i++){
            if(i==0)
                comentarios.add("PNCEBT Fiscalização de Vacinações\n");
            if(i==1)
                comentarios.add("PNCEBT Vigilância Epidemiológica\n");
            if(i==2)
                comentarios.add("PNCEBT Controles do Programa\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //PNCEBT Fiscalizações------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormPNCEBTFiscalizacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("pncebt_fiscalizacoes").getResposta(),
                            FormPNCEBTFiscalizacoes.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNCEBT Vigilância-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormPNCEBTVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pncebt_vigilancia").getResposta(),
                            FormPNCEBTVigilancia.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNCEBT Controles-----------------------------------------------------------------------------------------------
                    }else if(i==2){
                        FormPNCEBTControles formulario = gson.fromJson(supervisao.getHashRespostas().get("pncebt_controles").getResposta(),
                            FormPNCEBTControles.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
//PNCRH----------------------------------------------------------------------------------------------------
    public void PNCRH(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 9);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                comentarios.add("PNCRH Fiscalização de Vacinações\n");
            }
            if (i == 1) {
                comentarios.add("PNCRH Vigilância Epidemiológica\n");
            }
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario) {
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list) {
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //PNCRH Fiscalizações------------------------------------------------------------------------------------------------
                    if (i == 0) {
                        FormPNCRHFiscalizacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("pncrh_fiscalizacoes").getResposta(),
                                FormPNCRHFiscalizacoes.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nComentários:\n" + formulario.getComentario()
                                    + "\n______________________________________________________________________________________________\n", f);
                        } else if (tipo == "Recomendações ULSAV / EAC") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                                    + "\n______________________________________________________________________________________________\n", f);
                        } else if (tipo == "Prazo para Ajuste") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nPrazos para ajuste:\n" + formulario.getPrazo()
                                    + "\n______________________________________________________________________________________________\n", f);
                        } else if (tipo == "Recomendações UR") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                                    + "\n______________________________________________________________________________________________\n", f);
                        } else if (tipo == "Recomendações UC") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                                    + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNCRH Vigilância-----------------------------------------------------------------------------------------------
                    } else if (i == 1) {
                        FormPNCRHVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pncrh_vigilancia").getResposta(),
                                FormPNCRHVigilancia.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nComentários:\n" + formulario.getComentario()
                                    + "\n______________________________________________________________________________________________\n", f);
                        } else if (tipo == "Recomendações ULSAV / EAC") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                                    + "\n______________________________________________________________________________________________\n", f);
                        } else if (tipo == "Prazo para Ajuste") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nPrazos para ajuste:\n" + formulario.getPrazo()
                                    + "\n______________________________________________________________________________________________\n", f);
                        } else if (tipo == "Recomendações UR") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                                    + "\n______________________________________________________________________________________________\n", f);
                        } else if (tipo == "Recomendações UC") {
                            comentariosRecomendacoes = new Paragraph(
                                    "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                                    + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                    document.newPage();
                }
            }
        }
    }
//Fim
//PNEEB--------------------------------------------------------------------------------------------------
    public void PNEEB(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)", chaFont);
        anchor.setName("Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 10);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("Vigilância Epidemiológica\n", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
        String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
        Paragraph resp = new Paragraph();
        Paragraph comentariosRecomendacoes = null;
        for (String tipo : tipoComentario) {
            //Colocar o subtitulo
            comentarios.add(tipo);
            document.add(comentarios);
            comentarios.clear();
            for (Supervisao supervisao : list) {
                //Adiciona o nome do municipio auditado
                String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                        : supervisao.getUlsav().getNome());
                Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                resp.add(muniAuditado);
                FormPNEEBVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pneeb_vigilancia").getResposta(),
                        FormPNEEBVigilancia.class);
                //adiciona a resposta dos comentarios do municipio auditado
                if (tipo == "Comentarios") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                } else if (tipo == "Recomendações ULSAV / EAC") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                } else if (tipo == "Prazo para Ajuste") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                } else if (tipo == "Recomendações UR") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                } else if (tipo == "Recomendações UC") {
                    comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                }
                comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                resp.add(comentariosRecomendacoes);
                document.add(resp);
                resp.clear();
            }
            document.newPage();
        }
    }
//Fim
//PNSE----------------------------------------------------------------------------------------------------
    public void PNSE(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 11);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<3; i++){
            if(i==0)
                comentarios.add("PNSE Cadastro de Estabelecimentos\n");
            if(i==1)
                comentarios.add("PNSE Vigilância Epidemiológica\n");
            if(i==2)
                comentarios.add("PNSE Controles do Programa\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //PNSE Cadastro estabelecimento------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormPNSECadastroEstabelecimento formulario = gson.fromJson(supervisao.getHashRespostas().get("pnse_cadastro_estabelecimentos").getResposta(),
                            FormPNSECadastroEstabelecimento.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNSE Vigilância-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormPNSEVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnse_vigilancia").getResposta(),
                            FormPNSEVigilancia.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNSE Controles-----------------------------------------------------------------------------------------------
                    }else if(i==2){
                        FormPNSEControles formulario = gson.fromJson(supervisao.getHashRespostas().get("pnse_controles").getResposta(),
                            FormPNSEControles.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
//PNSCO-----------------------------------------------------------------------------------------------------
    public void PNSCO(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 12);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<2; i++){
            if(i==0)
                comentarios.add("PNSCO Cadastro de Estabelecimentos\n");
            if(i==1)
                comentarios.add("PNSCO Vigilância Epidemiológica\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //PNSCO Cadastro estabelecimento------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormPNSCOCadastros formulario = gson.fromJson(supervisao.getHashRespostas().get("pnsco_cadastro_estabelecimentos").getResposta(),
                            FormPNSCOCadastros.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNSCO Vigilância-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormPNSCOVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnsco_vigilancia").getResposta(),
                            FormPNSCOVigilancia.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
//PNSS------------------------------------------------------------------------------------------------------
    public void PNSS(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 13);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<2; i++){
            if(i==0)
                comentarios.add("PNSS Cadastro de Estabelecimentos\n");
            if(i==1)
                comentarios.add("PNSS Vigilância Epidemiológica\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //PNSS Cadastro estabelecimento------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormPNSSCadastroEstabelecimento formulario = gson.fromJson(supervisao.getHashRespostas().get("pnss_cadastro_estabelecimentos").getResposta(),
                            FormPNSSCadastroEstabelecimento.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNSS Vigilância-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormPNSSVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnss_vigilancia").getResposta(),
                            FormPNSSVigilancia.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
//Fim
//PNSA--------------------------------------------------------------------------------------------------------
    public void PNSA(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 14);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
        Paragraph comentarios = new Paragraph("", subFont);
        //Laço de repetição para passar para o proximo sutitulo
        for(int i=0; i<2; i++){
            if(i==0)
                comentarios.add("PNSA Cadastro de Estabelecimentos\n");
            if(i==1)
                comentarios.add("PNSA Vigilância Epidemiológica\n");
            //Laço para mostrar os COMENTÁRIOS e RECOMENDAÇÔES de todos os municipios auditados
            String tipoComentario[] = {"Comentarios", "Recomendações ULSAV / EAC", "Prazo para Ajuste", "Recomendações UR", "Recomendações UC"};
            Paragraph resp = new Paragraph();
            Paragraph comentariosRecomendacoes = null;
            for (String tipo : tipoComentario){
                //Colocar o subtitulo
                comentarios.add(tipo);
                document.add(comentarios);
                comentarios.clear();
                for (Supervisao supervisao : list){
                    //Adiciona o nome do municipio auditado
                    String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                            : supervisao.getUlsav().getNome());
                    Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
                    resp.add(muniAuditado);
    //PNSA Cadastro estabelecimento------------------------------------------------------------------------------------------------
                    if(i==0){
                        FormPNSACadastroEstabelecimentos formulario = gson.fromJson(supervisao.getHashRespostas().get("pnsa_cadastro_estabelecimentos").getResposta(),
                            FormPNSACadastroEstabelecimentos.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
    //PNSA Vigilância-----------------------------------------------------------------------------------------------
                    }else if(i==1){
                        FormPNSAVigilancia formulario = gson.fromJson(supervisao.getHashRespostas().get("pnsa_vigilancia").getResposta(),
                            FormPNSAVigilancia.class);
                        //adiciona a resposta dos comentarios do municipio auditado
                        if (tipo == "Comentarios"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nComentários:\n" + formulario.getComentario()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações ULSAV / EAC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações ULSAV / EAC:\n" + formulario.getRecomendacaoUlsavEac()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Prazo para Ajuste"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nPrazos para ajuste:\n" + formulario.getPrazo()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UR"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UR:\n" + formulario.getRecomendacaoUr()
                            + "\n______________________________________________________________________________________________\n", f);
                        }else if(tipo == "Recomendações UC"){
                            comentariosRecomendacoes = new Paragraph(
                            "\nRecomendações UC:\n" + formulario.getRecomendacaoUC()
                            + "\n______________________________________________________________________________________________\n", f);
                        }
                        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
                        resp.add(comentariosRecomendacoes);
                        document.add(resp);
                        resp.clear();
                    }
                }
                document.newPage();
            }
        }
    }
/*
//Fim
//Interação Partes--------------------------------------------------------------------------------------------
    public void InteracaoPartes(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Interação com as partes interessadas", chaFont);
        anchor.setName("Interação com as partes interessadas");

        Chapter catPart = new Chapter(new Paragraph(anchor), 15);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//IP Comunidade Educação--------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Educação sanitária e comunicação social (divulgação e publicidade)\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormICEducacaoSanitaria formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_comunidade_educacao").getResposta(),
                FormICEducacaoSanitaria.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario.getComentario()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    
        //Colocar o subtitulo
        comentarios.add("Recomendações ULSAV / EAC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES ULSAV/EAC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICEducacaoSanitaria formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_comunidade_educacao").getResposta(),
                FormICEducacaoSanitaria.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Prazos para ajuste");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os PRAZOS P/ AJUSTE de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICEducacaoSanitaria formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_comunidade_educacao").getResposta(),
                FormICEducacaoSanitaria.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario.getPrazo()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subTitulo
        comentarios.add("Recomendações UR");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UR de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICEducacaoSanitaria formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_comunidade_educacao").getResposta(),
                FormICEducacaoSanitaria.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Recomendações UC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICEducacaoSanitaria formulario = gson.fromJson(supervisao.getHashRespostas().get("interacao_comunidade_educacao").getResposta(),
                FormICEducacaoSanitaria.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
//Fim
//IP Comunidade-----------------------------------------------------------------------------------------------
        comentarios.add("Participação com a comunidade\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoComunidade formulario2 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario2.getComentario()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    
        //Colocar o subtitulo
        comentarios.add("Recomendações ULSAV / EAC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES ULSAV/EAC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoComunidade formulario2 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Prazos para ajuste");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os PRAZOS P/ AJUSTE de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoComunidade formulario2 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario2.getPrazo()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subTitulo
        comentarios.add("Recomendações UR");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UR de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoComunidade formulario2 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Recomendações UC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoComunidade formulario2 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
//Fim
//IP Comunidade Instituições----------------------------------------------------------------------------------
        comentarios.add("Participação com instituições e representações\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoInstituicoes formulario3 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario3.getComentario()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    
        //Colocar o subtitulo
        comentarios.add("Recomendações ULSAV / EAC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES ULSAV/EAC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoInstituicoes formulario3 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Prazos para ajuste");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os PRAZOS P/ AJUSTE de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoInstituicoes formulario3 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario3.getPrazo()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subTitulo
        comentarios.add("Recomendações UR");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UR de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoInstituicoes formulario3 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Recomendações UC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormICParticipacaoInstituicoes formulario3 = gson.fromJson(
                supervisao.getHashRespostas().get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
//Fim
//IP Veterinario Habilitação---------------------------------------------------------------------------------
        comentarios.add("Veterinario Habilitação\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIMVHabilitacao formulario4 = gson.fromJson(supervisao.getHashRespostas().get("interacao_veterinario_habilitacao").getResposta(),
                FormIMVHabilitacao.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario4.getComentario()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    
        //Colocar o subtitulo
        comentarios.add("Recomendações ULSAV / EAC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES ULSAV/EAC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIMVHabilitacao formulario4 = gson.fromJson(supervisao.getHashRespostas().get("interacao_veterinario_habilitacao").getResposta(),
                FormIMVHabilitacao.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario4.getRecomendacaoUlsavEac()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Prazos para ajuste");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os PRAZOS P/ AJUSTE de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIMVHabilitacao formulario4 = gson.fromJson(supervisao.getHashRespostas().get("interacao_veterinario_habilitacao").getResposta(),
                FormIMVHabilitacao.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario4.getPrazo()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subTitulo
        comentarios.add("Recomendações UR");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UR de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIMVHabilitacao formulario4 = gson.fromJson(supervisao.getHashRespostas().get("interacao_veterinario_habilitacao").getResposta(),
                FormIMVHabilitacao.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario4.getRecomendacaoUr()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Recomendações UC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIMVHabilitacao formulario4 = gson.fromJson(supervisao.getHashRespostas().get("interacao_veterinario_habilitacao").getResposta(),
                FormIMVHabilitacao.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario4.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
//Fim
//IP Instituição Inspeção-----------------------------------------------------------------------------------------
        comentarios.add("Sistema de inspeção (seguridade alimentar)\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISistemaInspecao formulario5 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_inspecao").getResposta(),
                FormIISistemaInspecao.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario5.getComentario()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    
        //Colocar o subtitulo
        comentarios.add("Recomendações ULSAV / EAC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES ULSAV/EAC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISistemaInspecao formulario5 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_inspecao").getResposta(),
                FormIISistemaInspecao.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario5.getRecomendacaoUlsavEac()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Prazos para ajuste");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os PRAZOS P/ AJUSTE de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISistemaInspecao formulario5 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_inspecao").getResposta(),
                FormIISistemaInspecao.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario5.getPrazo()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subTitulo
        comentarios.add("Recomendações UR");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UR de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISistemaInspecao formulario5 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_inspecao").getResposta(),
                FormIISistemaInspecao.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario5.getRecomendacaoUr()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Recomendações UC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISistemaInspecao formulario5 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_inspecao").getResposta(),
                FormIISistemaInspecao.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario5.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
//Fim
//IP Instituição SUS----------------------------------------------------------------------------------------------
        comentarios.add("Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISUS formulario6 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario6.getComentario()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    
        //Colocar o subtitulo
        comentarios.add("Recomendações ULSAV / EAC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES ULSAV/EAC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISUS formulario6 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario6.getRecomendacaoUlsavEac()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Prazos para ajuste");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os PRAZOS P/ AJUSTE de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISUS formulario6 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario6.getPrazo()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subTitulo
        comentarios.add("Recomendações UR");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UR de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISUS formulario6 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario6.getRecomendacaoUr()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Recomendações UC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormIISUS formulario6 = gson.fromJson(supervisao.getHashRespostas().get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario6.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    }
//Fim
//Acesso Mercados--------------------------------------------------------------------------------------------
    public void AcessoMercados(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Acesso aos mercados", chaFont);
        anchor.setName("Acesso aos mercados");

        Chapter catPart = new Chapter(new Paragraph(anchor), 16);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//Acesso aos Mercados----------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Acesso aos mercados\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormAMAcesso formulario = gson.fromJson(supervisao.getHashRespostas().get("acesso_mercado").getResposta(), FormAMAcesso.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario.getComentario()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    
        //Colocar o subtitulo
        comentarios.add("Recomendações ULSAV / EAC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES ULSAV/EAC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormAMAcesso formulario = gson.fromJson(supervisao.getHashRespostas().get("acesso_mercado").getResposta(), FormAMAcesso.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Prazos para ajuste");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os PRAZOS P/ AJUSTE de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormAMAcesso formulario = gson.fromJson(supervisao.getHashRespostas().get("acesso_mercado").getResposta(), FormAMAcesso.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario.getPrazo()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subTitulo
        comentarios.add("Recomendações UR");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UR de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormAMAcesso formulario = gson.fromJson(supervisao.getHashRespostas().get("acesso_mercado").getResposta(), FormAMAcesso.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
        
        //Colocar o subtitulo
        comentarios.add("Recomendações UC");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar as RECOMENDAÇÕES UC de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormAMAcesso formulario = gson.fromJson(supervisao.getHashRespostas().get("acesso_mercado").getResposta(), FormAMAcesso.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    }
//Fim
//Vulnerabilidades-----------------------------------------------------------------------------------------
    public void VulnerabilidadesPotencialidades(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Vulnerabilidades e Potencialidades", chaFont);
        anchor.setName("Vulnerabilidades e Potencialidades");

        Chapter catPart = new Chapter(new Paragraph(anchor), 17);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//VP Vulnerabilidade----------------------------------------------------------------------------------------
        Paragraph subtitulo = new Paragraph("Vulnerabilidades", subFont);
        document.add(subtitulo);
        subtitulo.clear();
        
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades formulario = gson.fromJson(
                    supervisao.getHashRespostas().get("vulnerabilidade_potencialidade").getResposta(),
                    FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            Paragraph vulnerabilidades = new Paragraph(
                    "Vulnerabilidades que o escritório apresenta na execução das ações de defesa animal: \n"
                    + formulario.getVulnerabilidades()
                    + "\n___________________________________________________________________________________\n\n", f);
            vulnerabilidades.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(vulnerabilidades);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
//Fim
//VP Potencialidade-----------------------------------------------------------------------------------------
        subtitulo.add("Potencialidades");
        document.add(subtitulo);
        
        for (Supervisao supervisao : list){
            FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades formulario = gson.fromJson(
                    supervisao.getHashRespostas().get("vulnerabilidade_potencialidade").getResposta(),
                    FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            Paragraph potencialidades = new Paragraph(
                    "Potencialidades que o escritório apresenta na execução das ações de defesa animal: \n"
                    + formulario.getPotencialidades()
                    + "\n___________________________________________________________________________________\n\n", f);
            potencialidades.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(potencialidades);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    }*/
//Fim
    public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
