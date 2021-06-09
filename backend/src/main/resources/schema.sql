DROP SCHEMA IF EXISTS `issue_tracker`;
-- -----------------------------------------------------
-- Schema issue_tracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `issue_tracker` DEFAULT CHARACTER SET utf8;
USE `issue_tracker`;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `milestone`;
DROP TABLE IF EXISTS `issue`;
DROP TABLE IF EXISTS `label`;
DROP TABLE IF EXISTS `issue_label`;
DROP TABLE IF EXISTS `assigned`;
DROP TABLE IF EXISTS `comment`;

create table `user`
(
    id               int primary key auto_increment,
    oauth_id         varchar(100),
    authenticated_by varchar(20),
    nickname         varchar(30),
    name             varchar(30),
    profile_image    varchar(300),
    access_token     varchar(300)
);

create table `milestone`
(
    id                      int primary key auto_increment,
    title                   varchar(50),
    description             varchar(300),
    last_modified_date_time timestamp,
    due_to_date             DATE,
    is_open                 BOOLEAN
);

create table `issue`
(
    id                      int primary key auto_increment,
    title                   varchar(50),
    author_id               int,
    is_open                 BOOLEAN,
    milestone_id            int,
    last_modified_date_time TIMESTAMP,
    CONSTRAINT fk_issue_user FOREIGN KEY (author_id) REFERENCES user (id),
    CONSTRAINT fk_issue_milestone FOREIGN KEY (milestone_id) REFERENCES milestone (id)
);

create table `label`
(
    id          int primary key auto_increment,
    title       varchar(50),
    description varchar(300),
    color       varchar(20)
);

create table `issue_label`
(
    id       int primary key auto_increment,
    issue_id int,
    label_id int,
    CONSTRAINT fk_issue_label_issue FOREIGN KEY (issue_id) REFERENCES issue (id),
    CONSTRAINT fk_issue_label_label FOREIGN KEY (label_id) REFERENCES label (id)
);

create table `assigned`
(
    id       int primary key auto_increment,
    issue_id int,
    user_id  int,
    CONSTRAINT fk_assigned_issue FOREIGN KEY (issue_id) REFERENCES issue (id),
    CONSTRAINT fk_assigned_user FOREIGN KEY (user_id) REFERENCES user (id)
);

create table `comment`
(
    id        int primary key auto_increment,
    content   varchar(1000),
    issue_id  int,
    author_id int,
    CONSTRAINT fk_comment_issue FOREIGN KEY (issue_id) REFERENCES issue (id),
    CONSTRAINT fk_comment_user FOREIGN KEY (author_id) REFERENCES user (id)
);
