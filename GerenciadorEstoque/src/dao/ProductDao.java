package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
    private static final String URL = "jdbc:mysql:localhost/3306";
    private static final String USER = "root"; //inserir credenciais reais
    private static final String PASS = ""; 

    //conexão com banco de dados
    private Connection connect() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS); //inserir os dados 
    }

    //inserir produto
    public void inserirProduto(Produto product) { //Recebeu parametros, usará PreparedStatement
        String sql = "INSERT INTO produto (name, price, quantity) VALUES (?,?,?)"; 

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());

            stmt.executeUpdate();
            System.out.println("Produto inserido com SUCESSO!");
        }catch(SQLException e){
            e.printStackTrace(); //imprimir a excessão
        }

    }

    //Listar todos os produtos
    public List<Produto> listarProdutos() { //nao recebeu paramentro, usará Statement
        List<Produto> produtos = new ArrayList<> (); //será retornado
        String sql = "SELECT FROM * produto"; //seleciona todos os produtos

        try (Connection conn = connect(); //faz a conexão
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql)){

            while(result.next()) {
                Produto product = new Produto(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getDouble("price"),
                    result.getInt("quantity")
                );

                produtos.add(product);
            };
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos; //retorno
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,produto.getName() ); // A ordem do index refere-se ao ? na sql
            stmt.setInt(4, produto.getId());
            stmt.setInt(3, produto.getQuantity());
            stmt.setDouble(2, produto.getPrice());

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0 ) {
                System.out.println("Produto atualizado com SUCESSO!");
            }else{
                System.out.println("Produto não encontrado");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Remover produto
    public void removerProduto(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0 ) {
                System.out.println("Produto removido com SUCESSO!");
            }else{
                System.out.println("Prduto não encontrado.");
            }
        }catch( SQLException e){
            e.printStackTrace();    
        }
    }
}

