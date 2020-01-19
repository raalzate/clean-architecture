package com.techandsolve.micronaut.config;

import com.techandsolve.micronaut.repository.DocumentRepository;
import com.techandsolve.service.CompressService;
import com.techandsolve.usecase.CompressPDFUseCase;
import com.techandsolve.usecase.ListAllPDFUseCase;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class CompressConfig {

    @Inject
    private DocumentRepository documentRepository;

    public CompressService getCompressService() {
        CompressPDFUseCase compressPDFUseCase = new CompressPDFUseCase(documentRepository);
        ListAllPDFUseCase listAllPDFUseCase = new ListAllPDFUseCase(documentRepository);
        return new CompressService(compressPDFUseCase, listAllPDFUseCase);
    }


}
