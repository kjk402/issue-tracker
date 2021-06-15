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

const val FIND_MILESTONES: String = """
select id, title, description, last_modified_date_time, due_to_date, is_open
from milestone;
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

const val DELETE_MILESTONE: String = """
delete from milestone where id = :id;
"""

const val FIND_MILESTONE_BY_ISSUE_ID: String = """
select m.id, m.title, m.is_open 
from milestone m
inner join issue i ON m.id = i.milestone_id
where i.id = :issue_id;
"""
