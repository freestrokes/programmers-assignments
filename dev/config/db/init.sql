-- DROP TABLE IF EXISTS user;
-- DROP TABLE IF EXISTS board;
-- DROP TABLE IF EXISTS board_comment;
--
-- CREATE TABLE user (
--     user_id varchar(100) NOT NULL COMMENT '사용자 id',
--     name varchar(100) DEFAULT NULL COMMENT '이름',
--     email varchar(100) DEFAULT NULL COMMENT '이메일',
--     picture text DEFAULT NULL COMMENT '사진',
--     password varchar(255) DEFAULT NULL COMMENT '비밀번호',
--     role varchar(50) DEFAULT NULL COMMENT '권한',
--     PRIMARY KEY (user_id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자';
--
-- CREATE TABLE board (
--     board_id varchar(100) NOT NULL COMMENT '게시글 id',
--     title varchar(500) DEFAULT NULL COMMENT '제목',
--     content text DEFAULT NULL COMMENT '내용',
--     author varchar(100) DEFAULT NULL COMMENT '작성자',
--     PRIMARY KEY (board_id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='게시글';
--
-- CREATE TABLE board_comment (
--     board_comment_id varchar(100) NOT NULL COMMENT '게시글 댓글 id',
--     board_id varchar(100) NOT NULL COMMENT '게시글 id',
--     content text DEFAULT NULL COMMENT '내용',
--     author varchar(100) DEFAULT NULL COMMENT '작성자',
--     PRIMARY KEY (board_comment_id),
--     CONSTRAINT board_comment_fk1 FOREIGN KEY (board_id) REFERENCES board (board_id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='게시글 댓글';
--
--
-- -- user
-- INSERT INTO
--     testdb.`user`(user_id, name, email, picture, password, `role`)
-- VALUES
--     ('12345678-0544-45ae-a2b9-7199126c21b5', 'freestrokes', 'freestrokes@freestrokes.com', null, '$2a$10$ka71fEV3sh2WDAzM8wO0Vu0t/ILGu3EHXo2u7EAOKzgB1fj9s8uKK', 'MANAGER');
--
-- -- board
-- INSERT INTO
--     testdb.board(board_id, title, content, author)
-- VALUES
--     ('c41feac0-3da0-4a42-8271-038ead97280d', 'title1', 'content1', 'author1'),
--     ('ce0eca6b-0544-45ae-a2b9-7199126c21b5', 'title2', 'content2', 'author2'),
--     ('8bfe97a1-c29f-4758-abe9-9c8302b74d23', 'title3', 'content3', 'author3');
--
-- -- board_comment
-- INSERT INTO
--     testdb.board_comment(board_comment_id, board_id, content, author)
-- VALUES
--     ('141feac0-3da0-4a42-8271-038ead97280d', 'c41feac0-3da0-4a42-8271-038ead97280d', 'board1 comment content1', 'author1'),
--     ('241feac0-3da0-4a42-8271-038ead97280d', 'c41feac0-3da0-4a42-8271-038ead97280d', 'board1 comment content2', 'author2'),
--     ('341feac0-3da0-4a42-8271-038ead97280d', 'c41feac0-3da0-4a42-8271-038ead97280d', 'board1 comment content3', 'author3'),
--     ('4e0eca6b-0544-45ae-a2b9-7199126c21b5', 'ce0eca6b-0544-45ae-a2b9-7199126c21b5', 'board2 comment content1', 'author1'),
--     ('5e0eca6b-0544-45ae-a2b9-7199126c21b5', 'ce0eca6b-0544-45ae-a2b9-7199126c21b5', 'board2 comment content2', 'author2'),
--     ('6e0eca6b-0544-45ae-a2b9-7199126c21b5', 'ce0eca6b-0544-45ae-a2b9-7199126c21b5', 'board2 comment content3', 'author3'),
--     ('7bfe97a1-c29f-4758-abe9-9c8302b74d12', '8bfe97a1-c29f-4758-abe9-9c8302b74d23', 'board3 comment content1', 'author1'),
--     ('8bfe97a1-c29f-4758-abe9-9c8302b74d12', '8bfe97a1-c29f-4758-abe9-9c8302b74d23', 'board3 comment content2', 'author2'),
--     ('9bfe97a1-c29f-4758-abe9-9c8302b74d12', '8bfe97a1-c29f-4758-abe9-9c8302b74d23', 'board3 comment content3', 'author3');

DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
    seq           bigint      NOT NULL AUTO_INCREMENT, --사용자 PK
    name          varchar(10) NOT NULL,                --사용자명
    email         varchar(50) NOT NULL,                --로그인 이메일
    passwd        varchar(80) NOT NULL,                --로그인 비밀번호
    login_count   int         NOT NULL DEFAULT 0,      --로그인 횟수. 로그인시 마다 1 증가
    last_login_at datetime             DEFAULT NULL,   --최종 로그인 일자
    create_at     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (seq),
    CONSTRAINT unq_user_email UNIQUE (email)
);

CREATE TABLE products
(
    seq          bigint      NOT NULL AUTO_INCREMENT, --상품 PK
    name         varchar(50) NOT NULL,                --상품명
    details      varchar(1000)        DEFAULT NULL,   --상품설명
    review_count int         NOT NULL DEFAULT 0,      --리뷰 갯수. 리뷰가 새로 작성되면 1 증가
    create_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (seq)
);

CREATE TABLE reviews
(
    seq         bigint        NOT NULL AUTO_INCREMENT, --리뷰 PK
    user_seq    bigint        NOT NULL,                --리뷰 작성자 PK (users 테이블 참조)
    product_seq bigint        NOT NULL,                --리뷰 상품 PK (products 테이블 참조)
    content     varchar(1000) NOT NULL,                --리뷰 내용
    create_at   datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (seq),
    CONSTRAINT fk_reviews_to_users FOREIGN KEY (user_seq) REFERENCES users (seq) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_reviews_to_products FOREIGN KEY (product_seq) REFERENCES products (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE orders
(
    seq          bigint   NOT NULL AUTO_INCREMENT, --주문 PK
    user_seq     bigint   NOT NULL,                --주문자 PK (users 테이블 참조)
    product_seq  bigint   NOT NULL,                --주문상품 PK (products 테이블 참조)
    review_seq   bigint            DEFAULT NULL,   --주문에 대한 리뷰 PK (reviews 테이블 참조)
    state        enum('REQUESTED','ACCEPTED','SHIPPING','COMPLETED','REJECTED') DEFAULT 'REQUESTED' NOT NULL, --주문상태
    request_msg  varchar(1000)     DEFAULT NULL,   --주문 요청 메시지
    reject_msg   varchar(1000)     DEFAULT NULL,   --주문 거절 메시지
    completed_at datetime          DEFAULT NULL,   --주문 완료 처리 일자
    rejected_at  datetime          DEFAULT NULL,   -- 주문 거절일자
    create_at    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (seq),
    CONSTRAINT unq_review_seq UNIQUE (review_seq),
    CONSTRAINT fk_orders_to_users FOREIGN KEY (user_seq) REFERENCES users (seq) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_orders_to_products FOREIGN KEY (product_seq) REFERENCES products (seq) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_orders_to_reviews FOREIGN KEY (review_seq) REFERENCES reviews (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);