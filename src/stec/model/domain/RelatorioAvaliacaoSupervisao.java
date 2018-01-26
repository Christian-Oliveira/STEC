package stec.model.domain;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

public class RelatorioAvaliacaoSupervisao {

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
            RelatorioAvaliacaoSupervisao relatorio = new RelatorioAvaliacaoSupervisao();

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file + ".pdf"));
            document.open();

            relatorio.Capa(document, supervisao);
            relatorio.Avaliacao(document, supervisao, hashRespostas);

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

        Paragraph titulo = new Paragraph("Avaliação da Auditoria / Supervisão em Defesa Sanitária Animal\n"
                + "Escritório de Atendimento à Comunidade", catFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        capa.add(titulo);

        addEmptyLine(capa, 4);

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

    public void Avaliacao(Document document, Supervisao supervisao, HashMap<String, Resposta> resposta) throws DocumentException {

        Gson gson = new Gson();

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);// 100% da pagina
        table.setWidths(new float[]{1, 1, 3, 1});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// alinha as celulas comuns

        PdfPCell c1 = new PdfPCell(new Phrase("Componentes", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Competências", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Item avaliado", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Avaliacao", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1. RECUROS HUMANOS, FÍSICOS E FINANCEIROS", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(10);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1.1 Recursos Humanos", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(4);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1.1.1. Quantitativo, jornada e distribuição", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRHQuantitativo formulario = gson.fromJson(resposta.get("rh_quantitativo").getResposta(), FormRHQuantitativo.class);
        table.addCell(formulario.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.1.2. Estabilidade das estruturas e sustentabilidade das políticas sanitárias", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRHEstabilidade formulario2 = gson.fromJson(resposta.get("rh_estabilidade").getResposta(), FormRHEstabilidade.class);
        table.addCell(formulario2.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.1.3. Capacitação técnica e formação continuada", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRHCapacitacao formulario3 = gson.fromJson(resposta.get("rh_capacitacao").getResposta(), FormRHCapacitacao.class);
        table.addCell(formulario3.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.1.4. Competências e independência técnica.", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRHCompetencias formulario4 = gson.fromJson(resposta.get("rh_competencias").getResposta(), FormRHCompetencias.class);
        table.addCell(formulario4.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.2 Recursos Físicos", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(4);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1.2.1. Instalações ", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRFInstalacoes formulario5 = gson.fromJson(resposta.get("rf_instalacoes").getResposta(), FormRFInstalacoes.class);
        table.addCell(formulario5.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.2.2. Transportes ", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRFTransportes formulario6 = gson.fromJson(resposta.get("rf_transportes").getResposta(),
                FormRFTransportes.class);
        table.addCell(formulario6.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.2.3. Equipamentos e acesso à comunicação", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRFEquipamentos formulario7 = gson.fromJson(resposta.get("rf_equipamentos").getResposta(),
                FormRFEquipamentos.class);
        table.addCell(formulario7.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.2.4. Sistemas de Informação", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRFSistemasInformacao formulario8 = gson.fromJson(resposta.get("rf_sistemas").getResposta(), FormRFSistemasInformacao.class);
        table.addCell(formulario8.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.3 Recursos Financeiros", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1.3.1. Recursos para custeio", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRFICusteio formulario9 = gson.fromJson(resposta.get("rfi_custeio").getResposta(), FormRFICusteio.class);
        table.addCell(formulario9.getAvaliacao());

        c1 = new PdfPCell(new Phrase("1.3.2. Arrecadação ", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormRFIArrecadacao formulario10 = gson.fromJson(resposta.get("rfi_arrecadacao").getResposta(), FormRFIArrecadacao.class);
        table.addCell(formulario10.getAvaliacao());

        //------------------------------------------------------------------------------------------//
        c1 = new PdfPCell(new Phrase("2. Autoridade, capacidade e técnica operacional", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(20);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2.1 Estrutura Organizacional", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(1);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2.1.1. Estrutura Organizacional e capacidade de coordenação interna", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormEOEstrutura formulario11 = gson.fromJson(resposta.get("eo_estrutura").getResposta(), FormEOEstrutura.class);
        table.addCell(formulario11.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.2 Autoridade e Gestão de Qualidade", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2.2.1. Base legal e aplicação de legislação, manuais e POPs ", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormAGBaseLegal formulario12 = gson.fromJson(resposta.get("ag_base_legal").getResposta(), FormAGBaseLegal.class);
        table.addCell(formulario12.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.2.2. Organização dos processos e unidades ", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormAGOrganizacao formulario13 = gson.fromJson(resposta.get("ag_organizacao").getResposta(),
                FormAGOrganizacao.class);
        table.addCell(formulario13.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.2.3. Supervisão e controle interno", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormAGSupervisao formulario14 = gson.fromJson(resposta.get("ag_supervisao").getResposta(),
                FormAGSupervisao.class);
        table.addCell(formulario14.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.3 Capacidade técnica e operacional", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(8);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2.3.1. Controle de cadastro de produtores, propriedades e animais", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormCTOControleCadastro formulario15 = gson.fromJson(resposta.get("cto_controle_cadastro").getResposta(),
                FormCTOControleCadastro.class);
        table.addCell(formulario15.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.3.2. Planejamento, execução de atividades e registro", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormCTOPlanejamento formulario16 = gson.fromJson(resposta.get("cto_planejamento").getResposta(),
                FormCTOPlanejamento.class);
        table.addCell(formulario16.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.3.3. Controle de divisas e trânsito interno", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormCTOControle formulario17 = gson.fromJson(resposta.get("cto_controle").getResposta(), FormCTOControle.class);
        table.addCell(formulario17.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.3.4. Controle de trânsito de animais e produtores de origem animal", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormCTOControleTransitoAnimais formulario18 = gson.fromJson(resposta.get("cto_transito_animais").getResposta(),
                FormCTOControleTransitoAnimais.class);
        table.addCell(formulario18.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.3.5. Controle de eventos de aglomeração de animais", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormCTOControleEventosAglomeracao formulario19 = gson
                .fromJson(resposta.get("cto_controle_eventos").getResposta(), FormCTOControleEventosAglomeracao.class);
        table.addCell(formulario19.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.3.6. Fiscalização em revendas veterinárias", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormCTOFiscalizacao formulario20 = gson.fromJson(resposta.get("cto_fiscalizacao").getResposta(),
                FormCTOFiscalizacao.class);
        table.addCell(formulario20.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.3.7. Capacidade para detecção precone e notificação imediata de doenças", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormCTOCapacidadeDeteccaoPrecoce formulario21 = gson.fromJson(resposta.get("cto_deteccao_precoce").getResposta(),
                FormCTOCapacidadeDeteccaoPrecoce.class);
        table.addCell(formulario21.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.3.8. Capacidade para atendimento a suspeitas e atuação em emergências", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormCTOAtendimentoSuspeita formulario22 = gson.fromJson(resposta.get("cto_atendimento_suspeita").getResposta(),
                FormCTOAtendimentoSuspeita.class);
        table.addCell(formulario22.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.4 Prevenção, controle e erradicação de doenças", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(8);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2.4.1. Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão(PNEFA/MA)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormPNEFAFiscalizacoes formulario23 = gson.fromJson(resposta.get("pnefa_fiscalizacoes").getResposta(),
                FormPNEFAFiscalizacoes.class);
        table.addCell(formulario23.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.4.2. Programa Nacional de Controle e Erradicação da Brucelose - Maranhão(PNCEBT/MA)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormPNCEBTFiscalizacoes formulario24 = gson.fromJson(resposta.get("pncebt_fiscalizacoes").getResposta(),
                FormPNCEBTFiscalizacoes.class);
        table.addCell(formulario24.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.4.3. Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão(PNCRH/MA)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormPNCRHFiscalizacoes formulario25 = gson.fromJson(resposta.get("pncrh_fiscalizacoes").getResposta(),
                FormPNCRHFiscalizacoes.class);
        table.addCell(formulario25.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.4.4. Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão(PNEEB/MA)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormPNEEBVigilancia formulario26 = gson.fromJson(resposta.get("pneeb_vigilancia").getResposta(),
                FormPNEEBVigilancia.class);
        table.addCell(formulario26.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.4.5. Programa Nacional de Sanidade Equídea - Maranhão(PNSE/MA)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormPNSECadastroEstabelecimento formulario27 = gson.fromJson(
                resposta.get("pnse_cadastro_estabelecimentos").getResposta(), FormPNSECadastroEstabelecimento.class);
        table.addCell(formulario27.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.4.6. Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão(PNSCO/MA)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormPNSCOCadastros formulario28 = gson.fromJson(resposta.get("pnsco_cadastro_estabelecimentos").getResposta(),
                FormPNSCOCadastros.class);
        table.addCell(formulario28.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.4.7. Programa Nacional de Su´´ideos - Maranhão(PNSS/MA)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormPNSSVigilancia formulario29 = gson.fromJson(resposta.get("pnss_vigilancia").getResposta(),
                FormPNSSVigilancia.class);
        table.addCell(formulario29.getAvaliacao());

        c1 = new PdfPCell(new Phrase("2.4.8. Programa Nacional de Sanidade Avícola - Maranhão(PNSA/MA)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormPNSAVigilancia formulario30 = gson.fromJson(resposta.get("pnsa_vigilancia").getResposta(),
                FormPNSAVigilancia.class);
        table.addCell(formulario30.getAvaliacao());

        //------------------------------------------------------------------------------------------//
        c1 = new PdfPCell(new Phrase("3. Interação com as partes interessadas", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(6);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("3.1 Interação com a comunidade", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(3);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("3.1.1. Educação sanitária e comunicação social(divulgação e publicidade)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormICEducacaoSanitaria formulario31 = gson.fromJson(resposta.get("interacao_comunidade_educacao").getResposta(),
                FormICEducacaoSanitaria.class);
        table.addCell(formulario31.getAvaliacao());

        c1 = new PdfPCell(new Phrase("3.1.2. Participação com a comunidade", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormICParticipacaoComunidade formulario32 = gson.fromJson(
                resposta.get("interacao_comunidade_comunidade").getResposta(), FormICParticipacaoComunidade.class);
        table.addCell(formulario32.getAvaliacao());

        c1 = new PdfPCell(new Phrase("3.1.3. Participações com instituições e representaçãoes", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormICParticipacaoInstituicoes formulario33 = gson.fromJson(
                resposta.get("interacao_comunidade_instituicoes").getResposta(), FormICParticipacaoInstituicoes.class);
        table.addCell(formulario33.getAvaliacao());

        c1 = new PdfPCell(new Phrase("3.2 Interação com os médicos veterinários", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(1);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("3.2.1. Habilitação e cadastramento dos médicos veterinários", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormIMVHabilitacao formulario34 = gson.fromJson(resposta.get("interacao_veterinario_habilitacao").getResposta(),
                FormIMVHabilitacao.class);
        table.addCell(formulario34.getAvaliacao());

        c1 = new PdfPCell(new Phrase("3.3 Interação com instituições", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(2);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("3.3.1. Sistema de Inspeção(seguridade alimentar)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormIISistemaInspecao formulario35 = gson.fromJson(resposta.get("interacao_instituicao_inspecao").getResposta(),
                FormIISistemaInspecao.class);
        table.addCell(formulario35.getAvaliacao());

        c1 = new PdfPCell(new Phrase("3.3.2. Sistema Único de Saúde(zoonoses, vigilância sanitária,etc.)", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        FormIISUS formulario36 = gson.fromJson(resposta.get("interacao_instituicao_sus").getResposta(), FormIISUS.class);
        table.addCell(formulario36.getAvaliacao());

        //------------------------------------------------------------------------------------------//
        c1 = new PdfPCell(new Phrase("4. Acesso aos mercados", f));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(1);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("4.1 Capacidade de certificação para acesso a mercados", f));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c1.setRowspan(1);
        c1.setColspan(2);
        table.addCell(c1);

        FormAMAcesso formulario37 = gson.fromJson(resposta.get("acesso_mercado").getResposta(), FormAMAcesso.class);
        table.addCell(formulario37.getAvaliacao());

        document.add(table);// adiciona o conteudo da capa ao documento
    }

    public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
