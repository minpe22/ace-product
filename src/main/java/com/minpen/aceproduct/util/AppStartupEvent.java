package com.minpen.aceproduct.util;

import com.minpen.aceproduct.domain.AceProduct;
import com.minpen.aceproduct.service.AceProductService;
import com.minpen.aceproduct.service.AceProductSpecification;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final AceProductService aceProductService;

    public AppStartupEvent(AceProductService aceProductService) {
        this.aceProductService = aceProductService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        Pageable pageable = PageRequest.of(0, 100, Sort.by("productId"));
//        Iterable<AceProduct> aceProducts = this.aceProductService.getAllAceProducts(pageable, new AceProductSpecification(0.0, null, null));
//        aceProducts.forEach(System.out::println);
    }
}
