/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interfaces.IDatabaseCRUD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entities.Funcionario;
import utils.Database;



/**
 *
 * @author italo-santos-mendes
 */

public class FuncionarioDAO implements IDatabaseCRUD<Funcionario>{

    @Override
    public void save(Funcionario funcionario) throws SQLException {
        
        String sql = "INSERT INTO funcionario(NOME, CARGO) VALUES (?, ?);";
        PreparedStatement ps = null;
       
        

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCargo());

            ps.executeUpdate();
            ps.close();

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            Database.closeConnection();
        }
    }

    @Override
    public Funcionario search(Long id) throws SQLException {
        
        String sql = "SELECT * FROM funcionario WHERE ID = ?;";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            Funcionario funcionario = null;
            if (result.next()){
                funcionario =  new Funcionario(result.getLong("ID"), result.getString("nome"), result.getString("cargo"));
            }
            return funcionario;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }
    }
    
    @Override
    public int delete(Long id) throws SQLException{
        String sql = "DELETE FROM funcionario WHERE ID = ?";
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
    public int update(Funcionario funcionario) {
        long id = funcionario.getId();
        
        PreparedStatement ps = null;
        Connection connection = Database.getConexao();
        
        try {
            connection = Database.getConexao();
            
            StringBuilder sql = new StringBuilder("UPDATE funcionario SET ");
            // Lista para armazenar os campos a serem atualizados
            List<String> campos = new ArrayList<>();
            
            if (funcionario.getNome() != null) {
                sql.append("nome = ?, ");
                campos.add(funcionario.getNome());
            }
            
            if (funcionario.getCargo() != null) {
                sql.append("cargo = ?, ");
                campos.add(funcionario.getCargo());
            }
            
            // Remove a última vírgula adicionada ao final do StringBuilder
            sql.delete(sql.length() - 2, sql.length());
            
            sql.append(" WHERE ID = ?");
            
            ps = connection.prepareStatement(sql.toString());
            
            // Define os valores dos parâmetros de acordo com os campos a serem atualizados
            int index = 1;
            for (String campo : campos) {
                ps.setObject(index++, campo);
            }
            
            ps.setLong(index, id);
            
            int result = ps.executeUpdate();
            
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
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
    public ArrayList<Funcionario> findAll() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            while(rs.next()){
                long id = rs.getLong("ID");
                String cargo = rs.getString("cargo");
                String nome = rs.getString("nome");

                Funcionario funcionario = new Funcionario(id, nome, cargo);
                funcionarios.add(funcionario);
            }
            return funcionarios;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }

        
    }

}
