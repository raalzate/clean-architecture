package com.techandsolve.springboot.config;

import com.techandsolve.service.CompressService;
import com.techandsolve.springboot.repository.DocumentRepository;
import com.techandsolve.usecase.CompressPDFUseCase;
import com.techandsolve.usecase.ListAllPDFUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompressConfig {

    @Autowired
    private DocumentRepository documentRepository;

    @Bean
    public CompressService configure() {
        CompressPDFUseCase compressPDFUseCase = new CompressPDFUseCase(documentRepository);
        ListAllPDFUseCase listAllPDFUseCase = new ListAllPDFUseCase(documentRepository);
        return new CompressService(compressPDFUseCase, listAllPDFUseCase);
    }
}
