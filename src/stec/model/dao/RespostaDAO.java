package stec.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import stec.model.database.SQLite;
import stec.model.domain.Formulario;
import stec.model.domain.Resposta;
import stec.model.domain.Supervisao;
import stec.resources.Classes.AlertMaker;

public class RespostaDAO {
        
    SQLite handler;//Atributo que vai armazenar a instancia unica do BD

    public RespostaDAO() {
        handler = SQLite.getInstance();
    }
    
    public boolean inserir(Resposta resposta) {

        String sql = "INSERT INTO respostas(supervisao_id, slug_formulario, resposta, supervisao_hash) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            
            stmt.setInt(1, resposta.getSupervisao().getId());        
            stmt.setString(2, resposta.getFormulario().getSlug());
            stmt.setString(3, resposta.getResposta());
            stmt.setString(4, resposta.getSupervisao().getHashAuditoria());
            stmt.execute();
            AlertMaker.showSimpleAlert("Info", "Resposta adicionada com sucesso");
            return true;
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Erro ao executar ação SQL", sql);
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public boolean alterar(Resposta resposta) {

        String sql = "UPDATE respostas SET resposta = ? WHERE id = ?";
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            
            stmt.setString(1, resposta.getResposta());
            stmt.setInt(2, resposta.getId());
            stmt.execute();
            AlertMaker.showSimpleAlert("Info", "Resposta alterada com sucesso");
            return true;
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Erro ao executar ação SQL", sql);
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public Resposta buscar(Resposta resposta) {
        String sql = "SELECT * FROM respostas WHERE id = ?";
        Resposta retorno = new Resposta();
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            stmt.setInt(1, resposta.getId());
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                resposta.setResposta(resultado.getString("resposta"));
                retorno = resposta;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    
    public boolean temResposta(Resposta resposta) {
        String sql = "SELECT * FROM respostas WHERE supervisao_id = ? AND slug_formulario = ?";
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            
            stmt.setInt(1, resposta.getSupervisao().getId());
            stmt.setString(2, resposta.getFormulario().getSlug());
            
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                resposta.setId(resultado.getInt("id"));
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public HashMap<String, Resposta> listarRespostasDaSupervisao(Supervisao supervisao) {
    	String sql = "SELECT * FROM respostas WHERE supervisao_id = ?";//lista todas as respostas da supervisao
        HashMap<String, Resposta> retorno = new HashMap<>();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            stmt.setInt(1, supervisao.getId());
            ResultSet resultado = stmt.executeQuery();
            
            while(resultado.next()) {
                Resposta resposta = new Resposta();
                Formulario formulario = new Formulario();
                
                formulario.setSlug(resultado.getString("slug_formulario"));
                resposta.setFormulario(formulario);
                
            	resposta.setResposta(resultado.getString("resposta"));
                
                retorno.put(resultado.getString("slug_formulario"), resposta);
            }
        } catch (SQLException e) {
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return retorno;
    }
    
    public HashMap<String, Resposta> listarRespostasDaSupervisaoImportada(Supervisao supervisao) {
    	String sql = "SELECT * FROM import_respostas WHERE supervisao_id = ?";//lista todas as respostas da supervisao
        HashMap<String, Resposta> retorno = new HashMap<>();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            stmt.setInt(1, supervisao.getId());
            ResultSet resultado = stmt.executeQuery();
            
            while(resultado.next()) {
                Resposta resposta = new Resposta();
                Formulario formulario = new Formulario();
                
                formulario.setSlug(resultado.getString("slug_formulario"));
                resposta.setFormulario(formulario);
                
            	resposta.setResposta(resultado.getString("resposta"));
                
                retorno.put(resultado.getString("slug_formulario"), resposta);
            }
        } catch (SQLException e) {
            Logger.getLogger(SupervisaoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return retorno;
    }

}
