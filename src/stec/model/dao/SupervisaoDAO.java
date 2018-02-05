package stec.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

import stec.model.database.SQLite;
import stec.model.domain.Eac;
import stec.model.domain.Formulario;
import stec.model.domain.Resposta;
import stec.model.domain.Supervisao;
import stec.model.domain.Ulsav;
import stec.model.domain.Ur;
import stec.model.domain.Usuario;
import stec.resources.Classes.AlertMaker;

public class SupervisaoDAO {

    SQLite handler;//Atributo que vai armazenar a instancia unica do BD

    public List<String> listEscritorios = new ArrayList<>();
    public List<String> opcoesSupervisao = new ArrayList<>();
    public List<String> opcoesAvaliacao = new ArrayList<>();
    public List<Formulario> listFormularios = new ArrayList<>();
    public List<Formulario> listProgramas = new ArrayList<>();
    
    public SupervisaoDAO() {
        handler = SQLite.getInstance();

        listEscritorios.add("EAC");
        listEscritorios.add("ULSAV");

        //opcoes que vao nos combobox dos formularios
        /*
        'NE'=>'NE - Nao existe',
        'ED'=>'ED - Existe, mas e deficiente',
        'EA'=>'EA - Existe e e adequado',
        'NA'=>'NA - Nao se aplica'
         */
        opcoesSupervisao.add("NE");
        opcoesSupervisao.add("ED");
        opcoesSupervisao.add("EA");
        opcoesSupervisao.add("NA");

        //opcoes da avaliacao
        opcoesAvaliacao.add("1");
        opcoesAvaliacao.add("2");
        opcoesAvaliacao.add("3");
        opcoesAvaliacao.add("4");
        opcoesAvaliacao.add("5");
        opcoesAvaliacao.add("NA");

        //lista dos formularios 
        listFormularios.add(new Formulario("identificacao_escritorio", "Identificação do Escritório"));
        listFormularios.add(new Formulario("rh_quantitativo", "Recursos Humanos - Quantitativo, jornada e distribuição"));
        listFormularios.add(new Formulario("rh_estabilidade", "Recursos Humanos - Estabilidade das estruturas e sustentabilidade das políticas sanitárias"));
        listFormularios.add(new Formulario("rh_capacitacao", "Recursos Humanos - Capacitação técnica e formação continuada"));
        listFormularios.add(new Formulario("rh_competencias", "Recursos Humanos - Competências e independência técnica"));
        listFormularios.add(new Formulario("rf_instalacoes", "Recursos Físicos - INSTALAÇÕES"));
        listFormularios.add(new Formulario("rf_transportes", "Recursos Físicos - TRANSPORTES"));
        listFormularios.add(new Formulario("rf_equipamentos", "Recursos Físicos - Equipamentos e acesso à comunicação"));
        listFormularios.add(new Formulario("rf_sistemas", "Recursos Físicos - Sistemas de informação"));
        listFormularios.add(new Formulario("rfi_custeio", "Recursos Financeiros - RECURSOS PARA CUSTEIO"));
        listFormularios.add(new Formulario("rfi_arrecadacao", "Recursos Financeiros - ARRECADAÇÃO"));
        listFormularios.add(new Formulario("eo_estrutura", "Estrutura Organizacional - Estrutura organizacional e capacidade de coordenação interna"));
        listFormularios.add(new Formulario("ag_base_legal", "Autoridade e Gestão da Qualidade - Base legal e aplicação da legislação, manuais e POPs"));
        listFormularios.add(new Formulario("ag_organizacao", "Autoridade e Gestão de Qualidade - Organização dos processos e unidades"));
        listFormularios.add(new Formulario("ag_supervisao", "Autoridade e Gestão de Qualidade - Supervisão e controle interno"));
        listFormularios.add(new Formulario("cto_controle_cadastro", "Capacidade Técnica e Operacional - Controle de cadastro de produtores, propriedades e animais"));
        listFormularios.add(new Formulario("cto_planejamento", "Capacidade Técnica e Operacional - Planejamento, execução de atividades e registro"));
        listFormularios.add(new Formulario("cto_controle", "Capacidade Técnica e Operacional - Controle de divisas e trânsito interno"));
        listFormularios.add(new Formulario("cto_transito_animais", "Capacidade Técnica e Operacional - Controle de trânsito de animais e produtos de origem animal"));
        listFormularios.add(new Formulario("cto_controle_eventos", "Capacidade Técnica e Operacional - Controle de eventos de aglomeração de animais"));
        listFormularios.add(new Formulario("cto_fiscalizacao", "Capacidade Técnica e Operacional - Fiscalização em revendas veterinárias"));
        listFormularios.add(new Formulario("cto_deteccao_precoce", "Capacidade Técnica e Operacional - Capacidade para detecção precoce e notificação imediata de doenças"));
        listFormularios.add(new Formulario("cto_atendimento_suspeita", "Capacidade Técnica e Operacional - Capacidade para atendimento a suspeitas e atuação em emergências"));
        listFormularios.add(new Formulario("interacao_comunidade_educacao", "INTERAÇÃO COM A COMUNIDADE: Educação sanitária e comunicação social (divulgação e publicidade)"));
        listFormularios.add(new Formulario("interacao_comunidade_comunidade", "INTERAÇÃO COM A COMUNIDADE: Participação com a comunidade"));
        listFormularios.add(new Formulario("interacao_comunidade_instituicoes", "INTERAÇÃO COM A COMUNIDADE: Participação com instituições e representações"));
        listFormularios.add(new Formulario("interacao_veterinario_habilitacao", "INTERAÇÃO COM OS MÉDICOS VETERINÁRIOS: Habilitação e cadastramento dos médicos veterinários"));
        listFormularios.add(new Formulario("interacao_instituicao_inspecao", "INTERAÇÃO COM INSTITUIÇÕES: Sistema de inspeção (seguridade alimentar)"));
        listFormularios.add(new Formulario("interacao_instituicao_sus", "INTERAÇÃO COM INSTITUIÇÕES: Sistema Único de Saúde (zoonoses, vigilância sanitária, etc.)"));
        listFormularios.add(new Formulario("acesso_mercado", "ACESSO AOS MERCADOS"));
        listFormularios.add(new Formulario("vulnerabilidade_potencialidade", "Vulnerabilidades e Potencialidades"));

    }

    public boolean inserir(Supervisao supervisao) {
        Timestamp hoje = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO supervisoes(usuario_id, ur_id, municipio_id, tipo_escritorio, programas, created, hash) VALUES (?, ?, ?, ?, ?, datetime(CURRENT_TIMESTAMP, 'localtime'), ?)";

        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);

            stmt.setInt(1, supervisao.getUsuario().getId());
            stmt.setInt(2, supervisao.getUr().getId());

            if (supervisao.getTipoEscritorio().equals("EAC")) {
                stmt.setInt(3, supervisao.getEac().getId());
            } else {
                stmt.setInt(3, supervisao.getUlsav().getId());
            }

            stmt.setString(4, supervisao.getTipoEscritorio());
            stmt.setString(5, supervisao.getProgramas().toString());
            stmt.setString(6, DigestUtils.sha1Hex(hoje.toString()));
            stmt.execute();
            return true;
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Erro ao executar ação SQL", sql);
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Supervisao> listar() {

        String sql = "SELECT * FROM supervisoes";//lista todas as supervisoes do usuario
        List<Supervisao> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Supervisao supervisao = new Supervisao();
                Usuario usuario = new Usuario();
                Ur ur = new Ur();
                Ulsav ulsav = new Ulsav();
                Eac eac = new Eac();
                List<String> programas = new ArrayList<>();
                String[] parts;

                supervisao.setId(resultado.getInt("id"));

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuario.setId(resultado.getInt("usuario_id"));
                supervisao.setUsuario(usuarioDAO.buscar(usuario));

                UrDAO urDAO = new UrDAO();
                ur.setId(resultado.getInt("ur_id"));
                supervisao.setUr(urDAO.buscar(ur));

                supervisao.setTipoEscritorio(resultado.getString("tipo_escritorio"));

                if (resultado.getString("tipo_escritorio").equals("EAC")) {//se o tipo de escritorio supervisionado for eac instancia e busca sua respectiva informação
                    EacDAO eacDAO = new EacDAO();
                    eac.setId(resultado.getInt("municipio_id"));
                    supervisao.setEac(eacDAO.buscar(eac));
                    supervisao.setEscritorio(resultado.getString("tipo_escritorio") + " / " + supervisao.getEac().getNome());
                } else {
                    UlsavDAO ulsavDAO = new UlsavDAO();
                    ulsav.setId(resultado.getInt("municipio_id"));
                    supervisao.setUlsav(ulsavDAO.buscar(ulsav));
                    supervisao.setEscritorio(resultado.getString("tipo_escritorio") + " / " + supervisao.getUlsav().getNome());
                }

                parts = resultado.getString("programas").replaceAll("[\\[\\]]", "").split(",");//Retira os [] da string e quebra para que cada programa tenha um valor no array

                programas.addAll(Arrays.asList(parts));//adiciona os valores do vetor na List

                supervisao.setProgramas(programas);
                supervisao.setStatus(resultado.getBoolean("status"));
                supervisao.setCreated(resultado.getString("created"));
                supervisao.setHashAuditoria(resultado.getString("hash"));
                retorno.add(supervisao);
            }
        } catch (SQLException e) {
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return retorno;
    }

    public List<Supervisao> listar(Usuario usuario) {

        String sql = "SELECT * FROM supervisoes WHERE usuario_id = ?";//lista todas as supervisoes do usuario
        List<Supervisao> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Supervisao supervisao = new Supervisao();
                Ur ur = new Ur();
                Ulsav ulsav = new Ulsav();
                Eac eac = new Eac();
                List<String> programas = new ArrayList<>();
                String[] parts;

                supervisao.setId(resultado.getInt("id"));
                supervisao.setUsuario(usuario);

                UrDAO urDAO = new UrDAO();
                ur.setId(resultado.getInt("ur_id"));
                supervisao.setUr(urDAO.buscar(ur));

                supervisao.setTipoEscritorio(resultado.getString("tipo_escritorio"));

                if (resultado.getString("tipo_escritorio").equals("EAC")) {//se o tipo de escritorio supervisionado for eac instancia e busca sua respectiva informação
                    EacDAO eacDAO = new EacDAO();
                    eac.setId(resultado.getInt("municipio_id"));
                    supervisao.setEac(eacDAO.buscar(eac));
                    supervisao.setEscritorio(resultado.getString("tipo_escritorio") + " / " + supervisao.getEac().getNome());
                } else {
                    UlsavDAO ulsavDAO = new UlsavDAO();
                    ulsav.setId(resultado.getInt("municipio_id"));
                    supervisao.setUlsav(ulsavDAO.buscar(ulsav));
                    supervisao.setEscritorio(resultado.getString("tipo_escritorio") + " / " + supervisao.getUlsav().getNome());
                }

                parts = resultado.getString("programas").replaceAll("[\\[\\]]", "").split(",");//Retira os [] da string e quebra para que cada programa tenha um valor no array

                programas.addAll(Arrays.asList(parts));//adiciona os valores do vetor na List

                supervisao.setProgramas(programas);
                supervisao.setStatus(resultado.getBoolean("status"));
                supervisao.setCreated(resultado.getString("created"));
                supervisao.setHashAuditoria(resultado.getString("hash"));
                retorno.add(supervisao);
            }
        } catch (SQLException e) {
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return retorno;
    }
    
    public List<Supervisao> listarImportadas() {

        String sql = "SELECT * FROM import_supervisoes";//lista todas as supervisoes do usuario
        List<Supervisao> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Supervisao supervisao = new Supervisao();
                Usuario usuario = new Usuario();
                Ur ur = new Ur();
                Ulsav ulsav = new Ulsav();
                Eac eac = new Eac();
                List<String> programas = new ArrayList<>();
                String[] parts;

                supervisao.setId(resultado.getInt("id"));

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuario.setId(resultado.getInt("usuario_id"));
                supervisao.setUsuario(usuarioDAO.buscar(usuario));

                UrDAO urDAO = new UrDAO();
                ur.setId(resultado.getInt("ur_id"));
                supervisao.setUr(urDAO.buscar(ur));

                supervisao.setTipoEscritorio(resultado.getString("tipo_escritorio"));

                if (resultado.getString("tipo_escritorio").equals("EAC")) {//se o tipo de escritorio supervisionado for eac instancia e busca sua respectiva informação
                    EacDAO eacDAO = new EacDAO();
                    eac.setId(resultado.getInt("municipio_id"));
                    supervisao.setEac(eacDAO.buscar(eac));
                    supervisao.setEscritorio(resultado.getString("tipo_escritorio") + " / " + supervisao.getEac().getNome());
                } else {
                    UlsavDAO ulsavDAO = new UlsavDAO();
                    ulsav.setId(resultado.getInt("municipio_id"));
                    supervisao.setUlsav(ulsavDAO.buscar(ulsav));
                    supervisao.setEscritorio(resultado.getString("tipo_escritorio") + " / " + supervisao.getUlsav().getNome());
                }

                parts = resultado.getString("programas").replaceAll("[\\[\\]]", "").split(",");//Retira os [] da string e quebra para que cada programa tenha um valor no array

                programas.addAll(Arrays.asList(parts));//adiciona os valores do vetor na List

                supervisao.setProgramas(programas);
                supervisao.setCreated(resultado.getString("created"));
                supervisao.setHashAuditoria(resultado.getString("hash"));
                retorno.add(supervisao);
            }
        } catch (SQLException e) {
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return retorno;
    }
    
    public boolean excluir(Supervisao supervisao) {
        String sqlDeletarSupervisao = "DELETE FROM supervisoes WHERE id = ?";
        String sqlDeletarRespostas = "DELETE FROM respostas WHERE supervisao_id = ?";
        String sqlQtdRespostas = "SELECT COUNT(*) AS qtd_respostas FROM respostas WHERE supervisao_id = ?";
        int qtdRespostas = 0;
        int rowsAffected = 0;

        try {
            handler.getConnection().setAutoCommit(false);//desabilita o auto commit

            PreparedStatement stmt = handler.getConnection().prepareStatement(sqlQtdRespostas);//Busca a quantidade de respostas da supervisao
            stmt.setInt(1, supervisao.getId());
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                qtdRespostas = resultado.getInt("qtd_respostas");//pega a quantidade de respostas
            }

            if (qtdRespostas > 0) {//se a supervisao tiver respostas
                PreparedStatement stmt2 = handler.getConnection().prepareStatement(sqlDeletarRespostas);//Deleta as respostas da supervisao
                stmt2.setInt(1, supervisao.getId());
                rowsAffected = stmt2.executeUpdate();//recebe a quantidade de linhas que foram excluidas

                if (rowsAffected != qtdRespostas) {//se as linhas afetadas forem diferentes da quantidade de respostas que a supervisao tinha
                    handler.getConnection().rollback();//retorna o estado anterior
                    AlertMaker.showErrorMessage("Erro ao executar ação SQL", "Não foi possível apagar as respostas desta supervisão");
                    return false;
                }
            }

            PreparedStatement stmt3 = handler.getConnection().prepareStatement(sqlDeletarSupervisao);//Deleta a supervisao
            stmt3.setInt(1, supervisao.getId());
            stmt3.executeUpdate();

            handler.getConnection().commit();//faz commit caso algo de errado tenha acontecido

            return true;
        } catch (SQLException e) {
            try {
                handler.getConnection().rollback();//retorna o estado anterior
            } catch (Exception e2) {
                AlertMaker.showErrorMessage(e2, "Erro ao executar ação SQL", sqlDeletarSupervisao);
            }
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean finalizar(Supervisao supervisao) {
        String sql = "UPDATE supervisoes SET status = 1 WHERE id = ?";
        String message = "";

        for (Formulario f : listFormularios) {//para cara formulario
            Resposta resposta = new Resposta();
            RespostaDAO respostaDAO = new RespostaDAO();

            resposta.setSupervisao(supervisao);//passa a supervisao
            resposta.setFormulario(f);//passa o formulario a ser verificado

            if (respostaDAO.temResposta(resposta) == false)//se o formulario nao tem resposta
            {
                message += f.getNome() + "\n";//coloca o titulo do formulario para mostrar na mensagem
            }
        }

        //verifica se tem o programa, caso tenha pesquisar se tem resposta cadastrada
        if (supervisao.getProgramas().toString().contains("PNEFA")) {
            listProgramas.add(new Formulario("pnefa_fiscalizacoes", "PNEFA - FISCALIZAÇÕES DE VACINAÇÕES"));
            listProgramas.add(new Formulario("pnefa_vigilancia", "PNEFA - VIGILÂNCIA EPIDEMIOLÓGICA"));
        }

        if (supervisao.getProgramas().toString().contains("PNCEBT")) {
            listProgramas.add(new Formulario("pncebt_fiscalizacoes", "PNCEBT - FISCALIZAÇÕES DE VACINAÇÕES"));
            listProgramas.add(new Formulario("pncebt_vigilancia", "PNCEBT - VIGILÂNCIA EPIDEMIOLÓGICA"));
            listProgramas.add(new Formulario("pncebt_controles", "PNCEBT - CONTROLES DO PROGRAMA"));
        }

        if (supervisao.getProgramas().toString().contains("PNCRH")) {
            listProgramas.add(new Formulario("pncrh_fiscalizacoes", "PNCRH - FISCALIZAÇÕES DE VACINAÇÕES"));
            listProgramas.add(new Formulario("pncrh_vigilancia", "PNCRH - VIGILÂNCIA EPIDEMIOLÓGICA"));
        }

        if (supervisao.getProgramas().toString().contains("PNEEB")) {
            listProgramas.add(new Formulario("pneeb_vigilancia", "PNEEB - VIGILÂNCIA EPIDEMIOLÓGICA"));
        }

        if (supervisao.getProgramas().toString().contains("PNSA")) {
            listProgramas.add(new Formulario("pnsa_cadastro_estabelecimentos", "PNSA - CADASTRO DE ESTABELECIMENTOS"));
            listProgramas.add(new Formulario("pnsa_vigilancia", "PNSA - VIGILÂNCIA EPIDEMIOLÓGICA"));
        }

        if (supervisao.getProgramas().toString().contains("PNSCO")) {
            listProgramas.add(new Formulario("pnsco_cadastro_estabelecimentos", "PNSCO - CADASTRO DE ESTABELECIMENTOS"));
            listProgramas.add(new Formulario("pnsco_vigilancia", "PNSCO - VIGILÂNCIA EPIDEMIOLÓGICA"));
        }

        if (supervisao.getProgramas().toString().contains("PNSE")) {
            listProgramas.add(new Formulario("pnse_cadastro_estabelecimentos", "PNSE - CADASTRO DE ESTABELECIMENTOS"));
            listProgramas.add(new Formulario("pnse_vigilancia", "PNSE - VIGILÂNCIA EPIDEMIOLÓGICA"));
            listProgramas.add(new Formulario("pnse_controles", "PNSE - CONTROLES DO PROGRAMA"));
        }

        if (supervisao.getProgramas().toString().contains("PNSS")) {
            listProgramas.add(new Formulario("pnss_cadastro_estabelecimentos", "PNSS - CADASTRO DE ESTABELECIMENTOS"));
            listProgramas.add(new Formulario("pnss_vigilancia", "PNSS - VIGILÂNCIA EPIDEMIOLÓGICA"));
        }

        for (Formulario f : listProgramas) {//para cara programa
            Resposta resposta = new Resposta();
            RespostaDAO respostaDAO = new RespostaDAO();

            resposta.setSupervisao(supervisao);//passa a supervisao
            resposta.setFormulario(f);//passa o formulario a ser verificado

            if (respostaDAO.temResposta(resposta) == false)//se o formulario nao tem resposta
            {
                message += f.getNome() + "\n";//coloca o titulo do formulario para mostrar na mensagem
            }
        }

        if (message.length() == 0) {//caso todos os formulario tenham resposta
            try {
                PreparedStatement stmt = handler.getConnection().prepareStatement(sql);//Busca a quantidade de respostas da supervisao
                stmt.setInt(1, supervisao.getId());
                stmt.executeUpdate();
                AlertMaker.showSimpleAlert("Aviso", "Supervisão finalizada com sucesso");
                return true;
            } catch (SQLException e) {
                AlertMaker.showErrorMessage(e, "Erro ao executar ação SQL", sql);
                Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }
        } else {
            AlertMaker.showErrorMessage("Para finalizar a supervisão é necessário preencher os formulários abaixo:", message);
            return false;
        }
    }
    
    public Supervisao buscarPorMunicipio(Supervisao supervisao) {
        String sql = "SELECT * FROM import_supervisoes WHERE municipio_id = ? AND tipo_escritorio = ?";
        Supervisao retorno = new Supervisao();
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            
            if (supervisao.getTipoEscritorio().equals("EAC"))
                stmt.setInt(1, supervisao.getEac().getId());
            else
                stmt.setInt(1, supervisao.getUlsav().getId());
            
            stmt.setString(2, supervisao.getTipoEscritorio());
            
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                Usuario usuario = new Usuario();
                Ur ur = new Ur();
                Ulsav ulsav = new Ulsav();
                Eac eac = new Eac();
                List<String> programas = new ArrayList<>();
                String[] parts;

                supervisao.setId(resultado.getInt("id"));

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuario.setId(resultado.getInt("usuario_id"));
                supervisao.setUsuario(usuarioDAO.buscar(usuario));

                UrDAO urDAO = new UrDAO();
                ur.setId(resultado.getInt("ur_id"));
                supervisao.setUr(urDAO.buscar(ur));

                if (resultado.getString("tipo_escritorio").equals("EAC")) {//se o tipo de escritorio supervisionado for eac instancia e busca sua respectiva informação
                    EacDAO eacDAO = new EacDAO();
                    eac.setId(resultado.getInt("municipio_id"));
                    supervisao.setEac(eacDAO.buscar(eac));
                    supervisao.setEscritorio(resultado.getString("tipo_escritorio") + " / " + supervisao.getEac().getNome());
                } else {
                    UlsavDAO ulsavDAO = new UlsavDAO();
                    ulsav.setId(resultado.getInt("municipio_id"));
                    supervisao.setUlsav(ulsavDAO.buscar(ulsav));
                    supervisao.setEscritorio(resultado.getString("tipo_escritorio") + " / " + supervisao.getUlsav().getNome());
                }

                parts = resultado.getString("programas").replaceAll("[\\[\\]]", "").split(",");//Retira os [] da string e quebra para que cada programa tenha um valor no array

                programas.addAll(Arrays.asList(parts));//adiciona os valores do vetor na List

                supervisao.setProgramas(programas);
                supervisao.setCreated(resultado.getString("created"));
                supervisao.setHashAuditoria(resultado.getString("hash"));
                
                retorno = supervisao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
}
