package com.minpen.aceproduct.controller;

import com.minpen.aceproduct.domain.AceProduct;
import com.minpen.aceproduct.dto.AceProductDto;
import com.minpen.aceproduct.dto.PageDto;
import com.minpen.aceproduct.service.AceProductService;
import com.minpen.aceproduct.service.AceProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class AceProductController {

    private final AceProductService aceProductService;

    public AceProductController(AceProductService aceProductService) {
        this.aceProductService = aceProductService;
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto> getAllProducts(@RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "category", required = false) List<String> categories,
                                                  @RequestParam(name = "minPrice", defaultValue = "0.0") Double minPrice,
                                                  @RequestParam(name = "maxPrice", required = false) Double maxPrice) {
        Pageable pageable = PageRequest.of(page - 1, 8, Sort.by("productId"));
        AceProductSpecification aceProductSpecification = new AceProductSpecification(minPrice, maxPrice, categories);
        Page<AceProduct> aceProducts = aceProductService.getAllAceProducts(pageable, aceProductSpecification);
        List<AceProductDto> aceProductDtos = aceProducts
                .stream()
                .map(AceProductDto::from)
                .toList();
        PageDto pageDto = new PageDto(aceProductDtos, page, aceProducts.getTotalPages());
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AceProductDto> getProductById(@PathVariable long id) {
        AceProduct aceProducts = aceProductService.getAceProductById(id);
        return ResponseEntity.ok(AceProductDto.fromWithDetails(aceProducts));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleRestClientResponseException(RestClientResponseException ex, WebRequest request) {
        Map<String, String> result = new HashMap<>();
        result.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
