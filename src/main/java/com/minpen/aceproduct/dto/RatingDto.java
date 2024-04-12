package com.minpen.aceproduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minpen.aceproduct.domain.Rating;

public record RatingDto(
                        @JsonProperty(value = "rate", required = true)
                        double rate,

                        @JsonProperty(value = "count", required = true)
                        long count) {

    public static RatingDto from(Rating rating) {
        return new RatingDto(rating.getRate(), rating.getCount());
    }
}
