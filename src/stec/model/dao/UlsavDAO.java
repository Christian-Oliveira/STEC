package stec.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import stec.model.database.SQLite;
import stec.model.domain.Ulsav;
import stec.model.domain.Ur;

public class UlsavDAO {
    
     //Atributo que vai armazenar a instancia unica do BD
    SQLite handler;

    public UlsavDAO() {
        handler = SQLite.getInstance();
    }
    
    public List<Ulsav> listar() {
        String sql = "SELECT * FROM ulsavs";
        List<Ulsav> listUlsavs = new ArrayList<>();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Ulsav ulsav = new Ulsav();
                Ur ur = new Ur();
                
                ulsav.setId(resultado.getInt("id"));
                ulsav.setNome(resultado.getString("nome"));
                ur.setId(resultado.getInt("ur_id"));
                
                //Obtem os dados completos da Ur
                UrDAO urDAO = new UrDAO();
                urDAO.buscar(ur);
                
                ulsav.setUr(ur);
                                
                listUlsavs.add(ulsav);
            }
        } catch (SQLException e) {
            Logger.getLogger(UrDAO.class.getName()).log(Level.SEVERE, null, e);
        }        
        
        return listUlsavs;
    }
    
    public List<Ulsav> listarPorUr(int urId) {
        String sql = "SELECT * FROM ulsavs WHERE ur_id = " + String.valueOf(urId);
        List<Ulsav> listUlsavs = new ArrayList<>();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Ulsav ulsav = new Ulsav();
                Ur ur = new Ur();
                
                ulsav.setId(resultado.getInt("id"));
                ulsav.setNome(resultado.getString("nome"));
                ur.setId(resultado.getInt("ur_id"));
                
                //Obtem os dados completos da Ur
                UrDAO urDAO = new UrDAO();
                urDAO.buscar(ur);
                
                ulsav.setUr(ur);
                                
                listUlsavs.add(ulsav);
            }
        } catch (SQLException e) {
            Logger.getLogger(UrDAO.class.getName()).log(Level.SEVERE, null, e);
        }        
        
        return listUlsavs;
    }
    
    public Ulsav buscar(Ulsav ulsav) {
        String sql = "SELECT * FROM ulsavs WHERE id = ?";
        Ulsav retorno = new Ulsav();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            stmt.setInt(1, ulsav.getId());
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                Ur ur = new Ur();
                
                ulsav.setNome(resultado.getString("nome"));
                ur.setId(resultado.getInt("ur_id"));
                
                //Obtem os dados completos da Ur
                UrDAO urDAO = new UrDAO();
                urDAO.buscar(ur);
                
                ulsav.setUr(ur);
                
                retorno = ulsav;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UrDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
}
