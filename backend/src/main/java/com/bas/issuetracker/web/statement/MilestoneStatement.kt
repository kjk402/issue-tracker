package com.bas.issuetracker.web.statement

const val SAVE_MILESTONE: String = """
insert into milestone (title, description, last_modified_date_time, due_to_date)
values (:title, :description, :last_modified_date_time, :due_to_date);
"""

const val FIND_MILESTONE: String = """
select id, title, description, last_modified_date_time, due_to_date
from milestone
where id = :id;
"""
