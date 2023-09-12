package com.freestrokes.service;

import com.freestrokes.domain.Board;
import com.freestrokes.domain.BoardComment;
import com.freestrokes.dto.BoardDto;
import com.freestrokes.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService implements OrderRequestService {

//    private final OrderRepository orderRepository;
    private final BoardRepository boardRepository;

    /**
     * 주문 목록을 조회
     * @param pageable 페이징 정보
     * @return 주문 목록
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BoardDto.ResponseDto> getOrders(Pageable pageable) {

        Page<Board> findBoards = boardRepository.findAll(pageable);
        List<BoardDto.ResponseDto> boardsResponseDto = new ArrayList<>();

        // 조회한 게시글 목록에 대한 DTO 변환
        findBoards.getContent().forEach(board -> {
            boardsResponseDto.add(
                BoardDto.ResponseDto.builder()
                    .boardId(board.getBoardId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .author(board.getAuthor())
                    .boardComments(
                        board.getBoardComments().stream().map(boardComment -> {
                            return BoardComment.builder()
                                .boardCommentId(boardComment.getBoardCommentId())
                                .board(board)
                                .content(boardComment.getContent())
                                .author(boardComment.getAuthor())
                                .build();
                        }).collect(Collectors.toList())
                    )
                    .build()
            );
        });

        // TODO: CASE1) 1:N 양방향 매핑 조회 후 DTO 변환 (stream 이용한 방법)
        // 게시글 조회
//        List<BoardDto.ResponseDto> boardsResponseDto = boardRepository.findAll(pageable)
//            .stream()
//            .map(board -> {
//                return BoardDto.ResponseDto.builder()
//                    .boardId(board.getBoardId())
//                    .title(board.getTitle())
//                    .content(board.getContent())
//                    .author(board.getAuthor())
//                    .boardComments(
//                        board.getBoardComments().stream().map(boardComment -> {
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
//        List<Board> boardList = boardRepository.findAll();
//        List<BoardDto.ResponseDto> boardsResponseDto = new ArrayList<>();
//
//        for (Board board : boardList) {
//            List<BoardComment> boardComments = new ArrayList<>();
//
//            // Board Comment DTO
//            if (board.getBoardComments().size() > 0) {
//                board.getBoardComments().stream().forEach(boardComment -> {
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
//            // Board DTO
//            boardsResponseDto.add(
//                BoardDto.ResponseDto.builder()
//                    .boardId(board.getBoardId())
//                    .title(board.getTitle())
//                    .content(board.getContent())
//                    .author(board.getAuthor())
//                    .boardComments(boardComments)
//                    .build()
//            );
//        }
//
//        return boardsResponseDto;

        return new PageImpl<>(boardsResponseDto, pageable, findBoards.getTotalElements());

    }

    /**
     * 단일 주문 정보를 조회
     * @param orderId 주문 ID
     * @return 조회한 주문 정보
     */
    @Override
    @Transactional
    public BoardDto.ResponseDto getOrderDetail(String orderId) {

        // 주문 정보 조회
        Board findBoard = boardRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        return BoardDto.ResponseDto.builder()
            .boardId(findBoard.getBoardId())
            .title(findBoard.getTitle())
            .content(findBoard.getContent())
            .author(findBoard.getAuthor())
            .build();

    }

    /**
     * 주문을 접수 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    @Override
    @Transactional
    public BoardDto.ResponseDto patchOrderAccept(String orderId) {

        // 주문 조회
        Board findBoard = boardRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

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
//        boardRepository.save(findBoard);

        return BoardDto.ResponseDto.builder()
            .boardId(findBoard.getBoardId())
            .title(findBoard.getTitle())
            .content(findBoard.getContent())
            .author(findBoard.getAuthor())
            .build();

    }

    /**
     * 주문을 완료 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    @Override
    @Transactional
    public BoardDto.ResponseDto patchOrderComplete(String orderId) {

        // 주문 조회
        Board findBoard = boardRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

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
//        boardRepository.save(findBoard);

        return BoardDto.ResponseDto.builder()
            .boardId(findBoard.getBoardId())
            .title(findBoard.getTitle())
            .content(findBoard.getContent())
            .author(findBoard.getAuthor())
            .build();

    }

    /**
     * 주문을 거절 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    @Override
    @Transactional
    public BoardDto.ResponseDto patchOrderReject(String orderId) {

        // 주문 조회
        Board findBoard = boardRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

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
//        boardRepository.save(findBoard);

        return BoardDto.ResponseDto.builder()
            .boardId(findBoard.getBoardId())
            .title(findBoard.getTitle())
            .content(findBoard.getContent())
            .author(findBoard.getAuthor())
            .build();

    }

    /**
     * 주문을 배송 상태로 수정
     * @param orderId 주문 ID
     * @return 수정한 주문 정보
     */
    @Override
    @Transactional
    public BoardDto.ResponseDto patchOrderShipping(String orderId) {

        // 주문 조회
        Board findBoard = boardRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

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
//        boardRepository.save(findBoard);

        return BoardDto.ResponseDto.builder()
            .boardId(findBoard.getBoardId())
            .title(findBoard.getTitle())
            .content(findBoard.getContent())
            .author(findBoard.getAuthor())
            .build();

    }

    /**
     * 주문 리뷰 등록
     * @param orderId 주문 ID
     * @return 등록한 주문 리뷰 정보
     */
    @Override
    @Transactional
    public BoardDto.ResponseDto postOrderReview(String orderId) {

        // 주문 조회
        Board findBoard = boardRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

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
//        boardRepository.save(findBoard);

        return BoardDto.ResponseDto.builder()
            .boardId(findBoard.getBoardId())
            .title(findBoard.getTitle())
            .content(findBoard.getContent())
            .author(findBoard.getAuthor())
            .build();

    }

}
