package stec.model.domain;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
import stec.controller.FormRFEquipamentosController;
import stec.controller.FormRFTransportesController;
import stec.controller.FormRHCapacitacaoController;
import stec.controller.FormVulnerabilidadesPotencialidadesController;

public class RelatorioAuditoriaCompilada {

    private static Font f = new Font(FontFamily.HELVETICA, 10, Font.NORMAL);// para texto normal
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);// titulo do documento
    private static Font chaFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);// capitulos do documento
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);// negrito texto normal
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);// sub capitulos capitulo
    private Supervisao supervisao = new Supervisao();
    private HashMap<String, Resposta> hashRespostas = new HashMap<>();

    public void setSupervisao(Supervisao supervisao) {
        this.supervisao = supervisao;
    }

    public Supervisao getSupervisao() {
        return this.supervisao;
    }

    public void setHashRespostas(HashMap<String, Resposta> respostas) {
        this.hashRespostas = respostas;
    }

    public HashMap<String, Resposta> getHashRespostas() {
        return this.hashRespostas;
    }

    public void show(String file) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("c:/Projetos/STECv2.0/Relatórios PDF/" + file + ".pdf"));
            document.open();

            this.Capa(document, supervisao);
            this.IdentificacaoEscritorio(document, supervisao, hashRespostas);
            this.RecursosHumanos(document, hashRespostas);
            this.RecursosFisicos(document, supervisao, hashRespostas);
            this.RecursosFinanceiros(document, hashRespostas);
            this.EstruturaOrganizacional(document, hashRespostas);
            this.AutoridadeGestao(document, hashRespostas);
            this.CapacidadeTecnicaOperacional(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNEFA")) {
                this.PNEFA(document, hashRespostas);
            }

            if (supervisao.getProgramas().toString().contains("PNCEBT")) {
                this.PNCEBT(document, hashRespostas);
            }

            if (supervisao.getProgramas().toString().contains("PNCRH")) {
                this.PNCRH(document, hashRespostas);
            }

            if (supervisao.getProgramas().toString().contains("PNEEB")) {
                this.PNEEB(document, hashRespostas);
            }

            if (supervisao.getProgramas().toString().contains("PNSE")) {
                this.PNSE(document, hashRespostas);
            }

            if (supervisao.getProgramas().toString().contains("PNSCO")) {
                this.PNSCO(document, hashRespostas);
            }

            if (supervisao.getProgramas().toString().contains("PNSS")) {
                this.PNSS(document, hashRespostas);
            }

            if (supervisao.getProgramas().toString().contains("PNSA")) {
                this.PNSA(document, hashRespostas);
            }

            this.InteracaoPartes(document, hashRespostas);
            this.AcessoMercados(document, hashRespostas);
            this.VulnerabilidadesPotencialidades(document, hashRespostas);
            this.Assinaturas(document, supervisao, hashRespostas);
            
            document.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
    * Adiciona a capa com o titulo ao documento OK
     */
    public void Capa(Document document, Supervisao supervisao) throws DocumentException {
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

    public void IdentificacaoEscritorio(Document document, Supervisao supervisao, HashMap<String, Resposta> resposta) throws DocumentException {
        Paragraph identificacaoEscritorio = new Paragraph();

        Anchor titulo = new Anchor("Identificação de Escritório", chaFont);
        titulo.setName("Identificação de Escritório");
        identificacaoEscritorio.add(titulo);

        addEmptyLine(identificacaoEscritorio, 1);

        Gson gson = new Gson();

        FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio formulario = gson.fromJson(resposta.get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio.class);

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        identificacaoEscritorio.add(comentariosRecomendacoes);

        document.add(identificacaoEscritorio);

        document.newPage();
    }

    public void RecursosHumanos(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Humanos", chaFont);
        anchor.setName("Recursos Humanos");

        // onde e descrito qual o numero do capitulo
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Gson gson = new Gson();
        
//RH Quantitativo----------------------------------------------------------------------------------------
        FormRHQuantitativo formulario = gson.fromJson(resposta.get("rh_quantitativo").getResposta(),
                FormRHQuantitativo.class);

        // sub titulo do sub capitulo com a avaliacao
        Paragraph subPara = new Paragraph();

        //Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        Section subCatPart = catPart.addSection(new Paragraph("Quantitativo, jornada e distribuição", subFont));

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);
//fim
        // ------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        
        //RH Estabilidade----------------------------------------------------------------------------
        FormRHEstabilidade formulario2 = gson.fromJson(resposta.get("rh_estabilidade").getResposta(),
                FormRHEstabilidade.class);

        // adiciona o sub titulo da secao do capitulo
        subCatPart = catPart.addSection(new Paragraph("Estabilidade das estruturas e sustentabilidade das políticas sanitárias", subFont));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);
//fim
        // -------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        
//RH Capacitação--------------------------------------------------------------------------------------
        FormRHCapacitacao formulario3 = gson.fromJson(resposta.get("rh_capacitacao").getResposta(),
                FormRHCapacitacao.class);
        
        subCatPart = catPart
                .addSection(new Paragraph("Capacitação técnica e formação continuada", subFont));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);
//fim
        // -------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        
//RH Competências-------------------------------------------------------------------------------------
        FormRHCompetencias formulario4 = gson.fromJson(resposta.get("rh_competencias").getResposta(),
                FormRHCompetencias.class);
        
        subCatPart = catPart
                .addSection(new Paragraph("Competências e independência técnica", subFont));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario4.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario4.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario4.getPrazo() + "\nRecomendações UR: \n" + formulario4.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario4.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();// adiciona uma nova página
    }
//fim
    public void RecursosFisicos(Document document, Supervisao supervisao, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Físicos", chaFont);
        anchor.setName("Recursos Físicos");

        Chapter catPart = new Chapter(new Paragraph(anchor), 2);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//RF Instalações------------------------------------------------------------------------------------
        FormRFInstalacoes formulario = gson.fromJson(resposta.get("rf_instalacoes").getResposta(),
                FormRFInstalacoes.class);
        Paragraph subPara = new Paragraph("Instalações",subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//RF Transportes-------------------------------------------------------------------------------------
        FormRFTransportes formulario2 = gson.fromJson(resposta.get("rf_transportes").getResposta(),
                FormRFTransportes.class);
        subPara = new Paragraph("Transportes", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//RF Equipamentos-------------------------------------------------------------------------------------
        FormRFEquipamentos formulario3 = gson.fromJson(resposta.get("rf_equipamentos").getResposta(),
                FormRFEquipamentos.class);
        subPara = new Paragraph("Equipamentos e acesso à comunicação", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//RF Sistemas------------------------------------------------------------------------------------------
        FormRFSistemasInformacao formulario4 = gson.fromJson(resposta.get("rf_sistemas").getResposta(),
                FormRFSistemasInformacao.class);
        subPara = new Paragraph("Sistemas de informação", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario4.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario4.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario4.getPrazo() + "\nRecomendações UR: \n" + formulario4.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario4.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void RecursosFinanceiros(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Financeiros", chaFont);
        anchor.setName("Recursos Financeiros");

        Chapter catPart = new Chapter(new Paragraph(anchor), 3);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//RFI Custeio--------------------------------------------------------------------------------------------------------
        FormRFICusteio formulario = gson.fromJson(resposta.get("rfi_custeio").getResposta(), FormRFICusteio.class);
        Paragraph subPara = new Paragraph("Recurso para Custeio", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//RFI Arrecadação-----------------------------------------------------------------------------------------
        FormRFIArrecadacao formulario2 = gson.fromJson(resposta.get("rfi_arrecadacao").getResposta(),
                FormRFIArrecadacao.class);
        subPara = new Paragraph("Arrecadação", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void EstruturaOrganizacional(Document document, HashMap<String, Resposta> resposta)
            throws DocumentException {
        Anchor anchor = new Anchor("Estrutura Organizacional", chaFont);
        anchor.setName("Estrutura Organizacional");

        Chapter catPart = new Chapter(new Paragraph(anchor), 4);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//EO Estrutura--------------------------------------------------------------------------------------
        FormEOEstrutura formulario = gson.fromJson(resposta.get("eo_estrutura").getResposta(), FormEOEstrutura.class);
        Paragraph subPara = new Paragraph("Estrutura organizacional e capacidade de coordenação interna", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void AutoridadeGestao(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Autoridade e Gestão de Qualidade", chaFont);
        anchor.setName("Autoridade e Gestão de Qualidade");

        Chapter catPart = new Chapter(new Paragraph(anchor), 5);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//AG Base Legal---------------------------------------------------------------------------------------------
        FormAGBaseLegal formulario = gson.fromJson(resposta.get("ag_base_legal").getResposta(), FormAGBaseLegal.class);
        Paragraph subPara = new Paragraph("Base legal e aplicação da legislação, manuais e POP", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -----------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//AG Organização-------------------------------------------------------------------------------------
        FormAGOrganizacao formulario2 = gson.fromJson(resposta.get("ag_organizacao").getResposta(),FormAGOrganizacao.class);
        subPara = new Paragraph("Organização dos processos e unidades", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//AG Supervisão------------------------------------------------------------------------------------
        FormAGSupervisao formulario3 = gson.fromJson(resposta.get("ag_supervisao").getResposta(),
                FormAGSupervisao.class);
        subPara = new Paragraph("Supervisão e controle interno", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void CapacidadeTecnicaOperacional(Document document, HashMap<String, Resposta> resposta)
            throws DocumentException {
        Anchor anchor = new Anchor("Capacidade Técnica e Operacional", chaFont);
        anchor.setName("Capacidade Técnica e Operacional");

        Chapter catPart = new Chapter(new Paragraph(anchor), 5);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//CTO Controle Cadastro-----------------------------------------------------------------------------------------
        FormCTOControleCadastro formulario = gson.fromJson(resposta.get("cto_controle_cadastro").getResposta(),
                FormCTOControleCadastro.class);
        Paragraph subPara = new Paragraph("Controle de cadastro de produtores, propriedades e animais", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -----------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//CTO Planejamento---------------------------------------------------------------------------------------
        FormCTOPlanejamento formulario2 = gson.fromJson(resposta.get("cto_planejamento").getResposta(),
                FormCTOPlanejamento.class);
        subPara = new Paragraph("Planejamento, execução de atividades e registro", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//CTO Controle-------------------------------------------------------------------------------------------
        FormCTOControle formulario3 = gson.fromJson(resposta.get("cto_controle").getResposta(), FormCTOControle.class);
        subPara = new Paragraph("Controle de divisas e trânsito interno", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//CTO Transito Animais-----------------------------------------------------------------------------------
        FormCTOControleTransitoAnimais formulario4 = gson.fromJson(resposta.get("cto_transito_animais").getResposta(),
                FormCTOControleTransitoAnimais.class);
        subPara = new Paragraph("Controle de trânsito de animais e produtos de origem animal", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario4.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario4.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario4.getPrazo() + "\nRecomendações UR: \n" + formulario4.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario4.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//CTO Controle Eventos------------------------------------------------------------------------------------
        FormCTOControleEventosAglomeracao formulario5 = gson
                .fromJson(resposta.get("cto_controle_eventos").getResposta(), FormCTOControleEventosAglomeracao.class);
        subPara = new Paragraph("Controle de eventos de aglomeração de animais", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph p2 = new Paragraph();
        addEmptyLine(p2, 2);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario5.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario5.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario5.getPrazo() + "\nRecomendações UR: \n" + formulario5.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario5.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//CTO Fiscalização-------------------------------------------------------------------------------------
        FormCTOFiscalizacao formulario6 = gson.fromJson(resposta.get("cto_fiscalizacao").getResposta(),
                FormCTOFiscalizacao.class);
        subPara = new Paragraph(
                "Fiscalização em revendas veterinárias", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario6.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario6.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario6.getPrazo() + "\nRecomendações UR: \n" + formulario6.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario6.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//CTO Detecção Precoce------------------------------------------------------------------------------------
        FormCTOCapacidadeDeteccaoPrecoce formulario7 = gson.fromJson(resposta.get("cto_deteccao_precoce").getResposta(),
                FormCTOCapacidadeDeteccaoPrecoce.class);
        subPara = new Paragraph("Capacidade para detecção precoce e notificação imediata de doenças", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario7.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario7.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario7.getPrazo() + "\nRecomendações UR: \n" + formulario7.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario7.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//CTO Atendimento Suspeita----------------------------------------------------------------------------------
        FormCTOAtendimentoSuspeita formulario8 = gson.fromJson(resposta.get("cto_atendimento_suspeita").getResposta(),
                FormCTOAtendimentoSuspeita.class);
        subPara = new Paragraph("Capacidade para atendimento a suspeitas e atuação em emergências", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario8.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario8.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario8.getPrazo() + "\nRecomendações UR: \n" + formulario8.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario8.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void PNEFA(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 6);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//PNEFA Fiscalizações----------------------------------------------------------------------------------------------
        FormPNEFAFiscalizacoes formulario = gson.fromJson(resposta.get("pnefa_fiscalizacoes").getResposta(),
                FormPNEFAFiscalizacoes.class);
        Paragraph subPara = new Paragraph("Fiscalização de Vacinações", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNEFA Vigilância------------------------------------------------------------------------------------------------
        FormPNEFAVigilanciaController.FormPNEFAVigilancia formulario2 = gson.fromJson(resposta.get("pnefa_vigilancia").getResposta(),
                FormPNEFAVigilanciaController.FormPNEFAVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void PNCEBT(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor(
                "Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 7);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//PNCEBT Fiscalizações----------------------------------------------------------------------------------------
        FormPNCEBTFiscalizacoes formulario = gson.fromJson(resposta.get("pncebt_fiscalizacoes").getResposta(),
                FormPNCEBTFiscalizacoes.class);
        Paragraph subPara = new Paragraph("Fiscalização de Vacinações", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNCEBT Vigilância------------------------------------------------------------------------------------------
        FormPNCEBTVigilianciaController.FormPNCEBTVigilancia formulario2 = gson.fromJson(resposta.get("pncebt_vigilancia").getResposta(),
                FormPNCEBTVigilianciaController.FormPNCEBTVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNCEBT Controles-----------------------------------------------------------------------------------------------
        FormPNCEBTControlesController.FormPNCEBTControles formulario3 = gson.fromJson(resposta.get("pncebt_controles").getResposta(),
                FormPNCEBTControlesController.FormPNCEBTControles.class);
        subPara = new Paragraph("Controles do Programa", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void PNCRH(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 8);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//PNCRH Fiscalizações--------------------------------------------------------------------------------------
        FormPNCRHFiscalizacoes formulario = gson.fromJson(resposta.get("pncrh_fiscalizacoes").getResposta(),
                FormPNCRHFiscalizacoes.class);
        Paragraph subPara = new Paragraph("Fiscalização de Vacinações", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNCRH Vigilância------------------------------------------------------------------------------------------
        FormPNCRHVigilanciaController.FormPNCRHVigilancia formulario2 = gson.fromJson(resposta.get("pncrh_vigilancia").getResposta(),
                FormPNCRHVigilanciaController.FormPNCRHVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void PNEEB(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor(
                "Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)",
                chaFont);
        anchor.setName(
                "Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 8);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//PNEEB Vigilância--------------------------------------------------------------------------------------
        FormPNEEBVigilancia formulario = gson.fromJson(resposta.get("pneeb_vigilancia").getResposta(),
                FormPNEEBVigilancia.class);
        Paragraph subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void PNSE(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 9);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//PNSE Cadastro Estabelecimentos--------------------------------------------------------------------------
        FormPNSECadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnse_cadastro_estabelecimentos").getResposta(), FormPNSECadastroEstabelecimento.class);
        Paragraph subPara = new Paragraph("Cadastro de Estabelecimentos", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNSE Vigilância-----------------------------------------------------------------------------------------------
        FormPNSEVigilanciaController.FormPNSEVigilancia formulario2 = gson.fromJson(resposta.get("pnse_vigilancia").getResposta(),
                FormPNSEVigilanciaController.FormPNSEVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNSE Controles-------------------------------------------------------------------------------------------------
        FormPNSEControlesController.FormPNSEControles formulario3 = gson.fromJson(resposta.get("pnse_controles").getResposta(),
                FormPNSEControlesController.FormPNSEControles.class);
        subPara = new Paragraph("Controles do Programa", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void PNSCO(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 8);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//PNSCO Cadastro Estabelecimentos----------------------------------------------------------------------------
        FormPNSCOCadastros formulario = gson.fromJson(resposta.get("pnsco_cadastro_estabelecimentos").getResposta(),
                FormPNSCOCadastros.class);
        Paragraph subPara = new Paragraph("Cadastro de Estabelecimentos", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNSCO Vigilância------------------------------------------------------------------------------------------
        FormPNSCOVigilanciaController.FormPNSCOVigilancia formulario2 = gson.fromJson(resposta.get("pnsco_vigilancia").getResposta(),
                FormPNSCOVigilanciaController.FormPNSCOVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void PNSS(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 10);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//PNSS Cadastro Estabelecimentos-----------------------------------------------------------------------------
        FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnss_cadastro_estabelecimentos").getResposta(), FormPNSSCadastroEstabelecimentosController.FormPNSSCadastroEstabelecimento.class);
        Paragraph subPara = new Paragraph("Cadastro de Estabelecimentos", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNSS Vigilância------------------------------------------------------------------------------------------
        FormPNSSVigilancia formulario2 = gson.fromJson(resposta.get("pnss_vigilancia").getResposta(),
                FormPNSSVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        
        subCatPart.add(new Paragraph("Registro da vigilância em propriedades e pontos de risco: "
                + formulario2.getRegistroVigilancia() + "\nMortandade: " + formulario2.getMortandade(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void PNSA(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 11);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//PNSA Cadastro Estabelecimentos-------------------------------------------------------------------------------
        FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos formulario = gson.fromJson(
                resposta.get("pnsa_cadastro_estabelecimentos").getResposta(), FormPNSACadastroEstabelecimentosController.FormPNSACadastroEstabelecimentos.class);
        Paragraph subPara = new Paragraph("Cadastro de Estabelecimentos", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//PNSA Vigilância-------------------------------------------------------------------------------------------
        FormPNSAVigilancia formulario2 = gson.fromJson(resposta.get("pnsa_vigilancia").getResposta(),
                FormPNSAVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        
        subCatPart.add(new Paragraph("Mortandade: " + formulario2.getMortandade()
                + "\nDoenças de notificação obrigatória: " + formulario2.getDoencaNotificacao(), f));
        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }
//Fim
    public void InteracaoPartes(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Interação com as partes interessadas", chaFont);
        anchor.setName("Interação com as partes interessadas");

        Chapter catPart = new Chapter(new Paragraph(anchor), 12);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
//IP Comunidade Educação--------------------------------------------------------------------------------------
        FormICEducacaoSanitaria formulario = gson.fromJson(resposta.get("interacao_comunidade_educacao").getResposta(),
                FormICEducacaoSanitaria.class);
        Paragraph subPara = new Paragraph("Educação sanitária e comunicação social (divulgação e publicidade)", subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -----------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//IP Comunidade-----------------------------------------------------------------------------------------------
        FormICParticipacaoComunidade formulario2 = gson.fromJson(
                resposta.get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
        subPara = new Paragraph("Participação com a comunidade", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//IP Comunidade Instituições----------------------------------------------------------------------------------
        FormICParticipacaoInstituicoes formulario3 = gson.fromJson(
                resposta.get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
        subPara = new Paragraph("Participação com instituições e representações", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//IP Veterinario Habilitação---------------------------------------------------------------------------------
        FormIMVHabilitacao formulario4 = gson.fromJson(resposta.get("interacao_veterinario_habilitacao").getResposta(),
                FormIMVHabilitacao.class);
        subPara = new Paragraph("Participação com instituições e representações", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario4.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario4.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario4.getPrazo() + "\nRecomendações UR: \n" + formulario4.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario4.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
//Fim
//IP Instituição Inspeção-----------------------------------------------------------------------------------------
        FormIISistemaInspecao formulario5 = gson.fromJson(resposta.get("interacao_instituicao_inspecao").getResposta(),
                FormIISistemaInspecao.class);
        subPara = new Paragraph(
                "Sistema de inspeção (seguridade alimentar)    " + "Avaliação: " + formulario5.getAvaliacao(),
                subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Interação com sistema de inspeção: " + formulario5.getInteracaoInspecao(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario5.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario5.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario5.getPrazo() + "\nRecomendações UR: \n" + formulario5.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario5.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormIISUS formulario6 = gson.fromJson(resposta.get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
        subPara = new Paragraph("Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)    " + "Avaliação: "
                + formulario6.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Interação com sistema único de saúde: " + formulario6.getInteracaoSUS(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario6.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario6.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario6.getPrazo() + "\nRecomendações UR: \n" + formulario6.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario6.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }

    public void AcessoMercados(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Acesso aos mercados", chaFont);
        anchor.setName("Acesso aos mercados");

        Chapter catPart = new Chapter(new Paragraph(anchor), 13);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormAMAcesso formulario = gson.fromJson(resposta.get("acesso_mercado").getResposta(), FormAMAcesso.class);
        Paragraph subPara = new Paragraph("Acesso aos mercados    " + "Avaliação: " + formulario.getAvaliacao(),
                subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Acesso aos mercados: " + formulario.getAcesso(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }

    public void VulnerabilidadesPotencialidades(Document document, HashMap<String, Resposta> resposta)
            throws DocumentException {
        Paragraph identificacaoEscritorio = new Paragraph();

        Anchor titulo = new Anchor("Vulnerabilidades e Potencialidades", chaFont);
        titulo.setName("Vulnerabilidades e Potencialidades");
        identificacaoEscritorio.add(titulo);

        addEmptyLine(identificacaoEscritorio, 1);

        Gson gson = new Gson();

        FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades formulario = gson.fromJson(
                resposta.get("vulnerabilidade_potencialidade").getResposta(),
                FormVulnerabilidadesPotencialidadesController.FormVulnerabilidadesPotencialidades.class);

        Paragraph vulnerabilidades = new Paragraph(
                "Vulnerabilidades que o escritório apresenta na execução das ações de defesa animal: \n"
                + formulario.getVulnerabilidades(),
                f);
        vulnerabilidades.setAlignment(Element.ALIGN_JUSTIFIED);
        identificacaoEscritorio.add(vulnerabilidades);

        addEmptyLine(identificacaoEscritorio, 1);

        Paragraph potencialidades = new Paragraph(
                "Potencialidades que o escritório apresenta na execução das ações de defesa animal: \n"
                + formulario.getPotencialidades(),
                f);
        potencialidades.setAlignment(Element.ALIGN_JUSTIFIED);
        identificacaoEscritorio.add(potencialidades);

        document.add(identificacaoEscritorio);

        document.newPage();
    }

    public void Assinaturas(Document document, Supervisao supervisao, HashMap<String, Resposta> resposta)
            throws DocumentException {
        Paragraph paragraph = new Paragraph();

        Gson gson = new Gson();

        FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio formulario = gson.fromJson(resposta.get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio.class);

        Paragraph atencao = new Paragraph("ATENÇÃO", chaFont);
        atencao.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(atencao);

        addEmptyLine(paragraph, 1);

        Paragraph lista = new Paragraph("Cabe ao responsável por este escritório:", subFont);
        lista.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(lista);

        addEmptyLine(paragraph, 1);

        List list = new List(List.ORDERED);

        ListItem item = new ListItem(
                "Reunir sua equipe de trabalho para discutir o diagnóstico realizado por esta supervisão, "
                + "bem como distribuir tarefas com o objetivo de sanar as possíveis inconformidades apontadas, "
                + "registrando em ata de reunião (formulário padronizado);");
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);

        item = new ListItem("Montar estratégia necessária para atender às recomendações registradas neste relatório, "
                + "solicitando auxílio da Unidade Regional caso necessário;");
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);

        item = new ListItem(
                "Solicitar sempre para a Unidade Regional as condições necessárias para a execução das ações inerentes "
                + "ao serviço de defesa sanitária animal de forma oficial, sem pré-julgar se há recurso disponível para atender a sua "
                + "solicitação. O atendimento ou não será responsabilidade de terceiros;");
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);

        item = new ListItem(
                "Recorrer ao supervisor regional caso necessite de esclarecimentos acerca do conteúdo aqui registrado de forma "
                + "a permitir melhor entendimento;");
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);

        item = new ListItem(
                "Recorrer aos setores da Unidade Central caso precise de orientação técnica que não tenha sido sanada com a equipe "
                + "da Unidade Regional ou em conformidade com direcionamentos do chefe regional.");
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);

        paragraph.add(list);

        addEmptyLine(paragraph, 3);

        DottedLineSeparator dottedline = new DottedLineSeparator();
        dottedline.setOffset(-2);
        dottedline.setGap(2f);

        paragraph.add(dottedline);

        addEmptyLine(paragraph, 1);

        Paragraph responsavel = new Paragraph("Responsável pelo escritório supervisionado/auditado", subFont);
        responsavel.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(responsavel);

        addEmptyLine(paragraph, 3);

        Paragraph chefe = new Paragraph(formulario.getChefeUr(), f);
        chefe.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(chefe);

        paragraph.add(dottedline);

        addEmptyLine(paragraph, 1);

        chefe = new Paragraph("Chefe da Unidade Regional", subFont);
        chefe.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(chefe);

        addEmptyLine(paragraph, 3);

        Paragraph supervisor = new Paragraph(supervisao.getUsuario().getNome(), f);
        supervisor.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(supervisor);

        paragraph.add(dottedline);

        addEmptyLine(paragraph, 1);

        supervisor = new Paragraph("Supervisor/Auditor", subFont);
        supervisor.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(supervisor);

        document.add(paragraph);

    }

    public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}