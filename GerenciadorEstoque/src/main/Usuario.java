package main;

import model.Produto;
import dao.ProductDao;
import java.util.Scanner;

public class Usuario {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //instanciando o scanner
        ProductDao produtoDao = new ProductDao(); //instanciando o acesso ao DB
        menu(); // chamando o menu inicial

        System.out.print("\nEscolha a opção que deseja: ");
        int opcao = sc.nextInt();
        sc.nextLine(); //limpar buffer do menu

        if (opcao == 5) {
            System.out.println("ENCERRANDO PROGRAMA...\n\n");
        }else{
            while (opcao != 5) {
                switch (opcao) {
                    case 1 :
                        System.out.println(" == CADASTRAR PRODUTO == "); //questionario para cadastrar
                        System.out.printf("Nome Produto: ");
                        String name = sc.nextLine();

                        System.out.printf("Preço unitário: ");
                        Double price = sc.nextDouble();

                        System.out.printf("Quantidade: ");
                        int quant = sc.nextInt();

                        Produto product = new Produto(0, name, price, quant);
                        produtoDao.inserirProduto(product);
                        break;                     
                    case 2:
    
                    case 3:
    
                    case 4:
                }
                menu();
                System.out.print("Escolha a opção que deseja: ");
                opcao = sc.nextInt();
                sc.nextLine(); //limpar buffer do menu
            }
        }
        sc.close();
    }

    public static void menu (){
        System.out.printf("===============================\n"); //menu inicial
        System.out.printf("[1] - Cadastrar novo produto\n[2] - Listar todos os produtos\n"); //menu inicial
        System.out.printf("[3] - Atualizar produto\n[4] - Remover produto\n");
        System.out.printf("[5] - Sair do programa\n\n");
    }
}
