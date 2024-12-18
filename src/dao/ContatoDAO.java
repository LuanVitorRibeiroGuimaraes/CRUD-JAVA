package dao;

import factory.ConnectionFactory;
import model.Contato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//fazer com que o java se molde a uma estrutura relacional que é o banco

public class ContatoDAO {

    /*
    *CRUD
    * c: CREATE
    * r: READ
    * u: UPDATE
    * d: DELETE
     */

    public void save(Contato contato){

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();

            //Criamos uma PreparedStatement, para executar uma query
            pstm = conn.prepareCall(sql);
            //adicionar os valores que são esperados pela query
            pstm.setNString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //executar a query

            pstm.executeUpdate();
            System.out.println("CONTATO SALVO COM SUCESSO!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            //fechar as conexões
            try {
                if(pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}