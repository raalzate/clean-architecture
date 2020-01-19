package com.techandsolve.springboot.repository;

import com.techandsolve.domain.Document;
import com.techandsolve.usecase.port.DocumentRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends DocumentRepositoryPort, JpaRepository<Document, Long> {

}
