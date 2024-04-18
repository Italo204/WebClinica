/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Paciente;
import services.PacienteServices;

/**
 *
 * @author italo-santos-mendes
 */
public class PacienteController {
    private final PacienteServices pacienteServices;

    public PacienteController() {
        this.pacienteServices = new PacienteServices();
    }

    public void savePaciente(Paciente paciente) {
        try{
            this.pacienteServices.addPaciente(paciente);
        } catch (SQLException e) {
            System.out.println("Não foi possível cadastrar paciente!");
        }
    }

    public void deletePaciente(Paciente paciente) {
        try {
            this.pacienteServices.deletePaciente(paciente.getID());
        } catch (SQLException e) {
            System.out.println("Não foi possível deletar paciente!");
        }
    }

    public void updatePaciente(Paciente paciente) {
        try {
            this.pacienteServices.updatePaciente(paciente);
        } catch (SQLException e) {
            System.out.println("Não foi posível atualizar paciente!");
        }
    }

    public void searchPaciente(Paciente paciente) {
        try {
            this.pacienteServices.searchPaciente(paciente.getID());
        } catch (SQLException e) {
            System.out.println("Não foi possível localizar paciente!");
        }
    }

    public ArrayList<Paciente> findAllPacientes() {
        try {
            return this.pacienteServices.findAllPaciente();
        } catch (SQLException e) {
            System.out.println("Não foi possível cadastrar paciente!");
            return null;
        }
    }
}
