create table tbl_reply
(
	rno int primary key auto_increment,
	bno int not null,
	email varchar(100) not null,
	content text not null,
	regdate date
)

select count(*) from tbl_reply where bno=1035 order by rno desc;