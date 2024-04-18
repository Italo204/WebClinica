package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entities.Consulta;
import interfaces.IDatabaseCRUD;
import utils.Database;

public class ConsultaDAO implements IDatabaseCRUD<Consulta>{

    @Override
    public void save(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consulta(Data, MEDICO, PACIENTE, MOTIVO) VALUES (?, ?, ?, ?) ;";
        PreparedStatement ps = null;

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setString(1, consulta.getData());
            ps.setString(2, consulta.getMedico());
            ps.setString(3, consulta.getPaciente());
            ps.setString(4, consulta.getMotivo());

            ps.executeUpdate();
            ps.close();

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            Database.closeConnection();
        }
    }

    @Override
    public Consulta search(Long id) throws SQLException {
        String sql = "SELECT A.IDAgendamento, A.Data, A.motivo, " +
        "M.Nome AS NomeMedico, P.Nome AS NomePaciente " +
        "FROM Agendamento A "+
        "JOIN PACIENTE P ON A.IDPaciente = P.IDPaciente "+
        "LEFT JOIN Medico M ON A.IDMedico = M.IDMedico "+
        "LEFT JOIN Convenio C ON A.IDConvenio = C.IDConvenio "+
        "WHERE A.IDAgendamento = ? ;";
        PreparedStatement ps = null;

        try{
            ps= Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            Consulta consulta = null;
            if (result.next()){
                consulta =  new Consulta(result.getLong("IDAgendamento"), result.getString("NomeMedico"), 
                result.getString("NomePaciente"), result.getString("Data"), result.getString("motivo"));
            }
            return consulta;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }
    }
    
    @Override
    public int delete(Long id) throws SQLException{
        String sql = "DELETE FROM agendamento WHERE IDAgendamento = ? ;";
        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return -1;
        } finally {
            Database.closeConnection();
        }

    }

    @Override
    public int update(Consulta consulta) throws SQLException{
        String sql = "UPDATE agendamento SET Data = ?, motivo = ? WHERE IDAgendamento = ?;";
        PreparedStatement ps = null;
        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setString(1, consulta.getData());
            ps.setString(2, consulta.getMotivo());
            ps.setLong(3, consulta.getID());
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
    public ArrayList<Consulta> findAll() throws SQLException {
        String sql = "SELECT A.IDAgendamento, A.Data, A.motivo, " +
        "M.Nome AS NomeMedico, P.Nome AS NomePaciente " +
        "FROM Agendamento A "+
        "JOIN PACIENTE P ON A.IDPaciente = P.IDPaciente "+
        "LEFT JOIN Medico M ON A.IDMedico = M.IDMedico "+
        "LEFT JOIN Convenio C ON A.IDConvenio = C.IDConvenio ;";

        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ArrayList<Consulta> consultas = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String data = rs.getString("Data");
                String motivo = rs.getString("motivo");
                String Medico = rs.getString("NomeMedico");
                String nome = rs.getString("NomePaciente");

                Consulta consulta = new Consulta(id,Medico , nome, data, motivo );
                consultas.add(consulta);
            }
            return consultas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }
    }
}
