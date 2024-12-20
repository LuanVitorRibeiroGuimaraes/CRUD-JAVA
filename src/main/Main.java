package main;

import dao.ContatoDAO;
import model.Contato;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {

        ContatoDAO contatoDao = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Idalia");
        contato.setIdade(64);
        contato.setDataCadastro(new Date());

        contatoDao.save(contato);
        //vizualização dos registros do banco de dados TODOS

        for(Contato c : contatoDao.getContatos()){
            System.out.println("Contatos: " + c.getNome());
        }
    }
}