/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author italo-santos-mendes
 */

 
public class Paciente{

    private Long ID;
    private String nome;
    private String dataNascimento;

    public Paciente(Long ID, String nome, String dataNascimento) {
        this.ID = ID;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Paciente(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString(){
        return this.nome;
    }

}

