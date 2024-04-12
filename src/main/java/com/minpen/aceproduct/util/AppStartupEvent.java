package com.minpen.aceproduct.util;

import com.minpen.aceproduct.domain.AceProduct;
import com.minpen.aceproduct.service.AceProductService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final AceProductService aceProductService;

    public AppStartupEvent(AceProductService aceProductService) {
        this.aceProductService = aceProductService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<AceProduct> aceProducts = this.aceProductService.getAllAceProducts();
        aceProducts.forEach(System.out::println);
    }
}
