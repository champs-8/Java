/**
 * 
 * @author fabio
 */

import java.util.Scanner;

public class GerenciaBanco {
    
    private String name;
    private String lastName;
    private String cpf;
    
    //construtor da classe
    public GerenciaBanco(String name, String lastName, String cpf) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
    }
    
    public static void main(String[] args) {   
        GerenciaBanco gb = new GerenciaBanco("","",""); //instanciando a classe
        Scanner sc = new Scanner(System.in); //instanciando o scanner
        cadastro(sc, gb); //chamando o metodo cadastro
        
        sc.close(); //fechando o scanner
    }
    
    public static void cadastro(Scanner sc, GerenciaBanco gb) {

        //Coletando o nome
        System.out.println("Digite apenas seu primeiro nome: ");
        String nome = sc.nextLine();
        
        gb.setName(nome);
        System.out.println("Seu nome é: "+gb.getName());

        //Coletando o sobrenome
        System.out.println("Digite seu sobrenome: ");
        String sobrenome = sc.nextLine();

        gb.setLastName(sobrenome);
        System.out.println("Seu sobrenome é: "+gb.getLastName());

        //coleando o cpf
        System.out.println("Digite seu CPF: ");
        String cpf = sc.nextLine();

        gb.setCpf(cpf);
        System.out.println("Seu CPF é: "+gb.getCpf());
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
}
