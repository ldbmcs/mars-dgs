package com.ldbmcs.dgs.core.domain.auth.service;

import com.ldbmcs.dgs.common.utils.BCryptUtil;
import com.ldbmcs.dgs.core.domain.auth.models.Identity;
import com.ldbmcs.dgs.core.domain.auth.repository.IdentityMapper;
import com.ldbmcs.dgs.core.domain.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentityDomainService {

    @Autowired
    private IdentityMapper identityMapper;

    public Identity selectByPrincipal(String principal) {
        return identityMapper.selectByPrincipal(principal);
    }

    public void insert(User user, String password) {
        Identity identity = Identity.of(user.getId(), user.getMobile(), BCryptUtil.encode(password));
        identityMapper.insert(identity);
    }
}
