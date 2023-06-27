
package psf;

/**
 *
 * @author 15385404608
 */
public class Receitas {
   private Medicos objMedico;
   private String nomeRemedio;
   private String dataEntrega;
   

    /**
     * @return the nomeRemedio
     */
    public String getNomeRemedio() {
        return nomeRemedio;
    }

    /**
     * @param nomeRemedio the nomeRemedio to set
     */
    public void setNomeRemedio(String nomeRemedio) {
        this.nomeRemedio = nomeRemedio;
    }

    /**
     * @return the dataEntrega
     */
    public String getDataEntrega() {
        return dataEntrega;
    }

    /**
     * @param dataEntrega the dataEntrega to set
     */
    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Medicos getObjMedico() {
        return objMedico;
    }

    public void setObjMedico(Medicos objMedico) {
        this.objMedico = objMedico;
    }
   
}

