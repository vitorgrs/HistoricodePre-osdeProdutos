package Controller;
import Model.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Cadastrar {
    private ArrayList<Produto> produtos;
    private ArrayList<Categoria> categorias;
    private ArrayList<Sites> sites;

    public Cadastrar() {
        this.produtos = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.sites = new ArrayList<>();
    }
    public void cadastrarProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a descrição do produto:");
        String descricao = scanner.nextLine();
        ArrayList<Categoria> categoriasProduto = new ArrayList<>();
        Hashtable<Sites, ArrayList<Preco_Data>> dadosProduto = null;
        while (true) {
            System.out.println("Digite o nome da categoria do produto (ou digite 'sair' para sair):");
            String nomeCategoria = scanner.nextLine();
            if (nomeCategoria.equals("sair")) {
                break;
            }
            Categoria categoria = new Categoria(nomeCategoria);
            categorias.add(categoria);
            categoriasProduto.add(categoria);
            dadosProduto = new Hashtable<>();
            System.out.println("Digite o nome do site do produto:");
            String nomeSite = scanner.nextLine();
            Sites site = new Sites(nomeSite);
            sites.add(site);
            ArrayList<Preco_Data> precos = new ArrayList<>();

            System.out.println("Digite o preço do produto no site " + nomeSite + " (ou digite 'sair' para sair):");
            String precoString = scanner.nextLine();
            if (precoString.equals("sair")) {
                break;
            }
            double preco = Double.parseDouble(precoString);
            System.out.println("Digite a data do preço no formato dd/mm/aaaa:");
            String dataString = scanner.nextLine();
            Preco_Data precoData = new Preco_Data(dataString, preco);

            precos.add(precoData);

            dadosProduto.put(site, precos);
            break;
        }
        Produto produto = new Produto(descricao, categoriasProduto, dadosProduto);
        produtos.add(produto);

        System.out.println("Produto cadastrado");

    }
    public void informarPrecoProduto(String descricaoProduto) {
        Scanner scanner = new Scanner(System.in);
        boolean produtoEncontrado = false;

        for (Produto produto : produtos) {
            if (produto.getDescricao().equalsIgnoreCase(descricaoProduto)) {
                produtoEncontrado = true;

                System.out.println("Produto encontrado:");
                System.out.println("Descrição: " + produto.getDescricao());
                ArrayList<Categoria> categorias = produto.getCategorias();

                for (Categoria categoria : categorias) {
                    System.out.println("Categorias: " + categoria.getNome());
                }

                Hashtable<Sites, ArrayList<Preco_Data>> dados = produto.getDados();
                for (Sites site : dados.keySet()) {
                    ArrayList<Preco_Data> precos = dados.get(site);
                    for (Preco_Data precoData : precos) {
                        System.out.println("Site: " + site.getNome());
                        System.out.println("Preço: " + precoData.getPreco());
                        System.out.println("Data: " + precoData.getData());
                    }
                }

                System.out.println();
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Produto não encontrado.");
        }
    }


    public void exibirProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println("Produto: " + produto.getDescricao());
                ArrayList<Categoria> categorias = produto.getCategorias();


                for (Categoria categoria : categorias) {
                    System.out.println("Categorias: " + categoria.getNome());
                }

                Hashtable<Sites, ArrayList<Preco_Data>> dados = produto.getDados();
                for (Sites site : dados.keySet()) {
                    ArrayList<Preco_Data> precos = dados.get(site);
                    for (Preco_Data precoData : precos) {
                        System.out.println("Site: " + site.getNome());
                        System.out.println("Preço: " + precoData.getPreco());
                        System.out.println("Data: " + precoData.getData());
                    }
                }
                System.out.println();
            }
        }
    }

}
