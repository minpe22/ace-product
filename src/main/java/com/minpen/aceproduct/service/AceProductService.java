package com.minpen.aceproduct.service;

import com.minpen.aceproduct.configuration.AppConfig;
import com.minpen.aceproduct.domain.AceProduct;
import com.minpen.aceproduct.dto.AceProductDto;
import com.minpen.aceproduct.repository.AceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Objects;

@Service
public class AceProductService {

    private final AceProductRepository aceProductRepository;
    private final RestClient client;


    public AceProductService(AppConfig appConfig, AceProductRepository aceProductRepository, RestClient.Builder builder) {
        this.aceProductRepository = aceProductRepository;
        this.client = builder
                .baseUrl(appConfig.getUrl())
                .build();
    }

    public List<AceProduct> getAllAceProducts() {
        List<AceProduct> aceProducts = this.aceProductRepository.findAll();
        if (aceProducts.isEmpty()) {
            List<AceProductDto> aceProductDtos = getAllProductsFromFakeStore();
            aceProducts = aceProductDtos.stream()
                    .map(AceProduct::from)
                    .toList();
            this.aceProductRepository.saveAll(aceProducts);
        }
        return aceProducts;
    }

    public List<AceProductDto> getAllProductsFromFakeStore() {
        return List.of(Objects.requireNonNull(client.get()
                .uri("https://fakestoreapi.com/products")
                .retrieve()
                .body(AceProductDto[].class)));
    }
}
