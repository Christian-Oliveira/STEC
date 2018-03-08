package stec.model.domain;

public class Resposta {
    
    private int id;
    private Formulario formulario;
    private Supervisao supervisao;
    private String resposta;

    public Resposta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Supervisao getSupervisao() {
        return supervisao;
    }

    public void setSupervisao(Supervisao supervisao) {
        this.supervisao = supervisao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
}
