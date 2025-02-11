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
('2020-01-15', '00', 1, 2, 20000, 1, 1),
('2020-02-18', '01', 0, 1, 10000, 2, 2),
('2020-03-12', '02', 1, 3, 30000, 3, 3),
('2020-04-05', '03', 0, 2, 20000, 4, 4),
('2020-05-19', '04', 1, 1, 10000, 5, 5),
('2020-06-21', '05', 1, 2, 20000, 6, 6),
('2020-07-13', '06', 0, 3, 30000, 7, 7),
('2020-08-16', '07', 1, 4, 40000, 8, 8),
('2020-09-22', '08', 0, 1, 10000, 9, 9),
('2020-10-27', '09', 1, 2, 20000, 10, 10),
('2020-11-11', '10', 1, 3, 30000, 1, 2),
('2020-12-03', '11', 0, 2, 20000, 2, 3),
('2020-12-25', '12', 1, 1, 10000, 3, 4),
('2020-11-19', '13', 0, 4, 40000, 4, 5),
('2020-10-05', '14', 1, 3, 30000, 5, 6),
('2020-09-17', '15', 0, 2, 20000, 6, 7),
('2020-08-21', '16', 1, 1, 10000, 7, 8),
('2020-07-09', '17', 0, 2, 20000, 8, 9),
('2020-06-30', '18', 1, 4, 40000, 9, 10);

-- 2021년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2021-01-25', '00', 1, 2, 20000, 1, 1),
('2021-02-10', '01', 0, 1, 10000, 2, 2),
('2021-03-15', '02', 1, 3, 30000, 3, 3),
('2021-04-20', '03', 0, 2, 20000, 4, 4),
('2021-05-10', '04', 1, 1, 10000, 5, 5),
('2021-06-07', '05', 1, 2, 20000, 6, 6),
('2021-07-30', '06', 0, 3, 30000, 7, 7),
('2021-08-13', '07', 1, 4, 40000, 8, 8),
('2021-09-19', '08', 0, 1, 10000, 9, 9),
('2021-10-09', '09', 1, 2, 20000, 10, 10),
('2021-11-11', '10', 1, 3, 30000, 1, 2),
('2021-12-15', '11', 0, 2, 20000, 2, 3),
('2021-10-03', '12', 1, 1, 10000, 3, 4),
('2021-09-28', '13', 0, 4, 40000, 4, 5),
('2021-08-10', '14', 1, 3, 30000, 5, 6),
('2021-07-22', '15', 0, 2, 20000, 6, 7),
('2021-06-14', '16', 1, 1, 10000, 7, 8),
('2021-05-19', '17', 0, 2, 20000, 8, 9),
('2021-04-10', '18', 1, 4, 40000, 9, 10);

-- 2022년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2022-01-13', '00', 1, 2, 20000, 1, 1),
('2022-02-21', '01', 0, 1, 10000, 2, 2),
('2022-03-25', '02', 1, 3, 30000, 3, 3),
('2022-04-10', '03', 0, 2, 20000, 4, 4),
('2022-05-15', '04', 1, 1, 10000, 5, 5),
('2022-06-09', '05', 1, 2, 20000, 6, 6),
('2022-07-07', '06', 0, 3, 30000, 7, 7),
('2022-08-20', '07', 1, 4, 40000, 8, 8),
('2022-09-12', '08', 0, 1, 10000, 9, 9),
('2022-10-14', '09', 1, 2, 20000, 10, 10),
('2022-11-03', '10', 1, 3, 30000, 1, 2),
('2022-12-01', '11', 0, 2, 20000, 2, 3),
('2022-11-22', '12', 1, 1, 10000, 3, 4),
('2022-10-25', '13', 0, 4, 40000, 4, 5),
('2022-09-03', '14', 1, 3, 30000, 5, 6),
('2022-08-10', '15', 0, 2, 20000, 6, 7),
('2022-07-19', '16', 1, 1, 10000, 7, 8),
('2022-06-25', '17', 0, 2, 20000, 8, 9),
('2022-05-30', '18', 1, 4, 40000, 9, 10);

-- 2023년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2023-01-04', '00', 1, 2, 20000, 1, 1),
('2023-02-18', '01', 0, 1, 10000, 2, 2),
('2023-03-22', '02', 1, 3, 30000, 3, 3),
('2023-04-11', '03', 0, 2, 20000, 4, 4),
('2023-05-08', '04', 1, 1, 10000, 5, 5),
('2023-06-17', '05', 1, 2, 20000, 6, 6),
('2023-07-24', '06', 0, 3, 30000, 7, 7),
('2023-08-30', '07', 1, 4, 40000, 8, 8),
('2023-09-10', '08', 0, 1, 10000, 9, 9),
('2023-10-18', '09', 1, 2, 20000, 10, 10),
('2023-11-07', '10', 1, 3, 30000, 1, 2),
('2023-12-05', '11', 0, 2, 20000, 2, 3),
('2023-11-21', '12', 1, 1, 10000, 3, 4),
('2023-10-02', '13', 0, 4, 40000, 4, 5),
('2023-09-28', '14', 1, 3, 30000, 5, 6),
('2023-08-22', '15', 0, 2, 20000, 6, 7),
('2023-07-13', '16', 1, 1, 10000, 7, 8),
('2023-06-18', '17', 0, 2, 20000, 8, 9),
('2023-05-28', '18', 1, 4, 40000, 9, 10);

-- 2024년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2024-01-15', '00', 1, 2, 20000, 1, 1),
('2024-02-10', '01', 0, 1, 10000, 2, 2),
('2024-03-20', '02', 1, 3, 30000, 3, 3),
('2024-04-15', '03', 0, 2, 20000, 4, 4),
('2024-05-05', '04', 1, 1, 10000, 5, 5),
('2024-06-12', '05', 1, 2, 20000, 6, 6),
('2024-07-25', '06', 0, 3, 30000, 7, 7),
('2024-08-13', '07', 1, 4, 40000, 8, 8),
('2024-09-21', '08', 0, 1, 10000, 9, 9),
('2024-10-11', '09', 1, 2, 20000, 10, 10),
('2024-11-30', '10', 1, 3, 30000, 1, 2),
('2024-12-10', '11', 0, 2, 20000, 2, 3),
('2024-11-15', '12', 1, 1, 10000, 3, 4),
('2024-10-25', '13', 0, 4, 40000, 4, 5),
('2024-09-14', '14', 1, 3, 30000, 5, 6),
('2024-08-21', '15', 0, 2, 20000, 6, 7),
('2024-07-18', '16', 1, 1, 10000, 7, 8),
('2024-06-05', '17', 0, 2, 20000, 8, 9),
('2024-05-28', '18', 1, 4, 40000, 9, 10);

-- 2025년 샘플 데이터
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES
('2025-01-05', '00', 1, 2, 20000, 1, 1),
('2025-02-17', '01', 0, 1, 10000, 2, 2),
('2025-01-22', '02', 1, 3, 30000, 3, 3),
('2025-02-18', '03', 0, 2, 20000, 4, 4),
('2025-01-13', '04', 1, 1, 10000, 5, 5);
	

SELECT SUM(rprice) AS total_sales FROM rental;

			