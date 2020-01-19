package com.techandsolve.micronaut.rest;

import com.techandsolve.micronaut.config.CompressConfig;
import com.techandsolve.service.format.DocumentFormat;
import com.techandsolve.usecase.base.UseCase;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.spring.tx.annotation.Transactional;
import io.micronaut.validation.Validated;
import org.reactivestreams.Publisher;
import rsc.publisher.PublisherCompletableFuture;

import java.io.IOException;
import java.util.List;

@Controller
@Validated
public class CompressRestController {

    private CompressConfig compressConfig;

    public CompressRestController(CompressConfig compressConfig) {
        this.compressConfig = compressConfig;
    }

    @Get(value = "/all", processes = MediaType.APPLICATION_JSON)
    @Transactional
    public Publisher<List<DocumentFormat>> all() {
        return new PublisherCompletableFuture<>(compressConfig.getCompressService().getAll());
    }


    @Post(value = "/reduces",
            consumes = MediaType.MULTIPART_FORM_DATA
    )
    @Transactional
    public Publisher<UseCase.ResponseValue> uploadDocument(CompletedFileUpload file) throws IOException {

        return new PublisherCompletableFuture<>(compressConfig.getCompressService().reduce("undefine", file.getBytes()));
    }

}
