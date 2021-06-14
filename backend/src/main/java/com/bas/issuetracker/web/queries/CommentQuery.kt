package com.bas.issuetracker.web.queries;

const val SELECT_COMMENT_BY_ISSUE_ID: String = """
SELECT c.content, c.last_modified_date_time, u.nickname, u.name, u.profile_image
FROM comment AS c
inner JOIN user u ON u.id = c.author_id
WHERE c.issue_id = :issue_id AND c.author_id = u.id;  
"""
