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
        LocalTime horaTime = agendamento.getHora();
        String sql = "INSERT INTO agendamento(DATA, CPF, OBSERVACAO, TIPOCONSULTA, MEDICO, CONVENIO, NOME, HORA) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ;";
        PreparedStatement ps = null;

        Date data = Date.valueOf(DataAgendamento);
        Time hora = Time.valueOf(horaTime);

        try{
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setDate(1, data);
            ps.setString(2, agendamento.getCPF());
            ps.setString(3, agendamento.getObservacao());
            ps.setString(4, agendamento.getTipoConsulta());
            ps.setString(5, agendamento.getMedico());
            ps.setString(6, agendamento.getConvenio());
            ps.setString(7, agendamento.getNome());
            ps.setTime(8, hora);

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
        String sql = "SELECT A.IDAgendamento, A.Dia, P.CPF, A.observacoes, A.TipoConsulta, " +
        "M.Nome AS NomeMedico, C.Nome AS NomeConvenio, P.Nome AS NomePaciente, A.hora " +
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
                agendamento =  new Agendamento(result.getLong("IDAgendamento"), result.getDate("Dia").toLocalDate(), result.getString("CPF"), 
                result.getString("observacoes"), result.getString("TipoConsulta"), result.getString("NomeMedico"), 
                result.getString("NomeConvenio"), result.getString("Nome"), result.getTime("hora").toLocalTime());
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
        String sql = "UPDATE agendamento SET Dia = ?, hora = ?, TipoConsulta = ? WHERE IDAgendamento = ?;";
        PreparedStatement ps = null;
        LocalDate DiaAgen = agendamento.getData();
        LocalTime horaAgen = agendamento.getHora();

        try {
            Date Dia = Date.valueOf(DiaAgen);
            Time hora = Time.valueOf(horaAgen);
            ps = Database.getConexao().prepareStatement(sql.toString());
            ps.setDate(1, Dia);
            ps.setTime(2, hora);
            ps.setString(3, agendamento.getTipoConsulta());
            ps.setLong(4, agendamento.getID());
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
        String sql = "SELECT A.IDAgendamento, A.Dia, P.CPF, A.observacoes, A.TipoConsulta, " +
        "M.Nome AS NomeMedico, C.Nome AS NomeConvenio, P.Nome AS NomePaciente, A.hora " +
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
                String cpf = rs.getString("CPF");
                String observacao = rs.getString("observacoes");
                String TipoConsulta = rs.getString("TipoCosnulta");
                String Medico = rs.getString("Medico");
                String convenio = rs.getString("Convenio");
                String nome = rs.getString("Nome");
                LocalTime hora = rs.getTime("hora").toLocalTime();

                Agendamento agendamentos = new Agendamento(id, dia, cpf, observacao, TipoConsulta, Medico, convenio, nome, hora);
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
