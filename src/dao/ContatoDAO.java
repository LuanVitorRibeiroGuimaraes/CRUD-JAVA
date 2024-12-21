package dao;

import factory.ConnectionFactory;
import model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//fazer com que o java se molde a uma estrutura relacional que é o banco

public class ContatoDAO {

    /*
    *CRUD
    * c: CREATE
    * r: READ
    * u: UPDATE
    * d: DELETE
     */

    //insert

    public void delelte(int id) throws SQLException{
        String sql = "DELETE * FROM contatos WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareCall(sql);
            pstm.setInt(1, id);

            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                if (pstm != null) {
                    pstm.close();
            } if(conn != null){
                    conn.close();
            } 
            
        }
    }

    public void save(Contato contato){

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar uma conexão com o banco de dados
            //conn = ConnectionFactory.createConnectionToMySQL();
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

    public void update(Contato contato){
        String sql = "UPDATE contatos SET name = ?, idade = ?, dataCadastro = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;


        try{
            //criar conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //criar a classe para executar a query
            pstm = conn.prepareCall(sql);

            //adicionar os valores para atualizar
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //qual o id do registro que deseja atualizar
            pstm.setInt(4, contato.getId());

            //executar a query
            pstm.executeUpdate(sql);
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try{
                if (pstm != null){
                    pstm.close();
                } if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    //read listando os contatos do banco de dados
    public List<Contato> getContatos() throws SQLException {
        String sql = "SELECT * FROM contatos";

        //instanciando o List com ArrayList
        List<Contato> contatos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //classe que vai recuperar os dados do banco  ****SELECT****;
        ResultSet rst = null;

        try{
            conn = ConnectionFactory.createConnectionToMySQL(); //criando a conexão com o banco

            //conn é uma Connection, por isso é feito o casting
            pstm = conn.prepareStatement(sql);

            rst = pstm.executeQuery();

            //enquanto estiver dado para percorrer, ele irá continuar no while
            while(rst.next()){

                Contato contato = new Contato();

                //recuperar o id
                contato.setId(rst.getInt("id"));
                //recuperar o nome
                contato.setNome(rst.getString("nome"));
                //recuperar a idade
                contato.setIdade(rst.getInt("idade"));
                //recuperar a data de cadastro
                contato.setDataCadastro(rst.getDate("dataCadastro"));

                //adicionando tudo isso na List contato;
                contatos.add(contato);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (rst != null){
                rst.close();
            } if (pstm != null){
                pstm.close();
            } if(conn != null){
                conn.close();
            }
        }
        return contatos;
    }
}