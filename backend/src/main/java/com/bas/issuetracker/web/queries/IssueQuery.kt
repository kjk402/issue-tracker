package com.bas.issuetracker.web.queries;

const val SELECT_MULTIPLE_ISSUE: String = """
SELECT DISTINCT i.id, i.title, i.is_open, (((SELECT COUNT(*) FROM comment WHERE issue_id = i.id) -1)) AS comment_count, i.last_modified_date_time, u.nickname, u.name, u.profile_image
FROM issue AS i
inner JOIN comment c ON c.issue_id = i.id
inner JOIN user u ON u.id = i.author_id
WHERE i.is_open = :open_or_close AND i.author_id = u.id AND i.id = c.issue_id;
"""

const val SELECT_ISSUE_DETAIL: String = """
SELECT DISTINCT i.id, i.title, i.is_open, (((SELECT COUNT(*) FROM comment WHERE issue_id = i.id) -1)) AS comment_count, i.last_modified_date_time, u.nickname, u.name, u.profile_image
FROM issue AS i
inner JOIN comment c ON c.issue_id = i.id
inner JOIN user u ON u.id = i.author_id
WHERE i.id = :issue_id AND i.author_id = u.id AND i.id = c.issue_id;  
"""

const val SEARCH_ISSUES_BY_FILTER_BODY: String = """
select i.id from issue i"""

const val FILTER_PART_ASSIGNED_BY_ME: String = """
inner join assigned a on i.id = a.issue_id and a.user_id = :assigned_user_id"""

const val FILTER_PART_COMMENT_BY_ME: String = """
inner join comment c on i.id = c.issue_id and c.author_id = :comment_author_id"""

const val FILTER_PART_IS_OPENED: String = """
where i.is_open = :is_open"""

const val FILTER_PART_ISSUE_AUTHOR: String = """
and i.author_id = :issue_author_id"""

const val FILTER_PART_END_OF_QUERY: String = """
group by i.id;"""
