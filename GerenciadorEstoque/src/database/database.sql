CREATE DATABASE estoque; --criar banco de dados
Use estoque; --usar banco de dados

CREATE TABLE produto( --criando tabela produtos
    id INT AUTO_INCREMENT PRIMARY KEY, --ID será chave primaria e autoincrementavel
    name VARCHAR(100) NOT NULL, --nome terá até 100 caracteres e nao pode ser nulo
    price DOUBLE NOT NULL, --preco será float
    quantity INT NOT NULL
);