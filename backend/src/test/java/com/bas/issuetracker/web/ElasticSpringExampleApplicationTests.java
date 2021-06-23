package com.bas.issuetracker.web;

import com.bas.issuetracker.web.config.elasticSearch.ElasticApi;
import com.bas.issuetracker.web.domain.issue.Issue;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSpringExampleApplicationTests {

    @Autowired
    ElasticApi elasticApi;

    private final String ELASTIC_INDEX = "test_index";
    private final String ELASTIC_TYPE = "test_type";


    @Test
    public void 엘라스틱서치_POST_전송() {
        String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE;
        Issue issue = new Issue();
        issue.setId(11);
        issue.setTitle("test");
        issue.setOpen(true);
        issue.setAuthorId(1);
        issue.setMilestoneId(1);
        issue.setLastModifiedDateTime(LocalDateTime.now());

        Map<String, Object> result = elasticApi.callElasticApi("POST", url, issue, null);
        System.out.println(result.get("resultCode"));
        System.out.println(result.get("resultBody"));
    }

    @Test
    public void 앨라스틱서치_GET_전송() {
        String title = "test";
        String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE+"/"+title;
        Map<String, Object> result = elasticApi.callElasticApi("GET", url, null, null);
        System.out.println(result.get("resultCode"));
        System.out.println(result.get("resultBody"));
    }

}
