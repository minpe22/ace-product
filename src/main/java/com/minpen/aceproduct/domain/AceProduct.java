package com.minpen.aceproduct.domain;

import com.minpen.aceproduct.dto.AceProductDto;
import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class AceProduct {

    @Id
    @Column(name="PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name="TITLE")
    private String title;
    @Column(name="PRICE")
    private Double price;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="CATEGORY")
    private String category;
    @Column(name="IMAGE")
    private String image;
    @Embedded
    private Rating rating;

    public long getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public Rating getRating() {
        return rating;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public static AceProduct from(AceProductDto aceProductDto) {
        AceProduct aceProduct = new AceProduct();
        aceProduct.setProductId(aceProductDto.productId());
        aceProduct.setCategory(aceProductDto.category());
        aceProduct.setDescription(aceProductDto.description());
        aceProduct.setTitle(aceProductDto.title());
        aceProduct.setImage(aceProductDto.image());
        aceProduct.setPrice(aceProductDto.price());
        aceProduct.setRating(Rating.from(aceProductDto.rating()));
        return aceProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", rating='" + rating.toString() + '\'' +
                '}';
    }
}
