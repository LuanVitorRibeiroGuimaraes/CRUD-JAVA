CREATE DATABASE agenda;

CREATE TABLE contatos(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(40),
    idade INT,
    dataCadastro DATE
);