package stec.model.domain;

import java.util.HashMap;
import java.util.List;

public class Supervisao {
    
    private int id;
    private Usuario usuario;
    private boolean status;
    private String created;
    private String tipoEscritorio;
    private List<String> programas;
    private String hashAuditoria;
    private Ur ur;
    private Eac eac;
    private Ulsav ulsav;
    private String escritorio;//juncao do tipo com o nome do municipio
    private HashMap<String, Resposta> hashRespostas = new HashMap<>();
    
    
    public Supervisao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<String> getProgramas() {
        return programas;
    }

    public void setProgramas(List<String> programas) {
        this.programas = programas;
    }

    public String getHashAuditoria() {
        return hashAuditoria;
    }

    public void setHashAuditoria(String hashAuditoria) {
        this.hashAuditoria = hashAuditoria;
    }

    public Ur getUr() {
        return ur;
    }

    public void setUr(Ur ur) {
        this.ur = ur;
    }

    public Eac getEac() {
        return eac;
    }

    public void setEac(Eac eac) {
        this.eac = eac;
    }

    public Ulsav getUlsav() {
        return ulsav;
    }

    public void setUlsav(Ulsav ulsav) {
        this.ulsav = ulsav;
    }

    public String getTipoEscritorio() {
        return tipoEscritorio;
    }

    public void setTipoEscritorio(String tipoEscritorio) {
        this.tipoEscritorio = tipoEscritorio;
    }

    public String getEscritorio() {
        return escritorio;
    }

    public void setEscritorio(String escritorio) {
        this.escritorio = escritorio;
    }

    public HashMap<String, Resposta> getHashRespostas() {
        return hashRespostas;
    }

    public void setHashRespostas(HashMap<String, Resposta> hashRespostas) {
        this.hashRespostas = hashRespostas;
    }
    
}
