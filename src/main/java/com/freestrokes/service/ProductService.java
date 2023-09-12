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
public class ProductService implements ProductRequestService {

//    private final ProductRepository productRepository;
    private final BoardRepository boardRepository;

    /**
     * 상품 목록을 조회
     * @param pageable 페이징 정보
     * @return 상품 목록
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BoardDto.ResponseDto> getProducts(Pageable pageable) {

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
     * 단일 상품 정보를 조회
     * @param productId 상품 ID
     * @return 조회한 상품 정보
     */
    @Override
    @Transactional
    public BoardDto.ResponseDto getProductDetail(String productId) {

        // 상품 정보 조회
        Board findBoard = boardRepository.findById(productId).orElseThrow(NoSuchElementException::new);

        return BoardDto.ResponseDto.builder()
            .boardId(findBoard.getBoardId())
            .title(findBoard.getTitle())
            .content(findBoard.getContent())
            .author(findBoard.getAuthor())
            .build();

    }

}
