package stec.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import stec.model.database.SQLite;
import stec.model.domain.Usuario;

public class UsuarioDAO {
    
    //Atributo que vai armazenar a instancia unica do BD
    SQLite handler;

    public UsuarioDAO() {
        handler = SQLite.getInstance();
    }
    
    public Usuario buscar(Usuario usuario) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario retorno = new Usuario();
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                usuario.setNome(resultado.getString("nome"));
                retorno = usuario;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    
    public Boolean login(Usuario usuario) {
        
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                usuario.setId(resultado.getInt("id"));
                usuario.setNome(resultado.getString("nome"));
                return true;           
            }
            
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return false;
    }
}
