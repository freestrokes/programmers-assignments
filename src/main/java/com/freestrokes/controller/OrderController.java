package com.freestrokes.controller;

import com.freestrokes.aop.LogExecutionTime;
import com.freestrokes.constants.PathConstants;
import com.freestrokes.dto.BoardDto;
import com.freestrokes.properties.ApplicationProperties;
import com.freestrokes.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderController {

//  * 주문 리뷰작성: /api/orders/{id}/review -> POST
//  * 주문 접수처리: /api/orders/{id}/accept -> POST
//  * 주문 배송처리: /api/orders/{id}/shipping -> PUT
//  * 주문 완료처리: /api/orders/{id}/complete -> PUT
//  * 주문 거절처리: /api/orders/{id}/reject -> PUT
//  * 단일 주문조회: /api/orders/{id} -> GET
//  * 주문 목록조회: /api/orders -> GET

//    private final OrderService orderService;

    @GetMapping(path = PathConstants.ORDERS, produces = "application/json")
    @Operation(
        summary = "주문 목록 조회",
        description = "주문 목록을 조회한다."
    )
    public ResponseEntity<Page<BoardDto.ResponseDto>> getOrders(
        // TODO: size, sort, direction 프로퍼티를 설정한 @PageableDefault 예시
//        @ParameterObject @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
        @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) throws Exception {
        // TODO
//        Page<BoardDto.ResponseDto> result = boardService.getBoards(pageable);
        Page<BoardDto.ResponseDto> result = null;
        return new ResponseEntity<Page<BoardDto.ResponseDto>>(result, HttpStatus.OK);
    }

    @GetMapping(path = PathConstants.ORDERS_DETAIL, produces = "application/json")
    @Operation(
        summary = "단일 주문 조회",
        description = "단일 주문 정보를 조회한다."
    )
    public ResponseEntity<BoardDto.ResponseDto> getOrderDetail(
        @PathVariable("orderId") String orderId
    ) throws Exception {
        // TODO
//        BoardDto.ResponseDto result = boardService.getBoardDetail(productId);
        BoardDto.ResponseDto result = null;
        return new ResponseEntity<BoardDto.ResponseDto>(result, HttpStatus.OK);
    }

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
