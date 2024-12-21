package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import factory.ConnectionFactory;

public class exemplo {
    public void deleteById(int id){
        String sql = "DELETE FROM contatos WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            // criando conexao com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
    
            //executar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
    
            //id a ser atualizado
            pstm.setInt(1, id);
    
            // executar
            pstm.execute();
            System.out.println("Id deletado!!");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
    
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
