package com.bas.issuetracker.web.statement

const val CREATE_MILESTONE: String = """
insert into milestone (title, description, last_modified_date_time, due_to_date, is_open)
values (:title, :description, :last_modified_date_time, :due_to_date, :is_open);
"""

const val FIND_MILESTONE: String = """
select id, title, description, last_modified_date_time, due_to_date, is_open
from milestone
where id = :id;
"""

const val UPDATE_MILESTONE: String = """
update milestone
set title                   = :title,
    description             = :description,
    due_to_date             = :due_to_date,
    last_modified_date_time = now(),
    is_open                 = :is_open
where id = :id;
"""
