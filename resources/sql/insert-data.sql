insert  into `user_detail`(`name`,`username`,`email`,`password`,`enabled`) values ('User1','Admin','user1@gmail.com', '123', true);
insert  into `user_detail`(`name`,`username`,`email`,`password`,`enabled`) values ('User2','username2','user2@gmail.com', '1234', true);




INSERT INTO `authorities` (`username`, `role`) VALUES ('Admin', 'ROLE_ADMIN');
INSERT INTO `authorities` (`username`, `role`) VALUES ('username2', 'ROLE_USER');

