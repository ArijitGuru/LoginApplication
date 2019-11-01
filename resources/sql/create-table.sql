CREATE TABLE `user_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `username` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(16) NOT NULL,
  `enabled` boolean NOT NULL DEFAULT true,
  PRIMARY KEY (`id`)
);

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES user_detail (username));