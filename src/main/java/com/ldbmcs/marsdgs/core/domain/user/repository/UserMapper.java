package com.ldbmcs.marsdgs.core.domain.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldbmcs.marsdgs.core.domain.user.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
