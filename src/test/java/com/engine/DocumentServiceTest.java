package com.engine;

import com.engine.jpa.Document;
import com.engine.jpa.DocumentRepository;
import com.engine.jpa.Token;
import com.engine.jpa.TokenRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @MockBean
    private DocumentRepository documentRepository;

    @MockBean
    private TokenRepository tokenRepository;

    private Document document;

    @Before
    public void setUp() {
        document = new Document();

        Token a = new Token("a");
        Token aa = new Token("aa");
        Token aaa = new Token("aaa");

        Set<Token> tokens = new HashSet(
                Arrays.asList(
                        a,
                        aa,
                        aaa
                )
        );

        document.setTokens(tokens);
        document.setValues("a,aa,aaa");
        document.setName("documentA");

        Mockito.when(documentRepository.findByName(document.getName())).thenReturn(document);
        Mockito.when(documentRepository.findAll()).thenReturn(new HashSet(Arrays.asList(document)));

        Mockito.when(tokenRepository.findTokensByName(anyList())).thenReturn(tokens);
    }


    @Test
    public void whenFindByName_thenReturnDocument() {

        final String documentName = "documentA";

        // when
        Document found = documentService.findDocumentByName(documentName);

        // then
        assertEquals(found.getName(), documentName);
    }

    @Test
    public void whenFindAll_thenReturnDocuments() {

        // when
        Iterable<Document> found = documentService.findAllDocuments();

        // then
        assertEquals(found.iterator().next().getName(), document.getName());
    }

}