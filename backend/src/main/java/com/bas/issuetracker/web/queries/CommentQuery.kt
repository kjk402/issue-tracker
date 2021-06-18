package com.bas.issuetracker.web.queries;

const val SELECT_COMMENT_BY_ISSUE_ID: String = """
SELECT c.id, c.content, c.last_modified_date_time, u.id AS user_id, u.nickname, u.name, u.profile_image
FROM comment AS c
inner JOIN user u ON u.id = c.author_id
WHERE c.issue_id = :issue_id AND c.author_id = u.id;  
"""

const val CREATE_COMMENT: String = """
INSERT INTO comment (content, issue_id, author_id, deletable, last_modified_date_time)
VALUES (:content, :issue_id, :author_id, :deletable, :last_modified_date_time);  
"""

const val UPDATE_COMMENT: String = """
UPDATE comment SET content =:content, last_modified_date_time = now() WHERE id =:comment_id;
"""

const val FIND_DELETABLE_COMMENT: String = """
SELECT deletable FROM comment WHERE id =:comment_id;
"""

const val DELETE_COMMENT: String = """
DELETE c FROM comment c WHERE c.id=:comment_id AND c.deletable = 1; 
"""
