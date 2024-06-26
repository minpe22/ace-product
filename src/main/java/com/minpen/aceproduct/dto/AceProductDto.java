package com.minpen.aceproduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minpen.aceproduct.domain.AceProduct;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AceProductDto(
        @JsonProperty(value = "id", required = true)
        long productId,

        @JsonProperty(value = "title", required = true)
        String title,

        @JsonProperty(value = "price", required = true)
        Double price,

        @JsonProperty(value = "description", required = false)
        String description,

        @JsonProperty(value = "category", required = true)
        String category,

        @JsonProperty(value = "image", required = true)
        String image,

        @JsonProperty(value = "rating", required = false)
        RatingDto rating) {

    public static AceProductDto fromWithDetails(AceProduct aceProduct) {
        return new AceProductDto(
                aceProduct.getProductId(),
                aceProduct.getTitle(),
                aceProduct.getPrice(),
                aceProduct.getDescription(),
                aceProduct.getCategory(),
                aceProduct.getImage(),
                RatingDto.from(aceProduct.getRating())
        );
    }

    public static AceProductDto from(AceProduct aceProduct) {
        return new AceProductDto(
                aceProduct.getProductId(),
                aceProduct.getTitle(),
                aceProduct.getPrice(),
                null,
                aceProduct.getCategory(),
                aceProduct.getImage(),
                null
        );
    }
}
