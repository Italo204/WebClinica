/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import services.MedicoServices;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Medico;
/**
 *
 * @author italo-santos-mendes
 */
public class MedicoController {
    private final MedicoServices medicoServices;

    public MedicoController(){
        this.medicoServices = new MedicoServices();
    }
    
    public void saveMedico(Medico medico) {
        try{
            this.medicoServices.addMedico(medico);
        } catch(SQLException e) {
            System.out.println("Não foi possível cadastrar médico!");
        }
    }

    public void updateMedico(Medico medico){
        try {
            this.medicoServices.updateMedico(medico);
        } catch (SQLException e) {
            System.out.println("Não foi possível atualizar médico!");
        }
    }

    public void deleteMedico(Medico medico) {
        try {
            this.medicoServices.deleteMedico(medico.getID());
        } catch(SQLException e) {
            System.out.println("Não foi possível deletar médico!");
        }
    }

    public void searchMedico(Medico medico) {
        try {
            this.medicoServices.searchMedico(medico.getID());
        } catch (SQLException e){
            System.out.println("Não foi possível achar médico!");
        }
    }

    public ArrayList<Medico> findAllMedicos() {
        try{
            return this.medicoServices.findAllMedicos();
        } catch(SQLException e) {
            System.out.println("Não foi possível achar os dados.");
            return null;
        }

    }
}
