package com.techandsolve.service;


import com.techandsolve.service.format.DocumentFormat;
import com.techandsolve.service.mapper.DocumentMapper;
import com.techandsolve.usecase.CompressPDFUseCase;
import com.techandsolve.usecase.ListAllPDFUseCase;
import com.techandsolve.usecase.base.UseCaseHandler;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompressService {
    private CompressPDFUseCase compressPDFUseCase;
    private ListAllPDFUseCase listAllPDFUseCase;

    public CompressService(CompressPDFUseCase compressPDFUseCase,
                           ListAllPDFUseCase listAllPDFUseCase) {
        this.compressPDFUseCase = compressPDFUseCase;
        this.listAllPDFUseCase = listAllPDFUseCase;
    }

    public CompletableFuture<CompressPDFUseCase.Response> reduce(String name, byte[] bytes) {
        return UseCaseHandler
                .getInstance()
                .execute(compressPDFUseCase, new CompressPDFUseCase.Request(name, bytes));
    }

    public CompletableFuture<List<DocumentFormat>> getAll() {
        return UseCaseHandler
                .getInstance()
                .execute(listAllPDFUseCase, new ListAllPDFUseCase.Request())
                .thenApply((value) -> value.getDocuments()
                        .stream()
                        .map(DocumentMapper.INSTANCE::documentToFormat)
                        .collect(Collectors.toList())
                );
    }

}
