package com.freestrokes.service;

import com.freestrokes.domain.Product;
import com.freestrokes.dto.ProductDto;
import com.freestrokes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductRequestService {

    private final ProductRepository productRepository;

    /**
     * 상품 목록을 조회
     * @param pageable 페이징 정보
     * @return 상품 목록
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto.ResponseDto> getProducts(Pageable pageable) {

        Page<Product> findProducts = productRepository.findAll(pageable);
        List<ProductDto.ResponseDto> productsResponseDto = new ArrayList<>();

        // 조회한 상품 목록에 대한 DTO 변환
        findProducts.getContent().forEach(product -> {
            productsResponseDto.add(
                ProductDto.ResponseDto.builder()
                    .productId(product.getProductId())
                    .name(product.getName())
                    .details(product.getDetails())
                    .reviewCount(product.getReviewCount())
                    .createAt(product.getCreateAt())
                    .build()
            );
        });

        return new PageImpl<>(productsResponseDto, pageable, findProducts.getTotalElements());

    }

    /**
     * 단일 상품 정보를 조회
     * @param productId 상품 ID
     * @return 조회한 상품 정보
     */
    @Override
    @Transactional
    public ProductDto.ResponseDto getProductDetail(String productId) {

        // 상품 정보 조회
        Product findProduct = productRepository.findById(productId).orElseThrow(NoSuchElementException::new);

        return ProductDto.ResponseDto.builder()
            .productId(findProduct.getProductId())
            .name(findProduct.getName())
            .details(findProduct.getDetails())
            .reviewCount(findProduct.getReviewCount())
            .createAt(findProduct.getCreateAt())
            .build();

    }

}
