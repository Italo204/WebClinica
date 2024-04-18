package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
    private long ID;
    private LocalDate Data;
    private LocalTime hora;
    private String CPF;
    private String Observacao;
    private String tipoConsulta;
    private String medico;
    private String convenio;
    private String nome;

    public Agendamento(long ID, LocalDate data, String CPF, String observacao, String tipoConsulta, String medico,
            String convenio, String nome, LocalTime hora) {
        this.Data = data;
        this.CPF = CPF;
        this.Observacao = observacao;
        this.tipoConsulta = tipoConsulta;
        this.medico = medico;
        this.convenio = convenio;
        this.nome = nome;
        this.ID = ID;
        this.hora = hora;
    }

    public long getID() {
        return ID;
    }

    public void setID(long iD) {
        this.ID = iD;
    }

    public LocalDate getData() {
        return Data;
    }

    public void setData(LocalDate data) {
        this.Data = data;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        this.CPF = cPF;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        this.Observacao = observacao;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    

    
}
