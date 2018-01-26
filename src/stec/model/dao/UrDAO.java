package stec.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import stec.model.database.SQLite;
import stec.model.domain.Ur;

public class UrDAO {
    
     //Atributo que vai armazenar a instancia unica do BD
    SQLite handler;

    public UrDAO() {
        handler = SQLite.getInstance();
    }
    
    public List<Ur> listar() {
        String sql = "SELECT * FROM urs";
        List<Ur> listUrs = new ArrayList<>();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Ur ur = new Ur();
                ur.setId(resultado.getInt("id"));
                ur.setNome(resultado.getString("nome"));
                                
                listUrs.add(ur);
            }
        } catch (SQLException e) {
            Logger.getLogger(UrDAO.class.getName()).log(Level.SEVERE, null, e);
        }        
        
        return listUrs;
    }
    
    public Ur buscar(Ur ur) {
        String sql = "SELECT * FROM urs WHERE id = ?";
        Ur retorno = new Ur();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            stmt.setInt(1, ur.getId());
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                ur.setNome(resultado.getString("nome"));
                retorno = ur;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UrDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
}
