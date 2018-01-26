package stec.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import stec.model.database.SQLite;
import stec.model.domain.Eac;
import stec.model.domain.Ulsav;

public class EacDAO {
    
    //Atributo que vai armazenar a instancia unica do BD
    SQLite handler;

    public EacDAO() {
        handler = SQLite.getInstance();
    }
        
    public List<Eac> listar() {
        String sql = "SELECT * FROM eacs";
        List<Eac> listEacs = new ArrayList<>();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Eac eac = new Eac();
                Ulsav ulsav = new Ulsav();
                
                eac.setId(resultado.getInt("id"));
                eac.setNome(resultado.getString("nome"));
                ulsav.setId(resultado.getInt("ulsav_id"));
                
                //Obtem os dados completos da Ulsav, por consequencia da Ur
                UlsavDAO ulsavDAO = new UlsavDAO();
                ulsavDAO.buscar(ulsav);
                
                eac.setUlsav(ulsav);
                                
                listEacs.add(eac);
            }
        } catch (SQLException e) {
            Logger.getLogger(UrDAO.class.getName()).log(Level.SEVERE, null, e);
        }        
        
        return listEacs;
    }
    
    public List<Eac> listarPor(String localidade, int id) {
        String parametro = null;
        
        if (localidade.equals("UR"))
            parametro = "ur_id";
        else
            parametro = "ulsav_id";
        
        String sql = "SELECT * FROM eacs WHERE " + parametro + " = " + String.valueOf(id) ;
        List<Eac> listEacs = new ArrayList<>();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Eac eac = new Eac();
                Ulsav ulsav = new Ulsav();
                
                eac.setId(resultado.getInt("id"));
                eac.setNome(resultado.getString("nome"));
                ulsav.setId(resultado.getInt("ulsav_id"));
                
                //Obtem os dados completos da Ulsav, por consequencia da Ur
                UlsavDAO ulsavDAO = new UlsavDAO();
                ulsavDAO.buscar(ulsav);
                
                eac.setUlsav(ulsav);
                                
                listEacs.add(eac);
            }
        } catch (SQLException e) {
            Logger.getLogger(UrDAO.class.getName()).log(Level.SEVERE, null, e);
        }        
        
        return listEacs;
    }
    
    public Eac buscar(Eac eac) {
        String sql = "SELECT * FROM eacs WHERE id = ?";
        Eac retorno = new Eac();
        
        try {
            PreparedStatement stmt = handler.getConnection().prepareStatement(sql);
            stmt.setInt(1, eac.getId());
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                Ulsav ulsav = new Ulsav();
                
                eac.setNome(resultado.getString("nome"));
                ulsav.setId(resultado.getInt("ulsav_id"));
                
                //Obtem os dados completos da Ur
                UlsavDAO ulsavDAO = new UlsavDAO();
                ulsavDAO.buscar(ulsav);
                
                eac.setUlsav(ulsav);
                
                retorno = eac;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UrDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
}
