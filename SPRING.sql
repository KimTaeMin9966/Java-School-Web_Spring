SELECT * FROM tbl_member;

show tables;

SELECT * FROM tbl_board;

INSERT INTO tbl_board(title, content, writer, regdate)
VALUES('이것은 제목입니다.', '내용이 없습니다.', '김태민', NOW());

CREATE TABLE `re_tbl_board` (
  `bno` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` text,
  `writer` varchar(50) DEFAULT NULL,
  `origin` int(11) DEFAULT '0',
  `depth` int(11) DEFAULT '0',
  `seq` int(11) DEFAULT '0',
  `regdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `viewcnt` int(11) DEFAULT '0',
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE re_tbl_board;

SELECT * FROM re_tbl_board;

CREATE TABLE `mydata`.`tbl_comment` (
  `cno` INT NOT NULL AUTO_INCREMENT,
  `bno` INT NOT NULL,
  `commentText` VARCHAR(100) NOT NULL,
  `commentAuth` VARCHAR(45) NOT NULL,
  `regdate` TIMESTAMP NOT NULL DEFAULT NOW(),
  `updatedate` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`cno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SELECT * FROM tbl_comment;

ALTER TABLE tbl_comment ADD CONSTRAINT fk_board
FOREIGN KEY(bno) REFERENCES re_tbl_board(bno);

CREATE TABLE mydata.test_member(
	userid VARCHAR(50) NOT NULL,
	userpw VARCHAR(45) NOT NULL,
	username VARCHAR(45) NOT NULL,
	email VARCHAR(45) NULL,
	regdate TIMESTAMP NOT NULL DEFAULT NOW(),
	updatedate TIMESTAMP NOT NULL DEFAULT NOW()
);