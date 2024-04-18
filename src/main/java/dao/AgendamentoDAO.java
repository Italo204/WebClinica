package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entities.Agendamento;
import interfaces.IDatabaseCRUD;
import utils.Database;
import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoDAO implements IDatabaseCRUD<Agendamento>{
    @Override
    public void save(Agendamento agendamento) throws SQLException {
        LocalDate DataAgendamento = agendamento.getData();
        String sql = "INSERT INTO agendamento(DATA, MEDICO, NOME) VALUES (?, ?, ?) ;";
        PreparedStatement ps = null;

        Date data = Date.valueOf(DataAgendamento);

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setDate(1, data);
            ps.setString(2, agendamento.getMedico());
            ps.setString(3, agendamento.getNome());

            ps.executeUpdate();
            ps.close();

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            Database.closeConnection();
        }
    }

    @Override
    public Agendamento search(Long id) throws SQLException {
        String sql = "SELECT A.IDAgendamento, A.Dia, A.motivo, " +
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
            Agendamento agendamento = null;
            if (result.next()){
                agendamento =  new Agendamento(result.getLong("IDAgendamento"), result.getDate("Dia").toLocalDate(), 
                result.getString("motivo"), result.getString("NomeMedico"), result.getString("Nome"));
            }
            return agendamento;
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
    public int update(Agendamento agendamento) throws SQLException{
        String sql = "UPDATE agendamento SET Dia = ?, motivo = ? WHERE IDAgendamento = ?;";
        PreparedStatement ps = null;
        LocalDate DiaAgen = agendamento.getData();

        try {
            Date Dia = Date.valueOf(DiaAgen);
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setDate(1, Dia);
            ps.setString(2, agendamento.getmotivo());
            ps.setLong(3, agendamento.getID());
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
    public ArrayList<Agendamento> findAll() throws SQLException {
        String sql = "SELECT A.IDAgendamento, A.Dia, A.motivo, " +
        "M.Nome AS NomeMedico, P.Nome AS NomePaciente " +
        "FROM Agendamento A "+
        "JOIN PACIENTE P ON A.IDPaciente = P.IDPaciente "+
        "LEFT JOIN Medico M ON A.IDMedico = M.IDMedico "+
        "LEFT JOIN Convenio C ON A.IDConvenio = C.IDConvenio ;";

        PreparedStatement ps = null;

        try {
            ps = Database.getConexao().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ArrayList<Agendamento> agendamento = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                LocalDate dia = rs.getDate("Dia").toLocalDate();
                String motivo = rs.getString("motivo");
                String Medico = rs.getString("Medico");
                String nome = rs.getString("Nome");

                Agendamento agendamentos = new Agendamento(id, dia, motivo, Medico, nome);
                agendamento.add(agendamentos);
            }
            return agendamento;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            Database.closeConnection();
        }
    }
}
