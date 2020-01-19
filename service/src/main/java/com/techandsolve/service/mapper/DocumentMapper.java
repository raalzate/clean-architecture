package com.techandsolve.service.mapper;

import com.techandsolve.domain.Document;
import com.techandsolve.service.format.DocumentFormat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mapping(source = "name", target = "name")
    DocumentFormat documentToFormat(Document document);
}
