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

        if (opcao == 0) {
            System.out.println("\nENCERRANDO PROGRAMA...\n");
        }else{
            while (opcao != 0) {
                switch (opcao) {
                    case 1 :
                        cadastrarOp1(sc, produtoDao); // em todos os casos passa-se SC e ProdutoDao como argumento
                        break;                        // para o metodo ter acesso a essas funcionalidades
                    case 2:
                        listarProdutosOp2(produtoDao);
                        break;
                    case 3:
                        atualizarProdutoOp3(sc, produtoDao);    
                        break;
                    case 4:
                        removerProdutoOp4(sc, produtoDao);
                        break;
                }
                menu();
                System.out.print("Escolha a opção que deseja: ");
                opcao = sc.nextInt();
                sc.nextLine(); //limpar buffer do menu
            }
            System.out.println("\nENCERRANDO PROGRAMA...\n");
        }
        sc.close();
    }

    public static void menu (){
        System.out.printf("\n===============================\n"); //menu inicial
        System.out.printf("[1] - Cadastrar novo produto\n[2] - Listar todos os produtos\n"); //menu inicial
        System.out.printf("[3] - Atualizar produto\n[4] - Remover produto\n");
        System.out.printf("[0] - Sair do programa\n\n");
    }

    public static void cadastrarOp1(Scanner sc, ProductDao produtoDao) { //metodo para a Opção 1
        try {
            System.out.println("\n == CADASTRAR PRODUTO == \n"); //questionario para cadastrar
            System.out.printf("(Digite 0 para voltar ao menu)\n"); //voltar ao menu
            System.out.printf("Nome Produto: ");
            String name = sc.nextLine();
            if (name.equals("0")) return;


            System.out.printf("\n(Digite 0 para voltar ao menu)\n");// voltar ao menu
            System.out.printf("Preço unitário: "); //irá preencher todas as opções sequencialmente
            Double price = sc.nextDouble();
            if (price == 0) return;

            System.out.printf("\n(Digite 0 para voltar ao menu)\n");// voltar ao menu
            System.out.printf("Quantidade: ");
            int quant = sc.nextInt();
            if (quant == 0) return;

            Produto product = new Produto(0, name, price, quant); //criou um novo objeto para para ser inserido
            produtoDao.inserirProduto(product); //metodo do DAO que irá inserir no DB
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listarProdutosOp2(ProductDao productDao) { //metodo para opção 2
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

    public static void atualizarProdutoOp3(Scanner sc, ProductDao productDao){ //metodo para opção 3
        try {
            System.out.println("\n == ATUALIZAR PRODUTO == \n");
            listarProdutosOp2(productDao); //chama o metodo para escolher o produto. lista todos disponiveis
            System.out.printf("\nQual o ID do produto que quer atualizar? Ou digite 0 para voltar ao menu: "); //coletar o numero do ID
            int escolha = sc.nextInt();
            sc.nextLine(); //consumir buffer

            if (escolha == 0) {
                return; // retorna ao menu
            }

            Produto produto = productDao.buscarProdPeloID(escolha); //chamar o metodo pelo ID
            if (produto != null) {
                System.out.println("\n-- Produto encontrado --\n");
                System.out.println(produto);

                //atualizar nome
                System.out.printf("Nome Produto - [%s]: ", produto.getName()); //vai inputar os dados a serem alterados
                String novoNome = sc.nextLine(); //vai dar a opção de atualizar com o nome atual
                if (novoNome.isEmpty()) novoNome = produto.getName(); // se nao alterar nada, vai permanecer o mesmo

                //atualizar preço
                System.out.printf("Preço - [%.2f]: ", produto.getPrice());//vai inputar o preço 
                String strPrice = sc.nextLine(); //opção de atualizar
                //se preço for vazio vai receber o valor do banco de dados, se nao a variavel vai receber a conversão da String
                double novoPrice = strPrice.isEmpty() ? produto.getPrice() : Double.parseDouble(strPrice); 

                //atualizar a quantidade
                System.out.printf("Quantidade - [%d]: ", produto.getQuantity());
                String strQtd = sc.nextLine(); //opção para atualizar
                //se quantidade for vazio vai receber o valor do banco de dados, se nao a variavel vai receber a conversão da String
                int novoQtd = strQtd.isEmpty() ?  produto.getQuantity() : Integer.parseInt(strQtd);                

                //atualizando o objeto
                produto.setName(novoNome); //configurado nome
                produto.setPrice(novoPrice); //configurado preço
                produto.setQuantity(novoQtd); //configurado quantidade

                try {
                    productDao.atualizarProduto(produto); //atualizando o banco de dados com o objeto
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.err.println("\n\n -- Produto não encontrado --");
                atualizarProdutoOp3(sc, productDao); // caso nao encontre, retornará para os objetos encontrados
                //em estoque para escolher um Id Valido
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removerProdutoOp4 (Scanner sc, ProductDao productDao){
        try {
            System.out.println("\n == REMOVER PRODUTO == ");
            listarProdutosOp2(productDao); // mostrar lista para ver qual produto quer remover
            System.out.print("\n(Digite 0 para retornar ao Menu)\nQual o ID do produto que quer remover? "); 
            int escolha = sc.nextInt(); //coletará o ID do produto que vai remover
            sc.nextLine(); //consumir buffer;
            if (escolha == 0) { //retorna ao menu
                return;
            }

            Produto produto = productDao.buscarProdPeloID(escolha); // irá buscar pelo produto do ID
            if (produto != null) {
                System.err.println("\n-- Produto encontrado --");
                System.err.println(produto);

                //confirmação para nao fazer cagada
                System.out.printf("\nConfirmar exclusão do produto [%s]?\n\n", produto.getName());
                System.out.print("[0] - VOLTAR AO MENU\n[1] - SIM / [2] - NÃO -> ");
                int confirmacao = sc.nextInt();
                if (confirmacao == 0){
                    return; //voltar ao menu
                }else if(confirmacao == 1){
                    productDao.removerProduto(escolha); //metodo do banco de dados
                }else if (confirmacao == 2) {
                    //se nao tiver certeza vai voltar a lista de produto para remover
                    removerProdutoOp4(sc, productDao); 
                }else{                              
                    //se nao for nenhuma dessas opções tambem volta ao menu de escolha para remover   
                    removerProdutoOp4(sc, productDao);
                }
            }else{
                System.err.println("\n -- Produto não encontrado -- \n");
                removerProdutoOp4(sc, productDao); //vai chamar a função até escolher um ID válido
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
