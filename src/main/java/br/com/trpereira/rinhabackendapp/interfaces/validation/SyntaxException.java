package br.com.trpereira.rinhabackendapp.interfaces.validation;

import java.util.List;

public class SyntaxException extends RuntimeException{
    public SyntaxException(List<String> syntaxErrors) {
        super(String.join(", ", syntaxErrors));
    }
}
