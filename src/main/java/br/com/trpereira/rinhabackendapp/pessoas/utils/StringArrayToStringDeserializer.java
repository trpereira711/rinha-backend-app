package br.com.trpereira.rinhabackendapp.pessoas.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringArrayToStringDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String[] stringArray = p.readValueAs(String[].class);
        return String.join(", ", stringArray);
    }
}

