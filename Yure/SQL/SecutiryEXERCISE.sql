use yure;

select * from global;

desc global;


-- quantas linhas tem na tabela?
select count(*) as total_linhas from global;


-- conta quantos ataques ocorreram depois de 2022 -- 932 ataques
select count(*) as tot_ataques from global
where year>=2022;


-- Quantidade de ataques em cada ano
SELECT year, count(*) as tot_ataques_by_year FROM global
GROUP BY year
ORDER BY year;

-- Agrupa o total de ataques por tipo e soma a perca total em milhões 
SELECT 
	`attack source` ataque, 
    count(*) as total_tipo,
    SUM(`Financial Loss (in Million $)`) as financial_loss_in_millions
FROM global
GROUP BY ataque;


-- qual ataque a maior perda financeira em um ataque
select max(`Financial Loss (in Million $)`) from global;


-- Quantidade de ataques separados por categoria de ataque
SELECT `Attack Source`, COUNT(`Attack Source`) AS attack_count 
FROM global
GROUP BY `Attack Source`;


# Subquery

-- Somar o total da query de obter as quantidades de cada categoria de ataque
SELECT SUM(attack_count) AS total_attacks 
FROM (
	SELECT `Attack Source`, COUNT(`Attack Source`) AS attack_count 
	FROM global 
	GROUP BY `Attack Source`
) AS subquery;


-- Se quiser encontrar todas as colunas do(s) registro(s) onde a perda financeira é máxima
SELECT * FROM global 
WHERE `Financial Loss (in Million $)` = 
	  (SELECT MAX(`Financial Loss (in Million $)`) FROM global);

-- TESTES
SELECT country, year FROM global
GROUP BY country, year
ORDER BY year;