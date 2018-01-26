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
import stec.controller.FormIdentificacaoEscritorioController.FormIdentificacaoEscritorio;
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

public class RelatorioSupervisao {

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
            RelatorioSupervisao relatorio = new RelatorioSupervisao();

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file + ".pdf"));
            document.open();

            relatorio.Capa(document, supervisao);
            relatorio.IdentificacaoEscritorio(document, supervisao, hashRespostas);
            relatorio.RecursosHumanos(document, hashRespostas);
            relatorio.RecursosFisicos(document, supervisao, hashRespostas);
            relatorio.RecursosFinanceiros(document, hashRespostas);
            relatorio.EstruturaOrganizacional(document, hashRespostas);
            relatorio.AutoridadeGestao(document, hashRespostas);
            relatorio.CapacidadeTecnicaOperacional(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNEFA"))
                relatorio.PNEFA(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNCEBT"))
                relatorio.PNCEBT(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNCRH"))
                relatorio.PNCRH(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNEEB"))
                relatorio.PNEEB(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNSE"))
                relatorio.PNSE(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNSCO"))
                relatorio.PNSCO(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNSS"))
                relatorio.PNSS(document, hashRespostas);

            if (supervisao.getProgramas().toString().contains("PNSA"))
                relatorio.PNSA(document, hashRespostas);

            relatorio.InteracaoPartes(document, hashRespostas);
            relatorio.AcessoMercados(document, hashRespostas);
            relatorio.VulnerabilidadesPotencialidades(document, hashRespostas);
            relatorio.Assinaturas(document, supervisao, hashRespostas);

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

        addEmptyLine(capa, 10);

        Paragraph titulo = new Paragraph("Relatório de Auditoria / Supervisão em Defesa Sanitária Animal\n"
                + "Escritório de Atendimento à Comunidade", catFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        capa.add(titulo);

        addEmptyLine(capa, 2);

        String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                : supervisao.getUlsav().getNome());

        Paragraph dadosSupervisao = new Paragraph("Unidade Regional: " + supervisao.getUr() + "\nEscritório Auditado: "
                + supervisao.getTipoEscritorio() + "\nMunicípio: " + municipio + "\nData da Auditoria:"
                + "\nProgramas: " + supervisao.getProgramas() + "\nAuditor: " + supervisao.getUsuario().getNome());
        dadosSupervisao.setAlignment(Element.ALIGN_CENTER);
        capa.add(dadosSupervisao);

        addEmptyLine(capa, 10);

        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setTotalWidth(150);
        table.setLockedWidth(true);

        PdfPCell c1 = new PdfPCell(new Phrase("Legenda", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("NE - Não existe", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ED - Existe, mas é deficiente", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("EA - Existe e é adequado", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("NA - Não se aplica", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        capa.add(table);

        // Pega a data Atual e transforma em String
        DateFormat df = new SimpleDateFormat("MM/yyyy");
        Paragraph mesAno = new Paragraph(df.format(new Date()));
        mesAno.setAlignment(Element.ALIGN_CENTER);
        capa.add(mesAno);

        document.add(capa);// adiciona o conteudo da capa ao documento

        // Start a new page
        document.newPage();
    }

    /*
	 * Adiciona formulario de identificacao de escritório ao documento OK
     */
    public void IdentificacaoEscritorio(Document document, Supervisao supervisao, HashMap<String, Resposta> resposta)
            throws DocumentException {

        Paragraph identificacaoEscritorio = new Paragraph();

        Anchor titulo = new Anchor("Identificação de Escritório", chaFont);
        titulo.setName("Identificação de Escritório");
        identificacaoEscritorio.add(titulo);

        addEmptyLine(identificacaoEscritorio, 1);

        Gson gson = new Gson();

        FormIdentificacaoEscritorio formulario = gson.fromJson(resposta.get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorio.class);

        String municipio = ((supervisao.getTipoEscritorio().equals("EAC")) ? supervisao.getEac().getNome()
                : supervisao.getUlsav().getNome());
        Paragraph dados = new Paragraph("Unidade Regional: " + supervisao.getUr()
                + "\nEscritório de Atendimento a Comunidade: " + municipio + "\nEndereço: " + formulario.getEndereco()
                + "\nTelefone: " + formulario.getTelefone() + "\nChefe da Unidade Regional: " + formulario.getChefeUr()
                + "\nEmail: " + formulario.getEmailChefe() + "\nMédico(a) veterinário(a) Responsável: "
                + formulario.getVeterinario() + "\nEmail: " + formulario.getEmailVeterinario()
                + "\n\n OBSERVAÇÃO: Na ausência do(a) veterinário(a) responsável pelo escritório, informar campo "
                + "\"Comentários\" o motivo da ausência e o nome e função de quem irá responder à supervisão ", f);
        identificacaoEscritorio.add(dados);

        addEmptyLine(identificacaoEscritorio, 1);

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
        FormRHQuantitativo formulario = gson.fromJson(resposta.get("rh_quantitativo").getResposta(),
                FormRHQuantitativo.class);

        // sub titulo do sub capitulo com a avaliacao
        Paragraph subPara = new Paragraph(
                "Quantitativo, jornada e distribuição     " + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        // -----------------Médico veterinário-----------------------
        subCatPart.add(new Paragraph("Médico Veterinário", smallBold));// adiciona o conteudo do sub capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        PdfPCell c1 = new PdfPCell(new Phrase("AFA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ANS", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ADO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("EMARPH", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SAGRIMA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SEDUC", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Saúde", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prefeitura", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("INAGRO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase("Quantidade Existente", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getVetQEAFA());// afa
        table.addCell(formulario.getVetQEANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getVetQEEMARPH());// emarph
        table.addCell(formulario.getVetQESAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getVetQESaude());// saude
        table.addCell(formulario.getVetQEPrefeitura());// prefeitura
        table.addCell(formulario.getVetQEINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com treinamento", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getVetCTAFA());// afa
        table.addCell(formulario.getVetCTANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getVetCTEMARPH());// emarph
        table.addCell(formulario.getVetCTSAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getVetCTSaude());// saude
        table.addCell(formulario.getVetCTPrefeitura());// prefeitura
        table.addCell(formulario.getVetCTINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com Pós-graduação", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getVetCPAFA());// afa
        table.addCell(formulario.getVetCPANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getVetCPEMARPH());// emarph
        table.addCell(formulario.getVetCPSAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getVetCPSaude());// saude
        table.addCell(formulario.getVetCPPrefeitura());// prefeitura
        table.addCell(formulario.getVetCPINAGRO());// inagro

        subCatPart.add(table);

        // -----------------Engenheiro agronomo-----------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        subCatPart.add(new Paragraph("Engenheiro Agrônomo", smallBold));// adiciona o conteudo do sub capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(9);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        c1 = new PdfPCell(new Phrase("AFA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ANS", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ADO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("EMARPH", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SAGRIMA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SEDUC", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Saúde", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prefeitura", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("INAGRO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase("Quantidade Existente", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getAgroQEAFA());// afa
        table.addCell(formulario.getAgroQEANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getAgroQEEMARPH());// emarph
        table.addCell(formulario.getAgroQESAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getAgroQESaude());// saude
        table.addCell(formulario.getAgroQEPrefeitura());// prefeitura
        table.addCell(formulario.getAgroQEINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com treinamento", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getAgroCTAFA());// afa
        table.addCell(formulario.getAgroCTANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getAgroCTEMARPH());// emarph
        table.addCell(formulario.getAgroCTSAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getAgroCTSaude());// saude
        table.addCell(formulario.getAgroCTPrefeitura());// prefeitura
        table.addCell(formulario.getAgroCTINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com Pós-graduação", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getAgroCPAFA());// afa
        table.addCell(formulario.getAgroCPANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getAgroCPEMARPH());// emarph
        table.addCell(formulario.getAgroCPSAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getAgroCPSaude());// saude
        table.addCell(formulario.getAgroCPPrefeitura());// prefeitura
        table.addCell(formulario.getAgroCPINAGRO());// inagro

        subCatPart.add(table);

        // -----------------Engenheiro florestal-----------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        subCatPart.add(new Paragraph("Engenheiro Florestal", smallBold));// adiciona o conteudo do sub capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(9);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        c1 = new PdfPCell(new Phrase("AFA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ANS", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ADO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("EMARPH", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SAGRIMA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SEDUC", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Saúde", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prefeitura", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("INAGRO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase("Quantidade Existente", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getFlorQEAFA());// afa
        table.addCell(formulario.getFlorQEANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getFlorQEEMARPH());// emarph
        table.addCell(formulario.getFlorQESAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getFlorQESaude());// saude
        table.addCell(formulario.getFlorQEPrefeitura());// prefeitura
        table.addCell(formulario.getFlorQEINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com treinamento", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getFlorCTAFA());// afa
        table.addCell(formulario.getFlorCTANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getFlorCTEMARPH());// emarph
        table.addCell(formulario.getFlorCTSAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getFlorCTSaude());// saude
        table.addCell(formulario.getFlorCTPrefeitura());// prefeitura
        table.addCell(formulario.getFlorCTINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com Pós-graduação", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getFlorCPAFA());// afa
        table.addCell(formulario.getFlorCPANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getFlorCPEMARPH());// emarph
        table.addCell(formulario.getFlorCPSAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getFlorCPSaude());// saude
        table.addCell(formulario.getFlorCPPrefeitura());// prefeitura
        table.addCell(formulario.getFlorCPINAGRO());// inagro

        subCatPart.add(table);

        // -----------------Zootecnista-----------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        subCatPart.add(new Paragraph("Zootecnista", smallBold));// adiciona o conteudo do sub capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(9);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        c1 = new PdfPCell(new Phrase("AFA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ANS", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ADO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("EMARPH", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SAGRIMA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SEDUC", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Saúde", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prefeitura", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("INAGRO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase("Quantidade Existente", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getZooQEAFA());// afa
        table.addCell(formulario.getZooQEANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getZooQEEMARPH());// emarph
        table.addCell(formulario.getZooQESAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getZooQESaude());// saude
        table.addCell(formulario.getZooQEPrefeitura());// prefeitura
        table.addCell(formulario.getZooQEINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com treinamento", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getZooCTAFA());// afa
        table.addCell(formulario.getZooCTANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getZooCTEMARPH());// emarph
        table.addCell(formulario.getZooCTSAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getZooCTSaude());// saude
        table.addCell(formulario.getZooCTPrefeitura());// prefeitura
        table.addCell(formulario.getZooCTINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com Pós-graduação", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getZooCPAFA());// afa
        table.addCell(formulario.getZooCPANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getZooCPEMARPH());// emarph
        table.addCell(formulario.getZooCPSAGRIMA());// sagrima
        table.addCell("");// seduc
        table.addCell(formulario.getZooCPSaude());// saude
        table.addCell(formulario.getZooCPPrefeitura());// prefeitura
        table.addCell(formulario.getZooCPINAGRO());// inagro

        subCatPart.add(table);

        Paragraph p6 = new Paragraph();
        addEmptyLine(p6, 6);
        subCatPart.add(p6);// adiciona linha vazia ao paragrafo da secao

        // -----------------Tecnico em agropecuaria-----------------------
        subCatPart.add(new Paragraph("Técnico em agropecuária", smallBold));// adiciona o conteudo do sub capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(9);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        c1 = new PdfPCell(new Phrase("AFA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ANS", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ADO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("EMARPH", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SAGRIMA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SEDUC", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Saúde", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prefeitura", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("INAGRO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase("Quantidade Existente", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getTecAgroQEAFA());// afa
        table.addCell(formulario.getTecAgroQEANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getTecAgroQEEMARPH());// emarph
        table.addCell(formulario.getTecAgroQESAGRIMA());// sagrima
        table.addCell(formulario.getTecAgroQESEDUC());// seduc
        table.addCell(formulario.getTecAgroQESaude());// saude
        table.addCell(formulario.getTecAgroQEPrefeitura());// prefeitura
        table.addCell(formulario.getTecAgroQEINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com treinamento", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getTecAgroCTAFA());// afa
        table.addCell(formulario.getTecAgroCTANS());// ans
        table.addCell("");// ado
        table.addCell(formulario.getTecAgroCTEMARPH());// emarph
        table.addCell(formulario.getTecAgroCTSAGRIMA());// sagrima
        table.addCell(formulario.getTecAgroCTSEDUC());// seduc
        table.addCell(formulario.getTecAgroCTSaude());// saude
        table.addCell(formulario.getTecAgroCTPrefeitura());// prefeitura
        table.addCell(formulario.getTecAgroCTINAGRO());// inagro

        subCatPart.add(table);

        // -----------------Auxiliar administrativo-----------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        subCatPart.add(new Paragraph("Auxiliar Administrativo", smallBold));// adiciona o conteudo do sub capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(9);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        c1 = new PdfPCell(new Phrase("AFA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ANS", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ADO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("EMARPH", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SAGRIMA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SEDUC", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Saúde", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prefeitura", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("INAGRO", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase("Quantidade Existente", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getAuxAdmQEAFA());// afa
        table.addCell(formulario.getAuxAdmQEANS());// ans
        table.addCell(formulario.getAuxAdmQEADO());// ado
        table.addCell(formulario.getAuxAdmQEEMARPH());// emarph
        table.addCell(formulario.getAuxAdmQESAGRIMA());// sagrima
        table.addCell(formulario.getAuxAdmQESEDUC());// seduc
        table.addCell(formulario.getAuxAdmQESaude());// saude
        table.addCell(formulario.getAuxAdmQEPrefeitura());// prefeitura
        table.addCell(formulario.getAuxAdmQEINAGRO());// inagro

        c1 = new PdfPCell(new Phrase("Com treinamento", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        table.addCell(formulario.getAuxAdmCTAFA());// afa
        table.addCell(formulario.getAuxAdmCTANS());// ans
        table.addCell(formulario.getAuxAdmCTADO());// ado
        table.addCell(formulario.getAuxAdmCTEMARPH());// emarph
        table.addCell(formulario.getAuxAdmCTSAGRIMA());// sagrima
        table.addCell(formulario.getAuxAdmCTSEDUC());// seduc
        table.addCell(formulario.getAuxAdmCTSaude());// saude
        table.addCell(formulario.getAuxAdmCTPrefeitura());// prefeitura
        table.addCell(formulario.getAuxAdmCTINAGRO());// inagro

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Considerando a quantidade: atende à demanda? " + formulario.getAtendeDemanda()
                + "\nHorário de funcionamento: " + "Manhã: " + formulario.getFuncionamentoManha() + "   Tarde: "
                + formulario.getFuncionamentoTarde(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormRHEstabilidade formulario2 = gson.fromJson(resposta.get("rh_estabilidade").getResposta(),
                FormRHEstabilidade.class);

        // adiciona o sub titulo da secao do capitulo
        subCatPart = catPart
                .addSection(new Paragraph("Estabilidade das estruturas e sustentabilidade das políticas sanitárias     "
                        + "Avaliação: " + formulario2.getAvaliacao(), subFont));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Tempo no serviço de defesa sanitária animal (Méd Vet): "
                + formulario2.getTempoDefesaSanitaria() + "\nTempo de serviço no escritório (Méd Vet): "
                + formulario2.getTempoEscritorio() + "\n Existe evidências de estabilidade do quadro funcional? "
                + formulario2.getEvidenciaEstabilidade(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormRHCapacitacao formulario3 = gson.fromJson(resposta.get("rh_capacitacao").getResposta(),
                FormRHCapacitacao.class);

        subCatPart = catPart.addSection(new Paragraph(
                "Capacitação técnica e formação continuada     " + "Avaliação: " + formulario3.getAvaliacao(),
                subFont));// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao
        subCatPart.add(new Paragraph("Participação em treinamento", smallBold));// adiciona o conteudo do sub capitulo
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        c1 = new PdfPCell(new Phrase("Nome", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cargo / Função", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Treinamento", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ano", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (Capacitacao capacitacao : formulario3.getCapacitacoes()) {
            table.addCell(capacitacao.getNome());// nome
            table.addCell(capacitacao.getCargo());// cargo
            table.addCell(capacitacao.getTreinamento());// treinamento
            table.addCell(capacitacao.getAno());// ano
        }

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph(
                "Aplicação prática das capacitações na rotina de trabalho: " + formulario3.getAplicacaoPratica(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormRHCompetencias formulario4 = gson.fromJson(resposta.get("rh_competencias").getResposta(),
                FormRHCompetencias.class);

        subCatPart = catPart.addSection(new Paragraph(
                "Competências e independência técnica     " + "Avaliação: " + formulario4.getAvaliacao(), subFont));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Competências e independência técnica: " + formulario4.getCompetencias(), f));

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

    public void RecursosFisicos(Document document, Supervisao supervisao, HashMap<String, Resposta> resposta)
            throws DocumentException {
        Anchor anchor = new Anchor("Recursos Físicos", chaFont);
        anchor.setName("Recursos Físicos");

        Chapter catPart = new Chapter(new Paragraph(anchor), 2);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormRFInstalacoes formulario = gson.fromJson(resposta.get("rf_instalacoes").getResposta(),
                FormRFInstalacoes.class);
        Paragraph subPara = new Paragraph("Instalações     " + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Natureza do Prédio", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Manutenção", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Instalações", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Condições de atendimento ao público", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Apresentação externa", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Organização interna", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Limpeza", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Estrutura", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Banheiro", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Sala de atendimento", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Sala p/ veterinário", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getNaturezaPredio());
        table.addCell(formulario.getLimpeza());
        table.addCell(formulario.getConservacao());
        table.addCell(formulario.getBanheiro());
        table.addCell(formulario.getSalaAtendimento());
        table.addCell(formulario.getSalaVeterinario());
        table.addCell(formulario.getCondicoesAtendimento());
        table.addCell(formulario.getApresentacaoExterna());
        table.addCell(formulario.getOrganizacaoInterna());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Mobiliário atende a demanda? " + formulario.getAtendeDemanda()
                + "\nHá uma relação do mobiliário existente por EAC? " + formulario.getRelacaoMobiliario(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormRFTransportes formulario2 = gson.fromJson(resposta.get("rf_transportes").getResposta(),
                FormRFTransportes.class);
        subPara = new Paragraph("Transportes     " + "Avaliação: " + formulario2.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(10);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        c1 = new PdfPCell(new Phrase("Tipo", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantidade", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Conservação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ano", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quota", f));
        c1.setColspan(2);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Manutenção", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Km", f));
        c1.setRowspan(2);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cartão", f));
        c1.setRowspan(2);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Valor (R$)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Atende demanda?", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Preventiva", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Emergencial", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (Transporte transporte : formulario2.getTransportes()) {
            table.addCell(transporte.getTipo());// afa
            table.addCell(transporte.getQuantidade());// ans
            table.addCell(transporte.getConservacao());// ado
            table.addCell(transporte.getAno());// emarph
            table.addCell(transporte.getValor());// afa
            table.addCell(transporte.getAtendeDemanda());// ans
            table.addCell(transporte.getManutencaoPreventiva());// ado
            table.addCell(transporte.getManutencaoEmergencial());// emarph
            table.addCell(transporte.getRegistroKM());// afa
            table.addCell(transporte.getCartaoAbastecimento());// ans
        }

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Condutores para o veículo: " + formulario2.getCondutoresVeiculo()
                + "\nRelação dos veículos e a lotação (ULSAV e EAC): " + formulario2.getRelacaoVeiculos()
                + "\nFrota adequada? " + formulario2.getFrotaAdequada(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormRFEquipamentos formulario3 = gson.fromJson(resposta.get("rf_equipamentos").getResposta(),
                FormRFEquipamentos.class);
        subPara = new Paragraph(
                "Equipamentos e acesso à comunicação     " + "Avaliação: " + formulario3.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(8);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Município", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Computadores", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Acesso a internet", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Linha Telefônica", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de computadores com acesso à internet?", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Demais Equipamentos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantidade", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Suficiente", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Condições de uso?", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (Municipio municipio : formulario3.getMunicipios()) {
            table.addCell(municipio.getMunicipio());// afa
            table.addCell(municipio.getQuantidadePC());// ans
            table.addCell(municipio.getSuficiente());// ado
            table.addCell(municipio.getCondicoes());// emarph
            table.addCell(municipio.getInternet());// afa
            table.addCell(municipio.getTelefone());// ans
            table.addCell(municipio.getnComputadores());// ado
            table.addCell(municipio.getDemaisEquipamentos());// ado
        }

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Fluxo de informações entre UC-UR-Ulsav-EAC e vice versa: " + formulario3.getFluxo(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormRFSistemasInformacao formulario4 = gson.fromJson(resposta.get("rf_sistemas").getResposta(),
                FormRFSistemasInformacao.class);
        subPara = new Paragraph("Sistemas de informação     " + "Avaliação: " + formulario4.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Sistemas de Informação: " + formulario4.getSistemas(), f));

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

    public void RecursosFinanceiros(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Recursos Financeiros", chaFont);
        anchor.setName("Recursos Financeiros");

        Chapter catPart = new Chapter(new Paragraph(anchor), 3);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormRFICusteio formulario = gson.fromJson(resposta.get("rfi_custeio").getResposta(), FormRFICusteio.class);
        Paragraph subPara = new Paragraph("Recurso para Custeio     " + "Avaliação: " + formulario.getAvaliacao(),
                subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Procedimentos para solicitação de diárias", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Planejamento de diárias X Comprovação da execução da ação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Disponibilidade de recurso financeiro", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("FAI", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Relatório de Km", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Relatório de viagem", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getSolicitacaoDiaria());
        table.addCell(formulario.getFAI());
        table.addCell(formulario.getRelatorioKM());
        table.addCell(formulario.getRelatorioViagem());
        table.addCell(formulario.getDisponibilidadeFinanceiro());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormRFIArrecadacao formulario2 = gson.fromJson(resposta.get("rfi_arrecadacao").getResposta(),
                FormRFIArrecadacao.class);
        subPara = new Paragraph("Arrecadação     " + "Avaliação: " + formulario2.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph p = new Paragraph();
        p.add(new Chunk("Controle mensal de arrecadação: " + formulario2.getControleMensal(), f));
        p.setTabSettings(new TabSettings(80f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Autos de infração: " + formulario2.getAutosInfracao(), f));
        subCatPart.add(p);

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

    public void EstruturaOrganizacional(Document document, HashMap<String, Resposta> resposta)
            throws DocumentException {
        Anchor anchor = new Anchor("Estrutura Organizacional", chaFont);
        anchor.setName("Estrutura Organizacional");

        Chapter catPart = new Chapter(new Paragraph(anchor), 4);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormEOEstrutura formulario = gson.fromJson(resposta.get("eo_estrutura").getResposta(), FormEOEstrutura.class);
        Paragraph subPara = new Paragraph("Estrutura organizacional e capacidade de coordenação interna     "
                + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Área de ocupação pecuária do município (km2): " + formulario.getAreaMunicipio()
                + "\nÁrea de ocupação pecuária da ULSAV (km2): " + formulario.getAreaULSAV(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Nº de municípios da jurisdição", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de EACs", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de municípios atendidos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de postos fixos de fiscalização", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getnMunicipioJurisdicao());
        table.addCell(formulario.getnEAC());
        table.addCell(formulario.getnMunicipiosAtendidos());
        table.addCell(formulario.getnPostosFixos());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(3);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        c1 = new PdfPCell(new Phrase("Nomes dos municípios que compõem a ULSAV", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ULSAV", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("EAC", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Municípios atendidos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(formulario.getUlsav(), f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        table.addCell(formulario.getEac1() + "\n" + formulario.getEac2() + "\n" + formulario.getEac3() + "\n"
                + formulario.getEac4() + "\n" + formulario.getEac5());
        table.addCell(formulario.getmAtendido1() + "\n" + formulario.getmAtendido2() + "\n" + formulario.getmAtendido3()
                + "\n" + formulario.getmAtendido4() + "\n" + formulario.getmAtendido5());

        subCatPart.add(table);

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        document.add(catPart);

        document.newPage();
    }

    public void AutoridadeGestao(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Autoridade e Gestão de Qualidade", chaFont);
        anchor.setName("Autoridade e Gestão de Qualidade");

        Chapter catPart = new Chapter(new Paragraph(anchor), 5);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormAGBaseLegal formulario = gson.fromJson(resposta.get("ag_base_legal").getResposta(), FormAGBaseLegal.class);
        Paragraph subPara = new Paragraph(
                "Base legal e aplicação da legislação, manuais e POPs    " + "Avaliação: " + formulario.getAvaliacao(),
                subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph p = new Paragraph();
        p.add(new Chunk("Legislação: " + formulario.getLegislacao(), f));
        p.setTabSettings(new TabSettings(80f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Manuais: " + formulario.getManuais(), f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("POPs: " + formulario.getPOP(), f));
        subCatPart.add(p);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -----------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormAGOrganizacao formulario2 = gson.fromJson(resposta.get("ag_organizacao").getResposta(),
                FormAGOrganizacao.class);
        subPara = new Paragraph(
                "Organização dos processos e unidades    " + "Avaliação: " + formulario2.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        p = new Paragraph();
        p.add(new Chunk("Arquivo: " + formulario2.getArquivo(), f));
        p.setTabSettings(new TabSettings(80f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Mural Técnico: " + formulario2.getMural(), f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Fluxo de informações: " + formulario2.getFluxo(), f));
        subCatPart.add(p);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormAGSupervisao formulario3 = gson.fromJson(resposta.get("ag_supervisao").getResposta(),
                FormAGSupervisao.class);
        subPara = new Paragraph("Supervisão e controle interno    " + "Avaliação: " + formulario3.getAvaliacao(),
                subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        p = new Paragraph();
        p.add(new Chunk("Visita técnica no PFFA: " + formulario3.getPFFA(), f));
        p.setTabSettings(new TabSettings(80f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Visita técnica no(s) EAC(s): " + formulario3.getEAC(), f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Supervisões recebidas: " + formulario3.getSupervisao(), f));
        subCatPart.add(p);

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

    public void CapacidadeTecnicaOperacional(Document document, HashMap<String, Resposta> resposta)
            throws DocumentException {
        Anchor anchor = new Anchor("Capacidade Técnica e Operacional", chaFont);
        anchor.setName("Capacidade Técnica e Operacional");

        Chapter catPart = new Chapter(new Paragraph(anchor), 5);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormCTOControleCadastro formulario = gson.fromJson(resposta.get("cto_controle_cadastro").getResposta(),
                FormCTOControleCadastro.class);
        Paragraph subPara = new Paragraph("Controle de cadastro de produtores, propriedades e animais    "
                + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph(
                "N° de propriedades com bovinos e bubalinos cadastradas: " + formulario.getnPropriedades()
                + "\nN° de produtores com bovinos e bubalinos cadastrados : " + formulario.getnProdutores(),
                f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("População Animal Cadastrada", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(9);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Bovina", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Bubalina", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Suídea", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ovina", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Caprina", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Equina", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Muar", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Asinina", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Avícola", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getBovina());
        table.addCell(formulario.getBubalina());
        table.addCell(formulario.getSuidea());
        table.addCell(formulario.getOvina());
        table.addCell(formulario.getCaprina());
        table.addCell(formulario.getEquina());
        table.addCell(formulario.getMuar());
        table.addCell(formulario.getAsinina());
        table.addCell(formulario.getAvicola());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph(
                "Informações de produtores, propriedades e efetivos de todos os municípios da jurisdição: "
                + formulario.getInfoProdutoresPropriedades()
                + "\nDocumentação referente ao cadastramento de propriedade, produtores rurais e explorações pecuárias: "
                + formulario.getDocumentacao() + "\nProcedimentos para abertura e encerramento de cadastros: "
                + formulario.getProcedimentos()
                + "\nRegistro de entrada, saída e saldo nas fichas de movimentação: "
                + formulario.getRegistro(),
                f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -----------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormCTOPlanejamento formulario2 = gson.fromJson(resposta.get("cto_planejamento").getResposta(),
                FormCTOPlanejamento.class);
        subPara = new Paragraph(
                "Planejamento, execução de atividades e registro   " + "Avaliação: " + formulario2.getAvaliacao(),
                subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph p = new Paragraph();
        p.add(new Chunk("Planejamento Técnico Mensal: " + formulario2.getPlanejamento(), f));
        p.setTabSettings(new TabSettings(80f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Registro das ações planejadas: " + formulario2.getRegistro(), f));
        p.add(new Chunk("\nCumprimento das metas propostas pelos programas sanitários: " + formulario2.getCumprimento(),
                f));
        subCatPart.add(p);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormCTOControle formulario3 = gson.fromJson(resposta.get("cto_controle").getResposta(), FormCTOControle.class);
        subPara = new Paragraph(
                "Controle de divisas e trânsito interno    " + "Avaliação: " + formulario3.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);
        c1 = new PdfPCell(new Phrase("Nº de blitz realizadas durante o ano (até o momento da supervisão)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Blitz realizadas durante o mês", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(5);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantidade", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de veículos inspecionados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de animais inspecionados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Com animais", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Com produtos e subprodutos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Vazios ou com carga que não seja de interesse da Defesa Animal", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario3.getnBlitzAno());
        table.addCell(formulario3.getQtdBlitzMes());
        table.addCell(formulario3.getQtdBlitzMesAnimais());
        table.addCell(formulario3.getQtdBlitzMesProdutos());
        table.addCell(formulario3.getQtdBlitzMesVazio());
        table.addCell(formulario3.getQtdBlitzMesInspecionados());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        c1 = new PdfPCell(new Phrase("Pontos Estratégicos para realização de blitz", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Realização mensal de blitz", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Registro da blitz", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Apreensões", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Material para realização de blitz", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Registro sobre operações volantes", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario3.getPontosEstrategicos());
        table.addCell(formulario3.getRealizacaoMensal());
        table.addCell(formulario3.getRegistroBlitz());
        table.addCell(formulario3.getApreensoes());
        table.addCell(formulario3.getMaterialBlitz());
        table.addCell(formulario3.getRegistro());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormCTOControleTransitoAnimais formulario4 = gson.fromJson(resposta.get("cto_transito_animais").getResposta(),
                FormCTOControleTransitoAnimais.class);
        subPara = new Paragraph("Controle de trânsito de animais e produtos de origem animal    " + "Avaliação: "
                + formulario4.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(5);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Nº de emitentes de GTA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Credenciamento e descredenciamento dos emitentes", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Manuais para preenchimento", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Emissão de GTA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Exigências zoossanitárias", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario4.getnGTAEmitente());
        table.addCell(formulario4.getCredenciamento());
        table.addCell(formulario4.getManuais());
        table.addCell(formulario4.getEmissaoGTA());
        table.addCell(formulario4.getExigencias());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        c1 = new PdfPCell(new Phrase("Relatórios de emissão de GTA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Gráficos da movimentação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Organização das GTAs", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Guias de trânsito para subprodutos de origem animal", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario4.getRelatorios());
        table.addCell(formulario4.getGraficos());
        table.addCell(formulario4.getOrganizacao());
        table.addCell(formulario4.getGuias());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario4.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario4.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario4.getPrazo() + "\nRecomendações UR: \n" + formulario4.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario4.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormCTOControleEventosAglomeracao formulario5 = gson
                .fromJson(resposta.get("cto_controle_eventos").getResposta(), FormCTOControleEventosAglomeracao.class);
        subPara = new Paragraph(
                "Controle de eventos de aglomeração de animais    " + "Avaliação: " + formulario5.getAvaliacao(),
                subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph dados = new Paragraph(
                "* Considerar os eventos que aconteceram durante o ano vigente até o dia da auditoria/supervisão. "
                + "Caso a quantidade de eventos realizados seja diferente da quantidade de eventos fiscalizados, "
                + "registrar no campo \"Comentários\" o motivo da diferença.",
                f);
        dados.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(dados);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(5);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Nº de recintos de aglomeração cadastrados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de eventos realizados*", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de eventos fiscalizados*", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de animais participantes", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de animais inspecionados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario5.getnRecintos());
        table.addCell(formulario5.getnEventosRealizados());
        table.addCell(formulario5.getnEventosFiscalizados());
        table.addCell(formulario5.getnAnimaisParticipantes());
        table.addCell(formulario5.getnAnimaisInspecionados());

        subCatPart.add(table);

        Paragraph p2 = new Paragraph();
        addEmptyLine(p2, 2);
        subCatPart.add(p2);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(5);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        c1 = new PdfPCell(new Phrase("Documentação de cadastramento dos recintos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Documentação para realização do evento", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Registro de eventos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Inspeção clínica de animais", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Emissão de GTAs de saída", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario5.getDocCadastramento());
        table.addCell(formulario5.getDocRealizacao());
        table.addCell(formulario5.getRegistro());
        table.addCell(formulario5.getInspecao());
        table.addCell(formulario5.getGTASaida());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario5.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario5.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario5.getPrazo() + "\nRecomendações UR: \n" + formulario5.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario5.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormCTOFiscalizacao formulario6 = gson.fromJson(resposta.get("cto_fiscalizacao").getResposta(),
                FormCTOFiscalizacao.class);
        subPara = new Paragraph(
                "Fiscalização em revendas veterinárias    " + "Avaliação: " + formulario6.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(7);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Nº de Revendas Cadastradas", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cadastramento de Revendas Veterinárias", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de revendas fiscalizadas durante o mês", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Frequência de fiscalizações em revendas", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos de Fiscalização", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Que comercializam vacinas", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Diárias", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Semanais", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario6.getnRevendasTotal());
        table.addCell(formulario6.getnRevendasVacinas());
        table.addCell(formulario6.getCadastramento());
        table.addCell(formulario6.getnRevendasFiscalizadas());
        table.addCell(formulario6.getFrequenciaFiscalizacao());
        table.addCell(formulario6.getDiarias());
        table.addCell(formulario6.getSemanais());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        p = new Paragraph();
        p.add(new Chunk("Supervisão na revenda veterinária: " + formulario6.getSupervisaoRevenda(), f));
        p.setTabSettings(new TabSettings(80f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Apreensão de vacinas: " + formulario6.getApreensaoVacinas(), f));
        subCatPart.add(p);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario6.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario6.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario6.getPrazo() + "\nRecomendações UR: \n" + formulario6.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario6.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormCTOCapacidadeDeteccaoPrecoce formulario7 = gson.fromJson(resposta.get("cto_deteccao_precoce").getResposta(),
                FormCTOCapacidadeDeteccaoPrecoce.class);
        subPara = new Paragraph("Capacidade para detecção precoce e notificação imediata de doenças    " + "Avaliação: "
                + formulario7.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(8);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Nº de notificações de enfermidades recebidas / Tipo de notificação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Enfermidade envolvida", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fluxo de informações", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Participação da comunidade", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fontes de informação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Registro das comunicações e atendimentos de ocorrência de enfermidades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Vigilância (Vg)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Proprietário (Pp)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Terceiros (Te)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario7.getVigilancia());
        table.addCell(formulario7.getProprietario());
        table.addCell(formulario7.getTerceiros());
        table.addCell(formulario7.getEnfermidade());
        table.addCell(formulario7.getFluxo());
        table.addCell(formulario7.getParticipacao());
        table.addCell(formulario7.getFonte());
        table.addCell(formulario7.getRegistro());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario7.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario7.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario7.getPrazo() + "\nRecomendações UR: \n" + formulario7.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario7.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormCTOAtendimentoSuspeita formulario8 = gson.fromJson(resposta.get("cto_atendimento_suspeita").getResposta(),
                FormCTOAtendimentoSuspeita.class);
        subPara = new Paragraph("Capacidade para atendimento a suspeitas e atuação em emergências    " + "Avaliação: "
                + formulario8.getAvaliacao(), subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Material para atendimento", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Acondicionamento e remessa de material para a Unidade Central ", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos no atendimento às notificações", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cadastro Agroprodutivo", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Equipamento de Proteção Individual (EPI)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Kit de atendimento à enfermidades vesiculares", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Kit de atendimento para colheita de material biológico (doenças nervosas)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario8.getEPI());
        table.addCell(formulario8.getKitVesiculares());
        table.addCell(formulario8.getKitMaterialBiologico());
        table.addCell(formulario8.getAcondicionamento());
        table.addCell(formulario8.getProcedimentos());
        table.addCell(formulario8.getAgroprodutivo());

        subCatPart.add(table);

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

    public void PNEFA(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 6);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormPNEFAFiscalizacoes formulario = gson.fromJson(resposta.get("pnefa_fiscalizacoes").getResposta(),
                FormPNEFAFiscalizacoes.class);
        Paragraph subPara = new Paragraph(
                "Fiscalização de Vacinações     " + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Procedimentos relacionados à vacinação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos de comprovação de vacinação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Relatórios de campanha", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos pós-etapa", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getVacinacao());
        table.addCell(formulario.getComprovacao());
        table.addCell(formulario.getRelatorios());
        table.addCell(formulario.getPosetapa());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormPNEFAVigilancia formulario2 = gson.fromJson(resposta.get("pnefa_vigilancia").getResposta(),
                FormPNEFAVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Direcionamento das ações de vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Propriedades e pontos de risco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Realização e registro da vigilância em propriedades e pontos de risco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Indicadores da caracterização epidemiológica", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario2.getDirecionamento());
        table.addCell(formulario2.getPropriedadesRisco());
        table.addCell(formulario2.getRegistroVigilancia());
        table.addCell(formulario2.getIndicadores());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(5);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Propriedades sob monitoramento para saneamento de foco", f));
        c1.setColspan(5);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Propriedade Foco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Propriedade Vínculo", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Doença em curso", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos adotados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos adotados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario2.getnPropriedadeFoco());
        table.addCell(formulario2.getDoencaCurso());
        table.addCell(formulario2.getProcedimentosAdotadosFoco());
        table.addCell(formulario2.getnPropriedadeVinculo());
        table.addCell(formulario2.getProcedimentosAdotadosVinculo());

        subCatPart.add(table);

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

    public void PNCEBT(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor(
                "Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 7);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormPNCEBTFiscalizacoes formulario = gson.fromJson(resposta.get("pncebt_fiscalizacoes").getResposta(),
                FormPNCEBTFiscalizacoes.class);
        Paragraph subPara = new Paragraph(
                "Fiscalização de Vacinações     " + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        PdfPCell c1 = new PdfPCell(new Phrase("Procedimentos de comprovação de vacinação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Relatórios de cobertura vacinal", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos com inadimplentes", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getProcedimentosComprovacao());
        table.addCell(formulario.getRelatorios());
        table.addCell(formulario.getProcedimentosInadimplentes());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormPNCEBTVigilancia formulario2 = gson.fromJson(resposta.get("pncebt_vigilancia").getResposta(),
                FormPNCEBTVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Direcionamento das ações de vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fiscalização em propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fiscalização em laboratório", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Propriedades sob monitoramento para saneamento de foco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Doença em curso", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos adotados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario2.getDirecionamento());
        table.addCell(formulario2.getFiscalizacaoPropriedade());
        table.addCell(formulario2.getFiscalizacaoLaboratorio());
        table.addCell(formulario2.getnPropriedadesFoco());
        table.addCell(formulario2.getDoencaCurso());
        table.addCell(formulario2.getProcedimentoAdotadoFoco());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormPNCEBTControles formulario3 = gson.fromJson(resposta.get("pncebt_controles").getResposta(),
                FormPNCEBTControles.class);
        subPara = new Paragraph("Controles do Programa", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph(
                "Os itens a seguir referem-se aos controle sobre os médicos veterinários autônomos cadastrados e/ou habilitados junto ao PNCEBT através da Ulsav que está sendo supervisionada",
                f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Portarias Med Vet cadastrados e/ou habilitados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Pontualidade Med Vet cadastrados e/ou habilitados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Relatórios do PNCEBT/MA", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(
                "Comercialização de blocos de Receituário e atestados de vacinação contra brucelose (UR)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario3.getPortaria());
        table.addCell(formulario3.getPontualidade());
        table.addCell(formulario3.getRelatorios());
        table.addCell(formulario3.getComercializacao());

        subCatPart.add(table);

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

    public void PNCRH(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)",
                chaFont);
        anchor.setName("Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 8);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormPNCRHFiscalizacoes formulario = gson.fromJson(resposta.get("pncrh_fiscalizacoes").getResposta(),
                FormPNCRHFiscalizacoes.class);
        Paragraph subPara = new Paragraph(
                "Fiscalização de Vacinações     " + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Procedimentos de comprovação de vacinação", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Relatórios de cobertura vacinal", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getProcedimentosComprovacao());
        table.addCell(formulario.getRelatoriosCobertura());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormPNCRHVigilancia formulario2 = gson.fromJson(resposta.get("pncrh_vigilancia").getResposta(),
                FormPNCRHVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Direcionamento das ações de vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cadastro e monitoramento de abrigos de morcegos hematófagos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Captura de morcegos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Propriedades sob monitoramento para saneamento de foco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Doença em curso", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos adotados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario2.getDirecionamento());
        table.addCell(formulario2.getCadastroMorcegos());
        table.addCell(formulario2.getCapturaMorcegos());
        table.addCell(formulario2.getnPropriedades());
        table.addCell(formulario2.getDoencaCurso());
        table.addCell(formulario2.getProcedimentosAdotados());

        subCatPart.add(table);

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

    public void PNEEB(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor(
                "Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)",
                chaFont);
        anchor.setName(
                "Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 8);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormPNEEBVigilancia formulario = gson.fromJson(resposta.get("pneeb_vigilancia").getResposta(),
                FormPNEEBVigilancia.class);
        Paragraph subPara = new Paragraph("Vigilância Epidemiológica     " + "Avaliação: " + formulario.getAvaliacao(),
                subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        PdfPCell c1 = new PdfPCell(new Phrase("Direcionamento das ações de vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Identificação de áreas segundo os fatores de risco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Inspeções em propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fiscalização em matadouro", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fiscalização em graxarias", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getDirecionamento());
        table.addCell(formulario.getIdentificacao());
        table.addCell(formulario.getInspecoes());
        table.addCell(formulario.getFiscalizacaoMatadouro());
        table.addCell(formulario.getFiscalizacaograxaria());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(3);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Propriedades sob monitoramento para saneamento de foco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Doença em curso", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos adotados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getnPropriedades());
        table.addCell(formulario.getDoencaCurso());
        table.addCell(formulario.getProcedimentosAdotados());

        subCatPart.add(table);

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

    public void PNSE(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 9);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormPNSECadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnse_cadastro_estabelecimentos").getResposta(), FormPNSECadastroEstabelecimento.class);
        Paragraph subPara = new Paragraph(
                "Cadastro de Estabelecimentos     " + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        PdfPCell c1 = new PdfPCell(new Phrase("Estabelecimentos de criação cadastrados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Atualização cadastral", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getEstabelecimentoCadastrado());
        table.addCell(formulario.getAtualizacaoCadastral());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormPNSEVigilancia formulario2 = gson.fromJson(resposta.get("pnse_vigilancia").getResposta(),
                FormPNSEVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Direcionamento das ações de vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Vigilância em propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Doenças de notificação obrigatória", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Propriedades sob monitoramento para saneamento de foco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de propriedades", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Doença em curso", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos adotados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario2.getDirecionamento());
        table.addCell(formulario2.getVigilancia());
        table.addCell(formulario2.getDoencaNotificacao());
        table.addCell(formulario2.getnPropriedade());
        table.addCell(formulario2.getDoencaCurso());
        table.addCell(formulario2.getProcedimentoAdotado());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormPNSEControles formulario3 = gson.fromJson(resposta.get("pnse_controles").getResposta(),
                FormPNSEControles.class);
        subPara = new Paragraph("Controles do Programa", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Quantidade de processos  no escritório", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Relação dos Med Vet cadastrados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Relatórios de colheita Med Vet cadastrados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Antigos", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Recentes", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario3.getQuantidadeAntigos());
        table.addCell(formulario3.getQuantidadeRecentes());
        table.addCell(formulario3.getRelacaoMedicoCadastrados());
        table.addCell(formulario3.getRelatorioColheita());

        subCatPart.add(table);

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

    public void PNSCO(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 8);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormPNSCOCadastros formulario = gson.fromJson(resposta.get("pnsco_cadastro_estabelecimentos").getResposta(),
                FormPNSCOCadastros.class);
        Paragraph subPara = new Paragraph(
                "Cadastro de Estabelecimentos    " + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Estabelecimentos de criação cadastrados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Atualização cadastral", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getEstabelecimentoCadastrado());
        table.addCell(formulario.getAtualizacaoCadastral());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormPNSCOVigilancia formulario2 = gson.fromJson(resposta.get("pnsco_vigilancia").getResposta(),
                FormPNSCOVigilancia.class);
        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(2);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Doenças de notificação obrigatória", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ações de vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario2.getDoencaNotificacao());
        table.addCell(formulario2.getAcoesVigilancia());

        subCatPart.add(table);

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

    public void PNSS(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 10);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormPNSSCadastroEstabelecimento formulario = gson.fromJson(
                resposta.get("pnss_cadastro_estabelecimentos").getResposta(), FormPNSSCadastroEstabelecimento.class);
        FormPNSSVigilancia formulario2 = gson.fromJson(resposta.get("pnss_vigilancia").getResposta(),
                FormPNSSVigilancia.class);

        Paragraph subPara = new Paragraph(
                "Cadastro de Estabelecimentos    " + "Avaliação: " + formulario2.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Estabelecimentos de criação cadastrados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de Granjas", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de Criatórios (subsistência)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Atualização cadastral", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Suídeos asselvajados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getEstabelecimentoCadastrados());
        table.addCell(formulario.getnGranjas());
        table.addCell(formulario.getnCriatorios());
        table.addCell(formulario.getAtualizacaoCadastral());
        table.addCell(formulario.getSuideos());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Direcionamento das ações de vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Propriedades de risco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de Granjas sob vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nº de Criatórios (subsistência) sob vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("existentes", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("visitadas", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("existentes", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("visitadas", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario2.getDirecionamento());
        table.addCell(formulario2.getPropriedades());
        table.addCell(formulario2.getnGranjaExistente());
        table.addCell(formulario2.getnGranjaVisitadas());
        table.addCell(formulario2.getnCriatoriosExistentes());
        table.addCell(formulario2.getnCriatoriosVisitadas());

        subCatPart.add(table);

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

    public void PNSA(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)", chaFont);
        anchor.setName("Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)");

        Chapter catPart = new Chapter(new Paragraph(anchor), 11);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormPNSACadastroEstabelecimentos formulario = gson.fromJson(
                resposta.get("pnsa_cadastro_estabelecimentos").getResposta(), FormPNSACadastroEstabelecimentos.class);
        FormPNSAVigilancia formulario2 = gson.fromJson(resposta.get("pnsa_vigilancia").getResposta(),
                FormPNSAVigilancia.class);

        Paragraph subPara = new Paragraph(
                "Cadastro de Estabelecimentos    " + "Avaliação: " + formulario2.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Estabelecimentos de criação cadastrados"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Número de estabelecimentos de avicultura existentes"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(4);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Procedimentos relacionados ao cadastramento"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("comerciais somente cadastrados (granjas corte/postura)"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("comerciais registrados (granjas corte/postura)"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Subsistência"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(
                "Estabelecimentos de venda de aves vivas (entreposto, distribuidores, casas agropecuárias, etc)"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getEstabelecimentos());
        table.addCell(formulario.getnAviculturaComeciaisCadastrados());
        table.addCell(formulario.getnAviculturaComerciaisRegistrados());
        table.addCell(formulario.getnAviculturaSubsistencia());
        table.addCell(formulario.getnAviculturaVendaVivas());
        table.addCell(formulario.getProcedimentos());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ---------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subPara = new Paragraph("Vigilância Epidemiológica", subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        table = new PdfPTable(6);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns
        table.setKeepTogether(true);

        c1 = new PdfPCell(new Phrase("Direcionamento das ações de vigilância", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Propriedades de risco", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Sítios migratórios", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Estabelecimentos de avicultura comercial", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(
                "Estabelecimentos de venda de aves vivas (entreposto, distribuidores, casas agropecuárias, etc)", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Somente Cadastrados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Registrados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario2.getDirecionamento());
        table.addCell(formulario2.getPontoRisco());
        table.addCell(formulario2.getSitioMigratorio());
        table.addCell(formulario2.getCadastrado());
        table.addCell(formulario2.getRegistrado());
        table.addCell(formulario2.getVendasAvesVivas());

        subCatPart.add(table);

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

    public void InteracaoPartes(Document document, HashMap<String, Resposta> resposta) throws DocumentException {
        Anchor anchor = new Anchor("Interação com as partes interessadas", chaFont);
        anchor.setName("Interação com as partes interessadas");

        Chapter catPart = new Chapter(new Paragraph(anchor), 12);// onde e descrito qual o numero do capitulo

        Gson gson = new Gson();
        FormICEducacaoSanitaria formulario = gson.fromJson(resposta.get("interacao_comunidade_educacao").getResposta(),
                FormICEducacaoSanitaria.class);
        Paragraph subPara = new Paragraph("Educação sanitária e comunicação social (divulgação e publicidade)    "
                + "Avaliação: " + formulario.getAvaliacao(), subFont);

        Section subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);// tira as bordas das celulas comuns
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Material de Educação Sanitária e Comunicação Social"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ações de Educação Sanitária"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Diagnóstico Educativo"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Comunicação Social"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.addCell(formulario.getMaterial());
        table.addCell(formulario.getAcaoEducacao());
        table.addCell(formulario.getDiagnostico());
        table.addCell(formulario.getComunicacao());

        subCatPart.add(table);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario.getPrazo() + "\nRecomendações UR: \n" + formulario.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // -----------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormICParticipacaoComunidade formulario2 = gson.fromJson(
                resposta.get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
        subPara = new Paragraph("Participação com a comunidade    " + "Avaliação: " + formulario2.getAvaliacao(),
                subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        Paragraph p = new Paragraph();
        p.add(new Chunk("Interação com a comunidade: " + formulario2.getInteracaoComunidade(), f));
        p.setTabSettings(new TabSettings(80f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("COMUSA: " + formulario2.getComusa(), f));
        subCatPart.add(p);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario2.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario2.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario2.getPrazo() + "\nRecomendações UR: \n" + formulario2.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario2.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormICParticipacaoInstituicoes formulario3 = gson.fromJson(
                resposta.get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
        subPara = new Paragraph(
                "Participação com instituições e representações    " + "Avaliação: " + formulario3.getAvaliacao(),
                subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        p = new Paragraph();
        p.add(new Chunk("Interação com instituições e representações: " + formulario3.getInteracaoInstituicao(), f));
        p.setTabSettings(new TabSettings(80f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Parcerias: " + formulario3.getParcerias(), f));
        subCatPart.add(p);

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario3.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario3.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario3.getPrazo() + "\nRecomendações UR: \n" + formulario3.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario3.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        FormIMVHabilitacao formulario4 = gson.fromJson(resposta.get("interacao_veterinario_habilitacao").getResposta(),
                FormIMVHabilitacao.class);
        subPara = new Paragraph(
                "Participação com instituições e representações    " + "Avaliação: " + formulario4.getAvaliacao(),
                subFont);

        subCatPart = catPart.addSection(subPara);// adiciona o sub titulo da secao do capitulo

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph(
                "OBS: Considerar as seguintes atividades: PNCEBT: vacinadores cadastrados, MVs habilitados; PNSA: MVs de granjas para emissão de GTA; "
                + "PNSE: MVs cadastrados para colheita de material biológico para diagnóstico AIE e MORMO.",
                f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        subCatPart.add(new Paragraph("Interação com MVs cadastrados/habilitados: " + formulario4.getInteracaoMV(), f));

        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

        comentariosRecomendacoes = new Paragraph("Comentários: \n" + formulario4.getComentario()
                + "\nRecomendações ULSAV / EAC: \n" + formulario4.getRecomendacaoUlsavEac() + "\nPrazos para ajuste: \n"
                + formulario4.getPrazo() + "\nRecomendações UR: \n" + formulario4.getRecomendacaoUr()
                + "\nRecomendações UC: \n" + formulario4.getRecomendacaoUC(), f);
        comentariosRecomendacoes.setAlignment(Element.ALIGN_JUSTIFIED);
        subCatPart.add(comentariosRecomendacoes);

        // ------------------------------------------------------
        subCatPart.add(paragraph);// adiciona linha vazia ao paragrafo da secao

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

        FormVulnerabilidadesPotencialidades formulario = gson.fromJson(
                resposta.get("vulnerabilidade_potencialidade").getResposta(),
                FormVulnerabilidadesPotencialidades.class);

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

        FormIdentificacaoEscritorio formulario = gson.fromJson(resposta.get("identificacao_escritorio").getResposta(),
                FormIdentificacaoEscritorio.class);

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
