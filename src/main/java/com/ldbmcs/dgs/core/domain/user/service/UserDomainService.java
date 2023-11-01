package com.ldbmcs.dgs.core.domain.user.service;

import com.ldbmcs.dgs.core.domain.user.models.User;
import com.ldbmcs.dgs.core.domain.user.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDomainService {
    @Autowired
    private UserMapper userMapper;

    public Map<String, User> findAllById(Set<String> ids) {
        List<User> users = userMapper.selectBatchIds(ids);
        return users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    public User selectById(String id) {
        return userMapper.selectById(id);
    }
}
