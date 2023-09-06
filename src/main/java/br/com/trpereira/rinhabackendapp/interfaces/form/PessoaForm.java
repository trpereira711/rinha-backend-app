package br.com.trpereira.rinhabackendapp.interfaces.form;

import br.com.trpereira.rinhabackendapp.entities.Pessoa;

import java.time.LocalDate;
import java.util.List;

public record PessoaForm(
        String apelido,
        String nome,
        LocalDate nascimento,
        List<String> stack) {

    public static PessoaForm of(Pessoa pessoa) {
        return new PessoaForm(pessoa.getApelido(), pessoa.getNome(), pessoa.getNascimento(), pessoa.getStack());
    }
}
