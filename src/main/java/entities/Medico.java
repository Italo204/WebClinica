/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author italo-santos-mendes
 */
public class Medico {

    private Long ID;
    private String nome;
    private String especialidade;

    
    public Medico() {
    }

    public Medico(String nome, String especialidade){  
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Medico(Long ID, String nome, String especialidade) {
        this.ID = ID;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    

    public Long getId() {
        return ID;
    }

    public void setId(Long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
