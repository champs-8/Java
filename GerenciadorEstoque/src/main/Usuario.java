package main;

import model.Produto;
import dao.ProductDao;

import java.util.List;
import java.util.Scanner;

public class Usuario {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //instanciando o scanner
        ProductDao produtoDao = new ProductDao(); //instanciando o acesso ao DB
        menu(); // chamando o menu inicial

        System.out.print("Escolha a opção que deseja: ");
        int opcao = sc.nextInt();
        sc.nextLine(); //limpar buffer do menu

        if (opcao == 5) {
            System.out.println("ENCERRANDO PROGRAMA...\n\n");
        }else{
            while (opcao != 5) {
                switch (opcao) {
                    case 1 :
                        cadastrarOp1(sc, produtoDao);
                        break;                     
                    case 2:
                        listarProdutos(produtoDao);
                        break;
                    case 3:
                        atualizarProduto(sc, produtoDao);    
                        break;
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
        System.out.printf("\n===============================\n"); //menu inicial
        System.out.printf("[1] - Cadastrar novo produto\n[2] - Listar todos os produtos\n"); //menu inicial
        System.out.printf("[3] - Atualizar produto\n[4] - Remover produto\n");
        System.out.printf("[5] - Sair do programa\n\n");
    }

    public static void cadastrarOp1(Scanner sc, ProductDao produtoDao) { //metodo para a Opção 1
        try {
            System.out.println(" == CADASTRAR PRODUTO == "); //questionario para cadastrar
            System.out.printf("Nome Produto: ");
            String name = sc.nextLine();

            System.out.printf("Preço unitário: "); //irá preencher todas as opções sequencialmente
            Double price = sc.nextDouble();

            System.out.printf("Quantidade: ");
            int quant = sc.nextInt();

            Produto product = new Produto(0, name, price, quant); //criou um novo objeto para para ser inserido
            produtoDao.inserirProduto(product); //metodo do DAO que irá inserir no DB
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listarProdutos(ProductDao productDao) { //metodo para opção 2
        try {
            List<Produto> produtos = productDao.listarProdutos();
            System.out.println("\n == PRODUTOS NO ESTOQUE == \n");
            for (Produto produto : produtos) { //interação entre todos os objetos da tabela produto.
                System.out.println(produto); //será exibido formatado devido ao Override em Produto.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void atualizarProduto(Scanner sc, ProductDao productDao){ //metodo para opção 3
        try {
            System.out.println("\n == ATUALIZAR PRODUTO == \n");
            System.out.println("Qual o ID do produto que quer remover? "); //coletar o numero do ID
            listarProdutos(productDao); //chama o metodo para escolher o produto.
            int escolha = sc.nextInt();

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
