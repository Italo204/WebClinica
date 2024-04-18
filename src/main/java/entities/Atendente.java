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

public class Atendente extends Pessoa{
    
    public Atendente (String nome, String telefone, String sexo, LocalDate nascimento) {
        super(nome, telefone, sexo, nascimento);
    }

    public Atendente (Long ID, String nome, String telefone, String sexo, LocalDate nascimento) {
        super(ID, nome, telefone, sexo, nascimento);
    }

    public Atendente () {
    }

    @Override
    public String toString(){
        return this.nome;
    } 
    
}



