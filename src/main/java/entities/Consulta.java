package entities;

public class Consulta {
    private Long ID;

    private String medico;

    private String paciente;

    private String data;

    private String motivo;

    public Consulta(Long iD, String medico, String paciente, String data, String motivo) {
        ID = iD;
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.motivo = motivo;
    }

    public Consulta(String medico, String paciente, String data, String motivo) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.motivo = motivo;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public String getMedico() {
        return medico.toString();
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente.toString();
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
}
