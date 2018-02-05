package stec.resources.Classes;

import javafx.stage.Stage;
import stec.model.domain.Resposta;

public interface IFormulario {
	
	void setDialog(Stage dialog);
	Stage getDialog();
	
	void setResposta(Resposta resposta);
	Resposta getResposta();
	
	boolean isBtConfirmarClicked();
	void setBtConfirmarClicked(boolean btConfirmarClicked);
        
        boolean validarDados();
}
