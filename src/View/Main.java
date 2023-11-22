package View;
import Controller.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Cadastrar cadastrar = new Cadastrar();
        Informar informar = new Informar();

        Scanner scanner = new Scanner(System.in);

        int opcao = 0;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - falta fazer");
            System.out.println("3 - faltar fazer");
            System.out.println("4 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrar.cadastrarProduto();
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
                            String descricaoProduto = scanner.nextLine();
                            cadastrar.informarPrecoProduto(descricaoProduto);
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

                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 4);
    }
}
