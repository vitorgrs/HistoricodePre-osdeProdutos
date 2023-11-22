package View;
import Controller.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Cadastrar cadastrar = new Cadastrar();

        Scanner scanner = new Scanner(System.in);

        int opcao = 0;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Exibir Preços dos Produtos");
            System.out.println("3 - Buscar menor preço atual para um produto");
            System.out.println("4 - Obter o histórico de preços de um produto ao longo de todos os sites");
            System.out.println("5 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Escolha uma opção:");
                    System.out.println("1 - Cadastrar um produto pegando os dados do arquivo");
                    System.out.println("2 - Cadastrar um produto inserindo os dados do arquivo");
                    int escolhaOpcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (escolhaOpcao) {
                        case 1:
                            cadastrar.cadastrarProdutoFromFile();
                            break;
                        case 2:
                            cadastrar.cadastrarProduto();
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Escolha uma opção:");
                    System.out.println("1 - Informar preço para um produto específico");
                    System.out.println("2 - Exibir todos os produtos e seus preços");
                    int escolhaOpcao2 = scanner.nextInt();
                    scanner.nextLine();

                    switch (escolhaOpcao2) {
                        case 1:
                            System.out.println("Digite a descrição do produto:");
                            String nomeProduto1 = scanner.nextLine();
                            cadastrar.informarPrecoProduto(nomeProduto1);
                            break;
                        case 2:
                            cadastrar.exibirProdutos();
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Digite a descrição do produto:");
                    String nomeProduto2 = scanner.nextLine();
                    cadastrar.menorpreco(nomeProduto2);
                    break;
                case 4:
                    System.out.println("Digite a descrição do produto:");
                    String nomeProduto3 = scanner.nextLine();
                    cadastrar.historicopreco(nomeProduto3);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 4);
    }
}
