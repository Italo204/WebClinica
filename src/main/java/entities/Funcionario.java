/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author italo-santos-mendes
 */

public class Funcionario{

    private Long id;

    private String nome;

    private String cargo;

    
    public Funcionario() {
    }


    public Funcionario(Long id, String nome, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }


    public Funcionario(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCargo() {
        return cargo;
    }


    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    @Override
    public String toString(){
        return this.nome;
    } 
    
}



