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
        
        String sql = "INSERT INTO MEDICO(NOME, ESPECIALIDADE) VALUES (?, ?)";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setString(1, medico.getNome());
            ps.setString(2, medico.getEspecialidade());

            ps.executeUpdate();
            ps.close();

        } catch(SQLException e) {
        } finally {
            Database.closeConnection();
        }
    }

    @Override
    public Medico search(Long id) throws SQLException {
        
        String sql = "SELECT ID, NOME, ESPECIALIDADE FROM medico WHERE ID = ?";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            Medico medico = null;
            if (result.next()){
                medico =  new Medico(result.getLong("ID"), result.getString("NOME"), result.getString("ESPECIALIDADE"));
            }
            return medico;
        } catch(SQLException e) {
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
            return -1;
        } finally {
            Database.closeConnection();
        }
        
    }

    @Override
    public int update(Medico medico) {
        long id = medico.getId();
        
        PreparedStatement ps = null;
        Connection connection = Database.getConexao();
        
        try {
            connection = Database.getConexao();
            
            StringBuilder sql = new StringBuilder("UPDATE medico SET ");
            // Verifica quais campos estão sendo atualizados e adiciona ao StringBuilder
            boolean hasFieldsToUpdate = false;
            if (medico.getNome() != null) {
                sql.append("NOME = ?, ");
                hasFieldsToUpdate = true;
            }
            if (medico.getEspecialidade() != null) {
                sql.append("ESPECIALIDADE = ?, ");
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
            if (medico.getEspecialidade() != null) {
                ps.setString(index++, medico.getEspecialidade());
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
    public ArrayList<Medico> findAll() throws SQLException {
        String sql = "SELECT IDMedico, Sexo, Nome, Nascimento FROM medico ";
        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ArrayList<Medico> medicos = new ArrayList<>();
            while(rs.next()){
                long id = rs.getLong("ID");
                String especialidade = rs.getString("Especialidade");
                String nome = rs.getString("Nome");

                Medico medico = new Medico(id, nome, especialidade);
                medicos.add(medico);
            }
            return medicos;
        } catch (SQLException e) {
            
            return null;
        } finally {
            Database.closeConnection();
        }

        
    }
}
