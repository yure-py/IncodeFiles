# ----------------
#    EXEMPLO 1
# ----------------

-- ALUNO e CURSO cada aluno possui um curso preferido 

USE yure;

DROP TABLE cursos;
DROP TABLE alunos;

CREATE TABLE Cursos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    carga_horaria int unsigned NOT NULL,
    ano_publi year NOT NULL
);

CREATE TABLE Alunos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    
    curso_preferido_id INT,
    FOREIGN KEY (curso_preferido_id) REFERENCES Cursos(id)
);

INSERT INTO 
  Cursos (nome, carga_horaria, ano_publi)
VALUES 
 ('HTML',40,2015), 
 ('CSS',30,2016), 
 ('JAVA',45,2017), 
 ('História',60,2018),
 ('Filosofia',100,2022);

INSERT INTO Alunos (nome, curso_preferido_id) 
VALUES 
	('Alice', 1), ('Bruno', 3), ('Carla', 2), ('Daniel', 1), ('Eduarda', 4), ('Felipe', 3);

INSERT INTO 
	Alunos (nome) 
VALUES 
    ('A'),('B'),('C'),('D'),('E');

# 1. 
# Faz um produto cartesiano de cada aluno com cada curso 3 registros de A com 3 de B 9 registros no total
# Forma ruim
SELECT a.nome, a.curso_preferido_id, c.nome, c.ano_publi
FROM alunos a INNER JOIN cursos c;

# 2 usando um filtro a chave primária de Curso se liga na Chave estrangeira de curso_perferido em aluno
SELECT 
  a.nome, a.curso_preferido_id AS preferido, 
  c.nome, c.ano_publi
FROM alunos a JOIN cursos c
ON a.curso_preferido_id = c.id;

# 3 mostrando as correspondecia nulas com outer join
SELECT 
  a.nome, a.curso_preferido_id AS preferido, 
  c.nome, c.ano_publi
FROM alunos a LEFT JOIN cursos c
ON a.curso_preferido_id = c.id;

SELECT 
  a.nome, a.curso_preferido_id AS preferido, 
  c.nome, c.ano_publi
FROM alunos a RIGHT OUTER JOIN cursos c
ON a.curso_preferido_id = c.id;
