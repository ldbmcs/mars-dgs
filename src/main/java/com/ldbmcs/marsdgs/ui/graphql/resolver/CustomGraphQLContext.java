package com.ldbmcs.marsdgs.ui.graphql.resolver;

import com.ldbmcs.marsdgs.core.domain.user.models.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static graphql.Assert.assertNotNull;

@SuppressWarnings("unchecked")
public class CustomGraphQLContext {

    private static final String CURRENT_USER_GRAPHQL_CONTEXT_KEY = "currentUser";

    private final ConcurrentMap<String, Object> map = new ConcurrentHashMap<>();

    public CustomGraphQLContext add(String key, Object value) {
        map.put(assertNotNull(key), assertNotNull(value));
        return this;
    }

    public <T> T get(String key) {
        return (T) map.get(assertNotNull(key));
    }

    public void addCurrentUser(User curentUser) {
        if (curentUser != null) {
            this.add(CURRENT_USER_GRAPHQL_CONTEXT_KEY, curentUser);
        }
    }

    public User getCurrentUser() {
        return this.get(CURRENT_USER_GRAPHQL_CONTEXT_KEY);
    }
}

