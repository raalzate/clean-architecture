package com.techandsolve.usecase;

import com.techandsolve.domain.Document;
import com.techandsolve.usecase.base.UseCase;
import com.techandsolve.usecase.port.DocumentRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ListAllPDFUseCase extends UseCase<ListAllPDFUseCase.Request, ListAllPDFUseCase.Response> {

    private DocumentRepositoryPort documentRepository;

    public ListAllPDFUseCase(DocumentRepositoryPort documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    protected void executeUseCase(Request requestValues) {
        Optional.of(documentRepository.findAll())
                .map(documents -> {
                    emit().onSuccess(new ListAllPDFUseCase.Response(documents));
                    return documents;
                }).orElseGet(() -> {
            emit().onError(new RuntimeException("No se pudo realizar la tarea"));
            return null;
        });
    }

    public static class Request implements UseCase.RequestValues {

    }

    public static class Response implements UseCase.ResponseValue {
        private final List<Document> document;

        public Response(List<Document> document) {
            this.document = document;
        }

        public List<Document> getDocuments() {
            return document;
        }
    }
}
