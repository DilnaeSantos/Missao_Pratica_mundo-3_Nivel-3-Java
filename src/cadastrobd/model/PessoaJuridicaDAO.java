/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Dilnae
 */
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    
    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica pessoa = null;
        String sql = "SELECT * FROM Pessoa p INNER JOIN PessoaJuridica pj ON p.id = pj.id WHERE p.id = ?";
        
        try (
            Connection conn = ConectorBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pessoa = new PessoaJuridica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
            }
        } catch (SQLException e) {
        }
        
        return pessoa;
    }
    
    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa p INNER JOIN PessoaJuridica pj ON p.id = pj.id";
        
        try (
            Connection conn = ConectorBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                PessoaJuridica pessoa = new PessoaJuridica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
        }
        
        return pessoas;
    }
    
    public void incluir(PessoaJuridica pessoa) {
        String sqlPessoa = "INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (id, cnpj) VALUES (?, ?)";
        
        try (
            Connection conn = ConectorBD.getConnection();
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
        ) {
            int id = SequenceManager.getValue("sequencia_id");
            stmtPessoa.setInt(1, id);
            stmtPessoa.setString(2, pessoa.getNome());
            stmtPessoa.setString(3, pessoa.getLogradouro());
            stmtPessoa.setString(4, pessoa.getCidade());
            stmtPessoa.setString(5, pessoa.getEstado());
            stmtPessoa.setString(6, pessoa.getTelefone());
            stmtPessoa.setString(7, pessoa.getEmail());
            stmtPessoa.executeUpdate();
            
            stmtPessoaJuridica.setInt(1, id);
            stmtPessoaJuridica.setString(2, pessoa.getCnpj());
            stmtPessoaJuridica.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void alterar(PessoaJuridica pessoa) {
    String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
    String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj = ? WHERE id = ?";

    try (
        Connection conn = ConectorBD.getConnection();
        PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
        PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
    ) {
        stmtPessoa.setString(1, pessoa.getNome());
        stmtPessoa.setString(2, pessoa.getLogradouro());
        stmtPessoa.setString(3, pessoa.getCidade());
        stmtPessoa.setString(4, pessoa.getEstado());
        stmtPessoa.setString(5, pessoa.getTelefone());
        stmtPessoa.setString(6, pessoa.getEmail());
        stmtPessoa.setInt(7, pessoa.getId());
        stmtPessoa.executeUpdate();

        stmtPessoaJuridica.setString(1, pessoa.getCnpj());
        stmtPessoaJuridica.setInt(2, pessoa.getId());
        stmtPessoaJuridica.executeUpdate();
    } catch (SQLException e) {
    }
}

public void excluir(int id) {
    String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE id = ?";
    String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

    try (
        Connection conn = ConectorBD.getConnection();
        PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
        PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
    ) {
        stmtPessoaJuridica.setInt(1, id);
        stmtPessoaJuridica.executeUpdate();

        stmtPessoa.setInt(1, id);
        stmtPessoa.executeUpdate();
    } catch (SQLException e) {
    }
}

}