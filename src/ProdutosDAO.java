/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */


import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    private Connection conn;
    private PreparedStatement prep;
    private ResultSet resultset;
    private ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String inserirProduto = "INSERT INTO produtos(nome, valor, status) "
                + "VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(inserirProduto);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Valor cadastrado com sucesso");
        }catch(SQLException erroAoInserirValor){
            System.out.println("Classe: ProdutosDAO/Metodo: venderProduto/Erro: " + erroAoInserirValor.getMessage());
        }     
    }
    
    public void venderProduto(int idProduto){
        conn = new conectaDAO().connectDB();
        String deletarProduto = "DELETE FROM produtos WHERE id = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(deletarProduto);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Produto vendido");
        }catch(SQLException erroAoExcluirProduto){
            System.out.println("Classe: ProdutosDAO/Metodo: venderProduto/Erro: " + erroAoExcluirProduto.getMessage());
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        String selectTodosProdutos = "SELECT * FROM produtos";
        ArrayList<ProdutosDTO> resultado = new ArrayList();
        ProdutosDTO produtosDTO;
        
        try{
            PreparedStatement stmt = conn.prepareStatement(selectTodosProdutos);
            resultset = stmt.executeQuery();
            while(resultset.next()){
                produtosDTO = new ProdutosDTO();
                produtosDTO.setId(resultset.getInt("id"));
                produtosDTO.setNome(resultset.getString("nome"));
                produtosDTO.setValor(resultset.getInt("valor"));
                produtosDTO.setStatus(resultset.getString("status"));
                resultado.addFirst(produtosDTO);
                stmt.close();
                conn.close();
            }
            return resultado;
        }catch(SQLException erroAoPegarProdutos){
            System.out.println("Classe: ProdutosDAO/Metodo: listarProdutos/Erro: " + erroAoPegarProdutos.getMessage());
            return null;
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
       
        conn = new conectaDAO().connectDB();
        PreparedStatement stmt;
        String selectProdutosVendidos = "SELECT * FROM produtos "
                + "WHERE status = ?";
        ResultSet resultadoPesquisa;
        ProdutosDTO produtosDto;
        ArrayList<ProdutosDTO> resultado = new ArrayList();
        
        try{
            stmt = conn.prepareStatement(selectProdutosVendidos);
            resultadoPesquisa = stmt.executeQuery();
            while(resultadoPesquisa.next()){
                produtosDto = new ProdutosDTO();
                produtosDto.setId(resultadoPesquisa.getInt("id"));
                produtosDto.setNome(resultadoPesquisa.getString("nome"));
                produtosDto.setValor(resultadoPesquisa.getInt("valor"));
                produtosDto.setStatus(resultadoPesquisa.getString("status"));
                resultado.add(produtosDto);
            }
            stmt.close();
            conn.close();
            return resultado;
        }catch(SQLException erroAoPesquisarProdutosVendidos){
            System.out.println("Classe: ProdutosDAO/Metodo: listarProdutosVendidos/Erro: " + erroAoPesquisarProdutosVendidos.getMessage());
            return null;
        }
    }
    
        
}

