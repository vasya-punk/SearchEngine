package com.engine;

import com.engine.jpa.Document;
import com.engine.jpa.DocumentRepository;
import com.engine.jpa.Token;
import com.engine.jpa.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.SetUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private IDocumentParser documentParser;

    public Iterable<Document> findAllDocuments(){
        return documentRepository.findAll();
    }

    public Document findDocumentByName(String name){
        return documentRepository.findByName(name);
    }

    public Set<Document> findDocumentsByTokenNames(Document document){
        List<Token> tokens = documentParser.parse(document);

        List<String> tokensNames = tokens.stream()
                .map(Token::getName)
                .collect(Collectors.toList());

        return tokenRepository.findTokensByName(tokensNames)
                    .stream()
                    .map(Token::getDocuments)
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
    }

    public void addNew(Document document){
        List<Token> newTokensToDb = documentParser.parse(document);

        // persist/merge tokens
        Set newTokens = SetUtils.toSet(tokenRepository.save(newTokensToDb));
        document.setTokens(newTokens);

        documentRepository.save(document);
    }
}
