package com.freestrokes.dto;

import com.freestrokes.auth.domain.User;
import com.freestrokes.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderDto {

    @Getter
    public static class RequestDto {
        String requestMsg;
        String rejectMsg;
        LocalDateTime completedAt;
        LocalDateTime rejectedAt;

        @Builder(toBuilder = true)
        public RequestDto(
            String requestMsg,
            String rejectMsg,
            LocalDateTime completedAt,
            LocalDateTime rejectedAt
        ) {
            this.requestMsg = requestMsg;
            this.rejectMsg = rejectMsg;
            this.completedAt = completedAt;
            this.rejectedAt = rejectedAt;
        }

        public Order toEntity() {
            return Order.builder()
                .requestMsg(requestMsg)
                .rejectMsg(rejectMsg)
                .completedAt(completedAt)
                .rejectedAt(rejectedAt)
                .build();
        }
    }

    @Getter
    public static class ResponseDto {
        String orderId;
        User user;
        Product product;
        Review review;
        String requestMsg;
        String rejectMsg;
        LocalDateTime completedAt;
        LocalDateTime rejectedAt;

        // TODO: @JsonIgnore
        // 양방향 연관관계 매핑을 한 경우 순환 참조가 발생할 수 있음
        // 프로퍼티에 @JsonIgnore 어노테이션을 추가하여 해결.

        @Builder(toBuilder = true)
        public ResponseDto(
            String orderId,
            User user,
            Product product,
            Review review,
            String requestMsg,
            String rejectMsg,
            LocalDateTime completedAt,
            LocalDateTime rejectedAt
        ) {
            this.orderId = orderId;
            this.user = user;
            this.product = product;
            this.review = review;
            this.requestMsg = requestMsg;
            this.rejectMsg = rejectMsg;
            this.completedAt = completedAt;
            this.rejectedAt = rejectedAt;
        }
    }

}
