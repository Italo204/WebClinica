/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author italo-santos-mendes
 */
public class Medico extends Pessoa {

      public Medico(long ID, String nome, String email, String senha, String CPF, String telefone, String sexo, LocalDate nascimento) {
        super(ID, nome, email, senha, CPF, telefone, sexo, nascimento);
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
