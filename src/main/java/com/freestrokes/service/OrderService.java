package com.freestrokes.service;

import com.freestrokes.auth.domain.User;
import com.freestrokes.domain.Order;
import com.freestrokes.domain.BoardComment;
import com.freestrokes.domain.Product;
import com.freestrokes.domain.Review;
import com.freestrokes.dto.OrderDto;
import com.freestrokes.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService implements OrderRequestService {

    private final OrderRepository orderRepository;

    /**
     * 주문 목록을 조회
     * @param pageable 페이징 정보
     * @return 주문 목록
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto.ResponseDto> getOrders(Pageable pageable) {

        Page<Order> findOrders = orderRepository.findAll(pageable);
        List<OrderDto.ResponseDto> ordersResponseDto = new ArrayList<>();

//        String orderId;
//        User user;
//        Product product;
//        Review review;
//        String requestMsg;
//        String rejectMsg;
//        LocalDateTime completedAt;
//        LocalDateTime rejectedAt;

        // 조회한 주문 목록에 대한 DTO 변환
        findOrders.getContent().forEach(order -> {
            ordersResponseDto.add(
                OrderDto.ResponseDto.builder()
                    .orderId(order.getOrderId())
//                    .user(order.getUser())
//                    .product(order.getProduct())
//                    .review(order.getReview())
                    .user(
                        User.builder()
                            .userId(order.getUser().getUserId())
                            .name(order.getUser().getName())
                            .email(order.getUser().getEmail())
                            .role(order.getUser().getRole())
                            .build()
                    )
                    .product(
                        Product.builder()
                            .productId(order.getProduct().getProductId())
                            .name(order.getProduct().getName())
                            .details(order.getProduct().getDetails())
                            .reviewCount(order.getProduct().getReviewCount())
                            .build()
                    )
                    .review(
                        Review.builder()
                            .reviewId(order.getReview().getReviewId())
                            .content(order.getReview().getContent())
                            .build()
                    )
                    .requestMsg(order.getRequestMsg())
                    .rejectMsg(order.getRejectMsg())
                    .completedAt(order.getCompletedAt())
                    .rejectedAt(order.getRejectedAt())
                    .build()
            );
        });

        // TODO: CASE1) 1:N 양방향 매핑 조회 후 DTO 변환 (stream 이용한 방법)
        // 게시글 조회
//        List<OrderDto.ResponseDto> ordersResponseDto = orderRepository.findAll(pageable)
//            .stream()
//            .map(board -> {
//                return OrderDto.ResponseDto.builder()
//                    .boardId(order.getBoardId())
//                    .title(order.getTitle())
//                    .content(order.getContent())
//                    .author(order.getAuthor())
//                    .boardComments(
//                        order.getBoardComments().stream().map(boardComment -> {
//                            return BoardComment.builder()
//                                .boardCommentId(boardComment.getBoardCommentId())
//                                .board(board)
//                                .content(boardComment.getContent())
//                                .author(boardComment.getAuthor())
//                                .build();
//                        }).collect(Collectors.toList())
//                    )
//                    .build();
//            })
//            .collect(Collectors.toList());

        //TODO: CASE2) 1:N 양방향 매핑 조회 후 DTO 변환 (for문 이용한 방법)
//        List<Board> boardList = orderRepository.findAll();
//        List<OrderDto.ResponseDto> ordersResponseDto = new ArrayList<>();
//
//        for (Board board : boardList) {
//            List<BoardComment> boardComments = new ArrayList<>();
//
//            // Board Comment DTO
//            if (order.getBoardComments().size() > 0) {
//                order.getBoardComments().stream().forEach(boardComment -> {
//                    boardComments.add(
//                        BoardComment.builder()
//                            .boardCommentId(boardComment.getBoardCommentId())
//                            .board(board)
//                            .content(boardComment.getContent())
//                            .author(boardComment.getAuthor())
//                            .build()
//                    );
//                });
//            }
//
//            // Order DTO
//            ordersResponseDto.add(
//                OrderDto.ResponseDto.builder()
//                    .boardId(order.getBoardId())
//                    .title(order.getTitle())
//                    .content(order.getContent())
//                    .author(order.getAuthor())
//                    .boardComments(boardComments)
//                    .build()
//            );
//        }
//
//        return ordersResponseDto;

        return new PageImpl<>(ordersResponseDto, pageable, findOrders.getTotalElements());

    }

    /**
     * 단일 주문 정보를 조회
     * @param orderId 주문 ID
     * @return 조회한 주문 정보
     */
    @Override
    @Transactional
    public OrderDto.ResponseDto getOrderDetail(String orderId) {

        // 주문 정보 조회
        Order findOrder = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        return OrderDto.ResponseDto.builder()
            .orderId(findOrder.getOrderId())
//            .user(findOrder.getUser())
//            .product(findOrder.getProduct())
//            .review(findOrder.getReview())
            .user(
                User.builder()
                    .userId(findOrder.getUser().getUserId())
                    .name(findOrder.getUser().getName())
                    .email(findOrder.getUser().getEmail())
                    .role(findOrder.getUser().getRole())
                    .build()
            )
            .product(
                Product.builder()
                    .productId(findOrder.getProduct().getProductId())
                    .name(findOrder.getProduct().getName())
                    .details(findOrder.getProduct().getDetails())
                    .reviewCount(findOrder.getProduct().getReviewCount())
                    .build()
            )
            .review(
                Review.builder()
                    .reviewId(findOrder.getReview().getReviewId())
                    .content(findOrder.getReview().getContent())
                    .build()
            )
            .requestMsg(findOrder.getRequestMsg())
            .rejectMsg(findOrder.getRejectMsg())
            .completedAt(findOrder.getCompletedAt())
            .rejectedAt(findOrder.getRejectedAt())
            .build();

    }

    /**
     * 주문을 접수 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    @Override
    @Transactional
    public OrderDto.ResponseDto patchOrderAccept(String orderId) {

        // 주문 조회
        Order findOrder = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        // TODO: @Transactional 어노테이션 사용하여 update 하려는 경우
        // @Transactional 어노테이션을 명시하여 repository save() 호출 없이 저장 가능.
        // board builder() 생성 없이 findBoard > updateBoard() 호출하는 것 만으로도 저장 가능
//        findBoard.updateBoard(
//            boardRequestDto.getTitle(),
//            boardRequestDto.getContent(),
//            boardRequestDto.getAuthor()
//        );

        // TODO: @Transactional 어노테이션이 없이 update 하려는 경우
//        Board board = Board.builder()
//            .title(boardRequestDto.getTitle())
//            .content(boardRequestDto.getContent())
//            .author(boardRequestDto.getAuthor())
//            .build();
//
//        findBoard.updateBoard(board);
//
//        orderRepository.save(findBoard);

        return OrderDto.ResponseDto.builder()
            .orderId(findOrder.getOrderId())
//            .user(findOrder.getUser())
//            .product(findOrder.getProduct())
//            .review(findOrder.getReview())
            .user(
                User.builder()
                    .userId(findOrder.getUser().getUserId())
                    .name(findOrder.getUser().getName())
                    .email(findOrder.getUser().getEmail())
                    .role(findOrder.getUser().getRole())
                    .build()
            )
            .product(
                Product.builder()
                    .productId(findOrder.getProduct().getProductId())
                    .name(findOrder.getProduct().getName())
                    .details(findOrder.getProduct().getDetails())
                    .reviewCount(findOrder.getProduct().getReviewCount())
                    .build()
            )
            .review(
                Review.builder()
                    .reviewId(findOrder.getReview().getReviewId())
                    .content(findOrder.getReview().getContent())
                    .build()
            )
            .requestMsg(findOrder.getRequestMsg())
            .rejectMsg(findOrder.getRejectMsg())
            .completedAt(findOrder.getCompletedAt())
            .rejectedAt(findOrder.getRejectedAt())
            .build();

    }

    /**
     * 주문을 완료 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    @Override
    @Transactional
    public OrderDto.ResponseDto patchOrderComplete(String orderId) {

        // 주문 조회
        Order findOrder = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        // TODO: @Transactional 어노테이션 사용하여 update 하려는 경우
        // @Transactional 어노테이션을 명시하여 repository save() 호출 없이 저장 가능.
        // board builder() 생성 없이 findBoard > updateBoard() 호출하는 것 만으로도 저장 가능
//        findBoard.updateBoard(
//            boardRequestDto.getTitle(),
//            boardRequestDto.getContent(),
//            boardRequestDto.getAuthor()
//        );

        // TODO: @Transactional 어노테이션이 없이 update 하려는 경우
//        Board board = Board.builder()
//            .title(boardRequestDto.getTitle())
//            .content(boardRequestDto.getContent())
//            .author(boardRequestDto.getAuthor())
//            .build();
//
//        findBoard.updateBoard(board);
//
//        orderRepository.save(findBoard);

        return OrderDto.ResponseDto.builder()
            .orderId(findOrder.getOrderId())
//            .user(findOrder.getUser())
//            .product(findOrder.getProduct())
//            .review(findOrder.getReview())
            .user(
                User.builder()
                    .userId(findOrder.getUser().getUserId())
                    .name(findOrder.getUser().getName())
                    .email(findOrder.getUser().getEmail())
                    .role(findOrder.getUser().getRole())
                    .build()
            )
            .product(
                Product.builder()
                    .productId(findOrder.getProduct().getProductId())
                    .name(findOrder.getProduct().getName())
                    .details(findOrder.getProduct().getDetails())
                    .reviewCount(findOrder.getProduct().getReviewCount())
                    .build()
            )
            .review(
                Review.builder()
                    .reviewId(findOrder.getReview().getReviewId())
                    .content(findOrder.getReview().getContent())
                    .build()
            )
            .requestMsg(findOrder.getRequestMsg())
            .rejectMsg(findOrder.getRejectMsg())
            .completedAt(findOrder.getCompletedAt())
            .rejectedAt(findOrder.getRejectedAt())
            .build();

    }

    /**
     * 주문을 거절 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    @Override
    @Transactional
    public OrderDto.ResponseDto patchOrderReject(String orderId) {

        // 주문 조회
        Order findOrder = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        // TODO: @Transactional 어노테이션 사용하여 update 하려는 경우
        // @Transactional 어노테이션을 명시하여 repository save() 호출 없이 저장 가능.
        // board builder() 생성 없이 findBoard > updateBoard() 호출하는 것 만으로도 저장 가능
//        findBoard.updateBoard(
//            boardRequestDto.getTitle(),
//            boardRequestDto.getContent(),
//            boardRequestDto.getAuthor()
//        );

        // TODO: @Transactional 어노테이션이 없이 update 하려는 경우
//        Board board = Board.builder()
//            .title(boardRequestDto.getTitle())
//            .content(boardRequestDto.getContent())
//            .author(boardRequestDto.getAuthor())
//            .build();
//
//        findBoard.updateBoard(board);
//
//        orderRepository.save(findBoard);

        return OrderDto.ResponseDto.builder()
            .orderId(findOrder.getOrderId())
//            .user(findOrder.getUser())
//            .product(findOrder.getProduct())
//            .review(findOrder.getReview())
            .user(
                User.builder()
                    .userId(findOrder.getUser().getUserId())
                    .name(findOrder.getUser().getName())
                    .email(findOrder.getUser().getEmail())
                    .role(findOrder.getUser().getRole())
                    .build()
            )
            .product(
                Product.builder()
                    .productId(findOrder.getProduct().getProductId())
                    .name(findOrder.getProduct().getName())
                    .details(findOrder.getProduct().getDetails())
                    .reviewCount(findOrder.getProduct().getReviewCount())
                    .build()
            )
            .review(
                Review.builder()
                    .reviewId(findOrder.getReview().getReviewId())
                    .content(findOrder.getReview().getContent())
                    .build()
            )
            .requestMsg(findOrder.getRequestMsg())
            .rejectMsg(findOrder.getRejectMsg())
            .completedAt(findOrder.getCompletedAt())
            .rejectedAt(findOrder.getRejectedAt())
            .build();

    }

    /**
     * 주문을 배송 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    @Override
    @Transactional
    public OrderDto.ResponseDto patchOrderShipping(String orderId) {

        // 주문 조회
        Order findOrder = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        // TODO: @Transactional 어노테이션 사용하여 update 하려는 경우
        // @Transactional 어노테이션을 명시하여 repository save() 호출 없이 저장 가능.
        // board builder() 생성 없이 findBoard > updateBoard() 호출하는 것 만으로도 저장 가능
//        findBoard.updateBoard(
//            boardRequestDto.getTitle(),
//            boardRequestDto.getContent(),
//            boardRequestDto.getAuthor()
//        );

        // TODO: @Transactional 어노테이션이 없이 update 하려는 경우
//        Board board = Board.builder()
//            .title(boardRequestDto.getTitle())
//            .content(boardRequestDto.getContent())
//            .author(boardRequestDto.getAuthor())
//            .build();
//
//        findBoard.updateBoard(board);
//
//        orderRepository.save(findBoard);

        return OrderDto.ResponseDto.builder()
            .orderId(findOrder.getOrderId())
//            .user(findOrder.getUser())
//            .product(findOrder.getProduct())
//            .review(findOrder.getReview())
            .user(
                User.builder()
                    .userId(findOrder.getUser().getUserId())
                    .name(findOrder.getUser().getName())
                    .email(findOrder.getUser().getEmail())
                    .role(findOrder.getUser().getRole())
                    .build()
            )
            .product(
                Product.builder()
                    .productId(findOrder.getProduct().getProductId())
                    .name(findOrder.getProduct().getName())
                    .details(findOrder.getProduct().getDetails())
                    .reviewCount(findOrder.getProduct().getReviewCount())
                    .build()
            )
            .review(
                Review.builder()
                    .reviewId(findOrder.getReview().getReviewId())
                    .content(findOrder.getReview().getContent())
                    .build()
            )
            .requestMsg(findOrder.getRequestMsg())
            .rejectMsg(findOrder.getRejectMsg())
            .completedAt(findOrder.getCompletedAt())
            .rejectedAt(findOrder.getRejectedAt())
            .build();

    }

    /**
     * 주문 리뷰 등록
     * @param orderId 주문 ID
     * @return 등록한 주문 리뷰 정보
     */
    @Override
    @Transactional
    public OrderDto.ResponseDto postOrderReview(String orderId) {

        // 주문 조회
        Order findOrder = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        // TODO: @Transactional 어노테이션 사용하여 update 하려는 경우
        // @Transactional 어노테이션을 명시하여 repository save() 호출 없이 저장 가능.
        // board builder() 생성 없이 findBoard > updateBoard() 호출하는 것 만으로도 저장 가능
//        findBoard.updateBoard(
//            boardRequestDto.getTitle(),
//            boardRequestDto.getContent(),
//            boardRequestDto.getAuthor()
//        );

        // TODO: @Transactional 어노테이션이 없이 update 하려는 경우
//        Board board = Board.builder()
//            .title(boardRequestDto.getTitle())
//            .content(boardRequestDto.getContent())
//            .author(boardRequestDto.getAuthor())
//            .build();
//
//        findBoard.updateBoard(board);
//
//        orderRepository.save(findBoard);

        return OrderDto.ResponseDto.builder()
            .orderId(findOrder.getOrderId())
//            .user(findOrder.getUser())
//            .product(findOrder.getProduct())
//            .review(findOrder.getReview())
            .user(
                User.builder()
                    .userId(findOrder.getUser().getUserId())
                    .name(findOrder.getUser().getName())
                    .email(findOrder.getUser().getEmail())
                    .role(findOrder.getUser().getRole())
                    .build()
            )
            .product(
                Product.builder()
                    .productId(findOrder.getProduct().getProductId())
                    .name(findOrder.getProduct().getName())
                    .details(findOrder.getProduct().getDetails())
                    .reviewCount(findOrder.getProduct().getReviewCount())
                    .build()
            )
            .review(
                Review.builder()
                    .reviewId(findOrder.getReview().getReviewId())
                    .content(findOrder.getReview().getContent())
                    .build()
            )
            .requestMsg(findOrder.getRequestMsg())
            .rejectMsg(findOrder.getRejectMsg())
            .completedAt(findOrder.getCompletedAt())
            .rejectedAt(findOrder.getRejectedAt())
            .build();

    }

}
