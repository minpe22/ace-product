package com.minpen.aceproduct.service;

import com.minpen.aceproduct.domain.AceProduct;
import com.minpen.aceproduct.dto.AceProductDto;
import com.minpen.aceproduct.repository.AceProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AceProductService {

    private final AceProductRepository aceProductRepository;
    private final RestClient client;


    public AceProductService(AceProductRepository aceProductRepository, RestClient restClient) {
        this.aceProductRepository = aceProductRepository;
        this.client = restClient;
    }

    public Page<AceProduct> getAllAceProducts(Pageable pageable, AceProductSpecification aceProductSpecification) {
        Specification<AceProduct> filters = Specification.where(aceProductSpecification.minPriceQuery())
                .and(aceProductSpecification.maxPriceQuery())
                .and(aceProductSpecification.hasProductInCategories());
        Page<AceProduct> aceProducts = this.aceProductRepository.findAll(filters, pageable);
        if (aceProducts.isEmpty() && aceProducts.getTotalPages() == 0) {
            List<AceProductDto> aceProductDtos = getAllProductsFromFakeStore();
             List<AceProduct> aceProductsFromFakeStore = aceProductDtos.stream()
                    .map(AceProduct::from)
                    .toList();
            this.aceProductRepository.saveAll(aceProductsFromFakeStore);
            aceProducts = this.aceProductRepository.findAll(filters, pageable);
        }
        return aceProducts;
    }

    public AceProduct getAceProductById(long id) {
        Optional<AceProduct> aceProduct = this.aceProductRepository.findById(id);
        if (aceProduct.isEmpty()) {
            AceProductDto aceProductDto = getProductFromFakeStoreById(id);
            if (aceProductDto == null) {
                throw new RestClientResponseException("No prodcut found with id: " + id,
                        HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        null,
                        null,
                        null
                );
            }
            return AceProduct.from(aceProductDto);
        }

        return aceProduct.get();
    }

    private List<AceProductDto> getAllProductsFromFakeStore() {
        return List.of(Objects.requireNonNull(client.get()
                .uri( "/products")
                .retrieve()
                .body(AceProductDto[].class)));
    }

    private AceProductDto getProductFromFakeStoreById(long id) {
        return client.get()
                .uri("/products/{id}", id)
                .retrieve()
                .body(AceProductDto.class);
    }
}
