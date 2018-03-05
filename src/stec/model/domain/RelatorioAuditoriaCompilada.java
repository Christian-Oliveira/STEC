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
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import stec.controller.FormAGBaseLegalController.FormAGBaseLegal;
import stec.controller.FormAGOrganizacaoController.FormAGOrganizacao;
import stec.controller.FormAGSupervisaoController.FormAGSupervisao;
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
import stec.controller.FormIdentificacaoEscritorioController;
import stec.controller.FormPNCEBTControlesController;
import stec.controller.FormPNCEBTVigilianciaController;
import stec.controller.FormPNCRHVigilanciaController;
import stec.controller.FormPNEFAVigilanciaController;
import stec.controller.FormPNSACadastroEstabelecimentosController;
import stec.controller.FormPNSCOVigilanciaController;
import stec.controller.FormPNSEControlesController;
import stec.controller.FormPNSEVigilanciaController;
import stec.controller.FormPNSSCadastroEstabelecimentosController;
import stec.controller.FormVulnerabilidadesPotencialidadesController;
import stec.model.dao.RespostaDAO;
import stec.model.dao.SupervisaoDAO;
import stec.model.domain.Supervisao;
import stec.resources.Classes.IFormulario;

public class RelatorioAuditoriaCompilada {

    private static Font f = new Font(FontFamily.HELVETICA, 10, Font.NORMAL);// para texto normal
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);// titulo do documento
    private static Font chaFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);// capitulos do documento
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);// negrito texto normal
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);// sub capitulos capitulo
    //private Supervisao supervisao = new Supervisao();
    private SupervisaoDAO supervisaoDAO = new SupervisaoDAO();
    private HashMap<String, Resposta> hashRespostas = new HashMap<>();
    private List<Supervisao> listSupervisao = new ArrayList<>();
    

/*    public void setSupervisao(Supervisao supervisao) {
        this.supervisao = supervisao;
    }

    public Supervisao getSupervisao() {
        return this.supervisao;
    }
*/
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
            //this.IdentificacaoEscritorio(document, listSupervisao, hashRespostas);
            this.RecursosHumanos(document, listSupervisao, hashRespostas);
            /*this.RecursosFisicos(document, listSupervisao, hashRespostas);
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

        Paragraph titulo = new Paragraph("Relatório dos Comentarios, Recomendações ULSAV/EAC\n"
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
                
        //Colocar o subtitulo
        Paragraph comentarios = new Paragraph("", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio formulario = gson.fromJson(supervisao.getHashRespostas().get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio.class);
            
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
            FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio formulario = gson.fromJson(supervisao.getHashRespostas().get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio.class);
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
            FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio formulario = gson.fromJson(supervisao.getHashRespostas().get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio.class);
            //Adiciona o nome do municipio auditado
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
            FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio formulario = gson.fromJson(supervisao.getHashRespostas().get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio.class);
            //Adiciona o nome do municipio auditado
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
            FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio formulario = gson.fromJson(supervisao.getHashRespostas().get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio.class);
            //Adiciona o nome do municipio auditado
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
    public void RecursosHumanos(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Humanos", chaFont);
        anchor.setName("Recursos Humanos");

        // onde e descrito qual o numero do capitulo
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        document.add(catPart);
        Gson gson = new Gson();

//RH Quantitativo----------------------------------------------------------------------------------------
        //Colocar o subtitulo
        Paragraph comentarios = new Paragraph("", subFont);
        comentarios.add("RH Quantitativo\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);
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
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);
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
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);
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
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);
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
            FormRHQuantitativo formulario = gson.fromJson(supervisao.getHashRespostas().get("rh_quantitativo").getResposta(),
                    FormRHQuantitativo.class);
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
//fim
//RH Estabilidade----------------------------------------------------------------------------
        //Colocar o subtitulo
        comentarios.add("RH Estabilidade\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormRHEstabilidade formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rh_estabilidade").getResposta(),
                    FormRHEstabilidade.class);
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
            FormRHEstabilidade formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rh_estabilidade").getResposta(),
                    FormRHEstabilidade.class);
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
            FormRHEstabilidade formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rh_estabilidade").getResposta(),
                    FormRHEstabilidade.class);
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
            FormRHEstabilidade formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rh_estabilidade").getResposta(),
                    FormRHEstabilidade.class);
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
            FormRHEstabilidade formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rh_estabilidade").getResposta(),
                    FormRHEstabilidade.class);
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
//RH Capacitação--------------------------------------------------------------------------------------
        comentarios.add("RH Capacitação\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormRHCapacitacao formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rh_capacitacao").getResposta(),
                    FormRHCapacitacao.class);
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
            FormRHCapacitacao formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rh_capacitacao").getResposta(),
                    FormRHCapacitacao.class);
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
            FormRHCapacitacao formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rh_capacitacao").getResposta(),
                    FormRHCapacitacao.class);
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
            FormRHCapacitacao formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rh_capacitacao").getResposta(),
                    FormRHCapacitacao.class);
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
            FormRHCapacitacao formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rh_capacitacao").getResposta(),
                    FormRHCapacitacao.class);
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
//RH Competências-------------------------------------------------------------------------------------
        comentarios.add("RH Competências\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormRHCompetencias formulario4 = gson.fromJson(supervisao.getHashRespostas().get("rh_competencias").getResposta(),
                    FormRHCompetencias.class);
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
            FormRHCompetencias formulario4 = gson.fromJson(supervisao.getHashRespostas().get("rh_competencias").getResposta(),
                    FormRHCompetencias.class);
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
            FormRHCompetencias formulario4 = gson.fromJson(supervisao.getHashRespostas().get("rh_competencias").getResposta(),
                    FormRHCompetencias.class);
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
            FormRHCompetencias formulario4 = gson.fromJson(supervisao.getHashRespostas().get("rh_competencias").getResposta(),
                    FormRHCompetencias.class);
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
            FormRHCompetencias formulario4 = gson.fromJson(supervisao.getHashRespostas().get("rh_competencias").getResposta(),
                    FormRHCompetencias.class);
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
    }
//fim
//Fecursos Fisicos-------------------------------------------------------------------------------------------
    public void RecursosFisicos(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Físicos", chaFont);
        anchor.setName("Recursos Físicos");

        Chapter catPart = new Chapter(new Paragraph(anchor), 2);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//RF Instalações------------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("RF Instalações\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormRFInstalacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_instalacoes").getResposta(),
                    FormRFInstalacoes.class);
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
            FormRFInstalacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_instalacoes").getResposta(),
                    FormRFInstalacoes.class);
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
            FormRFInstalacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_instalacoes").getResposta(),
                    FormRFInstalacoes.class);
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
            FormRFInstalacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_instalacoes").getResposta(),
                    FormRFInstalacoes.class);
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
            FormRFInstalacoes formulario = gson.fromJson(supervisao.getHashRespostas().get("rf_instalacoes").getResposta(),
                    FormRFInstalacoes.class);
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
//RF Transportes-------------------------------------------------------------------------------------
        comentarios.add("RF Transportes\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormRFTransportes formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rf_transportes").getResposta(),
                    FormRFTransportes.class);
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
            FormRFTransportes formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rf_transportes").getResposta(),
                    FormRFTransportes.class);
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
            FormRFTransportes formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rf_transportes").getResposta(),
                    FormRFTransportes.class);
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
            FormRFTransportes formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rf_transportes").getResposta(),
                    FormRFTransportes.class);
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
            FormRFTransportes formulario2 = gson.fromJson(supervisao.getHashRespostas().get("rf_transportes").getResposta(),
                    FormRFTransportes.class);
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
//RF Equipamentos-------------------------------------------------------------------------------------
        comentarios.add("RF Equipamentos\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormRFEquipamentos formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rf_equipamentos").getResposta(),
                    FormRFEquipamentos.class);
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
            FormRFEquipamentos formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rf_equipamentos").getResposta(),
                    FormRFEquipamentos.class);
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
            FormRFEquipamentos formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rf_equipamentos").getResposta(),
                    FormRFEquipamentos.class);
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
            FormRFEquipamentos formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rf_equipamentos").getResposta(),
                    FormRFEquipamentos.class);
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
            FormRFEquipamentos formulario3 = gson.fromJson(supervisao.getHashRespostas().get("rf_equipamentos").getResposta(),
                    FormRFEquipamentos.class);
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
//RF Sistemas------------------------------------------------------------------------------------------
        comentarios.add("RF Sistemas\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormRFSistemasInformacao formulario4 = gson.fromJson(resposta.get("rf_sistemas").getResposta(),
                    FormRFSistemasInformacao.class);
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
            FormRFSistemasInformacao formulario4 = gson.fromJson(resposta.get("rf_sistemas").getResposta(),
                    FormRFSistemasInformacao.class);
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
            FormRFSistemasInformacao formulario4 = gson.fromJson(resposta.get("rf_sistemas").getResposta(),
                    FormRFSistemasInformacao.class);
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
            FormRFSistemasInformacao formulario4 = gson.fromJson(resposta.get("rf_sistemas").getResposta(),
                    FormRFSistemasInformacao.class);
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
            FormRFSistemasInformacao formulario4 = gson.fromJson(resposta.get("rf_sistemas").getResposta(),
                    FormRFSistemasInformacao.class);
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
    }
//Fim
    public void RecursosFinanceiros(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Financeiros", chaFont);
        anchor.setName("Recursos Financeiros");

        Chapter catPart = new Chapter(new Paragraph(anchor), 3);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//RFI Custeio--------------------------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Custeio\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormRFICusteio formulario = gson.fromJson(resposta.get("rfi_custeio").getResposta(),
                    FormRFICusteio.class);
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
            FormRFICusteio formulario = gson.fromJson(resposta.get("rfi_custeio").getResposta(),
                    FormRFICusteio.class);
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
            FormRFICusteio formulario = gson.fromJson(resposta.get("rfi_custeio").getResposta(),
                    FormRFICusteio.class);
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
            FormRFICusteio formulario = gson.fromJson(resposta.get("rfi_custeio").getResposta(),
                    FormRFICusteio.class);
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
            FormRFICusteio formulario = gson.fromJson(resposta.get("rfi_custeio").getResposta(),
                    FormRFICusteio.class);
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
//RFI Arrecadação-----------------------------------------------------------------------------------------
        comentarios.add("RFI Arrecadação\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormRFIArrecadacao formulario2 = gson.fromJson(resposta.get("rfi_arrecadacao").getResposta(),
                    FormRFIArrecadacao.class);
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
            FormRFIArrecadacao formulario2 = gson.fromJson(resposta.get("rfi_arrecadacao").getResposta(),
                    FormRFIArrecadacao.class);
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
            FormRFIArrecadacao formulario2 = gson.fromJson(resposta.get("rfi_arrecadacao").getResposta(),
                    FormRFIArrecadacao.class);
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
            FormRFIArrecadacao formulario2 = gson.fromJson(resposta.get("rfi_arrecadacao").getResposta(),
                    FormRFIArrecadacao.class);
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
            FormRFIArrecadacao formulario2 = gson.fromJson(resposta.get("rfi_arrecadacao").getResposta(),
                    FormRFIArrecadacao.class);
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
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormEOEstrutura formulario = gson.fromJson(resposta.get("eo_estrutura").getResposta(),
                    FormEOEstrutura.class);
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
            FormEOEstrutura formulario = gson.fromJson(resposta.get("eo_estrutura").getResposta(),
                    FormEOEstrutura.class);
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
            FormEOEstrutura formulario = gson.fromJson(resposta.get("eo_estrutura").getResposta(),
                    FormEOEstrutura.class);
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
            FormEOEstrutura formulario = gson.fromJson(resposta.get("eo_estrutura").getResposta(),
                    FormEOEstrutura.class);
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
            FormEOEstrutura formulario = gson.fromJson(resposta.get("eo_estrutura").getResposta(),
                    FormEOEstrutura.class);
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
    public void AutoridadeGestao(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Autoridade e Gestão de Qualidade", chaFont);
        anchor.setName("Autoridade e Gestão de Qualidade");

        Chapter catPart = new Chapter(new Paragraph(anchor), 5);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//AG Base Legal---------------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("AG Base Legal\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormAGBaseLegal formulario = gson.fromJson(resposta.get("ag_base_legal").getResposta(),
                    FormAGBaseLegal.class);
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
            FormAGBaseLegal formulario = gson.fromJson(resposta.get("ag_base_legal").getResposta(),
                    FormAGBaseLegal.class);
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
            FormAGBaseLegal formulario = gson.fromJson(resposta.get("ag_base_legal").getResposta(),
                    FormAGBaseLegal.class);
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
            FormAGBaseLegal formulario = gson.fromJson(resposta.get("ag_base_legal").getResposta(),
                    FormAGBaseLegal.class);
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
            FormAGBaseLegal formulario = gson.fromJson(resposta.get("ag_base_legal").getResposta(),
                    FormAGBaseLegal.class);
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
//AG Organização-------------------------------------------------------------------------------------
        comentarios.add("AG Organização\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormAGOrganizacao formulario2 = gson.fromJson(resposta.get("ag_organizacao").getResposta(),
                    FormAGOrganizacao.class);
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
            FormAGOrganizacao formulario2 = gson.fromJson(resposta.get("ag_organizacao").getResposta(),
                    FormAGOrganizacao.class);
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
            FormAGOrganizacao formulario2 = gson.fromJson(resposta.get("ag_organizacao").getResposta(),
                    FormAGOrganizacao.class);
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
            FormAGOrganizacao formulario2 = gson.fromJson(resposta.get("ag_organizacao").getResposta(),
                    FormAGOrganizacao.class);
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
            FormAGOrganizacao formulario2 = gson.fromJson(resposta.get("ag_organizacao").getResposta(),
                    FormAGOrganizacao.class);
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
    }
//Fim
    public void CapacidadeTecnicaOperacional(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Capacidade Técnica e Operacional", chaFont);
        anchor.setName("Capacidade Técnica e Operacional");

        Chapter catPart = new Chapter(new Paragraph(anchor), 6);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//CTO Controle Cadastro-----------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("CTO Controle de Cadastro\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormCTOControleCadastro formulario = gson.fromJson(resposta.get("cto_controle_cadastro").getResposta(),
                    FormCTOControleCadastro.class);
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
            FormCTOControleCadastro formulario = gson.fromJson(resposta.get("cto_controle_cadastro").getResposta(),
                    FormCTOControleCadastro.class);
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
            FormCTOControleCadastro formulario = gson.fromJson(resposta.get("cto_controle_cadastro").getResposta(),
                    FormCTOControleCadastro.class);
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
            FormCTOControleCadastro formulario = gson.fromJson(resposta.get("cto_controle_cadastro").getResposta(),
                    FormCTOControleCadastro.class);
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
            FormCTOControleCadastro formulario = gson.fromJson(resposta.get("cto_controle_cadastro").getResposta(),
                    FormCTOControleCadastro.class);
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
//CTO Planejamento---------------------------------------------------------------------------------------
        comentarios.add("Planejamento\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormCTOPlanejamento formulario2 = gson.fromJson(resposta.get("cto_planejamento").getResposta(),
                    FormCTOPlanejamento.class);
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
            FormCTOPlanejamento formulario2 = gson.fromJson(resposta.get("cto_planejamento").getResposta(),
                    FormCTOPlanejamento.class);
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
            FormCTOPlanejamento formulario2 = gson.fromJson(resposta.get("cto_planejamento").getResposta(),
                    FormCTOPlanejamento.class);
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
            FormCTOPlanejamento formulario2 = gson.fromJson(resposta.get("cto_planejamento").getResposta(),
                    FormCTOPlanejamento.class);
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
            FormCTOPlanejamento formulario2 = gson.fromJson(resposta.get("cto_planejamento").getResposta(),
                    FormCTOPlanejamento.class);
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
//CTO Controle-------------------------------------------------------------------------------------------
        comentarios.add("Controle de Divisas e Trânsito Interno\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormCTOControle formulario3 = gson.fromJson(resposta.get("cto_controle").getResposta(),
                    FormCTOControle.class);
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
            FormCTOControle formulario3 = gson.fromJson(resposta.get("cto_controle").getResposta(),
                    FormCTOControle.class);
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
            FormCTOControle formulario3 = gson.fromJson(resposta.get("cto_controle").getResposta(),
                    FormCTOControle.class);
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
            FormCTOControle formulario3 = gson.fromJson(resposta.get("cto_controle").getResposta(),
                    FormCTOControle.class);
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
            FormCTOControle formulario3 = gson.fromJson(resposta.get("cto_controle").getResposta(),
                    FormCTOControle.class);
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
//CTO Transito Animais-----------------------------------------------------------------------------------
        comentarios.add("Controle de Trânsito de Animais e Produtos de Origem Animal\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormCTOControleTransitoAnimais formulario4 = gson.fromJson(resposta.get("cto_transito_animais").getResposta(),
                    FormCTOControleTransitoAnimais.class);
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
            FormCTOControleTransitoAnimais formulario4 = gson.fromJson(resposta.get("cto_transito_animais").getResposta(),
                    FormCTOControleTransitoAnimais.class);
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
            FormCTOControleTransitoAnimais formulario4 = gson.fromJson(resposta.get("cto_transito_animais").getResposta(),
                    FormCTOControleTransitoAnimais.class);
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
            FormCTOControleTransitoAnimais formulario4 = gson.fromJson(resposta.get("cto_transito_animais").getResposta(),
                    FormCTOControleTransitoAnimais.class);
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
            FormCTOControleTransitoAnimais formulario4 = gson.fromJson(resposta.get("cto_transito_animais").getResposta(),
                    FormCTOControleTransitoAnimais.class);
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
//CTO Controle Eventos------------------------------------------------------------------------------------
        comentarios.add("CTO Controle de eventos de aglomeração de animais\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormCTOControleEventosAglomeracao formulario5 = gson.fromJson(resposta.get("cto_controle_eventos").getResposta(),
                    FormCTOControleEventosAglomeracao.class);
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
            FormCTOControleEventosAglomeracao formulario5 = gson.fromJson(resposta.get("cto_controle_eventos").getResposta(),
                    FormCTOControleEventosAglomeracao.class);
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
            FormCTOControleEventosAglomeracao formulario5 = gson.fromJson(resposta.get("cto_controle_eventos").getResposta(),
                    FormCTOControleEventosAglomeracao.class);
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
            FormCTOControleEventosAglomeracao formulario5 = gson.fromJson(resposta.get("cto_controle_eventos").getResposta(),
                    FormCTOControleEventosAglomeracao.class);
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
            FormCTOControleEventosAglomeracao formulario5 = gson.fromJson(resposta.get("cto_controle_eventos").getResposta(),
                    FormCTOControleEventosAglomeracao.class);
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
//CTO Fiscalização-------------------------------------------------------------------------------------
        comentarios.add("Fiscalização em revendas veterinárias\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormCTOFiscalizacao formulario6 = gson.fromJson(resposta.get("cto_fiscalizacao").getResposta(),
                    FormCTOFiscalizacao.class);
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
            FormCTOFiscalizacao formulario6 = gson.fromJson(resposta.get("cto_fiscalizacao").getResposta(),
                    FormCTOFiscalizacao.class);
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
            FormCTOFiscalizacao formulario6 = gson.fromJson(resposta.get("cto_fiscalizacao").getResposta(),
                    FormCTOFiscalizacao.class);
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
            FormCTOFiscalizacao formulario6 = gson.fromJson(resposta.get("cto_fiscalizacao").getResposta(),
                    FormCTOFiscalizacao.class);
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
            FormCTOFiscalizacao formulario6 = gson.fromJson(resposta.get("cto_fiscalizacao").getResposta(),
                    FormCTOFiscalizacao.class);
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
//Fim
//CTO Detecção Precoce------------------------------------------------------------------------------------
        comentarios.add("Capacidade para detecção precoce e notificação imediata de doenças\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormCTOCapacidadeDeteccaoPrecoce formulario7 = gson.fromJson(resposta.get("cto_deteccao_precoce").getResposta(),
                FormCTOCapacidadeDeteccaoPrecoce.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario7.getComentario()
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
            FormCTOCapacidadeDeteccaoPrecoce formulario7 = gson.fromJson(resposta.get("cto_deteccao_precoce").getResposta(),
                FormCTOCapacidadeDeteccaoPrecoce.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario7.getRecomendacaoUlsavEac()
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
            FormCTOCapacidadeDeteccaoPrecoce formulario7 = gson.fromJson(resposta.get("cto_deteccao_precoce").getResposta(),
                FormCTOCapacidadeDeteccaoPrecoce.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario7.getPrazo()
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
            FormCTOCapacidadeDeteccaoPrecoce formulario7 = gson.fromJson(resposta.get("cto_deteccao_precoce").getResposta(),
                FormCTOCapacidadeDeteccaoPrecoce.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario7.getRecomendacaoUr()
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
            FormCTOCapacidadeDeteccaoPrecoce formulario7 = gson.fromJson(resposta.get("cto_deteccao_precoce").getResposta(),
                FormCTOCapacidadeDeteccaoPrecoce.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario7.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
//Fim
//CTO Atendimento Suspeita----------------------------------------------------------------------------------
        comentarios.add("Capacidade para atendimento a suspeitas e atuação em emergências\n");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormCTOAtendimentoSuspeita formulario8 = gson.fromJson(resposta.get("cto_atendimento_suspeita").getResposta(),
                FormCTOAtendimentoSuspeita.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nComentários: \n" + formulario8.getComentario()
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
            FormCTOAtendimentoSuspeita formulario8 = gson.fromJson(resposta.get("cto_atendimento_suspeita").getResposta(),
                FormCTOAtendimentoSuspeita.class);
            //Adiciona o nome do municipio auditado
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações ULSAV / EAC: \n" + formulario8.getRecomendacaoUlsavEac()
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
            FormCTOAtendimentoSuspeita formulario8 = gson.fromJson(resposta.get("cto_atendimento_suspeita").getResposta(),
                FormCTOAtendimentoSuspeita.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nPrazos para ajuste: \n" + formulario8.getPrazo()
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
            FormCTOAtendimentoSuspeita formulario8 = gson.fromJson(resposta.get("cto_atendimento_suspeita").getResposta(),
                FormCTOAtendimentoSuspeita.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UR: \n" + formulario8.getRecomendacaoUr()
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
            FormCTOAtendimentoSuspeita formulario8 = gson.fromJson(resposta.get("cto_atendimento_suspeita").getResposta(),
                FormCTOAtendimentoSuspeita.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            //adiciona a resposta dos comentarios do municipio auditado
            Paragraph comentariosRecomendacoes = new Paragraph(
                    "\nRecomendações UC: \n" + formulario8.getRecomendacaoUC()
                    + "\n___________________________________________________________________________________\n\n", f);
            comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(comentariosRecomendacoes);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    }
//Fim

    public void PNEFA(Document document, List<Supervisao> list,HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 7);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//PNEFA Fiscalizações----------------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Fiscalização de Vacinações\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormPNEFAFiscalizacoes formulario = gson.fromJson(resposta.get("pnefa_fiscalizacoes").getResposta(),
                FormPNEFAFiscalizacoes.class);
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
            FormPNEFAFiscalizacoes formulario = gson.fromJson(resposta.get("pnefa_fiscalizacoes").getResposta(),
                FormPNEFAFiscalizacoes.class);
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
            FormPNEFAFiscalizacoes formulario = gson.fromJson(resposta.get("pnefa_fiscalizacoes").getResposta(),
                FormPNEFAFiscalizacoes.class);
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
            FormPNEFAFiscalizacoes formulario = gson.fromJson(resposta.get("pnefa_fiscalizacoes").getResposta(),
                FormPNEFAFiscalizacoes.class);
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
            FormPNEFAFiscalizacoes formulario = gson.fromJson(resposta.get("pnefa_fiscalizacoes").getResposta(),
                FormPNEFAFiscalizacoes.class);
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
//PNEFA Vigilância------------------------------------------------------------------------------------------------
        comentarios.add("Vigilância Epidemiológica");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNEFAVigilanciaController.FormPNEFAVigilancia formulario2 = gson.fromJson(resposta.get("pnefa_vigilancia").getResposta(),
                FormPNEFAVigilanciaController.FormPNEFAVigilancia.class);
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
            FormPNEFAVigilanciaController.FormPNEFAVigilancia formulario2 = gson.fromJson(resposta.get("pnefa_vigilancia").getResposta(),
                FormPNEFAVigilanciaController.FormPNEFAVigilancia.class);
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
            FormPNEFAVigilanciaController.FormPNEFAVigilancia formulario2 = gson.fromJson(resposta.get("pnefa_vigilancia").getResposta(),
                FormPNEFAVigilanciaController.FormPNEFAVigilancia.class);
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
            FormPNEFAVigilanciaController.FormPNEFAVigilancia formulario2 = gson.fromJson(resposta.get("pnefa_vigilancia").getResposta(),
                FormPNEFAVigilanciaController.FormPNEFAVigilancia.class);
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
            FormPNEFAVigilanciaController.FormPNEFAVigilancia formulario2 = gson.fromJson(resposta.get("pnefa_vigilancia").getResposta(),
                FormPNEFAVigilanciaController.FormPNEFAVigilancia.class);
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
//PNCEBT Fiscalizações----------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Fiscalização de Vacinações\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormPNCEBTFiscalizacoes formulario = gson.fromJson(resposta.get("pncebt_fiscalizacoes").getResposta(),
                FormPNCEBTFiscalizacoes.class);
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
            FormPNCEBTFiscalizacoes formulario = gson.fromJson(resposta.get("pncebt_fiscalizacoes").getResposta(),
                FormPNCEBTFiscalizacoes.class);
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
            FormPNCEBTFiscalizacoes formulario = gson.fromJson(resposta.get("pncebt_fiscalizacoes").getResposta(),
                FormPNCEBTFiscalizacoes.class);
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
            FormPNCEBTFiscalizacoes formulario = gson.fromJson(resposta.get("pncebt_fiscalizacoes").getResposta(),
                FormPNCEBTFiscalizacoes.class);
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
            FormPNCEBTFiscalizacoes formulario = gson.fromJson(resposta.get("pncebt_fiscalizacoes").getResposta(),
                FormPNCEBTFiscalizacoes.class);
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
//PNCEBT Vigilância------------------------------------------------------------------------------------------
        comentarios.add("Vigilância Epidemiológica");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNCEBTVigilianciaController.FormPNCEBTVigilancia formulario2 = gson.fromJson(resposta.get("pncebt_vigilancia").getResposta(),
                FormPNCEBTVigilianciaController.FormPNCEBTVigilancia.class);
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
            FormPNCEBTVigilianciaController.FormPNCEBTVigilancia formulario2 = gson.fromJson(resposta.get("pncebt_vigilancia").getResposta(),
                FormPNCEBTVigilianciaController.FormPNCEBTVigilancia.class);
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
            FormPNCEBTVigilianciaController.FormPNCEBTVigilancia formulario2 = gson.fromJson(resposta.get("pncebt_vigilancia").getResposta(),
                FormPNCEBTVigilianciaController.FormPNCEBTVigilancia.class);
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
            FormPNCEBTVigilianciaController.FormPNCEBTVigilancia formulario2 = gson.fromJson(resposta.get("pncebt_vigilancia").getResposta(),
                FormPNCEBTVigilianciaController.FormPNCEBTVigilancia.class);
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
            FormPNCEBTVigilianciaController.FormPNCEBTVigilancia formulario2 = gson.fromJson(resposta.get("pncebt_vigilancia").getResposta(),
                FormPNCEBTVigilianciaController.FormPNCEBTVigilancia.class);
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
//PNCEBT Controles-----------------------------------------------------------------------------------------------
        comentarios.add("Controles do Programa");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNCEBTControlesController.FormPNCEBTControles formulario3 = gson.fromJson(resposta.get("pncebt_controles").getResposta(),
                FormPNCEBTControlesController.FormPNCEBTControles.class);
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
            FormPNCEBTControlesController.FormPNCEBTControles formulario3 = gson.fromJson(resposta.get("pncebt_controles").getResposta(),
                FormPNCEBTControlesController.FormPNCEBTControles.class);
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
            FormPNCEBTControlesController.FormPNCEBTControles formulario3 = gson.fromJson(resposta.get("pncebt_controles").getResposta(),
                FormPNCEBTControlesController.FormPNCEBTControles.class);
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
            FormPNCEBTControlesController.FormPNCEBTControles formulario3 = gson.fromJson(resposta.get("pncebt_controles").getResposta(),
                FormPNCEBTControlesController.FormPNCEBTControles.class);
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
            FormPNCEBTControlesController.FormPNCEBTControles formulario3 = gson.fromJson(resposta.get("pncebt_controles").getResposta(),
                FormPNCEBTControlesController.FormPNCEBTControles.class);
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
//PNCRH Fiscalizações--------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Fiscalização de Vacinações\n", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormPNCRHFiscalizacoes formulario = gson.fromJson(resposta.get("pncrh_fiscalizacoes").getResposta(),
                FormPNCRHFiscalizacoes.class);
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
            FormPNCRHFiscalizacoes formulario = gson.fromJson(resposta.get("pncrh_fiscalizacoes").getResposta(),
                FormPNCRHFiscalizacoes.class);
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
            FormPNCRHFiscalizacoes formulario = gson.fromJson(resposta.get("pncrh_fiscalizacoes").getResposta(),
                FormPNCRHFiscalizacoes.class);
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
            FormPNCRHFiscalizacoes formulario = gson.fromJson(resposta.get("pncrh_fiscalizacoes").getResposta(),
                FormPNCRHFiscalizacoes.class);
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
            FormPNCRHFiscalizacoes formulario = gson.fromJson(resposta.get("pncrh_fiscalizacoes").getResposta(),
                FormPNCRHFiscalizacoes.class);
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
//PNCRH Vigilância------------------------------------------------------------------------------------------
        comentarios.add("Vigilância Epidemiológica");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNCRHVigilanciaController.FormPNCRHVigilancia formulario2 = gson.fromJson(resposta.get("pncrh_vigilancia").getResposta(),
                FormPNCRHVigilanciaController.FormPNCRHVigilancia.class);
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
            FormPNCRHVigilanciaController.FormPNCRHVigilancia formulario2 = gson.fromJson(resposta.get("pncrh_vigilancia").getResposta(),
                FormPNCRHVigilanciaController.FormPNCRHVigilancia.class);
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
            FormPNCRHVigilanciaController.FormPNCRHVigilancia formulario2 = gson.fromJson(resposta.get("pncrh_vigilancia").getResposta(),
                FormPNCRHVigilanciaController.FormPNCRHVigilancia.class);
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
            FormPNCRHVigilanciaController.FormPNCRHVigilancia formulario2 = gson.fromJson(resposta.get("pncrh_vigilancia").getResposta(),
                FormPNCRHVigilanciaController.FormPNCRHVigilancia.class);
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
            FormPNCRHVigilanciaController.FormPNCRHVigilancia formulario2 = gson.fromJson(resposta.get("pncrh_vigilancia").getResposta(),
                FormPNCRHVigilanciaController.FormPNCRHVigilancia.class);
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
    }
//Fim
//PNEEB--------------------------------------------------------------------------------------------------
    public void PNEEB(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor(
                "Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)",
                chaFont);
        anchor.setName(
                "Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 10);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//PNEEB Vigilância--------------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Vigilância Epidemiológica", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormPNEEBVigilancia formulario = gson.fromJson(resposta.get("pneeb_vigilancia").getResposta(),
                FormPNEEBVigilancia.class);
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
            FormPNEEBVigilancia formulario = gson.fromJson(resposta.get("pneeb_vigilancia").getResposta(),
                FormPNEEBVigilancia.class);
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
            FormPNEEBVigilancia formulario = gson.fromJson(resposta.get("pneeb_vigilancia").getResposta(),
                FormPNEEBVigilancia.class);
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
            FormPNEEBVigilancia formulario = gson.fromJson(resposta.get("pneeb_vigilancia").getResposta(),
                FormPNEEBVigilancia.class);
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
            FormPNEEBVigilancia formulario = gson.fromJson(resposta.get("pneeb_vigilancia").getResposta(),
                FormPNEEBVigilancia.class);
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
//PNSE----------------------------------------------------------------------------------------------------
    public void PNSE(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 11);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//PNSE Cadastro Estabelecimentos--------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Cadastro de Estabelecimentos", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormPNSECadastroEstabelecimento formulario = gson.fromJson(resposta.get("pnse_cadastro_estabelecimentos").getResposta(), 
                    FormPNSECadastroEstabelecimento.class);
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
            FormPNSECadastroEstabelecimento formulario = gson.fromJson(resposta.get("pnse_cadastro_estabelecimentos").getResposta(), 
                    FormPNSECadastroEstabelecimento.class);
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
            FormPNSECadastroEstabelecimento formulario = gson.fromJson(resposta.get("pnse_cadastro_estabelecimentos").getResposta(), 
                    FormPNSECadastroEstabelecimento.class);
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
            FormPNSECadastroEstabelecimento formulario = gson.fromJson(resposta.get("pnse_cadastro_estabelecimentos").getResposta(), 
                    FormPNSECadastroEstabelecimento.class);
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
            FormPNSECadastroEstabelecimento formulario = gson.fromJson(resposta.get("pnse_cadastro_estabelecimentos").getResposta(), 
                    FormPNSECadastroEstabelecimento.class);
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
//PNSE Vigilância-----------------------------------------------------------------------------------------------
        comentarios.add("Vigilância Epidemiológica");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNSEVigilanciaController.FormPNSEVigilancia formulario2 = gson.fromJson(resposta.get("pnse_vigilancia").getResposta(),
                FormPNSEVigilanciaController.FormPNSEVigilancia.class);
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
            FormPNSEVigilanciaController.FormPNSEVigilancia formulario2 = gson.fromJson(resposta.get("pnse_vigilancia").getResposta(),
                FormPNSEVigilanciaController.FormPNSEVigilancia.class);
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
            FormPNSEVigilanciaController.FormPNSEVigilancia formulario2 = gson.fromJson(resposta.get("pnse_vigilancia").getResposta(),
                FormPNSEVigilanciaController.FormPNSEVigilancia.class);
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
            FormPNSEVigilanciaController.FormPNSEVigilancia formulario2 = gson.fromJson(resposta.get("pnse_vigilancia").getResposta(),
                FormPNSEVigilanciaController.FormPNSEVigilancia.class);
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
            FormPNSEVigilanciaController.FormPNSEVigilancia formulario2 = gson.fromJson(resposta.get("pnse_vigilancia").getResposta(),
                FormPNSEVigilanciaController.FormPNSEVigilancia.class);
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
//PNSE Controles-------------------------------------------------------------------------------------------------
        comentarios.add("Controles do Programa");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNSEControlesController.FormPNSEControles formulario3 = gson.fromJson(resposta.get("pnse_controles").getResposta(),
                FormPNSEControlesController.FormPNSEControles.class);
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
            FormPNSEControlesController.FormPNSEControles formulario3 = gson.fromJson(resposta.get("pnse_controles").getResposta(),
                FormPNSEControlesController.FormPNSEControles.class);
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
            FormPNSEControlesController.FormPNSEControles formulario3 = gson.fromJson(resposta.get("pnse_controles").getResposta(),
                FormPNSEControlesController.FormPNSEControles.class);
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
            FormPNSEControlesController.FormPNSEControles formulario3 = gson.fromJson(resposta.get("pnse_controles").getResposta(),
                FormPNSEControlesController.FormPNSEControles.class);
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
            FormPNSEControlesController.FormPNSEControles formulario3 = gson.fromJson(resposta.get("pnse_controles").getResposta(),
                FormPNSEControlesController.FormPNSEControles.class);
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
    }
//Fim
//PNSCO-----------------------------------------------------------------------------------------------------
    public void PNSCO(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 12);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//PNSCO Cadastro Estabelecimentos----------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Cadastro de Estabelecimentos", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormPNSCOCadastros formulario = gson.fromJson(resposta.get("pnsco_cadastro_estabelecimentos").getResposta(),
                FormPNSCOCadastros.class);
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
            FormPNSCOCadastros formulario = gson.fromJson(resposta.get("pnsco_cadastro_estabelecimentos").getResposta(),
                FormPNSCOCadastros.class);
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
            FormPNSCOCadastros formulario = gson.fromJson(resposta.get("pnsco_cadastro_estabelecimentos").getResposta(),
                FormPNSCOCadastros.class);
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
            FormPNSCOCadastros formulario = gson.fromJson(resposta.get("pnsco_cadastro_estabelecimentos").getResposta(),
                FormPNSCOCadastros.class);
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
            FormPNSCOCadastros formulario = gson.fromJson(resposta.get("pnsco_cadastro_estabelecimentos").getResposta(),
                FormPNSCOCadastros.class);
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
//PNSCO Vigilância------------------------------------------------------------------------------------------
        comentarios.add("Vigilância Epidemiológica");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNSCOVigilanciaController.FormPNSCOVigilancia formulario2 = gson.fromJson(resposta.get("pnsco_vigilancia").getResposta(),
                FormPNSCOVigilanciaController.FormPNSCOVigilancia.class);
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
            FormPNSCOVigilanciaController.FormPNSCOVigilancia formulario2 = gson.fromJson(resposta.get("pnsco_vigilancia").getResposta(),
                FormPNSCOVigilanciaController.FormPNSCOVigilancia.class);
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
            FormPNSCOVigilanciaController.FormPNSCOVigilancia formulario2 = gson.fromJson(resposta.get("pnsco_vigilancia").getResposta(),
                FormPNSCOVigilanciaController.FormPNSCOVigilancia.class);
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
            FormPNSCOVigilanciaController.FormPNSCOVigilancia formulario2 = gson.fromJson(resposta.get("pnsco_vigilancia").getResposta(),
                FormPNSCOVigilanciaController.FormPNSCOVigilancia.class);
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
            FormPNSCOVigilanciaController.FormPNSCOVigilancia formulario2 = gson.fromJson(resposta.get("pnsco_vigilancia").getResposta(),
                FormPNSCOVigilanciaController.FormPNSCOVigilancia.class);
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
    }
//Fim
//PNSS------------------------------------------------------------------------------------------------------
    public void PNSS(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 13);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//PNSS Cadastro Estabelecimentos-----------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Cadastro de Estabelecimentos", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnss_cadastro_estabelecimentos").getResposta(), FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento.class);
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
            FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnss_cadastro_estabelecimentos").getResposta(), FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento.class);
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
            FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnss_cadastro_estabelecimentos").getResposta(), FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento.class);
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
            FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnss_cadastro_estabelecimentos").getResposta(), FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento.class);
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
            FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnss_cadastro_estabelecimentos").getResposta(), FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento.class);
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
//PNSS Vigilância------------------------------------------------------------------------------------------
        comentarios.add("Vigilância Epidemiológica");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNSSVigilancia formulario2 = gson.fromJson(resposta.get("pnss_vigilancia").getResposta(),
                FormPNSSVigilancia.class);
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
            FormPNSSVigilancia formulario2 = gson.fromJson(resposta.get("pnss_vigilancia").getResposta(),
                FormPNSSVigilancia.class);
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
            FormPNSSVigilancia formulario2 = gson.fromJson(resposta.get("pnss_vigilancia").getResposta(),
                FormPNSSVigilancia.class);
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
            FormPNSSVigilancia formulario2 = gson.fromJson(resposta.get("pnss_vigilancia").getResposta(),
                FormPNSSVigilancia.class);
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
            FormPNSSVigilancia formulario2 = gson.fromJson(resposta.get("pnss_vigilancia").getResposta(),
                FormPNSSVigilancia.class);
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
    }
//Fim
//PNSA--------------------------------------------------------------------------------------------------------
    public void PNSA(Document document, List<Supervisao> list, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 14);// onde e descrito qual o numero do capitulo
        document.add(catPart);
        Gson gson = new Gson();
//PNSA Cadastro Estabelecimentos-------------------------------------------------------------------------------
        Paragraph comentarios = new Paragraph("Cadastro de Estabelecimentos", subFont);
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos formulario = gson.fromJson(
                resposta.get("pnsa_cadastro_estabelecimentos").getResposta(), FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos.class);
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
            FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos formulario = gson.fromJson(
                resposta.get("pnsa_cadastro_estabelecimentos").getResposta(), FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos.class);
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
            FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos formulario = gson.fromJson(
                resposta.get("pnsa_cadastro_estabelecimentos").getResposta(), FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos.class);
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
            FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos formulario = gson.fromJson(
                resposta.get("pnsa_cadastro_estabelecimentos").getResposta(), FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos.class);
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
            FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos formulario = gson.fromJson(
                resposta.get("pnsa_cadastro_estabelecimentos").getResposta(), FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos.class);
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
//PNSA Vigilância-------------------------------------------------------------------------------------------
        comentarios.add("Vigilância Epidemiológica");
        comentarios.add("Comentarios");
        document.add(comentarios);
        comentarios.clear();
        
        //Laço para mostrar os COMENTÁRIOS de todos os municipios auditados
        for (Supervisao supervisao : list){
            FormPNSAVigilancia formulario2 = gson.fromJson(resposta.get("pnsa_vigilancia").getResposta(),
                FormPNSAVigilancia.class);
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
            FormPNSAVigilancia formulario2 = gson.fromJson(resposta.get("pnsa_vigilancia").getResposta(),
                FormPNSAVigilancia.class);
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
            FormPNSAVigilancia formulario2 = gson.fromJson(resposta.get("pnsa_vigilancia").getResposta(),
                FormPNSAVigilancia.class);
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
            FormPNSAVigilancia formulario2 = gson.fromJson(resposta.get("pnsa_vigilancia").getResposta(),
                FormPNSAVigilancia.class);
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
            FormPNSAVigilancia formulario2 = gson.fromJson(resposta.get("pnsa_vigilancia").getResposta(),
                FormPNSAVigilancia.class);
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
    }
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
            FormICEducacaoSanitaria formulario = gson.fromJson(resposta.get("interacao_comunidade_educacao").getResposta(),
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
            FormICEducacaoSanitaria formulario = gson.fromJson(resposta.get("interacao_comunidade_educacao").getResposta(),
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
            FormICEducacaoSanitaria formulario = gson.fromJson(resposta.get("interacao_comunidade_educacao").getResposta(),
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
            FormICEducacaoSanitaria formulario = gson.fromJson(resposta.get("interacao_comunidade_educacao").getResposta(),
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
            FormICEducacaoSanitaria formulario = gson.fromJson(resposta.get("interacao_comunidade_educacao").getResposta(),
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
                resposta.get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
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
                resposta.get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
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
                resposta.get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
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
                resposta.get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
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
                resposta.get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
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
                resposta.get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
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
                resposta.get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
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
                resposta.get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
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
                resposta.get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
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
                resposta.get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
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
            FormIMVHabilitacao formulario4 = gson.fromJson(resposta.get("interacao_veterinario_habilitacao").getResposta(),
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
            FormIMVHabilitacao formulario4 = gson.fromJson(resposta.get("interacao_veterinario_habilitacao").getResposta(),
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
            FormIMVHabilitacao formulario4 = gson.fromJson(resposta.get("interacao_veterinario_habilitacao").getResposta(),
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
            FormIMVHabilitacao formulario4 = gson.fromJson(resposta.get("interacao_veterinario_habilitacao").getResposta(),
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
            FormIMVHabilitacao formulario4 = gson.fromJson(resposta.get("interacao_veterinario_habilitacao").getResposta(),
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
            FormIISistemaInspecao formulario5 = gson.fromJson(resposta.get("interacao_instituicao_inspecao").getResposta(),
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
            FormIISistemaInspecao formulario5 = gson.fromJson(resposta.get("interacao_instituicao_inspecao").getResposta(),
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
            FormIISistemaInspecao formulario5 = gson.fromJson(resposta.get("interacao_instituicao_inspecao").getResposta(),
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
            FormIISistemaInspecao formulario5 = gson.fromJson(resposta.get("interacao_instituicao_inspecao").getResposta(),
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
            FormIISistemaInspecao formulario5 = gson.fromJson(resposta.get("interacao_instituicao_inspecao").getResposta(),
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
            FormIISUS formulario6 = gson.fromJson(resposta.get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
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
            FormIISUS formulario6 = gson.fromJson(resposta.get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
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
            FormIISUS formulario6 = gson.fromJson(resposta.get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
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
            FormIISUS formulario6 = gson.fromJson(resposta.get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
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
            FormIISUS formulario6 = gson.fromJson(resposta.get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
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
            FormAMAcesso formulario = gson.fromJson(resposta.get("acesso_mercado").getResposta(), FormAMAcesso.class);
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
            FormAMAcesso formulario = gson.fromJson(resposta.get("acesso_mercado").getResposta(), FormAMAcesso.class);
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
            FormAMAcesso formulario = gson.fromJson(resposta.get("acesso_mercado").getResposta(), FormAMAcesso.class);
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
            FormAMAcesso formulario = gson.fromJson(resposta.get("acesso_mercado").getResposta(), FormAMAcesso.class);
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
            FormAMAcesso formulario = gson.fromJson(resposta.get("acesso_mercado").getResposta(), FormAMAcesso.class);
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
        
        Paragraph resp = new Paragraph();
        for (Supervisao supervisao : list){
            FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades formulario = gson.fromJson(
                    resposta.get("vulnerabilidade_potencialidade").getResposta(),
                    FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            Paragraph vulnerabilidades = new Paragraph(
                    "Vulnerabilidades que o escritório apresenta na execução das ações de defesa animal: \n"
                    + formulario.getVulnerabilidades(), f);
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
                    resposta.get("vulnerabilidade_potencialidade").getResposta(),
                    FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades.class);
            String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                    : supervisao.getUlsav().getNome());
            Paragraph muniAuditado = new Paragraph("Municipio Auditado: " + municipio, smallBold);
            resp.add(muniAuditado);
            Paragraph potencialidades = new Paragraph(
                    "Potencialidades que o escritório apresenta na execução das ações de defesa animal: \n"
                    + formulario.getPotencialidades(), f);
            potencialidades.setAlignment(Element.ALIGN_JUSTIFIED);
            resp.add(potencialidades);
        }
        document.add(resp);
        resp.clear();
        document.newPage();
    }
//Fim
    public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
