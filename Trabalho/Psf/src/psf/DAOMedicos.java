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
public class DAOMedicos {
    public List<Medicos> getLista() {
        String sql = "SELECT * FROM medicos;";
        List<Medicos> listaMedicos = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                Medicos objMedicos = new Medicos();
                objMedicos.setId(rs.getInt("codMEDICOS"));
                objMedicos.setNome(rs.getString("nome"));
                objMedicos.setCrm(rs.getString("crm"));
                listaMedicos.add(objMedicos);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ex.getMessage());
        }
        return listaMedicos;
    }
    
    public Medicos localizar(Integer id){
        String sql = "SELECT * FROM medicos WHERE codMEDICOS = ?;";
        Medicos objMedicos = new Medicos();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                objMedicos.setId(rs.getInt("codMEDICOS"));
                objMedicos.setNome(rs.getString("nome"));
                objMedicos.setCrm(rs.getString("crm"));
                return objMedicos;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ex.getMessage());
        }
        return null;
    }
    
    public boolean salvar(Medicos obj) {
        if (obj.getId()== null) {
            return incluir(obj);
        }
        else
        {
            return alterar(obj);
        }
    }
    
    public boolean incluir(Medicos objMedicos){
        String sql = "INSERT INTO medicos (nome, crm) values (?,?)";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objMedicos.getNome());
            pst.setString(2, objMedicos.getCrm());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Médico Incluida com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Médico não Incluida com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Medicos objMedicos){
        String sql = "UPDATE medicos SET nome = ?, crm = ? WHERE codMEDICOS = ?;";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objMedicos.getNome());
            pst.setString(2, objMedicos.getCrm());
            pst.setInt(3, objMedicos.getId());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Médico Alterada com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Médico não Alterada com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    }
    
    public boolean remover(Medicos objMedicos){
        String sql = "DELETE FROM medicos WHERE codMEDICOS = ?;";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objMedicos.getId());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Médico Apagada com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Médico não Apagada com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    }
}
