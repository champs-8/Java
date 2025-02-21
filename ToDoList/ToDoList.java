package ToDoList;
import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {
    public static void main(String args[]) {
        System.out.printf("Hello World!\n\n");
        
        //instanciando o array
        ArrayList<String> listaTarefas = new ArrayList<>();
        
        //instanciando o Scanner
        Scanner sc = new Scanner(System.in);
        
        
        tarefas(sc, listaTarefas);
        
        //fechando Scanner
        sc.close();
    };
    
    //adicionar tarefas
    public static void adicionar(Scanner sc, ArrayList<String> lista) {
        //pedir o nome da tarefa
        String nome = sc.nextLine();
        lista.add(nome);
        System.out.print("\n✅TAREFA ADICIONADA\n");
    };

    //listar tarefas
    public static void listar(ArrayList<String> lista) {
        //verificando lista vazia
        if (lista.size() == 0 ){
            System.out.println("⚠️LISTA VAZIA");
            
        }else{
            for (int i = 0; i < lista.size(); i++) { //for para percorrer todo o tamanho da lista
    
                //mostrará o indica da tarefa e depois o valor do nome da tarefa
                System.out.printf("[%s] - %s\n", i+1, lista.get(i)); //adicionando 1 para o usuario
            }
        }

    };

    //remover tarefas
    public static void remover(ArrayList<String> lista, Scanner sc) {
        //verificando se está vazio
        if (lista.size() == 0) {
            System.out.println("⚠️ADICIONE UMA TAREFA PARA REMOVER");
        }else{

            System.out.printf("Qual o número da tarefa que deseja remover?: ");
    
            //escolhendo qual tarefa retirar
            int indice = sc.nextInt();
    
            if (indice >= lista.size()) {
                System.out.printf("\n⚠️TAREFA NÃO ENCONTRADA\n");
                return;
            }
    
            System.out.printf("\nRemovendo tarefa - %s\n", lista.get(indice-1));  //get mostrar o valor do indice
            lista.remove(indice-1); //removendo 1 para o usuario ficar mais legivel
            System.out.printf("\nTAREFA REMOVIDA\n");
        };
    };
    
    public static void tarefas(Scanner sc, ArrayList<String> lista){
        System.out.println("=== Gerenciador de Tarefas ===");
        System.out.printf("TOTAL DE TAREFAS = %s\n[1] - Adicionar\n[2] - Listar\n[3] - Remover\n[4] - Sair\nEscolha sua opção: ", lista.size());
        
        //opção escolhida pelo usuário inicialmente
        int opcao = sc.nextInt();
        
        if (opcao == 4){
            System.out.println("ENCERRANDO PROGRAMA... ");
        }else{
            //menu de opção sendo mostrado até apertar para sair
            while (opcao != 4){
                
            //limpar o buffer do menu
            sc.nextLine();
                
                switch (opcao) {
                    case 1:
                        System.out.printf("\nAdicionar tarefa: ");
                        adicionar(sc,lista);
                        break;
                    case 2:
                        System.out.printf("\n📋 Lista das tarefas:\n\n");
                        listar(lista);
                        break;
                    case 3:
                        System.out.printf("\n🗑️Remover tarefa:\n\n");
                        listar(lista);
                        remover(lista, sc);
                        break;
                    default:
                        System.out.printf("\nOPÇÃO INVÁLIDA\n");
                        break;
                };
                
                System.out.println("=============================================");
                System.out.printf("\nTOTAL DE TAREFAS = %s\n[1] - Adicionar\n[2] - Listar\n[3] - Remover\n[4] - Sair\n\nEscolha sua opção: ", lista.size());
                opcao = sc.nextInt();
            }
            System.out.println("ENCERRANDO PROGRAMA... ");
        }
        
    };
};