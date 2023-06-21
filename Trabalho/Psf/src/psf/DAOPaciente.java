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
public class DAOPaciente {
    public List<Paciente> getLista() {
        String sql = "SELECT * FROM pacientes;";
        List<Paciente> listaPaciente = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                Paciente objPaciente = new Paciente();
                objPaciente.setId(rs.getInt("codPACIENTES"));
                objPaciente.setCpf(rs.getString("cpf"));
                objPaciente.setNome(rs.getString("nome"));
                objPaciente.setEndereco(rs.getString("endereco"));
                objPaciente.setIdade(rs.getInt("idade"));
                objPaciente.setTelefone(rs.getString("telefone"));
                listaPaciente.add(objPaciente);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ex.getMessage());
        }
        return listaPaciente;
    }
    
    public boolean salvar(Paciente obj) {
        if (obj.getId()== null) {
            return incluir(obj);
        }
        else
        {
            return alterar(obj);
        }
    }
    
    public boolean incluir(Paciente objPaciente){
        String sql = "INSERT INTO pacientes (nome, cpf,endereco,idade,telefone) values (?,?,?,?,?)";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objPaciente.getNome());
            pst.setString(2, objPaciente.getCpf());
            pst.setString(3, objPaciente.getEndereco());
            pst.setInt(4, objPaciente.getIdade());
            pst.setString(5, objPaciente.getTelefone());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Paciente Incluida com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Paciente não Incluida com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Paciente objPaciente){
        String sql = "UPDATE pacientes SET nome = ?, cpf = ?, endereco = ?, idade = ? ,telefone =? WHERE codPACIENTES = ?;";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objPaciente.getNome());
            pst.setString(2, objPaciente.getCpf());
            pst.setString(3, objPaciente.getEndereco());
            pst.setInt(4, objPaciente.getIdade());
            pst.setString(5, objPaciente.getTelefone());
            
            pst.setInt(6, objPaciente.getId());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Paciente Alterada com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Paciente não Alterada com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    }
    
    public boolean remover(Paciente objPaciente){
        String sql = "DELETE FROM pacientes WHERE codPACIENTES = ?;";
        
        try
            
        {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objPaciente.getId());
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Paciente Apagada com sucesso");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Paciente não Apagada com sucesso");
                return false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL"+ex.getMessage());
            return false;
        }
    }
}
