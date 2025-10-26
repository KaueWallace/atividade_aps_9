-- Cursos
INSERT INTO curso (nome, codigo_curso) VALUES ('Ciência da Computação', 'COMP01');
INSERT INTO curso (nome, codigo_curso) VALUES ('Engenharia de Software', 'ESOFT02');

-- Professores
INSERT INTO professor (nome, especialidade) VALUES ('Maria Souza', 'Banco de Dados');
INSERT INTO professor (nome, especialidade) VALUES ('Carlos Silva', 'Programação Orientada a Objetos');

--  Turmas
INSERT INTO turma (codigo_turma, horario, curso_id, professor_id) VALUES ('TURMA-A2023', '08:00 - 10:00', 1, 1);
INSERT INTO turma (codigo_turma, horario, curso_id, professor_id) VALUES ('TURMA-B2023', '10:00 - 12:00', 2, 2);

-- Estudantes
INSERT INTO estudante (nome, matricula) VALUES ('Ana Lima', 2320);
INSERT INTO estudante (nome, matricula) VALUES ('Bruno Pereira', 2310);
INSERT INTO estudante (nome, matricula) VALUES ('Camila Rocha', 2309);
INSERT INTO estudante (nome, matricula) VALUES ('Daniel Souza', 2342);
INSERT INTO estudante (nome, matricula) VALUES ('Eduarda Silva', 2388);
INSERT INTO estudante (nome, matricula) VALUES ('Felipe Mendes', 2323);
INSERT INTO estudante (nome, matricula) VALUES ('Gabriela Alves', 2345);
INSERT INTO estudante (nome, matricula) VALUES ('Henrique Costa', 2343);
INSERT INTO estudante (nome, matricula) VALUES ('Isabela Martins', 2321);
INSERT INTO estudante (nome, matricula) VALUES ('João Oliveira', 2365);

-- Estudante turma
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (1, 1);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (2, 1);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (3, 1);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (4, 1);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (5, 1);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (6, 2);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (7, 2);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (8, 2);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (9, 2);
INSERT INTO estudante_turma (estudante_id, turma_id) VALUES (10, 2);