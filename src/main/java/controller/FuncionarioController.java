/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entities.Funcionario;
import services.FuncionarioServices;

/**
 *
 * @author italo-santos-mendes
 */

@WebServlet("/funcionario")
public class FuncionarioController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private final FuncionarioServices funcionarioServices = new FuncionarioServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long id = null;
        try {
            String idString = req.getParameter("ID");
            id = Long.parseLong(idString);
        } catch (Exception e) {
            res.getWriter().println("Não foi possível converter de String para Long");
        }
        if (id == null){
            List<Funcionario> funcionarios = new ArrayList<>();
            try {
                funcionarios = funcionarioServices.findAllFuncionarios();
            } catch (SQLException e) {
            }
            Gson gson = new Gson();
            String json = gson.toJson(funcionarios);
            res.setContentType("application/json");
            res.getWriter().write(json);
        } else{
            Funcionario Funcionario = new Funcionario();
            try {
                Funcionario = funcionarioServices.FindFuncionario(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            String json = gson.toJson(Funcionario);
            res.setContentType("application/json");
            res.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        try {
            String nome = req.getParameter("nome");
            String cargo = req.getParameter("cargo");
            Funcionario funcionario = new Funcionario(nome, cargo);
            try {
                funcionarioServices.addFuncionario(funcionario);
                res.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                res.getWriter().println("Erro ao adicionar funcionario: " + e.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
        } catch (Exception e) {
            res.getWriter().println("Erro: " + e.getMessage());
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        try {
            String idString = req.getParameter("ID");
            Long id = Long.parseLong(idString);
            try {
                funcionarioServices.deleteFuncionario(id);
                res.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                res.getWriter().println("Erro ao deletar: " + e.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
        } catch (Exception e) {
            res.getWriter().println("Erro ao deletar: " + e.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String nome = req.getParameter("nome");
            String idString = req.getParameter("ID");
            Long ID = Long.parseLong(idString);
            String cargo = req.getParameter("cargo");
            Funcionario funcionario = new Funcionario(ID, nome, cargo);
            try {
                funcionarioServices.updateFuncionario(funcionario);
                res.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                res.getWriter().println("Erro ao atualizar: " + e.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            res.getWriter().println("Erro ao atualizar: " + e.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    
}
