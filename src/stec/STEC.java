package stec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stec.model.database.SQLite;

public class STEC extends Application {
    
    //metodo que inicializa a aplicação
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/stec/view/login/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();        
        
        //Cria uma thread que gera a conexao com o bd, apos isso sera utilizado apenas a mesma instancia
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLite.getInstance();
            }
        }).start();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
