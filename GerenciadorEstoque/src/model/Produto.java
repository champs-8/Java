/**
 * 
 * @author fabio
 */

package model;

public class Produto {
    //definir variaveis
    private int id, quantity;
    private String name;
    private double price;


    //contrutor
    public Produto (int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    };

    //getters e setters
    public int getId(){
        return id; 
    }
    public void setId(int id){ //receberá um valor como parametro
        this.id = id; //adicionará esse valor a varivel do construtor
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity (int quantity) {
        this.quantity = quantity;
    }

    @Override
    //metodo para mostrar infoProdutos
    public String toString () {
        return String.format("ID: %-4d  Nome: %-40s  Preco: %-8.2f  Quantidade: %-5d", id, name, price, quantity);
    }
}