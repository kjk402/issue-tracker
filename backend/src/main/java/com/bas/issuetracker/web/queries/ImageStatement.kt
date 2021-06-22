package com.bas.issuetracker.web.queries

const val CREATE_IMAGE: String = """
insert into image (image_url) values (:image_url);
"""

const val UPDATE_IMAGE: String = """
update image
set 
    image_url = :image_url,
    issue_id = :issue_id
where id = :image_id;
"""

const val FIND_IMAGES_BY_ISSUE_ID: String = """
select id, image_url, issue_id
from image
where issue_id = :issue_id
"""

const val UPDATE_ISSUE_ID: String = """
update image
set
    issue_id = :issue_id
where id = :image_id;
"""

