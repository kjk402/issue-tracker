package com.bas.issuetracker.web.statement

const val CREATE_LABEL: String = """
insert into label (title, description, color)
VALUES (:title, :description, :color);
"""

const val FIND_LABEL: String = """
select id, title, description, color
from label
where id = :id;
"""

const val FIND_LABELS: String = """
select id, title, description, color
from label;
"""

const val UPDATE_LABEL: String = """
update label
set title       = :title,
    description = :description,
    color       = :color
where id = :id;
"""

const val DELETE_LABEL: String = """
delete from label where id = :id;
"""

const val FIND_LABELS_BY_ISSUE_ID: String= """
SELECT l.id, l.title, l.description, l.color
FROM label l
inner JOIN issue_label ON issue_label.label_id = l.id
inner JOIN issue i ON i.id = issue_label.issue_id
WHERE i.id = :issue_id AND l.id = issue_label.label_id;
"""
