package com.ldbmcs.dgs.core.app.service.user;

import com.ldbmcs.dgs.core.domain.auth.service.IdentityDomainService;
import com.ldbmcs.dgs.core.domain.user.models.User;
import com.ldbmcs.dgs.core.domain.user.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAppService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdentityDomainService identityDomainService;

    @Transactional
    public User signUp(String mobile, String password, String name) {
        User user = User.of(mobile, name);
        userMapper.insert(user);
        identityDomainService.insert(user, password);
        return user;
    }
}
