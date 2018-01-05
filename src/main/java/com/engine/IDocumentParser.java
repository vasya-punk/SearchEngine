package com.engine;

import com.engine.jpa.Document;
import com.engine.jpa.Token;

import java.util.List;

public interface IDocumentParser {
    List<Token> parse(Document document);
}