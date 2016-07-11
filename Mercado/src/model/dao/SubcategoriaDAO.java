
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
import model.bean.Subcategoria;

/**
 *
 * @author Tafar
 */
public class SubcategoriaDAO {
    
        public void create(Subcategoria subcategoria) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("INSERT INTO subcategoria (idsubcategoria, nome, idcategoria)VALUES(?,?,?);");
            stmt.setInt(1, subcategoria.getIdSubcategoria());
            stmt.setString(2, subcategoria.getNome());
            stmt.setInt(3, subcategoria.getCategoria().getIdCategoria());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR! DETALHE:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }
    
    public List<Subcategoria> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Subcategoria> subcategoriaColecao = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM subcategoria;");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Subcategoria subcategoria = new Subcategoria();
                
                subcategoria.setIdSubcategoria(rs.getInt("idsubcategoria"));
                subcategoria.setNome(rs.getString("nome"));
                //subcategoria.setCategoria(rs.getObject());

                subcategoriaColecao.add(subcategoria);
            }
            
        }catch(SQLException exception) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }        
        return subcategoriaColecao;
    }
    
    public void update(Subcategoria subcategoria) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("UPDATE subcategoria SET nome = ?, idcategoria = ? WHERE idsubcategoria = ?;");
            stmt.setString(1, subcategoria.getNome());
            stmt.setInt(2, subcategoria.getCategoria().getIdCategoria());
            stmt.setInt(3, subcategoria.getIdSubcategoria());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR! DETALHE:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }
    
    public void delete(Subcategoria subcategoria) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement  stmt = null;
        
        try {        
            stmt = conn.prepareStatement("DELETE FROM subcategoria WHERE idsubcategoria = ?;");
            stmt.setInt(1, subcategoria.getIdSubcategoria());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO.");
            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR! DETALHES:"+exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt);
        }        
    }   
    
    //procurar por nome/descricao do categoria
    public List<Subcategoria> readForNome(String nome) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Subcategoria> subcategoriaColecao = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM subcategoria WHERE nome LIKE ?;");
            stmt.setString(1, '%'+nome+'%');
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Subcategoria subcategoria = new Subcategoria();
                
                subcategoria.setIdSubcategoria(rs.getInt("idsubcategoria"));
                subcategoria.setNome(rs.getString("nome"));
                
                subcategoriaColecao.add(subcategoria);
            }
            
        }catch(SQLException exception) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }        
        return subcategoriaColecao;
    }
}
