
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Categoria;
import model.bean.Produto;
import model.bean.Subcategoria;

/**
 *
 * @author Tafar
 */
public class ProdutoDAO {
    
        public void create(Produto produto) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("INSERT INTO produto (nome, quantidade, preco, descricao, categoria, subcategoria, marca, datafabricao, datavencimento)VALUES(?,?,?,?,?,?,?,?,?);");
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getDescricao());
            stmt.setInt(5, produto.getCategoria().getIdCategoria());
            stmt.setInt(6, produto.getSubcategoria().getIdSubcategoria());
            stmt.setInt(7, produto.getMarca().getIdMarca());
            stmt.setDate(8, (Date) produto.getDataFabricacao());
            stmt.setDate(9, (Date) produto.getDataVencimento());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR! DETALHE:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }
    
    public List<Produto> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtoColecao = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM produto;");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Produto produto = new Produto();
                //Categoria categoria = new Categoria();
                //Subcategoria subcategoria = new Subcategoria();
                //categoria.setNome(rs.getString("nome"));
                
                produto.setIdProduto(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setCategoria().getIdCategoria();
                //produto.setSubcategoria
                //produto.setMarca
                produto.setDataFabricacao(rs.getDate("datafabricacao"));
                produto.setDataVencimento(rs.getDate("datavencimento"));                
                
                produtoColecao.add(produto);
            }
            
        }catch(SQLException exception) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }        
        return produtoColecao;
    }
    
    public void update(Produto produto) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("UPDATE produto SET nome = ?, quantidade = ?, preco = ?, descricao = ?, idcategoria = ?, idsubcategoria = ?, idmarca = ?, datafabricacao = ?, datavencimento = ?  WHERE idproduto = ?;");
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getDescricao());
            stmt.setObject(5, produto.getCategoria());
            stmt.setObject(6, produto.getSubcategoria());
            stmt.setObject(7, produto.getMarca());
            stmt.setDate(9, (Date)produto.getDataFabricacao());
            stmt.setDate(9, (Date)produto.getDataVencimento());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR! DETALHE:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }
    
    public void delete(Produto produto) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("DELETE FROM produto WHERE id = ?;");
            stmt.setDouble(1, produto.getIdProduto());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR! DETALHES:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }   
    
    //procurar por nome/descricao do produto
    public List<Produto> readForDescricao(String descricao) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtoColecao = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?;");
            stmt.setString(1, '%'+descricao+'%');
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Produto produto = new Produto();
                
                produto.setIdProduto(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDescricao(rs.getString("descricao"));
                //produto.setCategoria(categoria);
                //produto.setSubcategoria(subcategoria);
                //produto.setMarca(marca);
                produto.setDataFabricacao(rs.getDate("datafabricao"));
                produto.setDataVencimento(rs.getDate("datavencimento"));
                
                produtoColecao.add(produto);
            }
            
        }catch(SQLException exception) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }        
        return produtoColecao;
    }
}
