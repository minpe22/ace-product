package com.minpen.aceproduct.service;

import com.minpen.aceproduct.domain.AceProduct;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class AceProductSpecification {

    private final Double minPrice;
    private final Double maxPrice;
    private final List<String> categories;

    public AceProductSpecification(Double minPrice, Double maxPrice, List<String> categories) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.categories = categories;
    }

    public Specification<AceProduct> minPriceQuery() {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"),  minPrice);
    }

    public Specification<AceProduct> maxPriceQuery() {
        return maxPrice == null || maxPrice < minPrice ? null : (root, query, builder) -> builder.lessThan(root.get("price"), maxPrice);
    }

    public Specification<AceProduct> hasProductInCategories() {
        return CollectionUtils.isEmpty(categories) ? null : (root, query, builder) -> root.get("category").in(categories);
    }
}
