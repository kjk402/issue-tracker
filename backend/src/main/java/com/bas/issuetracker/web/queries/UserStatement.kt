package com.bas.issuetracker.web.queries

const val SAVE_USER: String = """
insert into `user` (oauth_id, authenticated_by, nickname, name, profile_image, email, access_token)
values (:oauth_id, :authenticated_by, :nickname, :name, :profile_image, :email,:access_token);
"""

const val FIND_USER_BY_AUTHENTICATED_BY_AND_OAUTH_ID: String = """
select id, nickname, name, profile_image, access_token, oauth_id, authenticated_by
from user
where authenticated_by = :authenticated_by
  and oauth_id = :oauth_id;
"""

const val FIND_USER_BY_ID: String = """
select id, nickname, name, profile_image, access_token, oauth_id, authenticated_by
from user
where id = :id;
"""

const val UPDATE_TOKEN: String = """
update user
set access_token = :access_token
where id = :id;
"""

const val FIND_USERS_BY_ASSIGNED_ID: String= """
SELECT u.id AS user_id, u.nickname, u.name, u.profile_image
FROM user u
inner JOIN assigned ON assigned.user_id = u.id
inner JOIN issue i ON i.id = assigned.issue_id
WHERE i.id = :issue_id AND u.id = assigned.user_id;
"""

const val FIND_ALL_USERS: String = """
SELECT u.id AS user_id, u.nickname, u.name, u.profile_image
FROM user u
"""
