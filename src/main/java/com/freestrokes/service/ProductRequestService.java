package com.freestrokes.service;

import com.freestrokes.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 상품 요청을 위한 서비스 인터페이스
 */
public interface ProductRequestService {

    /**
     * 상품 목록을 조회
     * @param pageable 페이징 정보
     * @return 상품 목록
     */
    Page<ProductDto.ResponseDto> getProducts(Pageable pageable);

    /**
     * 단일 상품 정보를 조회
     * @param productId 상품 ID
     * @return 조회한 상품 정보
     */
    ProductDto.ResponseDto getProductDetail(String productId);

}
