package com.freestrokes.controller;

import com.freestrokes.aop.LogExecutionTime;
import com.freestrokes.properties.ApplicationProperties;
import com.freestrokes.constants.PathConstants;
import com.freestrokes.dto.BoardDto;
import com.freestrokes.service.BoardService;
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

//    private final BoardService boardService;
//    private final ProductService productService;

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
        // TODO
//        Page<BoardDto.ResponseDto> result = boardService.getBoards(pageable);
        Page<BoardDto.ResponseDto> result = null;
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
//        BoardDto.ResponseDto result = boardService.getBoardDetail(productId);
        BoardDto.ResponseDto result = null;
        return new ResponseEntity<BoardDto.ResponseDto>(result, HttpStatus.OK);
    }

//
//    @PostMapping(path = PathConstants.BOARDS, produces = "application/json")
//    @Operation(
//        summary = "게시글 등록",
//        description = "게시글 정보를 등록한다."
//    )
//    public ResponseEntity<BoardDto.ResponseDto> postBoard(
//        @RequestBody BoardDto.RequestDto boardRequestDto
//    ) throws Exception {
//        BoardDto.ResponseDto result = boardService.postBoard(boardRequestDto);
//        return new ResponseEntity<BoardDto.ResponseDto>(result, HttpStatus.OK);
//    }
//
//    @PutMapping(path = PathConstants.BOARD, produces = "application/json")
//    @Operation(
//        summary = "게시글 수정",
//        description = "게시글 ID를 이용하여 게시글 정보를 수정한다."
//    )
//    public ResponseEntity<BoardDto.ResponseDto> putBoard(
//        @PathVariable("boardId") String boardId,
//        @RequestBody BoardDto.RequestDto boardRequestDto
//    ) throws Exception {
//        BoardDto.ResponseDto result = boardService.putBoard(boardId, boardRequestDto);
//        return new ResponseEntity<BoardDto.ResponseDto>(result, HttpStatus.OK);
//    }
//
//    @DeleteMapping(path = PathConstants.BOARD, produces = "application/json")
//    @Operation(
//        summary = "게시글 삭제",
//        description = "게시글 ID를 이용하여 게시글 정보를 삭제한다."
//    )
//    public ResponseEntity<?> deleteBoard(
//        @PathVariable("boardId") String boardId
//    ) throws Exception {
//        boardService.deleteBoard(boardId);
//        return new ResponseEntity<>("{}", HttpStatus.OK);
//    }

}
