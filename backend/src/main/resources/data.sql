insert into milestone (id, title, description, last_modified_date_time, due_to_date, is_open)
values (1, '마일스톤 1', '마일스톤1 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (2, '마일스톤 2', '마일스톤2 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (3, '마일스톤 3', '마일스톤3 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (4, '마일스톤 4', '마일스톤4 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true);

INSERT INTO label (title, description, color)
VALUES ('backend', 'backend description', '#c2e0c6'),
       ('front', 'front description', '#AE72E0');

insert into user (id, oauth_id, authenticated_by, nickname, name, profile_image, access_token)
values (1, '1111', 'GITHUB', 'tester', 'tester', 'https://avatars.githubusercontent.com/u/59398492?v=4', ''),
       (2, '456', 'GITHUB', 'tester2', 'test2', 'https://avatars.githubusercontent.com/u/59312492?v=3', ''),
       (3, '789', 'GITHUB', 'tester3', 'test3', 'https://avatars.githubusercontent.com/u/784237?v=3', ''),
       (4, '000', 'GITHUB', 'tester4', 'test4', 'https://avatars.githubusercontent.com/u/79631237?v=3', '')
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
       (3, 1),
       (7, 1);

insert into comment (content, issue_id, author_id, deletable, last_modified_date_time)
values ('comment 1', 1, 1, false, timestamp('2021-06-12 14:03:21')),
       ('comment 2', 1, 1, true, timestamp('2021-06-12 14:03:21')),
       ('comment 3', 2, 1, false, timestamp('2021-06-12 14:03:21')),
       ('comment 4', 2, 1, true, timestamp('2021-06-12 14:03:21')),
       ('comment 5', 3, 1, false, timestamp('2021-06-12 14:03:21')),
       ('comment 6', 4, 1, false, timestamp('2021-06-12 14:03:21')),
       ('comment 7', 1, 2, true, timestamp('2021-06-12 14:03:21')),
       ('comment 8', 1, 2, true, timestamp('2021-06-12 14:03:21')),
       ('comment 9', 1, 3, true, timestamp('2021-06-12 14:03:21')),
       ('comment 10', 1, 3, true, timestamp('2021-06-12 14:03:21')),
       ('comment 11', 1, 3, true, timestamp('2021-06-12 14:03:21')),
       ('comment 12', 1, 3, true, timestamp('2021-06-12 14:03:21')),
       ('comment 13', 5, 3, false, timestamp('2021-06-12 14:03:21')),
       ('comment 14', 6, 3, false, timestamp('2021-06-12 14:03:21')),
       ('comment 15', 7, 3, false, timestamp('2021-06-12 14:03:21')),
       ('comment 16', 8, 3, false, timestamp('2021-06-12 14:03:21')),
       ('comment 17', 9, 3, false, timestamp('2021-06-12 14:03:21')),
       ('comment 18', 10, 3, false, timestamp('2021-06-12 14:03:21'))
;

insert into issue_label (issue_id, label_id)
VALUES (1, 1),
       (1, 2);
