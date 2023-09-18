package com.freestrokes.controller;

import com.freestrokes.constants.PathConstants;
import com.freestrokes.dto.ProductDto;
import com.freestrokes.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = PathConstants.PRODUCTS, produces = "application/json")
    @Operation(
        summary = "상품 목록 조회",
        description = "상품 목록을 조회한다."
    )
    public ResponseEntity<Page<ProductDto.ResponseDto>> getProducts(
        // TODO: size, sort, direction 프로퍼티를 설정한 @PageableDefault 예시
//        @ParameterObject @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
        @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) throws Exception {
        Page<ProductDto.ResponseDto> result = productService.getProducts(pageable);
        return new ResponseEntity<Page<ProductDto.ResponseDto>>(result, HttpStatus.OK);
    }

    @GetMapping(path = PathConstants.PRODUCTS_DETAIL, produces = "application/json")
    @Operation(
        summary = "단일 상품 조회",
        description = "단일 상품 정보를 조회한다."
    )
    public ResponseEntity<ProductDto.ResponseDto> getProductDetail(
        @PathVariable("productId") String productId
    ) throws Exception {
        ProductDto.ResponseDto result = productService.getProductDetail(productId);
        return new ResponseEntity<ProductDto.ResponseDto>(result, HttpStatus.OK);
    }

}
