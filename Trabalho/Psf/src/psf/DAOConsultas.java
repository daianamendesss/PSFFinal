/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author johan
 */
public class DAOConsultas {
    DAOMedicos daoMedicos = new DAOMedicos();
    DAOPaciente daoPacientes = new DAOPaciente();
    
    public List<Consultas> getLista() {
        String sql = "SELECT * FROM consultas;";
        List<Consultas> listaConsultas = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                Consultas objConsultas = new Consultas();
                objConsultas.setId(rs.getInt("codCONSULTA"));
                objConsultas.setObjMedico(daoMedicos.localizar(rs.getInt("MEDICOS_codMEDICOS")));
                objConsultas.setObjPaciente(daoPacientes.localizar(rs.getInt("PACIENTES_codPACIENTES")));
                objConsultas.setMotivo(rs.getString("motivo"));
                listaConsultas.add(objConsultas);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ex.getMessage());
        }
        return listaConsultas;
    }
    public boolean salvar(Consultas obj) {
        if (obj.getId()== null) {
            return incluir(obj);
        }
        else
        {
            return alterar(obj);
        }
    }
    public boolean incluir(Consultas objConsultas){
        String sql = "INSERT INTO consultas (MEDICOS_codMEDICOS, PACIENTES_codPACIENTES,motivo) values (?,?,?)";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objConsultas.getObjMedico().getId());
            pst.setInt(2, objConsultas.getObjPaciente().getId());
            pst.setString(3, objConsultas.getMotivo());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Consulta Incluida com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Consulta não Incluida com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    }
    public boolean alterar(Consultas objConsultas){
        String sql = "UPDATE consultas SET MEDICOS_codMEDICOS = ?, PACIENTES_codPACIENTES = ?, motivo=? WHERE codCONSULTA = ?;";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objConsultas.getObjMedico().getId());
            pst.setInt(2, objConsultas.getObjPaciente().getId());
            pst.setString(3, objConsultas.getMotivo());
            pst.setInt(4, objConsultas.getId());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Consulta Alterada com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Consulta não Alterada com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    } 
    public boolean remover(Consultas objConsultas){
        String sql = "DELETE FROM consultas WHERE codCONSULTA = ?;";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objConsultas.getId());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Consulta Apagada com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Consulta não Apagada com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    }  
}
