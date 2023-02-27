package com.ldbmcs.marsdgs.ui.graphql.resolver.query.user;

import com.ldbmcs.marsdgs.core.domain.user.models.User;
import com.ldbmcs.marsdgs.core.domain.user.repository.UserMapper;
import com.ldbmcs.marsdgs.ui.graphql.resolver.SecuredResolver;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class UserQueryResolver implements SecuredResolver {
    @Autowired
    private UserMapper userMapper;

    @DgsQuery
    public List<User> users() {
        return userMapper.selectList(null);
    }

    @DgsQuery
    public User me() {
        return getCurrentUser();
    }
}
