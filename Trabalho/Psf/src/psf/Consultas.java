
package psf;

import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author 15385404608
 */
public class Consultas {
   private Paciente objPaciente = new Paciente(); 
   private Medicos objMedico = new Medicos();
   //private Calendar dataEntrada;
   private String motivo;
   private Integer id;

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
   /**
     * @return the objPaciente
     */
    public Paciente getObjPaciente() {
        return objPaciente;
    }

    /**
     * @param objPaciente the objPaciente to set
     */
    public void setObjPaciente(Paciente objPaciente) {
        this.objPaciente = objPaciente;
    }

    /**
     * @return the objMedico
     */
    public Medicos getObjMedico() {
        return objMedico;
    }

    /**
     * @param objMedico the objMedico to set
     */
    public void setObjMedico(Medicos objMedico) {
        this.objMedico = objMedico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consultas other = (Consultas) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
   
    
    
}

