package stec.model.domain;

public class Formulario {
    
    private String slug;
    private String nome;

    public Formulario() {
    }
    
    public Formulario(String slug, String nome) {
    	this.slug = slug;
    	this.nome = nome;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
