package stec.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import stec.resources.Classes.AlertMaker;

public final class SQLite {
    
    private static SQLite handler;
    private static final String DB_URL = "jdbc:sqlite:stec.db";
    private static Connection connection;

    private SQLite() {
        conectar();
    }
    
    //Verifica se a instancia ja existe, se existir retorna a mesma, senao cria uma nova
    public static SQLite getInstance() {
        if (handler == null)
            handler = new SQLite();
        return handler;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    //metodo para fazer a conexão com banco de dados
    public void conectar() {
        try {
            if (connection == null) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Conexão realizada com sucesso!");
            }
        } catch (Exception e) {
            AlertMaker.showErrorMessage(e, "Erro ao conectar com o Banco de dados!", "");
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
