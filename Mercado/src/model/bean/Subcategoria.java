
package model.dao;

/**
 *
 * @author Tafar
 */
public class Subcategoria {
    
    private int idSubcategoria;
    private String nome;
    private Categoria categoria;

    public Subcategoria() {
    }

    public Subcategoria(int idSubcategoria, String nome, Categoria categoria) {
        this.idSubcategoria = idSubcategoria;
        this.nome = nome;
        this.categoria = categoria;
    }

    public int getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(int idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }    
        
}
