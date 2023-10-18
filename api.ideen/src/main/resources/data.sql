CREATE TABLE pessoas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT,
    cpf VARCHAR(14) UNIQUE,
    nacionalidade VARCHAR(255)
);

--INSERT INTO pessoas (nome, idade, cpf, nacionalidade) VALUES
--    ('John Doe', 30, '776.037.740-12', 'Brazil'),
--    ('Alice Smith', 25, '549.556.060-40', 'United States');