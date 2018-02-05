//// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart', 'bar' ]});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(
    function() { // Anonymous function that calls drawChart1 and drawChart2

        //------------1.1.1 Quantitativo, jornada e distribuição--------
        graf_avaliacao("rh_av_quantitativo");
        graf_distribuicao();
        graf_item("rh_atende_demanda");

        //------------1.1.2 Estabilidade das estruturas e sustentabilidade das políticas sanitárias--------
        graf_avaliacao("rh_av_estabilidade");
        graf_item("rh_estabilidade");

        //------------1.1.3 Capacitação técnica e formação continuada--------
        graf_avaliacao("rh_av_capacitacao");
        graf_item("rh_capacitacao");

        //------------1.1.4 Competências e independência técnica--------
        graf_avaliacao("rh_av_competencias");
        graf_item("rh_competencias");

        //------------1.2.1 Instalações--------
        graf_avaliacao("rf_av_instalacoes");
        var obj = {
            "Natureza do prédio": "natureza",
            "Limpeza": "limpeza",
            "Estrutura": "conservacao",
            "Banheiro": "banheiro",
            "Sala de Atendimento": "atendimento",
            "Sala p/ Veterinário": "veterinario",
            "Condições de atendimento": "atendimento",
            "Apresentação externa": "externo",
            "Organização interna": "interno",
        };
        graf_multi_itens(obj, "grafico_rf_instalacoes");
        graf_item("rf_demanda");
        graf_item("rf_mobiliario_relacao");

        //------------1.2.2 Instalações--------
        graf_avaliacao("rf_av_transportes");
        var obj = {
            "Estado de conservação": "trasnporteConservacao",
            "Atende a demanda?": "demanda",
            "Manutenção preventiva": "preventiva",
            "Manutenção emergencial": "emergencial",
            "Mapa KM": "mapaKm",
            "Condutores para veículos": "veterinario",
            "Relação dos veículos e a lotação?": "atendimento",
            "Frota adequada?": "externo"
        };
        graf_multi_itens(obj, "grafico_rf_transportes");

        //------------1.2.3 – Equipamentos e acesso à comunicação--------
        graf_avaliacao("rf_av_equipamentos");
        var obj = {
            "Suficiente?": "suficiente",
            "Condições de uso": "condicoesEquipamento",
            "Acesso a internet": "acessoInternet",
            "Linha Telefônica": "telefone",
            "Demais equipamentos": "demaisEquipamentos"
        };
        graf_multi_itens(obj, "grafico_rf_equipamentos");

        //------------1.2.4 – Sistemas de informação--------
        graf_avaliacao("rf_av_sistemas");
        graf_item("rf_sistemas");

        //------------1.3.1 Recursos para custeio--------
        graf_avaliacao("rfi_av_custeio");
        var obj = {
            "Solicitação de diárias": "diarias",
            "FAI": "fai",
            "Relatório de KM": "relatorioKm",
            "Relatório de viagem": "relatorioViagem",
            "Disponibilidade de recurso financeiro": "recursos"
        };
        graf_multi_itens(obj, "grafico_rfi_custeio");
        var obj = {
            "Controle mensal de arrecadação": "arrecadacao",
            "Autos de infração": "autosInfracao"
        };
        graf_multi_itens(obj, "grafico_rfi_arrecadacao");

        //------------2.1.1 Estrutura organizacional e capacidade de coordenação interna--------
        graf_avaliacao("eo_av_estrutura");
        graf_estrutura_organizacional();

        //------------2.2.1 Base legal e aplicação da legislação, manuais e POPs--------
        graf_avaliacao("ag_av_base_legal");
        var obj = {
            "Legislação": "legislacao",
            "Manuais": "manual",
            "POPs" : "pop",
            "Aplicação de procedimentos" : "procedimentos"
        };
        graf_multi_itens(obj, "grafico_ag_base_legal");

        //------------2.2.2 Organização dos processos e unidades--------
        graf_avaliacao("ag_av_organizacao");
        var obj = {
            "Arquivo": "arquivo",
            "Mural técnico": "mural",
            "Fluxo de informações" : "fluxoInformacao"
        };
        graf_multi_itens(obj, "grafico_ag_organizacao");

        //------------2.2.3 Supervisão e controle interno--------
        graf_avaliacao("ag_av_supervisao");
        var obj = {
            "Visita técnica PFFA": "pffa",
            "Visita técnica EAC": "eac",
            "Supervisoes Recebidas" : "supervisao"
        };
        graf_multi_itens(obj, "grafico_ag_supervisao");

        //------------2.3.1 Controle de cadastro de produtores, propriedades e animais--------
        graf_avaliacao("cto_av_controle_cadastro");
        var obj = {
            "Informações de produtores, propriedades e efetivos de todos os municípios da jurisdição": "infoProdutores",
            "Documentação referente ao cadastramento de propriedade, produtores rurais e explorações pecuárias": "docCadastramento",
            "Procedimentos para abertura e encerramento de cadastros" : "proAbertura",
            "Registro de entrada, saída e saldo nas fichas de movimentação": "regEntrada"
        };
        graf_multi_itens(obj, "grafico_cto_controle_cadastro");

        //------------2.3.2 Planejamento, execução de atividades e registro--------
        graf_avaliacao("cto_av_planejamento");
        var obj = {
            "Planejamento Técnico Mensal": "planMensal",
            "Registro das ações planejadas": "regAcoes",
            "Cumprimento das metas propostas pelos programas sanitários" : "cumMeta"
        };
        graf_multi_itens(obj, "grafico_cto_planejamento");

        //------------2.3.3 Controle de divisas e trânsito interno--------
        graf_avaliacao("cto_av_controle_divisas");
        var obj = {
            "Pontos Estratégicos para realização de blitz": "pontoEstrategico",
            "Realização mensal de blitz": "relMensal",
            "Registro da blitz": "regBlitz",
            "Apreensões": "apreensoes",
            "Material para realização de blitz": "matBlitz",
            "Registro sobre operações volantes": "regOperacoes",
        };
        graf_multi_itens(obj, "grafico_cto_controle_divisas");

        //------------2.3.4 Controle de trânsito de animais e produtos de origem animal--------
        graf_avaliacao("cto_av_controle_transito");
        var obj = {
            "Credenciamento e descredenciamento dos emitentes": "credenciamento",
            "Manuais para preenchimento": "manualPreenchimento",
            "Emissão de GTA": "emissaoGTA",
            "Exigências zoossanitárias": "exiZoosanitaria",
            "Relatórios de emissão de GTA": "relEmissao",
            "Gráficos da movimentação": "grafMovimentacao",
            "Organização das GTAs": "organizacaoGTA",
            "Guias de trânsito para subprodutos de origem animal": "guiaTransito",
        };
        graf_multi_itens(obj, "grafico_cto_controle_transito");

        //------------2.3.5 Controle de eventos de aglomeração de animais--------
        graf_avaliacao("cto_av_controle_eventos");
        var obj = {
            "Documentação de cadastramento dos recintos": "cadastramentoRecinto",
            "Documentação para realização do evento": "docEvento",
            "Registro de eventos": "regEventos",
            "Inspeção clínica de animais": "inspClinica",
            "Emissão de GTAs de saída": "emissaoGTASaida",
        };
        graf_multi_itens(obj, "grafico_cto_controle_eventos");

        //------------2.3.6 Fiscalização em revendas veterinárias--------
        graf_avaliacao("cto_av_fiscalizacao_revendas");
        var obj = {
            "Cadastramento de Revendas Veterinárias": "cadastramentoRevendas",
            "Frequência de fiscalizações em revendas": "freqFiscalizacao",
            "Diárias": "fiscDiaria",
            "Semanais": "fiscSemanal",
            "Supervisão na revenda veterinária": "supRevenda",
            "Apreensão de vacinas": "apreensaoVacina",
        };
        graf_multi_itens(obj, "grafico_cto_fiscalizacao_revendas");

        //------------2.3.7 Capacidade para detecção precoce e notificação imediata de doenças--------
        graf_avaliacao("cto_av_deteccao_precoce");
        var obj = {
            "Fluxo de informações": "fluxoInformacoes",
            "Participação da comunidade": "participacaoComunidade",
            "Fontes de informação": "fonteInformacao",
            "Registro das comunicações e atendimentos de ocorrência de enfermidades": "registroComunicacoes",
        };
        graf_multi_itens(obj, "grafico_cto_deteccao_precoce");

        //------------2.3.8 Capacidade para atendimento a suspeitas e atuação em emergências--------
        graf_avaliacao("cto_av_capacidade_atendimento");
        var obj = {
            "EPI": "materialEPI",
            "Enfermidades Vesiculares": "materialKitAtendimento",
            "Colheita de material biológico": "materialKitBiologico",
            "Acondicionamento e remessa de material para a Unidade Central": "acondicionamento",
            "Procedimentos no atendimento às notificações": "procedimentoNotificacoes",
            "Cadastro Agroprodutivo": "cadastroAgroprodutivo",
        };
        graf_multi_itens(obj, "grafico_cto_capacidade_atendimento");

        //------------2.4.1 Programa Nacional de Erradicação e Prevenção da Febre Aftosa - Maranhão (PNEFA/MA)--------
        graf_avaliacao("pnefa_av");
        var obj = {
            "Procedimentos relacionados à vacinação": "procedimentoVacinacao",
            "Procedimentos de comprovação de vacinação": "procedimentoComprovacao",
            "Relatórios de campanha": "relatorioCampanha",
            "Procedimentos pós-etapa": "procedimentoPosEtapa",
        };
        graf_multi_itens(obj, "grafico_pnefa_fiscalizacoes");
        var obj = {
            "Direcionamento das ações de vigilância": "direcionamentoAcoes",
            "Propriedades e pontos de risco": "propriedadePontosRisco",
            "Realização e registro da vigilância em propriedades e pontos de risco": "realizacaoRegistroVigilancia",
            "Indicadores da caracterização epidemiológica": "indicadoresCaracterizacaoEpidemiologica",
            "Procedimentos adotados Foco": "procedimentosAdotadosFoco",
            "Procedimentos adotados Vínculo": "procedimentosAdotadosVinculo",
        };
        graf_multi_itens(obj, "grafico_pnefa_vigilancia");

        //------------2.4.2 Programa Nacional de Controle e Erradicação da Brucelose e Tuberculose - Maranhão (PNCEBT/MA)--------
        graf_avaliacao("pncebt_av");
        var obj = {
            "Procedimentos de comprovação de vacinação": "procedimentoComprovacaoPNCEBT",
            "Relatórios de cobertura vacinal": "relatorioCobertura",
            "Procedimentos com inadimplentes": "procedimentoInadimplente",
        };
        graf_multi_itens(obj, "grafico_pncebt_fiscalizacao");
        var obj = {
            "Direcionamento das ações de vigilância": "direcionamentoAcoes",
            "Propriedades e pontos de risco": "propriedadePontosRisco",
            "Realização e registro da vigilância em propriedades e pontos de risco": "realizacaoRegistroVigilancia",
            "Indicadores da caracterização epidemiológica": "indicadoresCaracterizacaoEpidemiologica",
            "Procedimentos adotados Foco": "procedimentosAdotadosFoco",
            "Procedimentos adotados Vínculo": "procedimentosAdotadosVinculo",
        };
        graf_multi_itens(obj, "grafico_pncebt_vigilancia");
        var obj = {
            "Portarias Med Vet cadastrados e/ou habilitados": "portariaVetCadastrado",
            "Pontualidade Med Vet cadastrados e/ou habilitados": "pontualidadeVetCadastrado",
            "Relatórios do PNCEBT/MA": "relatorioPNCEBT",
            "Comercialização de blocos de Receituário e atestados de vacinação contra brucelose (UR)": "comercializacaoReceituario",
        };
        graf_multi_itens(obj, "grafico_pncebt_controle");

        //------------2.4.3 Programa Nacional de Controle da Raiva dos Herbívoros - Maranhão (PNCRH/MA)--------
        graf_avaliacao("pncrh_av");
        var obj = {
            "Procedimentos de comprovação de vacinação": "procedimentoComprovacaoPNCRH",
            "Relatórios de cobertura vacinal": "relatorioCoberturaPNCRH",
        };
        graf_multi_itens(obj, "grafico_pncrh_fiscalizacao");
        var obj = {
            "Direcionamento das ações de vigilância": "direcionamentoVigilanciaPNCRH",
            "Cadastro e monitoramento de abrigos de morcegos hematófagos": "cadastroMonitoramentoPNCRH",
            "Captura de morcegos": "capturaMorcegosPNCRH",
            "Procedimentos adotados Foco": "procedimentosAdotadosFocoPNCRH",
        };
        graf_multi_itens(obj, "grafico_pncrh_vigilancia");

        //------------2.4.4 Programa Nacional de Prevenção e Vigilância da Encefalite Espongiforme Bovina - Maranhão (PNEEB/MA)--------
        graf_avaliacao("pneeb_av");
        var obj = {
            "Direcionamento das ações de vigilância": "direcionamentoVigilanciaPNEEB",
            "Identificação de áreas segundo os fatores de risco": "identificacaoAreasPNEEB",
            "Inspeções em propriedades": "inspecoesPropriedadePNEEB",
            "Fiscalização em matadouro": "fiscalizacaoMatadouroPNEEB",
            "Fiscalização em graxaria": "fiscalizacaoGraxariaPNEEB",
            "Procedimentos adotados Foco": "procedimentosAdotadosFocoPNEEB",
        };
        graf_multi_itens(obj, "grafico_pneeb_vigilancia");

        //------------2.4.5 Programa Nacional de Sanidade Equídea - Maranhão (PNSE/MA)--------
        graf_avaliacao("pnse_av");
        var obj = {
            "Estabelecimentos de criação cadastrados": "estabelecimentoCadastradoPNSE",
            "Atualização cadastral": "atualizacaoCadastralPNSE",
        };
        graf_multi_itens(obj, "grafico_pnse_cadastro");
        var obj = {
            "Direcionamento das ações de vigilância": "direcionamentoVigilanciaPNSE",
            "Vigilância em propriedades": "vigilanciaPropriedadesPNSE",
            "Doenças de notificação obrigatória": "notificacaoObrigatoriaPNSE",
            "Procedimentos adotados Foco": "procedimentosAdotadosFocoPNSE",
        };
        graf_multi_itens(obj, "grafico_pnse_vigilancia");
        var obj = {
            "Relação dos Med Vet cadastrados": "relacaoVetCadastradosPNSE",
            "Relatórios de colheita Med Vet cadastrados": "relatorioVetCadastradoPNSE",
        };
        graf_multi_itens(obj, "grafico_pnse_controles");

        //------------2.4.6 Programa Nacional de Sanidade de Caprinos e Ovinos - Maranhão (PNSCO/MA)--------
        graf_avaliacao("pnsco_av");
        var obj = {
            "Estabelecimentos de criação cadastrados": "estabelecimentoCadastradoPNSCO",
            "Atualização cadastral": "atualizacaoCadastralPNSCO",
        };
        graf_multi_itens(obj, "grafico_pnsco_cadastro");
        var obj = {
            "Doenças de notificação obrigatória": "doencaNotificacaoObrigatoriaPNSCO",
            "Ações de vigilância": "acoesVigilanciaPNSCO",
        };
        graf_multi_itens(obj, "grafico_pnsco_vigilancia");

        //------------2.4.7 Programa Nacional de Sanidade de Suídeos - Maranhão (PNSS/MA)--------
        graf_avaliacao("pnss_av");
        var obj = {
            "Estabelecimentos de criação cadastrados": "estabelecimentoCadastradoPNSS",
            "Suídeos asselvajados": "suideosAlvejadosPNSS",
            "Atualização cadastral": "atualizacaoCadastralPNSS",
        };
        graf_multi_itens(obj, "grafico_pnss_cadastro");
        var obj = {
            "Direcionamento das ações de vigilância": "direcionamentoAcoesVigilanciaPNSS",
            "Propriedades de risco": "propriedadeRiscoPNSS",
            "Registro da vigilância em propriedades e pontos de risco": "registroVigilanciaPNSS",
            "Mortandade": "mortandadePNSS",
        };
        graf_multi_itens(obj, "grafico_pnss_vigilancia");

        //------------2.4.8 Programa Nacional de Sanidade Avícola - Maranhão (PNSA/MA)--------
        graf_avaliacao("pnsa_av");
        var obj = {
            "Estabelecimentos de criação cadastrados": "estabelecimentoCadastradoPNSA",
            "Procedimentos relacionados ao cadastramento": "procedimentosRelacionadosPNSA",
        };
        graf_multi_itens(obj, "grafico_pnsa_cadastro");
        var obj = {
            "Direcionamento das ações de vigilância": "direcionamentoAcoesPNSA",
            "Pontos de risco": "pontoRiscoPNSA",
            "Sítios migratórios": "sitiosMigratoriosPNSA",
            "Somente Cadastrados": "estabelecimentoCadastradoPNSA",
            "Registrados": "estabelecimentoComercialRegistradoPNSA",
            "Estabelecimentos de venda de aves vivas (entreposto, distribuidores, casas agropecuárias, etc)": "estabelecimentoVendaAvesPNSA",
            "Mortandade": "mortandadePNSA",
            "Doenças de notificação obrigatória": "doencaNotificacaoPNSA",
        };
        graf_multi_itens(obj, "grafico_pnsa_vigilancia");

        //------------3.1.1 Educação sanitária e comunicação social (divulgação e publicidade)--------
        graf_avaliacao("ic_av_educacao");
        var obj = {
            "Material de Educação Sanitária e Comunicação Social": "materialEducacaoSanitaria",
            "Ações de Educação Sanitária": "acoesEducacaoSanitaria",
            "Diagnóstico Educativo": "diagnosticoEducativo",
            "Comunicação Social": "comunicacaoSocial",
        };
        graf_multi_itens(obj, "grafico_ic_educacao");

        //------------3.1.2 Participação com a comunidade--------
        graf_avaliacao("ic_av_comunidade");
        var obj = {
            "Interação com a comunidade": "interacaoComunidade",
            "COMUSA": "comusa",
        };
        graf_multi_itens(obj, "grafico_ic_comunidade");

        //------------3.1.3 Participação com instituições e representações--------
        graf_avaliacao("ic_av_instituicoes");
        var obj = {
            "Interação com instituições e representações": "interacaoInstituicoes",
            "Parcerias": "parcerias",
        };
        graf_multi_itens(obj, "grafico_ic_instituicoes");

        //------------3.2.1 Habilitação e cadastramento dos médicos veterinários--------
        graf_avaliacao("imv_av_veterinario");
        graf_item("imv_veterinario");

        //------------3.3.1 Sistema de inspeção (seguridade alimentar)--------
        graf_avaliacao("ii_av_sus");
        graf_item("ii_sus");

        //------------3.3.2 Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)--------
        graf_avaliacao("ii_av_inspecao");
        graf_item("ii_inspecao");

        //------------4 ACESSO AOS MERCADOS--------
        graf_avaliacao("am_av_acesso");
        graf_item("am_acesso");
});

var verde = "#bce02e"; //EA 5
var amarelo = "#ffff00"; //ED 2
var vermelho = "#e0642e"; //NE 1
var azul = "#2e97e0"; //NA 4
var roxo = "#a850f4"; //3
var cinza = "#e5e4e2"; // na da avaliacao

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.

//Funcao para criar a avaliacao
function graf_avaliacao(avaliacao_id) {
    var data = google.visualization.arrayToDataTable([
        ["Avaliação", "", { role: "style" } ],
        ["1", parseInt(document.getElementById(avaliacao_id+"Av1").innerText), vermelho],
        ["2", parseInt(document.getElementById(avaliacao_id+"Av2").innerText), amarelo],
        ["3", parseInt(document.getElementById(avaliacao_id+"Av3").innerText), roxo],
        ["4", parseInt(document.getElementById(avaliacao_id+"Av4").innerText), azul],
        ["5", parseInt(document.getElementById(avaliacao_id+"Av5").innerText), verde],
        ["NA", parseInt(document.getElementById(avaliacao_id+"AvNA").innerText), cinza],
    ]);

    var options = {
        title: "Avaliação do Formulário: " ,
        width: 650,
        height: 400,
        bar: {groupWidth: "80%"},
        legend: { position: "none" },
    };

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_"+avaliacao_id));
    chart.draw(data, options);
}

//cria grafico com um unico item
function graf_item(item_id) {
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["EA", parseInt(document.getElementById(item_id+"EA").innerText), vermelho],
        ["ED", parseInt(document.getElementById(item_id+"ED").innerText), amarelo],
        ["NE", parseInt(document.getElementById(item_id+"NE").innerText), roxo],
        ["NA", parseInt(document.getElementById(item_id+"NA").innerText), cinza],
    ]);

    var options = {
        //title: "Avaliação do Formulário: " ,
        width: 650,
        height: 400,
        bar: {groupWidth: "80%"},
        legend: { position: "none" },
    };

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_"+item_id));
    chart.draw(data, options);
}

//cria grafico com multiplos itens
function graf_multi_itens(obj, nome_grafico) {
    var options = {
        //title: "Avaliação do Formulário: " ,
        width: 700,
        height: 400,
        bar: {groupWidth: "80%"},
        legend: { position: "top" },
        isStacked: true,
        chartArea: {width: '50%', height: '60%'},
    };

    var arrayData = [ ["", "EA", "ED", "NE", "NA"] ];

    //preenche o array com os titulo e valores de cada item
    for (var i in obj) {
        arrayData.push([i, 
            parseInt(document.getElementById(obj[i]+"EA").innerText),
            parseInt(document.getElementById(obj[i]+"ED").innerText),
            parseInt(document.getElementById(obj[i]+"NE").innerText),
            parseInt(document.getElementById(obj[i]+"NA").innerText),
        ]);
    }
    
    var data = google.visualization.arrayToDataTable(arrayData);

    var chart = new google.visualization.BarChart(document.getElementById(nome_grafico));
    chart.draw(data, options);
}

function graf_distribuicao() {

    var options = {
        //title: "Avaliação do Formulário: " ,
        width: 650,
        height: 400,
        bar: {groupWidth: "80%"},
        legend: { position: "none" },
    };

    //Medico veterinario
    //Vinculo
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["AGED", parseInt(document.getElementById("vetQEAGED").innerText), verde],
        ["INAGRO", parseInt(document.getElementById("vetQEINAGRO").innerText), verde],
        ["OUTROS", parseInt(document.getElementById("vetQEOUTROS").innerText), verde],
        ["TOTAL", parseInt(document.getElementById("vetQETOTAL").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_vet_vinculo"));
    chart.draw(data, options);

    //Capacitacao
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["TREINAMENTO", parseInt(document.getElementById("vetCT").innerText), verde],
        ["POS", parseInt(document.getElementById("vetCP").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_vet_capacitacao"));
    chart.draw(data, options);


    //Engenheiro agronomo
    //Vinculo
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["AGED", parseInt(document.getElementById("agroQEAGED").innerText), verde],
        ["INAGRO", parseInt(document.getElementById("agroQEINAGRO").innerText), verde],
        ["OUTROS", parseInt(document.getElementById("agroQEOUTROS").innerText), verde],
        ["TOTAL", parseInt(document.getElementById("agroQETOTAL").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_agro_vinculo"));
    chart.draw(data, options);

    //Capacitacao
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["TREINAMENTO", parseInt(document.getElementById("agroCT").innerText), verde],
        ["POS", parseInt(document.getElementById("agroCP").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_agro_capacitacao"));
    chart.draw(data, options);


    //Engenheiro florestal
    //Vinculo
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["AGED", parseInt(document.getElementById("florQEAGED").innerText), verde],
        ["INAGRO", parseInt(document.getElementById("florQEINAGRO").innerText), verde],
        ["OUTROS", parseInt(document.getElementById("florQEOUTROS").innerText), verde],
        ["TOTAL", parseInt(document.getElementById("florQETOTAL").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_flor_vinculo"));
    chart.draw(data, options);

    //Capacitacao
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["TREINAMENTO", parseInt(document.getElementById("florCT").innerText), verde],
        ["POS", parseInt(document.getElementById("florCP").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_flor_capacitacao"));
    chart.draw(data, options);


    //Zootecnista
    //Vinculo
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["AGED", parseInt(document.getElementById("zooQEAGED").innerText), verde],
        ["INAGRO", parseInt(document.getElementById("zooQEINAGRO").innerText), verde],
        ["OUTROS", parseInt(document.getElementById("zooQEOUTROS").innerText), verde],
        ["TOTAL", parseInt(document.getElementById("zooQETOTAL").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_zoo_vinculo"));
    chart.draw(data, options);

    //Capacitacao
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["TREINAMENTO", parseInt(document.getElementById("florCT").innerText), verde],
        ["POS", parseInt(document.getElementById("florCP").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_zoo_capacitacao"));
    chart.draw(data, options);


    //Tecnico agropecuaria
    //Vinculo
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["AGED", parseInt(document.getElementById("tecAgroQEAGED").innerText), verde],
        ["INAGRO", parseInt(document.getElementById("tecAgroQEINAGRO").innerText), verde],
        ["OUTROS", parseInt(document.getElementById("tecAgroQEOUTROS").innerText), verde],
        ["TOTAL", parseInt(document.getElementById("tecAgroQETOTAL").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_tecAgro_vinculo"));
    chart.draw(data, options);

    //Capacitacao
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["TREINAMENTO", parseInt(document.getElementById("tecAgroCT").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_tecAgro_capacitacao"));
    chart.draw(data, options);


    //Tecnico administrativo
    //Vinculo
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["AGED", parseInt(document.getElementById("tecAdmQEAGED").innerText), verde],
        ["INAGRO", parseInt(document.getElementById("tecAdmQEINAGRO").innerText), verde],
        ["OUTROS", parseInt(document.getElementById("tecAdmQEOUTROS").innerText), verde],
        ["TOTAL", parseInt(document.getElementById("tecAdmQETOTAL").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_tecAdm_vinculo"));
    chart.draw(data, options);

    //Capacitacao
    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["TREINAMENTO", parseInt(document.getElementById("tecAdmCT").innerText), verde]
    ]);

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_tecAdm_capacitacao"));
    chart.draw(data, options);
}

function graf_estrutura_organizacional() {

    var data = google.visualization.arrayToDataTable([
        ["", "", { role: "style" } ],
        ["Municípios da jurisdição", parseInt(document.getElementById("nMunicipioJurisdicao").innerText), vermelho],
        ["N° de EACs", parseInt(document.getElementById("nEACs").innerText), amarelo],
        ["Municípios Atendidos", parseInt(document.getElementById("nMunicipioAtendido").innerText), roxo],
        ["Postos de Fiscalização", parseInt(document.getElementById("nPostosFiscalizacao").innerText), verde],
    ]);

    var options = {
        //title: "Avaliação do Formulário: " ,
        width: 650,
        height: 400,
        bar: {groupWidth: "80%"},
        legend: { position: "none" },
    };

    var chart = new google.visualization.ColumnChart(document.getElementById("grafico_eo_estrutura"));
    chart.draw(data, options);
}