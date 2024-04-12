package com.minpen.aceproduct.repository;

import com.minpen.aceproduct.domain.AceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AceProductRepository extends JpaRepository<AceProduct, Long> {

}
