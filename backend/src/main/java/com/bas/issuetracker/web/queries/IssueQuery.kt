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

