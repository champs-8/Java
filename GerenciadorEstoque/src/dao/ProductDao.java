package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;


public class ProductDao {
    private static final String URL = "jdbc:mysql://localhost:3306/estoque";
    private static final String USER = "root"; //inserir credenciais reais
    private static final String PASS = ""; 

    //conexão com banco de dados
    private Connection connect() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS); //inserir os dados 
    }

    //inserir produto
    public void inserirProduto(Produto product){ //Recebeu parametros, usará PreparedStatement
        String sql = "INSERT INTO produto (name, price, quantity) VALUES (?,?,?)"; 

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) { //fez um try with resources

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());

            stmt.executeUpdate();
            System.out.println("\n --- Produto inserido com SUCESSO! ---\n");
        }catch(SQLException e){
            e.printStackTrace(); //imprimir a exceção
        }catch(CJCommunicationsException e) { //exceção 
            e.printStackTrace(); //imprimir a exceção
        }
    }

    //Listar todos os produtos
    public List<Produto> listarProdutos() { //nao recebeu paramentro, usará Statement
        List<Produto> produtos = new ArrayList<> (); //será retornado
        String sql = "SELECT * FROM produto"; //seleciona todos os produtos

        try (Connection conn = connect(); //faz a conexão
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery()){ //fez um try with resources

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

    public void atualizarProduto(Produto produto) throws CommunicationsException{
        String sql = "UPDATE produto SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) { //fez um try with resources

            stmt.setString(1,produto.getName() ); // A ordem do index refere-se ao ? na sql
            stmt.setDouble(2, produto.getPrice());
            stmt.setInt(3, produto.getQuantity());
            stmt.setInt(4, produto.getId());

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0 ) {
                System.out.println("\nProduto atualizado com SUCESSO!");
            }else{
                System.out.println("\nProduto não encontrado");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Remover produto
    public void removerProduto(int id) throws CommunicationsException {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){ //fez um try with resources

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0 ) {
                System.out.println("\nProduto removido com SUCESSO!");
            }else{
                System.out.println("Produto não encontrado.");
            }
        }catch( SQLException e){
            e.printStackTrace();    
        }
    }
    public Produto buscarProdPeloID(int id) { //servirá para retornar apenas um item pelo ID
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){ // faz a conexão
            
            stmt.setInt(1, id); //parametriza o id
            ResultSet result = stmt.executeQuery(); //executa query quando se é select

            if (result.next()) { //se encontrou o produto
                return new Produto(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getDouble("price"),
                    result.getInt("quantity")
                ); 
            };
        } catch (Exception e) {
            e.printStackTrace();
        } return null; //Caso não encontrar nada
    }
}

