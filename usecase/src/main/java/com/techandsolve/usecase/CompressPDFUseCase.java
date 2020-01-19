package com.techandsolve.usecase;

import com.techandsolve.domain.Document;
import com.techandsolve.usecase.base.UseCase;
import com.techandsolve.usecase.compress.CompressPDFUtil;
import com.techandsolve.usecase.port.DocumentRepositoryPort;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class CompressPDFUseCase extends UseCase<CompressPDFUseCase.Request, CompressPDFUseCase.Response> {
    private CompressPDFUtil compressPDFUtil;
    private DocumentRepositoryPort documentRepository;

    public CompressPDFUseCase(DocumentRepositoryPort documentRepository) {
        this.compressPDFUtil = CompressPDFUtil.getInstance();
        this.documentRepository = documentRepository;
    }

    @Override
    protected void executeUseCase(Request requestValues) {
        Optional.of(compressPDFUtil.reduce(requestValues.input, 500L))
                .map(bytes -> {
                    String name = requestValues.name;
                    String path = saveFile(name, bytes);
                    Document document = new Document();
                    document.setName(name);
                    document.setUri(path);
                    documentRepository.save(document);
                    emit().onSuccess(new Response(bytes, "Success"));
                    return bytes;
                }).orElseGet(() -> {
            emit().onError(new RuntimeException("No se pudo realizar la tarea"));
            return null;
        });
    }

    private String saveFile(String fileName, byte[] file) {
        FileOutputStream outputStream;
        String name = fileName + ".pdf";
        try {
            outputStream = new FileOutputStream(fileName + ".pdf");
            outputStream.write(file);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar documento =>" + name, e);
        }
        return name;
    }


    public static class Request implements UseCase.RequestValues {
        private final byte[] input;
        private final String name;

        public Request(String name, byte[] input) {
            this.input = input;
            this.name = name;
        }
    }

    public static class Response implements UseCase.ResponseValue {
        private final byte[] output;
        private final String status;

        public Response(byte[] output, String status) {
            this.status = status;
            this.output = output;
        }

        public String getResult() {
            return status;
        }
    }
}
