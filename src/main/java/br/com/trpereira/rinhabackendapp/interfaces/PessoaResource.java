package br.com.trpereira.rinhabackendapp.interfaces;


import br.com.trpereira.rinhabackendapp.entities.Pessoa;
import br.com.trpereira.rinhabackendapp.entities.dto.PessoaDTO;
import br.com.trpereira.rinhabackendapp.infra.PessoaRepository;
import br.com.trpereira.rinhabackendapp.interfaces.form.PessoaForm;

import br.com.trpereira.rinhabackendapp.interfaces.validation.ValidationForm;
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
    public ResponseEntity<PessoaDTO> get(@PathVariable String id) {
        return repository.findById(UUID.fromString(id))
                .map(pessoa -> ResponseEntity.ok(PessoaDTO.of(pessoa)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("pessoas")
    public ResponseEntity<List<Pessoa>> getByTerm(@RequestParam("t") String term,
                                                  @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                  @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(repository.getByTermo(term, pageable));
    }


    @GetMapping("contagem-pessoas")
    public int getTotal() {
        return repository.getTotal();
    }

    @PostMapping("pessoas")
    public ResponseEntity<PessoaDTO> newPessoa(@RequestBody PessoaForm form) {

        validation.validate(form);

        Pessoa novaPessoa = repository.save(Pessoa.of(form));

        URI uri = URI.create("/pessoas/" + novaPessoa.getId());

        return ResponseEntity.created(uri).body(PessoaDTO.of(novaPessoa));
    }
}
