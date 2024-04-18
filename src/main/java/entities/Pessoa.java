package entities;

import java.time.LocalDate;

public abstract class Pessoa {
    protected long ID;
    protected String nome;
    protected String telefone;
    protected String sexo;
    protected LocalDate nascimento;
    
    public Pessoa(){   
    }
    
    public Pessoa(String nome, String telefone, String sexo, LocalDate nascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.nascimento = nascimento;
    }

    public Pessoa(long iD, String nome, String email, String CPF, String telefone, String sexo, LocalDate nascimento) {
        this.ID = iD;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.nascimento = nascimento;
    }

    public Pessoa(Long ID, String nome, String telefone, String sexo, LocalDate nascimento) {
        this.ID = ID;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.nascimento = nascimento; 
    }

    public Pessoa(long ID, String nome, String telefone, String sexo, LocalDate nascimento) {
        this.ID = ID;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.nascimento = nascimento; 
    }

    public void setID(long iD) {
        this.ID = iD;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public long getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    
}
