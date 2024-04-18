/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.AtendenteDAO;
import entities.Atendente;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author italo-santos-mendes
 */

public class AtendenteServices {
    
    private final AtendenteDAO AtendenteDAO;

    public AtendenteServices() {
        this.AtendenteDAO = new AtendenteDAO();
    }

    public void addAtendente(Atendente atendente) throws SQLException {
        this.AtendenteDAO.save(atendente);
    }

    public Atendente FindAtendente(long id) throws SQLException {
        return this.AtendenteDAO.search(id);
    }

    public int deleteAtendente(long id) throws SQLException {
        return this.AtendenteDAO.delete(id);
    }

    public int updateAtendente(Atendente atendente) throws SQLException {
        return this.AtendenteDAO.update(atendente);
    }

    public ArrayList<Atendente> findAllAtendentes() throws SQLException {
        return this.AtendenteDAO.findAll();
    }
}
