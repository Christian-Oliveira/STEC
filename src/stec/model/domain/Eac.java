package stec.model.domain;

public class Eac {
    
    private int id;
    private String nome;
    private Ulsav ulsav;
    
    public Eac() {
    }

    public Eac(int id, String nome) {
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

    public Ulsav getUlsav() {
        return ulsav;
    }

    public void setUlsav(Ulsav ulsav) {
        this.ulsav = ulsav;
    }

}
