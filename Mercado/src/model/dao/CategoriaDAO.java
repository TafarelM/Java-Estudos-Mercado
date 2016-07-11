
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Categoria;

/**
 *
 * @author Tafar
 */
public class CategoriaDAO {
    
        public void create(Categoria categoria) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("INSERT INTO categoria (idcategoria, nome)VALUES(?,?);");
            stmt.setInt(1, categoria.getIdCategoria());
            stmt.setString(2, categoria.getNome());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR! DETALHE:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }
    
    public List<Categoria> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Categoria> categoriaColecao = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM categoria;");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(rs.getInt("idcategoria"));
                categoria.setNome(rs.getString("nome"));
                
                categoriaColecao.add(categoria);
            }
            
        }catch(SQLException exception) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }        
        return categoriaColecao;
    }
    
    public void update(Categoria categoria) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("UPDATE categoria SET nome = ? WHERE idcategoria = ?;");
            stmt.setString(1, categoria.getNome());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR! DETALHE:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }
    
    public void delete(Categoria categoria) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("DELETE FROM categoria WHERE id = ?;");
            stmt.setInt(1, categoria.getIdCategoria());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR! DETALHES:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }   
    
    //procurar por nome/descricao do categoria
    public List<Categoria> readForDescricao(String descricao) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Categoria> categoriaColecao = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM categoria WHERE nome LIKE ?;");
            stmt.setString(1, '%'+descricao+'%');
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(rs.getInt("idcategoria"));
                categoria.setNome(rs.getString("nome"));
                
                categoriaColecao.add(categoria);
            }
            
        }catch(SQLException exception) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }        
        return categoriaColecao;
    }
    
    
}
