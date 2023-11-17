package com.freestrokes.dto;

import com.freestrokes.domain.BoardComment;
import com.freestrokes.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductDto {

    @Getter
    public static class RequestDto {
        private String name;
        private String details;
        private Integer reviewCount;

        @Builder(toBuilder = true)
        public RequestDto(
            String name,
            String details,
            Integer reviewCount
        ) {
            this.name = name;
            this.details = details;
            this.reviewCount = reviewCount;
        }

        public Product toEntity() {
            return Product.builder()
                .name(name)
                .details(details)
                .reviewCount(reviewCount)
                .build();
        }
    }

    @Getter
    public static class ResponseDto {
        private String productId;
        private String name;
        private String details;
        private Integer reviewCount;
        private LocalDateTime createAt;

        // NOTE: @JsonIgnore
        // 양방향 연관관계 매핑을 한 경우 순환 참조가 발생할 수 있음
        // @JsonIgnore 어노테이션을 추가하여 해결.
//        @JsonIgnore
        private List<BoardComment> boardComments;

        @Builder(toBuilder = true)
        public ResponseDto(
            String productId,
            String name,
            String details,
            Integer reviewCount,
            LocalDateTime createAt
        ) {
            this.productId = productId;
            this.name = name;
            this.details = details;
            this.reviewCount = reviewCount;
            this.createAt = createAt;
        }
    }

}
