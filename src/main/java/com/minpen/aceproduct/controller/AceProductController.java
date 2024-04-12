package com.minpen.aceproduct.controller;

import com.minpen.aceproduct.domain.AceProduct;
import com.minpen.aceproduct.dto.AceProductDto;
import com.minpen.aceproduct.service.AceProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class AceProductController {

    private final AceProductService aceProductService;

    public AceProductController(AceProductService aceProductService) {
        this.aceProductService = aceProductService;
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AceProductDto>> getAllProducts() {
        List<AceProduct> aceProducts = aceProductService.getAllAceProducts();
        List<AceProductDto> aceProductDtos = aceProducts
                .stream()
                .map(AceProductDto::from)
                .toList();
        return ResponseEntity.ok(aceProductDtos);
    }
}
