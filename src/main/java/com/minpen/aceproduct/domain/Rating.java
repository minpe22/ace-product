package com.minpen.aceproduct.domain;

import com.minpen.aceproduct.dto.RatingDto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {

    @Column(name="RATE")
    private double rate;

    @Column(name="COUNTS")
    private long count;

    public static Rating from(RatingDto dto) {
        Rating rating = new Rating();
        rating.setCount(dto.count());
        rating.setRate(dto.rate());
        return rating;
    }

    public double getRate() {
        return rate;
    }

    public long getCount() {
        return count;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "{" +
                "rate=" + rate +
                ", count='" + count + '\'' +
                '}';
    }
}
