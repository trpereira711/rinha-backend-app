package br.com.trpereira.rinhabackendapp.pessoas;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query(value = "SELECT * FROM PESSOAS WHERE busca ILIKE %:termo%", nativeQuery = true)
    List<Pessoa> getByTermo(@Param("termo") String termo, Pageable pageable);

}
