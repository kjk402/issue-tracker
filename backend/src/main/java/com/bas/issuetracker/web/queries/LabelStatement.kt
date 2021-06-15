package com.bas.issuetracker.web.queries

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
