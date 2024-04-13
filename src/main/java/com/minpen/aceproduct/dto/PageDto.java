package com.minpen.aceproduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PageDto(
        @JsonProperty(value = "products")
        List<AceProductDto> aceProductDtos,

        @JsonProperty(value = "page")
        int page,

        @JsonProperty(value = "size")
        int size
) {
}
