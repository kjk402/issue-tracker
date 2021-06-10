insert into milestone (id, title, description, last_modified_date_time, due_to_date, is_open)
values (1, '마일스톤 1', '마일스톤1 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (2, '마일스톤 2', '마일스톤2 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (3, '마일스톤 3', '마일스톤3 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true),
       (4, '마일스톤 4', '마일스톤4 설명', timestamp('2021-06-10 13:04:21'), timestamp('2021-06-29'), true);

INSERT INTO label (title, description, color)
VALUES ('backend', 'backend description', '#c2e0c6'),
       ('front', 'front description', '#AE72E0');
