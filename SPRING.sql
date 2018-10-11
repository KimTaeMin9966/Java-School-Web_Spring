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
	updatedate TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY(userid)
);

DROP TABLE test_member;

CREATE TABLE mydata.test_board(
	bno INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    writer VARCHAR(50) NOT NULL,
	regdate TIMESTAMP NOT NULL DEFAULT NOW(),
    viewcnt INT DEFAULT 0
);

DROP TABLE test_board;

INSERT INTO mydata.test_board(title, content, writer)
SELECT title, content, writer FROM mydata.test_board;

CREATE TABLE tbl_user (
	uid VARCHAR(50) NOT NULL,
	upw VARCHAR(50) NOT NULL,
	uname VARCHAR(100) NOT NULL,
	upoint INT NOT NULL DEFAULT 0,
    PRIMARY KEY(uid)
);

CREATE TABLE tbl_message (
	mno INT NOT NULL AUTO_INCREMENT,
	targetid VARCHAR(50) NOT NULL,
	sender VARCHAR(50) NOT NULL,
	message TEXT NOT NULL,
	opendate TIMESTAMP,
	senddate TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY(mno)
);

ALTER TABLE tbl_message ADD CONSTRAINT fk_usertarget
FOREIGN KEY(targetid) REFERENCES tbl_user(uid);

ALTER TABLE tbl_message ADD CONSTRAINT fk_usersender
FOREIGN KEY(sender) REFERENCES tbl_user(uid);

INSERT INTO tbl_user(uid, upw, uname) VALUES
('id001', 'id001', 'DR.STRANGE'),
('id002', 'id002', 'IRON MAN'),
('id003', 'id003', 'THOR'),
('id004', 'id004', 'ANT MAN');

SELECT * FROM tbl_user;
SELECT * FROM tbl_message;
