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
maddr varchar(100),
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
rstate int not null default 0,			
rcount int unsigned not null,			
rprice int,
rreason varchar(100),			
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
INSERT INTO rental (rdate, rtime, rstate, rcount, rprice, mno, sno) VALUES			
('2024-01-01', '00', 1, 2, 20000, 1, 1),			
('2024-01-02', '01', 0, 1, 10000, 2, 2),			
('2024-01-03', '02', 1, 3, 30000, 3, 3),			
('2024-01-04', '03', 0, 2, 20000, 4, 4),			
('2024-01-05', '04', 1, 1, 10000, 5, 5),			
('2024-01-06', '05', 1, 2, 20000, 6, 6),			
('2024-01-07', '06', 0, 3, 30000, 7, 7),			
('2024-01-08', '07', 1, 4, 40000, 8, 8),			
('2024-01-09', '08', 0, 1, 10000, 9, 9),			
('2024-01-10', '09', 1, 2, 20000, 10, 10),			
('2024-01-11', '10', 1, 3, 30000, 1, 2),			
('2024-01-12', '11', 0, 2, 20000, 2, 3),			
('2024-01-13', '12', 1, 1, 10000, 3, 4),			
('2024-01-14', '13', 0, 4, 40000, 4, 5),			
('2024-01-15', '14', 1, 3, 30000, 5, 6),			
('2024-01-16', '15', 0, 2, 20000, 6, 7),			
('2024-01-17', '16', 1, 1, 10000, 7, 8),			
('2024-01-18', '17', 0, 2, 20000, 8, 9),			
('2024-01-19', '18', 1, 4, 40000, 9, 10),			
('2024-01-20', '19', 0, 3, 30000, 10, 1),			
('2024-01-21', '20', 1, 2, 20000, 1, 3),			
('2024-01-22', '21', 0, 1, 10000, 2, 4),			
('2024-01-23', '22', 1, 3, 30000, 3, 5),			
('2024-01-24', '23', 0, 2, 20000, 4, 6),			
('2024-01-25', '24', 1, 1, 10000, 5, 7),			
('2024-01-26', '00', 0, 4, 40000, 6, 8),			
('2024-01-27', '01', 1, 3, 30000, 7, 9),			
('2024-01-28', '02', 0, 2, 20000, 8, 10),			
('2024-01-29', '03', 1, 1, 10000, 9, 1),			
('2024-01-30', '04', 0, 2, 20000, 10, 2),			
('2024-01-31', '05', 1, 3, 30000, 1, 4),			
('2024-02-01', '06', 0, 1, 10000, 2, 5),			
('2024-02-02', '07', 1, 4, 40000, 3, 6),			
('2024-02-03', '08', 0, 2, 20000, 4, 7),			
('2024-02-04', '09', 1, 3, 30000, 5, 8),			
('2024-02-05', '10', 0, 1, 10000, 6, 9),			
('2024-02-06', '11', 1, 2, 20000, 7, 10),			
('2024-02-07', '12', 0, 3, 30000, 8, 1),			
('2024-02-08', '13', 1, 1, 10000, 9, 2),			
('2024-02-09', '14', 0, 2, 20000, 10, 3),			
('2024-02-10', '15', 1, 3, 30000, 1, 5),			
('2024-02-11', '16', 0, 2, 20000, 2, 6),			
('2024-02-12', '17', 1, 1, 10000, 3, 7),			
('2024-02-13', '18', 0, 4, 40000, 4, 8),			
('2024-02-14', '19', 1, 3, 30000, 5, 9),			
('2024-02-15', '20', 0, 2, 20000, 6, 10),			
('2024-02-16', '21', 1, 1, 10000, 7, 1),			
('2024-02-17', '22', 0, 2, 20000, 8, 2),			
('2024-02-18', '23', 1, 4, 40000, 9, 3),			
('2024-02-19', '24', 0, 3, 30000, 10, 4);			
			