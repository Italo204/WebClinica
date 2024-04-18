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
import entities.Paciente;
import interfaces.IDatabaseCRUD;
import utils.Database;
/**
 *
 * @author italo-santos-mendes
 */
public class PacienteDAO implements IDatabaseCRUD<Paciente>{
    @Override
    public void save(Paciente paciente) throws SQLException {
        LocalDate dataNas = paciente.getNascimento();
        
        String sql = "INSERT INTO paciente(Nome, email, CPF, Telefone, Sexo, Nascimento) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
       
        Date nascimento = Date.valueOf(dataNas);

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getEmail());
            ps.setString(3, paciente.getCPF());
            ps.setString(4, paciente.getTelefone());
            ps.setString(5, paciente.getSexo());
            ps.setDate(6, nascimento);

            ps.executeUpdate();
            ps.close();

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            Database.closeConnection();
        }
    }

    @Override
    public Paciente search(Long id) throws SQLException {
        
        String sql = "SELECT * FROM paciente WHERE IDPaciente = ?";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            Paciente paciente = null;
            if (result.next()){
                paciente =  new Paciente(result.getLong("IDPaciente"), result.getString("Nome"), result.getString("email"),
                result.getString("CPF"), result.getString("Telefone"), result.getString("Sexo"), result.getDate("Nascimento").toLocalDate());
            }
            return paciente;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }
    }
    
    @Override
    public int delete(Long id) throws SQLException{
        String sql = "DELETE FROM paciente WHERE IDPaciente = ?";
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
    public int update(Paciente paciente) {
        long id = paciente.getID();
        
        PreparedStatement ps = null;
        Connection connection = Database.getConexao();
        
        try {
            connection = Database.getConexao();
            
            StringBuilder sql = new StringBuilder("UPDATE paciente SET ");
            // Verifica quais campos estão sendo atualizados e adiciona ao StringBuilder
            boolean hasFieldsToUpdate = false;
            if (paciente.getNome() != null) {
                sql.append("nome = ?, ");
                hasFieldsToUpdate = true;
            }
            if (paciente.getEmail() != null) {
                sql.append("email = ?, ");
                hasFieldsToUpdate = true;
            }
            if (paciente.getCPF() != null) {
                sql.append("CPF = ?, ");
                hasFieldsToUpdate = true;
            }
            if (paciente.getTelefone() != null) {
                sql.append("telefone = ?, ");
                hasFieldsToUpdate = true;
            }
            if (paciente.getSexo() != null) {
                sql.append("sexo = ?, ");
                hasFieldsToUpdate = true;
            }
            if (paciente.getNascimento() != null) {
                sql.append("nascimento = ?, ");
                hasFieldsToUpdate = true;
            }
            
            if (!hasFieldsToUpdate) {
                JOptionPane.showMessageDialog(null, "NENHUMA ATUALIZAÇÂO PENDENTE!", "ERRO", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            
            // Remove a última vírgula adicionada ao final do StringBuilder
            sql.delete(sql.length() - 2, sql.length());
            
            sql.append(" WHERE IDPaciente = ?");
            
            ps = connection.prepareStatement(sql.toString());
            
            // Define os valores dos parâmetros de acordo com os campos a serem atualizados
            int index = 1;
            if (paciente.getNome() != null) {
                ps.setString(index++, paciente.getNome());
            }
            if (paciente.getEmail() != null) {
                ps.setString(index++, paciente.getEmail());
            }
            if (paciente.getCPF() != null) {
                ps.setString(index++, paciente.getCPF());
            }
            if (paciente.getTelefone() != null) {
                ps.setString(index++, paciente.getTelefone());
            }
            if (paciente.getSexo() != null) {
                ps.setString(index++, paciente.getSexo());
            }
            if (paciente.getNascimento() != null) {
                ps.setDate(index++, java.sql.Date.valueOf(paciente.getNascimento()));
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
    public ArrayList<Paciente> findAll() throws SQLException {
        String sql = "SELECT IDPaciente, Telefone, CPF, Sexo, email, Nome, Nascimento FROM paciente";
        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ArrayList<Paciente> paciente = new ArrayList<>();
            while(rs.next()){
                long id = rs.getLong("IDPaciente");
                String telefone = rs.getString("Telefone");
                String cpf = rs.getString("CPF");
                String sexo = rs.getString("Sexo");
                String email = rs.getString("email");
                String nome = rs.getString("Nome");
                LocalDate nascimento = rs.getDate("Nascimento").toLocalDate();

                Paciente pacientes = new Paciente(id, nome, email, cpf, telefone, sexo, nascimento);
                paciente.add(pacientes);
            }
            return paciente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }

        
    }
}
