package br.com.trpereira.rinhabackendapp.infra;

import br.com.trpereira.rinhabackendapp.entities.Pessoa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query(value = "SELECT * FROM PESSOAS WHERE apelido ILIKE %:termo% OR nome ILIKE %:termo% OR ARRAY_TO_STRING(stack, ',') ILIKE %:termo%", nativeQuery = true)
    List<Pessoa> getByTermo(@Param("termo") String termo, Pageable pageable);

    @Query(value = "SELECT COUNT(p) FROM PESSOAS p", nativeQuery = true)
    int getTotal();
}
