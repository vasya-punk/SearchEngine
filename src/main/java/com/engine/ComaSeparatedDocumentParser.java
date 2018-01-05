package com.engine;

import com.engine.jpa.Document;
import com.engine.jpa.Token;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComaSeparatedDocumentParser implements IDocumentParser {
    public static final String TOKENS_DELIMITER = ",";

    @Override
    public List<Token> parse(Document document) {
        return Arrays.asList(document.getValues().split(TOKENS_DELIMITER))
                .stream()
                .map(Token::new)
                .collect(Collectors.toList());
    }
}
