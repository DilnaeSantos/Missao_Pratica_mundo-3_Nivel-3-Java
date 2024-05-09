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

public class PessoaFisicaDAO {
    
    public PessoaFisica getPessoa(int id) {
        PessoaFisica pessoa = null;
        String sql = "SELECT * FROM Pessoa p INNER JOIN PessoaFisica pf ON p.id = pf.id WHERE p.id = ?";
        
        try (
            Connection conn = ConectorBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pessoa = new PessoaFisica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }
        } catch (SQLException e) {
        }
        
        return pessoa;
    }
    
    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa p INNER JOIN PessoaFisica pf ON p.id = pf.id";
        
        try (
            Connection conn = ConectorBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                PessoaFisica pessoa = new PessoaFisica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
        }
        
        return pessoas;
    }
    
    public void incluir(PessoaFisica pessoa) {
        String sqlPessoa = "INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";
        
        try (
            Connection conn = ConectorBD.getConnection();
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
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
            
            stmtPessoaFisica.setInt(1, id);
            stmtPessoaFisica.setString(2, pessoa.getCpf());
            stmtPessoaFisica.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void alterar(PessoaFisica pessoa) {
    String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
    String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE id = ?";

    try (
        Connection conn = ConectorBD.getConnection();
        PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
        PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
    ) {
        stmtPessoa.setString(1, pessoa.getNome());
        stmtPessoa.setString(2, pessoa.getLogradouro());
        stmtPessoa.setString(3, pessoa.getCidade());
        stmtPessoa.setString(4, pessoa.getEstado());
        stmtPessoa.setString(5, pessoa.getTelefone());
        stmtPessoa.setString(6, pessoa.getEmail());
        stmtPessoa.setInt(7, pessoa.getId());
        stmtPessoa.executeUpdate();

        stmtPessoaFisica.setString(1, pessoa.getCpf());
        stmtPessoaFisica.setInt(2, pessoa.getId());
        stmtPessoaFisica.executeUpdate();
    } catch (SQLException e) {
    }
}

public void excluir(int id) {
    String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id = ?";
    String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

    try (
        Connection conn = ConectorBD.getConnection();
        PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
        PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
    ) {
        stmtPessoaFisica.setInt(1, id);
        stmtPessoaFisica.executeUpdate();

        stmtPessoa.setInt(1, id);
        stmtPessoa.executeUpdate();
    } catch (SQLException e) {
    }
}
}