# ----------------
#    EXEMPLO 1
# ----------------

-- ALUNO e CURSO cada aluno possui um curso preferido 

USE yure;

CREATE TABLE Cursos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Alunos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    
    curso_preferido_id INT,
    FOREIGN KEY (curso_preferido_id) REFERENCES Cursos(id)
);

INSERT INTO Cursos (nome)
 VALUES ('Matemática'), ('Física'), ('Programação'), ('História');

INSERT INTO Alunos (nome, curso_preferido_id) 
VALUES ('Alice', 1), ('Bruno', 3), ('Carla', 2), ('Daniel', 1), ('Eduarda', 4), ('Felipe', 3);

SELECT * FROM alunos join cursos;

