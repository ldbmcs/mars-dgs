package com.ldbmcs.dgs.core.domain.auth.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ldbmcs.dgs.core.domain.user.models.User;
import com.ldbmcs.dgs.core.domain.user.service.UserDomainService;
import com.ldbmcs.dgs.infra.redis.RedisTemplateHelper;
import java.time.Duration;
import java.util.Locale;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthTokenDomainService {
    @Value("${secret.token-expire-days}")
    private Integer expireDays;

    @Autowired
    private UserDomainService userDomainService;
    @Autowired
    private RedisTemplateHelper redisTemplateHelper;

    public static final String SESSION_ID = "SESSION_ID:%s";

    public String generate(String userId) {
        return generate(userId, Duration.ofDays(expireDays));
    }

    private String generate(String accountUserId, Duration expireIn) {
        String token = IdWorker.get32UUID().toUpperCase(Locale.ROOT);
        redisTemplateHelper.set(getSessionKey(token), accountUserId, expireIn);
        return token;
    }

    public void refresh(String token) {
        Optional.ofNullable(this.parse(token)).ifPresent(accountUser ->
                redisTemplateHelper.set(getSessionKey(token), accountUser.getId(), Duration.ofDays(expireDays)));
    }

    public User parse(String token) {
        return getUserFromToken(token);
    }

    private User getUserFromToken(String token) {
        String key = getSessionKey(token);
        return Optional.ofNullable(redisTemplateHelper.get(key))
                .map(userId -> userDomainService.selectById(String.valueOf(userId)))
            .orElse(null);
    }

    private String getSessionKey(String token) {
        return String.format(SESSION_ID, token);
    }
}
