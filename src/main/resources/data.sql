DELETE FROM PESSOAS;


-- Inserindo alguns registros na tabela pessoas
INSERT INTO PESSOAS (apelido, nome, nascimento, stack) VALUES
                                                          ('joao123', 'João Silva', '1990-01-15', ARRAY['Java', 'Spring', 'Hibernate']),
                                                          ('maria456', 'Maria Oliveira', '1985-05-20', ARRAY['Python', 'Django', 'Flask']),
                                                          ('pedro789', 'Pedro Santos', '1992-10-10', ARRAY['JavaScript', 'React', 'Node.js']);
