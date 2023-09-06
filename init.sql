CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS PESSOAS;

CREATE TABLE PESSOAS (
                        id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        apelido VARCHAR(32) NOT NULL UNIQUE,
                        nome VARCHAR(100) NOT NULL,
                        nascimento DATE NOT NULL,
                        stack VARCHAR(32)[]
);

CREATE INDEX idx_pessoa_nome ON PESSOAS(nome);
