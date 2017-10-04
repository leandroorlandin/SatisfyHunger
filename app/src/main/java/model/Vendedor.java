package model;

/**
 * Created by privale on 03/10/17.
 */

public class Vendedor extends Pessoa {
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
}
