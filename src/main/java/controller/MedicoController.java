/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import services.MedicoServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entities.Medico;
/**
 *
 * @author italo-santos-mendes
 */
public class MedicoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MedicoServices medicoServices = new MedicoServices();

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
            List<Medico> medicos = new ArrayList<>();
            try {
                medicos = medicoServices.findAllMedicos();
            } catch (SQLException e) {
            }
            Gson gson = new Gson();
            String json = gson.toJson(medicos);
            res.setContentType("application/json");
            res.getWriter().write(json);
        } else{
            Medico medico = new Medico();
            try {
                medico = medicoServices.searchMedico(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            String json = gson.toJson(medico);
            res.setContentType("application/json");
            res.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        try {
            String nome = req.getParameter("nome");
            String especialidade = req.getParameter("especialidade");
            Medico medico = new Medico(nome, especialidade);
            try {
                medicoServices.addMedico(medico);
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
                medicoServices.deleteMedico(id);
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
            String especialidade = req.getParameter("especialidade");
            Medico medico = new Medico(ID, nome, especialidade);
            try {
                medicoServices.updateMedico(medico);
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
