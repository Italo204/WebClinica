/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        
        String sql = "INSERT INTO paciente(Nome, Nascimento) VALUES (?, ?)";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getDataNascimento());

            ps.executeUpdate();
            ps.close();

        } catch(SQLException e) {
            
        } finally {
            Database.closeConnection();
        }
    }

    @Override
    public Paciente search(Long id) throws SQLException {
        
        String sql = "SELECT * FROM paciente WHERE ID = ?";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            Paciente paciente = null;
            if (result.next()){
                paciente =  new Paciente(result.getLong("ID"), result.getString("Nome"), result.getString("Nascimento"));
            }
            return paciente;
        } catch(SQLException e) {
            return null;
        } finally {
            Database.closeConnection();
        }
    }
    
    @Override
    public int delete(Long id) throws SQLException{
        String sql = "DELETE FROM paciente WHERE ID = ?";
        PreparedStatement ps = null;
        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
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
                sql.append("Nome = ?, ");
                hasFieldsToUpdate = true;
            }
            if (paciente.getDataNascimento() != null) {
                sql.append("Nascimento = ?, ");
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
            if (paciente.getDataNascimento() != null) {
                ps.setDate(index++, java.sql.Date.valueOf(paciente.getDataNascimento()));
            }
            
            ps.setLong(index, id);
            
            int result = ps.executeUpdate();
            
            return result;
        } catch (SQLException e) {
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
        String sql = "SELECT ID, Nome, Nascimento FROM paciente";
        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ArrayList<Paciente> pacientes = new ArrayList<>();
            while(rs.next()){
                long id = rs.getLong("IDPaciente");
                String nome = rs.getString("Nome");
                String nascimento = rs.getString("Nascimento");

                Paciente paciente = new Paciente(id, nome, nascimento);
                pacientes.add(paciente);
            }
            return pacientes;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }

        
    }
}
