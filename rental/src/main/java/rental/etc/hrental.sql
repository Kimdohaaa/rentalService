drop database if exists hrental;         
create database hrental;         
use hrental;         
         
# [1] 회원테이블         
create table member(         
   mno int unsigned auto_increment,      
mid varchar(20) not null unique,         
mpwd varchar(20) not null,         
mphone varchar(13) not null unique,         
mgender int ,         
mstate int default 0,         
mdate datetime default now(),         
constraint primary key(mno)         
);         
-- member sample --         
INSERT INTO member (mid, mpwd, mphone, mgender) VALUES         
('user01', '1111', '010-1234-5678', 1),         
('user02', '2222', '010-2345-6789', 2),         
('user03', '3333', '010-3456-7890', 1),         
('user04', '4444', '010-4567-8901', 2),         
('user05', '5555', '010-5678-9012', 1),         
('user06', '6666', '010-6789-0123', 2),         
('user07', '7777', '010-7890-1234', 1),         
('user08', '8888', '010-8901-2345', 2),         
('user09', '9999', '010-9012-3456', 1),         
('user10', '0000', '010-0123-4567', 2);         
         
select * from member;         
         
         
# [2] 가맹테이블         
create table store(         
   sno int unsigned auto_increment,      
smno varchar(12) not null unique,         
saddr varchar(100) not null,         
sname varchar(20) not null unique ,         
sstate int unsigned default 1,         
simg varchar(255) not null default 'default.jpg',         
constraint primary key (sno)         
);         
         
-- store sample --         
INSERT INTO store (smno, saddr, sname) VALUES         
('101-81-12345', '서울특별시 강남구 테헤란로 10', '강남점'),         
('102-82-23456', '서울특별시 종로구 세종대로 15', '종로점'),         
('103-83-34567', '서울특별시 서초구 서초대로 25', '서초점'),         
('104-84-45678', '서울특별시 마포구 홍익로 30', '홍대점'),         
('105-85-56789', '부산광역시 해운대구 해운대로 50', '해운대점'),         
('106-86-67890', '부산광역시 남구 용소로 35', '부산남구점'),         
('107-87-78901', '대구광역시 중구 동성로 40', '대구점'),         
('108-88-89012', '대전광역시 서구 둔산로 20', '대전점'),         
('109-89-90123', '광주광역시 동구 충장로 55', '광주점'),         
('110-90-01234', '인천광역시 남동구 구월로 60', '인천점');         
         
select * from store;         
         
# [3] 관리자 테이블         
create table admin(         
ano int unsigned auto_increment,         
aid varchar(20) not null unique,         
apwd varchar(20) not null,         
constraint primary key(ano)         
);         
         
# [4] 대여 테이블         
create table rental(         
rno int unsigned auto_increment,         
rdate date not null,         
rtime char(2) not null,         
rstate int,         
rcount int unsigned not null,         
rprice int,         
rreason varchar(10),
rreason_detail varchar(255),
mno int unsigned,         
sno int unsigned,         
constraint primary key(rno),         
constraint foreign key(mno) references member(mno) on update cascade on delete cascade,         
constraint foreign key(sno) references store(sno) on update cascade on delete cascade         
);         
         
-- admin sample --         
INSERT INTO admin (aid, apwd) VALUES         
('admin001', 'a1'),         
('admin002', 'b2'),         
('admin003', 'c3'),         
('admin004', 'd4'),         
('admin005', 'e5'),         
('admin006', 'f6'),         
('admin007', 'g7'),         
('admin008', 'h8'),         
('admin009', 'i9'),         
('admin010', 'j0');         
         
-- rental sample --
-- 2020년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2020-01-15', '00', 1, 2, 18000, 1, 1),
('2020-02-18', '01', 0, 3, 29000, 2, 2),
('2020-03-12', '02', 1, 1, 12000, 3, 3),
('2020-04-05', '03', 0, 2, 21000, 4, 4),
('2020-05-19', '04', 1, 4, 35000, 5, 5),
('2020-06-21', '05', 1, 3, 27000, 6, 6),
('2020-07-13', '06', 0, 2, 19000, 7, 7),
('2020-08-16', '07', 1, 1, 10000, 8, 8),
('2020-09-22', '08', 0, 4, 38000, 9, 9),
('2020-10-27', '09', 1, 3, 31000, 10, 10);

-- 2021년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2021-01-25', '00', 1, 3, 28000, 1, 1),
('2021-02-10', '01', 0, 2, 21000, 2, 2),
('2021-03-15', '02', 1, 4, 39000, 3, 3),
('2021-04-20', '03', 0, 1, 11000, 4, 4),
('2021-05-10', '04', 1, 2, 20000, 5, 5),
('2021-06-07', '05', 1, 3, 32000, 6, 6),
('2021-07-30', '06', 0, 4, 40000, 7, 7),
('2021-08-13', '07', 1, 1, 13000, 8, 8),
('2021-09-19', '08', 0, 2, 21000, 9, 9),
('2021-10-09', '09', 1, 3, 31000, 10, 10);

-- 2022년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2022-01-13', '00', 1, 2, 22000, 1, 1),
('2022-02-21', '01', 0, 1, 13000, 2, 2),
('2022-03-25', '02', 1, 3, 29000, 3, 3),
('2022-04-10', '03', 0, 4, 35000, 4, 4),
('2022-05-15', '04', 1, 3, 25000, 5, 5),
('2022-06-09', '05', 1, 2, 19000, 6, 6),
('2022-07-07', '06', 0, 1, 12000, 7, 7),
('2022-08-20', '07', 1, 4, 37000, 8, 8),
('2022-09-12', '08', 0, 3, 26000, 9, 9),
('2022-10-14', '09', 1, 2, 18000, 10, 10);

-- 2023년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2023-01-04', '00', 1, 3, 31000, 1, 1),
('2023-02-18', '01', 0, 2, 19000, 2, 2),
('2023-03-22', '02', 1, 1, 15000, 3, 3),
('2023-04-11', '03', 0, 4, 41000, 4, 4),
('2023-05-08', '04', 1, 2, 20000, 5, 5),
('2023-06-17', '05', 1, 3, 27000, 6, 6),
('2023-07-24', '06', 0, 2, 23000, 7, 7),
('2023-08-30', '07', 1, 4, 39000, 8, 8),
('2023-09-10', '08', 0, 1, 12000, 9, 9),
('2023-10-18', '09', 1, 3, 29000, 10, 10);

-- 2024년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2024-01-15', '00', 1, 1, 14000, 1, 1),
('2024-02-10', '01', 0, 3, 33000, 2, 2),
('2024-03-20', '02', 1, 4, 42000, 3, 3),
('2024-04-15', '03', 0, 2, 22000, 4, 4),
('2024-05-05', '04', 1, 3, 30000, 5, 5),
('2024-06-12', '05', 1, 1, 10000, 6, 6),
('2024-07-25', '06', 0, 2, 19000, 7, 7),
('2024-08-13', '07', 1, 4, 40000, 8, 8),
('2024-09-21', '08', 0, 3, 27000, 9, 9),
('2024-10-11', '09', 1, 2, 20000, 10, 10);


SELECT SUM(rprice) AS total_sales FROM rental;

-- 2020년 샘플 데이터
UPDATE rental SET rreason = '0' WHERE rno = 2; -- 헬스장이 좁다
UPDATE rental SET rreason = '1' WHERE rno = 4; -- 헬스장이 더럽다
UPDATE rental SET rreason = '2' WHERE rno = 9; -- 기구가 별로 안좋다
UPDATE rental SET rreason = '기타', rreason_detail = '헬스장 위치가 불편하다' WHERE rno = 12; -- 기타
UPDATE rental SET rreason = '0' WHERE rno = 14; -- 헬스장이 좁다

-- 2021년 샘플 데이터
UPDATE rental SET rreason = '1' WHERE rno = 2; -- 헬스장이 더럽다
UPDATE rental SET rreason = '기타', rreason_detail = '시설이 너무 오래되었다' WHERE rno = 6; -- 기타
UPDATE rental SET rreason = '2' WHERE rno = 11; -- 기구가 별로 안좋다
UPDATE rental SET rreason = '0' WHERE rno = 13; -- 헬스장이 좁다
UPDATE rental SET rreason = '기타', rreason_detail = '운동 기구가 부족하다' WHERE rno = 16; -- 기타

SELECT rno, rreason, rreason_detail FROM rental WHERE rreason = '기타';
select rno, rreason from rental where rreason is not null;


SELECT s.sname AS store_name, YEAR(r.rdate) AS year, SUM(r.rprice) AS total_revenue FROM rental r JOIN store s ON r.sno = s.sno GROUP BY s.sname, YEAR(r.rdate) ORDER BY s.sname ASC, year ASC;      

