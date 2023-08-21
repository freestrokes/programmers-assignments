package com.freestrokes.controller;

import com.freestrokes.constants.PathConstants;
import com.freestrokes.dto.BoardCommentDto;
import com.freestrokes.service.BoardCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping(path = PathConstants.BOARD_COMMENTS, produces = "application/json")
    @Operation(
        summary = "게시글 댓글 등록",
        description = "게시글 댓글 정보를 등록한다."
    )
    public ResponseEntity<BoardCommentDto.ResponseDto> postBoardComment(
        @RequestBody BoardCommentDto.RequestDto boardCommentRequestDto
    ) throws Exception {
        BoardCommentDto.ResponseDto result = boardCommentService.postBoardComment(boardCommentRequestDto);
        return new ResponseEntity<BoardCommentDto.ResponseDto>(result, HttpStatus.OK);
    }

    @PutMapping(path = PathConstants.BOARD_COMMENT, produces = "application/json")
    @Operation(
        summary = "게시글 댓글 수정",
        description = "게시글 댓글 ID를 이용하여 게시글 댓글 정보를 수정한다."
    )
    public ResponseEntity<BoardCommentDto.ResponseDto> putBoardComment(
        @PathVariable String boardCommentId,
        @RequestBody BoardCommentDto.RequestDto boardCommentRequestDto
    ) throws Exception {
        BoardCommentDto.ResponseDto result = boardCommentService.putBoardComment(boardCommentId, boardCommentRequestDto);
        return new ResponseEntity<BoardCommentDto.ResponseDto>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = PathConstants.BOARD_COMMENT, produces = "application/json")
    @Operation(
        summary = "게시글 댓글 삭제",
        description = "게시글 댓글 ID를 이용하여 게시글 댓글 정보를 삭제한다."
    )
    public ResponseEntity<?> deleteBoardComment(
        @PathVariable String boardCommentId
    ) throws Exception {
        boardCommentService.deleteBoardComment(boardCommentId);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
