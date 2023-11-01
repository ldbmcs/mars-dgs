package com.ldbmcs.dgs.core.domain.auth.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldbmcs.dgs.core.domain.auth.models.Identity;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityMapper extends BaseMapper<Identity> {
    default Identity selectByPrincipal(String principal) {
        return selectOne(new LambdaQueryWrapper<Identity>().eq(Identity::getPrincipal, principal));
    }
}
