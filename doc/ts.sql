# 建立数据库ts
DROP DATABASE IF EXISTS ts;
CREATE DATABASE ts CHARACTER SET utf8mb4;
USE ts;

# 新建book_type表
CREATE TABLE `book_type`  (
  `type_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE,
  INDEX `type_id`(`type_id` ASC) USING BTREE
) ;
# 新建book表
CREATE TABLE `book`  (
  `book_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `book_publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `book_date` date NULL DEFAULT NULL,
  `book_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`book_id`, `book_type`) USING BTREE,
  INDEX `type`(`book_type` ASC) USING BTREE,
  INDEX `book_id`(`book_id` ASC) USING BTREE,
  CONSTRAINT `type` FOREIGN KEY (`book_type`) REFERENCES `book_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ; 
# 新建user表
CREATE TABLE `user`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_permission` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ;
# 新建book_log表
CREATE TABLE `book_log`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `log_date` datetime NULL DEFAULT NULL,
  `log_status` int NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`, `user_id`, `book_id`) USING BTREE,
  INDEX `user`(`user_id` ASC) USING BTREE,
  INDEX `book`(`book_id` ASC) USING BTREE,
  CONSTRAINT `book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ;
SET FOREIGN_KEY_CHECKS = 1;


# 插入数据
INSERT INTO book_type(type_id,type_name,type_position) VALUES
('T01','文学类','101'),
('T02','自然科学类','207'),
('T03','农业类','305'),
('T04','工程技术类','112'),
('T05','医药类','201'),
('T06','社会科学类','415'),
('T07','语言文学类','303'),
('T08','历史类','208'),
('T09','艺术类','306'),
('T10','教材类','102'),
('T11','参考书类','404'),
('T12','宗教类','309'),
('T13','传记类','213'),
('T14','幼儿图书类','115');

INSERT INTO book(book_id,book_name,book_author,book_publisher,book_date,book_type,book_count) VALUES
('9780140283334','飘','玛格丽特·米切尔','Scribner','2021/3/14','T01',4),
('978019280613','功夫','詹姆斯·威尔逊','Oxford University Press','2021/6/25','T13',5),
('9780316769488','围城','钱钟书','外国文学出版社','2021/9/8','T01',6),
('9780345339737','指环王','J.R.R.托尔金','Ballantine Books','2021/12/1','T01',7),
('9780393970124','红楼梦','曹雪芹','W. W. Norton & Company','2022/2/28','T01',10),
('9780743273565','老人与海','埃内斯特·海明威','Scribner','2022/5/16','T01',4),
('9780747532743','哈利·波特与魔法石','J.K.罗琳','Bloomsbury Publishing','2022/8/9','T01',5),
('9781400079279','追风筝的人','卡勒德·胡赛尼','Riverhead Books','2022/10/31','T01',6),
('9781409118036','百年孤独','加西亚·马尔克斯','HarperCollins Publishers','2023/1/4','T01',4),
('9781451673319','1984','乔治·奥威尔','oughton Mifflin Harcourt','2023/4/22','T01',6),
('9787020046915','三体','刘慈欣','重庆出版社','2023/7/18','T01',5),
('9787020049312','平凡的世界','路遥','人民文学出版社','2023/10/12','T01',3),
('9787030319645','活着','余华','上海文艺出版社','2023/12/25','T01',4),
('9787530212138','平凡的世界','路遥','人民文学出版社','2022/3/7','T01',6),
('9787532130864','红楼梦','曹雪芹','人民文学出版社','2022/6/3','T01',4),
('9787532132639','哈利·波特与密室','J.K.罗琳','人民文学出版社','2022/8/28','T01',6),
('9787532747586','围城','钱钟书','人民文学出版社','2023/2/2','T01',2),
('9787544282553','沉默的大多数','王小波','人民文学出版社','2023/5/1','T01',6),
('9789573334312','白夜行','东野圭吾','八旗文化传媒','2021/10/10','T01',1),
('9787302639336','Mysql数据库原理与应用','王金恒','清华大学出版社','2023/12/12','T10',3);

INSERT INTO user(user_id,user_name,user_password,user_permission) VALUES
('admin','管理员','admin',1),
('user','新用户','123',0);