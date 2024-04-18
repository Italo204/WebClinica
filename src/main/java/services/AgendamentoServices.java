package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AgendamentoDAO;
import entities.Agendamento;

public class AgendamentoServices {
    private final AgendamentoDAO AgendamentoDAO;

    public AgendamentoServices() {
        this.AgendamentoDAO =  new AgendamentoDAO();
    }

    public void addAgendamento(Agendamento agendamento) throws SQLException{
        this.AgendamentoDAO.save(agendamento);
    }

    public Agendamento searchAgendamento(long id) throws SQLException {
        return this.AgendamentoDAO.search(id);
    }

    public int deleteAgendamento(long id) throws SQLException {
        return this.AgendamentoDAO.delete(id);
    }

    public int updateAgendamento(Agendamento agendamento) throws SQLException {
        return this.AgendamentoDAO.update(agendamento);
    }

    public ArrayList<Agendamento> findAllAgendamentos() throws SQLException {
        return this.AgendamentoDAO.findAll();
    }
}
