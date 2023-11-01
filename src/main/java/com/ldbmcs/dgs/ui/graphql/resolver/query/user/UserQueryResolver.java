package com.ldbmcs.dgs.ui.graphql.resolver.query.user;

import com.ldbmcs.dgs.core.domain.user.models.User;
import com.ldbmcs.dgs.core.domain.user.repository.UserMapper;
import com.ldbmcs.dgs.ui.graphql.resolver.SecuredResolver;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

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
