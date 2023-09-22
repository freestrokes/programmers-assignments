package com.freestrokes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_id", length = 100, unique = true, nullable = false)
    private String productId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "details", length = 1000, nullable = false)
    private String details;

    @Column(name = "review_count", nullable = false)
    @ColumnDefault("0")
    private Integer reviewCount;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "create_at", updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createAt = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

    public void updateProduct(
        String name,
        String details,
        Integer reviewCount
    ) {
        this.name = name;
        this.details = details;
        this.reviewCount = reviewCount;
    }

    @Builder(toBuilder = true)
    public Product(
        String productId,
        String name,
        String details,
        Integer reviewCount
    ) {
        this.productId = productId;
        this.name = name;
        this.details = details;
        this.reviewCount = reviewCount;
    }

}
