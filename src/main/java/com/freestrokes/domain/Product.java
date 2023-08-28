package com.freestrokes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.List;

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


    // TODO: id 필드에 sequence 적용하려는 경우엔 아래와 같이 사용.
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "board_id", unique = true, nullable = false)
//    private Long boardId;

    // TODO: 양방향 연관 관계를 설정한 경우
    // 컨트롤러에서 JSON으로 값을 출력하는 경우 타입을 변환해야 하는데
    // 변환되는 엔티티의 필드가 다른 엔티티를 참조하고 또 그 엔티티의 필드가 또 다른 엔티티를 참조하는 동작이 반복
    // Infinite Recursion 발생
    // 해결하는 여러가지 방법이 있는데 간단하게 parent 엔티티에서 child @OneToMany 필드에 @JsonIgnore를 추가하여 해결 가능.
    // @JsonIgnore는 json으로 변환되는 대상 객체에 사용해야 함. -> return 객체가 domain 인지 dto 인지 확인하고 사용.
    // 사전에 이러한 문제를 방지하고자 양방향 연관 관계를 지양하고 단방향 연관 관계를 사용.
    // 단방향 매핑만으로 테이블과 연관 관계 매핑 설정이 가능.

    // TODO: 연관관계의 주인
    // 1:N 관계에서 외래키는 항상 N 쪽에 있음.
    // 연관관계의 주인은 N 쪽의 테이블.
    // 키의 주인이 아닌 관계의 주인인 것에 유의.

    // TODO: 일대다 단방향 매핑의 단점
    // 다른 테이블에 있는 외래키를 관리해줘야 함.
    // 이러한 경우 연관관계 처리를 위한 update sql을 추가로 실행해야 함.
    // 이런 문제를 해결하기 위해 외래키가 본인 테이블에 있도록 다대일 양방향 매핑을 사용하는 것을 권장.
    // 또한 양방향 매핑에서 @OneToMany는 연관관계의 주인이 될 수 없으므로 일대다 단방향 매핑은 권장되지 않음.
    // 일대다 단방향 매핑보다는 다대일 양방향 매핑이 권장 됨.

    // TODO: cascadeType을 이용한 영속성 전이
    // 부모 엔티티 삭제시 연관관계가 매핑된 자식 엔티티가 고아 객체가 되지 않도록 하기 위해 사용.
    // cascade = CascadeType.REMOVE 또는 cascade = {CascadeType.ALL}, orphanRemoval = true 옵션을 적용.
    // 이와 같이 영속성 전이를 통해 생명주기를 관리할 수 있음.
//    @JsonIgnore
//    @OneToMany(
//        mappedBy = "board",
//        cascade = {CascadeType.ALL},
//        orphanRemoval = true
////        cascade = CascadeType.REMOVE
//    )
//    private List<BoardComment> boardComments;

    public void updateProduct(
        String name,
        String details
    ) {
        this.name = name;
        this.details = details;
    }

    @Builder(toBuilder = true)
    public Product(
        String productId,
        String name,
        String details
    ) {
        this.productId = productId;
        this.name = name;
        this.details = details;
    }

}
