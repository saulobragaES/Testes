EXERCICIO 01

Senha e usuario

- Autenticacao
Url: POST http://localhost:8080/api/autenticacao/login  ( Pegar Token para logar na publica )

- Publica
Url: GET http://localhost:8080/api/empacotamento/hello

{
    "username": "user",
    "password": "password"
}


-- EXERCICIO 02



-- Calcular horas Aula Professor


SELECT
    p.id AS ProfessorID,
    p.name AS ProfessorNome,
    SUM(TIMESTAMPDIFF(MINUTE, h.start_time, h.end_time)) / 60 AS TotalHoras
FROM
    PROFESSOR p
JOIN
    class a ON p.title_id = a.subject_id 
JOIN
    class_schedule h ON a.id = h.class_id
GROUP BY
    p.id, p.name
ORDER BY
    TotalHoras DESC;



-- Listas com Horarios  Ocupados

SELECT
    s.id AS SalaID,
    s.name AS SalaNome,
    h.day_of_week AS DiaSemana,
    h.start_time AS Inicio,
    h.end_time AS Fim,
    'Ocupado' AS Status
FROM
    class s
JOIN
    class_schedule h ON s.id = h.room_id
ORDER BY
    s.name, h.day_of_week, h.start_time;



-- Select melhorado 

WITH class_default AS (
    SELECT dia, inicio, fim FROM (
        VALUES 
        (1, '08:00:00', '10:00:00'),
        (1, '10:00:00', '12:00:00'),
        (1, '13:00:00', '15:00:00'),
        (1, '15:00:00', '17:00:00'),
        (2, '08:00:00', '10:00:00'),
        (2, '10:00:00', '12:00:00'),
        (2, '13:00:00', '15:00:00'),
        (2, '15:00:00', '17:00:00'),
        -- ... segue 3, 4, 5
        (5, '15:00:00', '17:00:00')
    ) AS h(dia, inicio, fim)
)

SELECT
    s.id AS SalaID,
    s.name AS SalaNome,
    hp.dia AS DiaSemana,
    hp.inicio AS Inicio,
    hp.fim AS Fim,
    CASE 
        WHEN h.id IS NOT NULL THEN 'Ocupado'
        ELSE 'Livre'
    END AS Status
FROM
    class s
CROSS JOIN
    class_default hp
LEFT JOIN class_schedule h ON h.room_id = s.id
      AND h.day_of_week = hp.dia
      AND h.start_time = hp.inicio
      AND h.end_time = hp.fim
ORDER BY
    s.name, hp.dia, hp.inicio;

