package com.techandsolve.usecase.port;


import com.techandsolve.domain.Document;

import java.util.List;

public interface DocumentRepositoryPort {

    List<Document> findAll();

    Document save(Document document);
}
