package com.techandsolve.quarkus.rest;

import com.techandsolve.quarkus.config.CompressConfig;
import com.techandsolve.service.format.DocumentFormat;
import com.techandsolve.usecase.base.UseCase;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.reactivestreams.Publisher;
import rsc.publisher.PublisherCompletableFuture;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Path("/")
public class CompressRestController {


    @Inject
    private CompressConfig compressConfig;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Publisher<List<DocumentFormat>> all() {
        return new PublisherCompletableFuture<>(compressConfig.getCompressService().getAll());
    }


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/reduces")
    public Publisher<UseCase.ResponseValue> uploadDocument(MultipartFormDataInput input) throws IOException {
        InputStream uploadedInputStream = input.getFormDataPart("file", InputStream.class, null);
        byte[] bytes = IOUtils.toByteArray(uploadedInputStream);

        return new PublisherCompletableFuture<>(compressConfig.getCompressService().reduce("undefine", bytes));
    }

}
