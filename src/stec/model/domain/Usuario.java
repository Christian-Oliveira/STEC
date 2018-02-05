package stec.model.domain;

import org.apache.commons.codec.digest.DigestUtils;

public class Usuario {
    
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Usuario() {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = DigestUtils.sha1Hex(senha);
    }
    
    
}
