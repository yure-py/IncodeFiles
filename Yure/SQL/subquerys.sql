# Busca o ID de MA dos estados e insere neste registro novo da tabela cidades
INSERT INTO cidades
	(nome, area, estado_id)
VALUES
	("São luíz", 900.9, (SELECT id FROM estados WHERE sigla="MA"));
    

# Seleciona as cargas horarios de cursos registrados agrupando pela carga horaria em seguida calcula a média
# das cargas horarias com a subquery e então mostra que esta acima da média de carga horaria
# a carga horaria assim pode se alterar a cada novo registro
SELECT carga, count(*) FROM cursos
WHERE ano > 2015
GROUP BY carga
HAVING carga>(SELECT AVG(carga) FROM cursos);


