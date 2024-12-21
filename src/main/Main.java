package main;

import dao.ContatoDAO;
import model.Contato;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {

        ContatoDAO contatoDao = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Luan");
        contato.setIdade(0);
        contato.setDataCadastro(new Date());

        contatoDao.save(contato);
        
        //vizualização dos registros do banco de dados TODOS

        for(Contato c : contatoDao.getContatos()){
            System.out.println("Contatos: " + c.getNome());
        }

        Contato c1 = new Contato();

        c1.setNome("Luan Guimaraes");
        c1.setIdade(20);
        c1.setDataCadastro(new Date());
        c1.setId(15); //numero que esta no banco de dados

        //contatoDao.update(c1);

        //deletar o contato pelo id
        contatoDao.delelte(15);


    }
}