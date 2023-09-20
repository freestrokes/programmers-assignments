package com.freestrokes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.freestrokes.auth.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity(name = "order")
public class Order {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_id", length = 100, unique = true, nullable = false)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name = "request_msg", length = 1000)
    private String requestMsg;

    @Column(name = "reject_msg", length = 1000)
    private String rejectMsg;

    @Column(name = "completed_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime completedAt = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

    @Column(name = "rejected_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime rejectedAt = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

    @CreatedDate
    @CreationTimestamp
    @Column(name = "create_at", updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createAt = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

    public void updateOrder(
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

    @Builder(toBuilder = true)
    public Order(
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
