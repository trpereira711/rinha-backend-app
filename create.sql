CREATE EXTENSION PG_TRGM;

CREATE TABLE IF NOT EXISTS PESSOAS (
                                       ID UUID DEFAULT gen_random_uuid(),
                                       APELIDO VARCHAR(32) UNIQUE,
                                       NOME VARCHAR(100),
                                       NASCIMENTO CHAR(10),
                                       STACK TEXT,
                                       BUSCA TEXT GENERATED ALWAYS AS (
                                                lower(apelido || ' ' || nome || ' ' || STACK)
                                       ) STORED
);

CREATE INDEX CONCURRENTLY IF NOT EXISTS IDX_BUSCA_TGRM ON PESSOAS USING GIST (BUSCA GIST_TRGM_OPS);

ALTER SYSTEM SET max_connections = '450';
ALTER SYSTEM SET shared_buffers = '600MB';
ALTER SYSTEM SET effective_cache_size = '1300MB';
ALTER SYSTEM SET maintenance_work_mem = '256MB';
ALTER SYSTEM SET checkpoint_completion_target = '0.9';
ALTER SYSTEM SET checkpoint_timeout = '10min';
ALTER SYSTEM SET wal_buffers = '32MB';
ALTER SYSTEM SET default_statistics_target = '500';
ALTER SYSTEM SET random_page_cost = '3.1';
ALTER SYSTEM SET effective_io_concurrency = '200';
ALTER SYSTEM SET work_mem = '218kB';
ALTER SYSTEM SET min_wal_size = '4GB';
ALTER SYSTEM SET max_wal_size = '16GB';
ALTER SYSTEM SET synchronous_commit = 'off';
ALTER SYSTEM SET fsync = 'off';
ALTER SYSTEM SET full_page_writes = 'off';
ALTER SYSTEM SET autovacuum = 'off';