package com.techandsolve.quarkus.repository;

import com.techandsolve.domain.Document;
import com.techandsolve.usecase.port.DocumentRepositoryPort;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DocumentRepository implements DocumentRepositoryPort {

    private static List<Document> inMemoryDB = new ArrayList<>();

    @Override
    public List<Document> findAll() {
        return inMemoryDB;
    }

    @Override
    public Document save(Document document) {
        inMemoryDB.add(document);
        return document;
    }
}
