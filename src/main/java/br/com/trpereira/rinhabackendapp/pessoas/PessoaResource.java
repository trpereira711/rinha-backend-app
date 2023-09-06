package br.com.trpereira.rinhabackendapp.pessoas;


import br.com.trpereira.rinhabackendapp.pessoas.exception.ValidationForm;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class PessoaResource {

    private final PessoaRepository repository;

    private final ValidationForm validation;

    public PessoaResource(PessoaRepository repository, ValidationForm validation) {
        this.repository = repository;
        this.validation = validation;
    }

    @GetMapping("pessoas/{id}")
    public ResponseEntity<Pessoa> get(@PathVariable String id) {
        return repository.findById(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("pessoas")
    public ResponseEntity<List<Pessoa>> getByTerm(@RequestParam("t") String term,
                                                  @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                  @RequestParam(name = "size", required = false, defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(repository.getByTermo(term, pageable));
    }


    @GetMapping("contagem-pessoas")
    public long getTotal() {
        return repository.count();
    }

    @PostMapping("pessoas")
    public ResponseEntity<Pessoa> newPessoa(@RequestBody Pessoa pessoa) {
        pessoa.id = null;

        validation.validate(pessoa);

        var newPessoa = repository.save(pessoa);

        URI uri = URI.create("/pessoas/" + newPessoa.id);

        return ResponseEntity.created(uri).body(newPessoa);
    }
}
