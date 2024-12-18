package main;

import dao.ContatoDAO;
import model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        ContatoDAO contatoDao = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Idalia");
        contato.setIdade(64);
        contato.setDataCadastro(new Date());

        contatoDao.save(contato); 
    }
}
