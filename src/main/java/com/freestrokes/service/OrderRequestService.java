package com.freestrokes.service;

import com.freestrokes.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 주문 요청을 위한 서비스 인터페이스
 */
public interface OrderRequestService {

    /**
     * 주문 목록을 조회
     * @param pageable 페이징 정보
     * @return 주문 목록
     */
    Page<OrderDto.ResponseDto> getOrders(Pageable pageable);

    /**
     * 단일 주문 정보를 조회
     * @param orderId 주문 ID
     * @return 조회한 주문 정보
     */
    OrderDto.ResponseDto getOrderDetail(String orderId);

    /**
     * 주문을 접수 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    OrderDto.ResponseDto patchOrderAccept(String orderId);

    /**
     * 주문을 완료 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    OrderDto.ResponseDto patchOrderComplete(String orderId);

    /**
     * 주문을 거절 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    OrderDto.ResponseDto patchOrderReject(String orderId);

    /**
     * 주문을 배송 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    OrderDto.ResponseDto patchOrderShipping(String orderId);

    /**
     * 주문 리뷰 등록
     * @param orderId 주문 ID
     * @return 등록한 주문 리뷰 정보
     */
    OrderDto.ResponseDto postOrderReview(String orderId);

}
