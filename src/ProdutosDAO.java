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
            }
            return resultado;
        }catch(SQLException erroAoPegarProdutos){
            System.out.println("Classe: ProdutosDAO/Metodo: listarProdutos/Erro: " + erroAoPegarProdutos.getMessage());
            return null;
        }
    }
    
    
    
        
}

