package com.techandsolve.quarkus.config;

import com.techandsolve.quarkus.repository.DocumentRepository;
import com.techandsolve.service.CompressService;
import com.techandsolve.usecase.CompressPDFUseCase;
import com.techandsolve.usecase.ListAllPDFUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CompressConfig {

    @Inject
    private DocumentRepository documentRepository;

    public CompressService getCompressService() {
        CompressPDFUseCase compressPDFUseCase = new CompressPDFUseCase(documentRepository);
        ListAllPDFUseCase listAllPDFUseCase = new ListAllPDFUseCase(documentRepository);
        return new CompressService(compressPDFUseCase, listAllPDFUseCase);
    }

}
