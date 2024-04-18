package entities;

import java.time.LocalDate;

public class Agendamento {
    private Long ID;
    private LocalDate Data;
    private String motivo;
    private String medico;
    private String nome;

    public Agendamento(long ID, LocalDate data, String motivo, String medico, String nome) {
        this.Data = data;
        this.motivo = motivo;
        this.medico = medico;
        this.nome = nome;
        this.ID = ID;
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

    public String getmotivo() {
        return motivo;
    }

    public void setmotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
