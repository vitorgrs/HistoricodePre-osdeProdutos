package Model;
import java.util.ArrayList;
import java.util.Hashtable;

public class Produto {
    private String descricao;
    private ArrayList<Categoria> categorias;
    private Hashtable<Sites, ArrayList<Preco_Data>> dados;

    public Produto(String descricao, ArrayList<Categoria> categorias, Hashtable<Sites, ArrayList<Preco_Data>> dados) {
        this.descricao = descricao;
        this.categorias = categorias;
        this.dados = dados;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Hashtable<Sites, ArrayList<Preco_Data>> getDados() {
        return dados;
    }

    public void setDados(Hashtable<Sites, ArrayList<Preco_Data>> dados) {
        this.dados = dados;
    }
    // Dentro da classe Produto
    public void adicionarPreco(Sites site, Preco_Data precoData) {
        if (dados.containsKey(site)) {
            ArrayList<Preco_Data> precos = dados.get(site);
            precos.add(precoData);
        } else {
            ArrayList<Preco_Data> precos = new ArrayList<>();
            precos.add(precoData);
            dados.put(site, precos);
        }
    }



}
