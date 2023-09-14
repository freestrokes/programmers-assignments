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
import java.util.stream.Collectors;

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

        // TODO: CASE1) 1:N 양방향 매핑 조회 후 DTO 변환 (stream 이용한 방법)
        // 게시글 조회
//        List<BoardDto.ResponseDto> productsResponseDto = boardRepository.findAll(pageable)
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
//        List<BoardDto.ResponseDto> productsResponseDto = new ArrayList<>();
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
//            productsResponseDto.add(
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
//        return productsResponseDto;

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
