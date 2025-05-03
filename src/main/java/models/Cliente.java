package models;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    
    private static int id = 0;
    private String nome;
    private String cpf;
    private String telefone;
    private List<Locacao> locacoes;

    public Cliente(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.locacoes = new ArrayList<>();
        id++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public static int getId() {
        return id;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void addLocacao(Locacao locacao) {
        this.locacoes.add(locacao);
    }

    public void removeLocacao(Locacao locacao) {
        this.locacoes.remove(locacao);
    }
}
