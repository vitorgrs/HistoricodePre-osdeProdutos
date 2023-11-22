package Controller;
import Model.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Arrays;


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
    public void cadastrarProdutoFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("produtos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dadosProduto = line.split(";");

                if (dadosProduto.length >= 3) {
                    String descricao = dadosProduto[0].trim();
                    String nomeCategoria = dadosProduto[1].trim();
                    String nomeSite = dadosProduto[2].trim();
                    double preco = Double.parseDouble(dadosProduto[3].trim());
                    String dataString = dadosProduto[4].trim();

                    Categoria categoria = new Categoria(nomeCategoria);
                    categorias.add(categoria);

                    Sites site = new Sites(nomeSite);
                    sites.add(site);

                    ArrayList<Preco_Data> precos = new ArrayList<>();
                    Preco_Data precoData = new Preco_Data(dataString, preco);
                    precos.add(precoData);

                    Hashtable<Sites, ArrayList<Preco_Data>> dadosProdutoMap = new Hashtable<>();
                    dadosProdutoMap.put(site, precos);

                    Produto produto = new Produto(descricao, (ArrayList<Categoria>) Arrays.asList(categoria), dadosProdutoMap);
                    produtos.add(produto);
                }
            }
            System.out.println("Produtos cadastrados a partir do arquivo produtos.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void menorpreco(String descricaoProduto) {
        boolean produtoEncontrado = false;
        Produto produtoEncontradoObj = null;
        double menorPreco = Double.MAX_VALUE;

        for (Produto produto : produtos) {
            if (produto.getDescricao().equalsIgnoreCase(descricaoProduto)) {
                produtoEncontrado = true;

                Hashtable<Sites, ArrayList<Preco_Data>> dados = produto.getDados();
                for (Map.Entry<Sites, ArrayList<Preco_Data>> entry : dados.entrySet()) {
                    ArrayList<Preco_Data> precos = entry.getValue();
                    for (Preco_Data precoData : precos) {
                        if (precoData.getPreco() < menorPreco) {
                            menorPreco = precoData.getPreco();
                            produtoEncontradoObj = produto;
                        }
                    }
                }
            }
        }

        if (produtoEncontrado) {
            System.out.println("Menor preço encontrado para o produto:");
            System.out.println("Produto: " + produtoEncontradoObj.getDescricao());
            System.out.println("Menor Preço: " + menorPreco);

            // Escrever no arquivo menorpreco.txt
            escreverEmArquivo("data/menorpreco.txt", produtoEncontradoObj, menorPreco);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void historicopreco(String descricaoProduto) {
        boolean produtoEncontrado = false;

        for (Produto produto : produtos) {
            if (produto.getDescricao().equalsIgnoreCase(descricaoProduto)) {
                produtoEncontrado = true;

                System.out.println("Histórico de preços para o produto:");
                System.out.println("Produto: " + produto.getDescricao());

                Hashtable<Sites, ArrayList<Preco_Data>> dados = produto.getDados();
                for (Map.Entry<Sites, ArrayList<Preco_Data>> entry : dados.entrySet()) {
                    Sites site = entry.getKey();
                    ArrayList<Preco_Data> precos = entry.getValue();

                    System.out.println("Site: " + site.getNome());
                    for (Preco_Data precoData : precos) {
                        System.out.println("Preço: " + precoData.getPreco() + " Data: " + precoData.getData());
                    }
                }

                // Escrever no arquivo historicopreco.txt
                escreverHistoricoEmArquivo("data/historicopreco.txt", produto);
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Produto não encontrado.");
        }
    }

    // Método para escrever no arquivo menorpreco.txt
    private void escreverEmArquivo(String nomeArquivo, Produto produto, double menorPreco) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write("Produto: " + produto.getDescricao() + " - Menor Preço: " + menorPreco);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para escrever no arquivo historicopreco.txt
    private void escreverHistoricoEmArquivo(String nomeArquivo, Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write("Histórico de preços para o produto: " + produto.getDescricao());
            writer.newLine();

            Hashtable<Sites, ArrayList<Preco_Data>> dados = produto.getDados();
            for (Map.Entry<Sites, ArrayList<Preco_Data>> entry : dados.entrySet()) {
                Sites site = entry.getKey();
                ArrayList<Preco_Data> precos = entry.getValue();

                writer.write("Site: " + site.getNome());
                writer.newLine();

                for (Preco_Data precoData : precos) {
                    writer.write("Preço: " + precoData.getPreco() + " Data: " + precoData.getData());
                    writer.newLine();
                }
            }

            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
