package com.ldbmcs.marsdgs.ui.graphql.resolver.query.user;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserQueryResolverTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    void users() {
        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(
                " { shows { title releaseYear }}",
                "data.shows[*].title");
        assertThat(titles).contains("Ozark");
    }

    @Test
    void me() {
    }
}