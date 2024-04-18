/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entities.Atendente;
import services.AtendenteServices;

/**
 *
 * @author italo-santos-mendes
 */

 @WebServlet("/Atendente")
public class AtendenteController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private final AtendenteServices atendenteServices = new AtendenteServices();

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
            List<Atendente> atendentes = new ArrayList<>();
            try {
                atendentes = atendenteServices.findAllAtendentes();
            } catch (SQLException e) {
            }
            Gson gson = new Gson();
            String json = gson.toJson(atendentes);
            res.setContentType("application/json");
            res.getWriter().write(json);
        } else{
            Atendente atendente = new Atendente();
            try {
                atendente = atendenteServices.FindAtendente(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            String json = gson.toJson(atendente);
            res.setContentType("application/json");
            res.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        try {
            String nome = req.getParameter("nome");
            String telefone = req.getParameter("telefone");
            String sexo = req.getParameter("sexo");
            String nascimentoString = req.getParameter("nascimento");
            LocalDate nascimento = LocalDate.parse(nascimentoString);
            Atendente atendente = new Atendente(nome, telefone, sexo, nascimento);
            try {
                atendenteServices.addAtendente(atendente);
                res.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                res.getWriter().println("Erro ao adicionar atendente: " + e.getMessage());
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
                atendenteServices.deleteAtendente(id);
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
            String telefone = req.getParameter("telefone");
            String sexo = req.getParameter("sexo");
            String nascimentoString = req.getParameter("nascimento");
            LocalDate nascimento = LocalDate.parse(nascimentoString);
            Atendente atendente = new Atendente(nome, telefone, sexo, nascimento);
            try {
                atendenteServices.updateAtendente(atendente);
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
