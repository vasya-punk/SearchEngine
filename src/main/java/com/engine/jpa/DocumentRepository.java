package com.engine.jpa;

import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {
    Document findByName(String name);
}
