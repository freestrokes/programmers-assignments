package com.freestrokes.service;

import com.freestrokes.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 상품 요청을 위한 서비스 인터페이스
 */
public interface ProductRequestService {

    /**
     * 상품 목록을 조회.
     * @param pageable 페이징 정보
     * @return 상품 목록
     */
    Page<BoardDto.ResponseDto> getProducts(Pageable pageable);

    /**
     * 상품 정보를 조회.
     * @param productId 상품 ID
     * @return 상품 정보
     */
    BoardDto.ResponseDto getProductDetail(String productId);

}
