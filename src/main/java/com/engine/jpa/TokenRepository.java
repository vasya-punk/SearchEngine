package com.engine.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TokenRepository extends CrudRepository<Token, Long> {
    @Query("SELECT T FROM Token T WHERE T.name IN (:tokenNames)")
    Set<Token> findTokensByName(@Param("tokenNames") List<String> tokenNames);
}
