SELECT * FROM tbl_member;

show tables;

SELECT * FROM tbl_board;

INSERT INTO tbl_board(title, content, writer, regdate)
VALUES('이것은 제목입니다.', '내용이 없습니다.', '최기근', NOW());
