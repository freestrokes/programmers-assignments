package com.freestrokes.auth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.freestrokes.constants.AuthConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", length = 100, unique = true, nullable = false)
    private String userId;

    @Column(name = "name", length = 10)
    private String name;

    @Column(name = "email", length = 50)
    private String email;

//    @Column(name = "picture", columnDefinition = "TEXT")
//    private String picture;

    @Column(name = "password", length = 80, nullable = false)
    private String password;

    @Column(name = "login_count", nullable = false)
    @ColumnDefault("0")
    private Integer loginCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private AuthConstants.Role role;

    @LastModifiedDate
    @Column(name = "last_login_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastLoginAt = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

    @CreatedDate
    @CreationTimestamp
    @Column(name = "create_at", updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createAt = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

    public User updateUser(
        String name,
        String email,
//        String picture,
        AuthConstants.Role role
    ) {
        this.name = name;
        this.email = email;
//        this.picture = picture;
        this.role = role;

        return this;
    }

    @Builder(toBuilder = true)
    public User(
        String userId,
        String name,
        String email,
//        String picture,
        String password,
        AuthConstants.Role role
    ) {
        this.userId = userId;
        this.name = name;
        this.email = email;
//        this.picture = picture;
        this.password = password;
        this.role = role;
    }

}
