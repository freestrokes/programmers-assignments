package com.freestrokes.dto;

import com.freestrokes.domain.Board;
import com.freestrokes.domain.BoardComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewDto {

    @Getter
    public static class RequestDto {
        private String title;
        private String content;
        private String author;

        @Builder(toBuilder = true)
        public RequestDto(
            String title,
            String content,
            String author
        ) {
            this.title = title;
            this.content = content;
            this.author = author;
        }

        public Board toEntity() {
            return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        }
    }

    @Getter
    public static class ResponseDto {
        private String boardId;
        private String title;
        private String content;
        private String author;

        // NOTE: @JsonIgnore
        // 양방향 연관관계 매핑을 한 경우 순환 참조가 발생할 수 있음
        // @JsonIgnore 어노테이션을 추가하여 해결.
//        @JsonIgnore
        private List<BoardComment> boardComments;

        @Builder(toBuilder = true)
        public ResponseDto(
            String boardId,
            String title,
            String content,
            String author,
            List<BoardComment> boardComments
        ) {
            this.boardId = boardId;
            this.title = title;
            this.content = content;
            this.author = author;
            this.boardComments = boardComments;
        }
    }

}
