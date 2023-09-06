package br.com.trpereira.rinhabackendapp.entities.dto;

import br.com.trpereira.rinhabackendapp.entities.Pessoa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record PessoaDTO(String id, String apelido, String nome, LocalDate nascimento, List<String> stack) implements Serializable {

    public static PessoaDTO of(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getId().toString(),
                pessoa.getApelido(), pessoa.getNome(),
                pessoa.getNascimento(),
                pessoa.getStack());
    }
}
