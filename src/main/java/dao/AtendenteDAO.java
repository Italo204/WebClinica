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
import entities.Atendente;
import utils.Database;



/**
 *
 * @author italo-santos-mendes
 */

public class AtendenteDAO implements IDatabaseCRUD<Atendente>{

    @Override
    public void save(Atendente atendente) throws SQLException {
        LocalDate dataNas = atendente.getNascimento();
        
        String sql = "INSERT INTO atendente(NOME, EMAIL, SENHA, CPF, TELEFONE, SEXO, NASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
       
        Date nascimento = Date.valueOf(dataNas);

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setString(1, atendente.getNome());
            ps.setString(2, atendente.getEmail());
            ps.setString(3, atendente.getSenha());
            ps.setString(4, atendente.getCPF());
            ps.setString(5, atendente.getTelefone());
            ps.setString(6, atendente.getSexo());
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
    public Atendente search(Long id) throws SQLException {
        
        String sql = "SELECT * FROM atendente WHERE IDAtendente = ?";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            Atendente atendente = null;
            if (result.next()){
                atendente =  new Atendente(result.getLong("IDAtendente"), result.getString("nome"), result.getString("email"),result.getString("senha"),
                result.getString("CPF"), result.getString("telefone"), result.getString("sexo"), result.getDate("nascimento").toLocalDate());
            }
            return atendente;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }
    }
    
    @Override
    public int delete(Long id) throws SQLException{
        String sql = "DELETE FROM atendente WHERE IDAtendente = ?";
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
    public int update(Atendente atendente) {
        long id = atendente.getID();
        
        PreparedStatement ps = null;
        Connection connection = Database.getConexao();
        
        try {
            connection = Database.getConexao();
            
            StringBuilder sql = new StringBuilder("UPDATE atendente SET ");
            // Lista para armazenar os campos a serem atualizados
            List<String> campos = new ArrayList<>();
            
            if (atendente.getNome() != null) {
                sql.append("nome = ?, ");
                campos.add(atendente.getNome());
            }
            
            if (atendente.getEmail() != null) {
                sql.append("email = ?, ");
                campos.add(atendente.getEmail());
            }
            
            if (atendente.getSenha() != null) {
                sql.append("senha = ?, ");
                campos.add(atendente.getSenha());
            }
            
            if (atendente.getCPF() != null) {
                sql.append("CPF = ?, ");
                campos.add(atendente.getCPF());
            }
            
            if (atendente.getTelefone() != null) {
                sql.append("telefone = ?, ");
                campos.add(atendente.getTelefone());
            }
            
            if (atendente.getSexo() != null) {
                sql.append("sexo = ?, ");
                campos.add(atendente.getSexo());
            }
            
            if (atendente.getNascimento() != null) {
                sql.append("nascimento = ?, ");
                campos.add(atendente.getNascimento().toString()); // Supondo que getNascimento() retorna uma String formatada
            }
            
            // Remove a última vírgula adicionada ao final do StringBuilder
            sql.delete(sql.length() - 2, sql.length());
            
            sql.append(" WHERE IDAtendente = ?");
            
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
    public ArrayList<Atendente> findAll() throws SQLException {
        String sql = "SELECT * FROM atendente";
        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ArrayList<Atendente> atendente = new ArrayList<>();
            while(rs.next()){
                long id = rs.getLong("IDAtendente");
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("CPF");
                String sexo = rs.getString("sexo");
                String email = rs.getString("email");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                LocalDate nascimento = rs.getDate("nascimento").toLocalDate();

                Atendente atendentes = new Atendente(id, nome, email, senha, cpf, telefone, sexo, nascimento);
                atendente.add(atendentes);
            }
            return atendente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }

        
    }

}
