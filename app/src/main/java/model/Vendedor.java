package model;

/**
 * Created by privale on 03/10/17.
 */

public class Vendedor {
    private String id;
    private String nome;
    private String telefone;
    private String status;
    private String local;
    private String produto;
    private String descricao;

    public Vendedor() {
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + "\n" + "Produto: " + produto + "\n" +  "Local: " + getLocal()
                + "\n" +   "Telefone: " + getTelefone() + "\n" +   "Descrição: " + getDescricao();
    }
}
