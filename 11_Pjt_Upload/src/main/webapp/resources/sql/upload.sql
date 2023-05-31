-- 목록(PAGINATION, SCROLL)
-- 계층

-- 첨부
-- 댓글
-- 사용자

-- 1게시글 - 1첨부 - 1테이블
-- 1게시글 - 다중첨부 - 2테이블

-- 다중 첨부 게시판

-- 스키마 선택	
USE gdj61;
-- 첨부 파일 정보 테이블
DROP TABLE IF EXISTS ATTACH;
CREATE TABLE ATTACH(
    ATTACH_NO       INT             NOT NULL, -- PK
    PATH            VARCHAR(3000)  NOT NULL, -- 첨부 파일 경로
    ORIGIN_NAME     VARCHAR(3000)  NOT NULL, -- 첨부 파일의 원래 이름
    FILESYSTEM_NAME VARCHAR(50)   NOT NULL, -- 첨부 파일의 저장 이름 
    DOWNLOAD_COUNT  INT,                       -- 다운로드 횟수
    HAS_THUMBNAIL   INT,                       -- 썸네일이 있으면 1, 없으면 0  
    UPLOAD_NO       INT                        -- 게시글 FK
);

-- 게시글 정보 테이블   
DROP TABLE IF EXISTS UPLOAD;
CREATE TABLE UPLOAD(
    UPLOAD_NO       INT              NOT NULL, -- PK
    UPLOAD_TITLE    VARCHAR(1000) NOT NULL, -- 제목
    UPLOAD_CONTENT  BLOB,                         -- 내용
    CREATED_AT      TIMESTAMP,                    -- 작성일
    MODIFIED_AT     TIMESTAMP                     -- 수정일
       
);

-- 기본키
ALTER TABLE ATTACH
    ADD CONSTRAINT PK_ATTACH
        PRIMARY KEY(ATTACH_NO);

ALTER TABLE UPLOAD
    ADD CONSTRAINT PK_UPLOAD
        PRIMARY KEY(UPLOAD_NO);
        
-- 외래키
ALTER TABLE ATTACH
    ADD CONSTRAINT FK_ATTACH_UPLOAD
        FOREIGN KEY(UPLOAD_NO) REFERENCES UPLOAD(UPLOAD_NO)
            ON DELETE CASCADE;

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        