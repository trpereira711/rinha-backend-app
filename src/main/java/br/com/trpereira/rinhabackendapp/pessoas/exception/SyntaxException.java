package br.com.trpereira.rinhabackendapp.pessoas.exception;

import java.util.List;

public class SyntaxException extends RuntimeException{
    public SyntaxException(List<String> syntaxErrors) {
        super(String.join(", ", syntaxErrors));
    }
}
