package com.freestrokes.controller;

import com.freestrokes.aop.LogExecutionTime;
import com.freestrokes.properties.ApplicationProperties;
import com.freestrokes.constants.PathConstants;
import com.freestrokes.dto.BoardDto;
import com.freestrokes.service.BoardService;
import com.freestrokes.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<BoardDto.ResponseDto>> getProducts(
        // TODO: size, sort, direction 프로퍼티를 설정한 @PageableDefault 예시
//        @ParameterObject @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
        @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) throws Exception {
        Page<BoardDto.ResponseDto> result = productService.getProducts(pageable);
        return new ResponseEntity<Page<BoardDto.ResponseDto>>(result, HttpStatus.OK);
    }

    @GetMapping(path = PathConstants.PRODUCTS_DETAIL, produces = "application/json")
    @Operation(
        summary = "단일 상품 조회",
        description = "단일 상품 정보를 조회한다."
    )
    public ResponseEntity<BoardDto.ResponseDto> getProductDetail(
        @PathVariable("productId") String productId
    ) throws Exception {
        // TODO
        BoardDto.ResponseDto result = productService.getProductDetail(productId);
        return new ResponseEntity<BoardDto.ResponseDto>(result, HttpStatus.OK);
    }

}
