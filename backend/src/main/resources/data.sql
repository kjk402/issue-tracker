insert into milestone (id, title, description, last_modified_date_time, due_to_date, is_open)
values (1, '마일스톤 1', '마일스톤1 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (2, '마일스톤 2', '마일스톤2 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (3, '마일스톤 3', '마일스톤3 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (4, '마일스톤 4', '마일스톤4 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true);

INSERT INTO label (title, description, color)
VALUES ('backend', 'backend description', '#c2e0c6'),
       ('front', 'front description', '#AE72E0');

insert into user (id, oauth_id, authenticated_by, nickname, name, profile_image, access_token)
values (1, '1111', 'GITHUB', 'tester', 'tester', 'no-image', ''),
       (2, '456', 'GITHUB', 'tester2', 'test2', 'test2.img', ''),
       (3, '789', 'GITHUB', 'tester3', 'test3', 'test3.img', ''),
       (4, '000', 'GITHUB', 'tester4', 'test4', 'test4.img', '')
;

insert into issue (title, author_id, is_open, milestone_id, last_modified_date_time)
values ('issue1', 1, true, 1, timestamp('2021-06-12 14:03:21')),
       ('issue2', 1, true, 1, timestamp('2021-06-12 14:03:21')),
       ('issue3', 2, true, 2, timestamp('2021-06-12 14:03:21')),
       ('issue4', 2, false, 2, timestamp('2021-06-12 14:03:21')),
       ('issue5', 3, true, 1, timestamp('2021-06-12 14:03:21')),
       ('issue6', 3, true, 1, timestamp('2021-06-12 14:03:21')),
       ('issue7', 3, true, 1, timestamp('2021-06-12 14:03:21')),
       ('issue8', 3, false, 4, timestamp('2021-06-12 14:03:21')),
       ('issue9', 3, false, 4, timestamp('2021-06-12 14:03:21')),
       ('issue10', 3, false, 4, timestamp('2021-06-12 14:03:21'))
;

insert into assigned (issue_id, user_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (4, 1),
       (8, 1),
       (3, 2),
       (8, 3),
       (7, 2);

insert into comment (content, issue_id, author_id)
values ('comment 1', 1, 1),
       ('comment 2', 1, 1),
       ('comment 3', 2, 1),
       ('comment 4', 2, 1),
       ('comment 5', 3, 1),
       ('comment 6', 4, 1),
       ('comment 7', 1, 2),
       ('comment 8', 1, 2),
       ('comment 9', 1, 3),
       ('comment 10', 1, 3),
       ('comment 11', 1, 3),
       ('comment 12', 1, 3);

insert into issue_label (issue_id, label_id)
VALUES (1, 1),
       (1, 2);
