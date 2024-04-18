/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.ArrayList;

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
        try {
            String idString = req.getParameter("ID");
            Long id = Long.parseLong(idString);
            Atendente atendente = this.atendenteServices.FindAtendente(id);
            Gson gson = new Gson();
            String json = gson.toJson(atendente);
            res.setContentType("application/json");
            res.getWriter().write(json);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        
    }
    
    public void deleteAtendente(Atendente atendente) {
        try {
            this.atendenteServices.deleteAtendente(atendente.getID());
        } catch (SQLException e) {
            System.out.println("Não foi possível deletar atendente!");
        }
    }

    public void searchAtendente(Atendente atendente) {
        try {
            this.atendenteServices.FindAtendente(atendente.getID());
        } catch (SQLException e) {
            System.out.println("Não foi possível achar atendente!");
        }
    }

    public ArrayList<Atendente> findAllAtendente() {
        try {
            return this.atendenteServices.findAllAtendentes();
        } catch (SQLException e) {
            System.out.println("Não foi possível achar os dados");
            return null;
        }
    }
}
