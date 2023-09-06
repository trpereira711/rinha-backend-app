package br.com.trpereira.rinhabackendapp.interfaces.validation;

import br.com.trpereira.rinhabackendapp.interfaces.form.PessoaForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SIM, EU NÃO USEI O BEAN VALIDATION!!
 */
@Service
public class ValidationForm {
    private final List<String> contentErrors = new ArrayList<>();
    private final List<String> syntaxErrors = new ArrayList<>();

    public void validate(PessoaForm form) {
        contentErrors.clear();
        syntaxErrors.clear();

        if (Objects.isNull(form.apelido())) {
            contentErrors.add("apelido não pode ser nulo");
        }

        if (Objects.isNull(form.nome())) {
            contentErrors.add("nome não pode ser nulo");
        }

        if (Objects.nonNull(form.apelido()) && form.apelido().chars().allMatch(Character::isDigit)) {
            syntaxErrors.add("apelido deve ser string e não número");
        }

        if (Objects.nonNull(form.nome()) && form.nome().chars().allMatch(Character::isDigit)) {
            syntaxErrors.add("nome deve ser string e não número");
        }

        if (form.stack().stream().anyMatch(s -> s.chars().allMatch(Character::isDigit))) {
            syntaxErrors.add("stack deve ser um array de apenas strings");
        }

        verifyErrors();
    }

    private void verifyErrors() {
        if (!contentErrors.isEmpty()) {
            throw new ContentException(contentErrors);
        }

        if (!syntaxErrors.isEmpty()) {
            throw new SyntaxException(syntaxErrors);
        }
    }
}
