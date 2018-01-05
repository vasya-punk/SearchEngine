package com.engine.jpa;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Document {

    @Version
    @Column
    private long version;

    @Id
    @Column
    @NotEmpty
    private String name;

    @Column(name = "token_values")
    @NotEmpty
    private String values;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "document_token",
            joinColumns = { @JoinColumn(name = "document_id", referencedColumnName = "name") },
            inverseJoinColumns = { @JoinColumn(name = "token_id", referencedColumnName = "name") }
    )
    Set<Token> tokens = new HashSet<>();

    public Document(){

    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
