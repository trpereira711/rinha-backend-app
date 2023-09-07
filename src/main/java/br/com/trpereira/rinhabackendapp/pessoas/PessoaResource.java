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
    private final PessoaService service;

    public PessoaResource(PessoaRepository repository,
                          ValidationForm validation,
                          PessoaService service) {
        this.repository = repository;
        this.validation = validation;
        this.service = service;
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
    public ResponseEntity<Pessoa> newPessoa(@RequestBody Pessoa pessoa) throws InterruptedException {
        pessoa.id = UUID.randomUUID();

        validation.validate(pessoa);

        service.put(pessoa);

        URI uri = URI.create("/pessoas/" + pessoa.id);

        return ResponseEntity.created(uri).body(pessoa);
    }
}
