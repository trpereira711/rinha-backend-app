package br.com.trpereira.rinhabackendapp.pessoas.exception;

import br.com.trpereira.rinhabackendapp.pessoas.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * SIM, EU NÃO USEI O BEAN VALIDATION!!
 */
@Service
public class ValidationForm {

    private static final String DATE_PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})$";
    private final List<String> contentErrors = new ArrayList<>();
    private final List<String> syntaxErrors = new ArrayList<>();

    public void validate(Pessoa pessoa) {
        contentErrors.clear();
        syntaxErrors.clear();

        if (Objects.isNull(pessoa.apelido)) {
            contentErrors.add("apelido não pode ser nulo");
        }

        if (Objects.nonNull(pessoa.apelido) && pessoa.apelido.length() > 32) {
            contentErrors.add("apelido deve ter no máximo 32 caracteres");
        }

        if (Objects.nonNull(pessoa.apelido) && pessoa.apelido.chars().allMatch(Character::isDigit)) {
            syntaxErrors.add("apelido deve ser string e não número");
        }

        if (Objects.isNull(pessoa.nome)) {
            contentErrors.add("nome não pode ser nulo");
        }

        if (Objects.nonNull(pessoa.nome) && pessoa.nome.chars().allMatch(Character::isDigit)) {
            syntaxErrors.add("nome deve ser string e não número");
        }

        if (Objects.nonNull(pessoa.nome) && pessoa.nome.length() > 100) {
            contentErrors.add("nome deve ter no máximo 100 caracteres");
        }

        if (Objects.isNull(pessoa.nascimento)) {
            contentErrors.add("nascimento não pode ser nulo");
        }

        if (!Pattern.matches(DATE_PATTERN, pessoa.nascimento)) {
            syntaxErrors.add("data de nascimento inválida");
        }

        if (Arrays.stream(pessoa.stack.split(",")).anyMatch(s -> s.chars().allMatch(Character::isDigit))) {
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
