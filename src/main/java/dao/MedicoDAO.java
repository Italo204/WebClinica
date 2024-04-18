/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entities.Medico;
import interfaces.IDatabaseCRUD;
import utils.Database;

/**
 *
 * @author italo-santos-mendes
 */
public class MedicoDAO implements IDatabaseCRUD<Medico>{
    

    @Override
    public void save(Medico medico) throws SQLException {
        LocalDate dataNas = medico.getNascimento();
        
        String sql = "INSERT INTO MEDICO(NOME, EMAIL, SENHA, CPF, TELEFONE, SEXO, NASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
       
        Date nascimento = Date.valueOf(dataNas);

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setString(1, medico.getNome());
            ps.setString(2, medico.getEmail());
            ps.setString(3, medico.getSenha());
            ps.setString(4, medico.getCPF());
            ps.setString(5, medico.getTelefone());
            ps.setString(6, medico.getSexo());
            ps.setDate(7, nascimento);

            ps.executeUpdate();
            ps.close();

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            Database.closeConnection();
        }
    }

    @Override
    public Medico search(Long id) throws SQLException {
        
        String sql = "SELECT IDMedico, Telefone, CPF, Sexo, Email, Nome, Senha, Nascimento FROM medico WHERE IDMedico = ?";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            Medico medico = null;
            if (result.next()){
                medico =  new Medico(result.getLong("IDMedico"), result.getString("Telefone"), result.getString("CPF"),result.getString("Sexo"),
                result.getString("Email"), result.getString("Nome"), result.getString("Senha"), result.getDate("Nascimento").toLocalDate());
            }
            return medico;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }
    }
    
    @Override
    public int delete(Long id) throws SQLException{
        String sql = "DELETE FROM medico WHERE IDMedico = ?";
        PreparedStatement ps = null;
        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return -1;
        } finally {
            Database.closeConnection();
        }
        
    }

    @Override
    public int update(Medico medico) {
        long id = medico.getID();
        
        PreparedStatement ps = null;
        Connection connection = Database.getConexao();
        
        try {
            connection = Database.getConexao();
            
            StringBuilder sql = new StringBuilder("UPDATE medico SET ");
            // Verifica quais campos estão sendo atualizados e adiciona ao StringBuilder
            boolean hasFieldsToUpdate = false;
            if (medico.getNome() != null) {
                sql.append("nome = ?, ");
                hasFieldsToUpdate = true;
            }
            if (medico.getEmail() != null) {
                sql.append("email = ?, ");
                hasFieldsToUpdate = true;
            }
            if (medico.getSenha() != null) {
                sql.append("senha = ?, ");
                hasFieldsToUpdate = true;
            }
            if (medico.getCPF() != null) {
                sql.append("CPF = ?, ");
                hasFieldsToUpdate = true;
            }
            if (medico.getTelefone() != null) {
                sql.append("telefone = ?, ");
                hasFieldsToUpdate = true;
            }
            if (medico.getSexo() != null) {
                sql.append("sexo = ?, ");
                hasFieldsToUpdate = true;
            }
            if (medico.getNascimento() != null) {
                sql.append("nascimento = ?, ");
                hasFieldsToUpdate = true;
            }
            
            if (!hasFieldsToUpdate) {
                System.out.println("Nenhuma atualização pendente!");
                return 0;
            }
            
            // Remove a última vírgula adicionada ao final do StringBuilder
            sql.delete(sql.length() - 2, sql.length());
            
            sql.append(" WHERE IDMedico = ?");
            
            ps = connection.prepareStatement(sql.toString());
            
            // Define os valores dos parâmetros de acordo com os campos a serem atualizados
            int index = 1;
            if (medico.getNome() != null) {
                ps.setString(index++, medico.getNome());
            }
            if (medico.getEmail() != null) {
                ps.setString(index++, medico.getEmail());
            }
            if (medico.getSenha() != null) {
                ps.setString(index++, medico.getSenha());
            }
            if (medico.getCPF() != null) {
                ps.setString(index++, medico.getCPF());
            }
            if (medico.getTelefone() != null) {
                ps.setString(index++, medico.getTelefone());
            }
            if (medico.getSexo() != null) {
                ps.setString(index++, medico.getSexo());
            }
            if (medico.getNascimento() != null) {
                ps.setDate(index++, java.sql.Date.valueOf(medico.getNascimento()));
            }
            
            ps.setLong(index, id);
            
            int result = ps.executeUpdate();
            
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: "+ e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return -1;
        } finally {
            // Fechando recursos
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ArrayList<Medico> findAll() throws SQLException {
        String sql = "SELECT IDMedico, Telefone, CPF, Sexo, Email, Nome, Senha, Nascimento FROM medico ";
        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ArrayList<Medico> medico = new ArrayList<>();
            while(rs.next()){
                long id = rs.getLong("IDMedico");
                String telefone = rs.getString("Telefone");
                String cpf = rs.getString("CPF");
                String sexo = rs.getString("sexo");
                String email = rs.getString("Email");
                String nome = rs.getString("Nome");
                String senha = rs.getString("Senha");
                LocalDate nascimento = rs.getDate("Nascimento").toLocalDate();

                Medico medicos = new Medico(id, nome, email, senha, cpf, telefone, sexo, nascimento);
                medico.add(medicos);
            }
            return medico;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }

        
    }
}
