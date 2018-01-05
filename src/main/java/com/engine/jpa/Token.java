package com.engine.jpa;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Token {

    @Version
    @Column
    private long version;

    @Id
    @Column
    private String name;

    @ManyToMany(mappedBy = "tokens", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<Document> documents = new HashSet<>();

    public Token(){

    }

    public Token(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
