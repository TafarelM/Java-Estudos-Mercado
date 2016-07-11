
package model.bean;
/**
 *
 * @author Tafar
 */
public class Marca {
    private int idMarca;
    private String nome;
    private Fornecedor fornecedor;

    public Marca() {
    }

    public Marca(int idMarca, String nome, Fornecedor fornecedor) {
        this.idMarca = idMarca;
        this.nome = nome;
        this.fornecedor = fornecedor;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    } 
    
}
