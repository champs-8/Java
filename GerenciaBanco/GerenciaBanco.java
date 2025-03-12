/**
 * 
 * @author fabio
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciaBanco {
    
    private String name;
    private String lastName;
    private String cpf;
    private int saldo;
    
    
    //construtor da classe
    public GerenciaBanco(String name, String lastName, String cpf, int saldo) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.saldo = saldo;
    }
    
    public static void main(String[] args){   
        GerenciaBanco gb = new GerenciaBanco("","","", 0); //instanciando a classe
        Scanner sc = new Scanner(System.in); //instanciando o scanner
        // cadastro(sc, gb); //chamando o metodo cadastro

        //chamando o metodo menu para escolher a opção
        int opcao = menu(sc); //retorna um inteiro

        while(opcao != 4) {
            switch(opcao) {
                case 1:
                    System.out.println("\n-->Consultar saldo\n");
                    consultarSaldo(gb);
                    break;
                case 2:
                    System.out.println("\n-->Depositar\n");
                    depositar(gb, sc);
                    break;
                case 3:
                    System.out.println("\n-->Saque\n");
                    sacar(gb, sc);
                    break;
                default:
                    System.out.println("\n-->Opção inválida\n"); //caso digite um numero diferente de 1, 2, 3 ou 4
                    break;
            }
            opcao = menu(sc); // chamando o menu novamente até digitar 4
        }
        //se digitar 4, o programa encerra
        System.out.println("ENCERRANDO PROGRAMA...\n");
        
        
        sc.close(); //fechando o scanner
    }
    
    public static void cadastro(Scanner sc, GerenciaBanco gb) {

        //Coletando o nome
        System.out.print("Digite apenas seu primeiro nome: ");
        String nome = sc.nextLine();
        gb.setName(nome);

        //Coletando o sobrenome
        System.out.print("Digite seu sobrenome: ");
        String sobrenome = sc.nextLine();
        gb.setLastName(sobrenome);

        //coleando o cpf
        System.out.print("Digite seu CPF: ");
        String cpf = sc.nextLine();
        gb.setCpf(cpf);
    }


    public static int menu(Scanner sc){
        System.out.println("===== MENU =====");
        System.out.println("1 - Consultar saldo\n2 - Despositar\n3 - Saques\n4 - Sair");
        System.out.println("================");
        System.out.print("Digite a opção desejada: ");

        try {
            int opcao = sc.nextInt();
            sc.nextLine(); //limpando o buffer

            return opcao; //se escolher valor certo, retorna a opcao

        } catch (InputMismatchException e) {
            System.out.println("Erro ao digitar a opção, tente novamente");
            e.printStackTrace(); //imprime o erro
        }
        return 0;
    }

    //metodo da opcao 1
    public static void consultarSaldo(GerenciaBanco gb){
        System.out.printf("Seu saldo é: R$ %d,00\n", gb.getSaldo());
    }

    //metodo da opcao 2
    public static void depositar(GerenciaBanco gb, Scanner sc){
        try{
            System.out.print("Digite o valor que deseja depositar: R$");
            int valor = sc.nextInt();
            sc.nextLine(); //limpando o buffer

            if (valor > 0) { //caso tente depositar valor negativo(no caso teclado)
                gb.setSaldo(gb.getSaldo()+valor); //somando o valor digitado ao saldo
                System.out.printf("Deposito de R$ %d,00 realizado com sucesso!\n", valor); 
            }else{
                System.out.println("Valor inválido, tente novamente");
            }
        } catch (Exception e) {
            System.out.println("Erro ao depositar, tente novamente");
            e.printStackTrace(); //imprime o erro
        }
    }

    //metodo da opcao 3
    public static void sacar(GerenciaBanco gb, Scanner sc){
        try {
            System.out.print("Digite o valor que deseja sacar: R$");
            int valor = sc.nextInt();
            sc.nextLine(); //limpando o buffer

            if(valor > 0 && valor <= gb.getSaldo()) { //caso queira sacar mais do que tem disponivel
                gb.setSaldo(gb.getSaldo() - valor); //subtraindo o valor digitado ao saldo
                System.out.printf("Saque de R$ %d,00 realizado com sucesso!\n", valor);
            }else{
                System.out.println("Valor inválido, tente novamente");
            }
        }catch (Exception e) {
            System.out.println("Erro ao sacar, tente novamente");
            e.printStackTrace(); //imprime o erro
        }

    }


    // os getters e setters não serão staticos pois se trata de manipular atributos
    // de classe, é mais recomendado que sejam instanciados

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf(){
        return cpf;
    } 
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getSaldo() { //utilizarei int pois nao se saca centavos
        return saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}