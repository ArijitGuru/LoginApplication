insert  into `user_detail`(`name`,`username`,`email`,`password`,`enabled`) values ('User1','username1','user1@gmail.com', '123', true);
insert  into `user_detail`(`name`,`username`,`email`,`password`,`enabled`) values ('User2','username2','user2@gmail.com', '1234', true);
insert  into `user_detail`(`name`,`username`,`email`,`password`,`enabled`) values ('User3','username3','user3@gmail.com', '12345', true);
insert  into `user_detail`(`name`,`username`,`email`,`password`,`enabled`) values ('User4','username4','user4@gmail.com', '123456', true);


INSERT INTO user_roles (username, role) VALUES ('username1', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('username1', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('username2', 'ROLE_USER');

