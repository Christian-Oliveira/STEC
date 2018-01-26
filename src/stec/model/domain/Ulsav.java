package stec.model.domain;

public class Ulsav {
    
    private int id;
    private String nome;
    private Ur ur;

    public Ulsav() {
    }

    public Ulsav(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Ur getUr() {
        return ur;
    }

    public void setUr(Ur ur) {
        this.ur = ur;
    }
    
}
