/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.FuncionarioDAO;
import entities.Funcionario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author italo-santos-mendes
 */

public class FuncionarioServices {
    
    private final FuncionarioDAO FuncionarioDAO;

    public FuncionarioServices() {
        this.FuncionarioDAO = new FuncionarioDAO();
    }

    public void addFuncionario(Funcionario Funcionario) throws SQLException {
        this.FuncionarioDAO.save(Funcionario);
    }

    public Funcionario FindFuncionario(long id) throws SQLException {
        return this.FuncionarioDAO.search(id);
    }

    public int deleteFuncionario(long id) throws SQLException {
        return this.FuncionarioDAO.delete(id);
    }

    public int updateFuncionario(Funcionario Funcionario) throws SQLException {
        return this.FuncionarioDAO.update(Funcionario);
    }

    public ArrayList<Funcionario> findAllFuncionarios() throws SQLException {
        return this.FuncionarioDAO.findAll();
    }
}
