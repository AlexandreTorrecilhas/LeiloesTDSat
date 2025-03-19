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
        String selectTodosProdutos = "SELECT * FROM produto";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(selectTodosProdutos);
            resultset = stmt.executeQuery();
        }catch(SQLException erroAoPegarProdutos){
            System.out.println("Classe: ProdutosDAO/Metodo: listarProdutos/Erro: " + erroAoPegarProdutos.getMessage());
        }
        return listagem;
    }
    
    
    
        
}

