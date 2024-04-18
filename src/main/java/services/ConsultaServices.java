package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConsultaDAO;
import entities.Consulta;

public class ConsultaServices {
    private final ConsultaDAO AgendamentoDAO;

    public ConsultaServices() {
        this.AgendamentoDAO =  new ConsultaDAO();
    }

    public void addAgendamento(Consulta agendamento) throws SQLException{
        this.AgendamentoDAO.save(agendamento);
    }

    public Consulta searchAgendamento(long id) throws SQLException {
        return this.AgendamentoDAO.search(id);
    }

    public int deleteAgendamento(long id) throws SQLException {
        return this.AgendamentoDAO.delete(id);
    }

    public int updateAgendamento(Consulta agendamento) throws SQLException {
        return this.AgendamentoDAO.update(agendamento);
    }

    public ArrayList<Consulta> findAllAgendamentos() throws SQLException {
        return this.AgendamentoDAO.findAll();
    }
}
