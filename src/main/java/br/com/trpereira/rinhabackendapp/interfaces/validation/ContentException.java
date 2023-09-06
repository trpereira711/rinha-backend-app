package br.com.trpereira.rinhabackendapp.interfaces.validation;

import java.util.List;

public class ContentException extends RuntimeException {
    public ContentException(List<String> contentErrors) {
        super(String.join(", ", contentErrors));
    }
}
