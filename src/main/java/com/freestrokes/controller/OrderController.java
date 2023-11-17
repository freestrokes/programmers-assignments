package com.freestrokes.controller;

import com.freestrokes.constants.PathConstants;
import com.freestrokes.dto.OrderDto;
import com.freestrokes.service.OrderService;
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

    private final OrderService orderService;

    @GetMapping(path = PathConstants.ORDERS, produces = "application/json")
    @Operation(
        summary = "주문 목록 조회",
        description = "주문 목록을 조회한다."
    )
    public ResponseEntity<Page<OrderDto.ResponseDto>> getOrders(
        // NOTE: size, sort, direction 프로퍼티를 설정한 @PageableDefault 예시
//        @ParameterObject @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
        @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) throws Exception {
        Page<OrderDto.ResponseDto> result = orderService.getOrders(pageable);
        return new ResponseEntity<Page<OrderDto.ResponseDto>>(result, HttpStatus.OK);
    }

    @GetMapping(path = PathConstants.ORDERS_DETAIL, produces = "application/json")
    @Operation(
        summary = "단일 주문 조회",
        description = "주문 ID를 이용하여 단일 주문 정보를 조회한다."
    )
    public ResponseEntity<OrderDto.ResponseDto> getOrderDetail(
        @PathVariable("orderId") String orderId
    ) throws Exception {
        OrderDto.ResponseDto result = orderService.getOrderDetail(orderId);
        return new ResponseEntity<OrderDto.ResponseDto>(result, HttpStatus.OK);
    }

    @PatchMapping(path = PathConstants.ORDERS_ACCEPT, produces = "application/json")
    @Operation(
        summary = "주문 접수 처리",
        description = "주문 ID를 이용하여 주문을 접수 상태로 수정한다."
    )
    public ResponseEntity<OrderDto.ResponseDto> patchOrderAccept(
        @PathVariable("orderId") String orderId
    ) throws Exception {
        OrderDto.ResponseDto result = orderService.patchOrderAccept(orderId);
        return new ResponseEntity<OrderDto.ResponseDto>(result, HttpStatus.OK);
    }

    @PatchMapping(path = PathConstants.ORDERS_COMPLETE, produces = "application/json")
    @Operation(
        summary = "주문 완료 처리",
        description = "주문 ID를 이용하여 주문을 완료 상태로 수정한다."
    )
    public ResponseEntity<OrderDto.ResponseDto> patchOrderComplete(
        @PathVariable("orderId") String orderId
    ) throws Exception {
        OrderDto.ResponseDto result = orderService.patchOrderComplete(orderId);
        return new ResponseEntity<OrderDto.ResponseDto>(result, HttpStatus.OK);
    }

    @PatchMapping(path = PathConstants.ORDERS_REJECT, produces = "application/json")
    @Operation(
        summary = "주문 거절 처리",
        description = "주문 ID를 이용하여 주문을 거절 상태로 수정한다."
    )
    public ResponseEntity<OrderDto.ResponseDto> patchOrderReject(
        @PathVariable("orderId") String orderId
    ) throws Exception {
        OrderDto.ResponseDto result = orderService.patchOrderReject(orderId);
        return new ResponseEntity<OrderDto.ResponseDto>(result, HttpStatus.OK);
    }

    @PatchMapping(path = PathConstants.ORDERS_SHIPPING, produces = "application/json")
    @Operation(
        summary = "주문 배송 처리",
        description = "주문 ID를 이용하여 주문을 배송 상태로 수정한다."
    )
    public ResponseEntity<OrderDto.ResponseDto> patchOrderShipping(
        @PathVariable("orderId") String orderId
    ) throws Exception {
        OrderDto.ResponseDto result = orderService.patchOrderShipping(orderId);
        return new ResponseEntity<OrderDto.ResponseDto>(result, HttpStatus.OK);
    }

    @PostMapping(path = PathConstants.ORDERS_REVIEW, produces = "application/json")
    @Operation(
        summary = "주문 리뷰 작성",
        description = "주문에 대한 리뷰를 작성한다."
    )
    public ResponseEntity<OrderDto.ResponseDto> postOrderReview(
        @PathVariable("orderId") String orderId
    ) throws Exception {
        OrderDto.ResponseDto result = orderService.postOrderReview(orderId);
        return new ResponseEntity<OrderDto.ResponseDto>(result, HttpStatus.OK);
    }

}
